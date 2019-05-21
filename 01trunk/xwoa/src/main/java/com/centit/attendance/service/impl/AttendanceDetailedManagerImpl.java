package com.centit.attendance.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.advancedsearch.po.OaSearchResult;
import com.centit.attendance.po.AttendanceDetailed;
import com.centit.attendance.dao.AttendanceDetailedDao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.attendance.service.AttendanceDetailedManager;
import com.centit.support.utils.DatetimeOpt;

public class AttendanceDetailedManagerImpl extends
        BaseEntityManagerImpl<AttendanceDetailed> implements
        AttendanceDetailedManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(AttendanceDetailedManager.class);

    private AttendanceDetailedDao attendanceDetailedDao;

    public void setAttendanceDetailedDao(AttendanceDetailedDao baseDao) {
        this.attendanceDetailedDao = baseDao;
        setBaseDao(this.attendanceDetailedDao);
    }

    /**
     * 上下班时间设置自动生成id
     */
    @Override
    public String getNewCode() {
        return "LS" + attendanceDetailedDao.getNextKeyBySequence(
        		"S_Attendance_Detailed", 20);
    }

    /**
     * 根据用户编号获取部门名称
     */
    @Override
    public String findUnitnameByUcode(String ucode) {
        // TODO Auto-generated method stub
        return attendanceDetailedDao.findUnitnameByUcode(ucode);
    }
    
    @Override
    public List<Object[]> getExcelAttendanceDepsList(AttendanceDetailed object) {
        return attendanceDetailedDao.getExcelAttendanceDepsList(object
                .getMonth());
    }

    /**
     * 根据部门、月份查询对应部门人员
     */
    @Override
    public String[] getUserNamesHeaders(String month, String depname) {
        String[] re = null;
        // 获取部门人员
        List<Object[]> usernameslist = attendanceDetailedDao
                .getUserNamesHeaders(month, depname);

        if (usernameslist != null && usernameslist.size() > 0) {
            re = new String[usernameslist.size() * 2 + 1];//人员表头，为人员数*2+第一行第一列  格式 如下： 空  张三  李四  王五...
            int j = 0;
            for (int i = 0; i < usernameslist.size() * 2 + 1; i++) {
                if (i % 2 != 0) {
                    re[i] = usernameslist.get(j)[1].toString();
                    j++;
                } else {
                    re[i] = "";//人员都包括上午和下午，所有多余的列用空代替，后续再Excel处合并单元格做准备
                }

            }
        }
        return re;
    }

    @Override
    public List<List<String>> getExcelAttendanceDateList(String month,
            String depname) throws ParseException {
        List<List<String>> data = new ArrayList<List<String>>();

        // 获取某月的天数，作为每一sheet行
        List<Object[]> daysOfMonthlist = attendanceDetailedDao
                .getDaysOfMonthByDep(month, depname);

        if (daysOfMonthlist != null && daysOfMonthlist.size() > 0) {// 通过日期循环，对同一天上下午结合在一起。
           
            for (int i = 0; i < daysOfMonthlist.size(); i++) {// 月份
                List rowData = new ArrayList();
                rowData.add(DatetimeOpt.convertDateToString(DatetimeOpt.convertStringToDate(daysOfMonthlist.get(i)[0].toString()), "MM月dd号"));
                
                // 获取同一天，同部门人员上下班时间，并按照人员排序组合
                List<Object[]> attendancesOfUserslist = attendanceDetailedDao
                        .getAttendanceDays(daysOfMonthlist.get(i)[0].toString(),depname);
                if(attendancesOfUserslist!=null && attendancesOfUserslist.size()>0){
                    for (int j = 0; j < attendancesOfUserslist.size(); j++) {// 同一天 人员上下班出勤情况
                    rowData.add(attendancesOfUserslist.get(j)[1]==null?"--":attendancesOfUserslist.get(j)[1].toString());
                    rowData.add(attendancesOfUserslist.get(j)[2]==null?"--":attendancesOfUserslist.get(j)[2].toString());
                }               
            }
                data.add(rowData);   
        }
        }
        return data;
    }
    /**
     * 根据姓名跟日期查询
     */
    @Override
    public String findNumByUnameAndWorkdate(String uname,String workdate){
        // TODO Auto-generated method stub
        return attendanceDetailedDao.findNumByUnameAndWorkdate(uname,workdate);
    }
}
