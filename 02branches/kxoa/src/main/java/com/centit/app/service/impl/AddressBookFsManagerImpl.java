package com.centit.app.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.app.po.AddressBookFs;
import com.centit.app.dao.AddressBookFsDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.service.AddressBookFsManager;

public class AddressBookFsManagerImpl extends BaseEntityManagerImpl<AddressBookFs>
	implements AddressBookFsManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(AddressBookFsManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private AddressBookFsDao addressBookFsDao ;
	public void setAddressBookFsDao(AddressBookFsDao baseDao)
	{
		this.addressBookFsDao = baseDao;
		setBaseDao(this.addressBookFsDao);
	}
    @Override
    public List<Object> listBodyType(){
        return this.addressBookFsDao.listBodyType();
    }
    @Override
    public List<Object> listUnits(){
        return this.addressBookFsDao.listUnits();
    }
    @Override
    public AddressBookFs getObjectByCode(String bodycode,String bodytype) {
        return addressBookFsDao.getObjectByCode(bodycode,bodytype);
    }
    @Override
    public Long getNextLongSequence(String str) {
        return addressBookFsDao.getNextLongSequence(str);
    }
    @Override
    public AddressBookFs getObjectByUsername(String username, String bodytype) {
        // TODO Auto-generated method stub
        return addressBookFsDao.getObjectByUsername(username, bodytype);
    }
    public Object getObjectByPhone(String phone,String bodytype){
        return addressBookFsDao.getObjectByPhone(phone, bodytype);
    }
    public Object getObjectByPPhone(String phone,String bodytype){
        return addressBookFsDao.getObjectByPPhone(phone, bodytype);
    }
}

