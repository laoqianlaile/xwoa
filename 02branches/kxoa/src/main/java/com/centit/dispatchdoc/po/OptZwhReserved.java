package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OptZwhReserved implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long reservedId;

    private String djId;
    private String fwh;
    private String idea;
    private String wjlx;
    private Long lsh;
    private String lshYear;
    private String createUser;
    private Date createDate;
    private String reservedReason;
    private Date useDate;
    private String isValid;
    

    // Constructors
    /** default constructor */
    public OptZwhReserved() {
    }

    /** minimal constructor */
    public OptZwhReserved(Long reservedId) {

        this.reservedId = reservedId;

    }

    /** full constructor */
    public OptZwhReserved(Long reservedId, String djId, String fwh,
            String idea, String wjlx, Long lsh, String lshyear,
            String createUser, Date createDate, String reservedReason,
            Date useDate, String isValid) {

        this.reservedId = reservedId;

        this.djId = djId;
        this.fwh = fwh;
        this.idea = idea;
        this.wjlx = wjlx;
        this.lsh = lsh;
        this.lshYear = lshyear;
        this.createUser = createUser;
        this.createDate = createDate;
        this.reservedReason = reservedReason;
        this.useDate = useDate;
        this.isValid = isValid;
    }

    public Long getReservedId() {
        return this.reservedId;
    }

    public void setReservedId(Long reservedId) {
        this.reservedId = reservedId;
    }

    // Property accessors

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getFwh() {
        return this.fwh;
    }

    public void setFwh(String fwh) {
        this.fwh = fwh;
    }

    public String getIdea() {
        return this.idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getWjlx() {
        return this.wjlx;
    }

    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
    }

    public Long getLsh() {
        return lsh;
    }

    public void setLsh(Long lsh) {
        this.lsh = lsh;
    }

    public String getLshYear() {
        return this.lshYear;
    }

    public void setLshYear(String lshYear) {
        this.lshYear = lshYear;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getReservedReason() {
        return this.reservedReason;
    }

    public void setReservedReason(String reservedReason) {
        this.reservedReason = reservedReason;
    }

    public Date getUseDate() {
        return this.useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public String getIsValid() {
        return this.isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public void copy(OptZwhReserved other) {

        this.setReservedId(other.getReservedId());

        this.djId = other.getDjId();
        this.fwh = other.getFwh();
        this.idea = other.getIdea();
        this.wjlx = other.getWjlx();
        this.lsh = other.getLsh();
        this.lshYear = other.getLshYear();
        this.createUser = other.getCreateUser();
        this.createDate = other.getCreateDate();
        this.reservedReason = other.getReservedReason();
        this.useDate = other.getUseDate();
        this.isValid = other.getIsValid();

    }

    public void copyNotNullProperty(OptZwhReserved other) {

        if (other.getReservedId() != null)
            this.setReservedId(other.getReservedId());

        if (other.getDjId() != null)
            this.djId = other.getDjId();
        if (other.getFwh() != null)
            this.fwh = other.getFwh();
        if (other.getIdea() != null)
            this.idea = other.getIdea();
        if (other.getWjlx() != null)
            this.wjlx = other.getWjlx();
        if (other.getLsh() != null)
            this.lsh = other.getLsh();
        if (other.getLshYear() != null)
            this.lshYear = other.getLshYear();
        if (other.getCreateUser() != null)
            this.createUser = other.getCreateUser();
        if (other.getCreateDate() != null)
            this.createDate = other.getCreateDate();
        if (other.getReservedReason() != null)
            this.reservedReason = other.getReservedReason();
        if (other.getUseDate() != null)
            this.useDate = other.getUseDate();
        if (other.getIsValid() != null)
            this.isValid = other.getIsValid();

    }

    public void clearProperties() {

        this.djId = null;
        this.fwh = null;
        this.idea = null;
        this.wjlx = null;
        this.lsh = null;
        this.lshYear = null;
        this.createUser = null;
        this.createDate = null;
        this.reservedReason = null;
        this.useDate = null;
        this.isValid = null;

    }
}
