package com.centit.mip.po;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaMeetinfoMIP implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Expose(serialize = false)
    private String userid; // "用户唯一ID",
    @Expose(serialize = false)
    private String currentdatetime; // "分页检索时间",
    @Expose(serialize = false)
    private String pagesize; // "分页大小",
    @Expose(serialize = false)
    private String keyword; // "检索关键字"
    /**
     * 列表类型 枚举 0 自己所有的 1全部（自己所有+别人审核通过的）
     */
    @Expose(serialize = false)
    private String type;

    @Expose
    private String id; // "会议室id",
    @Expose
    private String name; // "会议室名称"
    @Expose
    private String status; // "会议室当前使用情况",
    @Expose
    private String lasttime; // "会议室最近可预订时间",
    @Expose
    private String duration; // "会议室最近可预订时长",
    @Expose
    private String boardroomid; // "会议室id",
    @Expose
    private String starttime; // "检索开始时间",
    @Expose
    private String endtime; // "检索结束时间"
    @Expose
    private String mettingLeaders; // "参会领导"
    @Expose
    private String meetingPersionsNum; // "参会人数"
    @Expose
    private String capacity; // "可容纳人数",
    @Expose
    @Since(1.0)
    private String meetingid; // "会议实例id",
    @Expose
    @Since(1.0)
    private String applicantid; // "申请人id",（来决定是否具备取消预订功能）
    @Expose
    @Since(1.0)
    private String applicant; // "申请人姓名",
    @Expose
    @Since(1.0)
    private String title; // "会议主题",
    @Expose
    @Since(1.0)
    private String isremind; // "是否提醒:0不提醒 1 提醒"
    @Expose
    @Since(1.0)
    private String isBasicUnit; // "是否有基层单位参加",
    @Expose
    @Since(1.0)
    private String isStopCar; // "是否需要停车",
    @Expose
    @Since(2.0)
    private List<InnerMeetinfo> meetinglist;
    
    @Expose
    private String state; // 会议申请状态

    public static class InnerMeetinfo {
        @Expose
        @Since(2.0)
        private String meetingid; // "会议实例id",
        @Expose
        @Since(2.0)
        private String applicantid; // "申请人id",（来决定是否具备取消预订功能）
        @Expose
        @Since(2.0)
        private String applicant; // "申请人姓名",
        @Expose
        @Since(2.0)
        private String title; // "会议主题",
        @Expose
        @Since(2.0)
        private String starttime; // "会议开始时间",
        @Expose
        @Since(2.0)
        private String endtime; // "会议结束时间
        @Expose
        @Since(2.0)
        private String mettingLeaders; // "参会领导"
        @Expose
        @Since(2.0)
        private String meetingPersionsNum; // "参会人数"
        @Expose
        @Since(2.0)
        private String isBasicUnit; // "是否有基层单位参加",
        @Expose
        @Since(2.0)
        private String isStopCar; // "是否需要停车",
        @Expose
        @Since(2.0)
        private String state; // 会议申请状态

        public InnerMeetinfo() {

        }

        public InnerMeetinfo(String meetingid, String applicantid,
                String applicant, String title, String starttime,
                String endtime, String isBasicUnit, String isStopCar,
                String state,String mettingLeaders,String meetingPersionsNum) {
            super();
            this.meetingid = meetingid;
            this.applicantid = applicantid;
            this.applicant = applicant;
            this.title = title;
            this.starttime = starttime;
            this.endtime = endtime;
            this.mettingLeaders = mettingLeaders;
            this.meetingPersionsNum = meetingPersionsNum;
            this.isBasicUnit = isBasicUnit;
            this.isStopCar = isStopCar;
            this.state = state;
        }

        public InnerMeetinfo(String meetingid, String applicantid,
                String applicant, String title, String starttime,
                String endtime, String state,String mettingLeaders,String meetingPersionsNum) {
            super();
            this.meetingid = meetingid;
            this.applicantid = applicantid;
            this.applicant = applicant;
            this.title = title;
            this.starttime = starttime;
            this.endtime = endtime;
            this.mettingLeaders = mettingLeaders;
            this.meetingPersionsNum = meetingPersionsNum;
            this.state = state;
        }

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
        
        public String getMettingLeaders() {
            return mettingLeaders;
        }

        public void setMettingLeaders(String mettingLeaders) {
            this.mettingLeaders = mettingLeaders;
        }

        public String getMeetingPersionsNum() {
            return meetingPersionsNum;
        }

        public void setMeetingPersionsNum(String meetingPersionsNum) {
            this.meetingPersionsNum = meetingPersionsNum;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBoardroomid() {
        return boardroomid;
    }

    public void setBoardroomid(String boardroomid) {
        this.boardroomid = boardroomid;
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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

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

    public String getIsremind() {
        return isremind;
    }

    public void setIsremind(String isremind) {
        this.isremind = isremind;
    }

    public List<InnerMeetinfo> getMeetinglist() {
        return meetinglist;
    }

    public void setMeetinglist(List<InnerMeetinfo> meetinglist) {
        this.meetinglist = meetinglist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMettingLeaders() {
        return mettingLeaders;
    }

    public void setMettingLeaders(String mettingLeaders) {
        this.mettingLeaders = mettingLeaders;
    }

    public String getMeetingPersionsNum() {
        return meetingPersionsNum;
    }

    public void setMeetingPersionsNum(String meetingPersionsNum) {
        this.meetingPersionsNum = meetingPersionsNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    

}
