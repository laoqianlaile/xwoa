package com.centit.dispatchdoc.dao;

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
import com.centit.dispatchdoc.po.VIncomeDocList;

public class VIncomeDocListDao extends BaseDaoImpl<VIncomeDocList> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VIncomeDocListDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDocNo", CodeBook.LIKE_HQL_ID);
            
            filterField.put("income_Doc_No", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDocTitle", CodeBook.LIKE_HQL_ID);
            
            filterField.put("income_Doc_Title", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDeptType", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDeptName", CodeBook.LIKE_HQL_ID);
            
            filterField.put("income_Dept_Name", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDate", CodeBook.LIKE_HQL_ID);

            filterField.put("secretDegree", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDocFileName", CodeBook.LIKE_HQL_ID);

            filterField.put("operateState", CodeBook.LIKE_HQL_ID);

            filterField.put("createDate", CodeBook.LIKE_HQL_ID);

            filterField.put("updateDate", CodeBook.LIKE_HQL_ID);

            filterField.put("optId", CodeBook.LIKE_HQL_ID);

            filterField.put("flowCode", CodeBook.LIKE_HQL_ID);

            filterField.put("flowInstId", CodeBook.LIKE_HQL_ID);

            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);

            filterField.put("bizstate", CodeBook.EQUAL_HQL_ID);
            filterField.put("NPbizstate", " bizstate !=? ");

            filterField.put("biztype", CodeBook.LIKE_HQL_ID);

            filterField.put("orgcode", CodeBook.LIKE_HQL_ID);

            filterField.put("orgname", CodeBook.LIKE_HQL_ID);

            filterField.put("powerid", CodeBook.LIKE_HQL_ID);

            filterField.put("powername", CodeBook.LIKE_HQL_ID);

            filterField.put("solvestatus", CodeBook.LIKE_HQL_ID);

            filterField.put("solvetime", CodeBook.LIKE_HQL_ID);

            filterField.put("solvenote", CodeBook.LIKE_HQL_ID);

            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);

            filterField.put("createdate", CodeBook.LIKE_HQL_ID);

            filterField.put("caseNo", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptArchiveNo", CodeBook.LIKE_HQL_ID);
            filterField.put("begTime", " createdate>= to_date(?, 'yyyy-mm-dd') ");
            filterField.put("endTime", " createdate< to_date(?, 'yyyy-mm-dd')+1 ");
            filterField.put("begUpdateTime", " update_Date>= to_date(?, 'yyyy-mm-dd') ");
            filterField.put("endUpdateTime", " update_Date< to_date(?, 'yyyy-mm-dd')+1 ");
            filterField.put("begincomeDate", " income_Date>= to_date(?, 'yyyy-mm-dd') ");
            filterField.put("endincomeDate", " income_Date< to_date(?, 'yyyy-mm-dd')+1 ");
            
            //默认收文更新时间排序
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " update_date desc");
            
            filterField.put("bizusernames", CodeBook.LIKE_HQL_ID);
            
            filterField.put("belongUnitcode", CodeBook.LIKE_HQL_ID);
            
            filterField.put("username", CodeBook.LIKE_HQL_ID);
            filterField.put("transcontent", CodeBook.LIKE_HQL_ID);
        }
        return filterField;
    }

    public List<VIncomeDocList> getDocRelativeList(String dispatchNo) {
        return super
                .listObjects(" from VIncomeDocList where djId in (select cid.incomeNo from DocRelative where cid.dispatchNo='"
                        + dispatchNo + "')");
    }
    
    //收文查看列表
    @SuppressWarnings("unchecked")
    public List<VIncomeDocList> listIncomeDoc(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        
       
        String shql = "from V_OA_INCOME_DOC_LIST where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by Extract(year from income_date) desc, to_number (substr((substr(acceptarchiveno,(instr(acceptarchiveno,'〕',1,1)+1))),0,(length(substr(acceptarchiveno,(instr(acceptarchiveno,'〕',1,1)+1)))-1))) desc";//排序
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
        
        String hql1 = "select distinct no,'1' as usercode,belongUnitcode," +
                "income_doc_no,income_doc_title,income_dept_type,income_dept_name,critical_level," +
                "income_doc_type,item_source,income_date,secret_degree,operate_state,create_date," +
                "update_date,optid,flowinstid,flowcode,transaffairname,bizstate,biztype," +
                "orgcode,orgname,powerid,powername,solvestatus,solvetime,solvenote," +
                "createdate,acceptarchiveno,nodename,bizusernames,warntype,username,transcontent " + hql.getHql() + queryUsercode+orderBy;
       
       
        String hql2 = "select count(distinct no )  " + hql.getHql() + queryUsercode+orderBy;
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<VIncomeDocList>  l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), startPos,
                            maxSize, VIncomeDocList.class));
            
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    }  
    
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId){

        String shql = "from V_OA_INCOME_DOC_LIST where 1=1 ";
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
        
        String hql1 = "select distinct no,'1' as usercode,belongUnitcode," +
                "income_doc_no,income_doc_title,income_dept_type,income_dept_name,critical_level," +
                "income_doc_type,item_source,income_date,secret_degree,operate_state,create_date," +
                "update_date,optid,flowinstid,flowcode,transaffairname,bizstate,biztype," +
                "orgcode,orgname,powerid,powername,solvestatus,solvetime,solvenote," +
                "createdate,acceptarchiveno,nodename,bizusernames,username " + hql.getHql() + queryUsercode+orderBy;
        
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
    //收文查看列表
    /**
     * unitcode查询单位和部位
     * @param filterMap
     * @param pageDesc
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<VIncomeDocList> listIncomeDocV2(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        
       
        String shql = " from V_OA_INCOME_DOC_LIST where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy=" order by income_date desc";//排序
        StringBuffer hql1=new StringBuffer();
        StringBuffer hql2=new StringBuffer();
        String unitcode="";//部门编码
        //去除默认排序
        if(StringUtils.contains(hql.getHql(), "order by  update_date desc")){
            hql.setHql(hql.getHql().replace(" order by  update_date desc", ""));
          
        }
        hql1.append( "select distinct no,'1' as usercode," +
                "income_doc_no,income_doc_title,income_dept_type,income_dept_name,critical_level," +
                "income_doc_type,item_source,income_date,secret_degree,operate_state,create_date," +
                "update_date,optid,flowinstid,flowcode,transaffairname,bizstate,biztype," +
                "'1' as orgcode,orgname,powerid,powername,solvestatus,solvetime,solvenote," +
                "createdate,acceptarchiveno,nodename,bizusernames,warntype,'1' as username,transcontent");
       
         hql2.append("select count(distinct no )  from (");
         if(null!=filterMap.get("unitcode")&&StringUtils.isNotBlank((String)filterMap.get("unitcode"))){
             unitcode=" and belongUnitcode="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("unitcode"));
             hql1.append(",belongUnitcode ");
         }else{
             hql1.append(",'1' as belongUnitcode ");
         }
         hql1.append(hql.getHql());
         hql1.append(unitcode);
         hql1.append(orderBy);
         hql2.append(hql1);
         hql2.append(")");
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<VIncomeDocList>  l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1.toString(), hql.getParams(), startPos,
                            maxSize, VIncomeDocList.class));
            
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2.toString(), hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    }  
    
    @SuppressWarnings("unchecked")
    public List<String> findNeighbouringDjId2(Map<String, Object> filterMap,String currDjId){

        String shql = "from V_OA_INCOME_DOC_LIST where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String orderBy="order by update_date desc";//排序
        StringBuffer hql1=new StringBuffer();
        String unitcode="";//部门编码
        //去除默认排序
        if(StringUtils.contains(hql.getHql(), "order by  update_date desc")){
            hql.setHql(hql.getHql().replace("order by  update_date desc", ""));
          
        }
        hql1.append( "select distinct no,'1' as usercode," +
                "income_doc_no,income_doc_title,income_dept_type,income_dept_name,critical_level," +
                "income_doc_type,item_source,income_date,secret_degree,operate_state,create_date," +
                "update_date,optid,flowinstid,flowcode,transaffairname,bizstate,biztype," +
                "'1' as orgcode,orgname,powerid,powername,solvestatus,solvetime,solvenote," +
                "createdate,acceptarchiveno,nodename,bizusernames ");
       
         if(null!=filterMap.get("unitcode")&&StringUtils.isNotBlank((String)filterMap.get("unitcode"))){
             unitcode=" and belongUnitcode="+HQLUtils.buildHqlStringForSQL((String)filterMap.get("unitcode"));
             hql1.append(",belongUnitcode ");
         }else{
             hql1.append(",'1' as belongUnitcode ");
         }
         hql1.append(hql.getHql());
         hql1.append(unitcode);
         hql1.append(orderBy);
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
//收文查看列表
    @SuppressWarnings("unchecked")
    public List<VIncomeDocList> listIncomeDocForExcel(
            Map<String, Object> filterMap) {
        
        
        String shql = "from V_OA_INCOME_DOC_LIST where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String queryUnderUnit = "";//下级部门
        String queryUsercode = "";//用户编码
        String orderBy="order by income_date desc";//排序
        
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
        
        String hql1 = "select distinct no,'1' as usercode,belongUnitcode," +
                "income_doc_no,income_doc_title,income_dept_type,income_dept_name,critical_level," +
                "income_doc_type,item_source,income_date,secret_degree,operate_state,create_date," +
                "update_date,optid,flowinstid,flowcode,transaffairname,bizstate,biztype," +
                "orgcode,orgname,powerid,powername,solvestatus,solvetime,solvenote," +
                "createdate,acceptarchiveno,nodename,bizusernames,warntype,username,transcontent " + hql.getHql() + queryUsercode+orderBy;
        
        List<VIncomeDocList>  l = null;
        try {
            
            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), VIncomeDocList.class));
            
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
        
    }  
}
