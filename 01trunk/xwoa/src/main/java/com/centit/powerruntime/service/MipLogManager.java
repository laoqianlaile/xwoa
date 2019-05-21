package com.centit.powerruntime.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.MipLog;

public interface MipLogManager extends BaseEntityManager<MipLog> 
{
public String nextKey();
}
