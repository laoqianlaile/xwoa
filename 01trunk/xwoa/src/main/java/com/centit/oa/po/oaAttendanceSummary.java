package com.centit.oa.po;

import java.util.List;


/**
 * create by scaffold
 */ 

public class oaAttendanceSummary implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
    private String usercode;//用户编号
    private List<OaLeaveOverTime> oaLeaveOverTime;
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public List<OaLeaveOverTime> getOaLeaveOverTime() {
        return oaLeaveOverTime;
    }
    public void setOaLeaveOverTime(List<OaLeaveOverTime> oaLeaveOverTime) {
        this.oaLeaveOverTime = oaLeaveOverTime;
    }
    public oaAttendanceSummary(String usercode,
            List<OaLeaveOverTime> oaLeaveOverTime) {
        super();
        this.usercode = usercode;
        this.oaLeaveOverTime = oaLeaveOverTime;
    }
    public oaAttendanceSummary() {
        super();
        // TODO Auto-generated constructor stub
    }
    
}
