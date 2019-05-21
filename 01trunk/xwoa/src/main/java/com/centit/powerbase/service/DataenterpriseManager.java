package com.centit.powerbase.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerbase.po.Dataenterprise;

public interface DataenterpriseManager extends
        BaseEntityManager<Dataenterprise> {
    /**
     * 获取新增组织机构编号
     * @return
     */
    public Long genNextID();
    /**
     * 修改组织机构是否使用状态
     */
    public void disableObject(Dataenterprise object);

}
