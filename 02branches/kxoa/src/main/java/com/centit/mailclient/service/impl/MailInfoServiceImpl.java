package com.centit.mailclient.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.mailclient.dao.MailInfoDao;
import com.centit.mailclient.po.MailAttachment;
import com.centit.mailclient.po.MailInfo;
import com.centit.mailclient.po.MailProfile;
import com.centit.mailclient.service.MailAttachmentService;
import com.centit.mailclient.service.MailInfoService;
import com.centit.mailclient.service.MailProfileService;
import com.centit.mailclient.util.MailConstants;
import com.centit.mailclient.util.MailHelper;

public class MailInfoServiceImpl extends BaseEntityManagerImpl<MailInfo> implements MailInfoService{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private MailAttachmentService mailAttachmentService;
    private MailProfileService mailProfileService;
    
    public void setMailAttachmentService(MailAttachmentService mailAttachmentService) {
        this.mailAttachmentService = mailAttachmentService;
    }
    
    public void setMailProfileService(MailProfileService mailProfileService) {
        this.mailProfileService = mailProfileService;
    }

    private MailInfoDao mailInfoDao;

    public void setMailInfoDao(MailInfoDao mailInfoDao) {
        this.mailInfoDao = mailInfoDao;
        super.setBaseDao(mailInfoDao);
    }

    @Override
    public Long getNextSequence() {
        return mailInfoDao.getNextLongSequence("SEQ_F_MAIL_INFO");
    }

    
    public void saveObject(MailInfo mailInfo) {
        super.saveObject(mailInfo);
        if(mailInfo.getAttachments()!=null){
            for(MailAttachment attachment : mailInfo.getAttachments()){
                attachment.setId(mailAttachmentService.getNextSequence());
                attachment.setMailInfoId(mailInfo.getId());
                mailAttachmentService.saveObject(attachment);
            }
        }
    }

    public void deleteObjectById(Long id) {
        mailAttachmentService.deleteByEmailId(id);
        super.deleteObjectById(id);
    }

    @Override
    public void saveMailAfterPull(String usercode,String email) {
        if(StringUtils.isEmpty(usercode) || StringUtils.isEmpty(email)){
            throw new IllegalArgumentException("传参异常：用户编码和邮箱地址不能同时为空");
        }
        //获取配置
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("usercode", usercode);
        filterMap.put("email", email);
        filterMap.put("isActive", MailConstants.BOOL_TRUE_ALIAS);
        List<MailProfile> profiles = mailProfileService.listObjects(filterMap);
        
        //根据配置到邮箱服务器上拉取邮件，这里只拉从昨天到现在的邮件
        if(profiles!=null){
            for(MailProfile profile : profiles){
                pullMailByProfile(profile);
            }
        }
        
    }
    
    @Override
    public void saveMailAfterPull(MailProfile profile) {
        //根据配置到邮箱服务器上拉取邮件，这里只拉从昨天到现在的邮件
        if(profile!=null){
                pullMailByProfile(profile);
        }
    }
    /**
     * 根据配置文件拉取邮件服务器上的数据，并筛选保存入库
     * @param profile
     */
    private void pullMailByProfile(MailProfile profile){
        //从服务器上拉取数据
        List<MailInfo> mails = MailHelper.receiveMails(profile);
        
        //过滤数据
        if(mails!=null){
            for(MailInfo mailInfo : mails){
                //如果存在就不入库了，但是要清除拉取时下载的附件
                if(isExist(mailInfo.getMessageId(),mailInfo.getEmail())){
                    if(mailInfo.getAttachments()!=null){
                        for(MailAttachment affix : mailInfo.getAttachments()){
                            File file = new File(affix.getAbsolutePath());
                            if (file.exists()){
                                FileUtils.deleteQuietly(file);
                            }
                        }
                    }
                }else{//如果不存在就需要入库
                    Long mailInfoId = getNextSequence();
                    mailInfo.setId(mailInfoId);
                   
                    saveObject(mailInfo);
                }
            }
        }
    }
    
    /**
     * 判断当前邮件是否已经存在
     * @param messageId
     * @param email
     * @return
     */
    private boolean isExist(String messageId,String email){
        long count = mailInfoDao.countByMessageIdAndEmail(messageId, email);
        if(count==0)
            return false;
        return true;
    }

  
  

}
