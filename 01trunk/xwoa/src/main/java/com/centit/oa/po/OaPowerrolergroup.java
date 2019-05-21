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

public class OaPowerrolergroup implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;//序号

	private String  groupType;//分组类型1:个人2:公共
	private String  groupName;//名称
	private String  remark;//描述
	private String  state;//启用状态
	private String  creater;//创建人
	private Date  creatertime;//创建日期
	private Set<OaPowergroupDetail> oaPowergroupDetails = null;// new ArrayList<OaPowergroupDetail>();

	// Constructors
	/** default constructor */
	public OaPowerrolergroup() {
	}
	/** minimal constructor */
	public OaPowerrolergroup(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaPowerrolergroup(
	 String no		
	,String  groupType,String  groupName,String  remark,String  state,String  creater,Date  creatertime) {
	
	
		this.no = no;		
	
		this.groupType= groupType;
		this.groupName= groupName;
		this.remark= remark;
		this.state= state;
		this.creater= creater;
		this.creatertime= creatertime;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getGroupType() {
		return this.groupType;
	}
	
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
  
	public String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
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


	public Set<OaPowergroupDetail> getOaPowergroupDetails(){
		if(this.oaPowergroupDetails==null)
			this.oaPowergroupDetails = new HashSet<OaPowergroupDetail>();
		return this.oaPowergroupDetails;
	}

	public void setOaPowergroupDetails(Set<OaPowergroupDetail> oaPowergroupDetails) {
		this.oaPowergroupDetails = oaPowergroupDetails;
	}	

	public void addOaPowergroupDetail(OaPowergroupDetail oaPowergroupDetail ){
		if (this.oaPowergroupDetails==null)
			this.oaPowergroupDetails = new HashSet<OaPowergroupDetail>();
		this.oaPowergroupDetails.add(oaPowergroupDetail);
	}
	
	public void removeOaPowergroupDetail(OaPowergroupDetail oaPowergroupDetail ){
		if (this.oaPowergroupDetails==null)
			return;
		this.oaPowergroupDetails.remove(oaPowergroupDetail);
	}
	
	public OaPowergroupDetail newOaPowergroupDetail(){
		OaPowergroupDetail res = new OaPowergroupDetail();
  
		res.setNo(this.getNo());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceOaPowergroupDetails(List<OaPowergroupDetail> oaPowergroupDetails) {
		List<OaPowergroupDetail> newObjs = new ArrayList<OaPowergroupDetail>();
		for(OaPowergroupDetail p :oaPowergroupDetails){
			if(p==null)
				continue;
			OaPowergroupDetail newdt = newOaPowergroupDetail();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaPowergroupDetail> oldObjs = new HashSet<OaPowergroupDetail>();
		oldObjs.addAll(getOaPowergroupDetails());
		
		for(Iterator<OaPowergroupDetail> it=oldObjs.iterator(); it.hasNext();){
			OaPowergroupDetail odt = it.next();
			found = false;
			for(OaPowergroupDetail newdt :newObjs){
				if(odt.getId().equals( newdt.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaPowergroupDetail(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaPowergroupDetail newdt :newObjs){
			found = false;
			for(Iterator<OaPowergroupDetail> it=getOaPowergroupDetails().iterator();
			 it.hasNext();){
				OaPowergroupDetail odt = it.next();
				if(odt.getId().equals( newdt.getId())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaPowergroupDetail(newdt);
		} 	
	}	


	public void copy(OaPowerrolergroup other){
  
		this.setNo(other.getNo());
  
		this.groupType= other.getGroupType();  
		this.groupName= other.getGroupName();  
		this.remark= other.getRemark();  
		this.state= other.getState();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();
	
		this.oaPowergroupDetails = other.getOaPowergroupDetails();
	}
	
	public void copyNotNullProperty(OaPowerrolergroup other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getGroupType() != null)
			this.groupType= other.getGroupType();  
		if( other.getGroupName() != null)
			this.groupName= other.getGroupName();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getState() != null)
			this.state= other.getState();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();
	
		this.oaPowergroupDetails = other.getOaPowergroupDetails();
	}
	
	public void clearProperties(){
  
		this.groupType= null;  
		this.groupName= null;  
		this.remark= null;  
		this.state= null;  
		this.creater= null;  
		this.creatertime= null;
	
		this.oaPowergroupDetails = new HashSet<OaPowergroupDetail>();
	}
}
