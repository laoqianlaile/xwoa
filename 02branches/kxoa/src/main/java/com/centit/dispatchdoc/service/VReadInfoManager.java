package com.centit.dispatchdoc.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.VReadInfo;

public interface VReadInfoManager extends BaseEntityManager<VReadInfo> 
{

    public String getNextkey(String zt);

}
