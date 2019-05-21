package com.centit.powerbase.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerbase.po.Pcfreeumpiredegree;
 
public interface PcfreeumpiredegreeManager extends BaseEntityManager<Pcfreeumpiredegree> 
{
   public List<Pcfreeumpiredegree> listFreeumpire(String itemId,Long version);
   
   public Pcfreeumpiredegree getObjectByItemsId(Long degreeno);
   public String generateNextDegreeno();
   @SuppressWarnings("rawtypes")
   public List<Map> listPunishFactGrade(String itemId,Long version);
    /**
     * 根据权力编码获取最新自由裁量标准－违法事实程度
     * @param itemId
     * @return
     */
   public List<Pcfreeumpiredegree> getObjectByItemsId(String itemId);
}
