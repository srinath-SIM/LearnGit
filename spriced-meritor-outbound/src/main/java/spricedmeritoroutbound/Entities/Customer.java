package spricedmeritoroutbound.Entities;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spricedmeritoroutbound.Entities.AuditRecord.ActionType;
import spricedmeritoroutbound.Entities.AuditRecord.Destination;
import spricedmeritoroutbound.Entities.AuditRecord.InformationType;
import spricedmeritoroutbound.Entities.AuditRecord.Source;
import spricedmeritoroutbound.Entities.AuditRecord.TransactionType;
import spricedmeritoroutbound.Repository.MainRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;

@Component
public class Customer extends RouteBuilder {

    @Value("${PGP.EMAIL}")
    private String email;

    @Value("${PGP.KEY.PATH}")
    private String pgpKeyPath;

    @Value("${FILE.PATH}")
    private String filePath;

    @Value("${COMPRESSED.FILE.PATH}")
    private String compressFilePath;

    @Value("${POLL.ENRICH.SETTINGS}")
    private String pollEnrichSetting;

    @Value("${PASSPHRASE}")
    private String passPhrase;

    @Value("${SFTP.USER}")
    private String user;

    @Value("${SFTP.HOST}")
    private String host;

    @Value("${SFTP.PRIVATE.KEY}")
    private String sftpPrivateKey;

    @Value("${DESTINATION}")
    private String destination;

    @Value("${SFTP.PORT}")
    private String sftpPort;

    @Value("${ENCRYPTED.FILE.PATH}")
    private String encryptPath;

    @Autowired
    MainRepository repo;

    @Override
    public void configure() throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
        String date=sdf.format(new Date());
        String fileName="AUSNDISC_"+date;
//        final List<String> columnHeaders = Arrays.asList("chainc","chnl","cbseg","grp","mngrp","subgrp_d","mngrp_d","shpto_d","sldto_d","chaind","chnldesc","grp_d","shpto","sldto","custid","name","custid_d","custsts","csubtype","ctype","export","fds","pdcflag","sales","bgrp","cprog","cdisccd","cdiscp","curr","subgrp","cusgrp","region","catcd","geo","xrefcd","housef","mkupf","mercf","discf","euccf","eucprcl","mmexcl","updated_by","updated_date","record_action");
        final List<String> columnHeaders = Arrays.asList("custid", "cdisccd", "cdiscp", "geo", "raction");
        String numRow=((Long)repo.getNumRowsCust()).toString();

        CsvDataFormat csv = new CsvDataFormat();
        csv.setHeader(columnHeaders);

