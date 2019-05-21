package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaCommonType implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;//流水号

	private String  publicType;//类别meeting:会议室类型cars:车辆类型
	
	private Long  orderno;//排序
	private String  coding;//编码
	private String  comName;//名称
	private String  remark;//描述
	private String  state;//启用状态
	private String  creater;//创建人
	private Date  creatertime;//创建日期
	private String  isopen;//是否公开

	// Constructors
	/** default constructor */
	public OaCommonType() {
	}
	/** minimal constructor */
	public OaCommonType(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaCommonType(
	 String no		
	,String  publicType,Long  orderno,String  coding,String  comName,String  remark,String  state,String  creater,Date  creatertime,String  isopen) {
	
	
		this.no = no;		
	
		this.publicType= publicType;
		this.orderno= orderno;
		this.coding= coding;
		this.comName= comName;
		this.remark= remark;
		this.state= state;
		this.creater= creater;
		this.creatertime= creatertime;
		this.isopen= isopen;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getPublicType() {
		return this.publicType;
	}
	
	public void setPublicType(String publicType) {
		this.publicType = publicType;
	}
  
	public Long getOrderno() {
		return this.orderno;
	}
	
	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}
  
	public String getCoding() {
		return this.coding;
	}
	
	public void setCoding(String coding) {
		this.coding = coding;
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
  
	public String getIsopen() {
		return this.isopen;
	}
	
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}



	public void copy(OaCommonType other){
  
		this.setNo(other.getNo());
  
		this.publicType= other.getPublicType();  
		this.orderno= other.getOrderno();  
		this.coding= other.getCoding();  
		this.comName= other.getComName();  
		this.remark= other.getRemark();  
		this.state= other.getState();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.isopen= other.getIsopen();

	}
	
	public void copyNotNullProperty(OaCommonType other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getPublicType() != null)
			this.publicType= other.getPublicType();  
		if( other.getOrderno() != null)
			this.orderno= other.getOrderno();  
		if( other.getCoding() != null)
			this.coding= other.getCoding();  
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
		if( other.getIsopen() != null)
			this.isopen= other.getIsopen();

	}
	
	public void clearProperties(){
  
		this.publicType= null;  
		this.orderno= null;  
		this.coding= null;  
		this.comName= null;  
		this.remark= null;  
		this.state= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.isopen= null;

	}
}
