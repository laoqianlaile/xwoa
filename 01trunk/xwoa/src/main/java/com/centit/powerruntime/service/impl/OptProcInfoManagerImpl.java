package com.centit.powerruntime.service.impl;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.IncomeDocDao;
import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.powerruntime.dao.OptIdeaInfoDao;
import com.centit.powerruntime.dao.OptProcAttentionDao;
import com.centit.powerruntime.dao.OptProcInfoDao;
import com.centit.powerruntime.dao.OptStuffInfoDao;
import com.centit.powerruntime.dao.VOptShowIdeaInfoDao;
import com.centit.powerruntime.dao.VOptStuffInfoDao;
import com.centit.powerruntime.dao.VoptIdeaInfoDao;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcAttention;
import com.centit.powerruntime.po.OptProcAttentionId;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.VOptShowIdeaInfo;
import com.centit.powerruntime.po.VOptStuffInfo;
import com.centit.powerruntime.po.VoptIdeaInfo;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.workflow.sample.dao.WfNodeInstanceDao;
import com.centit.workflow.sample.po.WfNodeInstance;

public class OptProcInfoManagerImpl extends BaseEntityManagerImpl<OptProcInfo>
	implements OptProcInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OptProcInfoManager.class);

	private OptProcInfoDao optProcInfoDao ;
	private VoptIdeaInfoDao vOptIdeaInfoDao;
    public void setOptProcInfoDao(OptProcInfoDao baseDao)
	{
		this.optProcInfoDao = baseDao;
		setBaseDao(this.optProcInfoDao);
	}
    private IncomeDocDao incomeDocDao;
	
    private OptStuffInfoDao optStuffInfoDao ;
    
    private VOptStuffInfoDao vOptStuffInfoDao ;
    private WfNodeInstanceDao nodeInstanceDao;
    
    public void setvOptStuffInfoDao(VOptStuffInfoDao vOptStuffInfoDao) {
        this.vOptStuffInfoDao = vOptStuffInfoDao;
    }

    public void setOptStuffInfoDao(OptStuffInfoDao baseDao)
    {
        this.optStuffInfoDao = baseDao;
    }
    
    public void setIncomeDocDao(IncomeDocDao incomeDocDao) {
        this.incomeDocDao = incomeDocDao;
    }
    private OptIdeaInfoDao optIdeaInfoDao ;
  
    public void setOptIdeaInfoDao(OptIdeaInfoDao baseDao)
    {
        this.optIdeaInfoDao = baseDao;
    }
    private VOptShowIdeaInfoDao vOptShowIdeaInfoDao ;
    
    

    public void setVOptShowIdeaInfoDao(VOptShowIdeaInfoDao vOptShowIdeaInfoDao) {
        this.vOptShowIdeaInfoDao = vOptShowIdeaInfoDao;
    }
    private OptProcAttentionDao optProcAttentionDao ;
    public void setOptProcAttentionDao(OptProcAttentionDao baseDao)
    {
        this.optProcAttentionDao = baseDao;
    }
    
    @Override
    public List<OptProcInfo> listProcessByDjId(String djId) {
        return optProcInfoDao.listObjects("From OptProcInfo where djId = ? order by nodeInstId",djId);
    }
    @Override
    public List<OptStuffInfo> listStuffsByDjId(String djId) {
        return optStuffInfoDao.listObjects("From OptStuffInfo where djId = ? order by uploadtime",djId);
    }
    
    public List<VOptStuffInfo> getStuffsByDjId(String djId) {
        return vOptStuffInfoDao.listObjects("From VOptStuffInfo where djId = ? order by uploadtime",djId);
    }
    @Override
    /**
     * 正文
     * @param djId
     * @return
     */  
    public List<OptStuffInfo> listStuffsByDjIdGroupByStuffname1(String djId){
        return optStuffInfoDao.listObjects("From OptStuffInfo where djId =? and groupid is null", djId);
//        return optStuffInfoDao.listObjects("From OptStuffInfo where djId =? and archivetype not in ('fj','nwfj','zw','zw-pdf')", djId);
    }
    
    public List<VOptStuffInfo> getStuffsByDjIdGroupByStuffname1(String djId){
        //return vOptStuffInfoDao.listObjects("From VOptStuffInfo where djId =? and groupid is null", djId);//此处查询的不准确；
        return vOptStuffInfoDao.listObjects("From VOptStuffInfo where djId =? order by uploadtime", djId);//查询所有
//        return vOptStuffInfoDao.listObjects("From VOptStuffInfo where djId =? and filetype=1", djId);
//        return vOptStuffInfoDao.listObjects("From VOptStuffInfo where djId =? and archivetype not in ('fj','nwfj','zw','zw-pdf')", djId);
    }
    @SuppressWarnings("unchecked")
    @Override
    /**
     * 附件
     * @param djId
     * @return
     */    
    public List<Object[]> listStuffsByDjIdGroupByStuffname0(String djId){
        return (List<Object[]>) optStuffInfoDao.findObjectsBySql("SELECT stuffname, count(*),filetype From Opt_Stuff_Info where dj_Id =? and groupid is not null group by stuffname, filetype", djId);
//        return optStuffInfoDao.findObjectsBySql("SELECT stuffname, count(*),filetype From Opt_Stuff_Info where dj_Id =? and filetype=0 group by stuffname, filetype", djId);
//        List<Object[]> list = optStuffInfoDao.findObjectsBySql("SELECT stuffname, count(*), archivetype From Opt_Stuff_Info where dj_Id =? and archivetype in ('fj','nwfj','zw','zw-pdf') group by stuffname, archivetype", djId);
//        for (int i=0; i<list.size(); i++) {
//            if ("fj".equals(list.get(i)[2])) {
//                list.get(i)[2] = "1";
//            } else {
//                list.get(i)[2] = "2";
//            }
//        }
//        return list;
    }
 
    
    public List<OptStuffInfo> listStuffsByNode(Long nodeInstId) {
        return optStuffInfoDao.listObjects("From OptStuffInfo where nodeInstId = ? order by uploadtime",nodeInstId);
    }
    
    public List<OptStuffInfo> listStuffsByType(String djId ,String fileType) {
        return optStuffInfoDao.listObjects("From OptStuffInfo where djId = ? and filetype = ? order by uploadtime",
                new Object[]{djId,fileType});
    }
    
    @Override
    public List<WfNodeInstance> listfNodeInstanceByUser(String userCode) {
        String hql = "select *from wf_node_instance w where w.lastupdateuser = '"
                + userCode + "' and (w.nodeinstid  in ( select w1.prevnodeinstid from wf_node_instance w1 where w1.nodestate = 'N' and  not EXISTS (select * from wf_node_instance w2 where w2.nodestate = 'C' and  w2.prevnodeinstid = w1.prevnodeinstid)) or (w.nodeid in (select nodeid from wf_node where opttype = 'C') and w.nodeinstid in (select nodeinstid  from wf_action_task where taskstate ='C' and usercode = '"
                + userCode + "'))) order by w.nodeinstid desc";
        
        return  (List<WfNodeInstance>)nodeInstanceDao.findObjectsBySql(hql, null, WfNodeInstance.class);
    }
    
    @Override
    public OptStuffInfo getStuffById(String id) {
         return optStuffInfoDao.getObjectById(id);
    }
    @Override
    public void saveStuffInfo(OptStuffInfo stuffInfo) {
        IncomeDoc incomeDoc = incomeDocDao.getObjectById(stuffInfo.getDjId());
        
        if (null != incomeDoc && "W".equalsIgnoreCase(incomeDoc.getItemSource())) {
            stuffInfo.setIsUpload("0"); // 默认置'0'，待外网同步后，将置为'1'
        }
        optStuffInfoDao.saveObject(stuffInfo);
    }
    @Override
    public void deleteStuffInfo(OptStuffInfo o) {
        optStuffInfoDao.deleteObject(o);
    }
    @Override
    public void deleteOptStuffInfoById(String id) {
        optStuffInfoDao.deleteObjectById(id);
    }
    public void isInuse(String djid){
        optStuffInfoDao.isInuse(djid);
    }
    
    
    @Override
    public OptProcInfo getObjectByNodeInstId(long nodeInstId)
    {
        return optProcInfoDao.getObjectByNodeInstId(nodeInstId);
    }
    
    public List<OptIdeaInfo>  listIdeaLogsByDjId(String djid){
        return optIdeaInfoDao.listObjects("From OptIdeaInfo where djId = ? order by transdate desc", djid);
     }
    
    public List<OptIdeaInfo>  listIdeaLogs(String djid,Long nodeInstId){
        return optIdeaInfoDao.listObjects("From OptIdeaInfo where djId = ? and nodeInstId =? order by transdate desc", new Object[]{djid,nodeInstId});
     }
    
    public List<VOptShowIdeaInfo>  listShowIdeaLogsByDjId(String djid){
        return vOptShowIdeaInfoDao.listObjects("From VOptShowIdeaInfo where djId = ? order by  nodeOrder asc", djid);
     }
    
    /**
     * 是否显示办理意见iframe
     * @param djId
     * @return
     */
    public  boolean isShowIdeaLogs(String djid) {

        List<VOptShowIdeaInfo> showIdealogs = this.listShowIdeaLogsByDjId(djid);
        
        return showIdealogs == null || showIdealogs.size()<=0 ? false :true ;
    }
    
    @Override
    public void saveIdeaInfo(OptIdeaInfo optIdeaInfo) {
        IncomeDoc incomeDoc = incomeDocDao.getObjectById(optIdeaInfo.getDjId());
        
        if (null != incomeDoc && "W".equalsIgnoreCase(incomeDoc.getItemSource())) {
            optIdeaInfo.setIsUpload("0"); // 默认置'0'，待外网同步后，将置为'1'
        }
        optIdeaInfoDao.saveObject(optIdeaInfo);
    }
    
    public void saveIdeaInfo(OptIdeaInfo optIdeaInfo,OptProcInfo procInfo){
        if(optIdeaInfo != null){
            optIdeaInfo.setProcid(optIdeaInfoDao.getNextIdeaPK());
            if(procInfo != null){
                optIdeaInfo.setDjId(procInfo.getDjId());
                optIdeaInfo.setNodeInstId(procInfo.getNodeInstId());
                optIdeaInfo.setNodeinststate(procInfo.getNodeinststate());
                optIdeaInfo.setNodename(procInfo.getNodename());
                optIdeaInfo.setTransdate(procInfo.getTransdate());
                optIdeaInfo.setOptcode(procInfo.getOptcode());
                optIdeaInfo.setTranscontent(procInfo.getTranscontent());
                optIdeaInfo.setUnitcode(procInfo.getUnitcode());
                optIdeaInfo.setUsercode(procInfo.getUsercode());
                optIdeaInfo.setIdeacode(procInfo.getIdeacode());
                optIdeaInfo.setTransidea(null==procInfo.getTransidea()?"提交":procInfo.getTransidea());//办理决定设置默认值
                optIdeaInfo.setNodeCode(procInfo.getNodeCode());
                optIdeaInfo.setFlowPhase(procInfo.getFlowPhase());
           }
            
            IncomeDoc incomeDoc = incomeDocDao.getObjectById(optIdeaInfo.getDjId());
            
            if (null != incomeDoc && "W".equalsIgnoreCase(incomeDoc.getItemSource())) {
                optIdeaInfo.setIsUpload("0"); // 默认置'0'，待外网同步后，将置为'1'
            }
            
            optIdeaInfoDao.saveObject(optIdeaInfo);
        }
    }

    @Override
    public void deleteIdeaInfo(OptIdeaInfo o) {
        optIdeaInfoDao.deleteObjectById(o);
    }

    @Override
    public void deleteIdeaInfoById(String id) {
        optIdeaInfoDao.deleteObjectById(id);
    }

    @Override
    public List<OptProcAttention> listProcAttentionsByDjId(String djid) {
        return optProcAttentionDao.listObjects("From OptProcAttention where cid.djId = ? order by attsettime", djid);
    }

    @Override
    public void saveProcAttention(OptProcAttention o) {
        optProcAttentionDao.saveObject(o);
    }

    @Override
    public void deleteProcAttention(OptProcAttention o) {
        optProcAttentionDao.deleteObject(o);
        
    }

    @Override
    public void deleteProcAttentionById(String djid, String usercode) {
        optProcAttentionDao.deleteObjectById(new OptProcAttentionId(djid, usercode));
    }

    @Override
    public void deleteProcAttentionByDjId(String djid) {
        optProcAttentionDao.doExecuteHql("delete From OptProcAttention where cid.djId = ?", djid);
    }

  

    @Override
    public void deleteStuffByiszhi(String sortId) {
        optStuffInfoDao.deleteStuffByiszhi(sortId);      
    }

    @Override
    public void saveAtt(OptProcAttention optProcAttention) {
        optProcAttentionDao.saveObject(optProcAttention);
    }

    @Override
    public List<OptIdeaInfo> listIdeaLogsByDjIdAndFlowPhase(String djid,
            String flowPhase) {
        return optIdeaInfoDao.listObjects("From OptIdeaInfo where djId = ? and flowPhase like "+HQLUtils.buildHqlStringForSQL(flowPhase+"%")+" order by procid desc",djid);
    }

    @Override
    public OptIdeaInfo getOptIdeaInfoByNodeInstId(long nodeInstId) {
        return optIdeaInfoDao.getObjectByNodeInstId(nodeInstId);
    }
    
   public List<OptStuffInfo> getStuffInfoList(OptProcInfo opi){
       String djid=opi.getDjId();
       String nodeinstid=opi.getNodeInstId().toString();
       List<OptStuffInfo> retlist=this.optStuffInfoDao.getStuffInfoListByNodeinstid(djid,nodeinstid);
       return retlist;
   }

   @Override
   public List<OptStuffInfo> listStuffsByArchiveType(String djId,
           String archiveType) {
       return optStuffInfoDao.listObjects("From OptStuffInfo where djId = ? and archivetype = ? order by uploadtime",
               new Object[]{djId,archiveType});
   }

