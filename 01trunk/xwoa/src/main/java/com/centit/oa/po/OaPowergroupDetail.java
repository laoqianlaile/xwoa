package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaPowergroupDetail implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

    //权限分组明细
	private String id;//id

	private String  no;//序号
	private String  usercode;//人员编码
	private Date  creatertime;//创建日期
	private String  depid;//所属部门

	// Constructors
	/** default constructor */
	public OaPowergroupDetail() {
	}
	/** minimal constructor */
	public OaPowergroupDetail(
		String id		
		) {
	
	
		this.id = id;		
			
	}

/** full constructor */
	public OaPowergroupDetail(
	 String id		
	,String  no,String  usercode,Date  creatertime,String  depid) {
	
	
		this.id = id;		
	
		this.no= no;
		this.usercode= usercode;
		this.creatertime= creatertime;
		this.depid= depid;		
	}
	

  
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	// Property accessors
  
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
  
	public String getUsercode() {
		return this.usercode;
	}
	
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}
  
	public String getDepid() {
		return this.depid;
	}
	
	public void setDepid(String depid) {
		this.depid = depid;
	}



	public void copy(OaPowergroupDetail other){
  
		this.setId(other.getId());
  
		this.no= other.getNo();  
		this.usercode= other.getUsercode();  
		this.creatertime= other.getCreatertime();  
		this.depid= other.getDepid();

	}
	
	public void copyNotNullProperty(OaPowergroupDetail other){
  
	if( other.getId() != null)
		this.setId(other.getId());
  
		if( other.getNo() != null)
			this.no= other.getNo();  
		if( other.getUsercode() != null)
			this.usercode= other.getUsercode();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getDepid() != null)
			this.depid= other.getDepid();

	}
	
	public void clearProperties(){
  
		this.no= null;  
		this.usercode= null;  
		this.creatertime= null;  
		this.depid= null;

	}
}
