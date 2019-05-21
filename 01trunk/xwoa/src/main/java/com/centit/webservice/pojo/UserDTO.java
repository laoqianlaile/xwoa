package com.centit.webservice.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
 * 太极用户传输对象
 * 
 * @author Ghost
 * @create 2017年2月24日
 * @version
 */
public class UserDTO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
   // private String uid;
    /**
     * 用户主键
     */
    private String userId;
    /**
     * 密码
     */
    private String loginPassword;
    /**
     * 创建人
     */
    private String founder;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户描述
     */
    private String describe;
    /**
     * 用户类型（20251 用户，20250管理员）
     */
    private String saveType;
    /**
     * 使用状态（20255启用，20256停用）
     */
    private String usingState;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private Date birthDate;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 证件类型
     * 
     */
    private String certType;
    /**
     * 证件ID
     */
    private String certId;
    /**
     * 办公室电话
     */
    private String officePhone;
    /**
     * 参加工作时间
     */
    private Date workDate;
    /**
     * 工作时间
     */
    private String offerInDate;//太极反馈信息没有该字段
    /**
     * 手机
     */
    private String phone;//太极反馈信息没有该字段
    /**
     *  数字证书
     */
    private String digitalCertId;
    /**
     * 是否删除
     */
    private String isDelete;//太极反馈信息没有该字段
    /**
     * 账号有效期
     */
    private String effectiveDate;
    /**
     * 更新时间
     */
    private String updateTime;//太极反馈信息没有该字段
    /**
     * 组织机构id
     */
   // private String oraganId;
    
    private String organId;
    /**
     * 组织机构id
     */
   // private String oraganId;
    
    private List<UserUnitDTO> oraganIds;
    /**
     * 人员编码
     */
    private String personCode;
    /**
     * 是否委办局用户（20253徐圩新区工作人员20252外部工作人员）
     */
    private String isXw;
    /**
     * 排序
     */
    private String sort;
    private String privateCode;
    private String publicCode;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getLoginPassword() {
        return loginPassword;
    }
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    public String getFounder() {
        return founder;
    }
    public void setFounder(String founder) {
        this.founder = founder;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getSaveType() {
        return saveType;
    }
    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }
    public String getUsingState() {
        return usingState;
    }
    public void setUsingState(String usingState) {
        this.usingState = usingState;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getCertType() {
        return certType;
    }
    public void setCertType(String certType) {
        this.certType = certType;
    }
    public String getCertId() {
        return certId;
    }
    public void setCertId(String certId) {
        this.certId = certId;
    }
    public String getOfficePhone() {
        return officePhone;
    }
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
    public Date getWorkDate() {
        return workDate;
    }
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }
    public String getOfferInDate() {
        return offerInDate;
    }
    public void setOfferInDate(String offerInDate) {
        this.offerInDate = offerInDate;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDigitalCertId() {
        return digitalCertId;
    }
    public void setDigitalCertId(String digitalCertId) {
        this.digitalCertId = digitalCertId;
    }
    public String getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
 
 
 
    public String getOrganId() {
        return organId;
    }
    public void setOrganId(String organId) {
        this.organId = organId;
    }
    public List<UserUnitDTO> getOraganIds() {
        return oraganIds;
    }
    public void setOraganIds(List<UserUnitDTO> oraganIds) {
        this.oraganIds = oraganIds;
    }
    public String getPersonCode() {
        return personCode;
    }
    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }
    public String getIsXw() {
        return isXw;
    }
    public void setIsXw(String isXw) {
        this.isXw = isXw;
    }
    public String getPrivateCode() {
        return privateCode;
    }
    public void setPrivateCode(String privateCode) {
        this.privateCode = privateCode;
    }
    public String getPublicCode() {
        return publicCode;
    }
    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
