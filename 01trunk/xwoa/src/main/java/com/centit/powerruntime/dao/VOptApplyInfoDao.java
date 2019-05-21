package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.VOptApplyInfo;

public class VOptApplyInfoDao extends BaseDaoImpl<VOptApplyInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VOptApplyInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("orgcode", CodeBook.EQUAL_HQL_ID);


            filterField.put("applyDate",
                    "applyDate like to_date(?,'yyyy-mm-dd')");

            filterField.put("proposerName", CodeBook.LIKE_HQL_ID);

            filterField.put("applyItem", CodeBook.LIKE_HQL_ID);

            filterField.put("applyReason", CodeBook.LIKE_HQL_ID);

            filterField.put("applyWay", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerType", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerPaperType", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerPaperCode", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerMobile", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerAddr", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerZipcode", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerEmail", CodeBook.LIKE_HQL_ID);

            filterField.put("proposerUnitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("agentName", CodeBook.LIKE_HQL_ID);

            filterField.put("agentPaperType", CodeBook.LIKE_HQL_ID);

            filterField.put("agentPaperCode", CodeBook.LIKE_HQL_ID);

            filterField.put("agentPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("agentMobile", CodeBook.LIKE_HQL_ID);

            filterField.put("agentAddr", CodeBook.LIKE_HQL_ID);

            filterField.put("agentZipcode", CodeBook.LIKE_HQL_ID);

            filterField.put("agentEmail", CodeBook.LIKE_HQL_ID);

            filterField.put("agentUnitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("applyMemo", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptDate",
                    "acceptDate like to_date(?,'yyyy-mm-dd')");

            filterField.put("auditingDate",
                    "auditingDate like to_date(?,'yyyy-mm-dd')");

            filterField.put("headUsercode", CodeBook.LIKE_HQL_ID);

            filterField.put("headAuditingIdea", CodeBook.LIKE_HQL_ID);

            filterField.put("checkIdeaDate", CodeBook.LIKE_HQL_ID);

            filterField.put("checkUsercode", CodeBook.LIKE_HQL_ID);

            filterField.put("checkIdea", CodeBook.LIKE_HQL_ID);

            filterField.put("checkDetail", CodeBook.LIKE_HQL_ID);

            filterField.put("checkAddr", CodeBook.LIKE_HQL_ID);

            filterField.put("checkDate", CodeBook.LIKE_HQL_ID);

            filterField.put("checkMemo", CodeBook.LIKE_HQL_ID);

            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);

            filterField.put("apply_Way", CodeBook.LIKE_HQL_ID);
            
            filterField.put("proposer_Name", CodeBook.LIKE_HQL_ID);
            
            filterField.put("proposer_Unitcode", CodeBook.LIKE_HQL_ID);
            
            filterField.put("NP_itemType", " item_type <>'XK' ");
            
            filterField.put("itemtype", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("bizstate", CodeBook.EQUAL_HQL_ID);
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " book_date desc");
            filterField.put("begincreateDate", " apply_date>= to_date(?, 'yyyy-mm-dd') ");
            filterField.put("endcreateDate", " apply_date< to_date(?, 'yyyy-mm-dd')+1 ");
            /*===by dk 2016-01-14=====*/
            filterField.put("powername", CodeBook.LIKE_HQL_ID);
            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);
            /*==========================*/
            
        }
        return filterField;
    }
    //事项查看
    @SuppressWarnings("unchecked")
    public List<VOptApplyInfo> listOptApplyInfo(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        
        String shql = "from v_opt_apply_info where 1=1 ";
        
        String params = (String)filterMap.get("params");
        if(StringUtils.isNotBlank(params)){
            shql += " and dj_id not in ("+params+")";
        }
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        
      /*  String queryUnderUnit = "";
        if ("true".equals(filterMap.get("queryUnderUnit"))) {
            queryUnderUnit = " and ( orgcode=" +HQLUtils.buildHqlStringForSQL((String)filterMap.get("orgcode"))+" or orgcode in ( select unitcode from f_unitinfo connect by prior unitcode = parentunit start with unitcode= "
                    + HQLUtils.buildHqlStringForSQL((String)filterMap.get("orgcode")) + " )) ";
        }
        
        String hql1 = "select *  " + hql.getHql() + queryUnderUnit;
        String hql2 = "select count(*)  " + hql.getHql() + queryUnderUnit;*/
        
        
        
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by book_date desc";//排序
        
        if ("true".equals(filterMap.get("queryUnderUnit"))) {
            if(null!=filterMap.get("unitcode")&&StringUtils.isNotBlank((String)filterMap.get("unitcode"))){
              String  unitcode=(String)filterMap.get("unitcode");
              queryUnderUnit = "  ( unitcode=" +HQLUtils.buildHqlStringForSQL(unitcode)+" or unitcode in ( select unitcode from f_unitinfo connect by prior unitcode = parentunit start with unitcode= "
                      + HQLUtils.buildHqlStringForSQL(unitcode) + " )) ";//下级部门
              queryUsercode="and usercode in ( select usercode from F_Userunit  where unitcode in(select unitcode from f_unitinfo where  " +queryUnderUnit+
                    " ))";//用户编码
            }
        }
        
        //去除默认排序
        if(StringUtils.contains(hql.getHql(), "order by  book_date desc")){
            hql.setHql(hql.getHql().replace("order by  book_date desc", ""));
          
        }
        //warntype 视图修改字段时 需要同时修改这边的字段
         String hql1 = "select distinct dj_id,usercode ,itemtype,apply_date,transaffairname,book_date," +
                "transaffairno,orgcode,orgname,powerid,powername,biztype,bizstate,apply_item_type,apply_reason,apply_way,proposer_type,proposer_paper_type,proposer_paper_code,proposer_phone,proposer_mobile,proposer_addr," +
                "proposer_zipcode,proposer_email,proposer_unitcode,agent_name,agent_paper_type,agent_paper_code,agent_phone,agent_mobile,agent_addr,agent_zipcode,agent_email,agent_unitcode," +
                "apply_memo,accept_date,auditing_date,head_usercode,head_auditing_idea,check_idea_date,check_usercode,check_idea,check_detail,check_addr,proposer_name," +
                "check_date,check_memo " + hql.getHql() + queryUsercode+orderBy;
       
       
        String hql2 = "select count(distinct dj_id )  " + hql.getHql() + queryUsercode+orderBy;
        
        
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<VOptApplyInfo> l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), startPos,
                            maxSize, VOptApplyInfo.class));
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return l;
    }
    
    
    
}
