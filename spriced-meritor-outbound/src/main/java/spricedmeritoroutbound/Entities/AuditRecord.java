package spricedmeritoroutbound.Entities;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "audit_record")
public class AuditRecord {



    public enum TransactionType {
        INBOUND_FILE, OUTBOUND_FILE, ACKNOWLEDGMENT, ADHOC_DOWNLOAD, MAIL_ATTACHMENT
    }

    public enum Source {
        SPRICED_APPLICATION,
        CFG,
        ERP,
        MAILED_BY_USER,
        MAILED_BY_SUPPORT
    }

    public enum Destination {
        SPRICED, CFG, ERP, DOWNLOADED_BY_USER, MAILED_TO_RECIPIENT
    }

    public enum ActionType {
        SYSTEM_ACTION, USER_ACTION
    }

    public enum InformationType {
        FULL_LOAD, INCREMENTAL, ADHOC
    }

    public enum Status {
        DONE, IN_PROGRESS, FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", columnDefinition = "TEXT")
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", columnDefinition = "TEXT")
    private Source source;

    @Enumerated(EnumType.STRING)
    @Column(name = "destination", columnDefinition = "TEXT")
    private Destination destination;

    @Column(name = "file_name", columnDefinition = "TEXT")
    private String fileName;

    @Column(name = "file_size", columnDefinition = "BIGINT")
    private long fileSize;

    @Column(name = "encryption_flag", columnDefinition = "TEXT")
    private String encryptionFlag;
    
    @Column(name = "encryption_type", columnDefinition = "TEXT")
    private String encryptionType;

    @Column(name = "checksum", columnDefinition = "TEXT")
    private String checksum;

    @Enumerated(EnumType.STRING)
    @Column(name = "information_type", columnDefinition = "TEXT")
    private InformationType informationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", columnDefinition = "TEXT")
    private ActionType actionType;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant endTime;

    @Column(name = "processing_time", columnDefinition = "double precision")
    private double processingTime;

    @Column(name = "number_of_records", columnDefinition = "INTEGER")
    private int numberOfRecords;

    @Column(name = "records_after_removing_duplicates", columnDefinition = "INTEGER")
    private int recordsAfterRemovingDuplicates;

    @Column(name = "records_processed", columnDefinition = "INTEGER")
    private Integer recordsProcessed;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "TEXT")
    private Status status;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getEncryptionFlag() {
        return encryptionFlag;
    }

    public void setEncryptionFlag(String encryptionFlag) {
        this.encryptionFlag = encryptionFlag;
    }
    
    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public InformationType getInformationType() {
        return informationType;
    }

    public void setInformationType(InformationType informationType) {
        this.informationType = informationType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
    
    public Timestamp getStartTimeAsTimestamp() {
        return startTime != null ? Timestamp.from(startTime) : null;
    }

    public Timestamp getEndTimeAsTimestamp() {
        return endTime != null ? Timestamp.from(endTime) : null;
    }

    public double getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(double processingTime) {
        this.processingTime = processingTime;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public int getRecordsAfterRemovingDuplicates() {
        return recordsAfterRemovingDuplicates;
    }

    public void setRecordsAfterRemovingDuplicates(int recordsAfterRemovingDuplicates) {
        this.recordsAfterRemovingDuplicates = recordsAfterRemovingDuplicates;
    }

    public int getRecordsProcessed() {
        return recordsProcessed;
    }

    public void setRecordsProcessed(Integer recordsProcessed) {
        this.recordsProcessed = recordsProcessed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

