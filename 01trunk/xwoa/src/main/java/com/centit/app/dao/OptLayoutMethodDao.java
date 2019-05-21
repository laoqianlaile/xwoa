package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.app.po.OptLayoutMethod;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class OptLayoutMethodDao extends BaseDaoImpl<OptLayoutMethod> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("methodName" , CodeBook.LIKE_HQL_ID);
        }
        return filterField;
    } 
}
