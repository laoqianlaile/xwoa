package com.centit.powerruntime.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.po.VOptProcCollection;
import com.centit.powerruntime.dao.VOptProcCollectionDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.powerruntime.service.VOptProcCollectionManager;

public class VOptProcCollectionManagerImpl extends BaseEntityManagerImpl<VOptProcCollection>
	implements VOptProcCollectionManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VOptProcCollectionManager.class);

	private VOptProcCollectionDao VOptProcCollectionDao ;
	public void setVOptProcCollectionDao(VOptProcCollectionDao baseDao)
	{
		this.VOptProcCollectionDao = baseDao;
		setBaseDao(this.VOptProcCollectionDao);
	}
    @Override
    public List<String> findNeighbouringDjId(Map<String, Object> params,
            String currDjId) {
        return VOptProcCollectionDao.findNeighbouringDjId(params, currDjId);
    }
	
}

