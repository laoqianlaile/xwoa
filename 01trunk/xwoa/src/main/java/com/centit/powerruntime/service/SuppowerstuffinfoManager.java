package com.centit.powerruntime.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.Suppowerstuffinfo;

public interface SuppowerstuffinfoManager extends BaseEntityManager<Suppowerstuffinfo> {
    
    public List<Suppowerstuffinfo> getinfosByGroupId(String groupid);
   
    public String getNextKey();

    /**
     * 根据材料分组编号 删除旗下的明细信息
     * @param groupId
     */
    public void deleteObjectByGroupId(String groupId);
}
