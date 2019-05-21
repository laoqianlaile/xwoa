package com.centit.oa.po;

/**
 * 
 * 资讯附件
 * 
 * @author Ghost
 * @create 2016年6月16日
 * @version
 */
public class OaInformationAttachment implements java.io.Serializable{
    
    public static final String TYPE_DOCUMENT = "1";
    
    public static final String TYPE_VEDIO = "2";
   /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 相对路径
     */
    private String path;
    /**
     * 资讯类型 1-文档 2-视频
     */
    private String type;
    /**
     * 关联id
     */
    private String refId;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRefId() {
        return refId;
    }
    public void setRefId(String refId) {
        this.refId = refId;
    }
    
    
}
