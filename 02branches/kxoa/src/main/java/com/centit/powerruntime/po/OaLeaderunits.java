package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaLeaderunits implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OaLeaderunitsId cid;



	// Constructors
	/** default constructor */
	public OaLeaderunits() {
	}


/** full constructor */
	public OaLeaderunits(OaLeaderunitsId id
			
	) {
		this.cid = id; 
			
			
	}

	public OaLeaderunitsId getCid() {
		return this.cid;
	}
	
	public void setCid(OaLeaderunitsId id) {
		this.cid = id;
	}
  
	public String getLeadercode() {
		if(this.cid==null)
			this.cid = new OaLeaderunitsId();
		return this.cid.getLeadercode();
	}
	
	public void setLeadercode(String leadercode) {
		if(this.cid==null)
			this.cid = new OaLeaderunitsId();
		this.cid.setLeadercode(leadercode);
	}
  
	public String getUnitcode() {
		if(this.cid==null)
			this.cid = new OaLeaderunitsId();
		return this.cid.getUnitcode();
	}
	
	public void setUnitcode(String unitcode) {
		if(this.cid==null)
			this.cid = new OaLeaderunitsId();
		this.cid.setUnitcode(unitcode);
	}
	
	

	// Property accessors



	public void copy(OaLeaderunits other){
  
		this.setLeadercode(other.getLeadercode());  
		this.setUnitcode(other.getUnitcode());


	}
	
	public void copyNotNullProperty(OaLeaderunits other){
  
	if( other.getLeadercode() != null)
		this.setLeadercode(other.getLeadercode());  
	if( other.getUnitcode() != null)
		this.setUnitcode(other.getUnitcode());


	}
	
	public void clearProperties(){


	}
}
