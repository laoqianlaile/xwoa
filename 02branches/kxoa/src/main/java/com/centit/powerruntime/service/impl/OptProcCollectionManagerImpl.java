package com.centit.powerruntime.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.po.OptProcCollection;
import com.centit.powerruntime.dao.OptProcCollectionDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerruntime.service.OptProcCollectionManager;

public class OptProcCollectionManagerImpl extends BaseEntityManagerImpl<OptProcCollection>
	implements OptProcCollectionManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OptProcCollectionManager.class);

	private OptProcCollectionDao optProcCollectionDao ;
	public void setOptProcCollectionDao(OptProcCollectionDao baseDao)
	{
		this.optProcCollectionDao = baseDao;
		setBaseDao(this.optProcCollectionDao);
	}
	
}