@Override
public List<OptProcInfo> getObjectsByNodeCode(String djId, String nodeCode) {
    // TODO Auto-generated method stub
    return optProcInfoDao.getObjectsByNodeCode(djId, nodeCode);
}

@Override
public List<OptProcInfo> getObjectsByNodeCode(String djId, String[] nodeCode){
    return optProcInfoDao.getObjectsByNodeCode(djId, nodeCode);
}

@Override
public OptProcInfo getObjectByNodeInstIdAndDjId(Long nodeInstId, String djId) {
    // TODO Auto-generated method stub
    return optProcInfoDao.getObjectByNodeInstIdAndDjId(nodeInstId, djId);
}

/**
 * 根据业务主键 获取过程日志信息中有文书的节点
 * @param djId
 * @return
 */
@Override
public List<OptProcInfo> getObjectOfRecordIdById(String djId) {
    // TODO Auto-generated method stub
    return optProcInfoDao.getObjectOfRecordIdById(djId);
}

@Override
public List<OptIdeaInfo> listIdeaLogsByDjIdAndNodeCode(String djId,
        String nodeCode) {
    return optIdeaInfoDao.listObjects("From OptIdeaInfo where djId = ? and nodeCode = ? order by transdate desc",
            new Object[]{djId,nodeCode});
}
/**
 * 根据djid以及环节代码获取对应过程信息
 * 环节代码 nodecode1，nodecode2
 */
