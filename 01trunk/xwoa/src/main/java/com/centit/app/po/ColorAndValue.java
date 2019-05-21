package com.centit.app.po;

import java.util.Date;

public class ColorAndValue {
 
    private String color;
    private Double value;
    private Date beginTime;
    private Date endTime;
    private String using; 
    private String user;
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public Date getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getUsing() {
        return using;
    }
    public void setUsing(String using) {
        this.using = using;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    } 
}
