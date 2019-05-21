package com.centit.powerbase.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerbase.po.PowerOrgInfo;
import com.centit.powerruntime.po.VPowerOrgInfo;
import com.centit.sys.po.FUnitinfo;

public interface PowerOrgInfoManager extends BaseEntityManager<PowerOrgInfo> 
{
    /**
     * //根据部门查询，该部门关联的权力事项
     * @param object
     * @return
     */
    public List<VPowerOrgInfo> listOfReadyPowerOrgInfoList(PowerOrgInfo object);
    /** 根据部门、权力类型查询，该部门除了已经关联的权力事项之外的权力事项,
     * 即所有条件外剩余的权力事项
     * 
     * @param object
     * @return
     */
    public List<VPowerOrgInfo> listOfAllPowerOrgInfoList(PowerOrgInfo object);
    /**
     * 保存
     * 1、在保存之前先删除，该部门历史数据
     * 2、保存此次新关联数据
     * @param object
     * @param retValue
     * @param depNo
     */
    public void saveObjects(PowerOrgInfo object, String retValue,String depNo);
    /**
     * 根据权力事项查询
     * @param object
     * @return
     */
    public List<VPowerOrgInfo> listOfPowerOrgInfoList(PowerOrgInfo object);
    /**
     * 返回部门信息_未被设置的部门
     * @param object
     * @return
     */
    public List<FUnitinfo> listUnitList(String itemId);
    /**
     * 
     * @param object 存放关联基本信息 比如设置人员、时间等信息
     * @param retValue 选择的部门编号
     */
    public void saveObjects(PowerOrgInfo object, String retValue);
    /**
     * 查询关联基本信息
     * @param sParentUnit
     * @param itemType
     * @return
     */
    public List<VPowerOrgInfo> listPowerOrgInfoList(String sParentUnit,
            String itemType);

}
