package com.centit.sys.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.VUserOnlineDao;
import com.centit.sys.po.VUserOnline;
import com.centit.sys.service.VUserOnlineManager;

public class VUserOnlineManagerImpl extends BaseEntityManagerImpl<VUserOnline>
        implements VUserOnlineManager {
    /**
* 
*/
    private static final long serialVersionUID = 1L;

    private VUserOnlineDao vUserOnlineDao;

    public void setVUserOnlineDao(VUserOnlineDao vUserOnlineDao) {
        this.vUserOnlineDao = vUserOnlineDao;
        setBaseDao(vUserOnlineDao);
    }

    @Override
    public void clearUserOnlineState() {
        // TODO Auto-generated method stub
        vUserOnlineDao.clearUserOnlineState();   
    }

}
