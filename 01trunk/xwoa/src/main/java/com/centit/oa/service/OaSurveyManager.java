package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSurvey;

public interface OaSurveyManager extends BaseEntityManager<OaSurvey> 
{
    /**
     * 获调查列表
     */
   List<OaSurvey> listObjects(Map<String, Object> filterMap, PageDesc pageDesc);

    void autoEnd();
}
