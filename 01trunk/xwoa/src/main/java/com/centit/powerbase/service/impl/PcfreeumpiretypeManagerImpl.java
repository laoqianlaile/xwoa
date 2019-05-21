package com.centit.powerbase.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerbase.dao.PcfreeumpiretypeDao;
import com.centit.powerbase.po.Pcfreeumpiretype;
import com.centit.powerbase.service.PcfreeumpiretypeManager;

public class PcfreeumpiretypeManagerImpl extends
        BaseEntityManagerImpl<Pcfreeumpiretype> implements
        PcfreeumpiretypeManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(PcfreeumpiretypeManager.class);

    private PcfreeumpiretypeDao pcfreeumpiretypeDao;

    public void setPcfreeumpiretypeDao(PcfreeumpiretypeDao baseDao) {
        this.pcfreeumpiretypeDao = baseDao;
        setBaseDao(this.pcfreeumpiretypeDao);
    }

    public List<Pcfreeumpiretype> listFreeumpiretype(Long degreeno) {
        return pcfreeumpiretypeDao.listFreeumpiretype(degreeno,"");
    }

    public List<Pcfreeumpiretype> getPCFreeUmpireTypeListByClassID(
            String itemId, Long version, String punishtypeid) {
        return pcfreeumpiretypeDao.getPCFreeUmpireTypeListByClassID(itemId,
                version, punishtypeid);
    }

    public void updatepcfreeumpiretype(long degreeno, String punishtypeid,
            long isinuse) {
        pcfreeumpiretypeDao.updatepcfreeumpiretype(degreeno, punishtypeid,
                isinuse);
    }

    public boolean ifHavePCFreeUmpireTypeRate(String punishtypeid,
            String degreeno) {
        return this.pcfreeumpiretypeDao.ifHavePCFreeUmpireTypeRate(
                punishtypeid, degreeno);
    }
}
