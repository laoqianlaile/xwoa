package com.centit.oa.jobTimer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.oa.service.OaSmssendManager;

/**
 * 短信发送
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2016年5月6日
 * @version
 */
public class OaSmssendTimer extends QuartzJobBean {
    
    private OaSmssendManager oaSmssendManager;
    /**
     * 短信发送方法
     */
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
       try {
           //oaSmssendManager.executeSendMsg();//网关形式发送短信
           oaSmssendManager.executeSendMsgTj();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public OaSmssendManager getOaSmssendManager() {
        return oaSmssendManager;
    }
    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }

}
