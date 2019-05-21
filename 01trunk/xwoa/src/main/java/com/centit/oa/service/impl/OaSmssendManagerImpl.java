package com.centit.oa.service.impl;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaSmssendDao;
import com.centit.oa.po.OaSMSConfig;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.service.OaSMSConfigManager;
import com.centit.oa.service.OaSmssendLogManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.webservice.client.impl.SendMsgDXClientManagerImpl;
import com.centit.webservice.client.impl.SendMsgYDClientManagerImpl;
import com.centit.webservice.util.CommonOptCodeUtil;
import com.centit.workflow.sample.dao.WfActionTaskDao;
import com.centit.workflow.sample.po.VUserTaskList;

public class OaSmssendManagerImpl extends BaseEntityManagerImpl<OaSmssend>
	implements OaSmssendManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaSmssendManager.class);

	private OaSmssendDao oaSmssendDao ;
	public void setOaSmssendDao(OaSmssendDao baseDao)
	{
		this.oaSmssendDao = baseDao;
		setBaseDao(this.oaSmssendDao);
	}
	
	private OaUserinfoManager oaUserinfoManager;
	
    public void setOaUserinfoManager(OaUserinfoManager oaUserinfoManager) {
        this.oaUserinfoManager = oaUserinfoManager;
    }

    private TaskExecutor taskExecutor;
    private SendMsgDXClientManagerImpl sendMsgDXClientManager;
    
    private SendMsgYDClientManagerImpl sendMsgYDClientManager;
    
    
    private OaSMSConfigManager oaSMSConfigManager;
    
    private OaSmssendLogManager oaSmssendLogManager;
    
    
    private static String mobileMatcher="^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";//号码匹配验证:根据正则表达式验证电话号码
    
    @Override
    public String getNextValueOfSequence(String string) {
        // TODO Auto-generated method stub
        return oaSmssendDao.getNextValueOfSequence("S_SMS_ID");
    }
    
    private WfActionTaskDao actionTaskDao;
    
    public void setActionTaskDao(WfActionTaskDao actionTaskDao) {
        this.actionTaskDao = actionTaskDao;
    }

    /**
     * 保存短信内容
     * @param acceptpeocode --接收人用户代码
     * @param sendercode -- 发送人用户代码
     * @param content -- 短信内容
     * @param datasource -- 短信来源
     */
    public void saveMsgs(String acceptpeocode, String sendercode, String content, String datasource){
        
        OaSmssend oaSmssend = new OaSmssend();
        oaSmssend.setSmsid(Long.parseLong((this.getNextValueOfSequence("S_SMS_ID"))));
        // 发送人信息封装
        FUserinfo sendUserinfo = CodeRepositoryUtil.getUserInfoByCode(sendercode);
        OaUserinfo oaUserinfoSend = oaUserinfoManager.getObjectById(sendercode);
        oaSmssend.setSendpeo(sendUserinfo.getUsername());
        oaSmssend.setSendpeocode(sendercode);
        oaSmssend.setSendnum(oaUserinfoSend.getMobilephone());

        // 接收人信息封装
        FUserinfo acceptUserinfo = CodeRepositoryUtil.getUserInfoByCode(acceptpeocode);
        OaUserinfo oaUserinfoAccept = oaUserinfoManager.getObjectById(acceptpeocode);
        if (oaUserinfoAccept != null) {
            oaSmssend.setAcceptpeo(acceptUserinfo.getUsername());
            oaSmssend.setAcceptpeocode(acceptpeocode);
            oaSmssend.setDatasource(datasource);// 接收人类型 0 内网 1 外网
            oaSmssend.setState("-1");// 默认待发送状态
            oaSmssend.setRestoremessage("-1");
            oaSmssend.setContent(content);
            oaSmssend.setSendtime(DatetimeOpt.currentUtilDate());
            
            String receivePhoneNum = oaUserinfoAccept.getMobilephone();
            oaSmssend.setAcceptnum(receivePhoneNum);

            if(StringUtils.isBlank(receivePhoneNum)){
                oaSmssend.setState("E"); // 将短信状态设置为错误
                oaSmssend.setRestoremessage("E");
            }else{

                if(!receivePhoneNum.matches(mobileMatcher)){
                    oaSmssend.setState("E"); // 将短信状态设置为错误
                    oaSmssend.setRestoremessage("E");
                }
            }
            // 发送短信内容到短信接口表
            this.saveObject(oaSmssend);  
            
            if("E".equals(oaSmssend.getState())){
                // 保存短信发送记录
                oaSmssendLogManager.saveSMSLogs(oaSmssend);
                return;
            }
        }
        
    }
    
    /**
     * 验证接收人手机号码是不是空，是不是有误
     * @param oaSmssend
     */
    public void validatePhoneNum(OaSmssend oaSmssend){
        
        if(null != oaSmssend){
            
            String receivePhoneNum = oaSmssend.getAcceptnum();
            if(StringUtils.isBlank(receivePhoneNum)){
                oaSmssend.setState("E"); // 将短信状态设置为错误
            }else{
                
                if(!receivePhoneNum.matches(mobileMatcher)){
                    oaSmssend.setState("E"); // 将短信状态设置为错误
                }
            }
        }
    }
    /**
     * 执行发送短信接口
     * 根据系统短信配置决定运营商
     */
    public void executeSendMsg(){
        taskExecutor.execute(new Runnable(){
            @Override
            public void run() {
                try {
                    OaSMSConfig  oaSMSConfig=  oaSMSConfigManager.getActiveSMSConfig();
                    if(null!=oaSMSConfig && CommonOptCodeUtil.SMSCONFIG_OPERATORSOURCE_YD.equals(oaSMSConfig.getOperatorSource())){
                        sendMsgYDClientManager.sendMsg();
                    }
                    if(null!=oaSMSConfig && CommonOptCodeUtil.SMSCONFIG_OPERATORSOURCE_DX.equals(oaSMSConfig.getOperatorSource())){
                        sendMsgDXClientManager.sendMsg();
                    }
                    
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 执行太极发送短信接口
     */
    public void executeSendMsgTj(){
        taskExecutor.execute(new Runnable(){
            @Override
            public void run() {
                try {
                       sendMsgDXClientManager.sendMsgTj();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 判断当前用户当月已发送的短信是否已到达上限，默认每个月200条
     * @param usercode
     * @return
     */
    public boolean canSendMsg(String usercode){
        
        Long numPermitted = 200l;     // 每个月发送短信的上限
        
        OaSMSConfig  oaSMSConfig=  oaSMSConfigManager.getActiveSMSConfig();
        
       
        numPermitted =oaSMSConfig.getLimitNumber();
       
        
        Long sentMsgs = oaSmssendDao.getSentMsgs(usercode);  // 当月已发送的短信数
        
        if(sentMsgs < numPermitted){
            return true;
        }else{
            return false;
        }
        
    }
    
    
    /**
     * 流程中生成短信
     * @param isSendMessage —— 是否发送短信：T 是
     * @param senderCode —— 发送人用户代码
     * @param nextNode —— 下一步操作节点
     */
    public void saveFlowMsgs(String isSendMessage, String senderCode, Set<Long> nextNode) {

        if ("T".equals(isSendMessage) && nextNode != null && !nextNode.isEmpty()) {
            OaSMSConfig  oaSMSConfig=  oaSMSConfigManager.getActiveSMSConfig();
            // 循环下一步操作节点相关的人员，生成对应的短信数据
            for (Long nodeActive : nextNode) {
                List<VUserTaskList> tasks = actionTaskDao
                        .getTasksByNodeInstId(nodeActive);
                for(VUserTaskList task : tasks){
                    
                    String djId = task.getFlowOptTag();
                    String bjType = null;   // 短信来源
                    if(StringUtils.isNotBlank(djId)){
                        bjType = djId.substring(0,2);                        
                    }
                    
                    //具体字典项
                    String dic=null==oaSMSConfig.getSendMSgMod()?"sendMSgMod":oaSMSConfig.getSendMSgMod();
                    // 通用内容
                    String comContent = CodeRepositoryUtil.getValue(dic, "com"); 
                    // 短信内容
                    String content = CodeRepositoryUtil.getValue(dic, bjType);
                    
                    if(null != bjType && bjType.equals(content)){
                        content = comContent;
                    }
                    /*comContent = StringUtils.replace(comContent, "{username}", CodeRepositoryUtil.getValue("usercode", task.getUserCode()));*/
                    comContent = StringUtils.replace(comContent, "{event}", task.getFlowOptName());
                   //时间
                    comContent = StringUtils.replace(comContent, "{datetime}",DatetimeOpt.convertDateToString(new Date(),"yyyy年MM月dd日hh点mm分"));
                   //发短信人
                    comContent = StringUtils.replace(comContent, "{userName}", CodeRepositoryUtil.getValue("usercode", senderCode));
                    this.saveMsgs(task.getUserCode(), senderCode, comContent, bjType);
                }
            }
            
        }
    }
    
    /**
     * 修改短信配置时
     * 断开连接
     */
    @Override
    public void closeConnect() {
        sendMsgYDClientManager.closeConnect();
        sendMsgDXClientManager.closeConnect();
    }
    
    
    
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

   
    
    public SendMsgDXClientManagerImpl getSendMsgDXClientManager() {
        return sendMsgDXClientManager;
    }

    public void setSendMsgDXClientManager(
            SendMsgDXClientManagerImpl sendMsgDXClientManager) {
        this.sendMsgDXClientManager = sendMsgDXClientManager;
    }

    public SendMsgYDClientManagerImpl getSendMsgYDClientManager() {
        return sendMsgYDClientManager;
    }

    public void setSendMsgYDClientManager(
            SendMsgYDClientManagerImpl sendMsgYDClientManager) {
        this.sendMsgYDClientManager = sendMsgYDClientManager;
    }

    public void setOaSmssendLogManager(OaSmssendLogManager oaSmssendLogManager) {
        this.oaSmssendLogManager = oaSmssendLogManager;
    }

    public void setOaSMSConfigManager(OaSMSConfigManager oaSMSConfigManager) {
        this.oaSMSConfigManager = oaSMSConfigManager;
    }


}

