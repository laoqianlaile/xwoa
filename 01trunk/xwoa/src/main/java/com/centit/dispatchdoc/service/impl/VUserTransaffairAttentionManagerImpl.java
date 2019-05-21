package com.centit.dispatchdoc.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.dao.VUserTransaffairAttentionDao;
import com.centit.dispatchdoc.po.VUserTransaffairAttention;
import com.centit.dispatchdoc.service.VUserTransaffairAttentionManager;

public class VUserTransaffairAttentionManagerImpl extends BaseEntityManagerImpl<VUserTransaffairAttention>
	implements VUserTransaffairAttentionManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VUserTransaffairAttentionManager.class);

	private VUserTransaffairAttentionDao VUserTransaffairAttentionDao ;
	public void setVUserTransaffairAttentionDao(VUserTransaffairAttentionDao baseDao)
	{
		this.VUserTransaffairAttentionDao = baseDao;
		setBaseDao(this.VUserTransaffairAttentionDao);
	}
	
    public List<VUserTransaffairAttention> listUserTransaffair(Map<String, Object> filterMap, PageDesc pageDesc) {
        return VUserTransaffairAttentionDao.listUserTransaffair(filterMap, pageDesc);
    }
}

