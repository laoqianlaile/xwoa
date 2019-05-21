package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.VoaUnitArchiveIncomedoc;
import com.centit.oa.dao.VoaUnitArchiveIncomedocDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.VoaUnitArchiveIncomedocManager;

public class VoaUnitArchiveIncomedocManagerImpl extends BaseEntityManagerImpl<VoaUnitArchiveIncomedoc>
	implements VoaUnitArchiveIncomedocManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VoaUnitArchiveIncomedocManager.class);

	private VoaUnitArchiveIncomedocDao voaUnitArchiveIncomedocDao ;
	public void setVoaUnitArchiveIncomedocDao(VoaUnitArchiveIncomedocDao baseDao)
	{
		this.voaUnitArchiveIncomedocDao = baseDao;
		setBaseDao(this.voaUnitArchiveIncomedocDao);
	}
    @Override
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,
            String currDjId) {
        return voaUnitArchiveIncomedocDao.findNeighbouringDjId(filterMap, currDjId);
    }
	
}

