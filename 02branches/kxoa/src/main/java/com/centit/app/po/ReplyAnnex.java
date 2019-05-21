package com.centit.app.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class ReplyAnnex implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private ReplyAnnexId cid;



	// Constructors
	/** default constructor */
	public ReplyAnnex() {
	}
	/** minimal constructor */
	public ReplyAnnex(ReplyAnnexId id 
				
		) {
		this.cid = id; 
			
			
	}


	public ReplyAnnexId getCid() {
		return this.cid;
	}
	
	public void setCid(ReplyAnnexId id) {
		this.cid = id;
	}
  
	public Long getReplyid() {
		if(this.cid==null)
			this.cid = new ReplyAnnexId();
		return this.cid.getReplyid();
	}
	
	public void setReplyid(Long replyid) {
		if(this.cid==null)
			this.cid = new ReplyAnnexId();
		this.cid.setReplyid(replyid);
	}
  
	public String getFilecode() {
		if(this.cid==null)
			this.cid = new ReplyAnnexId();
		return this.cid.getFilecode();
	}
	
	public void setFilecode(String filecode) {
		if(this.cid==null)
			this.cid = new ReplyAnnexId();
		this.cid.setFilecode(filecode);
	}
	
	

	// Property accessors



	public void copy(ReplyAnnex other){
  
		this.setReplyid(other.getReplyid());  
		this.setFilecode(other.getFilecode());


	}
	
	public void copyNotNullProperty(ReplyAnnex other){
  
	if( other.getReplyid() != null)
		this.setReplyid(other.getReplyid());  
	if( other.getFilecode() != null)
		this.setFilecode(other.getFilecode());


	}
	
	public void clearProperties(){


	}
}
