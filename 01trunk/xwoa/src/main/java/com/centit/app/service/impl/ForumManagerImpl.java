package com.centit.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.centit.app.dao.ForumDao;
import com.centit.app.dao.ForumStaffDao;
import com.centit.app.po.Forum;
import com.centit.app.po.ForumStaff;
import com.centit.app.po.ForumType;
import com.centit.app.service.ForumManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;

public class ForumManagerImpl extends BaseEntityManagerImpl<Forum> implements ForumManager {
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(ForumManager.class);

	// private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private ForumDao forumDao;

	public void setForumDao(ForumDao baseDao) {
		this.forumDao = baseDao;
		setBaseDao(this.forumDao);
	}

	private ForumStaffDao forumStaffDao;

	public void setForumStaffDao(ForumStaffDao forumStaffDao) {
		this.forumStaffDao = forumStaffDao;
	}

	@Override
	public void saveObject(Forum forum, ForumStaff forumStaff, List<ForumType> forumTypes) {
		// 创建时
		if (null == forum.getForumid()) {
			forum.setCreatetime(new Date());
			forum.setMebernum(1L);

			baseDao.saveObject(forum);
			
			if(null != forumStaff) {
				
				forumStaff.getCid().setForumid(forum.getForumid());
				forumStaff.setJointime(new Date());
				forumStaff.setUserrole("1");
				
				forum.getForumStaffs().add(forumStaff);
			}
			
		}

		baseDao.saveObject(forum);
		
		if(!CollectionUtils.isEmpty(forumTypes)) {
			for (ForumType forumType : forumTypes) {
				forumType.getCid().setForumid(forum.getForumid());
			}
			forum.replaceForumTypes(forumTypes);
			baseDao.saveObject(forum);
		}
		
	}

	@Override
	public void saveObjectByInOut(Forum forum, ForumStaff forumStaff) {
		ForumStaff object = forumStaffDao.getObject(forumStaff);

		forum = getObject(forum);

		if (null != object) {

			forum.setMebernum(forum.getMebernum() - 1);

			forumStaffDao.deleteObject(forumStaff);
		} else {
			// 判断是否需要进行审批
			if (0 == forum.getJoinright()) {
				forumStaff.setUserrole("0");
			} else {
				forumStaff.setUserrole("2");
			}
			forumStaff.setJointime(new Date());
			// forumStaff.setUserrole("0");

			forum.setMebernum(forum.getMebernum() + 1);
			forumStaffDao.saveObject(forumStaff);
		}

		saveObject(forum);
	}

	@Override
	public List<Forum> listConfirmApplyJoin(Forum forum, PageDesc pageDesc) {
		return forumDao.listConfirmApplyJoin(forum, pageDesc);
	}

	@Override
	public Map<Forum, Integer> listByJoinRight(Map<String, Object> params, PageDesc pageDesc) {
		List<Map<String, Object>> listByJoinRight = forumDao.listByJoinRight(params, pageDesc);

		Map<Forum, Integer> joinRight = new LinkedHashMap<Forum, Integer>();
		for (Map<String, Object> map : listByJoinRight) {
			Forum f = (Forum) map.get("forum");
			ForumStaff fs = (ForumStaff) map.get("fs");

			// 标识1=待申请，2=待审批，3=退出,初始化时最低级别

			if (!joinRight.containsKey(f)) {
				joinRight.put(f, 1);
			}

			int flag = 1;
			if (((String) params.get("JOINRIGHT")).equals(fs.getCid().getUsercode())) {
				if ("2".equals(fs.getUserrole())) {
					flag = 2;
				} else {
					flag = 3;
				}
			}

			if (flag > joinRight.get(f)) {
				joinRight.put(f, flag);
			}

		}

		return joinRight;
	}

	@Override
	public Forum getExternalUse(String boardcode, String forumname) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("boardcodeEq", boardcode);
		filterMap.put("forumnameEq", forumname);

		List<Forum> forums = baseDao.listObjects(filterMap);
		if (!CollectionUtils.isEmpty(forums)) {
			return forums.get(0);
		}

		Forum forum = new Forum();
		forum.setCreatetime(new Date());
		forum.setBoardcode(boardcode);
		forum.setForumname(forumname);
		forum.setForumstate("2");

		baseDao.saveObject(forum);

		return forum;
	}

	@Override
	public void deleteObject(Forum o) {
		forumStaffDao.deleteByForum(o.getForumid());
		super.deleteObject(o);
	}

}
