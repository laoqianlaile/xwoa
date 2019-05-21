package com.centit.dispatchdoc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.DispatchDoc;
import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.po.VTransactUser;
import com.centit.dispatchdoc.po.VoadDcdbNum;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.dispatchdoc.dao.VoadDcdbNumDao;
import com.centit.dispatchdoc.dao.VuserTaskListOADao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.dispatchdoc.service.VuserTaskListOAManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class VuserTaskListOAManagerImpl extends BaseEntityManagerImpl<VuserTaskListOA>
	implements VuserTaskListOAManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(VuserTaskListOAManager.class);

	private VuserTaskListOADao vuserTaskListOADao ;
	private VoadDcdbNumDao voadDcdbNumDao;
	private DispatchDocManager dispatchDocManager;
    private IncomeDocManager incomeDocManager;
    public void setVoadDcdbNumDao(VoadDcdbNumDao voadDcdbNumDao) {
	    this.voadDcdbNumDao = voadDcdbNumDao;
     }
	public void setVuserTaskListOADao(VuserTaskListOADao baseDao)
	{
		this.vuserTaskListOADao = baseDao;
		setBaseDao(this.vuserTaskListOADao);
	}
	
    public void setDispatchDocManager(DispatchDocManager dispatchDocManager) {
        this.dispatchDocManager = dispatchDocManager;
    }
    public void setIncomeDocManager(IncomeDocManager incomeDocManager) {
        this.incomeDocManager = incomeDocManager;
    }
    @Override
    public List<VoadDcdbNum> getdcdbnum(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return voadDcdbNumDao.listObjects(filterMap);
    }
    @Override
    public boolean checkUserTask(Long nodeInstId, String userCode) {
        // TODO Auto-generated method stub
        return vuserTaskListOADao.checkUserTask(nodeInstId, userCode);
    }
	@Override
	public List<VuserTaskListOA> listObjects(Map<String, Object> filterMap,
	        PageDesc pageDesc) {
	    String sHql = " from VuserTaskListOA t where 1=1 ";
        String F_userBizopt_log="";
        if(null!=filterMap.get("notInUserBizoptLog")){
            F_userBizopt_log=(String)filterMap.get("F_userBizopt_log");
        }
        if(StringUtils.hasText(F_userBizopt_log)){
            sHql+="and t.djId not in (" +
                  "select f.dj_id from F_userBizopt_log f where f.createUser= "+HQLUtils.buildHqlStringForSQL(F_userBizopt_log);
            
            sHql+=" ) ";
        }
        List<VuserTaskListOA> list=baseDao.listObjects(sHql, filterMap, pageDesc);
	    return list;
	}

    /**
     * 获取办件提醒状态：收发文添加办件是否超时提醒
     * @param o
     */
    public String getOverDueState(VuserTaskListOA o){
        
        String djid = o.getDjId();
        
        String overdueType = null;
        
        if("FW".equals(o.getItemtype())){
            DispatchDoc doc = dispatchDocManager.getObjectById(djid);
            if(null != doc){
                overdueType = BizCommUtil.getOverDueState(doc.getToBeanfinishedDate());                
            }
        }else if("SW".equals(o.getItemtype())){
            IncomeDoc doc = incomeDocManager.getObjectById(djid);
            if(null != doc){
                overdueType = BizCommUtil.getOverDueState(doc.getToBeanfinishedDate());                
            }
        }
        
         return overdueType;
    }
    
    /**
     * 获取当前步骤中待办及已办的人员
     * @param nodeinstId
     * @return
     */    
    public List<VTransactUser> getCurrentTransactUsers(String djId){
        List<Object[]> list = vuserTaskListOADao.getCurrentTransactUsers(djId);
        
        List<VTransactUser> users = new ArrayList<VTransactUser>();
        
        if(null != list && !list.isEmpty()){
            for(Object[] o : list){
                VTransactUser user = new VTransactUser();
                user.setUnitname(null != o[0] ? o[0].toString() : null);
                user.setUsercode(null != o[1] ? o[1].toString() : null);
                user.setTaskState(null != o[2] ? o[2].toString() : null);
                user.setNodename(null != o[3] ? o[3].toString() : null);
                users.add(user);
            }
        }
        
        return users;
    }
    
    /**
     * 获取办件当前的办理步骤，如果有多个的，就用逗号隔开
     * @param djId
     * @return
     */
    public String getCurrentNodeNames(String djId){
        
        List<Object[]> list = vuserTaskListOADao.getCurrentNodeNames(djId);
        String nodeNames = null;
        
        if(null != list && !list.isEmpty()){
            Object o = (Object)list.get(0);
            nodeNames = null != o ? o.toString() : null;
        }
        
        return nodeNames;
    }
    /**
     * 获取办件当前的节点nodeInstId，如果有多个的，就用逗号隔开
     * @param djId
     * @return
     */
    @Override
    public String getCurrentNodeInstId(String djId) {
        List<Object[]> list = vuserTaskListOADao.getCurrentNodeInstId(djId);
        String nodeInstId = null;
        
        if(null != list && !list.isEmpty()){
            Object o = (Object)list.get(0);
            nodeInstId = null != o ? o.toString() : null;
        }
        
        return nodeInstId;
    }
}

