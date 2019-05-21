package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.OptNewlyIdea;

public class OptNewlyIdeaDao extends BaseDaoImpl<OptNewlyIdea> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptNewlyIdeaDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djId", CodeBook.EQUAL_HQL_ID);

            filterField.put("nodeid", CodeBook.EQUAL_HQL_ID);

            filterField.put("nodeinstid", CodeBook.LIKE_HQL_ID);

            filterField.put("nodename", CodeBook.LIKE_HQL_ID);

            filterField.put("isdisplay"," isdisplay=to_number(?) ");

            filterField.put("orderno", CodeBook.LIKE_HQL_ID);

            filterField.put("url", CodeBook.LIKE_HQL_ID);

            filterField.put(CodeBook.ORDER_BY_HQL_ID , " orderno asc ");

        }
        return filterField;
    }
}
