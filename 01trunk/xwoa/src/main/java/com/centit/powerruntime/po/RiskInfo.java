package com.centit.powerruntime.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class RiskInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long riskid;

	private String  risktype;
	private String  riskdes;
	private String  riskdeal;
	private Date  settime;
	private String  setuser;
	private Set<PowerOptInfo> powerOptInfos = null;// new ArrayList<PowerOptInfo>();

	// Constructors
	/** default constructor */
	public RiskInfo() {
	}
	/** minimal constructor */
	public RiskInfo(
		Long riskid		
		) {
	
	
		this.riskid = riskid;		
			
	}

/** full constructor */
	public RiskInfo(
	 Long riskid		
	,String  risktype,String  riskdes,String  riskdeal,Date  settime,String  setuser) {
	
	
		this.riskid = riskid;		
	
		this.risktype= risktype;
		this.riskdes= riskdes;
		this.riskdeal= riskdeal;
		this.settime= settime;
		this.setuser= setuser;		
	}
	

  
	public Long getRiskid() {
		return this.riskid;
	}

	public void setRiskid(Long riskid) {
		this.riskid = riskid;
	}
	// Property accessors
  
	public String getRisktype() {
		return this.risktype;
	}
	
	public void setRisktype(String risktype) {
		this.risktype = risktype;
	}
  
	public String getRiskdes() {
		return this.riskdes;
	}
	
	public void setRiskdes(String riskdes) {
		this.riskdes = riskdes;
	}
  
	public String getRiskdeal() {
		return this.riskdeal;
	}
	
	public void setRiskdeal(String riskdeal) {
		this.riskdeal = riskdeal;
	}
  
	public Date getSettime() {
		return this.settime;
	}
	
	public void setSettime(Date settime) {
		this.settime = settime;
	}
  
	public String getSetuser() {
		return this.setuser;
	}
	
	public void setSetuser(String setuser) {
		this.setuser = setuser;
	}


	public Set<PowerOptInfo> getPowerOptInfos(){
		if(this.powerOptInfos==null)
			this.powerOptInfos = new HashSet<PowerOptInfo>();
		return this.powerOptInfos;
	}

	public void setPowerOptInfos(Set<PowerOptInfo> powerOptInfos) {
		this.powerOptInfos = powerOptInfos;
	}	

	public void addPowerOptInfo(PowerOptInfo powerOptInfo ){
		if (this.powerOptInfos==null)
			this.powerOptInfos = new HashSet<PowerOptInfo>();
		this.powerOptInfos.add(powerOptInfo);
	}
	
	public void removePowerOptInfo(PowerOptInfo powerOptInfo ){
		if (this.powerOptInfos==null)
			return;
		this.powerOptInfos.remove(powerOptInfo);
	}
	
	public PowerOptInfo newPowerOptInfo(){
		PowerOptInfo res = new PowerOptInfo();
  
		res.setRiskid(this.getRiskid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replacePowerOptInfos(List<PowerOptInfo> powerOptInfos) {
		List<PowerOptInfo> newObjs = new ArrayList<PowerOptInfo>();
		for(PowerOptInfo p :powerOptInfos){
			if(p==null)
				continue;
			PowerOptInfo newdt = newPowerOptInfo();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<PowerOptInfo> oldObjs = new HashSet<PowerOptInfo>();
		oldObjs.addAll(getPowerOptInfos());
		
		for(Iterator<PowerOptInfo> it=oldObjs.iterator(); it.hasNext();){
			PowerOptInfo odt = it.next();
			found = false;
			for(PowerOptInfo newdt :newObjs){
				if(odt.getItemId().equals( newdt.getItemId())){
					found = true;
					break;
				}
			}
			if(! found)
				removePowerOptInfo(odt);
		}
		oldObjs.clear();
		//insert or update
		for(PowerOptInfo newdt :newObjs){
			found = false;
			for(Iterator<PowerOptInfo> it=getPowerOptInfos().iterator();
			 it.hasNext();){
				PowerOptInfo odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addPowerOptInfo(newdt);
		} 	
	}	


	public void copy(RiskInfo other){
  
		this.setRiskid(other.getRiskid());
  
		this.risktype= other.getRisktype();  
		this.riskdes= other.getRiskdes();  
		this.riskdeal= other.getRiskdeal();  
		this.settime= other.getSettime();  
		this.setuser= other.getSetuser();
	
		this.powerOptInfos = other.getPowerOptInfos();
	}
	
	public void copyNotNullProperty(RiskInfo other){
  
	if( other.getRiskid() != null)
		this.setRiskid(other.getRiskid());
  
		if( other.getRisktype() != null)
			this.risktype= other.getRisktype();  
		if( other.getRiskdes() != null)
			this.riskdes= other.getRiskdes();  
		if( other.getRiskdeal() != null)
			this.riskdeal= other.getRiskdeal();  
		if( other.getSettime() != null)
			this.settime= other.getSettime();  
		if( other.getSetuser() != null)
			this.setuser= other.getSetuser();
	
		this.powerOptInfos = other.getPowerOptInfos();
	}
	
	public void clearProperties(){
  
		this.risktype= null;  
		this.riskdes= null;  
		this.riskdeal= null;  
		this.settime= null;  
		this.setuser= null;
	
		this.powerOptInfos = new HashSet<PowerOptInfo>();
	}
}
