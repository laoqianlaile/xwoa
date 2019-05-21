package com.centit.oa.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaInfosummary;
import com.centit.oa.dao.OaInfosummaryDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaInfosummaryManager;

public class OaInfosummaryManagerImpl extends BaseEntityManagerImpl<OaInfosummary>
	implements OaInfosummaryManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaInfosummaryManager.class);

	private OaInfosummaryDao oaInfosummaryDao ;
	public void setOaInfosummaryDao(OaInfosummaryDao baseDao)
	{
		this.oaInfosummaryDao = baseDao;
		setBaseDao(this.oaInfosummaryDao);
	}
    @Override
    public List<OaInfosummary> findInfoAttend(String no, String creater) {
        return oaInfosummaryDao.findInfoAttend(no, creater);
    }
    @Override
    public String getNextkey(String tag) {
        // TODO Auto-generated method stub
        return "HDCY"+oaInfosummaryDao.getNextKeyBySequence("S_OaInfoSum",12);
    }
    @Override
    public List<OaInfosummary> findInfoAttendByUser(String creater) {
        // TODO Auto-generated method stub
        return oaInfosummaryDao.findInfoAttendByUser(creater);
    }
	
}

