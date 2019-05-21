package com.centit.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.centit.app.dao.OaStatMonthDao;
import com.centit.app.dao.OaWorkDayDao;
import com.centit.app.po.OaStatMonth;
import com.centit.app.po.OaWorkDay;
import com.centit.app.po.OaWorkingTime;
import com.centit.core.utils.WorkTimeSpan;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.WorkCalendar;

public class WorkCalendarImpl implements WorkCalendar {

    private OaStatMonthDao oaStatMonthDao;
    private OaWorkDayDao oaWorkDayDao;

    public OaStatMonthDao getOaStatMonthDao() {
        return oaStatMonthDao;
    }

    public void setOaStatMonthDao(OaStatMonthDao oaStatMonthDao) {
        this.oaStatMonthDao = oaStatMonthDao;
    }

    public OaWorkDayDao getOaWorkDayDao() {
        return oaWorkDayDao;
    }

    public void setOaWorkDayDao(OaWorkDayDao oaWorkDayDao) {
        this.oaWorkDayDao = oaWorkDayDao;
    }

    /**
     * 获取某个统计月的起止日期
     * 
     * @param sMonth
     *            格式为YYYYMM 字符串，比如201202
     * @return 两个元素的一维数组。
     */
    public Date[] getStatMonth(String sMonth) {

        OaStatMonth dbobject = oaStatMonthDao.getObjectById(sMonth);
        Date[] statMonth = { dbobject.getBeginDay(), dbobject.getEendDay() };
        return statMonth;
    }

    
    
    //0810
    public Long getTimeFromString (String s){
        Long hour =Long.valueOf(s.substring(0, 2));
        Long min=Long.valueOf(s.substring(2,4));
        return hour*1000*60*60+min*60*1000;
    }
    
    /**
     * 根据企业日历计算实际工作时间，剔除假期和休息时间   加上加班的天数 (defaut规则是剔除了星期六和星期天 ，和法定节假日:从数据库里面的读的) 
     * 
     * @param beginTime
     *            开始时间,精确到天
     * @param endTime
     *            结束时间,精确到天
     * @return 以日为单位 半天的以一天为准
     */
    public WorkTimeSpan getWorkTime(Date beginTime, Date endTime) {
        
//        OaWorkingTime oaWorkingTime=oaWorkingTimeDao.getObjectById("qls"); 
        OaWorkingTime oaWorkingTime=null; 
        long n = getholidays(beginTime, endTime);
        long i = getAdddays (beginTime, endTime);
        long  m = getDaysBetween(beginTime, endTime)
                - DatetimeOpt.calcWeekendDays(beginTime, endTime);  
       long time1= getTimeFromString(oaWorkingTime.getSchedule1begin());
       long time2= getTimeFromString(oaWorkingTime.getSchedule1end());
       long time3= getTimeFromString(oaWorkingTime.getSchedule2begin());
       long time4= getTimeFromString(oaWorkingTime.getSchedule2end());
       
       long betime = DatetimeOpt.getHour(beginTime)*60*60*1000 + DatetimeOpt.getMinute(beginTime)*60*1000;
       long entime = DatetimeOpt.getHour(endTime)*60*60*1000 + DatetimeOpt.getMinute(endTime)*60*1000;
       
       
        
       long begSpan=0;
       if(isWorkDate(beginTime)){
           if(betime>time1 && betime<=time2)
               begSpan = betime - time1;
           else if(betime>time2 && betime<=time3)
               begSpan = time2 - time1;
           else if(betime>time3 && betime<=time4)
               begSpan = time2 - time1 + betime - time3;
           else if(betime>time4)
               begSpan = 8*60*60*1000;
       }
       
       long endSpan=0;
       if(isWorkDate(endTime)){
           if(entime>time3 && entime<=time4)
               endSpan = time4 - entime;
           else if(entime>time2 && entime<=time3)
               endSpan = time4 - time3;
           else if(entime>time1 && entime<=time2)
               endSpan = time4 - time3 + time2 - entime;
           else if(entime<=time1)
               endSpan = 8*60*60*1000; 
       }
       
       long removeTime = 960 - (endSpan + begSpan) / 60 /1000;
       long d = removeTime / 480;
       long h = (removeTime - d*480) / 60;
       long mi =  removeTime - d*480 - h * 60;
        
       return new WorkTimeSpan(m-n+i-2+d,h,mi);
        
    }

    //包含两头的日期
    public  static Long getDaysBetween(Date beginTime, Date endTime) {
        try{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String strd1=sdf.format(beginTime);
        String strd2=sdf.format(endTime);
        Date d1= sdf.parse(strd1);
        Date d2= sdf.parse(strd2);
        long daysBetween=(d2.getTime()-d1.getTime())/(3600*24*1000)+1;
        
        return daysBetween;
        }
        catch (Exception e) {
            return null;
        }
  
    }
    
    /**
     * 4.根据某个基础工作时间点和工作时间段偏差计算工作日期
     * 
     * @param baseWorkDate
     *            基点工作时间
     * @param workTime
     *            工作时间偏移，<0 向前， >0向后
     * @return
     */

   
    public  Date getWorkDate(Date baseWorkDate, String workTime) {        
        WorkTimeSpan wt=new WorkTimeSpan();
        wt.fromString(workTime);
        Date endWorkDate = getWorkDate(baseWorkDate,wt);
        
        return endWorkDate;
    }
    
