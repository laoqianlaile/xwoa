package com.centit.powerbase.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.po.Suppowerchglog;
import com.centit.powerbase.po.Suppowerqlbgsq;
import com.centit.powerbase.po.Suppowerstatechglog;


public interface SuppowerchglogManager extends BaseEntityManager<Suppowerchglog> 
{
    //获取表里面的最大值
    public String genNextChangeId();
    //获取Suppowerchglog信息
    public Suppowerchglog  getObjectByIdAndVersion(String itemId, String version);
    //保存Suppowerchglog信息
    public void saveSuppowerchglog(Suppowerchglog logBean);
    public Suppowerqlbgsq getSuppowerqlbgsqInfo(String item_id,Long version);
    //根据权力编号，查询所有的版本号和状态变更情况
    public List<Suppowerchglog> getlistVersionByItemid(Map<String, Object> filterMap, PageDesc pageDesc);
   
}
