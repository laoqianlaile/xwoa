package com.centit.sys.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.TaskLogDao;
import com.centit.sys.po.TaskLog;
import com.centit.sys.service.IQuartzJobBean;
import com.centit.sys.service.SchedulerManager;
import com.centit.sys.service.TaskLogManager;

public class TaskLogManagerImpl extends BaseEntityManagerImpl<TaskLog>
        implements TaskLogManager, IQuartzJobBean {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(TaskLogManager.class);

    private SchedulerManager schedulerManager;

    
//private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

    public void setSchedulerManager(SchedulerManager schedulerManager) {
		this.schedulerManager = schedulerManager;
	}

	private TaskLogDao taskLogDao;

    public void setTaskLogDao(TaskLogDao baseDao) {
        this.taskLogDao = baseDao;
        setBaseDao(this.taskLogDao);
    }

    @Override
    public void initTimerTask() {
        //定时任务，删除已执行过日志数据
        JobDetail jobDetail = schedulerManager.getJobDetail("Job_TaskLog", "Job_Group_TaskLog");
        jobDetail.getJobDataMap().put(QUARTZ_JOB_BEAN_KEY, this);

        //每日0:00:00统一删除 TODO 删除已执行的数据 需要更新
        schedulerManager.schedule(jobDetail, "Trigger_TaskLog", "Trigger_Group_TaskLog", "0 0 0 */1 * ?");
//        schedulerManager.schedule(jobDetail, "Trigger_TaskLog", "Trigger_Group_TaskLog", "0 */3 * * * ?");
    }

//    @Override
    public void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
        taskLogDao.deleteIsRun();
    }
}

