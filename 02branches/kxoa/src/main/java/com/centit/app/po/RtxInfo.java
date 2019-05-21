package com.centit.app.po;

import java.util.Date;

public class RtxInfo implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private String no;
    private String djId;
    private Long nodeId;
    private String createUserCode;
    private String createUserName;
    private String receiveUserCode;
    private String receiveUserName;
    private Date createDate;
    private String infoContent;
    private String isSend;
    private Date sendDate;
    
    public RtxInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    public RtxInfo(String no, String djId, Long nodeId,
            String createUserCode, String createUserName, Date createDate,
            String infoContent, String isSend, Date sendDate) {
        super();
        this.no = no;
        this.djId = djId;
        this.nodeId = nodeId;
        this.createUserCode = createUserCode;
        this.createUserName = createUserName;
        this.createDate = createDate;
        this.infoContent = infoContent;
        this.isSend = isSend;
        this.sendDate = sendDate;
    }
    
    public RtxInfo(String no, String djId, Long nodeId,
            String createUserCode, String createUserName,
            String receiveUserCode, String receiveUserName, Date createDate,
            String infoContent, String isSend, Date sendDate) {
        super();
        this.no = no;
        this.djId = djId;
        this.nodeId = nodeId;
        this.createUserCode = createUserCode;
        this.createUserName = createUserName;
        this.receiveUserCode = receiveUserCode;
        this.receiveUserName = receiveUserName;
        this.createDate = createDate;
        this.infoContent = infoContent;
        this.isSend = isSend;
        this.sendDate = sendDate;
    }
    public String getReceiveUserCode() {
        return receiveUserCode;
    }
    public void setReceiveUserCode(String receiveUserCode) {
        this.receiveUserCode = receiveUserCode;
    }
    public String getReceiveUserName() {
        return receiveUserName;
    }
    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
 
    public Long getNodeId() {
        return nodeId;
    }
    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
    public String getCreateUserCode() {
        return createUserCode;
    }
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    public String getCreateUserName() {
        return createUserName;
    }
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getInfoContent() {
        return infoContent;
    }
    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }
    public String getIsSend() {
        return isSend;
    }
    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }
    public Date getSendDate() {
        return sendDate;
    }
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
}
