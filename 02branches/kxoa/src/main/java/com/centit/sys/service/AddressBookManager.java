package com.centit.sys.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.AddressBook;

public interface AddressBookManager extends BaseEntityManager<AddressBook> 
{

	public Long getNextAddressId();

}
