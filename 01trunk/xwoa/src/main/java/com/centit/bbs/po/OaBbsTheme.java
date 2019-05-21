package com.centit.bbs.po;

import java.util.Date;

import com.centit.oa.po.OaLeaveMessage;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class OaBbsTheme implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String themeno;

	private String  layoutno;
	private String  sublayouttitle;
	private String  bodycontent;
	private String  creater;
	private Date  createtime;
	private String  state;
	private String  approval;
	private String  approvalresults;
	private Date  approvaltime;
	private String  approvalremark;
	private Long  postsnum;
	private Long  postsviewnum;
	private Date  lastmodifytime;
	private String  themeset;
	private Long  attentionum;
	private String ip;
	private OaLeaveMessage oaLeaveMessage;

	// Constructors
	/** default constructor */
	public OaBbsTheme() {
	}
	/** minimal constructor */
	public OaBbsTheme(
		String themeno		
		,String  layoutno) {
	
	
		this.themeno = themeno;		
	
		this.layoutno= layoutno; 		
	}

/** full constructor */
	public OaBbsTheme(
	 String themeno		
	,String  layoutno,String  sublayouttitle,String  bodycontent,String  creater,Date  createtime,String  state,String  approval,String  approvalresults,Date  approvaltime,String  approvalremark,Long  postsnum,Long  postsviewnum,Date  lastmodifytime,String  themeset,Long  attentionum) {
	
	
		this.themeno = themeno;		
	
		this.layoutno= layoutno;
		this.sublayouttitle= sublayouttitle;
		this.bodycontent= bodycontent;
		this.creater= creater;
		this.createtime= createtime;
		this.state= state;
		this.approval= approval;
		this.approvalresults= approvalresults;
		this.approvaltime= approvaltime;
		this.approvalremark= approvalremark;
		this.postsnum= postsnum;
		this.postsviewnum= postsviewnum;
		this.lastmodifytime= lastmodifytime;
		this.themeset= themeset;
		this.attentionum= attentionum;		
	}
	

  
	public String getThemeno() {
		return this.themeno;
	}

	public void setThemeno(String themeno) {
		this.themeno = themeno;
	}
	// Property accessors
  
	public String getLayoutno() {
		return this.layoutno;
	}
	
	public void setLayoutno(String layoutno) {
		this.layoutno = layoutno;
	}
  
	public String getSublayouttitle() {
		return this.sublayouttitle;
	}
	
	public void setSublayouttitle(String sublayouttitle) {
		this.sublayouttitle = sublayouttitle;
	}
  
	public String getBodycontent() {
		return this.bodycontent;
	}
	
	public void setBodycontent(String bodycontent) {
		this.bodycontent = bodycontent;
	}
  
	public String getCreater() {
		return this.creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
  
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
  
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
  
	public String getApproval() {
		return this.approval;
	}
	
	public void setApproval(String approval) {
		this.approval = approval;
	}
  
	public String getApprovalresults() {
		return this.approvalresults;
	}
	
	public void setApprovalresults(String approvalresults) {
		this.approvalresults = approvalresults;
	}
  
	public Date getApprovaltime() {
		return this.approvaltime;
	}
	
	public void setApprovaltime(Date approvaltime) {
		this.approvaltime = approvaltime;
	}
  
	public String getApprovalremark() {
		return this.approvalremark;
	}
	
	public void setApprovalremark(String approvalremark) {
		this.approvalremark = approvalremark;
	}
  
	public Long getPostsnum() {
		return this.postsnum;
	}
	
	public void setPostsnum(Long postsnum) {
		this.postsnum = postsnum;
	}
  
	public Long getPostsviewnum() {
		return this.postsviewnum;
	}
	
	public void setPostsviewnum(Long postsviewnum) {
		this.postsviewnum = postsviewnum;
	}
  
	public Date getLastmodifytime() {
		return this.lastmodifytime;
	}
	
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
  
	public String getThemeset() {
		return this.themeset;
	}
	
	public void setThemeset(String themeset) {
		this.themeset = themeset;
	}
  
	public Long getAttentionum() {
		return this.attentionum;
	}
	
	public void setAttentionum(Long attentionum) {
		this.attentionum = attentionum;
	}
    


	public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void copy(OaBbsTheme other){
  
		this.setThemeno(other.getThemeno());
  
		this.layoutno= other.getLayoutno();  
		this.sublayouttitle= other.getSublayouttitle();  
		this.bodycontent= other.getBodycontent();  
		this.creater= other.getCreater();  
		this.createtime= other.getCreatetime();  
		this.state= other.getState();  
		this.approval= other.getApproval();  
		this.approvalresults= other.getApprovalresults();  
		this.approvaltime= other.getApprovaltime();  
		this.approvalremark= other.getApprovalremark();  
		this.postsnum= other.getPostsnum();  
		this.postsviewnum= other.getPostsviewnum();  
		this.lastmodifytime= other.getLastmodifytime();  
		this.themeset= other.getThemeset();  
		this.attentionum= other.getAttentionum();
        this.ip = other.getIp();
	}
	
	public void copyNotNullProperty(OaBbsTheme other){
  
	if( other.getThemeno() != null)
		this.setThemeno(other.getThemeno());
  
		if( other.getLayoutno() != null)
			this.layoutno= other.getLayoutno();  
		if( other.getSublayouttitle() != null)
			this.sublayouttitle= other.getSublayouttitle();  
		if( other.getBodycontent() != null)
			this.bodycontent= other.getBodycontent();  
		if( other.getCreater() != null)
			this.creater= other.getCreater();  
		if( other.getCreatetime() != null)
			this.createtime= other.getCreatetime();  
		if( other.getState() != null)
			this.state= other.getState();  
		if( other.getApproval() != null)
			this.approval= other.getApproval();  
		if( other.getApprovalresults() != null)
			this.approvalresults= other.getApprovalresults();  
		if( other.getApprovaltime() != null)
			this.approvaltime= other.getApprovaltime();  
		if( other.getApprovalremark() != null)
			this.approvalremark= other.getApprovalremark();  
		if( other.getPostsnum() != null)
			this.postsnum= other.getPostsnum();  
		if( other.getPostsviewnum() != null)
			this.postsviewnum= other.getPostsviewnum();  
		if( other.getLastmodifytime() != null)
			this.lastmodifytime= other.getLastmodifytime();  
		if( other.getThemeset() != null)
			this.themeset= other.getThemeset();  
		if( other.getAttentionum() != null)
			this.attentionum= other.getAttentionum();
        if( other.getIp() != null)
            this.ip = other.getIp();
	}
	
	public void clearProperties(){
  
		this.layoutno= null;  
		this.sublayouttitle= null;  
		this.bodycontent= null;  
		this.creater= null;  
		this.createtime= null;  
		this.state= null;  
		this.approval= null;  
		this.approvalresults= null;  
		this.approvaltime= null;  
		this.approvalremark= null;  
		this.postsnum= null;  
		this.postsviewnum= null;  
		this.lastmodifytime= null;  
		this.themeset= null;  
		this.attentionum= null;
        this.ip = null;
	}
    public OaLeaveMessage getOaLeaveMessage() {
        return oaLeaveMessage;
    }
    public void setOaLeaveMessage(OaLeaveMessage oaLeaveMessage) {
        this.oaLeaveMessage = oaLeaveMessage;
    }
}
