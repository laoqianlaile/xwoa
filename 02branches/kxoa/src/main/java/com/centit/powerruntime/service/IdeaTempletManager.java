package com.centit.powerruntime.service;

import java.util.List;

import com.centit.powerruntime.po.OptIdeaInfo;

public interface IdeaTempletManager {
    /**
     * 加载意见模板
     * @param moduleCode
     * @return
     */
    public String loadTemplet(String moduleCode);
    
    /**
     * 根据模板解析数据
     * @param templetJson
     * @param optIdeaInfos
     * @return
     */
    public String parseByTemplet(String templetJson,List<OptIdeaInfo> optIdeaInfos);
    
    /**
     * 获取意见信息
     */
    public String getIdea(String djid,String moduleCode);
    
    /**
     * 加载出所有可用意见
     * 如：收文动态加载意见
     * @param djId
     * @return
     */
    public List<OptIdeaInfo> loadAllAvailableIdeas(String djId,boolean showIdeaContent);
}
