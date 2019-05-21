package com.centit.advancedsearch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.centit.advancedsearch.dao.OaSearchHistoryDao;
import com.centit.advancedsearch.po.OaSearchHistory;
import com.centit.advancedsearch.service.OaSearchHistoryManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;

public class OaSearchHistoryManagerImpl extends BaseEntityManagerImpl<OaSearchHistory>
   implements OaSearchHistoryManager{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OaSearchHistoryDao oaSearchHistoryDao;

    public void setOaSearchHistoryDao(OaSearchHistoryDao oaSearchHistoryDao) {
        this.oaSearchHistoryDao = oaSearchHistoryDao;
        super.setBaseDao(oaSearchHistoryDao);
    }

    @Override
    public void save(String keywords, String usercode) {
        OaSearchHistory history = new OaSearchHistory();
        history.setCreater(usercode); 
        history.setMainKey(keywords);
        history.setCreateTime(new Date());
        history.setNo(oaSearchHistoryDao.getNextValueOfSequence("S_OA_SEARCH_HISTORY"));
        oaSearchHistoryDao.save(history);
    }

    @Override
    public List<OaSearchHistory> findTop5Records(String usercode) {
        //按时间倒序取出前100条，防止数据库数据量大，而存在前多条都是重复的，所以尽可能多取点数据，
        //比如：数据库里有1000条，如果取出10条，而10条都是重复的，而去重后只有1条，所以尽量多取点
        //这里设置取100条，可能存在前100条都是重复的了？这里无所谓了，对于历史展示作为快捷查询没必要那么精确
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("creater", usercode);
        filterMap.put("state", "T");
        PageDesc pageDesc = new PageDesc(1,100);
        List<OaSearchHistory> historys = oaSearchHistoryDao.listObjects(filterMap, pageDesc);
        //去重复
        Set<String> keywordsSet = new TreeSet<String>();
        for(OaSearchHistory history : historys){
            keywordsSet.add(history.getMainKey());
            // 如果已经到达限制，那么跳出循环
            if(keywordsSet.size() == 5){
                break;
            }
        }
        
        List<OaSearchHistory> result = new ArrayList<OaSearchHistory>();
        Iterator<String> it = keywordsSet.iterator();
        while(it.hasNext()){
            OaSearchHistory history = new OaSearchHistory();
            history.setMainKey(it.next());
            result.add(history);
        }
        return result;
    }
    
    
}
