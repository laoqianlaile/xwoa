package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.Addressbooks;

public interface AddressbooksManager extends BaseEntityManager<Addressbooks> 
{
    /**
     *根据通讯主体ID获得该条记录的共享人
     * @param addrbookid
     * @return
     */
    List<String> listUsercodesByAddressbookId(String addrbookid);
    
    /**
     * 获取通讯录列表
     */
    List<Addressbooks> listObjects(Map<String, Object> filterMap, PageDesc pageDesc,String usercode);
    /**
     * 获取下一个编号
     * @return
     */
    String genNextAddressbookId();
    
    void saveObject(Addressbooks object);
/**
 * 根据部门编号,通讯录类型获取机构信息
 * @param unitName
 * @param type
 * @return
 */
    Addressbooks getObjectByCode(String unitcode, String type);
   /***
    *   根据usercode,通讯录类型获取用户信息
    * @param usercode
    * @param type
    * @return
    */
    Addressbooks getUserByCode(String usercode, String type);
    /**
     * 获取所有公共通讯录记录
     * @return
     */
   List<Addressbooks> addressbooksCList();
    
   /**
    * 获取所有公共通讯录记录unitcode
    * @return
    */
  List<Addressbooks> addressbooksCList(Map<String, Object> filterMap, PageDesc pageDesc,String unitcode);
  List<Addressbooks> addressbooksCList(Map<String, Object> filterMap,String unitcode);

}
