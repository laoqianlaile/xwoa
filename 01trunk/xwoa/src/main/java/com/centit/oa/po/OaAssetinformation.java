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

public class OaAssetinformation implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  datacode;
	private Long  assetnum;
	private String  assetunit;
	private String  creater;
	private Date  createtime;
	private String  createRemark;
	private String  createDepno;
	private String  senddeptype;
	private String  state;
	private Long  assetleftalert;
	private String hasOutIn;//是否有出库入库记录
	private Set<OaAssetinformationOutlog> oaAssetinformationOutlogs = null;// new ArrayList<OaAssetinformationOutlog>();
	private Set<OaAssetinformationInlog> oaAssetinformationInlogs = null;// new ArrayList<OaAssetinformationInlog>();

	// Constructors
	/** default constructor */
	public OaAssetinformation() {
	}
	/** minimal constructor */
	public OaAssetinformation(
		String no		
		,String  datacode,Long  assetnum,String  creater,Date  createtime,String  createDepno,String  senddeptype) {
	
	
		this.no = no;		
	
		this.datacode= datacode; 
		this.assetnum= assetnum; 
		this.creater= creater; 
		this.createtime= createtime; 
		this.createDepno= createDepno; 
		this.senddeptype= senddeptype; 		
	}

/** full constructor */
	public OaAssetinformation(
	 String no		
	,String  datacode,Long  assetnum,String  assetunit,String  creater,Date  createtime,String  createRemark,String  createDepno,String  senddeptype,String  state,Long  assetleftalert) {
	
	
		this.no = no;		
	
		this.datacode= datacode;
		this.assetnum= assetnum;
		this.assetunit= assetunit;
		this.creater= creater;
		this.createtime= createtime;
		this.createRemark= createRemark;
		this.createDepno= createDepno;
		this.senddeptype= senddeptype;
		this.state= state;
		this.assetleftalert= assetleftalert;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getDatacode() {
		return this.datacode;
	}
	
	public void setDatacode(String datacode) {
		this.datacode = datacode;
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
  
	public String getCreateDepno() {
		return this.createDepno;
	}
	
	public void setCreateDepno(String createDepno) {
		this.createDepno = createDepno;
	}
  
	public String getSenddeptype() {
		return this.senddeptype;
	}
	
	public void setSenddeptype(String senddeptype) {
		this.senddeptype = senddeptype;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
  
	public Long getAssetleftalert() {
		return this.assetleftalert;
	}
	
	public void setAssetleftalert(Long assetleftalert) {
		this.assetleftalert = assetleftalert;
	}


	public Set<OaAssetinformationOutlog> getOaAssetinformationOutlogs(){
		if(this.oaAssetinformationOutlogs==null)
			this.oaAssetinformationOutlogs = new HashSet<OaAssetinformationOutlog>();
		return this.oaAssetinformationOutlogs;
	}

	public void setOaAssetinformationOutlogs(Set<OaAssetinformationOutlog> oaAssetinformationOutlogs) {
		this.oaAssetinformationOutlogs = oaAssetinformationOutlogs;
	}	

	public void addOaAssetinformationOutlog(OaAssetinformationOutlog oaAssetinformationOutlog ){
		if (this.oaAssetinformationOutlogs==null)
			this.oaAssetinformationOutlogs = new HashSet<OaAssetinformationOutlog>();
		this.oaAssetinformationOutlogs.add(oaAssetinformationOutlog);
	}
	
	public void removeOaAssetinformationOutlog(OaAssetinformationOutlog oaAssetinformationOutlog ){
		if (this.oaAssetinformationOutlogs==null)
			return;
		this.oaAssetinformationOutlogs.remove(oaAssetinformationOutlog);
	}
	
	public OaAssetinformationOutlog newOaAssetinformationOutlog(){
		OaAssetinformationOutlog res = new OaAssetinformationOutlog();
  
		res.setNo(this.getNo());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceOaAssetinformationOutlogs(List<OaAssetinformationOutlog> oaAssetinformationOutlogs) {
		List<OaAssetinformationOutlog> newObjs = new ArrayList<OaAssetinformationOutlog>();
		for(OaAssetinformationOutlog p :oaAssetinformationOutlogs){
			if(p==null)
				continue;
			OaAssetinformationOutlog newdt = newOaAssetinformationOutlog();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaAssetinformationOutlog> oldObjs = new HashSet<OaAssetinformationOutlog>();
		oldObjs.addAll(getOaAssetinformationOutlogs());
		
		for(Iterator<OaAssetinformationOutlog> it=oldObjs.iterator(); it.hasNext();){
			OaAssetinformationOutlog odt = it.next();
			found = false;
			for(OaAssetinformationOutlog newdt :newObjs){
				if(odt.getDjid().equals( newdt.getDjid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaAssetinformationOutlog(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaAssetinformationOutlog newdt :newObjs){
			found = false;
			for(Iterator<OaAssetinformationOutlog> it=getOaAssetinformationOutlogs().iterator();
			 it.hasNext();){
				OaAssetinformationOutlog odt = it.next();
				if(odt.getDjid().equals( newdt.getDjid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaAssetinformationOutlog(newdt);
		} 	
	}	

	public Set<OaAssetinformationInlog> getOaAssetinformationInlogs(){
		if(this.oaAssetinformationInlogs==null)
			this.oaAssetinformationInlogs = new HashSet<OaAssetinformationInlog>();
		return this.oaAssetinformationInlogs;
	}

	public void setOaAssetinformationInlogs(Set<OaAssetinformationInlog> oaAssetinformationInlogs) {
		this.oaAssetinformationInlogs = oaAssetinformationInlogs;
	}	

	public void addOaAssetinformationInlog(OaAssetinformationInlog oaAssetinformationInlog ){
		if (this.oaAssetinformationInlogs==null)
			this.oaAssetinformationInlogs = new HashSet<OaAssetinformationInlog>();
		this.oaAssetinformationInlogs.add(oaAssetinformationInlog);
	}
	
	public void removeOaAssetinformationInlog(OaAssetinformationInlog oaAssetinformationInlog ){
		if (this.oaAssetinformationInlogs==null)
			return;
		this.oaAssetinformationInlogs.remove(oaAssetinformationInlog);
	}
	
	public OaAssetinformationInlog newOaAssetinformationInlog(){
		OaAssetinformationInlog res = new OaAssetinformationInlog();
  
		res.setNo(this.getNo());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceOaAssetinformationInlogs(List<OaAssetinformationInlog> oaAssetinformationInlogs) {
		List<OaAssetinformationInlog> newObjs = new ArrayList<OaAssetinformationInlog>();
		for(OaAssetinformationInlog p :oaAssetinformationInlogs){
			if(p==null)
				continue;
			OaAssetinformationInlog newdt = newOaAssetinformationInlog();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaAssetinformationInlog> oldObjs = new HashSet<OaAssetinformationInlog>();
		oldObjs.addAll(getOaAssetinformationInlogs());
		
		for(Iterator<OaAssetinformationInlog> it=oldObjs.iterator(); it.hasNext();){
			OaAssetinformationInlog odt = it.next();
			found = false;
			for(OaAssetinformationInlog newdt :newObjs){
				if(odt.getDjid().equals( newdt.getDjid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaAssetinformationInlog(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaAssetinformationInlog newdt :newObjs){
			found = false;
			for(Iterator<OaAssetinformationInlog> it=getOaAssetinformationInlogs().iterator();
			 it.hasNext();){
				OaAssetinformationInlog odt = it.next();
				if(odt.getDjid().equals( newdt.getDjid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaAssetinformationInlog(newdt);
		} 	
	}	


	public void copy(OaAssetinformation other){
  
		this.setNo(other.getNo());
  
		this.datacode= other.getDatacode();  
		this.assetnum= other.getAssetnum();  
		this.assetunit= other.getAssetunit();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.createRemark= other.getCreateRemark();  
		this.createDepno= other.getCreateDepno();  
		this.senddeptype= other.getSenddeptype();  
		this.state= other.getState();  
		this.assetleftalert= other.getAssetleftalert();
	
		this.oaAssetinformationOutlogs = other.getOaAssetinformationOutlogs();	
		this.oaAssetinformationInlogs = other.getOaAssetinformationInlogs();
	}
	
	public void copyNotNullProperty(OaAssetinformation other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getDatacode() != null)
			this.datacode= other.getDatacode();  
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
		if( other.getCreateDepno() != null)
			this.createDepno= other.getCreateDepno();  
		if( other.getSenddeptype() != null)
			this.senddeptype= other.getSenddeptype();  
		if( other.getState() != null)
			this.state= other.getState();  
		if( other.getAssetleftalert() != null)
			this.assetleftalert= other.getAssetleftalert();
	
		this.oaAssetinformationOutlogs = other.getOaAssetinformationOutlogs();	
		this.oaAssetinformationInlogs = other.getOaAssetinformationInlogs();
	}
	
	public void clearProperties(){
  
		this.datacode= null;  
		this.assetnum= null;  
		this.assetunit= null;  
		this.creater= null;  
		this.createtime= null;  
		this.createRemark= null;  
		this.createDepno= null;  
		this.senddeptype= null;  
		this.state= null;  
		this.assetleftalert= null;
	
		this.oaAssetinformationOutlogs = new HashSet<OaAssetinformationOutlog>();	
		this.oaAssetinformationInlogs = new HashSet<OaAssetinformationInlog>();
	}
    public String getHasOutIn() {
        return hasOutIn;
    }
    public void setHasOutIn(String hasOutIn) {
        this.hasOutIn = hasOutIn;
    }
	
}
