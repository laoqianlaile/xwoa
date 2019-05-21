package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VoadDcdbNum;
import com.centit.oa.po.OaSupervise;

public interface OaSuperviseManager extends BaseEntityManager<OaSupervise> 
{

    public  String getNextKey();

    public List<VoadDcdbNum> getdcdbnum(String supdjid);

    public List<OaSupervise> getSuplist(String state);

    public Long getLastVersion(String flowcode);

    public List<OaSupervise> listOaSupervise(Map<String, Object> filterMap,
            PageDesc pageDesc);

}
