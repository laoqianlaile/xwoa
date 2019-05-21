package com.centit.powerruntime.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.po.OaLeaderunits;
import com.centit.powerruntime.dao.OaLeaderunitsDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerruntime.service.OaLeaderunitsManager;

public class OaLeaderunitsManagerImpl extends BaseEntityManagerImpl<OaLeaderunits>
	implements OaLeaderunitsManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaLeaderunitsManager.class);

	private OaLeaderunitsDao oaLeaderunitsDao ;
	public void setOaLeaderunitsDao(OaLeaderunitsDao baseDao)
	{
		this.oaLeaderunitsDao = baseDao;
		setBaseDao(this.oaLeaderunitsDao);
	}
    @Override
    public void deleteObjects(String leadercode) {
        // TODO Auto-generated method stub
        oaLeaderunitsDao.deleteObjects(leadercode);
    }
	
}

