package com.centit.sys.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.dispatchdoc.service.VuserTaskListOAManager;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.UserbizoptLogManager;
	

public class UserbizoptLogAction  extends BaseEntityExtremeAction<UserbizoptLog>  {
	private static final Log log = LogFactory.getLog(UserbizoptLogAction.class);
	private static final long serialVersionUID = 1L;
	private UserbizoptLogManager userbizoptLogMag;
	private OptProcInfoManager optProcInfoManager;
	private VuserTaskListOAManager vuserTaskListOAManager;
	private String mark;
	public void setUserbizoptLogManager(UserbizoptLogManager basemgr)
	{
		userbizoptLogMag = basemgr;
		this.setBaseEntityManager(userbizoptLogMag);
	}
	
	/**
	 * 获得是否办理
	 * @return
	 */
	public String getProcessInfo(){
	    FUserDetail fuser = ((FUserDetail) getLoginUser());
	    String usercode=fuser.getUsercode();
	    String djId=request.getParameter("djId");
	    //获得当前办件的下一节点
	    String nodeinstids = vuserTaskListOAManager.getCurrentNodeInstId(djId);
	    String [] s=null;
	    if(nodeinstids!=null){
	        s=nodeinstids.split(",");
	    }
	    mark=null;
	    //Long nodeinstid=Long.parseLong(request.getParameter("nodeinstid"));
	    if(s!=null){
	        for(int i=0;i<s.length;i++){
	            Long nodeinstid=Long.parseLong(s[i]);
	            //查看当前办件除了当前用户外是否有人阅读过
	            List<UserbizoptLog> l=userbizoptLogMag.listObjectNotSelf(djId, usercode, nodeinstid);
	            if(l!=null&&l.size()>0){//办件已被阅读
	                //在日志里查看当前办件是否已经有用户办理过了
	                List<OptIdeaInfo> p= optProcInfoManager.listIdeaLogsByNodeInstIdAndDjId(nodeinstid, djId);
	                if(p!=null&&p.size()>0){//办件已被办理
	                    mark="haveprocess";
	                    break;
	                }else{
	                    mark="haveread";
	                }
	            }else{//没有被阅读，可以撤消
	                if(mark==null){
	                    mark="noread";
	                }
	            }
	        }
	    }else {
	        mark="noread";
	    }
	    return "getprocessinfo";
	}

    public OptProcInfoManager getOptProcInfoManager() {
        return optProcInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public VuserTaskListOAManager getVuserTaskListOAManager() {
        return vuserTaskListOAManager;
    }

    public void setVuserTaskListOAManager(
            VuserTaskListOAManager vuserTaskListOAManager) {
        this.vuserTaskListOAManager = vuserTaskListOAManager;
    }
	
    
	
	
		
}
