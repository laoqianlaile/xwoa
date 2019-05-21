package com.centit.powerbase.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.po.Suppowerchglog;

public class SuppowerchglogDao extends BaseDaoImpl<Suppowerchglog>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(SuppowerchglogDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
			filterField.put("changeId" , CodeBook.EQUAL_HQL_ID);
			filterField.put("itemId" , CodeBook.EQUAL_HQL_ID);
			filterField.put("version" , CodeBook.LIKE_HQL_ID);
			filterField.put("chgReason" , CodeBook.LIKE_HQL_ID);
			filterField.put("chgContent" , CodeBook.LIKE_HQL_ID);
			filterField.put("requestTime" , CodeBook.LIKE_HQL_ID);
			filterField.put("requester" , CodeBook.LIKE_HQL_ID);
			filterField.put("chgResult" , CodeBook.LIKE_HQL_ID);
			filterField.put("auditTime" , CodeBook.LIKE_HQL_ID);
			filterField.put("auditor" , CodeBook.LIKE_HQL_ID);
			filterField.put("auditContent" , CodeBook.LIKE_HQL_ID);
			filterField.put("chgType" , CodeBook.LIKE_HQL_ID);
			filterField.put("replyPeople" , CodeBook.LIKE_HQL_ID);
			filterField.put("replyTime" , CodeBook.LIKE_HQL_ID);
			filterField.put("replyContent" , CodeBook.LIKE_HQL_ID);
			filterField.put(CodeBook.ORDER_BY_HQL_ID, "version,changeId");

		}
		return filterField;
	}
	 public String genNextChangeId() {
	        return getNextKeyBySequence("S_CHANGE_ID", 10);
	    }

    public Suppowerchglog getObjectByIdAndVersion(String itemId,
            String version){
             List<Suppowerchglog> procs = this.listObjects("From Suppowerchglog where item_id =  "+HQLUtils.buildHqlStringForSQL(itemId)+" and version="+HQLUtils.buildHqlStringForSQL(version));
             if (procs == null || procs.size() < 1)
                 return null;
             return procs.get(0);
         } 
    /**
     * 下面是修改版本信息，TODO 这儿有sql注入问题，我修改了一下可能需要测试，这个SQL语句这是访问一个表，应该可以通过Hql语句来，试试看我注释1中的HQL语句
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<Suppowerchglog> getlistVersionByItemid(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        
        return listObjects( filterMap,  pageDesc);
        
        /*
        List<Suppowerchglog> list = new ArrayList<Suppowerchglog>();
        Object itemid = filterMap.get("itemId");
        StringBuffer sql = new StringBuffer();
        sql.append("select t.change_id as changeId,t.item_id as itemId,t.version as version,t.chg_reason as chgReason," +
        		"t.chg_content as chgContent,t.request_time as requestTime,t.requester as requester,t.chg_result as chgResult," +
        		"t.audit_time as auditTime,t.auditor as auditor,t.audit_content as auditContent," +
        		"t.chg_type as chgType,t.reply_people as replyPeople,t.reply_time as replyTime,t.reply_content as replyContent " +
        		" from B_powerchglog t where t.version <> '0' ");
        if(itemid != null){
            sql.append(" and item_id =?");// + itemid + "' ");
        }
        sql.append(" order by t.version,t.change_id");
        
        list = (List<Suppowerchglog>) findObjectsBySql(sql.toString(), itemid, Suppowerchglog.class);
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("changeId", StringType.INSTANCE);
        sqlQuery.addScalar("itemId", StringType.INSTANCE);
        sqlQuery.addScalar("version", LongType.INSTANCE);
        sqlQuery.addScalar("chgReason", StringType.INSTANCE);
        sqlQuery.addScalar("chgContent", StringType.INSTANCE);
        sqlQuery.addScalar("requestTime", DateType.INSTANCE);
        sqlQuery.addScalar("requester", StringType.INSTANCE);
        sqlQuery.addScalar("chgResult", StringType.INSTANCE);
        sqlQuery.addScalar("auditTime", DateType.INSTANCE);
        sqlQuery.addScalar("auditor", StringType.INSTANCE);
        sqlQuery.addScalar("auditContent", StringType.INSTANCE);
        sqlQuery.addScalar("chgType", StringType.INSTANCE);
        sqlQuery.addScalar("replyPeople", StringType.INSTANCE);
        sqlQuery.addScalar("replyTime", DateType.INSTANCE);
        sqlQuery.addScalar("replyContent", StringType.INSTANCE);
        list = (List<Suppowerchglog>) sqlQuery.setResultTransformer(Transformers.aliasToBean(Suppowerchglog.class)).list();
        pageDesc.setTotalRows(new Integer(list.size()));
        return list;
        */
    }
    /**
     * 下面是修改版本信息 TODO 这个也有sql注入
     * @param filterMap
     * @param pageDesc
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Suppower> getlistSuppowerOld(String itemId,Long version) {
       
        StringBuffer sql = new StringBuffer();
        sql.append("select t.item_id as itemId,t.version as version,t.item_name as itemName, t.org_id as orgId "
                +"from B_power t where 1=1 " +
                " and version<>0  ");
        if(StringUtils.isNotBlank(itemId)){
            sql.append(" and item_id = '" + itemId + "' ");
        }
        sql.append("  order by t.version");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.addScalar("itemId", StringType.INSTANCE);
        sqlQuery.addScalar("itemName", StringType.INSTANCE);
        sqlQuery.addScalar("version", LongType.INSTANCE);
        sqlQuery.addScalar("orgId", StringType.INSTANCE);
        List<Suppower> list = (List<Suppower>) sqlQuery.setResultTransformer(Transformers.aliasToBean(Suppower.class)).list();
        return list;
    } 
}
