package com.centit.dispatchdoc.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.centit.core.dao.CodeBook;
import com.centit.core.utils.LabelValueBean;
import com.centit.dispatchdoc.po.DispatchDoc;
import com.centit.dispatchdoc.po.OptZwh;
import com.centit.dispatchdoc.po.OptZwhReserved;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.IODocArchiveNoManager;
import com.centit.dispatchdoc.service.OptZwhReservedManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.OptFlowNoInfo;
import com.centit.sys.po.OptFlowNoInfoId;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.OptFlowNoInfoManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.workflow.FlowNodeInfo;
import com.centit.workflow.NodeInstance;

public class IODocArchiveNoAction extends IODocCommonBizAction<OptZwh> {
    private static final long serialVersionUID = 1L;
    private IODocArchiveNoManager ioDocArchiveNoManager;
//    private OptBaseInfoManager optBaseInfoManager;
    private OptFlowNoInfoManager optFlowNoInfoManager;
    private OptProcInfoManager optProcInfoManager;
    private SysUserManager sysUserManager;
    private OptZwhReservedManager optZwhReservedManager;
    private DispatchDocManager dispatchDocManager;

    private JSONObject json;
    private List<OptZwhReserved> zwhReservedList;
    private List<FDatadictionary> dictionaryList;
    private List<LabelValueBean> yearOptions;
    private String flowPhase;
    private String nodeCode;
    
    private OaSmssendManager oaSmssendManager;
    
    /**
     * 获取新的流水号和文号
     * @param containsReserved
     * @throws ParseException
     */
    private boolean setOptFwh(boolean isNewFwh, boolean containsReserved) throws ParseException {
        OptZwh optZwh = ioDocArchiveNoManager.getObjectById(object.getDjId());
        
        if (null != optZwh && !isNewFwh) { // 复制已有的赋文号信息
            ioDocArchiveNoManager.copyObject(object, optZwh);
        } else {
            String lshYear = object.getLshYear();
            if (StringUtils.isBlank(lshYear)) { // 设置当前年份为默认
                lshYear = String.valueOf(DatetimeOpt.getYear(DatetimeOpt.currentUtilDate()));
                object.setLshYear(lshYear);
            }
            
            Date codeDate = DatetimeOpt.createUtilDate(Integer.parseInt(lshYear), 1, 1); // 当前文号所对应的日期
            String newLsh = object.getLsh();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("wjlx", object.getWjlx());
            paramMap.put("lshYear", lshYear);
            
            if (containsReserved) {
                paramMap.put("lsh", Long.valueOf(object.getLsh()));
                paramMap.put("djIdCanNull", object.getDjId());
//                String hql = "From OptZwhReserved where lsh=" + object.getLsh();
//                hql += " and (djId is null or djId='" + object.getDjId() + "')";
                List<OptZwhReserved> list = optZwhReservedManager.listObjects(paramMap);
                paramMap.remove("lsh");
                paramMap.remove("djIdCanNull");
                
                if (list.isEmpty()) {
                    return false;
                }
            } else {
                String lsh = object.getLsh();
                if (StringUtils.isBlank(lsh)) { // 流水号为空，则获取最新的流水号（仅从OptFLowInfo表中）
                    lsh = optFlowNoInfoManager.genLsH("WJLX", object.getWjlx(), codeDate, null);
                }
                
                paramMap.put("largerLsh", lsh);
                
                List<OptZwh> zwhList = ioDocArchiveNoManager.listObjects(paramMap); // 查找已赋文号中大于等于现流水号中的记录
                List<OptZwhReserved> zwhReservedList = optZwhReservedManager.listObjects(paramMap); // 查找预留文号中大于等于现流水号中的记录
                
                if (null != optZwh) {
                    for (int i=zwhList.size()-1; i>=0; i--) {
                        if (optZwh.getDjId().equals(zwhList.get(i).getDjId())) {
                            zwhList.remove(i);
                        }
                    }
                    
                    for (int i=zwhReservedList.size()-1; i>=0; i--) {
                        if (optZwh.getDjId().equals(zwhReservedList.get(i).getDjId())) {
                            zwhReservedList.remove(i);
                        }
                    }
                }
                
                Set<Long> nLshSet = new HashSet<Long>(); // 所有已用以及预留占用的流水号
                
                for (int i=zwhList.size()-1; i>=0; i--) { // 已赋文号
                    nLshSet.add(Long.parseLong(zwhList.get(i).getLsh())); // 添加已使用的流水号（数值型）
                }
                
                for (int i=zwhReservedList.size()-1; i>=0; i--) {
                    nLshSet.add(zwhReservedList.get(i).getLsh()); // 添加已预留的流水号（数值型）
                }
                
                long nLsh = Long.parseLong(lsh); // 先取当前流水号
                while (true) {
                    if (nLshSet.contains(nLsh)) { // 如果该流水号已经存在或者被预留，则将当前流水号继续+1
                        nLsh++;
                    } else {
                        break;
                    }
                }
                
                newLsh = String.valueOf(nLsh);
                
                if (Long.parseLong(lsh) != nLsh) { // 如果不一致，则说明当前文号已被使用或预留，则将curNo调整至当前有效的lsh-1
                    String codeCode = CodeRepositoryUtil.getDataPiece("WJLX", object.getWjlx()).getDatadesc();
                    optFlowNoInfoManager.recordNextLshBaseYear("xtzwhgz", codeCode, codeDate, nLsh-1);
                }
            }
            
            object.setLsh(newLsh);
            object.setFwh(optFlowNoInfoManager.genFWH("WJLX", object.getWjlx(), codeDate, object.getOrgCode(), newLsh));
        }
        
        return true;
    }
    
