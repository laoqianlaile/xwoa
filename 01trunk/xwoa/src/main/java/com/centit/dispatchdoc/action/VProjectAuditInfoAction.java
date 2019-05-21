package com.centit.dispatchdoc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.LabelValueBean;
import com.centit.dispatchdoc.po.VProjectAuditInfo;
import com.centit.dispatchdoc.service.VProjectAuditInfoManager;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.service.CodeRepositoryUtil;
import com.ibm.icu.text.SimpleDateFormat;

public class VProjectAuditInfoAction extends
        BaseEntityExtremeAction<VProjectAuditInfo> {
    private static final long serialVersionUID = 1L;
    private VProjectAuditInfoManager VProjectAuditInfoMag;
    private VsuppowerinusingManager powerMgr;
    
    @SuppressWarnings("unchecked")
    public String list(){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        pageDesc = makePageDesc();
        
        objList = VProjectAuditInfoMag.listObjects(filterMap, pageDesc);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0; i<objList.size(); i++) {
            VProjectAuditInfo obj = objList.get(i);
            FDatadictionary data = CodeRepositoryUtil.getDataPiece("INDUSTRY_FIELD", obj.getEiaApprovalType());
            obj.setEiaApprovalType(null == data ? "" : data.getDatavalue());
            data = CodeRepositoryUtil.getDataPiece("PROJECT_TYPE", obj.getProjectType());
            obj.setProjectType(null == data ? "" : data.getDatavalue());
            obj.setSolvetimeStr(null == obj.getSolvetime() ? "" : df.format(obj.getSolvetime()));
        }
        
        totalRows = pageDesc.getTotalRows();
        
        // 权利类型
        String hql = " from Vsuppowerinusing where itemType in ('XK','XF') order by cid.itemId asc";
        List<Vsuppowerinusing> powerList = powerMgr.listObjects(hql, new HashMap<String, Object>());
        
        List<LabelValueBean> lvbList = new ArrayList<LabelValueBean>();
        for (int i=0; i<powerList.size(); i++) {
            Vsuppowerinusing power = powerList.get(i);
            lvbList.add(new LabelValueBean(power.getItemName(), power.getItemId()));
        }
        request.setAttribute("powerList", lvbList);
        
        return LIST;
    }
    
    public String listResult(){
        this.list();
        
        return "listResult";
    }
    
    
    public void setVProjectAuditInfoManager(VProjectAuditInfoManager basemgr) {
        VProjectAuditInfoMag = basemgr;
        this.setBaseEntityManager(VProjectAuditInfoMag);
    }
    
    public void setVsuppowerinusingManager(
            VsuppowerinusingManager vsuppowerinusingManager) {
        this.powerMgr = vsuppowerinusingManager;
    }
}
