package com.centit.oa.po;

import java.util.Date;
import java.util.List;
/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaFilemanager implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String no; 
    private String infoType;
    private String title;
    private  String remark;
    private  String creater;
    private  Date createtime;
    private  Date releaseDate;
    private Date validDate;
    private String state;
    private String publicKey;
    private   List<OaInformationAttachment> docAttachments;
    // Constructors
    /** default constructor */
    public OaFilemanager() {
    }

    /** minimal constructor */
    public OaFilemanager(String no) {

        this.no = no;

    }


    public OaFilemanager(String no,String infoType,String title, String remark, String creater, 
            Date createtime,  Date releaseDate, Date validDate, String state, String publicKey) {
        this.no = no;
        this.infoType = infoType;
        this.title = title;
        this.releaseDate = releaseDate;
        this.creater = creater;
        this.createtime = createtime;
        this.releaseDate = releaseDate;
        this.validDate = validDate;
        this.remark = remark;
        this.state = state;
        this.publicKey = publicKey;
    }

    

    public void copy(OaFilemanager other) {

        this.setNo(other.getNo());

        this.title = other.getTitle();
        this.createtime = other.getCreatetime();
        this.creater = other.getCreater();
        this.infoType = other.getInfoType();
        this.publicKey = other.getPublicKey();
        this.releaseDate = other.getReleaseDate();
        this.remark = other.getRemark();
        this.state = other.getState();
        this.validDate = other.getValidDate();

    }


    public void copyNotNullProperty(OaFilemanager other) {

        if (other.getNo() != null)
            this.setNo(other.getNo());

        if (other.getCreater() != null)
            this.creater = other.getCreater();
        
        if(other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if(other.getInfoType() != null)
            this.infoType = other.getInfoType();
        if(other.getPublicKey() != null)
            this.publicKey = other.getPublicKey();
        if(other.getReleaseDate() != null)
            this.releaseDate = other.getReleaseDate();
        if(other.getRemark() != null)
            this.remark = other.getRemark();
        if(other.getState() != null)
            this.state = other.getState();
        if(other.getTitle() != null)
            this.title = other.getTitle();
        if(other.getValidDate() != null)
            this.validDate = other.getValidDate();
        
    }

    public void clearProperties() {
        
        this.no = null;
        this.creater = null;
        this.createtime = null;
        this.title = null;
        this.releaseDate = null;
        this.infoType = null;
        this.validDate = null;
        this.state = null;
        this.publicKey = null;
        this.remark = null;
        
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public List<OaInformationAttachment> getDocAttachments() {
        return docAttachments;
    }

    public void setDocAttachments(List<OaInformationAttachment> docAttachments) {
        this.docAttachments = docAttachments;
    }

    
}
