package com.centit.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.bbs.po.OaBbsDashboard;

public class OaBbsDashboardDao extends BaseDaoImpl<OaBbsDashboard> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaBbsDashboardDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("layoutcode", CodeBook.EQUAL_HQL_ID);

            filterField.put("unitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("layouttype", CodeBook.EQUAL_HQL_ID);

            filterField.put("orderno", CodeBook.LIKE_HQL_ID);

            filterField.put("creater", CodeBook.LIKE_HQL_ID);

            filterField.put("createtime", CodeBook.LIKE_HQL_ID);

            filterField.put("openType", CodeBook.EQUAL_HQL_ID);

            filterField.put("approvals", CodeBook.LIKE_HQL_ID);

            filterField.put("approvaltime", CodeBook.LIKE_HQL_ID);

            filterField.put("approvalremark", CodeBook.LIKE_HQL_ID);

            filterField.put("starttime", CodeBook.LIKE_HQL_ID);

            filterField.put("endtime", CodeBook.LIKE_HQL_ID);
            
            filterField.put("layoutname", CodeBook.LIKE_HQL_ID);
            
            filterField.put("isdocontral", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("startCreateTime", " createtime >=to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            
            filterField.put("endCreateTime", " createtime <=to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            
            filterField.put("layouttypeFP", "( layouttype='A' or (layouttype ='U' and unitcode=?) )");
            
            filterField.put("excludeStates", "state not in(?)");
			//默认排序 模块类型 本部门U+综合模块A+排序号
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " layouttype desc, orderno asc");

        }
        return filterField;
    }
    
    public String getNextValueOfSequence(){
        
        return super.getNextValueOfSequence("S_OA_BBS_DASHBOARD");
    }

    /**
     * 获取bbs首页统计数据今日发帖、昨日发帖、帖子总数
     * 帖子统计的回复数
     * @return
     */
    public OaBbsDashboard getTotalSum() {
        OaBbsDashboard oaBbsDashboard = new OaBbsDashboard();
        String sql = " select "
                + "(select  count(*)   from OA_LEAVE_MESSAGE t1  "
                + "where "
                + "to_char(t1.creatertime,'yyyy-MM-dd') = to_char(sysdate,'yyyy-MM-dd') "
                + "and t1.info_type='BBS' ) as todayThemeNum , "
                + "(select  count(*)   from OA_LEAVE_MESSAGE t2  "
                + "where "
                + "to_char(t2.creatertime,'yyyy-MM-dd') = to_char(sysdate-1,'yyyy-MM-dd') "
                + "and t2.info_type='BBS') as preThemeNum , "
                + "( select case when sum(t3.postsNum) is null then 0 else sum(t3.postsNum) end  from oa_bbs_discussion t3 )  themeNum "
                + "from dual ";

        List<?> oaBbsDashboardList = findObjectsBySql(sql);
        if (oaBbsDashboardList != null && oaBbsDashboardList.size() > 0) {
            // 这里查询结果为1条，
            for (int i = 0; i < oaBbsDashboardList.size(); i++) {

                Object[] objs = (Object[]) oaBbsDashboardList.get(i);
                oaBbsDashboard.setTodayThemeNum( Long.valueOf(objs[0].toString()));// 今日帖子总数
                oaBbsDashboard.setPreThemeNum(Long.valueOf(objs[1].toString()));// 昨天帖子总数
                oaBbsDashboard.setThemeNum(Long.valueOf(objs[2].toString()));// 帖子总数

            }
        } else {
            // 无统计结果时设置默认值
            oaBbsDashboard.setTodayThemeNum((long) 0);// 今日帖子总数
            oaBbsDashboard.setPreThemeNum((long) 0);// 昨天帖子总数
            oaBbsDashboard.setThemeNum((long) 0);// 帖子总数
        }

        return oaBbsDashboard;

    }
}
