package com.centit.webservice.client.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.api.ApiException;
import com.api.SmsApiClient;
import com.centit.oa.dao.OaSmssendDao;
import com.centit.oa.po.OaSMSConfig;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.service.OaSMSConfigManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.util.DateUtil;
import com.centit.webservice.util.CommonOptCodeUtil;

/***
 * 短信发送接口
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2016年5月9日
 * @version
 */
public class SendMsgYDClientManagerImpl{
    
    private OaSmssendDao oaSmssendDao;
    private SmsApiClient smsApiClient ;
    private OaSMSConfigManager oaSMSConfigManager;
    


    
    private boolean isLogin = false;

    private static Logger log=Logger.getLogger(SendMsgYDClientManagerImpl.class);
   /**
    * 短信发送开关
    * @return
    */
    public boolean isSendMsg(){
        boolean  sendMsgFlage=false;
        OaSMSConfig  oaSMSConfig  =oaSMSConfigManager.getActiveSMSConfig();//获取短信参数
        String isopen=null!=oaSMSConfig && "T".equals(oaSMSConfig.getOpenOrClose())?"T":"F";
        if(null!=isopen && "T".equals(isopen)){
            sendMsgFlage=true;
        }
        return sendMsgFlage;
    } 
    /**
     * 初始化网关参数
     * @return
     * @throws UnknownHostException 
     */
    private  void initParameters () throws UnknownHostException{
        InetAddress addr = InetAddress.getLocalHost();
        String  ip=addr.getHostAddress().toString();//获得本机IP
        String  address=addr.getHostName().toString();//获得本机名称
        log.info("短信发送开始  ip:"+ip  +"本机名称"  +address+" 我是内存地址 "+smsApiClient.hashCode());
        
        OaSMSConfig  oaSMSConfig  =oaSMSConfigManager.getActiveSMSConfig();//获取短信参数
        if (null == oaSMSConfig ||null != oaSMSConfig
                && !CommonOptCodeUtil.SMSCONFIG_OPERATORSOURCE_DX
                        .equals(oaSMSConfig.getOperatorSource())) {
            log.error("请核对短信发送参数是否正确！");
        } else {
            
        if (!SmsApiClient.conn) {
        
            try {
                String ipAddress = oaSMSConfig.getGateway();
                int port = oaSMSConfig.getPort().intValue();
                String username = oaSMSConfig.getUsername();
                String password = oaSMSConfig.getPassword();
                
                if (SmsApiClient.conn) {
                    System.out.println("已建立链接，不需要再建立链接！返回");
                }
                
                /*int result =SmsApiClient.initConnect(ipAddress, port, "apiId",
                        username, password);// 初始化参数  apiId暂时不清楚为何物
              
                if(result==0){
                    SmsApiClient.conn = true;
                    System.out.println("短信登录认证成功:初始化链接成功");
                }else{
                    System.out.println("短信登录认证成功初始化链接失败，抛出异常信息");
                }*/
                
            } catch (Exception e) {
                log.error("请核对短信发送参数是否正确：对应字典项为sendMsgInfo！");
            } 
         }
        }
    }
    

    
    /**
     * synchronized 避免重复发送短信
     * 发送短消息 对应字典项：
     *                sendMSg  是否启用短信功能
     *                sendMsgResState 
     *                sendMsgState
     *                sendMsgInfo -- "202.102.41.101", 9005, "025C00004375", "jh84558870"
     * 
     * 
     */
    public synchronized void sendMsg() throws UnknownHostException {
        if(isSendMsg()){
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("NP_sendstate", true);// is null and 非0
            filterMap.put("allowdatetime", DateUtil.AddDays(DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"),-1));//允许发送一天误差范围内的短信，避免存在太多历史信息的情况
            List<OaSmssend> msgList = oaSmssendDao.listObjects(filterMap);
            try{
            // 待发送短信列表
           if (null != msgList && msgList.size() > 0) {
                    this.initParameters();
                    if(SmsApiClient.conn){
                        
                        for (OaSmssend msg : msgList) {
                            String sendBack = "";
                            sendBack = smsApiClient.sendSingleMsg( msg.getAcceptnum(), msg.getContent());
                           
                            msg.setSequenceId(sendBack);
                            msg.setState("0");//发送成功
                            oaSmssendDao.saveObject(msg);
                            
                        }    
                        
                    }else{
                        smsApiClient.releaseConnect();
                    }
                } 
            } catch (Exception e) {
                    log.error("短信发送异常"+e.getMessage());
            }
            log.info("短信发送结束");
           }
        }

    //需要自己主动查询回执
   
    /**
     * 断开连接
     */
    public void closeConnect() {
        try {
            smsApiClient.releaseConnect();
        } catch (Exception e) {
            log.error("短信断开连接异常"+e.getMessage());
        }
        
    }
  
    public OaSmssendDao getOaSmssendDao() {
        return oaSmssendDao;
    }

    public void setOaSmssendDao(OaSmssendDao oaSmssendDao) {
        this.oaSmssendDao = oaSmssendDao;
    }
    public OaSMSConfigManager getOaSMSConfigManager() {
        return oaSMSConfigManager;
    }
    public void setOaSMSConfigManager(OaSMSConfigManager oaSMSConfigManager) {
        this.oaSMSConfigManager = oaSMSConfigManager;
    }
    public SmsApiClient getSmsApiClient() {
        return smsApiClient;
    }
    public void setSmsApiClient(SmsApiClient smsApiClient) {
        this.smsApiClient = smsApiClient;
    }


}
