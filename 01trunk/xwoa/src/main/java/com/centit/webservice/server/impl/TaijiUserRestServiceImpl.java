package com.centit.webservice.server.impl;

import java.util.Date;
import java.util.List;




import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.SysRoleManager;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.webservice.client.WsclientManager;
import com.centit.webservice.pojo.UnitDTO;
import com.centit.webservice.pojo.UserDTO;
import com.centit.webservice.pojo.UserUnitDTO;
import com.centit.webservice.server.TaijiUserRestService;
import com.centit.webservice.util.PostMsg;
/**
 * 自建系统同步太极用户信息
 * TODO Class description should be added
 * @author Administrator
 * @create 2017-7-19
 * @version
 */
public class TaijiUserRestServiceImpl implements TaijiUserRestService{
    private Logger log = Logger.getLogger(TaijiUserRestServiceImpl.class);
    private SysUnitManager sysUnitManager;
    private SysUserManager sysUserManager;
    private SysRoleManager sysRoleManager;
    private WsclientManager wsclientManager;
    String basePath = "http://192.16.199.9:81";//太极提供最新同步地址
    /**
     * 获取太极用户信息
     */
    @Override
    public List<UserDTO> getAllUser() {
        try{
            String result = PostMsg.get(basePath+"/byServiceId/1baebde2-6509-46da-a210-d414f5ccc260?sys_code=320781010037&key=3a53a53c9c52793a578b24014cad8532&nowpage=1&pagesize=1000","ff8080815df49bf3015e12f6bc020002");//最新获取路径/user/getAll/ff8080815df49bf3015e12f6bc020002为appid参数值
            JSONObject jo = JSONObject.parseObject(result);
            if(jo.get("success")==null || "false".equals(jo.getString("success"))){
                log.error("获取所有用户时,接口返回失败。");
            }
            JSONObject obj = jo.getJSONObject("obj");//获取传送对象
            if(obj != null){
                JSONArray rows = obj.getJSONArray("rows");
                if(rows!=null){
                    List<UserDTO> userDtos = JSONArray.parseArray(rows.toJSONString(), UserDTO.class);
                    return userDtos;
                }
            }
         }catch(Exception e){
             log.error("获取所有用户时:"+e.getMessage());
         }
        return null;
    }
   
    /**
     * 将太极用户数据转换为自身系统用户信息
     */
    @Override
    public void syncUser() {
      List<UserDTO> userDTOs = getAllUser();
      if(userDTOs != null && userDTOs.size() > 0){
          for(UserDTO userDto : userDTOs){
                JSONArray rowsT= JSONArray.parseArray(userDto.getOrganId());
                      if(rowsT!=null){
                          List<UserUnitDTO> userUnitDtos = JSONArray.parseArray(rowsT.toJSONString(), UserUnitDTO.class);
                          userDto.setOraganIds(userUnitDtos);
                      }
                  if(!(("1").equals(userDto.getSaveType()))){//savetype不为1的为OA正常用户
                      convertUser(userDto); 
                  }
              }
          }
    }
    /**
     * 用户信息转换到F_userinfo中
     * @param userDto
     * @return
     */
    private void convertUser(UserDTO userDto){
        FUserinfo fuser = sysUserManager.getFUserInfoByLoginname(userDto.getLoginName());//通过登录名获取f_userinfo信息
        if(fuser!=null){//已保存过
            if("20256".equals(userDto.getUsingState() )){//（20255启用，20256停用）
                fuser.setIsvalid("F");//禁用用户
                sysUserManager.saveObject(fuser);
                //同步人员信息到移动端
                wsclientManager.syncUserList(userDto.getUserId());
            }else{//正常用户
                if(!(userDto.getUpdateTime()).equals((fuser.getLastModifyDate()))){//根据updatetime变化来更新用户
                    userDto.setUserId(fuser.getUsercode());
                    if(userDto.getUsingState()!=null && "20255".equals(userDto.getUsingState())){//启用
                        fuser.setIsvalid("T");
                    }else{
                        fuser.setIsvalid("F");
                    }
                    log.info("最新密码："+userDto.getLoginPassword()+"原来的userpin:"+fuser.getUserpin());
                    fuser.setUserpin(sysUserManager.encodePassword(userDto.getLoginPassword(), fuser.getUsercode()));//密码加密
                    if((userDto.getLoginName()).equals((fuser.getLoginname()))){
                            if((userDto.getUserName()).equals((fuser.getUsername()))){
                                if((userDto.getPhone()).equals((fuser.getContactPhone()))){
                                        fuser.setLastModifyDate(userDto.getUpdateTime());
                                        deletesynUserunit(userDto);//删除用户部门、行政岗位、岗位
                                }
                            }
                        }
                    log.info("处理之后的userping："+fuser.getUserpin());
                    fuser.setLoginname(userDto.getLoginName());
                    fuser.setUsername(userDto.getUserName());
                    fuser.setContactPhone(userDto.getPhone());
                    fuser.setUserState("0");
                    fuser.setCreateDate(userDto.getCreateTime()); 
                    sysUserManager.saveObject(fuser);
                    //同步人员信息到移动端
                    wsclientManager.syncUserList(userDto.getUserId());
                }
            }
        }else{//新增用户
            fuser=new FUserinfo();
            fuser.setUsercode(userDto.getUserId());//标记为太极用户
            fuser.setUserpin(sysUserManager.encodePassword(userDto.getLoginPassword(), fuser.getUsercode()));//密码加密
            if(userDto.getUsingState()!=null && "20255".equals(userDto.getUsingState())){//启用
                fuser.setIsvalid("T");
            }else{
                fuser.setIsvalid("F");
            }
            fuser.setLoginname(userDto.getLoginName());
            fuser.setUsername(userDto.getUserName());
            fuser.setContactPhone(userDto.getPhone());
            fuser.setDatasource("3");//3代表是太极同步过来的用户
            fuser.setUserState("0");
            if(StringUtils.isBlank(userDto.getSort())){
                fuser.setUserorder(Long.valueOf("999"));//默认最大值
            }else{
                fuser.setUserorder(Long.valueOf(userDto.getSort()));  
            }
            if(fuser.getSortid() == null){
                fuser.setSortid(Long.valueOf("1"));
            }
            fuser.setCreateDate(new Date());   
            sysUserManager.saveObject(fuser);
            synUserunit(userDto);//初始化用户部门、行政岗位、岗位
            synUserrole(userDto);//初始化用户角色
            //同步人员信息到移动端
            wsclientManager.syncUserList(userDto.getUserId());
        }
    } 
    
