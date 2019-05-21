package com.centit.oa.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaAssetinformationBond;
import com.centit.oa.dao.OaAssetinformationBondDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaAssetinformationBondManager;

public class OaAssetinformationBondManagerImpl extends BaseEntityManagerImpl<OaAssetinformationBond>
	implements OaAssetinformationBondManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaAssetinformationBondManager.class);

	private OaAssetinformationBondDao oaAssetinformationBondDao ;
	public void setOaAssetinformationBondDao(OaAssetinformationBondDao baseDao)
	{
		this.oaAssetinformationBondDao = baseDao;
		setBaseDao(this.oaAssetinformationBondDao);
	}
    @Override
    public List<OaAssetinformationBond> listOaAssetinformation(String djid) {
        return oaAssetinformationBondDao.listOaAssetinformation(djid);
    }
}

