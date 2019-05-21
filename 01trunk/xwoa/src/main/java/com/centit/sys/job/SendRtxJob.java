package com.centit.sys.job;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.app.service.RtxInfoManager;
import com.centit.sys.service.CodeRepositoryUtil;

public class SendRtxJob extends  QuartzJobBean{
    private Logger log = Logger.getLogger(SendRtxJob.class);
    private RtxInfoManager rtxInfoManager;
    private boolean open; 
    
    public void setRtxInfoManager(RtxInfoManager rtxInfoManager) {
        this.rtxInfoManager = rtxInfoManager;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        log.info("==============RTX 发送定时任务开始==============");
        String autoLoginRtx = CodeRepositoryUtil.getDataPiece("SYSPARAM",
                "RTX").getDatavalue();
        log.info("定时器开关状态：" + open +" 数据库是否开放RTX开关状态："+autoLoginRtx);
        if (open) {
            if (StringUtils.isNotBlank(autoLoginRtx)
                    && "T".equals(autoLoginRtx)) {
                rtxInfoManager.sendRtx("0");
            }
        }
        log.info("==============RTX 发送定时任务结束==============");        
    }
}
