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
import spricedmeritoroutbound.Entities.AuditRecord.Status;
import spricedmeritoroutbound.Entities.AuditRecord.TransactionType;
import spricedmeritoroutbound.Repository.MainRepository;

import spricedmeritoroutbound.Service.FileChecksumUtil;
import spricedmeritoroutbound.Service.SftpClient;

import java.io.File;
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
import java.util.stream.Collectors;

@Component
public class ListPrice extends RouteBuilder {

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

    @Autowired
    MainRepository repo;

    @Value("${ENCRYPTED.FILE.PATH}")
    private String encryptPath;

    @Override
    public void configure() throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
        String date=sdf.format(new Date());
        String fileName="AUSLPRC_"+date;
        final List<String> columnHeaders = Arrays.asList("partnum", "price", "fromdat", "raction");
        String numRow=((Long)repo.getNumRowsListPrice()).toString();

        CsvDataFormat csv = new CsvDataFormat();
        csv.setHeader(columnHeaders);

        from("cron:listPrice?schedule=0+20+13+*+*+?")
                .marshal(csv)
                .to(filePath + "processed/?FileExist=Append&fileName=" + fileName+"_"+numRow+".csv")
                .to("sql:SELECT partnum,ROUND(lpriceus,2) AS lpriceus,TO_CHAR(DATE(fromdat),'MM/DD/YYYY') AS fromdat,(select 'R' as record_action) from list_price WHERE (DATE(updated_date) = CURRENT_DATE OR DATE(updated_date) = (CURRENT_DATE - INTERVAL '1 day')) AND partnum is not null?maxMessagesPerPoll=1000&outputType=StreamList&batch=true")
                .onCompletion()
                .onCompleteOnly()
                .process(exchange -> {
                	
					String normalizedFilePath = filePath.replace("file:///", "");
				    // Get the file object from the exchange
				    File file = new File(normalizedFilePath, "processed/"+fileName + "_" + numRow + ".csv");
				    if (file.exists() && file.isFile()) {
				    	
				        // Calculate the file size in bytes
				        long fileSize = file.length();
				        exchange.setProperty("fileSize", fileSize);
				        
				     // Calculate the file checksum (SHA-256)
				        String checksum = FileChecksumUtil.calculateChecksum(file);
				        exchange.setProperty("fileChecksum", checksum);
				        
				        log.info("Generated File: " + file.getName() + ", Size: " + fileSize + " bytes ");
				        log.info("Generated File: " + file.getName() + ", Checksum: " + checksum);
				    }
				    else {
				    	log.info("File does not exist: " + file.getAbsolutePath());
				    }
				    
				    List<Map<String, Object>> data = exchange.getIn().getBody(List.class);

				    if (data != null) {
				        List<Map<String, Object>> distinctRecords = data.stream()
				            .distinct()
				            .collect(Collectors.toList());

				        int recordsAfterRemovingDuplicates = distinctRecords.size();
				        log.info("recordsAfterRemovingDuplicates " + recordsAfterRemovingDuplicates);

				        exchange.setProperty("uniqueRecordCount", recordsAfterRemovingDuplicates);
				    }
                	
 
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
                    log.info("Displayed Local Time (UI): " + localFormatted);
                    
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
					
					
					// Construct the encrypt file path
					String encryptFilePath = encryptPath.replace("file:///", "");
					File encryptfile = new File(encryptFilePath, fileName + "_" + numRow + ".csv.gz.gpg");
					// Construct the file path
					String enc = encryptfile.getAbsolutePath();
					// Check if the file exists and is a file
					boolean fileExists = encryptfile.isFile();

					// Determine encryption details based on file existence and extension
					String encryptionFlag = fileExists ? "YES" : "NO";
					String encryptionType = fileExists && enc.endsWith(".gpg") ? "GPG" : "NONE";

					// Log the encryption details
					log.info("Encryption Flag: " + encryptionFlag);
					log.info("Encryption Type: " + encryptionType);
					exchange.setProperty("encryptionFlag", encryptionFlag);
	                exchange.setProperty("encryptionType", encryptionType);
	                
	                
	              //sftp
					SftpClient sftpClient = new SftpClient(host, sftpPort, user, sftpPrivateKey, passPhrase);
					
					// Construct the remote path dynamically based on DESTINATION
					String remotePath = destination.endsWith("/") 
					    ? destination + fileName + "_" + numRow + ".csv.gz.gpg" 
					    : destination + "/" + fileName + "_" + numRow + ".csv.gz.gpg";
					
					log.info("Constructed remote path: " + remotePath);
					
					Status sftpStatus = sftpClient.fileStatus(remotePath);
					if (sftpStatus != null) {
					    exchange.setProperty("sftpStatus", sftpStatus);
					} else {
					    // Handle the case where status might not be set
					    exchange.setProperty("sftpStatus", Status.FAILED);  // Default status
					}
					
					String statusString = exchange.getProperty("sftpStatus") != null 
		                      ? exchange.getProperty("sftpStatus").toString() 
		                      : Status.FAILED.toString();  // Fallback status

					// Check if the file exists
					boolean sftpExists = sftpClient.fileExists(remotePath);
					log.info("notes: " + (sftpExists ? "file uploaded" : "file not uploaded"));

                    TransactionType transactionType = TransactionType.OUTBOUND_FILE;
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
                    sqlParams.put("encryption_flag", exchange.getProperty("encryptionFlag"));
                    sqlParams.put("encryption_type", exchange.getProperty("encryptionType"));
                    sqlParams.put("number_of_records", numberOfRecords);
                    sqlParams.put("start_time", startTimestamp);
                    sqlParams.put("end_time", endTimestamp);
                    sqlParams.put("processing_time", seconds);
                    sqlParams.put("file_size", exchange.getProperty("fileSize"));
                    sqlParams.put("checksum", exchange.getProperty("fileChecksum")); 
                    sqlParams.put("records_after_removing_duplicates", exchange.getProperty("uniqueRecordCount"));
                    sqlParams.put("notes", sftpExists ? "File uploaded to SFTP server" : "File not found on SFTP server");
                    sqlParams.put("status", statusString);
                    

                    exchange.getContext().createProducerTemplate().sendBody(
                            "sql:INSERT INTO audit_record (file_name, transaction_type, source, destination, information_type, action_type,  number_of_records, encryption_flag, encryption_type, start_time, end_time, processing_time, file_size, checksum, records_after_removing_duplicates, notes, status) VALUES (:#file_name, :#transaction_type, :#source, :#destination, :#information_type, :#action_type, :#number_of_records, :#encryption_flag, :#encryption_type, :#start_time, :#end_time, :#processing_time, :#file_size, :#checksum, :#records_after_removing_duplicates, :#notes, :#status)",
                            sqlParams
                        );
                })
                .pollEnrich(filePath + "?fileName=" + fileName +"_"+numRow+".csv"+ pollEnrichSetting)
                .marshal().gzipDeflater()//.marshal().pgp(pgpKeyPath,email,pgpPassPhrase)
                .to(compressFilePath+"?fileName="+fileName+"_"+numRow+".csv.gz")
                .marshal()
                .pgp(pgpKeyPath,email)
                .to(encryptPath+"?fileName="+fileName+"_"+numRow+".csv.gz.gpg")
                .to(String.format("sftp://"+user+"@"+host+":"+sftpPort+destination+"?fileName="+fileName+"_"+numRow+".csv.gz.gpg&privateKeyFile="+sftpPrivateKey+"&privateKeyPassphrase="+passPhrase))                .end()
                .split(body())
                .streaming()
                .marshal()
                .csv()
                .to(filePath + "?FileExist=Append&fileName=" + fileName+"_"+numRow+".csv")
                .end();
    }
}