    private UserbizoptLogManager userbizoptLogManager;
    
    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }
    /**
     * 进入流程赋文号操作
     */
    public String edit() {
        try {
            this.setOptFwh(false, false);
            this.setYearOptions(0);
            
            moduleParam = generalModuleParamManager.getObjectById(moduleCode);
            
            
          //保存查看日志
            FUserDetail user = (FUserDetail) getLoginUser();
            DispatchDoc o=dispatchDocManager.getObjectById(object.getDjId());
            UserbizoptLog u=new UserbizoptLog(new UserbizoptLogId(o.getDispatchDocTitle(),o.getDjId()),Long.parseLong(request.getParameter("nodeInstId")));
            userbizoptLogManager.saveUserbizoptLog(u, user);
            
            return EDIT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
    /**
     * 保存置文号信息
     * @return
     */
    public String saveIODocArchiveNoInfo() {
        json = new JSONObject();
        try {
            //String fwh = new String(object.getFwh().getBytes("ISO-8859-1"), "GBK");
            //采用post方式传值不需要转码
            String fwh=object.getFwh();
            object.setFwh(fwh);
            
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("wjlx", object.getWjlx());
            paramMap.put("lshYear", object.getLshYear());
            paramMap.put("lsh", object.getLsh());
            
            List<OptZwh> zwhList = ioDocArchiveNoManager.listObjects(paramMap);
            
            if (zwhList.isEmpty() || (1 == zwhList.size() && object.getDjId().equals(zwhList.get(0).getDjId()))) { // 该文号规则未被其他使用
                OptZwh optZwh = ioDocArchiveNoManager.getObjectById(object.getDjId());
                
                if (null != optZwh) {
                    ioDocArchiveNoManager.copyObjectNotNullProperty(optZwh, object);
                    object = optZwh;
                }
                ioDocArchiveNoManager.saveObject(object); // 保存置文号表
                
                //保存置文号 OPT_BASE_INFO表
                OptBaseInfo baseInfo = optBaseInfoManager.getObjectById(object.getDjId());
                baseInfo.setSendArchiveNo(object.getFwh());
                optBaseInfoManager.saveObject(baseInfo);
                //保存置文号M_DISPATCH_DOC表
                DispatchDoc dispatchDoc = dispatchDocManager.getObjectById(object.getDjId());
                dispatchDoc.setDispatchDocNo(object.getFwh());
                dispatchDocManager.saveObject(dispatchDoc);
                // 更新文号规则表留痕
                Date now = DatetimeOpt.currentUtilDate();
                
                paramMap.put("lsh", Long.parseLong(object.getLsh()));
                List<OptZwhReserved> zwhReservedList = optZwhReservedManager.listObjects(paramMap);
                
                paramMap.clear();
                paramMap.put("djId", object.getDjId());
                List<OptZwhReserved> usedZwhReservedList = optZwhReservedManager.listObjects(paramMap);
                
                for (int i=0; i<usedZwhReservedList.size(); i++) { // 将原先已关联djId的预留记录清空
                    OptZwhReserved zwhReserved = usedZwhReservedList.get(i);
                    zwhReserved.setDjId(null);
                    zwhReserved.setFwh(null);
                    zwhReserved.setIdea(null);
                    zwhReserved.setUseDate(null);
                    
                    optZwhReservedManager.saveObject(zwhReserved);
                }
                
                if (!zwhReservedList.isEmpty()) { // 如果当前文号是预留文号，则更新信息（并关联djId）
                    OptZwhReserved zwhReserved = zwhReservedList.get(0);
                    zwhReserved.setDjId(object.getDjId());
                    zwhReserved.setWjlx(object.getWjlx());
                    zwhReserved.setLshYear(object.getLshYear());
                    zwhReserved.setLsh(Long.parseLong(object.getLsh()));
                    zwhReserved.setFwh(object.getFwh());
                    zwhReserved.setUseDate(now);
                    
                    optZwhReservedManager.saveObject(zwhReserved);
                } else { // 如果不是预留文号，则可以更新F_OPTFLOWNO
                    String dataDesc = CodeRepositoryUtil.getDataPiece("WJLX", object.getWjlx()).getDatadesc();
                    Date codeDate = DatetimeOpt.createUtilDate(Integer.parseInt(object.getLshYear()), 1, 1);
                    OptFlowNoInfoId flowNoId = new OptFlowNoInfoId("xtzwhgz", codeDate, dataDesc);
                    OptFlowNoInfo flowNo = optFlowNoInfoManager.getObjectById(flowNoId);
                    if(flowNo==null){
                        flowNo=new OptFlowNoInfo();
                        flowNo.setCid(new OptFlowNoInfoId("xtzwhgz", codeDate, dataDesc));
                        flowNo.setCurNo(0L);
                    }
                    Long curNo = Long.valueOf(flowNo.getCurNo()); // 库中的记录
                    Long curLsh = Long.valueOf(object.getLsh()); // 当前流水号
                    
                    if (curLsh > curNo) { // 如果当前流水号大于库中的记录，则做更新
                        flowNo.setCurNo(curLsh);
                        flowNo.setLastCodeDate(now);
                        optFlowNoInfoManager.saveObject(flowNo);
                    }
                }
                
                json.put("status", "success");
            } else { // 该文号规则已经被使用
                json.put("status", "unavailable"); // 使用的预留号已经被使用或删除，不可用
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e);
            json.put("status", "failed");
        }
        
        return "ajaxResult";
    }
    
    //获得办理意见
    private String transcontent;
    
    public String getTranscontent() {
        return transcontent;
    }
    public void setTranscontent(String transcontent) {
        this.transcontent = transcontent;
    }
    
    /**
     * 保存过程日志信息
     */
    public void saveIdeaInfo() {
        FUserDetail loginInfo = (FUserDetail) getLoginUser();
        
        Long nodeInstId = Long.parseLong(request.getParameter("nodeInstId"));
        NodeInstance nodeInst = flowEngine.getNodeInstById(nodeInstId);
        FlowNodeInfo nodeInfo = flowEngine.getNodeInfoById(nodeInst.getNodeId());
        
        OptProcInfo optProcInfo = new OptProcInfo();
        optProcInfo.setTransdate(new Date(System.currentTimeMillis()));
        optProcInfo.setUsercode(loginInfo.getUsercode());
        optProcInfo.setUnitcode(loginInfo.getPrimaryUnit());
        optProcInfo.setNodename(nodeInfo.getNodeName());
        optProcInfo.setNodeinststate(nodeInst.getNodeState());
        optProcInfo.setIdeacode(object.getIdeacode());
        // 存在风险点的信息:风险类别、风险描述、风险内控手段与结果
        optProcInfo.setIsrisk(StringUtils.isBlank(optProcInfo.getRisktype()) ? "F" : "T");
        optProcInfo.setNodeInstId(nodeInstId);
        optProcInfo.setFlowPhase(flowPhase);
        optProcInfo.setNodeCode(nodeCode);
        //获得办理意见
        optProcInfo.setTranscontent(transcontent);
        optProcInfo.setDjId(request.getParameter("djId"));
        optProcInfoManager.saveObject(optProcInfo);// 保存办件人员
        
        /** 获得用户所在部门 start */
        VUserUnits fuerunit = new VUserUnits();
        fuerunit.setUnitCode(loginInfo.getPrimaryUnit());
        fuerunit.setUserCode(loginInfo.getUsercode());
        fuerunit = sysUserManager.getUnitByUserCode(fuerunit);
        /** 获得用户所在部门 end */
        
        OptIdeaInfo optIdeaInfo = new OptIdeaInfo();
        optIdeaInfo.setUsername(loginInfo.getUsername());
        optIdeaInfo.setUnitname(fuerunit.getUnitName());
        optIdeaInfo.setFlowPhase(flowPhase);
        optIdeaInfo.setNodeCode(nodeCode);
        optIdeaInfo.setModuleCode(moduleCode);//办理意见与通用模块关联（控制环节意见显示）

        optProcInfoManager.saveIdeaInfo(optIdeaInfo, optProcInfo);
    }
    
    
    /**
     * 提交操作结果
     * @return
     */
    public String submitOpt() {
        try {
           
            //saveIODocArchiveNoInfo();
           // submitNode();
           Set<Long> nextNode = null;
            nextNode = submitNode();          
            
            FUserDetail user = (FUserDetail)getLoginUser();
            if(null != user){
                // 发送短信
                oaSmssendManager.saveFlowMsgs(request.getParameter("isSendMessage"), user.getUsercode(), nextNode);   
                oaSmssendManager.executeSendMsg();
            }
            
            saveIdeaInfo(); // 保存过程日志信息
            this.initReturn();
            
            return "refreshTasks";
        } catch (Exception e) {
            log.error(e, e.getCause());
            request.setAttribute("error", "当前操作提交失败，详见系统日志。");
            return ERROR;
        }
    }

    //跳转流程代办列表
    private String flowCode;
    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    private void initReturn() {
        OptBaseInfo optBaseInfo = optBaseInfoManager.getOptBaseByFlowId(super.getFlowInstId());

        if (object.getDjId().indexOf("FW") != -1) {
            flowCode=optBaseInfo.getFlowCode();
        }else if (object.getDjId().indexOf("SW") != -1) {
            flowCode=optBaseInfo.getFlowCode();
        }
        
    }


    /**
     * 设置文号流水号规则所存在的年份下拉列表（从最早年份至明年）
     * @return 文件类型组成的 in 内容
     */
    private String setExistYear() {
        dictionaryList = CodeRepositoryUtil.getDictionary("WJLX");
        List<String> codeList = new ArrayList<String>();
        String codeCodeRange = "";
        for (int i=0; i<dictionaryList.size(); i++) {
            if (!codeList.contains(dictionaryList.get(i).getDatadesc())) {
                codeCodeRange += ",'" + dictionaryList.get(i).getDatadesc() + "'";
                codeList.add(dictionaryList.get(i).getDatadesc());
            }
        }
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ownerCode", "xtzwhgz");
        paramMap.put(CodeBook.SELF_ORDER_BY, " cid.codeDate asc"); // 排序
        
        String hql = "From OptFlowNoInfo where cid.codeCode in (" + codeCodeRange.substring(1) + ")";
        List<OptFlowNoInfo> flowNoList = optFlowNoInfoManager.listObjects(hql, paramMap);
        
        int minYear = 0;
        if (flowNoList.isEmpty()) {
            minYear = DatetimeOpt.getYear(DatetimeOpt.currentUtilDate()); // 如果没有，则从当年开始
        } else {
            minYear = DatetimeOpt.getYear(flowNoList.get(0).getCid().getCodeDate());
        }
        
        this.setYearOptions(minYear); // 获取文号的最低历史记录年份并且设置下拉选项
        
        return codeCodeRange.substring(1);
    }
    
    
    /**
     * 即将使用的文号查看页面（页面不执行分页，可以选择年份）
     * @return
     */
    public String showZwhReserved() {
        try {
            String lshYear = object.getLshYear();
            if (StringUtils.isBlank(lshYear)) { // 设置当前年份为默认
                lshYear = String.valueOf(DatetimeOpt.getYear(DatetimeOpt.currentUtilDate()));
                object.setLshYear(lshYear);
            }
            
            this.setExistYear(); // 设置年份下拉框选项
            
            Date codeBaseDate = DatetimeOpt.createUtilDate(Integer.parseInt(lshYear), 1, 1);
            
            for (int i=0; i<dictionaryList.size(); i++) {
                long nextLsh = optFlowNoInfoManager.viewNextLshBaseYear("xtzwhgz", dictionaryList.get(i).getDatadesc(), codeBaseDate);
                dictionaryList.get(i).setExtracode2(String.valueOf(nextLsh)); // 记录即将开始的文号
            }
            
            totalRows = dictionaryList.size();
            
            return "zwhReservedShow";
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
    }
    
    
    /**
     * 设置预留文号的弹出页面
     * @return
     */
    public String setZwhReserved() {
        try {
            FDatadictionary dictionary = CodeRepositoryUtil.getDataPiece("WJLX", object.getWjlx());
            Date codeBaseDate = DatetimeOpt.createUtilDate(Integer.parseInt(object.getLshYear()), 1, 1);
            object.setLsh(String.valueOf(optFlowNoInfoManager.viewNextLshBaseYear("xtzwhgz", dictionary.getDatadesc(), codeBaseDate)));
            
            return "zwhReservedSetPop";
        } catch (Exception e) {
            log.error(e, e.getCause());
            return ERROR;
        }
    }
    
    
    /**
     * 保存设置的预留文号（可支持多个保存）
     * @return
     */
    public String saveZwhReserved() {
        json = new JSONObject();
        try {
            long reservedNo = Long.valueOf(request.getParameter("reservedNo"));
            
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("wjlx", object.getWjlx());
            paramMap.put("lshYear", object.getLshYear());
            paramMap.put("lsh", reservedNo);
            
            List<OptZwh> zwhList = ioDocArchiveNoManager.listObjects(paramMap);
            
            String res = "";
            if (zwhList.isEmpty()) {
                String reservedReason = new String(request.getParameter("reservedReason").getBytes("ISO-8859-1"), "GBK");
                FUserDetail fuser = ((FUserDetail) getLoginUser());
                res = optZwhReservedManager.saveOptZwhReserveds(object.getWjlx(), object.getLshYear(), reservedNo, fuser.getUsercode(), reservedReason);
            } else {
                res = "unavailable";
            }
            
            String codeCode = CodeRepositoryUtil.getDataPiece("WJLX", object.getWjlx()).getDatadesc();
            Date codeBaseDate = DatetimeOpt.createUtilDate(Integer.valueOf(object.getLshYear()), 1, 1);
            
            if ("success".equals(res) || "unavailable".equals(res)) {
                optFlowNoInfoManager.recordNextLshBaseYear("xtzwhgz", codeCode, codeBaseDate, reservedNo);
            }
            
            json.put("status", res);
        } catch (Exception e) {
            log.info(e);
            json.put("status", "failed");
        }
        
        return "ajaxResult";
    }
    
    
    /**
     * ajax方式删除预留文号
     * @return
     */
    public String deleteZwhReserved() {
        json = new JSONObject();
        try {
            Long reservedId = Long.parseLong(request.getParameter("reservedId"));
            
            OptZwhReserved zwhReserved = optZwhReservedManager.getObjectById(reservedId);
            
            if (StringUtils.isBlank(zwhReserved.getDjId())) {
                optZwhReservedManager.deleteObject(zwhReserved);
                
                zwhReserved = optZwhReservedManager.getObjectById(reservedId);
                json.put("status", null == zwhReserved ? "success" : "failed");
            } else {
                json.put("status", "unavailable");
            }
        } catch (Exception e) {
            log.info(e);
            json.put("status", "failed");
        }
        
        return "ajaxResult";
    }
    
    
    /**
     * 流程赋文号操作流水号使用预留号
     * @return
     */
    public String useZwhReserved() {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("wjlx", object.getWjlx());
            paramMap.put("lshYear", object.getLshYear());
            paramMap.put(CodeBook.SELF_ORDER_BY, " lsh asc");
            
            String hql = "From OptZwhReserved where djId is null";
            
            pageDesc = makePageDesc();
            zwhReservedList = optZwhReservedManager.listObjects(hql, paramMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            
            return "zwhReservedUsePop";
        } catch (Exception e) {
            log.error(e, e.getCause());
            return ERROR;
        }
    }
    
    
    /**
     * 预留文号查看
     * @return
     */
    public String viewZwhReserved() {
        try {
            String lshYear = object.getLshYear();
            if (StringUtils.isBlank(lshYear)) { // 设置当前年份为默认
                lshYear = String.valueOf(DatetimeOpt.getYear(DatetimeOpt.currentUtilDate()));
                object.setLshYear(lshYear);
            }
            
            String hql = "From OptZwhReserved where 1=1";
            
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("lshYear", lshYear);
            paramMap.put(CodeBook.SELF_ORDER_BY, " createDate desc, lsh desc"); // 排序
            
            if (StringUtils.isNotBlank(object.getWjlx())) { // 参数中已经存在文件类型，则年份也已经固定，不可选择
                paramMap.put("wjlx", object.getWjlx());
            } else {
                String codeCodeRange = this.setExistYear(); // 设置年份下拉框选项
                
                dictionaryList = CodeRepositoryUtil.getDictionary("WJLX");
                for (int i=0; i<dictionaryList.size(); i++) {
                    String dataDesc = dictionaryList.get(i).getDatadesc();
                    String dataCode = dictionaryList.get(i).getDatacode();
                    codeCodeRange = codeCodeRange.replace("'" + dataDesc + "'", "'" + dataCode + "'");
                }
                
                hql += " and wjlx in (" + codeCodeRange + ")";
            }
            
            pageDesc = makePageDesc();
            zwhReservedList = optZwhReservedManager.listObjects(hql, paramMap, pageDesc);
            
            totalRows = pageDesc.getTotalRows();
            
            return "zwhReservedView";
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
    }
    
    
    /**
     * 页面文件类型、年份、流水号改变后获取新的文号
     * @return
     */
    public String afterChange() {
        json = new JSONObject();
        try {
            long nLsh = StringUtils.isBlank(object.getLsh()) ? 0 : Long.parseLong(object.getLsh());
            
            boolean res = this.setOptFwh(true, StringUtils.isNotBlank(request.getParameter("usedReserved")));
            
            if (res) {
                long nNewLsh = Long.parseLong(object.getLsh());
                
                if (nLsh != nNewLsh) {
                    json.put("lsh", nNewLsh);
                }
                json.put("fwh", object.getFwh());
                json.put("status", "success");
            } else {
                json.put("status", "unavailable"); // 使用的预留号已经被使用或删除，不可用
            }
        } catch (Exception e) {
            log.info(e);
            e.printStackTrace();
            json.put("status", "failed");
        }
        
        return "ajaxResult";
    }
    
    
    /**
     * 获取页面年份的下拉选项
     * @param year 为0时，跳转赋文号页面，下拉为前后10年；为具体年份时，跳转设置预留文号页面，下拉为最早赋文号年至明年
     */
    private void setYearOptions(int year) {
        int startYear = year, endYear = 0;
        int curYear = DatetimeOpt.getYear(DatetimeOpt.currentUtilDate());
        if (0 == year) {
            startYear = curYear - 5;
            endYear = curYear + 5;
        } else {
            endYear = curYear + 2;
        }
        
        yearOptions = new ArrayList<LabelValueBean>();
        for (int i=startYear; i<endYear; i++) {
            yearOptions.add(new LabelValueBean(String.valueOf(i), String.valueOf(i)));
        }
    }


    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public void setOptFlowNoInfoManager(
            OptFlowNoInfoManager optFlowNoInfoManager) {
        this.optFlowNoInfoManager = optFlowNoInfoManager;
    }

    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }
    
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }
    
    public void setIoDocArchiveNoManager(IODocArchiveNoManager basemgr) {
        ioDocArchiveNoManager = basemgr;
        this.setBaseEntityManager(ioDocArchiveNoManager);
    }

    public void setDispatchDocManager(DispatchDocManager dispatchDocManager) {
        this.dispatchDocManager = dispatchDocManager;
    }


    /*public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }*/

    public void setOptZwhReservedManager(OptZwhReservedManager optZwhReservedManager) {
        this.optZwhReservedManager = optZwhReservedManager;
    }

    public List<LabelValueBean> getYearOptions() {
        return yearOptions;
    }

    public void setYearOptions(List<LabelValueBean> yearOptions) {
        this.yearOptions = yearOptions;
    }

    public List<OptZwhReserved> getZwhReservedList() {
        return zwhReservedList;
    }

    public void setZwhReservedList(List<OptZwhReserved> zwhReservedList) {
        this.zwhReservedList = zwhReservedList;
    }
    
    public List<FDatadictionary> getDictionaryList() {
        return dictionaryList;
    }
    
    public void setDictionaryList(List<FDatadictionary> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    public String getFlowPhase() {
        return flowPhase;
    }

    public void setFlowPhase(String flowPhase) {
        this.flowPhase = flowPhase;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
    
    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }
    
    
}