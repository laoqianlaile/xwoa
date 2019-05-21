package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VOaInformation implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;

	private String  infoType;
	private String  title;
	private String  majorDegree;
	private Date  releaseDate;
	private Date  validDate;
	private String  publicKey;
	private String  docNo;
	private String  author;
	//private byte[]  uploadFile;
	private String  uploadFileName;
	private String  referenceLinks;
	//private String  bodyContent;
	//private String  remark;
	private Date  lastmodifytime;
	private String  creater;
	private Date  creatertime;
	private String  state;
	private String depno;
	
	private String isAllowComment;//留言回复
	
	private String imagePath;//是否为图片新闻
	
	private String readstate;//阅读状态 by dk:2016-2-22
	private String newmsg;//新消息标记 by dk:2016-3-8
	
	private String isTop;//是否置顶 by dk :2016-8-11
	
    // Constructors
	/** default constructor */
	public VOaInformation() {
	}
	/** minimal constructor */
	public VOaInformation(
		String no		
		) {
	
	
		this.no = no;		
			
	}

public VOaInformation(String no, String infoType, String title,
            String majorDegree, Date releaseDate, Date validDate,
            String publicKey, String docNo, String author,
            String uploadFileName, String referenceLinks, String remark,
            Date lastmodifytime, String creater, Date creatertime, String state,String isAllowComment,String newmsg) {
        super();
        this.no = no;
        this.infoType = infoType;
        this.title = title;
        this.majorDegree = majorDegree;
        this.releaseDate = releaseDate;
        this.validDate = validDate;
        this.publicKey = publicKey;
        this.docNo = docNo;
        this.author = author;
        this.uploadFileName = uploadFileName;
        this.referenceLinks = referenceLinks;
       // this.remark = remark;
        this.lastmodifytime = lastmodifytime;
        this.creater = creater;
        this.creatertime = creatertime;
        this.state = state;
        this.isAllowComment=isAllowComment;
        this.newmsg=newmsg;
    }
