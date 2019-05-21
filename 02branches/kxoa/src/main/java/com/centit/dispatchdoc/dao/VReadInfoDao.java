package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.VReadInfo;

public class VReadInfoDao extends BaseDaoImpl<VReadInfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VReadInfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("readNo", CodeBook.EQUAL_HQL_ID);

            filterField.put("incomeDocTitle", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDeptName", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDocNo", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDate", CodeBook.LIKE_HQL_ID);

            filterField.put("createDate", CodeBook.LIKE_HQL_ID);

            filterField.put("createName", CodeBook.LIKE_HQL_ID);

            filterField.put("incomeDocName", CodeBook.LIKE_HQL_ID);

            filterField.put("readInfoRole", CodeBook.LIKE_HQL_ID);

            filterField.put("docFileType", CodeBook.EQUAL_HQL_ID);

            filterField.put(CodeBook.ORDER_BY_HQL_ID, " createDate desc");

        }
        return filterField;
    }
}
