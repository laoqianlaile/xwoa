package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaSubvideoInformation;

public interface OaSubvideoInformationManager extends BaseEntityManager<OaSubvideoInformation> 
{

   public String createid();
   
   /**
    * 根据视频信息删除
    * @param videoNo
    */
   public void deleteByVideoNo(String videoNo);
}
