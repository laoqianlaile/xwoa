package com.centit.oa.po;

import java.util.Date;

public class OaLeaveOverTime implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String loroNo;
    private String usercode;
    private String reasonType;
    private Date beginTime;//临时属性，请假开始时间
    private Date endTime;//临时属性，请假结束时间
    private String reasonDesc;
    private String createId;//一组对象是同一个人一次申请的假期时，具有同一个createId（方便查看和修改）
    private Date duringDate;
    private Date createDate;
    private String proposer;
    private String duringDates;//临时属性，一类的请假总时间
    private long num;//临时属性，计算已休次数
    private String holidayNum;//请假天数
    public String getHolidayNum() {
        return holidayNum;
    }

    public void setHolidayNum(String holidayNum) {
        this.holidayNum = holidayNum;
    }
    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getDuringDates() {
        return duringDates;
    }

    public void setDuringDates(String duringDates) {
        this.duringDates = duringDates;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDuringDate() {
        return duringDate;
    }

    public void setDuringDate(Date duringDate) {
        this.duringDate = duringDate;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    // Constructors
    /** default constructor */
    public OaLeaveOverTime() {
    }

    /** minimal constructor */
    public OaLeaveOverTime(String loroNo, String reasonType) {
        this.loroNo = loroNo;
        this.reasonType = reasonType;
    }

    /** full constructor */
    public OaLeaveOverTime(String loroNo, String usercode, String reasonType,
            String reasonDesc, Date duringDate, Date createDate, String proposer,String createId) {

        this.loroNo = loroNo;
        this.usercode = usercode;
        this.reasonType = reasonType;
        this.reasonDesc = reasonDesc;
        this.duringDate = duringDate;
        this.createDate = createDate;
        this.proposer = proposer;
        this.createId=createId;
    }

    public String getLoroNo() {
        return this.loroNo;
    }

    public void setLoroNo(String loroNo) {
        this.loroNo = loroNo;
    }

    // Property accessors

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getReasonType() {
        return this.reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public String getReasonDesc() {
        return this.reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public OaLeaveOverTime copy(OaLeaveOverTime other) {

        this.setLoroNo(other.getLoroNo());
        this.usercode = other.getUsercode();
        this.reasonType = other.getReasonType();
        this.reasonDesc = other.getReasonDesc();
        this.duringDate = other.getDuringDate();
        this.createDate = other.getCreateDate();
        this.proposer = other.getProposer();
        this.createId=other.getCreateId();
        return this;
    }

    public OaLeaveOverTime copyNotNullProperty(OaLeaveOverTime other) {
        if (other.getLoroNo() != null)
            this.setLoroNo(other.getLoroNo());
        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getReasonType() != null)
            this.reasonType = other.getReasonType();
        if (other.getReasonDesc() != null)
            this.reasonDesc = other.getReasonDesc();
        if (other.getDuringDate() != null)
            this.duringDate = other.getDuringDate();
        if (other.getCreateDate() != null)
            this.createDate = other.getCreateDate();
        if (other.getProposer() != null)
            this.proposer = other.getProposer();
        if(other.getCreateId()!=null){
            this.createId=other.getCreateId();
        }
        return this;
    }

    public OaLeaveOverTime clearProperties() {
        this.loroNo = null;
        this.usercode = null;
        this.reasonType = null;
        this.reasonDesc = null;
        this.duringDate = null;
        this.createDate = null;
        this.proposer = null;
        this.createId=null;
        return this;
    }
}
