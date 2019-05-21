package com.centit.dispatchdoc.po;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class DocRelative implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private DocRelativeId cid;

    private String relativeType;
    private String relativeDesc;

    // Constructors
    /** default constructor */
    public DocRelative() {
    }

    /** minimal constructor */
    public DocRelative(DocRelativeId id

    ) {
        this.cid = id;

    }

    /** full constructor */
    public DocRelative(DocRelativeId id

    , String relativeType, String relativeDesc) {
        this.cid = id;

        this.relativeType = relativeType;
        this.relativeDesc = relativeDesc;
    }

    public DocRelativeId getCid() {
        return this.cid;
    }

    public void setCid(DocRelativeId id) {
        this.cid = id;
    }

    public String getIncomeNo() {
        if (this.cid == null)
            this.cid = new DocRelativeId();
        return this.cid.getIncomeNo();
    }

    public void setIncomeNo(String incomeNo) {
        if (this.cid == null)
            this.cid = new DocRelativeId();
        this.cid.setIncomeNo(incomeNo);
    }

    public String getDispatchNo() {
        if (this.cid == null)
            this.cid = new DocRelativeId();
        return this.cid.getDispatchNo();
    }

    public void setDispatchNo(String dispatchNo) {
        if (this.cid == null)
            this.cid = new DocRelativeId();
        this.cid.setDispatchNo(dispatchNo);
    }

    // Property accessors

    public String getRelativeType() {
        return this.relativeType;
    }

    public void setRelativeType(String relativeType) {
        this.relativeType = relativeType;
    }

    public String getRelativeDesc() {
        return this.relativeDesc;
    }

    public void setRelativeDesc(String relativeDesc) {
        this.relativeDesc = relativeDesc;
    }

    public void copy(DocRelative other) {

        this.setIncomeNo(other.getIncomeNo());
        this.setDispatchNo(other.getDispatchNo());

        this.relativeType = other.getRelativeType();
        this.relativeDesc = other.getRelativeDesc();

    }

    public void copyNotNullProperty(DocRelative other) {

        if (other.getIncomeNo() != null)
            this.setIncomeNo(other.getIncomeNo());
        if (other.getDispatchNo() != null)
            this.setDispatchNo(other.getDispatchNo());

        if (other.getRelativeType() != null)
            this.relativeType = other.getRelativeType();
        if (other.getRelativeDesc() != null)
            this.relativeDesc = other.getRelativeDesc();

    }

    public void clearProperties() {

        this.relativeType = null;
        this.relativeDesc = null;

    }
}
