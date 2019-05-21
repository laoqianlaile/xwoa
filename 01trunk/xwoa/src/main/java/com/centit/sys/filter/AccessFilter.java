package com.centit.sys.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.centit.bbs.util.IPUtil;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.AccessLogManager;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;

/**
 * 
 * 访问控制过滤器，记录访问日志、拦截登录、退出请求
 * 
 * @author lay
 * @create 2015年12月9日
 * @version
 */
public class AccessFilter implements Filter{
   private FilterConfig config;
   private final String [] ignoreUrls = new String[]{"!loginWithRanKey.do","!logincas.do","!captchaimage.do","!logincas.do","!loginSuccess.do","/anonymous"};
   private  AccessLogManager acLogMgr;
   private  SysUserManager sysUserMgr;
   private SessionRegistry sessionRegistry;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());//获取spring的context 
        acLogMgr = (AccessLogManager) wac.getBean("accessLogManager");
        sysUserMgr = (SysUserManager)wac.getBean("sysUserManager");
        sessionRegistry = (SessionRegistry) wac.getBean("sessionRegistry");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq =(HttpServletRequest)request;
        HttpServletResponse httpResp = (HttpServletResponse)response;
        String reqUrl = httpReq.getServletPath();
        //访问类型，主要用来判断是否是页面定时任务自动触发的请求
        String acType = httpReq.getParameter("acType");
        
        //判断当前请求路径是否需要处理
        boolean needOper = true;
        for(String url:ignoreUrls){
            if(reqUrl.indexOf(url) > -1){
                needOper = false;
                break;
            }
        }
        //如果是页面定时任务自动发动的请求，也不处理
        if(!StringUtils.isEmpty(acType) && "auto".equalsIgnoreCase(acType)){
            needOper = false;
        }
       
        if(needOper){
            SecurityContext scontext = SecurityContextHolder.getContext();
            Authentication auth = scontext.getAuthentication();
          
            Object o = null;
            //如果无法获取用户登录身份信息，直接让他过,不进行后处理
            if(auth == null ){
                chain.doFilter(request, response);
                return;
            }else{
               o = auth.getPrincipal();
               if(o==null || !(o instanceof UserDetails)){
                   chain.doFilter(request, response);
                   return;
               }
               
            }
           
            FUserDetail user = (FUserDetail)o;
            FUserinfo fuserInfo = sysUserMgr.getObjectById(user.getUsercode());
            
           //如果是日常操作请求
            boolean canNotGoNext = filterInvalidSession(httpReq,httpResp, fuserInfo);
         
            if(canNotGoNext){
                    return;
             }
            //判断是否有权限访问
            if(!matchPrivilegeAccessUrl(user,reqUrl)){
                    writeIllegalAccessLog(httpReq, user);
                    //跳转到提示页面
                    //TODO
             }
             //记录访问操作级别日志
             writeAccessLogOperLevel(httpReq,httpResp, user);
        }
        
        chain.doFilter(request, response);
    }
   
    /**
     * 过滤掉逻辑意义上失效的session，保持与数据库用户状态同步
     * @param httpReq
     * @param fuserInfo
     * @return true 过滤了session, false 没有过滤
     * @throws IOException 
     */
    private boolean filterInvalidSession(HttpServletRequest httpReq,HttpServletResponse httpResp,FUserinfo fuserInfo){
        //如果用户在登录后一段时间没有操作，而系统定时任务已经修改了用户的状态，改为了失效，我们需要将用户的当前session全部失效掉
        if(StringUtils.isEmpty(fuserInfo.getUserState())||"0".equals(fuserInfo.getUserState())){
            //注意，这里只把web容器的session失效是没有用的，spring security的用户身份仍然存在，不过他们两者的SessionId相同
            HttpSession session = httpReq.getSession(false);
            String sessionId = session.getId();
            session.invalidate();
            sessionRegistry.removeSessionInformation(sessionId);
            try {
                String contextPath = httpReq.getScheme()+"://"+httpReq.getServerName() +":"+httpReq.getServerPort()+httpReq.getContextPath();
                httpResp.reset();
                httpResp.setHeader("content-type","text/html;charset=UTF-8");
              if("T".equals(CodeRepositoryUtil.getItemState("SYSPARAM", "CAS"))){//如果是单点登录
                  httpResp.getWriter().print("<script>"
                          + "window.location.href='"+SysParametersUtils.getParameters("cas.home", "")+"/login';</script>");
              }else{
                  httpResp.getWriter().print("<script>"
                          + "window.location.href='"+contextPath+"/page/frame/loginTransition.jsp';</script>");
              }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
    
    /**
     * 匹配访问路径的权限
     * @param url
     * @return
     */
    private boolean matchPrivilegeAccessUrl(FUserDetail user,String url){
       //TODO
        return false;
    }
    /**
     * 记录非法访问的日志
     * @param httpReq
     * @param user
     */
    private void writeIllegalAccessLog(HttpServletRequest httpReq,FUserDetail user){
      //TODO
    }
    /**
     * 记录操作的日志
     * @param httpReq
     * @param user
     */
    private void writeAccessLogOperLevel(HttpServletRequest httpReq,HttpServletResponse httpResp,FUserDetail user){
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("usercode", user.getUsercode());
        filterMap.put("accesstype", FAccessLog.ACCESS_OPERING);
        String currDate = DateUtil.getCurrentDate();
        filterMap.put("beginDate", currDate+" 00:00:00");
        filterMap.put("endDate", currDate+ " 23:59:59");
        
        List<FAccessLog> accessLogs = acLogMgr.listObjects(filterMap);
        
        Date accessTime = new Date();//提前当前访问时间，让入库时间和存session里的时间一致
        
        //判断今天有没有操作记录，如果有则跟新操作时间，如果没有则添加操作记录
        if(accessLogs == null || accessLogs.isEmpty()){
            FAccessLog accessLog = buitObj(httpReq, user,FAccessLog.ACCESS_OPERING);
            accessLog.setAccesstime(accessTime);
          //保存
            acLogMgr.saveObject(accessLog);
        }else{
            FAccessLog accessLog = accessLogs.get(0);
            accessLog.setAccesstime(accessTime);
            accessLog.setIp(IPUtil.getIRealIPAddr(httpReq));
            acLogMgr.saveObject(accessLog);
        }
        //缓存最后touch的时间
        cacheLastAccessTime(httpReq,httpResp,accessTime);
    }
    
    /**
     * 缓存最后访问时间到session
     * @param httpReq
     * @param accessTime
     */
    private void cacheLastAccessTime(HttpServletRequest httpReq,HttpServletResponse httpResp,Date accessTime){
        addCookie(httpResp, "__lastAccessTime", String.valueOf(accessTime.getTime()), 12*60*60);
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
     * 构建访问日志瞬时对象
     * @param httpReq
     * @param userInfo
     * @param type 1-登录 3-操作
     * @return
     */
    private FAccessLog buitObj(HttpServletRequest httpReq,FUserDetail userInfo,String type){
        FAccessLog accessLog = new FAccessLog();
        accessLog.setId(acLogMgr.getNextLogId());
        accessLog.setAccesstime(new Date());
        accessLog.setUsercode(userInfo.getUsercode());
        accessLog.setIp(IPUtil.getIRealIPAddr(httpReq));
        accessLog.setAccesstype(type);
        return accessLog;
    }
    @Override
    public void destroy() {
        config = null;
    }

}
