package com.centit.oa.service;

import java.io.File;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OptFileTransferSend;

public interface OptFileTransferSendManager extends BaseEntityManager<OptFileTransferSend>{
    public String getNextSequence();
    
    public void saveObject(OptFileTransferSend o,File[] files,String[] fileName);
}
