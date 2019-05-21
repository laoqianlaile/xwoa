package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaSpecialBudget;
import com.centit.oa.dao.OaSpecialBudgetDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaSpecialBudgetManager;

public class OaSpecialBudgetManagerImpl extends BaseEntityManagerImpl<OaSpecialBudget>
	implements OaSpecialBudgetManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaSpecialBudgetManager.class);

	private OaSpecialBudgetDao oaSpecialBudgetDao ;
	public void setOaSpecialBudgetDao(OaSpecialBudgetDao baseDao)
	{
		this.oaSpecialBudgetDao = baseDao;
		setBaseDao(this.oaSpecialBudgetDao);
	}
	
}

