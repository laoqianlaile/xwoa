package com.centit.mip.po;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;


/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class PublicinfoMIP {
    
    private static final long serialVersionUID = 1L;
    
    @Expose 
    private String docid;// 文档id    
    @Expose 
    private String docname;//文档名称
    @Expose 
    private String doctype;// 文档类型
    @Expose 
    private String docsize;// 文档大小
    @Expose 
    private String docurl;// 文档下载地址 "（以资源地址的形式传输）
    @Expose 
    private String isfolder;// 是否是文件夹 1 folder 0 file
    @Expose 
    private String crud;// 操作权限
    @Expose 
    private String modifytime;// 更新时间
    @Expose 
    private String usercode;//创建人
    @Expose 
    private  String  unitcode;//部门编号
//    @Expose
    private String owner;// 所有者 部门或者个人唯一标识

   
    @Expose (serialize = false) 
    private String userid;
    @Expose (serialize = false) 
    private String foldertype;
    @Expose (serialize = false) 
    private String parentnode;//空字符串为获取根节点
    @Expose (serialize = false) 
    private String currentdatetime;
    @Expose (serialize = false) 
    private String pagesize;
    @Expose (serialize = false) 
    private String keyword;
    @Expose (serialize=false)
    private String title;
    @Expose (serialize=false)
    private String foldername;
    @Expose (serialize=false)
    private String id;//"文件夹id"
    @Expose (serialize=false)
    private String operationtype;//"操作类型（0：修改，1：删除）"
    
    
    public String getDocid() {
        return docid;
    }
    public void setDocid(String docid) {
        this.docid = docid;
    }
    public String getDocname() {
        return docname;
    }
    public void setDocname(String docname) {
        this.docname = docname;
    }
    public String getDoctype() {
        return doctype;
    }
    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }
    public String getDocsize() {
        return docsize;
    }
    public void setDocsize(String docsize) {
        this.docsize = docsize;
    }
    public String getDocurl() {
        return docurl;
    }
    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }
    public String getCrud() {
        return crud;
    }
    public void setCrud(String crud) {
        this.crud = crud;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getFoldertype() {
        return foldertype;
    }
    public void setFoldertype(String foldertype) {
        this.foldertype = foldertype;
    }
    public String getParentnode() {
        return parentnode;
    }
    public void setParentnode(String parentnode) {
        this.parentnode = parentnode;
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
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getModifytime() {
        return modifytime;
    }
    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }
    public String getIsfolder() {
        return isfolder;
    }
    public void setIsfolder(String isfolder) {
        this.isfolder = isfolder;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFoldername() {
        return foldername;
    }
    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOperationtype() {
        return operationtype;
    }
    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getUnitcode() {
        return unitcode;
    }
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
    
    
}
