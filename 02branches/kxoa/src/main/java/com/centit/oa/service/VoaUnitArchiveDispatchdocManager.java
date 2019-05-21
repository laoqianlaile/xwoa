package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.VoaUnitArchiveDispatchdoc;

public interface VoaUnitArchiveDispatchdocManager extends BaseEntityManager<VoaUnitArchiveDispatchdoc> 
{
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,String currDjId);
}
