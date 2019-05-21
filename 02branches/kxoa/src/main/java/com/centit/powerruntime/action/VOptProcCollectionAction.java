package com.centit.powerruntime.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.OptProcCollection;
import com.centit.powerruntime.po.OptProcCollectionId;
import com.centit.powerruntime.po.VOptProcCollection;
import com.centit.powerruntime.service.OptProcCollectionManager;
import com.centit.powerruntime.service.VOptProcCollectionManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.DateUtil;
import com.centit.workflow.FlowManager;

public class VOptProcCollectionAction extends
        BaseEntityExtremeAction<VOptProcCollection> {
    private static final Log log = LogFactory
            .getLog(VOptProcCollectionAction.class);
    private static final long serialVersionUID = 1L;
    private VOptProcCollectionManager VOptProcCollectionMag;
    private FlowManager flowManager;
    private OptProcCollectionManager optProcCollectionManager;
    private String s_atttype;
    private Object json;

    public void setVOptProcCollectionManager(VOptProcCollectionManager basemgr) {
        VOptProcCollectionMag = basemgr;
        this.setBaseEntityManager(VOptProcCollectionMag);
    }

    public String getS_atttype() {
        return s_atttype;
    }

    public void setS_atttype(String s_atttype) {
        this.s_atttype = s_atttype;
    }

    @Override
    public String list() {
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String userCode = fuser.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("userCode", userCode);
            //默认查询当前年份第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("endTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = VOptProcCollectionMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            if(filterMap.get("atttype")!=null){
                if("FW".equals(filterMap.get("atttype").toString())){
                    filterMap.put("fromMenu", "FWBJSCLB");
                }
                if("SW".equals(filterMap.get("atttype").toString())){
                    filterMap.put("fromMenu", "SWBJSCLB");
                }
            }
           
            setbackSearchColumn(filterMap);
            /*
             * 
             * 判断办件是否有权限,决定是否显示办理操作
             */
            if(objList!=null &&objList.size()>0){
                for(VOptProcCollection collection:objList){
                    //modify by lay 2015-11-11
                    //if(StringBaseOpt.isNvl(flowManager.getTaskGrantor(collection.getNodeInstId(),userCode))){
                    if(collection.getNodeInstId()==null || StringBaseOpt.isNvl(flowManager.getTaskGrantor(collection.getNodeInstId(),userCode))){
                    //end modify
                        collection.setIsPower("0");
                    }else{
                        collection.setIsPower("1");
                    }                    
                }
            }
           // return LIST;
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 待办进收藏操作
     * @return
     */
    public String saveColl(){
        json = new JSONObject();
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String userCode = fuser.getUsercode();
            OptProcCollection collection=optProcCollectionManager.getObjectById(new OptProcCollectionId(object.getDjId(),userCode));
            if(collection!=null&& collection.getIsatt().equals("T")){
                collection.setIsatt("F");
                collection.setRemovesettime(new Date(System.currentTimeMillis()));
                optProcCollectionManager.saveObject(collection);
                ((JSONObject) json).put("status", "recoll" );
            }else{
                if(collection==null){
                    collection =new OptProcCollection();
                    collection.setDjId(object.getDjId()); 
                    collection.setUsercode(userCode);
                    collection.setAtttype(object.getDjId().substring(0, 2));
                    collection.setAttsettime(new Date(System.currentTimeMillis()));
                }
                collection.setIsatt("T");
                collection.setRemovesettime(new Date(System.currentTimeMillis()));
                optProcCollectionManager.saveObject(collection);
                ((JSONObject) json).put("status", "coll" );
            }
        } catch (Exception e) {
            log.info(e);
            ((JSONObject) json).put("status", "failed");
        }

        return "json";
    }
    /**
     * 是否取消收藏操作
     * @return
     */
    public String removeColl(){
       
        OptProcCollection collection=optProcCollectionManager.getObjectById(new OptProcCollectionId(object.getCid().getDjId(),object.getCid().getUsercode()));
        if(collection!=null&& collection.getIsatt().equals("T")){
            collection.setIsatt("F");
            collection.setRemovesettime(new Date(System.currentTimeMillis()));
            optProcCollectionManager.saveObject(collection);
        }else{
            if(collection.getDjId()==""){
                collection.setDjId(object.getDjId());
            }
            collection.setIsatt("T");
            collection.setRemovesettime(new Date(System.currentTimeMillis()));
            optProcCollectionManager.saveObject(collection);
        }
       
        return this.list();
    }
    
    public void setOptProcCollectionManager(
            OptProcCollectionManager optProcCollectionManager) {
        this.optProcCollectionManager = optProcCollectionManager;
    }

    public void setFlowManager(FlowManager flowManager) {
        this.flowManager = flowManager;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }
    
}
