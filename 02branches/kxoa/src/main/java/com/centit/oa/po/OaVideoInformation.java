package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaVideoInformation implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;
	private String  videoType;
	private String  infoType;
	private String  title;
	private Date  releaseDate;
	private Date  validDate;
	private String  publicKey;
    private String  bigImage;
    private String  smallImage;
	private String  referenceLinks;
    private String  bookmark;
    private String  remark;
	private String  creater;
	private Date  creatertime;
	private Date  lastmodifytime;
	private String  state;      
	private String isAllowComment;//留言回复
	private Long clickNum;//点击次数
	
	public String getIsAllowComment() {
        return isAllowComment;
    }
    public void setIsAllowComment(String isAllowComment) {
        this.isAllowComment = isAllowComment;
    }
    // Constructors
	/** default constructor */
	public OaVideoInformation() {
	}
	/** minimal constructor */
	public OaVideoInformation(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaVideoInformation(
	 String no	,String videoType	,String bigImage,String smallImage ,String  bookmark,Long clickNum
	,String  infoType,String  title,Date  releaseDate,Date  validDate,String  publicKey,String  referenceLinks,String  remark,String  creater,Date  creatertime,Date  lastmodifytime,String  state,String isAllowComment) {
	
	
		this.no = no;		
	
		this.infoType= infoType;
		this.title= title;
		this.releaseDate= releaseDate;
		this.validDate= validDate;
		this.publicKey= publicKey;
		this.videoType= videoType;
		this.bigImage=bigImage;
		this.smallImage=smallImage;
		this.bookmark=bookmark;
		this.referenceLinks= referenceLinks;
		this.remark= remark;
		this.creater= creater;
		this.creatertime= creatertime;
		this.lastmodifytime= lastmodifytime;
		this.state= state;

		this.isAllowComment=isAllowComment;
		this.clickNum=clickNum;
	}
	

  
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	// Property accessors
  
	public String getInfoType() {
		return this.infoType;
	}
	
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
  
	public String getTitle() {
		return this.title;
	}
	

    public Long getClickNum() {
        return clickNum;
    }
    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }
    public void setTitle(String title) {
		this.title = title;
	}
  
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
  
	public Date getValidDate() {
		return this.validDate;
	}
	
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
  
	public String getPublicKey() {
		return this.publicKey;
	}
	
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
  

  
	public String getReferenceLinks() {
		return this.referenceLinks;
	}
	
	public void setReferenceLinks(String referenceLinks) {
		this.referenceLinks = referenceLinks;
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

    public String getVideoType() {
        return videoType;
    }
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
    public String getBigImage() {
        return bigImage;
    }
    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }
    public String getSmallImage() {
        return smallImage;
    }
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }
    public String getBookmark() {
        return bookmark;
    }
    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

	public void copy(OaVideoInformation other){
  
		this.setNo(other.getNo());
        this.videoType=other.getVideoType();
		this.infoType= other.getInfoType();  
		this.title= other.getTitle();  
		this.releaseDate= other.getReleaseDate();  
		this.validDate= other.getValidDate();  
		this.publicKey= other.getPublicKey();  
	this.bigImage=other.getBigImage();
	this.smallImage=other.getSmallImage();
		this.referenceLinks= other.getReferenceLinks();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.state= other.getState();
		
		this.isAllowComment=other.getIsAllowComment();
		this.clickNum = other.getClickNum();
        this.bookmark = other.getBookmark();
	}
	
	public void copyNotNullProperty(OaVideoInformation other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getInfoType() != null)
			this.infoType= other.getInfoType();  
		if( other.getVideoType() != null)
            this.videoType= other.getVideoType();  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getReleaseDate() != null)
			this.releaseDate= other.getReleaseDate();  
		if( other.getValidDate() != null)
			this.validDate= other.getValidDate();  
		if( other.getPublicKey() != null)
			this.publicKey= other.getPublicKey();  

		if( other.getReferenceLinks() != null)
			this.referenceLinks= other.getReferenceLinks();  
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
		if( other.getBigImage() != null)
            this.bigImage= other.getBigImage();
		if( other.getSmallImage()!= null)
            this.smallImage= other.getSmallImage();
		if( other.getBookmark() != null)
            this.bookmark= other.getBookmark();
		if (other.getIsAllowComment()!=null)
		    this.isAllowComment=other.getIsAllowComment();
		if(other.getClickNum()!=null)
		    this.clickNum=other.getClickNum();


	}
	
	public void clearProperties(){
  
		this.infoType= null;  
		this.title= null;  
		this.releaseDate= null;  
		this.validDate= null;  
		this.publicKey= null;  

		this.referenceLinks= null;  
		this.remark= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.lastmodifytime= null;  
		this.state= null;
		this.videoType= null;
		this.bigImage= null;
		this.smallImage= null;
		this.bookmark= null;
        this.isAllowComment=null;
        this.clickNum=null;
        
	}
  
}
