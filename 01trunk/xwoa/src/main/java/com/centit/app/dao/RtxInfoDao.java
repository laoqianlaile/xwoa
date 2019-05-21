package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.po.RtxInfo;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class RtxInfoDao extends BaseDaoImpl<RtxInfo>{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(RtxInfoDao.class);
    
public Map<String, String> getFilterField() {
    if( filterField == null){
        filterField = new HashMap<String, String>();

        filterField.put("no" , CodeBook.EQUAL_HQL_ID);

        filterField.put("djId" , CodeBook.EQUAL_HQL_ID);

        filterField.put("nodeId" , CodeBook.EQUAL_HQL_ID);

        filterField.put("createUserCode" , CodeBook.EQUAL_HQL_ID);

        filterField.put("createUserName" , CodeBook.LIKE_HQL_ID);

        filterField.put("createDate" , CodeBook.EQUAL_HQL_ID);

        filterField.put("infoContent" , CodeBook.LIKE_HQL_ID);

        filterField.put("isSend" , CodeBook.EQUAL_HQL_ID);

        filterField.put("sendDate" , CodeBook.EQUAL_HQL_ID);
        
       

    }
    return filterField;
} 
}
