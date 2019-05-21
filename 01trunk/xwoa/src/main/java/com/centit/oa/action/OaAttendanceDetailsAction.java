package com.centit.oa.action;


import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.po.OaAttendanceDetails;
import com.centit.oa.service.OaAttendanceDetailsManager;

public class OaAttendanceDetailsAction extends BaseEntityExtremeAction<OaAttendanceDetails>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private OaAttendanceDetailsManager oaAttendanceDetailsMgr;
    
    public OaAttendanceDetailsManager getOaAttendanceDetailsMgr() {
        return oaAttendanceDetailsMgr;
    }

    public void setOaAttendanceDetailsMgr(OaAttendanceDetailsManager oaAttendanceDetailsMgr) {
        this.oaAttendanceDetailsMgr = oaAttendanceDetailsMgr;
        this.setBaseEntityManager(oaAttendanceDetailsMgr);
    }
}
    