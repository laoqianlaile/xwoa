package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VOaInformation;

public interface VOaInformationManager extends BaseEntityManager<VOaInformation> 
{   
    /**
     * 查询除去大字段blob clob
     * @param filterMap
     * 
     * @return list
     */
    public List<VOaInformation> listVoainformation(Map<String, Object> filterMap);

    /**
     * 
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<VOaInformation> listInformationOfAttandList(
            Map<String, Object> filterMap, PageDesc pageDesc);
    /**
     * 查询未读通知
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<VOaInformation> listUnReadInfos(Map<String, Object> filterMap, PageDesc pageDesc);
}
