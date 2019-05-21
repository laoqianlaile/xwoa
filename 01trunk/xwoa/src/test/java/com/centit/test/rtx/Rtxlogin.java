package com.centit.test.rtx;

import rtx.RTXSvrApi;


public class Rtxlogin {
    public static String getSessionKey(String strUser) throws Exception 
    {
        String strSessionKey = "";
        String rtxSer= "127.0.0.1";
        
       
            
            /*检测客户端登录状态beg*/
            int iState = -1;
            RTXSvrApi  RtxsvrapiObj = null;
            try {
                RtxsvrapiObj = new RTXSvrApi(); 
            } catch (Throwable e) {//error exception
                e.printStackTrace();
            }
              
            if( RtxsvrapiObj.Init())
            {   
                iState =RtxsvrapiObj.QueryUserState(strUser);
                
                switch   (iState)   
                  {   
                          case   0:   
                                System.out.println("用户: " + strUser + "状态为: " + "离线");
                                break;   
                          case   1:   
                                System.out.println("用户: " + strUser + "状态为: " + "在线");
                                break;   
                          case   2:     
                                System.out.println("用户: " + strUser + "状态为: " + "离开");
                                break;   
                          case   -984:     
                                System.out.println("用户: " + strUser + "不存在");
                                break;  
                          default:   
                                System.out.println("访问RTX服务器出错!");
                                break;   
                  }
            }   
            RtxsvrapiObj.UnInit();
            /*检测客户端登录状态end*/
            
            
            /*客户端未登录时 依据SessionKey 登录 beg */
            if(iState==0){
               
                /* 登录客户端方法一 beg*/
               /* String strURL = "http://127.0.0.1:6000/GetSession.cgi?receiver=" + strUser;

                try
                {
                    java.net.URL url = new URL(strURL);
                    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
                
                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
                    strSessionKey=reader.readLine();  
                
                }
                catch(Exception e)
                {
                    System.out.println("系统出错"+e);
                }*/
                /* 登录客户端方法一 end*/
                
                /* 登录客户端方法二 beg*/
                RTXSvrApi rtxsvrapiObj = new RTXSvrApi(); 
                System.out.println(2);
                System.out.println(rtxSer);
                rtxsvrapiObj.setServerIP(rtxSer);
                rtxsvrapiObj.setServerPort(6000);
                strSessionKey=rtxsvrapiObj.getSessionKey(strUser);
                /* 登录客户端方法二end*/
                
            }
            /*客户端未登录时 依据SessionKey 登录 end */
           
           
       
        return strSessionKey;

        
    }
    
    public static void main(String[] args) throws Exception {
        Rtxlogin.getSessionKey("sa");
    }
}
