package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.VProcTransUsers;

public class VProcTransUsersDao extends BaseDaoImpl<VProcTransUsers>{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VProcTransUsersDao.class);
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

            filterField.put("usercode" , CodeBook.EQUAL_HQL_ID);

            filterField.put("username" , CodeBook.EQUAL_HQL_ID);

            filterField.put("transdate" , CodeBook.EQUAL_HQL_ID);
            
            filterField.put("nodeCode" , CodeBook.EQUAL_HQL_ID);

          

        }
        return filterField;
    }
}
