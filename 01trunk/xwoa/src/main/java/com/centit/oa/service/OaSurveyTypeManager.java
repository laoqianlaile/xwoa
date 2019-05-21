package com.centit.oa.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaSurveyType;

public interface OaSurveyTypeManager extends BaseEntityManager<OaSurveyType> 
{
    /**
     * 保存新增或修改后的调查类型
     */
    public void saveSuryType(OaSurveyType o);
    /**
     * 删除调查类型（只是修改state字段）
     */
    public void deleteSuryType(OaSurveyType o);  
}
