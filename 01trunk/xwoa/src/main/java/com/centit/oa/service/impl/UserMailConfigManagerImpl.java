package com.centit.oa.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.UserMailConfigDao;
import com.centit.oa.po.UserMailConfig;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.UserMailConfigManager;

public class UserMailConfigManagerImpl extends BaseEntityManagerImpl<UserMailConfig> implements UserMailConfigManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(UserMailConfigManager.class);

    // private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

    private UserMailConfigDao userMailConfigDao;
    @SuppressWarnings("unused")
    private InnermsgManager innermsgManager;

    public void setUserMailConfigDao(UserMailConfigDao baseDao) {
        this.userMailConfigDao = baseDao;
        setBaseDao(this.userMailConfigDao);
    }

    public void setInnermsgManager(InnermsgManager innermsgManager) {
        this.innermsgManager = innermsgManager;
    }

    @Override
    public void saveObject(UserMailConfig c) {
        super.saveObject(c);

        /*Innermsg msg = new Innermsg();
        msg.setEmailid(c.getEmailid());
        msg.setSender(c.getMailaccount());
        msg.setTo(c.getMailaccount());
        msg.setMsgtitle("邮箱测试");
        msg.setMsgcontent("邮箱测试");

        try {
            msg.setMailtype(CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE, Innermsg.MAIL_TYPE_O));
            this.innermsgManager.saveSendMail(msg);

            this.innermsgManager.saveReceiveMail(msg);
        } catch (RuntimeException e) {
            super.deleteObject(c);

            return false;
        }*/
    }

    @Override
    public List<UserMailConfig> getObjectsByCodeAndAccount(
            String usercode, String emailaccount) {
        // TODO Auto-generated method stub
        return userMailConfigDao.getUsrmailfonfigsByCodeAndAccount(usercode, emailaccount);
    }

}
