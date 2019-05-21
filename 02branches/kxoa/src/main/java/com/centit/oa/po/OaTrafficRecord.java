package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaTrafficRecord implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;
    private String cardjid;
    private String  djid;
	private String  carno;
	private String  carType;
	private String  brand;
	private String  modelType;
	private String  depno;
	private String  douser;
	private Date  dotime;
	private String  doaddress;
	private String  penalty;
	private String  actualDamages;
	private String  remark;
	private String  creater;
	private Date  creatertime;

	// Constructors
	/** default constructor */
	public OaTrafficRecord() {
	}
	/** minimal constructor */
	public OaTrafficRecord(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaTrafficRecord(
	 String no		
	,String  djid,String  carno,String  carType,String  brand,String  modelType,String  depno,String  douser,Date  dotime,String  doaddress,String  penalty,String  actualDamages,String  remark,String  creater,Date  creatertime,String cardjid) {
	
	
		this.no = no;		
	
		this.djid= djid;
		this.carno= carno;
		this.carType= carType;
		this.brand= brand;
		this.modelType= modelType;
		this.depno= depno;
		this.douser= douser;
		this.dotime= dotime;
		this.doaddress= doaddress;
		this.penalty= penalty;
		this.actualDamages= actualDamages;
		this.remark= remark;
		this.creater= creater;
		this.creatertime= creatertime;	
		this.cardjid=cardjid;
	}
	
        public String getCardjid() {
        return cardjid;
    }
    public void setCardjid(String cardjid) {
        this.cardjid = cardjid;
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
  
	public String getCarno() {
		return this.carno;
	}
	
	public void setCarno(String carno) {
		this.carno = carno;
	}
  
	public String getCarType() {
		return this.carType;
	}
	
	public void setCarType(String carType) {
		this.carType = carType;
	}
  
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
  
	public String getModelType() {
		return this.modelType;
	}
	
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
  
	public String getDepno() {
		return this.depno;
	}
	
	public void setDepno(String depno) {
		this.depno = depno;
	}
  
	public String getDouser() {
		return this.douser;
	}
	
	public void setDouser(String douser) {
		this.douser = douser;
	}
  
	public Date getDotime() {
		return this.dotime;
	}
	
	public void setDotime(Date dotime) {
		this.dotime = dotime;
	}
  
	public String getDoaddress() {
		return this.doaddress;
	}
	
	public void setDoaddress(String doaddress) {
		this.doaddress = doaddress;
	}
  
	public String getPenalty() {
		return this.penalty;
	}
	
	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
  
	public String getActualDamages() {
		return this.actualDamages;
	}
	
	public void setActualDamages(String actualDamages) {
		this.actualDamages = actualDamages;
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



	public void copy(OaTrafficRecord other){
  
		this.setNo(other.getNo());
  
		this.djid= other.getDjid();  
		this.carno= other.getCarno();  
		this.carType= other.getCarType();  
		this.brand= other.getBrand();  
		this.modelType= other.getModelType();  
		this.depno= other.getDepno();  
		this.douser= other.getDouser();  
		this.dotime= other.getDotime();  
		this.doaddress= other.getDoaddress();  
		this.penalty= other.getPenalty();  
		this.actualDamages= other.getActualDamages();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();
		this.cardjid=other.getCardjid();

	}
	
	public void copyNotNullProperty(OaTrafficRecord other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getDjid() != null)
			this.djid= other.getDjid();  
		if( other.getCarno() != null)
			this.carno= other.getCarno();  
		if( other.getCarType() != null)
			this.carType= other.getCarType();  
		if( other.getBrand() != null)
			this.brand= other.getBrand();  
		if( other.getModelType() != null)
			this.modelType= other.getModelType();  
		if( other.getDepno() != null)
			this.depno= other.getDepno();  
		if( other.getDouser() != null)
			this.douser= other.getDouser();  
		if( other.getDotime() != null)
			this.dotime= other.getDotime();  
		if( other.getDoaddress() != null)
			this.doaddress= other.getDoaddress();  
		if( other.getPenalty() != null)
			this.penalty= other.getPenalty();  
		if( other.getActualDamages() != null)
			this.actualDamages= other.getActualDamages();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();
        if( other.getCardjid() != null)
            this.cardjid= other.getCardjid();
	}
	
	public void clearProperties(){
  
		this.djid= null;  
		this.carno= null;  
		this.carType= null;  
		this.brand= null;  
		this.modelType= null;  
		this.depno= null;  
		this.douser= null;  
		this.dotime= null;  
		this.doaddress= null;  
		this.penalty= null;  
		this.actualDamages= null;  
		this.remark= null;  
		this.creater= null;  
		this.creatertime= null;
		this.cardjid=null;

	}
}
