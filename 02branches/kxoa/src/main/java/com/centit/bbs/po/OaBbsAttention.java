package com.centit.bbs.po;

import java.util.Date;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaBbsAttention implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	private OaBbsAttentionId cid;


	private Date  createtime;
	
	private Long attentionNum;//支持，收藏统计数据
//	private String  laytype;//联合主键

	// Constructors
	/** default constructor */
	public OaBbsAttention() {
	}
	/** minimal constructor */
	public OaBbsAttention(OaBbsAttentionId id 
				
		) {
		this.cid = id; 
			
			
	}

/** full constructor */
	public OaBbsAttention(OaBbsAttentionId id
			
	,Date  createtime) {
		this.cid = id; 
			
	
		this.createtime= createtime;
//		this.laytype= laytype;		
	}

	public OaBbsAttentionId getCid() {
		return this.cid;
	}
	
	public void setCid(OaBbsAttentionId id) {
		this.cid = id;
	}
  
	public String getThemeno() {
		if(this.cid==null)
			this.cid = new OaBbsAttentionId();
		return this.cid.getThemeno();
	}
	
	public void setThemeno(String themeno) {
		if(this.cid==null)
			this.cid = new OaBbsAttentionId();
		this.cid.setThemeno(themeno);
	}
  
	public String getUsercode() {
		if(this.cid==null)
			this.cid = new OaBbsAttentionId();
		return this.cid.getUsercode();
	}
	
	public void setUsercode(String usercode) {
		if(this.cid==null)
			this.cid = new OaBbsAttentionId();
		this.cid.setUsercode(usercode);
	}
	
	

	// Property accessors
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
//	public String getLaytype() {
//		return this.laytype;
//	}
//	
//	public void setLaytype(String laytype) {
//		this.laytype = laytype;
//	}



	public void copy(OaBbsAttention other){
  
		this.setThemeno(other.getThemeno());  
		this.setUsercode(other.getUsercode());
  
		this.createtime= other.getCreatetime();  
//		this.laytype= other.getLaytype();

	}
	
	public void copyNotNullProperty(OaBbsAttention other){
  
	if( other.getThemeno() != null)
		this.setThemeno(other.getThemeno());  
	if( other.getUsercode() != null)
		this.setUsercode(other.getUsercode());
  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
//		if( other.getLaytype() != null)
//			this.laytype= other.getLaytype();

	}
	
	public void clearProperties(){
  
		this.createtime= null;  
//		this.laytype= null;

	}
    public Long getAttentionNum() {
        return attentionNum;
    }
    public void setAttentionNum(Long attentionNum) {
        this.attentionNum = attentionNum;
    }
}
