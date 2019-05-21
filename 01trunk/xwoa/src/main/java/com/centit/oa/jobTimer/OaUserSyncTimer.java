package com.centit.oa.jobTimer;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.centit.webservice.server.TaijiUserRestService;
/**
 * 同步太极用户和部门信息定时器
 * TODO Class description should be added
 * 
 * @author Administrator
 * @create 2017-7-25
 * @version
 */
public class OaUserSyncTimer extends QuartzJobBean {
    private TaijiUserRestService taijiUserRestService;

    /**
     * 定时器定时执行
     */
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        try {
            taijiUserRestService.syncUnit();//同步部门信息
            taijiUserRestService.syncUser();//同步用户信息
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public TaijiUserRestService getTaijiUserRestService() {
        return taijiUserRestService;
    }

    public void setTaijiUserRestService(TaijiUserRestService taijiUserRestService) {
        this.taijiUserRestService = taijiUserRestService;
    }
    
}
