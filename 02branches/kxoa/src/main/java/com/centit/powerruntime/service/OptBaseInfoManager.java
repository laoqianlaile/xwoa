package com.centit.powerruntime.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.sys.security.FUserDetail;

public interface OptBaseInfoManager extends BaseEntityManager<OptBaseInfo> 
{
    public OptBaseInfo getOptBaseByFlowId(Long flowinstid);
    
    public String getOptBaseJson(String djId);
    
    public int getNumOfsameModel(String codeModel, String year);

    public List<OptBaseInfo> listOptBaseInfo(Map<String, Object> filterMap,
            PageDesc pageDesc);

    public List<VuserTaskListOA> getTasksByNodeInstId(Long curNodeInstId);
    /**
     * 对收文编号重复性验证
     * @param acceptArchiveNo 收文编号
     * @return
     */
    public List<OptBaseInfo> listOptBaseInfo(String acceptArchiveNo);
    /**
     * 如果人员存在分管部门，则获取分管部门下的人员
     * @param usercode
     * @return
     */
    public Set<String> getUsersOfleader(String usercode);
    /**
     * 验证办件合法性，保存日志
     * @param djId
     * @param fuser
     * @return
     */
    public String saveVOptBaseListVail(String djId, FUserDetail fuser);
    /**
     * 根据流程实例号 获取办件所在环节名称：可能办件处在不同环节，环节名称合并
     * @param flowinstid
     * @return
     */
    public String getNodeNamesByFlowinstid(Long flowinstid);
    /**
     * 根据流程实例号 获取办件当前的办理人员：可能办件处在不同环节，办理人员也不一样，人员合并
     * @param flowinstid
     * @return
     */
    public String getuserNamesByFlowinstid(Long flowinstid);

    /**
     * 因为多个多实例情况，强行将因为流程结束而结束的节点变成正常的节点；
     * @param flowInstId
     * @param nodeInstId
     * @param string
     */
    public void activizeNodeInstance(Long flowinstid,Long nodeInstId,
            String usercode);
    /**
     * 获取下一步流程节点实例  自动提交+哑元 递归
     * 
     * @param curNodeInstId
     * @return
     */
    public Set<Long> getNextNodeInstends(Long curNodeInstId);

    public String getApplyItemType(String djId);
}
