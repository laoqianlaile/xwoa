package com.centit.powerruntime.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.powerruntime.po.OaUnitsLeader;

public class OaUnitsLeaderDao extends BaseDaoImpl<OaUnitsLeader> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaUnitsLeaderDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("leadercode", CodeBook.EQUAL_HQL_ID);

            filterField.put("isuse", CodeBook.LIKE_HQL_ID);

            filterField.put("unitcodes", CodeBook.LIKE_HQL_ID);

            filterField.put("createtime", CodeBook.LIKE_HQL_ID);

            filterField.put("createuser", CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifytime", CodeBook.LIKE_HQL_ID);

            filterField.put("updateuser", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    @SuppressWarnings({ "unchecked" })
    public Set<String> listObjectsByUsercode(String usercode) {
        // TODO Auto-generated method stub
        Set<String> rr=new HashSet<String>();
        String sql = "select * from (select distinct t.username,t.usercode,t.userorder "
                + "from v_user_units t where t.unitcode in "
                + "(select n.unitcode from oa_leaderunits n left join "
                + " oa_units_leader m on m.leadercode=n.leadercode where m.isuse='T' and n.leadercode="+HQLUtils.buildHqlStringForSQL(usercode)+")) order by userorder asc ";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sql.toString());
        if(l!=null && l.size()>0){
            for(Object[] o:l){
                rr.add(String.valueOf(o[1]));
            }
            return rr;   
        }else{
        return null;
        }
    }
    /**
     * 根据用户编号查询部门分管领导
     * @param usercode
     * @return
     */
    public Map<String,String> getLeadercode(String usercode){
        String sql = "select t.*,u.username from (select n.unitcode,n.leadercode from oa_leaderunits n left join "
                + " oa_units_leader m on m.leadercode=n.leadercode where m.isuse='T' and n.unitcode in (select f.unitcode from f_userunit f where f.usercode="+HQLUtils.buildHqlStringForSQL(usercode)+")"
                +") t join f_userinfo u on t.leadercode=u.usercode ";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sql.toString());
        Map<String,String> map=new HashMap<String,String>(); 
        if(l!=null&&l.size()>0){
            for(Object[] o:l){
                
                map.put((String)o[1],(String) o[2]);
            }
            return map;
        }
        else 
            return null;
    } 
}
