package com.centit.oa.po;

import java.util.Date;

public class OaLeaveOver {

    private String usercode;
    private Date attendancedate;
    private String state;
    private int daynum;
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public Date getAttendancedate() {
        return attendancedate;
    }
    public void setAttendancedate(Date attendancedate) {
        this.attendancedate = attendancedate;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getDaynum() {
        return daynum;
    }
    public void setDaynum(int daynum) {
        this.daynum = daynum;
    }
    
    
}
