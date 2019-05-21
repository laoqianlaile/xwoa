package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSchedule;

public interface OaScheduleManager extends BaseEntityManager<OaSchedule> 
{
    /**
     * 获日程安排列表
     * @param filterMap 
     * @param pageDesc 分页
     * @param usercode 人员编号
     * @return
     */
    List<OaSchedule> listObjects(Map<String, Object> filterMap, PageDesc pageDesc,String usercode);
    /**
     * 获日程安排列表
     * @param filterMap
     * @param usercode 人员编号
     * @return
     */
    List<OaSchedule> listObjects(Map<String, Object> filterMap,String usercode);
    /**
     * 按关联djId批量删除日程安排
     */
    void deleteByDjId(String djId);
    
   
}
