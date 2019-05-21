package com.centit.sys.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.FUserunitId;

public interface SysUnitManager extends BaseEntityManager<FUnitinfo> {

    public List<FUnitinfo> getSubUnits(String superUnitID);

    public List<FUnitinfo> getAllSubUnits(String superUnitID);

    public List<FUserunit> getSysUsersByUnitId(String unitCode);

    public List<FUserinfo> getUnitUsers(String unitCode);

    public List<FUserinfo> getRelationUsers(String unitCode);

    public List<FUserunit> getSysUnitsByUserId(String userId) ;
    
    public void deleteUnitUser(FUserunitId id);

    public FUserunit findUnitUserById(FUserunitId id);

    public void saveUnitUser(FUserunit object);

    public String getNextKey();
    public String getUnitCode(String depno);

    public List<FUserunit> getSysUsersByRoleAndUnit(String roleType,
            String roleCode, String unitCode);
    /**
     * 获取全部机构JSON数据
     * @return String
     */
    public String getAllUnitsJSON();
    
    
    /**
     * 获取下属机构JSON数据
     * @param unitCode 当前机构编号
     * @return String
     */
    public String getAllSubUnitsJSON(String unitCode);
    /**
     * 获取下属机构JSON数据
     * @param unitCode
     * @return
     */
    public String getAllUnitsJSON(String unitCode);
    
    public String getAllUnitsJSONNoTree();
    /**
     * 通过部门名称获取部门信息
     * @param name
     * @return
     */
    public FUnitinfo getUnitByName(String name);
    /**
     * 通过部门编码获取部门信息
     * @param depno
     * @return
     */
    public FUnitinfo getUnitByDepno(String depno);
    /**
     * 更新部门信息
     * @param info
     */
    public void saveUpdateUnitInfo(FUnitinfo info);
    /**
     * 插入新部门信息
     * @param info
     */
    public void saveNewUnitInfo(FUnitinfo info);

    /**
     * 删除主部门信息
     * @param usercode
     */
    public void deleteUserUnit(String usercode);
}
