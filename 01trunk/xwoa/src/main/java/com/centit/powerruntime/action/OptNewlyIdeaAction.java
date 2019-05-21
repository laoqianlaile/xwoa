package com.centit.powerruntime.action;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerruntime.po.OptNewlyIdea;
import com.centit.powerruntime.service.OptNewlyIdeaManager;

public class OptNewlyIdeaAction extends BaseEntityExtremeAction<OptNewlyIdea> {
    private static final long serialVersionUID = 1L;
    private OptNewlyIdeaManager optNewlyIdeaMag;

    public void setOptNewlyIdeaManager(OptNewlyIdeaManager basemgr) {
        optNewlyIdeaMag = basemgr;
        this.setBaseEntityManager(optNewlyIdeaMag);
    }

}
