package com.centit.efile.model;

import java.io.File;

/**
 * 
 * 电子文件存储对象类
 * 
 * @author lay
 * @create 2016年6月2日
 * @version
 */
public class EfileStore {
    public static final String SRC_PARAM_TYPE_FILE = "file";
    public static final String SRC_PARAM_TYPE_BYTES = "bytes";
    /**
     * 存放磁盘上的文件名称
     */
    private String storeName;
    /**
     * 存放磁盘上的目录,这是绝对路径不是相对的
     */
    private String storeDir;
    /**
     * 待存放的内容
     */
    private File content;
    
    /**
     * 待存放内容字节数组
     */
    private byte[] contentBytes;

    /**
     * 原始名称，也就是存放之前的文件名称
     */
    private String originalName;
    
    /**
     * 待存储对象的传参类型 file-以File的对象 bytes-以字节数组格式上传
     */
    private String srcParamType = SRC_PARAM_TYPE_FILE;
    
    public EfileStore() {

    }

    public EfileStore(File file, String fileName) {
        this.content = file;
        this.originalName = fileName;
    }
    
    public EfileStore(File file,String fileName,String dirPath){
        this(file,fileName);
        this.storeDir = dirPath;
    }
    
    public EfileStore(File file,String fileName,String dirPath,String saveFileName){
        this(file,fileName,dirPath);
        this.storeName = saveFileName;
    }
    
    public EfileStore(byte[] fileBytes, String fileName) {
        this.contentBytes = fileBytes;
        this.originalName = fileName;
        this.srcParamType = SRC_PARAM_TYPE_BYTES;
    }
    
    public EfileStore(byte[] fileBytes,String fileName,String dirPath){
        this(fileBytes,fileName);
        this.storeDir = dirPath;
    }
    
    public EfileStore(byte[] fileBytes,String fileName,String dirPath,String saveFileName){
        this(fileBytes,fileName,dirPath);
        this.storeName = saveFileName;
    }
    
    /**
     * 获取文件的扩展名带.
     * @return
     */
    public String getExtName(boolean includePoint){
       if(originalName != null && !"".equals(originalName)){
           int startIndex = originalName.lastIndexOf(".");
           if(!includePoint){
               startIndex++;
           }
           return originalName.substring(startIndex).toLowerCase();
       }
       return "";
    }
    
    /**
     * 获取存到磁盘上面的绝对路径
     * @return
     */
    public String getAbsolutePath(){
        if(storeDir != null && !"".equals(storeDir) 
                && storeName != null && !"".equals(storeName)){
            return storeDir + File.separator + storeName;
        }
        return "";
    }
    
    /**
     * 获取文件内容大小
     * @return
     */
    public long getContentLength(){
        if(content != null){
            return content.length();
        }
        return 0;
    }
    
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDir() {
        return storeDir;
    }

    public void setStoreDir(String storeDir) {
        this.storeDir = storeDir;
    }

    public File getContent() {
        return content;
    }

    public void setContent(File content) {
        this.content = content;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public byte[] getContentBytes() {
        return contentBytes;
    }

    public void setContentBytes(byte[] contentBytes) {
        this.contentBytes = contentBytes;
    }

    public String getSrcParamType() {
        return srcParamType;
    }
    
    

}
