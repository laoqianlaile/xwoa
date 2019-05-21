package com.centit.powerruntime.service.impl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.VoptBaseExceptionListDao;
import com.centit.powerruntime.po.VoptBaseExceptionList;
import com.centit.powerruntime.service.VoptBaseExceptionListManager;

public class VoptBaseExceptionListManagerImpl extends BaseEntityManagerImpl<VoptBaseExceptionList>
	implements VoptBaseExceptionListManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VoptBaseExceptionListManager.class);

    private VoptBaseExceptionListDao vOptBaseExceptionListDao ;

    public void setvOptBaseExceptionListDao(
            VoptBaseExceptionListDao baseDao) {
        this.vOptBaseExceptionListDao = baseDao;
        setBaseDao(this.vOptBaseExceptionListDao);
    }

    
    
}

