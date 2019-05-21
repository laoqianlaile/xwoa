package com.centit.oa.service;

import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.EquipmentUsing;

public interface EquipmentUsingManager extends BaseEntityManager<EquipmentUsing> 
{
    public Long  genNextUseRequestId();

    public List<EquipmentUsing> getEquipmentUsingList(String string,
            Long equipmentId, PageDesc pageDesc);
    public List<EquipmentUsing> getEquipmentUsingList(Date beginTime,Date endTime,Long equipmentId);
}
