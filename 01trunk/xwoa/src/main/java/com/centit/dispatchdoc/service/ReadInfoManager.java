package com.centit.dispatchdoc.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.ReadInfo;

public interface ReadInfoManager extends BaseEntityManager<ReadInfo> 
{

    public String getNextkey(String zt);

}
