package com.centit.bbs.po;

import java.util.Date;

import com.centit.oa.po.OaLeaveMessage;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class VBbsThemeUser implements java.io.Serializable {
    private static final long serialVersionUID =  1L;


    private VBbsThemeUserId vid;
    
    //private String themeno;

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
    
   // private String manager;
    
    private String layoutcode  ;//所属大模块主键
    private OaLeaveMessage oaLeaveMessage;
    
    public VBbsThemeUserId getVid() {
        return vid;
    }
    
    public void setVid(VBbsThemeUserId vid) {
        this.vid = vid;
    }
    
    // Constructors
    /** default constructor */
    public VBbsThemeUser() {
    }
    /** minimal constructor */
    public VBbsThemeUser(
            VBbsThemeUserId vid) {
    
        this.vid = vid;
    }

/** full constructor */
    public VBbsThemeUser(
     String themeno     
    ,String  layoutno,String  sublayouttitle,String  bodycontent,String  creater,Date  createtime,String  state,String  approval,String  approvalresults,Date  approvaltime,String  approvalremark,Long  postsnum,Long  postsviewnum,Date  lastmodifytime,String  themeset,Long  attentionum, String manager,String layoutcode) {
    
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
        this.layoutcode=layoutcode;
    }
    

  
    public String getThemeno() {
        if(this.vid==null)
            this.vid = new VBbsThemeUserId();
        return this.vid.getThemeno();
    }

    public void setThemeno(String themeno) {
        if(this.vid==null)
            this.vid = new VBbsThemeUserId();
        this.vid.setThemeno(themeno);
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

    public String getManager() {
        if(this.vid==null)
            this.vid = new VBbsThemeUserId();
        return this.vid.getManager();
    }
    
    public void setManager(String manager) {
        if(this.vid==null)
            this.vid = new VBbsThemeUserId();
        this.vid.setManager(manager);
    }
    
    public void copy(VBbsThemeUser other){
  
        this.setThemeno(other.getThemeno());
        this.setManager(other.getManager());
  
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

    }
    
    public void copyNotNullProperty(VBbsThemeUser other){
  
        if (other.getThemeno() != null)
            this.setThemeno(other.getThemeno());
        if( other.getManager() != null)
            this.setManager(other.getManager());
  
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
        this.layoutcode=null;

    }
    public OaLeaveMessage getOaLeaveMessage() {
        return oaLeaveMessage;
    }
    public void setOaLeaveMessage(OaLeaveMessage oaLeaveMessage) {
        this.oaLeaveMessage = oaLeaveMessage;
    }

    public String getLayoutcode() {
        return layoutcode;
    }

    public void setLayoutcode(String layoutcode) {
        this.layoutcode = layoutcode;
    }
    
    
}
