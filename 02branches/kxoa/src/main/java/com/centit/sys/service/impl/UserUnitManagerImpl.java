package com.centit.sys.service.impl;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.dao.UserUnitDao;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.UserUnitManager;


public class UserUnitManagerImpl extends BaseEntityManagerImpl<FUserunit> implements UserUnitManager{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    private UserUnitDao userunitDao;


    public UserUnitDao getUserunitDao() {
        return userunitDao;
    }


    public void setUserunitDao(UserUnitDao userunitDao) {
        setBaseDao(userunitDao);
        this.userunitDao = userunitDao;
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}