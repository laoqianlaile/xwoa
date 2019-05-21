package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSurvey;
import com.centit.oa.dao.OaSurveyDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaSurveyManager;

public class OaSurveyManagerImpl extends BaseEntityManagerImpl<OaSurvey>
	implements OaSurveyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaSurveyManager.class);

	private OaSurveyDao oaSurveyDao ;
	public void setOaSurveyDao(OaSurveyDao baseDao)
	{
		this.oaSurveyDao = baseDao;
		setBaseDao(this.oaSurveyDao);
	}
	
    /**
     * 在线调查信息
     */
    @Override
    public void saveObject(OaSurvey o) {
        if (!StringUtils.hasText(o.getDjid())) {
            o.setDjid("ZXDC"
                    + oaSurveyDao.getNextKeyBySequence("S_OA_SURVEY_NO", 12));
        }
        super.saveObject(o);
    }
    
    private String getHsql(Map<String, Object> filterMap){
        String usercode=(String) filterMap.get("ownerCode");
        //creater为当前登录人的，或者AddressBookRelection 共享里面sharename为登录人
        String sHql=" from OaSurvey t where 1=1 ";
        //只查看自己(勾选)
        if(("true").equals(filterMap.get("owner"))){
            if(StringUtils.hasText(usercode)){
                sHql+="and t.djid in (select distinct a.djid from OaSurvey a  where  a.creater="+HQLUtils.buildHqlStringForSQL(usercode)+")";
               
            } 
        }else{
            //查看自己，以及别人分享过来的的工作日志
            if(StringUtils.hasText(usercode)){
                sHql+="and t.djid in (select distinct a.djid from OaSurvey a  where  a.creater="+HQLUtils.buildHqlStringForSQL(usercode)+
                " or a.djid in (select r.cid.addrbookid from AddressBookRelection r where r.cid.shareman= "+HQLUtils.buildHqlStringForSQL(usercode)+" ))" ;
            }  
        }
      
       return sHql;
    }
    /**
     * 获调查列表
     */
     public List<OaSurvey> listObjects(Map<String, Object> filterMap, PageDesc pageDesc) {
         String sHql=getHsql(filterMap);
         List<OaSurvey> oaSurveyList=baseDao.listObjects(sHql, filterMap, pageDesc);
         return oaSurveyList;
     }

    @Override
    public void autoEnd() {
        oaSurveyDao.autoEnd();
        
    }
}

