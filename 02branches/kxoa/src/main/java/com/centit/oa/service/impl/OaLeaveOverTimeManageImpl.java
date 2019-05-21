package com.centit.oa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.centit.app.po.OaWorkDay;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.OaLeaveOverTimeDao;
import com.centit.oa.po.OaLeaveOver;
import com.centit.oa.po.OaLeaveOverTime;
import com.centit.oa.service.OaAttendanceDetailsManager;
import com.centit.oa.service.OaLeaveOverTimeManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.SysUserManager;

public class OaLeaveOverTimeManageImpl extends BaseEntityManagerImpl<OaLeaveOverTime> implements OaLeaveOverTimeManager {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private OaLeaveOverTimeDao  oaLeaveOverTimeDao ;
    public void setOaLeaveOverTimeDao(OaLeaveOverTimeDao baseDao) {
        this.oaLeaveOverTimeDao = baseDao;
        setBaseDao(oaLeaveOverTimeDao);
    }
    
    private OaAttendanceDetailsManager oaAttendanceDetailsManager;
    
    private SysUserManager sysUserManager;
    
    public void setOaAttendanceDetailsManager(
            OaAttendanceDetailsManager oaAttendanceDetailsManager) {
        this.oaAttendanceDetailsManager = oaAttendanceDetailsManager;
    }
    
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    @Override
    public String getNextKey() {
        return "OUTSQ"+ oaLeaveOverTimeDao.getNextKeyBySequence("S_OAOUTAPPLY", 11);
    }
    /**
     * 如果是新增，而且数据库里已经请过假了，那么删除以前请过假的时间，重新请假
     */
    @Override
    public void deleteExist(String usercode,String existDate){
       if(StringUtils.isNotBlank(usercode)&&StringUtils.isNotBlank(existDate)){
           List<OaLeaveOverTime> result=new ArrayList<OaLeaveOverTime>();//要删除的对象集合
           String [] dateArra=null;
           //1.找到重复对象的重复的年月日
           if(existDate.indexOf(",")>0)
               dateArra=existDate.split(",");
           else
               dateArra=new String []{existDate};
           //2.找出重复对象
           if(dateArra!=null&&dateArra.length>0){
               for (String date : dateArra) {
                OaLeaveOverTime obj=oaLeaveOverTimeDao.findExist(date, usercode); 
                if(obj!=null)
                    result.add(obj);
               }
           }
           //3.删除集合中对象
           if(result!=null&&!result.isEmpty()){
               for (OaLeaveOverTime ob : result) {
                   deleteObject(ob);
            }
           }
       } 
    }
    @Override
    public void save(OaLeaveOverTime object,String usercode) {
        if(object!=null&&object.getBeginTime()!=null&&object.getEndTime()!=null&&StringUtils.isNotBlank(usercode)){
           //1.如果是修改，那么删除原先的信息
            if(object!=null&&StringUtils.isNotBlank(object.getLoroNo())&&StringUtils.isNotBlank(object.getCreateId())){
                this.deleteBatch(object.getCreateId());
            }else{//如果是新增，而且数据库里已经请过假了，那么删除以前请过假的时间，重新请假
              String  beginTimeStr=DatetimeOpt.convertDateToString(object.getBeginTime(), "yyyy-MM-dd");
              String  endTimeStr=DatetimeOpt.convertDateToString(object.getEndTime(), "yyyy-MM-dd");
              String existDate=this.isExist(object.getUsercode(),beginTimeStr,endTimeStr);
              if(StringUtils.isNotBlank(existDate)){
                  this.deleteExist(object.getUsercode(), existDate);
              }
            }
            //2.根据请假开始时间和结束时间，生成多个请假对象OaLeaveOverTime
            List<OaLeaveOverTime> objects=this.setObjects(object,usercode);
            //3.保存对象
            if(objects!=null&&!objects.isEmpty())
                for (OaLeaveOverTime obj : objects) {
                    this.saveObject(obj);
                }
        }
    }
    /**
     * 判断请假时间是否非周末的那五天，如果是，返回1，不是返回0
     */
    public int isweekday(Date duringDate){
        int isweekend=0;
        isweekend=(Integer) oaLeaveOverTimeDao.isWeekend(duringDate);
        return isweekend;
    }
    /**
     * 根据请假天数，决定分装多少个OaLeaveOverTime对象(如果请假天数里包括周六，周末，自动去掉周六周末)
     */
    private List<OaLeaveOverTime> setObjects(OaLeaveOverTime object,String usercode){
        List<OaLeaveOverTime> objects=new ArrayList<OaLeaveOverTime>();
        Long between_days=this.doMinus(object.getBeginTime(), object.getEndTime());
        if(between_days>=0){
             String createid=this.getCreateId();//取同一组假期的标记
            for (int j = 0; j < between_days+1; j++) {
                Date duringDate=DatetimeOpt.addDays(object.getBeginTime(), j);
                if(isweekday(duringDate)!=1){
                    continue;
                }
                OaLeaveOverTime obj=new OaLeaveOverTime();
                
                obj.setLoroNo(this.getNextKey());//取主键
                obj.setCreateId(createid);//设置同一组假期的标记
                obj.setUsercode(object.getUsercode());
                obj.setReasonType(object.getReasonType());
                obj.setReasonDesc(object.getReasonDesc());
                obj.setDuringDate(duringDate);
                obj.setCreateDate(new Date());
                obj.setProposer(usercode);
                
                objects.add(obj);
            }
        }
        return objects;
    }
    /**
     * 计算请假开始和结束的时间差
     */
    private Long doMinus(Date beginTime,Date endTime){
        long between_days=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
            beginTime=sdf.parse(sdf.format(beginTime));  
            endTime=sdf.parse(sdf.format(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(beginTime);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(endTime);    
        long time2 = cal.getTimeInMillis();         
        between_days=(time2-time1)/(1000*3600*24);  
        return between_days;
    }
    @Override
    public String getCreateId() {
        return oaLeaveOverTimeDao.getNextKeyBySequence("S_CREATEID", 11);
    }
    @Override
    public void deleteBatch(String createId) {
        oaLeaveOverTimeDao.deleteBatch(createId);
    }
    @Override
    public List<OaLeaveOverTime> setOaLeaveOverTimes(List<OaLeaveOverTime> objects,String endDate) {
        List<OaLeaveOverTime> objList=new ArrayList<OaLeaveOverTime>();
        if(objects!=null&&!objects.isEmpty()){
            for (OaLeaveOverTime oaLeaveOverTime : objects) {
                OaLeaveOverTime oaleave=new OaLeaveOverTime();
                oaleave=this.getByCreateID(oaLeaveOverTime.getCreateId());
                if(oaleave!=null){
                    oaLeaveOverTime.copyNotNullProperty(oaleave);
                    objList.add(this.setObject(oaLeaveOverTime,endDate));
                }
            }
        }
        if(!objList.isEmpty())
            return objList;
        else 
            return objects;
    }
    @Override
    public OaLeaveOverTime setObject(OaLeaveOverTime obj,String endDate) {
        return oaLeaveOverTimeDao.setObject(obj,endDate) ;
    }
    @Override
    public List<OaLeaveOverTime> listOaLeaveOverTime(Map<String, Object> filterMap, PageDesc pageDesc,List<String> usercodes) {
        return oaLeaveOverTimeDao.listOaLeaveOverTime(filterMap, pageDesc, usercodes);
    }
    @Override
    public List<OaLeaveOverTime> listOaLeaveOvers(
            Map<String, Object> filterMap, PageDesc pageDesc,
            List<String> usercodes) {
        List<OaLeaveOverTime> objects=new ArrayList<OaLeaveOverTime>();
        List<String> createids=new ArrayList<String>();
        createids=oaLeaveOverTimeDao.listOaLeaveOvers(filterMap, pageDesc, usercodes);
        if(createids!=null&&!createids.isEmpty()){
            for (String id : createids) {
                OaLeaveOverTime oaleave=new OaLeaveOverTime();
                oaleave.setCreateId(id);
                
                objects.add(oaleave);
            }
        }
        return objects;
    }
    @Override
    public OaLeaveOverTime getByCreateID(String CreateID) {
        return oaLeaveOverTimeDao.getByCreateID(CreateID);
    }
    @Override
    public String isExist(String usercode, String beginTimeStr, String endTimeStr) {
        Date beginTime=DatetimeOpt.convertStringToDate(beginTimeStr, "yyyy-MM-dd");
        Date endTime=DatetimeOpt.convertStringToDate(endTimeStr, "yyyy-MM-dd");
        List<Date> duringDates=new ArrayList<Date>();
        Long between_days=this.doMinus(beginTime, endTime);
        if(between_days>=0){
            for (int j = 0; j < between_days+1; j++) {
               
                Date day=DatetimeOpt.addDays(beginTime, j);
               
                duringDates.add(day);
            }
        }
        return oaLeaveOverTimeDao.isExist(usercode, duringDates);
    }
    
    /**
     * 自动添加考勤数据
     */
    public void addAttendanceDetails(Date date){
        oaLeaveOverTimeDao.addAttendanceDetails(date);
    }
    

    /**
     * 修改、添加或删除请假信息时更新考勤明细表
     * @param o
     * @param isUpdate 1---添加/修改请假信息，0---删除请假信息
     */
    public void updateAttendanceDetails(OaLeaveOverTime o, OaLeaveOverTime dbObject, String isUpdate){
        
        if (null != o) {
            
            List<Date> duringDates = getDuringDates(o);
            
            for(Date date : duringDates){
                // 更新考勤信息
                oaAttendanceDetailsManager.updateAttendanceDetails(o.getUsercode(), date , isUpdate);
            }
            
            // 以下仅修改请假信息时可能进入，删除请假信息时不会执行到
            List<Date> datesToDelete = getDatesToDelete(duringDates, dbObject);
            
            if(null != datesToDelete && !datesToDelete.isEmpty()){
                for(Date d : datesToDelete){
                    // 将原来的请假时间中已经修改过的日期的考勤信息修改
                    oaAttendanceDetailsManager.updateAttendanceDetails(dbObject.getUsercode(), d, "0");
                }
            }
        }
    }
    
    /**
     * 根据请假起始和结束时间生成请假日期列表
     * @param o
     * @return
     */
    public List<Date> getDuringDates(OaLeaveOverTime o){
        
        List<Date> duringDates = new ArrayList<Date>();
        int spanDays = DatetimeOpt.equalOnDay(o.getBeginTime(), o.getEndTime()) ? 1 : DatetimeOpt.calcSpanDays(o.getBeginTime(), o.getEndTime()) + 1;
        for(int i = 0 ; i < spanDays; i++){
            duringDates.add(DatetimeOpt.addDays(o.getBeginTime(), i));
        }
        
        return duringDates;
    }

    /**
     * 根据数据库中的原来的请假信息对象dbObject和修改后的请假时间找出需要更新考勤的日期
     * @param duringDates---新修改的请假时间集合
     * @param dbObject---数据库中的请假信息对象
     * @return
     */
    public List<Date> getDatesToDelete(List<Date> duringDates, OaLeaveOverTime dbObject){
        
        List<Date> dbDuringDates = null;
        List<Date> datesToDelete = null;
        if(null != dbObject){
            dbDuringDates = getDuringDates(dbObject);
            datesToDelete = new ArrayList<Date>();
            
            if(null != dbDuringDates){
                
                // 循环比对新旧请假时间集合，找出旧的请假时间中需要修改的部分
                for(Date d1 : dbDuringDates){
                    
                    boolean flag = false;
                    for(Date d2 : duringDates ){
                        if(DatetimeOpt.equalOnDay(d1, d2)){
                            flag = true;
                            break;
                        }
                    }
                    
                    if(!flag){
                        datesToDelete.add(d1);
                    }
                }
            }
        }
        
        return datesToDelete;
    }
    
    /**
     * 更新日历时更新考勤信息
     * @param day
     */
    public void updateAttendDetailsByCalendar(OaWorkDay day){
        
        if(null != day){
            
            Map<String, Object > map = new HashMap<String, Object>();
            map.put("ISVALID", "T");
            List<FUserinfo> users = sysUserManager.listObjects(map);
            
            if(null != users && !users.isEmpty()){
                for(FUserinfo user : users){
                    
                    /*if("A".equals(day.getDaytype())){*/
                        oaAttendanceDetailsManager.updateAttendanceDetails(user.getUsercode(), day.getWorkday(), "1");
                    /*}else if("B".equals(day.getDaytype())){
                        oaAttendanceDetailsManager.updateAttendanceDetails(usercode, updateDate, isUpdate);
                    }*/
                }
            }
        }
    }
    
    @Override
    public void setHoliday(OaLeaveOverTime object) {
        oaLeaveOverTimeDao.setHoliday(object);
    }
    @Override
    public List<OaLeaveOver> listOaLeaveOverTime(Map<String, Object> filterMap) {
        // TODO Auto-generated method stub
        return oaLeaveOverTimeDao.listOaLeaveOverTime(filterMap);
    }
    @Override
    public List<FUserinfo> getUsersByUnitCode(String primaryUnit) {
        // TODO Auto-generated method stub
        return oaLeaveOverTimeDao.getUsersByUnitCode(primaryUnit);
    }
}
