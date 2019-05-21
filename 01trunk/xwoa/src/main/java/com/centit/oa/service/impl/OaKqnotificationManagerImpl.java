package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaKqnotification;
import com.centit.oa.dao.OaKqnotificationDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaKqnotificationManager;

public class OaKqnotificationManagerImpl extends BaseEntityManagerImpl<OaKqnotification>
	implements OaKqnotificationManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaKqnotificationManager.class);

	private OaKqnotificationDao oaKqnotificationDao ;
	public void setOaKqnotificationDao(OaKqnotificationDao baseDao)
	{
		this.oaKqnotificationDao = baseDao;
		setBaseDao(this.oaKqnotificationDao);
	}
	 /**
     * 查询期号
     * 
     */
  @Override
  public String getNextKeyMax() {
    
             String keynum=oaKqnotificationDao.getNextKeyMax("periods", " OaKqnotification ");
             if("0".equals(keynum)){
                 return "1";
             }
             return keynum;
         } 

}

