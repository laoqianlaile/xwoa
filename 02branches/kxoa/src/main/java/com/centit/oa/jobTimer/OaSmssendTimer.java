package com.centit.oa.jobTimer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.webservice.client.impl.SendMsgClientManagerImpl;

/**
 * 短信发送
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2016年5月6日
 * @version
 */
public class OaSmssendTimer extends QuartzJobBean {
    private SendMsgClientManagerImpl sendMsgClientManager;

    public SendMsgClientManagerImpl getSendMsgClientManager() {
        return sendMsgClientManager;
    }

    public void setSendMsgClientManager(
            SendMsgClientManagerImpl sendMsgClientManager) {
        this.sendMsgClientManager = sendMsgClientManager;
    }

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
       try {
           sendMsgClientManager.sendMsg() ;
            
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
