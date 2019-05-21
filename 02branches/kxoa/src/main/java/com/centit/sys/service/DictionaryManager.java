package com.centit.sys.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.FDatacatalog;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FDatadictionaryId;
import com.centit.sys.po.FUserunit;

public interface DictionaryManager extends BaseEntityManager<FDatacatalog>{
	public List<FDatacatalog> getSysCdctgs();
	public List<FDatacatalog> getUserCdctgs();
	public List<FDatacatalog> getGBCdctgs();
	
	public void deleteCditms(FDatadictionaryId id);
	public void saveCditms(FDatadictionary dd);
	
	public List<FDatadictionary> getSysCditms(Map<String,Object> filterDescMap);
	public FDatadictionary findById(FDatadictionaryId id);
	public List<FDatadictionary> findByCdtbnm(String cdtbnm);
	public String[] getFieldsDesc(String sDesc,String sType);
	public List<FUserunit> getUserUnitByCdtbnm(String roleType,String Cdtbnm);
}
