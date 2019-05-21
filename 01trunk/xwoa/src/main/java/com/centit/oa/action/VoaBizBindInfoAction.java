package com.centit.oa.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VoaBizBindInfo;
import com.centit.oa.service.VoaBizBindInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.DateUtil;
	

public class VoaBizBindInfoAction  extends BaseEntityExtremeAction<VoaBizBindInfo>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(VoaBizBindInfoAction.class);
	private static final long serialVersionUID = 1L;
	private VoaBizBindInfoManager voaBizBindInfoMag;
	private String bjType;
	public void setVoaBizBindInfoManager(VoaBizBindInfoManager basemgr)
	{
		voaBizBindInfoMag = basemgr;
		this.setBaseEntityManager(voaBizBindInfoMag);
	}
	/**
	 * 流程关联事权及发文的列表
	 * @return
	 */
	public String oaOpenGLSQ(){//(原方法不支持条件查询)dk 2015-12-28 
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            if(fuser==null){
                return "login";
            }
            Map<Object, Object> paramMap = request.getParameterMap();
            String itemtype = (String)request.getParameter("s_itemtype");
            String djid=(String)request.getParameter("s_djId");
            if(StringUtils.isNotBlank(djid)){
                object.setDjId(djid);
            }
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            
            if(filterMap.containsKey("djId")){
                filterMap.remove("djId");
            }
            filterMap.put("djIdno", object.getDjId());//可关联签报不显示其本身
            filterMap.put("djIdnoin", object.getDjId());
            filterMap.put("itemType", itemtype);
            //默认查询当前月份第一天到现在的记录
           /* if(!"FW".equals(itemtype)){
                if(StringUtils.isBlank((String)filterMap.get("createStartDate"))&&StringUtils.isBlank((String)filterMap.get("createEndDate"))){
                    filterMap.put("createStartDate",DateUtil.getCurrentMonthOfDay() );
                    filterMap.put("createEndDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
                }
            }*/
            //按发文号查询
            if(StringUtils.isNotBlank((String)filterMap.get("dispatchDocNo"))){
                //filterMap.remove("dispatchDocNo");
                filterMap.put("dispatchDocNoli", "%"+(String)filterMap.get("dispatchDocNo")+"%");
            }
            //列表页面查看只显示与其用户相关信息
           if(fuser!=null){
                filterMap.put("usercode", fuser.getUsercode());
            }
            
            
            
            objList = voaBizBindInfoMag.listVoaBizBindInfo(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            
            bjType = (String)request.getParameter("s_itemtype");
            request.setAttribute("startDjid", object.getDjId());
            request.setAttribute("s_itemtype", itemtype);
            request.setAttribute("bjType", bjType);
            String nodeInstId = (String)request.getParameter("nodeInstId");
           
            setbackSearchColumn(filterMap);
            if(StringUtils.isNotBlank(nodeInstId)){
                Long nodeId = Long.parseLong(nodeInstId);  
                request.setAttribute("nodeInstId", nodeId);
            }
            
            
            return "oaOpenGLSQ";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ERROR;
        }
        
        
	    
	}

    public String getBjType() {
        return bjType;
    }

    public void setBjType(String bjType) {
        this.bjType = bjType;
    }
	
		
}
