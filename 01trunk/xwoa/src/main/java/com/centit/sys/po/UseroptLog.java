package com.centit.sys.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class UseroptLog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String id;

	private Date  createtime;
	private String  createuser;
	private String  bizname;
	private String  biztype;
	private String  runerrortype;
	private String  createrip;
	private String  remark;

	// Constructors
	/** default constructor */
	public UseroptLog() {
	}
	/** minimal constructor */
	public UseroptLog(
		String id		
		) {
	
	
		this.id = id;		
			
	}

/** full constructor */
	public UseroptLog(
	 String id		
	,Date  createtime,String  createuser,String  bizname,String  biztype,String  runerrortype,String  createrip,String  remark) {
	
	
		this.id = id;		
	
		this.createtime= createtime;
		this.createuser= createuser;
		this.bizname= bizname;
		this.biztype= biztype;
		this.runerrortype= runerrortype;
		this.createrip= createrip;
		this.remark= remark;		
	}
	

  
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	// Property accessors
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public String getCreateuser() {
		return this.createuser;
	}
	
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
  
	public String getBizname() {
		return this.bizname;
	}
	
	public void setBizname(String bizname) {
		this.bizname = bizname;
	}
  
	public String getBiztype() {
		return this.biztype;
	}
	
	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}
  
	public String getRunerrortype() {
		return this.runerrortype;
	}
	
	public void setRunerrortype(String runerrortype) {
		this.runerrortype = runerrortype;
	}
  
	public String getCreaterip() {
		return this.createrip;
	}
	
	public void setCreaterip(String createrip) {
		this.createrip = createrip;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}



	public void copy(UseroptLog other){
  
		this.setId(other.getId());
  
		this.createtime= other.getCreatetime();  
		this.createuser= other.getCreateuser();  
		this.bizname= other.getBizname();  
		this.biztype= other.getBiztype();  
		this.runerrortype= other.getRunerrortype();  
		this.createrip= other.getCreaterip();  
		this.remark= other.getRemark();

	}
	
	public void copyNotNullProperty(UseroptLog other){
  
	if( other.getId() != null)
		this.setId(other.getId());
  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getCreateuser() != null)
			this.createuser= other.getCreateuser();  
		if( other.getBizname() != null)
			this.bizname= other.getBizname();  
		if( other.getBiztype() != null)
			this.biztype= other.getBiztype();  
		if( other.getRunerrortype() != null)
			this.runerrortype= other.getRunerrortype();  
		if( other.getCreaterip() != null)
			this.createrip= other.getCreaterip();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();

	}
	
	public void clearProperties(){
  
		this.createtime= null;  
		this.createuser= null;  
		this.bizname= null;  
		this.biztype= null;  
		this.runerrortype= null;  
		this.createrip= null;  
		this.remark= null;

	}
}
