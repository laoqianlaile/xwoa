package com.centit.powerbase.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.PcpunishItem;

public interface PcpunishItemManager extends BaseEntityManager<PcpunishItem> 
{
 public String generateNextPunishClassID();
    
    public PcpunishItem getObjectByItemId(String punishclasscode);
    
    public List<PcpunishItem> listPcpunishItem(Map<String, Object> filterMap, PageDesc pageDesc);
    //根据处罚项目类别编号s获得处罚项目类别
    public String getPunishclassname(String punishClassIDs);
  /*  public String getpunishItemType(String punishObjectID, String punishClassID);*/
}
