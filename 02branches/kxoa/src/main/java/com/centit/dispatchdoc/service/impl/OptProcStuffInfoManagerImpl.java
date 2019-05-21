package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.OptProcStuffInfo;
import com.centit.dispatchdoc.dao.OptProcStuffInfoDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.dispatchdoc.service.OptProcStuffInfoManager;
import com.centit.oa.po.OaCommonType;

public class OptProcStuffInfoManagerImpl extends BaseEntityManagerImpl<OptProcStuffInfo>
	implements OptProcStuffInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OptProcStuffInfoManager.class);

	private OptProcStuffInfoDao optProcStuffInfoDao ;
	public void setOptProcStuffInfoDao(OptProcStuffInfoDao baseDao)
	{
		this.optProcStuffInfoDao = baseDao;
		setBaseDao(this.optProcStuffInfoDao);
	}
	
}

