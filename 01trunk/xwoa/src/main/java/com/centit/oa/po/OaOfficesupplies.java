package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaOfficesupplies implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  layoutno;
	private String  transaffairname;
	private Date  applydate;
	private String  applyuser;
	private String  creater;
	private String  remarkContent;
	private String  remark;
	private Date  creatertime;
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
	public OaOfficesupplies() {
	}
	/** minimal constructor */
	public OaOfficesupplies(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaOfficesupplies(
	 String djId		
	,String  layoutno,String  transaffairname,Date  applydate,String  applyuser,String  creater,String  remarkContent,String  remark,Date  creatertime,String  flowcode,Long  flowInstId,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optId) {
	
	
		this.djId = djId;		
	
		this.layoutno= layoutno;
		this.transaffairname= transaffairname;
		this.applydate= applydate;
		this.applyuser= applyuser;
		this.creater= creater;
		this.remarkContent= remarkContent;
		this.remark= remark;
		this.creatertime= creatertime;
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
  
	public String getLayoutno() {
		return this.layoutno;
	}
	
	public void setLayoutno(String layoutno) {
		this.layoutno = layoutno;
	}
  
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
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public String getRemarkContent() {
		return this.remarkContent;
	}
	
	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent;
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



	public void copy(OaOfficesupplies other){
  
		this.setDjId(other.getDjId());
  
		this.layoutno= other.getLayoutno();  
		this.transaffairname= other.getTransaffairname();  
		this.applydate= other.getApplydate();  
		this.applyuser= other.getApplyuser();  
		this.creater= other.getCreater();  
		this.remarkContent= other.getRemarkContent();  
		this.remark= other.getRemark();  
		this.creatertime= other.getCreatertime();  
		this.flowcode= other.getFlowcode();  
		this.flowInstId= other.getFlowInstId();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();  
		this.solvenote= other.getSolvenote();  
		this.optId= other.getOptId();

	}
	
	public void copyNotNullProperty(OaOfficesupplies other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getLayoutno() != null)
			this.layoutno= other.getLayoutno();  
		if( other.getTransaffairname() != null)
			this.transaffairname= other.getTransaffairname();  
		if( other.getApplydate() != null)
			this.applydate= other.getApplydate();  
		if( other.getApplyuser() != null)
			this.applyuser= other.getApplyuser();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getRemarkContent() != null)
			this.remarkContent= other.getRemarkContent();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
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
  
		this.layoutno= null;  
		this.transaffairname= null;  
		this.applydate= null;  
		this.applyuser= null;  
		this.creater= null;  
		this.remarkContent= null;  
		this.remark= null;  
		this.creatertime= null;  
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
