package com.centit.sys.action;

import java.util.List;
import java.util.Map;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.Usersetting;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.FunctionManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.UserSettingManager;
import com.opensymphony.xwork2.ActionContext;

public class UserSettingAction extends BaseEntityExtremeAction<Usersetting> {
    private static final long serialVersionUID = 1L;
    private SysUserManager sysUserMgr;
    private UserSettingManager userSettingMgr;
    private FunctionManager functionMgr;
    private FUserinfo uinfo;
    private List<FOptinfo> functions;

    public FunctionManager getFunctionMgr() {
        return functionMgr;
    }

    public List<FOptinfo> getFunctions() {
        return functions;
    }


    public void setFunctions(List<FOptinfo> functions) {
        this.functions = functions;
    }


    public FUserinfo getUinfo() {
        return uinfo;
    }

    public void setUinfo(FUserinfo uinfo) {
        this.uinfo = uinfo;
    }

    public void setFunctionMgr(FunctionManager fMgr) {
        this.functionMgr = fMgr;
    }

    public void setUserSettingMgr(UserSettingManager basemgr) {
        userSettingMgr = basemgr;
        this.setBaseEntityManager(userSettingMgr);
    }

    public UserSettingManager getUserSettingMgr() {
        return userSettingMgr;
    }

    public void setSysUserMgr(SysUserManager sysuserMagr) {
        this.sysUserMgr = sysuserMagr;
    }

    public SysUserManager getSysUserMgr() {
        return sysUserMgr;
    }

    @Override
    public String edit() {

        try {
            uinfo = (FUserDetail) getLoginUser();
            if (uinfo == null)
                return ERROR;
            
            object = sysUserMgr.getUserSetting(uinfo.getUsercode());
            object.setUsercode(uinfo.getUsercode());
            
            functions = functionMgr.findMenuFuncByType("H");
            
            return EDIT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String save() {
        FUserDetail ud = (FUserDetail) getLoginUser();
        object.setUsercode(ud.getUsercode());
        
        Usersetting userSet = userSettingMgr.getObject(object);
        
        if (null != userSet) {
            userSet.copyNotNullProperty(object);
            object = userSet;
        }
        
        userSettingMgr.saveObject(object);
        functions = functionMgr.findMenuFuncByType("H");
        ud.getUserSetting().copyNotNullProperty(object);
        
        Map<String, Object> session = ActionContext.getContext().getSession();
        String stylePath = request.getContextPath() + "/styles/"
                + object.getPagestyle();
        session.put("STYLE_PATH", stylePath);
        session.put("LAYOUT", object.getFramelayout());
        session.put("saved", true);
        savedMessage();
        
        return EDIT;
    }

    public String find() {
        
        uinfo = (FUserDetail) getLoginUser();
        functions = functionMgr.findMenuFuncByType("H");
        
        if (uinfo == null)
            return ERROR;
        
        return "list";
    }

}
