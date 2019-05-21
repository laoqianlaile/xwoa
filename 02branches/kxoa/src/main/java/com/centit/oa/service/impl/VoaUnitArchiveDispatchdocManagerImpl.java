package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.VoaUnitArchiveDispatchdoc;
import com.centit.oa.dao.VoaUnitArchiveDispatchdocDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.VoaUnitArchiveDispatchdocManager;

public class VoaUnitArchiveDispatchdocManagerImpl extends BaseEntityManagerImpl<VoaUnitArchiveDispatchdoc>
	implements VoaUnitArchiveDispatchdocManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VoaUnitArchiveDispatchdocManager.class);

	private VoaUnitArchiveDispatchdocDao voaUnitArchiveDispatchdocDao ;
	public void setVoaUnitArchiveDispatchdocDao(VoaUnitArchiveDispatchdocDao baseDao)
	{
		this.voaUnitArchiveDispatchdocDao = baseDao;
		setBaseDao(this.voaUnitArchiveDispatchdocDao);
	}
	
	 public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId){
	     return voaUnitArchiveDispatchdocDao.findNeighbouringDjId(filterMap, currDjId);
	 }
}

