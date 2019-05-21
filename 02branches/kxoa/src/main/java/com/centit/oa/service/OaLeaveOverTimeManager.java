package com.centit.oa.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.centit.app.po.OaWorkDay;
import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaLeaveOver;
import com.centit.oa.po.OaLeaveOverTime;
import com.centit.sys.po.FUserinfo;

public interface OaLeaveOverTimeManager extends
        BaseEntityManager<OaLeaveOverTime> {
    /**
     * 取主键
     */
    public String getNextKey();

    /**
     * 取createid
     */
    public String getCreateId();

    /**
     * 生成请假信息
     */
    public void save(OaLeaveOverTime object, String usercode);

    /**
     * 根据createId删除一组请假对象
     */
    public void deleteBatch(String createId);

    /**
     * 重新封装一下OaLeaveOverTime的集合(得到他们的除createid以外的属性，如：开始时间和结束时间)
     */
    public List<OaLeaveOverTime> setOaLeaveOverTimes(
            List<OaLeaveOverTime> objects, String endDate);

    /**
     * 封装单个OaLeaveOverTime的beginTime和endTime临时属性(endDate为统计年月的数据)
     */
    public OaLeaveOverTime setObject(OaLeaveOverTime obj, String endDate);

    /**
     * 搜索请假信息(可对同一部门下的所有用户，在某一月份内进行统计)
     */
    public List<OaLeaveOverTime> listOaLeaveOverTime(
            Map<String, Object> filterMap, PageDesc pageDesc,
            List<String> usercodes);

    /**
     * 搜索请假信息(可对同一部门下的所有用户，在某一月份内进行统计),wxy重新写的
     */
    public List<OaLeaveOverTime> listOaLeaveOvers(
            Map<String, Object> filterMap, PageDesc pageDesc,
            List<String> usercodes);

    /**
     * 通过createid拿到任意一个OaLeaveOverTime
     */
    public OaLeaveOverTime getByCreateID(String CreateID);

    /***
     * 检查某用户某时间段是否已经请过假(返回已请假的天数由逗号连接的字符串)
     */
    public String isExist(String usercode, String beginTime, String endTime);

    /**
     * 如果是新增，而且数据库里已经请过假了，那么删除以前同个人同时间请过的假，重新请假
     */
    public void deleteExist(String usercode, String existDate);

    /**
     * 设置请假的临时属性天数
     */
    public void setHoliday(OaLeaveOverTime object);

    /**
     * 获取单个人员的一个月内的考勤情况信息
     * 
     * @param filterMap
     * @return
     */
    public List<OaLeaveOver> listOaLeaveOverTime(Map<String, Object> filterMap);

    /**
     * 获取本科室下的所有在用人员
     * 
     * @param primaryUnit
     * @return
     */
    public List<FUserinfo> getUsersByUnitCode(String primaryUnit);
    
    /**
     * 自动添加某个日期下的考勤数据
     */
    public void addAttendanceDetails(Date date);
    
    /**
     * 修改、添加或删除请假信息时更新考勤明细表
     * @param o
     * @param isUpdate 1---添加/修改请假信息，0---删除请假信息
     */
    public void updateAttendanceDetails(OaLeaveOverTime o, OaLeaveOverTime dbObject, String isUpdate);
    
    /**
     * 更新日历时更新考勤信息
     * @param day
     */
    public void updateAttendDetailsByCalendar(OaWorkDay day);
}
