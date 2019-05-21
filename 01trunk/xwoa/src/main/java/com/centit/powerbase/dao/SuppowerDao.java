package com.centit.powerbase.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Pcfreeumpiredegree;
import com.centit.powerbase.po.Suppower;

public class SuppowerDao extends BaseDaoImpl<Suppower> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(SuppowerDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("version", CodeBook.LIKE_HQL_ID);

            filterField.put("item_id" , CodeBook.LIKE_HQL_ID);

            filterField.put("orgId", CodeBook.LIKE_HQL_ID);

            filterField.put("itemName", CodeBook.LIKE_HQL_ID);

            filterField.put("itemStaName", CodeBook.LIKE_HQL_ID);

            filterField.put("itemType", CodeBook.EQUAL_HQL_ID);

            filterField.put("timeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("isNetwork", CodeBook.LIKE_HQL_ID);

            filterField.put("isFormula", CodeBook.LIKE_HQL_ID);

            filterField.put("processDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("phone", CodeBook.LIKE_HQL_ID);

            filterField.put("address", CodeBook.LIKE_HQL_ID);

            filterField.put("zfAccording", CodeBook.LIKE_HQL_ID);

            filterField.put("xwAccording", CodeBook.LIKE_HQL_ID);

            filterField.put("isinuse", CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifytime", CodeBook.LIKE_HQL_ID);
           // filterField.put("beginTime", CodeBook.LIKE_HQL_ID);
            //filterField.put("endTime", CodeBook.LIKE_HQL_ID);

            filterField.put("ischange", CodeBook.LIKE_HQL_ID);

            filterField.put("fileName", CodeBook.LIKE_HQL_ID);

            filterField.put("auditSign", CodeBook.LIKE_HQL_ID);

            filterField.put("monitorPhone", CodeBook.LIKE_HQL_ID);

            filterField.put("srvDirectory", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptLink", CodeBook.LIKE_HQL_ID);

            filterField.put("applyForm", CodeBook.LIKE_HQL_ID);

            filterField.put("question", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

            filterField.put("legalTimeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("charge", CodeBook.LIKE_HQL_ID);

            filterField.put("formName", CodeBook.LIKE_HQL_ID);

            filterField.put("freeJudge", CodeBook.LIKE_HQL_ID);

            filterField.put("levyUpon", CodeBook.LIKE_HQL_ID);

            filterField.put("condition", CodeBook.LIKE_HQL_ID);

            filterField.put("inFlowInfo", CodeBook.LIKE_HQL_ID);

            filterField.put("inFlowImg", CodeBook.LIKE_HQL_ID);

            filterField.put("begTime", "beginTime <= to_date(?, 'yyyy-mm-dd') ");
            
            filterField.put("endTime", " (endTime is null or endTime > to_date(?, 'yyyy-mm-dd')) ");
            
            filterField.put("NP_not" , "version <>0 ");
            
            filterField.put("inFlowImgName", CodeBook.LIKE_HQL_ID);

            filterField.put("ischarge", CodeBook.LIKE_HQL_ID);

            filterField.put("chargeBasis", CodeBook.LIKE_HQL_ID);

            filterField.put("chargeStandard", CodeBook.LIKE_HQL_ID);

            filterField.put("punishClass", CodeBook.LIKE_HQL_ID);

            filterField.put("transactDepname", CodeBook.LIKE_HQL_ID);

            filterField.put("promiseType", CodeBook.LIKE_HQL_ID);

            filterField.put("anticipateType", CodeBook.LIKE_HQL_ID);

            filterField.put("acceptCondition", CodeBook.LIKE_HQL_ID);

            filterField.put("qlDepid", CodeBook.LIKE_HQL_ID);

            filterField.put("qlDepstate", CodeBook.LIKE_HQL_ID);

            filterField.put("entrustName", CodeBook.LIKE_HQL_ID);

            filterField.put("qlState", CodeBook.EQUAL_HQL_ID);

            filterField.put("punishobject", CodeBook.LIKE_HQL_ID);

            filterField.put("punishbasiscontent", CodeBook.LIKE_HQL_ID);

            filterField.put("punishbasis", CodeBook.LIKE_HQL_ID);

            filterField.put("islawsuit", CodeBook.LIKE_HQL_ID);
            
            filterField.put("isreconsider", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    @SuppressWarnings("unchecked")
    public List<Suppower> getPicList(Map<String, String> filterMap, PageDesc pageDesc) {
     
        String itemid = filterMap.get("itemId");
        String itemName = filterMap.get("itemName");
        String itemType = filterMap.get("itemType");
        String orgId = filterMap.get("orgId");
        StringBuffer sql = new StringBuffer();
        sql.append(" select item_id as itemId, item_Name as itemName,org_id as orgId, ITEM_TYPE as itemType, max(version) as version, ql_state  as qlState"
                + " from B_V_PowerInUsing where 1=1 ");
        
        if (StringUtils.isNotBlank(itemid)) {
            sql.append(" and item_id like " + HQLUtils.buildHqlStringForSQL(HQLUtils.getMatchString(itemid)));
        }
        if (StringUtils.isNotBlank(itemName)) {
            sql.append(" and item_Name like " + HQLUtils.buildHqlStringForSQL(HQLUtils.getMatchString(itemName)));
        }
        if (StringUtils.isNotBlank(itemType)) {
            sql.append(" and item_Type = " + HQLUtils.buildHqlStringForSQL(itemType));
        }
        if (StringUtils.isNotBlank(orgId)) {
            sql.append(" and org_Id like " + HQLUtils.buildHqlStringForSQL(HQLUtils.getMatchString(orgId)));
        }
        sql.append(" group by item_id ,item_Name,org_id,ITEM_TYPE,ql_state");

        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("itemId", StringType.INSTANCE);
        sqlQuery.addScalar("itemName", StringType.INSTANCE);
        sqlQuery.addScalar("orgId", StringType.INSTANCE);
        sqlQuery.addScalar("itemType", StringType.INSTANCE);
        sqlQuery.addScalar("version", StringType.INSTANCE);
        sqlQuery.addScalar("qlState", StringType.INSTANCE);
        List<Suppower>  list = (List<Suppower>) sqlQuery.setResultTransformer(
                Transformers.aliasToBean(Suppower.class)).list();
        pageDesc.setTotalRows(Integer.valueOf(list.size()));
        return list;
    }

    

    @SuppressWarnings("unchecked")
    public List<Suppower> getPicListByState(Map<String, Object> filterMap,
            PageDesc pageDesc, String qlState, String ItemType) {
        List<Suppower> list = null;
        String itemid = (String) filterMap.get("itemId");
        String itemName = (String) filterMap.get("itemName");
        String itemType = (String) filterMap.get("itemType");
        String orgId = (String) filterMap.get("orgId");
        String sParentUnit = (String) filterMap.get("sParentUnit");
        StringBuffer sql = new StringBuffer();
        sql.append(" select item_id as itemId, item_Name as itemName,org_id as orgId, ITEM_TYPE as itemType, version as version, ql_state  as qlState"
                + " from b_power left join V_HI_UNITINFO on  b_power.org_id = V_HI_UNITINFO.depno " +
                " where 1=1  and (item_id ,version) in (select item_id as itemId,    max(version) as version from b_power where 1 = 1 group by item_id ) ");
        if (StringUtils.isNotBlank(sParentUnit)) {
            sql.append(" and topunitcode = " + HQLUtils.buildHqlStringForSQL(sParentUnit));
        }
        if (StringUtils.isNotBlank(itemid)) {
            sql.append(" and item_id like " + HQLUtils.buildHqlStringForSQL(HQLUtils.getMatchString(itemid)));
        }
        if (StringUtils.isNotBlank(itemName)) {
            sql.append(" and item_Name like " + HQLUtils.buildHqlStringForSQL(HQLUtils.getMatchString(itemName)));
        }
        //下面两个是不同作用的，不能删除
        if (StringUtils.isNotBlank(itemType)) {
            sql.append(" and item_Type = " + HQLUtils.buildHqlStringForSQL(itemType));
        }
        if (StringUtils.isNotBlank(ItemType)) {//
            sql.append(" and item_Type = " + HQLUtils.buildHqlStringForSQL(ItemType));
        }
        if (StringUtils.isNotBlank(orgId)) {
            sql.append(" and org_Id like " + HQLUtils.buildHqlStringForSQL(HQLUtils.getMatchString(orgId)));
        }
        if (StringUtils.isNotBlank(qlState)) {
                sql.append(" and ql_state like " + HQLUtils.buildHqlStringForSQL(qlState));

        }
        // sql.append(" group by item_id ,item_Name,org_id,ITEM_TYPE,ql_state");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("itemId", StringType.INSTANCE);
        sqlQuery.addScalar("itemName", StringType.INSTANCE);
        sqlQuery.addScalar("orgId", StringType.INSTANCE);
        sqlQuery.addScalar("itemType", StringType.INSTANCE);
        sqlQuery.addScalar("version", LongType.INSTANCE);
        sqlQuery.addScalar("qlState", StringType.INSTANCE);
        list = (List<Suppower>) sqlQuery.setResultTransformer(Transformers.aliasToBean(Suppower.class)).list();
        pageDesc.setTotalRows(Integer.valueOf(list.size()));
        return list;
    }

    public Suppower getSuppower(String itemId, Long version) {
        if (version != null) {
            List<Suppower> procs = this
                    .listObjects("From Suppower where item_id =  "
                            + HQLUtils.buildHqlStringForSQL(itemId)
                            + " and version="
                            + HQLUtils.buildHqlStringForSQL(Long.toString(version)));
            if (procs != null&&procs.size() >= 1)
                return procs.get(0);
        }
        return null;
    }
    
    public Suppower getSuppowerLastVersion(String itemId, Date applyDate) {
        List<Suppower> procs = this
                .listObjects("From Suppower where item_id =  "
                        + HQLUtils.buildHqlStringForSQL(itemId)
                        + " and begin_time <= " + HQLUtils.buildHqlDateStringForOracle(applyDate)+
                        " and (end_time is null or end_time > "+HQLUtils.buildHqlDateStringForOracle(applyDate)+") order by version DESC");
        if (procs != null&&procs.size() >= 1)
            return procs.get(0);
        return null;
    }

    @SuppressWarnings("rawtypes")
    public List getVersionList(String itemId) {
       
        StringBuffer sql = new StringBuffer();
        sql.append(" select version from B_power where 1=1 ");
        if (StringUtils.isNotBlank(itemId)) {
            sql.append(" and item_id = " + HQLUtils.buildHqlStringForSQL(itemId));
        }
        sql.append(" order by item_id");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        List versionList = sqlQuery.list();
        return versionList;

    }
    /**
     * 下面是修改版本信息
     * @param filterMap
     * @param pageDesc
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Suppower> getlistSuppowerOld(String itemId,Long version) {
       
        StringBuffer sql = new StringBuffer();
        sql.append("select t.item_id as itemId,t.version as version,t.item_name as itemName, t.org_id as orgId,t.begin_time as beginTime, t.end_time as endTime "
                +"from b_power t where 1=1 and version<>0  ");
        if(StringUtils.isNotBlank(itemId)){
            sql.append(" and item_id = " + HQLUtils.buildHqlStringForSQL(itemId));
        }
        sql.append("  order by t.version desc");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("itemId", StringType.INSTANCE);
        sqlQuery.addScalar("itemName", StringType.INSTANCE);
        sqlQuery.addScalar("version", LongType.INSTANCE);
        sqlQuery.addScalar("orgId", StringType.INSTANCE);
        sqlQuery.addScalar("beginTime", DateType.INSTANCE);
        sqlQuery.addScalar("endTime", DateType.INSTANCE);
        List<Suppower>  list = (List<Suppower>) sqlQuery.setResultTransformer(Transformers.aliasToBean(Suppower.class)).list();
        return list;
    } 
    
    @SuppressWarnings("unchecked")
    public List<Suppower> getlistSuppower(String item_type) {
     
        StringBuffer sql = new StringBuffer();
        sql.append("select t.item_id as itemId,t.version as version,t.item_name as itemName, t.org_id as orgId,t.begin_time as beginTime, t.end_time as endTime "
                +"from b_power t where 1=1 " +
                " and version<>0 and (item_id ,version) in (select item_id as itemId,    max(version) as version from b_power where 1 = 1 group by item_id )  ");
        if(StringUtils.isNotBlank(item_type)&&!"-".equals(item_type)){
            sql.append(" and item_type = " + HQLUtils.buildHqlStringForSQL(item_type));
        }else if ("-".equals(item_type)){
            sql.append(" and item_type <> 'CF' ");
        }
        sql.append("  order by t.version desc");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("itemId", StringType.INSTANCE);
        sqlQuery.addScalar("itemName", StringType.INSTANCE);
        sqlQuery.addScalar("version", LongType.INSTANCE);
        sqlQuery.addScalar("orgId", StringType.INSTANCE);
        sqlQuery.addScalar("beginTime", DateType.INSTANCE);
        sqlQuery.addScalar("endTime", DateType.INSTANCE);
        List<Suppower>  list = (List<Suppower>) sqlQuery.setResultTransformer(Transformers.aliasToBean(Suppower.class)).list();
        return list;
    } 
    public void updateSuppower(Suppower suppower) {
        doExecuteHql(
                "update Suppower set lastmodifytime=?,qlState=?,endTime=? where cid.itemId =?  and cid.version=? ",
                new Object[] { suppower.getLastmodifytime(),suppower.getQlState(),suppower.getEndTime(),suppower.getItemId(), suppower.getVersion()});
    }
    /**
     * 权力发布的时候查找需要改变的版本
     * @param itemId
     * @param version
     * @return
     */
    @SuppressWarnings("unchecked")
    public Suppower getSuppowerQlfb(String itemId) {
      
        StringBuffer sql = new StringBuffer();
        sql.append("select t.item_id as itemId,t.version as version,t.item_name as itemName, t.org_id as orgId "
                +"from b_power t where 1=1 " +
                " and version<>0 and end_time is null ");
        if(StringUtils.isNotBlank(itemId)){
            sql.append(" and item_id = " + HQLUtils.buildHqlStringForSQL(itemId));
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("itemId", StringType.INSTANCE);
        sqlQuery.addScalar("itemName", StringType.INSTANCE);
        sqlQuery.addScalar("version", LongType.INSTANCE);
        sqlQuery.addScalar("orgId", StringType.INSTANCE);
        List<Suppower>  procs = (List<Suppower>) sqlQuery.setResultTransformer(Transformers.aliasToBean(Suppower.class)).list();
            if (procs.size() >= 1){
                return procs.get(0);
            }
        return null;
    }
    @SuppressWarnings("unchecked")
    public List<Pcfreeumpiredegree> getzycviewnew(String Linagesize) {
       

        StringBuffer sql = new StringBuffer();
        sql.append(" select extracode as standardNo,datavalue as punishfactgrade from f_datadictionary where  catalogcode="
                + HQLUtils.buildHqlStringForSQL(Linagesize));

        sql.append(" order by extracode");

        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("standardNo", StringType.INSTANCE);
        sqlQuery.addScalar("punishfactgrade", StringType.INSTANCE);
        List<Pcfreeumpiredegree> list = (List<Pcfreeumpiredegree>) sqlQuery.setResultTransformer(
                Transformers.aliasToBean(Pcfreeumpiredegree.class)).list();
        // pageDesc.setTotalRows(new Integer(list.size()));
        return list;
    }

    public void delStandardItemID(String item_id, String version) {
        doExecuteHql(
                "delete from PcpunishStandard  where cid.itemId =?  and cid.version=? ",
                new Object[] { item_id, version });
    }

    public void delDiscretionValueByItemID(String item_id, Long version) {
        doExecuteHql(
                "delete from Pcfreeumpiretype  where cid.degreeno in (select cid.degreeno from Pcfreeumpiredegree where itemId=? and version=?) ",
                new Object[] { item_id, version });
    }

    public void delDiscretionItemID(String item_id, Long version) {
        doExecuteHql(
                "delete from Pcfreeumpiredegree  where itemId =?  and version=? ",
                new Object[] { item_id, version });
    }
}
