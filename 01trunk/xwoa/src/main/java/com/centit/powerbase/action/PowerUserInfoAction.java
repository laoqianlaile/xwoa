package com.centit.powerbase.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.PowerUserInfo;
import com.centit.powerbase.po.PowerUserInfoId;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.PowerUserInfoManager;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
	

public class PowerUserInfoAction  extends BaseEntityDwzAction<PowerUserInfo>  {
	private static final long serialVersionUID = 1L;
	private PowerUserInfoManager powerUserInfoMag;
	private VsuppowerinusingManager vsuppowerinusingManager;
	 public VsuppowerinusingManager getVsuppowerinusingManager() {
        return vsuppowerinusingManager;
    }

    public void setVsuppowerinusingManager(
            VsuppowerinusingManager vsuppowerinusingManager) {
        this.vsuppowerinusingManager = vsuppowerinusingManager;
    }

    private SysUserManager sysUserManager;
	 private SysUnitManager sysUnitManager;
	public void setPowerUserInfoManager(PowerUserInfoManager basemgr)
	{
		powerUserInfoMag = basemgr;
		this.setBaseEntityManager(powerUserInfoMag);
	}
	
	private List<FUserinfo> userList; //获取系统用户列表
	private List<FUnitinfo> unitList; //获取部门列表。查询页面下拉框
	private List<VPowerUserInfo> vPowerUserInfoList;
	private List<Vsuppowerinusing> vsuppowerinusingList;
	private List<PowerUserInfo> powerUserInfoList;
	
	
	private FUserinfo  userinfo;
	private FUserunit userunit;//人员信息
	private Vsuppowerinusing suppowerinusing;//权力信息
    private String unitsJson;
    private String resetUsers;//配置人员
    private String resetItemIds;//配置权力
    private String itemIds;//已选择权力编号
    private String usercodes;//已选择人员编号
    
    
    

 
    @SuppressWarnings("unchecked")
    public String userCheckList(){
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            String itemId =(String)request.getParameter("itemId");
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            //获取系统用户列表 除去 已被事权人员关联配置的人员 
            userList = powerUserInfoMag.listUserCodes(itemId,filterMap, pageDesc);
            
             //查询条件部门下拉框  法1
             unitsJson =sysUnitManager.getAllUnitsJSON();
             //查询条件部门下拉框  法2
             FUserDetail user = ((FUserDetail) getLoginUser());
             FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
             String sParentUnit = dept.getUnitcode();
             unitList =sysUnitManager.getAllSubUnits(sParentUnit);
             //添加人员主机构
             if (userList != null && userList.size() > 0) {
                 for (int i = 0; i < userList.size(); i++) {
                     String primaryUnit=CodeRepositoryUtil.getPrimaryUnit(userList.get(i).getUsercode());
                     if(StringUtils.isNotBlank(primaryUnit)){
                         userList.get(i).setPrimaryUnit(primaryUnit) ;
                     }                       
                 }
             }
             
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            this.pageDesc = pageDesc;
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    @SuppressWarnings("unchecked")
    public String list() {
	        try {
	            Map<Object, Object> paramMap = request.getParameterMap();
	            resetPageParam(paramMap);

	            Map<String, Object> filterMap = convertSearchColumn(paramMap);
	            PageDesc pageDesc = makePageDesc();
	            //获取系统用户列表
	             userList = sysUserManager.listObjects(filterMap, pageDesc);
	            
	             //查询条件部门下拉框  法1
	             unitsJson =sysUnitManager.getAllUnitsJSON();
	             //查询条件部门下拉框  法2
	             FUserDetail user = ((FUserDetail) getLoginUser());
	             FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
	             String sParentUnit = dept.getUnitcode();
	             unitList =sysUnitManager.getAllSubUnits(sParentUnit);
	             //添加人员主机构
	             if (userList != null && userList.size() > 0) {
	                 for (int i = 0; i < userList.size(); i++) {
	                     String primaryUnit=CodeRepositoryUtil.getPrimaryUnit(userList.get(i).getUsercode());
	                     if(StringUtils.isNotBlank(primaryUnit)){
	                         userList.get(i).setPrimaryUnit(primaryUnit) ;
	                     }	                     
	                 }
	             }
	             
	            totalRows = pageDesc.getTotalRows();
	            setbackSearchColumn(filterMap);
	            this.pageDesc = pageDesc;
	            return LIST;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ERROR;
	        }
	    }
    
    /**
     * 编辑，最上面显示人员相关信息，下面显示已经关联的可登记的事权列表；已经关联的事权，可以删除（只有删除功能）；
     */
    public String powerList() {
        userunit=sysUserManager.getUserPrimaryUnit(object.getUsercode());
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("usercode", object.getUsercode());
        filterMap.put("itemType","SQ");
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        
        vPowerUserInfoList=powerUserInfoMag.getVList(filterMap, pageDesc);
       
        if (vPowerUserInfoList != null && vPowerUserInfoList.size() > 0) {
            itemIds="";
            for (int i = 0; i < vPowerUserInfoList.size(); i++) {
                String value = vPowerUserInfoList.get(i).getItemId()+ ",";
                itemIds += value;
            }
        }
        
        totalRows = pageDesc.getTotalRows();
      
        return "powerList";
    }
    
    /**
     * 编辑，最上面权力相关信息，下面显示已经关联的人员列表；已经关联的人员，可以删除（只有删除功能）；
     */
    public String userList() {
        //根据ItemId获取在用权力信息
        suppowerinusing=vsuppowerinusingManager.findB_PowerByItemId(object.getItemId());
        
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("itemId", object.getItemId());
        filterMap.put("itemType","SQ");
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        powerUserInfoList=powerUserInfoMag.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        if (powerUserInfoList != null && powerUserInfoList.size() > 0) {
            usercodes="";
            for (int i = 0; i < powerUserInfoList.size(); i++) {
                String value = powerUserInfoList.get(i).getUsercode()+ ",";
                usercodes += value;
            }
        }
        return "userList";
    }
    
    
    public String userSelectList(){
        //获取系统用户列表
       /* Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        PageDesc pageDesc = makePageDesc();
        userList = sysUserManager.listObjects(filterMap, pageDesc);*/
        
        userCheckList();
        return "userSelectList";
    }
    //权力库关联事权
    @SuppressWarnings("unchecked")
    public String powerSelectList(){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
       
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        filterMap.put("itemType","SQ");
        
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        
        itemIds =(String)request.getParameter("itemIds");
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        vsuppowerinusingList=vsuppowerinusingManager.listUserlist(itemIds,filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "powerSelectList";
    }

    
    /**
     * 保存关联--权力
     */
    public void infoSaveByUsercode(){
//        String items = (String)request.getParameter("items");
        String usercode = (String)request.getParameter("usercode");
        //判断关联关系是否已存在，完全删除再全部insert
        //powerUserInfoMag.delectByUsercode(usercode);
        if(StringUtils.isNotBlank(resetItemIds)){
            String ar[]=resetItemIds.split(",");
            for(String a :ar){
                saveObjectBanInfo(usercode,a);
            }
        }
        //原页面刷新
        powerList();
    }
    
    /**
     * 保存关联-用户
     */
    public void infoSaveByItemId(){
        String itemId = (String)request.getParameter("itemId");
        //判断关联关系是否已存在，完全删除再全部insert
        //powerUserInfoMag.delectByItemId(itemId);
        if(StringUtils.isNotBlank(resetUsers)){
            String ar[]=resetUsers.split(",");
            for(String a :ar){
                saveObjectBanInfo(a,itemId);
            }
        }
        //原页面刷新
        userList();
    }
    public void saveObjectBanInfo(String usercode,String itemId){
        PowerUserInfo info=new PowerUserInfo();
        info.setItemId(itemId);
        info.setUsercode(usercode);
        info.setSetoperator(getLoginUserCode());
        info.setSettime(new Date());
        powerUserInfoMag.saveObject(info);
       }
    /**
     * 获取当前人员usercode
     * @return
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }
    /**
     * 依据usercode单条删除
     * @return
     */
    public String deleteUser() {
        powerUserInfoMag.deleteObjectById(new PowerUserInfoId(object.getUsercode(), object.getItemId()));
        return userList() ;
    }
   
    /**
     * 依据itemId单条删除
     * @return
     */
    
    public String deletePower() {
      powerUserInfoMag.deleteObjectById(new PowerUserInfoId(object.getUsercode(), object.getItemId()));
      return powerList() ;
  }
    /*************************************** 以下为getter()、setter() ****************************************/
    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public List<FUserinfo> getUserList() {
        return userList;
    }

    public void setUserList(List<FUserinfo> userList) {
        this.userList = userList;
    }
    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }
    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }










