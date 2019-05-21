package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VoaUnitArchiveIncomedoc implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  usercode;
	private String  incomedDocTitle;
	private Date  incomeDate;
	private String  acceptarchiveno;
	private String  incomeDeptName;
	private String  incomeDocNo;
	private String  unitcode;
	private Date  createtime;
	private Date  lastmodifytime;
	private String  updateuser;
	private String  incomeDeptType;
	private String  belongUnitcode;
	private String  incomeDocType;
	private String  itemSource;
	private String id;
	private String bizstate;//业务状态
	
    private String gwNature;//公文性质 上级部门01 本部门02

	// Constructors
	/** default constructor */
	public VoaUnitArchiveIncomedoc() {
	}
	/** minimal constructor */
	public VoaUnitArchiveIncomedoc(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	
	public VoaUnitArchiveIncomedoc(
	 String no		
	,String  usercode,String  incomedDocTitle,Date  incomeDate,String  acceptarchiveno,String  incomeDeptName,String  incomeDocNo,String  unitcode,Date  createtime,Date  lastmodifytime,String  updateuser,String  incomeDeptType,String  belongUnitcode,String  incomeDocType,String  itemSource,String id) {
	
	
		this.no = no;		
	
		this.usercode= usercode;
		this.incomedDocTitle= incomedDocTitle;
		this.incomeDate= incomeDate;
		this.acceptarchiveno= acceptarchiveno;
		this.incomeDeptName= incomeDeptName;
		this.incomeDocNo= incomeDocNo;
		this.unitcode= unitcode;
		this.createtime= createtime;
		this.lastmodifytime= lastmodifytime;
		this.updateuser= updateuser;
		this.incomeDeptType= incomeDeptType;
		this.belongUnitcode= belongUnitcode;
		this.incomeDocType= incomeDocType;
		this.itemSource= itemSource;	
		this.id =id;
	}
	

  
	public VoaUnitArchiveIncomedoc(String no, String usercode,
        String incomedDocTitle, Date incomeDate, String acceptarchiveno,
        String incomeDeptName, String incomeDocNo, String unitcode,
        Date createtime, Date lastmodifytime, String updateuser,
        String incomeDeptType, String belongUnitcode, String incomeDocType,
        String itemSource, String id, String bizstate) {
    super();
    this.no = no;
    this.usercode = usercode;
    this.incomedDocTitle = incomedDocTitle;
    this.incomeDate = incomeDate;
    this.acceptarchiveno = acceptarchiveno;
    this.incomeDeptName = incomeDeptName;
    this.incomeDocNo = incomeDocNo;
    this.unitcode = unitcode;
    this.createtime = createtime;
    this.lastmodifytime = lastmodifytime;
    this.updateuser = updateuser;
    this.incomeDeptType = incomeDeptType;
    this.belongUnitcode = belongUnitcode;
    this.incomeDocType = incomeDocType;
    this.itemSource = itemSource;
    this.id = id;
    this.bizstate = bizstate;
}
    public String getBizstate() {
    return bizstate;
}
public void setBizstate(String bizstate) {
    this.bizstate = bizstate;
}
    public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getUsercode() {
		return this.usercode;
	}
	
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
  
	public String getIncomedDocTitle() {
		return this.incomedDocTitle;
	}
	
	public void setIncomedDocTitle(String incomedDocTitle) {
		this.incomedDocTitle = incomedDocTitle;
	}
  
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getIncomeDate() {
		return this.incomeDate;
	}
	
	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}
  
	public String getAcceptarchiveno() {
		return this.acceptarchiveno;
	}
	
	public void setAcceptarchiveno(String acceptarchiveno) {
		this.acceptarchiveno = acceptarchiveno;
	}
  
	public String getIncomeDeptName() {
		return this.incomeDeptName;
	}
	
	public void setIncomeDeptName(String incomeDeptName) {
		this.incomeDeptName = incomeDeptName;
	}
  
	public String getIncomeDocNo() {
		return this.incomeDocNo;
	}
	
	public void setIncomeDocNo(String incomeDocNo) {
		this.incomeDocNo = incomeDocNo;
	}
  
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
  
	public Date getLastmodifytime() {
		return this.lastmodifytime;
	}
	
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
  
	public String getUpdateuser() {
		return this.updateuser;
	}
	
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
  
	public String getIncomeDeptType() {
		return this.incomeDeptType;
	}
	
	public void setIncomeDeptType(String incomeDeptType) {
		this.incomeDeptType = incomeDeptType;
	}
  
	public String getBelongUnitcode() {
		return this.belongUnitcode;
	}
	
	public void setBelongUnitcode(String belongUnitcode) {
		this.belongUnitcode = belongUnitcode;
	}
  
	public String getIncomeDocType() {
		return this.incomeDocType;
	}
	
	public void setIncomeDocType(String incomeDocType) {
		this.incomeDocType = incomeDocType;
	}
  
	public String getItemSource() {
		return this.itemSource;
	}
	
	public void setItemSource(String itemSource) {
		this.itemSource = itemSource;
	}



	public void copy(VoaUnitArchiveIncomedoc other){
  
		this.setNo(other.getNo());
  
		this.usercode= other.getUsercode();  
		this.incomedDocTitle= other.getIncomedDocTitle();  
		this.incomeDate= other.getIncomeDate();  
		this.acceptarchiveno= other.getAcceptarchiveno();  
		this.incomeDeptName= other.getIncomeDeptName();  
		this.incomeDocNo= other.getIncomeDocNo();  
		this.unitcode= other.getUnitcode();  
		this.createtime= other.getCreatetime();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.updateuser= other.getUpdateuser();  
		this.incomeDeptType= other.getIncomeDeptType();  
		this.belongUnitcode= other.getBelongUnitcode();  
		this.incomeDocType= other.getIncomeDocType();  
		this.itemSource= other.getItemSource();
		this.id=other.getId();
		this.bizstate=other.getBizstate();

	}
	
	public void copyNotNullProperty(VoaUnitArchiveIncomedoc other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getUsercode() != null)
			this.usercode= other.getUsercode();  
		if( other.getIncomedDocTitle() != null)
			this.incomedDocTitle= other.getIncomedDocTitle();  
		if( other.getIncomeDate() != null)
			this.incomeDate= other.getIncomeDate();  
		if( other.getAcceptarchiveno() != null)
			this.acceptarchiveno= other.getAcceptarchiveno();  
		if( other.getIncomeDeptName() != null)
			this.incomeDeptName= other.getIncomeDeptName();  
		if( other.getIncomeDocNo() != null)
			this.incomeDocNo= other.getIncomeDocNo();  
		if( other.getUnitcode() != null)
			this.unitcode= other.getUnitcode();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getLastmodifytime() != null)
			this.lastmodifytime= other.getLastmodifytime();  
		if( other.getUpdateuser() != null)
			this.updateuser= other.getUpdateuser();  
		if( other.getIncomeDeptType() != null)
			this.incomeDeptType= other.getIncomeDeptType();  
		if( other.getBelongUnitcode() != null)
			this.belongUnitcode= other.getBelongUnitcode();  
		if( other.getIncomeDocType() != null)
			this.incomeDocType= other.getIncomeDocType();  
		if( other.getItemSource() != null)
			this.itemSource= other.getItemSource();
		if( other.getId() != null)
            this.id= other.getId();
		if( other.getBizstate() != null)
		this.bizstate=other.getBizstate();
	}
	
	public void clearProperties(){
  
		this.usercode= null;  
		this.incomedDocTitle= null;  
		this.incomeDate= null;  
		this.acceptarchiveno= null;  
		this.incomeDeptName= null;  
		this.incomeDocNo= null;  
		this.unitcode= null;  
		this.createtime= null;  
		this.lastmodifytime= null;  
		this.updateuser= null;  
		this.incomeDeptType= null;  
		this.belongUnitcode= null;  
		this.incomeDocType= null;  
		this.itemSource= null;
		this.id=null;
		this.bizstate=null;
	}
    public String getGwNature() {
        return gwNature;
    }
    public void setGwNature(String gwNature) {
        this.gwNature = gwNature;
    }
	
}
