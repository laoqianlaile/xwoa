package com.centit.powerruntime.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.po.OaUnitsLeaderLog;
import com.centit.powerruntime.dao.OaUnitsLeaderLogDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerruntime.service.OaUnitsLeaderLogManager;

public class OaUnitsLeaderLogManagerImpl extends BaseEntityManagerImpl<OaUnitsLeaderLog>
	implements OaUnitsLeaderLogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaUnitsLeaderLogManager.class);

	private OaUnitsLeaderLogDao oaUnitsLeaderLogDao ;
	public void setOaUnitsLeaderLogDao(OaUnitsLeaderLogDao baseDao)
	{
		this.oaUnitsLeaderLogDao = baseDao;
		setBaseDao(this.oaUnitsLeaderLogDao);
	}
    @Override
    public String getnextKey() {
        // TODO Auto-generated method stub
        return oaUnitsLeaderLogDao.getNextKeyBySequence("s_OaUnitsLeaderLog", 14);
    }
	
}

