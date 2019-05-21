package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaDuescollection;
import com.centit.oa.po.OaDuescollectioninfos;

public interface OaDuescollectioninfosManager extends BaseEntityManager<OaDuescollectioninfos> 
{

    /**
     * 设置临时标志
     * @param djId
     */
    public void initalTempState(String djId);

    /**
     * 清理无效人员
     * @param djId
     */
    public void deleteObjectProperties(OaDuescollection object);

    /**
     * 根据最新记录同步收缴明细表
     * @param object
     */
    public void updateInfos(OaDuescollection object);
    /**
     * 根据业务编号，获取机构
     * @param djId
     * @return
     */
    public List<Object[]> listUnitsDistinct(String djId);

}
