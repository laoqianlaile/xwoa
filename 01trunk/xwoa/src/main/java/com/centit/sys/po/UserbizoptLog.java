package com.centit.sys.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class UserbizoptLog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private UserbizoptLogId cid;


	private String  remark;
	private Long  nodeinstid;

	// Constructors
	/** default constructor */
	public UserbizoptLog() {
	}
	/** minimal constructor */
	public UserbizoptLog(UserbizoptLogId id 
				
		) {
		this.cid = id; 
			
			
	}

/** full constructor */
	public UserbizoptLog(UserbizoptLogId id
			
	,String  remark,Long  nodeinstid) {
		this.cid = id; 
			
	
		this.remark= remark;
		this.nodeinstid= nodeinstid;		
	}
	public UserbizoptLog(UserbizoptLogId id
            
	        ,Long  nodeinstid) {
	            this.cid = id; 
	            this.nodeinstid= nodeinstid;        
	        }

	public UserbizoptLogId getCid() {
		return this.cid;
	}
	
	public void setCid(UserbizoptLogId id) {
		this.cid = id;
	}
  
	public String getId() {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		return this.cid.getId();
	}
	
	public void setId(String id) {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		this.cid.setId(id);
	}
  
	public String getBizname() {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		return this.cid.getBizname();
	}
	
	public void setBizname(String bizname) {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		this.cid.setBizname(bizname);
	}
  
	public String getBiztype() {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		return this.cid.getBiztype();
	}
	
	public void setBiztype(String biztype) {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		this.cid.setBiztype(biztype);
	}
  
	public String getDjId() {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		return this.cid.getDjId();
	}
	
	public void setDjId(String djId) {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		this.cid.setDjId(djId);
	}
  
	public Date getCreatetime() {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		return this.cid.getCreatetime();
	}
	
	public void setCreatetime(Date createtime) {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		this.cid.setCreatetime(createtime);
	}
  
	public String getCreateuser() {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		return this.cid.getCreateuser();
	}
	
	public void setCreateuser(String createuser) {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		this.cid.setCreateuser(createuser);
	}
  
	public String getCreaterip() {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		return this.cid.getCreaterip();
	}
	
	public void setCreaterip(String createrip) {
		if(this.cid==null)
			this.cid = new UserbizoptLogId();
		this.cid.setCreaterip(createrip);
	}
	
	

	// Property accessors
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public Long getNodeinstid() {
		return this.nodeinstid;
	}
	
	public void setNodeinstid(Long nodeinstid) {
		this.nodeinstid = nodeinstid;
	}



	public void copy(UserbizoptLog other){
  
		this.setId(other.getId());  
		this.setBizname(other.getBizname());  
		this.setBiztype(other.getBiztype());  
		this.setDjId(other.getDjId());  
		this.setCreatetime(other.getCreatetime());  
		this.setCreateuser(other.getCreateuser());  
		this.setCreaterip(other.getCreaterip());
  
		this.remark= other.getRemark();  
		this.nodeinstid= other.getNodeinstid();

	}
	
	public void copyNotNullProperty(UserbizoptLog other){
  
	if( other.getId() != null)
		this.setId(other.getId());  
	if( other.getBizname() != null)
		this.setBizname(other.getBizname());  
	if( other.getBiztype() != null)
		this.setBiztype(other.getBiztype());  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());  
	if( other.getCreatetime() != null)
		this.setCreatetime(other.getCreatetime());  
	if( other.getCreateuser() != null)
		this.setCreateuser(other.getCreateuser());  
	if( other.getCreaterip() != null)
		this.setCreaterip(other.getCreaterip());
  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getNodeinstid() != null)
			this.nodeinstid= other.getNodeinstid();

	}
	
	public void clearProperties(){
  
		this.remark= null;  
		this.nodeinstid= null;

	}
}
