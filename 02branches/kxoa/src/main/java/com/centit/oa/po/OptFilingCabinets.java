package com.centit.oa.po;


public class OptFilingCabinets {
    private Long id;
    
    private String refId;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRefId() {
        return refId;
    }
    public void setRefId(String refId) {
        this.refId = refId;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
    
    public void copy(OptFilingCabinets other){
        this.id = other.getId();
        this.refId = other.getRefId();
        this.fileSize = other.getFileSize();
        this.fileName = other.getFileName();
        this.filePath = other.getFilePath();
    }
}
