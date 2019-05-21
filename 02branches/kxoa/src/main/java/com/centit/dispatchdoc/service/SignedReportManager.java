package com.centit.dispatchdoc.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.SignedReport;

public interface SignedReportManager extends BaseEntityManager<SignedReport> 
{

    public String getNextkey(String zt);

}
