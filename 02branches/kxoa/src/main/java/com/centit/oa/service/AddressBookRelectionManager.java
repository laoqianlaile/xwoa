package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.AddressBookRelection;

public interface AddressBookRelectionManager extends BaseEntityManager<AddressBookRelection> 
{

   public List<AddressBookRelection> getUserlist(String djId,String biztype);
   public List<AddressBookRelection> getUserlist(String djId);
   public void deleteuser(String djId);
   public void deleteuser(String djId,String biztype);
}
