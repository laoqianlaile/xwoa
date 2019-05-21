package com.centit.oa.action;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.sys.security.FUserDetail;
import com.centit.workflow.FlowRoleRelegate;
import com.centit.workflow.sample.action.SampleFlowRelegateAction;
import com.centit.workflow.sample.po.WfRoleRelegate;

public class SampleFlowRoleRelegateExpandAction extends BaseEntityExtremeAction<WfRoleRelegate> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private FlowRoleRelegate flowRoleRelegate;
    private String grant;
    
    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    public void setFlowRoleRelegate(FlowRoleRelegate flowRoleRelegate) {
        this.flowRoleRelegate = flowRoleRelegate;
    }

    public void setGrant(String grant) {
        this.grant = grant;
    }

    public String getGrant() {
        return grant;
    }

    public String edit(){
        initUsers();
        object = flowRoleRelegate.getObjectById(object.getRelegateno());
        return EDIT;
    }
    
    public String built(){
        initUsers();
        return EDIT;
    }
    
    public String save(){
        try {
            String grantees = object.getGrantee();
            String[]granteeArr = grantees.split(",");
            
            if(object.getRelegateno()!=null){
                flowRoleRelegate.deleteRoleRelegate(object.getRelegateno());
            }
            for(String grantee : granteeArr){
                object.setGrantee(grantee);
                object.setRelegateno(null);
                object.setIsvalid("T");
                
                WfRoleRelegate wfRole = new WfRoleRelegate();
                wfRole.copyNotNullProperty(object);
                wfRole.setRecorder(((FUserDetail)getLoginUser()).getUsercode());
                flowRoleRelegate.saveRoleRelegate(wfRole);
            }
           
            savedMessage();
            return "RefreshList";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return ERROR;
        }
    }
    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
    }
}
