package com.centit.oa.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaLeaveOver;
import com.centit.oa.po.OaLeaveOverTime;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;

public class OaLeaveOverTimeDao extends BaseDaoImpl<OaLeaveOverTime> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();
            filterField.put("usercode",CodeBook.EQUAL_HQL_ID);
            filterField.put("reasonType",CodeBook.EQUAL_HQL_ID);
            filterField.put("duringDate",CodeBook.EQUAL_HQL_ID);
            
            filterField.put("createDate desc",CodeBook.ORDER_BY_HQL_ID);
            
        }
        return filterField;
    }
    @SuppressWarnings("unchecked")
    public OaLeaveOverTime setObject(OaLeaveOverTime obj,String endDate1) {
       if(obj!=null&&obj.getCreateId()!=null){
           DetachedCriteria criteria=DetachedCriteria.forClass(OaLeaveOverTime.class);
           criteria.add(Restrictions.eq("createId", obj.getCreateId()));
           List<OaLeaveOverTime> objects=this.getHibernateTemplate().findByCriteria(criteria);
           if(objects!=null&&!objects.isEmpty()){
               DetachedCriteria criteria1=DetachedCriteria.forClass(OaLeaveOverTime.class);
               criteria1.add(Restrictions.eq("createId", obj.getCreateId()));
               if(StringUtils.isNotBlank(endDate1)){
                   Date beginDate=DatetimeOpt.convertStringToDate(endDate1, "yyyy-MM");
                   Date endDate=DatetimeOpt.addDays(DatetimeOpt.addMonths(DatetimeOpt.convertStringToDate(endDate1, "yyyy-MM"), 1), -1);
                   criteria1.add(Restrictions.between("duringDate", beginDate, endDate));
               }
               criteria1.setProjection(Projections.min("duringDate"));
               List<Date> objects1=this.getHibernateTemplate().findByCriteria(criteria1);
               if(objects1!=null&&!objects1.isEmpty()){
                   Date beginTime=new Date();
                   beginTime=objects1.get(0);
                   obj.setBeginTime(beginTime);
               }
               
               DetachedCriteria criteria2=DetachedCriteria.forClass(OaLeaveOverTime.class);
               criteria2.add(Restrictions.eq("createId", obj.getCreateId()));
               if(StringUtils.isNotBlank(endDate1)){
                   Date beginDate=DatetimeOpt.convertStringToDate(endDate1, "yyyy-MM");
                   Date endDate=DatetimeOpt.addDays(DatetimeOpt.addMonths(DatetimeOpt.convertStringToDate(endDate1, "yyyy-MM"), 1), -1);
                   criteria2.add(Restrictions.between("duringDate", beginDate, endDate));
               }
               criteria2.setProjection(Projections.max("duringDate"));
               List<Date> objects2=this.getHibernateTemplate().findByCriteria(criteria2);
               if(objects2!=null&&!objects2.isEmpty()){
                   Date endTime=new Date();
                   endTime=objects2.get(0);
                   obj.setEndTime(endTime);
               }else if(objects1!=null&&!objects1.isEmpty()){
                   Date endTime=new Date();
                   endTime=objects1.get(0);
                   obj.setEndTime(endTime);
               }
           }
       } 
        return obj;
    }
    public void deleteBatch(String createId) {
        final String hql = "delete from OaLeaveOverTime oal where oal.createId = ?";
        this.doExecuteHql(hql, new Object[] {createId});
    }
    public void deleteExist(String duringDate,String usercode) {
        Date date=DatetimeOpt.convertStringToDate(duringDate, "yyyy-MM-dd");
        final String hql = "delete from OaLeaveOverTime oal where oal.usercode = ? and oal.duringDate=?";
        this.doExecuteHql(hql, new Object[] {usercode,date});
    }
    @SuppressWarnings("unchecked")
    public OaLeaveOverTime findExist(String duringDate,String usercode) {
       OaLeaveOverTime obj=null;
       Date date=DatetimeOpt.convertStringToDate(duringDate, "yyyy-MM-dd");
      if(StringUtils.isNotBlank(duringDate)&&StringUtils.isNotBlank(usercode)){
          DetachedCriteria criteria=DetachedCriteria.forClass(OaLeaveOverTime.class);
          criteria.add(Restrictions.eq("usercode", usercode));
          criteria.add(Restrictions.eq("duringDate", date));
          List<OaLeaveOverTime> objects=this.getHibernateTemplate().findByCriteria(criteria);
          if(objects!=null&&!objects.isEmpty()){
              obj=new OaLeaveOverTime();
              obj=objects.get(0);
          }
      }
          return obj;
    }
    public DetachedCriteria getCriteria(Map<String, Object> filterMap,List<String> usercodes){
        DetachedCriteria criteria=DetachedCriteria.forClass(OaLeaveOverTime.class);
        String  auditDate=(String) filterMap.get("auditDate");
        if(StringUtils.isNotBlank(auditDate)){
            Date beginDate=DatetimeOpt.convertStringToDate(auditDate, "yyyy-MM");
            Date endDate=DatetimeOpt.addDays(DatetimeOpt.addMonths(DatetimeOpt.convertStringToDate(auditDate, "yyyy-MM"), 1), -1);
            criteria.add(Restrictions.between("duringDate", beginDate, endDate));
        }
        if(usercodes!=null&&! usercodes.isEmpty()){
            criteria.add(Restrictions.in("usercode", usercodes));
        }
        return criteria;
    }
    @SuppressWarnings("unchecked")
    public List<OaLeaveOverTime> listOaLeaveOverTime(Map<String, Object> filterMap, PageDesc pageDesc,List<String> usercodes) {
        List<OaLeaveOverTime> results=new ArrayList<OaLeaveOverTime>();
        if(usercodes!=null&&!usercodes.isEmpty()){
            // 1.查显示的数据
            DetachedCriteria criteria = this.getCriteria(filterMap, usercodes);
            results = getHibernateTemplate().findByCriteria(criteria,pageDesc.getRowStart(), pageDesc.getPageSize());
            // 2.查数据中满足条件的信息行数
            DetachedCriteria criteria1 = this.getCriteria(filterMap,usercodes);
            criteria1.setProjection(Projections.rowCount());
            List<Long> q = getHibernateTemplate().findByCriteria(criteria1);
            pageDesc.setTotalRows(q.get(0).intValue());
        }
        return results;
    }

    public DetachedCriteria getNewCriteria(Map<String, Object> filterMap,
            List<String> usercodes) {
        DetachedCriteria criteria = DetachedCriteria
                .forClass(OaLeaveOverTime.class);
        String auditDate = (String) filterMap.get("auditDate");//汇总时间
        String usercode=(String) filterMap.get("usercode");
        String reasonType=(String) filterMap.get("reasonType");//请假类型
        String duringDateBeg=(String) filterMap.get("duringDateBeg");//查询时间范围
        String duringDateEnd=(String) filterMap.get("duringDateEnd");
        
        
        
        if (StringUtils.isNotBlank(auditDate)) {
            Date beginDate = DatetimeOpt.convertStringToDate(auditDate, "yyyy-MM");
            Date endDate = DatetimeOpt.addDays(DatetimeOpt.addMonths(
                    DatetimeOpt.convertStringToDate(auditDate, "yyyy-MM"), 1),-1);
            criteria.add(Restrictions.between("duringDate", beginDate, endDate));
        }
        if (usercodes != null && !usercodes.isEmpty()) {
            criteria.add(Restrictions.in("usercode", usercodes));
        }
        if(StringUtils.isNotBlank(usercode)){
            criteria.add(Restrictions.eq("usercode", usercode));
        }
        if(StringUtils.isNotBlank(reasonType)){
            criteria.add(Restrictions.eq("reasonType", reasonType));
        }
        //查询起始时间
        if(StringUtils.isNotBlank(duringDateBeg)){
            Date duringDateBegtemp = DatetimeOpt.convertStringToDate(duringDateBeg, "yyyy-MM-dd");
            criteria.add(Restrictions.ge("duringDate", duringDateBegtemp));
        }
        //查询结束时间
        if(StringUtils.isNotBlank(duringDateEnd)){
            Date duringDateEndtemp = DatetimeOpt.convertStringToDate(duringDateEnd, "yyyy-MM-dd");
            criteria.add(Restrictions.le("duringDate", duringDateEndtemp));
        }
       
        criteria.setProjection(Projections.groupProperty("createId"));
        return criteria;
    }
    @SuppressWarnings("unchecked")
    public List<String> listOaLeaveOvers(
            Map<String, Object> filterMap, PageDesc pageDesc,
            List<String> usercodes) {
        List<String> results=new ArrayList<String>();
        if(usercodes!=null&&!usercodes.isEmpty()){
            // 1.查显示的数据
            DetachedCriteria criteria = this.getNewCriteria(filterMap, usercodes);
            results = getHibernateTemplate().findByCriteria(criteria,pageDesc.getRowStart(), pageDesc.getPageSize());
            // 2.查数据中满足条件的信息行数
            DetachedCriteria criteria1 = this.getNewCriteria(filterMap,usercodes);
            List<String> createids = getHibernateTemplate().findByCriteria(criteria1);
            pageDesc.setTotalRows(createids.size());
        }
        return results;
    }
    @SuppressWarnings("unchecked")
    public OaLeaveOverTime getByCreateID(String CreateID){
        OaLeaveOverTime obj=new OaLeaveOverTime();
        if(StringUtils.isNotBlank(CreateID)){
            DetachedCriteria criteria = DetachedCriteria.forClass(OaLeaveOverTime.class);
            criteria.add(Restrictions.eq("createId", CreateID));
            List<OaLeaveOverTime> objects=getHibernateTemplate().findByCriteria(criteria);
            if(objects!=null&&!objects.isEmpty()){
                obj=objects.get(0);
            }
        }
        return obj;
    }
    @SuppressWarnings("unchecked")
    public String isExist(String usercode,List<Date> duringDates) {
            String duringDate="";//结果字符串
            DetachedCriteria criteria=DetachedCriteria.forClass(OaLeaveOverTime.class);
            criteria.add(Restrictions.eq("usercode", usercode));
            criteria.add(Restrictions.in("duringDate", duringDates));
            List<OaLeaveOverTime>  objects=this.getHibernateTemplate().findByCriteria(criteria);
            if(objects!=null&&!objects.isEmpty()){
                for (OaLeaveOverTime obj : objects) {
                    String value=DatetimeOpt.convertDateToString(obj.getDuringDate(), "yyyy-MM-dd");
                    if(duringDate.equals(""))
                        duringDate=value;
                    else
                        duringDate+=","+value;
                }
            }
            return duringDate;
            
    }
    @SuppressWarnings("unchecked")
    public void setHoliday(OaLeaveOverTime object) {
        if(null!=object&&object.getCreateId()!=null){
            DetachedCriteria criteria=DetachedCriteria.forClass(OaLeaveOverTime.class);
            criteria.add(Restrictions.eq("createId", object.getCreateId()));
            List<OaLeaveOverTime>  objects=this.getHibernateTemplate().findByCriteria(criteria);
            if(null!=objects&&!objects.isEmpty()){
               object.setHolidayNum(String.valueOf(objects.size())) ;
            } else{
                object.setHolidayNum("0") ; 
            }   
        }
    }
    public Object isWeekend(Date duringDate){
        Object isweekend=0;
        isweekend=callFunction("is_work_day", 4, duringDate);
        return isweekend;
    }
    
    @SuppressWarnings("unchecked")
    public List<OaLeaveOver> listOaLeaveOverTime(Map<String, Object> filterMap) {
        String sSqlsen = "select u.usercode,(case when lo.state = 'A' then '-' when lo.state = 'B' then '○' when lo.state = 'C' then f_hoidayreson(lo.holiday_type) end),"+
       "to_number(TO_CHAR(lo.attendance_date,'dd'))   from v_user_units u left join oa_attendance_details lo  on lo.userid = u.usercode "+
       "where TO_CHAR(lo.attendance_date,'YYYY-MM')="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("auditDate"))+" and u.usercode="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("usercode"))+" group by (case when lo.state = 'A' then '-' when lo.state = 'B' then '○' when lo.state = 'C' then  f_hoidayreson(lo.holiday_type) end),"+
          "lo.attendance_date,u.usercode order by u.usercode";
            
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sSqlsen);
        List<OaLeaveOver> oaLeaveOvers =new ArrayList<OaLeaveOver>();
        
        if(l!=null && l.size()>0){
           for(Object[] o:l){
               OaLeaveOver oaLeaveOver=new OaLeaveOver();
               oaLeaveOver.setUsercode(o[0].toString());
               oaLeaveOver.setState(o[1].toString());
               oaLeaveOver.setDaynum(Integer.parseInt(o[2].toString()));
               oaLeaveOvers.add(oaLeaveOver);
           }
        }
        return oaLeaveOvers;
    }
    
    @SuppressWarnings("unchecked")
    public List<FUserinfo> getUsersByUnitCode(String primaryUnit) {
        String sSqlsen = "select v.unitcode,v.usercode,v.username from v_user_units v where  v.unitcode="+HQLUtils.buildHqlStringForSQL(primaryUnit) +" order by v.userorder asc";
                     
                 List<Object[]> l = (List<Object[]>) findObjectsBySql(sSqlsen);
                 List<FUserinfo> userinfos =new ArrayList<FUserinfo>();
                 
                 if(l!=null && l.size()>0){
                    for(Object[] o:l){
                        FUserinfo userinfo=new FUserinfo();
                        userinfo.setUsercode(o[1].toString());
                        userinfo.setPrimaryUnit(o[0].toString());
                        userinfo.setUsername(o[2].toString());
                        userinfos.add(userinfo);
                    }
                 }
                 return userinfos;
    }
    
    /**
     * 自动添加考勤数据
     */
    public void addAttendanceDetails(Date date){
        callProcedure("P_INSERT_ATTENDANCE_DAY", date);
    }
}
