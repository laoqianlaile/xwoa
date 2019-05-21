package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaDuescollection;
import com.centit.sys.po.FUserinfo;

public interface OaDuescollectionManager extends BaseEntityManager<OaDuescollection> 
{
    public List<FUserinfo> getUserByCode(String usercode);

    public List<OaDuescollection> listOaDuescollection(
            Map<String, Object> filterMap, PageDesc pageDesc);

    /**
     * 删除收缴记录
     * 包括收缴明细表
     * @param bean
     */
    public void deleteDuescollectiont(OaDuescollection bean);
    /**
     * 获取主键策略
     * @return
     */
    public String getNextKey();
}
