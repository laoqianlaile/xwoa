package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaCostInfo;
import com.centit.oa.dao.OaCostInfoDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaCostInfoManager;

public class OaCostInfoManagerImpl extends BaseEntityManagerImpl<OaCostInfo>
	implements OaCostInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaCostInfoManager.class);

	private OaCostInfoDao oaCostInfoDao ;
	public void setOaCostInfoDao(OaCostInfoDao baseDao)
	{
		this.oaCostInfoDao = baseDao;
		setBaseDao(this.oaCostInfoDao);
	}
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "FYJL"+oaCostInfoDao.getNextKeyBySequence("s_OaCostInfo", 12);
    }
	
}

