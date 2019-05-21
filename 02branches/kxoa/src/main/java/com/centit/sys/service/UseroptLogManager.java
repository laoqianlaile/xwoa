package com.centit.sys.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.UseroptLog;

public interface UseroptLogManager extends BaseEntityManager<UseroptLog> 
{
    public String getNextNO(String ev);
}
