package com.centit.webservice.client.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.centit.oa.dao.OaSmssendDao;
import com.centit.oa.po.OaSmssend;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateUtil;
import com.linkage.netmsg.NetMsgclient;

/***
 * 短信发送接口
 * TODO Class description should be added
 * 
 * @author lq
 * @create 2016年5月9日
 * @version
 */
public class SendMsgClientManagerImpl {
    
    private OaSmssendDao oaSmssendDao;
    private SendMsgReceive sendMsgReceive;
    private NetMsgclient client ;
    public NetMsgclient getClient() {
        return client;
    }
    public void setClient(NetMsgclient client) {
        this.client = client;
    }

    private boolean isLogin = false;

    private static Logger log=Logger.getLogger(SendMsgClientManagerImpl.class);
   /**
    * 短信发送开关
    * @return
    */
    public boolean isSendMsg(){
        boolean  sendMsgFlage=false;
        String isopen=CodeRepositoryUtil.getValue("sendMSg", "isopen");
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
      
        if (null == client.socket ||!client.socket.isConnected()&&!client.isStoped) {
        
        String ipAddress = null;
        int port = 0;
        String username = null;
        String password = null;
            try {
                ipAddress = CodeRepositoryUtil.getValue("sendMsgInfo",
                        "ipAddress");
                port = Integer.valueOf(CodeRepositoryUtil.getValue(
                        "sendMsgInfo", "port"));
                username = CodeRepositoryUtil.getValue("sendMsgInfo",
                        "username");
                password = CodeRepositoryUtil.getValue("sendMsgInfo",
                        "password");

                client = client.initParameters(ipAddress, port, username,
                        password, sendMsgReceive);// 初始化参数
                isLogin = client.anthenMsg(client);// 登录认证
                if(isLogin){
                    log.info("短信登录认证成功发送开始");
                }
                
            } catch (Exception e) {
                log.error("请核对短信发送参数是否正确：对应字典项为sendMsgInfo！");
            } 
        }
        return client;
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
                    }
                } 
            } catch (Exception e) {
                    log.error("短信发送异常"+e.getMessage());
                    client.closeConn();
            }
            log.info("短信发送结束");
           }
        }

   
    public SendMsgReceive getSendMsgReceive() {
        return sendMsgReceive;
    }


    public void setSendMsgReceive(SendMsgReceive sendMsgReceive) {
        this.sendMsgReceive = sendMsgReceive;
    }


    public OaSmssendDao getOaSmssendDao() {
        return oaSmssendDao;
    }

    public void setOaSmssendDao(OaSmssendDao oaSmssendDao) {
        this.oaSmssendDao = oaSmssendDao;
    }

}
