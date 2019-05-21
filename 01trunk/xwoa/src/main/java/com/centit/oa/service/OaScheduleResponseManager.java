package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaScheduleResponse;

public interface OaScheduleResponseManager extends BaseEntityManager<OaScheduleResponse> 
{
    /**
     * 获取自己的响应
     * @param object
     * @return
     */
    public OaScheduleResponse getOwnRes(OaScheduleResponse object);
    /**
     * 根据日程编号以及响应类型获取
     * @param schno 日程编号
     * @param resType 响应类型
     * @return 
     */
    public List<OaScheduleResponse> getOaScheduleResponseListByresType(String schno,String resType);
}
