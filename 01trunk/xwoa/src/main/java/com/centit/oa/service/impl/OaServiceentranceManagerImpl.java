package com.centit.oa.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaServiceentrance;
import com.centit.oa.dao.OaServiceentranceDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaServiceentranceManager;

public class OaServiceentranceManagerImpl extends BaseEntityManagerImpl<OaServiceentrance>
	implements OaServiceentranceManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaServiceentranceManager.class);

	private OaServiceentranceDao oaServiceentranceDao ;
	public void setOaServiceentranceDao(OaServiceentranceDao baseDao)
	{
		this.oaServiceentranceDao = baseDao;
		setBaseDao(this.oaServiceentranceDao);
	}
    @Override
    public List<OaServiceentrance> getListByusercode(String usercode) {
        return oaServiceentranceDao.getListByusercode(usercode);
    }
	
}

