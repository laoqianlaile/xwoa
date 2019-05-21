package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.app.po.OptDashboardLayout;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class OptDashboardLayoutDao extends BaseDaoImpl<OptDashboardLayout> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("layoutName" , CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    } 
}
