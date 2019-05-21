package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaWorkLog;

public interface OaWorkLogManager extends BaseEntityManager<OaWorkLog> 
{
    /**
     * 获日程安排列表
     */
    List<OaWorkLog> listObjects(Map<String, Object> filterMap, PageDesc pageDesc);
    List<OaWorkLog> listObjects(Map<String, Object> filterMap);
}
