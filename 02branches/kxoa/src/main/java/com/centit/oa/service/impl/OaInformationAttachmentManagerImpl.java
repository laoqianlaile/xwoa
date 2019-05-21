package com.centit.oa.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaInformationAttachmentDao;
import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.service.OaInformationAttachmentManager;

public class OaInformationAttachmentManagerImpl extends BaseEntityManagerImpl<OaInformationAttachment>
          implements OaInformationAttachmentManager{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OaInformationAttachmentDao oaInfoAttachmentDao;
    
    public OaInformationAttachmentDao getOaInfoAttachmentDao() {
        return oaInfoAttachmentDao;
    }

    public void setOaInfoAttachmentDao(
            OaInformationAttachmentDao oaInfoAttachmentDao) {
        this.oaInfoAttachmentDao = oaInfoAttachmentDao;
        super.setBaseDao(oaInfoAttachmentDao);
    }


    @Override
    public Long getNextSequence() {
        return oaInfoAttachmentDao.getNextLongSequence("SEQ_OA_INFORMATION_ATTACHMENT");
    }

    @Override
    public List<OaInformationAttachment> findDocAttachments(String refId) {
        return oaInfoAttachmentDao.findDocAttachments(refId);
    }

    @Override
    public List<OaInformationAttachment> findVideoAttachments(String refId) {
        return oaInfoAttachmentDao.findVideoAttachments(refId);
    }

    @Override
    public List<OaInformationAttachment> findAttachments(String refId) {
        return oaInfoAttachmentDao.findAttachments(refId);
    }
    
    
    
}
