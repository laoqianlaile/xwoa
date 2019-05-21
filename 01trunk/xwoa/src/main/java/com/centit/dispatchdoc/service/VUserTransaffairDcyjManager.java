package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VUserTransaffairDcyj;

public interface VUserTransaffairDcyjManager extends BaseEntityManager<VUserTransaffairDcyj> 
{
    public List<VUserTransaffairDcyj> listUserTransaffair(Map<String, Object> filterMap, PageDesc pageDesc);
}
