package com.centit.powerruntime.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.VOptProcCollection;

public interface VOptProcCollectionManager extends BaseEntityManager<VOptProcCollection> 
{
    /**
     * 获取相邻的djId
     * @param params
     * @return
     */
    public List<String> findNeighbouringDjId(Map<String,Object> params,String currDjId); 
}
