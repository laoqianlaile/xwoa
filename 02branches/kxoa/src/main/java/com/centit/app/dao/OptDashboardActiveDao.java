package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.app.po.OptDashboardActive;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class OptDashboardActiveDao extends BaseDaoImpl<OptDashboardActive> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("usercode" , CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    } 
}
