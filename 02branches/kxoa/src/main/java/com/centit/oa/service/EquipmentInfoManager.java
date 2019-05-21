package com.centit.oa.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.EquipmentInfo;
import com.centit.sys.po.FDatadictionaryId;

public interface EquipmentInfoManager extends BaseEntityManager<EquipmentInfo> 
{
    public Long  genNextEquipmentId();

    public List<EquipmentInfo> listByType(EquipmentInfo object,
            List<String> types, PageDesc pageDesc, Order order);

   public void delAndUpdateDic(FDatadictionaryId id);
    
 
}
