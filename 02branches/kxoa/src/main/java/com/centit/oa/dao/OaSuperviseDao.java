package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VoadDcdbNum;
import com.centit.oa.po.OaSupervise;

public class OaSuperviseDao extends BaseDaoImpl<OaSupervise> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaSuperviseDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.LIKE_HQL_ID);

            filterField.put("supDjid", CodeBook.LIKE_HQL_ID);

            filterField.put("nodecode", CodeBook.LIKE_HQL_ID);

            filterField.put("title", CodeBook.LIKE_HQL_ID);

            filterField.put("superviseType", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

            filterField.put("creatertime", CodeBook.LIKE_HQL_ID);

            filterField.put("creater", CodeBook.LIKE_HQL_ID);

            filterField.put("superviseDepno", CodeBook.LIKE_HQL_ID);

            filterField.put("limittime", CodeBook.LIKE_HQL_ID);

            filterField.put("replayRemark", CodeBook.LIKE_HQL_ID);

            filterField.put("replayDate", CodeBook.LIKE_HQL_ID);

            filterField.put("replayDepno", CodeBook.LIKE_HQL_ID);

            filterField.put("replayUser", CodeBook.LIKE_HQL_ID);

            filterField.put("superviseUsers", CodeBook.LIKE_HQL_ID);

            filterField.put("feedbackRemark", CodeBook.LIKE_HQL_ID);

            filterField.put("feedbackDate", CodeBook.LIKE_HQL_ID);

            filterField.put("feedbacker", CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifytime", CodeBook.LIKE_HQL_ID);

            filterField.put("state", CodeBook.LIKE_HQL_ID);

            filterField.put("flowInstId", CodeBook.LIKE_HQL_ID);

            filterField.put("bizstate", CodeBook.LIKE_HQL_ID);

            filterField.put("biztype", CodeBook.LIKE_HQL_ID);

            filterField.put("solvetime", CodeBook.LIKE_HQL_ID);

            filterField.put("solvenote", CodeBook.LIKE_HQL_ID);

            filterField.put("optid", CodeBook.LIKE_HQL_ID);

            filterField.put("flowcode", CodeBook.LIKE_HQL_ID);
            filterField.put("begTime", " creatertime>= to_date(?, 'yyyy-mm-dd') ");

            filterField.put("endTime", " creatertime<= to_date(?, 'yyyy-mm-dd')+1 ");

            filterField.put(CodeBook.ORDER_BY_HQL_ID, "creatertime desc");

            filterField.put("supDjidEq", " supdjid= ? ");
        }
        return filterField;
    }

    @SuppressWarnings("unchecked")
    public List<VoadDcdbNum> getdcdbnum(String supdjid) {

        String sql = "select * from v_oa_dcdbnum where dj_id='" + supdjid
                + "' ";

        return ((List<VoadDcdbNum>) this.findObjectsBySql(sql,
                VoadDcdbNum.class));
    }

    public List<OaSupervise> getSuplist(String state) {
        String shql = " from OaSupervise where state=?  ";
        Object[] objects = new Object[] { state };
        List<OaSupervise> list = this.listObjects(shql, objects);
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<OaSupervise> listOaSupervise(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        String shql = "from OA_SUPERVISE where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by creatertime desc";//排序
        
        if ("true".equals(filterMap.get("queryUnderUnit"))) {
            if(null!=filterMap.get("unitcode")&&StringUtils.isNotBlank((String)filterMap.get("unitcode"))){
              String  unitcode=(String)filterMap.get("unitcode");
              queryUnderUnit = "  ( unitcode=" +HQLUtils.buildHqlStringForSQL(unitcode)+" or unitcode in ( select unitcode from f_unitinfo connect by prior unitcode = parentunit start with unitcode= "
                      + HQLUtils.buildHqlStringForSQL(unitcode) + " )) ";//下级部门
              queryUsercode="and creater in ( select usercode from F_Userunit  where unitcode in(select unitcode from f_unitinfo where  " +queryUnderUnit+
                    " ))";//用户编码
            }
        }
        
        //去除默认排序
        if(StringUtils.contains(hql.getHql(), "order by creatertime desc")){
            hql.setHql(hql.getHql().replace("order by creatertime desc", ""));
          
        }
        
        String hql1 = "select * " + hql.getHql() + queryUsercode+orderBy;
       
       
        String hql2 = "select count(*)  " + hql.getHql() + queryUsercode+orderBy;
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<OaSupervise>  l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), startPos,
                            maxSize, OaSupervise.class));
            
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    
    }
}
