package com.centit.sys.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.sys.po.VUserOnline;

public class VUserOnlineDao extends BaseDaoImpl<VUserOnline>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);
            filterField.put("unitcode", CodeBook.EQUAL_HQL_ID);
            filterField.put("unitname", CodeBook.LIKE_HQL_ID);
            filterField.put("username",CodeBook.LIKE_HQL_ID);
            filterField.put("userdesc", CodeBook.LIKE_HQL_ID);
            
            /*filterField.put(CodeBook.ORDER_BY_HQL_ID, " usercode desc");*/
        }
        return filterField;
    }

    public void clearUserOnlineState() {
        doExecuteHql(
                "update FUserinfo set userState='0' ");
        
    }
    
}