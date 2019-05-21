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

public class OaDriverBook implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;
    private String cardjid;
    private String  carno;
	private String  brand;
	private String  driver;
	private String  depno;
	private String  caruser;
	private Date  startDate;
	private Date  backDate;
	private String  startMileage;
	private String  endMileage;
	private String  thisMileage;
	private String  totalCost;
	private String  remark;
	private String  creater;
	private Date  creatertime;
	private Set<OaCostInfo> oaCostInfos = null;// new ArrayList<OaCostInfo>();
	private Set<OaAccidentRecord> oaAccidentRecords = null;// new ArrayList<OaAccidentRecord>();
	private Set<OaTrafficRecord> oaTrafficRecords = null;// new ArrayList<OaTrafficRecord>();

	// Constructors
	/** default constructor */
	public OaDriverBook() {
	}
	/** minimal constructor */
	public OaDriverBook(
		String djid		
		) {
	
	
		this.djid = djid;		
			
	}

/** full constructor */
	public OaDriverBook(
	 String djid		
	,String  carno,String  brand,String  driver,String  depno,String  caruser,Date  startDate,Date  backDate,String  startMileage,String  endMileage,String  thisMileage,String  totalCost,String  remark,String  creater,Date  creatertime,String cardjid) {
	
	
		this.djid = djid;		
	
		this.carno= carno;
		this.brand= brand;
		this.driver= driver;
		this.depno= depno;
		this.caruser= caruser;
		this.startDate= startDate;
		this.backDate= backDate;
		this.startMileage= startMileage;
		this.endMileage= endMileage;
		this.thisMileage= thisMileage;
		this.totalCost= totalCost;
		this.remark= remark;
		this.creater= creater;
		this.creatertime= creatertime;	
		this.cardjid=cardjid;
	}
	

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
	// Property accessors
  
	public String getCarno() {
		return this.carno;
	}
	
	public void setCarno(String carno) {
		this.carno = carno;
	}
  
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
  
	public String getDriver() {
		return this.driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
  
	public String getDepno() {
		return this.depno;
	}
	
	public void setDepno(String depno) {
		this.depno = depno;
	}
  
	public String getCaruser() {
		return this.caruser;
	}
	
	public void setCaruser(String caruser) {
		this.caruser = caruser;
	}
  
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
  
	public Date getBackDate() {
		return this.backDate;
	}
	
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
  
	public String getStartMileage() {
		return this.startMileage;
	}
	
	public void setStartMileage(String startMileage) {
		this.startMileage = startMileage;
	}
  
	public String getEndMileage() {
		return this.endMileage;
	}
	
	public void setEndMileage(String endMileage) {
		this.endMileage = endMileage;
	}
  
	public String getThisMileage() {
		return this.thisMileage;
	}
	
	public void setThisMileage(String thisMileage) {
		this.thisMileage = thisMileage;
	}
  
	public String getTotalCost() {
		return this.totalCost;
	}
	
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
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
    public String getCardjid() {
    return cardjid;
}
public void setCardjid(String cardjid) {
    this.cardjid = cardjid;
}

	public Set<OaCostInfo> getOaCostInfos(){
		if(this.oaCostInfos==null)
			this.oaCostInfos = new HashSet<OaCostInfo>();
		return this.oaCostInfos;
	}

	public void setOaCostInfos(Set<OaCostInfo> oaCostInfos) {
		this.oaCostInfos = oaCostInfos;
	}	

	public void addOaCostInfo(OaCostInfo oaCostInfo ){
		if (this.oaCostInfos==null)
			this.oaCostInfos = new HashSet<OaCostInfo>();
		this.oaCostInfos.add(oaCostInfo);
	}
	
	public void removeOaCostInfo(OaCostInfo oaCostInfo ){
		if (this.oaCostInfos==null)
			return;
		this.oaCostInfos.remove(oaCostInfo);
	}
	
	public OaCostInfo newOaCostInfo(){
		OaCostInfo res = new OaCostInfo();
  
		res.setDjid(this.getDjid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceOaCostInfos(List<OaCostInfo> oaCostInfos) {
		List<OaCostInfo> newObjs = new ArrayList<OaCostInfo>();
		for(OaCostInfo p :oaCostInfos){
			if(p==null)
				continue;
			OaCostInfo newdt = newOaCostInfo();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaCostInfo> oldObjs = new HashSet<OaCostInfo>();
		oldObjs.addAll(getOaCostInfos());
		
		for(Iterator<OaCostInfo> it=oldObjs.iterator(); it.hasNext();){
			OaCostInfo odt = it.next();
			found = false;
			for(OaCostInfo newdt :newObjs){
				if(odt.getNo().equals( newdt.getNo())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaCostInfo(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaCostInfo newdt :newObjs){
			found = false;
			for(Iterator<OaCostInfo> it=getOaCostInfos().iterator();
			 it.hasNext();){
				OaCostInfo odt = it.next();
				if(odt.getNo().equals( newdt.getNo())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaCostInfo(newdt);
		} 	
	}	

	public Set<OaAccidentRecord> getOaAccidentRecords(){
		if(this.oaAccidentRecords==null)
			this.oaAccidentRecords = new HashSet<OaAccidentRecord>();
		return this.oaAccidentRecords;
	}

	public void setOaAccidentRecords(Set<OaAccidentRecord> oaAccidentRecords) {
		this.oaAccidentRecords = oaAccidentRecords;
	}	

	public void addOaAccidentRecord(OaAccidentRecord oaAccidentRecord ){
		if (this.oaAccidentRecords==null)
			this.oaAccidentRecords = new HashSet<OaAccidentRecord>();
		this.oaAccidentRecords.add(oaAccidentRecord);
	}
	
	public void removeOaAccidentRecord(OaAccidentRecord oaAccidentRecord ){
		if (this.oaAccidentRecords==null)
			return;
		this.oaAccidentRecords.remove(oaAccidentRecord);
	}
	
	public OaAccidentRecord newOaAccidentRecord(){
		OaAccidentRecord res = new OaAccidentRecord();
  
		res.setDjid(this.getDjid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceOaAccidentRecords(List<OaAccidentRecord> oaAccidentRecords) {
		List<OaAccidentRecord> newObjs = new ArrayList<OaAccidentRecord>();
		for(OaAccidentRecord p :oaAccidentRecords){
			if(p==null)
				continue;
			OaAccidentRecord newdt = newOaAccidentRecord();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaAccidentRecord> oldObjs = new HashSet<OaAccidentRecord>();
		oldObjs.addAll(getOaAccidentRecords());
		
		for(Iterator<OaAccidentRecord> it=oldObjs.iterator(); it.hasNext();){
			OaAccidentRecord odt = it.next();
			found = false;
			for(OaAccidentRecord newdt :newObjs){
				if(odt.getNo().equals( newdt.getNo())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaAccidentRecord(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaAccidentRecord newdt :newObjs){
			found = false;
			for(Iterator<OaAccidentRecord> it=getOaAccidentRecords().iterator();
			 it.hasNext();){
				OaAccidentRecord odt = it.next();
				if(odt.getNo().equals( newdt.getNo())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaAccidentRecord(newdt);
		} 	
	}	

	public Set<OaTrafficRecord> getOaTrafficRecords(){
		if(this.oaTrafficRecords==null)
			this.oaTrafficRecords = new HashSet<OaTrafficRecord>();
		return this.oaTrafficRecords;
	}

	public void setOaTrafficRecords(Set<OaTrafficRecord> oaTrafficRecords) {
		this.oaTrafficRecords = oaTrafficRecords;
	}	

	public void addOaTrafficRecord(OaTrafficRecord oaTrafficRecord ){
		if (this.oaTrafficRecords==null)
			this.oaTrafficRecords = new HashSet<OaTrafficRecord>();
		this.oaTrafficRecords.add(oaTrafficRecord);
	}
	
	public void removeOaTrafficRecord(OaTrafficRecord oaTrafficRecord ){
		if (this.oaTrafficRecords==null)
			return;
		this.oaTrafficRecords.remove(oaTrafficRecord);
	}
	
	public OaTrafficRecord newOaTrafficRecord(){
		OaTrafficRecord res = new OaTrafficRecord();
  
		res.setDjid(this.getDjid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
	 * 
	 */
	public void replaceOaTrafficRecords(List<OaTrafficRecord> oaTrafficRecords) {
		List<OaTrafficRecord> newObjs = new ArrayList<OaTrafficRecord>();
		for(OaTrafficRecord p :oaTrafficRecords){
			if(p==null)
				continue;
			OaTrafficRecord newdt = newOaTrafficRecord();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<OaTrafficRecord> oldObjs = new HashSet<OaTrafficRecord>();
		oldObjs.addAll(getOaTrafficRecords());
		
		for(Iterator<OaTrafficRecord> it=oldObjs.iterator(); it.hasNext();){
			OaTrafficRecord odt = it.next();
			found = false;
			for(OaTrafficRecord newdt :newObjs){
				if(odt.getNo().equals( newdt.getNo())){
					found = true;
					break;
				}
			}
			if(! found)
				removeOaTrafficRecord(odt);
		}
		oldObjs.clear();
		//insert or update
		for(OaTrafficRecord newdt :newObjs){
			found = false;
			for(Iterator<OaTrafficRecord> it=getOaTrafficRecords().iterator();
			 it.hasNext();){
				OaTrafficRecord odt = it.next();
				if(odt.getNo().equals( newdt.getNo())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addOaTrafficRecord(newdt);
		} 	
	}	


	public void copy(OaDriverBook other){
  
		this.setDjid(other.getDjid());
  
		this.carno= other.getCarno();  
		this.brand= other.getBrand();  
		this.driver= other.getDriver();  
		this.depno= other.getDepno();  
		this.caruser= other.getCaruser();  
		this.startDate= other.getStartDate();  
		this.backDate= other.getBackDate();  
		this.startMileage= other.getStartMileage();  
		this.endMileage= other.getEndMileage();  
		this.thisMileage= other.getThisMileage();  
		this.totalCost= other.getTotalCost();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();
	
		this.oaCostInfos = other.getOaCostInfos();	
		this.oaAccidentRecords = other.getOaAccidentRecords();	
		this.oaTrafficRecords = other.getOaTrafficRecords();
		this.cardjid=other.getCardjid();
	}
	
	public void copyNotNullProperty(OaDriverBook other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getCarno() != null)
			this.carno= other.getCarno();  
		if( other.getBrand() != null)
			this.brand= other.getBrand();  
		if( other.getDriver() != null)
			this.driver= other.getDriver();  
		if( other.getDepno() != null)
			this.depno= other.getDepno();  
		if( other.getCaruser() != null)
			this.caruser= other.getCaruser();  
		if( other.getStartDate() != null)
			this.startDate= other.getStartDate();  
		if( other.getBackDate() != null)
			this.backDate= other.getBackDate();  
		if( other.getStartMileage() != null)
			this.startMileage= other.getStartMileage();  
		if( other.getEndMileage() != null)
			this.endMileage= other.getEndMileage();  
		if( other.getThisMileage() != null)
			this.thisMileage= other.getThisMileage();  
		if( other.getTotalCost() != null)
			this.totalCost= other.getTotalCost();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();
		   if( other.getCardjid() != null)
	            this.cardjid= other.getCardjid();
	    
		this.oaCostInfos = other.getOaCostInfos();	
		this.oaAccidentRecords = other.getOaAccidentRecords();	
		this.oaTrafficRecords = other.getOaTrafficRecords();
	}
	
	public void clearProperties(){
  
		this.carno= null;  
		this.brand= null;  
		this.driver= null;  
		this.depno= null;  
		this.caruser= null;  
		this.startDate= null;  
		this.backDate= null;  
		this.startMileage= null;  
		this.endMileage= null;  
		this.thisMileage= null;  
		this.totalCost= null;  
		this.remark= null;  
		this.creater= null;  
		this.creatertime= null;
	    this.cardjid=null;
		this.oaCostInfos = new HashSet<OaCostInfo>();	
		this.oaAccidentRecords = new HashSet<OaAccidentRecord>();	
		this.oaTrafficRecords = new HashSet<OaTrafficRecord>();
	}
}
