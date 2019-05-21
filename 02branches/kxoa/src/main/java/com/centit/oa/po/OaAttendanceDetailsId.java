package com.centit.oa.po;


import java.util.Date;

/**
 * FAddressBook entity.
 * 
 * @author codefan@hotmail.com
 */ 

public class OaAttendanceDetailsId implements java.io.Serializable {
    private static final long serialVersionUID =  1L;

    private String userid;
    private Date attendanceDate;

    // Constructors
    /** default constructor */
    public OaAttendanceDetailsId() {
    }
    /** full constructor */
    public OaAttendanceDetailsId(String userid, Date attendanceDate) {

        this.userid = userid;
        this.attendanceDate = attendanceDate;   
    }
    
    
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public Date getAttendanceDate() {
        return attendanceDate;
    }
    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
    
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof OaAttendanceDetailsId))
            return false;
        
        OaAttendanceDetailsId castOther = (OaAttendanceDetailsId) other;
        boolean ret = true;
  
        ret = ret && ( this.getUserid() == castOther.getUserid() ||
                       (this.getUserid() != null && castOther.getUserid() != null
                               && this.getUserid().equals(castOther.getUserid())));
  
        ret = ret && ( this.getAttendanceDate() == castOther.getAttendanceDate() ||
                       (this.getAttendanceDate() != null && castOther.getAttendanceDate() != null
                               && this.getAttendanceDate().equals(castOther.getAttendanceDate())));

        return ret;
    }
    
    public int hashCode() {
        int result = 17;
  
        result = 37 * result +
            (this.getUserid() == null ? 0 :this.getUserid().hashCode());
  
        result = 37 * result +
            (this.getAttendanceDate() == null ? 0 :this.getAttendanceDate().hashCode());
    
        return result;
    }
}
