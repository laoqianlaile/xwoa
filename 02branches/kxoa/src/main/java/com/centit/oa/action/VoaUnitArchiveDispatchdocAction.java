package com.centit.oa.action;

import java.util.Date;
import java.util.Map;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VoaUnitArchiveDispatchdoc;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;
	

		

import com.centit.oa.service.VoaUnitArchiveDispatchdocManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.util.DateUtil;
	

public class VoaUnitArchiveDispatchdocAction  extends OACommonBizAction<VoaUnitArchiveDispatchdoc>  {
	private static final Log log = LogFactory.getLog(VoaUnitArchiveDispatchdocAction.class);
	private static final long serialVersionUID = 1L;
	private VoaUnitArchiveDispatchdocManager voaUnitArchiveDispatchdocMag;
	public void setVoaUnitArchiveDispatchdocManager(VoaUnitArchiveDispatchdocManager basemgr)
	{
		voaUnitArchiveDispatchdocMag = basemgr;
		this.setBaseEntityManager(voaUnitArchiveDispatchdocMag);
	}
	//发文归档列表
    @SuppressWarnings("unchecked")
    public String list(){
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            
            //filterMap.put("usercode", getLoginUserCode());
            filterMap.put("NP_id", true);
          //默认查询当年第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("begincomeDate"))&&StringUtils.isBlank((String)filterMap.get("endincomeDate"))){
                filterMap.put("begincomeDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endincomeDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = voaUnitArchiveDispatchdocMag.listObjects(filterMap,pageDesc);
            totalRows = pageDesc.getTotalRows();
            this.pageDesc = pageDesc;
            filterMap.put("fromMenu", "FWDGD");
            setbackSearchColumn(filterMap);
            //return LIST;
            return "listV2";
        }catch(Exception e){
            e.printStackTrace();
            return ERROR;
        }
    }

  //获取登录人员信息
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }
	
		
}