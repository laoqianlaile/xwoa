package com.centit.oa.po;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.centit.core.dao.CodeBook;
import com.centit.webservice.util.CommonOptCodeUtil;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaArchive implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String no;
    private String responsibledep;
    private String titanic;
    private String title;
    private Date bookdate;
    private Long pages;
    private Long copies;
    private String duration;
    private Long filingannual;
    private Date filingdate;
    private String locationpath;
    private String remark;
    private String doctype;
    private Date createtime;
    private String createuser;
    private Date lastmodifytime;
    private String updateuser;
    private String unitcode;
    private String docno;

    private String allcaseno;// 全宗号
    private String parallelTitle;// 并列题名
    private String deputyTitle;// 副题名
    private String classification;// 密级
    private String keywords;// 关键字
    private String archiveType;// 文档类型
    
    
    private String gwNature;//公文性质 上级部门01 本部门02
    
    private String belongUnitcode;//所属机构
    
    

    // Constructors
    /** default constructor */
    public OaArchive() {
    }

    /** minimal constructor */
    public OaArchive(String id) {

        this.id = id;

    }

    public OaArchive(String id, String no, String responsibledep, String titanic,
            String title, Date bookdate, Long pages, Long copies,
            String duration, Long filingannual, Date filingdate,
            String locationpath, String remark, String doctype,
            Date createtime, String createuser, Date lastmodifytime,
            String updateuser, String unitcode, String docno, String allcaseno,
            String parallelTitle, String deputyTitle, String classification,
            String keywords, String archiveType,String gwNature,String belongUnitcode) {
        super();
        this.id = id;
        this.no = no;
        this.responsibledep = responsibledep;
        this.titanic = titanic;
        this.title = title;
        this.bookdate = bookdate;
        this.pages = pages;
        this.copies = copies;
        this.duration = duration;
        this.filingannual = filingannual;
        this.filingdate = filingdate;
        this.locationpath = locationpath;
        this.remark = remark;
        this.doctype = doctype;
        this.createtime = createtime;
        this.createuser = createuser;
        this.lastmodifytime = lastmodifytime;
        this.updateuser = updateuser;
        this.unitcode = unitcode;
        this.docno = docno;
        this.allcaseno = allcaseno;
        this.parallelTitle = parallelTitle;
        this.deputyTitle = deputyTitle;
        this.classification = classification;
        this.keywords = keywords;
        this.archiveType = archiveType;
        this.gwNature = gwNature;
        this.belongUnitcode = belongUnitcode;
    }

    public OaArchive(String id, String no, String responsibledep, String titanic,
            String title, Date bookdate, Long pages, Long copies,
            String duration, Long filingannual, Date filingdate,
            String locationpath, String remark, String doctype,
            Date createtime, String createuser, Date lastmodifytime,
            String updateuser, String unitcode, String docno, String allcaseno,
            String parallelTitle, String deputyTitle, String classification,
            String keywords) {
        super();
        this.id = id;
        this.no = no;
        this.responsibledep = responsibledep;
        this.titanic = titanic;
        this.title = title;
        this.bookdate = bookdate;
        this.pages = pages;
        this.copies = copies;
        this.duration = duration;
        this.filingannual = filingannual;
        this.filingdate = filingdate;
        this.locationpath = locationpath;
        this.remark = remark;
        this.doctype = doctype;
        this.createtime = createtime;
        this.createuser = createuser;
        this.lastmodifytime = lastmodifytime;
        this.updateuser = updateuser;
        this.unitcode = unitcode;
        this.docno = docno;
        this.allcaseno = allcaseno;
        this.parallelTitle = parallelTitle;
        this.deputyTitle = deputyTitle;
        this.classification = classification;
        this.keywords = keywords;
    }

    /** full constructor */
    public OaArchive(String id, String no, String responsibledep, String titanic,
            String title, Date bookdate, Long pages, Long copies,
            String duration, Long filingannual, Date filingdate,
            String locationpath, String remark, String doctype,
            Date createtime, String createuser, Date lastmodifytime,
            String updateuser, String docno) {

        this.id = id;

        this.no = no;
        this.responsibledep = responsibledep;
        this.titanic = titanic;
        this.title = title;
        this.bookdate = bookdate;
        this.pages = pages;
        this.copies = copies;
        this.duration = duration;
        this.filingannual = filingannual;
        this.filingdate = filingdate;
        this.locationpath = locationpath;
        this.remark = remark;
        this.doctype = doctype;
        this.createtime = createtime;
        this.createuser = createuser;
        this.lastmodifytime = lastmodifytime;
        this.updateuser = updateuser;
        this.docno = docno;
    }

    public String getAllcaseno() {
        return allcaseno;
    }

    public void setAllcaseno(String allcaseno) {
        this.allcaseno = allcaseno;
    }

    public String getParallelTitle() {
        return parallelTitle;
    }

    public void setParallelTitle(String parallelTitle) {
        this.parallelTitle = parallelTitle;
    }

    public String getDeputyTitle() {
        return deputyTitle;
    }

    public void setDeputyTitle(String deputyTitle) {
        this.deputyTitle = deputyTitle;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Property accessors

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getResponsibledep() {
        return this.responsibledep;
    }

    public void setResponsibledep(String responsibledep) {
        this.responsibledep = responsibledep;
    }

   

    public String getTitanic() {
        return titanic;
    }

    public void setTitanic(String titanic) {
        this.titanic = titanic;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBookdate() {
        return this.bookdate;
    }

    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    public Long getPages() {
        return this.pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getCopies() {
        return this.copies;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public void setCopies(Long copies) {
        this.copies = copies;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getFilingannual() {
        return this.filingannual;
    }

    public void setFilingannual(Long filingannual) {
        this.filingannual = filingannual;
    }

    public Date getFilingdate() {
        return this.filingdate;
    }

    public void setFilingdate(Date filingdate) {
        this.filingdate = filingdate;
    }

    public String getLocationpath() {
        return this.locationpath;
    }

    public void setLocationpath(String locationpath) {
        this.locationpath = locationpath;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDoctype() {
        return this.doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
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

    public void copy(OaArchive other) {

        this.setId(other.getId());

        this.no = other.getNo();
        this.responsibledep = other.getResponsibledep();
        this.titanic = other.getTitanic();
        this.title = other.getTitle();
        this.bookdate = other.getBookdate();
        this.pages = other.getPages();
        this.copies = other.getCopies();
        this.duration = other.getDuration();
        this.filingannual = other.getFilingannual();
        this.filingdate = other.getFilingdate();
        this.locationpath = other.getLocationpath();
        this.remark = other.getRemark();
        this.doctype = other.getDoctype();
        this.createtime = other.getCreatetime();
        this.createuser = other.getCreateuser();
        this.lastmodifytime = other.getLastmodifytime();
        this.updateuser = other.getUpdateuser();
        this.unitcode = other.getUnitcode();
        this.docno = other.getDocno();
        this.allcaseno = other.getAllcaseno();
        this.parallelTitle = other.getParallelTitle();
        this.deputyTitle = other.getDeputyTitle();
        this.classification = other.getClassification();
        this.keywords = other.getKeywords();
        this.archiveType = other.getArchiveType();
        this.gwNature = other.getGwNature();
        this.belongUnitcode = other.getBelongUnitcode();

    }

    public OaArchive(String id, String no, String responsibledep, String titanic,
            String title, Date bookdate, Long pages, Long copies,
            String duration, Long filingannual, Date filingdate,
            String locationpath, String remark, String doctype,
            Date createtime, String createuser, Date lastmodifytime,
            String updateuser, String unitcode, String docno,String belongUnitcode) {
        super();
        this.id = id;
        this.no = no;
        this.responsibledep = responsibledep;
        this.titanic = titanic;
        this.title = title;
        this.bookdate = bookdate;
        this.pages = pages;
        this.copies = copies;
        this.duration = duration;
        this.filingannual = filingannual;
        this.filingdate = filingdate;
        this.locationpath = locationpath;
        this.remark = remark;
        this.doctype = doctype;
        this.createtime = createtime;
        this.createuser = createuser;
        this.lastmodifytime = lastmodifytime;
        this.updateuser = updateuser;
        this.unitcode = unitcode;
        this.docno = docno;
        this.belongUnitcode = belongUnitcode;
    }

    public void copyNotNullProperty(OaArchive other) {

        if (other.getId() != null)
            this.setId(other.getId());

        if (other.getNo() != null)
            this.no = other.getNo();
        if (other.getResponsibledep() != null)
            this.responsibledep = other.getResponsibledep();
        if (other.getTitanic() != null)
            this.titanic = other.getTitanic();
        if (other.getTitle() != null)
            this.title = other.getTitle();
        if (other.getBookdate() != null)
            this.bookdate = other.getBookdate();
        if (other.getPages() != null)
            this.pages = other.getPages();
        if (other.getCopies() != null)
            this.copies = other.getCopies();
        if (other.getDuration() != null)
            this.duration = other.getDuration();
        if (other.getFilingannual() != null)
            this.filingannual = other.getFilingannual();
        if (other.getFilingdate() != null)
            this.filingdate = other.getFilingdate();
        if (other.getLocationpath() != null)
            this.locationpath = other.getLocationpath();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getDoctype() != null)
            this.doctype = other.getDoctype();
        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getCreateuser() != null)
            this.createuser = other.getCreateuser();
        if (other.getLastmodifytime() != null)
            this.lastmodifytime = other.getLastmodifytime();
        if (other.getUpdateuser() != null)
            this.updateuser = other.getUpdateuser();
        if (other.getUnitcode() != null)
            this.unitcode = other.getUnitcode();
        if (other.getDocno() != null)
            this.docno = other.getDocno();

        if (other.getAllcaseno() != null)
            this.allcaseno = other.getAllcaseno();
        if (other.getParallelTitle() != null)
            this.parallelTitle = other.getParallelTitle();
        if (other.getDeputyTitle() != null)
            this.deputyTitle = other.getDeputyTitle();
        if (other.getClassification() != null)
            this.classification = other.getClassification();
        if (other.getKeywords() != null)
            this.keywords = other.getKeywords();
        if (other.getArchiveType() != null)
            this.archiveType = other.getArchiveType();
        if( other.getGwNature()!=null)
            this.gwNature=other.getGwNature();
        
        if( other.getBelongUnitcode() != null)
            this.belongUnitcode = other.getBelongUnitcode();
    }

    public void clearProperties() {

        this.no = null;
        this.responsibledep = null;
        this.titanic = null;
        this.title = null;
        this.bookdate = null;
        this.pages = null;
        this.copies = null;
        this.duration = null;
        this.filingannual = null;
        this.filingdate = null;
        this.locationpath = null;
        this.remark = null;
        this.doctype = null;
        this.createtime = null;
        this.createuser = null;
        this.lastmodifytime = null;
        this.updateuser = null;
        this.unitcode = null;
        this.docno = null;
        this.allcaseno = null;
        this.parallelTitle = null;
        this.deputyTitle = null;
        this.classification = null;
        this.keywords = null;
        this.archiveType = null;
        this.gwNature=null;
        this.belongUnitcode = null;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public String getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getGwNature() {
        if(StringUtils.isBlank(gwNature)){
            gwNature=CommonOptCodeUtil.GW_NATURE_DEP;
        }
        return gwNature;
    }

    public void setGwNature(String gwNature) {
        this.gwNature = gwNature;
    }

    public String getBelongUnitcode() {
        return belongUnitcode;
    }

    public void setBelongUnitcode(String belongUnitcode) {
        this.belongUnitcode = belongUnitcode;
    }
    
    
    
}
