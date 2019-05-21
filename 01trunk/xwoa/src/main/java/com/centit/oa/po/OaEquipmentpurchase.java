package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaEquipmentpurchase implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  transaffairname;
	private String  itentype;
	private String  ecategory;
	private String  tmodel;
	private Long  thenumber;
	private Long  theprice;
	private String  serialnumber;
	private Date  applydate;
	private String  applyuser;
	private String  remark;
	private String  remarkContent;
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
	public OaEquipmentpurchase() {
	}
	/** minimal constructor */
	public OaEquipmentpurchase(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaEquipmentpurchase(
	 String djId		
	,String  transaffairname,String  itentype,String  ecategory,String  tmodel,Long  thenumber,Long  theprice,String  serialnumber,Date  applydate,String  applyuser,String  remark,String  remarkContent,Date  creatertime,String  creater,String  flowcode,Long  flowInstId,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optId) {
	
	
		this.djId = djId;		
	
		this.transaffairname= transaffairname;
		this.itentype= itentype;
		this.ecategory= ecategory;
		this.tmodel= tmodel;
		this.thenumber= thenumber;
		this.theprice= theprice;
		this.serialnumber= serialnumber;
		this.applydate= applydate;
		this.applyuser= applyuser;
		this.remark= remark;
		this.remarkContent= remarkContent;
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
  
	public String getItentype() {
		return this.itentype;
	}
	
	public void setItentype(String itentype) {
		this.itentype = itentype;
	}
  
	public String getEcategory() {
		return this.ecategory;
	}
	
	public void setEcategory(String ecategory) {
		this.ecategory = ecategory;
	}
  
	public String getTmodel() {
		return this.tmodel;
	}
	
	public void setTmodel(String tmodel) {
		this.tmodel = tmodel;
	}
  
	public Long getThenumber() {
		return this.thenumber;
	}
	
	public void setThenumber(Long thenumber) {
		this.thenumber = thenumber;
	}
  
	public Long getTheprice() {
		return this.theprice;
	}
	
	public void setTheprice(Long theprice) {
		this.theprice = theprice;
	}
  
	public String getSerialnumber() {
		return this.serialnumber;
	}
	
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
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
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getRemarkContent() {
		return this.remarkContent;
	}
	
	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent;
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



	public void copy(OaEquipmentpurchase other){
  
		this.setDjId(other.getDjId());
  
		this.transaffairname= other.getTransaffairname();  
		this.itentype= other.getItentype();  
		this.ecategory= other.getEcategory();  
		this.tmodel= other.getTmodel();  
		this.thenumber= other.getThenumber();  
		this.theprice= other.getTheprice();  
		this.serialnumber= other.getSerialnumber();  
		this.applydate= other.getApplydate();  
		this.applyuser= other.getApplyuser();  
		this.remark= other.getRemark();  
		this.remarkContent= other.getRemarkContent();  
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
	
	public void copyNotNullProperty(OaEquipmentpurchase other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTransaffairname() != null)
			this.transaffairname= other.getTransaffairname();  
		if( other.getItentype() != null)
			this.itentype= other.getItentype();  
		if( other.getEcategory() != null)
			this.ecategory= other.getEcategory();  
		if( other.getTmodel() != null)
			this.tmodel= other.getTmodel();  
		if( other.getThenumber() != null)
			this.thenumber= other.getThenumber();  
		if( other.getTheprice() != null)
			this.theprice= other.getTheprice();  
		if( other.getSerialnumber() != null)
			this.serialnumber= other.getSerialnumber();  
		if( other.getApplydate() != null)
			this.applydate= other.getApplydate();  
		if( other.getApplyuser() != null)
			this.applyuser= other.getApplyuser();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getRemarkContent() != null)
			this.remarkContent= other.getRemarkContent();  
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
		this.itentype= null;  
		this.ecategory= null;  
		this.tmodel= null;  
		this.thenumber= null;  
		this.theprice= null;  
		this.serialnumber= null;  
		this.applydate= null;  
		this.applyuser= null;  
		this.remark= null;  
		this.remarkContent= null;  
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
