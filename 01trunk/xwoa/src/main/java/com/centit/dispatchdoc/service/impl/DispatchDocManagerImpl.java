package com.centit.dispatchdoc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.dispatchdoc.dao.*;
import com.centit.dispatchdoc.po.*;
import com.centit.sys.util.DateUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.service.ApplyUnitInfoManager;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.powerruntime.dao.OptApplyDao;
import com.centit.powerruntime.dao.OptBaseInfoDao;
import com.centit.powerruntime.dao.OptIdeaInfoDao;
import com.centit.powerruntime.dao.OptProcInfoDao;
import com.centit.powerruntime.dao.OptStuffInfoDao;
import com.centit.powerruntime.dao.TemplateFileDao;
import com.centit.powerruntime.po.OptApplyInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.OptFlowNoInfoManager;
import com.centit.sys.service.WorkCalendar;
import com.centit.workflow.FlowEngine;

public class DispatchDocManagerImpl extends BaseEntityManagerImpl<DispatchDoc>
	implements DispatchDocManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(DispatchDocManager.class);

	private DispatchDocDao dispatchDocDao ;
	private DocRelativeDao docRelativeDao;
	private VDispatchDocListDao vDispatchDocListDao;
	private OptBaseInfoDao optBaseInfoDao;
	private OptApplyDao optApplyDao;
	private IncomeDocDao incomeDocDao;
	private IncomeProjectDao incomeProjectDao;
	private TemplateFileDao templateFileDao;
	private OptStuffInfoDao optStuffInfoDao;
	private VProcTransUsersDao vprocTransUsersDao;
	private OptIdeaInfoDao optIdeaInfoDao;
	
	private OptFlowNoInfoManager optFlowNoInfoManager;
	@SuppressWarnings("unused")
    private ApplyUnitInfoManager applyUnitInfoManager;
	private OptProcInfoDao optProcInfoDao;
	protected FlowEngine flowEngine;
	private WorkCalendar workCalendar;
	private OaLeaderSortDao oaLeaderSortDao;
	
    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    public void setOptProcInfoDao(OptProcInfoDao optProcInfoDao) {
        this.optProcInfoDao = optProcInfoDao;
    }

    public void setVprocTransUsersDao(VProcTransUsersDao vprocTransUsersDao) {
        this.vprocTransUsersDao = vprocTransUsersDao;
    }

    public void setIncomeProjectDao(IncomeProjectDao incomeProjectDao) {
        this.incomeProjectDao = incomeProjectDao;
    }

    public void setIncomeDocDao(IncomeDocDao incomeDocDao) {
        this.incomeDocDao = incomeDocDao;
    }

    public void setOptBaseInfoDao(OptBaseInfoDao optBaseInfoDao) {
        this.optBaseInfoDao = optBaseInfoDao;
    }

    public void setOptApplyDao(OptApplyDao optApplyDao) {
        this.optApplyDao = optApplyDao;
    }
    
    
    public OptIdeaInfoDao getOptIdeaInfoDao() {
        return optIdeaInfoDao;
    }

    public void setOptIdeaInfoDao(OptIdeaInfoDao optIdeaInfoDao) {
        this.optIdeaInfoDao = optIdeaInfoDao;
    }

    public String getNextkey() {
        return "FW" + dispatchDocDao.getNextKeyBySequence("S_DISPATCH_DOC", 14);
    }
    
    public DispatchDoc getDispatchDoc(String internalNo, String itemId){
        return dispatchDocDao.getDispatchDoc(internalNo, itemId);
    }
	
    /**
     * 查询视图v_dispatchdoc(未提交发文/已办结发文)
     * 
     * @return
     */
    public List<VDispatchDocList> getVDispatchDocList(Map<String, Object> filterMap,PageDesc pageDesc){
        
        return vDispatchDocListDao.listObjects(filterMap, pageDesc);
         
    }
    
    public VDispatchDocListDao getvDispatchDocListDao() {
        return vDispatchDocListDao;
    }

    public void setvDispatchDocListDao(VDispatchDocListDao vDispatchDocListDao) {
        this.vDispatchDocListDao = vDispatchDocListDao;
    }

    public DocRelativeDao getDocRelativeDao() {
        return docRelativeDao;
    }

    public void setDocRelativeDao(DocRelativeDao docRelativeDao) {
        this.docRelativeDao = docRelativeDao;
    }

    public void setDispatchDocDao(DispatchDocDao baseDao)
    {
        this.dispatchDocDao = baseDao;
        setBaseDao(this.dispatchDocDao);
    }
    
    public String getOptLeaderShip(String djId) {
        return dispatchDocDao.getOptLeaderShip(djId);
    }
    
    public JSONObject getOptBaseInfoJSONByDjId(String djId){
       
        JSONObject jsonObj = new JSONObject();
     
        OptBaseInfo optBaseInfo = optBaseInfoDao.getObjectById(djId);
        if(optBaseInfo != null && !StringBaseOpt.isNvl(optBaseInfo.getDjId())){
           
            jsonObj.put("transaffairname", optBaseInfo.getTransaffairname());  
            jsonObj.put("orgname", optBaseInfo.getOrgname());  
            jsonObj.put("headunitcode", optBaseInfo.getHeadunitcode());  
            jsonObj.put("headusercode", optBaseInfo.getHeadusercode());  
            jsonObj.put("content", optBaseInfo.getContent());  
            jsonObj.put("powerid", optBaseInfo.getPowerid());  
            jsonObj.put("powername", optBaseInfo.getPowername());  
            jsonObj.put("solvestatus", optBaseInfo.getSolvestatus());  
            jsonObj.put("solvetime",DatetimeOpt.convertDateToString(optBaseInfo.getSolvetime(), "yyyy年MM月dd日"));  
            jsonObj.put("solvenote", optBaseInfo.getSolvenote());  
            jsonObj.put("createuser", optBaseInfo.getCreateuser());  
            jsonObj.put("createdate", DatetimeOpt.convertDateToString( optBaseInfo.getCreatedate(), "yyyy年MM月dd日"));
            jsonObj.put("transAffairNo", optBaseInfo.getTransAffairNo());
            jsonObj.put("transAffairQueryKey", optBaseInfo.getTransAffairQueryKey());
            //jsonObj.put("optProcInfos", optBaseInfo.getOptProcInfos());    
            //jsonObj.put("optProcAttentions", optBaseInfo.getOptProcAttentions());  
            //jsonObj.put("optStuffInfos", optBaseInfo.getOptStuffInfos());
            jsonObj.put("sendArchiveNo", optBaseInfo.getSendArchiveNo());
            jsonObj.put("acceptArchiveNo", optBaseInfo.getAcceptArchiveNo());
            
            //判断是否有收文信息，有则加载并合并JSON数据
            IncomeDoc incomeDoc = incomeDocDao.getObjectById(djId);
            if(incomeDoc != null && !StringBaseOpt.isNvl(incomeDoc.getDjId())){
                getIncomeDocJSONByDjId(jsonObj,incomeDoc);
            }
            
            //判断是否有发文信息，有则加载并合并JSON数据
            DispatchDoc dispatchDoc =  dispatchDocDao.getObjectById(djId);
            if(dispatchDoc != null && !StringBaseOpt.isNvl(dispatchDoc.getInternalNo())){
                getDispatchDocJSONByDjId(jsonObj,dispatchDoc);
            }
            
            //判断是否有项目信息，有则加载并合并JSON数据
            IncomeProject incomeProject = incomeProjectDao.getObjectById(djId);
            if(incomeProject != null && !StringBaseOpt.isNvl(incomeProject.getDjId())){
                getIncomeProjectJSONByDjId(jsonObj,incomeProject);
            } else { // 进口设备免税使用，没有incomeProject表单信息，但是有项目名称
                if (null != incomeDoc && "SBMS".equals(incomeDoc.getApplyItemType())) {
                    jsonObj.put("projectName", incomeDoc.getProjectName());
                    jsonObj.put("projectbuildaddr", incomeDoc.getApplyUserAddr());
                    jsonObj.put("contactName", incomeDoc.getApplyUser());
                    jsonObj.put("contactPhone", incomeDoc.getApplyUserPhone());
                    jsonObj.put("adminAreaZip", incomeDoc.getApplyUserZip());
                }
            }
        }
       
        return jsonObj;
    }
    
    public JSONObject getOptProcInfoJSONByDjId(String djId){
        JSONObject jsonObj = new JSONObject();
        List<OptProcInfo> list = optProcInfoDao.getObjectsByNodeCode(djId, new String[]{"hbcssh", "N_hbcssh"});
        List<OptProcInfo> list1 = optProcInfoDao.getObjectsByNodeCode(djId, new String[]{"bgszrsh", "N_bgszrsh"});
        String begDate = "";
        if(list1.size() > 0){
            begDate = DatetimeOpt.convertDateToString(list1.get(list1.size() - 1).getTransdate(), "yyyy年MM月dd日");
        }
        List<OptProcInfo> zbList = optProcInfoDao.getObjectsByNodeCode(djId, new String[]{"zbcsfzrsh", "N_zbcsfzrsh"});
        if(zbList.size() > 0){
            for(int i = 0; i < zbList.size(); i++){
                OptProcInfo info = zbList.get(i);
                jsonObj.put("unitname_"+(i+1), CodeRepositoryUtil.getValue("unitcode",  info.getUnitcode()) == null ? "" :CodeRepositoryUtil.getValue("unitcode", info.getUnitcode()));  
                jsonObj.put("begDate_"+(i+1), begDate);
                jsonObj.put("username_"+(i+1), CodeRepositoryUtil.getValue("usercode",  info.getUsercode()) == null ? "" :CodeRepositoryUtil.getValue("usercode", info.getUsercode()));
                jsonObj.put("endDate_"+(i+1), DatetimeOpt.convertDateToString(info.getTransdate(), "yyyy年MM月dd日"));
            }
        }
        if(list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                OptProcInfo info = list.get(i);
                jsonObj.put("unitname_"+(i+2), CodeRepositoryUtil.getValue("unitcode",  info.getUnitcode()) == null ? "" :CodeRepositoryUtil.getValue("unitcode", info.getUnitcode()));  
                jsonObj.put("begDate_"+(i+2), begDate);
                jsonObj.put("username_"+(i+2), CodeRepositoryUtil.getValue("usercode",  info.getUsercode()) == null ? "" :CodeRepositoryUtil.getValue("usercode", info.getUsercode()));
                jsonObj.put("endDate_"+(i+2), DatetimeOpt.convertDateToString(info.getTransdate(), "yyyy年MM月dd日"));
            }
        }
