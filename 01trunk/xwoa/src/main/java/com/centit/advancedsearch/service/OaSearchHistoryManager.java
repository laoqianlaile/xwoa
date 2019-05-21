package com.centit.advancedsearch.service;

import java.util.Date;
import java.util.List;

import com.centit.advancedsearch.po.OaSearchHistory;
import com.centit.core.service.BaseEntityManager;

public interface OaSearchHistoryManager extends BaseEntityManager<OaSearchHistory> {
    /**
     * 添加查询历史
     * @param keywords
     * @param usercode
     */
    void save(String keywords,String usercode);
    
    /**
     * 获取前5条记录，不重复的
     * @param usercode
     * @return
     */
    List<OaSearchHistory> findTop5Records(String usercode);
}
