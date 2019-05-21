package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class EquipmentUsing implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long useRequestId;//使用申请号

	private Long  equipmentId;//固定资产编号
	private String  applicant;//申请人
	private Date  applicantTime;//申请时间
	private String  purposeType;//用途类别
	private String  purposeDesc;//用途说明
	private Date  planBeginTime;//预计开始时间
	private Date  planEndTime;//预计结束时间
	private String  auditor;//确认人
	private Date  auditTime;//确认时间
	private String  beAccepted;//是否接受申请
	private String  beUsed;//是否实际使用
	private Date  beginTime;//实际开始时间
	private Date  endTime;//实际结束时间
	private Double  useCost;//使用费用
	private String  useDesc;//实际使用说明
	private String  feedbackUser;//使用反馈人
	private Date  feedbackTime;//反馈时间
	private String  equipmentState;//资产状态 
    private EquipmentInfo equipmentInfo;
	// Constructors
	/** default constructor */
	public EquipmentUsing() {
	}
	/** minimal constructor */
	public EquipmentUsing(
		Long useRequestId		
		,Date  planBeginTime) {
	
	
		this.useRequestId = useRequestId;		
	
		this.planBeginTime= planBeginTime; 		
	}

/** full constructor */
	public EquipmentUsing(
	 Long useRequestId		
	,Long  equipmentId,String  applicant,Date  applicantTime,String  purposeType,String  purposeDesc,Date  planBeginTime,Date  planEndTime,String  auditor,Date  auditTime,String  beAccepted,String  beUsed,Date  beginTime,Date  endTime,Double  useCost,String  useDesc,String  feedbackUser,Date  feedbackTime,String  equipmentState) {
	
	
		this.useRequestId = useRequestId;		
	
		this.equipmentId= equipmentId;
		this.applicant= applicant;
		this.applicantTime= applicantTime;
		this.purposeType= purposeType;
		this.purposeDesc= purposeDesc;
		this.planBeginTime= planBeginTime;
		this.planEndTime= planEndTime;
		this.auditor= auditor;
		this.auditTime= auditTime;
		this.beAccepted= beAccepted;
		this.beUsed= beUsed;
		this.beginTime= beginTime;
		this.endTime= endTime;
		this.useCost= useCost;
		this.useDesc= useDesc;
		this.feedbackUser= feedbackUser;
		this.feedbackTime= feedbackTime;	
		this.setEquipmentState(equipmentState);
	}
	

  
	public Long getUseRequestId() {
		return this.useRequestId;
	}

	public void setUseRequestId(Long useRequestId) {
		this.useRequestId = useRequestId;
	}
	// Property accessors
  
	public Long getEquipmentId() {
		return this.equipmentId;
	}
	
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
  
	public String getApplicant() {
		return this.applicant;
	}
	
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
  
	public Date getApplicantTime() {
		return this.applicantTime;
	}
	
	public void setApplicantTime(Date applicantTime) {
		this.applicantTime = applicantTime;
	}
  
	public String getPurposeType() {
		return this.purposeType;
	}
	
	public void setPurposeType(String purposeType) {
		this.purposeType = purposeType;
	}
  
	public String getPurposeDesc() {
		return this.purposeDesc;
	}
	
	public void setPurposeDesc(String purposeDesc) {
		this.purposeDesc = purposeDesc;
	}
  
	public Date getPlanBeginTime() {
		return this.planBeginTime;
	}
	
	public void setPlanBeginTime(Date planBeginTime) {
		this.planBeginTime = planBeginTime;
	}
  
	public Date getPlanEndTime() {
		return this.planEndTime;
	}
	
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
  
	public String getAuditor() {
		return this.auditor;
	}
	
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
  
	public Date getAuditTime() {
		return this.auditTime;
	}
	
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
  
	public String getBeAccepted() {
		return this.beAccepted;
	}
	
	public void setBeAccepted(String beAccepted) {
		this.beAccepted = beAccepted;
	}
  
	public String getBeUsed() {
		return this.beUsed;
	}
	
	public void setBeUsed(String beUsed) {
		this.beUsed = beUsed;
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
  
	public Double getUseCost() {
		return this.useCost;
	}
	
	public void setUseCost(Double useCost) {
		this.useCost = useCost;
	}
  
	public String getUseDesc() {
		return this.useDesc;
	}
	
	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}
  
	public String getFeedbackUser() {
		return this.feedbackUser;
	}
	
	public void setFeedbackUser(String feedbackUser) {
		this.feedbackUser = feedbackUser;
	}
  
	public Date getFeedbackTime() {
		return this.feedbackTime;
	}
	
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}



	public void copy(EquipmentUsing other){
  
		this.setUseRequestId(other.getUseRequestId());
  
		this.equipmentId= other.getEquipmentId();  
		this.applicant= other.getApplicant();  
		this.applicantTime= other.getApplicantTime();  
		this.purposeType= other.getPurposeType();  
		this.purposeDesc= other.getPurposeDesc();  
		this.planBeginTime= other.getPlanBeginTime();  
		this.planEndTime= other.getPlanEndTime();  
		this.auditor= other.getAuditor();  
		this.auditTime= other.getAuditTime();  
		this.beAccepted= other.getBeAccepted();  
		this.beUsed= other.getBeUsed();  
		this.beginTime= other.getBeginTime();  
		this.endTime= other.getEndTime();  
		this.useCost= other.getUseCost();  
		this.useDesc= other.getUseDesc();  
		this.feedbackUser= other.getFeedbackUser();  
		this.feedbackTime= other.getFeedbackTime();
		this.setEquipmentState(other.getEquipmentState());  


	}
	
	public void copyNotNullProperty(EquipmentUsing other){
  
	if( other.getUseRequestId() != null)
		this.setUseRequestId(other.getUseRequestId());
  
		if( other.getEquipmentId() != null)
			this.equipmentId= other.getEquipmentId();  
		if( other.getApplicant() != null)
			this.applicant= other.getApplicant();  
		if( other.getApplicantTime() != null)
			this.applicantTime= other.getApplicantTime();  
		if( other.getPurposeType() != null)
			this.purposeType= other.getPurposeType();  
		if( other.getPurposeDesc() != null)
			this.purposeDesc= other.getPurposeDesc();  
		if( other.getPlanBeginTime() != null)
			this.planBeginTime= other.getPlanBeginTime();  
		if( other.getPlanEndTime() != null)
			this.planEndTime= other.getPlanEndTime();  
		if( other.getAuditor() != null)
			this.auditor= other.getAuditor();  
		if( other.getAuditTime() != null)
			this.auditTime= other.getAuditTime();  
		if( other.getBeAccepted() != null)
			this.beAccepted= other.getBeAccepted();  
		if( other.getBeUsed() != null)
			this.beUsed= other.getBeUsed();  
		if( other.getBeginTime() != null)
			this.beginTime= other.getBeginTime();  
		if( other.getEndTime() != null)
			this.endTime= other.getEndTime();  
		if( other.getUseCost() != null)
			this.useCost= other.getUseCost();  
		if( other.getUseDesc() != null)
			this.useDesc= other.getUseDesc();  
		if( other.getFeedbackUser() != null)
			this.feedbackUser= other.getFeedbackUser();  
		if( other.getFeedbackTime() != null)
			this.feedbackTime= other.getFeedbackTime();
		if( other.getEquipmentState() != null)
            this.setEquipmentState(other.getEquipmentState());  

	}
	
	public void clearProperties(){
  
		this.equipmentId= null;  
		this.applicant= null;  
		this.applicantTime= null;  
		this.purposeType= null;  
		this.purposeDesc= null;  
		this.planBeginTime= null;  
		this.planEndTime= null;  
		this.auditor= null;  
		this.auditTime= null;  
		this.beAccepted= null;  
		this.beUsed= null;  
		this.beginTime= null;  
		this.endTime= null;  
		this.useCost= null;  
		this.useDesc= null;  
		this.feedbackUser= null;  
		this.feedbackTime= null;
		this.setEquipmentState(null);  

	}
    public String getEquipmentState() {
        return equipmentState;
    }
    public void setEquipmentState(String equipmentState) {
        this.equipmentState = equipmentState;
    }
    public EquipmentInfo getEquipmentInfo() {
        return equipmentInfo;
    }
    public void setEquipmentInfo(EquipmentInfo equipmentInfo) {
        this.equipmentInfo = equipmentInfo;
    }
}
