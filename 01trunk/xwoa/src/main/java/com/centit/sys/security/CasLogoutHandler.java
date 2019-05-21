package com.centit.sys.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.centit.bbs.util.IPUtil;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.AccessLogManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.SysParametersUtils;

/**
  * 单点登出处理 
  * 参考{@link org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler}
  */
public class CasLogoutHandler implements LogoutHandler {
    private boolean invalidateHttpSession = true;

    //~ Methods ========================================================================================================

    /**
     * Requires the request to be passed in.
     *
     * @param request        from which to obtain a HTTP session (cannot be null)
     * @param response       not used (can be <code>null</code>)
     * @param authentication not used (can be <code>null</code>)
     */
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext());//获取spring的context 
        AccessLogManager  acLogMgr = (AccessLogManager) wac.getBean("accessLogManager");
         if(authentication!=null){
            Object o= authentication.getPrincipal();
            FUserDetail userInfo = (FUserDetail)o;
            //插入登出日志
            FAccessLog accessLog = new FAccessLog();
            accessLog.setId(acLogMgr.getNextLogId());
            accessLog.setAccesstime(new Date());
            accessLog.setUsercode(userInfo.getUsercode());
            accessLog.setIp(IPUtil.getIRealIPAddr(request));
            accessLog.setAccesstype(FAccessLog.ACCESS_LOGOUTING);
            acLogMgr.saveObject(accessLog);
            
            if(Boolean.valueOf((SysParametersUtils.getParameters("modifyUserStateAfterLogout", "true")))){
                //修改登录状态
                SysUserManager sysUserMgr = (SysUserManager)wac.getBean("sysUserManager");
                FUserinfo fuserInfo = sysUserMgr.getObjectById(userInfo.getUsercode());
                fuserInfo.setUserState("0");
                sysUserMgr.saveObject(fuserInfo);
            }
             }
            Assert.notNull(request, "HttpServletRequest required");
            if (invalidateHttpSession) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
        }

        SecurityContextHolder.clearContext();
    }

    public boolean isInvalidateHttpSession() {
        return invalidateHttpSession;
    }

    /**
     * Causes the {@link HttpSession} to be invalidated when this {@link LogoutHandler} is invoked. Defaults to true.
     *
     * @param invalidateHttpSession true if you wish the session to be invalidated (default) or false if it should
     * not be.
     */
    public void setInvalidateHttpSession(boolean invalidateHttpSession) {
        this.invalidateHttpSession = invalidateHttpSession;
    }

}
