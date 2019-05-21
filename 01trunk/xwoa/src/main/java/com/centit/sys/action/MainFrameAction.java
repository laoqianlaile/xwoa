package com.centit.sys.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import rtx.RTXSvrApi;
import rtx.SessionKeylogin;
import rtx.Signauth;

import com.centit.core.action.BaseAction;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.CentitMenu;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.Usersetting;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.AccessLogManager;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.FunctionManager;
import com.centit.sys.service.SysUserManager;
import com.opensymphony.xwork2.ActionContext;

public class MainFrameAction extends BaseAction{
    private static final long serialVersionUID = 1L;
    private FunctionManager functionMgr;
    private String userFirstPage;
    private SysUserManager sysUserManager;
    private String superFunctionId;
    private AccessLogManager accessLogManager;
    private OaUserinfoManager oaUserinfoMag;
    
    private boolean flage=false;//标记是否调用rtx 或者其他

    private List<CentitMenu> menus;

    public List<CentitMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<CentitMenu> menus) {
        this.menus = menus;
    }

    private String casHome;
    
    private String url;//跳转url

    public String getCasHome() {
        return casHome;
    }

    public void setCasHome(String casHome) {
        this.casHome = casHome;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    // private String superFunctionId;
    public String getUserFirstPage() {
        return userFirstPage;
    }

    public void setUserFirstPage(String userFirstPage) {
        this.userFirstPage = userFirstPage;
    }

    public void setFunctionMgr(FunctionManager functionMgr) {
        this.functionMgr = functionMgr;
    }

    private CodeRepositoryManager codeRepositoryManager;

    public void setCodeRepositoryManager(
            CodeRepositoryManager codeRepositoryManager) {
        this.codeRepositoryManager = codeRepositoryManager;
    }

    /**
     * JSON方式获取菜单
     * 
     * @return
     */
    public String getJSONMenus() {
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        FUserinfo user = new FUserinfo();

        List<FOptinfo> optInfos = new ArrayList<FOptinfo>();

        if (uinfo != null) {
            user.setUsercode(uinfo.getUsercode());

            if (StringUtils.isBlank(superFunctionId)) {
                optInfos = this.functionMgr.getMenuFuncByUser(user);
            } else {
                optInfos = this.functionMgr
                        .getMenuFuncByUserIDAndSuperFunctionId(user,
                                superFunctionId);
            }
        }

        menus = parseOptinfo2Menu(optInfos, "0");

        return "menus";
    }

    private List<CentitMenu> parseOptinfo2Menu(List<FOptinfo> optInfos, String topId) {
        
        Map<String, CentitMenu> allMenus = new HashMap<String, CentitMenu>();
        List<CentitMenu> centitMenus = new ArrayList<CentitMenu>();
        
        // 遍历所有opt待用
        for (FOptinfo opt : optInfos) {
            allMenus.put(opt.getOptid(), new CentitMenu(opt));
            
            // preoptid为空或指定preoptid为顶层菜单
            if (null == opt.getPreoptid() || (null != topId && topId.equals(opt.getPreoptid()))) {
                centitMenus.add(allMenus.get(opt.getOptid()));
            }
        }
        
        // 确定父子关系
        for (FOptinfo opt : optInfos) {
            // 非顶级菜单
            if (null != opt.getPreoptid() && (null != topId && !topId.equals(opt.getPreoptid()))) {
                // 塞入父级节点
                if (null != allMenus.get(opt.getPreoptid())) {
                    allMenus.get(opt.getPreoptid()).getChildren().add(allMenus.get(opt.getOptid()));
                }
            }
        }
        
        return centitMenus;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String showMain() throws Exception {

        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        FUnitinfo unitInfo=CodeRepositoryManager.UNITREPO.get(uinfo.getPrimaryUnit());
        
        
        if(uinfo!=null){
        Usersetting us = uinfo.getUserSetting();

        try {
            if (StringUtils.isEmpty(us.getMainpage())) {
                FOptinfo f = (FOptinfo) functionMgr.getFunctionsByUser(uinfo)
                        .get(0);
                us.setMainpage(f.getOpturl());
            }
        } catch (Exception e) {
        }
        
        //主机构-中间层级
        if("M".equals(unitInfo.getUnittype())){
            String  mainpage="app/dashboard!showMiddleUnit.do";
            us.setMainpage(mainpage);
        }
        
        Map<String, Object> session = ActionContext.getContext().getSession();
        String stylePath = request.getContextPath() + "/styles/"
                + us.getPagestyle();
        session.put("STYLE_PATH", stylePath);
        userFirstPage = '/' + us.getMainpage();
        
        
        
        request.setAttribute("firstPage", userFirstPage);
        
        
        
        session.put("LAYOUT", us.getFramelayout());
        // 当前用户所能获取菜单
        ActionContext.getContext().put(
                "OA_MENUS",
                this.getFunctionsByUserCode(((FUserinfo) super.getLoginUser())
                        .getUsercode()));

        /**
         * 公文待办数
         */
        Map<String, Object> filterMap = new HashMap();
        filterMap.put("userCode", uinfo.getUsercode());

        request.setAttribute("gwDbnumber", "0");

        /**
         * 办件待办数
         */
        Map<String, Object> bjMap = new HashMap();
        bjMap.put("userCode", uinfo.getUsercode());

        request.setAttribute("bjDbnumber", "0");

        // 首页显示登录人员所在部门
        FUserunit dept = sysUserManager.getUserPrimaryUnit(uinfo.getUsercode());
        String sParentUnit = dept.getUnitcode();
        request.setAttribute("unitcode", sParentUnit);
        //最后一次登录时间
        String lastLoginTime = accessLogManager.fingLastLoginTime(uinfo.getUsercode());
        request.setAttribute("lastLoginTime", lastLoginTime);
        
        //获取个人扩展信息
        OaUserinfo userExtInfo = oaUserinfoMag.getObjectById(uinfo.getUsercode());
        request.setAttribute("userExtInfo", userExtInfo);
        
        if (StringUtils.isNotBlank(request.getParameter("theme"))) {
            request.setAttribute("theme", request.getParameter("theme"));
            
            return "MainPageNew";
        }
        
        return "MainPage";
        }else{
            return "login";
        }
    }
    

    /***
     * 根据rtx链接 自动登录oa系统 (反向登录)
     * 依据获取到的user sign 补充seesion
     * @return
     * @throws Exception
     */
    public String showMainByRTX() throws Exception {
        String strUser=request.getParameter("user");//loginname
        String strSign=request.getParameter("sign");
       
        if(!hasSession()){
        if(StringUtils.isNotBlank(strUser)&&StringUtils.isNotBlank(strSign)){
            boolean rtxSign=Signauth.auth(strUser, strSign);//反向登录是否成功
            //判断是否意已经登录--若已登录跳过seesion塞值部分
            if(true){//rtxSign
//                模拟登录成功 session 塞入用户信息 end 
                FUserDetail uinfo = null;
                try {
                    uinfo = sysUserManager.loadUserByUsername(strUser);
                } catch (Exception e) {
                    return "login";
                }
               if(null!=uinfo){
                   setSeeion(uinfo);
               }
             }  
            return this.showMain();
           }
       else{
            return this.loginError();  
        } 
       }
        return this.showMain();
    }

    /**
     * 通过Ajax请求获取当前菜单下所有子菜单
     * 
     * @param superFunctionId
     * @return
     */
    public String getSuperFunc() {
        String superFunctionId = this.request.getParameter("superFunctionId");
        if (!org.springframework.util.StringUtils.hasText(superFunctionId)) {
            return getMenuFunc(new ArrayList<FOptinfo>());
        }

        FUserinfo user = new FUserinfo();
        user.setUsercode(((FUserinfo) this.getLoginUser()).getUsercode());
        List<FOptinfo> menuFunsByUser = this.functionMgr
                .getMenuFuncByUserIDAndSuperFunctionId(user, superFunctionId);

        return getMenuFunc(menuFunsByUser);
    }
    
    /**
     * 锁屏
     * @throws IOException 
     */
    public void lockScreen() throws IOException{
        boolean flag = false;
        try{
            FUserinfo fuserInfo = sysUserManager.getObjectById(((FUserinfo) this.getLoginUser()).getUsercode());
            fuserInfo.setUserState("2");//2代表离开状态，1代表在使用（正在操作）
            sysUserManager.saveObject(fuserInfo);
            
            flag = true;
            
            
            HttpSession session = request.getSession(false);
            WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());//获取spring的context 
            SessionRegistry sessionRegistry = (SessionRegistry) wac.getBean("sessionRegistry");
            String sessionId = session.getId();
            session.invalidate();
            sessionRegistry.removeSessionInformation(sessionId);
        }catch(Exception e){
        }
        ServletActionContext.getResponse().getWriter().print(flag);
    }
    /**
     * 判断当前用户是否有session
     * @return
     */
    private boolean hasSession(){
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request   
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT"); 
        
        if (null != securityContextImpl   
                && null != securityContextImpl.getAuthentication()) { 
            return true;
        }
        return false;
    }
    /**
     * 模拟spring验证机制 SecurityContextImpl对象存储到session
     * @param uinfo
     */
    private void setSeeion(FUserDetail uinfo){
    //      模拟登录成功 session 塞入用户信息  
    //根据userDetails构建新的Authentication,这里使用了   
      //PreAuthenticatedAuthenticationToken当然可以用其他token,如UsernamePasswordAuthenticationToken                  
      PreAuthenticatedAuthenticationToken authentication =   
           new PreAuthenticatedAuthenticationToken(uinfo, uinfo.getPassword(),uinfo.getAuthorities());  
        
      //设置authentication中details   
      authentication.setDetails(new WebAuthenticationDetails(request));  
       
     //存放authentication到SecurityContextHolder   
      SecurityContextHolder.getContext().setAuthentication(authentication);  
       
     //在session中存放security context,方便同一个session中控制用户的其他操作   
      request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); 
    }
    
    
    
    
    private String getFunctionsByUserCode(String usercode) {
        FUserinfo user = new FUserinfo();

        List<FOptinfo> menuFunsByUser = null;
        if (StringUtils.isBlank(superFunctionId)) {
            user.setUsercode(((FUserinfo) this.getLoginUser()).getUsercode());
            menuFunsByUser = this.functionMgr.getMenuFuncByUser(user);
        } else {
            user.setUsercode(usercode);
            menuFunsByUser = this.functionMgr
                    .getMenuFuncByUserIDAndSuperFunctionId(user,
                            superFunctionId);
        }
        // 将用户所有opt列表存入缓存中
        codeRepositoryManager.setUserOptList(menuFunsByUser);
        return getMenuFunc(menuFunsByUser);
    }

    private static String getMenuFunc(List<FOptinfo> menuFunsByUser) {
        Map<String, List<Map<String, String>>> result = new HashMap<String, List<Map<String, String>>>();

        List<Map<String, String>> menuList = new ArrayList<Map<String, String>>();

        for (FOptinfo fOptinfo : menuFunsByUser) {
            Map<String, String> menu = new HashMap<String, String>();

            menu.put("MID", fOptinfo.getOptid());
            menu.put("MText", fOptinfo.getOptname());
            menu.put("ParentID", fOptinfo.getPreoptid());
            menu.put("MUrl", fOptinfo.getOpturl());
            menu.put("MType", fOptinfo.getPageType());

            menuList.add(menu);

        }
        result.put("menuList", menuList);

        return JSONObject.fromObject(result).toString();
    }

    public String login() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (request.getParameter("inDialog") != null) {
            session.put("loginInDialog", "true");
            return "loginInDialog";
        } else {
            session.put("loginInDialog", "false");
            return "login";
        }
    }
    public String loginWithRanKey() {
        return "login";
    }
    public String fgLogin() {
        String loginName = request.getParameter("loginName");
        FUserDetail uinfo = null;
        try {
            uinfo = sysUserManager.loadUserByUsername(loginName);
        } catch (Exception e) {
            return "login";
        }
        Usersetting us = uinfo.getUserSetting();

        try {
            if (StringUtils.isEmpty(us.getMainpage())) {
                FOptinfo f = (FOptinfo) functionMgr.getFunctionsByUser(uinfo)
                        .get(0);
                us.setMainpage(f.getOpturl());
            }
        } catch (Exception e) {
        }

        Map<String, Object> session = ActionContext.getContext().getSession();
        String stylePath = request.getContextPath() + "/styles/"
                + us.getPagestyle();
        session.put("STYLE_PATH", stylePath);
        userFirstPage = '/' + us.getMainpage();
        request.setAttribute("firstPage", userFirstPage);
        session.put("LAYOUT", us.getFramelayout());
        // 当前用户所能获取菜单
        ActionContext.getContext().put(
                "OA_MENUS",
                this.getFunctionsByUserCode(((FUserinfo) super.getLoginUser())
                        .getUsercode()));
        request.getSession().setAttribute("USERDETAIL", uinfo);
        return "MainPage";
    }

    public String loginError() {
        //SessionKey 登录 rtx
//        loginRtx();
        
        Map<String, Object> session = ActionContext.getContext().getSession();
        String inDialog = (String) session.get("loginInDialog");
        if (inDialog != null || "true".equals(inDialog)) {
            saveError("用户名或密码错误！");
            return "loginResInDialog";
        } else {
            return "loginError";
        }
    }

    public String loginSuccess()  {
        //是否自动启动rtx客户端
        String autoLoginRtx=CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTX").getDatavalue();
        if(StringUtils.isNotBlank(autoLoginRtx) && "T".equals(autoLoginRtx)){
        //SessionKey 登录 rtx
        try {
            loginRtx();
            flage=true;
        } catch (Exception e) {
            flage=false;
        }
        }
        //request.setAttribute("onling_num", CountLineListener.ONLING_NUM);
        Map<String, Object> session = ActionContext.getContext().getSession();
        String inDialog = (String) session.get("loginInDialog");
        if (inDialog != null)
            session.remove("loginInDialog");
        if (inDialog != null && "true".equals(inDialog)) {           
            saveMessage("登录成功");
            return "loginResInDialog";
        } else {
            return "loginSuccess";
        }

    }
    /**
     * 登录rtx 参数
     */
  public void loginRtx() throws Exception{
      //是否自动启动rtx客户端
      String autoLoginRtx=CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTX").getDatavalue();
      if(StringUtils.isNotBlank(autoLoginRtx) && "T".equals(autoLoginRtx)){
      //登录成功单点登录rtx
      String strUser=((FUserDetail) getLoginUser()).getLoginname();
      String strSessionKey=SessionKeylogin.getSessionKey( strUser);
      
      String rtxSer= CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTXSER")!=null?CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTXSER").getDatavalue():RTXSvrApi.RTXSER;;
      request.getSession().setAttribute("strSessionKey", strSessionKey);
      request.getSession().setAttribute("strUser", strUser);
      request.getSession().setAttribute("rtxSer", rtxSer);
      } 
      
  }
  /**
   * 单点登录保存信息
   * @return
   * @throws Exception
   */
  public String logincas()throws Exception {
      return "loginCASSuccess";
  }

    public String dashboard() throws Exception {

        return "dashboard";
    }

    public String getSuperFunctionId() {
        return superFunctionId;
    }

    public void setSuperFunctionId(String superFunctionId) {
        this.superFunctionId = superFunctionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public AccessLogManager getAccessLogManager() {
        return accessLogManager;
    }

    public void setAccessLogManager(AccessLogManager accessLogManager) {
        this.accessLogManager = accessLogManager;
    }

    public OaUserinfoManager getOaUserinfoMag() {
        return oaUserinfoMag;
    }

    public void setOaUserinfoMag(OaUserinfoManager oaUserinfoMag) {
        this.oaUserinfoMag = oaUserinfoMag;
    }
  
}
