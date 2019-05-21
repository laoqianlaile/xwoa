package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.dao.OaUserinfoDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaUserinfoManager;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.po.FUserinfo;

public class OaUserinfoManagerImpl extends BaseEntityManagerImpl<OaUserinfo>
	implements OaUserinfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaUserinfoManager.class);

	private OaUserinfoDao oaUserinfoDao ;
	private UserInfoDao userInfoDao;
	public void setOaUserinfoDao(OaUserinfoDao baseDao)
	{
		this.oaUserinfoDao = baseDao;
		setBaseDao(this.oaUserinfoDao);
	}
    
   public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    //查询出部门下面的人员
    @Override
    public List<FUserinfo> getUserUnderUserUnit(Map<String, Object> filterMap,String bodycode) {
        // TODO Auto-generated method stub
        return userInfoDao.getUserUnderUserUnit(filterMap,bodycode);
    }
    public List<OaUserinfo> listOauserinfoObjects(Map<String, Object> filterMap){
        return oaUserinfoDao.listOauserinfoObjects(filterMap);
    }

    @Override
    public List<OaUserinfo> listUserInfos(Map<String, Object> filterMap) {
        return oaUserinfoDao.listUserInfos(filterMap);
    }
}

