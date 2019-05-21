package com.centit.sys.action;

import org.apache.log4j.Logger;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.sys.po.VUserOnline;
import com.centit.sys.service.VUserOnlineManager;

public class VUserOnlineAction extends BaseEntityExtremeAction<VUserOnline>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private VUserOnlineManager vUserOnlineMgr;
    private static final Logger logger = Logger.getLogger(VUserOnlineAction.class);
    
    
    
    
    public VUserOnlineManager getVUserOnlineMgr() {
        return vUserOnlineMgr;
    }

    public void setVUserOnlineMgr(VUserOnlineManager vUserOnlineMgr) {
        this.vUserOnlineMgr = vUserOnlineMgr;
        this.setBaseEntityManager(vUserOnlineMgr);
    }
}
    