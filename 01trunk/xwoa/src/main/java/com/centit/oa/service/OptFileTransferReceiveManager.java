package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OptFileTransferReceive;

public interface OptFileTransferReceiveManager extends BaseEntityManager<OptFileTransferReceive> {
    public String getNextSequence();
}
