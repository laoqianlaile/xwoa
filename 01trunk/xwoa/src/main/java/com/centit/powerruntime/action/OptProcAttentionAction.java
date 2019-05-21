package com.centit.powerruntime.action;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.OptProcAttentionId;
import com.centit.powerruntime.po.VProcAttention;
import com.centit.powerruntime.service.OptProcAttentionManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUserManager;
import com.centit.workflow.sample.optmodel.BaseWFEntityAction;

/**
 * 
 * TODO Class description should be added
 * 
 * @author ljy
 * @create 2012-9-24
 * @version
 */
public class OptProcAttentionAction extends
        BaseWFEntityAction<OptProcAttention> {
    
    private static final long serialVersionUID = 1L;
    private OptProcAttentionManager optProcAttentionMag;
    private List<VProcAttention> attentionList;
    private OptProcInfoManager optProcInfoManager;
    private SysUserManager sysUserManager;

    public void setOptProcAttentionManager(OptProcAttentionManager basemgr) {
        optProcAttentionMag = basemgr;
        this.setBaseEntityManager(optProcAttentionMag);
    }

    @SuppressWarnings("unchecked")
    public String listAtt() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if(filterMap.get("isAtt") != null && !"".equals(filterMap.get("isAtt"))){
            filterMap.put("isAtt", "1");
        }
        filterMap.put("userCode", ((FUserDetail) getLoginUser()).getUsercode());
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        attentionList = optProcAttentionMag.listProcAttention(filterMap,
                pageDesc);
        totalRows = pageDesc.getTotalRows();
        return LIST;
    }
    
    /**
     * 提交关注信息
     * @return
     */
    public String saveIdeaInfo(){
        OptProcAttentionId id = new OptProcAttentionId();
        id.setDjId(object.getDjId());
        id.setUserCode(object.getUserCode());
        OptProcAttention optProc = optProcAttentionMag.getObjectById(id);
        optProc.setIsAtt("1");
        optProcAttentionMag.saveObject(optProc);
        
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setDjId(object.getDjId());
        optIdeaInfo.setUsername(loginInfo.getUsername());
        optIdeaInfo.setUsercode(loginInfo.getUsercode());
        optIdeaInfo.setUnitcode(loginInfo.getPrimaryUnit());
        //NodeInstance nodeInst=flowEngine.getNodeInstById(object.getNodeInstId());        
        //FlowNodeInfo nodeInfo =flowEngine.getNodeInfoById(nodeInst.getNodeId());
        optIdeaInfo.setNodename("关注");
        optIdeaInfo.setNodeInstId(object.getNodeInstId());
        //optIdeaInfo.setNodeinststate(nodeInst.getNodeState());
        
        VUserUnits fuerunit=new VUserUnits();
        fuerunit.setUnitCode(loginInfo.getPrimaryUnit());
        fuerunit.setUserCode(loginInfo.getUsercode());
        fuerunit=sysUserManager.getUnitByUserCode(fuerunit);
        optIdeaInfo.setUnitname(fuerunit.getUnitName());
        
        optIdeaInfo.setTranscontent(object.getAttIdea());
        optIdeaInfo.setTransidea("录入备注");
        optIdeaInfo.setIdeacode("A");//关注
        optProcAttentionMag.saveOptIdeaInfo(optIdeaInfo);
        
        return listAtt();
    }

    public List<VProcAttention> getAttentionList() {
        return attentionList;
    }

    public void setAttentionList(List<VProcAttention> attentionList) {
        this.attentionList = attentionList;
    }

    public OptProcInfoManager getOptProcInfoManager() {
        return optProcInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public SysUserManager getSysUserManager() {
        return sysUserManager;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

}
