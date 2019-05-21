package com.centit.sys.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.sys.po.FAccessLog;
import com.centit.webservice.pojo.SysLogDTO;

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
            filterField.put("isUpload", CodeBook.EQUAL_HQL_ID);
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
    /**
     * 获取系统日志和应用日志信息，进行封装
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getSysLogDTOlist(){
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct 'ed48ecf75d3e4fd888788042b9ca4e93' as key,'info' as logLevel,a.usercode as userId,c.optName as logModular,")
           .append("c.optName as logFunction,")
           .append("case when b.optmethod like '%save%' then '保存' " +
                        "when b.optmethod like '%update%' then '修改' " +
                        "when b.optmethod like '%insert%' then '新增'" +
                        "when b.optmethod like '%view%' then '查看' " +
                        "when b.optmethod like '%delete%' then '删除' else '其他' end as operatType " +
           		        ",a.ip as logIp, ")
           .append(" b.optId as logClass,b.optmethod as logMethod,a.id ")
           .append(" from f_access_log a ")
           .append(" left join f_opt_log b on a.usercode = b.usercode and to_char(b.opttime,'yyyy-mm-dd hh')=to_char(a.accesstime,'yyyy-mm-dd hh')")
           .append(" left join f_optinfo c on b.optid=c.optid " +
           		" where a.accesstype='1' and a.isupload is null  and c.optname is not null and to_char(a.accesstime,'yyyy-mm-dd')=to_char(sysdate-3,'yyyy-mm-dd')");
     
        List<SysLogDTO> l =new ArrayList<SysLogDTO>() ;
        List<Object[]> listObject = (List<Object[]>) findObjectsBySql(sql.toString());
       /* if(listObject!=null && listObject.size()>0){
            
        }*/
        /*try{
            l=getHibernateTemplate().executeFind(new SQLQueryCallBack(sql.toString(),SysLogDTO.class));
        }catch (Exception e) {
            log.error(e.getMessage());
        }*/
        return listObject;
    }
}
