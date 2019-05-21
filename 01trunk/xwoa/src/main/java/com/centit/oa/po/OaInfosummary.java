package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaInfosummary implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String id;

	private String  no;
	private String  creater;
	private String  unitcode;
	private String  telephone;
	private String  sex;
	private String  remark;
	private Date  creatertime;

	private OptBaseInfo optBaseInfo;
	// Constructors
	/** default constructor */
	public OaInfosummary() {
	}
	/** minimal constructor */
	public OaInfosummary(
		String id		
		) {
	
	
		this.id = id;		
			
	}

/** full constructor */
	public OaInfosummary(
	 String id		
	,String  no,String  creater,String  unitcode,String  telephone,String  sex,String  remark,Date  creatertime) {
	
	
		this.id = id;		
	
		this.no= no;
		this.creater= creater;
		this.unitcode= unitcode;
		this.telephone= telephone;
		this.sex= sex;
		this.remark= remark;
		this.creatertime= creatertime;		
	}
	

  
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	// Property accessors
  
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public String getUnitcode() {
		return this.unitcode;
	}
	
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
  
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
  
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}



	public void copy(OaInfosummary other){
  
		this.setId(other.getId());
  
		this.no= other.getNo();  
		this.creater= other.getCreater();  
		this.unitcode= other.getUnitcode();  
		this.telephone= other.getTelephone();  
		this.sex= other.getSex();  
		this.remark= other.getRemark();  
		this.creatertime= other.getCreatertime();

	}
	
	public void copyNotNullProperty(OaInfosummary other){
  
	if( other.getId() != null)
		this.setId(other.getId());
  
		if( other.getNo() != null)
			this.no= other.getNo();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getUnitcode() != null)
			this.unitcode= other.getUnitcode();  
		if( other.getTelephone() != null)
			this.telephone= other.getTelephone();  
		if( other.getSex() != null)
			this.sex= other.getSex();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();

	}
	
	public void clearProperties(){
  
		this.no= null;  
		this.creater= null;  
		this.unitcode= null;  
		this.telephone= null;  
		this.sex= null;  
		this.remark= null;  
		this.creatertime= null;

	}
    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }
    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
	
	
}
