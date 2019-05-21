package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;


/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OptProcCollectionMIP {
    
    private static final long serialVersionUID = 1L;
    
    @Expose 
    private String docid;// 工作项唯一标示
    @Expose 
    private String title;
    @Expose 
    private String createusername;
    @Expose 
    private String createdatetime;
    
    @Expose
    @Since(2.0)
    private String createuser;//锁定字段
    @Expose
    @Since(2.0)
    private String lockstatus;//锁定字段
   
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
    private String messageitemguid;//工作项唯一消息id
    @Expose (serialize = false) 
    private String collectstatus;
    
    
   
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
    public String getMessageitemguid() {
        return messageitemguid;
    }
    public void setMessageitemguid(String messageitemguid) {
        this.messageitemguid = messageitemguid;
    }
    public String getCollectstatus() {
        return collectstatus;
    }
    public void setCollectstatus(String collectstatus) {
        this.collectstatus = collectstatus;
    }
    public String getLockstatus() {
        return lockstatus;
    }
    public void setLockstatus(String lockstatus) {
        this.lockstatus = lockstatus;
    }
    public String getCreateuser() {
        return createuser;
    }
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }
   
    
    
   


}