    /**
     * 关联用户部门表userunit
     * @param basemgr
     */
    public void synUserunit(UserDTO userInfo){
       
        if(userInfo.getOrganId()!=null && userInfo.getOraganIds().size()>0){
            for(int i=0;i<userInfo.getOraganIds().size();i++){
                UserUnitDTO info=userInfo.getOraganIds().get(i);
                sysUnitManager.deleteUserUnit(info.getUserId());
                FUserunit userUnit = new FUserunit();
                userUnit.setUnitcode(info.getOrganId());//所在部门,  
                userUnit.setUsercode(userInfo.getUserId());//用户id 
                userUnit.setUserstation("blank");//默认岗位
                userUnit.setUserrank("0");//默认行政岗位
                userUnit.setCreateDate(userInfo.getCreateTime());
                if(i==0){
                    userUnit.setIsprimary("T");   
                }else{
                    userUnit.setIsprimary("F");   
                }
                sysUnitManager.saveUnitUser(userUnit);  
               
            }
        }else{
            FUserunit userUnit = new FUserunit();
            userUnit.setUsercode(userInfo.getUserId()); 
            userUnit.setUnitcode("881");//所在部门
            userUnit.setIsprimary("T");    
            userUnit.setUserstation("blank");//默认岗位
            userUnit.setUserrank("0");//默认行政岗位
            userUnit.setCreateDate(userInfo.getCreateTime());
            sysUnitManager.saveUnitUser(userUnit);  
        }
       
       
    }
    
