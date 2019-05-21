package com.centit.oa.po;

import java.util.Date;

/**
 * 
 * 文件发送
 * 
 * @author Ghost
 * @create 2016年6月25日
 * @version
 */
public class OptFileTransferSend {
    private String id;
    /**
     * 接收者名称
     */
    private String receiverName;
    private String receiverCode;
    /**
     * 发送者编码
     */
    private String senderCode;
    
    private String senderName;
    /**
     * 文件传输范围SYSIN,SYSOUT
     */
    private String scopeType;
    /**
     * 发送类型 1-上报 2-下发
     */
    private Integer sendType;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
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

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
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

}
