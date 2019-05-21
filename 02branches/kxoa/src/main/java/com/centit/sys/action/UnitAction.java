package com.centit.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.support.utils.Algorithm;
import com.centit.support.utils.Algorithm.ParentChild;
import com.centit.sys.po.AddressBook;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FRolepower;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.FUserunitId;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.AddressBookManager;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysRoleManager;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.ISysOptLog;
import com.centit.sys.util.SysOptLogFactoryImpl;
import com.centit.webservice.client.WsclientManager;

public class UnitAction extends BaseEntityExtremeAction<FUnitinfo> {
    private static final long serialVersionUID = 1L;
    private SysUnitManager sysUnitManager;
    private CodeRepositoryManager codeRepositoryManager;
    private SysRoleManager sysRoleManager;
    private AddressBookManager addressBookMag;
    private SysUserManager sysUserManager;
    
    private List<FUserunit> unitusers;
    private Integer totalRows;
    private List<FOptinfo> fOptinfos;
    private FUserunit userunit;
    
    private WsclientManager wsclientManager;
    
    
    
    public static final Log log = LogFactory.getLog(UnitAction.class);
    private static final ISysOptLog SYS_OPT_LOG = SysOptLogFactoryImpl.getSysOptLog("UNITMAG");

    public void setSysRoleManager(SysRoleManager sysRoleManager) {
        this.sysRoleManager = sysRoleManager;
    }

    public FUserunit getUserunit() {
        return userunit;
    }

    public void setUserunit(FUserunit userunit) {
        this.userunit = userunit;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public void setCodeRepositoryManager(CodeRepositoryManager codeRepositoryManager) {
        this.codeRepositoryManager = codeRepositoryManager;
    }
    
    public void setAddressBookMag(AddressBookManager addressBookMag) {
        this.addressBookMag = addressBookMag;
    }
    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }
    
    public List<FOptinfo> getFOptinfos() {
        return fOptinfos;
    }
    public void setFOptinfos(List<FOptinfo> fOptinfos) {
        this.fOptinfos = fOptinfos;
    }
    public Integer getTotalRows() {
        return totalRows;
    }
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
    
