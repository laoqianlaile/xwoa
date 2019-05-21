package com.centit.powerruntime.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.OaUnitsLeaderDao;
import com.centit.powerruntime.po.OaUnitsLeader;
import com.centit.powerruntime.service.OaUnitsLeaderManager;

public class OaUnitsLeaderManagerImpl extends BaseEntityManagerImpl<OaUnitsLeader>
	implements OaUnitsLeaderManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaUnitsLeaderManager.class);

	private OaUnitsLeaderDao oaUnitsLeaderDao ;
	public void setOaUnitsLeaderDao(OaUnitsLeaderDao baseDao)
	{
		this.oaUnitsLeaderDao = baseDao;
		setBaseDao(this.oaUnitsLeaderDao);
	}
    @Override
    public Map<String,String> getLeadercode(String usercode) {
        // TODO Auto-generated method stub
        return oaUnitsLeaderDao.getLeadercode(usercode);
    }
	
}

