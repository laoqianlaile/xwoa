package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.ReadInfo;
import com.centit.dispatchdoc.dao.ReadInfoDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.ReadInfoManager;

public class ReadInfoManagerImpl extends BaseEntityManagerImpl<ReadInfo>
	implements ReadInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(ReadInfoManager.class);

	private ReadInfoDao readInfoDao ;
	public void setReadInfoDao(ReadInfoDao baseDao)
	{
		this.readInfoDao = baseDao;
		setBaseDao(this.readInfoDao);
	}
    @Override
    public String getNextkey(String zt) {
        return zt + readInfoDao.getNextKeyBySequence("S_READ_INFO", 14);
    }
	
}

