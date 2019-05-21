package com.centit.app.service;

import com.centit.app.po.RtxInfo;
import com.centit.core.service.BaseEntityManager;

public interface RtxInfoManager extends BaseEntityManager<RtxInfo>{

    public String getNextkey();
    
    public boolean sendRtx(String isSend);
}
