package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.app.po.OptDashboardModule;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class OptDashboardModuleDao extends BaseDaoImpl<OptDashboardModule> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("moduleName" , CodeBook.LIKE_HQL_ID);
            filterField.put("isUsed", CodeBook.EQUAL_HQL_ID);
            filterField.put("moduleCode", CodeBook.EQUAL_HQL_ID);
        }
        return filterField;
    } 
}
