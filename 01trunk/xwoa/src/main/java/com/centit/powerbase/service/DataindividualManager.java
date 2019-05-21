package com.centit.powerbase.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerbase.po.Dataindividual;

public interface DataindividualManager extends
        BaseEntityManager<Dataindividual> {
    /**
     * 获取新增人员编号
     * @return
     */
    public Long genNextID();
    /**
     * 修改人员是否使用状态
     */
    public void disableObject(Dataindividual object);
}
