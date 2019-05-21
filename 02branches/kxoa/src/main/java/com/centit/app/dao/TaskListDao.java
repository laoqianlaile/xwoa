package com.centit.app.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.centit.app.po.TaskList;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;

public class TaskListDao extends BaseDaoImpl<TaskList>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(TaskListDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("taskid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("taskowner" , CodeBook.LIKE_HQL_ID);

			filterField.put("tasktag" , CodeBook.LIKE_HQL_ID);

			filterField.put("taskrank" , CodeBook.LIKE_HQL_ID);

			filterField.put("taskstatus" , CodeBook.LIKE_HQL_ID);
			filterField.put("taskstatusNot" , "taskstatus <> ?");

			filterField.put("taskmemo" , CodeBook.LIKE_HQL_ID);

			filterField.put("tasktype" , CodeBook.LIKE_HQL_ID);

			filterField.put("optid" , CodeBook.LIKE_HQL_ID);

			filterField.put("optmethod" , CodeBook.LIKE_HQL_ID);

			filterField.put("opttag" , CodeBook.LIKE_HQL_ID);

			filterField.put("creator" , CodeBook.LIKE_HQL_ID);

			filterField.put("created" , CodeBook.LIKE_HQL_ID);

			filterField.put("planbegintimeStr" , "planbegintime >= to_date(?,'yyyy-mm-dd HH24:mi:ss')");

			filterField.put("planendtimeStr" , "planendtime <= to_date(?,'yyyy-mm-dd HH24:mi:ss')");

			filterField.put("begintime" , CodeBook.LIKE_HQL_ID);

			filterField.put("endtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("finishmemo" , CodeBook.LIKE_HQL_ID);

			filterField.put("noticesign" , CodeBook.LIKE_HQL_ID);

			filterField.put("lastnoticetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("taskdeadline" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	}

	@Override
	public void saveObject(TaskList o) {
		if(null == o.getTaskid()) {
			o.setTaskid(getNextLongSequence("S_TASK_LIST"));
		}
		getHibernateTemplate().merge(o);
	} 
	
	@SuppressWarnings("unchecked")
	public List<TaskList> listAll(Map<String, Object> params, PageDesc pageDesc) {
		DetachedCriteria criteria = getTaskListParams(params);
		
		criteria.setProjection(Projections.rowCount());
		pageDesc.setTotalRows(((Long)getHibernateTemplate().findByCriteria(criteria).get(0)).intValue());
		
		criteria = getTaskListParams(params);
		return getHibernateTemplate().findByCriteria(criteria, pageDesc.getRowStart(), pageDesc.getPageSize());
	}
	
	@SuppressWarnings("unchecked")
    private DetachedCriteria getTaskListParams(Map<String, Object> params) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TaskList.class);
		criteria.add(Restrictions.in("taskowner", (List<String>)params.get("usercodes")));
		criteria.add(Restrictions.ge("planbegintime", (Date)params.get("planbegintime")));
		criteria.add(Restrictions.le("planendtime", (Date)params.get("planendtime")));
		criteria.add(Restrictions.ne("taskstatus", (String)params.get("taskstatus")));
        return criteria;
    }
}
