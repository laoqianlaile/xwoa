package com.centit.sys.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.FUserForgetPassword;

public interface UserForgetPasswordManager extends BaseEntityManager<FUserForgetPassword> {

     /**
      * 密码找回记录--发送短信
      * @param telephone
      * @return
      */
    public FUserForgetPassword createForgetPasswordInfo(FUserForgetPassword fUserForgetPassword);
    /**
     * 确认短信验证码
     * @param fUserForgetPassword
     * @return
     */
    public boolean messagecodeCheck(FUserForgetPassword fUserForgetPassword);
    /**
     * 更新密码
     * @param fUserForgetPassword
     * @return
     */
    public boolean updatePassword(FUserForgetPassword fUserForgetPassword);
    /**
     * 创建短信验证码
     * @param fUserForgetPassword
     * @return
     */
    public boolean messagecodeCreate(FUserForgetPassword fUserForgetPassword);
}
