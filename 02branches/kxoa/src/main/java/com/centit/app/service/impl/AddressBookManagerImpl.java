package com.centit.app.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.AddressBookDao;
import com.centit.sys.po.AddressBook;
import com.centit.sys.service.AddressBookManager;

public class AddressBookManagerImpl extends BaseEntityManagerImpl<AddressBook>
	implements AddressBookManager{
    private static final long serialVersionUID = 1L;
	private AddressBookDao addressBookDao ;
	public void setAddressBookDao(AddressBookDao baseDao)
	{
		this.addressBookDao = baseDao;
		setBaseDao(this.addressBookDao);
	}
	public Long getNextAddressId()
	{
		String sKey = addressBookDao.getNextValueOfSequence("S_ADDRESSID");
		return Long.valueOf(sKey);
	}
}

