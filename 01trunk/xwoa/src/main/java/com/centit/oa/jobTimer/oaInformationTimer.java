package com.centit.oa.jobTimer;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.oa.service.OaInformationManager;

public class oaInformationTimer   extends QuartzJobBean {
    private Logger log = Logger.getLogger(oaInformationTimer.class);
    public OaInformationManager getOaInformationManager() {
        return oaInformationManager;
    }



    public void setOaInformationManager(OaInformationManager oaInformationManager) {
        this.oaInformationManager = oaInformationManager;
    }



    private OaInformationManager oaInformationManager;
    
  

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        log.info("===============通知公告过了信息有效期，自动变成禁用 定 时任务开始=================");
        try {
            oaInformationManager.autoInvalidated();
        } catch (Exception e) {
          log.error(e.getMessage());
        }
        log.info("===============通知公告过了信息有效期，自动变成禁用 定 时任务结束=================");
    }

}
