package com.centit.powerbase.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.dao.VsuppowerinusingDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.dao.VOrgSupPowerDao;
import com.centit.powerruntime.po.VOrgSupPower;

public class VsuppowerinusingManagerImpl extends BaseEntityManagerImpl<Vsuppowerinusing>
	implements VsuppowerinusingManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VsuppowerinusingManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private VsuppowerinusingDao vsuppowerinusingDao ;
	private VOrgSupPowerDao vOrgSupPowerDao;
	public void setVsuppowerinusingDao(VsuppowerinusingDao baseDao)
	{
		this.vsuppowerinusingDao = baseDao;
		setBaseDao(this.vsuppowerinusingDao);
	}
    public void setvOrgSupPowerDao(VOrgSupPowerDao vOrgSupPowerDao) {
        this.vOrgSupPowerDao = vOrgSupPowerDao;
    }
    @Override
    public Vsuppowerinusing findB_PowerByItemId(String itemId) {
        // TODO Auto-generated method stub
        return vsuppowerinusingDao.findB_PowerByItemId(itemId);
    }
	
    
    public List<VOrgSupPower> listOrgSuppower(String hql, Map<String, Object> filterMap, PageDesc pageDesc) {
        return vOrgSupPowerDao.listObjects(hql, filterMap, pageDesc);
    }
  //获取系统事权列表 除去 已被事权人员关联配置的
    @Override
    public List<Vsuppowerinusing> listUserlist(String itemIds,
            Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return vsuppowerinusingDao.listUserlist(itemIds,filterMap,pageDesc);
    }
}

