package com.centit.oa.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaScheduleResponse;
import com.centit.oa.service.OaScheduleResponseManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;

public class OaScheduleResponseAction extends
BaseEntityDwzAction<OaScheduleResponse> {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory
            .getLog(OaScheduleResponseAction.class);
    private static final long serialVersionUID = 1L;
    private OaScheduleResponseManager oaScheduleResponseMag;
    
    private String viewtype; // 区别查看页面是否有返回按钮

    public void setOaScheduleResponseManager(OaScheduleResponseManager basemgr) {
        oaScheduleResponseMag = basemgr;
        this.setBaseEntityManager(oaScheduleResponseMag);
    }

    /**
     * 列表数据
     */
    public String list() {
        try {
       
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
           // FUserDetail fuser = ((FUserDetail) getLoginUser());

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaScheduleResponseMag.listObjects(filterMap,pageDesc);
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String save() {
        try {
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
                object.setUsercode(user.getUsercode());
                object.setCreatedate(DatetimeOpt.currentUtilDate());
         
            super.save();
          
            return VIEW; 
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {

        super.edit();
       
       
        return EDIT;
    }
    
    /**
     * 编辑，自己响应
     */
    public String ownResEdit() {
        String schno=object.getSchno();
        FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
        object.setUsercode(user.getUsercode());
        object=oaScheduleResponseMag.getOwnRes(object);
        object.setSchno(schno);
        return EDIT;
    }
    /**
     * 编辑，查看自己响应
     */
    public String ownResView() {
        FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
        object.setUsercode(user.getUsercode());
        object=oaScheduleResponseMag.getOwnRes(object);
        return VIEW;
    }
    
    public String getViewtype() {
        return viewtype;
    }

    public void setViewtype(String viewtype) {
        this.viewtype = viewtype;
    }
}
