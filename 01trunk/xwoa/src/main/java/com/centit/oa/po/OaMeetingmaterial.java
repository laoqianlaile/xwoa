package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaMeetingmaterial implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;
    private String mId;
    private Long orderId;
    private String meetingName;
    private String meetingAgenda;
    private String reportName;//汇报人
    private String meetingProxys;
    private String meetingProxyCodes;
    private String meetingAttendees;
    private String meetingAttendeesCodes;
    private String meetingUnitnames;
    private String meetingComein;
    private String meetingComeindeps;
    private String meetingAddress;
    private Date meetingTime;
    private String meetingRemark;
    private String creater;
    private Date createtime;
    private Date motifytime;
    private String isuse;
    private String flagRight;//是否有查看议程详情的权限
    
  
    public String getMeetingProxys() {
        return meetingProxys;
    }

    public void setMeetingProxys(String meetingProxys) {
        this.meetingProxys = meetingProxys;
    }

    public String getMeetingProxyCodes() {
        return meetingProxyCodes;
    }

    public void setMeetingProxyCodes(String meetingProxyCodes) {
        this.meetingProxyCodes = meetingProxyCodes;
    }

    // Constructors
    /** default constructor */
    public OaMeetingmaterial() {
    }

    /** minimal constructor */
    public OaMeetingmaterial(String djId) {

        this.djId = djId;

    }
  
    public OaMeetingmaterial(String djId, String mId,Long orderId,String meetingName,
            String meetingAgenda, String reportName,String meetingProxys,
            String meetingProxyCodes, String meetingAttendees,
            String meetingAttendeesCodes, String meetingUnitnames,
            String meetingComein, String meetingComeindeps,
            String meetingAddress, Date meetingTime, String meetingRemark,
            String creater, Date createtime, Date motifytime, String isuse) {
        super();
        this.djId = djId;
        this.mId = mId;
        this.reportName=reportName;
        this.orderId = orderId;
        this.meetingName = meetingName;
        this.meetingAgenda = meetingAgenda;
        this.meetingProxys = meetingProxys;
        this.meetingProxyCodes = meetingProxyCodes;
        this.meetingAttendees = meetingAttendees;
        this.meetingAttendeesCodes = meetingAttendeesCodes;
        this.meetingUnitnames = meetingUnitnames;
        this.meetingComein = meetingComein;
        this.meetingComeindeps = meetingComeindeps;
        this.meetingAddress = meetingAddress;
        this.meetingTime = meetingTime;
        this.meetingRemark = meetingRemark;
        this.creater = creater;
        this.createtime = createtime;
        this.motifytime = motifytime;
        this.isuse = isuse;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors

    public String getMeetingName() {
        return this.meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingAgenda() {
        return this.meetingAgenda;
    }

    public void setMeetingAgenda(String meetingAgenda) {
        this.meetingAgenda = meetingAgenda;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getMeetingAttendees() {
        return this.meetingAttendees;
    }

    public void setMeetingAttendees(String meetingAttendees) {
        this.meetingAttendees = meetingAttendees;
    }

    public String getMeetingUnitnames() {
        return this.meetingUnitnames;
    }

    public void setMeetingUnitnames(String meetingUnitnames) {
        this.meetingUnitnames = meetingUnitnames;
    }

    public String getMeetingComein() {
        return this.meetingComein;
    }

    public void setMeetingComein(String meetingComein) {
        this.meetingComein = meetingComein;
    }

    public String getMeetingComeindeps() {
        return this.meetingComeindeps;
    }

    public void setMeetingComeindeps(String meetingComeindeps) {
        this.meetingComeindeps = meetingComeindeps;
    }

    public String getMeetingAddress() {
        return this.meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public Date getMeetingTime() {
        return this.meetingTime;
    }

    public void setMeetingTime(Date meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingRemark() {
        return this.meetingRemark;
    }

    public void setMeetingRemark(String meetingRemark) {
        this.meetingRemark = meetingRemark;
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

    public Date getMotifytime() {
        return this.motifytime;
    }

    public void setMotifytime(Date motifytime) {
        this.motifytime = motifytime;
    }

    public String getIsuse() {
        return this.isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getMeetingAttendeesCodes() {
        return meetingAttendeesCodes;
    }

    public void setMeetingAttendeesCodes(String meetingAttendeesCodes) {
        this.meetingAttendeesCodes = meetingAttendeesCodes;
    }

    public void copy(OaMeetingmaterial other) {

        this.setDjId(other.getDjId());

        this.meetingName = other.getMeetingName();
        this.mId = other.getMId();
        this.orderId = other.getOrderId();
        this.meetingAgenda = other.getMeetingAgenda();
        this.reportName = other.getReportName();
        this.meetingAttendees = other.getMeetingAttendees();
        this.meetingProxys = other.getMeetingProxys();
        this.meetingProxyCodes = other.getMeetingProxyCodes();
        this.meetingAttendeesCodes = other.getMeetingAttendeesCodes();
        this.meetingUnitnames = other.getMeetingUnitnames();
        this.meetingComein = other.getMeetingComein();
        this.meetingComeindeps = other.getMeetingComeindeps();
        this.meetingAddress = other.getMeetingAddress();
        this.meetingTime = other.getMeetingTime();
        this.meetingRemark = other.getMeetingRemark();
        this.creater = other.getCreater();
        this.createtime = other.getCreatetime();
        this.motifytime = other.getMotifytime();
        this.isuse = other.getIsuse();

    }

    public void copyNotNullProperty(OaMeetingmaterial other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getMeetingName() != null)
            this.meetingName = other.getMeetingName();
        if(other.getMId() != null)
            this.mId = other.getMId();
        if(other.getOrderId() != null)
            this.orderId = other.getOrderId();
        if (other.getMeetingAgenda() != null)
            this.meetingAgenda = other.getMeetingAgenda();
        if(other.getReportName() != null)
            this.reportName = other.getReportName();
        if (other.getMeetingAttendees() != null)
            this.meetingAttendees = other.getMeetingAttendees();
        if(other.getMeetingAddress() != null)
            this.meetingProxys = other.getMeetingProxys();
        if(other.getMeetingProxyCodes() != null)
            this.meetingProxyCodes = other.getMeetingProxyCodes();
        if (other.getMeetingAttendeesCodes() != null)
            this.meetingAttendeesCodes = other.getMeetingAttendeesCodes();
        if (other.getMeetingUnitnames() != null)
            this.meetingUnitnames = other.getMeetingUnitnames();
        if (other.getMeetingComein() != null)
            this.meetingComein = other.getMeetingComein();
        if (other.getMeetingComeindeps() != null)
            this.meetingComeindeps = other.getMeetingComeindeps();
        if (other.getMeetingAddress() != null)
            this.meetingAddress = other.getMeetingAddress();
        if (other.getMeetingTime() != null)
            this.meetingTime = other.getMeetingTime();
        if (other.getMeetingRemark() != null)
            this.meetingRemark = other.getMeetingRemark();
        if (other.getCreater() != null)
            this.creater = other.getCreater();
        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getMotifytime() != null)
            this.motifytime = other.getMotifytime();
        if (other.getIsuse() != null)
            this.isuse = other.getIsuse();

    }

    public void clearProperties() {

        this.meetingName = null;
        this.mId =null;
        this.orderId = null;
        this.meetingAgenda = null;
        this.reportName = null;
        this.meetingAttendees = null;
        this.meetingProxys = null;
        this.meetingProxyCodes = null;
        this.meetingAttendeesCodes = null;
        this.meetingUnitnames = null;
        this.meetingComein = null;
        this.meetingComeindeps = null;
        this.meetingAddress = null;
        this.meetingTime = null;
        this.meetingRemark = null;
        this.creater = null;
        this.createtime = null;
        this.motifytime = null;
        this.isuse = null;

    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }
    
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
    
    public String getFlagRight() {
        return flagRight;
    }

    public void setFlagRight(String flagRight) {
        this.flagRight = flagRight;
    }

    
}
