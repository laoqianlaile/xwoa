package com.centit.powerruntime.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.GeneralModuleParam;

public interface GeneralModuleParamManager extends BaseEntityManager<GeneralModuleParam> 
{
    /**
     * 获取流程相关配置信息
     * @param flowCode
     * @param version
     * @return
     */
    List<GeneralModuleParam> getGeneralModuleList(String flowCode,Long version);
}
