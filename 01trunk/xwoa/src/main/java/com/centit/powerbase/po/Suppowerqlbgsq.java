package com.centit.powerbase.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class Suppowerqlbgsq implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String  itemId;
    private Long  version;
	private Date  beginTime;
	private Date  endTime;
	private String  orgId;
	private String  itemName;
	private String  itemStaName;
	private String  itemType;
	private Long  timeLimit;
	private String  isNetwork;
	private String  isFormula;
	private String  phone;
	private String  address;
	private Date  lastmodifytime;
	private String  auditSign;
	private String  monitorPhone;
	private String  acceptLink;
	private Long  legalTimeLimit;
	private String  charge;
	private String  formName;
	private String  fileName;
	private String  inFlowImgName;
	private String  ischarge;
	private String  punishClass;
	private String  transactDepname;
	private String  promiseType;
	private String  anticipateType;
	private String  qlDepid;
	private String  qlDepstate;
	private String  entrustName;
	private String  qlState;
	private String  replyPeople;
	private String  auditor;
	private String  requester;
	private String chgType;
	
	
	private String chgContent;
	private String chgResult;
	private Date begTime;
	private String chgReason;
	private String replyContent;
	private String auditContent;
	private Date requestTime;
	private Date auditTime;
	private Date replyTime;
	private String changeId;
	
	
    public Date getBegTime() {
        return begTime;
    }
    public void setBegTime(Date begTime) {
        this.begTime = begTime;
    }
    // Constructors
    /** default constructor */
    public Suppowerqlbgsq() {
    }
    /** minimal constructor */
    public Suppowerqlbgsq(
        String changeId     
        ) {
    
    
        this.changeId = changeId;       
            
    }
	public String getChgContent() {
        return chgContent;
    }
    public void setChgContent(String chgContent) {
        this.chgContent = chgContent;
    }
    public String getChgResult() {
        return chgResult;
    }
    public void setChgResult(String chgResult) {
        this.chgResult = chgResult;
    }
    public String getChgReason() {
        return chgReason;
    }
    public void setChgReason(String chgReason) {
        this.chgReason = chgReason;
    }
    public String getReplyContent() {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    public String getAuditContent() {
        return auditContent;
    }
    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }
    public Date getRequestTime() {
        return requestTime;
    }
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
    public Date getAuditTime() {
        return auditTime;
    }
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
    public Date getReplyTime() {
        return replyTime;
    }
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

