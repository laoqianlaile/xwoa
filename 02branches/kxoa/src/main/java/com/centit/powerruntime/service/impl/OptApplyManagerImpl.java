package com.centit.powerruntime.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.powerruntime.dao.OptApplyDao;
import com.centit.powerruntime.dao.OptBaseInfoDao;
import com.centit.powerruntime.dao.OptStuffInfoDao;
import com.centit.powerruntime.dao.VOptApplyInfoDao;
import com.centit.powerruntime.po.OptApplyInfo;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.po.VOptApplyInfo;
import com.centit.powerruntime.service.OptApplyManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;

public class OptApplyManagerImpl extends
        BaseEntityManagerImpl<OptApplyInfo> implements OptApplyManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptApplyManagerImpl.class);

    private OptApplyDao optApplyDao;
//    private VSrPermitApplyDao vsrPermitApplyDao;
//    private VSrApplyUserDao vSrApplyUserDao;
    private OptBaseInfoDao optBaseInfoDao;
    private VOptApplyInfoDao vOptApplyInfoDao;
    private  OptStuffInfoDao optStuffInfoDao;
    

//    public void setvSrApplyUserDao(VSrApplyUserDao vSrApplyUserDao) {
//        this.vSrApplyUserDao = vSrApplyUserDao;
//    }

    public void setOptStuffInfoDao(OptStuffInfoDao optStuffInfoDao) {
        this.optStuffInfoDao = optStuffInfoDao;
    }

    public VOptApplyInfoDao getvOptApplyInfoDao() {
        return vOptApplyInfoDao;
    }

    public void setvOptApplyInfoDao(VOptApplyInfoDao vOptApplyInfoDao) {
        this.vOptApplyInfoDao = vOptApplyInfoDao;
    }

    public void setOptBaseInfoDao(OptBaseInfoDao optBaseInfoDao) {
        this.optBaseInfoDao = optBaseInfoDao;
    }

   /* public void setVsrPermitApplyDao(VSrPermitApplyDao vsrPermitApplyDao) {
        this.vsrPermitApplyDao = vsrPermitApplyDao;
    }*/

    public String generateNextDjId() {
        return optApplyDao.genNextDjID();
    }


    /**
     * 查询保存但未提交的许可业务数据
     */
    public List<VOptApplyInfo> listOptApplyInfo(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return vOptApplyInfoDao.listOptApplyInfo(filterMap, pageDesc);
    }

