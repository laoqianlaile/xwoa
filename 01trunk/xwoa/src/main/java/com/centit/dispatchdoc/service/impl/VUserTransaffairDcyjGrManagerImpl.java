package com.centit.dispatchdoc.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.dao.VUserTransaffairDcyjGrDao;
import com.centit.dispatchdoc.po.VUserTransaffairDcyjGr;
import com.centit.dispatchdoc.service.VUserTransaffairDcyjGrManager;

public class VUserTransaffairDcyjGrManagerImpl extends BaseEntityManagerImpl<VUserTransaffairDcyjGr>
	implements VUserTransaffairDcyjGrManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VUserTransaffairDcyjGrManager.class);

	private VUserTransaffairDcyjGrDao VUserTransaffairDcyjGrDao ;
	public void setVUserTransaffairDcyjGrDao(VUserTransaffairDcyjGrDao baseDao)
	{
		this.VUserTransaffairDcyjGrDao = baseDao;
		setBaseDao(this.VUserTransaffairDcyjGrDao);
	}
    public List<VUserTransaffairDcyjGr>  listUserTransaffair(Map<String, Object> filterMap, PageDesc pageDesc) {
        return VUserTransaffairDcyjGrDao.listUserTransaffair(filterMap, pageDesc);
    }
}