//        }else{
//            OptBaseInfo optBaseInfo = optBaseInfoDao.getObjectById(djId);
//            Long flowInstId = optBaseInfo.getFlowInstId();
//            List<WfOrganize> deplist = flowEngine.viewFlowOrganizeList(flowInstId, "hbcsfb");
//            if(deplist.size() == 0){
//                deplist = flowEngine.viewFlowOrganizeList(flowInstId, "zbcshq");
//            }
//            List<WfOrganize> zblist = flowEngine.viewFlowOrganizeList(flowInstId, "zbcsfb");
//            for(int i = 0; i < zblist.size(); i++){
//                WfOrganize info = zblist.get(i);
//                jsonObj.put("unitname_"+(i+1), CodeRepositoryUtil.getValue("unitcode",  info.getUnitCode()) == null ? "" :CodeRepositoryUtil.getValue("unitcode", info.getUnitCode()));  
//                jsonObj.put("begDate_"+(i+1), begDate);
//                jsonObj.put("username_"+(i+1), "");
//                jsonObj.put("endDate_"+(i+1), "");
//            }
//            for(int i = 0; i < deplist.size(); i++){
//                WfOrganize info = deplist.get(i);
//                jsonObj.put("unitname_"+(i+2), CodeRepositoryUtil.getValue("unitcode",  info.getUnitCode()) == null ? "" :CodeRepositoryUtil.getValue("unitcode", info.getUnitCode()));  
//                jsonObj.put("begDate_"+(i+2), begDate);
//                jsonObj.put("username_"+(i+2), "");
//                jsonObj.put("endDate_"+(i+2), "");
//            }
//        }
        return jsonObj;
    }
    
    public JSONObject getOptSLHJSONByDjId(String djId){
        JSONObject jsonObj = new JSONObject();
        
        OptBaseInfo optBaseInfo = optBaseInfoDao.getObjectById(djId);
        if(optBaseInfo != null && !StringBaseOpt.isNvl(optBaseInfo.getDjId())){
            jsonObj.put("sendArchiveNo", optBaseInfo.getSendArchiveNo()); 
        }
        return jsonObj;
        
    }
    public JSONObject getOptBaseInfoJSONByDjId(String djId,String documentTemplateIds,String primaryUnit){
        
        JSONObject jsonObj = new JSONObject();
     
        OptBaseInfo optBaseInfo = optBaseInfoDao.getObjectById(djId);
        if(optBaseInfo != null && !StringBaseOpt.isNvl(optBaseInfo.getDjId())){
           
            jsonObj.put("transaffairname", optBaseInfo.getTransaffairname());  
            jsonObj.put("orgname", optBaseInfo.getOrgname());  
            jsonObj.put("headunitcode", CodeRepositoryUtil.getValue("unitcode",  optBaseInfo.getHeadunitcode()) == null ? "" :CodeRepositoryUtil.getValue("unitcode", optBaseInfo.getHeadunitcode()));  
            jsonObj.put("headusercode", optBaseInfo.getHeadusercode());  
            jsonObj.put("content", optBaseInfo.getContent());  
            jsonObj.put("powerid", optBaseInfo.getPowerid());  
            jsonObj.put("powername", optBaseInfo.getPowername());  
            jsonObj.put("solvestatus", optBaseInfo.getSolvestatus());
            jsonObj.put("solvetime", optBaseInfo.getSolvetime()!=null?DatetimeOpt.convertDateToString(optBaseInfo.getSolvetime(), "yyyy年MM月dd日"):"");
            jsonObj.put("solvenote", optBaseInfo.getSolvenote());  
            jsonObj.put("createuser", optBaseInfo.getCreateuser());  
            jsonObj.put("createdate", optBaseInfo.getCreatedate());
            jsonObj.put("transAffairNo", optBaseInfo.getTransAffairNo());
            jsonObj.put("transAffairQueryKey", optBaseInfo.getTransAffairQueryKey());
            //jsonObj.put("optProcInfos", optBaseInfo.getOptProcInfos());    
            //jsonObj.put("optProcAttentions", optBaseInfo.getOptProcAttentions());  
            //jsonObj.put("optStuffInfos", optBaseInfo.getOptStuffInfos());
            jsonObj.put("sendArchiveNo", optBaseInfo.getSendArchiveNo());
            jsonObj.put("acceptArchiveNo", optBaseInfo.getAcceptArchiveNo());
            
            //判断是否有收文信息，有则加载并合并JSON数据
            IncomeDoc incomeDoc = incomeDocDao.getObjectById(djId);
            if(incomeDoc != null && !StringBaseOpt.isNvl(incomeDoc.getDjId())){
                getIncomeDocJSONByDjId(jsonObj,incomeDoc);
            }
            
            //判断是否有发文信息，有则加载并合并JSON数据
            DispatchDoc dispatchDoc =  dispatchDocDao.getObjectById(djId);
            if(dispatchDoc != null && !StringBaseOpt.isNvl(dispatchDoc.getInternalNo())){
                getDispatchDocJSONByDjId(jsonObj,dispatchDoc);
            }
            
            //判断操作参数是否设置文书情况:有则根据规则生成对应的文书流水号
            if (StringUtils.isNotBlank(documentTemplateIds)) {

                String[] recordIds = documentTemplateIds.split(",");
                for (String recordId : recordIds) {
                    // 从模板中获取文号规则
                    TemplateFile templateFile = templateFileDao
                            .getTemplateByRecordId(recordId);
                    if (templateFile != null) {
                        String codeCode = templateFile.getCodeCode();// 文号规则
                        if (StringUtils.isNotBlank(codeCode)) {
                            // 通过业务编号、文书类型获取文书附件信息
                            if (!StringBaseOpt.isNvl(optBaseInfo.getCaseNo())) {
                                jsonObj.put(codeCode, optBaseInfo.getCaseNo());
                            } else {// 如果没有生成过，则新生成
                                String wsno = optFlowNoInfoManager.genFWH(
                                        codeCode, null, primaryUnit, null);
                                jsonObj.put(codeCode, wsno);
                                optBaseInfo.setCaseNo(wsno);
                                optBaseInfoDao.saveObject(optBaseInfo);
                            }

                        }
                    }
                }
            }        
            
            //getProcTransUsersJSONByDjId(jsonObj, djId);
            getOptStuffJSONByDjId(jsonObj, djId);            
        }
       
        return jsonObj;
    }
    
    /**
     * 根据登记id和文书类型组（支持多个附件类型）判断文书是否保存
     * @param djId
     * @param documentTemplateIds
     * @return 当已保存文书类型组中任意一个文书时返回true；一个没存时返回false
     */
    public boolean isHaveWordByDjidAndTemplateIds(String djId,String documentTemplateIds){
        boolean bl = false;
        if (StringUtils.isNotBlank(documentTemplateIds)) {
            String[] recordIds = documentTemplateIds.split(",");
            for (String recordId : recordIds) {
                // 从模板中获取文号规则
                TemplateFile templateFile = templateFileDao
                        .getTemplateByRecordId(recordId);
                if (templateFile != null) {                   
                    // 通过业务编号、文书类型获取文书附件信息
                    OptStuffInfo optStuffInfo = optStuffInfoDao
                            .getStuffInfoByArchiveType(djId,
                                    templateFile.getTempType());
                    if (optStuffInfo != null
                            && StringUtils.isNotBlank(optStuffInfo
                                    .getWsno())) {// 判断是否已经生成过文号
                        bl = true;
                        break;
                    }
                    
                }
            }
        }
        return bl;
    }
    public JSONObject getOptApplyInfoJSONByDjId(String djId){
       
        JSONObject jsonObj = new JSONObject();
      
        OptApplyInfo optApplyInfo = optApplyDao.getObjectById(djId);
        if(optApplyInfo != null && !StringBaseOpt.isNvl(optApplyInfo.getDjId())){
            jsonObj.put("applyDate",  DatetimeOpt.convertDateToString(optApplyInfo.getApplyDate(), "yyyy年MM月dd日"));
            jsonObj.put("proposerName", optApplyInfo.getProposerName());
            jsonObj.put("applyItemType", optApplyInfo.getApplyItemType());
            jsonObj.put("applyReason", optApplyInfo.getApplyReason());
            jsonObj.put("applyWay", optApplyInfo.getApplyWay());
            jsonObj.put("proposerType", optApplyInfo.getProposerType());
            jsonObj.put("proposerPaperType", optApplyInfo.getProposerPaperType());
            jsonObj.put("proposerPaperCode", optApplyInfo.getProposerPaperCode());
            jsonObj.put("proposerPhone", optApplyInfo.getProposerPhone());
            jsonObj.put("proposerMobile", optApplyInfo.getProposerMobile());
            jsonObj.put("proposerAddr", optApplyInfo.getProposerAddr());
            jsonObj.put("proposerZipcode", optApplyInfo.getProposerZipcode());
            jsonObj.put("proposerEmail", optApplyInfo.getProposerEmail());
            jsonObj.put("proposerUnitcode", optApplyInfo.getProposerUnitcode());
            jsonObj.put("agentName", optApplyInfo.getAgentName());
            jsonObj.put("agentPaperType", optApplyInfo.getAgentPaperType());
            jsonObj.put("agentPaperCode", optApplyInfo.getAgentPaperCode());
            jsonObj.put("agentPhone", optApplyInfo.getAgentPhone());
            jsonObj.put("agentMobile", optApplyInfo.getAgentMobile());
            jsonObj.put("agentAddr", optApplyInfo.getAgentAddr());
            jsonObj.put("agentZipcode", optApplyInfo.getAgentZipcode());
            jsonObj.put("agentEmail", optApplyInfo.getAgentEmail());
            jsonObj.put("agentUnitcode", optApplyInfo.getAgentUnitcode());
            jsonObj.put("applyMemo", optApplyInfo.getApplyMemo());
            jsonObj.put("acceptDate",  DatetimeOpt.convertDateToString(optApplyInfo.getAcceptDate(), "yyyy年MM月dd日"));
            jsonObj.put("auditingDate",  DatetimeOpt.convertDateToString(optApplyInfo.getAuditingDate(), "yyyy年MM月dd日"));
            jsonObj.put("headUsercode", optApplyInfo.getHeadUsercode());
            jsonObj.put("headAuditingIdea", optApplyInfo.getHeadAuditingIdea());
            jsonObj.put("checkIdeaDate",  DatetimeOpt.convertDateToString(optApplyInfo.getCheckIdeaDate(), "yyyy年MM月dd日"));
            jsonObj.put("checkUsercode", optApplyInfo.getCheckUsercode());
            jsonObj.put("checkIdea", optApplyInfo.getCheckIdea());
            jsonObj.put("checkDetail", optApplyInfo.getCheckDetail());
            jsonObj.put("checkAddr", optApplyInfo.getCheckAddr());
            jsonObj.put("checkDate",  DatetimeOpt.convertDateToString(optApplyInfo.getCheckDate(), "yyyy年MM月dd日"));
            jsonObj.put("checkMemo", optApplyInfo.getCheckMemo());
            jsonObj.put("optBaseInfo", optApplyInfo.getOptBaseInfo());
            jsonObj.put("legal_person", optApplyInfo.getLegal_person());
        }
        
        return jsonObj;
    }
    
    /**
     * 根据业务流水号获得收文信息JSON
     * @param djId
     * @return
     */
    public void getIncomeDocJSONByDjId(JSONObject jsonObj,IncomeDoc incomeDoc){
       
            jsonObj.put("acceptNo", incomeDoc.getAcceptNo());
            jsonObj.put("incomeDocNo", incomeDoc.getIncomeDocNo());
            jsonObj.put("incomeDocTitle", incomeDoc.getIncomeDocTitle());
            jsonObj.put("incomeDeptType", CodeRepositoryUtil.getDataPiece("INCOME_DEPT_TYPE",incomeDoc.getIncomeDeptType()) == null ? "" :CodeRepositoryUtil.getDataPiece("INCOME_DEPT_TYPE",incomeDoc.getIncomeDeptType()).getDatavalue() );
            jsonObj.put("incomeDeptName", incomeDoc.getIncomeDeptName());
            jsonObj.put("itemSource", incomeDoc.getItemSource());
            jsonObj.put("applyOrgCode", incomeDoc.getApplyOrgCode());
            jsonObj.put("docCreateDate",DatetimeOpt.convertDateToString(incomeDoc.getDocCreateDate(), "yyyy年MM月dd日"));
            jsonObj.put("incomeDate",DatetimeOpt.convertDateToString(incomeDoc.getIncomeDate(), "yyyy年MM月dd日"));
            jsonObj.put("secretDegree", incomeDoc.getSecretDegree());
            jsonObj.put("contactName", incomeDoc.getContactName());
            jsonObj.put("contactPhone", incomeDoc.getContactPhone());
            jsonObj.put("applyUser", incomeDoc.getApplyUser());
            jsonObj.put("applyUserPhone", incomeDoc.getApplyUserPhone());
            jsonObj.put("applyUserAddr", incomeDoc.getApplyUserAddr());
            jsonObj.put("applyUserZip", incomeDoc.getApplyUserZip());
            jsonObj.put("incomeDocFileName", incomeDoc.getIncomeDocFileName());
            jsonObj.put("applyDemo", incomeDoc.getApplyDemo());
            jsonObj.put("operateState", incomeDoc.getOperateState());
            jsonObj.put("syncErrorDesc", incomeDoc.getSyncErrorDesc());
            jsonObj.put("createDate",DatetimeOpt.convertDateToString(incomeDoc.getCreateDate(), "yyyy年MM月dd日"));
            jsonObj.put("updateDate",DatetimeOpt.convertDateToString(incomeDoc.getUpdateDate(), "yyyy年MM月dd日"));
            if (null != incomeDoc.getCaseAcceptDate() && null != incomeDoc.getSignIssueDate()) {
                int workDays = workCalendar.calcWorkDays(incomeDoc.getCaseAcceptDate(), incomeDoc.getSignIssueDate());
                jsonObj.put("workDays", 0 == workDays ? "当天" : String.valueOf(workDays));
            } else {
                jsonObj.put("workDays", "");
            }
    }
    /**
     * 
     * @param jsonObj
     * @param djId
     */
    private void getProcTransUsersJSONByDjId(JSONObject jsonObj,String djId){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("djId", djId);
        List<VProcTransUsers> list = vprocTransUsersDao.listObjects(paramMap);
        List<OptProcInfo> hqlist = optProcInfoDao.getObjectsByNodeCode(djId, new String[]{"hqxgcs", "N_hqxgcs"});
        
        if(list == null || list.isEmpty())
            return;
        for(VProcTransUsers info: list){
            if(!"hqxgcs".equals(info.getNodeCode())&& !"N_hqxgcs".equals(info.getNodeCode())){
                jsonObj.put(info.getNodeCode()+"_userName", info.getUsername());
                jsonObj.put(info.getNodeCode()+"_createDate", DatetimeOpt.convertDateToString(info.getTransdate(), "yyyy年MM月dd日"));
                jsonObj.put(info.getNodeCode()+"_transcontent", info.getTranscontent());
                jsonObj.put(info.getNodeCode()+"_unitname", info.getUnitname());
            }else{
                String userName = "";
                String unitName = "";
                String content = "";
                if(hqlist!=null && hqlist.size()>0){
                    for(OptProcInfo opt : hqlist){
                        userName += CodeRepositoryUtil.getValue("usercode",  opt.getUsercode()) == null ? "" :CodeRepositoryUtil.getValue("usercode", opt.getUsercode())+" ";
                        unitName += opt.getTranscontent()+" ";
                        content += CodeRepositoryUtil.getValue("unitcode",  opt.getUnitcode()) == null ? "" :CodeRepositoryUtil.getValue("unitcode", opt.getUnitcode())+" ";
                    }
                }
                
                jsonObj.put(info.getNodeCode()+"_userName", userName);
                jsonObj.put(info.getNodeCode()+"_createDate", DatetimeOpt.convertDateToString(info.getTransdate(), "yyyy年MM月dd日"));
                jsonObj.put(info.getNodeCode()+"_transcontent", content);
                jsonObj.put(info.getNodeCode()+"_unitname", unitName);
            }
        }
    }
    
    private void getOptStuffJSONByDjId(JSONObject jsonObj,String djId){
        List<String> list = optStuffInfoDao.getStuffInfoList(djId);
        String stuffName = "";
        for(String fileName: list){
            stuffName += fileName+"\n";
        }
        jsonObj.put("stuff_Name", stuffName);
    }
    /**
     * 根据业务流水号获得项目信息信息表的JSON
     * @param djId
     * @return
     */
    private void getIncomeProjectJSONByDjId(JSONObject jsonObj,IncomeProject incomeProject){
        jsonObj.put("projectUnitName", incomeProject.getProjectUnitName());
        jsonObj.put("economicType", CodeRepositoryUtil.getDataPiece("ECONOMIC_TYPE",incomeProject.getEconomicType()) == null ? "" :CodeRepositoryUtil.getDataPiece("ECONOMIC_TYPE",incomeProject.getEconomicType()).getDatavalue());
        jsonObj.put("industryField", CodeRepositoryUtil.getDataPiece("INDUSTRY_FIELD",incomeProject.getIndustryField()) == null ? "" :CodeRepositoryUtil.getDataPiece("INDUSTRY_FIELD",incomeProject.getIndustryField()).getDatavalue());
        jsonObj.put("membership", CodeRepositoryUtil.getDataPiece("MEMBERSHIP",incomeProject.getMembership()) == null ? "" :CodeRepositoryUtil.getDataPiece("MEMBERSHIP",incomeProject.getMembership()).getDatavalue());
        jsonObj.put("businessScope", CodeRepositoryUtil.getDataPiece("BUSINESS_SCOPE",incomeProject.getBusinessScope()) == null ? "" :CodeRepositoryUtil.getDataPiece("BUSINESS_SCOPE",incomeProject.getBusinessScope()).getDatavalue());
        jsonObj.put("registeredCapital", incomeProject.getRegisteredCapital());
        jsonObj.put("countryArea", CodeRepositoryUtil.getDataPiece("COUNTRY_AREA",incomeProject.getCountryArea()) == null ? "" :CodeRepositoryUtil.getDataPiece("COUNTRY_AREA",incomeProject.getCountryArea()).getDatavalue());
        jsonObj.put("registeredAddr", incomeProject.getRegisteredAddr());
        jsonObj.put("buildLegal", incomeProject.getBuildLegal());
        jsonObj.put("adminAreaCode", CodeRepositoryUtil.getDataPiece("ADMIN_AREA_CODE",incomeProject.getAdminAreaCode()) == null ? "" :CodeRepositoryUtil.getDataPiece("ADMIN_AREA_CODE",incomeProject.getAdminAreaCode()).getDatavalue());
        jsonObj.put("adminAreaZip", incomeProject.getAdminAreaZip());
        jsonObj.put("contactName", incomeProject.getContactName());
        jsonObj.put("contactPhone", incomeProject.getContactPhone());
        jsonObj.put("contactEmail", incomeProject.getContactEmail());
        jsonObj.put("projectName", incomeProject.getProjectName());
        jsonObj.put("projectBuildAddr", incomeProject.getProjectBuildAddr());
        String projectAreaAddr = "";
        String projectAreaCode = incomeProject.getProjectAreaCode();
        if (StringUtils.isNotBlank(projectAreaCode) && 6 == projectAreaCode.length()) {
            if (!projectAreaCode.endsWith("00")) {
                projectAreaAddr = CodeRepositoryUtil.getDataPiece("ADMIN_AREA_CODE", projectAreaCode) + projectAreaAddr;
                projectAreaCode = projectAreaCode.substring(0, 4) + "00";
            }
            if (projectAreaCode.endsWith("00") && !projectAreaCode.endsWith("0000")) {
                projectAreaAddr = CodeRepositoryUtil.getDataPiece("ADMIN_AREA_CODE", projectAreaCode) + projectAreaAddr;
                projectAreaCode = projectAreaCode.substring(0, 2) + "0000";
            }
            if (projectAreaCode.endsWith("0000")) {
                projectAreaAddr = CodeRepositoryUtil.getDataPiece("ADMIN_AREA_CODE", projectAreaCode) + projectAreaAddr;
            }
        }
        jsonObj.put("projectAreaCode", projectAreaAddr);
//            jsonObj.put("projectAreaCode", CodeRepositoryUtil.getDataPiece("ADMIN_AREA_CODE",incomeProject.getProjectAreaCode()) == null ? "" :CodeRepositoryUtil.getDataPiece("ADMIN_AREA_CODE",incomeProject.getProjectAreaCode()).getDatavalue());
        jsonObj.put("projectIndustryField", CodeRepositoryUtil.getDataPiece("INDUSTRY_FIELD",incomeProject.getProjectIndustryField()) == null ? "" :CodeRepositoryUtil.getDataPiece("INDUSTRY_FIELD",incomeProject.getProjectIndustryField()).getDatavalue());
        jsonObj.put("projectBuildType", CodeRepositoryUtil.getDataPiece("PROJ_BUILD_TYPE",incomeProject.getProjectBuildType()) == null ? "" :CodeRepositoryUtil.getDataPiece("PROJ_BUILD_TYPE",incomeProject.getProjectBuildType()).getDatavalue());
        jsonObj.put("projectMembership", incomeProject.getProjectMembership());
        jsonObj.put("projectType", CodeRepositoryUtil.getDataPiece("PROJECT_TYPE",incomeProject.getProjectType()) == null ? "" :CodeRepositoryUtil.getDataPiece("PROJECT_TYPE",incomeProject.getProjectType()).getDatavalue());
        jsonObj.put("projectNature", incomeProject.getProjectNature());
        jsonObj.put("projectBuildLand", incomeProject.getProjectBuildLand());
        jsonObj.put("buildBeginDate", incomeProject.getBuildBeginDate());
        jsonObj.put("industryMinistries", incomeProject.getIndustryMinistries());
        jsonObj.put("buildContent", incomeProject.getBuildContent());
        jsonObj.put("totalInvestment", incomeProject.getTotalInvestment());
        jsonObj.put("allowType", CodeRepositoryUtil.getDataPiece("ALLOW_TYPE",incomeProject.getAllowType()) == null ? "" :CodeRepositoryUtil.getDataPiece("ALLOW_TYPE",incomeProject.getAllowType()).getDatavalue());
        jsonObj.put("allowDocNo", incomeProject.getAllowDocNo());
        jsonObj.put("allowTime", incomeProject.getAllowTime());
        jsonObj.put("allowUnit", incomeProject.getAllowUnit());
        jsonObj.put("auditType", CodeRepositoryUtil.getDataPiece("AUDIT_TYPE",incomeProject.getAuditType()) == null ? "" :CodeRepositoryUtil.getDataPiece("AUDIT_TYPE",incomeProject.getAuditType()).getDatavalue());
        jsonObj.put("auditTime",DatetimeOpt.convertDateToString(incomeProject.getAuditTime(), "yyyy年MM月dd日"));
        jsonObj.put("auditDocNo", incomeProject.getAuditDocNo());
        jsonObj.put("auditUnit", incomeProject.getAuditUnit());
        jsonObj.put("eiaType", incomeProject.getEiaType());
        jsonObj.put("eiaDocNo", incomeProject.getEiaDocNo());
        jsonObj.put("eiaTime",DatetimeOpt.convertDateToString(incomeProject.getEiaTime(), "yyyy年MM月dd日"));
        jsonObj.put("eiaUnit", incomeProject.getEiaUnit());
        jsonObj.put("officialType", CodeRepositoryUtil.getDataPiece("OFFICIAL_TYPE",incomeProject.getOfficialType()) == null ? "" :CodeRepositoryUtil.getDataPiece("OFFICIAL_TYPE",incomeProject.getOfficialType()).getDatavalue());
        jsonObj.put("officialDocNo", incomeProject.getOfficialDocNo());
        jsonObj.put("officialTime",  DatetimeOpt.convertDateToString(incomeProject.getOfficialTime(), "yyyy年MM月dd日"));
        jsonObj.put("officialUnit", incomeProject.getOfficialUnit());
        jsonObj.put("officialBuildContent", incomeProject.getOfficialBuildContent());
        jsonObj.put("officialTotalInvestment", officialTotalInvestment(incomeProject));
        jsonObj.put("centreInvestment", incomeProject.getCentreInvestment());
        jsonObj.put("provinceInvestment", incomeProject.getProvinceInvestment());
        jsonObj.put("cityInvestment", incomeProject.getCityInvestment());
        jsonObj.put("countyInvestment", incomeProject.getCountyInvestment());
        jsonObj.put("townInvestment", incomeProject.getTownInvestment());
        jsonObj.put("foreignInvestment", incomeProject.getForeignInvestment());
        jsonObj.put("unitSelfRaise", incomeProject.getUnitSelfRaise());
        jsonObj.put("unitSelfOwner", incomeProject.getUnitSelfOwner());
        jsonObj.put("bankAdvance", incomeProject.getBankAdvance());
        jsonObj.put("otherAdvance", incomeProject.getOtherAdvance());
        jsonObj.put("otherSources", incomeProject.getOtherSources());
        jsonObj.put("eiaApprovalType", incomeProject.getEiaApprovalType());
        jsonObj.put("eiaApprovalDocNo", incomeProject.getEiaApprovalDocNo());
        jsonObj.put("eiaApprovalTime", DatetimeOpt.convertDateToString(incomeProject.getEiaApprovalTime(), "yyyy年MM月dd日"));
        jsonObj.put("eiaApprovalUnit", incomeProject.getEiaApprovalUnit());
    }
    
    private String officialTotalInvestment(IncomeProject incomeProject){
        String officialTotal = "";
        if(incomeProject.getCentreInvestment() != null && incomeProject.getCentreInvestment() > 0){
            officialTotal += "中央投资："+ incomeProject.getCentreInvestment()+",";
        }
        if(incomeProject.getProvinceInvestment() != null && incomeProject.getProvinceInvestment() > 0){
            officialTotal += "省级投资："+ incomeProject.getProvinceInvestment()+",";
        }
        if(incomeProject.getCityInvestment() != null && incomeProject.getCityInvestment() > 0){
            officialTotal += "市级投资："+ incomeProject.getCityInvestment()+",";
        }
        if(incomeProject.getCountyInvestment() != null && incomeProject.getCountyInvestment() > 0){
            officialTotal += "县级投资："+ incomeProject.getCountyInvestment()+",";
        }
        if(incomeProject.getTownInvestment() != null && incomeProject.getTownInvestment() > 0){
            officialTotal += "镇级投资："+ incomeProject.getTownInvestment()+",";
        }
        if(incomeProject.getForeignInvestment() != null && incomeProject.getForeignInvestment() > 0){
            officialTotal += "外资："+ incomeProject.getForeignInvestment()+",";
        }
        if(incomeProject.getUnitSelfRaise() != null && incomeProject.getUnitSelfRaise() > 0){
            officialTotal += "单位自筹："+ incomeProject.getUnitSelfRaise()+"(其中：";
            if(incomeProject.getUnitSelfOwner() != null && incomeProject.getUnitSelfOwner() > 0){
                officialTotal += "单位自有："+ incomeProject.getUnitSelfOwner()+" ";
            }      
            if(incomeProject.getBankAdvance() != null && incomeProject.getBankAdvance() > 0){
                officialTotal += "银行贷款："+ incomeProject.getBankAdvance()+" ";
            }
            if(incomeProject.getOtherAdvance() != null && incomeProject.getOtherAdvance() > 0){
                officialTotal += "其他贷款："+ incomeProject.getOtherAdvance()+" ";
            }
        }
        if(incomeProject.getOtherSources() != null && incomeProject.getOtherSources() > 0){
            officialTotal += "其他来源："+ incomeProject.getOtherSources()+",";
        }
        officialTotal = StringUtils.isBlank(officialTotal) ? "" : (officialTotal.substring(0, officialTotal.length() - 1) + "。");
        return officialTotal;
    }
    
    /**
     * 根据业务流水号获得发文信息JSON
     * @param djId
     * @return
     */
    private void getDispatchDocJSONByDjId(JSONObject jsonObj,DispatchDoc dispatchDoc){
       
            jsonObj.put("internalNo" ,dispatchDoc.getInternalNo());
            jsonObj.put("itemId" ,dispatchDoc.getItemId());
            jsonObj.put("dispatchDocNo" ,dispatchDoc.getDispatchDocNo());
            jsonObj.put("dispatchDocTitle" ,dispatchDoc.getDispatchDocTitle());
            jsonObj.put("dispatchFileType" ,CodeRepositoryUtil.getDataPiece("FW_FILE_TYPE",dispatchDoc.getDispatchFileType()) == null ? "" :CodeRepositoryUtil.getDataPiece("FW_FILE_TYPE", dispatchDoc.getDispatchFileType()).getDatavalue() );
            jsonObj.put("dispatchDocType" ,CodeRepositoryUtil.getDataPiece("FW_DOC_TYPE",dispatchDoc.getDispatchDocType()) == null ? "" :CodeRepositoryUtil.getDataPiece("FW_DOC_TYPE", dispatchDoc.getDispatchDocType()).getDatavalue() );
            jsonObj.put("dispatchCanOpen" ,CodeRepositoryUtil.getDataPiece("FW_CAN_OPEN",dispatchDoc.getDispatchCanOpen()) == null ? "" :CodeRepositoryUtil.getDataPiece("FW_CAN_OPEN",dispatchDoc.getDispatchCanOpen()).getDatavalue());
            jsonObj.put("dispatchOpenType" ,CodeRepositoryUtil.getDataPiece("FW_OPEN_TYPE",dispatchDoc.getDispatchOpenType()) == null ? "" :CodeRepositoryUtil.getDataPiece("FW_OPEN_TYPE", dispatchDoc.getDispatchOpenType()).getDatavalue());
            jsonObj.put("notOpenReason" ,dispatchDoc.getNotOpenReason());
            jsonObj.put("isUnionDispatch" ,CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",dispatchDoc.getIsUnionDispatch()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",dispatchDoc.getIsUnionDispatch()).getDatavalue());
            jsonObj.put("unionOthers" ,dispatchDoc.getUnionOthers());
            jsonObj.put("isCountersign" ,CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",dispatchDoc.getIsCountersign()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",dispatchDoc.getIsCountersign()).getDatavalue());
            jsonObj.put("dispatchDocSummary" ,dispatchDoc.getDispatchDocSummary());
            jsonObj.put("draftDate" ,dispatchDoc.getDraftDate());
            jsonObj.put("optUnitName" ,dispatchDoc.getOptUnitName());
            jsonObj.put("draftUserName" , dispatchDoc.getDraftUserName());
            jsonObj.put("secretsDegree" ,CodeRepositoryUtil.getDataPiece("FW_SECRETS_LEVEL",dispatchDoc.getSecretsDegree()) == null ? "" :CodeRepositoryUtil.getDataPiece("FW_SECRETS_LEVEL", dispatchDoc.getSecretsDegree()).getDatavalue());
            jsonObj.put("dispatchCopies" ,dispatchDoc.getDispatchCopies());
            //add by lay 2015-11-17 
            //签发人
            jsonObj.put("dispatchUser", CodeRepositoryUtil.getValue("usercode",  dispatchDoc.getDispatchUser()) == null ? "" :CodeRepositoryUtil.getValue("usercode", dispatchDoc.getDispatchUser()));
            //会签人
            List<OptIdeaInfo> hqList = optIdeaInfoDao.getObjectsByNodeCode(dispatchDoc.getDjId(),"FW_LDHQ");
            //升序查询的列表这里取最后一个,以下都是
            if(hqList != null && hqList.size() > 0){
                jsonObj.put("hqUserName", hqList.get(hqList.size()-1).getUsername());
            }
            
            //拟稿人
            List<OptIdeaInfo> ngList = optIdeaInfoDao.getObjectsByNodeCode(dispatchDoc.getDjId(),"N_nw");
            if(ngList != null && ngList.size() > 0){
                jsonObj.put("ngUserName", ngList.get(ngList.size()-1).getUsername());    
            }
            //处室负责审核人
            List<OptIdeaInfo> csshList = optIdeaInfoDao.getObjectsByNodeCode(dispatchDoc.getDjId(),"FW_CSSH");
            if(csshList != null && csshList.size()>0){
                jsonObj.put("csshUserName", csshList.get(csshList.size()-1).getUsername());
            }
            //部门会搞人
            List<OptIdeaInfo> bmhgList = optIdeaInfoDao.getObjectsByNodeCode(dispatchDoc.getDjId(),"hqxgcs");
            if(bmhgList != null && bmhgList.size() > 0){
                jsonObj.put("bmhgUserName", bmhgList.get(bmhgList.size()-1).getUsername());
            }
            //办公室秘书核搞
            List<OptIdeaInfo> bgsmshgList = optIdeaInfoDao.getObjectsByNodeCode(dispatchDoc.getDjId(),"zbcsfzrsh");
            if(bgsmshgList != null && bgsmshgList.size() > 0){
                jsonObj.put("bgsmshgUserName", bgsmshgList.get(bgsmshgList.size()-1).getUsername());
            }
            //办公室负责人复审
            List<OptIdeaInfo> bgsfsList = optIdeaInfoDao.getObjectsByNodeCode(dispatchDoc.getDjId(),"FW_BGSFZRFS");
            if(bgsfsList != null && bgsfsList.size() > 0){
                jsonObj.put("bgsfsUserName", bgsfsList.get(bgsfsList.size()-1).getUsername());
            }
           
            //主题词
            jsonObj.put("dispatchTitle", dispatchDoc.getDispatchTitle());
            //印数（维）
            jsonObj.put("dispatchcopiew", dispatchDoc.getDispatchcopiew());
            //附件
            List<OptStuffInfo> stuffList = optStuffInfoDao.getStuffInfoListByFileType(dispatchDoc.getDjId(),null);
            if(stuffList != null && stuffList.size() > 0){
                StringBuffer sb = new StringBuffer();
                for(OptStuffInfo stuffInfo : stuffList){
                    if(StringUtils.isNotBlank(stuffInfo.getGroupid())){
                        sb.append(stuffInfo.getFilename()).append(",");
                    }
                }
                if(sb.length()>0){
                    sb.substring(0, sb.length()-1);
                }
                jsonObj.put("stuffNames", sb.toString());
            }
            //end add
