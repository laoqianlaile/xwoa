package com.centit.sys.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.po.UseroptLog;
import com.centit.sys.dao.UseroptLogDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.sys.service.UseroptLogManager;

public class UseroptLogManagerImpl extends BaseEntityManagerImpl<UseroptLog>
	implements UseroptLogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(UseroptLogManager.class);

	private UseroptLogDao useroptLogDao ;
	public void setUseroptLogDao(UseroptLogDao baseDao)
	{
		this.useroptLogDao = baseDao;
		setBaseDao(this.useroptLogDao);
	}
    @Override
    public String getNextNO(String ev) {
        // TODO Auto-generated method stub
        return ev + useroptLogDao.getNextKeyBySequence("S_F_USEROPT_LOG", 14);
    }
	
}

