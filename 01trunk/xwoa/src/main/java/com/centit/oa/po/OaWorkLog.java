package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaWorkLog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String no;//序号

	private String  infoType;//日志类型 字典项OAInfoType=m:个人w:工作
	private String  title;//日志主题
	private Date  releaseDate;//日志日期
	private String  remark;//日志内容
	private String  creater;//创建人
	private Date  creatertime;//创建日期
	private String  isallowcomment;//是否可以回复留言
	private String  isshare;//是否共享
	private String  shares;//共享人

	// Constructors
	/** default constructor */
	public OaWorkLog() {
	}
	/** minimal constructor */
	public OaWorkLog(
		String no		
		) {
	
	
		this.no = no;		
			
	}

/** full constructor */
	public OaWorkLog(
	 String no		
	,String  infoType,String  title,Date  releaseDate,String  remark,String  creater,Date  creatertime,String  isallowcomment,String  isshare,String  shares) {
	
	
		this.no = no;		
	
		this.infoType= infoType;
		this.title= title;
		this.releaseDate= releaseDate;
		this.remark= remark;
		this.creater= creater;
		this.creatertime= creatertime;
		this.isallowcomment= isallowcomment;
		this.isshare= isshare;
		this.shares= shares;		
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
  
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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
  
	public String getIsallowcomment() {
		return this.isallowcomment;
	}
	
	public void setIsallowcomment(String isallowcomment) {
		this.isallowcomment = isallowcomment;
	}
  
	public String getIsshare() {
		return this.isshare;
	}
	
	public void setIsshare(String isshare) {
		this.isshare = isshare;
	}
  
	public String getShares() {
		return this.shares;
	}
	
	public void setShares(String shares) {
		this.shares = shares;
	}



	public void copy(OaWorkLog other){
  
		this.setNo(other.getNo());
  
		this.infoType= other.getInfoType();  
		this.title= other.getTitle();  
		this.releaseDate= other.getReleaseDate();  
		this.remark= other.getRemark();  
		this.creater= other.getCreater();  
		this.creatertime= other.getCreatertime();  
		this.isallowcomment= other.getIsallowcomment();  
		this.isshare= other.getIsshare();  
		this.shares= other.getShares();

	}
	
	public void copyNotNullProperty(OaWorkLog other){
  
	if( other.getNo() != null)
		this.setNo(other.getNo());
  
		if( other.getInfoType() != null)
			this.infoType= other.getInfoType();  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getReleaseDate() != null)
			this.releaseDate= other.getReleaseDate();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatertime() != null)
			this.creatertime= other.getCreatertime();  
		if( other.getIsallowcomment() != null)
			this.isallowcomment= other.getIsallowcomment();  
		if( other.getIsshare() != null)
			this.isshare= other.getIsshare();  
		if( other.getShares() != null)
			this.shares= other.getShares();

	}
	
	public void clearProperties(){
  
		this.infoType= null;  
		this.title= null;  
		this.releaseDate= null;  
		this.remark= null;  
		this.creater= null;  
		this.creatertime= null;  
		this.isallowcomment= null;  
		this.isshare= null;  
		this.shares= null;

	}
}
