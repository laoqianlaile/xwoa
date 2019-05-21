package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaUnitIncomedoc implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String id;

	private String  no;
	private String  unitcode;
	private Date  createtime;
	private String  createuser;
	private String  isopen;
	private Date  lastmodifytime;
	private String  updateuser;

	// Constructors
	/** default constructor */
	public OaUnitIncomedoc() {
	}
	/** minimal constructor */
	public OaUnitIncomedoc(
		String id		
		) {
	
	
		this.id = id;		
			
	}

/** full constructor */
	public OaUnitIncomedoc(
	 String id		
	,String  no,String  unitcode,Date  createtime,String  createuser,String  isopen,Date  lastmodifytime,String  updateuser) {
	
	
		this.id = id;		
	
		this.no= no;
		this.unitcode= unitcode;
		this.createtime= createtime;
		this.createuser= createuser;
		this.isopen= isopen;
		this.lastmodifytime= lastmodifytime;
		this.updateuser= updateuser;		
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
  
	public String getUnitcode() {
		return this.unitcode;
	}
	
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
  
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
  
	public String getIsopen() {
		return this.isopen;
	}
	
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
  
	public Date getLastmodifytime() {
		return this.lastmodifytime;
	}
	
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
  
	public String getUpdateuser() {
		return this.updateuser;
	}
	
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}



	public void copy(OaUnitIncomedoc other){
  
		this.setId(other.getId());
  
		this.no= other.getNo();  
		this.unitcode= other.getUnitcode();  
		this.createtime= other.getCreatetime();  
		this.createuser= other.getCreateuser();  
		this.isopen= other.getIsopen();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.updateuser= other.getUpdateuser();

	}
	
	public void copyNotNullProperty(OaUnitIncomedoc other){
  
	if( other.getId() != null)
		this.setId(other.getId());
  
		if( other.getNo() != null)
			this.no= other.getNo();  
		if( other.getUnitcode() != null)
			this.unitcode= other.getUnitcode();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getCreateuser() != null)
			this.createuser= other.getCreateuser();  
		if( other.getIsopen() != null)
			this.isopen= other.getIsopen();  
		if( other.getLastmodifytime() != null)
			this.lastmodifytime= other.getLastmodifytime();  
		if( other.getUpdateuser() != null)
			this.updateuser= other.getUpdateuser();

	}
	
	public void clearProperties(){
  
		this.no= null;  
		this.unitcode= null;  
		this.createtime= null;  
		this.createuser= null;  
		this.isopen= null;  
		this.lastmodifytime= null;  
		this.updateuser= null;

	}
}
