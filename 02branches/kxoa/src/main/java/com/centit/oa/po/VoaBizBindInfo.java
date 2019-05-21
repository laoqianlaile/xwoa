package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VoaBizBindInfo implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  transaffairname;
	private String  orgcode;
	private String  powerid;
	private String  powername;
	private String  bizstate;
	private String  biztype;
	private Date  createdate;
	private String  itemtype;
	
	private String dispatchUser;//发文人   dk 2015-12-28
	private Date dispatchDate;//发文时间
	private String dispatchDocNo;//发文号

	public String getDispatchDocNo() {
        return dispatchDocNo;
    }
    public void setDispatchDocNo(String dispatchDocNo) {
        this.dispatchDocNo = dispatchDocNo;
    }
    public String getDispatchUser() {
        return dispatchUser;
    }
    public void setDispatchUser(String dispatchUser) {
        this.dispatchUser = dispatchUser;
    }
    public Date getDispatchDate() {
        return dispatchDate;
    }
    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

   
	// Constructors
	/** default constructor */
	public VoaBizBindInfo() {
	}
	/** minimal constructor */
	public VoaBizBindInfo(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public VoaBizBindInfo(
	 String djId		
	,String  transaffairname,String  orgcode,String  powerid,String  powername,String  bizstate,String  biztype,Date  createdate,String  itemtype) {
	
	
		this.djId = djId;		
	
		this.transaffairname= transaffairname;
		this.orgcode= orgcode;
		this.powerid= powerid;
		this.powername= powername;
		this.bizstate= bizstate;
		this.biztype= biztype;
		this.createdate= createdate;
		this.itemtype= itemtype;		
	}
	

  
	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}
	// Property accessors
  
	public String getTransaffairname() {
		return this.transaffairname;
	}
	
	public void setTransaffairname(String transaffairname) {
		this.transaffairname = transaffairname;
	}
  
	public String getOrgcode() {
		return this.orgcode;
	}
	
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
  
	public String getPowerid() {
		return this.powerid;
	}
	
	public void setPowerid(String powerid) {
		this.powerid = powerid;
	}
  
	public String getPowername() {
		return this.powername;
	}
	
	public void setPowername(String powername) {
		this.powername = powername;
	}
  
	public String getBizstate() {
		return this.bizstate;
	}
	
	public void setBizstate(String bizstate) {
		this.bizstate = bizstate;
	}
  
	public String getBiztype() {
		return this.biztype;
	}
	
	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}
  
	public Date getCreatedate() {
		return this.createdate;
	}
	
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
  
	public String getItemtype() {
		return this.itemtype;
	}
	
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}



	public void copy(VoaBizBindInfo other){
  
		this.setDjId(other.getDjId());
  
		this.transaffairname= other.getTransaffairname();  
		this.orgcode= other.getOrgcode();  
		this.powerid= other.getPowerid();  
		this.powername= other.getPowername();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.createdate= other.getCreatedate();  
		this.itemtype= other.getItemtype();

	}
	
	public void copyNotNullProperty(VoaBizBindInfo other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTransaffairname() != null)
			this.transaffairname= other.getTransaffairname();  
		if( other.getOrgcode() != null)
			this.orgcode= other.getOrgcode();  
		if( other.getPowerid() != null)
			this.powerid= other.getPowerid();  
		if( other.getPowername() != null)
			this.powername= other.getPowername();  
		if( other.getBizstate() != null)
			this.bizstate= other.getBizstate();  
		if( other.getBiztype() != null)
			this.biztype= other.getBiztype();  
		if( other.getCreatedate() != null)
			this.createdate= other.getCreatedate();  
		if( other.getItemtype() != null)
			this.itemtype= other.getItemtype();

	}
	
	public void clearProperties(){
  
		this.transaffairname= null;  
		this.orgcode= null;  
		this.powerid= null;  
		this.powername= null;  
		this.bizstate= null;  
		this.biztype= null;  
		this.createdate= null;  
		this.itemtype= null;

	}
}
