package com.centit.powerruntime.po;

import java.util.Date;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class TemplateFile implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long templateId;//流水号

    /**
     * 模版编号
     */
    private String recordId;//模版编号
    private String fileName;//文件名
    private String fileType;//文件类型
    private Date fileDate;//上传日期
    private Long fileSize;//文件大小
    private String filePath;//文件路径
    /**
     * 模版名称
     */
    private String descript;//模版名称
    private String tempType;
    
    /**
     * 是否启用，0未启用；1启用
     */
    private String isUsed;
    
    /**
     * 模版排序号
     */
    private String orderBy;
    
    /**
     * 文号规则
     */
    private String codeCode;

    // Constructors
    /** default constructor */
    public TemplateFile() {
    }

    /** minimal constructor */
    public TemplateFile(Long templateId) {

        this.templateId = templateId;

    }

    /** full constructor */
    public TemplateFile(Long templateId, String recordId, String fileName,
            String fileType, Date fileDate, Long fileSize, String filePath,
            String descript, String tempType, String isUsed, String orderBy) {

        this.templateId = templateId;

        this.recordId = recordId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDate = fileDate;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.descript = descript;
        this.tempType = tempType;
        this.isUsed = isUsed;
        this.orderBy = orderBy;
    }

    public TemplateFile(Long templateId, String recordId, String fileName,
            String fileType, Date fileDate, Long fileSize, String filePath,
            String descript, String tempType, String isUsed, String orderBy,
            String codeCode) {
        super();
        this.templateId = templateId;
        this.recordId = recordId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDate = fileDate;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.descript = descript;
        this.tempType = tempType;
        this.isUsed = isUsed;
        this.orderBy = orderBy;
        this.codeCode = codeCode;
    }

    public Long getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    // Property accessors

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getFileDate() {
        return this.fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getTempType() {
        return this.tempType;
    }

    public void setTempType(String tempType) {
        this.tempType = tempType;
    }

    public String getIsUsed() {
        return this.isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void copy(TemplateFile other) {

        this.setTemplateId(other.getTemplateId());

        this.recordId = other.getRecordId();
        this.fileName = other.getFileName();
        this.fileType = other.getFileType();
        this.fileDate = other.getFileDate();
        this.fileSize = other.getFileSize();
        this.filePath = other.getFilePath();
        this.descript = other.getDescript();
        this.tempType = other.getTempType();
        this.isUsed = other.getIsUsed();
        this.orderBy = other.getOrderBy();
        this.codeCode = other.getCodeCode();
    }

    public void copyNotNullProperty(TemplateFile other) {

        if (other.getTemplateId() != null)
            this.setTemplateId(other.getTemplateId());

        if (other.getRecordId() != null)
            this.recordId = other.getRecordId();
        if (other.getFileName() != null)
            this.fileName = other.getFileName();
        if (other.getFileType() != null)
            this.fileType = other.getFileType();
        if (other.getFileDate() != null)
            this.fileDate = other.getFileDate();
        if (other.getFileSize() != null)
            this.fileSize = other.getFileSize();
        if (other.getFilePath() != null)
            this.filePath = other.getFilePath();
        if (other.getDescript() != null)
            this.descript = other.getDescript();
        if (other.getTempType() != null)
            this.tempType = other.getTempType();
        if (other.getIsUsed() != null)
            this.isUsed = other.getIsUsed();
        if (other.getOrderBy() != null)
            this.orderBy = other.getOrderBy();
        if (other.getCodeCode()!= null)
            this.codeCode = other.getCodeCode();
    }

    public String getCodeCode() {
        return codeCode;
    }

    public void setCodeCode(String codeCode) {
        this.codeCode = codeCode;
    }

    public void clearProperties() {

        this.recordId = null;
        this.fileName = null;
        this.fileType = null;
        this.fileDate = null;
        this.fileSize = null;
        this.filePath = null;
        this.descript = null;
        this.tempType = null;
        this.isUsed = null;
        this.orderBy = null;
        this.codeCode=null;
    }
}
