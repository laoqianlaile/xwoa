package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaSubvideoInformation implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String id;

	private String  no;
	private String  title;
	private Date  releaseDate;
	private String  isuseprarent;
	private String  smallimage;
	private String  videoPath;
	private String  remark;
	private String  creater;
	private Date  creatertime;
	private Date  lastmodifytime;
	private String  state;
	private Long clickNum;

	// Constructors
	/** default constructor */
	public OaSubvideoInformation() {
	}
	/** minimal constructor */
	public OaSubvideoInformation(
		String id		
		) {
	
	
		this.id = id;		
			
	}

/** full constructor */
	public OaSubvideoInformation(
	 String id		,Long clickNum
	,String  no,String  title,Date  releaseDate,String  isuseprarent,String  smallimage,String  videoPath,String  remark,String  creater,Date  creatertime,Date  lastmodifytime,String  state) {
	
	
		this.id = id;		
	
		this.no= no;
		this.title= title;
		this.releaseDate= releaseDate;
		this.isuseprarent= isuseprarent;
		this.smallimage= smallimage;
		this.videoPath= videoPath;
		this.remark= remark;
		this.creater= creater;
		this.creatertime= creatertime;
		this.lastmodifytime= lastmodifytime;
		this.state= state;		
		this.clickNum=clickNum;
	}
	

  
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	// Property accessors
  
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
  
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
  
	
    public Long getClickNum() {
        return clickNum;
    }
    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }
    public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
  
	public String getIsuseprarent() {
		return this.isuseprarent;
	}
	
	public void setIsuseprarent(String isuseprarent) {
		this.isuseprarent = isuseprarent;
	}
  
	public String getSmallimage() {
		return this.smallimage;
	}
	
	public void setSmallimage(String smallimage) {
		this.smallimage = smallimage;
	}
  
	public String getVideoPath() {
		return this.videoPath;
	}
	
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatertime() {
		return this.creatertime;
	}
	
	public void setCreatertime(Date creatertime) {
		this.creatertime = creatertime;
	}
  
	public Date getLastmodifytime() {
		return this.lastmodifytime;
	}
	
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}



	public void copy(OaSubvideoInformation other){
  
		this.setId(other.getId());
  
		this.no= other.getNo();  
		this.title= other.getTitle();  
		this.releaseDate= other.getReleaseDate();  
		this.isuseprarent= other.getIsuseprarent();  
		this.smallimage= other.getSmallimage();  
		this.videoPath= other.getVideoPath();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.state= other.getState();
		this.clickNum=other.getClickNum();

	}
	
	public void copyNotNullProperty(OaSubvideoInformation other){
  
	if( other.getId() != null)
		this.setId(other.getId());
  
		if( other.getNo() != null)
			this.no= other.getNo();  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getReleaseDate() != null)
			this.releaseDate= other.getReleaseDate();  
		if( other.getIsuseprarent() != null)
			this.isuseprarent= other.getIsuseprarent();  
		if( other.getSmallimage() != null)
			this.smallimage= other.getSmallimage();  
		if( other.getVideoPath() != null)
			this.videoPath= other.getVideoPath();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getLastmodifytime() != null)
			this.lastmodifytime= other.getLastmodifytime();  
		if( other.getState() != null)
			this.state= other.getState();
		if(other.getClickNum()!=null)
		    this.clickNum=other.getClickNum();

	}
	
	public void clearProperties(){
  
		this.no= null;  
		this.title= null;  
		this.releaseDate= null;  
		this.isuseprarent= null;  
		this.smallimage= null;  
		this.videoPath= null;  
		this.remark= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.lastmodifytime= null;  
		this.state= null;
		this.clickNum=null;

	}
}
