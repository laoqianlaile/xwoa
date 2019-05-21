package com.centit.bbs.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.centit.core.service.BaseEntityManager;
import com.centit.bbs.po.OaBbsDashboard;

public interface OaBbsDashboardManager extends BaseEntityManager<OaBbsDashboard> 
{
/**
 * 获取bbs首页统计数据今日发帖、昨日发帖、帖子总数
 * @return
 */
    OaBbsDashboard getTotalSum();
    
    /**
     * 获取序列生成的主键
     * @return
     */
    public String getNextKey();

    /**
     * 判断是否为开放时间
     * @param begTime
     * @param now
     * @param endTime
     * @return
     */
    public  boolean isShowTime(Date begTime, Date now, Date endTime,int hhBeg,int hhEnd);
    
    /**
     * 删除原先大模块与子模块管理员间的关联
     * @param o
     */
    public void deleteDashboardRelations(OaBbsDashboard o);
    /**
     * 获取讨论版面树
     * 
     * @return
     */
    public JSONArray putLayoutTree(String usercode, String sParentUnit);
    
    /**
     * 根据管理员信息获取相应模板列表
     * 
     * @return
     */
    public List<OaBbsDashboard> getDashboarListByUser(String usercode, String sParentUnit);
    /**
     * 模块信息（公开+模块类型）+子模块信息
     * @return
     */
    public List<OaBbsDashboard> getDashboardList( Map<String, Object> filterMap);


    
}
