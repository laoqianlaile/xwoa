package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetingmaterial;

public interface OaMeetingmaterialManager extends BaseEntityManager<OaMeetingmaterial> 
{
    public String getDjId();
    public List<OaMeetingmaterial> listOaMeetingmaterial(Map<String, Object> filterMap, PageDesc pageDesc);
    
    
    /**
     * 根据业务id获取所有记录
     * @param djId
     * @return
     */
    public List<OaMeetingmaterial> findListByDjId(String mId);
}
