package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaMeetinfoDao;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.po.FUserinfo;

public class OaMeetinfoManagerImpl extends BaseEntityManagerImpl<OaMeetinfo>
	implements OaMeetinfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaMeetinfoManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private OaMeetinfoDao oaMeetinfoDao ;
	
	

    public void setOaMeetinfoDao(OaMeetinfoDao baseDao)
	{
		this.oaMeetinfoDao = baseDao;
		setBaseDao(this.oaMeetinfoDao);
	}

	    @Override
	    public void saveObject(OaMeetinfo o) {
	        if (!StringUtils.hasText(o.getDjid())) {
	            o.setDjid("HYSGL"+oaMeetinfoDao.getNextKeyBySequence("S_MEETDJID",11));
	        }
	        super.saveObject(o);
	    }

        @Override
        public List<OaMeetinfo> listObjectsWithOutLob(Map<String, Object> filterMapHys) {
            // TODO Auto-generated method stub
            return oaMeetinfoDao.listObjectsWithOutLob(filterMapHys);
        }

        @Override
        public List<OaMeetinfo> listObjectsWithOutLOB(Map<String, Object> picMap) {
            // TODO Auto-generated method stub
            return oaMeetinfoDao.listObjectsWithOutLOB(picMap);
        }

        @Override
        public OaMeetinfo getObjectByIdWithOutLOB(String djid) {
            return oaMeetinfoDao.getObjectByIdWithOutLOB(djid);
        }
        
      
}

