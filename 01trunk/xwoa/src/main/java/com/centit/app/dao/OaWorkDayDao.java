package com.centit.app.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.po.OaWorkDay;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

public class OaWorkDayDao extends BaseDaoImpl<OaWorkDay>
	{
    private static final long serialVersionUID = 1L;
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("workday" , CodeBook.EQUAL_HQL_ID);

			filterField.put("daytype" , CodeBook.LIKE_HQL_ID);

			filterField.put("worktimetype" , CodeBook.LIKE_HQL_ID);

			filterField.put("workdaydesc" , CodeBook.LIKE_HQL_ID);

			filterField.put("hasschedule1" , CodeBook.LIKE_HQL_ID);

			filterField.put("hasschedule2" , CodeBook.LIKE_HQL_ID);

			filterField.put("hasschedule3" , CodeBook.LIKE_HQL_ID);

			filterField.put("hasschedule4" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 

    public List<OaWorkDay> getListByDate(Date beginTime,Date endTime){
	    
	    String hql="FROM OaWorkDay WHERE workday >= to_date(?,'yyyy-mm-dd') AND workday <= to_date(?,'yyyy-mm-dd')";
	    return listObjects(hql,
	            new Object[]{DatetimeOpt.convertDateToString(beginTime),DatetimeOpt.convertDateToString(endTime)});
	    
	}

    public OaWorkDay getObjectByWorkDay(Date workDay){
        
        String hql="FROM OaWorkDay WHERE workday = to_date(?,'yyyy-mm-dd')";
        List<OaWorkDay> ls = listObjects(hql, DatetimeOpt.convertDateToString(workDay));
        if(ls==null || ls.size()<1)
            return null;
        return ls.get(0);
        
    }
    
    
    //// hll 20140209 迁移数据库packages下WORK_DAY_OPT
    // 判断是否是工作日
    public boolean isWorkDay(Date currDate) {
        String hql="FROM OaWorkDay WHERE workday = to_date(?,'yyyy-mm-dd')";
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        if (Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK) || Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)) {
            hql += " and daytype='B'";
        } else { // 周一到周五
            hql += " and (daytype is null or daytype<>'A')";
        }
        
        List<OaWorkDay> ls = listObjects(hql, DatetimeOpt.convertDateToString(currDate));
        
        return !ls.isEmpty();
    }
	
    // 计算下一个工作日
    public Date calcNextWorkDate(Date currDate, double workDays) {
        String hql="FROM OaWorkDay WHERE workday >= to_date(?,'yyyy-mm-dd') and workday < to_date(?,'yyyy-mm-dd')";
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        
        int nWeeks = Double.valueOf(Math.floor(workDays / 5)).intValue();
        
        int nCurWorkDays = nWeeks * 7;
        cal.add(Calendar.DATE, nCurWorkDays);
        
        Object[] objs = {DatetimeOpt.convertDateToString(currDate), DatetimeOpt.convertDateToString(cal.getTime())};
        int holidays = listObjects(hql + " and daytype='A'", objs).size();
        int adddays = listObjects(hql + " and daytype='B'", objs).size();
        nCurWorkDays = nWeeks * 5 + adddays - holidays;
        
        while (nCurWorkDays < workDays) {
            cal.add(Calendar.DATE, 1);
            if (this.isWorkDay(cal.getTime())) {
                nCurWorkDays++;
            }
        }
        
        while (nCurWorkDays > workDays) {
            cal.add(Calendar.DATE, -1);
            if (this.isWorkDay(cal.getTime())) {
                nCurWorkDays--;
            }
        }
        
        return cal.getTime(); 
    }
    
    
    public Date calcNextWorkTime(Date currDate, double workDays, double workHours) {
        Date beginDate = currDate;
        double addDays = workDays;
        
        String amBeginVal = CodeRepositoryUtil.getDataPiece("SYSPARAM", "AM_BEGIN").getValue();
        String amEndVal = CodeRepositoryUtil.getDataPiece("SYSPARAM", "AM_END").getValue();
//        String pmBeginVal = CodeRepositoryUtil.getDataPiece("SYSPARAM", "PM_BEGIN").getValue();
        String pmEndVal = CodeRepositoryUtil.getDataPiece("SYSPARAM", "PM_END").getValue();
        String amBreak = CodeRepositoryUtil.getDataPiece("SYSPARAM", "AM_END").getExtracode();
        String pmBreak = CodeRepositoryUtil.getDataPiece("SYSPARAM", "PM_END").getExtracode();
        
        SimpleDateFormat date = new SimpleDateFormat(DatetimeOpt.getDatePattern());
        SimpleDateFormat dateTime = new SimpleDateFormat(DatetimeOpt.getDateTimePattern());
        
        Date amBegin = null;
        Date amEnd = null;
//        Date pmBegin = null;
        Date pmEnd = null;
        try {
            amBegin = dateTime.parse(date.format(currDate) + " " + amBeginVal + ":00");
            amEnd = dateTime.parse(date.format(currDate) + " " + amEndVal + ":00");
//            pmBegin = dateTime.parse(date.format(currDate) + " " + pmBeginVal + ":00");
            pmEnd = dateTime.parse(date.format(currDate) + " " + pmEndVal + ":00");
        } catch (Exception e) {
            log.error("未获取有效的上午以及下午上下班时间！", e);
        }
        
        if (beginDate.before(amBegin)) {
            beginDate = amBegin;
        } else if (beginDate.after(pmEnd)) {
            beginDate = amBegin;
            addDays = workDays + 1;
        }
    
        int wHours = Double.valueOf(Math.floor(workHours)).intValue();
        int wMinutes = Long.valueOf(Math.round((workHours - wHours) * 60)).intValue();
    
        Date calcDate = DatetimeOpt.addHours(beginDate, wHours);
        calcDate = DatetimeOpt.addMinutes(calcDate, wMinutes);
        
        if (beginDate.before(amEnd) && calcDate.after(amEnd)) {
            calcDate = DatetimeOpt.addHours(calcDate, Integer.parseInt(amBreak.split(":")[0]));
            calcDate = DatetimeOpt.addMinutes(calcDate, Integer.parseInt(amBreak.split(":")[1]));
        }
        
        if (calcDate.after(pmEnd)) {
            calcDate = DatetimeOpt.addHours(calcDate, Integer.parseInt(pmBreak.split(":")[0]));
            calcDate = DatetimeOpt.addMinutes(calcDate, Integer.parseInt(pmBreak.split(":")[1]));
            calcDate = DatetimeOpt.addDays(calcDate, -1);
            if (calcDate.after(amEnd)) {
                calcDate = DatetimeOpt.addHours(calcDate, Integer.parseInt(amBreak.split(":")[0]));
                calcDate = DatetimeOpt.addMinutes(calcDate, Integer.parseInt(amBreak.split(":")[1]));
            }
            addDays = workDays +1;    
        }
        
        return this.calcNextWorkDate(calcDate, addDays);
    }
    
    
    public int calcWeekendDays(Date beginDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        int nWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int m = Long.valueOf((endDate.getTime() - beginDate.getTime()) / 86400000l).intValue();
        int days = Double.valueOf(Math.floor((m + nWeekDay) / 7)).intValue() * 2;
        
        days = days - ((nWeekDay > 0) ? 1 : 0);
        days = days + ((((m + nWeekDay) % 7) > 0) ? 1 : 0);
        
        return days;
    }
    
    
    public Map<String, Object> calcWorkTime(Date beginDate, Date endDate) {
        // procedure begin
        String hql="FROM OaWorkDay WHERE workday >= to_date(?,'yyyy-mm-dd') and workday < to_date(?,'yyyy-mm-dd')";
        Object[] objs = {DatetimeOpt.convertDateToString(beginDate), DatetimeOpt.convertDateToString(endDate)};
        int holidays = listObjects(hql + " and daytype='A'", objs).size();
        int adddays = listObjects(hql + " and daytype='B'", objs).size();
        
        SimpleDateFormat date = new SimpleDateFormat(DatetimeOpt.getDatePattern());
        SimpleDateFormat dateTime = new SimpleDateFormat(DatetimeOpt.getDateTimePattern());
        
        Date beginDay = null;
        Date endDay = null;
        
        try {
            beginDay = date.parse(dateTime.format(beginDate).substring(0, 10));
            endDay = date.parse(dateTime.format(endDate).substring(0, 10));
        } catch (Exception e) {
            log.error("计算有效工作日未获取准备的开始时间或结束时间！", e);
        }
        
        int nDays = Long.valueOf((endDay.getTime() - beginDay.getTime()) / 86400000l).intValue();
        int nWeekEnds = this.calcWeekendDays(beginDate, endDate);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.add(Calendar.DATE, nDays);
        Date currDate = cal.getTime();
        
        Date bDate = null;
        Date eDate = null;
        int nSign = 0;
        
        if (currDate.before(endDate)) {
            bDate = currDate;
            eDate = endDate;
            nSign = 1;
        } else {
            eDate = currDate;
            bDate = endDate;
            nSign = -1;
        }
        
        double wHours = ((eDate.getTime() - bDate.getTime()) * 1.0) / 86400000.0 * 24;
        
        String amBeginVal = CodeRepositoryUtil.getDataPiece("SYSPARAM", "AM_BEGIN").getValue();
        String amEndVal = CodeRepositoryUtil.getDataPiece("SYSPARAM", "AM_END").getValue();
        String pmBeginVal = CodeRepositoryUtil.getDataPiece("SYSPARAM", "PM_BEGIN").getValue();
        String pmEndVal = CodeRepositoryUtil.getDataPiece("SYSPARAM", "PM_END").getValue();
        String amBreak = CodeRepositoryUtil.getDataPiece("SYSPARAM", "AM_END").getExtracode();
        
        Date amBegin = null;
        Date amEnd = null;
        Date pmBegin = null;
        Date pmEnd = null;
        try {
            amBegin = dateTime.parse(date.format(currDate) + " " + amBeginVal + ":00");
            amEnd = dateTime.parse(date.format(currDate) + " " + amEndVal + ":00");
            pmBegin = dateTime.parse(date.format(currDate) + " " + pmBeginVal + ":00");
            pmEnd = dateTime.parse(date.format(currDate) + " " + pmEndVal + ":00");
        } catch (Exception e) {
            log.error("未获取有效的上午以及下午上下班时间！", e);
        }
        
        if (bDate.before(amEnd) && eDate.after(pmBegin)) {
            try {
                wHours = wHours - Integer.valueOf(amBreak.split(":")[0]);
                wHours = wHours - Integer.valueOf(amBreak.split(":")[1]) * 1.0 / 60.0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        int workDays = nDays - nWeekEnds + adddays - holidays;
        double timeOffset = nSign * wHours;
        // procedure end
        
        int oneDayWorkTime = 0;
        oneDayWorkTime += (amEnd.getTime() - amBegin.getTime()) * 1.0 / 3600000.0;
        oneDayWorkTime += (pmEnd.getTime() - pmBegin.getTime()) * 1.0 / 3600000.0;
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        resultMap.put("workDays", workDays);
        resultMap.put("timeOffset", timeOffset);
        resultMap.put("workTimes", workDays * oneDayWorkTime + timeOffset);
        
        return  resultMap;
    }
    
    
    public int calcWorkDays(Date beginDate, Date endDate) {
        Map<String, Object> map = this.calcWorkTime(beginDate, endDate);
        
        int workDays = -1;
        try {
            workDays = Integer.valueOf(String.valueOf(map.get("workDays")));
        } catch (Exception e) {
            
        }
        
        return workDays;
    }
}
