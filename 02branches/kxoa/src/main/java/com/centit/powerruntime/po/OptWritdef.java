package com.centit.powerruntime.po;


/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OptWritdef implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long writid;

	private String  temptype;
	private String  recordid;
	private String  writcode;
	private String  initvalue;
	private String  isinuse;
	private String  remark;

	// Constructors
	/** default constructor */
	public OptWritdef() {
	}
	/** minimal constructor */
	public OptWritdef(
		Long writid		
		) {
	
	
		this.writid = writid;		
			
	}

/** full constructor */
	public OptWritdef(
	 Long writid		
	,String  temptype,String  recordid,String  writcode,String  initvalue,String  isinuse,String  remark) {
	
	
		this.writid = writid;		
	
		this.temptype= temptype;
		this.recordid= recordid;
		this.writcode= writcode;
		this.initvalue= initvalue;
		this.isinuse= isinuse;
		this.remark= remark;		
	}
	

  
	public Long getWritid() {
		return this.writid;
	}

	public void setWritid(Long writid) {
		this.writid = writid;
	}
	// Property accessors
  
	public String getTemptype() {
		return this.temptype;
	}
	
	public void setTemptype(String temptype) {
		this.temptype = temptype;
	}
  
	public String getRecordid() {
		return this.recordid;
	}
	
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
  
	public String getWritcode() {
		return this.writcode;
	}
	
	public void setWritcode(String writcode) {
		this.writcode = writcode;
	}
  
	public String getInitvalue() {
		return this.initvalue;
	}
	
	public void setInitvalue(String initvalue) {
		this.initvalue = initvalue;
	}
  
	public String getIsinuse() {
		return this.isinuse;
	}
	
	public void setIsinuse(String isinuse) {
		this.isinuse = isinuse;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}



	public void copy(OptWritdef other){
  
		this.setWritid(other.getWritid());
  
		this.temptype= other.getTemptype();  
		this.recordid= other.getRecordid();  
		this.writcode= other.getWritcode();  
		this.initvalue= other.getInitvalue();  
		this.isinuse= other.getIsinuse();  
		this.remark= other.getRemark();

	}
	
	public void copyNotNullProperty(OptWritdef other){
  
	if( other.getWritid() != null)
		this.setWritid(other.getWritid());
  
		if( other.getTemptype() != null)
			this.temptype= other.getTemptype();  
		if( other.getRecordid() != null)
			this.recordid= other.getRecordid();  
		if( other.getWritcode() != null)
			this.writcode= other.getWritcode();  
		if( other.getInitvalue() != null)
			this.initvalue= other.getInitvalue();  
		if( other.getIsinuse() != null)
			this.isinuse= other.getIsinuse();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();

	}
	
	public void clearProperties(){
  
		this.temptype= null;  
		this.recordid= null;  
		this.writcode= null;  
		this.initvalue= null;  
		this.isinuse= null;  
		this.remark= null;

	}
}
