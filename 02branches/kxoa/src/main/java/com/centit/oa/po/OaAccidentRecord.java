package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaAccidentRecord implements java.io.Serializable {
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
	private String  creater;
	private Date  creatertime;
	private Date  dotime;
	private String  doaddress;
	private String  responsibility;
	private String  casualties;
	private String  losses;
	private String  penalty;
	private String  actualDamages;
	private String  claimDifference;
	private String  accidentAfter;
	private byte[]  scenePhotos;
	private String  photoName;
	private String  remark;

	// Constructors
	/** default constructor */
	public OaAccidentRecord() {
	}
	/** minimal constructor */
	public OaAccidentRecord(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaAccidentRecord(
	 String no		
	,String  djid,String  carno,String  carType,String  brand,String  modelType,String  depno,String  douser,String  creater,Date  creatertime,Date  dotime,String  doaddress,String  responsibility,String  casualties,String  losses,String  penalty,String  actualDamages,String  claimDifference,String  accidentAfter,byte[]  scenePhotos,String  photoName,String  remark,String cardjid) {
	
	
		this.no = no;		

		this.djid= djid;
		this.carno= carno;
		this.carType= carType;
		this.brand= brand;
		this.modelType= modelType;
		this.depno= depno;
		this.douser= douser;
		this.creater= creater;
		this.creatertime= creatertime;
		this.dotime= dotime;
		this.doaddress= doaddress;
		this.responsibility= responsibility;
		this.casualties= casualties;
		this.losses= losses;
		this.penalty= penalty;
		this.actualDamages= actualDamages;
		this.claimDifference= claimDifference;
		this.accidentAfter= accidentAfter;
		this.scenePhotos= scenePhotos;
		this.photoName= photoName;
		this.remark= remark;	
		  this.cardjid=cardjid;
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
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	public String getCardjid() {
        return cardjid;
    }
    public void setCardjid(String cardjid) {
        this.cardjid = cardjid;
    }

	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
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
  
	public String getResponsibility() {
		return this.responsibility;
	}
	
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
  
	public String getCasualties() {
		return this.casualties;
	}
	
	public void setCasualties(String casualties) {
		this.casualties = casualties;
	}
  
	public String getLosses() {
		return this.losses;
	}
	
	public void setLosses(String losses) {
		this.losses = losses;
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
  
	public String getClaimDifference() {
		return this.claimDifference;
	}
	
	public void setClaimDifference(String claimDifference) {
		this.claimDifference = claimDifference;
	}
  
	public String getAccidentAfter() {
		return this.accidentAfter;
	}
	
	public void setAccidentAfter(String accidentAfter) {
		this.accidentAfter = accidentAfter;
	}
  
	public byte[] getScenePhotos() {
		return this.scenePhotos;
	}
	
	public void setScenePhotos(byte[] scenePhotos) {
		this.scenePhotos = scenePhotos;
	}
  
	public String getPhotoName() {
		return this.photoName;
	}
	
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}



	public void copy(OaAccidentRecord other){
  
		this.setNo(other.getNo());
  
		this.djid= other.getDjid();  
		this.carno= other.getCarno();  
		this.carType= other.getCarType();  
		this.brand= other.getBrand();  
		this.modelType= other.getModelType();  
		this.depno= other.getDepno();  
		this.douser= other.getDouser();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.dotime= other.getDotime();  
		this.doaddress= other.getDoaddress();  
		this.responsibility= other.getResponsibility();  
		this.casualties= other.getCasualties();  
		this.losses= other.getLosses();  
		this.penalty= other.getPenalty();  
		this.actualDamages= other.getActualDamages();  
		this.claimDifference= other.getClaimDifference();  
		this.accidentAfter= other.getAccidentAfter();  
		this.scenePhotos= other.getScenePhotos();  
		this.photoName= other.getPhotoName();  
		this.remark= other.getRemark();
this.cardjid=other.getCardjid();
	}
	
	public void copyNotNullProperty(OaAccidentRecord other){
  
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
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getDotime() != null)
			this.dotime= other.getDotime();  
		if( other.getDoaddress() != null)
			this.doaddress= other.getDoaddress();  
		if( other.getResponsibility() != null)
			this.responsibility= other.getResponsibility();  
		if( other.getCasualties() != null)
			this.casualties= other.getCasualties();  
		if( other.getLosses() != null)
			this.losses= other.getLosses();  
		if( other.getPenalty() != null)
			this.penalty= other.getPenalty();  
		if( other.getActualDamages() != null)
			this.actualDamages= other.getActualDamages();  
		if( other.getClaimDifference() != null)
			this.claimDifference= other.getClaimDifference();  
		if( other.getAccidentAfter() != null)
			this.accidentAfter= other.getAccidentAfter();  
		if( other.getScenePhotos() != null)
			this.scenePhotos= other.getScenePhotos();  
		if( other.getPhotoName() != null)
			this.photoName= other.getPhotoName();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();
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
		this.creater= null;  
		this.creatertime= null;  
		this.dotime= null;  
		this.doaddress= null;  
		this.responsibility= null;  
		this.casualties= null;  
		this.losses= null;  
		this.penalty= null;  
		this.actualDamages= null;  
		this.claimDifference= null;  
		this.accidentAfter= null;  
		this.scenePhotos= null;  
		this.photoName= null;  
		this.remark= null;
this.cardjid=null;
	}
}
