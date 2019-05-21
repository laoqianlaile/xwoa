package com.centit.sys.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.centit.bbs.util.IPUtil;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.AccessLogManager;
import com.centit.sys.service.SysUserManager;

/**
 * 
 * cas认证成功后一些处理，为了避免改动原来单机登陆后处理功能，重新创建一个类
 * 参考类{#org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler}
 * 我们需要把上次拦截的请求给记录下来，重新认证成功后重新转向到上次请求
 * @author Ghost
 * @create 2017年2月22日
 * @version
 */
public class CasAuthenticationSuccessHandler extends org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler{
    private  AccessLogManager acLogMgr;
    private  SysUserManager sysUserMgr;
    
    private RequestCache requestCache = new HttpSessionRequestCache();
    
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        makeUserOnline(request,authentication,true);//记录用户状态
        
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        if (isAlwaysUseDefaultTargetUrl() || StringUtils.hasText(request.getParameter(getTargetUrlParameter()))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
    
    /**
     * 用户上线
     * @param authentication
     */
    protected void makeUserOnline(HttpServletRequest httpReq ,Authentication authentication,boolean needlog){
        if(authentication!=null && authentication.getPrincipal()!=null && (authentication.getPrincipal() instanceof UserDetails)){
            //修改用户在线状态
            FUserinfo fuserInfo = sysUserMgr.getObjectById(((FUserDetail)authentication.getPrincipal()).getUsercode());
            if(fuserInfo!=null){
                fuserInfo.setUserState("1");
                sysUserMgr.saveObject(fuserInfo);
                ((FUserDetail)authentication.getPrincipal()).setUserState("1");
                ((FUserDetail)authentication.getPrincipal()).setLoginip(IPUtil.getIRealIPAddr(httpReq));
                
                if(needlog){
                    //登录成功记录操作日志
                    FAccessLog accessLog = buitObj(httpReq, fuserInfo,FAccessLog.ACCESS_LOGINING);
                    acLogMgr.saveObject(accessLog);
                }
            }
        }
    }
    
    /**
     * 构建访问日志瞬时对象
     * @param httpReq
     * @param userInfo
     * @param type 1-登录
     * @return
     */
    private FAccessLog buitObj(HttpServletRequest httpReq,FUserinfo userInfo,String type){
        FAccessLog accessLog = new FAccessLog();
        accessLog.setId(acLogMgr.getNextLogId());
        accessLog.setAccesstime(new Date());
        accessLog.setUsercode(userInfo.getUsercode());
        accessLog.setIp(IPUtil.getIRealIPAddr(httpReq));
        accessLog.setAccesstype(type);
        return accessLog;
    }

    public void setAcLogMgr(AccessLogManager acLogMgr) {
        this.acLogMgr = acLogMgr;
    }

    public void setSysUserMgr(SysUserManager sysUserMgr) {
        this.sysUserMgr = sysUserMgr;
    }
    
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
    
}
