package com.centit.oa.service;


import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaAssetinformation;

public interface OaAssetinformationManager extends
        BaseEntityManager<OaAssetinformation> {

    public String getNextKey();

}
