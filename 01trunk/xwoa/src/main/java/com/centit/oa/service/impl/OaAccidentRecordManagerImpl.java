package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaAccidentRecord;
import com.centit.oa.dao.OaAccidentRecordDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaAccidentRecordManager;

public class OaAccidentRecordManagerImpl extends BaseEntityManagerImpl<OaAccidentRecord>
	implements OaAccidentRecordManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaAccidentRecordManager.class);

	private OaAccidentRecordDao oaAccidentRecordDao ;
	public void setOaAccidentRecordDao(OaAccidentRecordDao baseDao)
	{
		this.oaAccidentRecordDao = baseDao;
		setBaseDao(this.oaAccidentRecordDao);
	}
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "SGJL"+oaAccidentRecordDao.getNextKeyBySequence("s_OaAccidentRecord", 12);
    }
	
}

