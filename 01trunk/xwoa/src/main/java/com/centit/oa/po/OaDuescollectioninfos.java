package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaDuescollectioninfos implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OaDuescollectioninfosId cid;


	private String  unitcode;
	private Date  createtime;
	private String  amount;
	private String  isfinish;
	private String  tempstate;//临时状态
	// Constructors
	/** default constructor */
	public OaDuescollectioninfos() {
	}
	/** minimal constructor */
	public OaDuescollectioninfos(OaDuescollectioninfosId id 
				
		) {
		this.cid = id; 
			
			
	}

public OaDuescollectioninfos(OaDuescollectioninfosId cid, String unitcode,
            Date createtime, String amount, String isfinish, String tempstate) {
        this.cid = cid;
        this.unitcode = unitcode;
        this.createtime = createtime;
        this.amount = amount;
        this.isfinish = isfinish;
        this.tempstate = tempstate;
    }
/** full constructor */
	public OaDuescollectioninfos(OaDuescollectioninfosId id
			
	,String  unitcode,Date  createtime,String  amount,String  isfinish) {
		this.cid = id; 
			
	
		this.unitcode= unitcode;
		this.createtime= createtime;
		this.amount= amount;
		this.isfinish= isfinish;		
	}

	public OaDuescollectioninfosId getCid() {
		return this.cid;
	}
	
	public void setCid(OaDuescollectioninfosId id) {
		this.cid = id;
	}
  
	public String getDjId() {
		if(this.cid==null)
			this.cid = new OaDuescollectioninfosId();
		return this.cid.getDjId();
	}
	
	public void setDjId(String djId) {
		if(this.cid==null)
			this.cid = new OaDuescollectioninfosId();
		this.cid.setDjId(djId);
	}
  
	public String getUsercode() {
		if(this.cid==null)
			this.cid = new OaDuescollectioninfosId();
		return this.cid.getUsercode();
	}
	
	public void setUsercode(String usercode) {
		if(this.cid==null)
			this.cid = new OaDuescollectioninfosId();
		this.cid.setUsercode(usercode);
	}
	
	

	// Property accessors
  
	public String getUnitcode() {
		return this.unitcode;
	}
	
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public String getAmount() {
		return this.amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
  
	public String getIsfinish() {
		return this.isfinish;
	}
	
	public void setIsfinish(String isfinish) {
		this.isfinish = isfinish;
	}



	public String getTempstate() {
        return tempstate;
    }
    public void setTempstate(String tempstate) {
        this.tempstate = tempstate;
    }
    public void copy(OaDuescollectioninfos other){
  
		this.setDjId(other.getDjId());  
		this.setUsercode(other.getUsercode());
  
		this.unitcode= other.getUnitcode();  
		this.createtime= other.getCreatetime();  
		this.amount= other.getAmount();  
		this.isfinish= other.getIsfinish();
		this.tempstate=other.getTempstate();

	}
	
	public void copyNotNullProperty(OaDuescollectioninfos other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());  
	if( other.getUsercode() != null)
		this.setUsercode(other.getUsercode());
  
		if( other.getUnitcode() != null)
			this.unitcode= other.getUnitcode();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getAmount() != null)
			this.amount= other.getAmount();  
		if( other.getIsfinish() != null)
			this.isfinish= other.getIsfinish();
		if( other.getTempstate() != null)
		this.tempstate=other.getTempstate();

	}
	
	public void clearProperties(){
  
		this.unitcode= null;  
		this.createtime= null;  
		this.amount= null;  
		this.isfinish= null;
		this.tempstate=null;
	}
}
