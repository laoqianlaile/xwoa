package com.centit.oa.service;

import java.io.File;
import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OptFilingCabinets;

public interface OptFilingCabinetsManager extends BaseEntityManager<OptFilingCabinets>{
    public Long getNextSequence();
    
    public List<OptFilingCabinets> save(String refId,String rootDir,String subdir,File[] files,String[]fileName);
    
    public void save(List<OptFilingCabinets> objList,String refId);
}
