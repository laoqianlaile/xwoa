package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaAssetinformationInlog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;

	private String  no;
	private Long  assetnum;
	private String  assetunit;
	private String  creater;
	private Date  createtime;
	private String  createRemark;

	// Constructors
	/** default constructor */
	public OaAssetinformationInlog() {
	}
	/** minimal constructor */
	public OaAssetinformationInlog(
		String djid		
		,String  no,Long  assetnum,String  creater,Date  createtime,String  createRemark) {
	
	
		this.djid = djid;		
	
		this.no= no; 
		this.assetnum= assetnum; 
		this.creater= creater; 
		this.createtime= createtime; 
		this.createRemark= createRemark; 		
	}

/** full constructor */
	public OaAssetinformationInlog(
	 String djid		
	,String  no,Long  assetnum,String  assetunit,String  creater,Date  createtime,String  createRemark) {
	
	
		this.djid = djid;		
	
		this.no= no;
		this.assetnum= assetnum;
		this.assetunit= assetunit;
		this.creater= creater;
		this.createtime= createtime;
		this.createRemark= createRemark;		
	}
	

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
	// Property accessors
  
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
  
	public Long getAssetnum() {
		return this.assetnum;
	}
	
	public void setAssetnum(Long assetnum) {
		this.assetnum = assetnum;
	}
  
	public String getAssetunit() {
		return this.assetunit;
	}
	
	public void setAssetunit(String assetunit) {
		this.assetunit = assetunit;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public String getCreateRemark() {
		return this.createRemark;
	}
	
	public void setCreateRemark(String createRemark) {
		this.createRemark = createRemark;
	}



	public void copy(OaAssetinformationInlog other){
  
		this.setDjid(other.getDjid());
  
		this.no= other.getNo();  
		this.assetnum= other.getAssetnum();  
		this.assetunit= other.getAssetunit();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.createRemark= other.getCreateRemark();

	}
	
	public void copyNotNullProperty(OaAssetinformationInlog other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getNo() != null)
			this.no= other.getNo();  
		if( other.getAssetnum() != null)
			this.assetnum= other.getAssetnum();  
		if( other.getAssetunit() != null)
			this.assetunit= other.getAssetunit();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getCreateRemark() != null)
			this.createRemark= other.getCreateRemark();

	}
	
	public void clearProperties(){
  
		this.no= null;  
		this.assetnum= null;  
		this.assetunit= null;  
		this.creater= null;  
		this.createtime= null;  
		this.createRemark= null;

	}
}
