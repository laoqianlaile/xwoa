package com.centit.sys.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.sys.po.UseroptLog;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.UseroptLogManager;
	

public class UseroptLogAction  extends BaseEntityExtremeAction<UseroptLog>  {
    @SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(UseroptLogAction.class);
	private static final long serialVersionUID = 1L;
	private UseroptLogManager useroptLogMag;
	private OaPowerrolergroupManager oaPowerrolergroupManager;
	public void setUseroptLogManager(UseroptLogManager basemgr)
	{
		useroptLogMag = basemgr;
		this.setBaseEntityManager(useroptLogMag);
	}

	    public String list() {
	     try {
            FUserDetail fuser = (FUserDetail) getLoginUser();
             if(fuser==null){
                 return "login";
             }
             @SuppressWarnings("unchecked")
             Map<Object, Object> paramMap = request.getParameterMap();
             resetPageParam(paramMap);

             Map<String, Object> filterMap = convertSearchColumn(paramMap);
             PageDesc pageDesc = makePageDesc();
             objList=useroptLogMag.listObjects(filterMap, pageDesc);
             totalRows = pageDesc.getTotalRows();
             setbackSearchColumn(filterMap);
             JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
             request.setAttribute("userjson", userjson);
             
             return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
	     
	 }
	 
	 @Override
	    public String delete() {
	     try {
            FUserDetail fuser=(FUserDetail)getLoginUser();
             if(fuser==null){
                 return "login";
             }
             UseroptLog useroptLog=useroptLogMag.getObjectById(object.getId());
             if(null!=useroptLog){
                 useroptLogMag.deleteObjectById(object.getId());
             }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
	    
	     return this.list();
	 }
	
	 
	 public OaPowerrolergroupManager getOaPowerrolergroupManager() {
	        return oaPowerrolergroupManager;
	    }

	    public void setOaPowerrolergroupManager(
	            OaPowerrolergroupManager oaPowerrolergroupManager) {
	        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
	    }
		
}
