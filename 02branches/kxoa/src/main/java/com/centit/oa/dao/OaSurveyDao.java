package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.po.OaSurvey;

public class OaSurveyDao extends BaseDaoImpl<OaSurvey> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaSurveyDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("djid", CodeBook.EQUAL_HQL_ID);

            filterField.put("title", CodeBook.LIKE_HQL_ID);

            filterField.put("reType", CodeBook.EQUAL_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

            filterField.put("begtime", CodeBook.LIKE_HQL_ID);

            filterField.put("endtime", CodeBook.LIKE_HQL_ID);

            filterField.put("creater", CodeBook.LIKE_HQL_ID);

            filterField.put("createtime", CodeBook.LIKE_HQL_ID);

            filterField.put("createRemark", CodeBook.LIKE_HQL_ID);

            filterField.put("createDepno", CodeBook.LIKE_HQL_ID);

            filterField.put("thesign", CodeBook.EQUAL_HQL_ID);

            filterField.put("sendusers", CodeBook.LIKE_HQL_ID);

            filterField.put("isautoend", CodeBook.EQUAL_HQL_ID);

            filterField.put("isviewresult", CodeBook.EQUAL_HQL_ID);

            filterField.put("isbookn", CodeBook.EQUAL_HQL_ID);
            // 调查开始时间
            filterField.put("begtimeBegin",
                    " begtime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");

            filterField.put("begtimeEnd",
                    " begtime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
            // 调查结束时间
            filterField.put("endtimeBegin",
                    " endtime >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");

            filterField.put("endtimeEnd",
                    " endtime <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");

            filterField.put("NP_thesign", " thesign != '4' ");

        }
        return filterField;
    }

    /**
     * 系統自動結束調查
     */
    public void autoEnd() {
        doExecuteHql("update OaSurvey t set t.thesign='3'   where t.isautoend ='Y' and  t.thesign not in ('3','4') and t.endtime<=sysdate ");

    }
}
