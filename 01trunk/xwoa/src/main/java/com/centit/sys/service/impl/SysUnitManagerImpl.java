package com.centit.sys.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.dao.UserUnitDao;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.FUserunitId;
import com.centit.sys.service.SysUnitManager;

public class SysUnitManagerImpl extends BaseEntityManagerImpl<FUnitinfo>
        implements SysUnitManager {
    private static final long serialVersionUID = 1L;
    private UnitInfoDao sysunitdao;
    private UserUnitDao unituserDao;

    public void setSysunitdao(UnitInfoDao unitdao) {
        setBaseDao(unitdao);
        this.sysunitdao = unitdao;
    }

    public void setUnituserDao(UserUnitDao userunitdao) {
        this.unituserDao = userunitdao;
    }

    public List<FUnitinfo> getSubUnits(String superUnitID) {
        return sysunitdao.getSubUnits(superUnitID);

    }

    public List<FUnitinfo> getAllSubUnits(String superUnitID) {
        return sysunitdao.getAllSubUnits(superUnitID);
    }

    /***
     * 查找对象，如果没有新建一个空对象，并附一个默认的编码
     */
    public FUnitinfo getObject(FUnitinfo object) {
        FUnitinfo newObj = sysunitdao.getObjectById(object.getUnitcode());
        if (newObj == null) {
            newObj = object;
            newObj.setUnitcode(sysunitdao.getNextKey());
            newObj.setIsvalid("T");
        }
        return newObj;
    }

    public List<FUserunit> getSysUsersByUnitId(String unitCode) {
        return sysunitdao.getSysUsersByUnitId(unitCode);
    }

    public List<FUserinfo> getUnitUsers(String unitCode) {
        return sysunitdao.getUnitUsers(unitCode);
    }

    public List<FUserunit> getSysUnitsByUserId(String userId) {
        return sysunitdao.getSysUnitsByUserId(userId);
    }   
    
    public List<FUserinfo> getRelationUsers(String unitCode) {
        return sysunitdao.getRelationUsers(unitCode);
    }

    public FUserunit findUnitUserById(FUserunitId id) {
        return unituserDao.getObjectById(id);
    }

    public void saveUnitUser(FUserunit object) {
        unituserDao.saveObject(object);

    }
    public String getUnitCode(String depno){
        return sysunitdao.getUnitCode(depno);
    }
    public String getNextKey() {
        return sysunitdao.getNextKey();
    }

    public void deleteUnitUser(FUserunitId id) {
        unituserDao.deleteObjectById(id);

    }
    
    public void deleteUserUnit(String usercode) {
        unituserDao.deleteUserUnit(usercode);

    }

    public List<FUserunit> getSysUsersByRoleAndUnit(String roleType,
            String roleCode, String unitCode) {
        return unituserDao.getSysUsersByRoleAndUnit(roleType, roleCode,
                unitCode);
    }

    /**
     * 获取全部机构JSON数据
     * 
     * @return
     */
    public String getAllUnitsJSON() {
        List<FUnitinfo> unitList = sysunitdao.listObjects();
        return unitList2JSON(unitList);
    }

    /**
     * 根据当前机构编号，获取下级所有机构
     * 
     * @param ParentID
     * @return
     */
    public String getAllSubUnitsJSON(String unitCode) {
        List<FUnitinfo> unitList = sysunitdao.getAllSubUnits(unitCode);
        return unitList2JSON(unitList);
    }

    public String getAllUnitsJSON(String unitCode) {
        List<FUnitinfo> unitList = sysunitdao.getAllSubUnits(unitCode);
        return unit2JSON(unitList);
    }
    
    public String getAllUnitsJSONNoTree() {
        List<FUnitinfo> unitList = sysunitdao.listObjects();
        return unit2JSON(unitList);
    }
    /**
     * 机构列表转换为Json对象
     * 
     * @param unitList
     * @return
     */
    private String unitList2JSON(List<FUnitinfo> unitList) {

        if (unitList == null) {
            return "";
        }

        JSONArray jsonArr = new JSONArray();
        for (FUnitinfo unitInfo : unitList) {

            // 如果机构名称为空，则不放入JSON对象
            if (unitInfo.getUnitname() == null
                    || unitInfo.getUnitname().equals("")) {
                continue;
            }

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("MID", unitInfo.getUnitcode());//菜单编号
            jsonObj.put("ParentID", unitInfo.getParentunit());//父级菜单编号
            jsonObj.put("MText", unitInfo.getUnitname());//菜单名称
            jsonObj.put("depno", unitInfo.getDepno()); // 部门编号扩展
            jsonArr.add(jsonObj);
        }
        return jsonArr.toString();
    }
    
    private String unit2JSON(List<FUnitinfo> unitList) {

        if (unitList == null) {
            return "";
        }

        JSONArray jsonArr = new JSONArray();
        for (FUnitinfo unitInfo : unitList) {

            // 如果机构名称为空，则不放入JSON对象
            if (unitInfo.getUnitname() == null
                    || unitInfo.getUnitname().equals("")) {
                continue;
            }

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("nodeID", unitInfo.getUnitcode());//菜单编号
            
            jsonObj.put("name", unitInfo.getUnitname());//菜单名称
           
            jsonArr.add(jsonObj);
        }
        if(jsonArr!=null && jsonArr.size()>0){
            jsonArr.remove(0);
        }
        return jsonArr.toString();
    }

    /**
     * 通过部门名称获取部门信息
     * @param name
     * @return
     */
    public FUnitinfo getUnitByName(String name) {
        return sysunitdao.getUnitByName(name);
    }
    /**
     * 通过部门编码获取部门信息
     * @param depno
     * @return
     */
    public FUnitinfo getUnitByDepno(String depno){
        return sysunitdao.getUnitByDepno(depno);
    }
    /**
     * 插入新部门信息
     * @param info
     */
    public void saveNewUnitInfo(FUnitinfo info){
        sysunitdao.saveNewObject(info);
    }
    /**
     * 更新部门信息
     * @param info
     */
    public void saveUpdateUnitInfo(FUnitinfo info){
        sysunitdao.saveObject(info);
    }
}
