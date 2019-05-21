package com.centit.oa.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.efile.mgt.EfileManager;
import com.centit.efile.model.EfileStore;
import com.centit.oa.dao.OptFilingCabinetsDao;
import com.centit.oa.po.OptFilingCabinets;
import com.centit.oa.service.OptFilingCabinetsManager;

public class OptFilingCabinetsManagerImpl extends BaseEntityManagerImpl<OptFilingCabinets> implements OptFilingCabinetsManager{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptFilingCabinetsDao optFilingCabinetsDao;

    public OptFilingCabinetsDao getOptFilingCabinetsDao() {
        return optFilingCabinetsDao;
    }




    public void setOptFilingCabinetsDao(OptFilingCabinetsDao optFilingCabinetsDao) {
        this.optFilingCabinetsDao = optFilingCabinetsDao;
        super.setBaseDao(optFilingCabinetsDao);
    }




    @Override
    public Long getNextSequence() {
        return optFilingCabinetsDao.getNextLongSequence("SEQ_OPT_FIlING_CABINETS");
    }




    @Override
    public List<OptFilingCabinets> save(String refId, String rootDir,String subdir, File[] files, String[] fileName) {
        List<OptFilingCabinets> result = new ArrayList<OptFilingCabinets>();
        try{
            //保存附件信息
              if(files!=null){
                  for(int i=0;i<files.length;i++){
                      EfileStore efileStore = new EfileStore(files[i], fileName[i],  rootDir+subdir);
                      EfileManager.store(efileStore);
                      
                      OptFilingCabinets optFilingCabinets = new OptFilingCabinets();
                      optFilingCabinets.setId(getNextSequence());
                      optFilingCabinets.setRefId(refId);
                      optFilingCabinets.setFileName(fileName[i]);
                      optFilingCabinets.setFileSize(files[i].length());
                      optFilingCabinets.setFilePath(efileStore.getAbsolutePath().replace(rootDir, ""));
                      super.saveObject(optFilingCabinets);
                      result.add(optFilingCabinets);
                  }
              }
          }catch(Exception e){
              e.printStackTrace();
          }
        return result;
    }




    @Override
    public void save(List<OptFilingCabinets> objList,String refId) {
        if(objList!=null){
            for(OptFilingCabinets item : objList){
                OptFilingCabinets other = new OptFilingCabinets();
                other.copy(item);
                other.setId(getNextSequence());
                other.setRefId(refId);
                super.saveObject(other);
            }
        }
    }
}
