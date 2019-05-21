package com.centit.mip.po;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaUnitIncomedocMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	@Expose(serialize = false) 
	private String userid; //"用户唯一ID",
	@Expose(serialize = false) 
    private String keyword; //"检索关键字",
	@Expose(serialize = false) 
    private String currentdatetime; //"当前检索的时间",
	@Expose(serialize = false) 
    private String pagesize; //"需要检索的记录条数"
	@Expose(serialize = false) 
    private String type; //"流程类型"

	@Expose
	@Since(1.0)
    private String docid; //"工作项唯一标示"
	@Expose
	@Since(1.0)
    private String title; //"标题",
	@Expose
	@Since(1.0)
    private String readstatus; //"阅读状态 0已读 1未读"（是否保留待阅 0：否1：是）,
	@Expose
    private String createusername; //"创建人",
	@Expose
    private String createdatetime; //"创建时间"
	@Expose(serialize = false) 
	@Since(1.0)
    private String messageitemguid; //"工作项的唯一消息id",
	@Expose
	@Since(1.0)
    private String modifydatetime; //"修改时间",
	@Expose
	@Since(2.0)
    private String collectstatus; //是否被收藏（1：是 0：否），
	@Expose
	@Since(2.0)
    private String docurl; //正文下载地址,
	@Expose
	@Since(2.0)
    private String filePw; //加密后的pdf文件密码
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
    public String getReadstatus() {
        return readstatus;
    }
    public void setReadstatus(String readstatus) {
        this.readstatus = readstatus;
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
    public String getMessageitemguid() {
        return messageitemguid;
    }
    public void setMessageitemguid(String messageitemguid) {
        this.messageitemguid = messageitemguid;
    }
    public String getModifydatetime() {
        return modifydatetime;
    }
    public void setModifydatetime(String modifydatetime) {
        this.modifydatetime = modifydatetime;
    }
    public String getCollectstatus() {
        return collectstatus;
    }
    public void setCollectstatus(String collectstatus) {
        this.collectstatus = collectstatus;
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


    

}
