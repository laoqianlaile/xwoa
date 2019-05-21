package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaArrangeweek;

public class OaArrangeweekDao extends BaseDaoImpl<OaArrangeweek> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaArrangeweekDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("beginCreatetime",
                    " to_char(createtime,'yyyy-mm-dd HH:mm:ss')>=?");

            filterField.put("endCreatetime",
                    " to_char(createtime,'yyyy-mm-dd HH:mm:ss')<=?");
            filterField.put("depno", CodeBook.EQUAL_HQL_ID);
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " createtime asc");
        }
        return filterField;
    }
}
