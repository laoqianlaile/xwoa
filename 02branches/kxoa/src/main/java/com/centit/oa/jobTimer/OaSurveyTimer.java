package com.centit.oa.jobTimer;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.oa.service.OaSurveyManager;

public class OaSurveyTimer extends QuartzJobBean {

    private OaSurveyManager oaSurveyManager;

    /**
     * 系统自动结束调查
     */
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {

        try {
            oaSurveyManager.autoEnd();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public OaSurveyManager getOaSurveyManager() {
        return oaSurveyManager;
    }

    public void setOaSurveyManager(OaSurveyManager oaSurveyManager) {
        this.oaSurveyManager = oaSurveyManager;
    }

}
