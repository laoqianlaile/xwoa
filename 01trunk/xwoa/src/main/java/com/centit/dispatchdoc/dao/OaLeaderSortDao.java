package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.po.Oaleadersort;

public class OaLeaderSortDao extends BaseDaoImpl<Oaleadersort> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaLeaderSortDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("lid", CodeBook.EQUAL_HQL_ID);

            filterField.put("usercode", CodeBook.LIKE_HQL_ID);

            filterField.put("stagecode", CodeBook.LIKE_HQL_ID);

            filterField.put("isvalid", CodeBook.LIKE_HQL_ID);

            filterField.put("leaderorder", CodeBook.LIKE_HQL_ID);

            filterField.put("teamrolecode", CodeBook.LIKE_HQL_ID);
            
            filterField.put("nodeInstId", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

}
