package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaServiceentrance;

public interface OaServiceentranceManager extends BaseEntityManager<OaServiceentrance> 
{
    public List<OaServiceentrance> getListByusercode(String usercode);
}
