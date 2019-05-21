package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaBudgetstravel implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  title;
	private String  depno;
	private String  address;
	private String  meetingNo;
	private Date  begTime;
	private Date  endTime;
	private String  creater;
	private Date  createtime;
	private String  trans;
	private String  transStandard;
	private String  amountTrans;
	private String  accomStandard;
	private String  amountAccom;
	private String  standardFood;
	private String  amountFood;
	private String  miscellaneousStandard;
	private String  amountMiscellaneous;
	private String  otStandard;
	private String  amountOther;
	private String  otStandard2;
	private String  amountOther2;
	private String  amountall;
	private String  flowcode;
	private String  optid;
	private String  solvenote;
	private Long  flowInstId;
	private String  bizstate;
	private String  biztype;
	private String  solvestatus;
	private Date  solvetime;
	
	private OptBaseInfo optBaseInfo;

	// Constructors
	/** default constructor */
	public OaBudgetstravel() {
	}
	/** minimal constructor */
	public OaBudgetstravel(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaBudgetstravel(
	 String djId		
	,String  title,String  depno,String  address,String  meetingNo,Date  begTime,Date  endTime,String  creater,Date  createtime,String  trans,String  transStandard,String  amountTrans,String  accomStandard,String  amountAccom,String  standardFood,String  amountFood,String  miscellaneousStandard,String  amountMiscellaneous,String  otStandard,String  amountOther,String  otStandard2,String  amountOther2,String  amountall,String  flowCode,String  optid,String  solvenote,Long  flowInstId,String  bizstate,String  biztype,String  solvestatus,Date  solvetime) {
	
	
		this.djId = djId;		
	
		this.title= title;
		this.depno= depno;
		this.address= address;
		this.meetingNo= meetingNo;
		this.begTime= begTime;
		this.endTime= endTime;
		this.creater= creater;
		this.createtime= createtime;
		this.trans= trans;
		this.transStandard= transStandard;
		this.amountTrans= amountTrans;
		this.accomStandard= accomStandard;
		this.amountAccom= amountAccom;
		this.standardFood= standardFood;
		this.amountFood= amountFood;
		this.miscellaneousStandard= miscellaneousStandard;
		this.amountMiscellaneous= amountMiscellaneous;
		this.otStandard= otStandard;
		this.amountOther= amountOther;
		this.otStandard2= otStandard2;
		this.amountOther2= amountOther2;
		this.amountall= amountall;
		this.flowcode= flowCode;
		this.optid= optid;
		this.solvenote= solvenote;
		this.flowInstId= flowInstId;
		this.bizstate= bizstate;
		this.biztype= biztype;
		this.solvestatus= solvestatus;
		this.solvetime= solvetime;		
	}
	

  
	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}
	// Property accessors
  
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
  
	public String getDepno() {
		return this.depno;
	}
	
	public void setDepno(String depno) {
		this.depno = depno;
	}
  
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
  
	public String getMeetingNo() {
		return this.meetingNo;
	}
	
	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}
  
	public Date getBegTime() {
		return this.begTime;
	}
	
	public void setBegTime(Date begTime) {
		this.begTime = begTime;
	}
  
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public String getTrans() {
		return this.trans;
	}
	
	public void setTrans(String trans) {
		this.trans = trans;
	}
  
	public String getTransStandard() {
		return this.transStandard;
	}
	
	public void setTransStandard(String transStandard) {
		this.transStandard = transStandard;
	}
  
	public String getAmountTrans() {
		return this.amountTrans;
	}
	
	public void setAmountTrans(String amountTrans) {
		this.amountTrans = amountTrans;
	}
  
	public String getAccomStandard() {
		return this.accomStandard;
	}
	
	public void setAccomStandard(String accomStandard) {
		this.accomStandard = accomStandard;
	}
  
	public String getAmountAccom() {
		return this.amountAccom;
	}
	
	public void setAmountAccom(String amountAccom) {
		this.amountAccom = amountAccom;
	}
  
	public String getStandardFood() {
		return this.standardFood;
	}
	
	public void setStandardFood(String standardFood) {
		this.standardFood = standardFood;
	}
  
	public String getAmountFood() {
		return this.amountFood;
	}
	
	public void setAmountFood(String amountFood) {
		this.amountFood = amountFood;
	}
  
	public String getMiscellaneousStandard() {
		return this.miscellaneousStandard;
	}
	
	public void setMiscellaneousStandard(String miscellaneousStandard) {
		this.miscellaneousStandard = miscellaneousStandard;
	}
  
	public String getAmountMiscellaneous() {
		return this.amountMiscellaneous;
	}
	
	public void setAmountMiscellaneous(String amountMiscellaneous) {
		this.amountMiscellaneous = amountMiscellaneous;
	}
  
	public String getOtStandard() {
		return this.otStandard;
	}
	
	public void setOtStandard(String otStandard) {
		this.otStandard = otStandard;
	}
  
	public String getAmountOther() {
		return this.amountOther;
	}
	
	public void setAmountOther(String amountOther) {
		this.amountOther = amountOther;
	}
  
	public String getOtStandard2() {
		return this.otStandard2;
	}
	
	public void setOtStandard2(String otStandard2) {
		this.otStandard2 = otStandard2;
	}
  
	public String getAmountOther2() {
		return this.amountOther2;
	}
	
	public void setAmountOther2(String amountOther2) {
		this.amountOther2 = amountOther2;
	}
  
	public String getAmountall() {
		return this.amountall;
	}
	
	public void setAmountall(String amountall) {
		this.amountall = amountall;
	}
  
  
	public String getFlowcode() {
        return flowcode;
    }
    public void setFlowcode(String flowcode) {
        this.flowcode = flowcode;
    }
    public String getOptid() {
		return this.optid;
	}
	
	public void setOptid(String optid) {
		this.optid = optid;
	}
  
	public String getSolvenote() {
		return this.solvenote;
	}
	
	public void setSolvenote(String solvenote) {
		this.solvenote = solvenote;
	}
  
	public Long getFlowInstId() {
		return this.flowInstId;
	}
	
	public void setFlowInstId(Long flowInstId) {
		this.flowInstId = flowInstId;
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
  
	public String getSolvestatus() {
		return this.solvestatus;
	}
	
	public void setSolvestatus(String solvestatus) {
		this.solvestatus = solvestatus;
	}
  
	public Date getSolvetime() {
		return this.solvetime;
	}
	
	public void setSolvetime(Date solvetime) {
		this.solvetime = solvetime;
	}

	


	public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }
    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
    public void copy(OaBudgetstravel other){
  
		this.setDjId(other.getDjId());
  
		this.title= other.getTitle();  
		this.depno= other.getDepno();  
		this.address= other.getAddress();  
		this.meetingNo= other.getMeetingNo();  
		this.begTime= other.getBegTime();  
		this.endTime= other.getEndTime();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.trans= other.getTrans();  
		this.transStandard= other.getTransStandard();  
		this.amountTrans= other.getAmountTrans();  
		this.accomStandard= other.getAccomStandard();  
		this.amountAccom= other.getAmountAccom();  
		this.standardFood= other.getStandardFood();  
		this.amountFood= other.getAmountFood();  
		this.miscellaneousStandard= other.getMiscellaneousStandard();  
		this.amountMiscellaneous= other.getAmountMiscellaneous();  
		this.otStandard= other.getOtStandard();  
		this.amountOther= other.getAmountOther();  
		this.otStandard2= other.getOtStandard2();  
		this.amountOther2= other.getAmountOther2();  
		this.amountall= other.getAmountall();  
		this.flowcode= other.getFlowcode();  
		this.optid= other.getOptid();  
		this.solvenote= other.getSolvenote();  
		this.flowInstId= other.getFlowInstId();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();

	}
	
	public void copyNotNullProperty(OaBudgetstravel other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getDepno() != null)
			this.depno= other.getDepno();  
		if( other.getAddress() != null)
			this.address= other.getAddress();  
		if( other.getMeetingNo() != null)
			this.meetingNo= other.getMeetingNo();  
		if( other.getBegTime() != null)
			this.begTime= other.getBegTime();  
		if( other.getEndTime() != null)
			this.endTime= other.getEndTime();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getTrans() != null)
			this.trans= other.getTrans();  
		if( other.getTransStandard() != null)
			this.transStandard= other.getTransStandard();  
		if( other.getAmountTrans() != null)
			this.amountTrans= other.getAmountTrans();  
		if( other.getAccomStandard() != null)
			this.accomStandard= other.getAccomStandard();  
		if( other.getAmountAccom() != null)
			this.amountAccom= other.getAmountAccom();  
		if( other.getStandardFood() != null)
			this.standardFood= other.getStandardFood();  
		if( other.getAmountFood() != null)
			this.amountFood= other.getAmountFood();  
		if( other.getMiscellaneousStandard() != null)
			this.miscellaneousStandard= other.getMiscellaneousStandard();  
		if( other.getAmountMiscellaneous() != null)
			this.amountMiscellaneous= other.getAmountMiscellaneous();  
		if( other.getOtStandard() != null)
			this.otStandard= other.getOtStandard();  
		if( other.getAmountOther() != null)
			this.amountOther= other.getAmountOther();  
		if( other.getOtStandard2() != null)
			this.otStandard2= other.getOtStandard2();  
		if( other.getAmountOther2() != null)
			this.amountOther2= other.getAmountOther2();  
		if( other.getAmountall() != null)
			this.amountall= other.getAmountall();  
		if( other.getFlowcode() != null)
			this.flowcode= other.getFlowcode();  
		if( other.getOptid() != null)
			this.optid= other.getOptid();  
		if( other.getSolvenote() != null)
			this.solvenote= other.getSolvenote();  
		if( other.getFlowInstId() != null)
			this.flowInstId= other.getFlowInstId();  
		if( other.getBizstate() != null)
			this.bizstate= other.getBizstate();  
		if( other.getBiztype() != null)
			this.biztype= other.getBiztype();  
		if( other.getSolvestatus() != null)
			this.solvestatus= other.getSolvestatus();  
		if( other.getSolvetime() != null)
			this.solvetime= other.getSolvetime();

	}
	
	public void clearProperties(){
  
		this.title= null;  
		this.depno= null;  
		this.address= null;  
		this.meetingNo= null;  
		this.begTime= null;  
		this.endTime= null;  
		this.creater= null;  
		this.createtime= null;  
		this.trans= null;  
		this.transStandard= null;  
		this.amountTrans= null;  
		this.accomStandard= null;  
		this.amountAccom= null;  
		this.standardFood= null;  
		this.amountFood= null;  
		this.miscellaneousStandard= null;  
		this.amountMiscellaneous= null;  
		this.otStandard= null;  
		this.amountOther= null;  
		this.otStandard2= null;  
		this.amountOther2= null;  
		this.amountall= null;  
		this.flowcode= null;  
		this.optid= null;  
		this.solvenote= null;  
		this.flowInstId= null;  
		this.bizstate= null;  
		this.biztype= null;  
		this.solvestatus= null;  
		this.solvetime= null;

	}
}
