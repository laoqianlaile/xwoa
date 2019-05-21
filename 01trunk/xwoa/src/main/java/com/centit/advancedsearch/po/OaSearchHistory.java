package com.centit.advancedsearch.po;

import java.util.Date;

/**
 * 
 * 搜索历史
 * 
 * @author Ghost
 * @create 2017年2月6日
 * @version
 */
public class OaSearchHistory implements java.io.Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 流水号
     */
    private String no;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 搜索关键字
     */
    private String mainKey;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态 D-已删除 T-活动状态
     */
    private String state = "T";

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getMainKey() {
        return mainKey;
    }

    public void setMainKey(String mainKey) {
        this.mainKey = mainKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
