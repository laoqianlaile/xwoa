package com.centit.dispatchdoc.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.po.IncomeProject;
import com.centit.dispatchdoc.service.IncomeProjectManager;
	

public class IncomeProjectAction  extends IODocCommonBizAction<IncomeProject>  {
	private static final Log log = LogFactory.getLog(IncomeProjectAction.class);
	private static final long serialVersionUID = 1L;
	private IncomeProjectManager incomeProjectMgr;
	
	
	/**
     * 获取建设项目信息
     * @author hll 2013-11-26 epowersd
     * @return
     */
	public void getProjectInfo() {
	    String djId = object.getDjId();
	    object = incomeProjectMgr.getObjectById(djId);
	    
	    if (null == object) {
	        IncomeProject incomeProject = new IncomeProject();
	        incomeProject.setDjId(djId);
	        object = incomeProject;
	    }
	}
	
	/**
     * 编辑建设项目信息
     * @author hll 2013-11-23 epowersd
     * @return
     */
    public String edit() {
        try {
            getProjectInfo();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
        
        return request.getParameter("retVal");
    }
    
    /**
     * 查看建设项目信息
     * @author hll 2013-11-23 epowersd
     * @return
     */
    public String view() {
        try {
            getProjectInfo();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
        
        return request.getParameter("retVal");
    }
    
    
    public String saveApprovalInfo() {
        try {
            String djId = object.getDjId();
            
            IncomeProject incomeProject = incomeProjectMgr.getObjectById(djId);
            
            incomeProjectMgr.copyObjectNotNullProperty(incomeProject, object);
            
            object = incomeProject;
            
            incomeProjectMgr.saveObject(object);
            
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
        return null;
    }
	
	/////////////////////////////////////////////////////////////////
	public void setIncomeProjectManager(IncomeProjectManager basemgr)
	{
	    incomeProjectMgr = basemgr;
		this.setBaseEntityManager(incomeProjectMgr);
	}
		
}
