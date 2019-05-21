package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaFwdepmange implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  dispatchfiletype;
	private String  templateids;
	private String  unitcode;
	private String  creater;
	private Date  creatertime;
	
	//存放文件模板描述字段
    private String descript;//模版名称
	 
	// Constructors
	/** default constructor */
	public OaFwdepmange() {
	}
	/** minimal constructor */
	public OaFwdepmange(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaFwdepmange(
	 String no		
	,String  dispatchfiletype,String  templateids,String  unitcode,String  creater,Date  creatertime) {
	
	
		this.no = no;		
	
		this.dispatchfiletype= dispatchfiletype;
		this.templateids= templateids;
		this.unitcode= unitcode;
		this.creater= creater;
		this.creatertime= creatertime;		
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getDispatchfiletype() {
		return this.dispatchfiletype;
	}
	
	public void setDispatchfiletype(String dispatchfiletype) {
		this.dispatchfiletype = dispatchfiletype;
	}
  
	public String getTemplateids() {
		return this.templateids;
	}
	
	public void setTemplateids(String templateids) {
		this.templateids = templateids;
	}
  
	public String getUnitcode() {
		return this.unitcode;
	}
	
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
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



	public void copy(OaFwdepmange other){
  
		this.setNo(other.getNo());
  
		this.dispatchfiletype= other.getDispatchfiletype();  
		this.templateids= other.getTemplateids();  
		this.unitcode= other.getUnitcode();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();

	}
	
	public void copyNotNullProperty(OaFwdepmange other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getDispatchfiletype() != null)
			this.dispatchfiletype= other.getDispatchfiletype();  
		if( other.getTemplateids() != null)
			this.templateids= other.getTemplateids();  
		if( other.getUnitcode() != null)
			this.unitcode= other.getUnitcode();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();

	}
	
	public void clearProperties(){
  
		this.dispatchfiletype= null;  
		this.templateids= null;  
		this.unitcode= null;  
		this.creater= null;  
		this.creatertime= null;

	}
    public String getDescript() {
        return descript;
    }
    public void setDescript(String descript) {
        this.descript = descript;
    }
	
	
}
