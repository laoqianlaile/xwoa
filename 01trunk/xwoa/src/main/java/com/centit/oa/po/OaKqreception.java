package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaKqreception implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String djId;

	private String  transaffairname;
	private String  layoutno;
	private String  kqdepname;
	private Date  approtime;
	private String  approvalremark;
	private String  approval;
	private String  approphone;
	private Date  leavetime;
	private Date  arrivetime;
	private String  bodycontent;
	private String  lodgingplace;
	private String  lodgingcase;
	private String  mealplace;
	private String  mealcase;
	private String  meetplase;
	private String  meetcase;
	private String  meetcontent;
	private String  meetnum;
	private String  otheritems;
	private String  costtotalcapital;
	private String  costtotallowcase;
	private String  remark;
	private String  creater;
	private Date  createtime;
	private String  flowcode;
	private Long  flowInstId;
	private String  bizstate;
	private String  biztype;
	private String  solvestatus;
	private Date  solvetime;
	private String  solvenote;
	private String  optId;
	
	private OptBaseInfo optBaseInfo;
	
	
	//流程数据
	 private String recievedepno;//接收部门

	// Constructors
	/** default constructor */
	public OaKqreception() {
	}
	/** minimal constructor */
	public OaKqreception(
		String djId		
		) {
	
	
		this.djId = djId;		
			
	}

