package com.centit.powerbase.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.utils.PageDesc;
import com.centit.powerbase.dao.DataenterpriseDao;
import com.centit.powerbase.dao.DataindividualDao;
import com.centit.powerbase.po.Dataenterprise;
import com.centit.powerbase.po.Dataindividual;

public class DataBasicInfoImpl implements IDataBasicInfo {
    
    private DataindividualDao dataindividualDao;
    private DataenterpriseDao dataenterpriseDao;

    public void setDataindividualDao(DataindividualDao dataindividualDao) {
        this.dataindividualDao = dataindividualDao;
    }

    public void setDataenterpriseDao(DataenterpriseDao dataenterpriseDao) {
        this.dataenterpriseDao = dataenterpriseDao;
    }

    @Override
    public List<Dataenterprise> getDataenterpriseList(Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return this.dataenterpriseDao.listObjects(filterMap, pageDesc);
    }
    @Override
    public List<Dataenterprise> getDataenterpriseList(Map<String, Object> filterMap) {
        // TODO Auto-generated method stub
        return this.dataenterpriseDao.listObjects(filterMap);
    }
    
    @Override
    public List<Dataenterprise> getDataenterpriseList(String shql, Map<String, Object> filterMap,PageDesc pageDesc){
        return this.dataenterpriseDao.listObjects(shql, filterMap,pageDesc);
    }

    @Override
    public List<Dataindividual> getDataindividualList(Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return this.dataindividualDao.listObjects(filterMap, pageDesc);
    }
    @Override
    public List<Dataindividual> getDataindividualList(Map<String, Object> filterMap) {
        // TODO Auto-generated method stub
        return this.dataindividualDao.listObjects(filterMap);
    }
    
    @Override
    public List<Dataindividual> getDataindividualList(String shql, Map<String, Object> filterMap,PageDesc pageDesc){
        return this.dataindividualDao.listObjects(shql, filterMap,pageDesc);
    }

    @Override
    public Dataenterprise getDataenterprise(Long enterpriseid) {
        // TODO Auto-generated method stub
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("enterpriseid", enterpriseid);
        List<Dataenterprise> listObjects = this.dataenterpriseDao.listObjects(filterMap);
        Dataenterprise enterprise = null;
        if(listObjects.size()>0)
            enterprise = listObjects.get(0);
        return enterprise;
    }
    

    @Override
    public Dataindividual getDataindividual(Long individualid) {
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("individualid", individualid);
        List<Dataindividual> listObjects = this.dataindividualDao.listObjects(filterMap);
        Dataindividual individual = null;
        if(listObjects.size()>0)
            individual = listObjects.get(0);
        return individual;
    }

    @Override
    public void saveDataenterprise(Dataenterprise enterprise) {
        // TODO Auto-generated method stub
        this.dataenterpriseDao.saveObject(enterprise);
    }

    @Override
    public void saveDataenterprise(Dataindividual individual) {
        // TODO Auto-generated method stub
        this.dataindividualDao.saveObject(individual);
    }

}
