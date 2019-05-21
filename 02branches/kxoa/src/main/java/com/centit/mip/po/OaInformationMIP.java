package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaInformationMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	@Expose(serialize = false) 
	private String userid;
	@Expose(serialize = false) 
    private String keyword;
	@Expose(serialize = false) 
    private String currentdatetime;
	@Expose(serialize = false) 
    private String pagesize;
	@Expose(serialize = false) 
    private String type;
	@Expose(serialize = false) 
    private String pagenum;
	
	@Expose        
    private String id;
	@Expose        
    private String title;
	@Expose
    private String publishtime;
	@Expose
    private String publisher;
	@Expose
	private String informationid;	
    @Expose
	private String isTop;//置顶
    @Expose
    private String readstate;//阅读状态
    
	@Expose
	@Since(1.1)
	private String publishdept;
	@Expose
	@Since(1.1)
	private String fileurl;
	@Expose
    @Since(1.1)
	private String filename;
	
	@Expose
    @Since(2.0)
	private String attachid; //附件id",
	@Expose
    @Since(2.0)
    private String attachtitle; //附件名称",
	@Expose
    @Since(2.0)
    private String attachsize; //附件大小",
	@Expose
    @Since(2.0)
    private String attachurl; //附件地址"  (提供接口下载，后续提供URL的方式)
	@Expose
    @Since(2.0)
	private String content;//
	


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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPublishtime() {
        return publishtime;
    }
    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getInformationid() {
        return informationid;
    }
    public void setInformationid(String informationid) {
        this.informationid = informationid;
    }
    public String getPublishdept() {
        return publishdept;
    }
    public void setPublishdept(String publishdept) {
        this.publishdept = publishdept;
    }
    public String getFileurl() {
        return fileurl;
    }
    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
    public String getAttachid() {
        return attachid;
    }
    public void setAttachid(String attachid) {
        this.attachid = attachid;
    }
    public String getAttachtitle() {
        return attachtitle;
    }
    public void setAttachtitle(String attachtitle) {
        this.attachtitle = attachtitle;
    }
    public String getAttachsize() {
        return attachsize;
    }
    public void setAttachsize(String attachsize) {
        this.attachsize = attachsize;
    }
    public String getAttachurl() {
        return attachurl;
    }
    public void setAttachurl(String attachurl) {
        this.attachurl = attachurl;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getPagenum() {
        return pagenum;
    }
    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }
    public String getIsTop() {
        return isTop;
    }
    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }
    public String getReadstate() {
        return readstate;
    }
    public void setReadstate(String readstate) {
        this.readstate = readstate;
    }
    
	
	
  
}
