package com.centit.powerbase.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class Suppowerchglog implements java.io.Serializable {
    private static final long serialVersionUID =  1L;


    private String changeId;
    private String  itemId;
    private Long  version;
    private String  chgReason;
    private String  chgContent;
    private Date  requestTime;
    private String  requester;
    private String  chgResult;
    private Date  auditTime;
    private Date  begTime;
    private String  auditor;
    private String  auditContent;
    private String  chgType;
    private String  replyPeople;
    private Date  replyTime;
    private String  replyContent;
    
    public Date getBegTime() {
        return begTime;
    }
    public void setBegTime(Date begTime) {
        this.begTime = begTime;
    }

    private Suppower suppower;
//  private Set<Suppower> suppowers = null;
    public Suppower getSuppower() {
        return suppower;
    }
    public void setSuppower(Suppower suppower) {
        this.suppower = suppower;
    }

    

    // Constructors
    /** default constructor */
    public Suppowerchglog() {
    }
    /** minimal constructor */
    public Suppowerchglog(
            String changeId       
        ) {
    
    
        this.changeId = changeId;       
            
    }

/** full constructor */
    public Suppowerchglog(
            String changeId      
    ,String  itemId,Long  version,String  chgReason,String  chgContent,Date  requestTime,Date  beginTime,String  requester,String  chgResult,Date  auditTime,String  auditor,String  auditContent,String  chgType,String  replyPeople,Date  replyTime,String  replyContent) {
    
    
        this.changeId = changeId;       
    
        this.itemId= itemId;
        this.version= version;
        this.chgReason= chgReason;
        this.chgContent= chgContent;
        this.requestTime= requestTime;
        this.requester= requester;
        this.chgResult= chgResult;
        this.auditTime= auditTime;
        this.auditor= auditor;
        this.auditContent= auditContent;
        this.chgType= chgType;
        this.replyPeople= replyPeople;
        this.replyTime= replyTime;
        this.begTime= beginTime;
        this.replyContent= replyContent;        
    }
    
  
    public String getChangeId() {
        return this.changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }
    // Property accessors
  
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
  
    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
  
    public String getChgReason() {
        return this.chgReason;
    }
    
    public void setChgReason(String chgReason) {
        this.chgReason = chgReason;
    }
  
    public String getChgContent() {
        return this.chgContent;
    }
    
    public void setChgContent(String chgContent) {
        this.chgContent = chgContent;
    }
  
    public Date getRequestTime() {
        return this.requestTime;
    }
    
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
  
    public String getRequester() {
        return this.requester;
    }
    
    public void setRequester(String requester) {
        this.requester = requester;
    }
  
    public String getChgResult() {
        return this.chgResult;
    }
    
    public void setChgResult(String chgResult) {
        this.chgResult = chgResult;
    }
  
    public Date getAuditTime() {
        return this.auditTime;
    }
    
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
  
    public String getAuditor() {
        return this.auditor;
    }
    
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }
  
    public String getAuditContent() {
        return this.auditContent;
    }
    
    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }
  
    public String getChgType() {
        return this.chgType;
    }
    
    public void setChgType(String chgType) {
        this.chgType = chgType;
    }
  
    public String getReplyPeople() {
        return this.replyPeople;
    }
    
    public void setReplyPeople(String replyPeople) {
        this.replyPeople = replyPeople;
    }
  
    public Date getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
  
    public String getReplyContent() {
        return this.replyContent;
    }
    
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }



    public void copy(Suppowerchglog other){
  
        this.setChangeId(other.getChangeId());
  
        this.itemId= other.getItemId();  
        this.version= other.getVersion();  
        this.chgReason= other.getChgReason();  
        this.chgContent= other.getChgContent();  
        this.requestTime= other.getRequestTime();  
        this.requester= other.getRequester();  
        this.chgResult= other.getChgResult();  
        this.auditTime= other.getAuditTime();  
        this.auditor= other.getAuditor();  
        this.auditContent= other.getAuditContent();  
        this.chgType= other.getChgType();  
        this.replyPeople= other.getReplyPeople();  
        this.replyTime= other.getReplyTime();  
        this.replyContent= other.getReplyContent();
        this.begTime=other.getBegTime();

    }
    
    public void copyNotNullProperty(Suppowerchglog other){
  
    if( other.getChangeId() != null)
        this.setChangeId(other.getChangeId());
  
        if( other.getItemId() != null)
            this.itemId= other.getItemId();  
        if( other.getVersion() != null)
            this.version= other.getVersion();  
        if( other.getChgReason() != null)
            this.chgReason= other.getChgReason();  
        if( other.getChgContent() != null)
            this.chgContent= other.getChgContent();  
        if( other.getRequestTime() != null)
            this.requestTime= other.getRequestTime();  
        if( other.getRequester() != null)
            this.requester= other.getRequester();  
        if( other.getChgResult() != null)
            this.chgResult= other.getChgResult();  
        if( other.getAuditTime() != null)
            this.auditTime= other.getAuditTime();  
        if( other.getAuditor() != null)
            this.auditor= other.getAuditor();  
        if( other.getAuditContent() != null)
            this.auditContent= other.getAuditContent();  
        if( other.getChgType() != null)
            this.chgType= other.getChgType();  
        if( other.getReplyPeople() != null)
            this.replyPeople= other.getReplyPeople();  
        if( other.getReplyTime() != null)
            this.replyTime= other.getReplyTime();  
        if( other.getReplyContent() != null)
            this.replyContent= other.getReplyContent();
        if( other.getBegTime() != null)
            this.begTime= other.getBegTime();

    }
    
    public void clearProperties(){
  
        this.itemId= null;  
        this.version= null;  
        this.chgReason= null;  
        this.chgContent= null;  
        this.requestTime= null;  
        this.requester= null;  
        this.chgResult= null;  
        this.auditTime= null;  
        this.auditor= null;  
        this.auditContent= null;  
        this.chgType= null;  
        this.replyPeople= null;  
        this.replyTime= null;  
        this.replyContent= null;
        this.begTime=null;
    }
}
