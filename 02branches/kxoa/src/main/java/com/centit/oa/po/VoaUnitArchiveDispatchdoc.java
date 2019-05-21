package com.centit.oa.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class VoaUnitArchiveDispatchdoc implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String no;

    private String usercode;
    private String transaffairname;
    private String dispatchDocNo;
    private String optUnitName;
    private String mainNotifyUnit;
    private String otherUnits;
    private Date createTime;
    private Date createDate;
    private Date lastmodifytime;
    private String unitcode;
    private String updateuser;
    private String id;
    private String draftUserName;// 经办人
    private String dispatchUser;// 签发人
    private String bizstate;//业务状态

    // Constructors
    /** default constructor */
    public VoaUnitArchiveDispatchdoc() {
    }

    /** minimal constructor */
    public VoaUnitArchiveDispatchdoc(String no) {

        this.no = no;

    }

    /** full constructor */

    public VoaUnitArchiveDispatchdoc(String no, String usercode,
            String transaffairname, String dispatchDocNo, String optUnitName,
            String mainNotifyUnit, String otherUnits, Date createTime,
            Date createDate, Date lastmodifytime, String unitcode,
            String updateuser, String id) {

        this.no = no;

        this.usercode = usercode;
        this.transaffairname = transaffairname;
        this.dispatchDocNo = dispatchDocNo;
        this.optUnitName = optUnitName;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createTime = createTime;
        this.createDate = createDate;
        this.lastmodifytime = lastmodifytime;
        this.unitcode = unitcode;
        this.updateuser = updateuser;
        this.id = id;
    }

    public VoaUnitArchiveDispatchdoc(String no, String usercode,
            String transaffairname, String dispatchDocNo, String optUnitName,
            String mainNotifyUnit, String otherUnits, Date createTime,
            Date createDate, Date lastmodifytime, String unitcode,
            String updateuser, String id, String draftUserName,
            String dispatchUser, String bizstate) {
        super();
        this.no = no;
        this.usercode = usercode;
        this.transaffairname = transaffairname;
        this.dispatchDocNo = dispatchDocNo;
        this.optUnitName = optUnitName;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createTime = createTime;
        this.createDate = createDate;
        this.lastmodifytime = lastmodifytime;
        this.unitcode = unitcode;
        this.updateuser = updateuser;
        this.id = id;
        this.draftUserName = draftUserName;
        this.dispatchUser = dispatchUser;
        this.bizstate = bizstate;
    }

    public VoaUnitArchiveDispatchdoc(String no, String usercode,
            String transaffairname, String dispatchDocNo, String optUnitName,
            String mainNotifyUnit, String otherUnits, Date createTime,
            Date createDate, Date lastmodifytime, String unitcode,
            String updateuser, String id, String draftUserName,
            String dispatchUser) {
        super();
        this.no = no;
        this.usercode = usercode;
        this.transaffairname = transaffairname;
        this.dispatchDocNo = dispatchDocNo;
        this.optUnitName = optUnitName;
        this.mainNotifyUnit = mainNotifyUnit;
        this.otherUnits = otherUnits;
        this.createTime = createTime;
        this.createDate = createDate;
        this.lastmodifytime = lastmodifytime;
        this.unitcode = unitcode;
        this.updateuser = updateuser;
        this.id = id;
        this.draftUserName = draftUserName;
        this.dispatchUser = dispatchUser;
    }

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    // Property accessors

    public String getBizstate() {
        return bizstate;
    }

    public void setBizstate(String bizstate) {
        this.bizstate = bizstate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getTransaffairname() {
        return this.transaffairname;
    }

    public void setTransaffairname(String transaffairname) {
        this.transaffairname = transaffairname;
    }

    public String getDispatchDocNo() {
        return this.dispatchDocNo;
    }

    public void setDispatchDocNo(String dispatchDocNo) {
        this.dispatchDocNo = dispatchDocNo;
    }

    public String getOptUnitName() {
        return this.optUnitName;
    }

    public void setOptUnitName(String optUnitName) {
        this.optUnitName = optUnitName;
    }

    public String getMainNotifyUnit() {
        return this.mainNotifyUnit;
    }

    public void setMainNotifyUnit(String mainNotifyUnit) {
        this.mainNotifyUnit = mainNotifyUnit;
    }

    public String getOtherUnits() {
        return this.otherUnits;
    }

    public void setOtherUnits(String otherUnits) {
        this.otherUnits = otherUnits;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastmodifytime() {
        return this.lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getUnitcode() {
        return this.unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getUpdateuser() {
        return this.updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public void copy(VoaUnitArchiveDispatchdoc other) {

        this.setNo(other.getNo());

        this.usercode = other.getUsercode();
        this.transaffairname = other.getTransaffairname();
        this.dispatchDocNo = other.getDispatchDocNo();
        this.optUnitName = other.getOptUnitName();
        this.mainNotifyUnit = other.getMainNotifyUnit();
        this.otherUnits = other.getOtherUnits();
        this.createTime = other.getCreateTime();
        this.createDate = other.getCreateDate();
        this.lastmodifytime = other.getLastmodifytime();
        this.unitcode = other.getUnitcode();
        this.updateuser = other.getUpdateuser();
        this.id = other.getId();
        this.draftUserName = other.getDraftUserName();
        this.dispatchUser = other.getDispatchUser();
        this.bizstate=other.getBizstate();
    }

    public String getDraftUserName() {
        return draftUserName;
    }

    public void setDraftUserName(String draftUserName) {
        this.draftUserName = draftUserName;
    }

    public String getDispatchUser() {
        return dispatchUser;
    }

    public void setDispatchUser(String dispatchUser) {
        this.dispatchUser = dispatchUser;
    }

    public void copyNotNullProperty(VoaUnitArchiveDispatchdoc other) {

        if (other.getNo() != null)
            this.setNo(other.getNo());

        if (other.getUsercode() != null)
            this.usercode = other.getUsercode();
        if (other.getTransaffairname() != null)
            this.transaffairname = other.getTransaffairname();
        if (other.getDispatchDocNo() != null)
            this.dispatchDocNo = other.getDispatchDocNo();
        if (other.getOptUnitName() != null)
            this.optUnitName = other.getOptUnitName();
        if (other.getMainNotifyUnit() != null)
            this.mainNotifyUnit = other.getMainNotifyUnit();
        if (other.getOtherUnits() != null)
            this.otherUnits = other.getOtherUnits();
        if (other.getCreateTime() != null)
            this.createTime = other.getCreateTime();
        if (other.getCreateDate() != null)
            this.createDate = other.getCreateDate();
        if (other.getLastmodifytime() != null)
            this.lastmodifytime = other.getLastmodifytime();
        if (other.getUnitcode() != null)
            this.unitcode = other.getUnitcode();
        if (other.getUpdateuser() != null)
            this.updateuser = other.getUpdateuser();
        if (other.getId() != null)
            this.id = other.getId();
        if (other.getDraftUserName() != null)
            this.draftUserName = other.getDraftUserName();
        if (other.getDispatchUser() != null)
            this.dispatchUser = other.getDispatchUser();
        if (other.getBizstate() != null)
        this.bizstate=other.getBizstate();
    }

    public void clearProperties() {

        this.usercode = null;
        this.transaffairname = null;
        this.dispatchDocNo = null;
        this.optUnitName = null;
        this.mainNotifyUnit = null;
        this.otherUnits = null;
        this.createTime = null;
        this.createDate = null;
        this.lastmodifytime = null;
        this.unitcode = null;
        this.updateuser = null;
        this.id = null;
        this.draftUserName = null;
        this.dispatchUser = null;
        this.bizstate=null;
    }
}
