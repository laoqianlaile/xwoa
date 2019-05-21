package com.centit.mailclient.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.mailclient.po.MailProfile;
import com.centit.mailclient.service.MailInfoService;
import com.centit.mailclient.service.MailProfileService;
import com.centit.mailclient.util.MailConstants;
import com.centit.mailclient.util.MailRuntimeException;


/**
 * 
 * 邮箱收取定时器
 * 
 * @author Ghost
 * @create 2016年5月24日
 * @version
 */
public class MailPullJob extends  QuartzJobBean{
    private Logger log = Logger.getLogger(MailPullJob.class);
    private MailInfoService mailInfoService;
    private MailProfileService mailProfileService;
    
    public void setMailInfoService(MailInfoService mailInfoService) {
        this.mailInfoService = mailInfoService;
    }
    
    public void setMailProfileService(MailProfileService mailProfileService) {
        this.mailProfileService = mailProfileService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        log.info("===============收件拉取任务开始===============");
        //获取配置
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("isActive", MailConstants.BOOL_TRUE_ALIAS);
        List<MailProfile> profiles = mailProfileService.listObjects(filterMap);
        
        if(profiles!=null){
            for(MailProfile mailProfile : profiles){
                try{
                    mailInfoService.saveMailAfterPull(mailProfile);
                }catch(MailRuntimeException e){
                    log.error("配置usercode:"+mailProfile.getUsercode()+",email:"+mailProfile.getEmail()+"收取邮件发生异常："+e.getDescMessage());
                }catch(Exception e){
                    log.error("配置usercode:"+mailProfile.getUsercode()+",email:"+mailProfile.getEmail()+"收取邮件发生异常："+e.getMessage());
                }
            }
        }
        log.info("===============收件拉取任务结束===============");
    }
}
