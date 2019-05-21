package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaArchive;
import com.centit.oa.dao.OaArchiveDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaArchiveManager;

public class OaArchiveManagerImpl extends BaseEntityManagerImpl<OaArchive>
	implements OaArchiveManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaArchiveManager.class);

	private OaArchiveDao oaArchiveDao ;
	public void setOaArchiveDao(OaArchiveDao baseDao)
	{
		this.oaArchiveDao = baseDao;
		setBaseDao(this.oaArchiveDao);
	}
	//生成收文归档主键
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "SWGD"+oaArchiveDao.getNextKeyBySequence("S_SWGDINCOMEDOC", 12);
    }
    @Override
    public void updateTatanic(String duration) {
        // TODO Auto-generated method stub
        oaArchiveDao.updateTatanic(duration);
    }
    @Override
    public Long getNextMaxKey() {
        // TODO Auto-generated method stub
        return  oaArchiveDao.getNextMaxKey();
    }
    @Override
    public OaArchive getObjectByIds(Long filingannual, String titanic,String  belongUnitcode) {
        // TODO Auto-generated method stub
        return oaArchiveDao.getObjectByIds(filingannual,titanic,belongUnitcode);
    }
    @Override
    public List<OaArchive> listNdList() {
        // TODO Auto-generated method stub
        return oaArchiveDao.listNdList();
    }
	
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId){
        return oaArchiveDao.findNeighbouringDjId(filterMap, currDjId);
    }
    @Override
    public Long getNextTitanic(String filingannual, String belongUnitcode) {
     // TODO Auto-generated method stub
        return  oaArchiveDao.getNextTitanic( filingannual, belongUnitcode);
    }
}

