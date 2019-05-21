package com.centit.mailclient.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.mailclient.po.MailInfo;
import com.centit.mailclient.po.MailProfile;

public interface MailInfoService extends BaseEntityManager<MailInfo> {
   
   public Long getNextSequence();
   
   /**
    * 将邮箱服务器上邮件拉取保存到数据库
    * <p>1、只拉取从昨天到现在的邮件，为了防止数据量过大</p>
    * <p>2、比对消息头id是否已经存在库里，如果存在就不用保存到库</p>
    * <p>3、如果数据库里已经存在不需要保存到库里，那么把同步到磁盘上的附件也给删掉，因为没找到判断附件唯一的方法，只要先同步下来</p>
    * @param usercode
    * @param email
    */
   public void saveMailAfterPull(String usercode,String email);
   
   /**
    * 拉取用户的邮件
    */
   public void saveMailAfterPull(MailProfile mailProfile);
}
