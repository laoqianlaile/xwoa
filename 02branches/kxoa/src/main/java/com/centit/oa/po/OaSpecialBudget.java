package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaSpecialBudget implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  title;
	private String  depno;
	private String  applyNo;
	private String  meetingNo;
	private String  meettype;
	private String  outerPersions;
	private Long  meetingPersionsNum;
	private Date  begTime;
	private Date  endTime;
	private String  creater;
	private Date  createtime;
	private String  standard;
	private String  amount;
	private String  standard2;
	private String  amount2;
	private String  standard3;
	private String  amount3;
	private String  standard4;
	private String  amount4;
	private String  standard5;
	private String  amount5;
	private String  standard6;
	private String  amount6;
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
	public OaSpecialBudget() {
	}
	/** minimal constructor */
	public OaSpecialBudget(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	
	

  
	public String getDjId() {
		return this.djId;
	}

	
    
    public OaSpecialBudget(String djId, String title, String depno, String applyNo,
        String meetingNo, String meettype, String outerPersions,
        Long meetingPersionsNum, Date begTime, Date endTime, String creater,
        Date createtime, String standard, String amount, String standard2,
        String amount2, String standard3, String amount3, String standard4,
        String amount4, String standard5, String amount5, String standard6,
        String amount6, String amountall, String flowcode, String optid,
        String solvenote, Long flowInstId, String bizstate, String biztype,
        String solvestatus, Date solvetime) {
    super();
    this.djId = djId;
    this.title = title;
    this.depno = depno;
    this.applyNo = applyNo;
    this.meetingNo = meetingNo;
    this.meettype = meettype;
    this.outerPersions = outerPersions;
    this.meetingPersionsNum = meetingPersionsNum;
    this.begTime = begTime;
    this.endTime = endTime;
    this.creater = creater;
    this.createtime = createtime;
    this.standard = standard;
    this.amount = amount;
    this.standard2 = standard2;
    this.amount2 = amount2;
    this.standard3 = standard3;
    this.amount3 = amount3;
    this.standard4 = standard4;
    this.amount4 = amount4;
    this.standard5 = standard5;
    this.amount5 = amount5;
    this.standard6 = standard6;
    this.amount6 = amount6;
    this.amountall = amountall;
    this.flowcode = flowcode;
    this.optid = optid;
    this.solvenote = solvenote;
    this.flowInstId = flowInstId;
    this.bizstate = bizstate;
    this.biztype = biztype;
    this.solvestatus = solvestatus;
    this.solvetime = solvetime;
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
  
	public String getApplyNo() {
		return this.applyNo;
	}
	
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
  
	public String getMeetingNo() {
		return this.meetingNo;
	}
	
	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}
  
	public String getMeettype() {
		return this.meettype;
	}
	
	public void setMeettype(String meettype) {
		this.meettype = meettype;
	}
  
	public String getOuterPersions() {
		return this.outerPersions;
	}
	
	public void setOuterPersions(String outerPersions) {
		this.outerPersions = outerPersions;
	}
  
	public Long getMeetingPersionsNum() {
		return this.meetingPersionsNum;
	}
	
	public void setMeetingPersionsNum(Long meetingPersionsNum) {
		this.meetingPersionsNum = meetingPersionsNum;
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
  
	public String getStandard() {
		return this.standard;
	}
	
	public void setStandard(String standard) {
		this.standard = standard;
	}
  
	public String getAmount() {
		return this.amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
  
	public String getStandard2() {
		return this.standard2;
	}
	
	public void setStandard2(String standard2) {
		this.standard2 = standard2;
	}
 
  
	public String getStandard3() {
		return this.standard3;
	}
	
	public void setStandard3(String standard3) {
		this.standard3 = standard3;
	}
  

  
	public String getStandard4() {
		return this.standard4;
	}
	
	public void setStandard4(String standard4) {
		this.standard4 = standard4;
	}
  

  
	public String getStandard5() {
		return this.standard5;
	}
	
	public void setStandard5(String standard5) {
		this.standard5 = standard5;
	}
  

  
	public String getStandard6() {
		return this.standard6;
	}
	
	public void setStandard6(String standard6) {
		this.standard6 = standard6;
	}
  
	public String getAmount6() {
		return this.amount6;
	}
	
	public void setAmount6(String amount6) {
		this.amount6 = amount6;
	}
  
	public String getAmountall() {
		return this.amountall;
	}
	
	public void setAmountall(String amountall) {
		this.amountall = amountall;
	}
  
	public String getFlowcode() {
		return this.flowcode;
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



	public String getAmount2() {
        return amount2;
    }
    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }
    public String getAmount3() {
        return amount3;
    }
    public void setAmount3(String amount3) {
        this.amount3 = amount3;
    }
    public String getAmount4() {
        return amount4;
    }
    public void setAmount4(String amount4) {
        this.amount4 = amount4;
    }
    public String getAmount5() {
        return amount5;
    }
    public void setAmount5(String amount5) {
        this.amount5 = amount5;
    }
    
    
    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }
    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
    public void copy(OaSpecialBudget other){
  
		this.setDjId(other.getDjId());
  
		this.title= other.getTitle();  
		this.depno= other.getDepno();  
		this.applyNo= other.getApplyNo();  
		this.meetingNo= other.getMeetingNo();  
		this.meettype= other.getMeettype();  
		this.outerPersions= other.getOuterPersions();  
		this.meetingPersionsNum= other.getMeetingPersionsNum();  
		this.begTime= other.getBegTime();  
		this.endTime= other.getEndTime();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.standard= other.getStandard();  
		this.amount= other.getAmount();  
		this.standard2= other.getStandard2();  
		this.amount2= other.getAmount2();  
		this.standard3= other.getStandard3();  
		this.amount3= other.getAmount3();  
		this.standard4= other.getStandard4();  
		this.amount4= other.getAmount4();  
		this.standard5= other.getStandard5();  
		this.amount5= other.getAmount5();  
		this.standard6= other.getStandard6();  
		this.amount6= other.getAmount6();  
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
	
	public void copyNotNullProperty(OaSpecialBudget other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getDepno() != null)
			this.depno= other.getDepno();  
		if( other.getApplyNo() != null)
			this.applyNo= other.getApplyNo();  
		if( other.getMeetingNo() != null)
			this.meetingNo= other.getMeetingNo();  
		if( other.getMeettype() != null)
			this.meettype= other.getMeettype();  
		if( other.getOuterPersions() != null)
			this.outerPersions= other.getOuterPersions();  
		if( other.getMeetingPersionsNum() != null)
			this.meetingPersionsNum= other.getMeetingPersionsNum();  
		if( other.getBegTime() != null)
			this.begTime= other.getBegTime();  
		if( other.getEndTime() != null)
			this.endTime= other.getEndTime();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getStandard() != null)
			this.standard= other.getStandard();  
		if( other.getAmount() != null)
			this.amount= other.getAmount();  
		if( other.getStandard2() != null)
			this.standard2= other.getStandard2();  
		if( other.getAmount2() != null)
			this.amount2= other.getAmount2();  
		if( other.getStandard3() != null)
			this.standard3= other.getStandard3();  
		if( other.getAmount3() != null)
			this.amount3= other.getAmount3();  
		if( other.getStandard4() != null)
			this.standard4= other.getStandard4();  
		if( other.getAmount4() != null)
			this.amount4= other.getAmount4();  
		if( other.getStandard5() != null)
			this.standard5= other.getStandard5();  
		if( other.getAmount5() != null)
			this.amount5= other.getAmount5();  
		if( other.getStandard6() != null)
			this.standard6= other.getStandard6();  
		if( other.getAmount6() != null)
			this.amount6= other.getAmount6();  
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
		this.applyNo= null;  
		this.meetingNo= null;  
		this.meettype= null;  
		this.outerPersions= null;  
		this.meetingPersionsNum= null;  
		this.begTime= null;  
		this.endTime= null;  
		this.creater= null;  
		this.createtime= null;  
		this.standard= null;  
		this.amount= null;  
		this.standard2= null;  
		this.amount2= null;  
		this.standard3= null;  
		this.amount3= null;  
		this.standard4= null;  
		this.amount4= null;  
		this.standard5= null;  
		this.amount5= null;  
		this.standard6= null;  
		this.amount6= null;  
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
