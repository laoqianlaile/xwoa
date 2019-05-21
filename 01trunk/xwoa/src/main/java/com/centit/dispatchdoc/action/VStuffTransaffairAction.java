package com.centit.dispatchdoc.action;

import java.util.HashMap;
import java.util.Map;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.dispatchdoc.po.VStuffTransaffair;
import com.centit.dispatchdoc.service.VStuffTransaffairManager;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.sys.service.SysUnitManager;

public class VStuffTransaffairAction extends BaseEntityExtremeAction<VStuffTransaffair> {
    private static final long serialVersionUID = 1L;
    private VStuffTransaffairManager VStuffTransaffairMag;
    private OptBaseInfoManager optBaseInfoManager;
    private VsuppowerinusingManager powerMgr;
    private SysUnitManager sysUnitManager;
    private String unitsJson;

    
    /**
     * 未办结的办件的材料信息管理（页面的传递使用djId）
     * @return
     */
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
        
        pageDesc = makePageDesc();
        objList = VStuffTransaffairMag.listStuffTransaffair(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        unitsJson = sysUnitManager.getAllUnitsJSON();
        
        return LIST;
    }
    
    
    /**
     * 材料管理
     * @return
     */
    public String edit() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getObjectById(object.getDjId());
        
        // 根据权力编号获取权力类型
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("equalItemId", optBaseInfo.getPowerid());
        
        request.setAttribute("groupid", powerMgr.listObjects(paramMap).get(0).getGroup_id());
        
        return EDIT;
    }
    
    ///////////////////////////////////////////////////////////////
    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public void setVStuffTransaffairManager(VStuffTransaffairManager basemgr) {
        VStuffTransaffairMag = basemgr;
        this.setBaseEntityManager(VStuffTransaffairMag);
    }
    
    public void setVsuppowerinusingManager(
            VsuppowerinusingManager vsuppowerinusingManager) {
        this.powerMgr = vsuppowerinusingManager;
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
