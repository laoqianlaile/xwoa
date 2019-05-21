package com.centit.webservice.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * 与太极对接的日志传输对象
 * 
 * @author Ghost
 * @create 2017年2月21日
 * @version
 */
public class SysLogDTO{
    private static final long serialVersionUID = 1L;
    /**
     * ed48ecf75d3e4fd888788042b9ca4e93,固定值,不可空
     */
    private String key;
    /**
     * //日志级别，可为空，默认值info
     */
     @JSONField(name="logLevel")
     private String logLevel;
     /**
      * ”model_name”,//模块名称，不可空，被访问模块
      */
     private String logModular;
     
     /**
      * 携带参数，可为空,例如：name=lili,sex=3132
      *//*
     @JSONField(name="log_parameter")
     private String logParameter;*/
     /**
      * ”function_name”,//功能，不可空，被访问功能
      */
     private String logFunction;
     /**
      * ”增加”,//操作类型，不可空（例如：增加，删除，修改，查询，调用，提交，审核通过，审核失败）
      */
     @JSONField(name="operatType")
     private String operatType;
     /**
      * 子系统标示，不可为空，子系统标识符
      */
     @JSONField(name="sysCode")
     private String sysCode;
     
     /**
      * 用户id，可为空
      */
     @JSONField(name="userId")
     private String userId;
     
     /**
      * 访问ip，可为空
      */
     @JSONField(name="logIp")
     private String logIp;
     /**
      * 类(全限定类名)，不可为空，被访问类
      */
     @JSONField(name="logClass")
     private String logClass;
     
     /**
      * 方法，不可为空，被访问方法
      */
     @JSONField(name="logMethod")
     private String logMethod;
     /**
      * ”添加用户”//可为空,日志描述信息
      */
     @JSONField(name="logMessage")
     private String logMessage;

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogClass() {
        return logClass;
    }

    public void setLogClass(String logClass) {
        this.logClass = logClass;
    }

    public String getLogMethod() {
        return logMethod;
    }

    public void setLogMethod(String logMethod) {
        this.logMethod = logMethod;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getLogModular() {
        return logModular;
    }

    public void setLogModular(String logModular) {
        this.logModular = logModular;
    }

    public String getLogFunction() {
        return logFunction;
    }

    public void setLogFunction(String logFunction) {
        this.logFunction = logFunction;
    }

    public String getOperatType() {
        return operatType;
    }

    public void setOperatType(String operatType) {
        this.operatType = operatType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
}
