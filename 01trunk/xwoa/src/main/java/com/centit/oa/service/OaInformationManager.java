package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaInformation;

public interface OaInformationManager extends BaseEntityManager<OaInformation> 
{

    public String getNextkey(String tag);
    /**
     * 定时器使用，使得过了有效期的通知公告的状态变成禁用
     */
    public void autoInvalidated();
}
