package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaDuescollection implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  transaffairname;
	private Date  endtime;
	private String  remark;
	private String  sendinfo;
	private String  sendshortnew;
	private String  sendnotice;
	private String  sendemail;
	private String  isfinish;
	private String  sendpersens;
	private String  sendpersennames;
	private String  creater;
	private Date  createtime;
	private String finflag;

	public String getFinflag() {
        return finflag;
    }
    public void setFinflag(String finflag) {
        this.finflag = finflag;
    }
    // Constructors
	/** default constructor */
	public OaDuescollection() {
	}
	/** minimal constructor */
	public OaDuescollection(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaDuescollection(
	 String djId		
	,String  transaffairname,Date  endtime,String  remark,String  sendinfo,String  sendshortnew,String  sendnotice,String  sendemail,String  isfinish,String  sendpersens,String  sendpersennames,String  creater,Date  createtime) {
	
	
		this.djId = djId;		
	
		this.transaffairname= transaffairname;
		this.endtime= endtime;
		this.remark= remark;
		this.sendinfo= sendinfo;
		this.sendshortnew= sendshortnew;
		this.sendnotice= sendnotice;
		this.sendemail= sendemail;
		this.isfinish= isfinish;
		this.sendpersens= sendpersens;
		this.sendpersennames= sendpersennames;
		this.creater= creater;
		this.createtime= createtime;		
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
  
	public Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getSendinfo() {
		return this.sendinfo;
	}
	
	public void setSendinfo(String sendinfo) {
		this.sendinfo = sendinfo;
	}
  
	public String getSendshortnew() {
		return this.sendshortnew;
	}
	
	public void setSendshortnew(String sendshortnew) {
		this.sendshortnew = sendshortnew;
	}
  
	public String getSendnotice() {
		return this.sendnotice;
	}
	
	public void setSendnotice(String sendnotice) {
		this.sendnotice = sendnotice;
	}
  
	public String getSendemail() {
		return this.sendemail;
	}
	
	public void setSendemail(String sendemail) {
		this.sendemail = sendemail;
	}
  
	public String getIsfinish() {
		return this.isfinish;
	}
	
	public void setIsfinish(String isfinish) {
		this.isfinish = isfinish;
	}
  
	public String getSendpersens() {
		return this.sendpersens;
	}
	
	public void setSendpersens(String sendpersens) {
		this.sendpersens = sendpersens;
	}
  
	public String getSendpersennames() {
		return this.sendpersennames;
	}
	
	public void setSendpersennames(String sendpersennames) {
		this.sendpersennames = sendpersennames;
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



	public void copy(OaDuescollection other){
  
		this.setDjId(other.getDjId());
  
		this.transaffairname= other.getTransaffairname();  
		this.endtime= other.getEndtime();  
		this.remark= other.getRemark();  
		this.sendinfo= other.getSendinfo();  
		this.sendshortnew= other.getSendshortnew();  
		this.sendnotice= other.getSendnotice();  
		this.sendemail= other.getSendemail();  
		this.isfinish= other.getIsfinish();  
		this.sendpersens= other.getSendpersens();  
		this.sendpersennames= other.getSendpersennames();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();

	}
	
	public void copyNotNullProperty(OaDuescollection other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTransaffairname() != null)
			this.transaffairname= other.getTransaffairname();  
		if( other.getEndtime() != null)
			this.endtime= other.getEndtime();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getSendinfo() != null)
			this.sendinfo= other.getSendinfo();  
		if( other.getSendshortnew() != null)
			this.sendshortnew= other.getSendshortnew();  
		if( other.getSendnotice() != null)
			this.sendnotice= other.getSendnotice();  
		if( other.getSendemail() != null)
			this.sendemail= other.getSendemail();  
		if( other.getIsfinish() != null)
			this.isfinish= other.getIsfinish();  
		if( other.getSendpersens() != null)
			this.sendpersens= other.getSendpersens();  
		if( other.getSendpersennames() != null)
			this.sendpersennames= other.getSendpersennames();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();

	}
	
	public void clearProperties(){
  
		this.transaffairname= null;  
		this.endtime= null;  
		this.remark= null;  
		this.sendinfo= null;  
		this.sendshortnew= null;  
		this.sendnotice= null;  
		this.sendemail= null;  
		this.isfinish= null;  
		this.sendpersens= null;  
		this.sendpersennames= null;  
		this.creater= null;  
		this.createtime= null;

	}
}
