package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaInfosummary;

public interface OaInfosummaryManager extends BaseEntityManager<OaInfosummary> 
{
    public  List<OaInfosummary> findInfoAttend(String no,String creater);

    public String getNextkey(String tag);
    
    public  List<OaInfosummary> findInfoAttendByUser(String creater);
}
