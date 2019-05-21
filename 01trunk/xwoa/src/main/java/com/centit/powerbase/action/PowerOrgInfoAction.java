package com.centit.powerbase.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.LabelValueBean;
import com.centit.powerbase.po.PowerOrgInfo;
import com.centit.powerbase.po.PowerOrgInfoId;
import com.centit.powerbase.service.PowerOrgInfoManager;
import com.centit.powerbase.service.VsuppowerinusingManager;
import com.centit.powerruntime.po.VPowerOrgInfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.workflow.FlowDefine;
import com.centit.workflow.FlowDescribe;

/**
 * 
 * TODO Class description should be added
 * 
 * @author hx
 * @create 2012-12-7
 * @version
 */
public class PowerOrgInfoAction extends BaseEntityExtremeAction<PowerOrgInfo> {

    private static final Log log = LogFactory.getLog(PowerOrgInfoAction.class);
    private static final long serialVersionUID = 1L;
    private PowerOrgInfoManager powerOrgInfoManager;
    private PowerOrgInfoId orgInfoId;
    private FlowDefine flowDefine;
    private VsuppowerinusingManager vsuppowerinusingManager;

    public void setPowerOrgInfoManager(PowerOrgInfoManager basemgr) {
        powerOrgInfoManager = basemgr;
        this.setBaseEntityManager(powerOrgInfoManager);
    }

    public void setVsuppowerinusingManager(
            VsuppowerinusingManager vsuppowerinusingManager) {
        this.vsuppowerinusingManager = vsuppowerinusingManager;
    }

    private String type;
    private String retValue;
    private String selectItem;
    private String allItem;

    public String getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(String selectItem) {
        this.selectItem = selectItem;
    }

    public void setFlowDefine(FlowDefine flowDefine) {
        this.flowDefine = flowDefine;
    }

    public String getAllItem() {
        return allItem;
    }

    public void setAllItem(String allItem) {
        this.allItem = allItem;
    }

    private String s_unitcode;
    private List<VPowerOrgInfo> selectList = new ArrayList<VPowerOrgInfo>(); // 已选择的项目列表
    private List<VPowerOrgInfo> allList = new ArrayList<VPowerOrgInfo>(); // 所有未选择的项目列表

    public String getS_unitcode() {
        return s_unitcode;
    }

    public void setS_unitcode(String s_unitcode) {
        this.s_unitcode = s_unitcode;
    }

    public PowerOrgInfoId getOrgInfoId() {
        return orgInfoId;
    }

    public void setOrgInfoId(PowerOrgInfoId orgInfoId) {
        this.orgInfoId = orgInfoId;
    }

    /***************************** 保存关联信息 *暂时未使用 ************************************/
    @Override
    public String save() {
        // 保存新数据
        if (StringUtils.isNotBlank(retValue)) {
            object.setSetime(new Date(System.currentTimeMillis()));
            object.setSetoperator(((FUserDetail) getLoginUser()).getUsercode());

            powerOrgInfoManager.saveObjects(object, retValue, s_unitcode);
        }

        savedMessage();
        return this.powerOrgInfoList();
    }

    /************************* 查询 **********************************************/
    public String powerOrgInfoList() {
        // 根据部门查询
        object.setUnitcode(this.s_unitcode);
        if (StringUtils.isNotBlank(this.s_unitcode)) {
            // 根据部门查询，该部门已经关联的权力事项
            List<VPowerOrgInfo> redList = powerOrgInfoManager
                    .listOfReadyPowerOrgInfoList(object);
            this.genSelectList(selectList, redList, 28);
        }

        object.setItem_type(this.type);
        // 根据部门、权力类型查询，该部门除了已经关联的权力事项之外的权力事项,即所有条件外剩余的权力事项
        List<VPowerOrgInfo> nowList = powerOrgInfoManager
                .listOfAllPowerOrgInfoList(object);
        this.genSelectList(allList, nowList, 28);
        return "powerOrgInfoList";
    }

    /**
     * 
     * @return 返回部门信息_未被设置的部门
     */
    List<FUnitinfo> listSelectOrgList = new ArrayList<FUnitinfo>();

