package com.centit.mailclient.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.mailclient.po.MailProfile;
import com.centit.mailclient.service.MailProfileService;
import com.centit.mailclient.util.MailConstants;
import com.centit.mailclient.util.MailHelper;
import com.centit.sys.security.FUserDetail;

/**
 * 
 * 邮箱服务器配置类
 * 
 * @author lay
 * @create 2016年5月11日
 * @version
 */
public class MailProfileAction extends BaseEntityExtremeAction<MailProfile>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private MailProfileService mailProfileService;
    private Logger log = Logger.getLogger(getClass());
    
    public void setMailProfileService(MailProfileService mailProfileService) {
        this.mailProfileService = mailProfileService;
        super.setBaseEntityManager(mailProfileService);
    }
    
    /**
     * 邮箱设置面板
     * @return
     */
    public String settingPanel(){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("usercode", loginUser.getUsercode());
        //获取配置列表
        List<MailProfile> profileList = mailProfileService.listObjects(filterMap);
        request.setAttribute("profileList", profileList);
        return "settingPanel";
    }
    /**
     * 邮箱定制
     * @return
     */
    public String settingCustomPanel(){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("usercode", loginUser.getUsercode());
        //获取配置列表
        List<MailProfile> profileList = mailProfileService.listObjects(filterMap);
        if(profileList!=null){
            for(MailProfile mailProfile : profileList){
                if(mailProfile.getEmail()!=null && mailProfile.getEmail().indexOf("@njgh.org")>-1){
                    object = mailProfile;
                    break;
                }
            }
        }
        return "settingCustomPanel";
    }
    public String settingForm(){
        object = mailProfileService.getObject(object);
        return "settingForm";
    }
    
    public String accountAdd(){
        return "accountAdd";
    }
    
    /**
     * 检测账号是否重复
     */
    public void checkDuplicateProfile(){
        boolean duplicate = mailProfileService.checkDuplicateProfile(object.getEmail(),object.getId());
        responseJson(duplicate);
    }
    
    /**
     * 检测配置文件是否有效
     */
    public void checkProfileAvailable(){
        object.setPassword(MailProfile.encryptPassword(object.getPassword()));
        boolean available = MailHelper.assertProfileAvailable(object);
        responseJson(available);
    }
    
    private void responseJson(Object obj){
        PrintWriter out = null;;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(obj);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();  
        out.close();  
    }
    
    /**
     * 保存配置文件
     */
    public void saveProfile(){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        boolean f = false;
        try{
            if(object.getId()==null){
                Long id = mailProfileService.getNextSequence();
                object.setId(id);
                if(MailConstants.MAIL_PROTOCOL_TYPE_EXCHANGE.equals(object.getReceiveHostType())){
                    object.setSendHostAddr(object.getReceiveHostAddr());
                }
            }
            object.setUsercode(loginUser.getUsercode());
            object.setPassword(MailProfile.encryptPassword(object.getPassword()));
            mailProfileService.saveObject(object);
            f = true;
        }catch(Exception e){
            log.error(e.getMessage());
        }
       
        responseJson(f);
       
    }
    
    public void removeProfile(){
        boolean f = false;
        try{
            Long id = Long.valueOf(request.getParameter("id"));
            mailProfileService.deleteObjectById(id);
            f = true;
        }catch(Exception e){
            log.error(e.getMessage());
        }
        responseJson(f);
    }
    
    
}
