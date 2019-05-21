package com.centit.dispatchdoc.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class SignedReport implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;


    private String  secretsDegree;
	private String  criticalLeveal;
	private String  signedReportTitle;
	private String  signedReportNo;
	private String  signedPersion;
	private Date  signedDate;
	private String  otherDep;
	private String  mainSignedDep;
	private String  draftUserName;
	private Date  draftDate;
	private String  draftTelephone;
	private String  retentionPeriod;
	private byte[]  signedReportFile;

    private String  signedReportFileName;
	private String  recordId;
	private String  queryPower;
	private String  particularGroup;
	private String  archiveDepno;
	private String  archiveNo;
	private String  signedFileType;
	private String  remark;
	private String  bizstate;
	private String  biztype;
	private String  solvestatus;
	private Date  solvetime;
	private String  solvenote;
	private String  optid;
	private String  flowcode;
	private Long  flowinstid;
    private String itemId;
    private OptBaseInfo optBaseInfo;

    // Constructors
	/** default constructor */
	public SignedReport() {
	}
	/** minimal constructor */
	public SignedReport(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public SignedReport(
	 String djId		
	,String  secretsDegree,String  criticalLeveal,String  signedReportTitle,String  signedReportNo,String  signedPersion,Date  signedDate,String  otherDep,String  mainSignedDep,String  draftUserName,Date  draftDate,String  draftTelephone,String  retentionPeriod,byte[]  signedReportFile,String  signedReportFileName,String  recordId,String  queryPower,String  particularGroup,String  archiveDepno,String  archiveNo,String  signedFileType,String  remark,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optid,String  flowcode,Long  flowinstid) {
	
	
		this.djId = djId;		
	
		this.secretsDegree= secretsDegree;
		this.criticalLeveal= criticalLeveal;
		this.signedReportTitle= signedReportTitle;
		this.signedReportNo= signedReportNo;
		this.signedPersion= signedPersion;
		this.signedDate= signedDate;
		this.otherDep= otherDep;
		this.mainSignedDep= mainSignedDep;
		this.draftUserName= draftUserName;
		this.draftDate= draftDate;
		this.draftTelephone= draftTelephone;
		this.retentionPeriod= retentionPeriod;
		this.signedReportFile= signedReportFile;
		this.signedReportFileName= signedReportFileName;
		this.recordId= recordId;
		this.queryPower= queryPower;
		this.particularGroup= particularGroup;
		this.archiveDepno= archiveDepno;
		this.archiveNo= archiveNo;
		this.signedFileType= signedFileType;
		this.remark= remark;
		this.bizstate= bizstate;
		this.biztype= biztype;
		this.solvestatus= solvestatus;
		this.solvetime= solvetime;
		this.solvenote= solvenote;
		this.optid= optid;
		this.flowcode= flowcode;
		this.flowinstid= flowinstid;		
	}
	
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }

  

	// Property accessors
  
	public String getSecretsDegree() {
		return this.secretsDegree;
	}
	
	public void setSecretsDegree(String secretsDegree) {
		this.secretsDegree = secretsDegree;
	}
  
	public String getCriticalLeveal() {
		return this.criticalLeveal;
	}
	
	public void setCriticalLeveal(String criticalLeveal) {
		this.criticalLeveal = criticalLeveal;
	}
  
	public String getSignedReportTitle() {
		return this.signedReportTitle;
	}
	
	public void setSignedReportTitle(String signedReportTitle) {
		this.signedReportTitle = signedReportTitle;
	}
  
	public String getSignedReportNo() {
		return this.signedReportNo;
	}
	
	public void setSignedReportNo(String signedReportNo) {
		this.signedReportNo = signedReportNo;
	}
  
	public String getSignedPersion() {
		return this.signedPersion;
	}
	
	public void setSignedPersion(String signedPersion) {
		this.signedPersion = signedPersion;
	}
  
	public Date getSignedDate() {
		return this.signedDate;
	}
	
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}
  
	public String getOtherDep() {
		return this.otherDep;
	}
	
	public void setOtherDep(String otherDep) {
		this.otherDep = otherDep;
	}
  
	public String getMainSignedDep() {
		return this.mainSignedDep;
	}
	
	public void setMainSignedDep(String mainSignedDep) {
		this.mainSignedDep = mainSignedDep;
	}
  
	public String getDraftUserName() {
		return this.draftUserName;
	}
	
	public void setDraftUserName(String draftUserName) {
		this.draftUserName = draftUserName;
	}
  
	public Date getDraftDate() {
		return this.draftDate;
	}
	
	public void setDraftDate(Date draftDate) {
		this.draftDate = draftDate;
	}
  
	public String getDraftTelephone() {
		return this.draftTelephone;
	}
	
	public void setDraftTelephone(String draftTelephone) {
		this.draftTelephone = draftTelephone;
	}
  
	public String getRetentionPeriod() {
		return this.retentionPeriod;
	}
	
	public void setRetentionPeriod(String retentionPeriod) {
		this.retentionPeriod = retentionPeriod;
	}
  

	  public byte[] getSignedReportFile() {
	        return signedReportFile;
	    }
	    public void setSignedReportFile(byte[] signedReportFile) {
	        this.signedReportFile = signedReportFile;
	    }

	public String getSignedReportFileName() {
		return this.signedReportFileName;
	}
	
	public void setSignedReportFileName(String signedReportFileName) {
		this.signedReportFileName = signedReportFileName;
	}
  
	public String getRecordId() {
		return this.recordId;
	}
	
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
  
	public String getQueryPower() {
		return this.queryPower;
	}
	
	public void setQueryPower(String queryPower) {
		this.queryPower = queryPower;
	}
  
	public String getParticularGroup() {
		return this.particularGroup;
	}
	
	public void setParticularGroup(String particularGroup) {
		this.particularGroup = particularGroup;
	}
  
	public String getArchiveDepno() {
		return this.archiveDepno;
	}
	
	public void setArchiveDepno(String archiveDepno) {
		this.archiveDepno = archiveDepno;
	}
  
	public String getArchiveNo() {
		return this.archiveNo;
	}
	
	public void setArchiveNo(String archiveNo) {
		this.archiveNo = archiveNo;
	}
  
	public String getSignedFileType() {
		return this.signedFileType;
	}
	
	public void setSignedFileType(String signedFileType) {
		this.signedFileType = signedFileType;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getBizstate() {
		return this.bizstate;
	}
	
	public void setBizstate(String bizstate) {
		this.bizstate = bizstate;
	}
  
	public String getBiztype() {
		return this.biztype;
	}
	
	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}
  
	public String getSolvestatus() {
		return this.solvestatus;
	}
	
	public void setSolvestatus(String solvestatus) {
		this.solvestatus = solvestatus;
	}
  
	public Date getSolvetime() {
		return this.solvetime;
	}
	
	public void setSolvetime(Date solvetime) {
		this.solvetime = solvetime;
	}
  
	public String getSolvenote() {
		return this.solvenote;
	}
	
	public void setSolvenote(String solvenote) {
		this.solvenote = solvenote;
	}
  
	public String getOptid() {
		return this.optid;
	}
	
	public void setOptid(String optid) {
		this.optid = optid;
	}
  
	public String getFlowcode() {
		return this.flowcode;
	}
	
	public void setFlowcode(String flowcode) {
		this.flowcode = flowcode;
	}
  
	public Long getFlowinstid() {
		return this.flowinstid;
	}
	
	public void setFlowinstid(Long flowinstid) {
		this.flowinstid = flowinstid;
	}

    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }
    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

	public void copy(SignedReport other){
  
		this.setDjId(other.getDjId());
  
		this.secretsDegree= other.getSecretsDegree();  
		this.criticalLeveal= other.getCriticalLeveal();  
		this.signedReportTitle= other.getSignedReportTitle();  
		this.signedReportNo= other.getSignedReportNo();  
		this.signedPersion= other.getSignedPersion();  
		this.signedDate= other.getSignedDate();  
		this.otherDep= other.getOtherDep();  
		this.mainSignedDep= other.getMainSignedDep();  
		this.draftUserName= other.getDraftUserName();  
		this.draftDate= other.getDraftDate();  
		this.draftTelephone= other.getDraftTelephone();  
		this.retentionPeriod= other.getRetentionPeriod();  
		this.signedReportFile= other.getSignedReportFile();  
		this.signedReportFileName= other.getSignedReportFileName();  
		this.recordId= other.getRecordId();  
		this.queryPower= other.getQueryPower();  
		this.particularGroup= other.getParticularGroup();  
		this.archiveDepno= other.getArchiveDepno();  
		this.archiveNo= other.getArchiveNo();  
		this.signedFileType= other.getSignedFileType();  
		this.remark= other.getRemark();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();  
		this.solvenote= other.getSolvenote();  
		this.optid= other.getOptid();  
		this.flowcode= other.getFlowcode();  
		this.flowinstid= other.getFlowinstid();

	}
	
	public void copyNotNullProperty(SignedReport other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getSecretsDegree() != null)
			this.secretsDegree= other.getSecretsDegree();  
		if( other.getCriticalLeveal() != null)
			this.criticalLeveal= other.getCriticalLeveal();  
		if( other.getSignedReportTitle() != null)
			this.signedReportTitle= other.getSignedReportTitle();  
		if( other.getSignedReportNo() != null)
			this.signedReportNo= other.getSignedReportNo();  
		if( other.getSignedPersion() != null)
			this.signedPersion= other.getSignedPersion();  
		if( other.getSignedDate() != null)
			this.signedDate= other.getSignedDate();  
		if( other.getOtherDep() != null)
			this.otherDep= other.getOtherDep();  
		if( other.getMainSignedDep() != null)
			this.mainSignedDep= other.getMainSignedDep();  
		if( other.getDraftUserName() != null)
			this.draftUserName= other.getDraftUserName();  
		if( other.getDraftDate() != null)
			this.draftDate= other.getDraftDate();  
		if( other.getDraftTelephone() != null)
			this.draftTelephone= other.getDraftTelephone();  
		if( other.getRetentionPeriod() != null)
			this.retentionPeriod= other.getRetentionPeriod();  
		if( other.getSignedReportFile() != null)
			this.signedReportFile= other.getSignedReportFile();  
		if( other.getSignedReportFileName() != null)
			this.signedReportFileName= other.getSignedReportFileName();  
		if( other.getRecordId() != null)
			this.recordId= other.getRecordId();  
		if( other.getQueryPower() != null)
			this.queryPower= other.getQueryPower();  
		if( other.getParticularGroup() != null)
			this.particularGroup= other.getParticularGroup();  
		if( other.getArchiveDepno() != null)
			this.archiveDepno= other.getArchiveDepno();  
		if( other.getArchiveNo() != null)
			this.archiveNo= other.getArchiveNo();  
		if( other.getSignedFileType() != null)
			this.signedFileType= other.getSignedFileType();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getBizstate() != null)
			this.bizstate= other.getBizstate();  
		if( other.getBiztype() != null)
			this.biztype= other.getBiztype();  
		if( other.getSolvestatus() != null)
			this.solvestatus= other.getSolvestatus();  
		if( other.getSolvetime() != null)
			this.solvetime= other.getSolvetime();  
		if( other.getSolvenote() != null)
			this.solvenote= other.getSolvenote();  
		if( other.getOptid() != null)
			this.optid= other.getOptid();  
		if( other.getFlowcode() != null)
			this.flowcode= other.getFlowcode();  
		if( other.getFlowinstid() != null)
			this.flowinstid= other.getFlowinstid();
		if( other.getItemId() != null)
            this.itemId= other.getItemId();

	}
	
	public void clearProperties(){
  
		this.secretsDegree= null;  
		this.criticalLeveal= null;  
		this.signedReportTitle= null;  
		this.signedReportNo= null;  
		this.signedPersion= null;  
		this.signedDate= null;  
		this.otherDep= null;  
		this.mainSignedDep= null;  
		this.draftUserName= null;  
		this.draftDate= null;  
		this.draftTelephone= null;  
		this.retentionPeriod= null;  
		this.signedReportFile= null;  
		this.signedReportFileName= null;  
		this.recordId= null;  
		this.queryPower= null;  
		this.particularGroup= null;  
		this.archiveDepno= null;  
		this.archiveNo= null;  
		this.signedFileType= null;  
		this.remark= null;  
		this.bizstate= null;  
		this.biztype= null;  
		this.solvestatus= null;  
		this.solvetime= null;  
		this.solvenote= null;  
		this.optid= null;  
		this.flowcode= null;  
		this.flowinstid= null;
        this.itemId=null;
	}
}
