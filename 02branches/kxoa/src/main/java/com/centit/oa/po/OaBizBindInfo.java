package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaBizBindInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  startDjid;
	private String  endDjid;
	private String  bizType;
	private Date  createtime;
	private String  createuser;
	private Long  nodeinstid;
	private String  nodename;

	private OptBaseInfo optBaseInfo; 
	// Constructors
	/** default constructor */
	public OaBizBindInfo() {
	}
	/** minimal constructor */
	public OaBizBindInfo(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaBizBindInfo(
	 String no		
	,String  startDjid,String  endDjid,String  bizType,Date  createtime,String  createuser,Long  nodeinstid,String  nodename) {
	
	
		this.no = no;		
	
		this.startDjid= startDjid;
		this.endDjid= endDjid;
		this.bizType= bizType;
		this.createtime= createtime;
		this.createuser= createuser;
		this.nodeinstid= nodeinstid;
		this.nodename= nodename;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getStartDjid() {
		return this.startDjid;
	}
	
	public void setStartDjid(String startDjid) {
		this.startDjid = startDjid;
	}
  
	public String getEndDjid() {
		return this.endDjid;
	}
	
	public void setEndDjid(String endDjid) {
		this.endDjid = endDjid;
	}
  
	public String getBizType() {
		return this.bizType;
	}
	
	public void setBizType(String bizType) {
		this.bizType = bizType;
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
  
	public Long getNodeinstid() {
		return this.nodeinstid;
	}
	
	public void setNodeinstid(Long nodeinstid) {
		this.nodeinstid = nodeinstid;
	}
  
	public String getNodename() {
		return this.nodename;
	}
	
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}



	public void copy(OaBizBindInfo other){
  
		this.setNo(other.getNo());
  
		this.startDjid= other.getStartDjid();  
		this.endDjid= other.getEndDjid();  
		this.bizType= other.getBizType();  
		this.createtime= other.getCreatetime();  
		this.createuser= other.getCreateuser();  
		this.nodeinstid= other.getNodeinstid();  
		this.nodename= other.getNodename();

	}
	
	public void copyNotNullProperty(OaBizBindInfo other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getStartDjid() != null)
			this.startDjid= other.getStartDjid();  
		if( other.getEndDjid() != null)
			this.endDjid= other.getEndDjid();  
		if( other.getBizType() != null)
			this.bizType= other.getBizType();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getCreateuser() != null)
			this.createuser= other.getCreateuser();  
		if( other.getNodeinstid() != null)
			this.nodeinstid= other.getNodeinstid();  
		if( other.getNodename() != null)
			this.nodename= other.getNodename();

	}
	
	public void clearProperties(){
  
		this.startDjid= null;  
		this.endDjid= null;  
		this.bizType= null;  
		this.createtime= null;  
		this.createuser= null;  
		this.nodeinstid= null;  
		this.nodename= null;

	}
    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }
    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }	
}
