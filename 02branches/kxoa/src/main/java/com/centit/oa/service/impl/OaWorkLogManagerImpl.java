package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaWorkLog;
import com.centit.oa.dao.OaWorkLogDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaWorkLogManager;

public class OaWorkLogManagerImpl extends BaseEntityManagerImpl<OaWorkLog>
	implements OaWorkLogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaWorkLogManager.class);

	private OaWorkLogDao oaWorkLogDao ;
	public void setOaWorkLogDao(OaWorkLogDao baseDao)
	{
		this.oaWorkLogDao = baseDao;
		setBaseDao(this.oaWorkLogDao);
	}
	
	
	 /**
     * 工作日志
     */
    @Override
    public void saveObject(OaWorkLog o) {
        if (!StringUtils.hasText(o.getNo())) {
            o.setNo("GZRZ"
                    + oaWorkLogDao.getNextKeyBySequence("S_OAWORKLOG", 12));
        }
        super.saveObject(o);
    }
    
    
    
    private String getHsql(Map<String, Object> filterMap){
        String usercode=(String) filterMap.get("ownerCode");
        //creater为当前登录人的，或者AddressBookRelection 共享里面sharename为登录人
        String sHql=" from OaWorkLog t where 1=1 ";
        //只查看自己(勾选)
        if(("true").equals(filterMap.get("s_owner"))){
            if(StringUtils.hasText(usercode)){
                sHql+="and t.no in (select distinct a.no from OaWorkLog a  where  a.creater="+HQLUtils.buildHqlStringForSQL(usercode)+")";
               
            } 
        }else{
            //查看自己，以及别人分享过来的的工作日志
            if(StringUtils.hasText(usercode)){
                sHql+="and t.no in (select distinct a.no from OaWorkLog a  where  a.creater="+HQLUtils.buildHqlStringForSQL(usercode)+
                " or a.no in (select r.cid.addrbookid from AddressBookRelection r where r.cid.shareman= "+HQLUtils.buildHqlStringForSQL(usercode)+"))" ;
            }  
        }
      
       return sHql;
    }
    /**
     *  获日程安排列表
     */
     public List<OaWorkLog> listObjects(Map<String, Object> filterMap, PageDesc pageDesc) {
         String sHql=getHsql(filterMap);
         List<OaWorkLog> oaWorkLogList=baseDao.listObjects(sHql, filterMap, pageDesc);
         return oaWorkLogList;
     }
     
     /**
      *  获日程安排列表
      */
      public List<OaWorkLog> listObjects(Map<String, Object> filterMap) {
          String sHql=getHsql(filterMap);
          List<OaWorkLog> oaWorkLogList=baseDao.listObjects(sHql, filterMap);
          return oaWorkLogList;
      }
}

