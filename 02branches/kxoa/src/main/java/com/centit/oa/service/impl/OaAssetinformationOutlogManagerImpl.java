package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.OaAssetinformationOutlogDao;
import com.centit.oa.po.OaAssetinformationOutlog;
import com.centit.oa.service.OaAssetinformationOutlogManager;

public class OaAssetinformationOutlogManagerImpl extends BaseEntityManagerImpl<OaAssetinformationOutlog>
	implements OaAssetinformationOutlogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaAssetinformationOutlogManager.class);

	private OaAssetinformationOutlogDao oaAssetinformationOutlogDao ;
	public void setOaAssetinformationOutlogDao(OaAssetinformationOutlogDao baseDao)
	{
		this.oaAssetinformationOutlogDao = baseDao;
		setBaseDao(this.oaAssetinformationOutlogDao);
	}
	/**
	  * 生成主键
      */
	 @Override
     public String getNextKey() {
         // TODO Auto-generated method stub
         return "ZCCK"+oaAssetinformationOutlogDao.getNextKeyBySequence("S_OAASSETINFORMATIONOUTLOG", 12);   
	 }
	 /**
	  * 查询资产列表
	  */
	  @Override
	    public List<OaAssetinformationOutlog> assetinList(String no,
	            Map<String, Object> filterMap, PageDesc pageDesc) {
	        String shql = "  From OaAssetinformationOutlog where no ="+ HQLUtils.buildHqlStringForSQL(no); ;
	        return oaAssetinformationOutlogDao.listObjects(shql,filterMap,pageDesc);
	    }
}

