package com.centit.oa.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaCostInfo;
import com.centit.oa.service.OaCostInfoManager;
import com.centit.sys.security.FUserDetail;

public class OaCostInfoAction extends BaseEntityDwzAction<OaCostInfo> {
    private static final Log log = LogFactory.getLog(OaCostInfoAction.class);
//    private String edittype;
//    public String getEdittype() {
//        return edittype;
//    }
//
//    public void setEdittype(String edittype) {
//        this.edittype = edittype;
//    }
    private String djid;//查看返回参数
    public String getDjid() {
        return djid;
    }

    public void setDjid(String djid) {
        this.djid = djid;
    }
    private static final long serialVersionUID = 1L;
    private OaCostInfoManager oaCostInfoMag;

    public void setOaCostInfoManager(OaCostInfoManager basemgr) {
        oaCostInfoMag = basemgr;
        this.setBaseEntityManager(oaCostInfoMag);
    }
     private String show_type;
    
    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
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
            objList = oaCostInfoMag.listObjects(filterMap,pageDesc);
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

    @Override
    public String edit() {
        super.edit();
        if (StringUtils.isBlank(object.getNo())) {
            object.setNo(oaCostInfoMag.getNextKey());
        }
        object.setDjid(request.getParameter("djid"));
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
//            djid=object.getDjid();
            OaCostInfo  info = baseEntityManager.getObjectById(object.getNo());
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            if (info == null) {
                info = new OaCostInfo();
            }
            oaCostInfoMag.copyObjectNotNullProperty(info, object);
            object = info;
            if (!StringUtils.isBlank(object.getDjid())) {
                
//            } else {
                object.setCreater(fuser.getUsercode());
                object.setCreatertime(new Date(System.currentTimeMillis()));

            }

            oaCostInfoMag.saveObject(object);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
          return null;
//        return "saveCostInfo";
    }  
    
    
 
    
}
