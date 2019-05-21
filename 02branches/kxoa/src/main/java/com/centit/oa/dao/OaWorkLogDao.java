package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaWorkLog;

public class OaWorkLogDao extends BaseDaoImpl<OaWorkLog> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaWorkLogDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("no", CodeBook.EQUAL_HQL_ID);

            filterField.put("infoType", CodeBook.EQUAL_HQL_ID);

            filterField.put("title", CodeBook.LIKE_HQL_ID);

//            filterField.put("releaseDate", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

            filterField.put("creater", CodeBook.EQUAL_HQL_ID);

            filterField.put("creatertime", CodeBook.LIKE_HQL_ID);

            filterField.put("isallowcomment", CodeBook.LIKE_HQL_ID);

            filterField.put("isshare", CodeBook.LIKE_HQL_ID);

            filterField.put("shares", CodeBook.LIKE_HQL_ID);

            filterField.put("releaseDate",
                    " releaseDate  like to_date(?,'yyyy-MM-dd hh24:mi:ss') ");

            filterField.put("releaseBegDate",
                    " releaseDate  >= to_date(?,'yyyy-MM-dd') ");
            filterField.put("releaseEndDate",
                    " releaseDate  <= to_date(?,'yyyy-MM-dd') ");
            filterField.put(CodeBook.ORDER_BY_HQL_ID, "releaseDate desc");

        }
        return filterField;
    }
}