    public List<FUserunit> getUnitusers() {
        return unitusers;
    }
    public void setUnitusers(List<FUserunit> unitusers) {
        this.unitusers = unitusers;
    }
    public void setAddressBookManager(AddressBookManager basemgr)
    {
        addressBookMag = basemgr;
    }
    

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
        setBaseEntityManager(sysUnitManager);
    }
    
    public String list()
    {
        try {       
            objList = sysUnitManager.listObjects();
            totalRows=objList.size();           
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }   
    
    public String view() {
        try {
            object=sysUnitManager.getObjectById(object.getUnitcode());
            //log.debug(object.getUnitname());
            unitusers = sysUnitManager.getSysUsersByUnitId(object.getUnitcode());
            totalRows= unitusers.size();
            return VIEW;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    private String underUnit;
    private String unitsJson;
    
    public String edit() {
        try {           
            FUnitinfo o = sysUnitManager.getObject(object);
            if(o!=null) {                                           
                //将对象o copy给object，object自己的属性会保留
                sysUnitManager.copyObjectNotNullProperty(object ,o); 
                if(o.getParentunit()!=null)
                   objList=sysUnitManager.getAllSubUnits(o.getParentunit()); 
            }
            
            unitsJson =sysUnitManager.getAllUnitsJSON();
             
            return EDIT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }       
    }
    public String builtNext()
    {
        try {
            unitsJson = "\"\"";
            object.setUnitcode(sysUnitManager.getNextKey());
             if(object.getParentunit()!=null)
                 objList=sysUnitManager.getAllSubUnits(object.getParentunit()); 
            return EDIT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    private Map<String, String> powerlist;
    
    public Map<String, String> getPowerlist() {
        return powerlist;
    }
    public void setPowerlist(Map<String, String> powerlist) {
        this.powerlist = powerlist;
    }
    

    public String editUnitPower() {

        try {
                    
            FUnitinfo dbobject = sysUnitManager.getObjectById(object.getUnitcode());            
            object=dbobject;
            List<FRolepower> list = sysRoleManager.getRolePowers("G$" + object.getUnitcode());
            
            powerlist = new HashMap<String,String>();
            for(FRolepower p:list){
                powerlist.put(p.getOptcode(), "1");
            }
            fOptinfos = CodeRepositoryUtil.getOptinfoList("R");
            //以下代码为了显示菜单间的父子级关系
            ParentChild<FOptinfo> c = new  Algorithm.ParentChild<FOptinfo>() {
                public boolean parentAndChild(FOptinfo p,FOptinfo c) {
                    return p.getOptid().equals(c.getPreoptid());
                 }
             };
            
            Algorithm.sortAsTree( fOptinfos ,c);    
            List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(fOptinfos, c);
            //fOptinfos = sortOptInfoAsTree ( CodeRepositoryUtil.getOptinfoList("R"),optIndex);
            ServletActionContext.getContext().put("INDEX", getOptIndex(optIndex));

            
            totalRows = fOptinfos.size();   
            return "editUnitPower";
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

    public String[] optcodelist;
    
    public String[] getOptcodelist() {
        return optcodelist;
    }
    public void setOptcodelist(String[] optcodelist) {
        this.optcodelist = optcodelist;
    }
    public String saveUnitPower() {
            try {
              
                sysRoleManager.saveRolePowers( "G$" + object.getUnitcode() ,optcodelist);   
                savedMessage();
                SYS_OPT_LOG.log(((FUserinfo) this.getLoginUser()).getUsercode(), object.getUnitcode(), "更新 ["
                        + CodeRepositoryUtil.getValue("unitcode", object.getUnitcode()) + "] 机构权限信息");
                return SUCCESS;
            }   
         catch (Exception ee) {
            ee.printStackTrace();
            return ERROR;
        }
    }
    
    private String usercode;
        
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    
    public String deleteUnitUser() {
   
        try {       
            sysUnitManager.deleteUnitUser(userunit.getId());
            deletedMessage();   
            object= sysUnitManager.getObjectById(userunit.getUnitcode());
            SYS_OPT_LOG.log(((FUserinfo) this.getLoginUser()).getUsercode(), object.getUnitcode(),
                    "删除 [" + CodeRepositoryUtil.getValue("unitcode", userunit.getUnitcode()) + "] 机构用户 ["
                            + CodeRepositoryUtil.getValue("usercode", userunit.getUsercode()) + "]");
    
            return "reView";
            
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    private List<FUserinfo> userlist;
    public String builtUnitUser(){  
        FUserDetail user = ((FUserDetail)getLoginUser());//.getUserinfo（）;
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        userlist=sysUserManager.getUserUnderUnit(dept.getUnitcode());
            return "editUnitUser";      
    }
    
    public String editUnitUser() {
   
        try {
            FUserunitId id = new FUserunitId();
            id.setUnitcode(object.getUnitcode());
            id.setUsercode(usercode);
            FUserunit dbobject = sysUnitManager.findUnitUserById(id);   
            if (dbobject == null) {
                dbobject = new FUserunit();
                dbobject.setId(id);
            }       
            userunit=dbobject;
            
            return "editUnitUser";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }   
    
    public String saveUnitUser() {

        try {
        
            FUserunit dbobject = sysUnitManager.findUnitUserById(userunit.getId());
            String oldValue = null;
            if(dbobject!=null)
            {
               this.setEorroMessage("你保存的用户已存在");
            }
            else{
                oldValue = userunit.display();
                List<FUserunit> ls= sysUnitManager.getSysUnitsByUserId(userunit.getUsercode());
                userunit.setIsprimary("T");
                for(FUserunit l :ls){
                    if(l.getIsprimary().equals("T"))
                        userunit.setIsprimary("F");
                }
            }
            try {               
                sysUnitManager.saveUnitUser (userunit);
                
                SYS_OPT_LOG.log(((FUserinfo) this.getLoginUser()).getUsercode(), userunit.getId().toString(),
                        userunit.display(), oldValue);
            
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                saveError(e.getMessage());
                return "editUnitUser";
            }       
            return "reView";
            
        } catch (Exception ee) {
            ee.printStackTrace();
            return ERROR;
        }
    }

    public String delete() 
    {

        try {
            
            try {
                object=sysUnitManager.getObjectById(object.getUnitcode());
                sysUnitManager.disableObject(object);
                //刷新内存中的单位信息
                codeRepositoryManager.refresh("unitcode");  
                deletedMessage();
                //同步机构信息到移动端
                wsclientManager.syncDepList(object.getUnitcode());
            } catch (Exception e) {
                log.error(e.getMessage(), e);               
                return EDIT;
            }
            if(underUnit==null)
            return SUCCESS;
            else
                return "underUnit";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

    }
    public String renew() 
    {
        try {
            try {
                object=sysUnitManager.getObjectById(object.getUnitcode());
                sysUnitManager.renewObject(object);
                renewedMessage();
            } catch (Exception e) {
                
                return EDIT;
            }
            if(underUnit==null)
            return SUCCESS;
            else
                return "underUnit";
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    public String save() {
        
        try {
            if(object!=null)
            {
            FUnitinfo dbobject=sysUnitManager.getObjectById(object.getUnitcode());
            if(dbobject!=null)
            {
                dbobject.copyNotNullProperty(object);
                object=dbobject;        
            }
            }
            try {               
                sysUnitManager.saveObject(object);
                //刷新内存中的单位信息
                codeRepositoryManager.refresh("unitcode");              
                savedMessage();
                //同步机构信息到移动端
                wsclientManager.syncDepList(object.getUnitcode());
                if(underUnit==null)
                return SUCCESS;
                else
                    return "underUnit";
            } catch (Exception e) {
                log.error(e.getMessage(), e);               
                return EDIT;
            }           
            
        } catch (Exception ee) {
            ee.printStackTrace();
            return ERROR;
        }
    }
    public String selectUser() {
        
        return "selectUser";
    }
    private AddressBook addressBook;
    
    public AddressBook getAddressBook() {
        return addressBook;
    }
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
    public String editAddressBook() {

        try {
            
//          FUnitinfo dbobject = sysUnitManager.getObjectById(object.getUnitcode());
//          if (dbobject == null ) {
//              return ERROR;
//          }
            
            if (object.getAddrbookid()==null||object.getAddrbookid().equals(0L)){
                Long id = addressBookMag.getNextAddressId();
                addressBook = new AddressBook();
                addressBook.setAddrbookid(id);
                addressBook.setBodycode( object.getUnitcode());
                addressBook.setBodytype("D");
                addressBook.setRepresentation( object.getUnitname() );
                object.setAddrbookid(id);               
                sysUnitManager.saveObject(object);
                addressBookMag.saveObject(addressBook);
            }
            return "editAddressBook";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }   
    public JSONObject result;
    public String selectvalue;
    
    public String getSelectvalue() {
        return selectvalue;
    }
    public void setSelectvalue(String selectvalue) {
        this.selectvalue = selectvalue;
    }
    public JSONObject getResult() {
        return result;
    }
    public void setResult(JSONObject result) {
        this.result = result;
    }
    public String getUnituser(){
        
        Set<FUserinfo> userList=CodeRepositoryUtil.getUnitUsers(selectvalue);
        Map<String, String> map=new HashMap<String, String>();
        
        for(FUserinfo userinfo : userList){
            
            map.put(userinfo.getUsercode(), userinfo.getUsername());
            
        }
        result=JSONObject.fromObject(map);

        return "unituser";
    }

    public List<FUserinfo> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<FUserinfo> userlist) {
        this.userlist = userlist;
    }

    public String getUnderUnit() {
        return underUnit;
    }

    public void setUnderUnit(String underUnit) {
        this.underUnit = underUnit;
    }

    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }

    public void setWsclientManager(WsclientManager wsclientManager) {
        this.wsclientManager = wsclientManager;
    }
    
}
