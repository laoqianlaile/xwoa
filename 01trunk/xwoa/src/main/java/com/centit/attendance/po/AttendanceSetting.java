package com.centit.attendance.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class AttendanceSetting implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djid;                   //考勤设置编号

	private String  attcode;               //考勤明细编号
	private Date  createdate;              //创建时间
	private Date  lastcodedate;            //最后修改时间
	private String  schedulingtype;        //排班类型
	private String  statetime;             //状态（上下班时间）
	private String  onetype;               //第一次登记类型
	private String  twotype;               //第二次登记类型
	private String  threetype;             //第三次登记类型
	private String  fourtype;              //第四次登记类型
	private String  fivetype;              //第五次登记类型
	private String  sixtype;               //第六次登记类型
	private Date  onetime;                 //第一次登记时间
	private Date  twotime;                 //第二次登记时间
	private Date  threetime;               //第三次登记时间
	private Date  fourtime;                //第四次登记时间
	private Date  fivetime;                //第五次登记时间
	private Date  sextime;                 //第六次登记时间
	private String  notworkweek;           //非工作日（星期）
	private String  notworkdate;           //非工作日（日期）
	private String  personnelcode;         //使用人员
	private Date  intermissiontime;        //间歇时间
	private String  state;                 //是否启用考勤
	private String  saattendancetime;        //上班时间
	private String  xaattendancetime;        //下班时间

	// Constructors
	/** default constructor */
	public AttendanceSetting() {
	}
	/** minimal constructor */
	public AttendanceSetting(
		String djid		
		) {
	
	
		this.djid = djid;		
			
	}

