package com.centit.app.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.centit.app.po.OptLayoutMethod;
import com.centit.app.service.OptLayoutMethodManager;
import com.centit.core.action.BaseEntityDwzAction;

public class OptLayoutMethodAction extends BaseEntityDwzAction<OptLayoutMethod>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptLayoutMethodManager optLayoutMethodManager;
    
    public OptLayoutMethodManager getOptLayoutMethodManager() {
        return optLayoutMethodManager;
    }

    public void setOptLayoutMethodManager(
            OptLayoutMethodManager optLayoutMethodManager) {
        this.optLayoutMethodManager = optLayoutMethodManager;
        super.setBaseEntityManager(optLayoutMethodManager);
    }



    
     public String save() {
       try {
           if(object.getId() == null){
               object.setId(optLayoutMethodManager.getNextSequence());
           }
           OptLayoutMethod dbObject = optLayoutMethodManager.getObject(object);
           if (dbObject != null) {
               optLayoutMethodManager.copyObjectNotNullProperty(dbObject, object);
               object = dbObject;
           }
           optLayoutMethodManager.saveObject(object);
          
           savedMessage();
           return SUCCESS;
       } catch (Exception e) {
           log.error(e.getMessage(), e);
           saveError(e.getMessage());
           return ERROR;
       }
     }
    
     public void getContent(){
         String msg = "";
         try{
             OptLayoutMethod dbObject = optLayoutMethodManager.getObject(object);
             msg = dbObject.getContent();
         }catch(Exception e){
             msg = e.getMessage();
             log.error("发生异常，异常信息："+e.getMessage());
         }
         PrintWriter out = null;
         try {
             out = ServletActionContext.getResponse().getWriter();
             out.print(msg);
         } catch (IOException e) {
             log.error(e.getMessage());
         }
         out.flush();  
         out.close();
     }
}
