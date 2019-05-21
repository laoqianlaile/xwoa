package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptProcInfo;
 
/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaCarApply implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;
	private String prevDjId;
	private String nextDjId;
	    
    private String  applyNo;
	private String  depno;
	private String  creater;
	private Date  createtime;
	private Long  meetingPersionsNum;
	private String  destination;
	private String  telephone;
	private Date  planBegTime;
	private Date  planEndTime;
	private String  meetingNo;
	private String  reqRemark;
	private String  meetingPersions;
	private String  remark;
	private Date  doTime;
	private Date  doBegTime;
	private Date  doEndTime;
	private String  state;
	private String  doDepno;
	private String  doCreater;
	private String  cardjid;
	private String  carno;
	private String  driver;
	private String  brand;
	private String  modelType;
	private String  drTelephone;
	private String  doRemark;
	private String  isUse;
	private Date  begTime;
	private Date  endTime;
	private String  todoremark;
	private Long  flowInstId;
	private String  bizstate;
	private String  biztype;
	private String  solvestatus;
	private Date  solvetime;
	private String  solvenote;
	private String  optid;
	private String  flowcode;
	private String transaffairname;
	private Date cpBegtime;
	private Date cpEndtime;
    private  OptProcInfo optProcInfo;
    
    //新增有车来源字段 工会特有
    private String range_type;
	// Constructors
	/** default constructor */
	public OaCarApply() {
	}
	/** minimal constructor */
	public OaCarApply(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

public OaCarApply(String djId, String applyNo, String depno,
            String creater, Date createtime, Long meetingPersionsNum,
            String destination, String telephone, Date planBegTime,
            Date planEndTime, String meetingNo, String reqRemark,
            String meetingPersions, String remark, Date doTime, Date doBegTime,
            Date doEndTime, String state, String doDepno, String doCreater,
            String cardjid, String carno, String driver, String brand,
            String modelType, String drTelephone, String doRemark,
            String isUse, Date begTime, Date endTime, String todoremark,
            Long flowInstId, String bizstate, String biztype,
            String solvestatus, Date solvetime, String solvenote, String optid,
            String flowcode, String transaffairname,Date cpBegtime, Date cpEndtime) {
        super();
        this.djId = djId;
        this.applyNo = applyNo;
        this.depno = depno;
        this.creater = creater;
        this.createtime = createtime;
        this.meetingPersionsNum = meetingPersionsNum;
        this.destination = destination;
        this.telephone = telephone;
        this.planBegTime = planBegTime;
        this.planEndTime = planEndTime;
        this.meetingNo = meetingNo;
        this.reqRemark = reqRemark;
        this.meetingPersions = meetingPersions;
        this.remark = remark;
        this.doTime = doTime;
        this.doBegTime = doBegTime;
        this.doEndTime = doEndTime;
        this.state = state;
        this.doDepno = doDepno;
        this.doCreater = doCreater;
        this.cardjid = cardjid;
        this.carno = carno;
        this.driver = driver;
        this.brand = brand;
        this.modelType = modelType;
        this.drTelephone = drTelephone;
        this.doRemark = doRemark;
        this.isUse = isUse;
        this.begTime = begTime;
        this.endTime = endTime;
        this.todoremark = todoremark;
        this.flowInstId = flowInstId;
        this.bizstate = bizstate;
        this.biztype = biztype;
        this.solvestatus = solvestatus;
        this.solvetime = solvetime;
        this.solvenote = solvenote;
        this.optid = optid;
        this.flowcode = flowcode;
        this.transaffairname = transaffairname;
        this.cpBegtime= cpBegtime;
        this.cpEndtime = cpEndtime;
    }
/** full constructor */

	public OaCarApply(
	 String djId		
	,String  applyNo,String  depno,String  creater,Date  createtime,Long  meetingPersionsNum,String  destination,String  telephone,Date  planBegTime,Date  planEndTime,String  meetingNo,String  reqRemark,String  meetingPersions,String  remark,Date  doTime,Date  doBegTime,Date  doEndTime,String  state,String  doDepno,String  doCreater,String  cardjid,String  carno,String  driver,String  brand,String  modelType,String  drTelephone,String  doRemark,String  isUse,Date  begTime,Date  endTime,String  todoremark,Long  flowinstid,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optid,String  flowcode) {
	
	
		this.djId = djId;		
	
		this.applyNo= applyNo;
		this.depno= depno;
		this.creater= creater;
		this.createtime= createtime;
		this.meetingPersionsNum= meetingPersionsNum;
		this.destination= destination;
		this.telephone= telephone;
		this.planBegTime= planBegTime;
		this.planEndTime= planEndTime;
		this.meetingNo= meetingNo;
		this.reqRemark= reqRemark;
		this.meetingPersions= meetingPersions;
		this.remark= remark;
		this.doTime= doTime;
		this.doBegTime= doBegTime;
		this.doEndTime= doEndTime;
		this.state= state;
		this.doDepno= doDepno;
		this.doCreater= doCreater;
		this.cardjid= cardjid;
		this.carno= carno;
		this.driver= driver;
		this.brand= brand;
		this.modelType= modelType;
		this.drTelephone= drTelephone;
		this.doRemark= doRemark;
		this.isUse= isUse;
		this.begTime= begTime;
		this.endTime= endTime;
		this.todoremark= todoremark;
		this.flowInstId= flowinstid;
		this.bizstate= bizstate;
		this.biztype= biztype;
		this.solvestatus= solvestatus;
		this.solvetime= solvetime;
		this.solvenote= solvenote;
		this.optid= optid;
		this.flowcode= flowcode;		
	}
	

    public OaCarApply(String djId, String prevDjId, String nextDjId,
        String applyNo, String depno, String creater, Date createtime,
        Long meetingPersionsNum, String destination, String telephone,
        Date planBegTime, Date planEndTime, String meetingNo, String reqRemark,
        String meetingPersions, String remark, Date doTime, Date doBegTime,
        Date doEndTime, String state, String doDepno, String doCreater,
        String cardjid, String carno, String driver, String brand,
        String modelType, String drTelephone, String doRemark, String isUse,
        Date begTime, Date endTime, String todoremark, Long flowInstId,
        String bizstate, String biztype, String solvestatus, Date solvetime,
        String solvenote, String optid, String flowcode, String range_type) {
    super();
    this.djId = djId;
    this.prevDjId = prevDjId;
    this.nextDjId = nextDjId;
    this.applyNo = applyNo;
    this.depno = depno;
    this.creater = creater;
    this.createtime = createtime;
    this.meetingPersionsNum = meetingPersionsNum;
    this.destination = destination;
    this.telephone = telephone;
    this.planBegTime = planBegTime;
    this.planEndTime = planEndTime;
    this.meetingNo = meetingNo;
    this.reqRemark = reqRemark;
    this.meetingPersions = meetingPersions;
    this.remark = remark;
    this.doTime = doTime;
    this.doBegTime = doBegTime;
    this.doEndTime = doEndTime;
    this.state = state;
    this.doDepno = doDepno;
    this.doCreater = doCreater;
    this.cardjid = cardjid;
    this.carno = carno;
    this.driver = driver;
    this.brand = brand;
    this.modelType = modelType;
    this.drTelephone = drTelephone;
    this.doRemark = doRemark;
    this.isUse = isUse;
    this.begTime = begTime;
    this.endTime = endTime;
    this.todoremark = todoremark;
    this.flowInstId = flowInstId;
    this.bizstate = bizstate;
    this.biztype = biztype;
    this.solvestatus = solvestatus;
    this.solvetime = solvetime;
    this.solvenote = solvenote;
    this.optid = optid;
    this.flowcode = flowcode;
    this.range_type = range_type;
}
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }


	// Property accessors
  
	public String getTransaffairname() {
        return transaffairname;
    }
    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }
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
  
	public Long getMeetingPersionsNum() {
		return this.meetingPersionsNum;
	}
	
	public void setMeetingPersionsNum(Long meetingPersionsNum) {
		this.meetingPersionsNum = meetingPersionsNum;
	}
  
	public String getDestination() {
		return this.destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
  
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
  
	public String getMeetingNo() {
		return this.meetingNo;
	}
	
	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}
  
	public String getReqRemark() {
		return this.reqRemark;
	}
	
	public void setReqRemark(String reqRemark) {
		this.reqRemark = reqRemark;
	}
  
	public String getMeetingPersions() {
		return this.meetingPersions;
	}
	
	public void setMeetingPersions(String meetingPersions) {
		this.meetingPersions = meetingPersions;
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
  
	public String getCardjid() {
		return this.cardjid;
	}
	
	public void setCardjid(String cardjid) {
		this.cardjid = cardjid;
	}
  
	public String getCarno() {
		return this.carno;
	}
	
	public void setCarno(String carno) {
		this.carno = carno;
	}
  
	public String getDriver() {
		return this.driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
  
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
  
	public String getModelType() {
		return this.modelType;
	}
	
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
  
	public String getDrTelephone() {
		return this.drTelephone;
	}
	
	public void setDrTelephone(String drTelephone) {
		this.drTelephone = drTelephone;
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
	


	public Date getCpBegtime() {
        return cpBegtime;
    }
    public void setCpBegtime(Date cpBegtime) {
        this.cpBegtime = cpBegtime;
    }
    public Date getCpEndtime() {
        return cpEndtime;
    }
    public void setCpEndtime(Date cpEndtime) {
        this.cpEndtime = cpEndtime;
    }
    public String getPrevDjId() {
        return prevDjId;
    }
    public void setPrevDjId(String prevDjId) {
        this.prevDjId = prevDjId;
    }
    public String getNextDjId() {
        return nextDjId;
    }
    public void setNextDjId(String nextDjId) {
        this.nextDjId = nextDjId;
    }
    public void copy(OaCarApply other){
  
		this.setDjId(other.getDjId());
  
		this.applyNo= other.getApplyNo();  
		this.depno= other.getDepno();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.meetingPersionsNum= other.getMeetingPersionsNum();  
		this.destination= other.getDestination();  
		this.telephone= other.getTelephone();  
		this.planBegTime= other.getPlanBegTime();  
		this.planEndTime= other.getPlanEndTime();  
		this.meetingNo= other.getMeetingNo();  
		this.reqRemark= other.getReqRemark();  
		this.meetingPersions= other.getMeetingPersions();  
		this.remark= other.getRemark();  
		this.doTime= other.getDoTime();  
		this.doBegTime= other.getDoBegTime();  
		this.doEndTime= other.getDoEndTime();  
		this.state= other.getState();  
		this.doDepno= other.getDoDepno();  
		this.doCreater= other.getDoCreater();  
		this.cardjid= other.getCardjid();  
		this.carno= other.getCarno();  
		this.driver= other.getDriver();  
		this.brand= other.getBrand();  
		this.modelType= other.getModelType();  
		this.drTelephone= other.getDrTelephone();  
		this.doRemark= other.getDoRemark();  
		this.isUse= other.getIsUse();  
		this.begTime= other.getBegTime();  
		this.endTime= other.getEndTime();  
		this.todoremark= other.getTodoremark();  
		this.flowInstId= other.getFlowInstId();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();  
		this.solvenote= other.getSolvenote();  
		this.optid= other.getOptid();  
		this.flowcode= other.getFlowcode();
		this.transaffairname=other.getTransaffairname();
		this.range_type=other.getRange_type();

	}
	
	public void copyNotNullProperty(OaCarApply other){
  
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
		if( other.getMeetingPersionsNum() != null)
			this.meetingPersionsNum= other.getMeetingPersionsNum();  
		if( other.getDestination() != null)
			this.destination= other.getDestination();  
		if( other.getTelephone() != null)
			this.telephone= other.getTelephone();  
		if( other.getPlanBegTime() != null)
			this.planBegTime= other.getPlanBegTime();  
		if( other.getPlanEndTime() != null)
			this.planEndTime= other.getPlanEndTime();  
		if( other.getMeetingNo() != null)
			this.meetingNo= other.getMeetingNo();  
		if( other.getReqRemark() != null)
			this.reqRemark= other.getReqRemark();  
		if( other.getMeetingPersions() != null)
			this.meetingPersions= other.getMeetingPersions();  
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
		if( other.getCardjid() != null)
			this.cardjid= other.getCardjid();  
		if( other.getCarno() != null)
			this.carno= other.getCarno();  
		if( other.getDriver() != null)
			this.driver= other.getDriver();  
		if( other.getBrand() != null)
			this.brand= other.getBrand();  
		if( other.getModelType() != null)
			this.modelType= other.getModelType();  
		if( other.getDrTelephone() != null)
			this.drTelephone= other.getDrTelephone();  
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
		if( other.getSolvenote() != null)
			this.solvenote= other.getSolvenote();  
		if( other.getOptid() != null)
			this.optid= other.getOptid();  
		if( other.getFlowcode() != null)
			this.flowcode= other.getFlowcode();
		if( other.getTransaffairname() != null)
		this.transaffairname=other.getTransaffairname();
		if( other.getRange_type() != null)
		this.range_type=other.getRange_type();
	}
	
	public void clearProperties(){
  
		this.applyNo= null;  
		this.depno= null;  
		this.creater= null;  
		this.createtime= null;  
		this.meetingPersionsNum= null;  
		this.destination= null;  
		this.telephone= null;  
		this.planBegTime= null;  
		this.planEndTime= null;  
		this.meetingNo= null;  
		this.reqRemark= null;  
		this.meetingPersions= null;  
		this.remark= null;  
		this.doTime= null;  
		this.doBegTime= null;  
		this.doEndTime= null;  
		this.state= null;  
		this.doDepno= null;  
		this.doCreater= null;  
		this.cardjid= null;  
		this.carno= null;  
		this.driver= null;  
		this.brand= null;  
		this.modelType= null;  
		this.drTelephone= null;  
		this.doRemark= null;  
		this.isUse= null;  
		this.begTime= null;  
		this.endTime= null;  
		this.todoremark= null;  
		this.flowInstId= null;  
		this.bizstate= null;  
		this.biztype= null;  
		this.solvestatus= null;  
		this.solvetime= null;  
		this.solvenote= null;  
		this.optid= null;  
		this.flowcode= null;
		this.transaffairname=null;
		this.range_type=null;
	}
    public OptProcInfo getOptProcInfo() {
        return optProcInfo;
    }
    public void setOptProcInfo(OptProcInfo optProcInfo) {
        this.optProcInfo = optProcInfo;
    }
    public String getRange_type() {
        return range_type;
    }
    public void setRange_type(String range_type) {
        this.range_type = range_type;
    }
    
}
