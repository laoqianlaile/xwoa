package com.centit.attendance.action;

import java.util.Date;

import com.centit.attendance.po.AttendanceSetting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
	







import com.centit.core.action.BaseEntityDwzAction;

		

import com.centit.attendance.service.AttendanceSettingManager;
	

public class AttendanceSettingAction  extends BaseEntityDwzAction<AttendanceSetting>  {
	private static final Log log = LogFactory.getLog(AttendanceSettingAction.class);
	private static final long serialVersionUID = 1L;
	private AttendanceSettingManager attendanceSettingMag;
	public void setAttendanceSettingManager(AttendanceSettingManager basemgr)
	{
		attendanceSettingMag = basemgr;
		this.setBaseEntityManager(attendanceSettingMag);
	}
/**
 * 重写新增方法
 * 
 */
    public String built() {
        try {
            object = new AttendanceSetting();
            return BUILT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
	
/**
 * 重写保存方法
 * 
 */
    public String save() {
        try {
            AttendanceSetting dbObject = baseEntityManager.getObject(object);
            if (dbObject != null) {
                baseEntityManager.copyObjectNotNullProperty(dbObject, object);
                object = dbObject;
            }else{
                Date date = new Date();
                object.setCreatedate(date);//设置创建时间为当前系统时间
                object.setDjid(attendanceSettingMag.getNewCode());//获取自动生成的id
            }
            baseEntityManager.saveObject(object);
            savedMessage();
            return SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return ERROR;
        }
    }
    /**
     * 重写编辑方法，此方法是从考勤信息页面点击过来的
     */
    @SuppressWarnings("unused")
    public String newedit() {
        AttendanceSetting object= new AttendanceSetting();
        object = attendanceSettingMag.findTime();
        request.setAttribute("object",object);
        if(object != null){
            try {
                if (object == null) {
                    object = getEntityClass().newInstance();
                } else {
                    AttendanceSetting o = baseEntityManager.getObject(object);
                    if (o != null)
                        // 将对象o copy给object，object自己的属性会保留
                        baseEntityManager.copyObject(object, o);
                    else
                        baseEntityManager.clearObjectProperties(object);
                }
                return EDIT;
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
            }
        }else{
            try {
                object = new AttendanceSetting();
                return BUILT;
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
            }
        }
    }
		
}
