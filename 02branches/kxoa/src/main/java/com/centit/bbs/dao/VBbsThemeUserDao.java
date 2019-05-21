package com.centit.bbs.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.bbs.po.VBbsThemeUser;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VBbsThemeUserDao extends BaseDaoImpl<VBbsThemeUser>
    {
        private static final long serialVersionUID = 1L;
        public static final Log log = LogFactory.getLog(VBbsThemeUserDao.class);
        
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("themeno" , CodeBook.EQUAL_HQL_ID);
            
            filterField.put("manager", CodeBook.EQUAL_HQL_ID);

            filterField.put("layoutno" , CodeBook.EQUAL_HQL_ID);
            
            filterField.put("layoutcode" , CodeBook.EQUAL_HQL_ID);

            filterField.put("sublayouttitle" , CodeBook.LIKE_HQL_ID);

            filterField.put("bodycontent" , CodeBook.LIKE_HQL_ID);

            filterField.put("creater" , CodeBook.LIKE_HQL_ID);

            filterField.put("createtime" , CodeBook.LIKE_HQL_ID);

            filterField.put("state" , CodeBook.LIKE_HQL_ID);

            filterField.put("approval" , CodeBook.LIKE_HQL_ID);

            filterField.put("approvalresults" , CodeBook.EQUAL_HQL_ID);

            filterField.put("approvaltime" , CodeBook.LIKE_HQL_ID);

            filterField.put("approvalremark" , CodeBook.LIKE_HQL_ID);

            filterField.put("postsnum" , CodeBook.LIKE_HQL_ID);

            filterField.put("postsviewnum" , CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifytime" , CodeBook.LIKE_HQL_ID);

            filterField.put("themeset" , CodeBook.LIKE_HQL_ID);

            filterField.put("attentionum" , CodeBook.LIKE_HQL_ID);
            
            filterField.put("createtimeEq" , " createtime =to_date(?,'yyyy-mm-dd') ");
            
            filterField.put("startCreateTime", " createtime >=to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            
            filterField.put("endCreateTime", " createtime <=to_date(?,'yyyy-mm-dd hh24:mi:ss')");
            
            filterField.put("notDeleted", " (state <> ? or state is null) ");
           
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " state desc ,lastmodifytime desc,createtime desc");

        }
        return filterField;
    } 
}
