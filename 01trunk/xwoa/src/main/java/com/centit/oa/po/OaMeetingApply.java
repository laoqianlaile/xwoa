package com.centit.oa.po;

import java.util.Date;

public class OaMeetingApply implements java.io.Serializable{

    /**
     * 会议
     */
    private static final long serialVersionUID = 1L;
    
    
    private String mId;

    private String meetApplyName;//会议名称
    
    private String meetApplyAddress;//会议地点
    
    private String attendLeaderCode;//参会领导
    
    private String attendLeaderName;//
    
    private String attendUnitCode;//参会部门
    
    private String attendUnitName;
    
    private String creater;//创建者
    
    private Date createtime;//创建时间
    
    private Date motifytime;//修改时间
    
    private Date meetApplytime;//会议时间
    
    private String foreigUserName;//外来人员
    
    private String foreigUnitName;//外来部门
    
    private String meetingRemark;//备注
    
    private String releteCode;//关联议程id
    
    private String releteName;//关联议程name
    
    public void clearProperties() {

        this.mId = null;
        this.meetApplyName = null;
        this.meetApplyAddress = null;
        this.attendLeaderCode = null;
        this.attendLeaderName = null;
        this.attendUnitCode = null;
        this.attendUnitName = null;
        this.creater = null;
        this.createtime = null;
        this.foreigUnitName = null;
        this.motifytime = null;
        this.foreigUserName = null;
        this.meetingRemark = null;
        this.releteCode = null;
        this.releteName = null;
        this.meetApplytime=null;
    }
    
    public void copy(OaMeetingApply other) {

        this.setMId(other.getMId());

        this.meetApplyName = other.getMeetApplyName();
        this.meetApplytime = other.getMeetApplytime();
        this.meetApplyAddress = other.getMeetApplyAddress();
        this.attendLeaderCode = other.getAttendLeaderCode();
        this.attendLeaderName = other.getAttendLeaderName();
        this.attendUnitCode = other.getAttendUnitCode();
        this.attendUnitName = other.getAttendUnitName();
        this.creater = other.getCreater();
        this.createtime = other.getCreatetime();
        this.foreigUnitName = other.getForeigUnitName();
        this.motifytime = other.getMotifytime();
        this.foreigUserName = other.getForeigUserName();
        this.meetingRemark = other.getMeetingRemark();
        this.releteCode = other.getReleteCode();
        this.releteName = other.getReleteName();
        

    }

    public void copyNotNullProperty(OaMeetingApply other) {

        if (other.getMId() != null)
            this.setMId(other.getMId());
        if(other.getMeetApplytime() !=null)
            this.setMeetApplytime(other.getMeetApplytime());
        if (other.getMeetApplyName() != null)
            this.meetApplyName = other.getMeetApplyName();
        if (other.getMeetApplyAddress() != null)
            this.meetApplyAddress = other.getMeetApplyAddress();
        if (other.getAttendLeaderCode() != null)
            this.attendLeaderCode = other.getAttendLeaderCode();
        if(other.getAttendLeaderName() != null)
            this.attendLeaderName = other.getAttendLeaderName();
        if(other.getAttendUnitCode() != null)
            this.attendUnitCode = other.getAttendUnitCode();
        if (other.getAttendUnitName() != null)
            this.attendUnitName = other.getAttendUnitName();
        if (other.getCreater() != null)
            this.creater = other.getCreater();
        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getForeigUnitName() != null)
            this.foreigUnitName = other.getForeigUnitName();
        if (other.getMeetApplyAddress() != null)
            this.meetApplyAddress = other.getMeetApplyAddress();
        if (other.getMotifytime() != null)
            this.motifytime = other.getMotifytime();
        if (other.getForeigUserName() != null)
            this.foreigUserName = other.getForeigUserName();
        if (other.getMeetingRemark() != null)
            this.meetingRemark = other.getMeetingRemark();
        if (other.getReleteCode() != null)
            this.releteCode = other.getReleteCode();
        if (other.getReleteName() != null)
            this.releteName = other.getReleteName();

    }
    
    // Constructors
    /** default constructor */
    public OaMeetingApply() {
    }

    /** minimal constructor */
    public OaMeetingApply(String mId) {

        this.mId = mId;

    }
    
    public OaMeetingApply(String mId, String meetApplyName,
            String meetApplyAddress, String attendLeaderCode,
            String attendLeaderName, String attendUnitCode,
            String attendUnitName, String creater, Date createtime,
            Date motifytime, Date meetApplytime, String foreigUserName,
            String foreigUnitName, String meetingRemark, String releteCode,
            String releteName) {
        super();
        this.mId = mId;
        this.meetApplyName = meetApplyName;
        this.meetApplyAddress = meetApplyAddress;
        this.attendLeaderCode = attendLeaderCode;
        this.attendLeaderName = attendLeaderName;
        this.attendUnitCode = attendUnitCode;
        this.attendUnitName = attendUnitName;
        this.creater = creater;
        this.createtime = createtime;
        this.motifytime = motifytime;
        this.meetApplytime = meetApplytime;
        this.foreigUserName = foreigUserName;
        this.foreigUnitName = foreigUnitName;
        this.meetingRemark = meetingRemark;
        this.releteCode = releteCode;
        this.releteName = releteName;
    }

    public String getAttendLeaderCode() {
        return attendLeaderCode;
    }

    public void setAttendLeaderCode(String attendLeaderCode) {
        this.attendLeaderCode = attendLeaderCode;
    }

    public String getAttendLeaderName() {
        return attendLeaderName;
    }

    public void setAttendLeaderName(String attendLeaderName) {
        this.attendLeaderName = attendLeaderName;
    }

    public String getAttendUnitCode() {
        return attendUnitCode;
    }

    public void setAttendUnitCode(String attendUnitCode) {
        this.attendUnitCode = attendUnitCode;
    }

    public String getAttendUnitName() {
        return attendUnitName;
    }

    public void setAttendUnitName(String attendUnitName) {
        this.attendUnitName = attendUnitName;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getMotifytime() {
        return motifytime;
    }

    public void setMotifytime(Date motifytime) {
        this.motifytime = motifytime;
    }

    public String getForeigUserName() {
        return foreigUserName;
    }

    public void setForeigUserName(String foreigUserName) {
        this.foreigUserName = foreigUserName;
    }

    public String getForeigUnitName() {
        return foreigUnitName;
    }

    public void setForeigUnitName(String foreigUnitName) {
        this.foreigUnitName = foreigUnitName;
    }

    public String getMeetingRemark() {
        return meetingRemark;
    }

    public void setMeetingRemark(String meetingRemark) {
        this.meetingRemark = meetingRemark;
    }

    public String getReleteCode() {
        return releteCode;
    }

    public void setReleteCode(String releteCode) {
        this.releteCode = releteCode;
    }

    public String getReleteName() {
        return releteName;
    }

    public void setReleteName(String releteName) {
        this.releteName = releteName;
    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getMeetApplyName() {
        return meetApplyName;
    }

    public void setMeetApplyName(String meetApplyName) {
        this.meetApplyName = meetApplyName;
    }

    public String getMeetApplyAddress() {
        return meetApplyAddress;
    }

    public void setMeetApplyAddress(String meetApplyAddress) {
        this.meetApplyAddress = meetApplyAddress;
    }

    public Date getMeetApplytime() {
        return meetApplytime;
    }

    public void setMeetApplytime(Date meetApplytime) {
        this.meetApplytime = meetApplytime;
    }
    
}
