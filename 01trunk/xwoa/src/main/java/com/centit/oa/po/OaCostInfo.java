package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaCostInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  djid;
	private String  costType;
	private String  thecost;
	private String  remark;
	private String  creater;
	private Date  creatertime;

	// Constructors
	/** default constructor */
	public OaCostInfo() {
	}
	/** minimal constructor */
	public OaCostInfo(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaCostInfo(
	 String no		
	,String  djid,String  costType,String  thecost,String  remark,String  creater,Date  creatertime) {
	
	
		this.no = no;		
	
		this.djid= djid;
		this.costType= costType;
		this.thecost= thecost;
		this.remark= remark;
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
  
	public String getDjid() {
		return this.djid;
	}
	
	public void setDjid(String djid) {
		this.djid = djid;
	}
  
	public String getCostType() {
		return this.costType;
	}
	
	public void setCostType(String costType) {
		this.costType = costType;
	}
  
	public String getThecost() {
		return this.thecost;
	}
	
	public void setThecost(String thecost) {
		this.thecost = thecost;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
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



	public void copy(OaCostInfo other){
  
		this.setNo(other.getNo());
  
		this.djid= other.getDjid();  
		this.costType= other.getCostType();  
		this.thecost= other.getThecost();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();

	}
	
	public void copyNotNullProperty(OaCostInfo other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getDjid() != null)
			this.djid= other.getDjid();  
		if( other.getCostType() != null)
			this.costType= other.getCostType();  
		if( other.getThecost() != null)
			this.thecost= other.getThecost();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();

	}
	
	public void clearProperties(){
  
		this.djid= null;  
		this.costType= null;  
		this.thecost= null;  
		this.remark= null;  
		this.creater= null;  
		this.creatertime= null;

	}
}
