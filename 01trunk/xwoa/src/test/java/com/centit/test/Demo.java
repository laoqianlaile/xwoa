package com.centit.test;
import com.linkage.netmsg.NetMsgclient;
import com.linkage.netmsg.server.ReceiveMsg;

public class Demo {
    public static void main(String[] args) {
        NetMsgclient client   = new NetMsgclient();
        /*ReceiveMsgImpl为ReceiveMsg类的子类，构造时，构造自己继承的子类就行*/
        ReceiveMsg receiveMsg = new ReceiveDemo();
        /*初始化参数*/
        client = client.initParameters("202.102.41.101", 9005, "02584551238", "NJGHxjzx2016", receiveMsg);
        try {     /*登录认证*/
            boolean isLogin = client.anthenMsg(client);
            if(isLogin) {
            	System.out.println("login sucess");
	            /*发送下行短信*/
	          	System.out.println(client.sendMsg(client, 0, "15950469405", "test thread ", 1));  } 
        } catch (Exception e1) {
            e1.printStackTrace();
        }
     }
    }