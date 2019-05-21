package com.centit.app.po;

import java.io.Serializable;


/**
 * 
 * 首页模块定义
 * 
 * @author Ghost
 * @create 2016年6月22日
 * @version
 */
public class OptDashboardModule implements  java.io.Serializable  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 模块编码
     */
    private String moduleCode;
    /**
     * 数据展示地址
     */
    private String displayUrl;
    /**
     * “更多”链接地址
     */
    private String linkUrl;
    /**
     * 是否在用 T-是 F-不是
     */
    private String isUsed = "T";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public void copyNotNullProperty(OptDashboardModule other) {
        if (other.getId() != null)
            this.setId(other.getId());
        if (other.getModuleName() != null)
            this.setModuleName(other.getModuleName());
        if (other.getModuleCode() != null)
            this.moduleCode = other.getModuleCode();
        if (other.getDisplayUrl() != null)
            this.displayUrl = other.getDisplayUrl();
        if (other.getLinkUrl() != null)
            this.linkUrl = other.getLinkUrl();
        if (other.getIsUsed() != null)
            this.isUsed = other.getIsUsed();
    }
    
   public void copy(OptDashboardModule other){
        this.setId(other.getId());
        this.setModuleName(other.getModuleName());
        this.moduleCode = other.getModuleCode();
        this.displayUrl = other.getDisplayUrl();
        this.linkUrl = other.getLinkUrl();
        this.isUsed = other.getIsUsed();
    }
    
    
    public void clearProperties(){
        this.id=null;
        this.moduleName=null;
        this.moduleCode=null;
        this.displayUrl=null;
        this.linkUrl=null;
        this.isUsed=null;
    }
}
