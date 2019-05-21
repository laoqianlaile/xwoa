package com.centit.powerbase.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerbase.dao.PcfreeumpiredegreeDao;
import com.centit.powerbase.po.Pcfreeumpiredegree;
import com.centit.powerbase.service.PcfreeumpiredegreeManager;

public class PcfreeumpiredegreeManagerImpl extends
        BaseEntityManagerImpl<Pcfreeumpiredegree> implements
        PcfreeumpiredegreeManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(PcfreeumpiredegreeManager.class);

    private PcfreeumpiredegreeDao pcfreeumpiredegreeDao;

    public void setPcfreeumpiredegreeDao(PcfreeumpiredegreeDao baseDao) {
        this.pcfreeumpiredegreeDao = baseDao;
        setBaseDao(this.pcfreeumpiredegreeDao);
    }

    public List<Pcfreeumpiredegree> listFreeumpire(String itemId, Long version) {
        return pcfreeumpiredegreeDao.listFreeumpire(itemId, version);
    }

    public Pcfreeumpiredegree getObjectByItemsId(Long degreeno) {
        return pcfreeumpiredegreeDao.getObjectByItemsId(degreeno);
    }

    public String generateNextDegreeno() {
        return pcfreeumpiredegreeDao.generateNextDegreeno();
    }

    @SuppressWarnings("rawtypes")
    public List<Map> listPunishFactGrade(String itemId, Long version) {
        return pcfreeumpiredegreeDao.listPunishFactGrade(itemId, version);
    }

    @Override
    public List<Pcfreeumpiredegree> getObjectByItemsId(String itemId) {
        // TODO Auto-generated method stub
        return pcfreeumpiredegreeDao.getObjectByItemsId(itemId);
    }
}
