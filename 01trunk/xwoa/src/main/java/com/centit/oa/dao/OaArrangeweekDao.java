package com.centit.oa.dao;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaArrangeweek;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OaArrangeweekDao extends BaseDaoImpl<OaArrangeweek> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaArrangeweekDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("beginCreatetime"," to_char(createtime,'yyyy-mm-dd HH:mm:ss')>=?"); //开始时间

            filterField.put("endCreatetime"," to_char(createtime,'yyyy-mm-dd HH:mm:ss')<=?");      //结束时间
            
            filterField.put("createdate", CodeBook.LIKE_HQL_ID);    //记录时间
            
            filterField.put("depno", CodeBook.EQUAL_HQL_ID);    //责任部门
            filterField.put("meetType", CodeBook.EQUAL_HQL_ID);    //类型
            
            filterField.put("attendusers", CodeBook.EQUAL_HQL_ID);    //参加人员
            
            filterField.put("attendleaders", CodeBook.EQUAL_HQL_ID);    //参加领导
            
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " createtime asc");       //安排日期排序

            filterField.put("meetType",CodeBook.EQUAL_HQL_ID);
            
            filterField.put("currentdatetime", " createtime < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");

            filterField.put("NP_susername", "  ( attendusers like ?  )");

        }
        return filterField;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<OaArrangeweek> getarrangeweeklist(String username) {//模糊搜索参加人员和参会领导
        String shql = "";
       /* if(){
            shql =" from oa_arrangeweek  where  attendleaders like '%-"+username+"-%'  "; 
        }else if(){
            shql =" from oa_arrangeweek  where attendusers like '%-"+username+"-%' or attendleaders like '%-"+username+"-%'  ";
        }else{
            shql =" from oa_arrangeweek  where attendusers like '%-"+username+"-%' or attendleaders like '%-"+username+"-%'  ";
        }*/
        shql ="select * from oa_arrangeweek  where attendusers like '%"+username+"%' or attendleaders like '%"+username+"%'  ";
        List<OaArrangeweek> list = (List<OaArrangeweek>) findObjectsBySql(shql,OaArrangeweek.class);
        return list; 
    } 
}
