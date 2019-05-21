package com.centit.powerbase.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class PowerUserInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private PowerUserInfoId cid;


	private String  setoperator;
	private Date  settime;
	
	private Long version;//页面传递权力版本号

	// Constructors
	/** default constructor */
	public PowerUserInfo() {
	}
	/** minimal constructor */
	public PowerUserInfo(PowerUserInfoId id 
				
		) {
		this.cid = id; 
			
			
	}

/** full constructor */
	public PowerUserInfo(PowerUserInfoId id
			
	,String  setoperator,Date  settime) {
		this.cid = id; 
			
	
		this.setoperator= setoperator;
		this.settime= settime;		
	}

	public PowerUserInfoId getCid() {
		return this.cid;
	}
	
	public void setCid(PowerUserInfoId id) {
		this.cid = id;
	}
  
	public String getUsercode() {
		if(this.cid==null)
			this.cid = new PowerUserInfoId();
		return this.cid.getUsercode();
	}
	
	public void setUsercode(String usercode) {
		if(this.cid==null)
			this.cid = new PowerUserInfoId();
		this.cid.setUsercode(usercode);
	}
  
	public String getItemId() {
		if(this.cid==null)
			this.cid = new PowerUserInfoId();
		return this.cid.getItemId();
	}
	
	public void setItemId(String itemId) {
		if(this.cid==null)
			this.cid = new PowerUserInfoId();
		this.cid.setItemId(itemId);
	}
	
	

	// Property accessors
  
	public String getSetoperator() {
		return this.setoperator;
	}
	
	public void setSetoperator(String setoperator) {
		this.setoperator = setoperator;
	}
  
	public Date getSettime() {
		return this.settime;
	}
	
	public void setSettime(Date settime) {
		this.settime = settime;
	}



	public void copy(PowerUserInfo other){
  
		this.setUsercode(other.getUsercode());  
		this.setItemId(other.getItemId());
  
		this.setoperator= other.getSetoperator();  
		this.settime= other.getSettime();

	}
	
	public void copyNotNullProperty(PowerUserInfo other){
  
	if( other.getUsercode() != null)
		this.setUsercode(other.getUsercode());  
	if( other.getItemId() != null)
		this.setItemId(other.getItemId());
  
		if( other.getSetoperator() != null)
			this.setoperator= other.getSetoperator();  
		if( other.getSettime() != null)
			this.settime= other.getSettime();

	}
	
	public void clearProperties(){
  
		this.setoperator= null;  
		this.settime= null;

	}
    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
}
