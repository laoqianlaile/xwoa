package com.centit.oa.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OptFileTransferReceive;

public class OptFileTransferReceiveDao extends BaseDaoImpl<OptFileTransferReceive>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if( filterField == null){
            filterField = new HashMap<String, String>();

            filterField.put("receiverCode" , CodeBook.EQUAL_HQL_ID);
            filterField.put("subject", CodeBook.LIKE_HQL_ID);
            filterField.put("beginTime", " createTime >= to_date(?,'yyyy-MM-dd HH24:MI')");
            filterField.put("endTime", " createTime <= to_date(?,'yyyy-MM-dd HH24:MI')");
            filterField.put(CodeBook.ORDER_BY_HQL_ID, " createTime desc");
        }
        return filterField;
    }

}
