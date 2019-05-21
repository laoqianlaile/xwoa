package com.centit.oa.service;

import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaMeetroomApply;

public interface OaMeetroomApplyManager extends BaseEntityManager<OaMeetroomApply> 
{

   public String getNextKey();
   /**
    * 一段时间内的申请记录，调配记录
 * @param meetingNO 
 * @param djId 
 * @param end 
 * @param beg 
    * @return
    */
   public List<OaMeetroomApply> getApplylist(String djId, String meetingNO, Date beg, Date end,String state);

}
