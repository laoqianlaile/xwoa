package com.centit.mailclient.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.mailclient.dao.MailProfileDao;
import com.centit.mailclient.po.MailProfile;
import com.centit.mailclient.service.MailProfileService;
import com.centit.mailclient.util.MailConstants;

public class MailProfileServiceImpl extends BaseEntityManagerImpl<MailProfile> 
       implements MailProfileService{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private MailProfileDao mailProfileDao;
    
    
    public void setMailProfileDao(MailProfileDao mailProfileDao) {
        this.mailProfileDao = mailProfileDao;
        super.setBaseDao(mailProfileDao);
    }

    @Override
    public boolean checkDuplicateProfile(String email,Long excludeId) {
        long count = mailProfileDao.countByEmail(email,excludeId);
        if(count == 0)
            return false;
        return true;
    }

    @Override
    public void enableProfile(Long id) {
        MailProfile mailProfile = mailProfileDao.getObjectById(id);
        mailProfile.setIsActive(MailConstants.BOOL_TRUE_ALIAS);
    }

    @Override
    public void disableProfile(Long id) {
        MailProfile mailProfile = mailProfileDao.getObjectById(id);
        mailProfile.setIsActive(MailConstants.BOOL_FALSE_ALIAS);
    }

    @Override
    public Long getNextSequence() {
        return mailProfileDao.getNextLongSequence("SEQ_F_MAIL_PROFILE");
    }
    
    
}
