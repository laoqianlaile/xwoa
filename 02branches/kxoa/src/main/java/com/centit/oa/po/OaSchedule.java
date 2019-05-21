package com.centit.oa.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaSchedule implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String schno;// 日程编号

    private Date planBegTime;// 预计开始时间
    private Date planEndTime;// 预计结束时间
    private Date begTime;// 实际开始时间
    private Date endTime;// 实际结束时间
    private String title;// 主题
    private String address;// 地点
    private String remark;// 事项说明
    private String sehType;// 日程类型
    private String itemtype;// 事项类型
    private String meetid;// 会议室ID
    private String city;// 参会人员
    private String creater;// 创建者
    private Date createdate;// 创建时间
    private String secretDegress;// 密级
    private String noticesign;// 提醒标志《未用》
    private Date lastnoticetime;// 最后提醒时间
    private Date taskdeadline;// 任务有效期
    private String importantTag;// 重要程度
    private String taskTag;// 任务标记

    private String leaders;// 参会领导

    private String isDo;// 与会安排标识
    private String resRemark;// 与会安排说明
    private String isEmail;// 是否邮件通知
    private String noticeSign;// 是否发送提醒

    private String dateTag;// 页面日期差
    private Set<OaScheduleResponse> oaScheduleResponses = null;

    private String  djId;//系统自动创建-关联业务

    // Constructors
    /** default constructor */
    public OaSchedule() {
    }

    /** minimal constructor */
    public OaSchedule(String schno) {

        this.schno = schno;

    }

    public OaSchedule(String schno, Date planBegTime, String title,
            String sehType, String itemtype, String creater, String importantTag) {
        super();
        this.schno = schno;
        this.planBegTime = planBegTime;
        this.title = title;
        this.sehType = sehType;
        this.itemtype = itemtype;
        this.creater = creater;
        this.importantTag = importantTag;
    }

    /** full constructor */
    public OaSchedule(String schno, Date planBegTime, Date planEndTime,
            Date begTime, Date endTime, String title, String address,
            String remark, String sehType, String itemtype, String meetid,
            String city, String creater, Date createdate, String secretDegress,
            String noticesign, Date lastnoticetime, Date taskdeadline,
            String importantTag, String taskTag, String leaders, String isDo,
            String resRemark, String isEmail, String noticeSign,String djId) {

        this.schno = schno;

        this.planBegTime = planBegTime;
        this.planEndTime = planEndTime;
        this.begTime = begTime;
        this.endTime = endTime;
        this.title = title;
        this.address = address;
        this.remark = remark;
        this.sehType = sehType;
        this.itemtype = itemtype;
        this.meetid = meetid;
        this.city = city;
        this.creater = creater;
        this.createdate = createdate;
        this.secretDegress = secretDegress;
        this.noticesign = noticesign;
        this.lastnoticetime = lastnoticetime;
        this.taskdeadline = taskdeadline;
        this.importantTag = importantTag;
        this.taskTag = taskTag;

        this.leaders = leaders;

        this.isDo = isDo;
        this.resRemark = resRemark;
        this.isEmail = isEmail;
        this.noticeSign = noticeSign;
        this.djId=djId;

    }
    public Set<OaScheduleResponse> getOaScheduleResponses() {
        if (this.oaScheduleResponses == null)
            this.oaScheduleResponses = new HashSet<OaScheduleResponse>();
        return this.oaScheduleResponses;
    }

    public void setOaScheduleResponses(
            Set<OaScheduleResponse> oaScheduleResponses) {
        this.oaScheduleResponses = oaScheduleResponses;
    }
    public String getIsDo() {
        return isDo;
    }

    public void setIsDo(String isDo) {
        this.isDo = isDo;
    }

    public String getResRemark() {
        return resRemark;
    }

    public void setResRemark(String resRemark) {
        this.resRemark = resRemark;
    }

    public String getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail;
    }

    public String getNoticeSign() {
        return noticeSign;
    }

    public void setNoticeSign(String noticeSign) {
        this.noticeSign = noticeSign;
    }

    public String getLeaders() {
        return leaders;
    }

    public void setLeaders(String leaders) {
        this.leaders = leaders;
    }

    public String getSchno() {
        return this.schno;
    }

    public void setSchno(String schno) {
        this.schno = schno;
    }

    // Property accessors

    public Date getPlanBegTime() {
        return this.planBegTime;
    }

    public void setPlanBegTime(Date planBegTime) {
        this.planBegTime = planBegTime;
    }

    public Date getPlanEndTime() {
        return this.planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSehType() {
        return this.sehType;
    }

    public void setSehType(String sehType) {
        this.sehType = sehType;
    }

    public String getItemtype() {
        return this.itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getMeetid() {
        return this.meetid;
    }

    public void setMeetid(String meetid) {
        this.meetid = meetid;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreater() {
        return this.creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getSecretDegress() {
        return this.secretDegress;
    }

    public void setSecretDegress(String secretDegress) {
        this.secretDegress = secretDegress;
    }

    public String getNoticesign() {
        return this.noticesign;
    }

    public void setNoticesign(String noticesign) {
        this.noticesign = noticesign;
    }

    public Date getLastnoticetime() {
        return this.lastnoticetime;
    }

    public void setLastnoticetime(Date lastnoticetime) {
        this.lastnoticetime = lastnoticetime;
    }

    public Date getTaskdeadline() {
        return this.taskdeadline;
    }

    public void setTaskdeadline(Date taskdeadline) {
        this.taskdeadline = taskdeadline;
    }

    public void copy(OaSchedule other) {

        this.setSchno(other.getSchno());

        this.planBegTime = other.getPlanBegTime();
        this.planEndTime = other.getPlanEndTime();
        this.begTime = other.getBegTime();
        this.endTime = other.getEndTime();
        this.title = other.getTitle();
        this.address = other.getAddress();
        this.remark = other.getRemark();
        this.sehType = other.getSehType();
        this.itemtype = other.getItemtype();
        this.meetid = other.getMeetid();
        this.city = other.getCity();
        this.creater = other.getCreater();
        this.createdate = other.getCreatedate();
        this.secretDegress = other.getSecretDegress();
        this.noticesign = other.getNoticesign();
        this.lastnoticetime = other.getLastnoticetime();
        this.taskdeadline = other.getTaskdeadline();
        this.importantTag = other.getImportantTag();
        this.taskTag = other.getTaskTag();

        this.leaders = other.getLeaders();

        this.isDo = other.getIsDo();
        this.resRemark = other.getResRemark();
        this.isEmail = other.getIsEmail();
        this.noticeSign = other.getNoticeSign();
        this.djId=other.getDjId();

    }

    public void copyNotNullProperty(OaSchedule other) {

        if (other.getSchno() != null)
            this.setSchno(other.getSchno());

        if (other.getPlanBegTime() != null)
            this.planBegTime = other.getPlanBegTime();
        if (other.getPlanEndTime() != null)
            this.planEndTime = other.getPlanEndTime();
        if (other.getBegTime() != null)
            this.begTime = other.getBegTime();
        if (other.getEndTime() != null)
            this.endTime = other.getEndTime();
        if (other.getTitle() != null)
            this.title = other.getTitle();
        if (other.getAddress() != null)
            this.address = other.getAddress();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getSehType() != null)
            this.sehType = other.getSehType();
        if (other.getItemtype() != null)
            this.itemtype = other.getItemtype();
        if (other.getMeetid() != null)
            this.meetid = other.getMeetid();
        if (other.getCity() != null)
            this.city = other.getCity();
        if (other.getCreater() != null)
            this.creater = other.getCreater();
        if (other.getCreatedate() != null)
            this.createdate = other.getCreatedate();
        if (other.getSecretDegress() != null)
            this.secretDegress = other.getSecretDegress();
        if (other.getNoticesign() != null)
            this.noticesign = other.getNoticesign();
        if (other.getLastnoticetime() != null)
            this.lastnoticetime = other.getLastnoticetime();
        if (other.getTaskdeadline() != null)
            this.taskdeadline = other.getTaskdeadline();
        if (other.getImportantTag() != null)
            this.importantTag = other.getImportantTag();
        if (other.getTaskTag() != null)
            this.taskTag = other.getTaskTag();

        if (other.getLeaders() != null)
            this.leaders = other.getLeaders();
        if (other.getIsDo() != null)
            this.isDo = other.isDo;
        if (other.getResRemark() != null)
            this.resRemark = other.resRemark;
        if (other.getIsEmail() != null)
            this.isEmail = other.isEmail;
        if (other.getNoticeSign() != null)
            this.noticeSign = other.noticeSign;
        
        if(other.getDjId()!=null)
            this.djId=other.getDjId();

    }

    public void clearProperties() {

        this.planBegTime = null;
        this.planEndTime = null;
        this.begTime = null;
        this.endTime = null;
        this.title = null;
        this.address = null;
        this.remark = null;
        this.sehType = null;
        this.itemtype = null;
        this.meetid = null;
        this.city = null;
        this.creater = null;
        this.createdate = null;
        this.secretDegress = null;
        this.noticesign = null;
        this.lastnoticetime = null;
        this.taskdeadline = null;
        this.importantTag = null;
        this.taskTag = null;

        this.leaders = null;
        this.isDo = null;
        this.resRemark = null;
        this.isEmail = null;
        this.noticeSign = null;
        this.djId=null;

    }

    public String getImportantTag() {
        return importantTag;
    }

    public void setImportantTag(String importantTag) {
        this.importantTag = importantTag;
    }

    public String getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
    }

    public String getDateTag() {
        return dateTag;
    }

    public void setDateTag(String dateTag) {
        this.dateTag = dateTag;
    }

    public String getDjId() {
        return djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

   

}
