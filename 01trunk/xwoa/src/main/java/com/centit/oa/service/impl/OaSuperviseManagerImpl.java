package com.centit.oa.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VoadDcdbNum;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.dao.OaSuperviseDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.workflow.sample.dao.WfFlowDefineDao;


public class OaSuperviseManagerImpl extends BaseEntityManagerImpl<OaSupervise>
	implements OaSuperviseManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaSuperviseManager.class);

	private OaSuperviseDao oaSuperviseDao ;
	public void setOaSuperviseDao(OaSuperviseDao baseDao)
	{
		this.oaSuperviseDao = baseDao;
		setBaseDao(this.oaSuperviseDao);
	}
    private WfFlowDefineDao flowDefineDao;
    public WfFlowDefineDao getFlowDefineDao() {
        return flowDefineDao;
    }
    public void setFlowDefineDao(WfFlowDefineDao flowDefineDao) {
        this.flowDefineDao = flowDefineDao;
    }
    @Override
    public String getNextKey() {
        
        return "DCDB"+oaSuperviseDao.getNextKeyBySequence("s_OaSupervise", 12);
       
    }
    @Override
    public List<VoadDcdbNum> getdcdbnum(String supdjid) {
        // TODO Auto-generated method stub
        return oaSuperviseDao.getdcdbnum(supdjid);
    }
    @Override
    public List<OaSupervise> getSuplist(String state) {
        List<OaSupervise> suplist=oaSuperviseDao.getSuplist(state);
   
        return suplist;
        
        
    }
    @Override
    public Long getLastVersion(String flowcode) {
        // TODO Auto-generated method stub
        return flowDefineDao.getLastVersion(flowcode);
    }
    @Override
    public List<OaSupervise> listOaSupervise(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return oaSuperviseDao.listOaSupervise(filterMap,pageDesc);
    }
    
    
  
}

