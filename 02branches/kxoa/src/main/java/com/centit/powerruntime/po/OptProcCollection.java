package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OptProcCollection implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OptProcCollectionId cid;


	private String  atttype;
	private Date  attsettime;
	private String  isatt;
	private Date  removesettime;

	// Constructors
	/** default constructor 
	 * @param optProcCollectionId */
	   public OptProcCollection() {
	    }
	    
	
	public OptProcCollection(OptProcCollectionId id) {
	    this.cid = id; 
	}
	
	
	/** minimal constructor */
	public OptProcCollection(OptProcCollectionId id 
				
		,String  atttype,Date  attsettime,String  isatt) {
		this.cid = id; 
			
	
		this.atttype= atttype; 
		this.attsettime= attsettime; 
		this.isatt= isatt; 		
	}

/** full constructor */
	public OptProcCollection(OptProcCollectionId id
			
	,String  atttype,Date  attsettime,String  isatt,Date  removesettime) {
		this.cid = id; 
			
	
		this.atttype= atttype;
		this.attsettime= attsettime;
		this.isatt= isatt;
		this.removesettime= removesettime;		
	}

	public OptProcCollectionId getCid() {
		return this.cid;
	}
	
	public void setCid(OptProcCollectionId id) {
		this.cid = id;
	}
  
	public String getDjId() {
		if(this.cid==null)
			this.cid = new OptProcCollectionId();
		return this.cid.getDjId();
	}
	
	public void setDjId(String djId) {
		if(this.cid==null)
			this.cid = new OptProcCollectionId();
		this.cid.setDjId(djId);
	}
  
	public String getUsercode() {
		if(this.cid==null)
			this.cid = new OptProcCollectionId();
		return this.cid.getUsercode();
	}
	
	public void setUsercode(String usercode) {
		if(this.cid==null)
			this.cid = new OptProcCollectionId();
		this.cid.setUsercode(usercode);
	}
	
	

	// Property accessors
  
	public String getAtttype() {
		return this.atttype;
	}
	
	public void setAtttype(String atttype) {
		this.atttype = atttype;
	}
  
	public Date getAttsettime() {
		return this.attsettime;
	}
	
	public void setAttsettime(Date attsettime) {
		this.attsettime = attsettime;
	}
  
	public String getIsatt() {
		return this.isatt;
	}
	
	public void setIsatt(String isatt) {
		this.isatt = isatt;
	}
  
	public Date getRemovesettime() {
		return this.removesettime;
	}
	
	public void setRemovesettime(Date removesettime) {
		this.removesettime = removesettime;
	}



	public void copy(OptProcCollection other){
  
		this.setDjId(other.getDjId());  
		this.setUsercode(other.getUsercode());
  
		this.atttype= other.getAtttype();  
		this.attsettime= other.getAttsettime();  
		this.isatt= other.getIsatt();  
		this.removesettime= other.getRemovesettime();

	}
	
	public void copyNotNullProperty(OptProcCollection other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());  
	if( other.getUsercode() != null)
		this.setUsercode(other.getUsercode());
  
		if( other.getAtttype() != null)
			this.atttype= other.getAtttype();  
		if( other.getAttsettime() != null)
			this.attsettime= other.getAttsettime();  
		if( other.getIsatt() != null)
			this.isatt= other.getIsatt();  
		if( other.getRemovesettime() != null)
			this.removesettime= other.getRemovesettime();

	}
	
	public void clearProperties(){
  
		this.atttype= null;  
		this.attsettime= null;  
		this.isatt= null;  
		this.removesettime= null;

	}
}
