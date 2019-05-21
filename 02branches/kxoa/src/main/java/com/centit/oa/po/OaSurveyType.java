package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaSurveyType implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	/**
	 * 流水号
	 */
	private String no;
	/**
     * 调查类型
     */
	private String  reType;
	/**
     * 名称（已经弃用，改用reType）
     */
	private String  comName;
	/**
     * 描述
     */
	private String  remark;
	/**
	 * 启用状态
	 * T启用，F未启用
	 */
	private String  state;
	/**
     *创建人
     */
	private String  creater;
	/**
     * 创建日期
     */
	private Date  creatertime;

	// Constructors
	/** default constructor */
	public OaSurveyType() {
	}
	/** minimal constructor */
	public OaSurveyType(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaSurveyType(
	 String no		
	,String  reType,String  comName,String  remark,String  state,String  creater,Date  creatertime) {
	
	
		this.no = no;		
	
		this.reType= reType;
		this.comName= comName;
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
  
	public String getReType() {
		return this.reType;
	}
	
	public void setReType(String reType) {
		this.reType = reType;
	}
  
	public String getComName() {
		return this.comName;
	}
	
	public void setComName(String comName) {
		this.comName = comName;
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



	public void copy(OaSurveyType other){
  
		this.setNo(other.getNo());
  
		this.reType= other.getReType();  
		this.comName= other.getComName();  
		this.remark= other.getRemark();  
		this.state= other.getState();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();

	}
	
	public void copyNotNullProperty(OaSurveyType other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getReType() != null)
			this.reType= other.getReType();  
		if( other.getComName() != null)
			this.comName= other.getComName();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getState() != null)
			this.state= other.getState();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();

	}
	
	public void clearProperties(){
  
		this.reType= null;  
		this.comName= null;  
		this.remark= null;  
		this.state= null;  
		this.creater= null;  
		this.creatertime= null;

	}
}
