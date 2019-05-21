package com.centit.sys.po;

import java.util.Date;


/**
 * Created by lay on 2015-12-9.
 */
public class VUserOnline {
    /**
     * 登录
     */
    public static final String ACCESS_LOGINING = "1";
    /**
     * 退出
     */
    public static final String ACCESS_LOGOUTING = "2";
    /**
     * 普通操作
     */
    public static final String ACCESS_OPERING = "3";
    /**
     * 异常退出
     */
    public static final String ACCESS_LOGOUT_ABNORMAL = "4";
    private String unitcode;
    private String unitname;
    private String usercode;
    private String username;
    private String userdesc;
    private String userstate;
    private Date accesstime;
    
    
    public String getUnitcode() {
        return unitcode;
    }
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
    public String getUnitname() {
        return unitname;
    }
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserdesc() {
        return userdesc;
    }
    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }
    public Date getAccesstime() {
        return accesstime;
    }
    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }
    public String getUserstate() {
        return userstate;
    }
    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }
    
    
    
}
