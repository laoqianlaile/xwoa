package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaSMSConfig implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String smsid;//版本号
    private String status;//发布状态 A草稿，B正常，C过期，D禁用，E已发布(A,E仅对0版本有效)
	
    private String  openOrClose;//开启状态'T开启F关闭 此项标志是否使用短信功能';
	private String  operatorSource;//运营商
	private String  gateway;//短信网关
	private String  username;//授权用户
	private String  password;//授权密码
	private Long  port;//授权端口
	private String  sendMSgMod;//短信模板
	private String  limitYesOrNo;//是否限制条数
	private Long  limitNumber;//限制条数
	private String  remark;//备注说明
	private Date    settime;//创建时间
	
    public OaSMSConfig() {
        super();
    }
    
    
    
    public OaSMSConfig(String smsid, String status, String openOrClose,
            String operatorSource, String gateway, String username,
            String password, Long port, String sendMSgMod,
            String limitYesOrNo, Long limitNumber, String remark,Date    settime) {
        super();
        this.smsid = smsid;
        this.status = status;
        this.openOrClose = openOrClose;
        this.operatorSource = operatorSource;
        this.gateway = gateway;
        this.username = username;
        this.password = password;
        this.port = port;
        this.sendMSgMod = sendMSgMod;
        this.limitYesOrNo = limitYesOrNo;
        this.limitNumber = limitNumber;
        this.remark = remark;
        this.settime = settime;
    }

    public void copy(OaSMSConfig other){
        
        this.setSmsid(other.getSmsid());
  
        this.status= other.getStatus();  
        this.openOrClose= other.getOpenOrClose();  
        this.operatorSource= other.getOperatorSource();  
        this.gateway= other.getGateway();  
        this.username= other.getUsername();
        this.password = other.getPassword();
        this.port = other.getPort();
        this.sendMSgMod = other.getSendMSgMod();
        this.limitYesOrNo = other.getLimitYesOrNo();
        this.limitNumber = other.getLimitNumber();
        this.remark=other.getRemark();
        this.settime= other.getSettime();

    }
    
    public void copyNotNullProperty(OaSMSConfig other){
  
    if( other.getSmsid() != null)
        this.setSmsid(other.getSmsid());
    
      if( other.getStatus() != null)
        this.status= other.getStatus();
  
        if( other.getOpenOrClose() != null)
            this.openOrClose= other.getOpenOrClose();  
        if( other.getOperatorSource() != null)
            this.operatorSource= other.getOperatorSource();  
        if( other.getGateway() != null)
            this.gateway= other.getGateway();  
        if( other.getUsername() != null)
            this.username= other.getUsername();  
        if( other.getPassword() != null)
            this.password= other.getPassword();  
        if( other.getPort() != null)
            this.port= other.getPort();
        if( other.getSendMSgMod() != null)
            this.sendMSgMod= other.getSendMSgMod();  
        if( other.getLimitYesOrNo() != null)
            this.limitYesOrNo= other.getLimitYesOrNo();  
        if( other.getLimitNumber() != null)
            this.limitNumber= other.getLimitNumber();  
        if( other.getRemark() != null)
            this.remark= other.getRemark();
        if( other.getSettime() != null)
            this.settime= other.getSettime();
    }

    public String getSmsid() {
        return smsid;
    }
    public void setSmsid(String smsid) {
        this.smsid = smsid;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getOpenOrClose() {
        return openOrClose;
    }
    public void setOpenOrClose(String openOrClose) {
        this.openOrClose = openOrClose;
    }
    public String getOperatorSource() {
        return operatorSource;
    }
    public void setOperatorSource(String operatorSource) {
        this.operatorSource = operatorSource;
    }
    public String getGateway() {
        return gateway;
    }
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getPort() {
        return port;
    }
    public void setPort(Long port) {
        this.port = port;
    }
    public String getSendMSgMod() {
        return sendMSgMod;
    }
    public void setSendMSgMod(String sendMSgMod) {
        this.sendMSgMod = sendMSgMod;
    }
    public String getLimitYesOrNo() {
        return limitYesOrNo;
    }
    public void setLimitYesOrNo(String limitYesOrNo) {
        this.limitYesOrNo = limitYesOrNo;
    }
    public Long getLimitNumber() {
        return limitNumber;
    }
    public void setLimitNumber(Long limitNumber) {
        this.limitNumber = limitNumber;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }



    public Date getSettime() {
        return settime;
    }



    public void setSettime(Date settime) {
        this.settime = settime;
    }
	

	
}
