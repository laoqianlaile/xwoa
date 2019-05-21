package com.centit.oa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaCarApply;
import com.centit.oa.po.OaCommonType;
import com.centit.oa.po.OaTrafficRecord;
import com.centit.oa.service.OaCarApplyManager;
import com.centit.oa.service.OaCommonTypeManager;
import com.centit.oa.service.OaTrafficRecordManager;
import com.centit.sys.security.FUserDetail;

public class OaTrafficRecordAction extends BaseEntityDwzAction<OaTrafficRecord> {
    private static final Log log = LogFactory
            .getLog(OaTrafficRecordAction.class);
    private static final long serialVersionUID = 1L;
    private OaTrafficRecordManager oaTrafficRecordMag;

    public void setOaTrafficRecordManager(OaTrafficRecordManager basemgr) {
        oaTrafficRecordMag = basemgr;
        this.setBaseEntityManager(oaTrafficRecordMag);
    }

    public static Log getLog() {
        return log;
    }

    private String djid;//查看返回参数
    public String getDjid() {
        return djid;
    }

    public void setDjid(String djid) {
        this.djid = djid;
    }
    private String show_type;
    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }
    private OaCarApplyManager oaCarApplyManager;
    public OaCarApplyManager getOaCarApplyManager() {
        return oaCarApplyManager;
    }

    public void setOaCarApplyManager(OaCarApplyManager oaCarApplyManager) {
        this.oaCarApplyManager = oaCarApplyManager;
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
           // FUserDetail fuser = ((FUserDetail) getLoginUser());

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaTrafficRecordMag.listObjects(filterMap,pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑
     */
    private List<OaCommonType> oaCommonTypeList;
    public List<OaCommonType> getOaCommonTypeList() {
        return oaCommonTypeList;
    }

    public void setOaCommonTypeList(List<OaCommonType> oaCommonTypeList) {
        this.oaCommonTypeList = oaCommonTypeList;
    }
    private OaCommonTypeManager oaCommonTypeManager;
    public OaCommonTypeManager getOaCommonTypeManager() {
        return oaCommonTypeManager;
    }

    public void setOaCommonTypeManager(OaCommonTypeManager oaCommonTypeManager) {
        this.oaCommonTypeManager = oaCommonTypeManager;
    }

    @Override
    public String edit() {
        super.edit();
        Map<String, Object> filterMapCL= new HashMap<String, Object>();
        filterMapCL.put("state", "T");
        filterMapCL.put("publicType", "cars");
        oaCommonTypeList=oaCommonTypeManager.listObjects(filterMapCL);
        if (StringUtils.isBlank(object.getNo())) {
            object.setNo(oaTrafficRecordMag.getNextKey());
        }
        String djId=request.getParameter("djid");
        OaCarApply oaCarApply=oaCarApplyManager.getObjectById(djId);
        if(oaCarApply!=null){
            object.setCardjid(oaCarApply.getCardjid());
            object.setCarno(oaCarApply.getCarno()); 
            object.setBrand(oaCarApply.getBrand()); 
            object.setModelType(oaCarApply.getModelType()); 
          
        }
        object.setDjid(djId);
        return EDIT;
   
    }
    
    /**
     * 保存业务数据
     * 
     * @return
     */

    public String savePermitReg() {
        try {
            // 保存
         
            OaTrafficRecord  info = baseEntityManager.getObjectById(object.getNo());
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            if (info == null) {
                info = new OaTrafficRecord();
            }
            oaTrafficRecordMag.copyObjectNotNullProperty(info, object);
            object = info;
            if (!StringUtils.isBlank(object.getDjid())) {
            
                object.setCreater(fuser.getUsercode());
                object.setCreatertime(new Date(System.currentTimeMillis()));

            }

            oaTrafficRecordMag.saveObject(object);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }  
    
}
