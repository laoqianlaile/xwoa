package com.centit.oa.po;

import java.util.Date;

/**
 * 
 * 文件传输接收
 * 
 * @author Ghost
 * @create 2016年6月25日
 * @version
 */
public class OptFileTransferReceive {
    private String id;
    /**
     * 发送者编码
     */
    private String senderCode;
    /**
     * 发送者名称
     */
    private String senderName;
    /**
     * 接收者编码
     */
    private String receiverCode;
    
    private String receiverName;
    /**
     * 文件传输范围SYSIN,SYSOUT
     */
    private String scopeType;
    /**
     * 标题
     */
    private String subject;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    
    private String readstate;//阅读状态
    private String newmsg;//最新

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReadstate() {
        return readstate;
    }

    public void setReadstate(String readstate) {
        this.readstate = readstate;
    }

    public String getNewmsg() {
        return newmsg;
    }

    public void setNewmsg(String newmsg) {
        this.newmsg = newmsg;
    }
    
}
