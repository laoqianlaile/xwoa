package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaUnitsLeaderLog implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String no;

    private String leadercode;
    private String isuse;
    private String unitcodes;
    private Date createtime;
    private String createuser;
    private String remark;
    private String unitNames;

    // Constructors
    /** default constructor */
    public OaUnitsLeaderLog() {
    }

    /** minimal constructor */
    public OaUnitsLeaderLog(String no) {

        this.no = no;

    }

    public OaUnitsLeaderLog(String no, String leadercode, String isuse,
            String unitcodes, Date createtime, String createuser,
            String remark, String unitNames) {
        super();
        this.no = no;
        this.leadercode = leadercode;
        this.isuse = isuse;
        this.unitcodes = unitcodes;
        this.createtime = createtime;
        this.createuser = createuser;
        this.remark = remark;
        this.unitNames = unitNames;
    }

    /** full constructor */
    public OaUnitsLeaderLog(String no, String leadercode, String isuse,
            String unitcodes, Date createtime, String createuser, String remark) {

        this.no = no;

        this.leadercode = leadercode;
        this.isuse = isuse;
        this.unitcodes = unitcodes;
        this.createtime = createtime;
        this.createuser = createuser;
        this.remark = remark;
    }

    public String getUnitNames() {
        return unitNames;
    }

    public void setUnitNames(String unitNames) {
        this.unitNames = unitNames;
    }

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    // Property accessors

    public String getLeadercode() {
        return this.leadercode;
    }

    public void setLeadercode(String leadercode) {
        this.leadercode = leadercode;
    }

    public String getIsuse() {
        return this.isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getUnitcodes() {
        return this.unitcodes;
    }

    public void setUnitcodes(String unitcodes) {
        this.unitcodes = unitcodes;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void copy(OaUnitsLeaderLog other) {

        this.setNo(other.getNo());

        this.leadercode = other.getLeadercode();
        this.isuse = other.getIsuse();
        this.unitcodes = other.getUnitcodes();
        this.createtime = other.getCreatetime();
        this.createuser = other.getCreateuser();
        this.remark = other.getRemark();
        this.unitNames = other.getUnitNames();
    }

    public void copyNotNullProperty(OaUnitsLeaderLog other) {

        if (other.getNo() != null)
            this.setNo(other.getNo());

        if (other.getLeadercode() != null)
            this.leadercode = other.getLeadercode();
        if (other.getIsuse() != null)
            this.isuse = other.getIsuse();
        if (other.getUnitcodes() != null)
            this.unitcodes = other.getUnitcodes();
        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getCreateuser() != null)
            this.createuser = other.getCreateuser();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getUnitNames() != null)
            this.unitNames = other.getUnitNames();
    }

    public void clearProperties() {

        this.leadercode = null;
        this.isuse = null;
        this.unitcodes = null;
        this.createtime = null;
        this.createuser = null;
        this.remark = null;
        this.unitNames = null;
    }
}
