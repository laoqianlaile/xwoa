package com.centit.powerruntime.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.po.MipLog;
import com.centit.powerruntime.dao.MipLogDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.powerruntime.service.MipLogManager;

public class MipLogManagerImpl extends BaseEntityManagerImpl<MipLog>
	implements MipLogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(MipLogManager.class);

	private MipLogDao mipLogDao ;
	public void setMipLogDao(MipLogDao baseDao)
	{
		this.mipLogDao = baseDao;
		setBaseDao(this.mipLogDao);
	}
    @Override
    public String nextKey() {
        // TODO Auto-generated method stub
        return "MIP"+mipLogDao.getNextKeyBySequence("S_MipLog", 14);
    }
	
}

