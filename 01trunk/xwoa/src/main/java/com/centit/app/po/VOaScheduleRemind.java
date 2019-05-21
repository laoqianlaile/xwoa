package com.centit.app.po;

import java.util.Date;

public class VOaScheduleRemind implements java.io.Serializable{
	private static final long serialVersionUID =  1L;
	
	private String no;
	private String title;
	private Date begTime;		
	private String usercode;	
	private String itemType;

	private String reType;

    public VOaScheduleRemind() {
        super();
        // TODO Auto-generated constructor stub
    }

    public VOaScheduleRemind(String no, String title, Date begTime,
            String usercode, String itemType, String reType) {
        super();
        this.no = no;
        this.title = title;
        this.begTime = begTime;
        this.usercode = usercode;
        this.itemType = itemType;
        this.reType = reType;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBegTime() {
        return begTime;
    }

    public void setBegTime(Date begTime) {
        this.begTime = begTime;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getReType() {
        return reType;
    }

    public void setReType(String reType) {
        this.reType = reType;
    }

	
	
}
	