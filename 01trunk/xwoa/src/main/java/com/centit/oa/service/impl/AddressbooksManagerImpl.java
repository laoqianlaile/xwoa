package com.centit.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.AddressbooksDao;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.AddressBookRelectionId;
import com.centit.oa.po.Addressbooks;
import com.centit.oa.service.AddressbooksManager;

public class AddressbooksManagerImpl extends BaseEntityManagerImpl<Addressbooks>
	implements AddressbooksManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(AddressbooksManager.class);
	
	//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

	private AddressbooksDao addressbooksDao ;
	public void setAddressbooksDao(AddressbooksDao baseDao)
	{
		this.addressbooksDao = baseDao;
		setBaseDao(this.addressbooksDao);
	}
    
	 @Override
	    //获取下一个个人通讯录
	 public String genNextAddressbookId() {
	    
	     return "GRTXL"+addressbooksDao.getNextKeyBySequence("S_addressbookId",11);
	   }

	 @Override
	    public List<String> listUsercodesByAddressbookId(String addrbookid) {
	        return addressbooksDao.listUsercodesByTrainId(addrbookid);
	    }

    @Override
    @Transactional
    public void saveObject(Addressbooks object) {
      //当页面勾选共享时同时保存共享对象
        List<AddressBookRelection> addressBookRelections=new  ArrayList<AddressBookRelection>();
        if ("1".equals(object.getIsshare())){
            addressBookRelections=getRelectionsByUsercodes(object.getAddrbookid(),object.getShareUserCode());   
        }else{
            object.setShareNames(null);
            object.setIsshare("0");
        }
        object.replaceAddressBookRelections( addressBookRelections);
        baseDao.saveObject(object);
        
    }
    /**
     *将usercodes转换为共享人员list
     * @param usercodes
     * @return
     */
  public List<AddressBookRelection> getRelectionsByUsercodes(String addressbookId,String usercodes) {
        
        List<AddressBookRelection> addressBookRelections=new ArrayList<AddressBookRelection>();
        if (StringUtils.hasText(usercodes)) {
            String[] usercode = usercodes.split(",");
            for (String s : usercode) {
                
                addressBookRelections.add(
                        //中间关联表
                        new AddressBookRelection(new AddressBookRelectionId(addressbookId,s,"a")));
            }
        }
        return addressBookRelections;
    }
  
  public List<Addressbooks> listObjects(Map<String, Object> filterMap, PageDesc pageDesc,String usercode) {
      //creater为当前登录人的，或者AddressBookRelection 共享里面sharename为登录人
      String sHql=" from Addressbooks t where 1=1 ";
     if(StringUtils.hasText(usercode)){
         sHql+="and t.addrbookid in (select distinct a.addrbookid from Addressbooks a  where a.creater="+HQLUtils.buildHqlStringForSQL(usercode)+
         " or a.addrbookid in (select r.cid.addrbookid from AddressBookRelection r where r.cid.shareman= "+HQLUtils.buildHqlStringForSQL(usercode)+"))" ;
     } 
      List<Addressbooks> addressooksList=baseDao.listObjects(sHql, filterMap, pageDesc);
      
     
      return addressooksList;
  }

@Override
public Addressbooks getObjectByCode(String unitcode, String type) {
    // TODO Auto-generated method stub
      return addressbooksDao.getObjectByCode(unitcode,type);
}
  
@Override
public Addressbooks getUserByCode(String usercode, String type) {
    // TODO Auto-generated method stub
      return addressbooksDao.getUserByCode(usercode,type);
}

@Override
public List<Addressbooks> addressbooksCList() {
    // TODO Auto-generated method stub
      return addressbooksDao.addressbooksCList();
}

@Override
public List<Addressbooks> addressbooksCList(Map<String, Object> filterMap, PageDesc pageDesc,String unitcode) {
    // TODO Auto-generated method stub
    String  sHql=" from Addressbooks t where t.type='C' ";
    if(StringUtils.hasText(unitcode)){
        
        if (!"true".equals(filterMap.get("queryUnderUnit"))) {
        sHql+=" and t.unitcode="+HQLUtils.buildHqlStringForSQL(unitcode);
        }
    }
    
    List<Addressbooks> addressooksList=baseDao.listObjects(sHql, filterMap, pageDesc);
      return addressooksList;
}

@Override
public List<Addressbooks> addressbooksCList(Map<String, Object> filterMap,String unitcode) {
    // TODO Auto-generated method stub
    String  sHql=" from Addressbooks t where t.type='C' ";
    if(StringUtils.hasText(unitcode)){
        
        if (!"true".equals(filterMap.get("queryUnderUnit"))) {
        sHql+=" and t.unitcode="+HQLUtils.buildHqlStringForSQL(unitcode);
        }
    }
    
    List<Addressbooks> addressooksList=baseDao.listObjects(sHql, filterMap);
      return addressooksList;
}
}

