package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaRemindInformation implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  title;
	private String  usercode;
	private String  thesign;
	private String  djid;
	private String  reType;
	private Date  begtime;
	private Date  endtime;
	private Long  frequency;
	private String  remark;
	private String  creater;
	private Date  createtime;
	private String  createRemark;

	private String isSendMsg;
	// Constructors
	/** default constructor */
	public OaRemindInformation() {
	}
	/** minimal constructor */
	public OaRemindInformation(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaRemindInformation(
	 String no		
	,String  title,String  usercode,String  thesign,String  djid,String  reType,Date  begtime,Date  endtime,Long  frequency,String  remark,String  creater,Date  createtime,String  createRemark, String isSendMsg) {
	
	
		this.no = no;		
	
		this.title= title;
		this.usercode= usercode;
		this.thesign= thesign;
		this.djid= djid;
		this.reType= reType;
		this.begtime= begtime;
		this.endtime= endtime;
		this.frequency= frequency;
		this.remark= remark;
		this.creater= creater;
		this.createtime= createtime;
		this.createRemark= createRemark;
		this.isSendMsg = isSendMsg;
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
  
	public String getUsercode() {
		return this.usercode;
	}
	
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
  
	public String getThesign() {
		return this.thesign;
	}
	
	public void setThesign(String thesign) {
		this.thesign = thesign;
	}
  
	public String getDjid() {
		return this.djid;
	}
	
	public void setDjid(String djid) {
		this.djid = djid;
	}
  
	public String getReType() {
		return this.reType;
	}
	
	public void setReType(String reType) {
		this.reType = reType;
	}
  
	public Date getBegtime() {
		return this.begtime;
	}
	
	public void setBegtime(Date begtime) {
		this.begtime = begtime;
	}
  
	public Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
  
	public Long getFrequency() {
		return this.frequency;
	}
	
	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
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
  
	public String getCreateRemark() {
		return this.createRemark;
	}
	
	public void setCreateRemark(String createRemark) {
		this.createRemark = createRemark;
	}
	
	public String getIsSendMsg() {
        return isSendMsg;
    }
    public void setIsSendMsg(String isSendMsg) {
        this.isSendMsg = isSendMsg;
    }
    public void copy(OaRemindInformation other){
  
		this.setNo(other.getNo());
  
		this.title= other.getTitle();  
		this.usercode= other.getUsercode();  
		this.thesign= other.getThesign();  
		this.djid= other.getDjid();  
		this.reType= other.getReType();  
		this.begtime= other.getBegtime();  
		this.endtime= other.getEndtime();  
		this.frequency= other.getFrequency();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.createRemark= other.getCreateRemark();
		this.isSendMsg = other.getIsSendMsg();
	}
	
	public void copyNotNullProperty(OaRemindInformation other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getUsercode() != null)
			this.usercode= other.getUsercode();  
		if( other.getThesign() != null)
			this.thesign= other.getThesign();  
		if( other.getDjid() != null)
			this.djid= other.getDjid();  
		if( other.getReType() != null)
			this.reType= other.getReType();  
		if( other.getBegtime() != null)
			this.begtime= other.getBegtime();  
		if( other.getEndtime() != null)
			this.endtime= other.getEndtime();  
		if( other.getFrequency() != null)
			this.frequency= other.getFrequency();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getCreateRemark() != null)
			this.createRemark= other.getCreateRemark();
		if( other.getIsSendMsg() != null)
		    this.isSendMsg = other.isSendMsg;

	}
	
	public void clearProperties(){
  
		this.title= null;  
		this.usercode= null;  
		this.thesign= null;  
		this.djid= null;  
		this.reType= null;  
		this.begtime= null;  
		this.endtime= null;  
		this.frequency= null;  
		this.remark= null;  
		this.creater= null;  
		this.createtime= null;  
		this.createRemark= null;
		this.isSendMsg = null;
	}
}
