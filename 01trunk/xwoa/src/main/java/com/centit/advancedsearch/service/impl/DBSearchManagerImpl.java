package com.centit.advancedsearch.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.advancedsearch.dao.AdvancedSearchDao;
import com.centit.advancedsearch.po.OaSearchResult;
import com.centit.advancedsearch.service.AdvancedSearchManager;
import com.centit.core.utils.PageDesc;
/**
 * 
 * 高级检索-直接数据库查询 
 * 
 * 关键字不涉及分词，直接通过sql模糊查询
 * 
 * @author Ghost
 * @create 2017年2月6日
 * @version
 */
public class DBSearchManagerImpl implements AdvancedSearchManager{
    
    private AdvancedSearchDao advancedSearchDao;

    public void setAdvancedSearchDao(AdvancedSearchDao advancedSearchDao) {
        this.advancedSearchDao = advancedSearchDao;
    }

    @Override
    public List<OaSearchResult> findAll(String keywords, String usercode,
            PageDesc pageDesc) {
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("keywords", keywords);
        filterMap.put("usercode", usercode);
        return advancedSearchDao.findAll(filterMap, pageDesc);
    }

    @Override
    public List<OaSearchResult> findInTheRange(String keywords, int scope,
            String usercode, PageDesc pageDesc) {
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("keywords", keywords);
        filterMap.put("usercode", usercode);
        filterMap.put("scope", scope);
        if(scope==2){//如果是附件
            return advancedSearchDao.findInStuffScope(filterMap, pageDesc);
        }else{
            return advancedSearchDao.findInScope(filterMap, pageDesc);
        }
    }
    
    /**
     * 重写全局搜索的方法
     */
    @Override
    public List<OaSearchResult> findAllNew(String optval,String title,String ucode) {
        return advancedSearchDao.findAllNew(optval,title,ucode);
    }
    
}
