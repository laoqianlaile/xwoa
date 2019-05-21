package com.centit.powerbase.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class Suppowerstatechglog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String chgno;

	private String  itemId;
	private Long  version;
	private Date  beginTime;
	private Date  endTime;
	private String  chgType;
	private String  recoder;
	private String  remark;

	// Constructors
	/** default constructor */
	public Suppowerstatechglog() {
	}
	/** minimal constructor */
	public Suppowerstatechglog(
	        String chgno		
		) {
	
	
		this.chgno = chgno;		
			
	}

/** full constructor */
	public Suppowerstatechglog(
	        String chgno		
	,String  itemId,Long  version,Date  beginTime,Date  endTime,String  chgType,String  recoder,String  remark) {
	
	
		this.chgno = chgno;		
	
		this.itemId= itemId;
		this.version= version;
		this.beginTime= beginTime;
		this.endTime= endTime;
		this.chgType= chgType;
		this.recoder= recoder;
		this.remark= remark;		
	}
	

  
	public String getChgno() {
		return this.chgno;
	}

	public void setChgno(String chgno) {
		this.chgno = chgno;
	}
	// Property accessors
  
	public String getItemId() {
		return this.itemId;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
  
	public Long getVersion() {
		return this.version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
  
	public Date getBeginTime() {
		return this.beginTime;
	}
	
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
  
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
  
	public String getChgType() {
		return this.chgType;
	}
	
	public void setChgType(String chgType) {
		this.chgType = chgType;
	}
  
	public String getRecoder() {
		return this.recoder;
	}
	
	public void setRecoder(String recoder) {
		this.recoder = recoder;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}



	public void copy(Suppowerstatechglog other){
  
		this.setChgno(other.getChgno());
  
		this.itemId= other.getItemId();  
		this.version= other.getVersion();  
		this.beginTime= other.getBeginTime();  
		this.endTime= other.getEndTime();  
		this.chgType= other.getChgType();  
		this.recoder= other.getRecoder();  
		this.remark= other.getRemark();

	}
	
	public void copyNotNullProperty(Suppowerstatechglog other){
  
	if( other.getChgno() != null)
		this.setChgno(other.getChgno());
  
		if( other.getItemId() != null)
			this.itemId= other.getItemId();  
		if( other.getVersion() != null)
			this.version= other.getVersion();  
		if( other.getBeginTime() != null)
			this.beginTime= other.getBeginTime();  
		if( other.getEndTime() != null)
			this.endTime= other.getEndTime();  
		if( other.getChgType() != null)
			this.chgType= other.getChgType();  
		if( other.getRecoder() != null)
			this.recoder= other.getRecoder();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();

	}
	
	public void clearProperties(){
  
		this.itemId= null;  
		this.version= null;  
		this.beginTime= null;  
		this.endTime= null;  
		this.chgType= null;  
		this.recoder= null;  
		this.remark= null;

	}
}
