package com.centit.dispatchdoc.po;


/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class DocSend implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long sendId;

    private String djId;
    private String sendType;
    private String unitType;
    private String unitcode;
    private String orgCode;

    // Constructors
    /** default constructor */
    public DocSend() {
    }

    /** minimal constructor */
    public DocSend(Long sendId) {

        this.sendId = sendId;

    }

    /** full constructor */
    public DocSend(Long sendId, String djId, String sendType, String unitType,
            String unitcode, String orgCode) {

        this.sendId = sendId;

        this.djId = djId;
        this.sendType = sendType;
        this.unitType = unitType;
        this.unitcode = unitcode;
        this.orgCode = orgCode;
    }

    public Long getSendId() {
        return this.sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    // Property accessors

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getSendType() {
        return this.sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getUnitType() {
        return this.unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitcode() {
        return this.unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public void copy(DocSend other) {

        this.setSendId(other.getSendId());

        this.djId = other.getDjId();
        this.sendType = other.getSendType();
        this.unitType = other.getUnitType();
        this.unitcode = other.getUnitcode();
        this.orgCode = other.getOrgCode();

    }

    public void copyNotNullProperty(DocSend other) {

        if (other.getSendId() != null)
            this.setSendId(other.getSendId());

        if (other.getDjId() != null)
            this.djId = other.getDjId();
        if (other.getSendType() != null)
            this.sendType = other.getSendType();
        if (other.getUnitType() != null)
            this.unitType = other.getUnitType();
        if (other.getUnitcode() != null)
            this.unitcode = other.getUnitcode();
        if (other.getOrgCode() != null)
            this.orgCode = other.getOrgCode();

    }

    public void clearProperties() {

        this.djId = null;
        this.sendType = null;
        this.unitType = null;
        this.unitcode = null;
        this.orgCode = null;

    }
}
