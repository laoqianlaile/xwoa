package com.centit.app.po;

import java.util.Date;
import java.util.List;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OuterNetPublicinfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String infocode;
    private String parentinfocode;
    
    private OuterNetFileinfoFs fileinfoFs;
    
    private String filename;
    private String fileextension;
    private String ownercode;
    private String uploader;
    private Long readcount;
    private Long downloadcount;
    private Date uploadtime;
    private Date modifytime;
    private String status;
    private String foldertype;
    private String isfolder;
    private String filedescription;
    private String unitcode;
    private String authority;
    private Long filesize;
    private String zipPath;
    
    private String allowcomment;
    
    
    private String isOneself;
    
    public String getIsOneself() {
        return isOneself;
    }

    public void setIsOneself(String isOneself) {
        this.isOneself = isOneself;
    }

    public String getAllowcomment() {
        return allowcomment;
    }

    public void setAllowcomment(String allowcomment) {
        this.allowcomment = allowcomment;
    }

    private List<OuterNetPublicinfo> childFiles;
    public List<OuterNetPublicinfo> getChildFiles() {
        return childFiles;
    }

    public void setChildFiles(List<OuterNetPublicinfo> childFiles) {
        this.childFiles = childFiles;
    }

    public String getZipPath() {
        return zipPath;
    }

    public void setZipPath(String zipPath) {
        this.zipPath = zipPath;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    // Constructors
    /** default constructor */
    public OuterNetPublicinfo() {
    }

    /** minimal constructor */
    public OuterNetPublicinfo(String infocode) {

        this.infocode = infocode;

    }

    /** full constructor */
    public OuterNetPublicinfo(String infocode, String parentinfocode, OuterNetFileinfoFs fileinfo,
            String filename, String fileextension, String ownercode,
            Long readcount, Long downloadcount, String md5, Date uploadtime,
            Date modifytimes, String status, String foldertype, String isfolder,
            String filedescription) {

        this.infocode = infocode;

        this.parentinfocode = parentinfocode;
        this.fileinfoFs = fileinfo;
        this.filename = filename;
        this.fileextension = fileextension;
        this.ownercode = ownercode;
        this.readcount = readcount;
        this.downloadcount = downloadcount;
        this.uploadtime = uploadtime;
        this.modifytime = modifytimes;
        this.status = status;
        this.foldertype = foldertype;
        this.isfolder = isfolder;
        this.filedescription = filedescription;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitCode) {
        this.unitcode = unitCode;
    }

    public String getInfocode() {
        return this.infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    // Property accessors

    public String getParentinfocode() {
        return this.parentinfocode;
    }

    public void setParentinfocode(String parentinfocode) {
        this.parentinfocode = parentinfocode;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileextension() {
        return this.fileextension;
    }

    public void setFileextension(String fileextension) {
        this.fileextension = fileextension;
    }

    public String getOwnercode() {
        return this.ownercode;
    }

    public void setOwnercode(String ownercode) {
        this.ownercode = ownercode;
    }

    public Long getReadcount() {
        return this.readcount;
    }

    public void setReadcount(Long readcount) {
        this.readcount = readcount;
    }

    public Long getDownloadcount() {
        return this.downloadcount;
    }

    public void setDownloadcount(Long downloadcount) {
        this.downloadcount = downloadcount;
    }

    public Date getUploadtime() {
        return this.uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Date getModifytime() {
        return this.modifytime;
    }

    public void setModifytime(Date modifytimes) {
        this.modifytime = modifytimes;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoldertype() {
        return this.foldertype;
    }

    public void setFoldertype(String foldertype) {
        this.foldertype = foldertype;
    }

    public String getIsfolder() {
        return this.isfolder;
    }

    public void setIsfolder(String isfolder) {
        this.isfolder = isfolder;
    }

    public String getFiledescription() {
        return this.filedescription;
    }

    public void setFiledescription(String filedescription) {
        this.filedescription = filedescription;
    }

    public OuterNetFileinfoFs getFileinfoFs() {
        return fileinfoFs;
    }

    public void setFileinfoFs(OuterNetFileinfoFs fileinfo) {
        this.fileinfoFs = fileinfo;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     */

    public void copy(OuterNetPublicinfo other) {

        this.setInfocode(other.getInfocode());

        this.parentinfocode = other.getParentinfocode();
        this.fileinfoFs = other.getFileinfoFs();
        this.filename = other.getFilename();
        this.fileextension = other.getFileextension();
        this.ownercode = other.getOwnercode();
        this.readcount = other.getReadcount();
        this.downloadcount = other.getDownloadcount();
        this.uploadtime = other.getUploadtime();
        this.modifytime = other.getModifytime();
        this.status = other.getStatus();
        this.foldertype = other.getFoldertype();
        this.isfolder = other.getIsfolder();
        this.filedescription = other.getFiledescription();

        this.unitcode = other.getUnitcode();
    }

    public void copyNotNullProperty(OuterNetPublicinfo other) {

        if (other.getInfocode() != null)
            this.setInfocode(other.getInfocode());

        if (other.getParentinfocode() != null)
            this.parentinfocode = other.getParentinfocode();
        if (other.getFileinfoFs() != null)
            this.fileinfoFs = other.getFileinfoFs();
        if (other.getFilename() != null)
            this.filename = other.getFilename();
        if (other.getFileextension() != null)
            this.fileextension = other.getFileextension();
        if (other.getOwnercode() != null)
            this.ownercode = other.getOwnercode();
        if (other.getReadcount() != null)
            this.readcount = other.getReadcount();
        if (other.getDownloadcount() != null)
            this.downloadcount = other.getDownloadcount();
        if (other.getUploadtime() != null)
            this.uploadtime = other.getUploadtime();
        if (other.getModifytime() != null)
            this.modifytime = other.getModifytime();
        if (other.getStatus() != null)
            this.status = other.getStatus();
        if (other.getFoldertype() != null)
            this.foldertype = other.getFoldertype();
        if (other.getIsfolder() != null)
            this.isfolder = other.getIsfolder();
        if (other.getFiledescription() != null)
            this.filedescription = other.getFiledescription();
        if (null != other.getUnitcode()) {
            this.unitcode = other.getUnitcode();
        }

    }

    public void clearProperties() {

        this.parentinfocode = null;
        this.fileinfoFs = null;
        this.filename = null;
        this.fileextension = null;
        this.ownercode = null;
        this.readcount = null;
        this.downloadcount = null;
        this.uploadtime = null;
        this.modifytime = null;
        this.status = null;
        this.foldertype = null;
        this.isfolder = null;
        this.filedescription = null;

    }

}
