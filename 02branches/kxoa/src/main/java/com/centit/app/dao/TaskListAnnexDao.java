package com.centit.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.po.TaskListAnnex;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;


public class TaskListAnnexDao extends BaseDaoImpl<TaskListAnnex>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(TaskListAnnexDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("taskid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("fileinfoFs" , CodeBook.EQUAL_HQL_ID);

			filterField.put("taskList" , CodeBook.EQUAL_HQL_ID);

			

		}
		return filterField;
	}

	public void saveBatch(List<TaskListAnnex> taskListAnnexs) {
	    for (int i = 0; i < taskListAnnexs.size(); i++) {
            saveObject(taskListAnnexs.get(i));
            
            if(0 == 20 % i) {
                getHibernateTemplate().flush();
                getHibernateTemplate().clear();
            }
        }
	}
}
