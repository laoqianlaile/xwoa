package com.centit.bbs.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.po.VBbsThemeReplay;
import com.centit.bbs.po.VBbsThemeUser;
import com.centit.bbs.service.VBbsThemeUserManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FUserinfo;




public class VBbsThemeUserAction extends BaseEntityDwzAction<VBbsThemeUser> {
    private static final Log log = LogFactory.getLog(VBbsThemeUserAction.class);
    private static final long serialVersionUID = 1L;
    private VBbsThemeUserManager vBbsThemeUserMag;

    public void setVBbsThemeUserManager(VBbsThemeUserManager basemgr) {
        vBbsThemeUserMag = basemgr;
        this.setBaseEntityManager(vBbsThemeUserMag);
    }

    private String getLoginUserCode() {

        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    public String list(){
        
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        // 列出未删除的帖子
        if(null == filterMap.get("includeDel")){
            filterMap.put("notDeleted", "D"); 
        }
        
        filterMap.put("manager", getLoginUserCode());
        PageDesc pageDesc = makePageDesc();
        
        objList = vBbsThemeUserMag.listObjects(filterMap, pageDesc);
        
        totalRows = pageDesc.getTotalRows();
        this.pageDesc = pageDesc;

        setbackSearchColumn(filterMap);
        return LIST;
    }
    public String themeReplayList(){
        
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        // 列出未删除的回复
        if(null == filterMap.get("includeDel")){
            filterMap.put("notDeleted", "D"); 
        }
        
        filterMap.put("approvals", getLoginUserCode());
        PageDesc pageDesc = makePageDesc();
        
        List<VBbsThemeReplay> themeReplayList= vBbsThemeUserMag.getThemeReplyList(filterMap, pageDesc);
        
        totalRows = pageDesc.getTotalRows();
        this.pageDesc = pageDesc;

        setbackSearchColumn(filterMap);
        request.setAttribute("themeReplayList", themeReplayList);
        return "oaBbsThemeReplayList";
    }
}