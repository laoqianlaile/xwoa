package com.centit.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.po.FUserinfo;

public class AccessLogDao extends BaseDaoImpl<FAccessLog>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);
            filterField.put("accesstype", CodeBook.EQUAL_HQL_ID);
            filterField.put("beginDate", "accesstime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            filterField.put("endDate", "accesstime <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            filterField.put("excludeType", "accesstype <> ?");
            filterField.put(CodeBook.ORDER_BY_HQL_ID, "accesstime desc");
        }
        return filterField;
    }
    
    /**
     * 查询在线人数
     * @return
     */
    public int getUserCountOnline(){
        String sql = "select count(*) from VUserOnline ";
        Object o = super.getSingleObjectByHql(sql);
        if(o == null)
            return 0;
        String s = String.valueOf(o);
        return Integer.valueOf(s); 
    }
    
    @SuppressWarnings("unchecked")
    public List<FAccessLog> findAccessLogUndo(int limitMins){
        StringBuffer sql = new StringBuffer();
        sql.append("with tmp as(")
           .append("select t1.* from f_access_log t1,f_userinfo t2 where t1.usercode = t2.usercode and t1.accesstype = 3")
           .append(" and (t1.accesstime+(1/24/60)*").append(limitMins).append(") <= sysdate")
           .append(" and To_date(To_char(Trunc(SYSDATE), 'yyyy/mm/dd hh24:mi:ss'), 'yyyy/mm/dd hh24:mi:ss') <= t1.accesstime")
           .append(" and t2.userState = 1")
           .append(")select t1.* from  f_access_log t1")
           .append(" inner join (select usercode,max(id) as id from tmp  group by usercode) t2")
           .append(" on t1.id = t2.id");
     
        List<FAccessLog> l = null;
        try{
            l=getHibernateTemplate().executeFind(new SQLQueryCallBack(sql.toString(),FAccessLog.class));
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    }
}
