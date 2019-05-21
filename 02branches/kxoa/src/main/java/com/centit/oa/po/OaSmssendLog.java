package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaSmssendLog implements java.io.Serializable {
    private static final long serialVersionUID =  1L;


    private Long no;
    /*private OaSmssend oaSmssend;*/

    private Long smsid;
    private Date  sendtime;
    private String  restoremessage;
    private String  content;
    private String  state;
    private String  datasource;
    
    private String sequenceId;
    private String msgId;
    
    private Date succeedTime; // 短信网关发送短信的时间

    // Constructors
    /** default constructor */
    public OaSmssendLog() {
    }
    /** minimal constructor */
    public OaSmssendLog(
        Long no      
        ) {
    
    
        this.no = no;     
            
    }

/** full constructor */
    public OaSmssendLog(Long no, Long smsid,
            /*OaSmssend oaSmssend ,*/Date  sendtime,String  restoremessage,String  content,String  state,String  datasource,String sequenceId,String msgId,Date succeedTime) {
    
    
        this.no = no;
        /*this.oaSmssend = oaSmssend;  */   
        this.smsid = smsid;

        this.sendtime= sendtime;
        this.restoremessage= restoremessage;
        this.content= content;
        this.state= state;
        this.datasource= datasource;
        this.sequenceId= sequenceId;
        this.msgId = msgId;
        this.succeedTime = succeedTime;
                
    }
    
    // Property accessors
  
    public Date getSendtime() {
        return this.sendtime;
    }
    
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
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
  
    public String getDatasource() {
        return this.datasource;
    }
    
    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }


    public void copy(OaSmssendLog other){
  
        this.setNo(other.getNo());

        /*this.oaSmssend = other.getOaSmssend();*/
        this.smsid = other.getSmsid();
        this.sendtime= other.getSendtime();  
        this.restoremessage= other.getRestoremessage();  
        this.content= other.getContent();  
        this.state= other.getState();  
        this.datasource= other.getDatasource();  
        this.sequenceId =other.getSequenceId();
        this.msgId = other.getMsgId();

    }
    
    public void copyNotNullProperty(OaSmssendLog other){
  
        if( other.getNo() != null)
            this.setNo(other.getNo());
    
        /*if( other.getOaSmssend() != null)
            this.oaSmssend = other.getOaSmssend();*/
        
        if( other.getSmsid() != null)
            this.smsid = other.getSmsid();
 
        if( other.getSendtime() != null)
            this.sendtime= other.getSendtime();  
        if( other.getRestoremessage() != null)
            this.restoremessage= other.getRestoremessage();  
        if( other.getContent() != null)
            this.content= other.getContent();  
        if( other.getState() != null)
            this.state= other.getState();  
        if( other.getDatasource() != null)
            this.datasource= other.getDatasource();  
        if( other.getSequenceId()!= null)
            this.sequenceId= other.getSequenceId();
        if( other.getMsgId() != null)
            this.msgId= other.getMsgId();
        if( other.getSucceedTime() != null)
            this.succeedTime = other.getSucceedTime();
    }
    
    public void clearProperties(){
  
        /*this.oaSmssend = null;*/
        this.smsid = null;
        this.sendtime= null;  
        this.restoremessage= null;  
        this.content= null;  
        this.state= null;  
        this.datasource= null;  
        this.sequenceId = null;
        this.msgId = null;
        this.succeedTime = null;

    }
    public String getSequenceId() {
        return sequenceId;
    }
    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    public Date getSucceedTime() {
        return succeedTime;
    }
    public void setSucceedTime(Date succeedTime) {
        this.succeedTime = succeedTime;
    }
    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    /*public OaSmssend getOaSmssend() {
        return oaSmssend;
    }
    public void setOaSmssend(OaSmssend oaSmssend) {
        this.oaSmssend = oaSmssend;
    }*/
    public Long getSmsid() {
        return smsid;
    }
    public void setSmsid(Long smsid) {
        this.smsid = smsid;
    }
    
    
}
