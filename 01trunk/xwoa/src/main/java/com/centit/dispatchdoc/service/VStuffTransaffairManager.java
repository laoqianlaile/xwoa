package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VStuffTransaffair;

public interface VStuffTransaffairManager extends
        BaseEntityManager<VStuffTransaffair> {

    public List<VStuffTransaffair> listStuffTransaffair(Map<String, Object> filterMap, PageDesc pageDesc);
}
