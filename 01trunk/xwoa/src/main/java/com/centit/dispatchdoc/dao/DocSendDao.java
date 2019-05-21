package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.DocSend;

public class DocSendDao extends BaseDaoImpl<DocSend> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(DocSendDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("sendId", CodeBook.EQUAL_HQL_ID);

            filterField.put("djId", CodeBook.LIKE_HQL_ID);

            filterField.put("sendType", CodeBook.LIKE_HQL_ID);

            filterField.put("unitType", CodeBook.LIKE_HQL_ID);

            filterField.put("unitcode", CodeBook.LIKE_HQL_ID);

            filterField.put("orgCode", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }
}
