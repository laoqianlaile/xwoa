package com.centit.sys.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.web.util.WebUtils;

import com.centit.sys.util.RSAUtil;
import com.centit.webservice.util.Encodes;

public class CodeAuthenticationProcessingFilter extends
        UsernamePasswordAuthenticationFilter {

    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
        // 集成登录时排除验证码
        if (Boolean.TRUE.toString().equals(
                WebUtils.findParameterValue(request, "integratedLogin"))) {

            if (true && !request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException(
                        "Authentication method not supported: "
                                + request.getMethod());
            }
            String username = obtainUsername(request);
            String password = obtainPassword(request);

            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();

            // Place the last username attempted into HttpSession for views
            HttpSession session = request.getSession(false);
            
            SimpleUsernamePasswordAuthenticationToken authRequest = new SimpleUsernamePasswordAuthenticationToken(
                    username, password, Boolean.TRUE.toString());

            if (session != null || getAllowSessionCreation()) {
                request.getSession().setAttribute(
                        SPRING_SECURITY_LAST_USERNAME_KEY,
                        TextEscapeUtils.escapeEntities(username));
            }
            // 点击的代办的url
            if (session != null) {
                session.removeAttribute("taskurl");
                String taskurl = WebUtils
                        .findParameterValue(request, "taskurl");
                if (StringUtils.isNotBlank(taskurl))
                    session.setAttribute(username + "taskurl", taskurl);
                if (Boolean.TRUE.toString().equals(
                        WebUtils.findParameterValue(request, "integratedLogin"))){
                    session.setAttribute("unLogin", "1");
                }
            }
            

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);

            return super.getAuthenticationManager().authenticate(authRequest);
        }

        String request_checkcode = request
                .getParameter(CaptchaImageUtil.REQUESTCHECKCODE);
        //未出错跳过验证码
        String hascheckcode=request.getParameter("hascheckcode");
        if("have".equals(hascheckcode)){
            request_checkcode="nocheckcode";
        }
        if (!"nocheckcode".equals(request_checkcode)) {
            String session_checkcode = "";
            Object obj = request.getSession().getAttribute(
                    CaptchaImageUtil.SESSIONCHECKCODE);
            if (obj != null)
                session_checkcode = obj.toString();
            if (request_checkcode == null
                    || !request_checkcode.equalsIgnoreCase(session_checkcode))
                throw new AuthenticationServiceException("bad checkcode");
        }
    
        return doAuthc(request, response);
    }
    
    private Authentication doAuthc(HttpServletRequest request,
            HttpServletResponse response){
        if (true && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();
        
        //密码是否是rsa加密方式
        if("RSAciphertext".equals(request.getParameter("pwdTransferType"))){
            try{
                String RSAEncrypted = request.getParameter("RSAEncrypted");
                if(StringUtils.isNotEmpty(RSAEncrypted) && password.startsWith(RSAEncrypted)){
                    password = password.substring(RSAEncrypted.length());    
                }
                
                byte[] en_result = Encodes.decodeHex(password);
                byte[] de_result = RSAUtil.decrypt(RSAUtil.getKeyPair().getPrivate(),en_result);
                StringBuffer sb = new StringBuffer();  
                sb.append(new String(de_result));  
                password = sb.reverse().toString();
            }catch(Exception e){
                throw new AuthenticationServiceException("Authentication Failed ");
            }
           
        }
        
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Place the last username attempted into HttpSession for views
        HttpSession session = request.getSession(false);

        if (session != null || getAllowSessionCreation()) {
            request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, TextEscapeUtils.escapeEntities(username));
        }

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
