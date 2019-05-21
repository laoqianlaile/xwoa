package com.centit.app.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.app.po.AddressBookFs;

public interface AddressBookFsManager extends BaseEntityManager<AddressBookFs> 
{

    public List<Object> listBodyType();
    public List<Object> listUnits();
    public AddressBookFs getObjectByCode(String bodycode,String bodytype);
    public Long getNextLongSequence(String str);
    //根据部分用户名模糊查询得到的对象是AddressBook
    public AddressBookFs getObjectByUsername(String username,String bodytype);
    //根据确切的手机号码得到的是FUserinfo或者FUnitinfo对象
    public Object getObjectByPhone(String phone,String bodytype);
    //根据部分手机号码模糊查询出的是address对象
    public Object getObjectByPPhone(String phone,String bodytype);
}
