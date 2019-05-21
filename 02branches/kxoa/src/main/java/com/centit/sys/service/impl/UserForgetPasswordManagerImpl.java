package com.centit.sys.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.util.IPUtil;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.UserForgetPasswordDao;
import com.centit.sys.po.FUserForgetPassword;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.UserForgetPasswordManager;
import com.centit.sys.util.IdGen;
import com.google.common.collect.Sets.SetView;


public class UserForgetPasswordManagerImpl extends BaseEntityManagerImpl<FUserForgetPassword> implements   UserForgetPasswordManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(UserForgetPasswordManagerImpl.class);

    private UserForgetPasswordDao userForgetPasswordDao;


    public void setUserForgetPasswordDao(UserForgetPasswordDao userForgetPasswordDao) {
        this.userForgetPasswordDao = userForgetPasswordDao;
        setBaseDao(userForgetPasswordDao);
    }
    
    private SysUserManager sysUserMgr;
    public void setSysUserMgr(SysUserManager sysUserMgr) {
        this.sysUserMgr = sysUserMgr;
    }
    
    /**
     * 创建密码找回记录
     */
    @Override
    public FUserForgetPassword createForgetPasswordInfo(
            FUserForgetPassword fUserForgetPassword) {
        fUserForgetPassword.setDjId("MMZH"+IdGen.uuid());
        fUserForgetPassword.setForgettime(new Date());
        fUserForgetPassword.setState("0");
//        fUserForgetPassword.setValidatenum(IdGen.randomString(6));//产生6位随机数作为密钥--下一步页面主动发起;
        userForgetPasswordDao.save(fUserForgetPassword);
        return fUserForgetPassword;
    }
    /**
     * 生成短信验证码
     */
    @Override
    public boolean messagecodeCreate(
            FUserForgetPassword fUserForgetPassword) {
        FUserForgetPassword  fUserForgetPasswordNew=userForgetPasswordDao.getObjectById(fUserForgetPassword.getDjId());
        boolean validcode =(null!=fUserForgetPasswordNew&&fUserForgetPassword.getTelephone().equals(fUserForgetPasswordNew.getTelephone()))?true:false;//联系方式需要与页面保持一致发送短信验证码
      
        if(validcode){
            fUserForgetPasswordNew.setValidatenum(IdGen.randomNumber(4));//产生4位随机数作为密钥--下一步页面主动发起;
            fUserForgetPasswordNew.setState("0");
            userForgetPasswordDao.save(fUserForgetPasswordNew);
        }
        return validcode;
    }
    
    /**
     * 确认短信验证码
     * @param fUserForgetPassword
     * @return
     */
    @Override
    public boolean messagecodeCheck(
            FUserForgetPassword fUserForgetPassword) {
        FUserForgetPassword  fUserForgetPasswordNew=userForgetPasswordDao.getObjectById(fUserForgetPassword.getDjId());
        boolean validMessagecode =(null!=fUserForgetPasswordNew&&fUserForgetPassword.getValidatenum().equalsIgnoreCase(fUserForgetPasswordNew.getValidatenum())&&fUserForgetPassword.getTelephone().equals(fUserForgetPasswordNew.getTelephone()))?true:false;//联系方式需要与页面保持一致短信验证通过
        if(validMessagecode){
            fUserForgetPasswordNew.setState("1");//1 短信验证通过
            fUserForgetPasswordNew.setValidatetime(new Date());
        }else{
            fUserForgetPasswordNew.setState("4");//4短信验证码失效
            fUserForgetPasswordNew.setValidatetime(new Date());
        }
        userForgetPasswordDao.save(fUserForgetPasswordNew);
        return validMessagecode;
    }
    

    @Override
    public boolean updatePassword(FUserForgetPassword fUserForgetPassword) {
        //MMZH825ee0a0f0ff494a83b5cab46abfceaf
        FUserForgetPassword  fUserForgetPasswordNew=userForgetPasswordDao.getObjectById(fUserForgetPassword.getDjId());
        boolean updatePassword =(null!=fUserForgetPassword&&"1".equals(fUserForgetPasswordNew.getState()))?true:false;//致短信验证通过--避免用户恶意跳过前面步奏 
        if(updatePassword&& StringUtils.isNotBlank(fUserForgetPassword.getPassword())){
            //更新密码
            FUserinfo user = sysUserMgr.getObjectById(fUserForgetPasswordNew.getUsercode());
            user.setUserpin(sysUserMgr.encodePassword( fUserForgetPassword.getPassword(),  user.getUsercode()));
            user.setIsDefaultPwd("F");
            sysUserMgr.saveObject(user);
            fUserForgetPasswordNew.setState("2");// 2重置完成
        }else{
            fUserForgetPasswordNew.setState("3");//3因未知因素重置失败
        }
        
        userForgetPasswordDao.save(fUserForgetPasswordNew);
        return updatePassword;
    }
    
    
    

}

