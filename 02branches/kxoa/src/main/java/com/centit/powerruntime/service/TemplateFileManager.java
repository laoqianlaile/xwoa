package com.centit.powerruntime.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.TemplateFile;

public interface TemplateFileManager extends BaseEntityManager<TemplateFile> 
{
    public List<TemplateFile> listTemplateByType(String tempType);
    public TemplateFile getTempByRecord(String recordId);
    /**
     *根据模版类别查询模版编码
     * 根据查询来源决定是否剔除已经设置的模版编码
     * @param tempType 模版类别
     * @return
     */
    public List<TemplateFile> listTemplateByTypeNoWith(String tempType,String squerySource);
}
