package com.centit.mip.po;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaSmssendMip implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Expose
    @Since(1.0)
    private String smsid;
    @Expose
    @Since(1.0)
    private String sendnum;
    @Expose
    @Since(1.0)
    private String sendpeo;
    @Expose
    @Since(1.0)
    private String acceptnum;
    @Expose
    @Since(1.0)
    private String acceptpeo;
    @Expose
    @Since(1.0)
    private String sendtime;
    @Expose
    @Since(1.0)
    private String restoremessage;
    @Expose
    @Since(1.0)
    private String content;
    @Expose
    @Since(1.0)
    private String state;
    @Expose
    @Since(1.0)
    private String senderdepart;
    @Expose
    @Since(1.0)
    private String acceptpeocode;
    @Expose
    @Since(1.0)
    private String datasource;
    @Expose
    @Since(1.0)
    private String sendpeocode;

    @Expose(serialize = false)
    private String userid;
    @Expose(serialize = false)
    private String currentdatetime;
    @Expose(serialize = false)
    private String pagesize;
    @Expose(serialize = false)
    private String keyword;
    @Expose(serialize = false)
    private String receiverids;
    

  


    // Property accessors

    
    
    public String getSendnum() {
        return this.sendnum;
    }

    public String getSmsid() {
        return smsid;
    }

    public void setSmsid(String smsid) {
        this.smsid = smsid;
    }

    public void setSendnum(String sendnum) {
        this.sendnum = sendnum;
    }

    public String getSendpeo() {
        return this.sendpeo;
    }

    public void setSendpeo(String sendpeo) {
        this.sendpeo = sendpeo;
    }

    public String getAcceptnum() {
        return this.acceptnum;
    }

    public void setAcceptnum(String acceptnum) {
        this.acceptnum = acceptnum;
    }

    public String getAcceptpeo() {
        return this.acceptpeo;
    }

    public void setAcceptpeo(String acceptpeo) {
        this.acceptpeo = acceptpeo;
    }

  

    public String getRestoremessage() {
        return this.restoremessage;
    }

    public void setRestoremessage(String restoremessage) {
        this.restoremessage = restoremessage;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSenderdepart() {
        return this.senderdepart;
    }

    public void setSenderdepart(String senderdepart) {
        this.senderdepart = senderdepart;
    }

    public String getAcceptpeocode() {
        return this.acceptpeocode;
    }

    public void setAcceptpeocode(String acceptpeocode) {
        this.acceptpeocode = acceptpeocode;
    }

    public String getDatasource() {
        return this.datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getSendpeocode() {
        return this.sendpeocode;
    }

    public void setSendpeocode(String sendpeocode) {
        this.sendpeocode = sendpeocode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getReceiverids() {
        return receiverids;
    }

    public void setReceiverids(String receiverids) {
        this.receiverids = receiverids;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }



    
    
}
