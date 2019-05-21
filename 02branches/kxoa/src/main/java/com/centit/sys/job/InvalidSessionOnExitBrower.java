package com.centit.sys.job;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.sys.po.FAccessLog;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.AccessLogManager;
import com.centit.sys.service.SysUserManager;

/**
 * 
 * 当系统不是安全退出而是通过关闭浏览器的时候通过定时任务处理
 * 
 * @author lay
 * @create 2015年12月17日
 * @version
 */
public class InvalidSessionOnExitBrower extends  QuartzJobBean{
    
    private SysUserManager sysUserManager;
    private AccessLogManager accessManager;
    private final static int LIMIT_MINITUES = 30;
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        //获取超时没有操作的用户，我们认为他是关闭的
       List<FAccessLog> accessLogs = accessManager.findAccessLogUndo(LIMIT_MINITUES);
       if(accessLogs!=null && accessLogs.size() > 0){
           for(FAccessLog aclog : accessLogs){
              FUserinfo userInfo = sysUserManager.getObjectById(aclog.getUsercode());
              //当用户在线时，将他改成0
              if("1".equals(userInfo.getUserState())){
                  userInfo.setUserState("0");
                  sysUserManager.saveObject(userInfo);
                  //添加异常退出日志
                  FAccessLog accessLog = new FAccessLog();
                  accessLog.setIp(aclog.getIp());
                  accessLog.setAccesstime(new Date());
                  accessLog.setUsercode(aclog.getUsercode());
                  accessLog.setAccesstype(FAccessLog.ACCESS_LOGOUT_ABNORMAL);
                  accessManager.saveObject(accessLog);
              }
           }
       }
       
    }
    
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public void setAccessManager(AccessLogManager accessManager) {
        this.accessManager = accessManager;
    }
    
    
}
