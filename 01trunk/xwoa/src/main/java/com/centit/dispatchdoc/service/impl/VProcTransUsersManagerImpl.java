package com.centit.dispatchdoc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.VProcTransUsersDao;
import com.centit.dispatchdoc.po.VProcTransUsers;
import com.centit.dispatchdoc.service.VProcTransUsersManager;

public class VProcTransUsersManagerImpl extends BaseEntityManagerImpl<VProcTransUsers> implements VProcTransUsersManager{

    
    
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VProcTransUsersManager.class);

    private VProcTransUsersDao vprocTransUsersDao ;
    
    
    public void setVprocTransUsersDao(VProcTransUsersDao vprocTransUsersDao) {
        this.vprocTransUsersDao = vprocTransUsersDao;
        setBaseDao(this.vprocTransUsersDao);
    }


    
}
