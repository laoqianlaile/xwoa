package com.centit.sys.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OptFlowNoInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private OptFlowNoInfoId cid;

    private Long curNo;
    private java.util.Date lastCodeDate;
    private String curyear;

    // Constructors
    /** default constructor */
    public OptFlowNoInfo() {
    }

    /** minimal constructor */
    public OptFlowNoInfo(OptFlowNoInfoId id

    , Long curNo) {
        this.cid = id;

        this.curNo = curNo;
    }

    /** full constructor */
    public OptFlowNoInfo(OptFlowNoInfoId id

    , Long curNo, java.util.Date lastCodeDate) {
        this.cid = id;

        this.curNo = curNo;
        this.lastCodeDate = lastCodeDate;
    }

    public OptFlowNoInfo(OptFlowNoInfoId cid, Long curNo, Date lastCodeDate,
            String curyear) {
        super();
        this.cid = cid;
        this.curNo = curNo;
        this.lastCodeDate = lastCodeDate;
        this.curyear = curyear;
    }

    public OptFlowNoInfoId getCid() {
        return this.cid;
    }

    public void setCid(OptFlowNoInfoId id) {
        this.cid = id;
    }

    public String getOwnerCode() {
        if (this.cid == null)
            this.cid = new OptFlowNoInfoId();
        return this.cid.getOwnerCode();
    }

    public void setOwnerCode(String ownerCode) {
        if (this.cid == null)
            this.cid = new OptFlowNoInfoId();
        this.cid.setOwnerCode(ownerCode);
    }

    public Date getCodeDate() {
        if (this.cid == null)
            this.cid = new OptFlowNoInfoId();
        return this.cid.getCodeDate();
    }

    public void setCodeDate(Date codeDate) {
        if (this.cid == null)
            this.cid = new OptFlowNoInfoId();
        this.cid.setCodeDate(codeDate);
    }

    public String getCodeCode() {
        if (this.cid == null)
            this.cid = new OptFlowNoInfoId();
        return this.cid.getCodeCode();
    }

    public void setCodeCode(String codeCode) {
        if (this.cid == null)
            this.cid = new OptFlowNoInfoId();
        this.cid.setCodeCode(codeCode);
    }

    // Property accessors

    public Long getCurNo() {
        return this.curNo;
    }

    public void setCurNo(Long curNo) {
        this.curNo = curNo;
    }

    public java.util.Date getLastCodeDate() {
        return this.lastCodeDate;
    }

    public void setLastCodeDate(java.util.Date lastCodeDate) {
        this.lastCodeDate = lastCodeDate;
    }

    public void copy(OptFlowNoInfo other) {

        this.setOwnerCode(other.getOwnerCode());
        this.setCodeDate(other.getCodeDate());
        this.setCodeCode(other.getCodeCode());

        this.curNo = other.getCurNo();
        this.lastCodeDate = other.getLastCodeDate();
        this.curyear=other.getCuryear();

    }

    public void copyNotNullProperty(OptFlowNoInfo other) {

        if (other.getOwnerCode() != null)
            this.setOwnerCode(other.getOwnerCode());
        if (other.getCodeDate() != null)
            this.setCodeDate(other.getCodeDate());
        if (other.getCodeCode() != null)
            this.setCodeCode(other.getCodeCode());

        if (other.getCurNo() != null)
            this.curNo = other.getCurNo();
        if (other.getLastCodeDate() != null)
            this.lastCodeDate = other.getLastCodeDate();
        if (other.getCuryear() != null)
            this.curyear = other.getCuryear();
    }

    public void clearProperties() {

        this.curNo = null;
        this.lastCodeDate = null;
        this.curyear = null;
    }

    public String getCuryear() {
        return curyear;
    }

    public void setCuryear(String curyear) {
        this.curyear = curyear;
    }
}
