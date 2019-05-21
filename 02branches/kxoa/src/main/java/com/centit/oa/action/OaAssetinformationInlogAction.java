package com.centit.oa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaAssetinformation;
import com.centit.oa.po.OaAssetinformationBond;
import com.centit.oa.po.OaAssetinformationInlog;
import com.centit.oa.service.OaAssetinformationBondManager;
import com.centit.oa.service.OaAssetinformationInlogManager;
import com.centit.oa.service.OaAssetinformationManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateUtil;
	

public class OaAssetinformationInlogAction  extends BaseEntityDwzAction<OaAssetinformationInlog>  {
	private static final long serialVersionUID = 1L;
	private String s_supEquipmentType;//资产类型
	private String datacode;
	private OaAssetinformationInlogManager oaAssetinformationInlogMag;
	private OaAssetinformationManager oaAssetinformationManager;
	private OaAssetinformationBondManager oaAssetinformationBondManager;
	private String  begcreatertime;//记录开始时间
    private String  endcreatertime;//结束时间
    private List<OaAssetinformationInlog> assetinList;
	public void setOaAssetinformationInlogManager(OaAssetinformationInlogManager basemgr)
	{
		oaAssetinformationInlogMag = basemgr;
		this.setBaseEntityManager(oaAssetinformationInlogMag);
	}
	
    public void setOaAssetinformationBondManager(
            OaAssetinformationBondManager oaAssetinformationBondManager) {
        this.oaAssetinformationBondManager = oaAssetinformationBondManager;
    }

    public void setOaAssetinformationManager(
            OaAssetinformationManager oaAssetinformationManager) {
        this.oaAssetinformationManager = oaAssetinformationManager;
    }
    /*
     * 展示Form里的list
     */
    public String list(){
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        PageDesc pageDesc = makePageDesc();
        String no =(String)request.getParameter("no");
        //默认查询当前月份第一天到现在的记录
        if(StringUtils.isBlank((String)filterMap.get("begcreatertime"))&&StringUtils.isBlank((String)filterMap.get("endcreatertime"))){
            begcreatertime = DateUtil.getCurrentMonthOfDay();
            endcreatertime = DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd");
            filterMap.put("begcreatertime",begcreatertime );
            filterMap.put("endcreatertime", endcreatertime);
        }else{
            begcreatertime =(String)filterMap.get("begcreatertime");
        }
        assetinList = oaAssetinformationInlogMag.assetinList(no, filterMap,pageDesc);
        for(OaAssetinformationInlog s : assetinList){
            List<OaAssetinformationBond> o = oaAssetinformationBondManager.listOaAssetinformation(s.getDjid());
            if(o!=null &&o.size()>0){
                s.setAssetunit("T");
            }
        }
        totalRows =pageDesc.getTotalRows();
        request.setAttribute("no", no);
        setbackSearchColumn(filterMap);
        return LIST;
    }
    /**
     * 导出Excel 通讯录
     * 
     * @throws IOException
     */
    public void exportExcelByPo() throws IOException {
        this.list();
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
     // 获取需要生成Excel的数据
        if (assetinList != null && assetinList.size()>0) {
            for (OaAssetinformationInlog o : assetinList) {
                Object[] temp= new Object[4];
                temp[0]=o.getAssetnum();
                temp[1]=CodeRepositoryUtil.getValue("usercode", o.getCreater());
                temp[2]=DatetimeOpt.convertSqlDate(o.getCreatetime());
                temp[3]=o.getCreateRemark();
                chosedList.add(temp);
               
            }
        }
        String[] header = { "进库数量","记录者","记录时间", "记录备注"};// 列头
        String titleName = "在"+begcreatertime+"至"+endcreatertime+"入库情况";
        BizCommUtil.doPoi2Excel(titleName, header, chosedList);
    }
    
    @Override
	public String edit(){
	    super.edit();
	    String no =(String)request.getParameter("no");
	    datacode =(String)request.getParameter("datacode");
	    this.list();
	    object.setNo(datacode);
	    if(StringUtils.isBlank(object.getDjid())){
	        object.setDjid(oaAssetinformationInlogMag.getNextKey());
	    }
	    request.setAttribute("no", no);
	    return EDIT;
	}
	@Override
	public String save(){
	    //保存进库信息
	    FUserDetail fuser = (FUserDetail) getLoginUser();
	    object.setCreater(fuser.getUsercode());
	    object.setCreatetime(new Date(System.currentTimeMillis()));
	    oaAssetinformationInlogMag.saveObject(object);
	    //同步保存资产信息
	    String no = (String)request.getParameter("no");
	    if(StringUtils.isNotBlank(no)){
	        OaAssetinformation oaAssetinformation =oaAssetinformationManager.getObjectById(no);
	        oaAssetinformation.setAssetnum(object.getAssetnum()+oaAssetinformation.getAssetnum());
	        oaAssetinformationManager.saveObject(oaAssetinformation);
	    }
	    return this.edit();
	}
    public String getS_supEquipmentType() {
        return s_supEquipmentType;
    }
    public void setS_supEquipmentType(String s_supEquipmentType) {
        this.s_supEquipmentType = s_supEquipmentType;
    }
    public String getDatacode() {
        return datacode;
    }
    public void setDatacode(String datacode) {
        this.datacode = datacode;
    }

    public String getBegcreatertime() {
        return begcreatertime;
    }

    public void setBegcreatertime(String begcreatertime) {
        this.begcreatertime = begcreatertime;
    }

    public String getEndcreatertime() {
        return endcreatertime;
    }

    public void setEndcreatertime(String endcreatertime) {
        this.endcreatertime = endcreatertime;
    }

    public List<OaAssetinformationInlog> getAssetinList() {
        return assetinList;
    }

    public void setAssetinList(List<OaAssetinformationInlog> assetinList) {
        this.assetinList = assetinList;
    }
		
}
