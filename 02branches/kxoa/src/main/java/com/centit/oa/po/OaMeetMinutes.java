package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaMeetMinutes implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OaMeetMinutesId cid;


	private String  title;
	private Date  begTime;
	private Date  endTime;
	private String  meetingHost;
	private String  meetingNo;
	private String  meetingPersions;
	private String  doDepno;
	private String  doCreater;
	private byte[]  docFile;
	public byte[] getDocFile() {
        return docFile;
    }
    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }

    private String  docFileName;
	private String  remark;
	private String  state;
    private Date motifyTime;
    private String ccPersonnel;
    private  OaMeetinfo oaMeetinfo;

    // Constructors
	/** default constructor */
	public OaMeetMinutes() {
	}
	/** minimal constructor */
	public OaMeetMinutes(OaMeetMinutesId id 
				
		) {
		this.cid = id; 
			
			
	}

public OaMeetMinutes(OaMeetMinutesId cid, String title, Date begTime,
            Date endTime, String meetingHost, String meetingNo,
            String meetingPersions, String doDepno, String doCreater,
            String docFileName, String remark, String state, Date motifyTime,
            String ccPersonnel) {
        super();
        this.cid = cid;
        this.title = title;
        this.begTime = begTime;
        this.endTime = endTime;
        this.meetingHost = meetingHost;
        this.meetingNo = meetingNo;
        this.meetingPersions = meetingPersions;
        this.doDepno = doDepno;
        this.doCreater = doCreater;
        this.docFileName = docFileName;
        this.remark = remark;
        this.state = state;
        this.motifyTime = motifyTime;
        this.ccPersonnel = ccPersonnel;
    }
/** full constructor */
	public OaMeetMinutes(OaMeetMinutesId id
			
	,String  title,Date  begTime,Date  endTime,String  meetingHost,String  meetingNo,String  meetingPersions,String  doDepno,String  doCreater,byte[]  docFile,String  docFileName,String  remark,String  state,Date motifyTime,String ccPersonnel) {
		this.cid = id; 
			
	
		this.title= title;
		this.begTime= begTime;
		this.endTime= endTime;
		this.meetingHost= meetingHost;
		this.meetingNo= meetingNo;
		this.meetingPersions= meetingPersions;
		this.doDepno= doDepno;
		this.doCreater= doCreater;
		this.docFile= docFile;
		this.docFileName= docFileName;
		this.remark= remark;
		this.state= state;	
		this.motifyTime=motifyTime;
		this.ccPersonnel=ccPersonnel;
	}

	
    public OaMeetMinutesId getCid() {
		return this.cid;
	}
	
	public void setCid(OaMeetMinutesId id) {
		this.cid = id;
	}
  
	public String getDjid() {
		if(this.cid==null)
			this.cid = new OaMeetMinutesId();
		return this.cid.getDjid();
	}
	
	public void setDjid(String djid) {
		if(this.cid==null)
			this.cid = new OaMeetMinutesId();
		this.cid.setDjid(djid);
	}
  
	public String getVersion() {
		if(this.cid==null)
			this.cid = new OaMeetMinutesId();
		return this.cid.getVersion();
	}
	
	public void setVersion(String version) {
		if(this.cid==null)
			this.cid = new OaMeetMinutesId();
		this.cid.setVersion(version);
	}
	   public OaMeetinfo getOaMeetinfo() {
	        return oaMeetinfo;
	    }
	    public void setOaMeetinfo(OaMeetinfo oaMeetinfo) {
	        this.oaMeetinfo = oaMeetinfo;
	    }
	    public String getCcPersonnel() {
	        return ccPersonnel;
	    }
	    public void setCcPersonnel(String ccPersonnel) {
	        this.ccPersonnel = ccPersonnel;
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
  
	public String getMeetingPersions() {
		return this.meetingPersions;
	}
	
	public void setMeetingPersions(String meetingPersions) {
		this.meetingPersions = meetingPersions;
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
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}



	public void copy(OaMeetMinutes other){
  
		this.setDjid(other.getDjid());  
		this.setVersion(other.getVersion());
  
		this.title= other.getTitle();  
		this.begTime= other.getBegTime();  
		this.endTime= other.getEndTime();  
		this.meetingHost= other.getMeetingHost();  
		this.meetingNo= other.getMeetingNo();  
		this.meetingPersions= other.getMeetingPersions();  
		this.doDepno= other.getDoDepno();  
		this.doCreater= other.getDoCreater();  
		this.docFile= other.getDocFile();  
		this.docFileName= other.getDocFileName();  
		this.remark= other.getRemark();  
		this.state= other.getState();
		this.motifyTime=other.getMotifyTime();
		this.ccPersonnel=other.getCcPersonnel();

	}
	
	public void copyNotNullProperty(OaMeetMinutes other){
  
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
		if( other.getMeetingPersions() != null)
			this.meetingPersions= other.getMeetingPersions();  
		if( other.getDoDepno() != null)
			this.doDepno= other.getDoDepno();  
		if( other.getDoCreater() != null)
			this.doCreater= other.getDoCreater();  
		if( other.getDocFile() != null)
			this.docFile= other.getDocFile();  
		if( other.getDocFileName() != null)
			this.docFileName= other.getDocFileName();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getState() != null)
			this.state= other.getState();
		if( other.getMotifyTime() != null)
            this.motifyTime= other.getMotifyTime();
		  if( other.getCcPersonnel() != null)
	            this.ccPersonnel= other.getCcPersonnel();

	}
	
	public void clearProperties(){
  
		this.title= null;  
		this.begTime= null;  
		this.endTime= null;  
		this.meetingHost= null;  
		this.meetingNo= null;  
		this.meetingPersions= null;  
		this.doDepno= null;  
		this.doCreater= null;  
		this.docFile= null;  
		this.docFileName= null;  
		this.remark= null;  
		this.state= null;
		this.ccPersonnel= null;

	}
}
