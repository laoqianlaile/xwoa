package com.centit.advancedsearch.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.centit.advancedsearch.po.OaSearchHistory;
import com.centit.advancedsearch.po.OaSearchResult;
import com.centit.advancedsearch.service.AdvancedSearchManager;
import com.centit.advancedsearch.service.OaSearchHistoryManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.sys.security.FUserDetail;

/**
 * 
 * 高级搜索控制类
 * 
 * @author Ghost
 * @create 2017年2月6日
 * @version
 */
public class AdvancedSearchAction extends BaseEntityDwzAction<OaSearchResult>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(AdvancedSearchAction.class);
    
    private OaSearchHistoryManager searchHistoryManager;
    
    private AdvancedSearchManager advancedSearchManager;
    private String highsearch;
   

    /**
     * ******高级搜索*******
     */
    public String getSearchList(){
        FUserDetail user = ((FUserDetail) getLoginUser());
        String keywords = request.getParameter("keywords");
        String searchScope = request.getParameter("searchScope");
        String pageNo = request.getParameter("pageNo");
        
        PageDesc pageDesc = new PageDesc();
        pageDesc.setPageNo(Integer.parseInt(pageNo));
        pageDesc.setPageSize(20);
        
        List<OaSearchResult> searchResult = new ArrayList<OaSearchResult>();
        if(searchScope == null){//不限制查询范围的，查询所有
            searchResult = advancedSearchManager.findAll(keywords, user.getUsercode(), pageDesc);
        }else{//根据范围查询
            searchResult = advancedSearchManager.findInTheRange(keywords, Integer.parseInt(searchScope), user.getUsercode(), pageDesc);
        }
        //添加查询历史记录
        searchHistoryManager.save(keywords, user.getUsercode());
        
        try{
            JSONObject result = new JSONObject();
            result.put("page", pageDesc);
            result.put("objList", searchResult);
            ServletActionContext.getResponse().getWriter().print(result.toJSONString());
        }catch(Exception e){
            log.error("获取历史关键字时发生异常："+e.getMessage());
        }
        
        
        
        return "searchList";
    }
    
    /**
     * 重写高级搜索的查询方法
     *
     * @return
     */
    public String goSearchIndex() {
        try {
            //获取当前登录人的用户编号
            FUserDetail user = ((FUserDetail) getLoginUser());
            String ucode = user.getUsercode();
            //获取页面上参数  optval  类型     ，title  标题
            String optval = request.getParameter("optval");
            String title = request.getParameter("title");
            PageDesc pageDesc = makePageDesc();//分页
            objList = advancedSearchManager.findAllNew(optval,title,ucode);//获取数据
            if(objList.size()==0){
                pageDesc.setTotalRows(1);//设置分页的总数
            }else{
                totalRows = objList.size();//设置分页的总数
                pageDesc.setTotalRows(totalRows); 
            }
            this.pageDesc = pageDesc;
            request.setAttribute("optval", optval);
            return "searchIndex";//跳转搜索页
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
    
    
    
    
    
    /**
     * **************************************以下内容为初始方式 暂时舍弃***********************************************
     */
    
    
    
    /**
     * 检索主页面
     * @return
     */
/*    public String goSearchIndex(){
        return "searchIndex";
    }*/
    /**
     * 获取历史关键字
     */
    public void getHistorySearchKeys(){
        FUserDetail user = ((FUserDetail) getLoginUser());
        //获取关键字历史记录,只获取前5条
        List<OaSearchHistory> historySearchKeys = searchHistoryManager.findTop5Records(user.getUsercode());
        try{
            ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(historySearchKeys));
        }catch(Exception e){
            log.error("获取历史关键字时发生异常："+e.getMessage());
        }
    }
    
    /**
     * 获取查询结果
     */
    public void getSearchResult(){
        FUserDetail user = ((FUserDetail) getLoginUser());
        String keywords = request.getParameter("keywords");
        String searchScope = request.getParameter("searchScope");
        String pageNo = request.getParameter("pageNo");
        
        PageDesc pageDesc = new PageDesc();
        pageDesc.setPageNo(Integer.parseInt(pageNo));
        pageDesc.setPageSize(20);
        
        List<OaSearchResult> searchResult = new ArrayList<OaSearchResult>();
        if(searchScope == null){//不限制查询范围的，查询所有
            searchResult = advancedSearchManager.findAll(keywords, user.getUsercode(), pageDesc);
        }else{//根据范围查询
            searchResult = advancedSearchManager.findInTheRange(keywords, Integer.parseInt(searchScope), user.getUsercode(), pageDesc);
        }
        //添加查询历史记录
        searchHistoryManager.save(keywords, user.getUsercode());
        
        try{
            JSONObject result = new JSONObject();
            result.put("page", pageDesc);
            result.put("objList", searchResult);
            ServletActionContext.getResponse().getWriter().print(result.toJSONString());
        }catch(Exception e){
            log.error("获取历史关键字时发生异常："+e.getMessage());
        }
    }
    
    
    public void setSearchHistoryManager(OaSearchHistoryManager searchHistoryManager) {
        this.searchHistoryManager = searchHistoryManager;
    }

    public void setAdvancedSearchManager(AdvancedSearchManager advancedSearchManager) {
        this.advancedSearchManager = advancedSearchManager;
    }
    public String getHighsearch() {
        return highsearch;
    }
    public void setHighsearch(String highsearch) {
        this.highsearch = highsearch;
    }
    
    
}
