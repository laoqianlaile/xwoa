package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.IncomeProject;
import com.centit.dispatchdoc.dao.IncomeProjectDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.dispatchdoc.service.IncomeProjectManager;

public class IncomeProjectManagerImpl extends
        BaseEntityManagerImpl<IncomeProject> implements IncomeProjectManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(IncomeProjectManager.class);

    private IncomeProjectDao incomeProjectDao;

    public void setIncomeProjectDao(IncomeProjectDao baseDao) {
        this.incomeProjectDao = baseDao;
        setBaseDao(this.incomeProjectDao);
    }

    /**
     * 删除工作小组--办件角色
     * @param flowInstId  流程实例号 不能为空
     * @param roleCode 办件角色 不能为空
     * @param authdesc 添加
     */
    public void deleteFlowWorkTeam(long flowInstId,String roleCode,String authDesc){
       incomeProjectDao.doExecuteHql("delete WfTeam where id.flowInstId=? and id.roleCode=? and authDesc = ?",new Object[]{flowInstId, roleCode,authDesc});
    }
}
