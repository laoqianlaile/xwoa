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
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetMinutes;
import com.centit.oa.po.VoaMeetMinute;

public class OaMeetMinutesDao  extends BaseDaoImpl<OaMeetMinutes> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaMeetMinutesDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djid", CodeBook.EQUAL_HQL_ID);

            filterField.put("version", CodeBook.EQUAL_HQL_ID);

            filterField.put("title", CodeBook.LIKE_HQL_ID);
/*
            filterField.put("begTime", CodeBook.LIKE_HQL_ID);

            filterField.put("endTime", CodeBook.LIKE_HQL_ID);*/
            filterField.put("begTime" , "BEG_TIME <= to_date(?,'yyyy-mm-dd')");
            filterField.put("endTime" , "END_TIME >= to_date(?,'yyyy-mm-dd')-1");
            filterField.put("meetingHost", CodeBook.LIKE_HQL_ID);

            filterField.put("meetingNo", CodeBook.LIKE_HQL_ID);

            filterField.put("meetingPersions", CodeBook.LIKE_HQL_ID);

            filterField.put("doDepno", CodeBook.LIKE_HQL_ID);

            filterField.put("doCreater", CodeBook.LIKE_HQL_ID);

            filterField.put("docFile", CodeBook.LIKE_HQL_ID);

            filterField.put("docFileName", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

            filterField.put("state", CodeBook.LIKE_HQL_ID);
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " version desc");
            filterField.put("motifyTime", CodeBook.LIKE_HQL_ID);
            filterField.put("ccPersonnel" , CodeBook.LIKE_HQL_ID);
        }
        return filterField;
    }

   
    @SuppressWarnings("unchecked")
    public List<VoaMeetMinute> getMaxlist(Map<String, Object> filterMap,
            PageDesc pageDesc,String userCode) {
        String sql = "SELECT * FROM V_OA_MEET_MINUTES t WHERE 1=1 ";
        if(StringUtils.isNotBlank(userCode)){         
            sql+=" and t.djid in (select distinct r.addrbookid  from oa_address_book_relection r  where r.shareman="+HQLUtils.buildHqlStringForSQL(userCode)+") " ;
        } 
        System.out.println(sql);
        HqlAndParams sqlAndParams = builderHqlAndParams(sql, filterMap);
       /* return (List<OaMeetMinutes>) this.findObjectsBySql(
                sqlAndParams.getHql(), sqlAndParams.getParams(),
                OaMeetMinutes.class);*/
        return (List<VoaMeetMinute>) this.findObjectsBySql(
                sqlAndParams.getHql(), sqlAndParams.getParams(),
                VoaMeetMinute.class);
    }


    @SuppressWarnings("unchecked")
    public List<OaMeetMinutes> getversion(String djId) {
        String sql = "SELECT * FROM oa_meet_minutes t WHERE t.djid='"+djId+"' order by version desc";
     
        return (List<OaMeetMinutes>) this.findObjectsBySql(sql,OaMeetMinutes.class);

    }
}
