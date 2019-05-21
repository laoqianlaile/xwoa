package com.centit.oa.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.service.AddressBookRelectionManager;
	

public class AddressBookRelectionAction  extends BaseEntityExtremeAction<AddressBookRelection>  {
	private static final Log log = LogFactory.getLog(AddressBookRelectionAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private AddressBookRelectionManager addressBookRelectionMag;
	public void setAddressBookRelectionManager(AddressBookRelectionManager basemgr)
	{
		addressBookRelectionMag = basemgr;
		this.setBaseEntityManager(addressBookRelectionMag);
	}

	
	
		
	public static Log getLog() {
        return log;
    }




    public static long getSerialversionuid() {
        return serialVersionUID;
    }




    public String delete() {
	    super.delete();      
	    
	    return "delete";
	}
}
