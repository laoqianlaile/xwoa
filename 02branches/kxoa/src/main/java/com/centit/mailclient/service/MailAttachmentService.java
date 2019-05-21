package com.centit.mailclient.service;

import java.io.File;
import java.io.IOException;

import com.centit.core.service.BaseEntityManager;
import com.centit.mailclient.po.MailAttachment;

public interface MailAttachmentService extends BaseEntityManager<MailAttachment>{
    public Long getNextSequence();
    
    /**
     * 上传附件
     * @param file
     * @param fileName
     * @return
     * @throws IOException
     */
    public String upload(File file,String fileName) throws IOException;
    
    /**
     * 根据邮件id删除邮件
     */
    public void deleteByEmailId(Long emailInfoId);
}
