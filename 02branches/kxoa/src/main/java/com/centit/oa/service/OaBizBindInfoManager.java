package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaBizBindInfo;

public interface OaBizBindInfoManager extends BaseEntityManager<OaBizBindInfo> 
{  

   public String getNextNO(String ev);

   public void deleteStartObjectById(String djId);
   /**
    *根据不同的业务要求,设置关联类型
    *@return
    *@param djid 被关联djId
    *@param startDjId 关联源djId
    */
   public String initReturnBiztype(String djId,String startDjId);

}
