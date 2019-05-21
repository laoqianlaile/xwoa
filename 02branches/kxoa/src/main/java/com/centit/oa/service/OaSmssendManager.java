package com.centit.oa.service;

import java.util.Set;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaSmssend;

public interface OaSmssendManager extends BaseEntityManager<OaSmssend> 
{

    /**
     * 获取主键策略
     * @param string
     * @return
     */
    public String getNextValueOfSequence(String string);
    
    /**
     * 保存短信内容
     * @param acceptpeocode --接收人用户代码
     * @param sendercode -- 发送人用户代码
     * @param content -- 短信内容
     */
    public void saveMsgs(String acceptpeocode, String sendercode, String content, String datasource);
    
    /**
     * 验证接收人手机号码是不是空，是不是有误
     * @param oaSmssend
     */
    public void validatePhoneNum(OaSmssend oaSmssend);
    
    /**
     * 执行发送短信接口
     */
    public void executeSendMsg();
    
    /**
     * 判断当前用户当月已发送的短信是否已到达上限，默认每个月200条
     * @param usercode
     * @return
     */
    public boolean canSendMsg(String usercode);
    
    /**
     * 流程中生成短信
     * @param isSendMessage —— 是否发送短信：T 是
     * @param senderCode —— 发送人用户代码
     * @param nextNode —— 下一步操作节点
     */
    public void saveFlowMsgs(String isSendMessage, String senderCode, Set<Long> nextNode);

}
