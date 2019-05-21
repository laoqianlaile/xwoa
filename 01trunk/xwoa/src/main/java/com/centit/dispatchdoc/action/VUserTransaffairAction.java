package com.centit.dispatchdoc.action;

import java.util.Map;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.dispatchdoc.po.VUserTransaffair;
import com.centit.dispatchdoc.service.VUserTransaffairManager;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUnitManager;

public class VUserTransaffairAction extends BaseEntityExtremeAction<VUserTransaffair> {
    private static final long serialVersionUID = 1L;
    private VUserTransaffairManager VUserTransaffairMag;
    private SysUnitManager sysUnitManager;
    private String unitsJson;

    @SuppressWarnings("unchecked")
    public String list(){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        if (filterMap.containsKey("subItemTypenogw")) {
            if ("T".equalsIgnoreCase(String.valueOf(filterMap.get("subItemTypenogw")))) {
                filterMap.put("IN_subItemType", "FX,XK,CS");
            } else if ("F".equalsIgnoreCase(String.valueOf(filterMap.get("subItemTypenogw")))) {
                filterMap.put("IN_subItemType", "GW");
            }
        }
        
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        filterMap.put("userCode", loginUser.getUsercode());
        filterMap.put("IN_bizstate", filterMap.get("bizstate"));
        pageDesc = makePageDesc();
        objList = VUserTransaffairMag.listUserTransaffair(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        unitsJson = sysUnitManager.getAllUnitsJSON();
        
        request.setAttribute("s_bizstate", request.getParameter("s_bizstate"));
        request.setAttribute("s_subItemTypenogw", request.getParameter("s_subItemTypenogw"));

        return LIST;
    }
    
    @SuppressWarnings("unchecked")
    public String hbList(){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        if (filterMap.containsKey("subItemTypenogw")) {
            if ("T".equalsIgnoreCase(String.valueOf(filterMap.get("subItemTypenogw")))) {
                filterMap.put("IN_subItemType", "FX,XK,CS");
            } else if ("F".equalsIgnoreCase(String.valueOf(filterMap.get("subItemTypenogw")))) {
                filterMap.put("IN_subItemType", "GW");
            }
        }
        
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        filterMap.put("userCode", loginUser.getUsercode());
        filterMap.put("IN_bizstate", filterMap.get("bizstate"));
        pageDesc = makePageDesc();
        objList = VUserTransaffairMag.listUserTransaffair(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        unitsJson = sysUnitManager.getAllUnitsJSON();
        
        request.setAttribute("s_bizstate", request.getParameter("s_bizstate"));
        request.setAttribute("s_subItemTypenogw", request.getParameter("s_subItemTypenogw"));

        return "hbList";
    }
    
    
    @SuppressWarnings("unchecked")
    public String listFwh(){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        filterMap.put("userCode", loginUser.getUsercode());
        filterMap.put("NP_fwhNotNull", true);
        pageDesc = makePageDesc();
        objList = VUserTransaffairMag.listUserTransaffair(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        unitsJson = sysUnitManager.getAllUnitsJSON();

        return "listFwh";
    }
    
    
    
    ///////////////////////////////////////////////////////////////
    
    public void setVUserTransaffairManager(VUserTransaffairManager basemgr) {
        VUserTransaffairMag = basemgr;
        this.setBaseEntityManager(VUserTransaffairMag);
    }

    public SysUnitManager getSysUnitManager() {
        return sysUnitManager;
    }


    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }


    public String getUnitsJson() {
        return unitsJson;
    }


    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }
}
