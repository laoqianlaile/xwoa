package com.centit.bbs.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.dao.VBbsThemeReplayDao;
import com.centit.bbs.dao.VBbsThemeUserDao;
import com.centit.bbs.po.VBbsThemeReplay;
import com.centit.bbs.po.VBbsThemeUser;
import com.centit.bbs.service.VBbsThemeUserManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;


public class VBbsThemeUserManagerImpl extends
        BaseEntityManagerImpl<VBbsThemeUser> implements VBbsThemeUserManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VBbsThemeUserManager.class);

    private VBbsThemeUserDao vBbsThemeUserDao;
    private VBbsThemeReplayDao vBbsThemeReplayDao;
    public void setVBbsThemeUserDao(VBbsThemeUserDao baseDao) {
        this.vBbsThemeUserDao = baseDao;
        setBaseDao(this.vBbsThemeUserDao);
    }

    @Override
    public List<VBbsThemeReplay> getThemeReplyList(Map<String, Object> filterMap, PageDesc pageDesc) {
      
        return vBbsThemeReplayDao.listObjects(filterMap, pageDesc);
    }

    public VBbsThemeReplayDao getvBbsThemeReplayDao() {
        return vBbsThemeReplayDao;
    }

    public void setvBbsThemeReplayDao(VBbsThemeReplayDao vBbsThemeReplayDao) {
        this.vBbsThemeReplayDao = vBbsThemeReplayDao;
    }

}