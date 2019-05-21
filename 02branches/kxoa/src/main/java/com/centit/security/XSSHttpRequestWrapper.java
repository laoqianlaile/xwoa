package com.centit.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author hx
 * @date 
 * @describe request信息封装类，用于判断、处理request请求中特殊字符
 */
public class XSSHttpRequestWrapper extends HttpServletRequestWrapper {
    private final String [] ignoreUrls = new String[]{"login","publicinfo","index.do","showMain.do","init.jsp","download","listSelectOrg","selectList","listSelectFlowDescribe.do","listSelectSuppowerstuffgroup.do","listSelectTempfile.do","viewxml.do","powerSelectList.do","selectDocList.do","selectZWHList.do","templateFile!listSelect","uploadfile.do"};
    
    /**
     * 封装http请求
     * @param request
     */
    public XSSHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        // 若开启特殊字符替换，对特殊字符进行替换
        if(XSSSecurityConfig.REPLACE){
            XSSSecurityManager.securityReplace(name);
        }
        return value;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        // 若开启特殊字符替换，对特殊字符进行替换
        if(XSSSecurityConfig.REPLACE){
            XSSSecurityManager.securityReplace(name);
        }
        return value;
    }

    /**
     * 没有违规的数据，就返回false;
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean checkHeader(){
        Enumeration<String> headerParams = this.getHeaderNames();
        //ADD BY LAY 检验Referer
        String contextPathDefault = this.getScheme()+"://"+this.getServerName() + ("80".equals(this.getServerPort())? "" : (":" + this.getServerPort()))
                + this.getContextPath();
        String referer = null;
        //END ADD
        while(headerParams.hasMoreElements()){
            String headerName = headerParams.nextElement();
            String headerValue = this.getHeader(headerName);
            if(XSSSecurityManager.matches(headerValue)){
                return true;
            }
            //ADD BY LAY 检验Referer
            if("referer".equals(headerName)){
                referer = headerValue;
            }
            //END ADD
        }
        //ADD BY LAY 检验Referer,首次打开的时候正常操作这个参数会为空
        /*2017-2-22单点登录时注释掉，暂时不需要检验这个
        if((referer==null && !allowRefererEmpty()) || 
                (referer!=null && !referer.contains(contextPathDefault) && !referer.contains(XSSSecurityConfig.REFERER_ALLOW_URL_EXTRA.trim()))){
            return true;
        }*/
        //END ADD
        return false;
    }
    /**
     * 当用户没有登录的时候我们允许referer为空
     * @return
     */
    private boolean allowRefererEmpty(){
        SecurityContext scontext = SecurityContextHolder.getContext();
        Authentication auth = scontext.getAuthentication();
      
        Object o = null;
        //如果无法获取用户登录身份信息，直接让他过,不进行后处理
        if(auth != null ){
            o = auth.getPrincipal();
            if(o==null || !(o instanceof UserDetails)){
                return true;
            }
        }else{
            return true;
        }
        
        String reqUrl = this.getServletPath();
        for(String url:ignoreUrls){
            if(reqUrl.indexOf(url) > -1){
                return true;
            }
        }
        return false;
    }
    
    /**
     * 没有违规的数据，就返回false;
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean checkParameter(){
        Map<String,Object> submitParams = this.getParameterMap();
        Set<String> submitNames = submitParams.keySet();
        for(String submitName : submitNames){
            Object submitValues = submitParams.get(submitName);
            if(XSSSecurityManager.matches((String)submitName)){
                return true;
            }
            if(submitValues instanceof String){
                if(XSSSecurityManager.matches((String)submitValues)){
                    return true;
                }
            }else if(submitValues instanceof String[]){
                for(String submitValue : (String[])submitValues){
                    if(XSSSecurityManager.matches((String)submitValue)){
                        return true;
                    }
                    
                }
                
            }
        }
        return false;
    }
    
   
    /**
     * 没有违规的数据，就返回false;
     * 若存在违规数据，根据配置信息判断是否跳转到错误页面
     * @param response
     * @return
     * @throws IOException 
     * @throws ServletException 
     */
    public boolean validateParameter(HttpServletResponse response) throws ServletException, IOException{
        // 开始header校验，对header信息进行校验
        if(XSSSecurityConfig.IS_CHECK_HEADER){
            if(this.checkHeader()){
                return true;
            }
        }
        // 开始parameter校验，对parameter信息进行校验
        if(XSSSecurityConfig.IS_CHECK_PARAMETER){
            if(this.checkParameter()){
                return true;
            }
        }
        return false;
    }
    
}
