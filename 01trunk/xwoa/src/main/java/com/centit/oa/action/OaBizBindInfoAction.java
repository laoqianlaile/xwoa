package com.centit.oa.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaBizBindInfo;
import com.centit.oa.service.OaBizBindInfoManager;
import com.centit.powerruntime.optmodel.PowerRuntimeEntityAction;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;
	

public class OaBizBindInfoAction  extends PowerRuntimeEntityAction<OaBizBindInfo>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaBizBindInfoAction.class);
	private static final long serialVersionUID = 1L;
	private OaBizBindInfoManager oaBizBindInfoMag;
    private String  resetUsers;
    private String  bjType;
    //删除关联后，重新调用listbiz方法时使用的参数(optType,s_startDjid，s_endDjid)  
    private String optType;
    private String s_startDjid;
    private String s_endDjid;
    private String nodelete;//查看时不允许删除的标记
    public String getNodelete() {
        return nodelete;
    }
    public void setNodelete(String nodelete) {
        this.nodelete = nodelete;
    }
    public String getS_startDjid() {
        return s_startDjid;
    }
    public void setS_startDjid(String s_startDjid) {
        this.s_startDjid = s_startDjid;
    }
    public String getS_endDjid() {
        return s_endDjid;
    }
    public void setS_endDjid(String s_endDjid) {
        this.s_endDjid = s_endDjid;
    }
    public String getOptType() {
        return optType;
    }
    public void setOptType(String optType) {
        this.optType = optType;
    }
    public void setOaBizBindInfoManager(OaBizBindInfoManager basemgr)
	{
		oaBizBindInfoMag = basemgr;
		this.setBaseEntityManager(oaBizBindInfoMag);
	}
    
    public String getResetUsers() {
        return resetUsers;
    }
    public void setResetUsers(String resetUsers) {
        this.resetUsers = resetUsers;
    }
    public String getBjType() {
        return bjType;
    }
    public void setBjType(String bjType) {
        this.bjType = bjType;
    }
	/**
	 * 保存关联的事权或签报信息
	 */
	public void submitOaBizBindInfo(){
	    String startDjid = (String)request.getParameter("startDjid");
	    String nodeInstId = (String)request.getParameter("nodeInstId");
        Long nodeId = Long.parseLong(nodeInstId);
	    if(StringUtils.isNotBlank(resetUsers)){
            String ar[]=resetUsers.split(",");
            for(String a :ar){
                saveObjectBanInfo(a,nodeId,startDjid);
            }
        }
	    request.setAttribute("nodeInstId", nodeInstId);
	}
	
	 public void saveObjectBanInfo(String djId,Long nodeId,String startDjid){
	     OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(startDjid);
	     OaBizBindInfo oaBizBindInfo = new OaBizBindInfo();
	     oaBizBindInfo.setCreatetime(baseInfo.getCreatedate());
	     oaBizBindInfo.setBizType(oaBizBindInfoMag.initReturnBiztype(djId, startDjid));
	     oaBizBindInfo.setStartDjid(startDjid);
	     oaBizBindInfo.setEndDjid(djId);
	     oaBizBindInfo.setCreateuser(baseInfo.getCreateuser());
	     NodeInstance nodeInst = flowEngine.getNodeInstById(nodeId);
         FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst
                 .getNodeId());
         String nodeName = nodeInfo.getNodeName();
         oaBizBindInfo.setNodename(nodeName);
         oaBizBindInfo.setNodeinstid(nodeId);
         oaBizBindInfo.setNo(oaBizBindInfoMag.getNextNO("GL"));
	     oaBizBindInfoMag.saveObject(oaBizBindInfo);
	    }
    /**
     * 设置iframe的src
     * @param djId
     * @param optType
     * @param nodelete（这个参数不为空时，表示查看关联关系，在页面上标记不可删除关联关系，是全局参数）
     * @return
     */
    public static OptHtmlFrameInfo getBizBindListFrame(String djId,String optType1,String nodelete) {
        OptHtmlFrameInfo stuffsFrameInfo = new OptHtmlFrameInfo();
        stuffsFrameInfo.setFrameId("DestFrame");
        if(StringUtils.isBlank(optType1)){
            optType1="S";
        }
        String src="/oa/oaBizBindInfo!listbiz.do?optType="+optType1;
        if("S".equals(optType1)){//optType1为S时，查相关主动关联的事项
            src=src+"&s_startDjid="+djId;
        }else if("E".equals(optType1)){//optType1为E时，查相关被动关联的事项
            src=src+"&s_endDjid="+djId;  
        }
        if(StringUtils.isNotBlank(nodelete)){//页面上标记不可删除关联关系
            src=src+"&nodelete=1";  
        }
        stuffsFrameInfo.setFrameSrc(src);
        stuffsFrameInfo.setFrameHeight("300px");
        return stuffsFrameInfo;
    }
    /**
     * 为方法listbiz获取集合时使用
     */
    @SuppressWarnings("unchecked")
    private List<OaBizBindInfo> getObjectlist(){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if(StringUtils.isNotBlank(s_startDjid)){//s_startDjid非空时，查相关主动关联的事项
            filterMap.put("startDjid", s_startDjid);
        }else if(StringUtils.isNotBlank(s_endDjid)){//s_endDjid非空时，查相关被动关联的事项
            filterMap.put("endDjid", s_endDjid);
        }
        PageDesc pageDesc = makePageDesc();
        objList = oaBizBindInfoMag.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        this.pageDesc = pageDesc;
        return objList;
    }
    /**
     * 得到关联其他业务的信息
     * @return
     */
    public String listbiz(){
        this.getObjectlist();
        if(objList!=null && objList.size()>0){
           for(OaBizBindInfo bean :objList){
               OptBaseInfo optBaseInfo=null;
               if("S".equals(optType)){//主动发起的关联
                   optBaseInfo=new OptBaseInfo();
                   optBaseInfo=optBaseInfoManager.getObjectById(bean.getEndDjid());
               }else if("E".equals(optType)){//被其他事项关联
                   optBaseInfo=new OptBaseInfo();
                   optBaseInfo=optBaseInfoManager.getObjectById(bean.getStartDjid());
               }
              bean.setOptBaseInfo(optBaseInfo);
           }
        }
        return "listBizbind";
    }
    /**
     * 去重
     * @param list
     */
    public static void removeDuplicate(List<OaBizBindInfo> list) { 
        for ( int i = 0 ; i < list.size() - 1 ; i ++ ) { 
            for ( int j = list.size() - 1 ; j > i; j -- ) { 
                    if (list.get(j).getOptBaseInfo().getDjId().equals(list.get(i).getOptBaseInfo().getDjId())) { 
                            list.remove(j); 
                    } 
            } 
        } 
        
     } 
    /**
     * tab页上使用的关联事项（包括主动关联，被动关联）
     */
    public String listbiz4tab(){
        List<OaBizBindInfo> sobjectlist=null;//主动关联
        List<OaBizBindInfo> eobjectlist=null;//被动关联
        String djid=request.getParameter("djid");
        if(StringUtils.isNotBlank(djid)){//准备获得与所给业务有联系的两种集合
            this.s_endDjid=djid;
            eobjectlist=this.getObjectlist();
            //给集合中的每个对象都封装一个关联对象
            for(OaBizBindInfo bean :eobjectlist){
                OptBaseInfo optBaseInfo=null;
                optBaseInfo=new OptBaseInfo();
                optBaseInfo=optBaseInfoManager.getObjectById(bean.getStartDjid());
                bean.setOptBaseInfo(optBaseInfo);
            }
            
            this.s_startDjid=djid;
            sobjectlist=this.getObjectlist();
          //给集合中的每个对象都封装一个关联对象
            for(OaBizBindInfo bean :sobjectlist){
                OptBaseInfo optBaseInfo=null;
                optBaseInfo=new OptBaseInfo();
                optBaseInfo=optBaseInfoManager.getObjectById(bean.getEndDjid());
                bean.setOptBaseInfo(optBaseInfo);
            }
            //去重
            removeDuplicate(sobjectlist);
            removeDuplicate(eobjectlist);
            //写入会话
            request.setAttribute("sobjectlist", sobjectlist);
            request.setAttribute("eobjectlist", eobjectlist);
            
            request.setAttribute("startDjid", s_startDjid);
        }
        return "listBizbind4tab";
        
    }
    /**
     * 删除关联事项
     */
    public String delete(){
        OaBizBindInfo oabb=null;
        if(StringUtils.isNotBlank(object.getNo()))
        oabb=oaBizBindInfoMag.getObjectById(object.getNo());
        if(null!=oabb)
        oaBizBindInfoMag.deleteObject(oabb);      
        return SUCCESS;
    }
    /**
     * 删除关联事项(tab页上使用)
     */
    public String delete4tab(){
        OaBizBindInfo oabb=null;
        if(StringUtils.isNotBlank(object.getNo()))
        oabb=oaBizBindInfoMag.getObjectById(object.getNo());
        if(null!=oabb)
        oaBizBindInfoMag.deleteObject(oabb);
        return "deleteSuccess";
    }
}
