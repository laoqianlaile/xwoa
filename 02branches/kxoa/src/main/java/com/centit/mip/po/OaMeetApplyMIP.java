package com.centit.mip.po;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * 
 * TODO Class description should be added
 * 车辆申请接口数据
 * @author lq
 * @create 2016年5月12日
 * @version
 */

public class OaMeetApplyMIP implements java.io.Serializable {
	private static final long serialVersionUID =  1L;
	/**
	 * 用户唯一ID
	 */
	@Expose(serialize = false) 
	private String userid;
	/**
     * 关键字
     */
    @Expose(serialize = false) 
    private String boardroomid;
	/**
	 * 关键字
	 */
	@Expose(serialize = false) 
    private String keyword;
	/**
	 * 分页-开始时间
	 */
	@Expose(serialize = false) 
    private String currentdatetime;
	/**
	 * 页数
	 */
	@Expose(serialize = false) 
    private String pagesize;
	
	
	
	/**
	 * 列表类型
	 * 枚举 0 自己所有的  1全部（自己所有+别人审核通过的）
	 */
	@Expose(serialize = false) 
    private String type;
    @Expose  
    private String meetingid; //"会议实例id",
    @Expose  
    private String applicantid; //"申请人id",（来决定是否具备取消预订功能）
    @Expose  
    private String applicant; //"申请人姓名",
    @Expose  
    private String title; //"会议主题",
    @Expose  
    private String starttime; //"会议开始时间",
    @Expose   
    private String endtime; //"会议结束时间
    @Expose  
    private String state; //会议申请状态
    @Expose
    private String meetingName; //会议室
    
    @Expose
    private String isBasicUnit; // "是否有基层单位参加",
    
    @Expose
    private String isStopCar; // "是否需要停车",
    
    public String getMeetingid() {
        return meetingid;
    }
    public void setMeetingid(String meetingid) {
        this.meetingid = meetingid;
    }
    public String getApplicantid() {
        return applicantid;
    }
    public void setApplicantid(String applicantid) {
        this.applicantid = applicantid;
    }
    public String getApplicant() {
        return applicant;
    }
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getCurrentdatetime() {
        return currentdatetime;
    }
    public void setCurrentdatetime(String currentdatetime) {
        this.currentdatetime = currentdatetime;
    }
    public String getPagesize() {
        return pagesize;
    }
    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMeetingName() {
        return meetingName;
    }
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
    public String getBoardroomid() {
        return boardroomid;
    }
    public void setBoardroomid(String boardroomid) {
        this.boardroomid = boardroomid;
    }
    public String getIsBasicUnit() {
        return isBasicUnit;
    }
    public void setIsBasicUnit(String isBasicUnit) {
        this.isBasicUnit = isBasicUnit;
    }
    public String getIsStopCar() {
        return isStopCar;
    }
    public void setIsStopCar(String isStopCar) {
        this.isStopCar = isStopCar;
    }
    
	
	
  
}
