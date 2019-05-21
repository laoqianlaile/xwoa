package com.centit.advancedsearch.service;

import java.util.List;

import com.centit.advancedsearch.po.OaSearchResult;
import com.centit.core.utils.PageDesc;

public interface AdvancedSearchManager {
    /**
     * 全局搜索
     * @param keyword 关键字
     * @param usercode
     * @return
     */
   List<OaSearchResult> findAll(String keyword,String usercode,PageDesc pageDesc);
   
   /**
    * 在什么范围内搜索
    * @param keyword 关键字
    * @param scope 1-办件 2-附件 3-邮件 4-资讯
    * @param usercode
    * @return
    */
   List<OaSearchResult> findInTheRange(String keyword,int scope,String usercode,PageDesc pageDesc);
   
   
   /**
    * 重写全局搜索方法
    */
   List<OaSearchResult> findAllNew(String optval,String title,String ucode);
   
}
