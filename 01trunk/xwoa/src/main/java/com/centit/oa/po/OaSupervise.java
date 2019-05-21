package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaSupervise implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  supDjid;

    private String  nodecode;
	private String  title;
	private String  superviseType;
	private String  remark;
	private Date  creatertime;
	private String  creater;
	private String  superviseDepno;
	private Date  limittime;
	private String  replayRemark;
	private Date  replayDate;
	private String  replayDepno;
	private String  replayUser;
	private String  superviseUsers;
	private String  feedbackRemark;
	private Date  feedbackDate;
	private String  feedbacker;
	private Date  lastmodifytime;
	private String  state;
	
    private Long  flowInstId;
     private String  bizstate;
	 private String  biztype;
	 private Date  solvetime;
	 private String  solvenote;
	 private String  optid;
	 private String  flowcode;
	 private String  warnType; //提醒类型 0 提醒 1 超时
	 private String  advice; //分管领导提醒意见
	
	public String getWarnType() {
        return warnType;
    }
    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getSupDjid() {
        return supDjid;
    }
    public void setSupDjid(String supDjid) {
        this.supDjid = supDjid;
    }
    public void setFlowInstId(Long flowinstid) {
        this.flowInstId = flowinstid;
    }
    public String getBizstate() {
        return bizstate;
    }
    public void setBizstate(String bizstate) {
        this.bizstate = bizstate;
    }
    public String getBiztype() {
        return biztype;
    }
    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }
    public Date getSolvetime() {
        return solvetime;
    }
    public void setSolvetime(Date solvetime) {
        this.solvetime = solvetime;
    }
    public String getSolvenote() {
        return solvenote;
    }
    public void setSolvenote(String solvenote) {
        this.solvenote = solvenote;
    }
    public String getOptid() {
        return optid;
    }
    public void setOptid(String optid) {
        this.optid = optid;
    }
    public String getFlowcode() {
        return flowcode;
    }
    public void setFlowcode(String flowcode) {
        this.flowcode = flowcode;
    }
    
    
    public String getAdvice() {
        return advice;
    }
    public void setAdvice(String advice) {
        this.advice = advice;
    }
    // Constructors
	/** default constructor */
	public OaSupervise() {
	}
	/** minimal constructor */
	public OaSupervise(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaSupervise(
	 String djId		
	,String  supDjid,String  nodecode,String  title,String  superviseType,String  remark,Date  creatertime,String  creater,String  superviseDepno,Date  limittime,String  replayRemark,Date  replayDate,String  replayDepno,String  replayUser,String  superviseUsers,String  feedbackRemark,Date  feedbackDate,String  feedbacker,
	Date  lastmodifytime,String  state,String bizstate,String biztype,Date solvetime,String solvenote) {
	
	
		this.djId = djId;		
	
		this.supDjid= supDjid;
		this.nodecode= nodecode;
		this.title= title;
		this.superviseType= superviseType;
		this.remark= remark;
		this.creatertime= creatertime;
		this.creater= creater;
		this.superviseDepno= superviseDepno;
		this.limittime= limittime;
		this.replayRemark= replayRemark;
		this.replayDate= replayDate;
		this.replayDepno= replayDepno;
		this.replayUser= replayUser;
		this.superviseUsers= superviseUsers;
		this.feedbackRemark= feedbackRemark;
		this.feedbackDate= feedbackDate;
		this.feedbacker= feedbacker;
		this.lastmodifytime= lastmodifytime;
		this.state= state;	
		this.bizstate=bizstate;
		this.biztype=biztype;
		this.solvenote=solvenote;
		this.solvetime=solvetime;
	}
	
	public OaSupervise(
	        String djId        
	       ,String  supDjid,String  nodecode,String  title,String  superviseType,String  remark,Date  creatertime,String  creater,String  superviseDepno,Date  limittime,String  replayRemark,Date  replayDate,String  replayDepno,String  replayUser,String  superviseUsers,String  feedbackRemark,Date  feedbackDate,String  feedbacker,
	       Date  lastmodifytime,String  state,String bizstate,String biztype,Date solvetime,String solvenote,String advice) {
	       
	       
	           this.djId = djId;       
	       
	           this.supDjid= supDjid;
	           this.nodecode= nodecode;
	           this.title= title;
	           this.superviseType= superviseType;
	           this.remark= remark;
	           this.creatertime= creatertime;
	           this.creater= creater;
	           this.superviseDepno= superviseDepno;
	           this.limittime= limittime;
	           this.replayRemark= replayRemark;
	           this.replayDate= replayDate;
	           this.replayDepno= replayDepno;
	           this.replayUser= replayUser;
	           this.superviseUsers= superviseUsers;
	           this.feedbackRemark= feedbackRemark;
	           this.feedbackDate= feedbackDate;
	           this.feedbacker= feedbacker;
	           this.lastmodifytime= lastmodifytime;
	           this.state= state;  
	           this.bizstate=bizstate;
	           this.biztype=biztype;
	           this.solvenote=solvenote;
	           this.solvetime=solvetime;
	           this.advice=advice;
	       }
	


	public String getNodecode() {
		return this.nodecode;
	}
	
	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}
  
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
  
	public String getSuperviseType() {
		return this.superviseType;
	}
	
	public void setSuperviseType(String superviseType) {
		this.superviseType = superviseType;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public String getSuperviseDepno() {
		return this.superviseDepno;
	}
	
	public void setSuperviseDepno(String superviseDepno) {
		this.superviseDepno = superviseDepno;
	}
  
	public Date getLimittime() {
		return this.limittime;
	}
	
	public void setLimittime(Date limittime) {
		this.limittime = limittime;
	}
  
	public String getReplayRemark() {
		return this.replayRemark;
	}
	
	public void setReplayRemark(String replayRemark) {
		this.replayRemark = replayRemark;
	}
  
	public Date getReplayDate() {
		return this.replayDate;
	}
	
	public void setReplayDate(Date replayDate) {
		this.replayDate = replayDate;
	}
  
	public String getReplayDepno() {
		return this.replayDepno;
	}
	
	public void setReplayDepno(String replayDepno) {
		this.replayDepno = replayDepno;
	}
  
	public String getReplayUser() {
		return this.replayUser;
	}
	
	public void setReplayUser(String replayUser) {
		this.replayUser = replayUser;
	}
  
	public String getSuperviseUsers() {
		return this.superviseUsers;
	}
	
	public void setSuperviseUsers(String superviseUsers) {
		this.superviseUsers = superviseUsers;
	}
  
	public String getFeedbackRemark() {
		return this.feedbackRemark;
	}
	
	public void setFeedbackRemark(String feedbackRemark) {
		this.feedbackRemark = feedbackRemark;
	}
  
	public Date getFeedbackDate() {
		return this.feedbackDate;
	}
	
	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
  
	public String getFeedbacker() {
		return this.feedbacker;
	}
	
	public void setFeedbacker(String feedbacker) {
		this.feedbacker = feedbacker;
	}
  
	public Date getLastmodifytime() {
		return this.lastmodifytime;
	}
	
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}



	public void copy(OaSupervise other){
  
		this.setDjId(other.getDjId());
  
		this.supDjid= other.getSupDjid();  
		this.nodecode= other.getNodecode();  
		this.title= other.getTitle();  
		this.superviseType= other.getSuperviseType();  
		this.remark= other.getRemark();  
		this.creatertime= other.getCreatertime();  
		this.creater= other.getCreater();  
		this.superviseDepno= other.getSuperviseDepno();  
		this.limittime= other.getLimittime();  
		this.replayRemark= other.getReplayRemark();  
		this.replayDate= other.getReplayDate();  
		this.replayDepno= other.getReplayDepno();  
		this.replayUser= other.getReplayUser();  
		this.superviseUsers= other.getSuperviseUsers();  
		this.feedbackRemark= other.getFeedbackRemark();  
		this.feedbackDate= other.getFeedbackDate();  
		this.feedbacker= other.getFeedbacker();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.state= other.getState();
            this.bizstate=other.getBizstate();
            this.biztype=other.getBiztype();
            this.solvetime=other.getSolvetime();
            this.solvenote=other.getSolvenote();
        this.advice=other.getAdvice();
	}
	
	public void copyNotNullProperty(OaSupervise other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getSupDjid() != null)
			this.supDjid= other.getSupDjid();  
		if( other.getNodecode() != null)
			this.nodecode= other.getNodecode();  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getSuperviseType() != null)
			this.superviseType= other.getSuperviseType();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getSuperviseDepno() != null)
			this.superviseDepno= other.getSuperviseDepno();  
		if( other.getLimittime() != null)
			this.limittime= other.getLimittime();  
		if( other.getReplayRemark() != null)
			this.replayRemark= other.getReplayRemark();  
		if( other.getReplayDate() != null)
			this.replayDate= other.getReplayDate();  
		if( other.getReplayDepno() != null)
			this.replayDepno= other.getReplayDepno();  
		if( other.getReplayUser() != null)
			this.replayUser= other.getReplayUser();  
		if( other.getSuperviseUsers() != null)
			this.superviseUsers= other.getSuperviseUsers();  
		if( other.getFeedbackRemark() != null)
			this.feedbackRemark= other.getFeedbackRemark();  
		if( other.getFeedbackDate() != null)
			this.feedbackDate= other.getFeedbackDate();  
		if( other.getFeedbacker() != null)
			this.feedbacker= other.getFeedbacker();  
		if( other.getLastmodifytime() != null)
			this.lastmodifytime= other.getLastmodifytime();  
		if( other.getState() != null)
			this.state= other.getState();
	    if( other.getBizstate() != null)
            this.bizstate= other.getBizstate();
	    if( other.getBiztype() != null)
            this.biztype= other.getBiztype();

	    if( other.getSolvenote() != null)
            this.solvenote= other.getSolvenote();

	    if( other.getSolvetime() != null)
            this.solvetime= other.getSolvetime();
	    if(other.getAdvice()!=null)
	        this.advice=other.getAdvice();

	}
	
	public void clearProperties(){
  
		this.supDjid= null;  
		this.nodecode= null;  
		this.title= null;  
		this.superviseType= null;  
		this.remark= null;  
		this.creatertime= null;  
		this.creater= null;  
		this.superviseDepno= null;  
		this.limittime= null;  
		this.replayRemark= null;  
		this.replayDate= null;  
		this.replayDepno= null;  
		this.replayUser= null;  
		this.superviseUsers= null;  
		this.feedbackRemark= null;  
		this.feedbackDate= null;  
		this.feedbacker= null;  
		this.lastmodifytime= null;  
		this.state= null;
		this.advice=null;

	}
    public Long getFlowInstId() {
        return flowInstId;
    }
}
