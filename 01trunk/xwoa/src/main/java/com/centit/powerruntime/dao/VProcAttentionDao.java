package com.centit.powerruntime.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.powerruntime.po.VProcAttention;

public class VProcAttentionDao extends BaseDaoImpl<VProcAttention> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VProcAttentionDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("djId", CodeBook.EQUAL_HQL_ID);
            filterField.put("isAtt", CodeBook.LIKE_HQL_ID);
            filterField.put("userCode", CodeBook.EQUAL_HQL_ID);
            filterField.put("attsettime",
                    "attsettime like to_date(?,'yyyy-mm-dd')");
            filterField.put("attsetuser", CodeBook.LIKE_HQL_ID);
            filterField.put("transaffairname", CodeBook.LIKE_HQL_ID);
        }
        return filterField;
    }
}
