package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.OaAssetinformationInlogDao;
import com.centit.oa.po.OaAssetinformationInlog;
import com.centit.oa.service.OaAssetinformationInlogManager;

public class OaAssetinformationInlogManagerImpl extends BaseEntityManagerImpl<OaAssetinformationInlog>
	implements OaAssetinformationInlogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaAssetinformationInlogManager.class);

	private OaAssetinformationInlogDao oaAssetinformationInlogDao ;
	public void setOaAssetinformationInlogDao(OaAssetinformationInlogDao baseDao)
	{
		this.oaAssetinformationInlogDao = baseDao;
		setBaseDao(this.oaAssetinformationInlogDao);
	}
	/**
	 * 出库记录
	 */
    @Override
    public List<OaAssetinformationInlog> assetinList(String datacode,
            Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
       String shql = "  From OaAssetinformationInlog where no ="+ HQLUtils.buildHqlStringForSQL(datacode); ;
        return oaAssetinformationInlogDao.listObjects(shql,filterMap,pageDesc);
    }
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "ZCJK"+oaAssetinformationInlogDao.getNextKeyBySequence("S_OAASSETINFORMATIONINLOG", 12);
    }
	
}

