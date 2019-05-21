package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaMeetroomApply implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }

    private String  applyNo;
	private String  depno;
	private String  creater;
	private Date  createtime;
	private String  meetingNo;
	private Date  planBegTime;
	private Date  planEndTime;
	private String  telephone;
	private String  reqRemark;
	private String  remark;
	private Date  doTime;
	private Date  doBegTime;
	private Date  doEndTime;
	private String  state;
	private String  doDepno;
	private String  doCreater;
	private String  doRemark;
	private String  isUse;
	private Date  begTime;
	private Date  endTime;
	private String  todoremark;
	private Long  flowinstid;
	private String  bizstate;
	private String  biztype;
	private String  solvestatus;
	private Date  solvetime;
	private String  solvenote;
	private String  optid;
	private String  flowcode;
    private  OaMeetinfo oaMeetinfo;
	public OaMeetinfo getOaMeetinfo() {
        return oaMeetinfo;
    }
    public void setOaMeetinfo(OaMeetinfo oaMeetinfo) {
        this.oaMeetinfo = oaMeetinfo;
    }
    // Constructors
	/** default constructor */
	public OaMeetroomApply() {
	}
	/** minimal constructor */
	public OaMeetroomApply(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaMeetroomApply(
	 String djId		
	,String  applyNo,String  depno,String  creater,Date  createtime,String  meetingNo,Date  planBegTime,Date  planEndTime,String  telephone,String  reqRemark,String  remark,Date  doTime,Date  doBegTime,Date  doEndTime,String  state,String  doDepno,String  doCreater,String  doRemark,String  isUse,Date  begTime,Date  endTime,String  todoremark,Long  flowinstid,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optid,String  flowcode) {
	
	
		this.djId = djId;		
	
		this.applyNo= applyNo;
		this.depno= depno;
		this.creater= creater;
		this.createtime= createtime;
		this.meetingNo= meetingNo;
		this.planBegTime= planBegTime;
		this.planEndTime= planEndTime;
		this.telephone= telephone;
		this.reqRemark= reqRemark;
		this.remark= remark;
		this.doTime= doTime;
		this.doBegTime= doBegTime;
		this.doEndTime= doEndTime;
		this.state= state;
		this.doDepno= doDepno;
		this.doCreater= doCreater;
		this.doRemark= doRemark;
		this.isUse= isUse;
		this.begTime= begTime;
		this.endTime= endTime;
		this.todoremark= todoremark;
		this.flowinstid= flowinstid;
		this.bizstate= bizstate;
		this.biztype= biztype;
		this.solvestatus= solvestatus;
		this.solvetime= solvetime;
		this.solvenote= solvenote;
		this.optid= optid;
		this.flowcode= flowcode;		
	}
	

  

	// Property accessors
  
	public String getApplyNo() {
		return this.applyNo;
	}
	
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
  
	public String getDepno() {
		return this.depno;
	}
	
	public void setDepno(String depno) {
		this.depno = depno;
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
  
	public String getMeetingNo() {
		return this.meetingNo;
	}
	
	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}
  
	public Date getPlanBegTime() {
		return this.planBegTime;
	}
	
	public void setPlanBegTime(Date planBegTime) {
		this.planBegTime = planBegTime;
	}
  
	public Date getPlanEndTime() {
		return this.planEndTime;
	}
	
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
  
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
  
	public String getReqRemark() {
		return this.reqRemark;
	}
	
	public void setReqRemark(String reqRemark) {
		this.reqRemark = reqRemark;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public Date getDoTime() {
		return this.doTime;
	}
	
	public void setDoTime(Date doTime) {
		this.doTime = doTime;
	}
  
	public Date getDoBegTime() {
		return this.doBegTime;
	}
	
	public void setDoBegTime(Date doBegTime) {
		this.doBegTime = doBegTime;
	}
  
	public Date getDoEndTime() {
		return this.doEndTime;
	}
	
	public void setDoEndTime(Date doEndTime) {
		this.doEndTime = doEndTime;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
  
	public String getDoDepno() {
		return this.doDepno;
	}
	
	public void setDoDepno(String doDepno) {
		this.doDepno = doDepno;
	}
  
	public String getDoCreater() {
		return this.doCreater;
	}
	
	public void setDoCreater(String doCreater) {
		this.doCreater = doCreater;
	}
  
	public String getDoRemark() {
		return this.doRemark;
	}
	
	public void setDoRemark(String doRemark) {
		this.doRemark = doRemark;
	}
  
	public String getIsUse() {
		return this.isUse;
	}
	
	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
  
	public String getTodoremark() {
		return this.todoremark;
	}
	
	public void setTodoremark(String todoremark) {
		this.todoremark = todoremark;
	}
  
	public Long getFlowinstid() {
		return this.flowinstid;
	}
	
	public void setFlowinstid(Long flowinstid) {
		this.flowinstid = flowinstid;
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
  
	public String getSolvenote() {
		return this.solvenote;
	}
	
	public void setSolvenote(String solvenote) {
		this.solvenote = solvenote;
	}
  
	public String getOptid() {
		return this.optid;
	}
	
	public void setOptid(String optid) {
		this.optid = optid;
	}
  
	public String getFlowcode() {
		return this.flowcode;
	}
	
	public void setFlowcode(String flowcode) {
		this.flowcode = flowcode;
	}



	public void copy(OaMeetroomApply other){
  
		this.setDjId(other.getDjId());
  
		this.applyNo= other.getApplyNo();  
		this.depno= other.getDepno();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.meetingNo= other.getMeetingNo();  
		this.planBegTime= other.getPlanBegTime();  
		this.planEndTime= other.getPlanEndTime();  
		this.telephone= other.getTelephone();  
		this.reqRemark= other.getReqRemark();  
		this.remark= other.getRemark();  
		this.doTime= other.getDoTime();  
		this.doBegTime= other.getDoBegTime();  
		this.doEndTime= other.getDoEndTime();  
		this.state= other.getState();  
		this.doDepno= other.getDoDepno();  
		this.doCreater= other.getDoCreater();  
		this.doRemark= other.getDoRemark();  
		this.isUse= other.getIsUse();  
		this.begTime= other.getBegTime();  
		this.endTime= other.getEndTime();  
		this.todoremark= other.getTodoremark();  
		this.flowinstid= other.getFlowinstid();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();  
		this.solvenote= other.getSolvenote();  
		this.optid= other.getOptid();  
		this.flowcode= other.getFlowcode();

	}
	
	public void copyNotNullProperty(OaMeetroomApply other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getApplyNo() != null)
			this.applyNo= other.getApplyNo();  
		if( other.getDepno() != null)
			this.depno= other.getDepno();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getMeetingNo() != null)
			this.meetingNo= other.getMeetingNo();  
		if( other.getPlanBegTime() != null)
			this.planBegTime= other.getPlanBegTime();  
		if( other.getPlanEndTime() != null)
			this.planEndTime= other.getPlanEndTime();  
		if( other.getTelephone() != null)
			this.telephone= other.getTelephone();  
		if( other.getReqRemark() != null)
			this.reqRemark= other.getReqRemark();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getDoTime() != null)
			this.doTime= other.getDoTime();  
		if( other.getDoBegTime() != null)
			this.doBegTime= other.getDoBegTime();  
		if( other.getDoEndTime() != null)
			this.doEndTime= other.getDoEndTime();  
		if( other.getState() != null)
			this.state= other.getState();  
		if( other.getDoDepno() != null)
			this.doDepno= other.getDoDepno();  
		if( other.getDoCreater() != null)
			this.doCreater= other.getDoCreater();  
		if( other.getDoRemark() != null)
			this.doRemark= other.getDoRemark();  
		if( other.getIsUse() != null)
			this.isUse= other.getIsUse();  
		if( other.getBegTime() != null)
			this.begTime= other.getBegTime();  
		if( other.getEndTime() != null)
			this.endTime= other.getEndTime();  
		if( other.getTodoremark() != null)
			this.todoremark= other.getTodoremark();  
		if( other.getFlowinstid() != null)
			this.flowinstid= other.getFlowinstid();  
		if( other.getBizstate() != null)
			this.bizstate= other.getBizstate();  
		if( other.getBiztype() != null)
			this.biztype= other.getBiztype();  
		if( other.getSolvestatus() != null)
			this.solvestatus= other.getSolvestatus();  
		if( other.getSolvetime() != null)
			this.solvetime= other.getSolvetime();  
		if( other.getSolvenote() != null)
			this.solvenote= other.getSolvenote();  
		if( other.getOptid() != null)
			this.optid= other.getOptid();  
		if( other.getFlowcode() != null)
			this.flowcode= other.getFlowcode();

	}
	
	public void clearProperties(){
  
		this.applyNo= null;  
		this.depno= null;  
		this.creater= null;  
		this.createtime= null;  
		this.meetingNo= null;  
		this.planBegTime= null;  
		this.planEndTime= null;  
		this.telephone= null;  
		this.reqRemark= null;  
		this.remark= null;  
		this.doTime= null;  
		this.doBegTime= null;  
		this.doEndTime= null;  
		this.state= null;  
		this.doDepno= null;  
		this.doCreater= null;  
		this.doRemark= null;  
		this.isUse= null;  
		this.begTime= null;  
		this.endTime= null;  
		this.todoremark= null;  
		this.flowinstid= null;  
		this.bizstate= null;  
		this.biztype= null;  
		this.solvestatus= null;  
		this.solvetime= null;  
		this.solvenote= null;  
		this.optid= null;  
		this.flowcode= null;

	}
}