/** full constructor */
	public VOaInformation(
	 String no		
	,String  infoType,String  title,String  majorDegree,Date  releaseDate,Date  validDate,String  publicKey,String  docNo,String  author,byte[]  uploadFile,String  uploadFileName,String  referenceLinks,String  bodyContent,String  remark,Date  lastmodifytime,String  creater,Date  creatertime,String  state,String depno,String isAllowComment,String imagePath,String newmsg) {
	
	
		this.no = no;		
	
		this.infoType= infoType;
		this.title= title;
		this.majorDegree= majorDegree;
		this.releaseDate= releaseDate;
		this.validDate= validDate;
		this.publicKey= publicKey;
		this.docNo= docNo;
		this.author= author;
		//this.uploadFile= uploadFile;
		this.uploadFileName= uploadFileName;
		this.referenceLinks= referenceLinks;
		//this.bodyContent= bodyContent;
		//this.remark= remark;
		this.lastmodifytime= lastmodifytime;
		this.creater= creater;
		this.creatertime= creatertime;
		this.state= state;		
		this.depno= depno;
		this.imagePath=imagePath;
	    this.isAllowComment=isAllowComment;
	    this.newmsg=newmsg;
	}
	
	public VOaInformation(String no,String infoType,String title,String majorDegree,
	        Date releaseDate,Date validDate,String publicKey ,String docNo,
	        String author ,String uploadFileName ,
	        String referenceLinks,Date lastmodifytime,String creater,Date creatertime,
	        String state,String depno ,String isAllowComment  ,String  imagePath  ,String   newmsg){
	    this.no = no;      
        this.infoType= infoType;
        this.title= title;
        this.majorDegree= majorDegree;
        this.releaseDate= releaseDate;
        this.validDate= validDate;
        this.publicKey= publicKey;
        this.docNo= docNo;
        this.author= author;
        this.uploadFileName= uploadFileName;
        this.referenceLinks= referenceLinks;
        this.lastmodifytime= lastmodifytime;
        this.creater= creater;
        this.creatertime= creatertime;
        this.state= state;      
        this.depno= depno;
        this.isAllowComment=isAllowComment;
        this.imagePath=imagePath;
        this.newmsg=newmsg;
	}
	
	public VOaInformation(String no,String title,Date releaseDate,
            String state,String   newmsg,String majorDegree,String publicKey ,String docNo,String creater){
        this.no = no;      
        this.title= title;
        this.majorDegree=majorDegree;
        this.releaseDate= releaseDate;
        this.state= state;      
        this.newmsg=newmsg;
        this.publicKey= publicKey;
        this.docNo= docNo;
        this.creater= creater;
    }
	
	
	
	public String getIsAllowComment() {
    return isAllowComment;
}
public void setIsAllowComment(String isAllowComment) {
    this.isAllowComment = isAllowComment;
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
	
	public void setTitle(String title) {
		this.title = title;
	}
  
	public String getMajorDegree() {
		return this.majorDegree;
	}
	
	public void setMajorDegree(String majorDegree) {
		this.majorDegree = majorDegree;
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
	
	
	public String getDepno() {
        return depno;
    }
    public void setDepno(String depno) {
        this.depno = depno;
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
  
	public String getDocNo() {
		return this.docNo;
	}
	
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
  
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
  
	/*public byte[] getUploadFile() {
		return this.uploadFile;
	}
	
	public void setUploadFile(byte[] uploadFile) {
		this.uploadFile = uploadFile;
	}*/
  
	public String getUploadFileName() {
		return this.uploadFileName;
	}
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
  
	public String getReferenceLinks() {
		return this.referenceLinks;
	}
	
	public void setReferenceLinks(String referenceLinks) {
		this.referenceLinks = referenceLinks;
	}
  
	/*public String getBodyContent() {
		return this.bodyContent;
	}
	
	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}*/
  
	public Date getLastmodifytime() {
		return this.lastmodifytime;
	}
	
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
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
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	public String getNewmsg() {
        return newmsg;
    }
    public void setNewmsg(String newmsg) {
        this.newmsg = newmsg;
    }
    public String getReadstate() {
        return readstate;
    }
    public void setReadstate(String readstate) {
        this.readstate = readstate;
    }

    
	public String getIsTop() {
        return isTop;
    }
    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }
    public void copy(VOaInformation other){
  
		this.setNo(other.getNo());
  
		this.infoType= other.getInfoType();  
		this.title= other.getTitle();  
		this.majorDegree= other.getMajorDegree();  
		this.releaseDate= other.getReleaseDate();  
		this.validDate= other.getValidDate();  
		this.publicKey= other.getPublicKey();  
		this.docNo= other.getDocNo();  
		this.author= other.getAuthor();  
		//this.uploadFile= other.getUploadFile();  
		this.uploadFileName= other.getUploadFileName();  
		this.referenceLinks= other.getReferenceLinks();  
		//this.bodyContent= other.getBodyContent();  
		//this.remark= other.getRemark();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.state= other.getState();
		this.depno= other.getDepno();
		this.imagePath=other.getImagePath();
		this.isAllowComment=other.getIsAllowComment();
		this.readstate=other.getReadstate();
		this.newmsg=other.getNewmsg();
		this.isTop=other.getIsTop();
	}
	
	public void copyNotNullProperty(VOaInformation other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getInfoType() != null)
			this.infoType= other.getInfoType();  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getMajorDegree() != null)
			this.majorDegree= other.getMajorDegree();  
		if( other.getReleaseDate() != null)
			this.releaseDate= other.getReleaseDate();  
		if( other.getValidDate() != null)
			this.validDate= other.getValidDate();  
		if( other.getPublicKey() != null)
			this.publicKey= other.getPublicKey();  
		if( other.getDocNo() != null)
			this.docNo= other.getDocNo();  
		if( other.getAuthor() != null)
			this.author= other.getAuthor();  
		/*if( other.getUploadFile() != null)
			this.uploadFile= other.getUploadFile();*/  
		if( other.getUploadFileName() != null)
			this.uploadFileName= other.getUploadFileName();  
		if( other.getReferenceLinks() != null)
		/*	this.referenceLinks= other.getReferenceLinks();  
		if( other.getBodyContent() != null)
			this.bodyContent= other.getBodyContent();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  */
		if( other.getLastmodifytime() != null)
			this.lastmodifytime= other.getLastmodifytime();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getState() != null)
			this.state= other.getState();
		if( other.getDepno() != null)
            this.depno= other.getDepno();
		if (other.getIsAllowComment()!=null)
	        this.isAllowComment=other.getIsAllowComment();

		if(other.getImagePath()!=null)
		    this.imagePath=other.getImagePath();
		if(other.getReadstate()!=null)
            this.readstate=other.getReadstate();
		if(other.getNewmsg()!=null)
		    this.newmsg=other.getNewmsg();
		if(other.getIsTop() != null)
		    this.isTop=other.getIsTop();
	}
	
	public void clearProperties(){
  
		this.infoType= null;  
		this.title= null;  
		this.majorDegree= null;  
		this.releaseDate= null;  
		this.validDate= null;  
		this.publicKey= null;  
		this.docNo= null;  
		this.author= null;  
		//this.uploadFile= null;  
		this.uploadFileName= null;  
		this.referenceLinks= null;  
		//this.bodyContent= null;  
		//this.remark= null;  
		this.lastmodifytime= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.state= null;
		this.depno= null;
		this.imagePath=null;
		this.isAllowComment=null;
		this.readstate=null;
		this.newmsg=null;
		this.isTop=null;
	}
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
	
	
  
}
