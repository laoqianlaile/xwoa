package com.centit.sys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 
 * 登录失败跳转,注意这里全部是重定向，不转发，原来内置的转发和重定向可选择
 * 登录异常保存在session里，注意这里是http的session，不是权限框架的session，
 * 权限框架的session可能是用户的唯一标识，或者其他，登录成功后才创建
 * 
 * @author lay
 * @create 2016年1月21日
 * @version
 */
public class SimpleUrlAuthenticationFailureHandler implements  AuthenticationFailureHandler{
    protected final Log logger = LogFactory.getLog(getClass());
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    public SimpleUrlAuthenticationFailureHandler() {
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        if(isAjaxRequest(request)){
            response.getWriter().print(false);
        }else{
            String defaultFailureUrl = determineTargetUrl(request);
            if (defaultFailureUrl == null || "".equals(defaultFailureUrl)) {
                logger.debug("No failure URL set, sending 401 Unauthorized error");

                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
            } else {
                saveException(request, exception);

                logger.debug("Redirecting to " + defaultFailureUrl);
                redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
            }
        }
        
    }
    
    protected boolean isAjaxRequest(HttpServletRequest request){
        String requestType = request.getHeader("X-Requested-With");  
        return requestType!=null && "XMLHttpRequest".equals(requestType);
    }
    /**
     * 决定跳转页面,根据自己的逻辑自行编写
     * 也可以在xml里配置跳转路径，然后自己解析，可参照权限路径配置
     * @param request
     * @param authentication
     * @return
     */
    protected   String determineTargetUrl(HttpServletRequest request){
        String loginPosition = request.getParameter("__loginPosition");
        String targetUrl = "";
        //如果是主入口，就是正常的登录页面,失败返回
        if(StringUtils.isEmpty(loginPosition)){
            targetUrl="/sys/mainFrame!loginWithRanKey.do?error=true";
        }
        //如果是从门户网站登录
        else if("portal".equals(loginPosition)){
            targetUrl="/page/frame/loginPanel.jsp?msg=loginFail";
        }
        return targetUrl;
    }
    
    protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
    }
    
 

}
