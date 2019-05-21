package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaLeaveReply implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  ino;
	private String  creater;
	private Date  creatertime;
	private String  messagecomment;
	private String  perno;

	// Constructors
	/** default constructor */
	public OaLeaveReply() {
	}
	/** minimal constructor */
	public OaLeaveReply(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaLeaveReply(
	 String no		
	,String  ino,String  creater,Date  creatertime,String  messagecomment,String  perno) {
	
	
		this.no = no;		
	
		this.ino= ino;
		this.creater= creater;
		this.creatertime= creatertime;
		this.messagecomment= messagecomment;
		this.perno= perno;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getIno() {
		return this.ino;
	}
	
	public void setIno(String ino) {
		this.ino = ino;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}
  
	public String getMessagecomment() {
		return this.messagecomment;
	}
	
	public void setMessagecomment(String messagecomment) {
		this.messagecomment = messagecomment;
	}
  
	public String getPerno() {
		return this.perno;
	}
	
	public void setPerno(String perno) {
		this.perno = perno;
	}



	public void copy(OaLeaveReply other){
  
		this.setNo(other.getNo());
  
		this.ino= other.getIno();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.messagecomment= other.getMessagecomment();  
		this.perno= other.getPerno();

	}
	
	public void copyNotNullProperty(OaLeaveReply other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getIno() != null)
			this.ino= other.getIno();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getMessagecomment() != null)
			this.messagecomment= other.getMessagecomment();  
		if( other.getPerno() != null)
			this.perno= other.getPerno();

	}
	
	public void clearProperties(){
  
		this.ino= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.messagecomment= null;  
		this.perno= null;

	}
}
