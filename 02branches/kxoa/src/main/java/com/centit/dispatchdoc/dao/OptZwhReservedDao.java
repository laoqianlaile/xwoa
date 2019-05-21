package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.OptZwhReserved;

public class OptZwhReservedDao extends BaseDaoImpl<OptZwhReserved> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptZwhReservedDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("reservedId", CodeBook.EQUAL_HQL_ID);

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("fwh", CodeBook.LIKE_HQL_ID);

            filterField.put("idea", CodeBook.LIKE_HQL_ID);

            filterField.put("wjlx", CodeBook.EQUAL_HQL_ID);

            filterField.put("lsh", CodeBook.EQUAL_HQL_ID);

            filterField.put("lshYear", CodeBook.EQUAL_HQL_ID);

            filterField.put("createUser", CodeBook.LIKE_HQL_ID);

            filterField.put("createDate", CodeBook.LIKE_HQL_ID);

            filterField.put("reservedReason", CodeBook.LIKE_HQL_ID);

            filterField.put("useDate", CodeBook.LIKE_HQL_ID);

            filterField.put("isValid", CodeBook.LIKE_HQL_ID);
            
            filterField.put("largerLsh", "lsh>=to_number(?)");
            
            filterField.put("djIdCanNull", " (djId is null or djId=?)");
        }
        return filterField;
    }
}
