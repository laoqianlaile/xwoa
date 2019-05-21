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
import com.centit.oa.po.OaAssetinformationOutlog;
import com.centit.oa.service.OaAssetinformationBondManager;
import com.centit.oa.service.OaAssetinformationManager;
import com.centit.oa.service.OaAssetinformationOutlogManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.DateUtil;

public class OaAssetinformationOutlogAction extends
        BaseEntityDwzAction<OaAssetinformationOutlog> {
    private static final long serialVersionUID = 1L;
    private String s_supEquipmentType;// 资产类型
    private String isDept;//机构属性类型
    private String datacode;
    private OaAssetinformationManager oaAssetinformationManager;
    private OaAssetinformationOutlogManager oaAssetinformationOutlogMag;
    private OaAssetinformationBondManager oaAssetinformationBondManager;
    private SysUserManager sysUserManager;
    private SysUnitManager sysUnitManager;
    private String  begcreatertime;//记录开始时间
    private String  endcreatertime;//结束时间
    private List<FUnitinfo> unitlist;//出库部门
    private List<OaAssetinformationOutlog> assetinList;

    public void setOaAssetinformationOutlogManager(
            OaAssetinformationOutlogManager basemgr) {
        oaAssetinformationOutlogMag = basemgr;
        this.setBaseEntityManager(oaAssetinformationOutlogMag);
    }
    public void setOaAssetinformationBondManager(
            OaAssetinformationBondManager oaAssetinformationBondManager) {
        this.oaAssetinformationBondManager = oaAssetinformationBondManager;
    }
    public void setOaAssetinformationManager(
            OaAssetinformationManager oaAssetinformationManager) {
        this.oaAssetinformationManager = oaAssetinformationManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    private List<OaAssetinformationBond> oaAssetinformationBonds;

    public List<OaAssetinformationBond> getNewOaAssetinformationBonds() {
        return this.oaAssetinformationBonds;
    }

    public void setNewOaAssetinformationBonds(
            List<OaAssetinformationBond> oaAssetinformationBonds) {
        this.oaAssetinformationBonds = oaAssetinformationBonds;
    }
    /*
     * 展示Form里的list
     */
    public String list(){
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        FUserDetail user = ((FUserDetail)getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
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
            endcreatertime =(String)filterMap.get("endcreatertime");
        }
        assetinList = oaAssetinformationOutlogMag.assetinList(no, filterMap,pageDesc);
        for(OaAssetinformationOutlog s : assetinList){
            List<OaAssetinformationBond> o = oaAssetinformationBondManager.listOaAssetinformation(s.getDjid());
            if(o!=null &&o.size()>0){
               s.setAssetunit(o.get(0).getNo()); 
            }
        }
        String sParentUnit = dept.getUnitcode();
        unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
        totalRows =pageDesc.getTotalRows();
        request.setAttribute("no", no);
        setbackSearchColumn(filterMap);
        return LIST;
    }
    //删除关联
    @Override
    public String delete(){
        List<OaAssetinformationBond> o = oaAssetinformationBondManager.listOaAssetinformation(object.getDjid());
        oaAssetinformationBondManager.deleteObjectById(o.get(0).getCid());
        object = oaAssetinformationOutlogMag.getObjectById(object.getDjid());
        object.setAssetunit(null);//同时设置出库关联记录为空
        oaAssetinformationOutlogMag.saveObject(object);
        return this.list();
    }

    @Override
    public String edit() {
        super.edit();
        String no = (String) request.getParameter("no");
        datacode = (String) request.getParameter("datacode");
        this.list();
        object.setNo(datacode);
        if(StringUtils.isBlank(object.getDjid())){
            object.setDjid(oaAssetinformationOutlogMag.getNextKey());
        }
        request.setAttribute("no", no);
        return EDIT;
    }
    @Override
    public String save(){
        //保存资产出库信息
        FUserDetail fuser = (FUserDetail) getLoginUser();
        FUserunit funit = sysUserManager.getUserPrimaryUnit(fuser
                .getUsercode());
        FUnitinfo unitinfo = sysUnitManager.getObjectById(funit
                .getUnitcode());
        object.setCreater(fuser.getUsercode());
        object.setCreatetime(new Date(System.currentTimeMillis()));
        object.setApplyUnitcode(unitinfo.getUnitcode());
        oaAssetinformationOutlogMag.saveObject(object);
        //同步保存资产信息
        String no = (String)request.getParameter("no");
        if(StringUtils.isNotBlank(no)){
            OaAssetinformation oaAssetinformation =oaAssetinformationManager.getObjectById(no);
            oaAssetinformation.setAssetnum(oaAssetinformation.getAssetnum()-object.getAssetnum());
            oaAssetinformationManager.saveObject(oaAssetinformation);
        }
        return this.edit();
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
            for (OaAssetinformationOutlog o : assetinList) {
                Object[] temp= new Object[5];
                temp[0]=CodeRepositoryUtil.getValue("unitcode", o.getApplyUnitcode());
                temp[1]=CodeRepositoryUtil.getValue("usercode", o.getApplyuser());
                temp[2]=o.getAssetnum();
                temp[3]=DatetimeOpt.convertSqlDate(o.getCreatetime());
                temp[4]=o.getCreateRemark();
                chosedList.add(temp);
               
            }
        }
        String[] header = { "出库部门","领用者", "领用数量","领用时间时间", "领用备注"};// 列头
        String titleName = "在"+begcreatertime+"至"+endcreatertime+"出库情况";
        BizCommUtil.doPoi2Excel(titleName, header, chosedList);
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
    public String getIsDept() {
        return isDept;
    }
    public void setIsDept(String isDept) {
        this.isDept = isDept;
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
    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }
    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
    }
    public List<OaAssetinformationOutlog> getAssetinList() {
        return assetinList;
    }

    public void setAssetinList(List<OaAssetinformationOutlog> assetinList) {
        this.assetinList = assetinList;
    }
        
}
