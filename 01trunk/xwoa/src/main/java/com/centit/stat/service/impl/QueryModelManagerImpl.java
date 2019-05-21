package com.centit.stat.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.stat.po.QueryModel;
import com.centit.stat.dao.QueryModelDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.stat.service.QueryModelManager;

public class QueryModelManagerImpl extends BaseEntityManagerImpl<QueryModel>
	implements QueryModelManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(QueryModelManager.class);

	private QueryModelDao queryModelDao ;
	public void setQueryModelDao(QueryModelDao baseDao)
	{
		this.queryModelDao = baseDao;
		setBaseDao(this.queryModelDao);
	}
	
}

