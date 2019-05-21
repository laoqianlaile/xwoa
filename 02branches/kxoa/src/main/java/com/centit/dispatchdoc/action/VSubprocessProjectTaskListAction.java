package com.centit.dispatchdoc.action;

import java.util.Map;

import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VSubprocessProjectTaskList;
import com.centit.dispatchdoc.service.VSubprocessProjectTaskListManager;
import com.centit.sys.security.FUserDetail;
import com.centit.workflow.sample.optmodel.BaseWFEntityAction;
	

public class VSubprocessProjectTaskListAction  extends BaseWFEntityAction<VSubprocessProjectTaskList>  {
	
	private static final long serialVersionUID = 1L;
	private VSubprocessProjectTaskListManager VSubprocessProjectTaskListManager;
	public void setVSubprocessProjectTaskListManager(VSubprocessProjectTaskListManager basemgr)
	{
	    VSubprocessProjectTaskListManager = basemgr;
		this.setBaseEntityManager(VSubprocessProjectTaskListManager);
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
            objList = VSubprocessProjectTaskListManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
		
	public String delete() {
	    super.delete();      
	    
	    return "delete";
	}
}
