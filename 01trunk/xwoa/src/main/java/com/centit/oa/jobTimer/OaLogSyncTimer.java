package com.centit.oa.jobTimer;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.webservice.server.TaijiLogRestService;
import com.centit.webservice.server.TaijiUserRestService;
/**
 * 同步太极用户和部门信息定时器
 * TODO Class description should be added
 * 
 * @author Administrator
 * @create 2017-7-25
 * @version
 */
public class OaLogSyncTimer extends QuartzJobBean {
    private TaijiLogRestService taijiLogRestService;

    /**
     * 定时器定时执行
     */
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        try {
            taijiLogRestService.sendLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TaijiLogRestService getTaijiLogRestService() {
        return taijiLogRestService;
    }

    public void setTaijiLogRestService(TaijiLogRestService taijiLogRestService) {
        this.taijiLogRestService = taijiLogRestService;
    }
    
}
