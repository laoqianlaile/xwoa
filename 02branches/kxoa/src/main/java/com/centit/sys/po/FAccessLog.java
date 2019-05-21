package com.centit.sys.po;

import java.util.Date;


/**
 * Created by lay on 2015-12-9.
 */
public class FAccessLog {
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
    private long id;
    private String usercode;
    private Date accesstime;
    private String ip;
    /**
     * 1-登录 2-退出 3-操作
     */
    private String accesstype;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype;
    }
}
