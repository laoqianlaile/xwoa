package com.centit.mailclient.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.mailclient.po.MailProfile;

public interface MailProfileService extends BaseEntityManager<MailProfile>{
    /**
     * 检测配置是否重复
     * @param email
     * @return
     */
   public boolean checkDuplicateProfile(String email,Long excludeId);
   
   /**
    * 激活配置
    * @param id
    */
   public void enableProfile(Long id);
   
   /**
    * 关闭配置
    * @param id
    */
   public void disableProfile(Long id);
   
   /**
    * 
    * @return
    */
   public Long getNextSequence();
   
}
