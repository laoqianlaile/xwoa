package com.centit.oa.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaKqvisitorgroupDao;
import com.centit.oa.po.OaKqvisitorgroup;
import com.centit.oa.service.OaKqvisitorgroupManager;

public class OaKqvisitorgroupManagerImpl extends BaseEntityManagerImpl<OaKqvisitorgroup>
	implements OaKqvisitorgroupManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaKqvisitorgroupManager.class);

	private OaKqvisitorgroupDao oaKqvisitorgroupDao ;
	public void setOaKqvisitorgroupDao(OaKqvisitorgroupDao baseDao)
	{
		this.oaKqvisitorgroupDao = baseDao;
		setBaseDao(this.oaKqvisitorgroupDao);
	}
    /**
   * 会议资料：信息djid 长度默认14
   */
  @Override
  public void saveObject(OaKqvisitorgroup o) {
      if (!StringUtils.hasText(o.getDjId())) {
          o.setDjId("LF"+oaKqvisitorgroupDao.getNextKeyBySequence("S_OAKQVISITORGROUP",12));
      }
      super.saveObject(o);
  }	
}

