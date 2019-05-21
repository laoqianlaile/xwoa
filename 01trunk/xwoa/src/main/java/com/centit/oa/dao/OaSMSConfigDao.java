package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaSMSConfig;

public class OaSMSConfigDao extends BaseDaoImpl<OaSMSConfig> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OaSMSConfigDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("smsid", CodeBook.EQUAL_HQL_ID);
            filterField.put("status", CodeBook.EQUAL_HQL_ID);
            filterField.put("openOrClose", CodeBook.EQUAL_HQL_ID);
            filterField.put("operatorSource", CodeBook.EQUAL_HQL_ID);
            filterField.put(CodeBook.ORDER_BY_HQL_ID, "settime desc");

        }
        return filterField;
    }
    
    /**
     * 修改状态
     * state 为B时  需置其他记录状态值B-D
     */
    public void activeConfig(OaSMSConfig oaSMSConfig) {
        doExecuteHql("update OaSMSConfig t set t.status='D'   where t.smsid !=? and t.status='B'",new Object[]{oaSMSConfig.getSmsid()});
        
    }

    
}
