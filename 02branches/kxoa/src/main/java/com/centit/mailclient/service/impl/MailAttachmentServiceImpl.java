package com.centit.mailclient.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.mailclient.dao.MailAttachmentDao;
import com.centit.mailclient.po.MailAttachment;
import com.centit.mailclient.service.MailAttachmentService;
import com.centit.sys.util.IdGen;
import com.centit.sys.util.SysParametersUtils;

public class MailAttachmentServiceImpl extends BaseEntityManagerImpl<MailAttachment> implements MailAttachmentService{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private MailAttachmentDao mailAttachmentDao;
    
    
    public void setMailAttachmentDao(MailAttachmentDao mailAttachmentDao) {
        this.mailAttachmentDao = mailAttachmentDao;
        setBaseDao(mailAttachmentDao);
    }

    @Override
    public Long getNextSequence() {
        return mailAttachmentDao.getNextLongSequence("SEQ_F_MAIL_ATTACHMENT");
    }

    @Override
    public String upload(File file,String originalFilename) throws IOException {
        String baseDir = SysParametersUtils.getMailAffixHome();
        String extensionName = originalFilename.substring(originalFilename.indexOf("."));
        String newFileName = IdGen.uuid() + extensionName;
        String absolutePath = baseDir + File.separator + newFileName;
        
        File saveFile=new File(absolutePath);
        if(!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdirs();
        }
        FileUtils.copyFile(file, saveFile);
        return newFileName;
    }

    public void deleteObjectById(Long id) {
        MailAttachment affix = super.getObjectById(id);
        remove(affix);

    }
    private void remove(MailAttachment affix){
        super.deleteObjectById(affix.getId());
        
        File file = new File(affix.getAbsolutePath());
        if (file.exists()){
            FileUtils.deleteQuietly(file);
        }
    }
    @Override
    public void deleteByEmailId(Long emailInfoId) {
          Map<String,Object> filterMap = new HashMap<String,Object>();
          filterMap.put("mailInfoId", emailInfoId);
          List<MailAttachment> affixList = super.listObjects(filterMap);
          for(MailAttachment affix : affixList){
              remove(affix);
          }
    }

}
