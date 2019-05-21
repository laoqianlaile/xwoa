package com.centit.sys.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.support.utils.Algorithm;
import com.centit.support.utils.Algorithm.ParentChild;
import com.centit.sys.po.FOptWithPower;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FRolepower;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.FUserroleId;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.SysRoleManager;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.ISysOptLog;
import com.centit.sys.util.SysOptLogFactoryImpl;

public class DeptManagerAction extends BaseEntityExtremeAction<FUnitinfo> {
    
    private static final long serialVersionUID = 1L;
    private SysUnitManager sysUnitManager;
    private CodeRepositoryManager codeRepositoryManager;
    private SysRoleManager sysRoleManager;
    private SysUserManager sysUserManager;          
    private List<FOptWithPower> fOptPowers;
    public static final Log log = LogFactory.getLog(DeptManagerAction.class);
    
    private static final ISysOptLog SYS_OPT_LOG = SysOptLogFactoryImpl.getSysOptLog("DEPTROLE");
    
    public void setCodeRepositoryManager(CodeRepositoryManager codeRepositoryManager) {
        this.codeRepositoryManager = codeRepositoryManager;
    }

    private List<FRoleinfo> unitRoles;
    private List<FUserrole> userroles ;
    

    
    public List<FOptWithPower> getFOptPowers() {
        if(fOptPowers==null)
            fOptPowers = new ArrayList<FOptWithPower>();
        return fOptPowers;
    }

    public void setFOptPowers(List<FOptWithPower> fOptPowers) {
        this.fOptPowers = fOptPowers;
    }

    public List<FUserrole> getUserroles() {
        if(userroles==null)
            userroles = new ArrayList<FUserrole>();
        return userroles;
    }

