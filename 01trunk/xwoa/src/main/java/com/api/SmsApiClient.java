/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.axis.types.URI;
import org.csapi.www.schema.sms.SendMethodType;

import com.inspur.sms.intf.APIInfo;
import com.inspur.sms.intf.MOBean;
import com.inspur.sms.intf.MTBean;
import com.inspur.sms.intf.RPTBean;

public class SmsApiClient {
    private static HttpURLConnection connection;
    public static boolean conn = false;
    private static String apiName;
    private static String apiPwd;
    private static String apiId;
    private static String post = "127.0.0.1";
    private static int maxmobile = 50;

    public static int initConnect(String apiId, String uid, String pwd) {
        List mt = new ArrayList();

        APIInfo apInfo = new APIInfo();

        apInfo.setIntfId(apiId);
        apInfo.setIntfName(uid);
        apInfo.setIntfPwd(pwd);
        apInfo.setType("init");

        mt.add(apInfo);
        List resList = postObjectData(mt);

        return ((Integer) resList.get(0)).intValue();
    }

    public static int initConnect() throws IOException {
        String configFile = "config.properties";

        String configpath = System.getProperty("user.dir") + File.separator
                + configFile;
        FileInputStream fis = new FileInputStream(configpath);
        Properties properties = new Properties();
        properties.load(fis);

        post = properties.getProperty("post").trim();
        apiId = properties.getProperty("apiId").trim();
        apiName = properties.getProperty("apiName").trim();
        apiPwd = properties.getProperty("apiPwd").trim();

        if (conn) {
            System.out.println("已建立链接，不需要再建立链接！返回");
        }

        int result = initConnect(apiId, apiName, apiPwd);

        return result;
    }

    public static int initConnect(String ipAddress, int port, String apiId,
            String username, String password) throws IOException {
        post = ipAddress + ":" + port;
        apiId = apiId;
        apiName = username;
        apiPwd = password;

        if (conn) {
            System.out.println("已建立链接，不需要再建立链接！返回");
        }

        int result = initConnect(apiId, apiName, apiPwd);

        return result;
    }

    public int releaseConnect() throws ApiException {
        try {
            if (!(conn)) {
                System.out.println("未初始化链接,已是断开状态");
            }
            conn = false;
            System.out.println("断开连接");
        } catch (Exception localException) {
        }

        return 0;
    }

    public String sendSingleMsg(String mobile, String content)
            throws ApiException {
        MTBean[] mtBeans = new MTBean[1];
        mtBeans[0] = new MTBean();
        mtBeans[0].setMessage(content);
        mtBeans[0].setDestinationAddresses(getURIFromMobiles(mobile));
        mtBeans[0].setDeliveryResultRequest(true);

        mtBeans[0].setSendMethod(SendMethodType.Normal);

        return sendMsg(mtBeans);
    }

    public String sendMsg(MTBean[] mtBeans) throws ApiException {
        if (!(conn)) {
            System.out.println("未初始化，调用必须先初始化接口");
        }

        String type = "sendsms";

        List mt = new ArrayList();

        APIInfo apInfo = new APIInfo();
        apInfo.setIntfId(apiId);
        apInfo.setIntfName(apiName);
        apInfo.setIntfPwd(apiPwd);
        apInfo.setType(type);
        mt.add(apInfo);
        mt.add(mtBeans);

        List resList = postObjectData(mt);
        int reflag = ((Integer) resList.get(0)).intValue();
        String smid = "";
        if (reflag != 0)
            System.out.println("调用接口时出错，此处应该抛出异常" + reflag);
        else {
            smid = (String) resList.get(1);
        }

        return smid;
    }

    public RPTBean[] getRptMsg(int num, String[] RequestIdentifier)
            throws ApiException {
        if (!(conn)) {
            System.out.println("未初始化，调用必须先初始化接口");
        }

        String type = "getsmsrpt";

        List mt = new ArrayList();

        APIInfo apInfo = new APIInfo();
        apInfo.setIntfId(apiId);
        apInfo.setIntfName(apiName);
        apInfo.setIntfPwd(apiPwd);
        apInfo.setType(type);
        mt.add(apInfo);
        mt.add(Integer.valueOf(num));
        mt.add(RequestIdentifier);

        List resList = postObjectData(mt);

        int reflag = ((Integer) resList.get(0)).intValue();
        if (reflag != 0) {
            System.out.println("调用接口时出错，此处应该抛出异常" + reflag);
        }
        RPTBean[] rpBeans = (RPTBean[]) resList.get(1);

        return rpBeans;
    }

    public RPTBean[] getRptMsgString(String[] RequestIdentifier)
            throws ApiException {
        int num = 0;

        return getRptMsg(num, RequestIdentifier);
    }

    public MOBean[] getMoMsg(int num) throws ApiException {
        if (!(conn)) {
            System.out.println("未初始化，调用必须先初始化接口");
        }

        String type = "getsmsmo";
        List mt = new ArrayList();

        APIInfo apInfo = new APIInfo();
        apInfo.setIntfId(apiId);
        apInfo.setIntfName(apiName);
        apInfo.setIntfPwd(apiPwd);
        apInfo.setType(type);
        mt.add(apInfo);
        mt.add(Integer.valueOf(num));

        List resList = postObjectData(mt);

        int reflag = ((Integer) resList.get(0)).intValue();
        if (reflag != 0) {
            System.out.println("调用接口时出错，此处应该抛出异常" + reflag);
        }
        MOBean[] moBeans = (MOBean[]) resList.get(1);

        return moBeans;
    }

    public MOBean[] getMoMsg() throws ApiException {
        int num = 0;

        return getMoMsg(num);
    }

    public static URI[] getURIFromMobiles(String mobiles) {
        try {
            if ((mobiles == null) || ("null".equals(mobiles.toLowerCase()))
                    || (mobiles.trim().length() <= 0))
                return null;
            String[] sa = mobiles.split(",");
            URI[] addresses = new URI[sa.length];
            for (int i = 0; i < sa.length; ++i) {
                addresses[i] = new URI("tel", sa[i]);
            }
            return addresses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List postObjectData(List mt) {
        String urlStr = "";
        List li = new ArrayList();
        ObjectOutputStream out = null;
        if ((post != null) && (!(post.equals("")))) {
            urlStr = "http://" + post + "/smsapi";
        }
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            out = new ObjectOutputStream(connection.getOutputStream());

            out.writeObject(mt);
            out.flush();

            ObjectInputStream in = new ObjectInputStream(
                    connection.getInputStream());
            li = (List) in.readObject();

            in.close();
        } catch (MalformedURLException e) {
            System.out.println("URL对象创建异常");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("建立与MAS平台的连接出现异常");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("读取MAS响应消息时出现异常");
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("关闭对象时出现IO异常");
                e.printStackTrace();
            }
        }
        return li;
    }
}