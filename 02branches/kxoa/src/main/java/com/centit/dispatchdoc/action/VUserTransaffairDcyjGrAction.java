package com.centit.dispatchdoc.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.dispatchdoc.po.VUserTransaffairDcyjGr;
import com.centit.dispatchdoc.service.VUserTransaffairDcyjGrManager;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUnitManager;
	

public class VUserTransaffairDcyjGrAction  extends BaseEntityExtremeAction<VUserTransaffairDcyjGr>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(VUserTransaffairDcyjGrAction.class);
	private static final long serialVersionUID = 1L;
	private VUserTransaffairDcyjGrManager VUserTransaffairDcyjGrMag;
	public void setVUserTransaffairDcyjGrManager(VUserTransaffairDcyjGrManager basemgr)
	{
		VUserTransaffairDcyjGrMag = basemgr;
		this.setBaseEntityManager(VUserTransaffairDcyjGrMag);
	}
	 private SysUnitManager sysUnitManager;
	    private String unitsJson;

	       @SuppressWarnings("unchecked")
        public String list(){
	           Map<Object, Object> paramMap = request.getParameterMap();
	           resetPageParam(paramMap);
	           Map<String, Object> filterMap = convertSearchColumn(paramMap);
	           
	           if (filterMap.containsKey("subItemTypenogw")) {
	               if ("T".equalsIgnoreCase(String.valueOf(filterMap.get("subItemTypenogw")))) {
	                   filterMap.put("IN_subItemType", "FX,XK,CS");
	               } else if ("F".equalsIgnoreCase(String.valueOf(filterMap.get("subItemTypenogw")))) {
	                   filterMap.put("IN_subItemType", "GW");
	               }
	           }
	           
	           FUserDetail loginUser = ((FUserDetail) getLoginUser());
	           filterMap.put("userCode", loginUser.getUsercode());
	           filterMap.put("attUser", loginUser.getUsercode());
	           filterMap.put("IN_bizstate", filterMap.get("bizstate"));
	           if(filterMap.get("leaderName")==null||"".equals(filterMap.get("leaderName").toString())){
	               filterMap.put("leaderName", "havenoname");
	           }
	           pageDesc = makePageDesc();
	           objList = VUserTransaffairDcyjGrMag.listUserTransaffair(filterMap, pageDesc);
	           totalRows = pageDesc.getTotalRows();
	           
	           unitsJson = sysUnitManager.getAllUnitsJSON();
	           
	           request.setAttribute("s_bizstate", request.getParameter("s_bizstate"));
	           request.setAttribute("s_subItemTypenogw", request.getParameter("s_subItemTypenogw"));

	           return LIST;
	       }
	   
	      public SysUnitManager getSysUnitManager() {
	           return sysUnitManager;
	       }


	       public void setSysUnitManager(SysUnitManager sysUnitManager) {
	           this.sysUnitManager = sysUnitManager;
	       }


	       public String getUnitsJson() {
	           return unitsJson;
	       }


	       public void setUnitsJson(String unitsJson) {
	           this.unitsJson = unitsJson;
	       }
	
	
		
}
