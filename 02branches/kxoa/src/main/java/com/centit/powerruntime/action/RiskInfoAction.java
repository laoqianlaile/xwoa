package com.centit.powerruntime.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.RiskInfo;
import com.centit.powerruntime.service.RiskInfoManager;
import com.centit.sys.security.FUserDetail;

public class RiskInfoAction extends BaseEntityExtremeAction<RiskInfo> {
    private static final long serialVersionUID = 1L;
    private RiskInfoManager riskInfoManager;

    public void setRiskInfoManager(RiskInfoManager basemgr) {
        riskInfoManager = basemgr;
        this.setBaseEntityManager(riskInfoManager);
    }

    @Override
    public String save() {

        try {
            RiskInfo riskInfo = riskInfoManager.getObjectById(object
                    .getRiskid());
            if (riskInfo == null) {
                object.setRiskid(riskInfoManager.getNextRiskPK());

            } else {
                riskInfoManager.copyObjectNotNullProperty(riskInfo, object);
                object = riskInfo;
            }
            object.setSettime(new Date(System.currentTimeMillis()));
            object.setSetuser(((FUserDetail) getLoginUser()).getUsercode());
            riskInfoManager.saveObject(object);
            savedMessage();
            return super.list();
        } catch (Exception e) {
            e.printStackTrace();
            saveError(e.getMessage());
            return ERROR;
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        objList = riskInfoManager.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        this.pageDesc=pageDesc;
        return LIST;
    }
    
   private  String riskid;
    public String listSelect() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object,Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            
            Map<String,Object> filterMap = convertSearchColumn( paramMap );
            Limit limit=ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
            objList=riskInfoManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            //配置 风险点类别点击选择的时候 原来的值带进来
            riskid=request.getParameter("riskid");
            request.setAttribute("fromjs", request.getParameter("fromjs"));
            if(!(StringUtils.isBlank(riskid)||"0".equals(riskid))){
                RiskInfo info=riskInfoManager.getObjectById(Long.parseLong(riskid));
                if(objList.size()>0){
                  for(int i=0;i<objList.size();i++) {
                      //toDo 将原来选定的值放在第一行
                      if(objList.contains(info)){
                          objList.remove(info);//1、先去除选定的项 
                          List<RiskInfo> infoList  =new ArrayList<RiskInfo>();
                          //将原来选定的值放在第一行
                          infoList.add(info);
                          infoList.addAll(objList);
                          objList=infoList;
                      }
                  }
                }
            }
            
            return "riskList";
        } catch (Exception e) {
            e.printStackTrace();
            return  ERROR;
        }
    }

    public String getRiskid() {
        return riskid;
    }

    public void setRiskid(String riskid) {
        this.riskid = riskid;
    }
    

}