    public Date getWorkDate(Date baseWorkDate, WorkTimeSpan workTime) {
        Date endWorkDate = null;
        long d=  workTime.getDays();
        long m=d/5*7;
        Date lsdate=new Date(baseWorkDate.getTime()+m*24*60*60*1000);  //临时
        long holidays=getholidays(baseWorkDate, lsdate);         //正常工作日变成假期的
        long adddays=getAdddays(baseWorkDate, lsdate);           //正常假期变成工作日的
        long zctime=m-DatetimeOpt.calcWeekendDays(baseWorkDate, lsdate)-holidays+adddays;
        endWorkDate = lsdate;
        if(d>zctime){
            long needAddedDays = d-zctime;
            long i =0;
             while(i<needAddedDays){
                endWorkDate=new Date(endWorkDate.getTime() + 24*60*60*1000);
                if( isWorkDate(endWorkDate))
                    i++;
                
            }
        }else if(d<zctime){
            long needAddedDays = zctime-d;
            long i =0;
             while(i<needAddedDays){
                endWorkDate=new Date(endWorkDate.getTime() - 24*60*60*1000);
                if( isWorkDate(endWorkDate))
                    i++;               
            }
        }
        
        //算小时和分钟
        if(workTime.getHours()!=0||workTime.getMinutes()!=0){
            //不超过一天
            if(( (DatetimeOpt.getHour(baseWorkDate)*60+DatetimeOpt.getMinute(baseWorkDate)) *60*1000+
                 (workTime.getHours()*60+workTime.getMinutes())*60*1000)<24*60*60*1000
                    ){
              endWorkDate= new Date(endWorkDate.getTime()+
                 (workTime.getHours()*60+workTime.getMinutes())*60*1000);
             
            }
            //超过一天
            else 
            {   endWorkDate= new Date(endWorkDate.getTime()+
                    (workTime.getHours()*60+workTime.getMinutes()) *60*1000);             
                if(!isWorkDate(endWorkDate))
                { endWorkDate=new Date(endWorkDate.getTime()+
                        (workTime.getHours()*60+workTime.getMinutes())*60*1000+24*60*60*1000);
                }           
            }      
        }
        return endWorkDate;
    }

    /**
     * 
     * 获取某个统计月的工作时间
     * 
     * @param sMonth
     *            格式为YYYYMM 字符串，比如201202
     * @return
     */
    public WorkTimeSpan getStatMonthWorkTime(String sMonth) {
        // TODO 计算所有工作日时间
        return new WorkTimeSpan();
    }
    
//Datype:B表示周末加班，A表示工作日放假
    public boolean isWorkDate(Date date){
        boolean result = true;
        

        OaWorkDay dbobject= oaWorkDayDao.getObjectByWorkDay(date);
        if(DatetimeOpt.getDayOfWeek(date)==0
                ||DatetimeOpt.getDayOfWeek(date)==6) //周末的情况
        { 
            result=false;
            if(dbobject!=null){
               if( dbobject.getDaytype().equals("B"))      //周末加班的情况          
                result=true;
            }
           
        }else  if(dbobject!=null)
        {
           if( dbobject.getDaytype().equals("A")) //工作日放假的情况
            result =false;
        }
        return result;
    }

    public long getholidays(Date beginTime, Date endTime){
        long i=0;
       List<OaWorkDay> list=oaWorkDayDao.getListByDate(beginTime, endTime);
       if(list!=null){
       for(OaWorkDay a :list){
           if(a.getDaytype().equals("A"))
               i++;
       }
       }
        return i;        
    }
    public long getAdddays(Date beginTime, Date endTime){
        long i=0;
        List<OaWorkDay> list=oaWorkDayDao.getListByDate(beginTime, endTime);
        if(list!=null){
        for(OaWorkDay a :list){
            if(a.getDaytype().equals("B"))
                i++;
        }
        }
         return i;      
     }


    @Override
    /**
     * 根据企业日历判断 当前时间是否为工作时间
     * @param workTime 开始时间
     * @return 
     */
    public boolean isWorkTime(Date workTime) {
        
        if(!isWorkDate(workTime)){
            return false;
        }else{ 
           int m=DatetimeOpt.getMinute(workTime)+100*DatetimeOpt.getHour(workTime);
//           OaWorkingTime a=oaWorkingTimeDao.getObjectbyDate(workTime);
           OaWorkingTime a=null;
           if(a==null)//默认朝九晚五 codefan@sina.com
               return m > 900 && m< 1700;
           if("1".equals(a.getHasschedule1())){
               if(m>Integer.parseInt(a.getSchedule1begin())&&m<Integer.parseInt(a.getSchedule1end()))
                   return true; 
           }
           if("1".equals(a.getHasschedule2())){
               if(m>Integer.parseInt(a.getSchedule2begin())&&m<Integer.parseInt(a.getSchedule2end()))
                   return true; 
           }
           if("1".equals(a.getHasschedule3())){
               if(m>Integer.parseInt(a.getSchedule3begin())&&m<Integer.parseInt(a.getSchedule3end()))
                   return true; 
           }
           if("1".equals(a.getHasschedule4())){
               if(m>Integer.parseInt(a.getSchedule4begin())&&m<Integer.parseInt(a.getSchedule4end()))
                   return true; 
           }
        }
        return false;
    }
    
    
    public int calcWorkDays(Date beginDate, Date endDate) {
        return oaWorkDayDao.calcWorkDays(beginDate, endDate);
    }
}
