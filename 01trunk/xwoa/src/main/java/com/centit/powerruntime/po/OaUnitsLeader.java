package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaUnitsLeader implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String leadercode;

    private String isuse;
    private String unitcodes;
    private Date createtime;
    private String createuser;
    private Date lastmodifytime;
    private String updateuser;
    private String remark;
    private String unitNames;

    // Constructors
    /** default constructor */
    public OaUnitsLeader() {
    }

    /** minimal constructor */
    public OaUnitsLeader(String leadercode) {

        this.leadercode = leadercode;

    }

    public OaUnitsLeader(String leadercode, String isuse, String unitcodes,
            Date createtime, String createuser, Date lastmodifytime,
            String updateuser, String remark, String unitNames) {
        super();
        this.leadercode = leadercode;
        this.isuse = isuse;
        this.unitcodes = unitcodes;
        this.createtime = createtime;
        this.createuser = createuser;
        this.lastmodifytime = lastmodifytime;
        this.updateuser = updateuser;
        this.remark = remark;
        this.unitNames = unitNames;
    }

    /** full constructor */
    public OaUnitsLeader(String leadercode, String isuse, String unitcodes,
            Date createtime, String createuser, Date lastmodifytime,
            String updateuser, String remark) {

        this.leadercode = leadercode;

        this.isuse = isuse;
        this.unitcodes = unitcodes;
        this.createtime = createtime;
        this.createuser = createuser;
        this.lastmodifytime = lastmodifytime;
        this.updateuser = updateuser;
        this.remark = remark;
    }

    public String getLeadercode() {
        return this.leadercode;
    }

    public String getUnitNames() {
        return unitNames;
    }

    public void setUnitNames(String unitNames) {
        this.unitNames = unitNames;
    }

    public void setLeadercode(String leadercode) {
        this.leadercode = leadercode;
    }

    // Property accessors

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

    public Date getLastmodifytime() {
        return this.lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getUpdateuser() {
        return this.updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void copy(OaUnitsLeader other) {

        this.setLeadercode(other.getLeadercode());

        this.isuse = other.getIsuse();
        this.unitcodes = other.getUnitcodes();
        this.createtime = other.getCreatetime();
        this.createuser = other.getCreateuser();
        this.lastmodifytime = other.getLastmodifytime();
        this.updateuser = other.getUpdateuser();
        this.remark = other.getRemark();
        this.unitNames = other.getUnitNames();
    }

    public void copyNotNullProperty(OaUnitsLeader other) {

        if (other.getLeadercode() != null)
            this.setLeadercode(other.getLeadercode());

        if (other.getIsuse() != null)
            this.isuse = other.getIsuse();
        if (other.getUnitcodes() != null)
            this.unitcodes = other.getUnitcodes();
        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getCreateuser() != null)
            this.createuser = other.getCreateuser();
        if (other.getLastmodifytime() != null)
            this.lastmodifytime = other.getLastmodifytime();
        if (other.getUpdateuser() != null)
            this.updateuser = other.getUpdateuser();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getUnitNames() != null)
            this.unitNames = other.getUnitNames();
    }

    public void clearProperties() {

        this.isuse = null;
        this.unitcodes = null;
        this.createtime = null;
        this.createuser = null;
        this.lastmodifytime = null;
        this.updateuser = null;
        this.remark = null;
        this.unitNames = null;
    }
}
