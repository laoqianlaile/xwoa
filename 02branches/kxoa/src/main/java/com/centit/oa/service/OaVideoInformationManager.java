package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaVideoInformation;

public interface OaVideoInformationManager extends BaseEntityManager<OaVideoInformation> 
{

    public String createNo();
    /**
     * 定时器使用，使得过了视频有效期的视频的状态变成不可观看
     */
    public void autoLoseEfficacy();
}
