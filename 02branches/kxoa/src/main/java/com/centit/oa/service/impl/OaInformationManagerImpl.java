package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaInformation;
import com.centit.oa.dao.OaInformationDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaInformationManager;

public class OaInformationManagerImpl extends BaseEntityManagerImpl<OaInformation>
	implements OaInformationManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaInformationManager.class);

	private OaInformationDao oaInformationDao ;
	public void setOaInformationDao(OaInformationDao baseDao)
	{
		this.oaInformationDao = baseDao;
		setBaseDao(this.oaInformationDao);
	}
    @Override
    public String getNextkey(String tag) {
        // TODO Auto-generated method stub
        return "INFO"+oaInformationDao.getNextKeyBySequence("S_OaInformation",12);
    }
    @Override
    public void autoInvalidated() {
        // TODO Auto-generated method stub
        oaInformationDao.autoInvalidated();
    }
    @Override
    public List<OaInformation> listObjects(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        String sHql = " from OaInformation t where 1=1 ";
        String F_userBizopt_log="";
        if(null!=filterMap.get("notInUserBizoptLog")){
            F_userBizopt_log=(String)filterMap.get("F_userBizopt_log");
        }
        if(StringUtils.hasText(F_userBizopt_log)){
            sHql+="and t.no not in (" +
                  "select f.dj_id from F_userBizopt_log f where f.createUser= "+HQLUtils.buildHqlStringForSQL(F_userBizopt_log)+" ) ";
        }
        List<OaInformation> list=baseDao.listObjects(sHql, filterMap, pageDesc);
        return list;
    }
	
}

