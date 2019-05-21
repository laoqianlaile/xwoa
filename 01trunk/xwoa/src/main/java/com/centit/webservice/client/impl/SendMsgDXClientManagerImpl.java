package com.centit.webservice.client.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import antlr.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centit.oa.dao.OaSmssendDao;
import com.centit.oa.po.OaSMSConfig;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.service.OaSMSConfigManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateUtil;
import com.centit.webservice.pojo.UserDTO;
import com.centit.webservice.util.CommonOptCodeUtil;
import com.centit.webservice.util.PostMsg;
import com.ctc.wstx.util.StringUtil;
import com.linkage.netmsg.NetMsgclient;

/***
 * 短信发送接口
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2016年5月9日
 * @version
 */
public class SendMsgDXClientManagerImpl {
    
    private OaSmssendDao oaSmssendDao;
    private SendMsgDXReceive sendMsgReceive;
    private NetMsgclient client ;
    private OaSMSConfigManager oaSMSConfigManager;
    String basePath = "http://192.16.199.9:81";//太极提供最新同步地址
    public NetMsgclient getClient() {
        return client;
    }
    public void setClient(NetMsgclient client) {
        this.client = client;
    }
    
    private boolean isLogin = false;

    private static Logger log=Logger.getLogger(SendMsgDXClientManagerImpl.class);
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
    private  NetMsgclient initParameters () throws UnknownHostException{
        InetAddress addr = InetAddress.getLocalHost();
        String  ip=addr.getHostAddress().toString();//获得本机IP
        String  address=addr.getHostName().toString();//获得本机名称
        log.info("短信发送开始  ip:"+ip  +"本机名称"  +address+" 我是内存地址 "+client.hashCode());
        
        OaSMSConfig  oaSMSConfig  =oaSMSConfigManager.getActiveSMSConfig();//获取短信参数
        
        if (null == oaSMSConfig ||null != oaSMSConfig
                && !CommonOptCodeUtil.SMSCONFIG_OPERATORSOURCE_DX
                        .equals(oaSMSConfig.getOperatorSource())) {
            log.error("请核对短信发送参数是否正确！");
        } else {
            if (null == client.socket || !client.socket.isConnected()
                    && !client.isStoped) {
              
                try {
                    String ipAddress = oaSMSConfig.getGateway();
                    int port = oaSMSConfig.getPort().intValue();
                    String username = oaSMSConfig.getUsername();
                    String password = oaSMSConfig.getPassword();

                    client = client.initParameters(ipAddress, port, username,
                            password, sendMsgReceive);// 初始化参数
                    isLogin = client.anthenMsg(client);// 登录认证
                    if (isLogin) {
                        log.info("短信登录认证成功发送开始");
                    }

                } catch (Exception e) {
                    log.error("请核对短信发送参数是否正确：对应短信参数配置主键为："
                            + oaSMSConfig.getSmsid() + "！");
                }
            }
        }
        return client;
    }
    
    /**专为发送太极短信
     * synchronized 避免重复发送短信
     * 发送短消息 对应字典项：
     *                sendMSg  是否启用短信功能
     *                sendMsgResState 
     *                sendMsgState
     *                sendMsgInfo -- "202.102.41.101", 9005, "025C00004375", "jh84558870"
     * 
     * 
     */
    public synchronized void sendMsgTj() throws UnknownHostException {
        if(isSendMsg()){
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("NP_sendstate", true);// is null and 非0
            filterMap.put("allowdatetime", DateUtil.AddDays(DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"),-1));//允许发送一天误差范围内的短信，避免存在太多历史信息的情况
            List<OaSmssend> msgList = oaSmssendDao.listObjects(filterMap);
            try{
            // 待发送短信列表
           if (null != msgList && msgList.size() > 0) {
                        for (OaSmssend msg : msgList) {
                            if(!org.apache.commons.lang.StringUtils.isBlank(msg.getAcceptnum())){
                                JSONObject jsonObj=new JSONObject();
                                jsonObj.put("key","3a53a53c9c52793a578b24014cad8532");
                                jsonObj.put("phone",msg.getAcceptnum());
                                jsonObj.put("message",msg.getContent());
                                String sendBack= PostMsg.post(basePath+"/byServiceId/a8dc1e32-ea92-471b-a8d0-8672b9c809c8?sys_code=320781010037&key=3a53a53c9c52793a578b24014cad8532",jsonObj.toString(),"402880e65d1fd594015d20aae2fa0006");//最新获取路径/user/getAll/ff8080815df49bf3015e12f6bc020002为appid参数值
                              //上面应该会返回值给我们，确定是否发送成功
                                msg.setSequenceId(sendBack);
                                msg.setRestoremessage("0");
                                msg.setState("0");//发送成功
                                oaSmssendDao.saveObject(msg);
                            }
                             }     
                } 
            } catch (Exception e) {
                    log.error("短信发送异常"+e.getMessage());
                    client.closeConn();
            }
            log.info("短信发送结束");
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
                    client=this.initParameters();
                    if(isLogin){
                        for (OaSmssend msg : msgList) {
                            String sendBack = "";
                           
                            sendBack = client.sendMsg(client, 0, msg.getAcceptnum(),
                                    msg.getContent(), 1); // 发送下行短信
                           
                            if ("16".equals(sendBack)) { // 如果连接出现异常，sendMsg方法会返回16
                                log.info("短信连接异常！");
                                client.closeConn();
                                Thread.sleep(10000L);//确保链接完全断开
                                break;
                            }
                            msg.setSequenceId(sendBack);
                            msg.setState("0");//发送成功
                            oaSmssendDao.saveObject(msg);
//                            client.closeConn();
                            
                        }     
                    }else{
                        log.info("短信未登陆");
                    }
                } 
            } catch (Exception e) {
                    log.error("短信发送异常"+e.getMessage());
                    client.closeConn();
            }
            log.info("短信发送结束");
           }
        }

   
    public void closeConnect() {
        if(!client.isStoped){
            client.closeConn();
        }
    }


    public SendMsgDXReceive getSendMsgReceive() {
        return sendMsgReceive;
    }
    public void setSendMsgReceive(SendMsgDXReceive sendMsgReceive) {
        this.sendMsgReceive = sendMsgReceive;
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
    public String getBasePath() {
        return basePath;
    }
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

}
