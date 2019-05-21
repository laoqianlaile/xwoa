package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaKqnotification implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  transaffairname;
	private String  periods;
	private String  kqdepname;
	private Date  kqtime;
	private String  visitors;
	private String  contactuser;
	private String  contactphone;
	private String  kqcontent;
	private String  creater;
	private Date  createtime;
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
	public OaKqnotification() {
	}
	/** minimal constructor */
	public OaKqnotification(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaKqnotification(
	 String djId		
	,String  transaffairname,String  periods,String  kqdepname,Date  kqtime,String  visitors,String  contactuser,String  contactphone,String  kqcontent,String  creater,Date  createtime,String  flowcode,Long  flowInstId,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optId) {
	
	
		this.djId = djId;		
	
		this.transaffairname= transaffairname;
		this.periods= periods;
		this.kqdepname= kqdepname;
		this.kqtime= kqtime;
		this.visitors= visitors;
		this.contactuser= contactuser;
		this.contactphone= contactphone;
		this.kqcontent= kqcontent;
		this.creater= creater;
		this.createtime= createtime;
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
  
	public String getPeriods() {
		return this.periods;
	}
	
	public void setPeriods(String periods) {
		this.periods = periods;
	}
  
	public String getKqdepname() {
		return this.kqdepname;
	}
	
	public void setKqdepname(String kqdepname) {
		this.kqdepname = kqdepname;
	}
  
	public Date getKqtime() {
		return this.kqtime;
	}
	
	public void setKqtime(Date kqtime) {
		this.kqtime = kqtime;
	}
  
	public String getVisitors() {
		return this.visitors;
	}
	
	public void setVisitors(String visitors) {
		this.visitors = visitors;
	}
  
	public String getContactuser() {
		return this.contactuser;
	}
	
	public void setContactuser(String contactuser) {
		this.contactuser = contactuser;
	}
  
	public String getContactphone() {
		return this.contactphone;
	}
	
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
  
	public String getKqcontent() {
		return this.kqcontent;
	}
	
	public void setKqcontent(String kqcontent) {
		this.kqcontent = kqcontent;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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



	public void copy(OaKqnotification other){
  
		this.setDjId(other.getDjId());
  
		this.transaffairname= other.getTransaffairname();  
		this.periods= other.getPeriods();  
		this.kqdepname= other.getKqdepname();  
		this.kqtime= other.getKqtime();  
		this.visitors= other.getVisitors();  
		this.contactuser= other.getContactuser();  
		this.contactphone= other.getContactphone();  
		this.kqcontent= other.getKqcontent();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.flowcode= other.getFlowcode();  
		this.flowInstId= other.getFlowInstId();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();  
		this.solvenote= other.getSolvenote();  
		this.optId= other.getOptId();

	}
	
	public void copyNotNullProperty(OaKqnotification other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTransaffairname() != null)
			this.transaffairname= other.getTransaffairname();  
		if( other.getPeriods() != null)
			this.periods= other.getPeriods();  
		if( other.getKqdepname() != null)
			this.kqdepname= other.getKqdepname();  
		if( other.getKqtime() != null)
			this.kqtime= other.getKqtime();  
		if( other.getVisitors() != null)
			this.visitors= other.getVisitors();  
		if( other.getContactuser() != null)
			this.contactuser= other.getContactuser();  
		if( other.getContactphone() != null)
			this.contactphone= other.getContactphone();  
		if( other.getKqcontent() != null)
			this.kqcontent= other.getKqcontent();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
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
		this.periods= null;  
		this.kqdepname= null;  
		this.kqtime= null;  
		this.visitors= null;  
		this.contactuser= null;  
		this.contactphone= null;  
		this.kqcontent= null;  
		this.creater= null;  
		this.createtime= null;  
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
