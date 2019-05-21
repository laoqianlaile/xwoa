package com.centit.powerbase.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class Dataindividual implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long individualid;

    private String applicant;
    private String applicantPaperType;
    private String applicantPaperNumber;
    private String applicantPhone;
    private String applicantMobile;
    private String applicantAddress;
    private String applicantZipcode;
    private String applicantEmail;
    private String sex;
    private String workUnit;
    private String isInuse;
    private Date lastModdate;

    // Constructors
    /** default constructor */
    public Dataindividual() {
    }

    /** minimal constructor */
    public Dataindividual(Long individualid, String applicant,
            String applicantPaperType, String applicantPaperNumber,
            String isInuse, Date lastModdate) {

        this.individualid = individualid;

        this.applicant = applicant;
        this.applicantPaperType = applicantPaperType;
        this.applicantPaperNumber = applicantPaperNumber;
        this.isInuse = isInuse;
        this.lastModdate = lastModdate;
    }

    /** full constructor */
    public Dataindividual(Long individualid, String applicant,
            String applicantPaperType, String applicantPaperNumber,
            String applicantPhone, String applicantMobile,
            String applicantAddress, String applicantZipcode,
            String applicantEmail, String sex, String workUnit, String isInuse,
            Date lastModdate) {

        this.individualid = individualid;

        this.applicant = applicant;
        this.applicantPaperType = applicantPaperType;
        this.applicantPaperNumber = applicantPaperNumber;
        this.applicantPhone = applicantPhone;
        this.applicantMobile = applicantMobile;
        this.applicantAddress = applicantAddress;
        this.applicantZipcode = applicantZipcode;
        this.applicantEmail = applicantEmail;
        this.sex = sex;
        this.workUnit = workUnit;
        this.isInuse = isInuse;
        this.lastModdate = lastModdate;
    }

    public Long getIndividualid() {
        return this.individualid;
    }

    public void setIndividualid(Long individualid) {
        this.individualid = individualid;
    }

    // Property accessors

    public String getApplicant() {
        return this.applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicantPaperType() {
        return this.applicantPaperType;
    }

    public void setApplicantPaperType(String applicantPaperType) {
        this.applicantPaperType = applicantPaperType;
    }

    public String getApplicantPaperNumber() {
        return this.applicantPaperNumber;
    }

    public void setApplicantPaperNumber(String applicantPaperNumber) {
        this.applicantPaperNumber = applicantPaperNumber;
    }

    public String getApplicantPhone() {
        return this.applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getApplicantMobile() {
        return this.applicantMobile;
    }

    public void setApplicantMobile(String applicantMobile) {
        this.applicantMobile = applicantMobile;
    }

    public String getApplicantAddress() {
        return this.applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public String getApplicantZipcode() {
        return this.applicantZipcode;
    }

    public void setApplicantZipcode(String applicantZipcode) {
        this.applicantZipcode = applicantZipcode;
    }

    public String getApplicantEmail() {
        return this.applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkUnit() {
        return this.workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getIsInuse() {
        return this.isInuse;
    }

    public void setIsInuse(String isInuse) {
        this.isInuse = isInuse;
    }

    public Date getLastModdate() {
        return this.lastModdate;
    }

    public void setLastModdate(Date lastModdate) {
        this.lastModdate = lastModdate;
    }

    public void copy(Dataindividual other) {

        this.setIndividualid(other.getIndividualid());

        this.applicant = other.getApplicant();
        this.applicantPaperType = other.getApplicantPaperType();
        this.applicantPaperNumber = other.getApplicantPaperNumber();
        this.applicantPhone = other.getApplicantPhone();
        this.applicantMobile = other.getApplicantMobile();
        this.applicantAddress = other.getApplicantAddress();
        this.applicantZipcode = other.getApplicantZipcode();
        this.applicantEmail = other.getApplicantEmail();
        this.sex = other.getSex();
        this.workUnit = other.getWorkUnit();
        this.isInuse = other.getIsInuse();
        this.lastModdate = other.getLastModdate();

    }

    public void copyNotNullProperty(Dataindividual other) {

        if (other.getIndividualid() != null)
            this.setIndividualid(other.getIndividualid());

        if (other.getApplicant() != null)
            this.applicant = other.getApplicant();
        if (other.getApplicantPaperType() != null)
            this.applicantPaperType = other.getApplicantPaperType();
        if (other.getApplicantPaperNumber() != null)
            this.applicantPaperNumber = other.getApplicantPaperNumber();
        if (other.getApplicantPhone() != null)
            this.applicantPhone = other.getApplicantPhone();
        if (other.getApplicantMobile() != null)
            this.applicantMobile = other.getApplicantMobile();
        if (other.getApplicantAddress() != null)
            this.applicantAddress = other.getApplicantAddress();
        if (other.getApplicantZipcode() != null)
            this.applicantZipcode = other.getApplicantZipcode();
        if (other.getApplicantEmail() != null)
            this.applicantEmail = other.getApplicantEmail();
        if (other.getSex() != null)
            this.sex = other.getSex();
        if (other.getWorkUnit() != null)
            this.workUnit = other.getWorkUnit();
        if (other.getIsInuse() != null)
            this.isInuse = other.getIsInuse();
        if (other.getLastModdate() != null)
            this.lastModdate = other.getLastModdate();

    }

    public void clearProperties() {

        this.applicant = null;
        this.applicantPaperType = null;
        this.applicantPaperNumber = null;
        this.applicantPhone = null;
        this.applicantMobile = null;
        this.applicantAddress = null;
        this.applicantZipcode = null;
        this.applicantEmail = null;
        this.sex = null;
        this.workUnit = null;
        this.isInuse = null;
        this.lastModdate = null;

    }
}
