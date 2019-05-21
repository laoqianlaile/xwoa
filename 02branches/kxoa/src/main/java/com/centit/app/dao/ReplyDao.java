package com.centit.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.po.Reply;

public class ReplyDao extends BaseDaoImpl<Reply> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ReplyDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("replyid", CodeBook.EQUAL_HQL_ID);


            filterField.put("threadid", "thread.id = ?");
            
            filterField.put("forumname", "thread.forum.forumname = ?");
            filterField.put("forumboardcode", "thread.forum.boardcode = ?");
            filterField.put("threadname", "thread.titol = ?");

            filterField.put("reply", CodeBook.LIKE_HQL_ID);

            filterField.put("replytime", CodeBook.LIKE_HQL_ID);

            filterField.put("userid", CodeBook.LIKE_HQL_ID);

            filterField.put("username", CodeBook.LIKE_HQL_ID);

            filterField.put("annexnum", CodeBook.LIKE_HQL_ID);


            filterField.put(CodeBook.ORDER_BY_HQL_ID, "replytime desc");



        }
        return filterField;
    }

    @Override
    public void saveObject(Reply o) {
        if (null == o.getReplyid()) {
            o.setReplyid(getNextLongSequence("S_THREAD"));
        }
        super.saveObject(o);
    }
}
