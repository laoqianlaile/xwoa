package com.centit.powerruntime.action;

import java.util.Date;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.RiskInfo;
import com.centit.powerruntime.po.Suppowerstuffgroup;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.RiskInfoManager;
import com.centit.powerruntime.service.SuppowerstuffgroupManager;

public class GeneralModuleParamAction extends
        BaseEntityExtremeAction<GeneralModuleParam> {
    private static final long serialVersionUID = 1L;
    private GeneralModuleParamManager generalModuleParamMag;
    private RiskInfoManager riskInfoManager;
    private SuppowerstuffgroupManager suppowerstuffgroupManager;
    private String stuffGroupName;

    public void setRiskInfoManager(RiskInfoManager riskInfoManager) {
        this.riskInfoManager = riskInfoManager;
    }

    public void setGeneralModuleParamManager(GeneralModuleParamManager basemgr) {
        generalModuleParamMag = basemgr;
        this.setBaseEntityManager(generalModuleParamMag);
    }
private String isAutoClose ;
    
    /**
     * 
     */
    @Override
    public String edit() {
        object = generalModuleParamMag.getObject(object);

        if (object != null) {
            if (object.getRiskId() != null&&object.getRiskId() !=0) {
                RiskInfo riskInfo = riskInfoManager.getObjectById(object.getRiskId());
                object.setRiskInfo(riskInfo);
            }
            
            if(object.getStuffGroupId() != null){
                Suppowerstuffgroup stuffGroup=   suppowerstuffgroupManager.getObjectById(object.getStuffGroupId());
                if(stuffGroup!=null){
                stuffGroupName = stuffGroup.getStuffGroup();
                }
            }
        }
        return EDIT;
    }
    
    public String save(){
        object.setLastUpdateTime(new Date(System.currentTimeMillis()));
        return super.save();
    }
/**
 * 复制
 * @return
 */
    public String copy(){
        edit();
        object.setModuleCode("");
        return EDIT;
    }
    /**
     * 是否禁用操作
     * @return
     */
    public String editIsInUse(){
        object=generalModuleParamMag.getObjectById(object.getModuleCode());
       if("T".equals(object.getIsInUse())){
           object.setIsInUse("F"); 
       }else {
           object.setIsInUse("T");  
       }       
       generalModuleParamMag.saveObject(object);
      
        return this.list();
    }
    @Override
    public String delete(){
        return super.delete();
    }

    public String getStuffGroupName() {
        return stuffGroupName;
    }

    public void setStuffGroupName(String stuffGroupName) {
        this.stuffGroupName = stuffGroupName;
    }

    public void setSuppowerstuffgroupManager(
            SuppowerstuffgroupManager suppowerstuffgroupManager) {
        this.suppowerstuffgroupManager = suppowerstuffgroupManager;
    }

    public String getIsAutoClose() {
        return isAutoClose;
    }

    public void setIsAutoClose(String isAutoClose) {
        this.isAutoClose = isAutoClose;
    }
    
}
