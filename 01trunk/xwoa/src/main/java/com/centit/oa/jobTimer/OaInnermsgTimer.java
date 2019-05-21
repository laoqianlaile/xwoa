package com.centit.oa.jobTimer;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.oa.service.InnermsgManager;



public class OaInnermsgTimer extends QuartzJobBean {

    private InnermsgManager innermsgManager;

    /**
     * 系统自动结束调查
     */
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {

        try {
            innermsgManager.autoSend();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public InnermsgManager getInnermsgManager() {
        return innermsgManager;
    }

    public void setInnermsgManager(InnermsgManager innermsgManager) {
        this.innermsgManager = innermsgManager;
    }

  

}
