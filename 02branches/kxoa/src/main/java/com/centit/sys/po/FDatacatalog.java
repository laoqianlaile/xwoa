package com.centit.sys.po;


/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class FDatacatalog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	private String catalogcode; //类别代码
	private String catalogname;//类别名称
	private String catalogstyle;//类别状态
	private String catalogtype;//类别形式
	private String catalogdesc;//类别描述
	private String fielddesc; //字典字段描述
	private String isupload;  //是否需要导入
	
	private String dictionarytype;

	// Constructors
	/** default constructor */
	public FDatacatalog() {
	    isupload = "0";
	}
	/** minimal constructor */
	public FDatacatalog(
		String catalogcode		
		,String  catalogname,String  catalogstyle,String  catalogtype,String isupload) {
	
	
		this.catalogcode = catalogcode;		
	
		this.catalogname= catalogname; 
		this.catalogstyle= catalogstyle; 
		this.catalogtype= catalogtype; 	
		this.isupload=isupload;	
		        }

/** full constructor */
	public FDatacatalog(
	 String catalogcode		
	,String  catalogname,String  catalogstyle,String  catalogtype,String  catalogdesc,String isupload,String  fielddesc) {
	
	
		this.catalogcode = catalogcode;		
	
		this.catalogname= catalogname;
		this.catalogstyle= catalogstyle;
		this.catalogtype= catalogtype;
		this.catalogdesc= catalogdesc;
		this.fielddesc= fielddesc;
		this.isupload=isupload;
	}
	

	public String getCatalogcode() {
		return this.catalogcode;
	}

	public void setCatalogcode(String catalogcode) {
		this.catalogcode = catalogcode;
	}
	
	
	// Property accessors
  
	public String getDictionarytype() {
        return dictionarytype;
    }
    public void setDictionarytype(String dictionarytype) {
        this.dictionarytype = dictionarytype;
    }
    public String getCatalogname() {
		return this.catalogname;
	}
	
	public void setCatalogname(String catalogname) {
		this.catalogname = catalogname;
	}
  
	public String getCatalogstyle() {
		return this.catalogstyle;
	}
	
	public void setCatalogstyle(String catalogstyle) {
		this.catalogstyle = catalogstyle;
	}
  
	public String getCatalogtype() {
		return this.catalogtype;
	}
	
	public void setCatalogtype(String catalogtype) {
		this.catalogtype = catalogtype;
	}
  
	public String getCatalogdesc() {
		return this.catalogdesc;
	}
	
	public void setCatalogdesc(String catalogdesc) {
		this.catalogdesc = catalogdesc;
	}
  
	public String getFielddesc() {
		return this.fielddesc;
	}
	
	public void setFielddesc(String fielddesc) {
		this.fielddesc = fielddesc;
	}
	
    public String getIsupload() {
        return isupload;
    }
    public void setIsupload(String isupload) {
        this.isupload = isupload;
    }

	public void copy(FDatacatalog other){
  
		this.catalogname= other.getCatalogname();  
		this.catalogstyle= other.getCatalogstyle();  
		this.catalogtype= other.getCatalogtype();  
		this.catalogdesc= other.getCatalogdesc();  
		this.fielddesc= other.getFielddesc();
		this.isupload=other.getIsupload();
		this.dictionarytype=other.getDictionarytype();
		   
	}
	

    public void copyNotNullProperty(FDatacatalog other){
  
		if( other.getCatalogname() != null)
			this.catalogname= other.getCatalogname();  
		if( other.getCatalogstyle() != null)
			this.catalogstyle= other.getCatalogstyle();  
		if( other.getCatalogtype() != null)
			this.catalogtype= other.getCatalogtype();  
		if( other.getCatalogdesc() != null)
			this.catalogdesc= other.getCatalogdesc();  
		if( other.getFielddesc() != null)
			this.fielddesc= other.getFielddesc();
		if(other.getIsupload()!=null)
		    this.isupload=other.getIsupload();
		if(other.getDictionarytype()!=null){
		    this.dictionarytype=other.getDictionarytype();
		}
	}
}
