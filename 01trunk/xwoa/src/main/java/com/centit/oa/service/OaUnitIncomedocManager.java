package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaUnitIncomedoc;
import com.centit.oa.po.VoaUnitIncomedoc;

public interface OaUnitIncomedocManager extends BaseEntityManager<OaUnitIncomedoc> 
{
    //查询收文归档视图列表
    public List<VoaUnitIncomedoc> listVoaBizBindInfo(Map<String, Object> filterMap,PageDesc pageDesc);
    //生成收文归档主键
    public String getNextKey();
    
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId);
}
