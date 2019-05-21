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
public class UserUnitDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;
    /**
     *  
     */
    private String userId;
    /**
     * 
     */
    private String organId;
    /**
    * "超级管理员",//创建人
     */
    private String founder;

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

    public String getOrganId() {
        return organId;
    }
    public void setOrganId(String organId) {
        this.organId = organId;
    }
   
    public String getFounder() {
        return founder;
    }
    public void setFounder(String founder) {
        this.founder = founder;
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
}
