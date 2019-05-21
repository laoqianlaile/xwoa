package com.centit.sys.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.centit.bbs.util.IPUtil;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.AccessLogManager;
import com.centit.sys.service.SysUserManager;
import com.centit.webservice.util.Encodes;

/**
 * 
 * 身份鉴定成功后，根据情况做出不同的跳转
 * 
 * @author lay
 * @create 2016年1月21日
 * @version
 */
public class SimpleUrlAuthenticationSuccessHandler implements  AuthenticationSuccessHandler{
    private Logger logger = Logger.getLogger(getClass());
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); 
    private  AccessLogManager acLogMgr;
    private  SysUserManager sysUserMgr;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        try{
            String loginName = new String(request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).getBytes("ISO-8859-1"),"UTF-8");
            String password=request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);
            addCookie(response, "loginName",  Encodes.encodeBase64(loginName), 2*365*24*60*60);
            addCookie(response, "userPwd",  password, 2*365*24*60*60);
        }catch(Exception e){}
        handle(request, response, authentication);  
        clearAuthenticationAttributes(request); 
    }
    
    protected void handle(HttpServletRequest request,   
            HttpServletResponse response, Authentication authentication) throws IOException {  
           /**
            * 屏保ajax过来的请求
            */
           if(isAjaxRequest(request)){
               makeUserOnline(request,authentication,false);
               addCookie(response, "__lastAccessTime", String.valueOf((new Date()).getTime()), 12*60*60);
               response.getWriter().print(true);
           }else{
               makeUserOnline(request,authentication,true);
               String targetUrl = determineTargetUrl(request,response,authentication);  
               
               //这个要加，防止已经将response的缓冲区里的数据发送到客户端，后面又将重定向可能造成io异常
               if (response.isCommitted()) {  
                   logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);  
                   return;  
               }  
          
               redirectStrategy.sendRedirect(request, response, targetUrl);   
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
     * 
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    private void addCookie(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        if(maxAge>0)  cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    /**
     * 是否是ajax请求
     * @param request
     * @return
     */
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
    protected String determineTargetUrl(HttpServletRequest request,HttpServletResponse response,Authentication authentication) {  
        String loginPosition = request.getParameter("__loginPosition");
        String targetUrl = "";
        //如果是主入口，就是正常的登录页面
        if(StringUtils.isEmpty(loginPosition)){
            targetUrl="/page/frame/loginTransition.jsp?msg=openTab";
        }
        //如果是从门户网站登录，我们需要中转一下
        else if("portal".equals(loginPosition)){
            targetUrl="/page/frame/loginPanel.jsp?msg=openTab";
        }
        
        FUserDetail userDetail = (FUserDetail) authentication.getPrincipal();
        /*if(!"F".equals(userDetail.getIsDefaultPwd())){
            targetUrl="/page/sys/forceModifyPassword.jsp";
        }*/
       return targetUrl;
    }  
    
   
    
    protected void clearAuthenticationAttributes(HttpServletRequest request) {  
        HttpSession session = request.getSession(false);  
        if (session == null) {  
            return;  
        }  
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);  
    }  
    
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {  
        this.redirectStrategy = redirectStrategy;  
    }  
    protected RedirectStrategy getRedirectStrategy() {  
        return redirectStrategy;  
    }

    public void setAcLogMgr(AccessLogManager acLogMgr) {
        this.acLogMgr = acLogMgr;
    }

    public void setSysUserMgr(SysUserManager sysUserMgr) {
        this.sysUserMgr = sysUserMgr;
    }  
    
    

}
