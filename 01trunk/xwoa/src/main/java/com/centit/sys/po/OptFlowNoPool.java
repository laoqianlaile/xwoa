package com.centit.sys.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OptFlowNoPool implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OptFlowNoPoolId cid;


	
	private java.util.Date  createDate;

	// Constructors
	/** default constructor */
	public OptFlowNoPool() {
	}
	/** minimal constructor */
	public OptFlowNoPool(OptFlowNoPoolId id ) {
		this.cid = id; 
	}

/** full constructor */
	public OptFlowNoPool(OptFlowNoPoolId id
	,java.util.Date lastCodeDate) {
		this.cid = id; 
		this.createDate= lastCodeDate;		
	}

	public OptFlowNoPoolId getCid() {
		return this.cid;
	}
	
	public void setCid(OptFlowNoPoolId id) {
		this.cid = id;
	}
  
	public String getOwnerCode() {
		if(this.cid==null)
			this.cid = new OptFlowNoPoolId();
		return this.cid.getOwnerCode();
	}
	
	public void setOwnerCode(String ownerCode) {
		if(this.cid==null)
			this.cid = new OptFlowNoPoolId();
		this.cid.setOwnerCode(ownerCode);
	}
  
	public Date getCodeDate() {
		if(this.cid==null)
			this.cid = new OptFlowNoPoolId();
		return this.cid.getCodeDate();
	}
	
	public void setCodeDate (Date codeDate) {
		if(this.cid==null)
			this.cid = new OptFlowNoPoolId();
		this.cid.setCodeDate(codeDate);
	}
  
	public String getCodeCode() {
		if(this.cid==null)
			this.cid = new OptFlowNoPoolId();
		return this.cid.getCodeCode();
	}
	
	public void setCodeCode(String codeCode) {
		if(this.cid==null)
			this.cid = new OptFlowNoPoolId();
		this.cid.setCodeCode(codeCode);
	}
	
	public Long getCurNo() {
	    if(this.cid==null)
            this.cid = new OptFlowNoPoolId();
        return this.cid.getCurNo();
	}
	
	public void setCurNo(Long curNo) {
	    if(this.cid==null)
            this.cid = new OptFlowNoPoolId();
        this.cid.setCurNo(curNo);
	}
  
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(java.util.Date lastCodeDate) {
		this.createDate = lastCodeDate;
	}

	public void copy(OptFlowNoPool other){
  
		this.setOwnerCode(other.getOwnerCode());  
		this.setCodeDate(other.getCodeDate());  
		this.setCodeCode(other.getCodeCode());
		this.setCurNo(other.getCurNo());
		
		this.createDate= other.getCreateDate();

	}
	
	public void copyNotNullProperty(OptFlowNoPool other){
  
	if( other.getOwnerCode() != null)
		this.setOwnerCode(other.getOwnerCode());  
	if( other.getCodeDate() != null)
		this.setCodeDate(other.getCodeDate());  
	if( other.getCodeCode() != null)
		this.setCodeCode(other.getCodeCode());
  
		if( other.getCurNo() != null)
		    this.setCurNo(other.getCurNo());
		
		if( other.getCreateDate() != null)
			this.createDate= other.getCreateDate();

	}
	
	public void clearProperties(){
  
		this.createDate= null;

	}
}