    public String listSelectOrg() {
        try {
            String itemId = request.getParameter("itemId");
            listSelectOrgList = powerOrgInfoManager.listUnitList(itemId);
            totalRows = listSelectOrgList==null?0:listSelectOrgList.size();
            return "listSelectOrg";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    @Override
    public String edit() {
        // 根据权力事项查询
        if (StringUtils.isNotBlank(object.getItemId())) {
            selectList = powerOrgInfoManager.listOfPowerOrgInfoList(object);
        }
        object.setItemName(vsuppowerinusingManager.findB_PowerByItemId(
                object.getItemId()).getItemName());

        return EDIT;
    }

    String uninName = "";

    public String builtOrg() {
        // 根据权力事项查询
        if (StringUtils.isNotBlank(object.getItemId())) {
            selectList = powerOrgInfoManager.listOfPowerOrgInfoList(object);
        }
        String uninName = "";
        String Unitcode = "";
        if (selectList.size() > 0) {
            for (VPowerOrgInfo info : selectList) {
                uninName += info.getUnitName() + ",";
                Unitcode += info.getUnitcode() + ",";
            }
            uninName = uninName.substring(0, uninName.length() - 1);
            Unitcode = Unitcode.substring(0, Unitcode.length() - 1);
            object.setUnitcode(Unitcode);
            retValue = uninName;
        }
        // 预加载流程代码
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<FlowDescribe> fdslist = flowDefine
                .listLastVersionFlow(new HashMap());
        this.getSelectList(flowDescribesList, fdslist, 22);

        return "built";
    }

    // 流程代码
    private List<FlowDescribe> flowDescribesList = new ArrayList<FlowDescribe>();

    /**
     * 编辑：编辑单个
     * 
     * @return
     */
    public String editInfo() {
        //
        object = powerOrgInfoManager.getObject(object);
        // 预加载流程代码
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<FlowDescribe> fdslist = flowDefine
                .listLastVersionFlow(new HashMap());
        this.getSelectList(flowDescribesList, fdslist, 22);
        return "editInfo";
    }

    /**
     * 编辑：编辑单个
     * 
     * @return
     */
    public String savePowerOrgInfo() {
        //
        try {
            PowerOrgInfo bean = powerOrgInfoManager.getObject(object);
            if (bean != null) {
                powerOrgInfoManager.copyObject(bean, object);
                object = bean;
            }
            object.setSetime(new Date(System.currentTimeMillis()));
            object.setSetoperator(((FUserDetail) getLoginUser()).getUsercode());

            powerOrgInfoManager.saveObject(object);
            return this.edit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return ERROR;
        }
    }

    /**
     * 批量保存关联信息
     * 
     * @return
     */
    public String saveOrg() {
        // 保存新数据
        if (StringUtils.isNotBlank(retValue)) {
            object.setSetime(new Date(System.currentTimeMillis()));
            object.setSetoperator(((FUserDetail) getLoginUser()).getUsercode());

            powerOrgInfoManager.saveObjects(object, retValue);
        }
        return this.edit();
    }

    /**
     * 删除已关联项,页面调转到编辑页面
     */
    @Override
    public String delete() {
        try {
            powerOrgInfoManager.deleteObject(object);
            deletedMessage();
            return this.edit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return EDIT;
        }

    }

    /**
     * 
     * @return
     */
    public String listOrgList() {
        selectList = powerOrgInfoManager.listOfPowerOrgInfoList(object);
        return "listOrgList";
    }

    public List<FlowDescribe> getFlowDescribesList() {
        return flowDescribesList;
    }

    public void setFlowDescribesList(List<FlowDescribe> flowDescribesList) {
        this.flowDescribesList = flowDescribesList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }

    public List<VPowerOrgInfo> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<VPowerOrgInfo> selectList) {
        this.selectList = selectList;
    }

    public List<VPowerOrgInfo> getAllList() {
        return allList;
    }

    public void setAllList(List<VPowerOrgInfo> allList) {
        this.allList = allList;
    }

    @SuppressWarnings("unchecked")
    public void genSelectList(@SuppressWarnings("rawtypes") List selectList,
            List<VPowerOrgInfo> nowList, int len) {
        if (selectList == null) {
            return;
        }
        selectList.clear();

        if (nowList != null && nowList.size() > 0) {
            for (int i = 0; i < nowList.size(); i++) {
                VPowerOrgInfo po = (VPowerOrgInfo) nowList.get(i);
                String id = po.getItemId();
                String value = po.getItemName();
                if (value.length() > len) {
                    value = value.substring(0, len) + "...";
                }
                selectList.add(new LabelValueBean(value, id));
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void getSelectList(
            @SuppressWarnings("rawtypes") List flowDescribesList,
            List<FlowDescribe> fdslist, int len) {
        if (flowDescribesList == null) {
            return;
        }
        flowDescribesList.clear();

        if (fdslist != null && fdslist.size() > 0) {
            for (int i = 0; i < fdslist.size(); i++) {
                FlowDescribe po = (FlowDescribe) fdslist.get(i);
                String id = po.getFlowCode();
                String value = po.getFlowName();
                if (value.length() > len) {
                    value = value.substring(0, len) + "...";
                }
                flowDescribesList.add(new LabelValueBean(value, id));
            }
        }

    }

    public List<FUnitinfo> getListSelectOrgList() {
        return listSelectOrgList;
    }

    public void setListSelectOrgList(List<FUnitinfo> listSelectOrgList) {
        this.listSelectOrgList = listSelectOrgList;
    }

    public String getUninName() {
        return uninName;
    }

    public void setUninName(String uninName) {
        this.uninName = uninName;
    }

}
