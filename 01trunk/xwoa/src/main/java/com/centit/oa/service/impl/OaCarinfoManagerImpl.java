package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaCarinfo;
import com.centit.oa.dao.OaCarinfoDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaCarinfoManager;

public class OaCarinfoManagerImpl extends BaseEntityManagerImpl<OaCarinfo>
	implements OaCarinfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaCarinfoManager.class);

	private OaCarinfoDao oaCarinfoDao ;
	public void setOaCarinfoDao(OaCarinfoDao baseDao)
	{
		this.oaCarinfoDao = baseDao;
		setBaseDao(this.oaCarinfoDao);
	}
	/**
     * 车辆：信息djid 长度默认14
     */
    @Override
    public void saveObject(OaCarinfo o) {
        if (!StringUtils.hasText(o.getDjid())) {
            o.setDjid("CLXX"+oaCarinfoDao.getNextKeyBySequence("S_CARINFO",12));
        }
        super.saveObject(o);
    }
}

