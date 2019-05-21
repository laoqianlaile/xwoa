package com.centit.dispatchdoc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.OptLeaderShipDao;
import com.centit.dispatchdoc.po.OptLeaderShip;
import com.centit.dispatchdoc.service.OptLeaderShipManager;

public class OptLeaderShipManagerImpl extends BaseEntityManagerImpl<OptLeaderShip>
implements OptLeaderShipManager{
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OptLeaderShipManager.class);

    private OptLeaderShipDao optLeaderShipDao;
    


    public void setOptLeaderShipDao(OptLeaderShipDao optLeaderShipDao) {
        this.optLeaderShipDao = optLeaderShipDao;
        setBaseDao(this.optLeaderShipDao);
    }



    public String getNextkey() {
        return optLeaderShipDao.getNextValueOfSequence("S_optLeaderShip");
    }

}
