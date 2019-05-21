package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaFeedback implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;

	private String  title;
	private String  remark;
	private byte[]  feedFile;
	private String  feedFileName;
	private String  depno;
	private String  reception;
	private String  creater;
	private Date  creatertime;
	private String  isanonymous;
	private String  replyInformation;
	private Date  replyTime;
	private String  isopen;
	private String  isAnswer;

	// Constructors
	/** default constructor */
	public OaFeedback() {
	}
	/** minimal constructor */
	public OaFeedback(
		String djid		
		) {
	
	
		this.djid = djid;		
			
	}

/** full constructor */
	public OaFeedback(
	 String djid		
	,String  title,String  remark,byte[]  feedFile,String  feedFileName,String  depno,String  reception,String  creater,Date  creatertime,String  isanonymous,String  replyInformation,Date  replyTime,String  isopen,String  isAnswer) {
	
	
		this.djid = djid;		
	
		this.title= title;
		this.remark= remark;
		this.feedFile= feedFile;
		this.feedFileName= feedFileName;
		this.depno= depno;
		this.reception= reception;
		this.creater= creater;
		this.creatertime= creatertime;
		this.isanonymous= isanonymous;
		this.replyInformation= replyInformation;
		this.replyTime= replyTime;
		this.isopen= isopen;
		this.isAnswer= isAnswer;
	}
	

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
	// Property accessors
  
	public String getTitle() {
		return this.title;
	}
	
	public String getIsAnswer() {
        return isAnswer;
    }
    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer;
    }
    public void setTitle(String title) {
		this.title = title;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public byte[] getFeedFile() {
		return this.feedFile;
	}
	
	public void setFeedFile(byte[] feedFile) {
		this.feedFile = feedFile;
	}
  
	public String getFeedFileName() {
		return this.feedFileName;
	}
	
	public void setFeedFileName(String feedFileName) {
		this.feedFileName = feedFileName;
	}
  
	public String getDepno() {
		return this.depno;
	}
	
	public void setDepno(String depno) {
		this.depno = depno;
	}
  
	public String getReception() {
		return this.reception;
	}
	
	public void setReception(String reception) {
		this.reception = reception;
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
  
	public String getIsanonymous() {
		return this.isanonymous;
	}
	
	public void setIsanonymous(String isanonymous) {
		this.isanonymous = isanonymous;
	}
  
	public String getReplyInformation() {
		return this.replyInformation;
	}
	
	public void setReplyInformation(String replyInformation) {
		this.replyInformation = replyInformation;
	}
  
	public Date getReplyTime() {
		return this.replyTime;
	}
	
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
  
	public String getIsopen() {
		return this.isopen;
	}
	
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}



	public void copy(OaFeedback other){
  
		this.setDjid(other.getDjid());
  
		this.title= other.getTitle();  
		this.remark= other.getRemark();  
		this.feedFile= other.getFeedFile();  
		this.feedFileName= other.getFeedFileName();  
		this.depno= other.getDepno();  
		this.reception= other.getReception();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.isanonymous= other.getIsanonymous();  
		this.replyInformation= other.getReplyInformation();  
		this.replyTime= other.getReplyTime();  
		this.isopen= other.getIsopen();
		this.isAnswer= other.isAnswer;

	}
	
	public void copyNotNullProperty(OaFeedback other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getFeedFile() != null)
			this.feedFile= other.getFeedFile();  
		if( other.getFeedFileName() != null)
			this.feedFileName= other.getFeedFileName();  
		if( other.getDepno() != null)
			this.depno= other.getDepno();  
		if( other.getReception() != null)
			this.reception= other.getReception();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getIsanonymous() != null)
			this.isanonymous= other.getIsanonymous();  
		if( other.getReplyInformation() != null)
			this.replyInformation= other.getReplyInformation();  
		if( other.getReplyTime() != null)
			this.replyTime= other.getReplyTime();  
		if( other.getIsopen() != null)
			this.isopen= other.getIsopen();
		if( other.getIsAnswer() != null)
            this.isAnswer= other.getIsAnswer();

	}
	
	public void clearProperties(){
  
		this.title= null;  
		this.remark= null;  
		this.feedFile= null;  
		this.feedFileName= null;  
		this.depno= null;  
		this.reception= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.isanonymous= null;  
		this.replyInformation= null;  
		this.replyTime= null;  
		this.isopen= null;
		this.isAnswer=null;

	}
}
