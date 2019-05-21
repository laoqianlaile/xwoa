package com.centit.dispatchdoc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.VReadInfoDao;
import com.centit.dispatchdoc.po.VReadInfo;
import com.centit.dispatchdoc.service.ReadInfoManager;
import com.centit.dispatchdoc.service.VReadInfoManager;

public class VReadInfoManagerImpl extends BaseEntityManagerImpl<VReadInfo>
	implements VReadInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(ReadInfoManager.class);

	private VReadInfoDao vReadInfoDao ;
	public void setVReadInfoDao(VReadInfoDao baseDao)
	{
		this.vReadInfoDao = baseDao;
		setBaseDao(this.vReadInfoDao);
	}
    @Override
    public String getNextkey(String zt) {
        return zt + vReadInfoDao.getNextKeyBySequence("S_READ_INFO", 14);
    }
	
}

