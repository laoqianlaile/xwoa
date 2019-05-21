package com.centit.powerruntime.action;

import java.util.Map;

import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.VOptApplyTaskList;
import com.centit.powerruntime.service.VOptApplyTaskManager;
import com.centit.sys.security.FUserDetail;

public class VOptApplyTaskAction extends
        BaseEntityExtremeAction<VOptApplyTaskList> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private VOptApplyTaskManager vOptApplyTaskManager;

    public void setVOptApplyTaskManager(
            VOptApplyTaskManager vOptApplyTaskManager) {
        this.vOptApplyTaskManager = vOptApplyTaskManager;
        setBaseEntityManager(vOptApplyTaskManager);
    }

    public String list() {
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String userCode = fuser.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("userCode", userCode);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = vOptApplyTaskManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

}
