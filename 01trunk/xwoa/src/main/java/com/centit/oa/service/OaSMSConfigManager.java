package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaSMSConfig;

public interface OaSMSConfigManager extends BaseEntityManager<OaSMSConfig> 
{
    /**
     * 获取系统当前生效的短信配置
     * @return
     */
    OaSMSConfig getActiveSMSConfig();
    
    /**
     * 获取历史配置信息
     * @return
     */
    List<OaSMSConfig> listSMSConfigs();
    
    
    /**
     * 保存短信配置信息
     */
    OaSMSConfig saveSMSConfig(OaSMSConfig oaSMSConfig);
    
    
    /**
     * 检验短信配置是否可用
     * @param oaSMSConfig
     * @return
     */
    boolean  checkSMSConfig(OaSMSConfig oaSMSConfig);
    
    /**
     * 启用某条历史记录
     * @param oaSMSConfig
     * @return
     */
    OaSMSConfig activeSMSConfig(OaSMSConfig oaSMSConfig);
    
    /**
     * 修改状态
     */
    void updateState(OaSMSConfig object);
}
