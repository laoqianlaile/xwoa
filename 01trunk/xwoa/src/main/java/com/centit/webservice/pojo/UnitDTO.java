package com.centit.webservice.pojo;

import java.io.Serializable;
/**
 * 
 * 太极部门传送对象
 * 
 * @author Ghost
 * @create 2017年2月24日
 * @version
 */
public class UnitDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String organId;
    /**
     *  "总经办",//部门名称
     */
    private String organName;
    /**
     * //无效字段（标注图标）
     */
    private String icon;
    /**
     * //排序
     */
    private String seq;
    /**
     * ,//部门地址
     */
    private String address;
    /**
     * //父节点id为空代表顶级部门
     */
    private String parentId;
    /**
     * "超级管理员",//创建人
     */
    private String founder;
    /**
     * "2016-11-28 15:13:02",//创建时间
     */
    private String createTime;
    /**
     *  1,//是否启用（1启用0停用）
     */
    private String saveType;
    /**
     * "组织机构-总经办",//描述
     */
    private String describe;
    /**
     * //无效字段
     */
    private String usingState;
    /*
     * //无效字段
     */
    private String level;
    /**
     * //部门编号
     */
    private String organCode;
    
    public String getOrganId() {
        return organId;
    }
    public void setOrganId(String organId) {
        this.organId = organId;
    }
    public String getOrganName() {
        return organName;
    }
    public void setOrganName(String organName) {
        this.organName = organName;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getSeq() {
        return seq;
    }
    public void setSeq(String seq) {
        this.seq = seq;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getFounder() {
        return founder;
    }
    public void setFounder(String founder) {
        this.founder = founder;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getSaveType() {
        return saveType;
    }
    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getUsingState() {
        return usingState;
    }
    public void setUsingState(String usingState) {
        this.usingState = usingState;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getOrganCode() {
        return organCode;
    }
    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }
    
}
