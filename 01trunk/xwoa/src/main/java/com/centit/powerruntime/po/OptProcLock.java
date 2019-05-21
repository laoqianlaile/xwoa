package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OptProcLock implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String lockid;

	private String  djId;
	private String  usercode;
	private Date  locksettime;
	private String  islocked;

	// Constructors
	/** default constructor */
	public OptProcLock() {
	}
	/** minimal constructor */

/** full constructor */
	public OptProcLock(
	 String lockid		
	,String  djId,String  usercode,Date  locksettime,String  islocked) {
	
	
		this.lockid = lockid;		
	
		this.djId= djId;
		this.usercode= usercode;
		this.locksettime= locksettime;
		this.islocked= islocked;		
	}
	

  
	public String getLockid() {
		return this.lockid;
	}

	public void setLockid(String lockid) {
		this.lockid = lockid;
	}
	// Property accessors
  
	public String getDjId() {
		return this.djId;
	}
	
	public void setDjId(String djId) {
		this.djId = djId;
	}
  
	public String getUsercode() {
		return this.usercode;
	}
	
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
  
	public Date getLocksettime() {
		return this.locksettime;
	}
	
	public void setLocksettime(Date locksettime) {
		this.locksettime = locksettime;
	}
  
	public String getIslocked() {
		return this.islocked;
	}
	
	public void setIslocked(String islocked) {
		this.islocked = islocked;
	}



	public void copy(OptProcLock other){
  
		this.setLockid(other.getLockid());
  
		this.djId= other.getDjId();  
		this.usercode= other.getUsercode();  
		this.locksettime= other.getLocksettime();  
		this.islocked= other.getIslocked();

	}
	
	public void copyNotNullProperty(OptProcLock other){
  
	if( other.getLockid() != null)
		this.setLockid(other.getLockid());
  
		if( other.getDjId() != null)
			this.djId= other.getDjId();  
		if( other.getUsercode() != null)
			this.usercode= other.getUsercode();  
		if( other.getLocksettime() != null)
			this.locksettime= other.getLocksettime();  
		if( other.getIslocked() != null)
			this.islocked= other.getIslocked();

	}
	
	public void clearProperties(){
  
		this.djId= null;  
		this.usercode= null;  
		this.locksettime= null;  
		this.islocked= null;

	}
}
