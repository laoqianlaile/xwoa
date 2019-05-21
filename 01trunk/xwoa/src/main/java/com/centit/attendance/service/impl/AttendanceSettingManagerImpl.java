package com.centit.attendance.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.attendance.po.AttendanceSetting;
import com.centit.attendance.dao.AttendanceSettingDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.attendance.service.AttendanceSettingManager;

public class AttendanceSettingManagerImpl extends BaseEntityManagerImpl<AttendanceSetting>
	implements AttendanceSettingManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(AttendanceSettingManager.class);

	private AttendanceSettingDao attendanceSettingDao ;
	public void setAttendanceSettingDao(AttendanceSettingDao baseDao)
	{
		this.attendanceSettingDao = baseDao;
		setBaseDao(this.attendanceSettingDao);
	}
	
	/**
	 * 上下班时间设置自动生成id
	 */
    @Override
    public String getNewCode() {
        // TODO Auto-generated method stub
        return "SZ" + attendanceSettingDao.getNextKeyBySequence("S_Attendance_Setting", 6);
    }
    
    /**
     * 查询设置上下班时间
     */
    @Override
    public AttendanceSetting findTime() {
        // TODO Auto-generated method stub
        return attendanceSettingDao.findTime();
    }
	
}