//            String mainNotifyUnit="";
//            String otherUnits="";
//            if(StringUtils.isNotBlank(dispatchDoc.getMainNotifyUnit())){
//                ApplyUnitInfo applyUnit = applyUnitInfoManager.getObjectById(dispatchDoc.getMainNotifyUnit());
//                if(applyUnit != null){
//                    mainNotifyUnit=applyUnit.getUnitname();
//                }
//            }
//            
//            if(StringUtils.isNotBlank(dispatchDoc.getOtherUnits())){
//                String [] ot=dispatchDoc.getOtherUnits().split(",");
//                for(String otherUnit:ot){
//                    ApplyUnitInfo applyUnit = applyUnitInfoManager.getObjectById(otherUnit);
//                    if(applyUnit != null){
//                        otherUnits+=applyUnit.getUnitname()+",";
//                    }
//                }
//                if(otherUnits != null && otherUnits != "" && otherUnits.length() > 1)
//                    otherUnits=otherUnits.substring(0, otherUnits.length()-1);
//            }
            jsonObj.put("mainNotifyUnit" , dispatchDoc.getMainNotifyUnit());
            jsonObj.put("otherUnits" , dispatchDoc.getOtherUnits());
            jsonObj.put("retentionPeriod" , dispatchDoc.getRetentionPeriod());
            jsonObj.put("checkUserName" ,dispatchDoc.getCheckUserName());
            jsonObj.put("dispatchDocFileName" ,dispatchDoc.getDispatchDocFileName());
            
            jsonObj.put("createDate" , DatetimeOpt.convertDateToString(dispatchDoc.getCreateDate(), "yyyy年MM月dd日"));
            jsonObj.put("dispatchDocRed" ,dispatchDoc.getDispatchDocRed());
            jsonObj.put("printDate" , DatetimeOpt.convertDateToString(dispatchDoc.getPrintDate(), "yyyy年MM月dd日"));
            jsonObj.put("criticalLevel" ,CodeRepositoryUtil.getDataPiece("critical_level",dispatchDoc.getCriticalLevel()) == null ? "" :CodeRepositoryUtil.getDataPiece("critical_level", dispatchDoc.getCriticalLevel()).getDatavalue());
    }

    @Override
    public void saveOaLeaderSort(Oaleadersort oaleadersort) {
        if (!org.springframework.util.StringUtils.hasText(oaleadersort.getLid())) {
            oaleadersort.setLid(DateUtil.getUUID());
        }
        oaLeaderSortDao.save(oaleadersort);
    }

    @Override
    public List<Oaleadersort> listOaLeaderSortByNode(Long nodeInstId) {
        String hql="from Oaleadersort where nodeInstId=? order by leaderorder";
        return oaLeaderSortDao.listObjects(hql,new Object[]{nodeInstId});
    }

    @Override
    public List<Oaleadersort> listOaLeaderSortByUserCode(String userCode) {
        String hql="from Oaleadersort where usercode=? order by leaderorder";
        return oaLeaderSortDao.listObjects(hql,new Object[]{userCode});
    }

    public void setTemplateFileDao(TemplateFileDao templateFileDao) {
        this.templateFileDao = templateFileDao;
    }

    public void setOptStuffInfoDao(OptStuffInfoDao optStuffInfoDao) {
        this.optStuffInfoDao = optStuffInfoDao;
    }

    public void setOptFlowNoInfoManager(OptFlowNoInfoManager optFlowNoInfoManager) {
        this.optFlowNoInfoManager = optFlowNoInfoManager;
    }

    public void setApplyUnitInfoManager(ApplyUnitInfoManager applyUnitInfoManager) {
        this.applyUnitInfoManager = applyUnitInfoManager;
    }

    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }

    public OaLeaderSortDao getOaLeaderSortDao() {
        return oaLeaderSortDao;
    }

    public void setOaLeaderSortDao(OaLeaderSortDao oaLeaderSortDao) {
        this.oaLeaderSortDao = oaLeaderSortDao;
    }
}

