package com.centit.mailclient.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.mailclient.po.MailProfile;

public class MailProfileDao extends BaseDaoImpl<MailProfile>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);
            filterField.put("email", CodeBook.EQUAL_HQL_ID);
            filterField.put("isActive", CodeBook.EQUAL_HQL_ID);
            filterField.put(CodeBook.ORDER_BY_HQL_ID," id asc");
        }
        return filterField;
    }
    
    public long countByEmail(String email,Long excludeId){
        String hql = "select count(*) from MailProfile where email=\'"+email+"\'";
        if(excludeId!=null){
            hql+=" and id<>"+excludeId;
        }
       return super.getSingleIntByHql(hql);
    }
}