public List<OptIdeaInfo>  listIdeaLogsByDjIdAndNodeCodes(String djid,String nodecodes){
    StringBuffer sb=new StringBuffer();
    sb.append(" From OptIdeaInfo where djId =  " +HQLUtils.buildHqlStringForSQL(djid));
    if(StringUtils.isNotBlank(nodecodes)){
        sb.append(" and nodeCode in  "+HQLUtils.convertStringsByQuote(nodecodes.split(",")));
     }
    sb.append(" order by transdate desc ");
    return optIdeaInfoDao.listObjects(sb.toString());
 }

/**
 * 根据djid以及模块代码获取对应过程信息
 * 按useroder 优先排序
 * moduleCode
 */
@SuppressWarnings("unchecked")
public List<OptIdeaInfo>  listIdeaLogsByDjIdAndModuleCode(String djid,String moduleCode){
    StringBuffer sb=new StringBuffer();
    sb.append(" select * From OPT_IDEA_INFO  o left  join   F_USERINFO f  on  o.usercode=f.usercode where     o.dj_Id =  " +HQLUtils.buildHqlStringForSQL(djid));
    if(StringUtils.isNotBlank(moduleCode)){
        sb.append(" and o.moduleCode in  "+HQLUtils.convertStringsByQuote(moduleCode.split(",")));
     }
    sb.append(" order by f.userorder  ,o.transdate  ");

    return (List<OptIdeaInfo>) optIdeaInfoDao.findObjectsBySql(sb.toString(), null, OptIdeaInfo.class);
 }

