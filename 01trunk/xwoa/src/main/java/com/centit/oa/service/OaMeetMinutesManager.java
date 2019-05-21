package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetMinutes;
import com.centit.oa.po.VoaMeetMinute;

public interface OaMeetMinutesManager extends BaseEntityManager<OaMeetMinutes> 
{

  public  List<VoaMeetMinute> getMaxlist(Map<String, Object> filterMap,
            PageDesc pageDesc,String userCode);

 public List<OaMeetMinutes> getversionbyid(String djId);


}
