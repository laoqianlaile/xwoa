package com.centit.app.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.dao.VOaScheduleRemindDao;
import com.centit.app.po.VOaScheduleRemind;
import com.centit.app.service.ThreadAnnexManager;
import com.centit.app.service.VOaScheduleRemindManager;
import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;

public class VOaScheduleRemindManagerImpl extends BaseEntityManagerImpl<VOaScheduleRemind>
        implements VOaScheduleRemindManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ThreadAnnexManager.class);

    //private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

    private VOaScheduleRemindDao voaScheduleRemindDao;

    public void setVoaScheduleRemindDao(VOaScheduleRemindDao baseDao) {
        this.voaScheduleRemindDao = baseDao;
        setBaseDao(this.voaScheduleRemindDao);
    }
@Override
public List<VOaScheduleRemind> listObjects(Map<String, Object> filterMap,String usercode) {
    List<VOaScheduleRemind> voa =voaScheduleRemindDao.listObjects(filterMap,usercode);
    return voa;
}
    

}

