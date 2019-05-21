package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaTripPlan;

public class OaTripPlanDao extends BaseDaoImpl<OaTripPlan>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaTripPlanDao.class);
    
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

            filterField.put("tripPlanName" , CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    } 
}
