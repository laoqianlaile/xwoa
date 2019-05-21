package com.centit.attendance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.attendance.po.AttendanceDetailed;
import com.centit.core.dao.HQLUtils;

public class AttendanceDetailedDao extends BaseDaoImpl<AttendanceDetailed> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(AttendanceDetailedDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djid", CodeBook.EQUAL_HQL_ID);

            filterField.put("createdate", CodeBook.LIKE_HQL_ID);

            filterField.put("usercode", CodeBook.LIKE_HQL_ID);

            filterField.put("username", CodeBook.LIKE_HQL_ID);

            filterField.put("unitcount", CodeBook.LIKE_HQL_ID);

            filterField.put("unitname", CodeBook.LIKE_HQL_ID);

            filterField.put("workdate", CodeBook.LIKE_HQL_ID);
            
           /* filterField.put("workdate"," workdate like to_date(?,'yyyy-mm') ");*/

            filterField.put("saattendancetime", CodeBook.LIKE_HQL_ID);

            filterField.put("xaattendancetime", CodeBook.LIKE_HQL_ID);

            filterField.put("latein", CodeBook.LIKE_HQL_ID);

            filterField.put("earlyout", CodeBook.LIKE_HQL_ID);

            filterField.put("overtimehours", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    public List<Object[]> getExcelAttendanceDepsList(String month) {
        String shql = "select distinct t.unitname,n.unitorder "
                + "from OA_ATTENDANCE_DETAILED t "
                + "left join v_user_units n on t.unitname=n.unitname "
                + "where 1=1 and t.workdate like'%"
                + HQLUtils.buildHqlStringForSQL(month).replace("\'", "")
                + "%' order by  n.unitorder asc";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(shql);

        return l;
    }

    
    /**
     * 根据部门、月份查询对应部门人员
     * @param month
     * @param depname
     * @return
     */
    public List<Object[]> getUserNamesHeaders(String month, String depname) {
        String shql = "select distinct  t.unitname,trim(t.username),n.userorder "
                + "from OA_ATTENDANCE_DETAILED t "
                + "left join v_user_units n on trim(t.username)=n.username "
                + "where 1=1 and t.workdate like'%"
                + HQLUtils.buildHqlStringForSQL(month).replace("\'", "")
                + "%' and t.unitname="
                + HQLUtils.buildHqlStringForSQL(depname)
                + " order by  n.userorder asc";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(shql);
        return l;
    }
    /**
     * 获取同一天，同部门人员上下班时间
     * @param day
     * @param depname
     * @return
     */
    public List<Object[]> getAttendanceDays(String day, String depname) {
        String shql = "select distinct  t.unitname,t.amtime, "
                + "t.pmtime,n.userorder "
                + "from OA_ATTENDANCE_DETAILED t "
                + "left join v_user_units n on t.username=n.username "
                + "where 1=1 and t.workdate like'%"
                + HQLUtils.buildHqlStringForSQL(day).replace("\'", "")
                + "%' and t.unitname="
                + HQLUtils.buildHqlStringForSQL(depname)
                + " order by  n.userorder asc";
        System.out.println("==========SQL:"+shql);
        List<Object[]> l = (List<Object[]>) findObjectsBySql(shql);
        return l;
    }
    /**
     * 获取某月的天数
     * @param month
     * @param depname
     * @return
     */
    public List<Object[]> getDaysOfMonthByDep(String month, String depname) {
        String shql = "select  distinct t.workdate ,t.unitname   "
                + " from OA_ATTENDANCE_DETAILED t  where 1 = 1   "
                + " and t.workdate like'%"
                + HQLUtils.buildHqlStringForSQL(month).replace("\'", "")
                + "%' and t.unitname ="
                + HQLUtils.buildHqlStringForSQL(depname)
                + " order by  t.workdate asc";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(shql);
        return l;
    }
    
    /**
     * 根据用户编号获取部门名称
     */
    public String findUnitnameByUcode(String ucode){
        StringBuffer sb = new StringBuffer();
        sb.append("select n.unitname from f_unitinfo n left join f_userunit un on un.unitcode = n.unitcode left join f_userinfo u on u.usercode = un.usercode where  u.usercode='"+ucode+"' and un.isprimary='T'");
        @SuppressWarnings("unchecked")
        List<Object[]> list = (List<Object[]>)this.findObjectsBySql(sb.toString());
        String unitname=String.valueOf(list.get(0));   
        return unitname;
    }
    
    /**
     * 根据姓名跟日期查询
     */
    public String findNumByUnameAndWorkdate(String uname,String workdate){
        StringBuffer sb = new StringBuffer();
        sb.append("select a.djid from oa_attendance_detailed a where a.username='"+uname+"'and a.workdate='"+workdate+"'");
        @SuppressWarnings("unchecked")
        List<Object[]> list = (List<Object[]>)this.findObjectsBySql(sb.toString());
        String djid = null;
        if(list.size()>0){
          djid=String.valueOf(list.get(0));
        }
        return djid;
    }
    
}
