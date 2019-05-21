package com.centit.app.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.dao.DashboardDao;
import com.centit.app.po.Dashboard;
import com.centit.app.service.DashboardManager;
import com.centit.core.service.BaseEntityManagerImpl;

public class DashboardManagerImpl extends BaseEntityManagerImpl<Dashboard>
implements DashboardManager{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(DashboardManager.class);
    private DashboardDao dashboardDao ;
    public void setDashboardDao(DashboardDao baseDao) {
        this.dashboardDao = baseDao;
        setBaseDao(this.dashboardDao);
       
    }
    @Override
    public List<Dashboard> getDashboardListFormVOADASHBOARD(
            Map<String, Object> filterMap) {
        // TODO Auto-generated method stub
        return dashboardDao.getDashboardListFormVOADASHBOARD(filterMap);
    }
    @Override
    public Map<String, String> getdashboardNum(String usercode, String itemtype) {
        // TODO Auto-generated method stub
        return dashboardDao.getdashboardNum(usercode, itemtype);
    }
    @Override
    public Map<String, String> getMessageNum(String usercode) {
        // TODO Auto-generated method stub
        return dashboardDao.getMessageNum(usercode);
    }
   
    /**
     * 获取未读邮件数
     * @param usercode
     * @return
     */
    public Long getUnReadEmailNum(String usercode){
        return dashboardDao.getUnReadEmailNum(usercode);
    }
    
    /**
    * 获取未读通知数
    * @param usercode
    * @return
    */
   public Long getUnReadBulletinNum(String usercode){
       return dashboardDao.getUnReadBulletinNum(usercode);
   }
   
   /**
    * 获取收文待办数
    * @param usercode
    * @return
    */
   public Long getSWTaskNum(String usercode){
       return dashboardDao.getSWTaskNum(usercode);
   }
   
   /**
    * 获取发文待办数
    * @param usercode
    * @return
    */
   public Long getFWTaskNum(String usercode){
       return dashboardDao.getFWTaskNum(usercode);
   }
   
   /**
    * 获取未读提醒数
    * @param usercode
    * @return
    */
   public Long getUnreadRemind(String usercode){
       return dashboardDao.getUnreadRemind(usercode);
   }
   
   /**
    * 获取未读外部邮件数
    * @param usercode
    * @return
    */
   public Long getUnreadOuterEmailNum(String usercode){
       return dashboardDao.getUnreadOuterEmailNum(usercode);
   }
   
   /**
    * 获取收文待办数
    * @param usercode
    * @return
    */
   public Long getLWTaskNum(String usercode){
       return dashboardDao.getLWTaskNum(usercode);
   }
}
