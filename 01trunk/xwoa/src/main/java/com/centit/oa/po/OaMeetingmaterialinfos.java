package com.centit.oa.po;

import java.util.Date;

import com.centit.powerruntime.po.OptStuffInfo;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaMeetingmaterialinfos implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private OaMeetingmaterialinfosId cid;

    private Date createtime;
    private String isback;
    private Date backtime;
    private String initalStuffId;
    private String remark;
    
    private OptStuffInfo optStuffInfoBeg;
    private OptStuffInfo optStuffInfoEnd;

    // Constructors
    /** default constructor */
    public OaMeetingmaterialinfos() {
    }

    /** minimal constructor */
    public OaMeetingmaterialinfos(OaMeetingmaterialinfosId id

    ) {
        this.cid = id;

    }

    /** full constructor */

    public OaMeetingmaterialinfos(OaMeetingmaterialinfosId id

    , Date createtime, String isback, Date backtime) {
        this.cid = id;

        this.createtime = createtime;
        this.isback = isback;
        this.backtime = backtime;
    }

    public OaMeetingmaterialinfosId getCid() {
        return this.cid;
    }

    public void setCid(OaMeetingmaterialinfosId id) {
        this.cid = id;
    }

    public String getDjId() {
        if (this.cid == null)
            this.cid = new OaMeetingmaterialinfosId();
        return this.cid.getDjId();
    }

    public void setDjId(String djId) {
        if (this.cid == null)
            this.cid = new OaMeetingmaterialinfosId();
        this.cid.setDjId(djId);
    }

    public String getMeetingAttendee() {
        if (this.cid == null)
            this.cid = new OaMeetingmaterialinfosId();
        return this.cid.getMeetingAttendee();
    }

    public void setMeetingAttendee(String meetingAttendee) {
        if (this.cid == null)
            this.cid = new OaMeetingmaterialinfosId();
        this.cid.setMeetingAttendee(meetingAttendee);
    }

    // Property accessors

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIsback() {
        return this.isback;
    }

    public void setIsback(String isback) {
        this.isback = isback;
    }

    public Date getBacktime() {
        return this.backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }
    
    public String getInitalStuffId() {
        return initalStuffId;
    }

    public void setInitalStuffId(String initalStuffId) {
        this.initalStuffId = initalStuffId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void copy(OaMeetingmaterialinfos other) {

        this.setDjId(other.getDjId());
        this.setMeetingAttendee(other.getMeetingAttendee());

        this.createtime = other.getCreatetime();
        this.isback = other.getIsback();
        this.backtime = other.getBacktime();
        this.initalStuffId = other.getInitalStuffId();
        this.remark = other.getRemark();

    }

    public void copyNotNullProperty(OaMeetingmaterialinfos other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());
        if (other.getMeetingAttendee() != null)
            this.setMeetingAttendee(other.getMeetingAttendee());

        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getIsback() != null)
            this.isback = other.getIsback();
        if (other.getBacktime() != null)
            this.backtime = other.getBacktime();
        if (other.getInitalStuffId() != null)
            this.initalStuffId = other.getInitalStuffId();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
    }

    public void clearProperties() {
        this.createtime = null;
        this.isback = null;
        this.backtime = null;
        this.initalStuffId = null;
        this.remark = null;
    }

    public OptStuffInfo getOptStuffInfoBeg() {
        return optStuffInfoBeg;
    }

    public void setOptStuffInfoBeg(OptStuffInfo optStuffInfoBeg) {
        this.optStuffInfoBeg = optStuffInfoBeg;
    }

    public OptStuffInfo getOptStuffInfoEnd() {
        return optStuffInfoEnd;
    }

    public void setOptStuffInfoEnd(OptStuffInfo optStuffInfoEnd) {
        this.optStuffInfoEnd = optStuffInfoEnd;
    }
    
}
