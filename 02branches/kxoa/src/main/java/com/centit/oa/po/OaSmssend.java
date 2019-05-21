package com.centit.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaSmssend implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private Long smsid;

	private String  sendnum;
	private String  sendpeo;
	private String  acceptnum;
	private String  acceptpeo;
	private Date  sendtime;
	private String  restoremessage;
	private String  content;
	private String  state;
	private String  senderdepart;
	private String  acceptpeocode;
	private String  datasource;
	private String  sendpeocode;
	
	private String sequenceId;
	private String msgId;
	
	private Date succeedTime; // 短信网关发送短信的时间
	
	private List<OaSmssendLog> oaSmssendLogList;
	
	private Set<OaSmssendLog> oaSmssendLogs = null;

	// Constructors
	/** default constructor */
	public OaSmssend() {
	}
	/** minimal constructor */
	public OaSmssend(
		Long smsid		
		) {
	
	
		this.smsid = smsid;		
			
	}

/** full constructor */
	public OaSmssend(
	 Long smsid		
	,String  sendnum,String  sendpeo,String  acceptnum,String  acceptpeo,Date  sendtime,String  restoremessage,String  content,String  state,String  senderdepart,String  acceptpeocode,String  datasource,String  sendpeocode ,String sequenceId,String msgId,Date succeedTime) {
	
	
		this.smsid = smsid;		
	
		this.sendnum= sendnum;
		this.sendpeo= sendpeo;
		this.acceptnum= acceptnum;
		this.acceptpeo= acceptpeo;
		this.sendtime= sendtime;
		this.restoremessage= restoremessage;
		this.content= content;
		this.state= state;
		this.senderdepart= senderdepart;
		this.acceptpeocode= acceptpeocode;
		this.datasource= datasource;
		this.sendpeocode= sendpeocode;
		this.sequenceId= sequenceId;
		this.msgId = msgId;
		this.succeedTime = succeedTime;
		        
	}
	

  
	public Long getSmsid() {
		return this.smsid;
	}

	public void setSmsid(Long smsid) {
		this.smsid = smsid;
	}
	// Property accessors
  
	public String getSendnum() {
		return this.sendnum;
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



	public void copy(OaSmssend other){
  
		this.setSmsid(other.getSmsid());
  
		this.sendnum= other.getSendnum();  
		this.sendpeo= other.getSendpeo();  
		this.acceptnum= other.getAcceptnum();  
		this.acceptpeo= other.getAcceptpeo();  
		this.sendtime= other.getSendtime();  
		this.restoremessage= other.getRestoremessage();  
		this.content= other.getContent();  
		this.state= other.getState();  
		this.senderdepart= other.getSenderdepart();  
		this.acceptpeocode= other.getAcceptpeocode();  
		this.datasource= other.getDatasource();  
		this.sendpeocode= other.getSendpeocode();
		this.sequenceId =other.getSequenceId();
		this.msgId = other.getMsgId();
		this.succeedTime = other.succeedTime;

	}
	
	public void copyNotNullProperty(OaSmssend other){
  
	if( other.getSmsid() != null)
		this.setSmsid(other.getSmsid());
  
		if( other.getSendnum() != null)
			this.sendnum= other.getSendnum();  
		if( other.getSendpeo() != null)
			this.sendpeo= other.getSendpeo();  
		if( other.getAcceptnum() != null)
			this.acceptnum= other.getAcceptnum();  
		if( other.getAcceptpeo() != null)
			this.acceptpeo= other.getAcceptpeo();  
		if( other.getSendtime() != null)
			this.sendtime= other.getSendtime();  
		if( other.getRestoremessage() != null)
			this.restoremessage= other.getRestoremessage();  
		if( other.getContent() != null)
			this.content= other.getContent();  
		if( other.getState() != null)
			this.state= other.getState();  
		if( other.getSenderdepart() != null)
			this.senderdepart= other.getSenderdepart();  
		if( other.getAcceptpeocode() != null)
			this.acceptpeocode= other.getAcceptpeocode();  
		if( other.getDatasource() != null)
			this.datasource= other.getDatasource();  
		if( other.getSendpeocode() != null)
			this.sendpeocode= other.getSendpeocode();
		if( other.getSequenceId()!= null)
            this.sequenceId= other.getSequenceId();
		if( other.getMsgId() != null)
            this.msgId= other.getMsgId();
		if( other.getSucceedTime() != null)
		    this.succeedTime = other.getSucceedTime();
	}
	
	public void clearProperties(){
  
		this.sendnum= null;  
		this.sendpeo= null;  
		this.acceptnum= null;  
		this.acceptpeo= null;  
		this.sendtime= null;  
		this.restoremessage= null;  
		this.content= null;  
		this.state= null;  
		this.senderdepart= null;  
		this.acceptpeocode= null;  
		this.datasource= null;  
		this.sendpeocode= null;
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
    
    public List<OaSmssendLog> getOaSmssendLogList() {
        return oaSmssendLogList;
    }
    public void setOaSmssendLogList(List<OaSmssendLog> oaSmssendLogList) {
        this.oaSmssendLogList = oaSmssendLogList;
    }
    
    /*public Set<OaSmssendLog> getOaSmssendLogs() {
        if (this.oaSmssendLogs == null)
            this.oaSmssendLogs = new HashSet<OaSmssendLog>();
        return this.oaSmssendLogs;
    }
    public void setOaSmssendLogs(Set<OaSmssendLog> oaSmssendLogs) {
        this.oaSmssendLogs = oaSmssendLogs;
    }
   
    public void addOaSmssendLog(OaSmssendLog oaSmssendLog) {
        if (this.oaSmssendLogs == null)
            this.oaSmssendLogs = new HashSet<OaSmssendLog>();
        this.oaSmssendLogs.add(oaSmssendLog);
    }

    public void removeOaSmssendLog(OaSmssendLog oaSmssendLog) {
        if (this.oaSmssendLogs == null)
            return;
        this.oaSmssendLogs.remove(oaSmssendLog);
    }

    public OaSmssendLog newOaSmssendLog() {
        OaSmssendLog res = new OaSmssendLog();

        res.setOaSmssend(this);

        return res;
    }

    *//**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
     * 
     *//*
    public void replaceOaSmssendLogs(
            List<OaSmssendLog> oaSmssendLogs) {
        List<OaSmssendLog> newObjs = new ArrayList<OaSmssendLog>();
        for (OaSmssendLog p : oaSmssendLogs) {
            if (p == null)
                continue;
            OaSmssendLog newdt = newOaSmssendLog();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<OaSmssendLog> oldObjs = new HashSet<OaSmssendLog>();
        oldObjs.addAll(getOaSmssendLogs());

        for (Iterator<OaSmssendLog> it = oldObjs.iterator(); it.hasNext();) {
            OaSmssendLog odt = it.next();
            found = false;
            for (OaSmssendLog newdt : newObjs) {
                if (odt.getNo().equals(newdt.getNo())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeOaSmssendLog(odt);
        }
        oldObjs.clear();
        // insert or update
        for (OaSmssendLog newdt : newObjs) {
            found = false;
            for (Iterator<OaSmssendLog> it = getOaSmssendLogs()
                    .iterator(); it.hasNext();) {
                OaSmssendLog odt = it.next();
                if (odt.getNo()==(newdt.getNo())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addOaSmssendLog(newdt);
        }
    }*/
	
}
