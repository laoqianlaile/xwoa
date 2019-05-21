package com.centit.app.po;

/**
 * 
 * 首页排版
 * 
 * @author Ghost
 * @create 2016年6月22日
 * @version
 */
public class OptLayoutMethod {
    private Long id;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 内容
     */
    private String content;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void copyNotNullProperty(OptLayoutMethod other) {
        if (other.getId() != null)
            this.id = other.getId();
        if (other.getMethodName() != null)
            this.methodName = other.getMethodName();
        if (other.getContent() != null)
            this.content = other.getContent();
    }
    
   public void copy(OptLayoutMethod other){
        this.id = other.getId();
        this.methodName = other.getMethodName();
        this.content = other.getContent();
    }
    
    public void clearProperties(){
        this.id=null;
        this.methodName=null;
        this.content=null;
    }
}
