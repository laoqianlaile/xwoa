package com.centit.oa.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.dao.AddressBookRelectionDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.AddressBookRelectionManager;

public class AddressBookRelectionManagerImpl extends BaseEntityManagerImpl<AddressBookRelection>
	implements AddressBookRelectionManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(AddressBookRelectionManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private AddressBookRelectionDao addressBookRelectionDao ;
	public void setAddressBookRelectionDao(AddressBookRelectionDao baseDao)
	{
		this.addressBookRelectionDao = baseDao;
		setBaseDao(this.addressBookRelectionDao);
	}
    @Override
    public List<AddressBookRelection> getUserlist(String djId,String biztype) {
        // TODO Auto-generated method stub
        return addressBookRelectionDao.getUserlist(djId,biztype);
    }
    @Override
    public void deleteuser(String djId) {
        // TODO Auto-generated method stub        
        addressBookRelectionDao.deleteuser(djId);
        return;
    }
    @Override
    public void deleteuser(String djId,String biztype) {
        // TODO Auto-generated method stub        
        addressBookRelectionDao.deleteuser(djId,biztype);
        return;
    }
    @Override
    public List<AddressBookRelection> getUserlist(String djId) {
        // TODO Auto-generated method stub
        return addressBookRelectionDao.getUserlist(djId);
    }
	
}

