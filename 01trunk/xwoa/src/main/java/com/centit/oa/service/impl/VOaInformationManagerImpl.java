package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.VOaInformationDao;
import com.centit.oa.po.VOaInformation;
import com.centit.oa.service.VOaInformationManager;

public class VOaInformationManagerImpl extends BaseEntityManagerImpl<VOaInformation>
	implements VOaInformationManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VOaInformationManager.class);

	private VOaInformationDao voaInformationDao ;

    public void setVoaInformationDao(VOaInformationDao baseDao) {
        this.voaInformationDao = baseDao;
        setBaseDao(this.voaInformationDao);
    }

    @Override
    public List<VOaInformation> listVoainformation(
            Map<String, Object> filterMap) {
        // TODO Auto-generated method stub
        return voaInformationDao.listVoainformation(filterMap);
    }

    @Override
    public List<VOaInformation> listInformationOfAttandList(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        String sHql = " from VOaInformation t where 1=1 ";    
            sHql+="and t.no in (" +
                  "select f.no from OaInfosummary f where f.creater= "+HQLUtils.buildHqlStringForSQL((String) filterMap.get("userCode"))+" ) ";
        List<VOaInformation> list=baseDao.listObjects(sHql, filterMap, pageDesc);
        return list;
    }	
    
    public List<VOaInformation> listUnReadInfos(Map<String, Object> filterMap, PageDesc pageDesc){
        return voaInformationDao.listUnReadInfo(filterMap, pageDesc);
    }
}

