package com.centit.sys.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.centit.bbs.util.IPUtil;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.AccessLogManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.SysParametersUtils;

/**
 * 
 * 定义spring security退出事件
 * 
 * @author lay
 * @create 2015年12月9日
 * @version
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{
    
    public LogoutSuccessHandler(String defaultTargetURL) {
         this.setDefaultTargetUrl(defaultTargetURL);
    }
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
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
        
        //销毁session
       SessionRegistry sessionRegistry = (SessionRegistry) wac.getBean("sessionRegistry");
       List<SessionInformation> sessionList = sessionRegistry.getAllSessions(authentication.getPrincipal(), true);
       for(SessionInformation session : sessionList){
           sessionRegistry.removeSessionInformation(session.getSessionId());
       }
        super.onLogoutSuccess(request, response, authentication);
       }
    }
}
