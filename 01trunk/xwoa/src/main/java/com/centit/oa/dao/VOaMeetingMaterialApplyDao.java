package com.centit.oa.dao;

import java.util.ArrayList;
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
import com.centit.oa.po.OaMeetingApply;
import com.centit.oa.po.VOaMeetingMaterialApply;
import com.centit.oa.po.VOptBaseList;
import com.centit.powerruntime.po.VOptApplyInfo;

public class VOaMeetingMaterialApplyDao extends BaseDaoImpl<VOaMeetingMaterialApply>
    {
        private static final long serialVersionUID = 1L;
        public static final Log log = LogFactory.getLog(VOaMeetingMaterialApplyDao.class);
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();
              filterField.put("mId", CodeBook.EQUAL_HQL_ID);
              filterField.put("djId", CodeBook.EQUAL_HQL_ID);
              filterField.put("meeting_Attendeescodes", CodeBook.LIKE_HQL_ID);//参会人员usercode
              filterField.put("meeting_Attendees", CodeBook.LIKE_HQL_ID);//参会人员姓名
              filterField.put("meetApplyName", CodeBook.LIKE_HQL_ID);//会议主题
              filterField.put("attendLeaderName", CodeBook.LIKE_HQL_ID);//参会领导
              filterField.put("attendLeaderCode", CodeBook.LIKE_HQL_ID);
              filterField.put("meetApplyTime", CodeBook.EQUAL_HQL_ID);
        }
        return filterField;
    }
    /**
     * 获取我的会议
     * @param filterMap
     * @param pageDesc
     * @return
     */
     @SuppressWarnings("unchecked")
     public List<OaMeetingApply> oaMeetingList(Map<String,Object> filterMap, PageDesc pageDesc){
        String shql = " from V_OA_MEETINGMATERIAL_APPLY where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String orderBy="";//排序
        String hql1 = " select distinct mId,meetApplyName ,meetApplyAddress,attendLeaderCode,attendLeaderName, " +
                      " attendUnitCode,attendUnitName,creater,createtime,motifytime, " +
                      " meetApplytime,foreigUserName,foreigUnitName,meetingRemark,releteCode,releteName "
                      + hql.getHql() +orderBy;
        String hql2 = "select count(distinct mId )  " + hql.getHql() +orderBy;
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();
        List<OaMeetingApply> l = null;
        try {
            l = getHibernateTemplate().executeFind(new SQLQueryCallBack(hql1, hql.getParams(), startPos,maxSize, OaMeetingApply.class));
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate().executeFind(new SQLQueryCallBack(hql2, hql.getParams())).get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
     }
}
