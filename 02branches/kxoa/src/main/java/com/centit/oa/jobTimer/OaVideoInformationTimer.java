package com.centit.oa.jobTimer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.oa.service.OaVideoInformationManager;

public class OaVideoInformationTimer extends QuartzJobBean {
    private OaVideoInformationManager oaVideoInformationManager;
   
    public OaVideoInformationManager getOaVideoInformationManager() {
        return oaVideoInformationManager;
    }

    public void setOaVideoInformationManager(
            OaVideoInformationManager oaVideoInformationManager) {
        this.oaVideoInformationManager = oaVideoInformationManager;
    }

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        try {
            //暂时停用2015 1119
            //oaVideoInformationManager.autoLoseEfficacy();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
