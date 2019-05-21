package com.centit.powerbase.action;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Dataenterprise;
import com.centit.powerbase.service.DataenterpriseManager;
import com.centit.support.utils.DatetimeOpt;

public class DataenterpriseAction extends
        BaseEntityExtremeAction<Dataenterprise> {
    private static final Log log = LogFactory
            .getLog(DataenterpriseAction.class);
    private static final long serialVersionUID = 1L;
    private DataenterpriseManager dataenterpriseManager;
    private String s_applicant;
    private String s_isInuse;
    private String s_begTime;
    private String s_endTime;

    public String getS_applicant() {
        return s_applicant;
    }

    public void setS_applicant(String s_applicant) {
        this.s_applicant = s_applicant;
    }

    public String getS_isInuse() {
        return s_isInuse;
    }

    public void setS_isInuse(String s_isInuse) {
        this.s_isInuse = s_isInuse;
    }

    public String getS_begTime() {
        return s_begTime;
    }

    public void setS_begTime(String s_begTime) {
        this.s_begTime = s_begTime;
    }

    public String getS_endTime() {
        return s_endTime;
    }

    public void setS_endTime(String s_endTime) {
        this.s_endTime = s_endTime;
    }

    public void setDataenterpriseManager(
            DataenterpriseManager dataenterpriseManager) {
        this.dataenterpriseManager = dataenterpriseManager;
        this.setBaseEntityManager(this.dataenterpriseManager);
    }

    @SuppressWarnings("unchecked")
    public String list() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            String orderField = request.getParameter("orderField");
            String orderDirection = request.getParameter("orderDirection");

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            if (!StringUtils.isBlank(orderField)
                    && !StringUtils.isBlank(orderDirection)) {

                filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                        + orderDirection);

                // request.setAttribute("orderDirection", orderDirection);
                // request.setAttribute("orderField", orderField);
            }
            PageDesc pageDesc = makePageDesc();
            objList = dataenterpriseManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            
            this.pageDesc = pageDesc;
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String edit() {
        try {
            if (object.getEnterpriseid() == null) {
                object = getEntityClass().newInstance();
                object.setEnterpriseid(dataenterpriseManager.genNextID());
                request.setAttribute("enterpriseid", object.getEnterpriseid());
            } else {
                Dataenterprise o = dataenterpriseManager.getObject(object);
                if (o != null)
                    // 将对象o copy给object，object自己的属性会保留
                    dataenterpriseManager.copyObject(object, o);
                else
                    dataenterpriseManager.clearObjectProperties(object);
            }
            return EDIT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String save() {
        try {
            Dataenterprise dbObject = baseEntityManager.getObject(object);
            if (dbObject != null) {
                dataenterpriseManager.copyObjectNotNullProperty(dbObject,
                        object);
                object = dbObject;
            }
            object.setLastModdate(DatetimeOpt.currentUtilDate());
            dataenterpriseManager.saveObject(object);
            savedMessage();
            return this.list();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return ERROR;
        }
    }

    public String delete() {
        try {
            Dataenterprise dbObject = baseEntityManager.getObject(object);
            dataenterpriseManager.disableObject(dbObject);
            return this.list();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            this.saveError(e.getMessage());
            return EDIT;
        }
    }
}
