package com.centit.powerbase.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerbase.po.PcpunishStandard;

public interface PcpunishStandardManager extends BaseEntityManager<PcpunishStandard> 
{

    public PcpunishStandard getObjectByClassId(String itemId,Long version);
    
    public PcpunishStandard getObjectByItemsId(String itemId,Long version,String punishtypeid);
    
    public List<PcpunishStandard> listPcType(String itemId,Long version );
    
    @SuppressWarnings("rawtypes")
    public List<Map> listPunishType(String itemId,Long version,String degreeno);
    public List<PcpunishStandard> listPcTypeInUse(String itemId,Long version);

    /**
     * 获取处罚标准处罚方式
     * @param punishobjectid
     * @param item_id
     * @param version
     * @return
     */
    public String getpunishItemType(String punishobjectid, String item_id,
            Long version);
}
