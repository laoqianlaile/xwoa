package com.centit.oa.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.OaScheduleDao;
import com.centit.oa.po.OaSchedule;
import com.centit.oa.service.OaScheduleManager;

public class OaScheduleManagerImpl extends BaseEntityManagerImpl<OaSchedule>
        implements OaScheduleManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaScheduleManager.class);

    private OaScheduleDao oaScheduleDao;

    public void setOaScheduleDao(OaScheduleDao baseDao) {
        this.oaScheduleDao = baseDao;
        setBaseDao(this.oaScheduleDao);
    }

    /**
     * 日程安排
     */
    @Override
    public void saveObject(OaSchedule o) {
        if (!StringUtils.isNotBlank(o.getSchno())) {
            o.setSchno("RCAP"
                    + oaScheduleDao.getNextKeyBySequence("S_SCHNO", 12));
        }
        super.saveObject(o);
    }
    
   /**
    *  获日程安排列表
    */
    @Override
    public List<OaSchedule> listObjects(Map<String, Object> filterMap, PageDesc pageDesc,String usercode) {
        
        //creater为当前登录人的，或者AddressBookRelection 共享里面sharename为登录人
        String sHql=" From  OaSchedule t where 1=1 ";
        //主要用于个人日程安排(领导日程安排选中你的，也不需在你的日历上显示这条记录)
       if(StringUtils.isNotBlank(usercode)){
           sHql+="and t.schno in (select distinct a.schno from OaSchedule a  where  a.creater="+HQLUtils.buildHqlStringForSQL(usercode)+
           " or a.schno in (select r.cid.addrbookid from AddressBookRelection r where r.cid.bizType='2'  and  r.cid.shareman= "+HQLUtils.buildHqlStringForSQL(usercode)+"))" ;
       }
       List<OaSchedule> oaScheduleList=oaScheduleDao.listObjects(sHql, filterMap, pageDesc);
      return oaScheduleList;
    }
    
    /**
     *  获日程安排列表无分页参数
     */
    @Override
     public List<OaSchedule> listObjects(Map<String, Object> filterMap,String usercode) {
         //creater为当前登录人的，或者AddressBookRelection 共享里面sharename为登录人
         String sHql=" from OaSchedule t where 1=1 ";
         //主要用于个人日程安排 sehType条件先限制，(领导日程安排选中你的，也不需在你的日历上显示这条记录)(作废：中间表中出来的可能包含领导部分的会议邀请。)
        if(StringUtils.isNotBlank(usercode)){
            sHql+="and t.schno in (select distinct a.schno from OaSchedule a  where a.creater="+HQLUtils.buildHqlStringForSQL(usercode)+
            " or a.schno in (select r.cid.addrbookid from AddressBookRelection r where r.cid.shareman= "+HQLUtils.buildHqlStringForSQL(usercode)+"))" ;
        } 
         List<OaSchedule> oaScheduleList=oaScheduleDao.listObjects(sHql, filterMap);
         return oaScheduleList;
     }

    @Override
    public void deleteByDjId(String djId) {
        oaScheduleDao.deleteByDjId(djId);
        
    }
}
