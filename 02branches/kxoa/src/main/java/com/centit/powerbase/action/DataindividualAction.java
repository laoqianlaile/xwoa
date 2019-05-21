package com.centit.powerbase.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerbase.po.Dataindividual;
import com.centit.powerbase.service.DataindividualManager;
import com.centit.support.utils.DatetimeOpt;

public class DataindividualAction extends
        BaseEntityExtremeAction<Dataindividual> {
    private static final Log log = LogFactory
            .getLog(DataindividualAction.class);
    private static final long serialVersionUID = 1L;
    private DataindividualManager dataindividualManager;
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

    public void setDataindividualManager(
            DataindividualManager dataindividualManager) {
        this.dataindividualManager = dataindividualManager;
        this.setBaseEntityManager(this.dataindividualManager);
    }

    public String edit() {
        try {
            if (object == null) {
                object = getEntityClass().newInstance();
                object.setIndividualid(dataindividualManager.genNextID());
                request.setAttribute("individualid", object.getIndividualid());
            } else {
                Dataindividual o = dataindividualManager.getObject(object);
                if (o != null)
                    // 将对象o copy给object，object自己的属性会保留
                    dataindividualManager.copyObject(object, o);
                else{
                    dataindividualManager.clearObjectProperties(object);

                    object.setIndividualid(dataindividualManager.genNextID());
                    request.setAttribute("individualid", object.getIndividualid());
                }
            }
            return EDIT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String save() {
        try {
            Dataindividual dbObject = dataindividualManager.getObject(object);
            if (dbObject != null) {
                dataindividualManager.copyObjectNotNullProperty(dbObject,
                        object);
                object = dbObject;
            }
            object.setLastModdate(DatetimeOpt.currentUtilDate());
            dataindividualManager.saveObject(object);
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
            Dataindividual dbObject = dataindividualManager.getObject(object);
            dataindividualManager.disableObject(dbObject);
            return this.list();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            this.saveError(e.getMessage());
            return EDIT;
        }
    }

}
