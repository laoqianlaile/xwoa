package com.centit.oa.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OptFileTransferSend;
import com.centit.oa.po.OptFilingCabinets;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.oa.service.OptFileTransferSendManager;
import com.centit.oa.service.OptFilingCabinetsManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;

public class OptFileTransferSendAction extends BaseEntityExtremeAction<OptFileTransferSend>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptFileTransferSendManager optFileTransferSendMgr;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private OptFilingCabinetsManager optFilingCabinetsMgr;
    private OaSmssendManager oaSmssendManager;
    
    private File []stuff;
    private String []stuffFileName;
    
    private String  show_type;//页面打开方式
    
    private String isSendMsgView;//是否发送短信标识
    
    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }
    
    public String getIsSendMsgView() {
        return isSendMsgView;
    }

    public void setIsSendMsgView(String isSendMsgView) {
        this.isSendMsgView = isSendMsgView;
    }

    public OptFileTransferSendManager getOptFileTransferSendMgr() {
        return optFileTransferSendMgr;
    }

    public void setOptFileTransferSendMgr(
            OptFileTransferSendManager optFileTransferSendMgr) {
        this.optFileTransferSendMgr = optFileTransferSendMgr;
        super.setBaseEntityManager(optFileTransferSendMgr);
    }
   
    
    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    

    public OptFilingCabinetsManager getOptFilingCabinetsMgr() {
        return optFilingCabinetsMgr;
    }

    public void setOptFilingCabinetsMgr(
            OptFilingCabinetsManager optFilingCabinetsMgr) {
        this.optFilingCabinetsMgr = optFilingCabinetsMgr;
    }

    public File[] getStuff() {
        return stuff;
    }

    public void setStuff(File[] stuff) {
        this.stuff = stuff;
    }

    public String[] getStuffFileName() {
        return stuffFileName;
    }

    public void setStuffFileName(String[] stuffFileName) {
        this.stuffFileName = stuffFileName;
    }
    
    /**
     * 上报系统外
     * @return
     */
    public String listReportToSysout(){
        return "listReportToSysout";
    }
    
    /**
     * 上报系统内
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listReportToSysin(){
        FUserDetail userDetail = (FUserDetail) getLoginUser();
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if(!("sa".equals(userDetail.getLoginname()) || "admin".equals(userDetail.getLoginname()))){
            filterMap.put("senderCode" , userDetail.getUsercode());
        }
        filterMap.put("sendType" , 1);
        filterMap.put("scopeType", "SYSIN");
        PageDesc pageDesc =makePageDesc();
       
        this.pageDesc = pageDesc;
        objList = optFileTransferSendMgr.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return "listReportToSysin";
    }
    
    /**
     * 下发
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listIssued(){
        FUserDetail userDetail = (FUserDetail) getLoginUser();
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if(!("sa".equals(userDetail.getLoginname()) || "admin".equals(userDetail.getLoginname()))){
            filterMap.put("senderCode" , userDetail.getUsercode());
        }
        filterMap.put("sendType" , 2);
        filterMap.put("scopeType", "SYSIN");
        
        PageDesc pageDesc =makePageDesc();
       
        this.pageDesc = pageDesc;
        objList = optFileTransferSendMgr.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return "listIssued";
    }
    
    @Override
    public String built() {
        if("SYSIN".equals(object.getScopeType())){
            initUsers(object.getSendType());
        }
        return BUILT;
    }
    
    @Override
    public String view() {
        String id = request.getParameter("id");
        object = optFileTransferSendMgr.getObjectById(id);
        object.setSenderName(CodeRepositoryUtil.getValue("usercode", object.getSenderCode()));
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("refId", id);
        List<OptFilingCabinets> fileList = optFilingCabinetsMgr.listObjects(filterMap);
        
        request.setAttribute("fileList", fileList);
        return VIEW;
    }
    
    @Override
    public String save() {
        try{
            FUserDetail userDetail = (FUserDetail) getLoginUser();
            object.setSenderCode(userDetail.getUsercode());
            object.setSenderName(userDetail.getUsername());
            object.setCreateTime(new Date());
            
            optFileTransferSendMgr.saveObject(object, stuff, stuffFileName);
            //========================发送短信================================
            if(StringUtils.isNotBlank(isSendMsgView)){
                //发送短信
                String receiverCodes=object.getReceiverCode();
                if(StringUtils.isNotBlank(receiverCodes)){
                    //短信内容
                    String comContent = CodeRepositoryUtil.getValue("sendMSgMod", "fileSend"); //
                    //收短信人
                    String [] ReceiverCode=receiverCodes.split(",");
                    for(String receiverCode :ReceiverCode){
                        //收短信人
                        comContent = StringUtils.replace(comContent, "{username}", CodeRepositoryUtil.getValue("usercode", receiverCode));
                        comContent = StringUtils.replace(comContent, "{event}", object.getSubject());
                        //时间
                        comContent = StringUtils.replace(comContent, "{datetime}",DatetimeOpt.convertDateToString(new Date(),"yyyy年MM月dd日hh点mm分"));
                        //发短信人
                        comContent = StringUtils.replace(comContent, "{userName}", CodeRepositoryUtil.getValue("usercode", userDetail.getUsercode()));
                        oaSmssendManager.saveMsgs(receiverCode, userDetail.getUsercode(), comContent, "R");
                        oaSmssendManager.executeSendMsg();
                    }
                }
            }
            //========================================================================
            
            if("SYSOUT".equals(object.getScopeType())){
                return listReportToSysout();
            }
            if("SYSIN".equals(object.getScopeType())){
                if(1==object.getSendType()){
                    return listReportToSysin();
                }
                if(2==object.getSendType()){
                    return listIssued();
                }
            }
            return null;
        } catch (Exception e) {
           log.error(e.getMessage(), e);
           saveError(e.getMessage());
           return ERROR;
       }
    }
    /*
     * 获取人员1-上报 2-下发
     */
    public void initUsers(int type) {
        JSONArray ja = new JSONArray();
        if(type == 1){
            List<Map<String, String>> userList = oaPowerrolergroupManager.setUnitUser();
            ja.addAll(userList);
        }
        if(type == 2){
            //获取中间层的人员
            List<Map<String, String>> userList = oaPowerrolergroupManager.setSubUnitsUserByUnitCode("002041"); 
            ja.addAll(userList);
        }
        request.setAttribute("userjson", ja);
    }

    
    /**
     * 上报系统内_首页展示信息
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listReportToSysin_dashboard(){
        listReportToSysin();

        return "listReportToSysin_dashboard";
    }

    
    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }
    
}
