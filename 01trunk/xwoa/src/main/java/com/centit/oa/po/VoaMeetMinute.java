package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VoaMeetMinute implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	

    private String djid;

    public String getDjid() {
        return djid;
    }
    public void setDjid(String djid) {
        this.djid = djid;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    private String version;

	private String  title;
	private Date  begTime;
	private Date  endTime;
	private String  meetingHost;
	private String  meetingNo;
	private String  doDepno;
	private String  doCreater;	
    private String  docFileName;
	private String  state;
    private Date motifyTime;

    private  OaMeetinfo oaMeetinfo;
    public OaMeetinfo getOaMeetinfo() {
        return oaMeetinfo;
    }
    public void setOaMeetinfo(OaMeetinfo oaMeetinfo) {
        this.oaMeetinfo = oaMeetinfo;
    }
    // Constructors
	/** default constructor */
	public VoaMeetMinute() {
	}
	/** minimal constructor */
	public VoaMeetMinute(OaMeetMinutesId id 
				
		) {
	
			
			
	}

public VoaMeetMinute(String djid,String version, String title, Date begTime,
            Date endTime, String meetingHost, String meetingNo,
            String meetingPersions, String doDepno, String doCreater,
            String docFileName,  String state, Date motifyTime
           ) {
        super();
 
        this.title = title;
        this.begTime = begTime;
        this.endTime = endTime;
        this.meetingHost = meetingHost;
        this.meetingNo = meetingNo;
  
        this.doDepno = doDepno;
        this.doCreater = doCreater;
        this.docFileName = docFileName;
    
        this.state = state;
        this.motifyTime = motifyTime;
    
    }
/** full constructor */
	public VoaMeetMinute(OaMeetMinutesId id
			
	,String  title,Date  begTime,Date  endTime,String  meetingHost,String  meetingNo,String  meetingPersions,String  doDepno,String  doCreater,byte[]  docFile,String  docFileName,String  remark,String  state,Date motifyTime,String ccPersonnel) {
		 
			
	
		this.title= title;
		this.begTime= begTime;
		this.endTime= endTime;
		this.meetingHost= meetingHost;
		this.meetingNo= meetingNo;
	
		this.doDepno= doDepno;
		this.doCreater= doCreater;

		this.docFileName= docFileName;
		
		this.state= state;	
		this.motifyTime=motifyTime;
		
	}

	
 
  

	    public Date getMotifyTime() {
	        return motifyTime;
	    }
	    public void setMotifyTime(Date motifyTime) {
	        this.motifyTime = motifyTime;
	    }
	

	// Property accessors
  
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
  
	public Date getBegTime() {
		return this.begTime;
	}
	
	public void setBegTime(Date begTime) {
		this.begTime = begTime;
	}
  
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
  
	public String getMeetingHost() {
		return this.meetingHost;
	}
	
	public void setMeetingHost(String meetingHost) {
		this.meetingHost = meetingHost;
	}
  
	public String getMeetingNo() {
		return this.meetingNo;
	}
	
	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}
 
	public String getDoDepno() {
		return this.doDepno;
	}
	
	public void setDoDepno(String doDepno) {
		this.doDepno = doDepno;
	}
  
	public String getDoCreater() {
		return this.doCreater;
	}
	
	public void setDoCreater(String doCreater) {
		this.doCreater = doCreater;
	}
  

  
	public String getDocFileName() {
		return this.docFileName;
	}
	
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}



	public void copy(VoaMeetMinute other){
  
		this.setDjid(other.getDjid());  
		this.setVersion(other.getVersion());
  
		this.title= other.getTitle();  
		this.begTime= other.getBegTime();  
		this.endTime= other.getEndTime();  
		this.meetingHost= other.getMeetingHost();  
		this.meetingNo= other.getMeetingNo();  

		this.doDepno= other.getDoDepno();  
		this.doCreater= other.getDoCreater();  

		this.docFileName= other.getDocFileName();  

		this.state= other.getState();
		this.motifyTime=other.getMotifyTime();


	}
	
	public void copyNotNullProperty(VoaMeetMinute other){
  
	if( other.getDjid() != null)
		this.setDjid(other.getDjid());  
	if( other.getVersion() != null)
		this.setVersion(other.getVersion());
  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getBegTime() != null)
			this.begTime= other.getBegTime();  
		if( other.getEndTime() != null)
			this.endTime= other.getEndTime();  
		if( other.getMeetingHost() != null)
			this.meetingHost= other.getMeetingHost();  
		if( other.getMeetingNo() != null)
			this.meetingNo= other.getMeetingNo();  
	
		if( other.getDoDepno() != null)
			this.doDepno= other.getDoDepno();  
		if( other.getDoCreater() != null)
			this.doCreater= other.getDoCreater();  

		if( other.getDocFileName() != null)
			this.docFileName= other.getDocFileName();  

		if( other.getState() != null)
			this.state= other.getState();
		if( other.getMotifyTime() != null)
            this.motifyTime= other.getMotifyTime();


	}
	
	public void clearProperties(){
  
		this.title= null;  
		this.begTime= null;  
		this.endTime= null;  
		this.meetingHost= null;  
		this.meetingNo= null;  

		this.doDepno= null;  
		this.doCreater= null;  
	
		this.docFileName= null;  
	
		this.state= null;

	}
}
