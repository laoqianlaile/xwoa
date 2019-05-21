package com.centit.oa.action;


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
import com.centit.oa.po.OaAccidentRecord;
import com.centit.oa.po.OaCarApply;
import com.centit.oa.po.OaCostInfo;
import com.centit.oa.po.OaDriverBook;
import com.centit.oa.po.OaTrafficRecord;
import com.centit.oa.service.OaCarApplyManager;
import com.centit.oa.service.OaDriverBookManager;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;

public class OaDriverBookAction extends BaseEntityDwzAction<OaDriverBook> {
    private static final Log log = LogFactory.getLog(OaDriverBookAction.class);
    private static final long serialVersionUID = 1L;
    private OaDriverBookManager oaDriverBookMag;

    public void setOaDriverBookManager(OaDriverBookManager basemgr) {
        oaDriverBookMag = basemgr;
        this.setBaseEntityManager(oaDriverBookMag);
    }

    private List<OaCostInfo> oaCostInfos;
    private List<OaAccidentRecord> oaAccidentRecords;
    private List<OaTrafficRecord> oaTrafficRecords;
    private SysUserManager sysUserManager;
    private SysUnitManager sysUnitManager;
    private String curUrl;
    private String show_type;
    private OaCarApplyManager oaCarApplyManager;



    /**
     * 列表数据
     */
    public String list() {
        try {
            // unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
           // FUserDetail fuser = ((FUserDetail) getLoginUser());

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
         
            objList = oaDriverBookMag.listObjects(filterMap,pageDesc);
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑
     */
private String edittype;


    @Override
    public String edit() {
        super.edit();
        //流程内的会议纪要登记,根据djid进行查询赋值

        if("T".equals(edittype)){
            
            OaCarApply oaCarApply= oaCarApplyManager.getObjectById(object.getDjid());// 业务数据
         
            object.setCarno(oaCarApply.getCarno());
            object.setDriver(oaCarApply.getDriver());
            object.setBrand(oaCarApply.getBrand());
             object.setCardjid(oaCarApply.getCardjid())  ;       
        }
        
    
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
       
            OaDriverBook info = baseEntityManager.getObjectById(object.getDjid());
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            if (info == null) {
                info = new OaDriverBook();
            }
            oaDriverBookMag.copyObjectNotNullProperty(info, object);
            object = info;
            if (!StringUtils.isBlank(object.getDjid())) {
                
//            } else {
                object.setCreater(fuser.getUsercode());
                object.setCreatertime(new Date(System.currentTimeMillis()));

            }

        
            oaDriverBookMag.saveObject(object);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return this.list();
    }  
    
    
    
    /****************************setter/getter**************************************/
    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public SysUnitManager getSysUnitManager() {
        return sysUnitManager;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public List<OaCostInfo> getNewOaCostInfos() {
        return this.oaCostInfos;
    }

    public void setNewOaCostInfos(List<OaCostInfo> oaCostInfos) {
        this.oaCostInfos = oaCostInfos;
    }


    public List<OaAccidentRecord> getNewOaAccidentRecords() {
        return this.oaAccidentRecords;
    }

    public void setNewOaAccidentRecords(List<OaAccidentRecord> oaAccidentRecords) {
        this.oaAccidentRecords = oaAccidentRecords;
    }



    public List<OaTrafficRecord> getNewOaTrafficRecords() {
        return this.oaTrafficRecords;
    }

    public void setNewOaTrafficRecords(List<OaTrafficRecord> oaTrafficRecords) {
        this.oaTrafficRecords = oaTrafficRecords;
    }

    public String getCurUrl() {
    return curUrl;
}

public void setCurUrl(String curUrl) {
    this.curUrl = curUrl;
}
public String getShow_type() {
    return show_type;
}

public void setShow_type(String show_type) {
    this.show_type = show_type;
}
public OaCarApplyManager getOaCarApplyManager() {
    return oaCarApplyManager;
}

public void setOaCarApplyManager(OaCarApplyManager oaCarApplyManager) {
    this.oaCarApplyManager = oaCarApplyManager;
}
public String getEdittype() {
    return edittype;
}

public void setEdittype(String edittype) {
    this.edittype = edittype;
}
}
