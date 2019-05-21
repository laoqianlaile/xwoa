package com.centit.sys.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.VUserOnline;

public interface VUserOnlineManager  extends BaseEntityManager<VUserOnline> {
    
    public void clearUserOnlineState();
}
