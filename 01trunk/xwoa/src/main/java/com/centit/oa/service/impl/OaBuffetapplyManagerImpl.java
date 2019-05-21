package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaBuffetapply;
import com.centit.oa.dao.OaBuffetapplyDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaBuffetapplyManager;

public class OaBuffetapplyManagerImpl extends BaseEntityManagerImpl<OaBuffetapply>
	implements OaBuffetapplyManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaBuffetapplyManager.class);

	private OaBuffetapplyDao oaBuffetapplyDao ;
	public void setOaBuffetapplyDao(OaBuffetapplyDao baseDao)
	{
		this.oaBuffetapplyDao = baseDao;
		setBaseDao(this.oaBuffetapplyDao);
	}
	 /**
     * 查询期号
     * 
     */
  @Override
  public String getNextKeyMax() {
    
             String keynum=oaBuffetapplyDao.getNextKeyMax("layoutno", " OaBuffetapply ");
             if("0".equals(keynum)){
                 return "1";
             }
             return keynum;
         } 

}

