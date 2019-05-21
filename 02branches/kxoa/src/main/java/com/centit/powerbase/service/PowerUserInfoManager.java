package com.centit.powerbase.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.PowerUserInfo;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.sys.po.FUserinfo;

public interface PowerUserInfoManager extends BaseEntityManager<PowerUserInfo> 
{
   //获取VPowerUserInfo列表
    public  List<VPowerUserInfo> getVList(Map<String, Object> filterMap,
            PageDesc pageDesc);
    //根据usercode删除关联关系
    public  void delectByUsercode(String usercode);
    //根据itemid删除关联关系
    public  void delectByItemId(String itemId);
    //查询出已被事权人员关联配置的人员
    public List<FUserinfo> listUserCodes(String itemId,
            Map<String, Object> filterMap, PageDesc pageDesc);
 
    
}
