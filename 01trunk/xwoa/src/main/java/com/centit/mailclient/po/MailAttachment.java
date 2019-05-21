package com.centit.mailclient.po;

import java.io.File;

import com.centit.sys.util.SysParametersUtils;

/**
 * 
 * 邮件附件
 * 
 * @author lay
 * @create 2016年5月11日
 * @version 1.0
 */
public class MailAttachment implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 附件id
     */
    private Long id;
    /**
     * 邮件id
     */
    private Long mailInfoId;
    /**
     * 附件名称
     */
    private String fileName;
    
    /**
     * 附件大小
     */
    private Long fileSize;
    
    /**
     * 附件存放路径
     */
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMailInfoId() {
        return mailInfoId;
    }

    public void setMailInfoId(Long mailInfoId) {
        this.mailInfoId = mailInfoId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtensionName(){
        if(fileName!=null && fileName.indexOf(".")!=-1){
            return fileName.substring(fileName.indexOf("."));
        }
       return null;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }
    
    public String getFmtFileSize(){
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
 
        if (fileSize >= gb) {
            return String.format("%.1fGB", (float) fileSize / gb);
        } else if (fileSize >= mb) {
            float f = (float) fileSize / mb;
            return String.format(f > 100 ? "%.0fMB" : "%.1fMB", f);
        } else if (fileSize >= kb) {
            float f = (float) fileSize / kb;
            return String.format(f > 100 ? "%.0fKB" : "%.1fKB", f);
        } else
            return String.format("%dB", fileSize);
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getPath() {
        return path;
    }

    public String getAbsolutePath(){
        return SysParametersUtils.getMailAffixHome() + File.separator + getPath();
    }
    public void setPath(String path) {
        this.path = path;
    }
    
    
    
}
