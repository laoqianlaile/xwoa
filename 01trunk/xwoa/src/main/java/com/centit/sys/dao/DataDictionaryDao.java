package com.centit.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.sys.po.FDatadictionary;

public class DataDictionaryDao extends BaseDaoImpl<FDatadictionary> {
    private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public List<FDatadictionary> findByCdtbnm(String cdtbnm) 
	{//查询状态为在用的
		return getHibernateTemplate().find("FROM FDatadictionary WHERE id.catalogcode = ?  and  (datatag='T' or datatag is null) ORDER BY extracode2  ",cdtbnm);
	}


	//转换主键中的 字段描述 对应关系
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
			filterField.put("datacode" , "id.datacode=?");
			filterField.put("catalogcode", "id.catalogcode=?");
            filterField.put("NP_system" , "datastyle='S'");
            filterField.put("datavalue" , CodeBook.LIKE_HQL_ID);
            filterField.put(CodeBook.ORDER_BY_HQL_ID, "extracode2");
		}
		return filterField;
	}	


	public String getNextPrimarykey() {
		return HQLUtils
				.object2String(getNextKeyByHqlStrOfMax(
						"fDatadictionary.id.datacode",
						"FDatadictionary fDatadictionary WHERE length(fDatadictionary.id.datacode)=12"));
	}

	public void deleteDictionary(String catalog) {
		try {
			doExecuteHql("delete from FDatadictionary where id.catalogcode =?",catalog);
			log.debug("delete dictionary successful");
		} catch (RuntimeException re) {
			log.error("delete dictionary failed", re);
			throw re;
		}		
	}

}
