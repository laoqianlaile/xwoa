package com.centit.dispatchdoc.po;

import java.io.File;
import java.util.Date;

/**
 * 
 * PDF信息pojo
 * 
 * @author lay
 * @create 2016-2-24
 * @version
 */
public class OptPdfInfo implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    private Long id;
    /**
     * 业务id
     */
    private String djId;
    /**
     * 业务类型 1-发文 2-收文
     */
    private String bizType;
    /**
     * 节点实例id
     */
    private Long nodeInstId;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件名称 可以和文件路径里的名称不同
     */
    private String fileName;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 密钥
     */
    private String secretKey;
    
    private String tempDirPath;
    /**
     * 表单内容页数
     */
    private int formDocPageCount;
   
    public OptPdfInfo(){
        
    }
    public OptPdfInfo(String djId,Long nodeInstId, String filePath,String fileName,String tempDirPath){
        this.djId = djId;
        this.filePath = filePath;
        this.fileName = fileName;
        this.createTime = new Date();
        this.tempDirPath = tempDirPath;
        this.nodeInstId = nodeInstId;
    }
  
    public String getTempDirPath() {
        return tempDirPath;
    }
    public void setTempDirPath(String tempDirPath) {
        this.tempDirPath = tempDirPath;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDjId() {
        return djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Long getNodeInstId() {
        return nodeInstId;
    }

    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public int getFormDocPageCount() {
        return formDocPageCount;
    }
    public void setFormDocPageCount(int formDocPageCount) {
        this.formDocPageCount = formDocPageCount;
    }
   
    
}
