package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaLeaveapply implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  transaffairname;
	private Date  applydate;
	private Date  endtime;
	private String  leavenum;
	private String  applyuser;
	private String  postleve;
	private String  remarkContent;
	private Date  creatertime;
	private String  creater;
	private Date  disploytime;
	private String  disployuser;
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
	public OaLeaveapply() {
	}
	/** minimal constructor */
	public OaLeaveapply(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaLeaveapply(
	 String djId		
	,String  transaffairname,Date  applydate,Date  endtime,String  leavenum,String  applyuser,String  postleve,String  remarkContent,Date  creatertime,String  creater,Date  disploytime,String  disployuser,String  flowcode,Long  flowInstId,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optId) {
	
	
		this.djId = djId;		
	
		this.transaffairname= transaffairname;
		this.applydate= applydate;
		this.endtime= endtime;
		this.leavenum= leavenum;
		this.applyuser= applyuser;
		this.postleve= postleve;
		this.remarkContent= remarkContent;
		this.creatertime= creatertime;
		this.creater= creater;
		this.disploytime= disploytime;
		this.disployuser= disployuser;
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
  
	public Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
  
	public String getLeavenum() {
		return this.leavenum;
	}
	
	public void setLeavenum(String leavenum) {
		this.leavenum = leavenum;
	}
  
	public String getApplyuser() {
		return this.applyuser;
	}
	
	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}
  
	public String getPostleve() {
		return this.postleve;
	}
	
	public void setPostleve(String postleve) {
		this.postleve = postleve;
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
  
	public Date getDisploytime() {
		return this.disploytime;
	}
	
	public void setDisploytime(Date disploytime) {
		this.disploytime = disploytime;
	}
  
	public String getDisployuser() {
		return this.disployuser;
	}
	
	public void setDisployuser(String disployuser) {
		this.disployuser = disployuser;
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



	public void copy(OaLeaveapply other){
  
		this.setDjId(other.getDjId());
  
		this.transaffairname= other.getTransaffairname();  
		this.applydate= other.getApplydate();  
		this.endtime= other.getEndtime();  
		this.leavenum= other.getLeavenum();  
		this.applyuser= other.getApplyuser();  
		this.postleve= other.getPostleve();  
		this.remarkContent= other.getRemarkContent();  
		this.creatertime= other.getCreatertime();  
		this.creater= other.getCreater();  
		this.disploytime= other.getDisploytime();  
		this.disployuser= other.getDisployuser();  
		this.flowcode= other.getFlowcode();  
		this.flowInstId= other.getFlowInstId();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();  
		this.solvenote= other.getSolvenote();  
		this.optId= other.getOptId();

	}
	
	public void copyNotNullProperty(OaLeaveapply other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTransaffairname() != null)
			this.transaffairname= other.getTransaffairname();  
		if( other.getApplydate() != null)
			this.applydate= other.getApplydate();  
		if( other.getEndtime() != null)
			this.endtime= other.getEndtime();  
		if( other.getLeavenum() != null)
			this.leavenum= other.getLeavenum();  
		if( other.getApplyuser() != null)
			this.applyuser= other.getApplyuser();  
		if( other.getPostleve() != null)
			this.postleve= other.getPostleve();  
		if( other.getRemarkContent() != null)
			this.remarkContent= other.getRemarkContent();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getDisploytime() != null)
			this.disploytime= other.getDisploytime();  
		if( other.getDisployuser() != null)
			this.disployuser= other.getDisployuser();  
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
		this.endtime= null;  
		this.leavenum= null;  
		this.applyuser= null;  
		this.postleve= null;  
		this.remarkContent= null;  
		this.creatertime= null;  
		this.creater= null;  
		this.disploytime= null;  
		this.disployuser= null;  
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
