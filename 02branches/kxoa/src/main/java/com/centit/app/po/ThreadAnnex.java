package com.centit.app.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class ThreadAnnex implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private ThreadAnnexId cid;





	// Constructors
	/** default constructor */
	public ThreadAnnex() {
	}
	/** minimal constructor */
	public ThreadAnnex(ThreadAnnexId id 
				
		) {
		this.cid = id; 
			
			
	}


	public ThreadAnnexId getCid() {
		return this.cid;
	}
	
	public void setCid(ThreadAnnexId id) {
		this.cid = id;
	}
  
	public Long getThreadid() {
		if(this.cid==null)
			this.cid = new ThreadAnnexId();
		return this.cid.getThreadid();
	}
	
	public void setThreadid(Long threadid) {
		if(this.cid==null)
			this.cid = new ThreadAnnexId();
		this.cid.setThreadid(threadid);
	}
  
	public String getFilecode() {
		if(this.cid==null)
			this.cid = new ThreadAnnexId();
		return this.cid.getFilecode();
	}
	
	public void setFilecode(String filecode) {
		if(this.cid==null)
			this.cid = new ThreadAnnexId();
		this.cid.setFilecode(filecode);
	}
	
	

	// Property accessors



	public void copy(ThreadAnnex other){
  
		this.setThreadid(other.getThreadid());  
		this.setFilecode(other.getFilecode());


	}
	
	public void copyNotNullProperty(ThreadAnnex other){
  
	if( other.getThreadid() != null)
		this.setThreadid(other.getThreadid());  
	if( other.getFilecode() != null)
		this.setFilecode(other.getFilecode());


	}
	
	public void clearProperties(){


	}
}
