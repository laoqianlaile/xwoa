package com.centit.powerruntime.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OptWritdef;
import com.centit.sys.po.FDatadictionary;

public interface OptWritdefManager extends BaseEntityManager<OptWritdef> 
{
    /**
     * 通过模版类别获取文书模版编号
     * @param tempType 模版类别
     * @return
     */
    public List<OptWritdef> getObjectsByTempType(String tempType);
/**
 * 剔除已经设置了的字典项
 * @return
 */
    public List<FDatadictionary> getItemTypesWithOutHave();
    
    /**
     * 通过模版类别获取文书文号信息
     * @param tempType
     * @return
     */   
    public OptWritdef getObjectByTempType(String tempType);

}
