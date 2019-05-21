package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaAssetinformationBond;

public interface OaAssetinformationBondManager extends BaseEntityManager<OaAssetinformationBond> 
{

    public List<OaAssetinformationBond> listOaAssetinformation(String djid);

}
