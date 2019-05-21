package com.centit.oa.dao;

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

import com.centit.oa.po.VoaUnitArchiveDispatchdoc;
import com.centit.support.utils.StringBaseOpt;
import com.centit.support.utils.StringRegularOpt;

public class VoaUnitArchiveDispatchdocDao extends BaseDaoImpl<VoaUnitArchiveDispatchdoc>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VoaUnitArchiveDispatchdocDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("no" , CodeBook.EQUAL_HQL_ID);


			filterField.put("usercode" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("id" , CodeBook.LIKE_HQL_ID);

			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("dispatchDocNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("optUnitName" , CodeBook.LIKE_HQL_ID);

			filterField.put("mainNotifyUnit" , CodeBook.LIKE_HQL_ID);

			filterField.put("otherUnits" , CodeBook.LIKE_HQL_ID);

			filterField.put("createTime" , CodeBook.LIKE_HQL_ID);

			filterField.put("createDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("updateuser" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("NP_id", " id is null");
			
			filterField.put("gwNature" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put("belongUnitcode" , CodeBook.EQUAL_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID,"create_date desc");
			
			filterField.put("begTime", " createTime>= to_date(?, 'yyyy-mm-dd') ");
            filterField.put("endTime", " createTime< to_date(?, 'yyyy-mm-dd')+1 ");
			
			filterField.put("begincomeDate", " createDate>= to_date(?, 'yyyy-mm-dd') ");
            filterField.put("endincomeDate", " createDate< to_date(?, 'yyyy-mm-dd')+1 ");
            filterField.put("currentdatetime" , "createTime<=  to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
		}
		return filterField;
	} 
	private Map<String,String> returnDbField(Map<String,String> params){
        Map<String,String> res = new HashMap<String,String>();
        if(params != null){
            for(Map.Entry<String,String> entry : params.entrySet()){
                if("dispatchDocNo".equals(entry.getKey())){
                    res.put("DISPATCH_DOC_NO", entry.getValue());
                }else if("optUnitName".equals(entry.getKey())){
                    res.put("OPT_UNIT_NAME",  entry.getValue());
                }else if("mainNotifyUnit".equals(entry.getKey())){
                    res.put("MAIN_NOTIFY_UNIT", entry.getValue());
                }else if("otherUnits".equals(entry.getKey())){
                    res.put("OTHER_UNITS", entry.getValue());
                }else if("createDate".equals(entry.getKey())){
                    res.put("CREATE_DATE", entry.getValue());
                }else if("begincomeDate".equals(entry.getKey())){
                    res.put("begincomeDate", " CREATE_DATE>= to_date(?, 'yyyy-mm-dd') ");
                }else if("endincomeDate".equals(entry.getKey())){
                    res.put("endincomeDate", " CREATE_DATE< to_date(?, 'yyyy-mm-dd')+1 ");
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
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId){
        String shql = "select * from V_OA_ARCHIVE_DISPATCHDOC where 1=1 ";
        HqlAndParams hql = this.builderHqlAndParams(shql, filterMap);
        
//    //添加上一项 和下一项
        StringBuffer sql = new StringBuffer("with temp as(");
        sql.append(hql.getHql())
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
}
