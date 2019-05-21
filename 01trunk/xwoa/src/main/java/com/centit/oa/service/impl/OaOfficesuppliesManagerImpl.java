package com.centit.oa.service.impl;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaOfficesupplies;
import com.centit.oa.dao.OaOfficesuppliesDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaOfficesuppliesManager;

public class OaOfficesuppliesManagerImpl extends BaseEntityManagerImpl<OaOfficesupplies>
	implements OaOfficesuppliesManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaOfficesuppliesManager.class);

	private OaOfficesuppliesDao oaOfficesuppliesDao ;
	public void setOaOfficesuppliesDao(OaOfficesuppliesDao baseDao)
	{
		this.oaOfficesuppliesDao = baseDao;
		setBaseDao(this.oaOfficesuppliesDao);
	}
	
	 /**
	   * 查询期号
	   * 
	   */
    @Override
    public String getNextKeyMax() {
      
               String keynum=oaOfficesuppliesDao.getNextKeyMax("layoutno", " OaOfficesupplies ");
               if("0".equals(keynum)){
                   return "1";
               }
               return keynum;
           } 
  
	
}

