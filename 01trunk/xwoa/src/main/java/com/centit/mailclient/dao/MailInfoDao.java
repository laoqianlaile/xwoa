package com.centit.mailclient.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.mailclient.po.MailInfo;

public class MailInfoDao extends BaseDaoImpl<MailInfo>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);
            filterField.put("email", CodeBook.EQUAL_HQL_ID);
            filterField.put("location", CodeBook.EQUAL_HQL_ID);
            filterField.put("isvalid", CodeBook.EQUAL_HQL_ID);
            filterField.put("messageId", CodeBook.EQUAL_HQL_ID);
            filterField.put(CodeBook.ORDER_BY_HQL_ID," sendTime desc");
        }
        return filterField;
    }

    public long countByMessageIdAndEmail(String messageId,String email){
        String hql = "select count(*) from MailInfo where messageId=\'"+messageId+"\' and email=\'"+email+"\'";
       return super.getSingleIntByHql(hql);
    }
}
