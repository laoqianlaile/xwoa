package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaUnitIncomedoc;
import com.centit.oa.po.VoaUnitIncomedoc;
import com.centit.oa.dao.OaUnitIncomedocDao;
import com.centit.oa.dao.VoaUnitIncomedocDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaUnitIncomedocManager;

public class OaUnitIncomedocManagerImpl extends BaseEntityManagerImpl<OaUnitIncomedoc>
	implements OaUnitIncomedocManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaUnitIncomedocManager.class);

	private OaUnitIncomedocDao oaUnitIncomedocDao ;
	private VoaUnitIncomedocDao voaUnitIncomedocDao;
	public void setOaUnitIncomedocDao(OaUnitIncomedocDao baseDao)
	{
		this.oaUnitIncomedocDao = baseDao;
		setBaseDao(this.oaUnitIncomedocDao);
	}
    public void setVoaUnitIncomedocDao(VoaUnitIncomedocDao voaUnitIncomedocDao) {
        this.voaUnitIncomedocDao = voaUnitIncomedocDao;
    }

    @Override
    public List<VoaUnitIncomedoc> listVoaBizBindInfo(
            Map<String, Object> filterMap,PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return (List<VoaUnitIncomedoc>)voaUnitIncomedocDao.listObjects(filterMap, pageDesc);
    }
    //生成收文归档主键
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "SWGD"+oaUnitIncomedocDao.getNextKeyBySequence("S_SWGDINCOMEDOC", 12);
    }
    @Override
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,
            String currDjId) {
        return voaUnitIncomedocDao.findNeighbouringDjId(filterMap, currDjId);
    }
	
}

