package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaAssetinformationOutlog;

public interface OaAssetinformationOutlogManager extends BaseEntityManager<OaAssetinformationOutlog> 
{
   /**
    * 生成主键
    * @return
    */
    public String getNextKey();
    /**
     * 查询出库记录
     * @param no 资产序号
     * @param filterMap
     * @param pageDesc
     * @return
     */

    public List<OaAssetinformationOutlog> assetinList(String no,
           Map<String, Object> filterMap, PageDesc pageDesc);

}
