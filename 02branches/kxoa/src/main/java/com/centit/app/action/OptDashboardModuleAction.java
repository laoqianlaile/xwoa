package com.centit.app.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.centit.app.po.OptDashboardModule;
import com.centit.app.service.OptDashboardModuleManager;
import com.centit.core.action.BaseEntityExtremeAction;

public class OptDashboardModuleAction extends BaseEntityExtremeAction<OptDashboardModule>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptDashboardModuleManager optDashboardModuleMgr;
    

    public OptDashboardModuleManager getOptDashboardModuleMgr() {
        return optDashboardModuleMgr;
    }

    public void setOptDashboardModuleMgr(
            OptDashboardModuleManager optDashboardModuleMgr) {
        this.optDashboardModuleMgr = optDashboardModuleMgr;
        super.setBaseEntityManager(optDashboardModuleMgr);
    }
    
     @Override
     public String save() {
       try {
           if(object.getId() == null){
               object.setId(optDashboardModuleMgr.getNextSequence());
           }
           OptDashboardModule dbObject = baseEntityManager.getObject(object);
           if (dbObject != null) {
               optDashboardModuleMgr.copyObjectNotNullProperty(dbObject, object);
               object = dbObject;
           }
           optDashboardModuleMgr.saveObject(object);
           savedMessage();
           return SUCCESS;
       } catch (Exception e) {
           log.error(e.getMessage(), e);
           saveError(e.getMessage());
           return ERROR;
       }
    }
     
     public void getJsonByModuleCode(){
         String moduleCode = request.getParameter("moduleCode");
         OptDashboardModule optDashboardModule = optDashboardModuleMgr.findByModuleCode(moduleCode);
         
         String jsonStr = "";
         if(optDashboardModule != null){
             JSONObject jsonObj = JSONObject.fromObject(optDashboardModule);
             jsonStr = jsonObj.toString();
         }
         PrintWriter out = null;;
         try {
             out = ServletActionContext.getResponse().getWriter();
             out.print(jsonStr);
         } catch (IOException e) {
             log.error(e.getMessage());
         }
         out.flush();  
         out.close();  
     }
    
}
