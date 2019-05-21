package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OptFilingCabinets;

public class OptFilingCabinetsDao extends BaseDaoImpl<OptFilingCabinets>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("refId", CodeBook.EQUAL_HQL_ID);
            filterField.put(CodeBook.ORDER_BY_HQL_ID," id asc");
        }
        return filterField;
    }
 
}
