package com.centit.powerruntime.optmodel;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OptIdeaInfo;

public interface PowerRuntimeEntityManager<T> extends BaseEntityManager<T> {
    /**
     * 保存业务日志信息
     * @param ideaInfo
     */
    public void saveOptIdeaInfo(OptIdeaInfo ideaInfo);
    
    
   
}
