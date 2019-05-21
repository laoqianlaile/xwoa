package com.centit.powerruntime.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.Suppowerstuffgroup;

public interface SuppowerstuffgroupManager extends BaseEntityManager<Suppowerstuffgroup>  {
    
    public String  getNextGroupCode();

}
