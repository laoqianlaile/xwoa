package com.centit.oa.service.impl;


import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaAssetinformation;
import com.centit.oa.dao.OaAssetinformationDao;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaAssetinformationManager;

public class OaAssetinformationManagerImpl extends BaseEntityManagerImpl<OaAssetinformation>
	implements OaAssetinformationManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaAssetinformationManager.class);

	private OaAssetinformationDao oaAssetinformationDao ;
	public void setOaAssetinformationDao(OaAssetinformationDao baseDao)
	{
		this.oaAssetinformationDao = baseDao;
		setBaseDao(this.oaAssetinformationDao);
	}
   /**
    * 生成主键
    */
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "ZCGL"+oaAssetinformationDao.getNextKeyBySequence("S_OAASSETINFORMATION", 12);
    }
	
}

