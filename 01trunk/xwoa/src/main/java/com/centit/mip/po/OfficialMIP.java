package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;


/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OfficialMIP {
    
    private static final long serialVersionUID = 1L;
    
    
    @Expose @Since(1.0)
    private String docid;// 工作项唯一标示
    @Expose @Since(1.0)
    private String title;
    @Expose @Since(1.0)
    private String createusername;//创建人
    @Expose @Since(1.0)
    private String createdatetime;//创建时间
    @Expose @Since(1.0)
    private String nodeinstid;//待办节点实例id 
    @Expose @Since(1.0)
    private String readstate;//阅读状态
    
   
    @Expose @Since(2.0)
    private String modifydatetime;// 修改时间
    @Expose @Since(2.0)
    private String handlestatus;// 处理状态0处理 1未处理
    @Expose @Since(2.0)
    private String lockstatus;// 锁状态 F未锁 T锁定
    @Expose @Since(2.0)
    private String lockuserid;// 锁定文件的处理人id
    @Expose @Since(2.0)
    private String lockusername;// 锁定文件的处理人姓名
    @Expose @Since(2.0)
    private String locktime;// 锁定文件的时间
    @Expose @Since(2.0)
    private String collectstatus;// 是否被收藏（1：是 0：否）
    @Expose @Since(1.0)
    private String emergercydegree;// 紧急程度
    @Expose @Since(2.0)
    private String isEndStep;// 是否最后的步骤 1是  0否
    @Expose @Since(2.0)
    private String wfType;// 流程类型（free-自由  fixed-固定）
    @Expose @Since(2.0)
    private String docurl;// 正文下载地址
    @Expose @Since(2.0)
    private String filePw;// 加密后的pdf文件密码（AES32方式）
    @Expose @Since(2.0)
    private String docFileId;// 传递给移动端的文档ID

    
   
    @Expose (serialize = false) 
    private String userid;
    @Expose (serialize = false) 
    private String keyword;
    @Expose (serialize = false) 
    private String currentdatetime;
    @Expose (serialize = false) 
    private String pagesize;
    @Expose (serialize = false) 
    private String type;//SW收文FW发文SQ内部事项DB督办。。。值为各业务流水的前缀
    @Expose (serialize = false) 
    private String handletype;//检索待办还是已办信息，0为待办 1为已办
    
    
    @Expose (serialize = false) 
    private String messageitemguid;//工作项唯一消息id
    
    @Expose @Since(1.0)
    private String handlesource ;//公文来源
   
    
    
    public String getDocid() {
        return docid;
    }
    public void setDocid(String docid) {
        this.docid = docid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCreateusername() {
        return createusername;
    }
    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }
    public String getCreatedatetime() {
        return createdatetime;
    }
    public void setCreatedatetime(String createdatetime) {
        this.createdatetime = createdatetime;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getCurrentdatetime() {
        return currentdatetime;
    }
    public void setCurrentdatetime(String currentdatetime) {
        this.currentdatetime = currentdatetime;
    }
    public String getPagesize() {
        return pagesize;
    }
    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getModifydatetime() {
        return modifydatetime;
    }
    public void setModifydatetime(String modifydatetime) {
        this.modifydatetime = modifydatetime;
    }
    public String getHandlestatus() {
        return handlestatus;
    }
    public void setHandlestatus(String handlestatus) {
        this.handlestatus = handlestatus;
    }
    public String getLockstatus() {
        return lockstatus;
    }
    public void setLockstatus(String lockstatus) {
        this.lockstatus = lockstatus;
    }
    public String getLockuserid() {
        return lockuserid;
    }
    public void setLockuserid(String lockuserid) {
        this.lockuserid = lockuserid;
    }
    public String getLockusername() {
        return lockusername;
    }
    public void setLockusername(String lockusername) {
        this.lockusername = lockusername;
    }
    public String getLocktime() {
        return locktime;
    }
    public void setLocktime(String locktime) {
        this.locktime = locktime;
    }
    public String getCollectstatus() {
        return collectstatus;
    }
    public void setCollectstatus(String collectstatus) {
        this.collectstatus = collectstatus;
    }
    public String getEmergercydegree() {
        return emergercydegree;
    }
    public void setEmergercydegree(String emergercydegree) {
        this.emergercydegree = emergercydegree;
    }
    public String getIsEndStep() {
        return isEndStep;
    }
    public void setIsEndStep(String isEndStep) {
        this.isEndStep = isEndStep;
    }
    public String getWfType() {
        return wfType;
    }
    public void setWfType(String wfType) {
        this.wfType = wfType;
    }
    public String getDocurl() {
        return docurl;
    }
    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }
    public String getFilePw() {
        return filePw;
    }
    public void setFilePw(String filePw) {
        this.filePw = filePw;
    }
    public String getDocFileId() {
        return docFileId;
    }
    public void setDocFileId(String docFileId) {
        this.docFileId = docFileId;
    }
    public String getHandletype() {
        return handletype;
    }
    public void setHandletype(String handletype) {
        this.handletype = handletype;
    }
    public String getMessageitemguid() {
        return messageitemguid;
    }
    public void setMessageitemguid(String messageitemguid) {
        this.messageitemguid = messageitemguid;
    }
    public String getNodeinstid() {
        return nodeinstid;
    }
    public void setNodeinstid(String nodeinstid) {
        this.nodeinstid = nodeinstid;
    }
   
    public String getReadstate() {
        return readstate;
    }
    public void setReadstate(String readstate) {
        this.readstate = readstate;
    }
    public String getHandlesource() {
        return handlesource;
    }
    public void setHandlesource(String handlesource) {
        this.handlesource = handlesource;
    }
    
    
   


}
