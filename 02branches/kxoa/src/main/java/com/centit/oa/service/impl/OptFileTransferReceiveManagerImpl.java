package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OptFileTransferReceiveDao;
import com.centit.oa.po.OptFileTransferReceive;
import com.centit.oa.service.OptFileTransferReceiveManager;

public class OptFileTransferReceiveManagerImpl extends BaseEntityManagerImpl<OptFileTransferReceive> 
       implements OptFileTransferReceiveManager  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptFileTransferReceiveDao optFileTransferReceiveDao;
    
    public OptFileTransferReceiveDao getOptFileTransferReceiveDao() {
        return optFileTransferReceiveDao;
    }

    public void setOptFileTransferReceiveDao(
            OptFileTransferReceiveDao optFileTransferReceiveDao) {
        this.optFileTransferReceiveDao = optFileTransferReceiveDao;
        super.setBaseDao(optFileTransferReceiveDao);
    }

    @Override
    public String getNextSequence() {
        return "IOR"+optFileTransferReceiveDao.getNextKeyBySequence("SEQ_OPT_FILETRANSFER_RECEIVE",14);
    }

}
