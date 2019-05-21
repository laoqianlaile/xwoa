package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.OptZwh;

public class IODocArchiveNoDao extends BaseDaoImpl<OptZwh> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(IODocArchiveNoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("fwh", CodeBook.LIKE_HQL_ID);

            filterField.put("lsh", "to_number(lsh)=to_number(?)");

            filterField.put("lshYear", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("matchLsh", "lsh like '%?'");

            filterField.put("wjlx", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("largerLsh", "to_number(lsh)>=to_number(?)");
            
            filterField.put("djIdCanNull", " (djId is null or djId=?)");

        }
        return filterField;
    }
}
