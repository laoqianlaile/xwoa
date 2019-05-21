package com.centit.dispatchdoc.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class ReadInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String readNo;
    private String incomeDocTitle;
    private String incomeDeptName;
    private String incomeDocNo;
    private Date incomeDate;
    private Date createDate;
    private String createName;
    private byte[] incomeDocFile;
    private String incomeDocName;
    private String readInfoRole;
    private String docFileType;

    public ReadInfo(String readNo, String incomeDocTitle,
            String incomeDeptName, String incomeDocNo, Date incomeDate,
            Date createDate, String createName, byte[] incomeDocFile,
            String incomeDocName, String readInfoRole, String docFileType) {
        super();
        this.readNo = readNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptName = incomeDeptName;
        this.incomeDocNo = incomeDocNo;
        this.incomeDate = incomeDate;
        this.createDate = createDate;
        this.createName = createName;
        this.incomeDocFile = incomeDocFile;
        this.incomeDocName = incomeDocName;
        this.readInfoRole = readInfoRole;
        this.docFileType = docFileType;
    }

    public String getDocFileType() {
        return docFileType;
    }

    public void setDocFileType(String docFileType) {
        this.docFileType = docFileType;
    }

    public ReadInfo(String readNo, String incomeDocTitle,
            String incomeDeptName, String incomeDocNo, Date incomeDate,
            Date createDate, String createName, byte[] incomeDocFile,
            String incomeDocName, String readInfoRole) {
        super();
        this.readNo = readNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptName = incomeDeptName;
        this.incomeDocNo = incomeDocNo;
        this.incomeDate = incomeDate;
        this.createDate = createDate;
        this.createName = createName;
        this.incomeDocFile = incomeDocFile;
        this.incomeDocName = incomeDocName;
        this.readInfoRole = readInfoRole;
    }

    public String getReadInfoRole() {
        return readInfoRole;
    }

    public void setReadInfoRole(String readInfoRole) {
        this.readInfoRole = readInfoRole;
    }

    // Constructors
    /** default constructor */
    public ReadInfo() {
    }

    /** minimal constructor */
    public ReadInfo(String readNo) {

        this.readNo = readNo;

    }

    /** full constructor */

    public String getReadNo() {
        return this.readNo;
    }

    public ReadInfo(String readNo, String incomeDocTitle,
            String incomeDeptName, String incomeDocNo, Date incomeDate,
            Date createDate, String createName, String incomeDocName) {
        super();
        this.readNo = readNo;
        this.incomeDocTitle = incomeDocTitle;
        this.incomeDeptName = incomeDeptName;
        this.incomeDocNo = incomeDocNo;
        this.incomeDate = incomeDate;
        this.createDate = createDate;
        this.createName = createName;
        this.incomeDocName = incomeDocName;
    }

    public void setReadNo(String readNo) {
        this.readNo = readNo;
    }

    // Property accessors

    public String getIncomeDocTitle() {
        return this.incomeDocTitle;
    }

    public void setIncomeDocTitle(String incomeDocTitle) {
        this.incomeDocTitle = incomeDocTitle;
    }

    public String getIncomeDeptName() {
        return this.incomeDeptName;
    }

    public void setIncomeDeptName(String incomeDeptName) {
        this.incomeDeptName = incomeDeptName;
    }

    public String getIncomeDocNo() {
        return this.incomeDocNo;
    }

    public void setIncomeDocNo(String incomeDocNo) {
        this.incomeDocNo = incomeDocNo;
    }

    public Date getIncomeDate() {
        return this.incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public byte[] getIncomeDocFile() {
        return this.incomeDocFile;
    }

    public void setIncomeDocFile(byte[] incomeDocFile) {
        this.incomeDocFile = incomeDocFile;
    }

    public String getIncomeDocName() {
        return this.incomeDocName;
    }

    public void setIncomeDocName(String incomeDocName) {
        this.incomeDocName = incomeDocName;
    }

    public void copy(ReadInfo other) {

        this.setReadNo(other.getReadNo());

        this.incomeDocTitle = other.getIncomeDocTitle();
        this.incomeDeptName = other.getIncomeDeptName();
        this.incomeDocNo = other.getIncomeDocNo();
        this.incomeDate = other.getIncomeDate();
        this.createDate = other.getCreateDate();
        this.createName = other.getCreateName();
        this.incomeDocFile = other.getIncomeDocFile();
        this.incomeDocName = other.getIncomeDocName();
        this.readInfoRole = other.getReadInfoRole();
        this.docFileType = other.getDocFileType();

    }

    public void copyNotNullProperty(ReadInfo other) {

        if (other.getReadNo() != null)
            this.setReadNo(other.getReadNo());

        if (other.getIncomeDocTitle() != null)
            this.incomeDocTitle = other.getIncomeDocTitle();
        if (other.getIncomeDeptName() != null)
            this.incomeDeptName = other.getIncomeDeptName();
        if (other.getIncomeDocNo() != null)
            this.incomeDocNo = other.getIncomeDocNo();
        if (other.getIncomeDate() != null)
            this.incomeDate = other.getIncomeDate();
        if (other.getCreateDate() != null)
            this.createDate = other.getCreateDate();
        if (other.getCreateName() != null)
            this.createName = other.getCreateName();
        if (other.getIncomeDocFile() != null)
            this.incomeDocFile = other.getIncomeDocFile();
        if (other.getIncomeDocName() != null)
            this.incomeDocName = other.getIncomeDocName();
        if (other.getReadInfoRole() != null)
            this.readInfoRole = other.getReadInfoRole();
        if (other.getDocFileType() != null)
            this.docFileType = other.getDocFileType();
    }

    public void clearProperties() {

        this.incomeDocTitle = null;
        this.incomeDeptName = null;
        this.incomeDocNo = null;
        this.incomeDate = null;
        this.createDate = null;
        this.createName = null;
        this.incomeDocFile = null;
        this.incomeDocName = null;
        this.readInfoRole = null;
        this.docFileType = null;

    }
}