/**
 *  根据通用模块配置、流程环节代码获取流程办理日志信息
 * @param djId
 * @param nodeCodes
 * @return
 */
public List<OptIdeaInfo> listIdeaLogsByDjIdAndModuleCodeXW(String djid,
        String moduleCode,String unitcode,String flag){
    StringBuffer sb=new StringBuffer();
    sb.append(" select * From OPT_IDEA_INFO  o left  join   F_USERINFO f  on  o.usercode=f.usercode where     o.dj_Id =  " +HQLUtils.buildHqlStringForSQL(djid));
    if(StringUtils.isNotBlank(moduleCode)){
        sb.append(" and o.moduleCode in  "+HQLUtils.convertStringsByQuote(moduleCode.split(",")));
     }
    sb.append(" order by f.userorder  ,o.transdate  ");
    return (List<OptIdeaInfo>) optIdeaInfoDao.findObjectsBySql(sb.toString(), null, OptIdeaInfo.class);
}
@Override
public List<OptProcInfo> getObjectOfRecordIdById(String djId,
        String documentType) {
    // TODO Auto-generated method stub
    return optProcInfoDao.getObjectOfRecordIdById(djId,documentType);
}
public List<OptIdeaInfo>  listIdeaLogsByNodeInstIdAndDjId(Long nodeInstId,String djid){
    return optIdeaInfoDao.listObjects("From OptIdeaInfo where nodeInstId=? and djId = ? order by procid desc",new Object[]{nodeInstId,djid});
 }

