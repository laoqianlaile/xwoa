package com.centit.app.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.centit.app.po.Thread;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;

public class ThreadDao extends BaseDaoImpl<Thread>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(ThreadDao.class);
		
	public Map<String, String> getFilterField() {
//		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("threadid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("forumid" , "forum.id = ?");
			filterField.put("forumid" , "forum.id = ?");

			filterField.put("titol" , CodeBook.LIKE_HQL_ID);
			filterField.put("threadType" , CodeBook.EQUAL_HQL_ID);

			filterField.put("content" , CodeBook.LIKE_HQL_ID);

			filterField.put("wirterid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("wirter" , CodeBook.LIKE_HQL_ID);

			filterField.put("posttime" , CodeBook.LIKE_HQL_ID);

            filterField.put("posttime_begin", "posttime >= to_date(?,'yyyy-mm-dd')");

            filterField.put("posttime_end", "posttime <= to_date(?,'yyyy-mm-dd')");


			filterField.put("viewnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("replnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("annexnum" , CodeBook.LIKE_HQL_ID);
			filterField.put("threadLock" , "(threadLock = ? or threadLock is null)");
			filterField.put("threadLockEq" , "threadLock = ?");

//			filterField.put(CodeBook.ORDER_BY_HQL_ID , "posttime desc");

//		}
		return filterField;
	}

        @Override
        public void saveObject(Thread o) {
            if (null == o.getThreadid()) {
                o.setThreadid(getNextLongSequence("S_THREAD"));
            }
            super.saveObject(o);
        }
        
        
        
        
        public List<Thread> listBySearch(Collection<Long> threadid, Long forumid, String usercode, PageDesc pageDesc) {
        	StringBuilder hql = new StringBuilder();
        	hql.append("from Thread t where t.threadid in (:threadid) and t.forum.forumstate = 1 ");
        	hql.append("and (t.forum.viewright = 0 or (t.forum.viewright = 1 and :usercode in ");
        	hql.append("(select fs.cid.usercode from Forum f, ForumStaff fs where f.forumid = fs.cid.forumid and f.forumid = :forumid and fs.userrole in ('0', '1'))");
        	hql.append(")) order by t.posttime desc");
        	
        	Map<String, Object> params = new HashMap<String, Object>();
        	params.put("threadid", threadid);
        	params.put("usercode", usercode);
        	params.put("forumid", forumid);
        	
        	
        	pageDesc.setTotalRows(count("select count(*) " + hql, params, pageDesc).intValue());
        	return list(hql.toString(), params, pageDesc);
        }
        
        
        private Long count(final String hql, final Map<String, Object> params, final PageDesc pageDesc) {
        	return getHibernateTemplate().execute(new HibernateCallback<Long>() {

				@Override
				public Long doInHibernate(Session session) throws HibernateException, SQLException {
					Query createQuery = session.createQuery(hql);
					createQuery.setProperties(params);
					
					return (Long)createQuery.iterate().next();
				}
			});
        }
        @SuppressWarnings("unchecked")
		private List<Thread> list(final String hql, final Map<String, Object> params, final PageDesc pageDesc) {
        	return getHibernateTemplate().executeFind(new HibernateCallback<List<Thread>>() {
        		
        		@Override
        		public List<Thread> doInHibernate(Session session) throws HibernateException, SQLException {
        			Query createQuery = session.createQuery(hql);
        			createQuery.setProperties(params);
        			
        			//pageDesc.setTotalRows(createQuery.list().size());
        			
        			return createQuery.setFirstResult(pageDesc.getRowStart()).setMaxResults(pageDesc.getPageSize())
					.list();
        		}
        	});
        }
        
        
    }




