package com.centit.dispatchdoc.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.IncomeProject;

public interface IncomeProjectManager extends BaseEntityManager<IncomeProject> {
    /**
     * 删除工作小组--办件角色
     * @param flowInstId  流程实例号 不能为空
     * @param roleCode 办件角色 不能为空
     * @param authdesc 添加
     */
    public void deleteFlowWorkTeam(long flowInstId,String roleCode,String authdesc);
}
