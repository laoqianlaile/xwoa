package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptBaseInfo;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaKqvisitorgroup implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private String title;// 标题 新增字段，用于方便展示
    private Date approtime;
    private String approvalremark;
    private String bodycontent;
    private String approval;
    private String jdtype;
    private String travel;
    private String leavetime;
    private String lodgingcase;
    private String mealplace;
    private String mealcase;
    private String kqdepname;
    private String meetplase;
    private String remark;
    private String creater;
    private Date createtime;

    private OptBaseInfo optBaseInfo;

    // Constructors
    /** default constructor */
    public OaKqvisitorgroup() {
    }

    /** minimal constructor */
    public OaKqvisitorgroup(String djId) {

        this.djId = djId;

    }

    /** full constructor */

    public OaKqvisitorgroup(String djId, Date approtime, String approvalremark,
            String bodycontent, String approval, String jdtype, String travel,
            String leavetime, String lodgingcase, String mealplace,
            String mealcase, String kqdepname, String meetplase, String remark,
            String creater, Date createtime) {

        this.djId = djId;

        this.approtime = approtime;
        this.approvalremark = approvalremark;
        this.bodycontent = bodycontent;
        this.approval = approval;
        this.jdtype = jdtype;
        this.travel = travel;
        this.leavetime = leavetime;
        this.lodgingcase = lodgingcase;
        this.mealplace = mealplace;
        this.mealcase = mealcase;
        this.kqdepname = kqdepname;
        this.meetplase = meetplase;
        this.remark = remark;
        this.creater = creater;
        this.createtime = createtime;
    }

    public OaKqvisitorgroup(String djId, String title, Date approtime,
            String approvalremark, String bodycontent, String approval,
            String jdtype, String travel, String leavetime, String lodgingcase,
            String mealplace, String mealcase, String kqdepname,
            String meetplase, String remark, String creater, Date createtime) {
        super();
        this.djId = djId;
        this.title = title;
        this.approtime = approtime;
        this.approvalremark = approvalremark;
        this.bodycontent = bodycontent;
        this.approval = approval;
        this.jdtype = jdtype;
        this.travel = travel;
        this.leavetime = leavetime;
        this.lodgingcase = lodgingcase;
        this.mealplace = mealplace;
        this.mealcase = mealcase;
        this.kqdepname = kqdepname;
        this.meetplase = meetplase;
        this.remark = remark;
        this.creater = creater;
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors

    public Date getApprotime() {
        return this.approtime;
    }

    public void setApprotime(Date approtime) {
        this.approtime = approtime;
    }

    public String getApprovalremark() {
        return this.approvalremark;
    }

    public void setApprovalremark(String approvalremark) {
        this.approvalremark = approvalremark;
    }

    public String getBodycontent() {
        return this.bodycontent;
    }

    public void setBodycontent(String bodycontent) {
        this.bodycontent = bodycontent;
    }

    public String getApproval() {
        return this.approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getJdtype() {
        return this.jdtype;
    }

    public void setJdtype(String jdtype) {
        this.jdtype = jdtype;
    }

    public String getTravel() {
        return this.travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String getLeavetime() {
        return this.leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    public String getLodgingcase() {
        return this.lodgingcase;
    }

    public void setLodgingcase(String lodgingcase) {
        this.lodgingcase = lodgingcase;
    }

    public String getMealplace() {
        return this.mealplace;
    }

    public void setMealplace(String mealplace) {
        this.mealplace = mealplace;
    }

    public String getMealcase() {
        return this.mealcase;
    }

    public void setMealcase(String mealcase) {
        this.mealcase = mealcase;
    }

    public String getKqdepname() {
        return this.kqdepname;
    }

    public void setKqdepname(String kqdepname) {
        this.kqdepname = kqdepname;
    }

    public String getMeetplase() {
        return this.meetplase;
    }

    public void setMeetplase(String meetplase) {
        this.meetplase = meetplase;
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

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void copy(OaKqvisitorgroup other) {

        this.setDjId(other.getDjId());

        this.approtime = other.getApprotime();
        this.approvalremark = other.getApprovalremark();
        this.bodycontent = other.getBodycontent();
        this.approval = other.getApproval();
        this.jdtype = other.getJdtype();
        this.travel = other.getTravel();
        this.leavetime = other.getLeavetime();
        this.lodgingcase = other.getLodgingcase();
        this.mealplace = other.getMealplace();
        this.mealcase = other.getMealcase();
        this.kqdepname = other.getKqdepname();
        this.meetplase = other.getMeetplase();
        this.remark = other.getRemark();
        this.creater = other.getCreater();
        this.createtime = other.getCreatetime();
        this.title = other.getTitle();

    }

    public void copyNotNullProperty(OaKqvisitorgroup other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getApprotime() != null)
            this.approtime = other.getApprotime();
        if (other.getApprovalremark() != null)
            this.approvalremark = other.getApprovalremark();
        if (other.getBodycontent() != null)
            this.bodycontent = other.getBodycontent();
        if (other.getApproval() != null)
            this.approval = other.getApproval();
        if (other.getJdtype() != null)
            this.jdtype = other.getJdtype();
        if (other.getTravel() != null)
            this.travel = other.getTravel();
        if (other.getLeavetime() != null)
            this.leavetime = other.getLeavetime();
        if (other.getLodgingcase() != null)
            this.lodgingcase = other.getLodgingcase();
        if (other.getMealplace() != null)
            this.mealplace = other.getMealplace();
        if (other.getMealcase() != null)
            this.mealcase = other.getMealcase();
        if (other.getKqdepname() != null)
            this.kqdepname = other.getKqdepname();
        if (other.getMeetplase() != null)
            this.meetplase = other.getMeetplase();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getCreater() != null)
            this.creater = other.getCreater();
        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getTitle() != null)
            this.title = other.getTitle();
    }

    public void clearProperties() {

        this.approtime = null;
        this.approvalremark = null;
        this.bodycontent = null;
        this.approval = null;
        this.jdtype = null;
        this.travel = null;
        this.leavetime = null;
        this.lodgingcase = null;
        this.mealplace = null;
        this.mealcase = null;
        this.kqdepname = null;
        this.meetplase = null;
        this.remark = null;
        this.creater = null;
        this.createtime = null;
        this.title = null;
    }

    public OptBaseInfo getOptBaseInfo() {
        return optBaseInfo;
    }

    public void setOptBaseInfo(OptBaseInfo optBaseInfo) {
        this.optBaseInfo = optBaseInfo;
    }

}
