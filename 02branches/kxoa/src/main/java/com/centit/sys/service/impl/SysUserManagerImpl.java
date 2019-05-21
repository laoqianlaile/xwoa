package com.centit.sys.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.service.ObjectException;
import com.centit.core.utils.PageDesc;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.dao.OptInfoDao;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.dao.UserRoleDao;
import com.centit.sys.dao.UserSettingDao;
import com.centit.sys.dao.UserUnitDao;
import com.centit.sys.dao.VUserUnitsDao;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.FUserroleId;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.FUserunitId;
import com.centit.sys.po.FVUseroptlist;
import com.centit.sys.po.Usersetting;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUserManager;


public class SysUserManagerImpl extends BaseEntityManagerImpl<FUserinfo> implements
        SysUserManager, UserDetailsService
        , AuthenticationUserDetailsService {
    private static final long serialVersionUID = 1L;
    // 加密
    Md5PasswordEncoder passwordEncoder;
    
    public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String password, String usercode) {
        return passwordEncoder.encodePassword(password, usercode);
    }

    private String getDefaultPassword(String usercode) {
        String defaultPassword = "000000";
        
        //数据字典设置默认重置密码
        FDatadictionary dictionary = CodeRepositoryUtil.getDataPiece("SYSPARAM", "DEFAULT_PWD");
        if(null != dictionary && StringUtils.isNotBlank(dictionary.getValue())) {
            defaultPassword = dictionary.getValue();
        }
        
        return encodePassword(defaultPassword, usercode);
    }

    private UserInfoDao sysuserdao;

    public void setSysuserDao(UserInfoDao userdao) {
        setBaseDao(userdao);
        this.sysuserdao = userdao;
    }

    private UserUnitDao unituserDao;

    public void setUnituserDao(UserUnitDao userunitdao) {
        this.unituserDao = userunitdao;
    }

    private UnitInfoDao sysunitdao;

    private UserRoleDao userRoleDao;
    private UserSettingDao userSettingdao;

    public void setSysunitDao(UnitInfoDao sysunitdao) {
        this.sysunitdao = sysunitdao;
    }

    public void setUserSettingDao(UserSettingDao usersettingdao) {
        this.userSettingdao = usersettingdao;
    }

    public void setUserRoleDao(UserRoleDao sysusrodao) {
        this.userRoleDao = sysusrodao;
    }

    private OptInfoDao functionDao;

    public void setFunctionDao(OptInfoDao dao) {
        this.functionDao = dao;
    }

    public List<FRoleinfo> getSysRolesByUsid(String usercode) {
        List<FRoleinfo> roles = userRoleDao.getSysRolesByUsid(usercode);
        return roles;
    }

    public List<FUserrole> getUserRoles(String usercode, String rolePrefix) {
        return userRoleDao.getUserRolesByUsid(usercode, rolePrefix);
    }

    public List<FUserrole> getAllUserRoles(String usercode, String rolePrefix) {
        return userRoleDao.getAllUserRolesByUsid(usercode, rolePrefix);
    }

    public Collection<GrantedAuthority> loadUserAuthorities(String loginname)
            throws UsernameNotFoundException {
        FUserDetail sysuser = (FUserDetail) sysuserdao.loadUserByLoginname(loginname);
        sysuser.setSysusrodao(userRoleDao);
        List<FRoleinfo> roles = getSysRolesByUsid(sysuser.getUsercode());
        sysuser.setAuthoritiesByRoles(roles);
        return sysuser.getAuthorities();
    }

    public FUserDetail loadUserByUsername(String loginname)
            throws UsernameNotFoundException, DataAccessException {
       
        FUserDetail sysuser = (FUserDetail) sysuserdao.loadUserByLoginname(loginname);
        sysuser.setSysusrodao(userRoleDao);
        List<FRoleinfo> roles = getSysRolesByUsid(sysuser.getUsercode());
        List<FUserunit> usun = sysunitdao.getSysUnitsByUserId(sysuser
                .getUsercode());
        sysuser.setUserUnits(usun);
        sysuser.setUserSetting(getUserSetting(sysuser.getUsercode()));
        sysuser.setUserFuncs(functionDao.getMenuFuncByUserID(sysuser.getUsercode()));

        sysuser.setAuthoritiesByRoles(roles);
        
        List<FVUseroptlist> uoptlist = functionDao.getAllOptMethodByUser(sysuser.getUsercode()); 
        Map<String,String> userOptList = new HashMap<String,String>();  
        if(uoptlist!=null){
            for(FVUseroptlist opt : uoptlist)
                userOptList.put(opt.getOptid() + "-"+opt.getOptmethod() , "T");
        }
//        ServletActionContext.getRequest().getSession().setAttribute("userOptList", userOptList);
        sysuser.setUserOptList(userOptList);
        
        return sysuser;
    }

    public UserDetails loadUserDetails(Authentication token)
            throws UsernameNotFoundException {
        FUserDetail ud = loadUserByUsername(token.getName());
        //ud.getUsercode()
        return ud;
    }

    public void resetPwd(String usid) {
        FUserinfo user = sysuserdao.getObjectById(usid);
        ;
        user.setUserpin(getDefaultPassword(user.getUsercode()));
        user.setIsDefaultPwd("T");//默认密码
        sysuserdao.saveObject(user);
    }

    public void setNewPassword(String userID, String oldPassword,
                               String newPassword) {
        FUserinfo user = sysuserdao.getObjectById(userID);
        if (!user.getUserpin().equals(
                encodePassword(oldPassword, user.getUsercode())))
            throw new ObjectException("旧密码不正确");
        user.setUserpin(encodePassword(newPassword, user.getUsercode()));
        user.setIsDefaultPwd("F");
        sysuserdao.saveObject(user);
    }

    public void saveObject(FUserinfo sysuser) {
        boolean hasExist = sysuserdao.checkIfUserExists(sysuser);// 查该登录名是不是已经被其他用户使

        if (StringUtils.isBlank(sysuser.getUsercode())) {// 新添
            //sysuser.setUsercode( getNextUserCode('u'));
            sysuser.setIsvalid("T");
            sysuser.setUserpin(getDefaultPassword(sysuser.getUsercode()));
        }
        if (!hasExist && StringUtils.isBlank(sysuser.getUserpin()))
            sysuser.setUserpin(getDefaultPassword(sysuser.getUsercode()));

        sysuserdao.saveObject(sysuser);
    }

    public FUserrole getFUserroleByID(FUserroleId id) {
        return userRoleDao.getObjectById(id);
    }

    public List<FUserunit> getSysUnitsByUserId(String userCode) {
        return sysunitdao.getSysUnitsByUserId(userCode);
    }

    public FUserunit getUserPrimaryUnit(String userId) {
        return sysunitdao.getUserPrimaryUnit(userId);
    }

    public FUserunit findUserUnitById(FUserunitId id) {
        return unituserDao.getObjectById(id);
    }

    public void saveUserUnit(FUserunit userunit) {
        //当前用户机构模式
        FDatadictionary agencyMode = getAgencyMode();
        if(agencyMode==null){
            agencyMode=new FDatadictionary();
        }
        //一对多模式,更换主机构   //多对多，删除当前主机构
        if ("O".equalsIgnoreCase(agencyMode.getDatavalue()) || "D".equalsIgnoreCase(agencyMode.getExtracode())) {
            FUserunit pUserUnit = sysunitdao.getUserPrimaryUnit(userunit.getUsercode());
            if (null != pUserUnit) {
                deleteUserUnit(pUserUnit.getId());
            }
        }
        FUserunitId id = new FUserunitId();
        id.setUsercode(userunit.getUsercode());
        id.setUnitcode(userunit.getUnitcode());
        id.setUserrank(userunit.getUserrank());
        id.setUserstation(userunit.getUserstation());
        FUserunit dbobject = findUserUnitById(id);

        if (dbobject != null) {
            dbobject.copyNotNullProperty(userunit);
            userunit = dbobject;
        }

        if (userunit.getIsprimary() != null && "T".equals(userunit.getIsprimary())) {
            sysuserdao.deleteOtherPrimaryUnit(userunit);
        }

       // userunit.setIsprimary("T");//modify by hx bug：会默认都是主机构
        
        
        
        unituserDao.saveObject(userunit);
        


    }
    public void saveUserUnit(FUserinfo userinfo, FUserunit userunit) {
        userinfo.setPrimaryUnit(userunit.getUnitcode());
        sysuserdao.saveObject(userinfo);
        
        
        saveUserUnit(userunit);
    }

    @Override
    public void saveUserUnit(FUserunit object, FUserunit oldObject) {
        unituserDao.deleteObject(oldObject);
        if (object.getIsprimary() != null && "T".equals(object.getIsprimary())) {
            sysuserdao.deleteOtherPrimaryUnit(object);
        }
        unituserDao.saveObject(object);
    }

    public void saveUserUnitFromXc(FUserunit object) {
        unituserDao.saveObject(object);
    }

