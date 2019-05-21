package com.centit.powerruntime.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.powerruntime.po.VOptProcCollection;
import com.centit.support.utils.StringBaseOpt;
import com.centit.support.utils.StringRegularOpt;

public class VOptProcCollectionDao extends BaseDaoImpl<VOptProcCollection>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VOptProcCollectionDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

			filterField.put("nodeInstId" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("userCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("roleType" , CodeBook.LIKE_HQL_ID);

			filterField.put("roleCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("optId" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowOptTag" , CodeBook.LIKE_HQL_ID);

			filterField.put("nodeName" , CodeBook.LIKE_HQL_ID);

			filterField.put("methodName" , CodeBook.LIKE_HQL_ID);

			filterField.put("optUrl" , CodeBook.LIKE_HQL_ID);

			filterField.put("optMethod" , CodeBook.LIKE_HQL_ID);

			filterField.put("optCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("optParam" , CodeBook.LIKE_HQL_ID);

			filterField.put("inststate" , CodeBook.LIKE_HQL_ID);

			filterField.put("grantor" , CodeBook.LIKE_HQL_ID);

			filterField.put("timeLimit" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastUpdateTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowPhase" , CodeBook.LIKE_HQL_ID);

			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("transAffairNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("atttype" , CodeBook.LIKE_HQL_ID);

			filterField.put("attsettime" , CodeBook.LIKE_HQL_ID);

			filterField.put("isatt" , CodeBook.LIKE_HQL_ID);

			filterField.put("removesettime" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("begTime", " attsettime>= to_date(?, 'yyyy-mm-dd') ");
            
            filterField.put("endTime", " attsettime< to_date(?, 'yyyy-mm-dd')+1 ");
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " attsettime desc");

		}
		return filterField;
	} 
	
	private Map<String,String> returnDbField(Map<String,String> params){
        Map<String,String> res = new HashMap<String,String>();
        if(params != null){
            for(Map.Entry<String,String> entry : params.entrySet()){
                if("djId".equals(entry.getKey())){
                    res.put("DJ_ID", entry.getValue());
                }else if("flowOptTag".equals(entry.getKey())){
                    res.put("WFOPTTAG",entry.getValue());
                }
                else{
                    res.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return res;
    }
	
	 public HqlAndParams builderHqlAndParams(String shql,
	            Map<String, Object> filterDesc) {

	        StringBuffer hql = new StringBuffer(shql);
	        List<Object> params = new ArrayList<Object>();
	        Map<String, String> filterFieldDesc = returnDbField(getFilterField());
	        String sOrderby = null;

	        for (Map.Entry<String, Object> ent : filterDesc.entrySet()) {
	            String skey = ent.getKey();
	            String sSqlFormat = null;
	            if (filterFieldDesc != null) {
	                sSqlFormat = filterFieldDesc.get(skey);
	                if (sSqlFormat == null)
	                    sSqlFormat = filterFieldDesc.get(skey.toUpperCase());
	            }
	            if (sSqlFormat != null) {
	                Object sValue = ent.getValue();

	                if ((null == sValue)
	                        || ((sValue instanceof String) && !StringUtils
	                                .hasText(sValue.toString()))) {
	                    continue;
	                }

	                if (skey.startsWith(CodeBook.NO_PARAM_FIX)) {
	                    if (StringRegularOpt.isTrue(sValue.toString()))
	                        hql.append(" and ").append(sSqlFormat);
	                } else {
	                    if (sSqlFormat.equalsIgnoreCase(CodeBook.LIKE_HQL_ID)) {
	                        sValue = HQLUtils.getMatchString(sValue.toString());
	                        hql.append(String.format(" and %s like ? ", skey));
	                    } else if (sSqlFormat
	                            .equalsIgnoreCase(CodeBook.EQUAL_HQL_ID)) {
	                        hql.append(String.format(" and %s = ? ", skey));
	                    } else {
	                        hql.append(" and ").append(sSqlFormat);
	                    }
	                    params.add(sValue);
	                }

	            } else if (CodeBook.SELF_ORDER_BY.equalsIgnoreCase(skey)) {
	                sOrderby = ent.getValue().toString();
	            }
	        }
	        if (sOrderby == null && filterFieldDesc != null)
	            sOrderby = filterFieldDesc.get(CodeBook.ORDER_BY_HQL_ID);
	        if (!StringBaseOpt.isNvl(sOrderby))
	            hql.append(" order by ").append(sOrderby);

	        return new HqlAndParams(hql.toString(), params.toArray());
	    }
	
	 @SuppressWarnings("unchecked")
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,
	            String currDjId) {
	     String tempSql = "select * from V_OPT_PROC_COLLECTION where 1=1 ";
	     HqlAndParams hql = this.builderHqlAndParams(tempSql, filterMap);   
	     
	     StringBuffer sql = new StringBuffer("with temp as(");
	        sql.append(hql.getHql())
	           .append(")")
	           .append("select p.dj_id prevno,n.dj_id nextno")
	           .append(" from (select rownum rn,temp.* from temp) c")
	           .append(" left join (select rownum rn,temp.dj_id from temp) p on p.rn = c.rn-1")
	           .append(" left join (select rownum rn,temp.dj_id from temp) n on n.rn = c.rn+1")
	           .append(" where c.dj_id="+HQLUtils.buildHqlStringForSQL(currDjId))
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
}
