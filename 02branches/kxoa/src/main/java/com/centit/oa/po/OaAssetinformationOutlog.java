package com.centit.oa.po;

import java.util.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaAssetinformationOutlog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;

	private String  no;
	private String  applyuser;
	private String  applyUnitcode;
	private Date  applydate;
	private Long  assetnum;
	private String  assetunit;
	private String  creater;
	private Date  createtime;
	private String  createRemark;
	private Set<OaAssetinformationBond> oaAssetinformationBonds = null;// new ArrayList<OaAssetinformationBond>();

	// Constructors
	/** default constructor */
	public OaAssetinformationOutlog() {
	}
	/** minimal constructor */
	public OaAssetinformationOutlog(
		String djid		
		,String  no,String  applyuser,String  applyUnitcode,Date  applydate,Long  assetnum,String  creater,Date  createtime,String  createRemark) {
	
	
		this.djid = djid;		
	
		this.no= no; 
		this.applyuser= applyuser; 
		this.applyUnitcode= applyUnitcode; 
		this.applydate= applydate; 
		this.assetnum= assetnum; 
		this.creater= creater; 
		this.createtime= createtime; 
		this.createRemark= createRemark; 		
	}

/** full constructor */
	public OaAssetinformationOutlog(
	 String djid		
	,String  no,String  applyuser,String  applyUnitcode,Date  applydate,Long  assetnum,String  assetunit,String  creater,Date  createtime,String  createRemark) {
	
	
		this.djid = djid;		
	
		this.no= no;
		this.applyuser= applyuser;
		this.applyUnitcode= applyUnitcode;
		this.applydate= applydate;
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
  
	public String getApplyuser() {
		return this.applyuser;
	}
	
	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}
  
	public String getApplyUnitcode() {
		return this.applyUnitcode;
	}
	
	public void setApplyUnitcode(String applyUnitcode) {
		this.applyUnitcode = applyUnitcode;
	}
  
	public Date getApplydate() {
		return this.applydate;
	}
	
	public void setApplydate(Date applydate) {
		this.applydate = applydate;
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


	public Set<OaAssetinformationBond> getOaAssetinformationBonds(){
		if(this.oaAssetinformationBonds==null)
			this.oaAssetinformationBonds = new HashSet<OaAssetinformationBond>();
		return this.oaAssetinformationBonds;
	}

	public void setOaAssetinformationBonds(Set<OaAssetinformationBond> oaAssetinformationBonds) {
		this.oaAssetinformationBonds = oaAssetinformationBonds;
	}	

	public void addOaAssetinformationBond(OaAssetinformationBond oaAssetinformationBond ){
		if (this.oaAssetinformationBonds==null)
			this.oaAssetinformationBonds = new HashSet<OaAssetinformationBond>();
		this.oaAssetinformationBonds.add(oaAssetinformationBond);
	}
	
	public void removeOaAssetinformationBond(OaAssetinformationBond oaAssetinformationBond ){
		if (this.oaAssetinformationBonds==null)
			return;
		this.oaAssetinformationBonds.remove(oaAssetinformationBond);
	}
	
	public OaAssetinformationBond newOaAssetinformationBond(){
		OaAssetinformationBond res = new OaAssetinformationBond();
  
		res.setDjid(this.getDjid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态
	 * 
	 */
	public void replaceOaAssetinformationBonds(List<OaAssetinformationBond> oaAssetinformationBonds) {
		List<OaAssetinformationBond> newObjs = new ArrayList<OaAssetinformationBond>();
		for(OaAssetinformationBond p :oaAssetinformationBonds){
			if(p==null)
				continue;
			OaAssetinformationBond newdt = newOaAssetinformationBond();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaAssetinformationBond> oldObjs = new HashSet<OaAssetinformationBond>();
		oldObjs.addAll(getOaAssetinformationBonds());
		
		for(Iterator<OaAssetinformationBond> it=oldObjs.iterator(); it.hasNext();){
			OaAssetinformationBond odt = it.next();
			found = false;
			for(OaAssetinformationBond newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaAssetinformationBond(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaAssetinformationBond newdt :newObjs){
			found = false;
			for(Iterator<OaAssetinformationBond> it=getOaAssetinformationBonds().iterator();
			 it.hasNext();){
				OaAssetinformationBond odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaAssetinformationBond(newdt);
		} 	
	}	


	public void copy(OaAssetinformationOutlog other){
  
		this.setDjid(other.getDjid());
  
		this.no= other.getNo();  
		this.applyuser= other.getApplyuser();  
		this.applyUnitcode= other.getApplyUnitcode();  
		this.applydate= other.getApplydate();  
		this.assetnum= other.getAssetnum();  
		this.assetunit= other.getAssetunit();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.createRemark= other.getCreateRemark();
	
		this.oaAssetinformationBonds = other.getOaAssetinformationBonds();
	}
	
	public void copyNotNullProperty(OaAssetinformationOutlog other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getNo() != null)
			this.no= other.getNo();  
		if( other.getApplyuser() != null)
			this.applyuser= other.getApplyuser();  
		if( other.getApplyUnitcode() != null)
			this.applyUnitcode= other.getApplyUnitcode();  
		if( other.getApplydate() != null)
			this.applydate= other.getApplydate();  
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
	
		this.oaAssetinformationBonds = other.getOaAssetinformationBonds();
	}
	
	public void clearProperties(){
  
		this.no= null;  
		this.applyuser= null;  
		this.applyUnitcode= null;  
		this.applydate= null;  
		this.assetnum= null;  
		this.assetunit= null;  
		this.creater= null;  
		this.createtime= null;  
		this.createRemark= null;
	
		this.oaAssetinformationBonds = new HashSet<OaAssetinformationBond>();
	}
}
