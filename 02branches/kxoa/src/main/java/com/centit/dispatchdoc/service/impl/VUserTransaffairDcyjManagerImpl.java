package com.centit.dispatchdoc.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.dao.VUserTransaffairDcyjDao;
import com.centit.dispatchdoc.po.VUserTransaffairDcyj;
import com.centit.dispatchdoc.service.VUserTransaffairDcyjManager;

public class VUserTransaffairDcyjManagerImpl extends BaseEntityManagerImpl<VUserTransaffairDcyj>
	implements VUserTransaffairDcyjManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VUserTransaffairDcyjManager.class);

	private VUserTransaffairDcyjDao VUserTransaffairDcyjDao ;
	public void setVUserTransaffairDcyjDao(VUserTransaffairDcyjDao baseDao)
	{
		this.VUserTransaffairDcyjDao = baseDao;
		setBaseDao(this.VUserTransaffairDcyjDao);
	}
    public List<VUserTransaffairDcyj>  listUserTransaffair(Map<String, Object> filterMap, PageDesc pageDesc) {
        return VUserTransaffairDcyjDao.listUserTransaffair(filterMap, pageDesc);
    }
}

