package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaAssetinformationInlog;

public interface OaAssetinformationInlogManager extends BaseEntityManager<OaAssetinformationInlog> 
{
   //查询进库记录
   public List<OaAssetinformationInlog> assetinList(String datacode,
            Map<String, Object> filterMap, PageDesc pageDesc);
   //生成主键
   public String getNextKey();

}
