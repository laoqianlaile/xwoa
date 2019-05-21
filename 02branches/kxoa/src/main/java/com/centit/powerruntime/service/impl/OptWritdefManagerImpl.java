package com.centit.powerruntime.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.OptWritdefDao;
import com.centit.powerruntime.po.OptWritdef;
import com.centit.powerruntime.service.OptWritdefManager;
import com.centit.sys.po.FDatadictionary;

public class OptWritdefManagerImpl extends BaseEntityManagerImpl<OptWritdef>
	implements OptWritdefManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OptWritdefManager.class);

	private OptWritdefDao optWritdefDao ;
	public void setOptWritdefDao(OptWritdefDao baseDao)
	{
		this.optWritdefDao = baseDao;
		setBaseDao(this.optWritdefDao);
	}
	
    @Override
    public List<OptWritdef> getObjectsByTempType(String tempType) {
        return optWritdefDao.getObjectsByTempType(tempType);
    }

    @Override
    public List<FDatadictionary> getItemTypesWithOutHave() {
        return optWritdefDao.getItemTypesWithOutHave();
    }

    @Override
    public OptWritdef getObjectByTempType(String tempType) {
        return optWritdefDao.getObjectByTempType(tempType);
    }
	
}

