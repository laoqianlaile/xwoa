package com.centit.dispatchdoc.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class SubprocessProject implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  headunitcode;
	private Date  accpetDate;
	private String  projectUnitName;
	private String  projectName;
	private String  projectType;
	private String  evaluationReason;
	private String  evaluationContent;
	private String  opinions;
	private String  createUserCode;
	private Date  createDate;
	private String  syncErrorDesc;
	private Date  updateDate;
	private Date  syncDate;
	private String  syncSign;
	private String  wfcode;
	private String supDjId;
	private IncomeDocTask incomeDocTask;
	 private  Long preinstid;
     private Long prenodeinstid;
     private  Long flowInstId;
     private  Long nodeInstId;
     private String processType;
     private String fileName;
     private String moduleCode;
     public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    private byte[] docFile;
	private String orgCode;
	private Date  finishDate;
    public Date getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
    public SubprocessProject(String djId, String headunitcode, Date accpetDate,
            String projectUnitName, String projectName, String projectType,
            String evaluationReason, String evaluationContent, String opinions,
            String createUserCode, Date createDate, String syncErrorDesc,
            Date updateDate, Date syncDate, String syncSign, String wfcode,
            String supDjId, IncomeDocTask incomeDocTask, Long preinstid,
            Long prenodeinstid, Long flowInstId, Long nodeInstId,
            String processType, String fileName, byte[] docFile,
            String orgCode, Date finishDate, OptBaseInfo optBaseInfo) {
        super();
        this.djId = djId;
        this.headunitcode = headunitcode;
        this.accpetDate = accpetDate;
        this.projectUnitName = projectUnitName;
        this.projectName = projectName;
        this.projectType = projectType;
        this.evaluationReason = evaluationReason;
        this.evaluationContent = evaluationContent;
        this.opinions = opinions;
        this.createUserCode = createUserCode;
        this.createDate = createDate;
        this.syncErrorDesc = syncErrorDesc;
        this.updateDate = updateDate;
        this.syncDate = syncDate;
        this.syncSign = syncSign;
        this.wfcode = wfcode;
        this.supDjId = supDjId;
        this.incomeDocTask = incomeDocTask;
        this.preinstid = preinstid;
        this.prenodeinstid = prenodeinstid;
        this.flowInstId = flowInstId;
        this.nodeInstId = nodeInstId;
        this.processType = processType;
        this.fileName = fileName;
        this.docFile = docFile;
        this.orgCode = orgCode;
        this.finishDate = finishDate;
        this.optBaseInfo = optBaseInfo;
    }
    public SubprocessProject(String djId, String headunitcode, Date accpetDate,
            String projectUnitName, String projectName, String projectType,
            String evaluationReason, String evaluationContent, String opinions,
            String createUserCode, Date createDate, String syncErrorDesc,
            Date updateDate, Date syncDate, String syncSign, String wfcode,
            String supDjId, IncomeDocTask incomeDocTask, Long preinstid,
            Long prenodeinstid, Long flowInstId, Long nodeInstId,
            String processType, String fileName, byte[] docFile,
            String orgCode, OptBaseInfo optBaseInfo) {
        super();
        this.djId = djId;
        this.headunitcode = headunitcode;
        this.accpetDate = accpetDate;
        this.projectUnitName = projectUnitName;
        this.projectName = projectName;
        this.projectType = projectType;
        this.evaluationReason = evaluationReason;
        this.evaluationContent = evaluationContent;
        this.opinions = opinions;
        this.createUserCode = createUserCode;
        this.createDate = createDate;
        this.syncErrorDesc = syncErrorDesc;
        this.updateDate = updateDate;
        this.syncDate = syncDate;
        this.syncSign = syncSign;
        this.wfcode = wfcode;
        this.supDjId = supDjId;
        this.incomeDocTask = incomeDocTask;
        this.preinstid = preinstid;
        this.prenodeinstid = prenodeinstid;
        this.flowInstId = flowInstId;
        this.nodeInstId = nodeInstId;
        this.processType = processType;
        this.fileName = fileName;
        this.docFile = docFile;
        this.orgCode = orgCode;
        this.optBaseInfo = optBaseInfo;
    }
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    public String getProcessType() {
        return processType;
    }
    public void setProcessType(String processType) {
        this.processType = processType;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
  
    public byte[] getDocFile() {
        return docFile;
    }
    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }
    public Long getFlowInstId() {
        return flowInstId;
    }
    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }
    public Long getNodeInstId() {
        return nodeInstId;
    }
    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }
    public Long getPreinstid() {
        return preinstid;
    }
    public void setPreinstid(Long preinstid) {
        this.preinstid = preinstid;
    }
    public Long getPrenodeinstid() {
        return prenodeinstid;
    }
    public void setPrenodeinstid(Long prenodeinstid) {
        this.prenodeinstid = prenodeinstid;
    }
    public IncomeDocTask getIncomeDocTask() {
        return incomeDocTask;
    }
    public void setIncomeDocTask(IncomeDocTask incomeDocTask) {
        this.incomeDocTask = incomeDocTask;
    }
    public String getSupDjId() {
        return supDjId;
    }
    public void setSupDjId(String supDjId) {
        this.supDjId = supDjId;
    }

    private OptBaseInfo optBaseInfo;

	public SubprocessProject(String djId, String headunitcode, Date accpetDate,
            String projectUnitName, String projectName, String projectType,
            String evaluationReason, String evaluationContent, String opinions,
            String createUserCode, Date createDate, String syncErrorDesc,
            Date updateDate, Date syncDate, String syncSign, String wfcode,
            String supDjId, OptBaseInfo optBaseInfo) {
        super();
        this.djId = djId;
        this.headunitcode = headunitcode;
        this.accpetDate = accpetDate;
        this.projectUnitName = projectUnitName;
        this.projectName = projectName;
        this.projectType = projectType;
        this.evaluationReason = evaluationReason;
        this.evaluationContent = evaluationContent;
        this.opinions = opinions;
        this.createUserCode = createUserCode;
        this.createDate = createDate;
        this.syncErrorDesc = syncErrorDesc;
        this.updateDate = updateDate;
        this.syncDate = syncDate;
        this.syncSign = syncSign;
        this.wfcode = wfcode;
        this.supDjId = supDjId;
        this.optBaseInfo = optBaseInfo;
    }
    public SubprocessProject(String djId, String headunitcode, Date accpetDate,
            String projectUnitName, String projectName, String projectType,
            String evaluationReason, String evaluationContent, String opinions,
            String createUserCode, Date createDate, String syncErrorDesc,
            Date updateDate, Date syncDate, String syncSign, String wfcode,
            OptBaseInfo optBaseInfo) {
        super();
        this.djId = djId;
        this.headunitcode = headunitcode;
        this.accpetDate = accpetDate;
        this.projectUnitName = projectUnitName;
        this.projectName = projectName;
        this.projectType = projectType;
        this.evaluationReason = evaluationReason;
        this.evaluationContent = evaluationContent;
        this.opinions = opinions;
        this.createUserCode = createUserCode;
        this.createDate = createDate;
        this.syncErrorDesc = syncErrorDesc;
        this.updateDate = updateDate;
        this.syncDate = syncDate;
        this.syncSign = syncSign;
        this.wfcode = wfcode;
        this.optBaseInfo = optBaseInfo;
    }
    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }
    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
    // Constructors
	/** default constructor */
	public SubprocessProject() {
	}
	/** minimal constructor */
	public SubprocessProject(
		String djId		
		,Date  createDate,Date  updateDate) {
	
	
		this.djId = djId;		
	
		this.createDate= createDate; 
		this.updateDate= updateDate; 		
	}

