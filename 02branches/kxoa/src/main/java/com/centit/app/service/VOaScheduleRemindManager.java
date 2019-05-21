package com.centit.app.service;

import java.util.List;
import java.util.Map;

import com.centit.app.po.VOaScheduleRemind;
import com.centit.core.service.BaseEntityManager;

public interface VOaScheduleRemindManager extends BaseEntityManager<VOaScheduleRemind> 
{
    public List<VOaScheduleRemind> listObjects(Map<String, Object> filterMap,String usercode);
}
