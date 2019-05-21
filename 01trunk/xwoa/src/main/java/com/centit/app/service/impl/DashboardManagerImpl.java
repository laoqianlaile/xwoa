package com.centit.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.app.dao.DashboardDao;
import com.centit.app.po.Dashboard;
import com.centit.app.service.DashboardManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaSchedule;
import com.centit.oa.po.VOptBaseList;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateUtil;

public class DashboardManagerImpl extends BaseEntityManagerImpl<Dashboard>
implements DashboardManager{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(DashboardManager.class);
    private DashboardDao dashboardDao ;
    public void setDashboardDao(DashboardDao baseDao) {
        this.dashboardDao = baseDao;
        setBaseDao(this.dashboardDao);
       
    }
    @Override
    public List<Dashboard> getDashboardListFormVOADASHBOARD(
            Map<String, Object> filterMap) {
        // TODO Auto-generated method stub
        return dashboardDao.getDashboardListFormVOADASHBOARD(filterMap);
    }
    @Override
    public Map<String, String> getdashboardNum(String usercode, String itemtype) {
        // TODO Auto-generated method stub
        return dashboardDao.getdashboardNum(usercode, itemtype);
    }
    @Override
    public Map<String, String> getMessageNum(String usercode) {
        // TODO Auto-generated method stub
        return dashboardDao.getMessageNum(usercode);
    }
   
    /**
     * 获取未读邮件数
     * @param usercode
     * @return
     */
    public Long getUnReadEmailNum(String usercode){
        return dashboardDao.getUnReadEmailNum(usercode);
    }
    
    /**
    * 获取未读通知数
    * @param usercode
    * @return
    */
   public Long getUnReadBulletinNum(String usercode){
       return dashboardDao.getUnReadBulletinNum(usercode);
   }
   
   /**
    * 获取收文待办数
    * @param usercode
    * @return
    */
   public Long getSWTaskNum(String usercode){
       return dashboardDao.getSWTaskNum(usercode);
   }
   
   /**
    * 获取发文待办数
    * @param usercode
    * @return
    */
   public Long getFWTaskNum(String usercode){
       return dashboardDao.getFWTaskNum(usercode);
   }
   
   /**
    * 获取未读提醒数
    * @param usercode
    * @return
    */
   public Long getUnreadRemind(String usercode){
       return dashboardDao.getUnreadRemind(usercode);
   }
   
   /**
    * 获取未读外部邮件数
    * @param usercode
    * @return
    */
   public Long getUnreadOuterEmailNum(String usercode){
       return dashboardDao.getUnreadOuterEmailNum(usercode);
   }
   
   /**
    * 获取收文待办数
    * @param usercode
    * @return
    */
   public Long getLWTaskNum(String usercode){
       return dashboardDao.getLWTaskNum(usercode);
   }
   
    @Override
    public Long getQBTaskNum(String usercode) {
        return dashboardDao.getQBTaskNum(usercode);
    }
    @Override
    public Long getDCDBTaskNum(String usercode) {
        return dashboardDao.getDCDBTaskNum(usercode);
    }
    @Override
    public JSONObject getScheduleOfLeaders(Integer year,Integer weekNo) {
        if(weekNo == null || weekNo <= 0){
            weekNo =  DateUtil.getWeekNoOfYear();
        }
        if(year == null){
            year = DatetimeOpt.getYear(new Date());
        }
        Date beginTime = DateUtil.getStartDayOfWeekNo(year, weekNo);
        Date endTime = DateUtil.getEndDayOfWeekNo(year, weekNo);
        
        List<String> days = listDays(beginTime, endTime);
        
        JSONObject result = new JSONObject();
        
        result.put("year", year);
        result.put("weekNo", weekNo);
        result.put("maxWeek", DateUtil.getMaxWeekNumOfYear(year));
        result.put("maxWeekLastYear", DateUtil.getMaxWeekNumOfYear(year-1));
        result.put("firstDayOfWeek", DateUtil.date2String(beginTime, "yyyy年MM月dd日"));
        result.put("lastDayOfWeek", DateUtil.date2String(endTime, "yyyy年MM月dd日"));
        result.put("daysEnum", days);
        result.put("weekEnum", new String[]{"周日","周一","周二","周三","周四","周五","周六"});
        try{
          List<OaSchedule> schedules = dashboardDao.getScheduleOfLeaders(beginTime,endTime);
          if(schedules!=null && !schedules.isEmpty()){
              String pointLeader = null;
              //结果集
              JSONArray data = new JSONArray();
              //当前领导数据集
              JSONObject jo = null;
              JSONArray jaEvents = null;
              
              for(OaSchedule schedule : schedules){
                  if(StringUtils.isNotBlank(schedule.getCreater()) && !schedule.getCreater().equals(pointLeader)){
                      jo = new JSONObject();
                      jo.put("leaderCode", schedule.getCreater());
                      jo.put("leaderName", CodeRepositoryUtil.getValue("usercode", schedule.getCreater()));
                      jaEvents = new JSONArray();
                      jo.put("events", jaEvents);
                      pointLeader = schedule.getCreater();
                      data.add(jo);
                  }
                  
                  JSONObject event = new JSONObject();
                  event.put("beginTime", DateUtil.date2String(schedule.getPlanBegTime(), "yyyy-MM-dd HH:mm:ss"));
                  event.put("endTime", DateUtil.date2String(schedule.getPlanEndTime(), "yyyy-MM-dd HH:mm:ss"));
                  event.put("title", schedule.getTitle());//事件主题
                  event.put("type", CodeRepositoryUtil.getValue("oaItemType", schedule.getItemtype()));//事件类型
                  event.put("bgcolorType", schedule.getTaskTag());//用任务类型来给事件填充不同的颜色
                  jaEvents.add(event);
              }
              
              result.put("data", data);
          }
        }catch(Exception e){
            log.error("获取领导日程安排数据时异常:"+e.getMessage());
        }
        return result;
    }
    
    /**
     * 枚举出两个日期间的时间
     * @param beginTime
     * @param endTime
     * @return
     */
    private List<String> listDays(Date beginTime,Date endTime){
        //枚举从开始到结束的天数
        List<String> days = new ArrayList<String>();
        
        Date nextDay = beginTime;
        String strNextDay = DateUtil.date2String(beginTime, "yyyy-MM-dd");
        do{
            days.add(strNextDay);
            
            strNextDay = DateUtil.AddDays(strNextDay, 1);
            nextDay = DateUtil.dateStr2Date(strNextDay);
        }while(!DateUtil.isAfter(endTime,nextDay));
        return days;
    }

    @Override
    public List<VOptBaseList> findCaseHandledBySelf(int limit, String usercode) {
        return dashboardDao.findCaseHandledBySelf(limit, usercode);
    }
    
    /**
     * 获取收文待办数
     * @param usercode
     * @return
     */
    @Override
    public Long getToDoTaskNum(String usercode) {
        return dashboardDao.getToDoTaskNum(usercode);
    }
    @Override
    public Map<String, String> getvusertasklistNum(String usercode) {
        return dashboardDao.getvusertasklistNum(usercode);
    }
}
