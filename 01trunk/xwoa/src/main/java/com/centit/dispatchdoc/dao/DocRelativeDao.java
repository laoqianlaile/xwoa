package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.DocRelative;

public class DocRelativeDao extends BaseDaoImpl<DocRelative> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(DocRelativeDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("incomeNo", CodeBook.EQUAL_HQL_ID);

            filterField.put("dispatchNo", CodeBook.EQUAL_HQL_ID);

            filterField.put("relativeType", CodeBook.LIKE_HQL_ID);

            filterField.put("relativeDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("cid.dispatchNo", CodeBook.EQUAL_HQL_ID);

            filterField.put("cid.incomeNo", CodeBook.EQUAL_HQL_ID);

        }
        return filterField;
    }

    @SuppressWarnings("unchecked")
    public List<DocRelative> getObjectsByDispatchNo(String dispatchNo) {
        return getHibernateTemplate().find(
                "FROM DocRelative where cid.dispatchNo= ? order by cid.incomeNo desc", dispatchNo);
    }

    @SuppressWarnings("unchecked")
    public List<DocRelative> getObjectsByIncomeNo(String incomeNo) {
        return getHibernateTemplate().find(
                "FROM DocRelative where cid.incomeNo= ? order by cid.dispatchNo desc", incomeNo);
    }
}
