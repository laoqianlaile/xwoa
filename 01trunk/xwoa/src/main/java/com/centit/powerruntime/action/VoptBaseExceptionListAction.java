package com.centit.powerruntime.action;

import java.util.Map;

import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.action.OACommonBizAction;
import com.centit.powerruntime.po.VoptBaseExceptionList;
import com.centit.powerruntime.service.VoptBaseExceptionListManager;

public class VoptBaseExceptionListAction extends OACommonBizAction<VoptBaseExceptionList> {
    private static final long serialVersionUID = 1L;
    private VoptBaseExceptionListManager voptBaseExceptionListManager;
    public void setVoptBaseExceptionListManager(
            VoptBaseExceptionListManager voptBaseExceptionListManager) {
        this.voptBaseExceptionListManager = voptBaseExceptionListManager;
    }
    @SuppressWarnings("unchecked")
    public String list(){
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList=voptBaseExceptionListManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            return "list";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
}
