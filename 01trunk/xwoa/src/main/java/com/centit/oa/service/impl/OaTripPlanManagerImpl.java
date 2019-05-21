package com.centit.oa.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaTripPlanDao;
import com.centit.oa.po.OaTripPlan;
import com.centit.oa.service.OaTripPlanManager;

public class OaTripPlanManagerImpl extends BaseEntityManagerImpl<OaTripPlan>
implements OaTripPlanManager{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaTripPlanManager.class);
    
    private OaTripPlanDao oaTripPlanDao;
    public void setOaTripPlanDao(OaTripPlanDao baseDao)
    {
        this.oaTripPlanDao = baseDao;
        setBaseDao(this.oaTripPlanDao);
    }
}
