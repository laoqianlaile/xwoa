package com.centit.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.dao.RoleInfoDao;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.dao.UserRoleDao;
import com.centit.sys.po.FOptWithPower;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FRolepower;
import com.centit.sys.po.FRolepowerId;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.FUserroleId;
import com.centit.sys.po.VOptTree;
import com.centit.sys.service.SysRoleManager;

public class SysRoleManagerImpl extends BaseEntityManagerImpl<FRoleinfo> implements
        SysRoleManager {
    private static final long serialVersionUID = 1L;
	private RoleInfoDao sysroledao;
	private UserRoleDao userroledao;
	private UserInfoDao userinfodao;
	public RoleInfoDao getSysroledao() {
        return sysroledao;
    }
	
    public void setSysroledao(RoleInfoDao sysroledao) {
        this.sysroledao = sysroledao;
    }

    public UserRoleDao getUserroledao() {
        return userroledao;
    }

    public void setUserroledao(UserRoleDao userroledao) {
        this.userroledao = userroledao;
    }

    public void setSysroleDao(RoleInfoDao roledao) {
		setBaseDao(roledao);
		this.sysroledao = roledao;
	}
	
	public List<FUserrole> getUserRolesByRoleCode(String roleCode){
	    return userroledao.getUserRolesByRoid(roleCode);
	}
	public FUserrole getUserRoleById(FUserroleId id){
	    return userroledao.getObjectById(id);
	}
	public FUserrole getValidUserrole(String usercode, String rolecode) {
	    return userroledao.getValidUserrole(usercode, rolecode);
	}
	public void saveUserrole(FUserrole userrole) {
       
        userroledao.saveObject(userrole);
    }
	 
	 
	  
	//各种角色代码获得该角色的操作权限 1对多
	public List<FRolepower> getRolePowers(String rolecode)		
	{
		return sysroledao.getRolePowers(rolecode);
	}
	
	//保存1对1的角色操作权限表
	public void saveRolePowers(List<FRolepower> rolePowers)
	{
		sysroledao.saveRolePowers(rolePowers);
	}
	//获取菜单TREE
    public List<VOptTree> getVOptTreeList()      
    {
        return sysroledao.getVOptTreeList();
    }
    
	//保存1对多的角色操作权限表
	public void saveRolePowers(String rolecode,String [] powerCodes)
	{
		List<FRolepower> rolePowers = new ArrayList<FRolepower>();
		if(powerCodes != null){
    		for(int i=0; i<powerCodes.length; i++){
    		    if(StringBaseOpt.isNvl(powerCodes[i]))
    		        continue;
    			FRolepower rp = new FRolepower(new FRolepowerId(rolecode, powerCodes[i].trim() ));
    			rolePowers.add(rp);
    		}
    		saveRolePowers(rolePowers);
		}
	}

	//
	public List<FOptWithPower> getOptWithPowerUnderUnit(String sUnitCode)
	{
		return sysroledao.getOptWithPowerUnderUnit(sUnitCode);
	}

	@Override
    public int deleteUserrole(FUserroleId id) {
        FUserrole userrole =getUserRoleById(id);
        return deleteUserrole(userrole);
        //userRoleDao.deleteObjectById(id);
        //return 1;
    }

    /**
     * 回收角色权限
     */
    public int deleteUserrole(FUserrole userrole) {
        if (userrole == null)
            return -1;
        
        Date today = new Date(System.currentTimeMillis());

        //if (userrole.getObtaindate().after(DatetimeOpt.truncateToDay(today))) {
        if (userrole.getObtaindate().after(today)) {
            userroledao.deleteObject(userrole);
            return 1;
        } else if(userrole.getSecededate() == null || userrole.getSecededate().after(today) ) {
            userrole.setSecededateToToday(); //.setSecededate( java.util.Date.valueOf( (new SimpleDateFormat("yyyy-MM-dd")).format(new Date() ) ));
            userroledao.saveObject(userrole);
            return 2;
        }
        userroledao.deleteObject(userrole);
        return 3;
    }

    @Override
    public List<FUserinfo> getAllUsers() {
        Map<String, Object> paramFilter = new HashMap<String, Object>();
        paramFilter.put("isValid","T");
        return userinfodao.listObjects(paramFilter);
    }

    public UserInfoDao getUserinfodao() {
        return userinfodao;
    }

    public void setUserinfodao(UserInfoDao userinfodao) {
        this.userinfodao = userinfodao;
    }

    @Override
    public FUserrole getValidUserrole2V(String usercode, String rolecode) {
        return userroledao.getValidUserrole2V(usercode, rolecode);
    }
   
}
