package com.centit.dispatchdoc.dao;

import java.text.SimpleDateFormat;
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
import com.centit.dispatchdoc.po.VDispatchDocList;

public class VDispatchDocListDao extends BaseDaoImpl<VDispatchDocList> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VDispatchDocListDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.LIKE_HQL_ID);

            filterField.put("itemId", CodeBook.LIKE_HQL_ID);

            filterField.put("dispatchDocNo", CodeBook.LIKE_HQL_ID);

            filterField.put("dispatchDocTitle", CodeBook.LIKE_HQL_ID);

            filterField.put("dispatchFileType", CodeBook.LIKE_HQL_ID);

            filterField.put("dispatchDocType", CodeBook.LIKE_HQL_ID);

            filterField.put("dispatchCanOpen", CodeBook.LIKE_HQL_ID);

            filterField.put("dispatchOpenType", CodeBook.LIKE_HQL_ID);

            filterField.put("isUnionDispatch", CodeBook.LIKE_HQL_ID);

            filterField.put("unionOthers", CodeBook.LIKE_HQL_ID);

            filterField.put("isCountersign", CodeBook.LIKE_HQL_ID);

            filterField.put("dispatchDocSummary", CodeBook.LIKE_HQL_ID);

            filterField.put("draftDate", CodeBook.LIKE_HQL_ID);

            filterField.put("optUnitName", CodeBook.LIKE_HQL_ID);

            filterField.put("draftUserName", CodeBook.LIKE_HQL_ID);

            filterField.put("secretsDegree", CodeBook.LIKE_HQL_ID);

            filterField.put("dispatchCopies", CodeBook.LIKE_HQL_ID);

            filterField.put("mainNotifyUnit", CodeBook.LIKE_HQL_ID);

            filterField.put("otherUnits", CodeBook.LIKE_HQL_ID);

            filterField.put("createDate", CodeBook.LIKE_HQL_ID);

            filterField.put("updateDate", CodeBook.LIKE_HQL_ID);

            filterField.put("criticalLevel", CodeBook.LIKE_HQL_ID);

            filterField.put("emergencyDegree", CodeBook.LIKE_HQL_ID);

            filterField.put("commissionCanOpen", CodeBook.LIKE_HQL_ID);

            filterField.put("unionDispatchUnits", CodeBook.LIKE_HQL_ID);

            filterField.put("recordId", CodeBook.LIKE_HQL_ID);

            filterField.put("optId", CodeBook.LIKE_HQL_ID);

            filterField.put("flowCode", CodeBook.LIKE_HQL_ID);

            filterField.put("flowInstId", CodeBook.LIKE_HQL_ID);

            filterField.put("transAffairNo", CodeBook.LIKE_HQL_ID);

            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);

            filterField.put("transAffairQueryKey", CodeBook.LIKE_HQL_ID);

            filterField.put("bizstate", CodeBook.EQUAL_HQL_ID);

            filterField.put("biztype", CodeBook.LIKE_HQL_ID);

            filterField.put("orgcode", CodeBook.LIKE_HQL_ID);

            filterField.put("orgname", CodeBook.LIKE_HQL_ID);

            filterField.put("headunitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("headusercode", CodeBook.LIKE_HQL_ID);

            filterField.put("powerid", CodeBook.LIKE_HQL_ID);

            filterField.put("powername", CodeBook.LIKE_HQL_ID);

            filterField.put("solvestatus", CodeBook.LIKE_HQL_ID);

            filterField.put("solvetime", CodeBook.LIKE_HQL_ID);

            filterField.put("solvenote", CodeBook.LIKE_HQL_ID);

            filterField.put("usercode", CodeBook.LIKE_HQL_ID);

            filterField.put("createdate", CodeBook.LIKE_HQL_ID);

            filterField.put("sendArchiveNo", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptArchiveNo", CodeBook.LIKE_HQL_ID);
            /*
            filterField.put("riskType", CodeBook.LIKE_HQL_ID);

            filterField.put("riskDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("riskResult", CodeBook.LIKE_HQL_ID);*/

            filterField.put("instState", CodeBook.LIKE_HQL_ID);
            filterField.put("begTime", " createdate>= to_date(?, 'yyyy-mm-dd') ");
            filterField.put("endTime", " createdate< to_date(?, 'yyyy-mm-dd')+1 ");

            //默认发文更新时间排序
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " update_date desc");
            
            filterField.put("unitcode", CodeBook.LIKE_HQL_ID);
            
            filterField.put("other_units", CodeBook.LIKE_HQL_ID);
            filterField.put("main_Notify_Unit", CodeBook.LIKE_HQL_ID);
            filterField.put("dispatch_doc_no", CodeBook.LIKE_HQL_ID);
        }
        return filterField;
    }
    /**
     * 发文列表
     * @param filterMap
     * @param pageDesc
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<VDispatchDocList> listDispatchDoc(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        String shql = "from V_OA_DISPATCH_DOC_LIST where 1=1 ";
        if(StringUtils.isNotBlank((String)filterMap.get("dispatchDocNo"))){
            filterMap.put("dispatch_doc_no", (String)filterMap.get("dispatchDocNo"));
            filterMap.remove("dispatchDocNo");
        }
        if(StringUtils.isNotBlank((String)filterMap.get("otherUnits"))){
            filterMap.put("other_units", (String)filterMap.get("otherUnits"));
            filterMap.remove("otherUnits");
        }
        if(StringUtils.isNotBlank((String)filterMap.get("mainNotifyUnit"))){
            filterMap.put("main_Notify_Unit", (String)filterMap.get("mainNotifyUnit"));
            filterMap.remove("mainNotifyUnit");
        }
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by update_date desc";//排序
        
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
        if(StringUtils.contains(hql.getHql(), "order by  update_date desc")){
            hql.setHql(hql.getHql().replace("order by  update_date desc", ""));
          
        }
        
        String hql1 = "select distinct no,'1' as usercode,item_id,dispatch_doc_no,dispatch_doc_title," +
                "dispatch_file_type,dispatch_doc_type,dispatch_can_open,dispatch_open_type,is_union_dispatch," +
                "union_others,is_countersign,dispatch_doc_summary,draft_date,opt_unit_name,draft_user_name," +
                "secrets_degree,dispatch_copies,main_notify_unit,other_units,create_date,update_date," +
                "critical_level,emergency_degree,commission_can_open,union_dispatch_units,recordid,optid," +
                "flowinstid,flowcode,transaffairno,transaffairname,transaffairquerykey,bizstate,biztype," +
                "orgcode,orgname,headunitcode,headusercode,powerid,powername,solvestatus,solvetime," +
                "solvenote,createdate,sendarchiveno,acceptarchiveno,dispatch_user,nodename,bizusernames,warntype,'1' as unitcode " + hql.getHql() + queryUsercode+orderBy;
       
       
        String hql2 = "select count(distinct no )  " + hql.getHql() + queryUsercode+orderBy;
        
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<VDispatchDocList>  l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), startPos,
                            maxSize, VDispatchDocList.class));
            
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(StringUtils.isNotBlank((String)filterMap.get("dispatch_doc_no"))){
            filterMap.put("dispatchDocNo", (String)filterMap.get("dispatch_doc_no"));
            filterMap.remove("dispatch_doc_no");
        }
        if(StringUtils.isNotBlank((String)filterMap.get("other_units"))){
            filterMap.put("otherunits", (String)filterMap.get("other_units"));
            filterMap.remove("other_units");
        }
        if(StringUtils.isNotBlank((String)filterMap.get("main_Notify_Unit"))){
            filterMap.put("mainNotifyUnit", (String)filterMap.get("main_Notify_Unit"));
            filterMap.remove("main_Notify_Unit");
        }
        return l;
    } 
    
    @SuppressWarnings("unchecked")
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId){
        String shql = "from V_OA_DISPATCH_DOC_LIST where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by update_date desc";//排序
        
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
        if(StringUtils.contains(hql.getHql(), "order by  update_date desc")){
            hql.setHql(hql.getHql().replace("order by  update_date desc", ""));
          
        }
        if(StringUtils.contains(hql.getHql(), "dispatchDocNo")){
            hql.setHql(hql.getHql().replace("dispatchDocNo", "dispatch_doc_no"));
          
        }
        
        String hql1 = "select distinct no,'1' as usercode,item_id,dispatch_doc_no,dispatch_doc_title," +
                "dispatch_file_type,dispatch_doc_type,dispatch_can_open,dispatch_open_type,is_union_dispatch," +
                "union_others,is_countersign,dispatch_doc_summary,draft_date,opt_unit_name,draft_user_name," +
                "secrets_degree,dispatch_copies,main_notify_unit,other_units,create_date,update_date," +
                "critical_level,emergency_degree,commission_can_open,union_dispatch_units,recordid,optid," +
                "flowinstid,flowcode,transaffairno,transaffairname,transaffairquerykey,bizstate,biztype," +
                "orgcode,orgname,headunitcode,headusercode,powerid,powername,solvestatus,solvetime," +
                "solvenote,createdate,sendarchiveno,acceptarchiveno,dispatch_user,nodename,bizusernames,'1' as unitcode " + hql.getHql() + queryUsercode + orderBy;
        
      //添加上一项 和下一项
        StringBuffer sql = new StringBuffer("with temp as(");
        sql.append(hql1)
           .append(")")
           .append("select p.no prevno,n.no nextno")
           .append(" from (select rownum rn,temp.* from temp) c")
           .append(" left join (select rownum rn,temp.no from temp) p on p.rn = c.rn-1")
           .append(" left join (select rownum rn,temp.no from temp) n on n.rn = c.rn+1")
           .append(" where c.no="+HQLUtils.buildHqlStringForSQL(currDjId))
           .append(" order by c.rn");
        List<Object[]>  l = getHibernateTemplate().executeFind(
                new SQLQueryCallBack(sql.toString(),hql.getParams()));
        List<String> res = new ArrayList<String>();
        if(l!=null&&l.size()>0){
            res.add(l.get(0)[0]==null?null : l.get(0)[0].toString());
            res.add(l.get(0)[1]==null?null :l.get(0)[1].toString());
        }
       return res;
    }
    @SuppressWarnings("unchecked")
    public List<VDispatchDocList> listDispatchDocV2(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        String shql = " from V_OA_DISPATCH_DOC_LIST where 1=1 ";
        //HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String orderBy=" order by draft_date desc";//排序
        String unitcode="";//部门编码
        String bizstate="";
        //去除默认排序
        /*if(StringUtils.contains(hql.getHql(), "order by  update_date desc")){
            hql.setHql(hql.getHql().replace("order by  update_date desc", ""));
          
        }*/
        StringBuffer hql1=new StringBuffer();
        StringBuffer hql2=new StringBuffer();
         hql1.append( "select distinct no as djId,'1' as usercode,dispatch_doc_no as dispatchDocNo,dispatch_doc_title as dispatchDocTitle," +
               "create_date as createDate,update_date as updateDate," +
                "flowinstid as flowInstId,flowcode as flowCode,transaffairname,bizstate,biztype," +
                "solvenote,createdate,draft_date as draftDate");
       
         hql2.append("select count(distinct no )  from (");
        if(null!=filterMap.get("unitcode")&&StringUtils.isNotBlank((String)filterMap.get("unitcode"))){
            unitcode=" and unitcode="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("unitcode"));
            filterMap.remove("unitcode");
            hql1.append(",unitcode ");
        }else{
            hql1.append(",'1' as unitcode ");
        }
        if(null!=filterMap.get("bizstate")&&StringUtils.isNotBlank((String)filterMap.get("bizstate"))){
            bizstate=" and bizstate="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("bizstate"));
            filterMap.remove("bizstate");
        }
        hql1.append(shql);
        hql1.append(unitcode);
        hql1.append(bizstate);
        hql1.append(orderBy);
        hql2.append(hql1);
        hql2.append(")");
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<VDispatchDocList>  l = new ArrayList<VDispatchDocList>();
        try {
            List<Object[]> t= (List<Object[]>) findObjectsBySql(hql1.toString());
            if(t!=null&&t.size()>0){
                for(Object [] o:t){
                    VDispatchDocList v=new VDispatchDocList();
                    v.setDjId(o[0]!=null?o[0].toString():"");
                    v.setDispatchDocNo(o[2]!=null?o[2].toString():"");
                    v.setDispatchDocTitle(o[3]!=null?o[3].toString():"");
                    v.setCreateDate(o[4]!=null?new SimpleDateFormat("yyyy-MM-dd").parse(o[4].toString()):null);
                    v.setUpdateDate(o[5]!=null?new SimpleDateFormat("yyyy-MM-dd").parse(o[5].toString()):null);
                    v.setFlowInstId(o[6]!=null?Long.parseLong(o[6].toString()):0);
                    v.setFlowCode(o[7]!=null?o[7].toString():"");
                    v.setTransaffairname(o[8]!=null?o[8].toString():"");
                    v.setBizstate(o[9]!=null?o[9].toString():"");
                    v.setBiztype(o[10]!=null?o[10].toString():"");
                    v.setSolvenote(o[11]!=null?o[11].toString():"");
                    v.setCreatedate(o[12]!=null?new SimpleDateFormat("yyyy-MM-dd").parse(o[12].toString()):null);
                    v.setDraftDate(o[13]!=null?new SimpleDateFormat("yyyy-MM-dd").parse(o[13].toString()):null);
                    v.setUnitcode(o[14]!=null?o[14].toString():"");
                    l.add(v);
                }
            }
            /*l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1.toString(), hql.getParams(), startPos,
                            maxSize, VDispatchDocList.class));*/
            pageDesc.setTotalRows(l.size());
            /*pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2.toString(), hql.getParams()))
                    .get(0).toString()));*/
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    }
    
    /**
     * 发文列表(去除分页)
     * @param filterMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<VDispatchDocList> listDispatchDocForExcel(
            Map<String, Object> filterMap) {
        String shql = "from V_OA_DISPATCH_DOC_LIST where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by update_date desc";//排序
        
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
        if(StringUtils.contains(hql.getHql(), "order by  update_date desc")){
            hql.setHql(hql.getHql().replace("order by  update_date desc", ""));
          
        }
        
        String hql1 = "select distinct no,'1' as usercode,item_id,dispatch_doc_no,dispatch_doc_title," +
                "dispatch_file_type,dispatch_doc_type,dispatch_can_open,dispatch_open_type,is_union_dispatch," +
                "union_others,is_countersign,dispatch_doc_summary,draft_date,opt_unit_name,draft_user_name," +
                "secrets_degree,dispatch_copies,main_notify_unit,other_units,create_date,update_date," +
                "critical_level,emergency_degree,commission_can_open,union_dispatch_units,recordid,optid," +
                "flowinstid,flowcode,transaffairno,transaffairname,transaffairquerykey,bizstate,biztype," +
                "orgcode,orgname,headunitcode,headusercode,powerid,powername,solvestatus,solvetime," +
                "solvenote,createdate,sendarchiveno,acceptarchiveno,dispatch_user,nodename,bizusernames,warntype,'1' as unitcode " + hql.getHql() + queryUsercode+orderBy;
       
        List<VDispatchDocList>  l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), VDispatchDocList.class));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    } 
    
}
