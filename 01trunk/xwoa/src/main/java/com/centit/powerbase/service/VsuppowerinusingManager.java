package com.centit.powerbase.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerruntime.po.VOrgSupPower;

public interface VsuppowerinusingManager extends BaseEntityManager<Vsuppowerinusing> 
{

    public Vsuppowerinusing findB_PowerByItemId(String itemId);

    public List<VOrgSupPower> listOrgSuppower(String hql, Map<String, Object> filterMap, PageDesc pageDesc);
    //获取系统事权列表 除去 已被事权人员关联配置的
    public List<Vsuppowerinusing> listUserlist(String itemIds,Map<String, Object> filterMap,
            PageDesc pageDesc);
    
}
