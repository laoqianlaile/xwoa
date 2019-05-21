package com.centit.dispatchdoc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dispatchdoc.po.VoadDcdbNum;

public class VoadDcdbNumDao extends BaseDaoImpl<VoadDcdbNum> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VoadDcdbNumDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("flowInstId", CodeBook.EQUAL_HQL_ID);

            filterField.put("userCode", CodeBook.EQUAL_HQL_ID);

            filterField.put("flowOptName", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeName", CodeBook.LIKE_HQL_ID);

            filterField.put("timeLimit", CodeBook.LIKE_HQL_ID);

            filterField.put("nodeCode", CodeBook.LIKE_HQL_ID);

            filterField.put("djId", CodeBook.LIKE_HQL_ID);
            
            filterField.put("begTime", " createtime>= to_date(?, 'yyyy-mm-dd') ");
            
            filterField.put("endTime", " createtime<= to_date(?, 'yyyy-mm-dd')+1 ");
            //add by lay 2015-11-12  机构编码过滤,因为视图里面unitcode是多个值用逗号分隔的
            filterField.put("unitcode", CodeBook.LIKE_HQL_ID);
            //end add
        }
        return filterField;
    }
}
