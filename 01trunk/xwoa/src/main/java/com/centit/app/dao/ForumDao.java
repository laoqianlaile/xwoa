package com.centit.app.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.utils.PageDesc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.po.Forum;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.StringUtils;

public class ForumDao extends BaseDaoImpl<Forum> {
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(ForumDao.class);

	public Map<String, String> getFilterField() {
//		if (filterField == null) {
			filterField = new HashMap<String, String>();

			filterField.put("forumid", CodeBook.EQUAL_HQL_ID);

			filterField.put("boardcode", CodeBook.LIKE_HQL_ID);
			filterField.put("boardcodeEq", "boardcode = ?");

			filterField.put("forumname", CodeBook.LIKE_HQL_ID);
			filterField.put("forumnameEq", "forumname = ?");

			filterField.put("forumpic", CodeBook.LIKE_HQL_ID);

			filterField.put("announcement", CodeBook.LIKE_HQL_ID);

			filterField.put("joinright", CodeBook.LIKE_HQL_ID);

			filterField.put("viewright", CodeBook.LIKE_HQL_ID);

			filterField.put("postright", CodeBook.LIKE_HQL_ID);

			filterField.put("replyright", CodeBook.LIKE_HQL_ID);

			filterField.put("isforumer", CodeBook.LIKE_HQL_ID);

			filterField.put("createtime", CodeBook.LIKE_HQL_ID);

			filterField.put("mebernum", CodeBook.LIKE_HQL_ID);

			filterField.put("threadnum", CodeBook.LIKE_HQL_ID);

			filterField.put("replynum", CodeBook.LIKE_HQL_ID);

			filterField.put("forumstate", CodeBook.EQUAL_HQL_ID);

			filterField
					.put("VIEWRIGHT",
							"forumid in (select f.forumid from Forum f where f.viewright = 0 "
									+ "or (f.forumid in (select f1.forumid from Forum f1, ForumStaff fs where f1.forumid = fs.cid.forumid and fs.cid.usercode = ? and fs.userrole <> 2)))");

			filterField
					.put("JOINRIGHT",
							"forumid in (select f.forumid from Forum f where f.joinright = 1 "
									+ "and (f.forumid not in (select f1.forumid from Forum f1, ForumStaff fs where f1.forumid = fs.cid.forumid and fs.cid.usercode = ?)))");

			// filterField.put(CodeBook.ORDER_BY_HQL_ID, "createtime desc");

//		}
		return filterField;
	}


//	@SuppressWarnings("unchecked")
//	private List<Forum> find(final String hql, final Map<String, Object> params, final PageDesc pageDesc) {
//		return getHibernateTemplate().executeFind(new HibernateCallback<List<Forum>>() {
//
//			@Override
//			public List<Forum> doInHibernate(Session session) throws HibernateException, SQLException {
//				
//				Query query = session.createQuery(hql.toString());
//				if (params != null) {
//					query.setProperties(params);
//				}
//
//				pageDesc.setTotalRows(query.list().size());
//
//				return query.setFirstResult(pageDesc.getRowStart()).setMaxResults(pageDesc.getPageSize())
//						.list();
//			}
//		});
//	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listByJoinRight(Map<String, Object> params, final PageDesc pageDesc) {
		final Map<String, Object> paras = new HashMap<String, Object>();
		final StringBuilder hql = new StringBuilder();
		hql.append("select new map(f as forum, fs as fs ");
//
//		hql.append(") from Forum f, ForumStaff fs where f.forumid = fs.cid.forumid and ((fs.userrole = 2 and fs.cid.usercode = :usercode) and f.boardcode = 'FORUM' ");
//		hql.append("or (fs.userrole = 0 and fs.cid.usercode = :usercode) ");
//		hql.append("or f.forumid not in (select f1.forumid from Forum f1, ForumStaff fs where f1.forumid = fs.cid.forumid and fs.cid.usercode = :usercode)) order by f.createtime desc");

		
		hql.append(") from Forum f, ForumStaff fs where f.forumid = fs.cid.forumid " +
				"and f.forumstate = 1 and (f.viewright = 1 or f.postright = 1 or f.replyright =1) and f.boardcode = 'FORUM' ");
		
		if(StringUtils.hasText((String)params.get("forumname"))) {
			hql.append("and f.forumname like (:forumname) ");
			
			paras.put("forumname", "%" + params.get("forumname") + "%");
		}
		
		hql.append("order by f.createtime desc ");
		
		
		paras.put("usercode", params.get("JOINRIGHT"));

		List<Map<String, Object>> execute = getHibernateTemplate().execute(
				new HibernateCallback<List<Map<String, Object>>>() {

					@Override
					public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException,
							SQLException {
						Query query = session.createQuery(hql.toString());
						if (paras != null) {
							query.setProperties(paras);
						}

						pageDesc.setTotalRows(query.list().size());

						return query.setFirstResult(pageDesc.getRowStart()).setMaxResults(pageDesc.getPageSize())
								.list();
					}
				});

		return execute;
	}

	@SuppressWarnings("unchecked")
	public List<Forum> listConfirmApplyJoin(Forum forum, PageDesc pageDesc) {
		DetachedCriteria criteria = getForumCriteria(forum);

		criteria.setProjection(Projections.rowCount());

		pageDesc.setTotalRows(((Long) getHibernateTemplate().findByCriteria(criteria).get(0)).intValue());

		criteria = getForumCriteria(forum);

		return getHibernateTemplate().findByCriteria(criteria, pageDesc.getRowStart(), pageDesc.getPageSize());
	}

	@SuppressWarnings("static-method")
	private DetachedCriteria getForumCriteria(Forum forum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Forum.class);

		if (null != forum && null != forum.getForumid()) {
			criteria.add(Restrictions.eq("forumid", forum.getForumid()));
		}

		criteria.createAlias("forumStaffs", "fs");

		criteria.add(Restrictions.eq("fs.userrole", "2"));

		return criteria;
	}

	@Override
	public void saveObject(Forum o) {
		if (null == o.getForumid()) {
			o.setForumid(getNextLongSequence("S_FORUM"));
		}
		super.saveObject(o);
	}
}