/*    @Override
    public String getJSONProposerNames() {
        List<VSrApplyUser> userList = vSrApplyUserDao.listObjects();
        JSONArray jsonArr = new JSONArray();
        for (VSrApplyUser userInfo : userList) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("proposerName", userInfo.getProposerName());
            jsonArr.add(jsonObj);
        }
        return jsonArr.toString();
    }
    
    public String getApplyInfoJsonByDjId(String djId){
        VSrPermitApply srPermitApply =  vsrPermitApplyDao.getObjectById(djId);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("channelName", srPermitApply.getChannelName());
        jsonObj.put("channelLevel", srPermitApply.getChannelLevel());
        jsonObj.put("elevationSystem", srPermitApply.getElevationSystem());
        jsonObj.put("applyDate", srPermitApply.getApplyDate());
        jsonObj.put("proposerName", srPermitApply.getProposerName());
        jsonObj.put("proposerUnitcode", srPermitApply.getProposerUnitcode());
        jsonObj.put("transAffairNo", srPermitApply.getTransAffairNo());
        jsonObj.put("transaffairname", srPermitApply.getTransaffairname());
        jsonObj.put("orgcode", srPermitApply.getOrgcode());
        jsonObj.put("orgname", srPermitApply.getOrgname());
        jsonObj.put("powerid", srPermitApply.getPowerid());
        jsonObj.put("powername", srPermitApply.getPowername());
        return jsonObj.toString();
    }
*/
    @Override
    public String getJSONDocumentNames(String dj_id) {
        
        OptApplyInfo info=optApplyDao.getObjectById(dj_id);
       
        JSONObject jsonObj = new JSONObject();        
        if(info!=null){
            //获取业务数据
            OptBaseInfo  optBaseInfo=optBaseInfoDao.getObjectById(dj_id);
            //获取许可登记时候的上传附件文件
            List<OptStuffInfo> optStuffInfos=optStuffInfoDao.getStuffInfoListByNodeinstid(dj_id, "0");
           String stuff_names="";
            if(optStuffInfos.size()>0){
                for(OptStuffInfo osi:optStuffInfos){
                stuff_names +=osi.getFilename()+"\n";
                }
            }

            //组装公共部分
            String transaffairname="";
            String caseNo="";
            if(optBaseInfo!=null){
                transaffairname=optBaseInfo.getTransaffairname(); 
                caseNo=optBaseInfo.getCaseNo();
            }
            /*else{
                optBaseInfo=new OptBaseInfo();
            }*/
            
            jsonObj.put("transaffairname",transaffairname);//办件名称
            jsonObj.put("proposer_paper_code",info.getProposerPaperCode());//申请者证件号码
            jsonObj.put("proposerName", info.getProposerName());//申请者
            jsonObj.put("proposerAddr", info.getProposerAddr());//申请者地址
            jsonObj.put("legal_person", info.getLegal_person());//法定代表人/负责人
            jsonObj.put("proposerPhone", StringUtils.isBlank(info.getProposerPhone())?info.getProposerMobile():info.getProposerPhone());//电话
            jsonObj.put("proposerZipcode", info.getProposerZipcode());//邮政编码
            
            jsonObj.put("agentName", info.getAgentName());//代理人姓名
            jsonObj.put("agentPhone", StringUtils.isBlank(info.getAgentPhone())?info.getAgentMobile():info.getAgentPhone());//代理人电话
            jsonObj.put("agentZipcode", info.getAgentZipcode());//代理人邮政编码
            
            jsonObj.put("stuff_names", stuff_names);//附送资料
            jsonObj.put("applyReason", info.getApplyReason());//申请理由
            jsonObj.put("applyMemo", info.getApplyMemo());//备注           
            
                       
            //组装文书使用到年月日的字段
            String currDate=DatetimeOpt.convertDateToString(info.getApplyDate(),"");
            jsonObj.put("applyDate",currDate);//申请日期
            jsonObj.put("applyYear",currDate.substring(0, 4));//申请日期_年
            jsonObj.put("applyMonth",currDate.substring(5, 7));//申请日期_月
            jsonObj.put("applyDay",currDate.substring(8, 10));//申请日期_日
            
            // 组装 关于XXX的许可申请：航道名称+办件名称
            jsonObj.put("cname_tname",transaffairname);
            // 案号
            jsonObj.put("caseNo",caseNo);
            
        }else{
            jsonObj.put("","");
        }
        return jsonObj.toString();
    }
    public void setOptApplyDao(OptApplyDao optApplyDao) {
        this.optApplyDao = optApplyDao;
        setBaseDao(this.optApplyDao);
    }

    @Override
    public String getNextDjId(String ev) {
        // TODO Auto-generated method stub
         return ev + optApplyDao.getNextKeyBySequence("S_OPT_APPLY", 14);
    }
    
    //支持业务删除办件使用
    @Override
    public void deleteObjectBanInfo(String djId) {
        // TODO Auto-generated method stub
        optApplyDao.deleteObjectBanInfo(djId);
    }

    @Override
    public String getNextDjId(String ev, String moduleType) {
        // TODO Auto-generated method stub
        if(StringUtils.isBlank(moduleType)){
            return this.getNextDjId(ev);
        }else{
            return ev +CodeRepositoryUtil.getValue("BIZTYPE", moduleType)+optApplyDao.getNextKeyBySequence("S_"+moduleType, 12); 
        }
    }

    @Override
    public List<VuserTaskListOA> getuserbysql(Long curNodeInstId) {
        // TODO Auto-generated method stub
        return optApplyDao.getuserbysql(curNodeInstId);
    }

}
