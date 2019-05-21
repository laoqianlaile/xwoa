package com.centit.powerruntime.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.action.OACommonBizAction;
import com.centit.powerruntime.po.OaUnitsLeaderLog;
import com.centit.powerruntime.service.OaUnitsLeaderLogManager;

public class OaUnitsLeaderLogAction extends OACommonBizAction<OaUnitsLeaderLog> {
    private static final Log log = LogFactory
            .getLog(OaUnitsLeaderLogAction.class);
    private static final long serialVersionUID = 1L;
    private OaUnitsLeaderLogManager oaUnitsLeaderLogMag;

    public void setOaUnitsLeaderLogManager(OaUnitsLeaderLogManager basemgr) {
        oaUnitsLeaderLogMag = basemgr;
        this.setBaseEntityManager(oaUnitsLeaderLogMag);
    }
}
