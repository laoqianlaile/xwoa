package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaDriverInfo;
import com.centit.oa.dao.OaDriverInfoDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaDriverInfoManager;

public class OaDriverInfoManagerImpl extends BaseEntityManagerImpl<OaDriverInfo>
	implements OaDriverInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaDriverInfoManager.class);

	private OaDriverInfoDao oaDriverInfoDao ;
	public void setOaDriverInfoDao(OaDriverInfoDao baseDao)
	{
		this.oaDriverInfoDao = baseDao;
		setBaseDao(this.oaDriverInfoDao);
	}
	/**
	 * 车辆：司机信息No 长度默认14
	 */
    @Override
    public void saveObject(OaDriverInfo o) {
        if (!StringUtils.hasText(o.getNo())) {
            o.setNo("CLSJ"+oaDriverInfoDao.getNextKeyBySequence("S_DRIVERINFO",12));
        }
        super.saveObject(o);
    }
    @Override
    public String getNextKey() {
        // TODO Auto-generated method stub
        return "CLSJ"+oaDriverInfoDao.getNextKeyBySequence("S_DRIVERINFO",12);
    }
}