@Override
public OptProcInfo getObjectByDjidAndNodeInstId(String djId, Long nodeInstId,
        String usercode) {
    return optProcInfoDao.getObjectByDjidAndNodeInstId(djId, nodeInstId, usercode);
}



@Override
public List<VoptIdeaInfo> listIdeaLogsBytoday() {
    
    return vOptIdeaInfoDao.listObjects("From VoptIdeaInfo where  transdate>=sysdate-1 order by transdate desc");
}

public void setvOptIdeaInfoDao(VoptIdeaInfoDao vOptIdeaInfoDao) {
    this.vOptIdeaInfoDao = vOptIdeaInfoDao;
}

public void setvOptShowIdeaInfoDao(VOptShowIdeaInfoDao vOptShowIdeaInfoDao) {
    this.vOptShowIdeaInfoDao = vOptShowIdeaInfoDao;
}

@Override
public void deleteLeaderOder(Long flowInstId) {
    StringBuffer sSql=new StringBuffer();
    sSql.append("delete from OA_LEADERSORT c where c.nodeinstid in(select nodeinstid from wf_node_instance a where a.wfinstid=  " +"'"+flowInstId+"'"+")");
    optIdeaInfoDao.doExecuteSql(sSql.toString());
}

public WfNodeInstanceDao getNodeInstanceDao() {
    return nodeInstanceDao;
}

public void setNodeInstanceDao(WfNodeInstanceDao nodeInstanceDao) {
    this.nodeInstanceDao = nodeInstanceDao;
}


}

