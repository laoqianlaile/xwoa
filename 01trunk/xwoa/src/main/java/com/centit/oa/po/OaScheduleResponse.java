package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaScheduleResponse implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  schno;
	private String  resType;
	private String  usercode;
	private Date  stopTime;
	private String  remark;
	private Date  createdate;

	// Constructors
	/** default constructor */
	public OaScheduleResponse() {
	}
	/** minimal constructor */
	public OaScheduleResponse(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaScheduleResponse(
	 String no		
	,String  schno,String  resType,String  usercode,Date  stopTime,String  remark,Date  createdate) {
	
	
		this.no = no;		
	
		this.schno= schno;
		this.resType= resType;
		this.usercode= usercode;
		this.stopTime= stopTime;
		this.remark= remark;
		this.createdate= createdate;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getSchno() {
		return this.schno;
	}
	
	public void setSchno(String schno) {
		this.schno = schno;
	}
  
	public String getResType() {
		return this.resType;
	}
	
	public void setResType(String resType) {
		this.resType = resType;
	}
  
	public String getUsercode() {
		return this.usercode;
	}
	
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
  
	public Date getStopTime() {
		return this.stopTime;
	}
	
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public Date getCreatedate() {
		return this.createdate;
	}
	
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}



	public void copy(OaScheduleResponse other){
  
		this.setNo(other.getNo());
  
		this.schno= other.getSchno();  
		this.resType= other.getResType();  
		this.usercode= other.getUsercode();  
		this.stopTime= other.getStopTime();  
		this.remark= other.getRemark();  
		this.createdate= other.getCreatedate();

	}
	
	public void copyNotNullProperty(OaScheduleResponse other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getSchno() != null)
			this.schno= other.getSchno();  
		if( other.getResType() != null)
			this.resType= other.getResType();  
		if( other.getUsercode() != null)
			this.usercode= other.getUsercode();  
		if( other.getStopTime() != null)
			this.stopTime= other.getStopTime();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreatedate() != null)
			this.createdate= other.getCreatedate();

	}
	
	public void clearProperties(){
  
		this.schno= null;  
		this.resType= null;  
		this.usercode= null;  
		this.stopTime= null;  
		this.remark= null;  
		this.createdate= null;

	}
}