/** full constructor */
	public SubprocessProject(
	 String djId		
	,String  headunitcode,Date  accpetDate,String  projectUnitName,String  projectName,String  projectType,String  evaluationReason,String  evaluationContent,String  opinions,String  createUserCode,Date  createDate,String  syncErrorDesc,Date  updateDate,Date  syncDate,String  syncSign,String  wfcode) {
	
	
		this.djId = djId;		
	
		this.headunitcode= headunitcode;
		this.accpetDate= accpetDate;
		this.projectUnitName= projectUnitName;
		this.projectName= projectName;
		this.projectType= projectType;
		this.evaluationReason= evaluationReason;
		this.evaluationContent= evaluationContent;
		this.opinions= opinions;
		this.createUserCode= createUserCode;
		this.createDate= createDate;
		this.syncErrorDesc= syncErrorDesc;
		this.updateDate= updateDate;
		this.syncDate= syncDate;
		this.syncSign= syncSign;
		this.wfcode= wfcode;		
	}
	

  
	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}
	// Property accessors
  
	public String getHeadunitcode() {
		return this.headunitcode;
	}
	
	public void setHeadunitcode(String headunitcode) {
		this.headunitcode = headunitcode;
	}
  
	public Date getAccpetDate() {
		return this.accpetDate;
	}
	
	public void setAccpetDate(Date accpetDate) {
		this.accpetDate = accpetDate;
	}
  
	public String getProjectUnitName() {
		return this.projectUnitName;
	}
	
	public void setProjectUnitName(String projectUnitName) {
		this.projectUnitName = projectUnitName;
	}
  
	public String getProjectName() {
		return this.projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
  
	public String getProjectType() {
		return this.projectType;
	}
	
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
  
	public String getEvaluationReason() {
		return this.evaluationReason;
	}
	
	public void setEvaluationReason(String evaluationReason) {
		this.evaluationReason = evaluationReason;
	}
  
	public String getEvaluationContent() {
		return this.evaluationContent;
	}
	
	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}
  
	public String getOpinions() {
		return this.opinions;
	}
	
	public void setOpinions(String opinions) {
		this.opinions = opinions;
	}
  
	public String getCreateUserCode() {
		return this.createUserCode;
	}
	
	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}
  
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
  
	public String getSyncErrorDesc() {
		return this.syncErrorDesc;
	}
	
	public void setSyncErrorDesc(String syncErrorDesc) {
		this.syncErrorDesc = syncErrorDesc;
	}
  
	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
  
	public Date getSyncDate() {
		return this.syncDate;
	}
	
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}
  
	public String getSyncSign() {
		return this.syncSign;
	}
	
	public void setSyncSign(String syncSign) {
		this.syncSign = syncSign;
	}
  
	public String getWfcode() {
		return this.wfcode;
	}
	
	public void setWfcode(String wfcode) {
		this.wfcode = wfcode;
	}



	public void copy(SubprocessProject other){
  
		this.setDjId(other.getDjId());
  
		this.headunitcode= other.getHeadunitcode();  
		this.accpetDate= other.getAccpetDate();  
		this.projectUnitName= other.getProjectUnitName();  
		this.projectName= other.getProjectName();  
		this.projectType= other.getProjectType();  
		this.evaluationReason= other.getEvaluationReason();  
		this.evaluationContent= other.getEvaluationContent();  
		this.opinions= other.getOpinions();  
		this.createUserCode= other.getCreateUserCode();  
		this.createDate= other.getCreateDate();  
		this.syncErrorDesc= other.getSyncErrorDesc();  
		this.updateDate= other.getUpdateDate();  
		this.syncDate= other.getSyncDate();  
		this.syncSign= other.getSyncSign();  
		this.wfcode= other.getWfcode();
		this.optBaseInfo = other.getOptBaseInfo();
		this.supDjId = other.getSupDjId();
		this.orgCode = other.getOrgCode();
		this.finishDate   = other.getFinishDate();
	}
	
	public void copyNotNullProperty(SubprocessProject other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getHeadunitcode() != null)
			this.headunitcode= other.getHeadunitcode();  
		if( other.getAccpetDate() != null)
			this.accpetDate= other.getAccpetDate();  
		if( other.getProjectUnitName() != null)
			this.projectUnitName= other.getProjectUnitName();  
		if( other.getProjectName() != null)
			this.projectName= other.getProjectName();  
		if( other.getProjectType() != null)
			this.projectType= other.getProjectType();  
		if( other.getEvaluationReason() != null)
			this.evaluationReason= other.getEvaluationReason();  
		if( other.getEvaluationContent() != null)
			this.evaluationContent= other.getEvaluationContent();  
		if( other.getOpinions() != null)
			this.opinions= other.getOpinions();  
		if( other.getCreateUserCode() != null)
			this.createUserCode= other.getCreateUserCode();  
		if( other.getCreateDate() != null)
			this.createDate= other.getCreateDate();  
		if( other.getSyncErrorDesc() != null)
			this.syncErrorDesc= other.getSyncErrorDesc();  
		if( other.getUpdateDate() != null)
			this.updateDate= other.getUpdateDate();  
		if( other.getSyncDate() != null)
			this.syncDate= other.getSyncDate();  
		if( other.getSyncSign() != null)
			this.syncSign= other.getSyncSign();  
		if( other.getWfcode() != null)
			this.wfcode= other.getWfcode();
		if( other.getSupDjId() != null)
            this.supDjId = other.getSupDjId();
		if( other.getOptBaseInfo() != null)
            this.optBaseInfo = other.getOptBaseInfo();
	    if( other.getProcessType() != null)
            this.processType = other.getProcessType();
	    if( other.getFileName() != null)
            this.fileName = other.getFileName();
	    if( other.getDocFile() != null)
            this.docFile = other.getDocFile();
	    if( other.getOrgCode() != null)
	        this.orgCode = other.getOrgCode();
	    if( other.getFinishDate() != null)
	        this.finishDate   = other.getFinishDate();
	}
	
	public void clearProperties(){
  
		this.headunitcode= null;  
		this.accpetDate= null;  
		this.projectUnitName= null;  
		this.projectName= null;  
		this.projectType= null;  
		this.evaluationReason= null;  
		this.evaluationContent= null;  
		this.opinions= null;  
		this.createUserCode= null;  
		this.createDate= null;  
		this.syncErrorDesc= null;  
		this.updateDate= null;  
		this.syncDate= null;  
		this.syncSign= null;  
		this.wfcode= null;
		this.optBaseInfo = null;
		this.supDjId = null;
		this.processType = null;
		this.fileName = null;
		this.docFile  = null;
		this.orgCode = null;
		this.finishDate   = null;
	}
}
