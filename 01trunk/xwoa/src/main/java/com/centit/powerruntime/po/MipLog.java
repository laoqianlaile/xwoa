package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class MipLog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String mipid;

	private String  infmethods;
	private String  accparameters;
	private String  returnddata;
	private String  exceptioninfo;
	private String  creater;
	private Date  createtime;
	private String  remarkMethods;

	// Constructors
	/** default constructor */
	public MipLog() {
	}
	/** minimal constructor */
	public MipLog(
		String mipid		
		) {
	
	
		this.mipid = mipid;		
			
	}

/** full constructor */
	public MipLog(
	 String mipid		
	,String  infmethods,String  accparameters,String  returnddata,String  exceptioninfo,String  creater,Date  createtime,String  remarkMethods) {
	
	
		this.mipid = mipid;		
	
		this.infmethods= infmethods;
		this.accparameters= accparameters;
		this.returnddata= returnddata;
		this.exceptioninfo= exceptioninfo;
		this.creater= creater;
		this.createtime= createtime;
		this.remarkMethods= remarkMethods;		
	}
	

  
	public String getMipid() {
		return this.mipid;
	}

	public void setMipid(String mipid) {
		this.mipid = mipid;
	}
	// Property accessors
  
	public String getInfmethods() {
		return this.infmethods;
	}
	
	public void setInfmethods(String infmethods) {
		this.infmethods = infmethods;
	}
  
	public String getAccparameters() {
		return this.accparameters;
	}
	
	public void setAccparameters(String accparameters) {
		this.accparameters = accparameters;
	}
  
	public String getReturnddata() {
		return this.returnddata;
	}
	
	public void setReturnddata(String returnddata) {
		this.returnddata = returnddata;
	}
  
	public String getExceptioninfo() {
		return this.exceptioninfo;
	}
	
	public void setExceptioninfo(String exceptioninfo) {
		this.exceptioninfo = exceptioninfo;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public String getRemarkMethods() {
		return this.remarkMethods;
	}
	
	public void setRemarkMethods(String remarkMethods) {
		this.remarkMethods = remarkMethods;
	}



	public void copy(MipLog other){
  
		this.setMipid(other.getMipid());
  
		this.infmethods= other.getInfmethods();  
		this.accparameters= other.getAccparameters();  
		this.returnddata= other.getReturnddata();  
		this.exceptioninfo= other.getExceptioninfo();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.remarkMethods= other.getRemarkMethods();

	}
	
	public void copyNotNullProperty(MipLog other){
  
	if( other.getMipid() != null)
		this.setMipid(other.getMipid());
  
		if( other.getInfmethods() != null)
			this.infmethods= other.getInfmethods();  
		if( other.getAccparameters() != null)
			this.accparameters= other.getAccparameters();  
		if( other.getReturnddata() != null)
			this.returnddata= other.getReturnddata();  
		if( other.getExceptioninfo() != null)
			this.exceptioninfo= other.getExceptioninfo();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getRemarkMethods() != null)
			this.remarkMethods= other.getRemarkMethods();

	}
	
	public void clearProperties(){
  
		this.infmethods= null;  
		this.accparameters= null;  
		this.returnddata= null;  
		this.exceptioninfo= null;  
		this.creater= null;  
		this.createtime= null;  
		this.remarkMethods= null;

	}
}
