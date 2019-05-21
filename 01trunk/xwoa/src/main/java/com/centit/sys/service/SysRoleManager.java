package com.centit.sys.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.FOptWithPower;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FRolepower;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.FUserroleId;
import com.centit.sys.po.VOptTree;

public interface SysRoleManager extends BaseEntityManager<FRoleinfo> {

	public List<FRolepower> getRolePowers(String rolecode);     //角色操作权限
	public void saveRolePowers(List<FRolepower> rolePowers);	//1对1的操作权限保存	
	public void saveRolePowers(String rolecode,String [] powerCodes);	//1对多的操作权限保存
	public List<FOptWithPower> getOptWithPowerUnderUnit(String sUnitCode);	//各种角色代码获得角色说拥有的业务
	public List<VOptTree> getVOptTreeList();//获取菜单TREE
    public List<FUserrole> getUserRolesByRoleCode(String rolecode);
    public FUserrole getUserRoleById(FUserroleId id);
    public FUserrole getValidUserrole(String usercode, String rolecode);
    public void saveUserrole(FUserrole userrole);
    public int deleteUserrole(FUserroleId id);

    public int deleteUserrole(FUserrole userrole);
    public List<FUserinfo> getAllUsers();
    
    /**
     * 从人员角色权限视图中获取当前人员权限
     * @param usercode
     * @param rolecode
     * @return
     */
    public FUserrole getValidUserrole2V(String usercode, String rolecode);
}
