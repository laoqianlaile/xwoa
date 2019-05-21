package com.centit.sys.dao;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.sys.po.AddressBook;

public class AddressBookDao extends BaseDaoImpl<AddressBook>
	{
    private static final long serialVersionUID = 1L;

    public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();
			filterField.put("memo" , CodeBook.LIKE_HQL_ID);
			filterField.put("searchstring" , CodeBook.LIKE_HQL_ID);
		}
		return filterField;
	} 
	
	@Override
	public void saveObject(AddressBook o) 
	{
		try {
			o.makeSearchString();
			getHibernateTemplate().saveOrUpdate(o);//.persist(o);//
			// log.debug("save or update successful");
		} catch (RuntimeException re) {
			log.error("save or update failed", re);
			throw re;
		}		
	}
}
