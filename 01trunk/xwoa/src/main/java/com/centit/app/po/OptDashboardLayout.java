package com.centit.app.po;

import java.util.Date;

/**
 * 
 * 首页排版
 * 
 * @author Ghost
 * @create 2016年6月22日
 * @version
 */
public class OptDashboardLayout {
    private Long id;
    /**
     * 布局排版名称
     */
    private String layoutName;
    /**
     * BUILTIN-系统内置的  ALLOCATED-被分配的 PERSONAL-个人
     */
    private String layoutType;
    /**
     * 用户范围 
     * layoutType为BUILTIN，值为ALL
     * layoutType为PERSONAL，值为用户编码
     * layoutType为ALLOCATED，值为行政职务编码
     */
    private String userScope;
    /**
     * 布局方式id
     */
    private Long layoutMethodId;
    /**
     * 内容
     */
    private String content;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLayoutName() {
        return layoutName;
    }
    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }
    public String getLayoutType() {
        return layoutType;
    }
    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }
    public String getUserScope() {
        return userScope;
    }
    public void setUserScope(String userScope) {
        this.userScope = userScope;
    }
    public Long getLayoutMethodId() {
        return layoutMethodId;
    }
    public void setLayoutMethodId(Long layoutMethodId) {
        this.layoutMethodId = layoutMethodId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCreateBy() {
        return createBy;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void copyNotNullProperty(OptDashboardLayout other) {
        if (other.getId() != null)
            this.id = other.getId();
        if (other.getLayoutName() != null)
            this.layoutName = other.getLayoutName();
        if (other.getLayoutType() != null)
            this.layoutType = other.getLayoutType();
        if (other.getUserScope() != null)
            this.userScope = other.getUserScope();
        if (other.getContent() != null)
            this.content = other.getContent();
        if (other.getLayoutMethodId() != null)
            this.layoutMethodId = other.getLayoutMethodId();
        if(other.getRemark() != null)
            this.remark = other.getRemark();
        if(other.getCreateBy() != null)
            this.createBy = other.getCreateBy();
        if(other.getCreateTime() != null)
            this.createTime = other.getCreateTime();
    }
    
   public void copy(OptDashboardLayout other){
        this.id = other.getId();
        this.layoutName = other.getLayoutName();
        this.layoutType = other.getLayoutType();
        this.userScope = other.getUserScope();
        this.content = other.getContent();
        this.layoutMethodId = other.getLayoutMethodId();
        this.remark = other.getRemark();
        this.createBy = other.getCreateBy();
        this.createTime = other.getCreateTime();
    }
    
    public void clearProperties(){
        this.id=null;
        this.layoutName=null;
        this.layoutType=null;
        this.userScope=null;
        this.content=null;
        this.layoutMethodId=null;
        this.remark = null;
        this.createBy = null;
        this.createTime = null;
    }
}
