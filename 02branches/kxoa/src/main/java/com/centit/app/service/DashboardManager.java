package com.centit.app.service;


import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.app.po.Dashboard;

public interface DashboardManager extends BaseEntityManager<Dashboard> 
{

    /**
     * 从首页待办视图获取个人待办事项(如果首页待办第一次从oa_Dashboard表中没有获取到数据时候 调用此接口)
     * @param filterMap
     * @return
     */
    List<Dashboard> getDashboardListFormVOADASHBOARD(Map<String, Object> filterMap);
    /**
     * OA系统查询未读信息数量
     * @param usercode
     *            用户id
     * @param itemtype
     *            "DWBL":待我办理   "SJX"：收件箱   null:查询全部
     * @return
     */
    public Map<String,String> getdashboardNum(String usercode,String itemtype);
    
    /**
     * OA系统查询消息提醒弹窗信息数量
     * @param usercode
     *            用户id
     * @param itemtype
     *            "DWBL":待我办理   "SJX"：收件箱
     * @return
     */
   public Map<String,String> getMessageNum(String usercode);
   
   /**
    * 获取未读邮件数
    * @param usercode
    * @return
    */
   public Long getUnReadEmailNum(String usercode);
   
   /**
    * 获取未读公告数
    * @param usercode
    * @return
    */
   public Long getUnReadBulletinNum(String usercode);
   
   /**
    * 获取收文待办数
    * @param usercode
    * @return
    */
   public Long getSWTaskNum(String usercode);
   
   /**
    * 获取发文待办数
    * @param usercode
    * @return
    */
   public Long getFWTaskNum(String usercode);
   /**
    * 获取未读提醒数
    * @param usercode
    * @return
    */
   public Long getUnreadRemind(String usercode);
   
   /**
    * 获取未读外部邮件数
    * @param usercode
    * @return
    */
   public Long getUnreadOuterEmailNum(String usercode);
   
   /**
    * 获取来文待办未读数
    * @param usercode
    * @return
    */
   public Long getLWTaskNum(String usercode);

}