/** full constructor */
	public Suppowerqlbgsq(String  itemId,Long  version,Date  beginTime,Date  endTime,String  orgId,String  itemName,String  itemStaName,String  itemType,
	Long timeLimit,String  isNetwork,String  isFormula,String  phone,String  address,Date 
	lastmodifytime,String  auditSign,String  monitorPhone,String  acceptLink,
	Long  legalTimeLimit,String  charge,String  formName,String  fileName,
	String  inFlowImgName,String  ischarge,String  punishClass,String  transactDepname,
	String  promiseType,String  anticipateType,String  qlDepid,String  qlDepstate,String  entrustName,
	String  qlState,String  replyPeople,String  auditor,String  requester,String chgType,
	String chgContent,String chgResult,String chgReason,String replyContent,
   String auditContent,Date requestTime,Date auditTime,Date replyTime,String changeId,Date begTime) {
			
	    this.itemId= itemId;
        this.version= version;
		this.beginTime= beginTime;
		this.endTime= endTime;
		this.orgId= orgId;
		this.itemName= itemName;
		this.itemStaName= itemStaName;
		this.itemType= itemType;
		this.timeLimit= timeLimit;
		this.isNetwork= isNetwork;
		this.isFormula= isFormula;
		this.phone= phone;
		this.address= address;
		this.lastmodifytime= lastmodifytime;
		this.auditSign= auditSign;
		this.monitorPhone= monitorPhone;
		this.acceptLink= acceptLink;
		this.legalTimeLimit= legalTimeLimit;
		this.charge= charge;
		this.formName= formName;
		this.fileName= fileName;
		this.inFlowImgName= inFlowImgName;
		this.ischarge= ischarge;
		this.punishClass= punishClass;
		this.transactDepname= transactDepname;
		this.promiseType= promiseType;
		this.anticipateType= anticipateType;
		this.qlDepid= qlDepid;
		this.qlDepstate= qlDepstate;
		this.entrustName= entrustName;
		this.qlState= qlState;
		this.replyPeople= replyPeople;
		this.auditor= auditor;
		this.requester= requester;
		this.chgType=chgType;
		
		this.chgContent=chgContent;
		this.chgReason=chgReason;
		this.chgResult=chgResult;
		this.requestTime=requestTime;
		this.auditContent=auditContent;
		this.auditTime=auditTime;
		this.replyContent=replyContent;
		this.replyTime=replyTime;
		this.changeId=changeId;
		this.begTime=begTime;
	}

	// Property accessors
  
	public String getChgType() {
        return chgType;
    }
    public void setChgType(String chgType) {
        this.chgType = chgType;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Date getBeginTime() {
		return this.beginTime;
	}
	
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
  
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
  
	public String getOrgId() {
		return this.orgId;
	}
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
  
	public String getItemName() {
		return this.itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
  
	public String getItemStaName() {
		return this.itemStaName;
	}
	
	public void setItemStaName(String itemStaName) {
		this.itemStaName = itemStaName;
	}
  
	public String getItemType() {
		return this.itemType;
	}
	
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
  
	public Long getTimeLimit() {
		return this.timeLimit;
	}
	
	public void setTimeLimit(Long timeLimit) {
		this.timeLimit = timeLimit;
	}
  
	public String getIsNetwork() {
		return this.isNetwork;
	}
	
	public void setIsNetwork(String isNetwork) {
		this.isNetwork = isNetwork;
	}
  
	public String getIsFormula() {
		return this.isFormula;
	}
	
	public void setIsFormula(String isFormula) {
		this.isFormula = isFormula;
	}
  
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
  
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
  
	public Date getLastmodifytime() {
		return this.lastmodifytime;
	}
	
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
  
	public String getAuditSign() {
		return this.auditSign;
	}
	
	public void setAuditSign(String auditSign) {
		this.auditSign = auditSign;
	}
  
	public String getMonitorPhone() {
		return this.monitorPhone;
	}
	
	public void setMonitorPhone(String monitorPhone) {
		this.monitorPhone = monitorPhone;
	}
  
	public String getAcceptLink() {
		return this.acceptLink;
	}
	
	public void setAcceptLink(String acceptLink) {
		this.acceptLink = acceptLink;
	}
  
	public Long getLegalTimeLimit() {
		return this.legalTimeLimit;
	}
	
	public void setLegalTimeLimit(Long legalTimeLimit) {
		this.legalTimeLimit = legalTimeLimit;
	}
  
	public String getCharge() {
		return this.charge;
	}
	
	public void setCharge(String charge) {
		this.charge = charge;
	}
  
	public String getFormName() {
		return this.formName;
	}
	
	public void setFormName(String formName) {
		this.formName = formName;
	}
  
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
  
	public String getInFlowImgName() {
		return this.inFlowImgName;
	}
	
	public void setInFlowImgName(String inFlowImgName) {
		this.inFlowImgName = inFlowImgName;
	}
  
	public String getIscharge() {
		return this.ischarge;
	}
	
	public void setIscharge(String ischarge) {
		this.ischarge = ischarge;
	}
  
	public String getPunishClass() {
		return this.punishClass;
	}
	
	public void setPunishClass(String punishClass) {
		this.punishClass = punishClass;
	}
  
	public String getTransactDepname() {
		return this.transactDepname;
	}
	
	public void setTransactDepname(String transactDepname) {
		this.transactDepname = transactDepname;
	}
  
	public String getPromiseType() {
		return this.promiseType;
	}
	
	public void setPromiseType(String promiseType) {
		this.promiseType = promiseType;
	}
  
	public String getAnticipateType() {
		return this.anticipateType;
	}
	
	public void setAnticipateType(String anticipateType) {
		this.anticipateType = anticipateType;
	}
  
	public String getQlDepid() {
		return this.qlDepid;
	}
	
	public void setQlDepid(String qlDepid) {
		this.qlDepid = qlDepid;
	}
  
	public String getQlDepstate() {
		return this.qlDepstate;
	}
	
	public void setQlDepstate(String qlDepstate) {
		this.qlDepstate = qlDepstate;
	}
  
	public String getEntrustName() {
		return this.entrustName;
	}
	
	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}
  
	public String getQlState() {
		return this.qlState;
	}
	
	public void setQlState(String qlState) {
		this.qlState = qlState;
	}
  
	public String getReplyPeople() {
		return this.replyPeople;
	}
	
	public void setReplyPeople(String replyPeople) {
		this.replyPeople = replyPeople;
	}
  
	public String getAuditor() {
		return this.auditor;
	}
	
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
  
	public String getRequester() {
		return this.requester;
	}
	
	public void setRequester(String requester) {
		this.requester = requester;
	}



	public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    public void copy(Suppowerqlbgsq other){
        this.setChangeId(other.getChangeId());
	    this.itemId= other.getItemId();  
        this.version= other.getVersion();  
		this.beginTime= other.getBeginTime();  
		this.endTime= other.getEndTime();  
		this.orgId= other.getOrgId();  
		this.itemName= other.getItemName();  
		this.itemStaName= other.getItemStaName();  
		this.itemType= other.getItemType();  
		this.timeLimit= other.getTimeLimit();  
		this.isNetwork= other.getIsNetwork();  
		this.isFormula= other.getIsFormula();  
		this.phone= other.getPhone();  
		this.address= other.getAddress();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.auditSign= other.getAuditSign();  
		this.monitorPhone= other.getMonitorPhone();  
		this.acceptLink= other.getAcceptLink();  
		this.legalTimeLimit= other.getLegalTimeLimit();  
		this.charge= other.getCharge();  
		this.formName= other.getFormName();  
		this.fileName= other.getFileName();  
		this.inFlowImgName= other.getInFlowImgName();  
		this.ischarge= other.getIscharge();  
		this.punishClass= other.getPunishClass();  
		this.transactDepname= other.getTransactDepname();  
		this.promiseType= other.getPromiseType();  
		this.anticipateType= other.getAnticipateType();  
		this.qlDepid= other.getQlDepid();  
		this.qlDepstate= other.getQlDepstate();  
		this.entrustName= other.getEntrustName();  
		this.qlState= other.getQlState();  
		this.replyPeople= other.getReplyPeople();  
		this.auditor= other.getAuditor();  
		this.requester= other.getRequester();
		this.chgType=other.getChgType();
		this.begTime=other.getBegTime();
		//this.changeId=other.getChangeId();

	}
	
	public String getChangeId() {
        return changeId;
    }
    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }
    public void copyNotNullProperty(Suppowerqlbgsq other){
        if( other.getItemId() != null)
            this.itemId= other.getItemId();  
        if( other.getVersion() != null)
            this.version= other.getVersion();  
		if( other.getBeginTime() != null)
			this.beginTime= other.getBeginTime();  
		if( other.getEndTime() != null)
			this.endTime= other.getEndTime();  
		if( other.getOrgId() != null)
			this.orgId= other.getOrgId();  
		if( other.getItemName() != null)
			this.itemName= other.getItemName();  
		if( other.getItemStaName() != null)
			this.itemStaName= other.getItemStaName();  
		if( other.getItemType() != null)
			this.itemType= other.getItemType();  
		if( other.getTimeLimit() != null)
			this.timeLimit= other.getTimeLimit();  
		if( other.getIsNetwork() != null)
			this.isNetwork= other.getIsNetwork();  
		if( other.getIsFormula() != null)
			this.isFormula= other.getIsFormula();  
		if( other.getPhone() != null)
			this.phone= other.getPhone();  
		if( other.getAddress() != null)
			this.address= other.getAddress();  
		if( other.getLastmodifytime() != null)
			this.lastmodifytime= other.getLastmodifytime();  
		if( other.getAuditSign() != null)
			this.auditSign= other.getAuditSign();  
		if( other.getMonitorPhone() != null)
			this.monitorPhone= other.getMonitorPhone();  
		if( other.getAcceptLink() != null)
			this.acceptLink= other.getAcceptLink();  
		if( other.getLegalTimeLimit() != null)
			this.legalTimeLimit= other.getLegalTimeLimit();  
		if( other.getCharge() != null)
			this.charge= other.getCharge();  
		if( other.getFormName() != null)
			this.formName= other.getFormName();  
		if( other.getFileName() != null)
			this.fileName= other.getFileName();  
		if( other.getInFlowImgName() != null)
			this.inFlowImgName= other.getInFlowImgName();  
		if( other.getIscharge() != null)
			this.ischarge= other.getIscharge();  
		if( other.getPunishClass() != null)
			this.punishClass= other.getPunishClass();  
		if( other.getTransactDepname() != null)
			this.transactDepname= other.getTransactDepname();  
		if( other.getPromiseType() != null)
			this.promiseType= other.getPromiseType();  
		if( other.getAnticipateType() != null)
			this.anticipateType= other.getAnticipateType();  
		if( other.getQlDepid() != null)
			this.qlDepid= other.getQlDepid();  
		if( other.getQlDepstate() != null)
			this.qlDepstate= other.getQlDepstate();  
		if( other.getEntrustName() != null)
			this.entrustName= other.getEntrustName();  
		if( other.getQlState() != null)
			this.qlState= other.getQlState();  
		if( other.getReplyPeople() != null)
			this.replyPeople= other.getReplyPeople();  
		if( other.getAuditor() != null)
			this.auditor= other.getAuditor();  
		if( other.getRequester() != null)
			this.requester= other.getRequester();
		if( other.getChgType() != null)
            this.chgType= other.getChgType();
		
		  if( other.getChgContent() != null)
	            this.chgContent= other.getChgContent();  
	        if( other.getChgReason() != null)
	            this.chgReason= other.getChgReason();  
	        if( other.getChgResult() != null)
	            this.chgResult= other.getChgResult();  
	        if( other.getReplyContent() != null)
	            this.replyContent= other.getReplyContent();  
	        if( other.getReplyTime() != null)
	            this.replyTime= other.getReplyTime();  
	        if( other.getAuditTime() != null)
	            this.auditTime= other.getAuditTime();  
	        if( other.getAuditContent() != null)
	            this.auditContent= other.getAuditContent();
	        if( other.getChgResult() != null)
	            this.chgResult= other.getChgResult();
	        if( other.getChangeId() != null)
                this.changeId= other.getChangeId();
	        if( other.getBegTime() != null)
                this.begTime= other.getBegTime();
		
		
	}
	
	public void clearProperties(){
  
		this.beginTime= null;  
		this.endTime= null;  
		this.orgId= null;  
		this.itemName= null;  
		this.itemStaName= null;  
		this.itemType= null;  
		this.timeLimit= null;  
		this.isNetwork= null;  
		this.isFormula= null;  
		this.phone= null;  
		this.address= null;  
		this.lastmodifytime= null;  
		this.auditSign= null;  
		this.monitorPhone= null;  
		this.acceptLink= null;  
		this.legalTimeLimit= null;  
		this.charge= null;  
		this.formName= null;  
		this.fileName= null;  
		this.inFlowImgName= null;  
		this.ischarge= null;  
		this.punishClass= null;  
		this.transactDepname= null;  
		this.promiseType= null;  
		this.anticipateType= null;  
		this.qlDepid= null;  
		this.qlDepstate= null;  
		this.entrustName= null;  
		this.qlState= null;  
		this.replyPeople= null;  
		this.auditor= null;  
		this.requester= null;
		this.chgType=null;
		
		
		this.chgContent=null;
        this.chgReason=null;
        this.chgResult=null;
        this.requestTime=null;
        this.auditContent=null;
        this.auditTime=null;
        this.replyContent=null;
        this.replyTime=null;
        this.begTime=null;
        //this.changeId=null;
	}
}
