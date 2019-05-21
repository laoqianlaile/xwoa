package com.centit.oa.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.CodeBook;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaSMSConfigDao;
import com.centit.oa.po.OaSMSConfig;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.OaSMSConfigManager;

public class OaSMSConfigManagerImpl extends BaseEntityManagerImpl<OaSMSConfig>
	implements OaSMSConfigManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(AddressBookRelectionManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private OaSMSConfigDao oaSMSConfigDao ;
	public void setOaSMSConfigDao(OaSMSConfigDao baseDao)
	{
		this.oaSMSConfigDao = baseDao;
		setBaseDao(this.oaSMSConfigDao);
	}
	
	
	
	
	 /**
     * 获取系统当前生效的短信配置
     * status 为B正常
     * smsid  为最大
     * 的数据
     * 若没有返回 new 对象
     * @return
     */
    @Override
    public OaSMSConfig getActiveSMSConfig() {
        
        OaSMSConfig oaSMSConfig=new OaSMSConfig();
        
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("status", "B");
        filterMap.put("ORDER_BY", "settime desc");
        List<OaSMSConfig>  oaSMSConfigList=oaSMSConfigDao.listObjects(filterMap);
        
        if(null!=oaSMSConfigList&&oaSMSConfigList.size()>0){
            oaSMSConfig=oaSMSConfigList.get(0);
        }
        
        return oaSMSConfig;
    }
    
    /**
     * 获取历史配置信息
     * @return
     */
    @Override
    public List<OaSMSConfig> listSMSConfigs() {
        
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put(CodeBook.ORDER_BY_HQL_ID, "settime desc"); 
        
        List<OaSMSConfig>  oaSMSConfigList=oaSMSConfigDao.listObjects(filterMap);
        
        return oaSMSConfigList;
    }
    
    
    /**
     * 启用某条历史记录
     */
    @Override
    public OaSMSConfig activeSMSConfig(OaSMSConfig oaSMSConfig) {
       
        
        oaSMSConfig.setStatus("B");
        oaSMSConfig.setSettime(new Date());
        
        saveObject(oaSMSConfig);
        
        //历史记录状态值维护
        if("B".equals(oaSMSConfig.getStatus())){
            oaSMSConfigDao.activeConfig(oaSMSConfig);
        }
        return oaSMSConfig;
    }
    
    /**
     * 保存短信配置信息
     * 修改编辑保存时只做新增操作
     */
    @Override
    public OaSMSConfig saveSMSConfig(OaSMSConfig oaSMSConfig) {
        
        oaSMSConfig.setSettime(new Date());
        
        saveObject(oaSMSConfig);
        
        //历史记录状态值维护
        if("B".equals(oaSMSConfig.getStatus())){
            oaSMSConfigDao.activeConfig(oaSMSConfig);
        }
        return oaSMSConfig;
    }
   
    
    /**
     * 保存对象
     * 主键生成规则SMS000000001
     */
    @Override
    public void saveObject(OaSMSConfig o) {
        if (!StringUtils.isNotBlank(o.getSmsid())) {
            o.setSmsid("SMS"
                    + oaSMSConfigDao.getNextKeyBySequence("S_SMSCONFIG", 12));
        }
        super.saveObject(o);
    }




    @Override
    public boolean checkSMSConfig(OaSMSConfig oaSMSConfig) {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * 修改状态
     * state 为B时  需置其他记录状态值B-D
     */

    @Override
    public void updateState(OaSMSConfig object) {
        //保存新状态
        OaSMSConfig oaSMSConfig =oaSMSConfigDao.getObjectById(object.getSmsid());
        
        oaSMSConfig.setStatus(object.getStatus());
        oaSMSConfig.setSettime(new Date());
        saveObject(oaSMSConfig);
        //历史记录状态值维护
        if("B".equals(object.getStatus())){
            oaSMSConfigDao.activeConfig(oaSMSConfig);
        }
    }
}

