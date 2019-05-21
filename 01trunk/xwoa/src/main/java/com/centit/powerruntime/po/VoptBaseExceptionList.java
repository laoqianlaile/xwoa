package com.centit.powerruntime.po;

import java.util.Date;

public class VoptBaseExceptionList implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private Long taskId;
    private String djId;
    private Long flowInstId;
    private String transaffairname;
    private Date createdate;
    private String bizusernames;// 当前办理人员
    private String bizusercodes;// 当前办理人员usercode
    private String  nodeName;
    private String itemType;//办件类别
    private String type;//异常类别
    private String typeName;//异常类别名称
    public VoptBaseExceptionList() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public VoptBaseExceptionList(Long taskId, String djId, Long flowInstId,
            String transaffairname, Date createdate, String bizusernames,
            String bizusercodes, String nodeName, String itemType, String type,
            String typeName) {
        super();
        this.taskId = taskId;
        this.djId = djId;
        this.flowInstId = flowInstId;
        this.transaffairname = transaffairname;
        this.createdate = createdate;
        this.bizusernames = bizusernames;
        this.bizusercodes = bizusercodes;
        this.nodeName = nodeName;
        this.itemType = itemType;
        this.type = type;
        this.typeName = typeName;
    }

    public VoptBaseExceptionList(String djId, Long flowInstId,
            String transaffairname, Date createdate, String bizusernames,
            String bizusercodes, String nodeName, String itemType, String type,
            String typeName) {
        super();
        this.djId = djId;
        this.flowInstId = flowInstId;
        this.transaffairname = transaffairname;
        this.createdate = createdate;
        this.bizusernames = bizusernames;
        this.bizusercodes = bizusercodes;
        this.nodeName = nodeName;
        this.itemType = itemType;
        this.type = type;
        this.typeName = typeName;
    }
    
    public Long getTaskId() {
        return taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public String getDjId() {
        return djId;
    }
    public void setDjId(String djId) {
        this.djId = djId;
    }
    public Long getFlowInstId() {
        return flowInstId;
    }
    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }
    public String getTransaffairname() {
        return transaffairname;
    }
    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }
    public Date getCreatedate() {
        return createdate;
    }
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getBizusernames() {
        return bizusernames;
    }
    public void setBizusernames(String bizusernames) {
        this.bizusernames = bizusernames;
    }
    public String getBizusercodes() {
        return bizusercodes;
    }
    public void setBizusercodes(String bizusercodes) {
        this.bizusercodes = bizusercodes;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    public void clearProperties(){
        this.djId = null;
        this.bizusercodes = null;
        this.bizusernames = null;
        this.createdate = null;
        this.flowInstId = null;
        this.itemType = null;
        this.nodeName = null;
        this.transaffairname = null;
        this.type = null;
        this.typeName = null;
    }
    
    public void copy(VoptBaseExceptionList other){
        this.setBizusercodes(other.getBizusercodes());
        this.setBizusernames(other.getBizusernames());
        this.setCreatedate(other.getCreatedate());
        this.setDjId(other.getDjId());
        this.setFlowInstId(other.getFlowInstId());
        this.setItemType(other.getItemType());
        this.setNodeName(other.getNodeName());
        this.setTransaffairname(other.getTransaffairname());
        this.setType(other.getType());
        this.setTypeName(other.getTypeName());
    }
    
    public void copyNotNullProperty(VoptBaseExceptionList other){
        if(other.getDjId() != null)
            this.setDjId(other.getDjId());
        if(other.getBizusercodes() != null)
            this.setBizusercodes(other.getBizusercodes());
        if(other.getBizusernames() != null)
            this.setBizusernames(other.getBizusernames());
        if(other.getCreatedate() != null)
            this.setCreatedate(other.getCreatedate());
        if(other.getFlowInstId() != null)
            this.setFlowInstId(other.getFlowInstId());
        if(other.getItemType() != null)
            this.setItemType(other.getItemType());
        if(other.getNodeName() != null)
            this.setNodeName(other.getNodeName());
        if(other.getTransaffairname() != null)
            this.setTransaffairname(other.getTransaffairname());
        if(other.getType() != null)
            this.setType(other.getType());
        if(other.getTypeName() != null)
            this.setTypeName(other.getTypeName());
        
    }
}
