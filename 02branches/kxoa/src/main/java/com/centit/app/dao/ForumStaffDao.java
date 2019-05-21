package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.po.ForumStaff;

public class ForumStaffDao extends BaseDaoImpl<ForumStaff> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ForumStaffDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("forumid", CodeBook.EQUAL_HQL_ID);
            
            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);
            filterField.put("forumidEq", "cid.forumid = ?");
            filterField.put("usercodeEq", "cid.usercode = ?");


            filterField.put("userrole", CodeBook.EQUAL_HQL_ID);

            filterField.put("jointime", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }


    public void deleteByForum(Long forumid) {
        final String hql = "delete from ForumStaff fs where fs.forumid = ?";
    }
    
    
}
