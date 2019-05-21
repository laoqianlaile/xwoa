package com.centit.oa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaKqvisitorgroup;
import com.centit.oa.service.OaKqvisitorgroupManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateUtil;
	

public class OaKqvisitorgroupAction  extends BaseEntityDwzAction<OaKqvisitorgroup>  {
	private static final Log log = LogFactory.getLog(OaKqvisitorgroupAction.class);
	private static final long serialVersionUID = 1L;
	private OaKqvisitorgroupManager oaKqvisitorgroupMag;
	private OaPowerrolergroupManager oaPowerrolergroupManager;
	public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }
    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    public void setOaKqvisitorgroupManager(OaKqvisitorgroupManager basemgr)
	{
		oaKqvisitorgroupMag = basemgr;
		this.setBaseEntityManager(oaKqvisitorgroupMag);
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
          
            //默认查询当前月份第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("begTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }  
            objList = oaKqvisitorgroupMag.listObjects(filterMap, pageDesc);
            totalRows =objList!=null?objList.size():0; 
            setbackSearchColumn(filterMap);
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }	
    public String save() {
        try {

            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            
            if (StringUtils.isBlank(object.getDjId())) {// 录入人员,录入时间
                object.setCreater(user.getUsercode());
                object.setCreatetime(DatetimeOpt.currentUtilDate());
            }
            object.setCreatetime(DatetimeOpt.currentUtilDate());
            super.save();
            return this.list();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }	
    
    /**
     * 批量删除功能
     * @return
     */
    public String deleteIds() {
        //批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
        for (String djid : ids.split(",")) {
            //存放作修改字段
            object.setDjId(djid);
            oaKqvisitorgroupMag.deleteObject(object);    
         }
        }
        return this.list();
    }   
    
    public void exportExcelKqv() throws IOException {
        this.list();
        List<Object[]> chosedList = new ArrayList<Object[]>();
     // 获取需要生成Excel的数据
        if (objList != null) {
            int i=1;
            for (OaKqvisitorgroup o : objList) {                
                Object[] temp= new Object[17];
                temp[0]=i++;//编号
                temp[1]=DatetimeOpt.convertDateToString(o.getApprotime(), "yyyy/MM/dd");//日期
                temp[2]=o.getApprovalremark();//接待对象
                temp[3]=o.getBodycontent();//来访目的
                temp[4]=o.getApproval();//人数
                temp[5]="ZW".equals(o.getJdtype())?"√":"";//接待类型 政务
                temp[6]="SW".equals(o.getJdtype())?"√":"";//接待类型  商务
                temp[7]=o.getTravel();//行程安排
                temp[8]="0".equals(o.getMealplace())?"√":"";//是否用餐
                temp[9]=o.getLodgingcase();//用餐人数
                temp[10]="1".equals(o.getLeavetime())?"√":"";//是否在外食宿
                temp[11]=o.getMealcase();//陪同领导
                temp[12]=o.getKqdepname();//接待部门
                temp[13]=o.getMeetplase();//接待人员
                temp[14]=CodeRepositoryUtil.getValue("usercode",o.getCreater());//经办人
                temp[15]=o.getRemark();//备注
                temp[16]=CodeRepositoryUtil.getValue("usercode",o.getCreater());//经办人审核
                chosedList.add(temp);
            }
        }
        //组织数据 访问路径、表格名称、大标题等
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        String begdate=filterMap.get("begTime").toString();
        String enddate=filterMap.get("endTime").toString();
        BizCommUtil.doPoi2ExcelByTemplate(request.getRealPath("/")+"writmodel/访客团统计.xls", "来访客团登记表",begdate+"至"+enddate+ "徐圩新区来访客团统计",chosedList,3);
}
}
