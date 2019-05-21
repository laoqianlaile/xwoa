package com.centit.powerruntime.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.PageDesc;
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

    private String isAutoClose;
    private String flowCode;//流程代码
    private Long version;//流程版本
    
    /**
     * 
     */
    @Override
    public String edit() {
        object = generalModuleParamMag.getObject(object);

        if (object != null) {
            if (object.getRiskId() != null && object.getRiskId() != 0) {
                RiskInfo riskInfo = riskInfoManager.getObjectById(object
                        .getRiskId());
                object.setRiskInfo(riskInfo);
            }

            if (object.getStuffGroupId() != null) {
                Suppowerstuffgroup stuffGroup = suppowerstuffgroupManager
                        .getObjectById(object.getStuffGroupId());
                if (stuffGroup != null) {
                    stuffGroupName = stuffGroup.getStuffGroup();
                }
            }
        }
        return EDIT;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String save() {//先取得flowCode和version的值
        flowCode=object.getFlowCode();
        version=object.getVersion();
        object.setLastUpdateTime(new Date(System.currentTimeMillis()));
        super.save();
        if(StringUtils.isBlank(flowCode)){//区别是流程定义跳转还是单独通用模块保存
            return this.list();
        }
        
        return this.listModeCode();
    }

    /**
     * 复制
     * 
     * @return
     */
    public String copy() {
        edit();
        object.setModuleCode("");
        return EDIT;
    }

    /**
     * 是否禁用操作
     * 
     * @return
     */
    public String editIsInUse() {
        object = generalModuleParamMag.getObjectById(object.getModuleCode());
        if ("T".equals(object.getIsInUse())) {
            object.setIsInUse("F");
        } else {
            object.setIsInUse("T");
        }
        generalModuleParamMag.saveObject(object);
        if(StringUtils.isBlank(request.getParameter("s_flowCode"))){//通过request来取值，在列表页面直接传值操作
            return this.list();
        }
        return this.listModeCode();
    }

    @Override
    public String delete() {
        super.delete();
        if(StringUtils.isBlank(request.getParameter("s_flowCode"))){//通过request来取值，在列表页面直接传值操作
            return this.list();
        }
        return this.listModeCode();
    }

    /**
     * 根据流程代码、流程版本获取流程环节属性配置信息
     * 
     * @return
     */
    public String listModeCode() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);

            //对flowCode和version进行判断塞值
            if(StringUtils.isBlank(flowCode)){
                flowCode=request.getParameter("s_flowCode");
                if(StringUtils.isBlank(flowCode)){
                    flowCode=object.getFlowCode();
                }
                filterMap.put("flowCode", flowCode);
            }else{
                filterMap.put("flowCode", flowCode);
            }
            if(version==null){
                String version_s=(String)request.getParameter("s_version");
                if(StringUtils.isBlank(version_s)){
                    version=object.getVersion();
                }else{
            version=Long.parseLong((String)request.getParameter("s_version"));
                }
                filterMap.put("version", version+"");
            }else{
                filterMap.put("version", version+"");
            }
            request.setAttribute("flowCode", flowCode);
            request.setAttribute("version", version);
            
            PageDesc pageDesc = makePageDesc();
            objList = generalModuleParamMag.listModeCode(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();

            this.pageDesc = pageDesc;
            
          
            return "listm";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
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
