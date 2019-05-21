package com.centit.advancedsearch.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.advancedsearch.po.OaSearchHistory;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class OaSearchHistoryDao extends BaseDaoImpl<OaSearchHistory>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();
            filterField.put("creater" , CodeBook.EQUAL_HQL_ID);

            filterField.put("mainKey" , CodeBook.LIKE_HQL_ID);

            filterField.put("state" , CodeBook.EQUAL_HQL_ID);
            filterField.put("eqMainKey", " mainKey = ?");
            filterField.put(CodeBook.ORDER_BY_HQL_ID, "createTime desc");
        }
        return filterField;
    }
}
