package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaAssetinformationBond implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OaAssetinformationBondId cid;


	private String  creater;
	private Date  createtime;

	// Constructors
	/** default constructor */
	public OaAssetinformationBond() {
	}

/** full constructor */
	public OaAssetinformationBond(OaAssetinformationBondId id
			
	,String  creater,Date  createtime) {
		this.cid = id; 
			
	
		this.creater= creater;
		this.createtime= createtime;		
	}

	public OaAssetinformationBondId getCid() {
		return this.cid;
	}
	
	public void setCid(OaAssetinformationBondId id) {
		this.cid = id;
	}
  
	public String getDjid() {
		if(this.cid==null)
			this.cid = new OaAssetinformationBondId();
		return this.cid.getDjid();
	}
	
	public void setDjid(String djid) {
		if(this.cid==null)
			this.cid = new OaAssetinformationBondId();
		this.cid.setDjid(djid);
	}
  
	public String getNo() {
		if(this.cid==null)
			this.cid = new OaAssetinformationBondId();
		return this.cid.getNo();
	}
	
	public void setNo(String no) {
		if(this.cid==null)
			this.cid = new OaAssetinformationBondId();
		this.cid.setNo(no);
	}
	
	

	// Property accessors
  
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



	public void copy(OaAssetinformationBond other){
  
		this.setDjid(other.getDjid());  
		this.setNo(other.getNo());
  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();

	}
	
	public void copyNotNullProperty(OaAssetinformationBond other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();

	}
	
	public void clearProperties(){
  
		this.creater= null;  
		this.createtime= null;

	}
}
