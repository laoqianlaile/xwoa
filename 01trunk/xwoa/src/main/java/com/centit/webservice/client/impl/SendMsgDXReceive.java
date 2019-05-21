package com.centit.webservice.client.impl;

import java.util.List;

import com.centit.oa.dao.OaSmssendDao;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.service.OaSmssendLogManager;
import com.centit.support.utils.DatetimeOpt;
import com.linkage.netmsg.server.AnswerBean;
import com.linkage.netmsg.server.ReceiveMsg;
import com.linkage.netmsg.server.ReturnMsgBean;
import com.linkage.netmsg.server.UpMsgBean;
/**
 * 
 * TODO Class description should be added
 * 回应是服务端产生的处理结果，回执是短信网关产生的发送结果。客户端收到了回应并显示成功，表示服务端已经接收到了消息并处理完成，已经发送给了短信网关。客户端收到了回执，表示短信网关已经将短信发送出去，并返回了发送结果
 * @author lq
 * @create 2016年5月6日
 * @version
 */
    public class SendMsgDXReceive extends ReceiveMsg {
       
        private OaSmssendDao oaSmssendDao ;
        private OaSmssendLogManager oaSmssendLogManager;
        
       /**
        * 企信通API会将下行短信返回的序列ID、状态及msgId送往此函数，用户使用时，需要实现该函数，自行处理获取的上行短信
        */
        public void getAnswer(AnswerBean answerBean) {
                super.getAnswer(answerBean);
                List<OaSmssend> msgList =oaSmssendDao.getMsgBySeqId(answerBean.getSeqId());
                if(null!=msgList&&msgList.size()>0){
                    for(OaSmssend msg: msgList){
                        msg.setMsgId(answerBean.getMsgId());
                        msg.setState(String.valueOf(answerBean.getStatus()));
                        oaSmssendDao.save(msg); 
                        oaSmssendLogManager.saveSMSLogs(msg);
                    }
                   
                }
              
        }
        /**
         * 企信通API会将回执送往此函数，用户使用时，需要实现该函数，自行处理获取的短信回执
         */
        public void getReturnMsg(ReturnMsgBean returnMsgBean) {
            super.getReturnMsg(returnMsgBean);
            List<OaSmssend> msgList =oaSmssendDao.getMsgByMsgId(returnMsgBean.getMsgId());
            if(null!=msgList&&msgList.size()>0){
                for(OaSmssend msg: msgList){
                    msg.setSequenceId(null);//下行短信返回的序列ID
                    msg.setRestoremessage(String.valueOf(returnMsgBean.getMsgErrStatus()));
                    msg.setSucceedTime(DatetimeOpt.currentUtilDate()); // 发送成功时间
                    oaSmssendDao.save(msg); 
                }
            }
        }

            public void getUpMsg(UpMsgBean upMsgBean) {
                super.getUpMsg(upMsgBean);

                String sequenceId = upMsgBean.getSequenceId();

                String sendNum = upMsgBean.getSendNum();

                String receiveNum = upMsgBean.getReceiveNum();

                String msgRecTime = upMsgBean.getMsgRecTime();

                String msgContent = upMsgBean.getMsgContent();

                System.out.println("UpMsgBean sequenceId: " + sequenceId);
                System.out.println("UpMsgBean sendNum: " + sendNum);
                System.out.println("UpMsgBean receiveNum: " + receiveNum);
                System.out.println("UpMsgBean msgRecTime: " + msgRecTime);
                try {
                    System.out.println("file.encoding:"
                            + System.getProperty("file.encoding"));
                    System.out.println("string from GBK to UTF-8 byte:  "
                            + new String(gbk2utf8(msgRecTime), "UTF-8"));
                    System.out.println("UpMsgBean msgContent ISO->UTF: "
                            + new String(msgContent.getBytes("ISO8859_1"), "UTF-8"));
                    System.out.println("UpMsgBean msgContent ISO->GBK: "
                            + new String(msgContent.getBytes("ISO8859_1"), "GBK"));
                    System.out.println("UpMsgBean msgContent GBK->ISO: "
                            + new String(msgContent.getBytes("GBK"), "ISO8859_1"));
                    System.out.println("UpMsgBean msgContent GBK->UTF: "
                            + new String(msgContent.getBytes("GBK"), "UTF-8"));
                    System.out.println("UpMsgBean msgContent GBK->GBK: "
                            + new String(msgContent.getBytes("GBK"), "GBK"));
                    System.out.println("UpMsgBean msgContent UTF->ISO: "
                            + new String(msgContent.getBytes("UTF-8"), "ISO8859_1"));
                    System.out.println("UpMsgBean msgContent UTF->GBK: "
                            + new String(msgContent.getBytes("UTF-8"), "GBK"));
                    System.out.println("UpMsgBean msgContent UTF->UTF: "
                            + new String(msgContent.getBytes("UTF-8"), "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public byte[] gbk2utf8(String chenese) {
                char[] c = chenese.toCharArray();
                byte[] fullByte = new byte[3 * c.length];
                for (int i = 0; i < c.length; ++i) {
                    int m = c[i];
                    String word = Integer.toBinaryString(m);

                    StringBuffer sb = new StringBuffer();
                    int len = 16 - word.length();

                    for (int j = 0; j < len; ++j) {
                        sb.append("0");
                    }
                    sb.append(word);
                    sb.insert(0, "1110");
                    sb.insert(8, "10");
                    sb.insert(16, "10");

                    String s1 = sb.substring(0, 8);
                    String s2 = sb.substring(8, 16);
                    String s3 = sb.substring(16);

                    byte b0 = Integer.valueOf(s1, 2).byteValue();
                    byte b1 = Integer.valueOf(s2, 2).byteValue();
                    byte b2 = Integer.valueOf(s3, 2).byteValue();
                    byte[] bf = new byte[3];
                    bf[0] = b0;
                    fullByte[(i * 3)] = bf[0];
                    bf[1] = b1;
                    fullByte[(i * 3 + 1)] = bf[1];
                    bf[2] = b2;
                    fullByte[(i * 3 + 2)] = bf[2];
                }

                return fullByte;
            }

            public byte[] utf82gbk(String chenese) {
                char[] c = chenese.toCharArray();
                byte[] fullByte = new byte[3 * c.length];
                for (int i = 0; i < c.length; ++i) {
                    int m = c[i];
                    String word = Integer.toBinaryString(m);

                    StringBuffer sb = new StringBuffer();
                    int len = 16 - word.length();

                    for (int j = 0; j < len; ++j) {
                        sb.append("0");
                    }
                    sb.append(word);
                    sb.insert(0, "1110");
                    sb.insert(8, "10");
                    sb.insert(16, "10");

                    String s1 = sb.substring(0, 8);
                    String s2 = sb.substring(8, 16);
                    String s3 = sb.substring(16);

                    byte b0 = Integer.valueOf(s1, 2).byteValue();
                    byte b1 = Integer.valueOf(s2, 2).byteValue();
                    byte b2 = Integer.valueOf(s3, 2).byteValue();
                    byte[] bf = new byte[3];
                    bf[0] = b0;
                    fullByte[(i * 3)] = bf[0];
                    bf[1] = b1;
                    fullByte[(i * 3 + 1)] = bf[1];
                    bf[2] = b2;
                    fullByte[(i * 3 + 2)] = bf[2];
                }

                return fullByte;
            }
         
            public OaSmssendDao getOaSmssendDao() {
                return oaSmssendDao;
            }

            public void setOaSmssendDao(OaSmssendDao oaSmssendDao) {
                this.oaSmssendDao = oaSmssendDao;
            }
           
            public void setOaSmssendLogManager(OaSmssendLogManager oaSmssendLogManager) {
                this.oaSmssendLogManager = oaSmssendLogManager;
            }     
            
            
}

