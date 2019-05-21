package com.centit.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.simple.JSONObject;

import com.centit.bbs.util.IPUtil;
import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.service.OaSmssendManager;
import com.centit.sys.po.FUserForgetPassword;
import com.centit.sys.po.FUserPwd;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.CaptchaImageUtil;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.UserForgetPasswordManager;
import com.centit.sys.util.ISysOptLog;
import com.centit.sys.util.IdGen;
import com.centit.sys.util.SysOptLogFactoryImpl;
import com.centit.webservice.client.WsclientManager;

public class UserForgetPasswordAction extends BaseEntityExtremeAction<FUserForgetPassword> implements
ServletResponseAware {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(UserForgetPasswordAction.class);
    private static final ISysOptLog SYS_OPT_LOG = SysOptLogFactoryImpl.getSysOptLog("USERMAG");
    private SysUserManager sysUserMgr;
  
    private WsclientManager wsclientManager;

    private CodeRepositoryManager codeRepositoryManager;
    
    private UserForgetPasswordManager userForgetPasswordManager;
 
    private OaSmssendManager oaSmssendManager;
    
    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }
    
    private FUserPwd userPwd;

   
    public FUserPwd getUserPwd() {
        if (userPwd == null)
            userPwd = new FUserPwd();
        return userPwd;
    }

    public void setUserPwd(FUserPwd userPwd) {
        this.userPwd = userPwd;
    }

    public void setCodeRepositoryManager(
            CodeRepositoryManager codeRepositoryManager) {
        this.codeRepositoryManager = codeRepositoryManager;
    }

    public void setSysUserMgr(SysUserManager sysuserMagr) {
        this.sysUserMgr = sysuserMagr;
    }

    
    public UserForgetPasswordManager getUserForgetPasswordManager() {
        return userForgetPasswordManager;
    }

    public void setUserForgetPasswordManager(
            UserForgetPasswordManager userForgetPasswordManager) {
        this.userForgetPasswordManager = userForgetPasswordManager;
    }

    /**
     * 返回AJAX数据
     * 发起忘记密码请求
     * @return
     * @throws IOException 
     */
    public void forgetPasswordCheckcode() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out;
        String session_checkcode = request.getSession().getAttribute(CaptchaImageUtil.SESSIONCHECKCODE).toString();
        String request_checkcode = null==request.getParameter(CaptchaImageUtil.REQUESTCHECKCODE)?"":request.getParameter(CaptchaImageUtil.REQUESTCHECKCODE).toString();
        boolean validCaptcha =  session_checkcode!=null && session_checkcode.equalsIgnoreCase(request_checkcode) ;
        out = response.getWriter();
        if (!validCaptcha) {
            out.print(false);
        }else{
            out.print(true);
        }
    }

    
    /**
     * 返回AJAX数据
     * 验证电话号码合法性
     * @return
     * @throws IOException 
     */
    public void telephoneCheck() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out;
        String telephone = null==request.getParameter("telephoneCheck")?"": request.getParameter("telephoneCheck").toString();
        List<FUserinfo> userinfos=sysUserMgr.getUserbByPhone(telephone);
        boolean telephoneCheck=(null!=userinfos&&userinfos.size()==1)?true:false;//有且只有一条记录时，号码合法
        FUserForgetPassword fUserForgetPassword=new FUserForgetPassword ();
        String username="匿名用户";
        if(telephoneCheck){
            String ip=IPUtil.getIRealIPAddr(request);
            String  usercode=userinfos.get(0).getUsercode();
            username=CodeRepositoryUtil.getValue("usercode", usercode);
            fUserForgetPassword.setTelephone(telephone);
            fUserForgetPassword.setUsercode(usercode);
            fUserForgetPassword.setIp(ip);
            fUserForgetPassword=  userForgetPasswordManager.createForgetPasswordInfo (fUserForgetPassword);
//            String content="【OA办公平台】校验码"+fUserForgetPassword.getValidatenum()+",您的OA办公平台正在使用重置密码功能，需要进行校验。【请勿向任何人提供您收到的短信校验码】";
//            oaSmssendManager.saveMsgs( usercode,  usercode,  content,  "MMZH");
//            oaSmssendManager.executeSendMsg(); 
        }
        out = response.getWriter();
        if (telephoneCheck) {
            out.print("{'validat':'true','uuid':'"+fUserForgetPassword.getDjId()+"','username':'"+username+"'}");
        }else{
            out.print("{'validat':'false','uuid':''}");
        }
    }
    
    /**
     * 返回AJAX数据
     * 短信验证码确认
     * uuid及telephone避免用户直接跳过此步骤
     * @return
     * @throws IOException 
     */
    public void messagecodeCreate() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out;
        String djId =null==request.getParameter("identityid")?"": request.getParameter("identityid").toString();
        String telephone = null==request.getParameter("telephoneCheck")?"": request.getParameter("telephoneCheck").toString();
        FUserForgetPassword fUserForgetPassword=new FUserForgetPassword ();
        fUserForgetPassword.setDjId(djId);
        fUserForgetPassword.setTelephone(telephone);
        boolean validcode=userForgetPasswordManager.messagecodeCreate(fUserForgetPassword);
        out = response.getWriter();
        if(validcode){
            fUserForgetPassword=userForgetPasswordManager.getObjectById(djId);
            String  usercode=fUserForgetPassword.getUsercode();
            String content="【OA办公平台】校验码"+fUserForgetPassword.getValidatenum()+",您正在使用OA办公平台重置密码功能，请输入校验码进行校验。【为了您的账户安全，请勿向任何人提供您收到的短信校验码】";
            oaSmssendManager.saveMsgs( usercode,  usercode,  content,  "MMZH");
            oaSmssendManager.executeSendMsg(); 
            out.print(true);
        }else{
            out.print(false);
        }
       
        
    }
    /**
     * 返回AJAX数据
     * 短信验证码确认
     * uuid及telephone避免用户直接跳过此步骤
     * @return
     * @throws IOException 
     */
    public void messagecodeCheck() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out;
        String djId =null==request.getParameter("identityid")?"": request.getParameter("identityid").toString();
        String telephone = null==request.getParameter("telephoneCheck")?"": request.getParameter("telephoneCheck").toString();
        String messagecode = null==request.getParameter("messagecode")?"": request.getParameter("messagecode").toString();
        FUserForgetPassword fUserForgetPassword=new FUserForgetPassword ();
        fUserForgetPassword.setDjId(djId);
        fUserForgetPassword.setTelephone(telephone);
        fUserForgetPassword.setValidatenum(messagecode);
        boolean validMessagecode=userForgetPasswordManager.messagecodeCheck(fUserForgetPassword);
        out = response.getWriter();
        if (validMessagecode) {
            userForgetPasswordManager.getObjectById(djId);
            out.print(true);
        }else{
            out.print(false);
        }
    }
   
    /**
     * 返回AJAX数据
     * 重置密码
     * uuid及telephone避免用户直接跳过此步骤
     * @return
     * @throws IOException 
     */
    public void  updatePassword() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out;
        String djId =null==request.getParameter("identityid")?"": request.getParameter("identityid").toString();
        String password = null==request.getParameter("password")?"": request.getParameter("password").toString();
        FUserForgetPassword fUserForgetPassword=new FUserForgetPassword ();
        fUserForgetPassword.setDjId(djId);
        fUserForgetPassword.setPassword(password);
        boolean updatePassword=userForgetPasswordManager.updatePassword(fUserForgetPassword);
        out = response.getWriter();
        if (updatePassword) {
            userForgetPasswordManager.getObjectById(djId);
            //同步人员信息到移动端
            wsclientManager.syncUserList(object.getUsercode());
            out.print(true);
        }else{
            out.print(false);
        }
    }
    
    
    
    public String resetpwd() {
        try {
            sysUserMgr.resetPwd(object.getUsercode());
            savedMessage();
            //同步人员信息到移动端
            wsclientManager.syncUserList(object.getUsercode());
        } catch (Exception ee) {
            ee.printStackTrace();
            return ERROR;
        }
        return errorMessage;
        
    }
    
 
    protected HttpServletResponse response;
    @Override
    public void setServletResponse(HttpServletResponse response) {
        // TODO Auto-generated method stub
        this.response = response;
    }
    /**
     * 弹出提示信息
     * 
     * @param msg
     * @param response
     */
    protected void postAlertMessage(String msg, HttpServletResponse response) {

        String alertCoding = "GBK";

        ServletOutputStream sos;
        String str = "<script language=\"JavaScript\""
                + " type=\"text/JavaScript\" charset=\"" + alertCoding + "\">"
                + "javascript:alert('" + msg + "');history.back(-1);"
                + " </script>";

        response.setContentType("text/html; charset=" + alertCoding);
        try {
            sos = response.getOutputStream();
//            int strSize = (int) str.length();
            byte[] b =  str.getBytes();
            sos.write(b);
            sos.flush();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    // 简单操作的ajax方法，使用json作为数据传输
    public void ajaxResponseJson(HttpServletResponse response, JSONObject json) {
        this.ajaxResponseText(response, json.toString());
    }
    
    // 简单操作的ajax方法，使用文本作为数据传输（主要在需要同步时使用）
    public void ajaxResponseText(HttpServletResponse response, String text) {
        // response 返回页面信息
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = null;

        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html;charset=UTF-8");

            writer = response.getWriter();
            writer.write(text); // 发送json数据
        } catch (Exception e) {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html;charset=UTF-8");

            try {
                writer = response.getWriter();
                writer.write(text);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void setWsclientManager(WsclientManager wsclientManager) {
        this.wsclientManager = wsclientManager;
    }
}
