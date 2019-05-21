package com.centit.bbs.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaLeaveMessagereply implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String lyno;

	private String  lno;
	private String  creater;
	private Date  creatertime;
	private String  messagecomment;
	/**
	 * N-正常 D- 删除
	 */
    private String state;
    /**
     * ip地址
     */
    private String ip;
	// Constructors
	/** default constructor */
	public OaLeaveMessagereply() {
	}
	/** minimal constructor */
	public OaLeaveMessagereply(
		String lyno		
		) {
	
	
		this.lyno = lyno;		
			
	}

/** full constructor */
	public OaLeaveMessagereply(
	 String lyno		
	,String  lno,String  creater,Date  creatertime,String  messagecomment) {
	
	
		this.lyno = lyno;		
	
		this.lno= lno;
		this.creater= creater;
		this.creatertime= creatertime;
		this.messagecomment= messagecomment;		
	}
	

  
	public String getLyno() {
		return this.lyno;
	}

	
	public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setLyno(String lyno) {
		this.lyno = lyno;
	}
	// Property accessors
  
	public String getLno() {
		return this.lno;
	}
	
	public void setLno(String lno) {
		this.lno = lno;
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



	public void copy(OaLeaveMessagereply other){
  
		this.setLyno(other.getLyno());
  
		this.lno= other.getLno();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.messagecomment= other.getMessagecomment();
        this.state = other.getState();
        this.ip = other.getIp();
	}
	
	public void copyNotNullProperty(OaLeaveMessagereply other){
  
	if( other.getLyno() != null)
		this.setLyno(other.getLyno());
  
		if( other.getLno() != null)
			this.lno= other.getLno();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getMessagecomment() != null)
			this.messagecomment= other.getMessagecomment();
        if(other.getState() != null)
            this.state = other.getState();
        if(other.getIp() != null)
            this.ip = other.getIp();
	}
	
	public void clearProperties(){
  
		this.lno= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.messagecomment= null;
        this.ip = null;
        this.state = null;
	}
}
