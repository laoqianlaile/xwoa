package com.centit.oa.service;

import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaCarApply;

public interface OaCarApplyManager extends BaseEntityManager<OaCarApply> 
{

   public String getNextKey();
   
   /**
    * 一段时间内的申请记录，调配记录
 * @param meetingNO 
 * @param cardjid 
 * @param end 
 * @param beg 
    * @return
    */
   public List<OaCarApply> getApplylist(String djId, String cardjid, Date beg, Date end,String state);

}
