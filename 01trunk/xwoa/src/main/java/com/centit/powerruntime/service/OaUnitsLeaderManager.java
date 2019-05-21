package com.centit.powerruntime.service;

import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OaUnitsLeader;

public interface OaUnitsLeaderManager extends BaseEntityManager<OaUnitsLeader> 
{
    /**
     * 根据部门编号查询部门分管领导
     * @param unitcode
     * @return
     */
    public Map<String,String> getLeadercode(String usercode);
}
