package com.centit.dispatchdoc.action;


import com.centit.dispatchdoc.po.VSwSearch;
import com.centit.dispatchdoc.service.VSwSearchManager;

public class VSwSearchAction extends IODocCommonBizAction<VSwSearch> {
    private static final long serialVersionUID = 1L;
    private VSwSearchManager VSwSearchMag;

    public void setVSwSearchManager(VSwSearchManager basemgr) {
        VSwSearchMag = basemgr;
        this.setBaseEntityManager(VSwSearchMag);
    }

}
