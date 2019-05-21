package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetingApply;
import com.centit.oa.po.VOaMeetingMaterialApply;
public interface OaMeetingApplyManager extends BaseEntityManager<OaMeetingApply> 
{
    public String getDjId();
    /**
     * 获取我的会议
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<OaMeetingApply> oaMeetingList(Map<String,Object> filterMap, PageDesc pageDesc);
}
