package com.centit.powerbase.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSchedule;
import com.centit.powerbase.po.PowerUserInfo;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.dao.PowerUserInfoDao;
import com.centit.powerbase.dao.VPowerUserInfoDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerbase.service.PowerUserInfoManager;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.po.FUserinfo;

public class PowerUserInfoManagerImpl extends BaseEntityManagerImpl<PowerUserInfo>
	implements PowerUserInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(PowerUserInfoManager.class);

	private PowerUserInfoDao powerUserInfoDao ;
	private VPowerUserInfoDao vPowerUserInfoDao ;
	private UserInfoDao userInfoDao;
	
	public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }
    public void setPowerUserInfoDao(PowerUserInfoDao baseDao)
	{
		this.powerUserInfoDao = baseDao;
		setBaseDao(this.powerUserInfoDao);
	}
    @Override
    public List<VPowerUserInfo> getVList(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        
       
        return  vPowerUserInfoDao.getVList(filterMap, pageDesc);
                
    }
  
    @Override
    public void delectByItemId(String itemId) {
        powerUserInfoDao.delectByItemId( itemId);
        
    }

    @Override
    public void delectByUsercode(String usercode) {
        powerUserInfoDao.delectByUsercode( usercode);
        
    }
    
    
    public VPowerUserInfoDao getVPowerUserInfoDao() {
        return vPowerUserInfoDao;
    }
    public void setVPowerUserInfoDao(VPowerUserInfoDao vPowerUserInfoDao) {
        this.vPowerUserInfoDao = vPowerUserInfoDao;
    }
    //获取系统人员 除去已被关联配置的
    @Override
    public List<FUserinfo> listUserCodes(String itemId,
            Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return userInfoDao.listUserCodes(itemId,filterMap,pageDesc);
    }
}

