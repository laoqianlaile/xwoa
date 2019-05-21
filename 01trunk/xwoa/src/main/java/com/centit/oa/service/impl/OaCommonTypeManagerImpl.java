package com.centit.oa.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaCommonTypeDao;
import com.centit.oa.po.OaCommonType;
import com.centit.oa.service.OaCommonTypeManager;

public class OaCommonTypeManagerImpl extends BaseEntityManagerImpl<OaCommonType>
	implements OaCommonTypeManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaCommonTypeManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private OaCommonTypeDao oaCommonTypeDao ;
	public void setOaCommonTypeDao(OaCommonTypeDao baseDao)
	{
		this.oaCommonTypeDao = baseDao;
		setBaseDao(this.oaCommonTypeDao);
	}
	
	  //办公类型no,总长度默认14
	  @Override
      public void saveObject(OaCommonType o) {
          if (!StringUtils.hasText(o.getNo())) {
              o.setNo("BGLX"+oaCommonTypeDao.getNextKeyBySequence("S_COMMON_TYPE_NO",12));
          }
          super.saveObject(o);
      }
}