/** full constructor */
	public AttendanceSetting(
	 String djid		
	,String  attcode,Date  createdate,Date  lastcodedate,String  schedulingtype,String  statetime,String  onetype,String  twotype,String  threetype,String  fourtype,String  fivetype,String  sixtype,Date  onetime,Date  twotime,Date  threetime,Date  fourtime,Date  fivetime,Date  sextime,String  notworkweek,String  notworkdate,String  personnelcode,Date  intermissiontime,String  state,String  saattendancetime,String  xaattendancetime) {
	
	
		this.djid = djid;		
	
		this.attcode= attcode;
		this.createdate= createdate;
		this.lastcodedate= lastcodedate;
		this.schedulingtype= schedulingtype;
		this.statetime= statetime;
		this.onetype= onetype;
		this.twotype= twotype;
		this.threetype= threetype;
		this.fourtype= fourtype;
		this.fivetype= fivetype;
		this.sixtype= sixtype;
		this.onetime= onetime;
		this.twotime= twotime;
		this.threetime= threetime;
		this.fourtime= fourtime;
		this.fivetime= fivetime;
		this.sextime= sextime;
		this.notworkweek= notworkweek;
		this.notworkdate= notworkdate;
		this.personnelcode= personnelcode;
		this.intermissiontime= intermissiontime;
		this.state= state;
		this.saattendancetime= saattendancetime;
		this.xaattendancetime= xaattendancetime;		
	}
	

  
	public String getDjid() {
		return this.djid;
	}

	public void setDjid(String djid) {
		this.djid = djid;
	}
	// Property accessors
  
	public String getAttcode() {
		return this.attcode;
	}
	
	public void setAttcode(String attcode) {
		this.attcode = attcode;
	}
  
	public Date getCreatedate() {
		return this.createdate;
	}
	
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
  
	public Date getLastcodedate() {
		return this.lastcodedate;
	}
	
	public void setLastcodedate(Date lastcodedate) {
		this.lastcodedate = lastcodedate;
	}
  
	public String getSchedulingtype() {
		return this.schedulingtype;
	}
	
	public void setSchedulingtype(String schedulingtype) {
		this.schedulingtype = schedulingtype;
	}
  
	public String getStatetime() {
		return this.statetime;
	}
	
	public void setStatetime(String statetime) {
		this.statetime = statetime;
	}
  
	public String getOnetype() {
		return this.onetype;
	}
	
	public void setOnetype(String onetype) {
		this.onetype = onetype;
	}
  
	public String getTwotype() {
		return this.twotype;
	}
	
	public void setTwotype(String twotype) {
		this.twotype = twotype;
	}
  
	public String getThreetype() {
		return this.threetype;
	}
	
	public void setThreetype(String threetype) {
		this.threetype = threetype;
	}
  
	public String getFourtype() {
		return this.fourtype;
	}
	
	public void setFourtype(String fourtype) {
		this.fourtype = fourtype;
	}
  
	public String getFivetype() {
		return this.fivetype;
	}
	
	public void setFivetype(String fivetype) {
		this.fivetype = fivetype;
	}
  
	public String getSixtype() {
		return this.sixtype;
	}
	
	public void setSixtype(String sixtype) {
		this.sixtype = sixtype;
	}
  
	public Date getOnetime() {
		return this.onetime;
	}
	
	public void setOnetime(Date onetime) {
		this.onetime = onetime;
	}
  
	public Date getTwotime() {
		return this.twotime;
	}
	
	public void setTwotime(Date twotime) {
		this.twotime = twotime;
	}
  
	public Date getThreetime() {
		return this.threetime;
	}
	
	public void setThreetime(Date threetime) {
		this.threetime = threetime;
	}
  
	public Date getFourtime() {
		return this.fourtime;
	}
	
	public void setFourtime(Date fourtime) {
		this.fourtime = fourtime;
	}
  
	public Date getFivetime() {
		return this.fivetime;
	}
	
	public void setFivetime(Date fivetime) {
		this.fivetime = fivetime;
	}
  
	public Date getSextime() {
		return this.sextime;
	}
	
	public void setSextime(Date sextime) {
		this.sextime = sextime;
	}
  
	public String getNotworkweek() {
		return this.notworkweek;
	}
	
	public void setNotworkweek(String notworkweek) {
		this.notworkweek = notworkweek;
	}
  
	public String getNotworkdate() {
		return this.notworkdate;
	}
	
	public void setNotworkdate(String notworkdate) {
		this.notworkdate = notworkdate;
	}
  
	public String getPersonnelcode() {
		return this.personnelcode;
	}
	
	public void setPersonnelcode(String personnelcode) {
		this.personnelcode = personnelcode;
	}
  
	public Date getIntermissiontime() {
		return this.intermissiontime;
	}
	
	public void setIntermissiontime(Date intermissiontime) {
		this.intermissiontime = intermissiontime;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
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



	public void copy(AttendanceSetting other){
  
		this.setDjid(other.getDjid());
  
		this.attcode= other.getAttcode();  
		this.createdate= other.getCreatedate();  
		this.lastcodedate= other.getLastcodedate();  
		this.schedulingtype= other.getSchedulingtype();  
		this.statetime= other.getStatetime();  
		this.onetype= other.getOnetype();  
		this.twotype= other.getTwotype();  
		this.threetype= other.getThreetype();  
		this.fourtype= other.getFourtype();  
		this.fivetype= other.getFivetype();  
		this.sixtype= other.getSixtype();  
		this.onetime= other.getOnetime();  
		this.twotime= other.getTwotime();  
		this.threetime= other.getThreetime();  
		this.fourtime= other.getFourtime();  
		this.fivetime= other.getFivetime();  
		this.sextime= other.getSextime();  
		this.notworkweek= other.getNotworkweek();  
		this.notworkdate= other.getNotworkdate();  
		this.personnelcode= other.getPersonnelcode();  
		this.intermissiontime= other.getIntermissiontime();  
		this.state= other.getState();  
		this.saattendancetime= other.getSaattendancetime();  
		this.xaattendancetime= other.getXaattendancetime();

	}
	
	public void copyNotNullProperty(AttendanceSetting other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());
  
		if( other.getAttcode() != null)
			this.attcode= other.getAttcode();  
		if( other.getCreatedate() != null)
			this.createdate= other.getCreatedate();  
		if( other.getLastcodedate() != null)
			this.lastcodedate= other.getLastcodedate();  
		if( other.getSchedulingtype() != null)
			this.schedulingtype= other.getSchedulingtype();  
		if( other.getStatetime() != null)
			this.statetime= other.getStatetime();  
		if( other.getOnetype() != null)
			this.onetype= other.getOnetype();  
		if( other.getTwotype() != null)
			this.twotype= other.getTwotype();  
		if( other.getThreetype() != null)
			this.threetype= other.getThreetype();  
		if( other.getFourtype() != null)
			this.fourtype= other.getFourtype();  
		if( other.getFivetype() != null)
			this.fivetype= other.getFivetype();  
		if( other.getSixtype() != null)
			this.sixtype= other.getSixtype();  
		if( other.getOnetime() != null)
			this.onetime= other.getOnetime();  
		if( other.getTwotime() != null)
			this.twotime= other.getTwotime();  
		if( other.getThreetime() != null)
			this.threetime= other.getThreetime();  
		if( other.getFourtime() != null)
			this.fourtime= other.getFourtime();  
		if( other.getFivetime() != null)
			this.fivetime= other.getFivetime();  
		if( other.getSextime() != null)
			this.sextime= other.getSextime();  
		if( other.getNotworkweek() != null)
			this.notworkweek= other.getNotworkweek();  
		if( other.getNotworkdate() != null)
			this.notworkdate= other.getNotworkdate();  
		if( other.getPersonnelcode() != null)
			this.personnelcode= other.getPersonnelcode();  
		if( other.getIntermissiontime() != null)
			this.intermissiontime= other.getIntermissiontime();  
		if( other.getState() != null)
			this.state= other.getState();  
		if( other.getSaattendancetime() != null)
			this.saattendancetime= other.getSaattendancetime();  
		if( other.getXaattendancetime() != null)
			this.xaattendancetime= other.getXaattendancetime();

	}
	
	public void clearProperties(){
  
		this.attcode= null;  
		this.createdate= null;  
		this.lastcodedate= null;  
		this.schedulingtype= null;  
		this.statetime= null;  
		this.onetype= null;  
		this.twotype= null;  
		this.threetype= null;  
		this.fourtype= null;  
		this.fivetype= null;  
		this.sixtype= null;  
		this.onetime= null;  
		this.twotime= null;  
		this.threetime= null;  
		this.fourtime= null;  
		this.fivetime= null;  
		this.sextime= null;  
		this.notworkweek= null;  
		this.notworkdate= null;  
		this.personnelcode= null;  
		this.intermissiontime= null;  
		this.state= null;  
		this.saattendancetime= null;  
		this.xaattendancetime= null;

	}
}
