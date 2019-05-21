package com.centit.dispatchdoc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.ApplyUnitInfo;
import com.centit.dispatchdoc.service.ApplyUnitInfoManager;

public class ApplyUnitInfoAction extends IODocCommonBizAction<ApplyUnitInfo> {
    private static final long serialVersionUID = 1L;
    private ApplyUnitInfoManager applyUnitInfoMag;
    
    public String frameId;
    public String selectType;
    public List<ApplyUnitInfo> unitList = new ArrayList<ApplyUnitInfo>();
    

    @SuppressWarnings("unchecked")
    public String selectList() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            
            filterMap.put(CodeBook.SELF_ORDER_BY, " userorder asc");
            
            PageDesc pageDesc = makePageDesc();
            
            unitList = applyUnitInfoMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            
            request.setAttribute("JDialogLink", request.getParameter("JDialogLink"));
            
            this.pageDesc = pageDesc;
            return "selectList";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    
    
    
    
    
    /**********************************/
    public void setApplyUnitInfoManager(ApplyUnitInfoManager basemgr) {
        applyUnitInfoMag = basemgr;
        this.setBaseEntityManager(applyUnitInfoMag);
    }

    public List<ApplyUnitInfo> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<ApplyUnitInfo> unitList) {
        this.unitList = unitList;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }
    
    
}
