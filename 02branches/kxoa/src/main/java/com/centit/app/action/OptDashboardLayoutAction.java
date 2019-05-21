package com.centit.app.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.po.OptDashboardLayout;
import com.centit.app.po.OptDashboardModule;
import com.centit.app.po.OptLayoutMethod;
import com.centit.app.service.OptDashboardLayoutManager;
import com.centit.app.service.OptDashboardModuleManager;
import com.centit.app.service.OptLayoutMethodManager;
import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.sys.security.FUserDetail;

public class OptDashboardLayoutAction extends BaseEntityExtremeAction<OptDashboardLayout>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptDashboardLayoutManager optDashboardLayoutMgr;
    
    private OptDashboardModuleManager optDashboardModuleMgr;
    
    private OptLayoutMethodManager optLayoutMethodMgr;
    
    public OptDashboardLayoutManager getOptDashboardLayoutMgr() {
        return optDashboardLayoutMgr;
    }

    public void setOptDashboardLayoutMgr(
            OptDashboardLayoutManager optDashboardLayoutMgr) {
        this.optDashboardLayoutMgr = optDashboardLayoutMgr;
        super.setBaseEntityManager(optDashboardLayoutMgr);
    }
    
   
    public OptDashboardModuleManager getOptDashboardModuleMgr() {
        return optDashboardModuleMgr;
    }

    public void setOptDashboardModuleMgr(
            OptDashboardModuleManager optDashboardModuleMgr) {
        this.optDashboardModuleMgr = optDashboardModuleMgr;
    }
    
    public OptLayoutMethodManager getOptLayoutMethodMgr() {
        return optLayoutMethodMgr;
    }

    public void setOptLayoutMethodMgr(OptLayoutMethodManager optLayoutMethodMgr) {
        this.optLayoutMethodMgr = optLayoutMethodMgr;
    }

    @Override
    public String built() {
        initCommonData();
        return super.built();
    }

    @Override
    public String edit() {
        initCommonData();
        return super.edit();
    }
    
    @Override
    public String view() {
        initCommonData();
        return super.view();
    }
    private void initCommonData(){
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("isUsed", "T");
        List<OptDashboardModule> modules = optDashboardModuleMgr.listObjects(filterMap);
        request.setAttribute("moduleList", modules);
        
        List<OptLayoutMethod> methods = optLayoutMethodMgr.listObjects();
        request.setAttribute("methodList", methods);
    }
    
     @Override
     public String save() {
       try {
           FUserDetail userInfo = (FUserDetail) getLoginUser();
           if(object.getId() == null){
               object.setId(optDashboardLayoutMgr.getNextSequence());
               object.setCreateBy(userInfo.getUsercode());
               object.setCreateTime(new Date());
           }
           OptDashboardLayout dbObject = baseEntityManager.getObject(object);
           if (dbObject != null) {
               optDashboardLayoutMgr.copyObjectNotNullProperty(dbObject, object);
               object = dbObject;
           }
           if("BUILTIN".equals(object.getLayoutType())){
               object.setUserScope("ALL");
           }
           if("PERSONAL".equals(object.getLayoutType())){
               object.setUserScope(userInfo.getUsercode());
           }
           
           optDashboardLayoutMgr.saveObject(object);
           savedMessage();
           return SUCCESS;
       } catch (Exception e) {
           log.error(e.getMessage(), e);
           saveError(e.getMessage());
           return ERROR;
       }
}
    
}
