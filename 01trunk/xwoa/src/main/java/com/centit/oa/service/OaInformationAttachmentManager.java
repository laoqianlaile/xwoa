package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaInformationAttachment;

public interface OaInformationAttachmentManager extends BaseEntityManager<OaInformationAttachment>{
   public Long getNextSequence();
   
   public List<OaInformationAttachment> findDocAttachments(String refId);
   
   public List<OaInformationAttachment> findVideoAttachments(String refId);
   
   public List<OaInformationAttachment> findAttachments(String refId);
}
