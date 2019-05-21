package com.centit.powerbase.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class PowerOrgInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private PowerOrgInfoId cid;

	private String itemName;
	private String uninName;
	public String getUninName() {
        return uninName;
    }
    public void setUninName(String uninName) {
        this.uninName = uninName;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String  wfcode;
	private String  setoperator;
	private Date  setime;
	private String item_type;
	// Constructors
	/** default constructor */
	public PowerOrgInfo() {
	}
	/** minimal constructor */
	public PowerOrgInfo(PowerOrgInfoId id 
				
		) {
		this.cid = id; 
			
			
	}

public String getItem_type() {
        return item_type;
    }
    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }
/** full constructor */
	public PowerOrgInfo(PowerOrgInfoId id
			
	,String  wfcode,String  setoperator,Date  setime) {
		this.cid = id; 
			
	
		this.wfcode= wfcode;
		this.setoperator= setoperator;
		this.setime= setime;		
	}

	public PowerOrgInfoId getCid() {
		return this.cid;
	}
	
	public void setCid(PowerOrgInfoId id) {
		this.cid = id;
	}
  
	public String getItemId() {
		if(this.cid==null)
			this.cid = new PowerOrgInfoId();
		return this.cid.getItemId();
	}
	
	public void setItemId(String itemId) {
		if(this.cid==null)
			this.cid = new PowerOrgInfoId();
		this.cid.setItemId(itemId);
	}
  
	public String getUnitcode() {
		if(this.cid==null)
			this.cid = new PowerOrgInfoId();
		return this.cid.getUnitcode();
	}
	
	public void setUnitcode(String unitcode) {
		if(this.cid==null)
			this.cid = new PowerOrgInfoId();
		this.cid.setUnitcode(unitcode);
	}
	
	

	// Property accessors
  
	public String getWfcode() {
		return this.wfcode;
	}
	
	public void setWfcode(String wfcode) {
		this.wfcode = wfcode;
	}
  
	public String getSetoperator() {
		return this.setoperator;
	}
	
	public void setSetoperator(String setoperator) {
		this.setoperator = setoperator;
	}
  
	public Date getSetime() {
		return this.setime;
	}
	
	public void setSetime(Date setime) {
		this.setime = setime;
	}



	public void copy(PowerOrgInfo other){
  
		this.setItemId(other.getItemId());  
		this.setUnitcode(other.getUnitcode());
  
		this.wfcode= other.getWfcode();  
		this.setoperator= other.getSetoperator();  
		this.setime= other.getSetime();

	}
	
	public void copyNotNullProperty(PowerOrgInfo other){
  
	if( other.getItemId() != null)
		this.setItemId(other.getItemId());  
	if( other.getUnitcode() != null)
		this.setUnitcode(other.getUnitcode());
  
		if( other.getWfcode() != null)
			this.wfcode= other.getWfcode();  
		if( other.getSetoperator() != null)
			this.setoperator= other.getSetoperator();  
		if( other.getSetime() != null)
			this.setime= other.getSetime();

	}
	
	public void clearProperties(){
  
		this.wfcode= null;  
		this.setoperator= null;  
		this.setime= null;

	}
}
