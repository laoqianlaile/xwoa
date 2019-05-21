package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaBudgetstravel;
import com.centit.oa.dao.OaBudgetstravelDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaBudgetstravelManager;

public class OaBudgetstravelManagerImpl extends BaseEntityManagerImpl<OaBudgetstravel>
	implements OaBudgetstravelManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaBudgetstravelManager.class);

	private OaBudgetstravelDao oaBudgetstravelDao ;
	public void setOaBudgetstravelDao(OaBudgetstravelDao baseDao)
	{
		this.oaBudgetstravelDao = baseDao;
		setBaseDao(this.oaBudgetstravelDao);
	}
	
}