    public void setUserroles(List<FUserrole> userroles) {
        this.userroles = userroles;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
        this.setBaseEntityManager(sysUnitManager);
    }
    public void setSysRoleManager(SysRoleManager sysRoleManager) {
        this.sysRoleManager = sysRoleManager;
    }
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }
    public List<FRoleinfo> getUnitRoles(){
        if(unitRoles==null)
            unitRoles = new ArrayList<FRoleinfo>();
        return unitRoles;
    }
    
    public List<FOptWithPower> getAllPowers(){
        return fOptPowers;
    }
    public void setAllPowers(List<FOptWithPower> ops){
        fOptPowers = ops;
    }
    


    public String listunit() 
    {
        try {
            FUserDetail user = ((FUserDetail)getLoginUser());//.getUserinfo（）;
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            if(dept != null){
                String sParentUnit = dept.getUnitcode();
                objList = sysUnitManager.getAllSubUnits(sParentUnit);
            }
            return "listunit";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    

    private Map<String, String> powerlist;


    public Map<String, String> getPowerlist() {
        if(powerlist==null)
            powerlist = new HashMap<String, String> ();
        return powerlist;
    }

    public void setPowerlist(Map<String, String> powerlist) {
        this.powerlist = powerlist;
    }

    public String editDeptPower() {
        try {
            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();
            
            FUnitinfo o =  sysUnitManager.getObjectById(object.getUnitcode());
            if(o!=null)                                             
                object.copyNotNullProperty(o);
        
            List<FRolepower> list = sysRoleManager.getRolePowers( sParentUnit + "$" + object.getUnitcode());
            
            powerlist=new HashMap<String, String>();
            
            for(FRolepower p:list){
                powerlist.put(p.getOptcode(), "1");
            }       
            fOptPowers = sysRoleManager.getOptWithPowerUnderUnit(sParentUnit);  
            
            //以下代码为了显示菜单间的父子级关系
            ParentChild<FOptWithPower> c = new  Algorithm.ParentChild<FOptWithPower>() {
                public boolean parentAndChild(FOptWithPower p,FOptWithPower c) {
                    return p.getOptid().equals(c.getPreoptid());
                 }
             };
            
            Algorithm.sortAsTree( fOptPowers ,c);    
            List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(fOptPowers, c);
            //fOptinfos = sortOptInfoAsTree ( CodeRepositoryUtil.getOptinfoList("R"),optIndex);
            ServletActionContext.getContext().put("INDEX", getOptIndex(optIndex));
            
            return "editDeptPower";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    private String[] optcodelist;
    
    
    public String[] getOptcodelist() {
        return optcodelist;
    }
    public String saveDeptPower() {
        try { 
            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();

            object = sysUnitManager.getObjectById(object.getUnitcode());
            
            if(object == null || object.getUnitcode() == null ){
                return "editDeptPower";
            }
            try {
                sysRoleManager.saveRolePowers( sParentUnit + "$" + object.getUnitcode() ,optcodelist);
                savedMessage();
                
                 SYS_OPT_LOG.log(((FUserinfo) this.getLoginUser()).getUsercode(), "DEPTPOW",
                            String.valueOf(object.getUnitcode()), "saveDeptPower",  object.display(), null);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                saveError( e.getMessage());
                return "editDeptPower";
            }
            return "saveDeptPower";
        } catch (Exception ee) {
            ee.printStackTrace();
            return ERROR;
        }
    }
    
    
     public void setOptcodelist(String[] optcodelist) {
        this.optcodelist = optcodelist;
    }
    
    private List<FRoleinfo> fRoleinfos;
    
    public List<FRoleinfo> getFRoleinfos() {
        return fRoleinfos;
    }

    public void setFRoleinfos(List<FRoleinfo> fRoleinfos) {
        this.fRoleinfos = fRoleinfos;
    }

    @SuppressWarnings("unchecked")
    public String listrole() 
    {
        try {
            Limit limit=ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            request.getParameterMap();
            Map<String,Object> filterMap=convertSearchColumn(request.getParameterMap());
            
            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            if (dept != null) {
                String sParentUnit = dept.getUnitcode();
                if("881".equals(sParentUnit))//太极传来顶级部门编码为881 
                    sParentUnit="1";
                filterMap.put("ROLECODE", sParentUnit + "-%");

                fRoleinfos = sysRoleManager.listObjects(filterMap, pageDesc);
                totalRows = pageDesc.getTotalRows();
            }
            return "listrole";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    private FRoleinfo roleinfo;

    public FRoleinfo getRoleinfo() {
        return roleinfo;
    }

    public void setRoleinfo(FRoleinfo roleinfo) {
        this.roleinfo = roleinfo;
    }

    public String editDeptRole() {

        try {
            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();
            
            if(roleinfo!=null){
                if("881".equals(sParentUnit))//太极传来顶级部门编码为881 
                    sParentUnit="1";
                FRoleinfo dbobject=sysRoleManager.getObjectById(roleinfo.getRolecode());
            
                if (dbobject != null ) {
                    roleinfo=dbobject;
                }
            
                List<FRolepower> list = sysRoleManager.getRolePowers(roleinfo.getRolecode());   
                powerlist = new HashMap<String,String>();
                for(FRolepower p:list){
                    powerlist.put(p.getOptcode(), "1");//p.getRolecode());
                }
            }
            fOptPowers = sysRoleManager.getOptWithPowerUnderUnit(sParentUnit);
            //以下规定节点间的父子关系
            ParentChild<FOptWithPower> c = new  Algorithm.ParentChild<FOptWithPower>() {
                public boolean parentAndChild(FOptWithPower p,FOptWithPower c) {
                    return p.getOptid().equals(c.getPreoptid());
                 }
             };
            
            Algorithm.sortAsTree(fOptPowers ,c);    
            List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(fOptPowers, c);
            ServletActionContext.getContext().put("INDEX", getOptIndex(optIndex));

            return "editDeptRole";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    private String getOptIndex(List<Integer> optIndex) {
        JSONObject jo = new JSONObject();
        jo.put("indexes", optIndex);
        return jo.toString();
    }   
    public String builtDeptRole() {
        try {
            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            if(dept != null){
                String sParentUnit = dept.getUnitcode();
                
                roleinfo = new FRoleinfo();
                //List<FRolepower> list = sysRoleManager.getRolePowers(roleinfo.getRolecode()); 
                powerlist = new HashMap<String,String>();
                //for(FRolepower p:list){
                    //powerlist.put(p.getOptcode(), "1");//p.getRolecode());
                //}
                if("881".equals(sParentUnit))//太极传来顶级部门编码为881 
                    sParentUnit="1";
                fOptPowers = sysRoleManager.getOptWithPowerUnderUnit(sParentUnit);
                
              //以下代码为了显示菜单间的父子级关系
                ParentChild<FOptWithPower> c = new  Algorithm.ParentChild<FOptWithPower>() {
                    public boolean parentAndChild(FOptWithPower p,FOptWithPower c) {
                        return p.getOptid().equals(c.getPreoptid());
                     }
                 };
                
                Algorithm.sortAsTree( fOptPowers ,c);    
                List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(fOptPowers, c);
                //fOptinfos = sortOptInfoAsTree ( CodeRepositoryUtil.getOptinfoList("R"),optIndex);
                ServletActionContext.getContext().put("INDEX", getOptIndex(optIndex));
            }
            return "editDeptRole";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }       
    }
    
//    private String[] optlist;
//    
//    public String[] getOptlist() {
//        return optlist;
//    }
//
//    public void setOptlist(String[] optlist) {
//        this.optlist = optlist;
//    }

    @SuppressWarnings("null")
    public String saveDeptRole() {

        try {
            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            if(dept == null){
                saveError("dept is null!");
                return ERROR;
            }
            String sParentUnit = dept.getUnitcode();
            if("881".equals(sParentUnit))//太极传来顶级部门编码为881 
                sParentUnit="1";

            if(roleinfo.getRolecode() == null ){
                return "savedrole";
            }
            // 检测角色代码前缀
            try {
                if(! roleinfo.getRolecode().startsWith(sParentUnit + "-")){
                    
                    roleinfo.setRolecode(sParentUnit + "-"+ roleinfo.getRolecode());
                }
                FRoleinfo dbRoleinfo = sysRoleManager.getObjectById(roleinfo.getRolecode());
                
                if (dbRoleinfo != null){
                    //更新新值
                    dbRoleinfo.copyNotNullProperty(roleinfo);
                    //dbRoleinfo.setIsvalid("T");   
                    sysRoleManager.saveObject(dbRoleinfo);
//                    sysRoleManager.saveRolePowers(dbRoleinfo.getRolecode(),optlist);
                    sysRoleManager.saveRolePowers(dbRoleinfo.getRolecode(),optcodelist);
                    //roleinfo = dbRoleinfo;
                }else{
                    if(roleinfo.getIsvalid() == null)
                        roleinfo.setIsvalid("T");           
                    sysRoleManager.saveObject(roleinfo);
//                    sysRoleManager.saveRolePowers(roleinfo.getRolecode(),optlist);
                    sysRoleManager.saveRolePowers(roleinfo.getRolecode(),optcodelist);
                    //roleinfo = new FRoleinfo();
                }
                //刷新系统中的缓存
                codeRepositoryManager.refresh("rolecode");
                savedMessage();
        
                SYS_OPT_LOG.log(((FUserinfo) this.getLoginUser()).getUsercode(), roleinfo.getRolecode(), 
                        roleinfo.display(), dbRoleinfo==null?"":dbRoleinfo.display());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                saveError( e.getMessage());
                return ERROR;
            }
            return "saveDeptRole";
        } catch (Exception ee) {
            ee.printStackTrace();
            return ERROR;
        }
    }
    
    public String deleteDeptRole() 
    {
        try {
            
            try {
                FRoleinfo dbObject=sysRoleManager.getObjectById(roleinfo.getRolecode());
                sysRoleManager.disableObject(dbObject);
                deletedMessage();
                //刷新系统中的缓存
                codeRepositoryManager.refresh("rolecode");
                
                SYS_OPT_LOG.log(((FUserinfo) this.getLoginUser()).getUsercode(), 
                        dbObject.getRolecode(), dbObject.display());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return "saveDeptRole";
            }
            return "saveDeptRole";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String renewDeptRole() 
    {

        try {
            FRoleinfo dbObject = sysRoleManager.getObjectById(roleinfo.getRolecode());
            try {
                
                sysRoleManager.renewObject(dbObject);
                //刷新系统中的缓存
                codeRepositoryManager.refresh("rolecode");
                renewedMessage();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                //saveDirectlyError( e.getMessage());

                return "saveDeptRole";
            }
            
            return "saveDeptRole";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }   
    
    private List<FUserinfo> userList;
    
    public List<FUserinfo> getUserList() {
        return userList;
    }
    public void setUserList(List<FUserinfo> userList) {
        this.userList = userList;
    }
    
    @SuppressWarnings("unchecked")
    public String listuser() 
    {
        try {   
            Limit limit=ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            Map<Object,Object> paramMap = (Map<Object,Object>)request.getParameterMap();
            resetPageParam(paramMap);
            if(paramMap.get("unitcode")!=null)
            {
                FUserDetail user = ((FUserDetail)getLoginUser());
                FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
                if (dept != null) {
                    String sParentUnit = dept.getUnitcode();
                    userList = sysUnitManager.getRelationUsers(sParentUnit);
                }
            }else {
                Map<String,Object> filterMap=convertSearchColumn(paramMap);
                userList=sysUserManager.listObjects(filterMap, pageDesc);
            }
            userList = (userList == null ? new ArrayList<FUserinfo>(): userList);
            
            totalRows=userList.size();      
            return "listuser";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    private FUserinfo userinfo;
    
    public FUserinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(FUserinfo userinfo) {
        this.userinfo = userinfo;
    }

    public String viewUserRole() {

        try {
            
            FUserinfo dbobject = sysUserManager.getObjectById(userinfo.getUsercode());
            if (dbobject == null) {
                saveError("entity.missing");
                return LIST;
            }
            userinfo=dbobject;

            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            if(dept != null){
                String sParentUnit = dept.getUnitcode();    
                userroles = sysUserManager.getAllUserRoles(userinfo.getUsercode(), sParentUnit + "-");      
                List<FUserrole> list= sysUserManager.getAllUserRoles(userinfo.getUsercode(), "P-");
                if(list!=null)
                userroles.addAll(list);
                totalRows= userroles.size();
            }
            return "viewUserRole";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }   
    // 用户角色设定
    private FUserrole userrole;  //用户角色
    
    public FUserrole getUserrole() {
        return userrole;
    }
    public void setUserrole(FUserrole userrole) {
        this.userrole = userrole;
    }
    
    private String roleprefix;
    
    public String getRoleprefix() {
        return roleprefix;
    }
    public void setRoleprefix(String roleprefix) {
        this.roleprefix = roleprefix;
    }
    public String bulitUserRole(){
        try {
            FUserroleId id = new FUserroleId();             
            id.setUsercode(userrole.getUsercode());
            userrole = new FUserrole();
            //获取当前时间前一天时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,-1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            id.setObtaindate(calendar.getTime()); 
            userrole.setId(id);     
            
            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            if(dept!=null){
                String sParentUnit = dept.getUnitcode();
                if("881".equals(sParentUnit))//太极传来顶级部门编码为881 
                    sParentUnit="1";
                roleprefix=sParentUnit+"-";
            }
            return "editUserRole";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String editUserRole() {
        try {
            FUserroleId id = new FUserroleId();             
            id.setUsercode(userrole.getUsercode());
            id.setRolecode(userrole.getRolecode());     
            userrole = sysUserManager.getValidUserrole(id.getUsercode(), id.getRolecode());
            FUserDetail user = ((FUserDetail)getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            if(dept != null){
                String sParentUnit = dept.getUnitcode();
                roleprefix=sParentUnit+"-";
            }
            return "editUserRole";
        } catch (Exception e) {
            e.printStackTrace();
            return (ERROR);
        }
    }

    public String saveUserRole() {
        try {
                
            FUserrole desobj = sysUserManager.getValidUserrole(userrole.getUsercode(), userrole.getRolecode());
            if (desobj != null)
            {
                desobj.copyNotNullProperty(userrole);
                userrole=desobj;
            }
            try {   
                log.debug(userrole);
                sysUserManager.saveUserrole(userrole);
                savedMessage();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return ERROR;
            }
            return "saveUserRole";
        } catch (Exception ee) {
            ee.printStackTrace();
            return ERROR;
        }
    }
    
    public String deleteUserRole() {
        try {
            userrole=sysUserManager.getFUserroleByID(userrole.getId());
            sysUserManager.deleteUserrole(userrole);
            deletedMessage();
            return "saveUserRole";

        } catch (Exception ee) {
            ee.printStackTrace();
            return (ERROR);
        }
    }   
}