    /**
     * 修改部门后关联用户部门表userunit
     * @param basemgr
     */
    public void deletesynUserunit(UserDTO userInfo){
        sysUnitManager.deleteUserUnit(userInfo.getUserId());//删除原先人员主部门
        if(userInfo.getOrganId()!=null && userInfo.getOraganIds().size()>0){
            for(int i=0;i<userInfo.getOraganIds().size();i++){
                UserUnitDTO info=userInfo.getOraganIds().get(i);
                FUserunit userUnit = new FUserunit();
                userUnit.setUnitcode(info.getOrganId());//所在部门,  
                userUnit.setUsercode(userInfo.getUserId());//用户id 
                userUnit.setUserstation("blank");//默认岗位
                userUnit.setUserrank("0");//默认行政岗位
                userUnit.setCreateDate(userInfo.getCreateTime());
                if(i==0){
                    userUnit.setIsprimary("T");   
                }else{
                    userUnit.setIsprimary("F");   
                }
                sysUnitManager.saveUnitUser(userUnit);  
               
            }
        }else{
            FUserunit userUnit = new FUserunit();
            userUnit.setUsercode(userInfo.getUserId()); 
            userUnit.setUnitcode("881");//所在部门
            userUnit.setIsprimary("T");    
            userUnit.setUserstation("blank");//默认岗位
            userUnit.setUserrank("0");//默认行政岗位
            userUnit.setCreateDate(userInfo.getCreateTime());
            sysUnitManager.saveUnitUser(userUnit);  
        }
       
       
    }
    /**
     * 关联用户角色表
     * @param basemgr
     */
    public void synUserrole(UserDTO userInfo){
        FUserrole userRole = new FUserrole();
        userRole.setUsercode(userInfo.getUserId());
        userRole.setRolecode("1-OA_COM");//默认角色
        userRole.setObtaindate(userInfo.getCreateTime());//审核通过时间
        sysRoleManager.saveUserrole(userRole);
        
    }
   /*
    /**
     * 获取所有部门
     * @return
     */
   public  List<UnitDTO> getAllUnit(){
       try{
           String result = PostMsg.get(basePath+"/byServiceId/40746bf0-c45d-4124-9c37-2be6dd877545?sys_code=320781010037&key=6a53a53c9c52793a578b24010cad8538&nowpage=1&pagesize=100","ff8080815df49bf3015e12f6bc020002");//原获取路径/user/getAll
           JSONObject jo = JSONObject.parseObject(result);
           if(jo.get("success")==null || "false".equals(jo.getString("success"))){
               log.error("获取所有部门时,接口返回失败。");
           }
           JSONArray rows = jo.getJSONArray("obj");
           //SONObject obj = jo.getJSONObject("obj");//获取传送对象
           if(rows!=null){
                List<UnitDTO> unitDtos = JSONArray.parseArray(rows.toJSONString(), UnitDTO.class);
                return unitDtos;
           } 
        }catch(Exception e){
            log.error("获取所有部门时:"+e.getMessage());
        }
       return null;
    }
   /* *//**
     * 获取所有部门
     * @return
     *//*
   public  List<UnitDTO> getAllUnit(){
       try{
           String result = RESTClient.get(basePath+"/service/orgInfo/list?key=6a53a53c9c52793a578b24010cad8538&nowpage=1&pagesize=1000");//原获取路径/user/getAll
           JSONArray rows = JSONArray.parseArray(result);
               if(rows!=null){
                   List<UnitDTO> unitDtos = JSONArray.parseArray(rows.toJSONString(), UnitDTO.class);
                   return unitDtos;
               }
        }catch(Exception e){
            log.error("获取所有部门时:"+e.getMessage());
        }
       return null;
    }*/
    /**
     * 同步部门
     */
    public void syncUnit(){
        List<UnitDTO> unitDTOs = getAllUnit();
        if(unitDTOs != null && unitDTOs.size() > 0){
            for(UnitDTO unitDTO : unitDTOs){
                if(unitDTO!=null){//将太极部门传输对象转换成系统部门信息
                    convertUnit(unitDTO);
                    //同步部门信息到移动端
                    wsclientManager.syncDepList(unitDTO.getOrganId());
                }
            }
        }
    }
    /**
     * 将太极部门数据同步到F-unitInfo中
     * @param userDto
     */
    private void convertUnit(UnitDTO unitDto){
        FUnitinfo funit = sysUnitManager.getUnitByDepno(unitDto.getOrganId());//通过登录名获部门编码depno信息
        if(funit!=null){//已保存过
                if("0".equals(unitDto.getSaveType())){//停用
                    funit.setIsvalid("F");//禁用部门
                }else{
                    funit.setIsvalid("T");//启用部门
                }
                funit.setUnitname(unitDto.getOrganName());//部门名称
                funit.setDepno(unitDto.getOrganId());//部门编码depno
                if(StringUtils.isBlank(unitDto.getParentId())){
                    funit.setParentunit("0"); 
                    funit.setUnittype("T");
                }else{
                    funit.setParentunit(unitDto.getParentId());
                    funit.setUnittype("S");
                }
                funit.setUnitshortname(unitDto.getOrganName());//部门名称
                funit.setUnitorder(Long.valueOf(unitDto.getSeq()));
                funit.setCreateDate(new Date());   
                sysUnitManager.saveUpdateUnitInfo(funit);
        }else{//新增部门
            funit=new FUnitinfo();
            if(StringUtils.isBlank(unitDto.getOrganId())){
                funit.setUnitcode(sysUnitManager.getNextKey());//获取unitcode 
            }else{
                funit.setUnitcode(unitDto.getOrganId());//获取unitcode 
            }
            if("0".equals(unitDto.getSaveType())){//停用
                funit.setIsvalid("F");//禁用部门
            }else{
                funit.setIsvalid("T");//启用部门
            }
            funit.setUnitname(unitDto.getOrganName());//部门名称
            funit.setDepno(unitDto.getOrganId());//部门编码depno
            if(StringUtils.isBlank(unitDto.getParentId())){
                funit.setParentunit("0"); //顶级部门 
                funit.setUnittype("T");
            }else{
                funit.setParentunit(unitDto.getParentId()); 
                funit.setUnittype("S");
            }
            funit.setUnitshortname(unitDto.getOrganName());//部门名称
            funit.setUnitorder(Long.valueOf(unitDto.getSeq()));
            funit.setCreateDate(new Date());   
            sysUnitManager.saveNewUnitInfo(funit);
        }
    } 
    public SysUnitManager getSysUnitManager() {
        return sysUnitManager;
    }
    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }
    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }
    public SysRoleManager getSysRoleManager() {
        return sysRoleManager;
    }
    public void setSysRoleManager(SysRoleManager sysRoleManager) {
        this.sysRoleManager = sysRoleManager;
    }

    public WsclientManager getWsclientManager() {
        return wsclientManager;
    }

    public void setWsclientManager(WsclientManager wsclientManager) {
        this.wsclientManager = wsclientManager;
    }
    
}
