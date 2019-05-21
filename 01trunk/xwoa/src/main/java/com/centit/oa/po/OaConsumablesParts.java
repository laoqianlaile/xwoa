package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaConsumablesParts implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  transaffairname;
	private Date  applydate;
	private String  applyuser;
	private String  unitcode;
	private String  consContent;
	private Long  consNum;
	private String  partsContent;
	private Long  partsNum;
	private String  remark;
	private Date  creatertime;
	private String  creater;
	private String  flowcode;
	private Long  flowInstId;
	private String  bizstate;
	private String  biztype;
	private String  solvestatus;
	private Date  solvetime;
	private String  solvenote;
	private String  optId;
	
	private OptBaseInfo optBaseInfo;

	// Constructors
	/** default constructor */
	public OaConsumablesParts() {
	}
	/** minimal constructor */
	public OaConsumablesParts(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaConsumablesParts(
	 String djId		
	,String  transaffairname,Date  applydate,String  applyuser,String  unitcode,String  consContent,Long  consNum,String  partsContent,Long  partsNum,String  remark,Date  creatertime,String  creater,String  flowcode,Long  flowInstId,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optId) {
	
	
		this.djId = djId;		
	
		this.transaffairname= transaffairname;
		this.applydate= applydate;
		this.applyuser= applyuser;
		this.unitcode= unitcode;
		this.consContent= consContent;
		this.consNum= consNum;
		this.partsContent= partsContent;
		this.partsNum= partsNum;
		this.remark= remark;
		this.creatertime= creatertime;
		this.creater= creater;
		this.flowcode= flowcode;
		this.flowInstId= flowInstId;
		this.bizstate= bizstate;
		this.biztype= biztype;
		this.solvestatus= solvestatus;
		this.solvetime= solvetime;
		this.solvenote= solvenote;
		this.optId= optId;		
	}
	

  
	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}
	// Property accessors
  
	public String getTransaffairname() {
		return this.transaffairname;
	}
	
	public void setTransaffairname(String transaffairname) {
		this.transaffairname = transaffairname;
	}
  
	public Date getApplydate() {
		return this.applydate;
	}
	
	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}
  
	public String getApplyuser() {
		return this.applyuser;
	}
	
	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}
  
	public String getUnitcode() {
		return this.unitcode;
	}
	
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
  
	public String getConsContent() {
		return this.consContent;
	}
	
	public void setConsContent(String consContent) {
		this.consContent = consContent;
	}
  
	public Long getConsNum() {
		return this.consNum;
	}
	
	public void setConsNum(Long consNum) {
		this.consNum = consNum;
	}
  
	public String getPartsContent() {
		return this.partsContent;
	}
	
	public void setPartsContent(String partsContent) {
		this.partsContent = partsContent;
	}
  
	public Long getPartsNum() {
		return this.partsNum;
	}
	
	public void setPartsNum(Long partsNum) {
		this.partsNum = partsNum;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public String getFlowcode() {
		return this.flowcode;
	}
	
	public void setFlowcode(String flowcode) {
		this.flowcode = flowcode;
	}
  
	public Long getFlowInstId() {
		return this.flowInstId;
	}
	
	public void setFlowInstId(Long flowInstId) {
		this.flowInstId = flowInstId;
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
  
	public String getOptId() {
		return this.optId;
	}
	
	public void setOptId(String optId) {
		this.optId = optId;
	}



	public void copy(OaConsumablesParts other){
  
		this.setDjId(other.getDjId());
  
		this.transaffairname= other.getTransaffairname();  
		this.applydate= other.getApplydate();  
		this.applyuser= other.getApplyuser();  
		this.unitcode= other.getUnitcode();  
		this.consContent= other.getConsContent();  
		this.consNum= other.getConsNum();  
		this.partsContent= other.getPartsContent();  
		this.partsNum= other.getPartsNum();  
		this.remark= other.getRemark();  
		this.creatertime= other.getCreatertime();  
		this.creater= other.getCreater();  
		this.flowcode= other.getFlowcode();  
		this.flowInstId= other.getFlowInstId();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();  
		this.solvenote= other.getSolvenote();  
		this.optId= other.getOptId();

	}
	
	public void copyNotNullProperty(OaConsumablesParts other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTransaffairname() != null)
			this.transaffairname= other.getTransaffairname();  
		if( other.getApplydate() != null)
			this.applydate= other.getApplydate();  
		if( other.getApplyuser() != null)
			this.applyuser= other.getApplyuser();  
		if( other.getUnitcode() != null)
			this.unitcode= other.getUnitcode();  
		if( other.getConsContent() != null)
			this.consContent= other.getConsContent();  
		if( other.getConsNum() != null)
			this.consNum= other.getConsNum();  
		if( other.getPartsContent() != null)
			this.partsContent= other.getPartsContent();  
		if( other.getPartsNum() != null)
			this.partsNum= other.getPartsNum();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getFlowcode() != null)
			this.flowcode= other.getFlowcode();  
		if( other.getFlowInstId() != null)
			this.flowInstId= other.getFlowInstId();  
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
		if( other.getOptId() != null)
			this.optId= other.getOptId();

	}
	
	public void clearProperties(){
  
		this.transaffairname= null;  
		this.applydate= null;  
		this.applyuser= null;  
		this.unitcode= null;  
		this.consContent= null;  
		this.consNum= null;  
		this.partsContent= null;  
		this.partsNum= null;  
		this.remark= null;  
		this.creatertime= null;  
		this.creater= null;  
		this.flowcode= null;  
		this.flowInstId= null;  
		this.bizstate= null;  
		this.biztype= null;  
		this.solvestatus= null;  
		this.solvetime= null;  
		this.solvenote= null;  
		this.optId= null;

	}
    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }
    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
	
}
