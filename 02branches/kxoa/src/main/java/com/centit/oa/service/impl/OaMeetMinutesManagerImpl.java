package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaMeetMinutes;
import com.centit.oa.po.VoaMeetMinute;
import com.centit.oa.dao.OaMeetMinutesDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaMeetMinutesManager;

public class OaMeetMinutesManagerImpl extends BaseEntityManagerImpl<OaMeetMinutes>
	implements OaMeetMinutesManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaMeetMinutesManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private OaMeetMinutesDao oaMeetMinutesDao ;
	public void setOaMeetMinutesDao(OaMeetMinutesDao baseDao)
	{
		this.oaMeetMinutesDao = baseDao;
		setBaseDao(this.oaMeetMinutesDao);
	}
    @Override
    public List<VoaMeetMinute> getMaxlist(Map<String, Object> filterMap,
            PageDesc pageDesc,String userCode) {
        
        return oaMeetMinutesDao.getMaxlist(filterMap,pageDesc,userCode);
    }
    @Override
    public List<OaMeetMinutes> getversionbyid(String djId) {
        
        return (List<OaMeetMinutes>) oaMeetMinutesDao.getversion(djId);
    }

	
}

