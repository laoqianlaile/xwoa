package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VoaBizBindInfo;
import com.centit.oa.dao.VoaBizBindInfoDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.VoaBizBindInfoManager;

public class VoaBizBindInfoManagerImpl extends BaseEntityManagerImpl<VoaBizBindInfo>
	implements VoaBizBindInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VoaBizBindInfoManager.class);

	private VoaBizBindInfoDao voaBizBindInfoDao ;
	public void setVoaBizBindInfoDao(VoaBizBindInfoDao baseDao)
	{
		this.voaBizBindInfoDao = baseDao;
		setBaseDao(this.voaBizBindInfoDao);
	}
    @Override
    public List<VoaBizBindInfo> listVoaBizBindInfo(String djId,String itemtype) {
        // TODO Auto-generated method stub
        return voaBizBindInfoDao.listVoaBizBindInfo(djId,itemtype);
    }
    public List<VoaBizBindInfo> listNotVoaBizBindInfo(String djId,String itemtype,String usercode) {
        // TODO Auto-generated method stub
        return voaBizBindInfoDao.listNotVoaBizBindInfo(djId,itemtype,usercode);
    }
    @Override
    public List<VoaBizBindInfo> listVoaBizBindInfo(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return voaBizBindInfoDao.listVoaBizBindInfo(filterMap, pageDesc);
    }
	
}

