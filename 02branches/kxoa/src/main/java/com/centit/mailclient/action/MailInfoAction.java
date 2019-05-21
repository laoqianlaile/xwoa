package com.centit.mailclient.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.core.task.TaskExecutor;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.mailclient.po.MailAttachment;
import com.centit.mailclient.po.MailInfo;
import com.centit.mailclient.po.MailProfile;
import com.centit.mailclient.service.MailAttachmentService;
import com.centit.mailclient.service.MailInfoService;
import com.centit.mailclient.service.MailProfileService;
import com.centit.mailclient.util.MailConstants;
import com.centit.mailclient.util.MailHelper;
import com.centit.mailclient.util.MailRuntimeException;
import com.centit.sys.security.FUserDetail;

/**
 * 
 * 邮件信息控制类
 * 
 * @author lay
 * @create 2016年5月16日
 * @version
 */
public class MailInfoAction  extends BaseEntityExtremeAction<MailInfo>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private static Logger log = Logger.getLogger(MailInfoAction.class);
    
    private MailProfileService mailProfileService;
    
    private MailInfoService mailInfoService;
    
    private MailAttachmentService mailAttachmentService;
    
    private File[] attachment;
    
    private String[] attachmentFileName;
    
    private InputStream inputStream;
    
    private String fileName;

    private TaskExecutor taskExecutor;
    
    public void setMailInfoService(MailInfoService mailInfoService) {
        this.mailInfoService = mailInfoService;
        super.setBaseEntityManager(mailInfoService);
    }
    
    public void setMailAttachmentService(MailAttachmentService mailAttachmentService) {
        this.mailAttachmentService = mailAttachmentService;
    }


    public void setMailProfileService(MailProfileService mailProfileService) {
        this.mailProfileService = mailProfileService;
    }
    
    public void setAttachment(File[] attachment) {
        this.attachment = attachment;
    }

    public void setAttachmentFileName(String[] attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }
    
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String mainPanel(){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("usercode", loginUser.getUsercode());
        filterMap.put("isActive", MailConstants.BOOL_TRUE_ALIAS);
        //获取配置列表
       List<MailProfile> profileList =  mailProfileService.listObjects(filterMap); 
       request.setAttribute("profileList", profileList);
       return "mainPanel";
    }
    
    public String list(){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("usercode", loginUser.getUsercode());
        //已删除文件位置是为空的
        if(filterMap.get("location")==null){
            filterMap.put("isvalid", MailConstants.BOOL_FALSE_ALIAS);
        }else{
            filterMap.put("location", Integer.valueOf(filterMap.get("location").toString()));
        }
        PageDesc pageDesc = DwzTableUtils.makePageDesc(request);
        objList = baseEntityManager.listObjects(filterMap, pageDesc);

        this.pageDesc = pageDesc;
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return "list";
    }
    
    public String view(){
        String mailInfoId = request.getParameter("id");
        object = mailInfoService.getObjectById(Long.valueOf(mailInfoId));
        
        if(MailConstants.BOOL_TRUE_ALIAS.equals(object.getHasAttachment())){
            Map<String,Object> filterMap = new HashMap<String,Object>();
            filterMap.put("mailInfoId", Long.valueOf(mailInfoId));
            List<MailAttachment> mailAttachments = mailAttachmentService.listObjects(filterMap);
            object.setAttachments(mailAttachments);
        }
        return "view";
    }
    
    public String mailForm(){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("usercode", loginUser.getUsercode());
        filterMap.put("isActive", MailConstants.BOOL_TRUE_ALIAS);
        //获取配置列表
        List<MailProfile> profileList =  mailProfileService.listObjects(filterMap); 
        request.setAttribute("profileList", profileList);
        //获取邮件信息        
        if(StringUtils.isNotEmpty(request.getParameter("id"))){
            view();
        }
        return "mailForm";
    }
    
    public String save(){
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        if(object.getId()==null){
            object.setId(mailInfoService.getNextSequence());
        }else{
            //查询是否有附件了
            Map<String,Object> filterMap = new HashMap<String,Object>();
            filterMap.put("mailInfoId", Long.valueOf(object.getId()));
            List<MailAttachment> mailAttachments = mailAttachmentService.listObjects(filterMap);
            
            if(mailAttachments==null || mailAttachments.isEmpty()){
                object.setHasAttachment("F");
            }else{
                object.setHasAttachment("T");
            }
        }
        
        object.setUsercode(loginUser.getUsercode());
        object.setCreateTime(new Date());
        object.setOwnType(MailConstants.OWN_TYPE_SEND);
        
        //附件操作
        List<MailAttachment> mailAttachments = null;
        if(attachment!=null){
            mailAttachments = new ArrayList<MailAttachment>();
            for(int i=0;i<attachment.length;i++){
                String relativePath = null;
                try {
                    relativePath = mailAttachmentService.upload(attachment[i],attachmentFileName[i]);
                } catch (IOException e) {
                   throw new RuntimeException(e.getMessage());
                }
                //先上传再保存
                MailAttachment mailAttachment = new MailAttachment();
                mailAttachment.setFileName(attachmentFileName[i]);//保留原始名称
                mailAttachment.setFileSize(attachment[i].length());
                mailAttachment.setPath(relativePath);
                mailAttachments.add(mailAttachment);
            }
            object.setAttachments(mailAttachments);
            object.setHasAttachment(MailConstants.BOOL_TRUE_ALIAS);
        }
       
        //如果不是保存草稿箱操作
        if(object.getLocation()!=MailConstants.LOCATION_DRAFTBOX){
            object.setSendTime(new Date());
        }
        mailInfoService.saveObject(object);
        return "mailHandleProgress";
    }
    
    /**
     * 发送邮件
     * @return
     */
    public String sendEmail(){
        //先保存
        save();
        final String profileId = request.getParameter("profileId");
        //再发送
        taskExecutor.execute(new Runnable(){

            @Override
            public void run() {
                MailProfile profile = mailProfileService.getObjectById(Long.valueOf(profileId));
                try{
                    MailHelper.sendMail(profile, object); 
                }catch(MailRuntimeException e){
                    log.error("发送邮件异常："+e.getDescMessage());
                }catch(Exception e){
                    log.error("发送邮件异常："+e.getMessage());
                }
            }
            
        });
        return "mailHandleProgress";
    }
    
    /**
     * 删除附件
     */
    public void removeAttachment(){
        boolean f = false;
        try{
            String attachmentId = request.getParameter("attachmentId");
            mailAttachmentService.deleteObjectById(Long.valueOf(attachmentId));
            f = true;
        }catch(Exception e){
            log.error("删除附件异常:"+e.getMessage());
        }
       
        PrintWriter out = null;;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(f);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();  
        out.close();  
    }
    /**
     * 逻辑删除
     */
    public void disableEmail(){
        boolean f = false;
        String ids = request.getParameter("ids");
        String []idArr = ids.split(",");
       try{
           for(String id : idArr){
               MailInfo mailInfo = new MailInfo();
               mailInfo.setId(Long.valueOf(id));
               mailInfoService.disableObject(mailInfo);
           }
           f = true;
       }catch(Exception e){
           log.error("标记邮件删除异常："+e.getMessage());
       }
       PrintWriter out = null;;
       try {
           out = ServletActionContext.getResponse().getWriter();
           out.print(f);
       } catch (IOException e) {
           log.error(e.getMessage());
       }
       out.flush();  
       out.close();  
    }
    
    /**
     * 物理删除
     */
    public void removeEmail(){
        boolean f = false;
        String ids = request.getParameter("ids");
        String []idArr = ids.split(",");
        try{
            for(String id : idArr){
                mailInfoService.deleteObjectById(Long.valueOf(id)); 
            }
            f = true;
        }catch(Exception e){
            log.error("删除邮件异常："+e.getMessage());
        }
        
        PrintWriter out = null;;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(f);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();  
        out.close();  
    }
    
    public String downloadAffix(){
        String attachmentId = request.getParameter("attachmentId");
       MailAttachment mailAttachment =  mailAttachmentService.getObjectById(Long.valueOf(attachmentId));
        try{
            this.setFileName(new String(mailAttachment.getFileName().getBytes("GBK"), "ISO8859-1"));
            this.setInputStream(new FileInputStream(mailAttachment.getAbsolutePath()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        return "downloadfile";
    }
    
    /**
     * 收取邮件
     */
    public void pullEmail(){
        boolean f = false;
        String msg = "";
        
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        String email = request.getParameter("email");
        try{
            mailInfoService.saveMailAfterPull(loginUser.getUsercode(), email);
            f = true;
        }catch(MailRuntimeException e){
            msg = e.getDescMessage();
            log.error("拉取邮件时发生异常，异常信息："+msg);
        }catch(Exception e){
            msg = e.getMessage();
            log.error("拉取邮件时发生异常，异常信息："+e.getMessage());
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
            String jsonStr = "{\"result\":"+f+",\"message\":\""+msg+"\"}";
            out.print(jsonStr);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();  
        out.close();
    }
    
    /**
     * 改变已读标识
     */
    public void changeReadState(){
        boolean f = false;
        String msg = "";
        try{
            String mailInfoId = request.getParameter("id");
            object = mailInfoService.getObjectById(Long.valueOf(mailInfoId));
            
            object.setReadState(MailConstants.BOOL_TRUE_ALIAS);
            mailInfoService.saveObject(object);
            f = true;
        }catch(Exception e){
            msg = e.getMessage();
            log.error("改变已读标识时发生异常，异常信息："+e.getMessage());
        }
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
            String jsonStr = "{\"result\":"+f+",\"message\":\""+msg+"\"}";
            out.print(jsonStr);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();  
        out.close();
    }
}
