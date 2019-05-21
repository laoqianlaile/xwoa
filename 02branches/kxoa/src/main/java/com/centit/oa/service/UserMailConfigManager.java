package com.centit.oa.service;

import java.util.List;

import com.centit.oa.po.UserMailConfig;
import com.centit.core.service.BaseEntityManager;

public interface UserMailConfigManager extends BaseEntityManager<UserMailConfig> {
    public List<UserMailConfig> getObjectsByCodeAndAccount(String usercode,String emailaccount);
}
