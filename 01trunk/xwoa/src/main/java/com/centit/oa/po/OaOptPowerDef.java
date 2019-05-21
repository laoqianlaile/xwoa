package com.centit.oa.po;



/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class OaOptPowerDef implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String optcode;//操作代码
	private String optname;	//操作名称	
	private String optid;	//所属业务DOCXZ 行政级别  OPT 具体业务   自行定义 '
	private String optmethod;//操作方法	
	private String optdesc;	//操作说明
	private String optlevel;//权限对象主键 可为 行政级别 可为具体djid';

	/**
     * 表单操作数据
     */
    private String optcodelist;
    
	/** default constructor */
	public OaOptPowerDef() {
	}



	public OaOptPowerDef(
	 String optcode	,String  optname,String  optid,String  optmethod,String  optdesc) {
		this.optcode = optcode;		
	
		this.optname= optname;
		this.optid= optid;
		this.optmethod= optmethod;
		this.optdesc= optdesc;		
	}
	

	/** full constructor */
	public OaOptPowerDef(String optcode, String optname, String optid, String optmethod,
        String optdesc, String optlevel) {
    this.optcode = optcode;
    this.optname = optname;
    this.optid = optid;
    this.optmethod = optmethod;
    this.optdesc = optdesc;
    this.optlevel = optlevel;
}
    public String getOptcode() {
		return this.optcode;
	}

	public void setOptcode(String optcode) {
		this.optcode = optcode;
	}
	
	public String toString() {
		return this.optname;
	}
	
	// Property accessors
  
	public String getOptname() {
		return this.optname;
	}
	
	public void setOptname(String optname) {
		this.optname = optname;
	}
  
	public String getOptid() {
		return this.optid;
	}
	
	public void setOptid(String optid) {
		this.optid = optid;
	}
  
	public String getOptmethod() {
		return this.optmethod;
	}
	
	public void setOptmethod(String optmethod) {
		this.optmethod = optmethod;
	}
  
	public String getOptdesc() {
		return this.optdesc;
	}
	
	public void setOptdesc(String optdesc) {
		this.optdesc = optdesc;
	}


	
	public void copy(OaOptPowerDef other){
  
		this.optname= other.getOptname();  
		this.optid= other.getOptid();  
		this.optmethod= other.getOptmethod();  
		this.optdesc= other.getOptdesc();
		this.optlevel = other.getOptlevel();
	}
	
	public void copyNotNullProperty(OaOptPowerDef other){
  
		if( other.getOptname() != null)
			this.optname= other.getOptname();  
		if( other.getOptid() != null)
			this.optid= other.getOptid();  
		if( other.getOptmethod() != null)
			this.optmethod= other.getOptmethod();  
		if( other.getOptdesc() != null)
			this.optdesc= other.getOptdesc();
		if( other.getOptlevel() != null)
			this.optlevel= other.getOptlevel();
	}
    public String getOptlevel() {
        return optlevel;
    }
    public void setOptlevel(String optlevel) {
        this.optlevel = optlevel;
    }



    public String getOptcodelist() {
        return optcodelist;
    }



    public void setOptcodelist(String optcodelist) {
        this.optcodelist = optcodelist;
    }

}
