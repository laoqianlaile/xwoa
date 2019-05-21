package com.centit.mip.po;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;


/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaScheduleMIP {
    
    private static final long serialVersionUID = 1L;
    
    @Expose  @Since(1.0)
    private String scheduleid;// 日程编号
    @Expose  @Since(1.0)
    private String scheduletitle;// 主题
    @Expose  @Since(1.0)
    private String schedulebegindate;// 预计开始时间
    @Expose  @Since(1.0)
    private String scheduleenddate;// 预计开始时间
    @Expose  @Since(1.0)
    private String createdate;// 创建时间
    @Expose  @Since(1.0)
    private String isimportant;// 重要程度
    @Expose  
    @Since(2.0) 
    private String scheduleurl;
    @Expose 
    @Since(1.0) 
    private String scheduleremark;
    @Expose 
    @Since(1.0) 
    private String address;// 地点
  //领导日程字段
    @Expose 
    @Since(1.1) 
    private String publisher;//发布人姓名
    @Expose 
    @Since(1.1) 
    private String leaders;// 参会领导
   
    @Expose (serialize = false) 
    private String userid;
    
    /*@Expose (serialize = false) 
    private String cycletime;
    @Expose (serialize = false) 
    private String cycletimetype;*/
    
    @Expose (serialize = false) 
    private String starttime;
    @Expose (serialize = false) 
    private String endtime;
    
    @Expose (serialize = false) 
    private String currentdatetime;
    @Expose (serialize = false) 
    private String pagesize;
    @Expose (serialize = false) 
    private String keyword;
    @Expose (serialize = false) 
    private String title;
    
    
    
    
    public String getScheduleid() {
        return scheduleid;
    }
    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }
    public String getScheduletitle() {
        return scheduletitle;
    }
    public void setScheduletitle(String scheduletitle) {
        this.scheduletitle = scheduletitle;
    }
   
   
    public String getSchedulebegindate() {
        return schedulebegindate;
    }
    public void setSchedulebegindate(String schedulebegindate) {
        this.schedulebegindate = schedulebegindate;
    }
    public String getScheduleenddate() {
        return scheduleenddate;
    }
    public void setScheduleenddate(String scheduleenddate) {
        this.scheduleenddate = scheduleenddate;
    }
    public String getIsimportant() {
        return isimportant;
    }
    public void setIsimportant(String isimportant) {
        this.isimportant = isimportant;
    }
    public String getScheduleurl() {
        return scheduleurl;
    }
    public void setScheduleurl(String scheduleurl) {
        this.scheduleurl = scheduleurl;
    }
    public String getScheduleremark() {
        return scheduleremark;
    }
    public void setScheduleremark(String scheduleremark) {
        this.scheduleremark = scheduleremark;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
  /*  public String getCycletime() {
        return cycletime;
    }
    public void setCycletime(String cycletime) {
        this.cycletime = cycletime;
    }
    public String getCycletimetype() {
        return cycletimetype;
    }
    public void setCycletimetype(String cycletimetype) {
        this.cycletimetype = cycletimetype;
    }*/
    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getCurrentdatetime() {
        return currentdatetime;
    }
    public void setCurrentdatetime(String currentdatetime) {
        this.currentdatetime = currentdatetime;
    }
    public String getPagesize() {
        return pagesize;
    }
    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getCreatedate() {
        return createdate;
    }
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getLeaders() {
        return leaders;
    }
    public void setLeaders(String leaders) {
        this.leaders = leaders;
    }
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    



}
