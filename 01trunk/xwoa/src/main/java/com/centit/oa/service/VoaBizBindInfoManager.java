package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VoaBizBindInfo;

public interface VoaBizBindInfoManager extends BaseEntityManager<VoaBizBindInfo> 
{

    public List<VoaBizBindInfo> listVoaBizBindInfo(String djId,String itemtype);
    public List<VoaBizBindInfo> listNotVoaBizBindInfo(String djId,String itemtype,String usercode);
    public List<VoaBizBindInfo> listVoaBizBindInfo(Map<String, Object> filterMap,
            PageDesc pageDesc);
}