        from("cron:customer?schedule=0+37+13+*+*+?")
                .marshal(csv)
                .to(filePath + "?FileExist=Append&fileName=" + fileName+"_"+numRow+".csv")
//                .to("sql:select chainc,chnl,cbseg,grp,mngrp,subgrp_d,mngrp_d,shpto_d,sldto_d,chaind,chnldesc,grp_d,shpto,sldto,custid,name,custid_d,custsts,csubtype,ctype,export,fds,pdcflag,sales,bgrp,cprog,cdisccd,ROUND(cdiscp::numeric,2) AS cdiscp,curr,subgrp,cusgrp,region,catcd,geo,xrefcd,housef,mkupf,mercf,discf,euccf,eucprcl,mmexcl,updated_by,TO_CHAR(DATE(updated_date),'MM-DD-YYYY') AS updated_date,(select 'R' as record_action) from customer WHERE DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day') LIMIT 2000?maxMessagesPerPoll=1000&outputType=StreamList&batch=true")
                .to("sql:SELECT custid,cdisccd,ROUND(cdiscp::numeric, 2) AS cdiscp, 'US' AS geo, 'R' AS raction FROM customer WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) AND custid_d = 'SOLDTO' and geo = 'US''?maxMessagesPerPoll=1000&outputType=StreamList&batch=true")
                .onCompletion()
                .onCompleteOnly()
                .pollEnrich(filePath + "?fileName=" + fileName +"_"+numRow+".csv"+pollEnrichSetting)
                .marshal().gzipDeflater()//.marshal().pgp(pgpKeyPath,email,pgpPassPhrase)
                .to(compressFilePath+"?fileName="+fileName+"_"+numRow+".csv.gz")
                .marshal()
                .pgp(pgpKeyPath,email)
                .to(encryptPath+"?fileName="+fileName+"_"+numRow+".csv.gz.gpg")
                .log("File encrypted: ${header.CamelFileName}")
                .to(String.format("sftp://"+user+"@"+host+":"+sftpPort+destination+"?fileName="+fileName+"_"+numRow+".csv.gz.gpg&privateKeyFile="+sftpPrivateKey+"&privateKeyPassphrase="+passPhrase))
                .process(exchange -> {
                	
                	// Get current UTC time
                    Instant startTime = Instant.now();
                    ZoneId utcZone = ZoneId.of("UTC");
                    ZonedDateTime utcTime = ZonedDateTime.ofInstant(startTime, utcZone);

                    // Convert UTC Instant to Timestamp for SQL compatibility
                    Timestamp startTimestamp = Timestamp.from(startTime);

                    // Format Local Time for UI Display
                    ZoneId localZone = ZoneId.systemDefault();
                    ZonedDateTime localTime = utcTime.withZoneSameInstant(localZone);
                    DateTimeFormatter localFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    String localFormatted = localTime.format(localFormatter);

                    System.out.println("Displayed Local Time (UI): " + localFormatted);
                    
                 // Capture end time after the process completes
                    Instant endTime = Instant.now();
                    ZonedDateTime endUtcTime = ZonedDateTime.ofInstant(endTime, utcZone);
                    Timestamp endTimestamp = Timestamp.from(endTime);

                    // Format Local Time for UI Display (both start and end times)
                    ZoneId endlocalZone = ZoneId.systemDefault();
                    DateTimeFormatter endlocalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    
                    // Calculate processing time
                    Duration processingDuration = Duration.between(startTime, endTime);

                    
                    
                 // Extract seconds with proper casting
//                  long milliseconds = processingDuration.toMillis();
                    long seconds = processingDuration.toMillis() / 1000;
                    
                    
                    // Audit logic
                	Integer numberOfRecords = null;
                	if (Integer.parseInt(numRow) > 0) {
                	    numberOfRecords = Integer.parseInt(numRow) - 1;
                	}
                 // Get the file name from exchange header
                	String path = encryptPath +fileName + "_" + numRow + ".csv.gz.gpg";

                 // Determine if the file is encrypted based on its extension (e.g., ".gpg")
                    boolean isEncrypted = path != null && path.endsWith(".gpg");

                    // Set encryption flag and encryption type
                    String encryptionFlag = isEncrypted ? "YES" : "NO";
                    String encryptionType = isEncrypted ? "GPG" : "NONE";

                    TransactionType transactionType = TransactionType.OUTBOUND_FILE; // Example: Dynamically set as OUTBOUND_FILE
                    Source source = Source.SPRICED_APPLICATION;
                    Destination destination = Destination.CFG;
                    InformationType informationType = InformationType.INCREMENTAL;
                    ActionType actionType = ActionType.SYSTEM_ACTION;
                	
                	
                

                       
                    Map<String, Object> sqlParams = new HashMap<>();
                    sqlParams.put("transaction_type", transactionType.name());
                    sqlParams.put("source", source.name());
                    sqlParams.put("destination", destination.name());
                    sqlParams.put("information_type", informationType.name());
                    sqlParams.put("action_type", actionType.name());
                    sqlParams.put("file_name", fileName + "_" + numRow + ".csv");
                    sqlParams.put("file_name", fileName + "_" + numRow + ".csv");

                    
                    
                    sqlParams.put("encryption_flag", encryptionFlag);  // Set the encryption flag (YES/NO)
                    sqlParams.put("encryption_type", encryptionType);
                    sqlParams.put("number_of_records", numberOfRecords);
                    sqlParams.put("start_time", startTimestamp);
                    sqlParams.put("end_time", endTimestamp);
                    sqlParams.put("processing_time", seconds);
                    
                    


                    exchange.getContext().createProducerTemplate().sendBody(
                            "sql:INSERT INTO audit_record (file_name, transaction_type, source, destination, information_type, action_type, encryption_flag, encryption_type, number_of_records, start_time, end_time, processing_time) VALUES (:#file_name, :#transaction_type, :#source, :#destination, :#information_type, :#action_type, :#encryption_flag, :#encryption_type, :#number_of_records, :#start_time, :#end_time, :#processing_time)",
                            sqlParams
                        );
                })
                .split(body())
                .streaming()
                .marshal()
                .csv()
                .to(filePath + "?FileExist=Append&fileName=" + fileName+"_"+numRow+".csv")
                .end();
    }
}
