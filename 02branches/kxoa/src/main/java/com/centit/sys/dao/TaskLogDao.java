package com.centit.sys.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.sys.po.TaskLog;

public class TaskLogDao extends BaseDaoImpl<TaskLog> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(TaskLogDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("taskLogId", CodeBook.EQUAL_HQL_ID);


            filterField.put("type", CodeBook.EQUAL_HQL_ID);

            filterField.put("taskDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("taskRun", CodeBook.EQUAL_HQL_ID);

        }
        return filterField;
    }


    public void deleteIsRun() {
        final String hql = "delete from TaskLog tl where tl.taskRun = 'T'";

        super.doExecuteHql(hql);
    }

    @Override
    public void saveObject(TaskLog o) {
        if (null == o.getTaskLogId()){
            o.setTaskLogId(this.getNextLongSequence("S_TASK_LOG"));
        }
        super.saveObject(o);
    }
    
    
}
