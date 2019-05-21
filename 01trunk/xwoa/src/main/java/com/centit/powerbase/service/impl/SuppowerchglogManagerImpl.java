package com.centit.powerbase.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.dao.SuppowerchglogDao;
import com.centit.powerbase.dao.SuppowerqlbgsqDao;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.po.Suppowerchglog;
import com.centit.powerbase.po.Suppowerqlbgsq;
import com.centit.powerbase.po.Suppowerstatechglog;
import com.centit.powerbase.service.SuppowerchglogManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SuppowerchglogManagerImpl extends BaseEntityManagerImpl<Suppowerchglog>
	implements SuppowerchglogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(SuppowerchglogManager.class);

	private SuppowerchglogDao suppowerchglogDao ;
	private SuppowerqlbgsqDao suppowerqlbgsqDao;
	
	public void setSuppowerqlbgsqDao(SuppowerqlbgsqDao suppowerqlbgsqDao) {
        this.suppowerqlbgsqDao = suppowerqlbgsqDao;
    }
    public void setSuppowerchglogDao(SuppowerchglogDao baseDao)
	{
		this.suppowerchglogDao = baseDao;
		setBaseDao(this.suppowerchglogDao);
	}
	   
	  @Override
	   public Suppowerchglog getObjectByIdAndVersion(String itemId,
	            String version) {
	        return  suppowerchglogDao.getObjectByIdAndVersion( itemId, version);
	    }
	  public String genNextChangeId() {
	        return suppowerchglogDao.genNextChangeId();
	    }
	  //保存Suppowerchglog信息
	    public void saveSuppowerchglog(Suppowerchglog logBean){
	        suppowerchglogDao.saveObject(logBean);
	    }
	    public Suppowerqlbgsq getSuppowerqlbgsqInfo(String item_id,Long version){
	        return suppowerqlbgsqDao.getSuppowerqlbgsqInfo(item_id,version);
	    }
    @Override
    public List<Suppowerchglog> getlistVersionByItemid(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return suppowerchglogDao.getlistVersionByItemid(filterMap, pageDesc);
    }
   
}

