package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaSurveydetail implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OaSurveydetailId cid;


	private String  no;//题目流水号
	private String  title;//选项明细名称
	private String  djid;//调查ID
	private Date  createtime;//发起时间

	// Constructors
	/** default constructor */
	public OaSurveydetail() {
	}
	/** minimal constructor */
	public OaSurveydetail(OaSurveydetailId id 
				
		) {
		this.cid = id; 
			
			
	}

/** full constructor */
	public OaSurveydetail(OaSurveydetailId id
			
	,String  no,String  title,String  djid,Date  createtime) {
		this.cid = id; 
			
	
		this.no= no;
		this.title= title;
		this.djid= djid;
		this.createtime= createtime;		
	}

	public OaSurveydetailId getCid() {
		return this.cid;
	}
	
	public void setCid(OaSurveydetailId id) {
		this.cid = id;
	}
  
	public String getItemid() {
		if(this.cid==null)
			this.cid = new OaSurveydetailId();
		return this.cid.getItemid();
	}
	
	public void setItemid(String itemid) {
		if(this.cid==null)
			this.cid = new OaSurveydetailId();
		this.cid.setItemid(itemid);
	}
  
	public String getCreater() {
		if(this.cid==null)
			this.cid = new OaSurveydetailId();
		return this.cid.getCreater();
	}
	
	public void setCreater(String creater) {
		if(this.cid==null)
			this.cid = new OaSurveydetailId();
		this.cid.setCreater(creater);
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
  
	public String getDjid() {
		return this.djid;
	}
	
	public void setDjid(String djid) {
		this.djid = djid;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}



	public void copy(OaSurveydetail other){
  
		this.setItemid(other.getItemid());  
		this.setCreater(other.getCreater());
  
		this.no= other.getNo();  
		this.title= other.getTitle();  
		this.djid= other.getDjid();  
		this.createtime= other.getCreatetime();

	}
	
	public void copyNotNullProperty(OaSurveydetail other){
  
	if( other.getItemid() != null)
		this.setItemid(other.getItemid());  
	if( other.getCreater() != null)
		this.setCreater(other.getCreater());
  
		if( other.getNo() != null)
			this.no= other.getNo();  
		if( other.getTitle() != null)
			this.title= other.getTitle();  
		if( other.getDjid() != null)
			this.djid= other.getDjid();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();

	}
	
	public void clearProperties(){
  
		this.no= null;  
		this.title= null;  
		this.djid= null;  
		this.createtime= null;

	}
}
