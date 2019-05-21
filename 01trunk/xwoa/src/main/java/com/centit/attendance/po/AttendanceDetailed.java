package com.centit.attendance.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class AttendanceDetailed implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;               //考勤流水号

	private Date  createdate;          //创建时间
	private String  usercode;          //员工编号
	private String  username;          //员工姓名
	private String  unitcount;         //部门编号
	private String  unitname;          //部门名称
	private String  workdate;            //上班日期
	private String  saattendancetime;    //上班时间
	private String  xaattendancetime;    //下班时间
	private String  latein;            //迟到
	private String  earlyout;          //早退
	private String  overtimehours;     //工作时长
	private String remarks;            //备注信息
	
	private String attenstate;         //迟到早退,T迟到，F早退，Y迟到并早退
	private String amtime;    //上班时间(var)，String类型
	private String pmtime;    //下班时间(var)String类型
	private Long unitOrder;//额外字段，不做映射，业务展示需要
	private Long userOrder;//额外字段，不做映射，业务展示需要
	
	private String month;//额外字段，不做映射，业务展示需要
	

	// Constructors
	/** default constructor */
	public AttendanceDetailed() {
	}
	/** minimal constructor */
	public AttendanceDetailed(
		String djid		
		) {
	
	
		this.djid = djid;		
			
	}
	

public String getAttenstate() {
        return attenstate;
    }
    public void setAttenstate(String attenstate) {
        this.attenstate = attenstate;
    }
    public String getAmtime() {
        return amtime;
    }
    public void setAmtime(String amtime) {
        this.amtime = amtime;
    }
    public String getPmtime() {
        return pmtime;
    }
    public void setPmtime(String pmtime) {
        this.pmtime = pmtime;
    }
/** full constructor */
	public AttendanceDetailed(
	 String djid		
	,Date  createdate,String  usercode,String  username,String  unitcount,String  unitname,String  workdate,String  saattendancetime,String  xaattendancetime,String  latein,String  earlyout,String  overtimehours,String attenstate,String amtime,String pmtime,String remarks) {
	
	
		this.djid = djid;		
	
		this.createdate= createdate;
		this.usercode= usercode;
		this.username= username;
		this.unitcount= unitcount;
		this.unitname= unitname;
		this.workdate= workdate;
		this.saattendancetime= saattendancetime;
		this.xaattendancetime= xaattendancetime;
		this.latein= latein;
		this.earlyout= earlyout;
		this.overtimehours= overtimehours;		
		this.attenstate= attenstate;
		this.amtime= amtime;
		this.pmtime= pmtime;
		this.remarks=remarks;
	}
	

  
	public String getRemarks() {
    return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
	// Property accessors
  
	public Date getCreatedate() {
		return this.createdate;
	}
	
	public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
  
	public String getUsercode() {
		return this.usercode;
	}
	
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
  
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
  
	public String getUnitcount() {
		return this.unitcount;
	}
	
	public Long getUnitOrder() {
        return unitOrder;
    }
    public void setUnitOrder(Long unitOrder) {
        this.unitOrder = unitOrder;
    }
    public Long getUserOrder() {
        return userOrder;
    }
    public void setUserOrder(Long userOrder) {
        this.userOrder = userOrder;
    }
    public void setUnitcount(String unitcount) {
		this.unitcount = unitcount;
	}
  
	public String getUnitname() {
		return this.unitname;
	}
	
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
  
	public String getWorkdate() {
		return this.workdate;
	}
	
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
  
	public String getSaattendancetime() {
		return this.saattendancetime;
	}
	
	public void setSaattendancetime(String saattendancetime) {
		this.saattendancetime = saattendancetime;
	}
  
	public String getXaattendancetime() {
		return this.xaattendancetime;
	}
	
	public void setXaattendancetime(String xaattendancetime) {
		this.xaattendancetime = xaattendancetime;
	}
  
	public String getLatein() {
		return this.latein;
	}
	
	public void setLatein(String latein) {
		this.latein = latein;
	}
  
	public String getEarlyout() {
		return this.earlyout;
	}
	
	public void setEarlyout(String earlyout) {
		this.earlyout = earlyout;
	}
  
	public String getOvertimehours() {
		return this.overtimehours;
	}
	
	public void setOvertimehours(String overtimehours) {
		this.overtimehours = overtimehours;
	}



	public void copy(AttendanceDetailed other){
  
		this.setDjid(other.getDjid());
  
		this.createdate= other.getCreatedate();  
		this.usercode= other.getUsercode();  
		this.username= other.getUsername();  
		this.unitcount= other.getUnitcount();  
		this.unitname= other.getUnitname();  
		this.workdate= other.getWorkdate();  
		this.saattendancetime= other.getSaattendancetime();  
		this.xaattendancetime= other.getXaattendancetime();  
		this.latein= other.getLatein();  
		this.earlyout= other.getEarlyout();  
		this.overtimehours= other.getOvertimehours();
		this.attenstate= other.getAttenstate();  
		this.amtime= other.getAmtime();
		this.pmtime= other.getPmtime();  
		this.remarks= other.getRemarks();

	}
	
	public void copyNotNullProperty(AttendanceDetailed other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getCreatedate() != null)
			this.createdate= other.getCreatedate();  
		if( other.getUsercode() != null)
			this.usercode= other.getUsercode();  
		if( other.getUsername() != null)
			this.username= other.getUsername();  
		if( other.getUnitcount() != null)
			this.unitcount= other.getUnitcount();  
		if( other.getUnitname() != null)
			this.unitname= other.getUnitname();  
		if( other.getWorkdate() != null)
			this.workdate= other.getWorkdate();  
		if( other.getSaattendancetime() != null)
			this.saattendancetime= other.getSaattendancetime();  
		if( other.getXaattendancetime() != null)
			this.xaattendancetime= other.getXaattendancetime();  
		if( other.getLatein() != null)
			this.latein= other.getLatein();  
		if( other.getEarlyout() != null)
			this.earlyout= other.getEarlyout();  
		if( other.getOvertimehours() != null)
			this.overtimehours= other.getOvertimehours();
        if( other.getEarlyout() != null)
            this.attenstate= other.getAttenstate();  
        if( other.getEarlyout() != null)
            this.amtime= other.getAmtime();  
        if( other.getEarlyout() != null)
            this.pmtime= other.getPmtime();  
        if(other.getRemarks() != null)
            this.remarks= other.getRemarks();

	}
	
	public void clearProperties(){
  
		this.createdate= null;  
		this.usercode= null;  
		this.username= null;  
		this.unitcount= null;  
		this.unitname= null;  
		this.workdate= null;  
		this.saattendancetime= null;  
		this.xaattendancetime= null;  
		this.latein= null;  
		this.earlyout= null;  
		this.overtimehours= null;
		this.attenstate= null;  
		this.amtime= null;  
		this.pmtime= null;  
		this.remarks= null;

	}
}
