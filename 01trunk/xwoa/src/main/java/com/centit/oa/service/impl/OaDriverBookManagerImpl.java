package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaDriverBook;
import com.centit.oa.dao.OaDriverBookDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaDriverBookManager;

public class OaDriverBookManagerImpl extends BaseEntityManagerImpl<OaDriverBook>
	implements OaDriverBookManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaDriverBookManager.class);

	private OaDriverBookDao oaDriverBookDao ;
	public void setOaDriverBookDao(OaDriverBookDao baseDao)
	{
		this.oaDriverBookDao = baseDao;
		setBaseDao(this.oaDriverBookDao);
	}

}

