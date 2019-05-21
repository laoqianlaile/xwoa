package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VUserTransaffairDcyjGr;

public interface VUserTransaffairDcyjGrManager extends BaseEntityManager<VUserTransaffairDcyjGr> 
{
    public List<VUserTransaffairDcyjGr> listUserTransaffair(Map<String, Object> filterMap, PageDesc pageDesc);
}
