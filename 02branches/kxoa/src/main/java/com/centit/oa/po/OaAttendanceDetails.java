package com.centit.oa.po;

import java.util.Date;

import com.centit.app.po.ForumStaff;
import com.centit.app.po.ForumStaffId;

public class OaAttendanceDetails implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private OaAttendanceDetailsId cid;
    private String state;
    private String holidayType;

    
    public OaAttendanceDetailsId getCid() {
        return cid;
    }

    public void setCid(OaAttendanceDetailsId cid) {
        this.cid = cid;
    }

    public String getUserid() {
        if(this.cid==null)
            this.cid = new OaAttendanceDetailsId();
        return this.cid.getUserid();
    }

    public void setUserid(String userid) {
        if(this.cid==null)
            this.cid = new OaAttendanceDetailsId();
        this.cid.setUserid(userid);
    }

    public Date getAttendanceDate() {
        if(this.cid==null)
            this.cid = new OaAttendanceDetailsId();
        return this.cid.getAttendanceDate();
    }

    public void setAttendanceDate(Date attendanceDate) {
        if(this.cid==null)
            this.cid = new OaAttendanceDetailsId();
        this.cid.setAttendanceDate(attendanceDate);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    // Constructors
    /** default constructor */
    public OaAttendanceDetails() {
    }

    /** minimal constructor */
    public OaAttendanceDetails(OaAttendanceDetailsId id ) {
            this.cid = id; 
        }

    /** full constructor */
    public OaAttendanceDetails(OaAttendanceDetailsId id, String state, String holidayType) {

        this.cid = id;
        this.state = state;
        this.holidayType = holidayType;
       
    }

    

    public OaAttendanceDetails copy(OaAttendanceDetails other) {

        this.setUserid(other.getUserid());
        this.setAttendanceDate(other.getAttendanceDate());
        this.state = other.getState();
        this.holidayType = other.getHolidayType();
        return this;
    }

    public OaAttendanceDetails copyNotNullProperty(OaAttendanceDetails other) {
        if (other.getUserid() != null)
            this.setUserid(other.getUserid());
        if (other.getAttendanceDate() != null)
            this.setAttendanceDate(other.getAttendanceDate());
        if (other.getState() != null)
            this.state = other.getState();
        if (other.getHolidayType() != null)
            this.holidayType = other.getHolidayType();
       
        return this;
    }

    public OaAttendanceDetails clearProperties() {
        this.state = null;
        this.holidayType = null;
        
        return this;
    }
    
    @Override
    public int hashCode() {
        return cid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OaAttendanceDetails)) {
            return false;
        }
        return cid.equals(((OaAttendanceDetails)obj).getCid());
    }
}
