package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaSmssendLog;

public class OaSmssendLogDao extends BaseDaoImpl<OaSmssendLog>
    {
        private static final long serialVersionUID = 1L;
        public static final Log log = LogFactory.getLog(OaSmssendLogDao.class);
        
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("no", CodeBook.EQUAL_HQL_ID);
            filterField.put("smsid" , CodeBook.EQUAL_HQL_ID);

            filterField.put("sendtime" , CodeBook.LIKE_HQL_ID);

            filterField.put("restoremessage" , CodeBook.LIKE_HQL_ID);

            filterField.put("content" , CodeBook.LIKE_HQL_ID);

            filterField.put("state" , CodeBook.EQUAL_HQL_ID);

            filterField.put("datasource" , CodeBook.LIKE_HQL_ID);

            filterField.put(CodeBook.ORDER_BY_HQL_ID, " sendtime desc");
            
        }
        return filterField;
    } 

}
