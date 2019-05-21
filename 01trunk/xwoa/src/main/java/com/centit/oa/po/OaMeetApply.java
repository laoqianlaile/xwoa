package com.centit.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaMeetApply implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private String applyNo;
    private String depno;// 存放的数值为unitcode
    private String creater;
    private Date createtime;
    private String title;
    private String meetingNo;
    private Date planBegTime;
    private Date planEndTime;
    private String telephone;
    private String meetingPersions;
    private String outerPersions;
    private Long meetingPersionsNum;
    private String reqRemark;
    private String remark;
    private Date doTime;
    private Date doBegTime;
    private Date doEndTime;
    private String state;//1申请中2已安排3已使用4不同意5被抢占6暂存  7取消
    private String doDepno;
    private String doCreater;
    private String doRemark;
    private String isUse;
    private Date begTime;
    private Date endTime;
    private String todoremark;
    private Long flowInstId;
    private String bizstate;
    private String biztype;
    private String solvestatus;//Z 中止 C 取消 W 办理中  F 不同意
    private Date solvetime;
    private String solvenote;
    private String optid;
    private String flowcode;
    private String supDjid;
    private String bjState;// 标记state
    private String otherItem;

    private String meetType; // 会议类型 O 一般会议 P 视频会议
    private String meeting_rang;// 会议范围
    private String units;// 发言单位
    private String attendingUnits;// 参会单位
    private Date alignment_time;// 联调时间
    private String use_venue;// 使用会场
    private String otheruse_venue;// 使用其他会场
    private String isSLmeeting;// 是否双流会议

    private String otherUnits;// 其他参会单位
    private String attendingLeadercodes;// 参会领导代码
    private String attendingLeaderNames;// 参会领导
    private String recomUnits;// 建议保障部门
    private String recomUnitNames;// 建议会议保障部门名称

    private OaMeetinfo oaMeetinfo;
    private Set<AddressBookRelection> addressBookRelections = null;
    
    private Date cpBegTime;
    private Date cpEndTime;
    
    private String meetingName;
    
    private String isBasicUnit;//是否有基层单位参加
    private String isStopCar;//是否需要停车

    // Constructors
    /** default constructor */
    public OaMeetApply() {
    }

    /** minimal constructor */
    public OaMeetApply(String djId) {
        this.djId = djId;
    }

    /** full constructor */

    public OaMeetApply(String djId, String applyNo, String depno,
            String creater, Date createtime, String title, String meetingNo,
            Date planBegTime, Date planEndTime, String telephone,
            String meetingPersions, String outerPersions,
            Long meetingPersionsNum, String reqRemark, String remark,
            Date doTime, Date doBegTime, Date doEndTime, String state,
            String doDepno, String doCreater, String doRemark, String isUse,
            Date begTime, Date endTime, String todoremark, Long flowInstId,
            String bizstate, String biztype, String solvestatus,
            Date solvetime, String solvenote, String optid, String flowcode,
            String supDjid, String bjState, String otherItem, String meetType,
            String meeting_rang, String units, String attendingUnits,
            Date alignment_time, String use_venue, String otheruse_venue,
            String isSLmeeting, OaMeetinfo oaMeetinfo,
            Set<AddressBookRelection> addressBookRelections) {
        super();
        this.djId = djId;
        this.applyNo = applyNo;
        this.depno = depno;
        this.creater = creater;
        this.createtime = createtime;
        this.title = title;
        this.meetingNo = meetingNo;
        this.planBegTime = planBegTime;
        this.planEndTime = planEndTime;
        this.telephone = telephone;
        this.meetingPersions = meetingPersions;
        this.outerPersions = outerPersions;
        this.meetingPersionsNum = meetingPersionsNum;
        this.reqRemark = reqRemark;
        this.remark = remark;
        this.doTime = doTime;
        this.doBegTime = doBegTime;
        this.doEndTime = doEndTime;
        this.state = state;
        this.doDepno = doDepno;
        this.doCreater = doCreater;
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
        this.supDjid = supDjid;
        this.bjState = bjState;
        this.otherItem = otherItem;
        this.meetType = meetType;
        this.meeting_rang = meeting_rang;
        this.units = units;
        this.attendingUnits = attendingUnits;
        this.alignment_time = alignment_time;
        this.use_venue = use_venue;
        this.otheruse_venue = otheruse_venue;
        this.isSLmeeting = isSLmeeting;
        this.oaMeetinfo = oaMeetinfo;
        this.addressBookRelections = addressBookRelections;
    }

    public OaMeetApply(String djId, String applyNo, String depno,
            String creater, Date createtime, String title, String meetingNo,
            Date planBegTime, Date planEndTime, String telephone,
            String meetingPersions, String outerPersions,
            Long meetingPersionsNum, String reqRemark, String remark,
            Date doTime, Date doBegTime, Date doEndTime, String state,
            String doDepno, String doCreater, String doRemark, String isUse,
            Date begTime, Date endTime, String todoremark, Long flowInstId,
            String bizstate, String biztype, String solvestatus,
            Date solvetime, String solvenote, String optid, String flowcode,
            String supDjid, String bjState, String otherItem, String meetType,
            String meeting_rang, String units, String attendingUnits,
            Date alignment_time, String use_venue, String otheruse_venue,
            String isSLmeeting, String otherUnits, String attendingLeadercodes,
            String attendingLeaderNames, String recomUnits,
            String recomUnitNames, OaMeetinfo oaMeetinfo, Date cpBegTime,
            Date cpEndTime, String meetingName, String isBasicUnit,
            String isStopCar) {
        super();
        this.djId = djId;
        this.applyNo = applyNo;
        this.depno = depno;
        this.creater = creater;
        this.createtime = createtime;
        this.title = title;
        this.meetingNo = meetingNo;
        this.planBegTime = planBegTime;
        this.planEndTime = planEndTime;
        this.telephone = telephone;
        this.meetingPersions = meetingPersions;
        this.outerPersions = outerPersions;
        this.meetingPersionsNum = meetingPersionsNum;
        this.reqRemark = reqRemark;
        this.remark = remark;
        this.doTime = doTime;
        this.doBegTime = doBegTime;
        this.doEndTime = doEndTime;
        this.state = state;
        this.doDepno = doDepno;
        this.doCreater = doCreater;
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
        this.supDjid = supDjid;
        this.bjState = bjState;
        this.otherItem = otherItem;
        this.meetType = meetType;
        this.meeting_rang = meeting_rang;
        this.units = units;
        this.attendingUnits = attendingUnits;
        this.alignment_time = alignment_time;
        this.use_venue = use_venue;
        this.otheruse_venue = otheruse_venue;
        this.isSLmeeting = isSLmeeting;
        this.otherUnits = otherUnits;
        this.attendingLeadercodes = attendingLeadercodes;
        this.attendingLeaderNames = attendingLeaderNames;
        this.recomUnits = recomUnits;
        this.recomUnitNames = recomUnitNames;
        this.oaMeetinfo = oaMeetinfo;
        this.cpBegTime = cpBegTime;
        this.cpEndTime = cpEndTime;
        this.meetingName = meetingName;
        this.isBasicUnit = isBasicUnit;
        this.isStopCar = isStopCar;
    }

    public OaMeetApply(String djId, String applyNo, String depno,
            String creater, Date createtime, String title, String meetingNo,
            Date planBegTime, Date planEndTime, String telephone,
            String meetingPersions, String outerPersions,
            Long meetingPersionsNum, String reqRemark, String remark,
            Date doTime, Date doBegTime, Date doEndTime, String state,
            String doDepno, String doCreater, String doRemark, String isUse,
            Date begTime, Date endTime, String todoremark, Long flowInstId,
            String bizstate, String biztype, String solvestatus,
            Date solvetime, String solvenote, String optid, String flowcode,
            String supDjid, String bjState, String otherItem, String meetType,
            String meeting_rang, String units, String attendingUnits,
            Date alignment_time, String use_venue, String otheruse_venue,
            String isSLmeeting, String otherUnits, String attendingLeadercodes,
            String attendingLeaderNames, String recomUnits,
            String recomUnitNames) {
        super();
        this.djId = djId;
        this.applyNo = applyNo;
        this.depno = depno;
        this.creater = creater;
        this.createtime = createtime;
        this.title = title;
        this.meetingNo = meetingNo;
        this.planBegTime = planBegTime;
        this.planEndTime = planEndTime;
        this.telephone = telephone;
        this.meetingPersions = meetingPersions;
        this.outerPersions = outerPersions;
        this.meetingPersionsNum = meetingPersionsNum;
        this.reqRemark = reqRemark;
        this.remark = remark;
        this.doTime = doTime;
        this.doBegTime = doBegTime;
        this.doEndTime = doEndTime;
        this.state = state;
        this.doDepno = doDepno;
        this.doCreater = doCreater;
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
        this.supDjid = supDjid;
        this.bjState = bjState;
        this.otherItem = otherItem;
        this.meetType = meetType;
        this.meeting_rang = meeting_rang;
        this.units = units;
        this.attendingUnits = attendingUnits;
        this.alignment_time = alignment_time;
        this.use_venue = use_venue;
        this.otheruse_venue = otheruse_venue;
        this.isSLmeeting = isSLmeeting;
        this.otherUnits = otherUnits;
        this.attendingLeadercodes = attendingLeadercodes;
        this.attendingLeaderNames = attendingLeaderNames;
        this.recomUnits = recomUnits;
        this.recomUnitNames = recomUnitNames;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getMeetingPersions() {
        return this.meetingPersions;
    }

    public void setMeetingPersions(String meetingPersions) {
        this.meetingPersions = meetingPersions;
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

    public String getBjState() {
        return bjState;
    }

    public void setBjState(String bjState) {
        this.bjState = bjState;
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

    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public String getMeeting_rang() {
        return meeting_rang;
    }

    public void setMeeting_rang(String meeting_rang) {
        this.meeting_rang = meeting_rang;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getAttendingUnits() {
        return attendingUnits;
    }

    public void setAttendingUnits(String attendingUnits) {
        this.attendingUnits = attendingUnits;
    }

    public Date getAlignment_time() {
        return alignment_time;
    }

    public void setAlignment_time(Date alignment_time) {
        this.alignment_time = alignment_time;
    }

    public String getUse_venue() {
        return use_venue;
    }

    public void setUse_venue(String use_venue) {
        this.use_venue = use_venue;
    }

    public String getOtheruse_venue() {
        return otheruse_venue;
    }

    public void setOtheruse_venue(String otheruse_venue) {
        this.otheruse_venue = otheruse_venue;
    }

    public String getIsSLmeeting() {
        return isSLmeeting;
    }

    public void setIsSLmeeting(String isSLmeeting) {
        this.isSLmeeting = isSLmeeting;
    }

    public String getIsBasicUnit() {
        if(StringUtils.isBlank(this.isBasicUnit)){
            this.isBasicUnit="F";
        }
        
        return isBasicUnit;
    }

    public void setIsBasicUnit(String isBasicUnit) {
        this.isBasicUnit = isBasicUnit;
    }

    public String getIsStopCar() {
        if(StringUtils.isBlank(this.isStopCar)){
            this.isStopCar="F";
        }
        return isStopCar;
    }

    public void setIsStopCar(String isStopCar) {
        this.isStopCar = isStopCar;
    }

    public void copy(OaMeetApply other) {

        this.setDjId(other.getDjId());

        this.applyNo = other.getApplyNo();
        this.depno = other.getDepno();
        this.creater = other.getCreater();
        this.createtime = other.getCreatetime();
        this.title = other.getTitle();
        this.meetingNo = other.getMeetingNo();
        this.planBegTime = other.getPlanBegTime();
        this.planEndTime = other.getPlanEndTime();
        this.telephone = other.getTelephone();
        this.meetingPersions = other.getMeetingPersions();
        this.outerPersions = other.getOuterPersions();
        this.meetingPersionsNum = other.getMeetingPersionsNum();
        this.reqRemark = other.getReqRemark();
        this.remark = other.getRemark();
        this.doTime = other.getDoTime();
        this.doBegTime = other.getDoBegTime();
        this.doEndTime = other.getDoEndTime();
        this.state = other.getState();
        this.doDepno = other.getDoDepno();
        this.doCreater = other.getDoCreater();
        this.doRemark = other.getDoRemark();
        this.isUse = other.getIsUse();
        this.begTime = other.getBegTime();
        this.endTime = other.getEndTime();
        this.todoremark = other.getTodoremark();
        this.flowInstId = other.getFlowInstId();
        this.bizstate = other.getBizstate();
        this.biztype = other.getBiztype();
        this.solvestatus = other.getSolvestatus();
        this.solvetime = other.getSolvetime();
        this.solvenote = other.getSolvenote();
        this.optid = other.getOptid();
        this.flowcode = other.getFlowcode();
        this.supDjid = other.getSupDjid();
        this.otherItem = other.getOtherItem();
        this.addressBookRelections = other.getAddressBookRelections();
        this.meetType = other.getMeetType();
        this.meeting_rang = other.getMeeting_rang();
        this.units = other.getUnits();
        this.attendingUnits = other.getAttendingUnits();
        this.alignment_time = other.getAlignment_time();
        this.use_venue = other.getUse_venue();
        this.otheruse_venue = other.getOtheruse_venue();
        this.isSLmeeting = other.getIsSLmeeting();

        this.otherUnits = other.getOtherUnits();
        this.attendingLeadercodes = other.getAttendingLeadercodes();
        this.attendingLeaderNames = other.getAttendingLeaderNames();
        this.recomUnits = other.getRecomUnits();
        this.recomUnitNames = other.getRecomUnitNames();
        this.isBasicUnit=other.getIsBasicUnit();
        this.isStopCar=other.getIsStopCar();

    }

    public void copyNotNullProperty(OaMeetApply other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getApplyNo() != null)
            this.applyNo = other.getApplyNo();
        if (other.getDepno() != null)
            this.depno = other.getDepno();
        if (other.getCreater() != null)
            this.creater = other.getCreater();
        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getTitle() != null)
            this.title = other.getTitle();
        if (other.getMeetingNo() != null)
            this.meetingNo = other.getMeetingNo();
        if (other.getPlanBegTime() != null)
            this.planBegTime = other.getPlanBegTime();
        if (other.getPlanEndTime() != null)
            this.planEndTime = other.getPlanEndTime();
        if (other.getTelephone() != null)
            this.telephone = other.getTelephone();
        if (other.getMeetingPersions() != null)
            this.meetingPersions = other.getMeetingPersions();
        if (other.getOuterPersions() != null)
            this.outerPersions = other.getOuterPersions();
        if (other.getMeetingPersionsNum() != null)
            this.meetingPersionsNum = other.getMeetingPersionsNum();
        if (other.getReqRemark() != null)
            this.reqRemark = other.getReqRemark();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getDoTime() != null)
            this.doTime = other.getDoTime();
        if (other.getDoBegTime() != null)
            this.doBegTime = other.getDoBegTime();
        if (other.getDoEndTime() != null)
            this.doEndTime = other.getDoEndTime();
        if (other.getState() != null)
            this.state = other.getState();
        if (other.getDoDepno() != null)
            this.doDepno = other.getDoDepno();
        if (other.getDoCreater() != null)
            this.doCreater = other.getDoCreater();
        if (other.getDoRemark() != null)
            this.doRemark = other.getDoRemark();
        if (other.getIsUse() != null)
            this.isUse = other.getIsUse();
        if (other.getBegTime() != null)
            this.begTime = other.getBegTime();
        if (other.getEndTime() != null)
            this.endTime = other.getEndTime();
        if (other.getTodoremark() != null)
            this.todoremark = other.getTodoremark();
        if (other.getFlowInstId() != null)
            this.flowInstId = other.getFlowInstId();
        if (other.getBizstate() != null)
            this.bizstate = other.getBizstate();
        if (other.getBiztype() != null)
            this.biztype = other.getBiztype();
        if (other.getSolvestatus() != null)
            this.solvestatus = other.getSolvestatus();
        if (other.getSolvetime() != null)
            this.solvetime = other.getSolvetime();
        if (other.getSolvenote() != null)
            this.solvenote = other.getSolvenote();
        if (other.getOptid() != null)
            this.optid = other.getOptid();
        if (other.getFlowcode() != null)
            this.flowcode = other.getFlowcode();
        if (other.getSupDjid() != null)
            this.supDjid = other.getSupDjid();
        if (other.getOtherItem() != null)
            this.otherItem = other.getOtherItem();

        if (other.getMeetType() != null)
            this.meetType = other.getMeetType();
        if (other.getMeeting_rang() != null)
            this.meeting_rang = other.getMeeting_rang();
        if (other.getAttendingUnits() != null)
            this.attendingUnits = other.getAttendingUnits();
        if (other.getUnits() != null)
            this.units = other.getUnits();
        if (other.getUse_venue() != null)
            this.use_venue = other.getUse_venue();
        if (other.getAlignment_time() != null)
            this.alignment_time = other.getAlignment_time();
        if (other.getOtheruse_venue() != null)
            this.otheruse_venue = other.getOtheruse_venue();
        if (other.getIsSLmeeting() != null)
            this.isSLmeeting = other.getIsSLmeeting();

        if (other.getOtherUnits() != null)
            this.otherUnits = other.getOtherUnits();
        if (other.getAttendingLeadercodes() != null)
            this.attendingLeadercodes = other.getAttendingLeadercodes();
        if (other.getAttendingLeaderNames() != null)
            this.attendingLeaderNames = other.getAttendingLeaderNames();
        if (other.getRecomUnits() != null)
            this.recomUnits = other.getRecomUnits();
        if (other.getRecomUnitNames() != null)
            this.recomUnitNames = other.getRecomUnitNames();
        if (other.getIsBasicUnit() != null)
        this.isBasicUnit=other.getIsBasicUnit();
        if (other.getIsStopCar() != null)
        this.isStopCar=other.getIsStopCar();
    }

    // 清理为一般会议室时 视频会议的数据
    public void clearTVProperties() {
        this.meeting_rang = null;
        this.units = null;
        // this.attendingUnits= null;
        this.alignment_time = null;
        this.use_venue = null;
        this.otheruse_venue = null;
        this.isSLmeeting = null;
    }

    public void clearProperties() {

        this.applyNo = null;
        this.depno = null;
        this.creater = null;
        this.createtime = null;
        this.title = null;
        this.meetingNo = null;
        this.planBegTime = null;
        this.planEndTime = null;
        this.telephone = null;
        this.meetingPersions = null;
        this.outerPersions = null;
        this.meetingPersionsNum = null;
        this.reqRemark = null;
        this.remark = null;
        this.doTime = null;
        this.doBegTime = null;
        this.doEndTime = null;
        this.state = null;
        this.doDepno = null;
        this.doCreater = null;
        this.doRemark = null;
        this.isUse = null;
        this.begTime = null;
        this.endTime = null;
        this.todoremark = null;
        this.flowInstId = null;
        this.bizstate = null;
        this.biztype = null;
        this.solvestatus = null;
        this.solvetime = null;
        this.solvenote = null;
        this.optid = null;
        this.flowcode = null;
        this.supDjid = null;
        this.otherItem = null;
        this.meetType = null;
        this.meeting_rang = null;
        this.units = null;
        this.attendingUnits = null;
        this.alignment_time = null;
        this.use_venue = null;
        this.otheruse_venue = null;
        this.isSLmeeting = null;
        this.addressBookRelections = new HashSet<AddressBookRelection>();
        this.otherUnits = null;
        this.attendingLeadercodes = null;
        this.attendingLeaderNames = null;
        this.recomUnits = null;
        this.recomUnitNames = null;
        this.isBasicUnit=null;
        this.isStopCar=null;
    }

    public Set<AddressBookRelection> getAddressBookRelections() {
        if (this.addressBookRelections == null)
            this.addressBookRelections = new HashSet<AddressBookRelection>();
        return this.addressBookRelections;
    }

    public void setAddressBookRelections(
            Set<AddressBookRelection> addressBookRelections) {
        this.addressBookRelections = addressBookRelections;
    }

    public void addAddressBookRelection(
            AddressBookRelection addressBookRelection) {
        if (this.addressBookRelections == null)
            this.addressBookRelections = new HashSet<AddressBookRelection>();
        this.addressBookRelections.add(addressBookRelection);
    }

    public void removeAddressBookRelection(
            AddressBookRelection addressBookRelection) {
        if (this.addressBookRelections == null)
            return;
        this.addressBookRelections.remove(addressBookRelection);
    }

    public AddressBookRelection newAddressBookRelection() {
        AddressBookRelection res = new AddressBookRelection();

        res.setAddrbookid(this.getDjId());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
     * 
     */
    public void replaceAddressBookRelections(
            List<AddressBookRelection> addressBookRelections) {
        List<AddressBookRelection> newObjs = new ArrayList<AddressBookRelection>();
        for (AddressBookRelection p : addressBookRelections) {
            if (p == null)
                continue;
            AddressBookRelection newdt = newAddressBookRelection();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<AddressBookRelection> oldObjs = new HashSet<AddressBookRelection>();
        oldObjs.addAll(getAddressBookRelections());

        for (Iterator<AddressBookRelection> it = oldObjs.iterator(); it
                .hasNext();) {
            AddressBookRelection odt = it.next();
            found = false;
            for (AddressBookRelection newdt : newObjs) {
                if (odt.getCid().equals(newdt.getCid())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeAddressBookRelection(odt);
        }
        oldObjs.clear();
        // insert or update
        for (AddressBookRelection newdt : newObjs) {
            found = false;
            for (Iterator<AddressBookRelection> it = getAddressBookRelections()
                    .iterator(); it.hasNext();) {
                AddressBookRelection odt = it.next();
                if (odt.getCid().equals(newdt.getCid())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addAddressBookRelection(newdt);
        }
    }

    public String getSupDjid() {
        return supDjid;
    }

    public void setSupDjid(String supDjid) {
        this.supDjid = supDjid;
    }

    public String getOtherItem() {
        return otherItem;
    }

    public void setOtherItem(String otherItem) {
        this.otherItem = otherItem;
    }

    public String getOtherUnits() {
        return otherUnits;
    }

    public void setOtherUnits(String otherUnits) {
        this.otherUnits = otherUnits;
    }

    public String getAttendingLeadercodes() {
        return attendingLeadercodes;
    }

    public void setAttendingLeadercodes(String attendingLeadercodes) {
        this.attendingLeadercodes = attendingLeadercodes;
    }

    public String getAttendingLeaderNames() {
        return attendingLeaderNames;
    }

    public void setAttendingLeaderNames(String attendingLeaderNames) {
        this.attendingLeaderNames = attendingLeaderNames;
    }

    public String getRecomUnits() {
        return recomUnits;
    }

    public void setRecomUnits(String recomUnits) {
        this.recomUnits = recomUnits;
    }

    public String getRecomUnitNames() {
        return recomUnitNames;
    }

    public void setRecomUnitNames(String recomUnitNames) {
        this.recomUnitNames = recomUnitNames;
    }

    public OaMeetinfo getOaMeetinfo() {
        return oaMeetinfo;
    }

    public void setOaMeetinfo(OaMeetinfo oaMeetinfo) {
        this.oaMeetinfo = oaMeetinfo;
    }

    public Date getCpBegTime() {
        return cpBegTime;
    }

    public void setCpBegTime(Date cpBegTime) {
        this.cpBegTime = cpBegTime;
    }

    public Date getCpEndTime() {
        return cpEndTime;
    }

    public void setCpEndTime(Date cpEndTime) {
        this.cpEndTime = cpEndTime;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
    
    
}
