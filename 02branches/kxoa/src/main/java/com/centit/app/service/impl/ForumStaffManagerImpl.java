package com.centit.app.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.app.po.Forum;
import com.centit.app.po.ForumStaff;
import com.centit.app.dao.ForumDao;
import com.centit.app.dao.ForumStaffDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.service.ForumStaffManager;

import java.util.Date;
import java.util.List;

public class ForumStaffManagerImpl extends BaseEntityManagerImpl<ForumStaff>
	implements ForumStaffManager{
	public ForumDao getForumDao() {
        return forumDao;
    }

    public void setForumDao(ForumDao forumDao) {
        this.forumDao = forumDao;
    }

    private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(ForumStaffManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();
	private ForumDao forumDao;
	private ForumStaffDao forumStaffDao ;
	public void setForumStaffDao(ForumStaffDao baseDao)
	{
		this.forumStaffDao = baseDao;
		setBaseDao(this.forumStaffDao);
	}

    @Override
    public void saveObjects(List<ForumStaff> forumStaffs) {
        for (ForumStaff forumStaff : forumStaffs) {
            forumStaff.setUserrole("0");
            forumStaff.setJointime(new Date());
            //管理员审批进入论坛的申请用户时，论坛的成员数加一
            Forum forum= this.forumDao.getObjectById(forumStaff.getCid().getForumid());
            forum.setMebernum(forum.getMebernum()+1);
            this.forumDao.saveObject(forum);
            
            saveObject(forumStaff);
        }
    }
}

