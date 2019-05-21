package com.centit.sys.job;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.centit.app.po.OuterNetFileinfoFs;
import com.centit.app.po.OuterNetPublicinfo;
import com.centit.app.service.OuterNetFileinfoFsManager;
import com.centit.app.service.OuterNetPublicinfoManager;
import com.centit.oa.service.OaLeaveOverTimeManager;
import com.centit.sys.service.VUserOnlineManager;

/**
 * 
 * 清除外网同步过来的文件，清理7天之前的数据,同时删除下载时产生的temp文件
 * 
 * @author lay
 * @create 2015年12月28日
 * @version
 */
public class ClearFileJob extends  QuartzJobBean{
    private Logger log = Logger.getLogger(ClearFileJob.class);
    private OuterNetPublicinfoManager publicinfoManager;
    private OuterNetFileinfoFsManager fileInfoFsManager;
    private VUserOnlineManager vUserOnlineManager;
    private OaLeaveOverTimeManager oaLeaveOverTimeManager;
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        log.info("=============清理7天之前外网同步到内网过来的文件，定时任务开始===========");
           Map<String,Object> filterMap = new HashMap<String,Object>();
           filterMap.put("timeout", new Double(7));
          List<OuterNetPublicinfo> pendingRemoveList =  publicinfoManager.listObjects(filterMap);
          if(pendingRemoveList == null || pendingRemoveList.size()==0){
              log.info("没有文件需要记录清理");
          }else{
              int removedFilesCount = 0;//删除的文件数n
              int removedRecsCount = 0;//删除的记录数
              Set<String> pendingRemoveDirs = new HashSet<String>();
              for(OuterNetPublicinfo pinfo : pendingRemoveList){
                  //删除物理文件
                  OuterNetFileinfoFs fileinfo = pinfo.getFileinfoFs();
                  if("0".equals(pinfo.getIsfolder()) && fileinfo!=null){
                      String path = fileinfo.getPath();
                      if(StringUtils.isNotBlank(path)){
                          path = path.substring(0, path.lastIndexOf(File.separator));
                          pendingRemoveDirs.add(path);
                          removedFilesCount++;
                      }
                  }
                  //删除数据库记录,fileinfo与publicinfo有级联关系
                  publicinfoManager.deleteObject(pinfo);
                  removedRecsCount ++;
              }
              //为了不留空文件夹，直接删除目录
              for(String path:pendingRemoveDirs){
                  fileInfoFsManager.deleteFileOnDisk(path);
              }
              log.info("删除了文件"+removedFilesCount+"个，删除了记录"+removedRecsCount+"条，7天文件数据删除成功");
          }
          //清理temp文件
          boolean clearTemp = fileInfoFsManager.deleteFileOnDisk(File.separator+"temp");
          if(clearTemp){
              log.info("清理下载时产生的临时文件成功");
          }
        log.info("=============清理7天之前外网同步到内网过来的文件，定时任务结束===========");
        
        
        log.info("=============添加考勤记录，定时开始===========");
        //oaLeaveOverTimeManager.addAttendanceDetails(new Date());
        log.info("=============添加考勤记录，定时结束");
        
        log.info("=============统一清理人员状态，定时任务开始===========");
        vUserOnlineManager.clearUserOnlineState();
        log.info("=============统一清理人员状态，定时任务结束===========");
    }    
    
    public void setPublicinfoManager(OuterNetPublicinfoManager publicinfoManager) {
        this.publicinfoManager = publicinfoManager;
    }
    public void setFileInfoFsManager(OuterNetFileinfoFsManager fileInfoFsManager) {
        this.fileInfoFsManager = fileInfoFsManager;
    }

    public void setvUserOnlineManager(VUserOnlineManager vUserOnlineManager) {
        this.vUserOnlineManager = vUserOnlineManager;
    }

    public void setOaLeaveOverTimeManager(
            OaLeaveOverTimeManager oaLeaveOverTimeManager) {
        this.oaLeaveOverTimeManager = oaLeaveOverTimeManager;
    }
    
    
    
}
