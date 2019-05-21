package com.centit.powerbase.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.dao.CodeBook;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.po.VOrgSupPower;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUnitManager;
	

public class VsuppowerinusingAction  extends BaseEntityExtremeAction<Vsuppowerinusing>  {
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private VsuppowerinusingManager vsuppowerinusingMag;
	protected SysUnitManager sysUnitManager;
	public List<VOrgSupPower> orgPowerList;
	public void setVsuppowerinusingManager(VsuppowerinusingManager basemgr)
	{
		vsuppowerinusingMag = basemgr;
		this.setBaseEntityManager(vsuppowerinusingMag);
	}
	
	public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }
	
    @SuppressWarnings("unchecked")
    public String listDocPower() {
	    try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            
            String orgId = "";
            if (filterMap.containsKey("orgId")) {
                orgId = (String) filterMap.get("orgId");
            } else {
                FUserDetail loginUser = ((FUserDetail) getLoginUser());
                filterMap.put("orgId", loginUser.getPrimaryUnit());
            }
//            FUnitinfo unit = sysUnitManager.getObjectById(orgId);
//            filterMap.put("orgId", unit.getDepno());
            
            if (filterMap.containsKey("itemId")) {
                String itemId = (String) filterMap.get("itemId");
                filterMap.put("itemId", "%" + itemId + "%");
            }
            
            filterMap.put(CodeBook.SELF_ORDER_BY, " itemId asc");
            
            pageDesc = makePageDesc();
            
            String hql = "FROM VOrgSupPower WHERE 1=1";
//            String hql = "FROM Vsuppowerinusing WHERE 1=1";
            
            String applyItemType = (String) filterMap.get("applyItemType");
            filterMap.remove("applyItemType");
            if (StringUtils.isNotBlank(applyItemType)) {
                hql += " AND applyItemType in ('" + applyItemType.replace(",", "','") + "')";
            }
            
            orgPowerList = vsuppowerinusingMag.listOrgSuppower(hql, filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            
            request.setAttribute("applyItemType", applyItemType);
            request.setAttribute("itemType", applyItemType);
            request.setAttribute("orgId", orgId);
            request.setAttribute("declare", request.getParameter("declare"));
            
            return "sdDocList";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
	}
	
		
	public String delete() {
	    super.delete();      
	    
	    return "delete";
	}

    public List<VOrgSupPower> getOrgPowerList() {
        return orgPowerList;
    }

    public void setOrgPowerList(List<VOrgSupPower> orgPowerList) {
        this.orgPowerList = orgPowerList;
    }
	
	
}