/*  
    public void saveUserWithPrimaryUnit(FUserinfo userinfo, FUserunit unitinfo) {
        sysuserdao.saveObject(userinfo);
        sysuserdao.deleteOtherPrimaryUnit(unitinfo);
        unituserDao.saveObject(unitinfo);
    }
*/

    public void deleteUserUnit(FUserunitId id) {
        unituserDao.deleteObjectById(id);
    }

    public FUserrole getValidUserrole(String usercode, String rolecode) {
        return userRoleDao.getValidUserrole(usercode, rolecode);
    }

    public int deleteUserrole(String usercode, String rolecode)  //这个方法不需要了
    {
        FUserrole userrole = getValidUserrole(usercode, rolecode);
        return deleteUserrole(userrole);
    }
    
    
    @Override
    public int deleteUserrole(FUserroleId id) {
        FUserrole userrole =getFUserroleByID(id);
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
            userRoleDao.deleteObject(userrole);
            return 1;
        } else if(userrole.getSecededate() == null || userrole.getSecededate().after(today) ) {
            userrole.setSecededateToToday(); //.setSecededate( java.util.Date.valueOf( (new SimpleDateFormat("yyyy-MM-dd")).format(new Date() ) ));
            userRoleDao.saveObject(userrole);
            return 2;
        }
        //userRoleDao.deleteObject(userrole);
        return 3;
    }

    public void saveUserrole(FUserrole userrole) {
        /*FUserrole desobj = getValidUserrole(userrole.getUsercode(), userrole.getRolecode());
        if (desobj != null) {
            if (desobj.getObtaindate().after(new Date(System.currentTimeMillis()))) {
                desobj.setObtaindate(userrole.getObtaindate());
            } //else if(desobj.getObtaindate().before(userrole.getObtaindate() ) )
            desobj.setChangedesc(userrole.getChangedesc());
            desobj.setSecededate(userrole.getSecededate());
        } else
            desobj = userrole;
        userRoleDao.saveObject(desobj);*/
        userRoleDao.saveObject(userrole);
    }

    public String getNextUserCode(char cType) {
        String sKey = "00000000000" + sysuserdao.getNextValueOfSequence("S_USERCODE");
        return cType + sKey.substring(sKey.length() - 7);
    }

    public Usersetting getUserSetting(String usercode) {
        Usersetting us = userSettingdao.getObjectById(usercode);
        if (us == null)
            us = userSettingdao.getObjectById("default");
        return us;
    }

    public FUserunit getUserunitByUserid(String userid) {
        return unituserDao.getUserunitByUserid(userid);
    }

    /**
     * 获取用户JSON数据
     *
     * @return
     */
    public String getJSONUsers() {
        List<FUserinfo> userList = sysuserdao.listObjects();
        JSONArray jsonArr = new JSONArray();
        for (FUserinfo userInfo : userList) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("usercode", userInfo.getUsercode());
            jsonObj.put("username", userInfo.getUsername());
            jsonArr.add(jsonObj);
        }
        return jsonArr.toString();
    }

    /* (non-Javadoc)
     * @see com.centit.sys.service.SysUserManager#listUnderUnit(java.util.Map, com.centit.core.utils.PageDesc)
     */
    @Override
    public List<FUserinfo> listUnderUnit(Map<String, Object> filterMap, PageDesc pageDesc) {
        return sysuserdao.listUnderUnit(filterMap, pageDesc);
    }


    @Override
    public List<FUserinfo> listUnderUnit(Map<String, Object> filterMap) {
        return sysuserdao.listUnderUnit(filterMap);
    }

    /* (non-Javadoc)
         * @see com.centit.sys.service.SysUserManager#getUserUnderUnit(java.lang.String)
         */
    @Override
    public List<FUserinfo> getUserUnderUnit(String unitcode) {
        return sysuserdao.getUserUnderUnit(unitcode);
    }

    @Override
    public void saveBatchUserRole(String rolecode, List<String> ucs) {
        List<String> userCodes = new ArrayList<String>(ucs);
        
        Date now = new Date();
     
        //新添加起始时间为当前，原先数据不进行修改
        Map<String, Object> paramFilter = new HashMap<String, Object>();
        paramFilter.put("rolecode", rolecode);
        List<FUserrole> userroles = userRoleDao.listObjects(paramFilter);
        if (CollectionUtils.isEmpty(userroles)) {
            for (String usercode : userCodes) {
                FUserrole userrole = new FUserrole(new FUserroleId(usercode, rolecode, new Date()));

                userRoleDao.saveObject(userrole);
            }
        }else {
            for (FUserrole userrole : userroles) {
                if (!userCodes.contains(userrole.getId().getUsercode())) {
                    //已经不存在于当前角色中
                    if (userrole.getObtaindate().after(DatetimeOpt.truncateToDay(now))) {
                        userRoleDao.deleteObject(userrole);
                    } else if(userrole.getSecededate() == null || userrole.getSecededate().after(now) ) {
                        userrole.setSecededateToToday();
                        userRoleDao.saveObject(userrole);
                    }
                }else if(null == userrole.getSecededate()  || userrole.getSecededate().after(now)) {
                    //存在于当前角色中，不再进行保存
                    userCodes.remove(userrole.getId().getUsercode());
                }
            }


            for (String usercode : userCodes) {
                FUserrole userrole = new FUserrole(new FUserroleId(usercode, rolecode, now));

                userRoleDao.saveObject(userrole);
            }
        }

    }
    
    private VUserUnitsDao vUserUnitsDao;
    
    public void setvUserUnitsDao(VUserUnitsDao vUserUnitsDao) {
        this.vUserUnitsDao = vUserUnitsDao;
    }

    public List<VUserUnits> getUnitUsers(String unitCode){
        
        List<VUserUnits> unitUsers = vUserUnitsDao.getUnitUsers(unitCode);
        
        return unitUsers;
    }
    @Override
    public VUserUnits getUnitByUserCode(VUserUnits o) {
        return vUserUnitsDao.getObject(o);
    }

    /**
     * 用户，机构，一对多，多对多模式
     *
     * @return
     */
    public static FDatadictionary getAgencyMode() {
        List<FDatadictionary> sysparam = CodeRepositoryUtil.getDictionaryIgnoreD("SYSPARAM");

        for (FDatadictionary d : sysparam) {
            if ("AgencyMode".equalsIgnoreCase(d.getId().getDatacode())) {
                return d;
            }
        }

        return null;
    }

    @Override
    public void disabledUserrole(String usercode, String rolecode) {
        FUserrole userrole = getValidUserrole(usercode, rolecode);
        if (null == userrole) {
            return;
        }
        Date now = new Date();
        
        if (userrole.getObtaindate().after(DatetimeOpt.truncateToDay(now))) {
            userRoleDao.deleteObject(userrole);
        } else if(userrole.getSecededate() == null || userrole.getSecededate().after(now)) {
            userrole.setSecededateToToday();
            userRoleDao.saveObject(userrole);
        }
    }

    @Override
    public boolean userHaveRole(String usercode) {
        List<FUserunit> listObjects = unituserDao.getSysUnitsByUserId(usercode);
        if(!CollectionUtils.isEmpty(listObjects)) {
            return true;
        }

        List<FUserrole> los = userRoleDao.getUserRolesByUsid(usercode, "%");
        if(!CollectionUtils.isEmpty(los)) {
            return true;
        }

        return false;
    }

    @Override
    public void saveCp(String sourceUsercode, String destUsercode, String action) {
        //覆盖，先将本身岗位权限清空，然后直接拷贝源岗位权限
        if("0".equals(action)) {
            List<FUserunit> userunits = unituserDao.getSysUnitsByUserId(destUsercode);
            if(!CollectionUtils.isEmpty(userunits)) {
                for (FUserunit userunit : userunits) {
                    unituserDao.deleteObject(userunit);
                }
            }

            List<FUserrole> userroles = userRoleDao.getUserRolesByUsid(destUsercode, "%");
            if(!CollectionUtils.isEmpty(userroles)) {
                for (FUserrole userrole : userroles) {
                    userRoleDao.deleteObject(userrole);
                }
            }
        }

        List<FUserunit> userunits = unituserDao.getSysUnitsByUserId(sourceUsercode);
        if(!CollectionUtils.isEmpty(userunits)) {
            for (FUserunit userunit : userunits) {
                FUserunit un = new FUserunit();
                BeanUtils.copyProperties(userunit, un);
                un.getId().setUsercode(destUsercode);

                unituserDao.saveObject(un);
            }
        }

        List<FUserrole> userroles = userRoleDao.getUserRolesByUsid(sourceUsercode, "%");
        if(!CollectionUtils.isEmpty(userroles)) {
            for (FUserrole userrole : userroles) {
                FUserrole ur = new FUserrole();
                BeanUtils.copyProperties(userrole, ur);
                ur.getId().setUsercode(destUsercode);

                userRoleDao.saveObject(ur);
            }
        }
    }

    @Override
    public void saveChange(String sourceUsercode, String destUsercode) {
        String middle = "middle";

        unituserDao.updateUsercode(sourceUsercode, middle);
        unituserDao.flush();
        unituserDao.updateUsercode(destUsercode, sourceUsercode);
        unituserDao.flush();
        unituserDao.updateUsercode(middle, destUsercode);



        userRoleDao.updateUsercode(sourceUsercode, middle);
        userRoleDao.flush();
        userRoleDao.updateUsercode(destUsercode, sourceUsercode);
        userRoleDao.flush();
        userRoleDao.updateUsercode(middle, destUsercode);
    }

    @Override
    public  List<FUserinfo> getUserbByPhone(String telephone) {
        // TODO Auto-generated method stub
        return sysuserdao.getUserbByPhone(telephone);
    }


}

