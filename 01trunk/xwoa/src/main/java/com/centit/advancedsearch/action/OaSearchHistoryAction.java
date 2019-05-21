package com.centit.advancedsearch.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.centit.advancedsearch.po.OaSearchHistory;
import com.centit.advancedsearch.service.OaSearchHistoryManager;
import com.centit.core.action.BaseAction;
import com.centit.sys.security.FUserDetail;
/**
 * 
 * 历史关键字搜索
 * 
 * @author Ghost
 * @create 2017年2月9日
 * @version
 */
public class OaSearchHistoryAction extends BaseAction{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OaSearchHistoryManager searchHistoryManager;
    /**
     * 删除单个历史
     */
    public void removeHistory(){
        boolean result = true;
        String keywords = request.getParameter("keywords");
        if(!StringUtils.isEmpty(keywords)){
            try{
                FUserDetail user = ((FUserDetail) getLoginUser());
                Map<String,Object> filterMap = new HashMap<String,Object>();
                filterMap.put("state", "T");
                filterMap.put("creater", user.getUsercode());
                filterMap.put("eqMainKey",keywords);
                
                List<OaSearchHistory> historys = searchHistoryManager.listObjects(filterMap);
                for(OaSearchHistory history : historys){
                    history.setState("D");
                    searchHistoryManager.saveObject(history);
                }
            }catch(Exception e){
                log.error("清除一个历史记录的时候发生异常："+e.getMessage());
                result = false;
            }
        }
       
        try{
            ServletActionContext.getResponse().getWriter().print(result);
        }catch(Exception e){
            log.error("删除历史关键字时发生异常："+e.getMessage());
        }
    }
    /**
     * 清除所有
     */
    public void removeAllHistory(){
        boolean result = true;
        try{
            FUserDetail user = ((FUserDetail) getLoginUser());
            Map<String,Object> filterMap = new HashMap<String,Object>();
            filterMap.put("state", "T");
            filterMap.put("creater", user.getUsercode());
            List<OaSearchHistory> historys = searchHistoryManager.listObjects(filterMap);
            for(OaSearchHistory history : historys){
                history.setState("D");
                searchHistoryManager.saveObject(history);
            }
        }catch(Exception e){
            log.error("清除所有历史记录的时候发生异常："+e.getMessage());
            result = false;
        }
        try{
            ServletActionContext.getResponse().getWriter().print(result);
        }catch(Exception e){
            log.error("清除所有历史记录的时候发生异常："+e.getMessage());
        }
    }

    public void setSearchHistoryManager(OaSearchHistoryManager searchHistoryManager) {
        this.searchHistoryManager = searchHistoryManager;
    }
    
    
}
