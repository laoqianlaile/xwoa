package com.centit.bbs.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.bbs.po.OaBbsTheme;

public interface OaBbsThemeManager extends BaseEntityManager<OaBbsTheme> 
{
/**
 * 更新主题帖子回复数，回復時間
 * @param themeno
 */
   public void updatePostsNum(String themeno);
   /**
    * 更新主題帖子查看數 
    * @param themeno
    */
   public void updatePostsViewNum(String themeno);
   /**
    * 子模块当天主题数
    * @param layoutno
    * @return
    */
   public Long getTodayThemeNum(String layoutno);
   
   /**
    * 删除根据子模块id
    * @param layoutno
    */
   public void deleteByLayoutNo(String layoutno);
   
   /**
    * 删除主题依赖的部分，比如回复，关注。。。
    * @param themeno
    */
   public void deleteThemeDependencies(String themeno);
}