/** full constructor */
	public OaKqreception(
	 String djId		
	,String  transaffairname,String  layoutno,String  kqdepname,Date  approtime,String  approvalremark,String  approval,String  approphone,Date  leavetime,String  bodycontent,String  lodgingplace,String  lodgingcase,String  mealplace,String  mealcase,String  meetplase,String  meetcase,String  meetcontent,String  meetnum,String  otheritems,String  costtotalcapital,String  costtotallowcase,String  remark,String  creater,Date  createtime,String  flowcode,Long  flowInstId,String  bizstate,String  biztype,String  solvestatus,Date  solvetime,String  solvenote,String  optId) {
	
	
		this.djId = djId;		
	
		this.transaffairname= transaffairname;
		this.layoutno= layoutno;
		this.kqdepname= kqdepname;
		this.approtime= approtime;
		this.approvalremark= approvalremark;
		this.approval= approval;
		this.approphone= approphone;
		this.leavetime= leavetime;
		this.arrivetime= arrivetime;
		this.bodycontent= bodycontent;
		this.lodgingplace= lodgingplace;
		this.lodgingcase= lodgingcase;
		this.mealplace= mealplace;
		this.mealcase= mealcase;
		this.meetplase= meetplase;
		this.meetcase= meetcase;
		this.meetcontent= meetcontent;
		this.meetnum= meetnum;
		this.otheritems= otheritems;
		this.costtotalcapital= costtotalcapital;
		this.costtotallowcase= costtotallowcase;
		this.remark= remark;
		this.creater= creater;
		this.createtime= createtime;
		this.flowcode= flowcode;
		this.flowInstId= flowInstId;
		this.bizstate= bizstate;
		this.biztype= biztype;
		this.solvestatus= solvestatus;
		this.solvetime= solvetime;
		this.solvenote= solvenote;
		this.optId= optId;		
	}
	

  
	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}
	// Property accessors
  
	public String getTransaffairname() {
		return this.transaffairname;
	}
	
	public void setTransaffairname(String transaffairname) {
		this.transaffairname = transaffairname;
	}
  
	public String getLayoutno() {
		return this.layoutno;
	}
	
	public void setLayoutno(String layoutno) {
		this.layoutno = layoutno;
	}
  
	public String getKqdepname() {
		return this.kqdepname;
	}
	
	public void setKqdepname(String kqdepname) {
		this.kqdepname = kqdepname;
	}
  
	public Date getApprotime() {
		return this.approtime;
	}
	
	public void setApprotime(Date approtime) {
		this.approtime = approtime;
	}
  
	public String getApprovalremark() {
		return this.approvalremark;
	}
	
	public void setApprovalremark(String approvalremark) {
		this.approvalremark = approvalremark;
	}
  
	public String getApproval() {
		return this.approval;
	}
	
	public void setApproval(String approval) {
		this.approval = approval;
	}
  
	public String getApprophone() {
		return this.approphone;
	}
	
	public void setApprophone(String approphone) {
		this.approphone = approphone;
	}
  
	public Date getLeavetime() {
		return this.leavetime;
	}
	
	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	}
  
	public Date getArrivetime() {
        return arrivetime;
    }
    public void setArrivetime(Date arrivetime) {
        this.arrivetime = arrivetime;
    }
    public String getBodycontent() {
		return this.bodycontent;
	}
	
	public void setBodycontent(String bodycontent) {
		this.bodycontent = bodycontent;
	}
  
	public String getLodgingplace() {
		return this.lodgingplace;
	}
	
	public void setLodgingplace(String lodgingplace) {
		this.lodgingplace = lodgingplace;
	}
  
	public String getLodgingcase() {
		return this.lodgingcase;
	}
	
	public void setLodgingcase(String lodgingcase) {
		this.lodgingcase = lodgingcase;
	}
  
	public String getMealplace() {
		return this.mealplace;
	}
	
	public void setMealplace(String mealplace) {
		this.mealplace = mealplace;
	}
  
	public String getMealcase() {
		return this.mealcase;
	}
	
	public void setMealcase(String mealcase) {
		this.mealcase = mealcase;
	}
  
	public String getMeetplase() {
		return this.meetplase;
	}
	
	public void setMeetplase(String meetplase) {
		this.meetplase = meetplase;
	}
  
	public String getMeetcase() {
		return this.meetcase;
	}
	
	public void setMeetcase(String meetcase) {
		this.meetcase = meetcase;
	}
  
	public String getMeetcontent() {
		return this.meetcontent;
	}
	
	public void setMeetcontent(String meetcontent) {
		this.meetcontent = meetcontent;
	}
  
	public String getMeetnum() {
		return this.meetnum;
	}
	
	public void setMeetnum(String meetnum) {
		this.meetnum = meetnum;
	}
  
	public String getOtheritems() {
		return this.otheritems;
	}
	
	public void setOtheritems(String otheritems) {
		this.otheritems = otheritems;
	}
  
	public String getCosttotalcapital() {
		return this.costtotalcapital;
	}
	
	public void setCosttotalcapital(String costtotalcapital) {
		this.costtotalcapital = costtotalcapital;
	}
  
	public String getCosttotallowcase() {
		return this.costtotallowcase;
	}
	
	public void setCosttotallowcase(String costtotallowcase) {
		this.costtotallowcase = costtotallowcase;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
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
  
	public String getFlowcode() {
		return this.flowcode;
	}
	
	public void setFlowcode(String flowcode) {
		this.flowcode = flowcode;
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
  
	public String getOptId() {
		return this.optId;
	}
	
	public void setOptId(String optId) {
		this.optId = optId;
	}



	public void copy(OaKqreception other){
  
		this.setDjId(other.getDjId());
  
		this.transaffairname= other.getTransaffairname();  
		this.layoutno= other.getLayoutno();  
		this.kqdepname= other.getKqdepname();  
		this.approtime= other.getApprotime();  
		this.approvalremark= other.getApprovalremark();  
		this.approval= other.getApproval();  
		this.approphone= other.getApprophone();  
		this.leavetime= other.getLeavetime();  
		this.arrivetime= other.getArrivetime();  
		this.bodycontent= other.getBodycontent();  
		this.lodgingplace= other.getLodgingplace();  
		this.lodgingcase= other.getLodgingcase();  
		this.mealplace= other.getMealplace();  
		this.mealcase= other.getMealcase();  
		this.meetplase= other.getMeetplase();  
		this.meetcase= other.getMeetcase();  
		this.meetcontent= other.getMeetcontent();  
		this.meetnum= other.getMeetnum();  
		this.otheritems= other.getOtheritems();  
		this.costtotalcapital= other.getCosttotalcapital();  
		this.costtotallowcase= other.getCosttotallowcase();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.flowcode= other.getFlowcode();  
		this.flowInstId= other.getFlowInstId();  
		this.bizstate= other.getBizstate();  
		this.biztype= other.getBiztype();  
		this.solvestatus= other.getSolvestatus();  
		this.solvetime= other.getSolvetime();  
		this.solvenote= other.getSolvenote();  
		this.optId= other.getOptId();

	}
	
	public void copyNotNullProperty(OaKqreception other){
  
	if( other.getDjId() != null)
		this.setDjId(other.getDjId());
  
		if( other.getTransaffairname() != null)
			this.transaffairname= other.getTransaffairname();  
		if( other.getLayoutno() != null)
			this.layoutno= other.getLayoutno();  
		if( other.getKqdepname() != null)
			this.kqdepname= other.getKqdepname();  
		if( other.getApprotime() != null)
			this.approtime= other.getApprotime();  
		if( other.getApprovalremark() != null)
			this.approvalremark= other.getApprovalremark();  
		if( other.getApproval() != null)
			this.approval= other.getApproval();  
		if( other.getApprophone() != null)
			this.approphone= other.getApprophone();  
		if( other.getLeavetime() != null)
			this.leavetime= other.getLeavetime();
		if( other.getArrivetime() !=null)
		    this.arrivetime= other.getArrivetime();
		if( other.getBodycontent() != null)
			this.bodycontent= other.getBodycontent();  
		if( other.getLodgingplace() != null)
			this.lodgingplace= other.getLodgingplace();  
		if( other.getLodgingcase() != null)
			this.lodgingcase= other.getLodgingcase();  
		if( other.getMealplace() != null)
			this.mealplace= other.getMealplace();  
		if( other.getMealcase() != null)
			this.mealcase= other.getMealcase();  
		if( other.getMeetplase() != null)
			this.meetplase= other.getMeetplase();  
		if( other.getMeetcase() != null)
			this.meetcase= other.getMeetcase();  
		if( other.getMeetcontent() != null)
			this.meetcontent= other.getMeetcontent();  
		if( other.getMeetnum() != null)
			this.meetnum= other.getMeetnum();  
		if( other.getOtheritems() != null)
			this.otheritems= other.getOtheritems();  
		if( other.getCosttotalcapital() != null)
			this.costtotalcapital= other.getCosttotalcapital();  
		if( other.getCosttotallowcase() != null)
			this.costtotallowcase= other.getCosttotallowcase();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getFlowcode() != null)
			this.flowcode= other.getFlowcode();  
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
		if( other.getOptId() != null)
			this.optId= other.getOptId();

	}
	
	public void clearProperties(){
  
		this.transaffairname= null;  
		this.layoutno= null;  
		this.kqdepname= null;  
		this.approtime= null;  
		this.approvalremark= null;  
		this.approval= null;  
		this.approphone= null;  
		this.leavetime= null;  
		this.arrivetime= null;  
		this.bodycontent= null;  
		this.lodgingplace= null;  
		this.lodgingcase= null;  
		this.mealplace= null;  
		this.mealcase= null;  
		this.meetplase= null;  
		this.meetcase= null;  
		this.meetcontent= null;  
		this.meetnum= null;  
		this.otheritems= null;  
		this.costtotalcapital= null;  
		this.costtotallowcase= null;  
		this.remark= null;  
		this.creater= null;  
		this.createtime= null;  
		this.flowcode= null;  
		this.flowInstId= null;  
		this.bizstate= null;  
		this.biztype= null;  
		this.solvestatus= null;  
		this.solvetime= null;  
		this.solvenote= null;  
		this.optId= null;

	}
    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }
    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }
    public String getRecievedepno() {
        return recievedepno;
    }
    public void setRecievedepno(String recievedepno) {
        this.recievedepno = recievedepno;
    }
	
	
}
