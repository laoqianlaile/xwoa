package com.centit.oa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaInfosummary;
import com.centit.oa.po.VOaInformation;
import com.centit.oa.service.OaInfosummaryManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.sys.service.CodeRepositoryUtil;
	

public class OaInfosummaryAction  extends BaseEntityDwzAction<OaInfosummary>  {
	private static final Log log = LogFactory.getLog(OaInfosummaryAction.class);
	private static final long serialVersionUID = 1L;
	private OaInfosummaryManager oaInfosummaryMag;
	private List<OaInfosummary> oaInfoSumList;
	public void setOaInfosummaryManager(OaInfosummaryManager basemgr)
	{
		oaInfosummaryMag = basemgr;
		this.setBaseEntityManager(oaInfosummaryMag);
	}

    /**
     * 列表数据
     */
    public String list() {
        try {

            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            filterMap.put("no", request.getParameter("infoNo"));
            objList = oaInfosummaryMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            oaInfoSumList = objList;
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    public void exportExcelAct() throws IOException {
        this.list();
        List<OaInfosummary> objectList =new ArrayList<OaInfosummary>();
        objectList = oaInfoSumList;         
        
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
     // 获取需要生成Excel的数据
        if (objectList != null) {
            int i=1;
            for (OaInfosummary o : objectList) {                
                Object[] temp= new Object[6];
                temp[0]=i++;
                temp[1]=CodeRepositoryUtil.getValue("usercode",o.getCreater());
                temp[2]=CodeRepositoryUtil.getValue("sex",o.getSex());
                temp[3]=CodeRepositoryUtil.getValue("unitcode",o.getUnitcode()); 
                temp[4]=o.getTelephone();
                temp[5]=o.getRemark();
                chosedList.add(temp);                    
               
            }
        }
        String[] header = {"序号","姓名","性别","部门","联系方式","备注"};// 列头
       BizCommUtil.doPoi2Excel("活动情况统计表", header, chosedList);
        
}
	
		
}
