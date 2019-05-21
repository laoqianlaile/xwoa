package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaTrafficRecord;
import com.centit.oa.dao.OaTrafficRecordDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaTrafficRecordManager;

public class OaTrafficRecordManagerImpl extends BaseEntityManagerImpl<OaTrafficRecord>
	implements OaTrafficRecordManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaTrafficRecordManager.class);

	private OaTrafficRecordDao oaTrafficRecordDao ;
	public void setOaTrafficRecordDao(OaTrafficRecordDao baseDao)
	{
		this.oaTrafficRecordDao = baseDao;
		setBaseDao(this.oaTrafficRecordDao);
	}
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "WZJL"+oaTrafficRecordDao.getNextKeyBySequence("s_OaTrafficRecord", 12);
    }
	
}

