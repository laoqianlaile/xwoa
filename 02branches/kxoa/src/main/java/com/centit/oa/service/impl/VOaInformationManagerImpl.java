package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
   
   
	
}

