package com.centit.powerbase.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerbase.po.Suppowerstatechglog;
import com.centit.powerbase.dao.SuppowerstatechglogDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerbase.service.SuppowerstatechglogManager;

public class SuppowerstatechglogManagerImpl extends BaseEntityManagerImpl<Suppowerstatechglog>
	implements SuppowerstatechglogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(SuppowerstatechglogManager.class);

	private SuppowerstatechglogDao suppowerstatechglogDao ;
	public void setSuppowerstatechglogDao(SuppowerstatechglogDao baseDao)
	{
		this.suppowerstatechglogDao = baseDao;
		setBaseDao(this.suppowerstatechglogDao);
	}
    @Override
    public Suppowerstatechglog getObjectByIdAndVersion(String itemId,
            Long version) {
       
        return  suppowerstatechglogDao.getObjectByIdAndVersion( itemId,
                 version);
    }
    public String generateNextPunishClassID() {
        return suppowerstatechglogDao.genNextPunishClassID();
    }
    
    public void saveObject(Suppowerstatechglog log){
        log.setEndTime(log.getBeginTime());
        suppowerstatechglogDao.updateSuppowerstatechglog(log);
        log.setEndTime(null);
        suppowerstatechglogDao.saveObject(log);
        
    }

}

