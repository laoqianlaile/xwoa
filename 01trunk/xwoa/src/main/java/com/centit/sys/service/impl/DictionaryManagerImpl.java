package com.centit.sys.service.impl;
/**
	@author codefan@centit.com
*/
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.DataCatalogDao;
import com.centit.sys.dao.DataDictionaryDao;
import com.centit.sys.dao.UserUnitDao;
import com.centit.sys.po.FDatacatalog;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FDatadictionaryId;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.DictionaryManager;

public class DictionaryManagerImpl extends BaseEntityManagerImpl<FDatacatalog>
	implements DictionaryManager{
    private static final long serialVersionUID = 1L;
	private DataCatalogDao catalogDao;
	private DataDictionaryDao dictionaryDao;
	private UserUnitDao userunitDao;
	
	public void setCatalogDao(DataCatalogDao dao) {
		this.catalogDao = dao;
		setBaseDao(dao);
	}

	public void setDictionaryDao(DataDictionaryDao dao) {
		this.dictionaryDao = dao;
	}
	
	public List<FDatadictionary> findByCdtbnm(String cdtbnm) {
		return dictionaryDao.findByCdtbnm(cdtbnm);
	}


	public FDatadictionary findById(FDatadictionaryId id) {
		return dictionaryDao.getObjectById(id);
	}

	public List<FDatacatalog> getGBCdctgs() {
		return catalogDao.getGBCdctgs();
	}

	public List<FDatacatalog> getSysCdctgs() {
		return catalogDao.getSysCdctgs();
	}
	public List<FDatacatalog> getUserCdctgs() {
		return catalogDao.getUserCdctgs();
	}

	public List<FDatadictionary> getSysCditms(Map<String,Object> filterDescMap){
		return dictionaryDao.listObjects(filterDescMap);
	}

	public void deleteCditms(FDatadictionaryId id)
	{     
	    FDatacatalog datacatalog= catalogDao.getObjectById(id.getCatalogcode());
	    datacatalog.setIsupload("0");
	    catalogDao.saveObject(datacatalog);
		dictionaryDao.deleteObjectById(id);
	}
	
	public void saveCditms(FDatadictionary dd)
	{
	    FDatacatalog datacatalog= catalogDao.getObjectById(dd.getCatalogcode());
	    datacatalog.setIsupload("0");
	    catalogDao.saveObject(datacatalog);
		dictionaryDao.saveObject(dd);
	}
	
	public String[] getFieldsDesc(String sDesc,String sType)
	{
		String [] nRes = {"数据代码","扩展代码(父代码)","扩展代码(排序号)","标记","数值","类型","数据描述"};
		if("T".equals(sType))
			nRes[1] = "上级代码";
		if(sDesc == null || "".equals(sDesc))
			return nRes;
		String [] s = StringUtils.split(sDesc,';');
		if(s==null)
			return nRes;
		int n= s.length;
		
		for(int i=0;i<n;i++){
			int p = s[i].indexOf(':');
			if( p > 1)
				nRes[i] = s[i].substring(0,p);
			else
				nRes[i] = s[i];
		}
		return nRes;
	}

    public UserUnitDao getUserunitDao() {
        return userunitDao;
    }

    public void setUserunitDao(UserUnitDao userunitDao) {
        this.userunitDao = userunitDao;
    }
    
    public List<FUserunit> getUserUnitByCdtbnm(String roleType,String Cdtbnm){
        return userunitDao.getSysUsersByRoleAndUnit(roleType, Cdtbnm, null);
    }
    
}
