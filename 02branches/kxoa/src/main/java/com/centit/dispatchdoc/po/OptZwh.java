package com.centit.dispatchdoc.po;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OptZwh implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djId;

    private String fwh;
    private String lsh;
    private String lshYear;
    private String wjlx;
    private long flowInstID;
    private String orgCode;
    private String ideacode;
    
    

    // Constructors
    /** default constructor */
    public OptZwh() {
    }

    /** minimal constructor */
    public OptZwh(String djId) {

        this.djId = djId;

    }

    /** full constructor */
    public OptZwh(String djId, String fwh, String lsh, String lshYear,
            String wjlx, String ideacode) {

        this.djId = djId;
        this.ideacode = ideacode;
        this.fwh = fwh;
        this.lsh = lsh;
        this.lshYear = lshYear;
        this.wjlx = wjlx;
    }

    public String getDjId() {
        return this.djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    // Property accessors

    public String getFwh() {
        return this.fwh;
    }

    public void setFwh(String fwh) {
        this.fwh = fwh;
    }

    public String getLsh() {
        return this.lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getLshYear() {
        return this.lshYear;
    }

    public void setLshYear(String lshYear) {
        this.lshYear = lshYear;
    }

    public String getWjlx() {
        return this.wjlx;
    }

    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
    }

    public void copy(OptZwh other) {

        this.setDjId(other.getDjId());

        this.fwh = other.getFwh();
        this.lsh = other.getLsh();
        this.lshYear = other.getLshYear();
        this.wjlx = other.getWjlx();
        this.ideacode = other.getIdeacode();

    }

    public void copyNotNullProperty(OptZwh other) {

        if (other.getDjId() != null)
            this.setDjId(other.getDjId());

        if (other.getFwh() != null)
            this.fwh = other.getFwh();
        if (other.getLsh() != null)
            this.lsh = other.getLsh();
        if (other.getLshYear() != null)
            this.lshYear = other.getLshYear();
        if (other.getWjlx() != null)
            this.wjlx = other.getWjlx();
        if (other.getIdeacode() != null)
            this.ideacode = other.getIdeacode();

    }

    public void clearProperties() {

        this.fwh = null;
        this.lsh = null;
        this.lshYear = null;
        this.wjlx = null;
        this.ideacode = null;
    }

    public long getFlowInstID() {
        return flowInstID;
    }

    public void setFlowInstID(long flowInstID) {
        this.flowInstID = flowInstID;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIdeacode() {
        return ideacode;
    }

    public void setIdeacode(String ideacode) {
        this.ideacode = ideacode;
    }

}