    public List<FUnitinfo> getUnitList() {
        return unitList;
    }










    public void setUnitList(List<FUnitinfo> unitList) {
        this.unitList = unitList;
    }

    public FUserinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(FUserinfo userinfo) {
        this.userinfo = userinfo;
    }

    public FUserunit getUserunit() {
        return userunit;
    }

    public void setUserunit(FUserunit userunit) {
        this.userunit = userunit;
    }

    public List<VPowerUserInfo> getVPowerUserInfoList() {
        return vPowerUserInfoList;
    }

    public void setVPowerUserInfoList(List<VPowerUserInfo> vPowerUserInfoList) {
        this.vPowerUserInfoList = vPowerUserInfoList;
    }

    public List<PowerUserInfo> getPowerUserInfoList() {
        return powerUserInfoList;
    }

    public void setPowerUserInfoList(List<PowerUserInfo> powerUserInfoList) {
        this.powerUserInfoList = powerUserInfoList;
    }

    public Vsuppowerinusing getSuppowerinusing() {
        return suppowerinusing;
    }

    public void setSuppowerinusing(Vsuppowerinusing suppowerinusing) {
        this.suppowerinusing = suppowerinusing;
    }

    public List<Vsuppowerinusing> getVsuppowerinusingList() {
        return vsuppowerinusingList;
    }

    public void setVsuppowerinusingList(List<Vsuppowerinusing> vsuppowerinusingList) {
        this.vsuppowerinusingList = vsuppowerinusingList;
    }

    public String getResetUsers() {
        return resetUsers;
    }

    public void setResetUsers(String resetUsers) {
        this.resetUsers = resetUsers;
    }

    public String getResetItemIds() {
        return resetItemIds;
    }

    public void setResetItemIds(String resetItemIds) {
        this.resetItemIds = resetItemIds;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    public String getUsercodes() {
        return usercodes;
    }

    public void setUsercodes(String usercodes) {
        this.usercodes = usercodes;
    }

 


   
}
