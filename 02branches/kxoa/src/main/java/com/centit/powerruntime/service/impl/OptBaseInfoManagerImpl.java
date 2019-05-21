package com.centit.powerruntime.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.oa.dao.VOptBaseListDao;
import com.centit.oa.po.VOptBaseList;
import com.centit.powerbase.dao.VsuppowerinusingDao;
import com.centit.powerbase.po.Vsuppowerinusing;
import com.centit.powerruntime.dao.OaUnitsLeaderDao;
import com.centit.powerruntime.dao.OptBaseInfoDao;
import com.centit.powerruntime.dao.OptIdeaInfoDao;
import com.centit.powerruntime.po.OaUnitsLeader;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.dao.UseroptLogDao;
import com.centit.sys.po.UseroptLog;
import com.centit.sys.security.FUserDetail;
import com.centit.workflow.ExtraFlowManager;
import com.centit.workflow.sample.dao.WfFlowInstanceDao;
import com.centit.workflow.sample.dao.WfNodeInstanceDao;
import com.centit.workflow.sample.po.WfFlowInstance;
import com.centit.workflow.sample.po.WfNodeInstance;

public class OptBaseInfoManagerImpl extends BaseEntityManagerImpl<OptBaseInfo>
        implements OptBaseInfoManager, ExtraFlowManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptBaseInfoManager.class);

    private OptBaseInfoDao optBaseInfoDao;
    private WfNodeInstanceDao nodeInstanceDao;
    private OaUnitsLeaderDao oaUnitsLeaderDao;
    private VOptBaseListDao vOptBaseListDao ;
    private WfFlowInstanceDao flowInstanceDao;
    
    private VsuppowerinusingDao vsuppowerinusingDao ;
    public void setvOptBaseListDao(VOptBaseListDao vOptBaseListDao) {
        this.vOptBaseListDao = vOptBaseListDao;
    }

    public void setUseroptLogDao(UseroptLogDao useroptLogDao) {
        this.useroptLogDao = useroptLogDao;
    }

    private UseroptLogDao useroptLogDao;

    public void setNodeInstanceDao(WfNodeInstanceDao nodeInstanceDao) {
        this.nodeInstanceDao = nodeInstanceDao;
    }

    public void setOptBaseInfoDao(OptBaseInfoDao baseDao) {
        this.optBaseInfoDao = baseDao;
        setBaseDao(this.optBaseInfoDao);
    }

    private OptIdeaInfoDao optIdeaInfoDao;

    public void setOptIdeaInfoDao(OptIdeaInfoDao baseDao) {
        this.optIdeaInfoDao = baseDao;
    }

    @Override
    public OptBaseInfo getOptBaseByFlowId(Long flowinstid) {
        return optBaseInfoDao.getOptBaseByFlowId(flowinstid);
    }

    public String getOptBaseJson(String djId) {
        OptBaseInfo optBaseInfo = optBaseInfoDao.getObjectById(djId);

        JSONObject jsonObj = new JSONObject();
        if (optBaseInfo != null) {
            jsonObj.put("transAffairNo", optBaseInfo.getTransAffairNo());
            jsonObj.put("transaffairname", optBaseInfo.getTransaffairname());
            jsonObj.put("orgcode", optBaseInfo.getOrgcode());
            jsonObj.put("orgname", optBaseInfo.getOrgname());
            jsonObj.put("powerid", optBaseInfo.getPowerid());
            jsonObj.put("powername", optBaseInfo.getPowername());
            jsonObj.put("createDate", optBaseInfo.getCreatedate());
        }
        return jsonObj.toString();
    }

    @Override
    public int getNumOfsameModel(String codeModel, String year) {
        return optBaseInfoDao.getNumOfsameModel(codeModel, year);
    }

    private void saveIdeaInfo(OptBaseInfo optinfo, String userCode,
            long nodeInstId, String nodeName, String optCode, String ideaCode,
            String transIdea, String transContent) {
        saveIdeaInfo(optinfo, userCode, nodeInstId, nodeName, optCode,
                ideaCode, transIdea, transContent, null, null);
    }

    private void saveIdeaInfo(OptBaseInfo optinfo, String userCode,
            long nodeInstId, String nodeName, String optCode, String ideaCode,
            String transIdea, String transContent, String unitCode,
            String flowPhase) {
        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setProcid(optIdeaInfoDao.getNextIdeaPK());

        optIdeaInfo.setDjId(optinfo.getDjId());
        optIdeaInfo.setNodeInstId(nodeInstId);
        optIdeaInfo.setNodeinststate("N");
        optIdeaInfo.setNodename(nodeName);
        optIdeaInfo.setTransdate(DatetimeOpt.currentUtilDate());
        optIdeaInfo.setOptcode(optCode);
        optIdeaInfo.setTranscontent(transContent);
        optIdeaInfo.setUsercode(userCode);
        optIdeaInfo.setIdeacode(ideaCode);
        optIdeaInfo.setTransidea(transIdea);
        optIdeaInfo.setUnitcode(unitCode);
        // optIdeaInfo.setNodeCode(nodeCode);
        optIdeaInfo.setFlowPhase(flowPhase);
        optIdeaInfoDao.saveObject(optIdeaInfo);
    }

    // // 流程管理接口
    private OptBaseInfo manageInstance(long flowInstId, String admindesc,
            String bizstate) {
        OptBaseInfo optBaseInfo = getOptBaseByFlowId(flowInstId);
        optBaseInfo.setBizstate(bizstate);
        optBaseInfo.setSolvenote(admindesc);
        optBaseInfo.setSolvetime(DatetimeOpt.currentUtilDate());

        optBaseInfoDao.saveObject(optBaseInfo);

        return optBaseInfo;
    }

    /**
     * 终止一个流程
     */
    public int stopInstance(long flowInstId, String mangerUserCode,
            String admindesc) {
        OptBaseInfo optBaseInfo = manageInstance(flowInstId, admindesc, "X");
        saveIdeaInfo(optBaseInfo, mangerUserCode, 0, "流程管理", "stop", "stop",
                "终止", admindesc);
        return 0;
    }

    /**
     * 暂停一个流程
     */
    public int suspendInstance(long flowInstId, String mangerUserCode,
            String admindesc) {
        OptBaseInfo optBaseInfo = manageInstance(flowInstId, admindesc, "S");
        saveIdeaInfo(optBaseInfo, mangerUserCode, 0, "流程管理", "suspend",
                "suspend", "暂停", admindesc);
        return 0;
    }

    /**
     * 使流程失效
     */
    public int invalidInstance(long flowInstId, String mangerUserCode,
            String admindesc) {
        OptBaseInfo optBaseInfo = manageInstance(flowInstId, admindesc, "I");
        saveIdeaInfo(optBaseInfo, mangerUserCode, 0, "流程管理", "invalid",
                "invalid", "失效", admindesc);
        return 0;
    }

    /**
     * 激活一个 挂起的或者无效的流程
     */
    public int activizeInstance(long flowInstId, String mangerUserCode,
            String admindesc) {
        OptBaseInfo optBaseInfo = manageInstance(flowInstId, admindesc, "T");
        saveIdeaInfo(optBaseInfo, mangerUserCode, 0, "流程管理", "wakeup",
                "wakeup", "唤醒", admindesc);
        return 0;
    }

    /**
     * 唤醒一个超时流程的一个节点
     */
    public long activizeExpireInstance(long flowInstId, String timeLimit,
            String mangerUserCode, String admindesc) {
        OptBaseInfo optBaseInfo = manageInstance(flowInstId, admindesc, "T");
        saveIdeaInfo(optBaseInfo, mangerUserCode, 0, "流程管理", "wakeup",
                "wakeup", "唤醒", admindesc);
        return 0;
    }

    @Override
    public long suspendNodeInstance(long nodeInstId, String mangerUserCode) {
        WfNodeInstance nodeInst = nodeInstanceDao.getObjectById(nodeInstId);
        if (nodeInst == null)
            return -1;
        OptBaseInfo optBaseInfo = getOptBaseByFlowId(nodeInst.getFlowInstId());
        saveIdeaInfo(optBaseInfo, mangerUserCode, nodeInstId,
                nodeInst.getNodeName(), "suspend", "suspend", "暂停", "暂停节点",
                nodeInst.getUnitCode(), nodeInst.getFlowPhase());
        return 0;
    }

    @Override
    public long activizeNodeInstance(long nodeInstId, String mangerUserCode) {
        WfNodeInstance nodeInst = nodeInstanceDao.getObjectById(nodeInstId);
        if (nodeInst == null)
            return -1;
        OptBaseInfo optBaseInfo = getOptBaseByFlowId(nodeInst.getFlowInstId());
        saveIdeaInfo(optBaseInfo, mangerUserCode, nodeInstId,
                nodeInst.getNodeName(), "wakeup", "wakeup", "唤醒", "唤醒节点",
                nodeInst.getUnitCode(), nodeInst.getFlowPhase());
        return 0;
    }

    // 查询optBaseInfo中的记录BIZTYPE<>'F'
    @Override
    public List<OptBaseInfo> listOptBaseInfo(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return optBaseInfoDao.listOptBaseInfo(filterMap, pageDesc);
    }

    @Override
    public List<VuserTaskListOA> getTasksByNodeInstId(Long curNodeInstId) {
        return optBaseInfoDao.getTasksByNodeInstId(curNodeInstId);
    }

    /**
     * 根据收文号查询 用于收文号的重复性验证
     * 
     * @param acceptArchiveNo
     *            收文编号
     */
    @Override
    public List<OptBaseInfo> listOptBaseInfo(String acceptArchiveNo) {
        return optBaseInfoDao.listOptBaseInfo(acceptArchiveNo);
    }

    @Override
    public Set<String> getUsersOfleader(String usercode) {
        Set<String> users = null;
        OaUnitsLeader oaUnitsLeader = oaUnitsLeaderDao.getObjectById(usercode);
        if (oaUnitsLeader == null) {
            return null;
        } else {
            users = oaUnitsLeaderDao.listObjectsByUsercode(usercode);
            return users;
        }
    }

    public void setOaUnitsLeaderDao(OaUnitsLeaderDao oaUnitsLeaderDao) {
        this.oaUnitsLeaderDao = oaUnitsLeaderDao;
    }
    /**
     * 验证办件合法性，保存日志
     * @param djId
     * @param fuser
     * @return
     */
    @Override
    public String saveVOptBaseListVail(String djId, FUserDetail fuser) {
        String v="";
        VOptBaseList l=vOptBaseListDao.getObjectById(djId);
        if(l!=null){//办件存在
            boolean b=vOptBaseListDao.listVOptBaseListVail(djId, fuser.getUsercode());
            if(!b){//没有查到，无权限
                    //保存日志（操作安全异常）
                UseroptLog u=new UseroptLog();
                u.setId("RZ"+useroptLogDao.getNextKeyBySequence("S_F_USEROPT_LOG",14));
                u.setCreatetime(new Date(System.currentTimeMillis()));
                u.setCreateuser(fuser.getUsercode());
                u.setBizname(l.getTransaffairname());
                u.setBiztype(l.getItemType());
                u.setRunerrortype("AQ");
                u.setCreaterip(fuser.getLoginip());
                //useroptLogDao.save(u);
                useroptLogDao.saveObject(u);
                v="false,AQ" ;   
            }else{//查到，通过验证
                v="true,"+djId;
                return null;
            }
        }else{//办件不存在
                    //数据异常，保存日志
            UseroptLog u=new UseroptLog();
            u.setId("RZ"+useroptLogDao.getNextKeyBySequence("S_F_USEROPT_LOG",14));
            u.setCreatetime(new Date(System.currentTimeMillis()));
            u.setCreateuser(fuser.getUsercode()); 
            u.setRunerrortype("SJ"); 
            u.setCreaterip(fuser.getLoginip());
            useroptLogDao.saveObject(u);
            v="false,SJ" ;   
        }
        
        
            
        return v;
        
    }

    @Override
    public String getNodeNamesByFlowinstid(Long flowinstid) {
        // TODO Auto-generated method stub
        return optBaseInfoDao.getNodeNamesByFlowinstid(flowinstid);
    }

    @Override
    public String getuserNamesByFlowinstid(Long flowinstid) {
        // TODO Auto-generated method stub
        return optBaseInfoDao.getuserNamesByFlowinstid(flowinstid);
    }

    @Override
    public void activizeNodeInstance(Long flowinstid,Long nodeInstId,
            String mangerUserCode) {
        // TODO Auto-generated method stub
        WfNodeInstance nodeInst = nodeInstanceDao.getObjectById(nodeInstId);
        nodeInst.setNodeState("N");
     // 设置最后更新时间和更新人
        nodeInst.setLastUpdateUser(mangerUserCode);
        nodeInst.setLastUpdateTime(new Date(System.currentTimeMillis()));
        nodeInstanceDao.saveObject(nodeInst);
        
        WfFlowInstance wfFlowInstance=flowInstanceDao.getObjectById(flowinstid);
        wfFlowInstance.setInstState("N");
        flowInstanceDao.saveObject(wfFlowInstance);
        
        OptBaseInfo optBaseInfo=optBaseInfoDao.getOptBaseByFlowId(flowinstid);
        optBaseInfo.setBizstate("W");
        optBaseInfoDao.save(optBaseInfo);
    }
    public void setFlowInstanceDao(WfFlowInstanceDao flowInstanceDao) {
        this.flowInstanceDao = flowInstanceDao;
    }
    
    /*
    **
    * 获取下一步流程节点实例  自动提交+哑元 递归
    * 
    * @param curNodeInstId
    * @return
    */
    @Override
    public Set<Long> getNextNodeInstends(Long curNodeInstId) {
        String hql=" select t.nodeinstid from WF_NODE_INSTANCE t,wf_node f "
                 + " where   t.nodeid =f.nodeid "
                 + " and    nodeinstid != " +curNodeInstId
                 + " START WITH nodeinstid = " +curNodeInstId
                 + "  CONNECT BY prevnodeinstid = PRIOR nodeinstid ";
        
        @SuppressWarnings("unchecked")
      /* Set<Long>  nextNodeInstends =new  HashSet<Long>(  (Collection<? extends Long>) 
                optBaseInfoDao.findObjectsBySql(hql.toString()));  */
      
        Set<Long>  nextNodeInstends =new  HashSet<Long>();
        List<Object>  l = ((List<Object>) optBaseInfoDao.findObjectsBySql(hql.toString()));  
        if(l!=null && l.size()>0){
            for(Object o:l){
                nextNodeInstends.add(Long.valueOf(o.toString()));
            }
        }
        return nextNodeInstends;
    }
    
    public void setVsuppowerinusingDao(VsuppowerinusingDao vsuppowerinusingDao) {
        this.vsuppowerinusingDao = vsuppowerinusingDao;
    }

    /*
     * 获取对应的业务模块信息
     */
    @Override
    public String getApplyItemType(String djId) {
        OptBaseInfo optBaseInfo = this.getObjectById(djId);
        if(null!=optBaseInfo&&null!=optBaseInfo.getPowerid()){
            Vsuppowerinusing vsuppowerinusing = vsuppowerinusingDao.findB_PowerByItemId(optBaseInfo.getPowerid());
            //判断是否是业务登记
            String applyItemType =vsuppowerinusing.getApplyItemType();
            if(StringUtils.isNotBlank(applyItemType)){
                applyItemType= applyItemType.split("\\/")[1];
                return applyItemType;
            }
        }
        return null;
    }
    
}
