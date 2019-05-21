package com.centit.mip.po;

import com.centit.sys.po.FUserinfo;
import com.centit.webservice.util.JsonUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * FUserinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
// 系统用户信息表
public class FUserinfoMIP implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = -1753127177790732963L;
    @Expose
    @Since(2.0)
    private String usercode; // 用户代码
    @Expose
    @Since(2.0)
    private String loginname; // 用户登录名
    @Expose
    @Since(2.0)
    private String username; // 用户姓名
    @Expose
    @Since(2.0)
    private String userdesc; // 用户描述
    @Expose
    @Since(2.0)
    private String orgCode; // 用户的主机构，只有在数据字典中有效
    @Expose
    @Since(2.0)
    private String unitName;// 单位名称
    @Expose
    @Since(2.0)
    private String officePhone;// 办公电话
    @Expose
    @Since(2.0)
    private String userState;// 审核状态
    @Expose
    @Since(2.0)
    private String userorder;     //用户排序
    @Expose
    @Since(2.0)
    private String isvalid; // 状态
    
    

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
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

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserorder() {
        return userorder;
    }

    public void setUserorder(String userorder) {
        this.userorder = userorder;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public void copyNotNullProperty(FUserinfo other) {
        this.setUsercode(JsonUtil.replaceNullString(other.getUsercode()));
        this.setLoginname(JsonUtil
                .replaceNullString(other.getLoginname()));
        this.setUsername(JsonUtil
                .replaceNullString(other.getUsername()));
        this.setUserdesc(JsonUtil
                .replaceNullString(other.getUserdesc()));
        this.setOrgCode(JsonUtil
                .replaceNullString(other.getOrgCode()));
        this.setUnitName(JsonUtil
                .replaceNullString(other.getUnitName()));
        this.setOfficePhone(JsonUtil
                .replaceNullString(other.getOfficePhone()));
        this.setUserState(JsonUtil
                .replaceNullString(other.getUserState()));
        this.setUserorder(null == other.getUserorder() ? null :String.valueOf(other.getUserorder()));
        this.setIsvalid(JsonUtil
                .replaceNullString(other.getIsvalid()));
    }
   
}
