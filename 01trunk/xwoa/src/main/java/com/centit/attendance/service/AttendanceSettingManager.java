package com.centit.attendance.service;

import com.centit.core.service.BaseEntityManager;
import com.centit.attendance.po.AttendanceSetting;

public interface AttendanceSettingManager extends BaseEntityManager<AttendanceSetting> 
{
    /**
     * 自动生成id
     * 这个需要创建序列，在Sequences新建一个序列即可
     */
    public String getNewCode();
    
    /**
     * 查询设置上下班时间
     */
    public AttendanceSetting findTime();
}
