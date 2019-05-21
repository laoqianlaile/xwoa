package com.centit.oa.action;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaCommonType;
import com.centit.oa.service.OaCommonTypeManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
	
/**
 * 资源类型
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2014-6-16
 * @version
 */
public class OaCommonTypeAction  extends BaseEntityDwzAction<OaCommonType>  {
	private static final Log log = LogFactory.getLog(OaCommonTypeAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private OaCommonTypeManager oaCommonTypeMag;
	public void setOaCommonTypeManager(OaCommonTypeManager basemgr)
	{
		oaCommonTypeMag = basemgr;
		this.setBaseEntityManager(oaCommonTypeMag);
	}
      /**
       * 列表数据：自己创建的and公开的
       */
	@SuppressWarnings("unchecked")
    public String list() {
        try {
           
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
//            filterMap.put("owner", user.getUsercode());//所有者：自己创建的and公开的 预留
            // state启用标识 ，排除禁用（启用以及未置状态）
            filterMap.put("NP_state", true);

            if (("true").equals(filterMap.get("isBoth"))) {
                filterMap.remove("NP_state");
            }
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaCommonTypeMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
	

    
	 public String save() {
	        FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
	        if (StringUtils.isBlank(object.getNo())) {// 录入人员,录入时间 在新增是加入，且维护时不修改
	            object.setCreater(user.getUsercode());
	            object.setCreatertime(DatetimeOpt.currentUtilDate());
	            
	        }
	         super.save();
	         return this.list();
	    } 
	
	
	
	public static Log getLog() {
        return log;
    }
    public String delete() {
	    super.delete();      
	    
	    return this.list();
	}
    
    /**
     * 批量删除功能
     * @return
     */
    public String deleteIds() {
        //批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
        for (String no : ids.split(",")) {
            //存放作修改字段
            object.setNo(no);
            super.delete();    
         }
        }
        return this.list();
    }
}
