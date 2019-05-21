package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VOaMeetingMaterialApply implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String mId;
    private String djId;
    private String meetingName;
    private String meetingAttendeescodes;
    private String meetingAttendees;
    private Date  meetingTime;
    private String meetApplyName;
    private String meetApplyAddress;
    private String attendLeaderCode;
    private Date meetApplyTime;
    public String getmId() {
        return mId;
    }
    public void setmId(String mId) {
        this.mId = mId;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getMeetingName() {
        return meetingName;
    }
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
    public String getMeetingAttendeescodes() {
        return meetingAttendeescodes;
    }
    public void setMeetingAttendeescodes(String meetingAttendeescodes) {
        this.meetingAttendeescodes = meetingAttendeescodes;
    }
    public Date getMeetingTime() {
        return meetingTime;
    }
    public void setMeetingTime(Date meetingTime) {
        this.meetingTime = meetingTime;
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
    public String getAttendLeaderCode() {
        return attendLeaderCode;
    }
    public void setAttendLeaderCode(String attendLeaderCode) {
        this.attendLeaderCode = attendLeaderCode;
    }
    public Date getMeetApplyTime() {
        return meetApplyTime;
    }
    public void setMeetApplyTime(Date meetApplyTime) {
        this.meetApplyTime = meetApplyTime;
    }
    public String getMeetingAttendees() {
        return meetingAttendees;
    }
    public void setMeetingAttendees(String meetingAttendees) {
        this.meetingAttendees = meetingAttendees;
    }
   
}
