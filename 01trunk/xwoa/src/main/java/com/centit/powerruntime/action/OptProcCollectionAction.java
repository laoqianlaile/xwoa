package com.centit.powerruntime.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerruntime.po.OptProcCollection;
import com.centit.powerruntime.service.OptProcCollectionManager;

public class OptProcCollectionAction extends
        BaseEntityExtremeAction<OptProcCollection> {
    private static final Log log = LogFactory
            .getLog(OptProcCollectionAction.class);
    private static final long serialVersionUID = 1L;
    private OptProcCollectionManager optProcCollectionMag;

    public void setOptProcCollectionManager(OptProcCollectionManager basemgr) {
        optProcCollectionMag = basemgr;
        this.setBaseEntityManager(optProcCollectionMag);
    }

}
