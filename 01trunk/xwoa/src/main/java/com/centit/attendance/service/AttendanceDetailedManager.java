package com.centit.attendance.service;
import java.util.List;
import java.text.ParseException;
import com.centit.core.service.BaseEntityManager;
import com.centit.attendance.po.AttendanceDetailed;

public interface AttendanceDetailedManager extends BaseEntityManager<AttendanceDetailed> 
{
    /**
     * 自动生成id
     * 这个需要创建序列，在Sequences新建一个序列即可
     */
    public String getNewCode();
    
    /**
     * 根据用户编号获取部门名称
     */
    public String findUnitnameByUcode(String ucode);
    /**
     * 根据月份 查询考勤部门 剔除重复项,用于多标签sheet。比如，办公室 |宣教部。。。
     * @param object
     * @return
     */
    public List<Object[]> getExcelAttendanceDepsList(
            AttendanceDetailed object);

    /**
     * 根据部门、月份查询对应部门人员
     * @param month
     * @param depname
     * @return
     */
    public String[] getUserNamesHeaders(String month, String depname);

    /**
     * 根据
     * @param bean
     * @return
     * @throws ParseException 
     */
    public List<List<String>> getExcelAttendanceDateList(String month,String depname) throws ParseException;
    
    
    /**
     * 根据姓名跟日期查询
     */
    public String findNumByUnameAndWorkdate(String uname,String workdate);

}
