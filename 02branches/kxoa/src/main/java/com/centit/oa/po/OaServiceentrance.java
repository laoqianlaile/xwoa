package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaServiceentrance implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long id;

	private String  usercode;
	private String  optid;
	private String  optname;
	private String  ordernum;
	private String  opturl;
	private Date  settime;

	// Constructors
	/** default constructor */
	public OaServiceentrance() {
	}
	/** minimal constructor */
	public OaServiceentrance(
	        Long id		
		) {
	
	
		this.id = id;		
			
	}

/** full constructor */
	public OaServiceentrance(
	        Long id		
	,String  usercode,String  optid,String  optname,String  ordernum,String  opturl,Date  settime) {
	
	
		this.id = id;		
	
		this.usercode= usercode;
		this.optid= optid;
		this.optname= optname;
		this.ordernum= ordernum;
		this.opturl= opturl;
		this.settime= settime;		
	}
	

  
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	// Property accessors
  
	public String getUsercode() {
		return this.usercode;
	}
	
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
  
	public String getOptid() {
		return this.optid;
	}
	
	public void setOptid(String optid) {
		this.optid = optid;
	}
  
	public String getOptname() {
		return this.optname;
	}
	
	public void setOptname(String optname) {
		this.optname = optname;
	}
  
	public String getordernum() {
		return this.ordernum;
	}
	
	public void setordernum(String ordernum) {
		this.ordernum = ordernum;
	}
  
	public String getOpturl() {
		return this.opturl;
	}
	
	public void setOpturl(String opturl) {
		this.opturl = opturl;
	}
  
	public Date getSettime() {
		return this.settime;
	}
	
	public void setSettime(Date settime) {
		this.settime = settime;
	}



	public void copy(OaServiceentrance other){
  
		this.setId(other.getId());
  
		this.usercode= other.getUsercode();  
		this.optid= other.getOptid();  
		this.optname= other.getOptname();  
		this.ordernum= other.getordernum();  
		this.opturl= other.getOpturl();  
		this.settime= other.getSettime();

	}
	
	public void copyNotNullProperty(OaServiceentrance other){
  
	if( other.getId() != null)
		this.setId(other.getId());
  
		if( other.getUsercode() != null)
			this.usercode= other.getUsercode();  
		if( other.getOptid() != null)
			this.optid= other.getOptid();  
		if( other.getOptname() != null)
			this.optname= other.getOptname();  
		if( other.getordernum() != null)
			this.ordernum= other.getordernum();  
		if( other.getOpturl() != null)
			this.opturl= other.getOpturl();  
		if( other.getSettime() != null)
			this.settime= other.getSettime();

	}
	
	public void clearProperties(){
  
		this.usercode= null;  
		this.optid= null;  
		this.optname= null;  
		this.ordernum= null;  
		this.opturl= null;  
		this.settime= null;

	}
}
