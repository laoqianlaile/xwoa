package com.centit.sys.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.FUserroleId;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.FUserunitId;
import com.centit.sys.po.Usersetting;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;

public interface SysUserManager extends BaseEntityManager<FUserinfo> {


    public Collection<GrantedAuthority> loadUserAuthorities(String username);


    public FUserDetail loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException;

    /**
     * 获取用户JSON数据
     *
     * @return
     */
    public String getJSONUsers();

    public void resetPwd(String usid);

    public FUserrole getFUserroleByID(FUserroleId id);

    public List<FRoleinfo> getSysRolesByUsid(String usercode);

    public List<FUserrole> getUserRoles(String usercode, String rolePrefix);

    public List<FUserrole> getAllUserRoles(String usercode, String rolePrefix);

    public FUserrole getValidUserrole(String usercode, String rolecode);

    public int deleteUserrole(String usercode, String rolecode);

    public int deleteUserrole(FUserroleId id);

    public int deleteUserrole(FUserrole userrole);

    public void saveUserrole(FUserrole userrole);

    
    public String encodePassword(String password, String usercode);

    public void setNewPassword(String userID, String oldPassword,
                               String newPassword);

    public List<FUserunit> getSysUnitsByUserId(String userCode);

    public FUserunit getUserPrimaryUnit(String userId);

    public FUserunit findUserUnitById(FUserunitId id);
    //public void saveUserWithPrimaryUnit(FUserinfo userinfo, FUserunit unitinfo);

    public void saveUserUnit(FUserinfo userinfo, FUserunit userunit);

    public void saveUserUnit(FUserunit userunit);

    void saveUserUnit(FUserunit object, FUserunit oldObject);

    public void deleteUserUnit(FUserunitId id);

    public String getNextUserCode(char cType);

    public Usersetting getUserSetting(String usercode);

    public FUserunit getUserunitByUserid(String userid);

    public void saveUserUnitFromXc(FUserunit object);

    // 
    public List<FUserinfo> listUnderUnit(Map<String, Object> filterMap, PageDesc pageDesc);

    public List<FUserinfo> listUnderUnit(Map<String, Object> filterMap);

    public List<FUserinfo> getUserUnderUnit(String unitcode);

    public List<VUserUnits> getUnitUsers(String unitCode);

    //根据系统登录人员得到人员所属部门
    public VUserUnits getUnitByUserCode(VUserUnits o);

    void saveBatchUserRole(String rolecode, List<String> usercode);

    void disabledUserrole(String usercode, String rolecode);

    boolean userHaveRole(String usercode);

    /**
     * 复制人员岗位权限
     *
     * @param sourceUsercode 被拷贝的用户代码
     * @param destUsercode   用户代码
     * @param action，操作，     "0"  覆盖
     *                       "1"  合并
     *                       "2"  忽略
     */
    void saveCp(String sourceUsercode, String destUsercode, String action);

    /**
     * 交换人员岗位权限
     *
     * @param sourceUsercode 用户代码
     * @param destUsercode   用户代码
     */
    void saveChange(String sourceUsercode, String destUsercode);
    


    public  List<FUserinfo> getUserbByPhone(String telephone);


}
