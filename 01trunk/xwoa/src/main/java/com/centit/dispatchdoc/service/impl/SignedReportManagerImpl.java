package com.centit.dispatchdoc.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.po.SignedReport;
import com.centit.dispatchdoc.dao.SignedReportDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.service.SignedReportManager;

public class SignedReportManagerImpl extends BaseEntityManagerImpl<SignedReport>
	implements SignedReportManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(SignedReportManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private SignedReportDao signedReportDao ;
	public void setSignedReportDao(SignedReportDao baseDao)
	{
		this.signedReportDao = baseDao;
		setBaseDao(this.signedReportDao);
	}
    @Override
    public String getNextkey(String zt) {
        // TODO Auto-generated method stub
        return zt + signedReportDao.getNextKeyBySequence("S_signed_report", 14);
    }
	
}

