package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VOaMeetApplyList;

public class VMeetApplyListDao extends BaseDaoImpl<VOaMeetApplyList> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            
            filterField.put("biztype" , CodeBook.EQUAL_HQL_ID);
            filterField.put("apply_no" , CodeBook.LIKE_HQL_ID);
            filterField.put("depno" , CodeBook.EQUAL_HQL_ID);
            filterField.put("meeting_no" , CodeBook.EQUAL_HQL_ID);
            filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);
            filterField.put("unitcode" , CodeBook.EQUAL_HQL_ID);
            filterField.put("state" , CodeBook.EQUAL_HQL_ID);
            filterField.put("begTime", " APPLYTIME>= to_date(?, 'yyyy-mm-dd') ");
            filterField.put("endTime", " APPLYTIME< to_date(?, 'yyyy-mm-dd')+1 ");
        }
        return filterField;
    }
    
    @SuppressWarnings("unchecked")
    public List<VOaMeetApplyList> listMeetApply(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        String shql = " FROM V_OA_MEET_APPLY_LIST  WHERE 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String orderBy = " ORDER BY APPLYTIME DESC";
        String hql1 = "SELECT DISTINCT DJID,APPLY_NO,TITLE,APPLYTIME,MEETING_NO,DO_BEG_TIME,DO_END_TIME,PLAN_BEG_TIME,PLAN_END_TIME,"
                + "TELEPHONE,MEETING_PERSIONS_NUM,STATE,USERCODE,UNITCODE,OPTID,FLOWINSTID,"
                + "FLOWCODE,TRANSAFFAIRNAME,BIZSTATE,POWERID,POWERNAME,SOLVESTATUS,SOLVETIME,SOLVENOTE,ACCEPTARCHIVENO,"
                + "BIZTYPE,OPERTIME" + hql.getHql()+orderBy;
       
       
        String hql2 = "SELECT COUNT(DISTINCT DJID )  " + hql.getHql();
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<VOaMeetApplyList>  l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1,hql.getParams(),startPos,
                            maxSize,VOaMeetApplyList.class));
            
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    }  
    
}
