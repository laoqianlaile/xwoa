package com.centit.sys.po;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.centit.sys.service.CodeRepositoryUtil;

/**
 * FUserinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
// 系统用户信息表
public class FUserForgetPassword implements java.io.Serializable {

    private static final long serialVersionUID =  1L;
    
    private String djId;//序列号
    private String usercode; //用户标识
    private String telephone; //用户系统内预留手机号
    private String ip; //记录ip
    private String state; //重置密码进度  0 发起重置申请 1 短信验证通过   2重置完成 3因未知因素重置失败 4短信验证码失效（同一页面支持再次发起短信
 

    private Date forgettime; //重置密码   发起重置申请时间
    private String validatenum; //短信验证码'
    private Date validatetime;//短信验证通过时间
    
    
    private String password;//密码
    
    public FUserForgetPassword() {
        super();
    }

    public FUserForgetPassword(String djId, String usercode, String telephone,
            String ip, String state, Date forgettime, String validatenum,
            Date validatetime) {
        this.djId = djId;
        this.usercode = usercode;
        this.telephone = telephone;
        this.ip = ip;
        this.state = state;
        this.forgettime = forgettime;
        this.validatenum = validatenum;
        this.validatetime = validatetime;
    }
    
    public void copyNotNullProperty(FUserForgetPassword other) {
        if (other.getDjId() != null)
            this.djId = other.getDjId();
        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getTelephone() != null)
            this.telephone = other.getTelephone();
        if (other.getIp() != null)
            this.ip = other.getIp();
        if (other.getState() != null)
            this.state = other.getState();
        if (other.getForgettime() != null)
            this.forgettime = other.getForgettime();
        if (other.getValidatenum() != null)
            this.validatenum = other.getValidatenum();
        if (other.getValidatetime() != null)
            this.validatetime = other.getValidatetime();
    }
    
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Date getForgettime() {
        return forgettime;
    }
    public void setForgettime(Date forgettime) {
        this.forgettime = forgettime;
    }
    public String getValidatenum() {
        return validatenum;
    }
    public void setValidatenum(String validatenum) {
        this.validatenum = validatenum;
    }
    public Date getValidatetime() {
        return validatetime;
    }
    public void setValidatetime(Date validatetime) {
        this.validatetime = validatetime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  

   
}
