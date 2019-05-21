package com.centit.webservice.client.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
//import org.apache.cxf.endpoint.Client;
//import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.codehaus.xfire.client.Client;

import com.centit.mip.po.FUnitinfoMIP;
import com.centit.mip.po.FUserinfoMIP;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.webservice.client.WsclientManager;
import com.centit.webservice.util.JsonUtil;

/**
 * TODO Class description should be added
 * 
 * @author hx
 * @create 2015-8-28
 * @version
 */
public class WSClientManagerImpl implements WsclientManager {

    /*
     * public static void main1(String[] args) throws IOException, Exception {
     * String results = ""; try { UrlResource resource = new UrlResource(
     * "http://192.168.132.35:8088/xjoa/services/centitWebService?wsdl"); Client
     * client = new Client(resource.getInputStream(), null);
     * 
     * @SuppressWarnings("unused") String nn =
     * "<?xml version=\"1.0\" encoding=\"gb2312\"?>" +
     * 
     * "<Department>" + "<uuid>9827e73a-782d-4544-80af-c8fb5da99e12</uuid>" +
     * "<name>信息中心</name>" + "<dn>ou=信息中心,ou=政府办公室,o=XX区政府</dn>" +
     * "<sort>10001</sort>" + "<description></description>" +
     * "<postalAddress></postalAddress>" +
     * "<telephoneNumber>025-88888888</telephoneNumber>" +
     * "<fax>025-88888888</fax>" +
     * "<superiorUuid>9827e73a-782d-4544-80af-c8fb5da99e13</superiorUuid>" +
     * "<oldSuperiorUuid>9827e73a-782d-4544-80af-c8fb5da99e14</oldSuperiorUuid>"
     * + "</Department>";
     * 
     * String nn2 =
     * "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
     * "<Department>" + "<description> </description>" +
     * "<dn>ou=龙潭街道办事处,ou=街道办事处,o=栖霞区</dn>" + " <name>龙潭街道办事处</name>" +
     * " <sort>10000</sort>" +
     * "<uuid>2d28320c-a251-4ced-bebe-29aba3b8c280</uuid>" +
     * " <oldSuperiorUuid>50ca0ba2-10cd-41ba-b5da-84545fb94b6c</oldSuperiorUuid>"
     * + " <superiorUuid>50ca0ba2-10cd-41ba-b5da-84545fb94b6c</superiorUuid>" +
     * "<fax></fax>" + "<linkManUuid></linkManUuid>" +
     * "<postalAddress></postalAddress>" + "<telephoneNumber></telephoneNumber>"
     * + "</Department>"; Object[] result = client.invoke("sync", new Object[] {
     * nn2, 2, 3 }); results = result[0].toString(); // results
     * =parseInputStream(); // saveListFusers(results);
     * System.out.println("====" + results); } catch (MalformedURLException e) {
     * results = "FALSE"; } catch (Exception e) { results = "FALSE"; } }
     */
    // 测试
    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            Client c = new Client(
                    new URL(
                            "http://192.168.131.83/centit-oaV0.1/services/centitWebService?wsdl"));
            Object[] result = c.invoke("getPersonalDocList",
                    new Object[] { "1" });
            System.out.println(result[0]);
            String results = (String) result[0];
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    private UserInfoDao sysuserdao;
    private UnitInfoDao sysunitdao;
    private OaUserinfoManager oaUserinfoManager;// 通讯录

    String clientUrl =  CodeRepositoryUtil.getValue("SYSPARAM", "MOBLIEPHONESER");
    private static Logger log = Logger.getLogger(WSClientManagerImpl.class);
    
    @Override
    public String syncUserList(String userInfos) {
        JSONObject dataJson = new JSONObject();// 接口查询数据结果
        
        try {
            if (StringUtils.isNotBlank(userInfos)) {
                List<String> usercodes = Arrays.asList(userInfos.split(","));
                 List<FUserinfo> userList = sysuserdao.listUserinfoByUsercodes(usercodes);
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != userList && userList.size() > 0) {
                for (FUserinfo userinfo : userList) {
                    OaUserinfo oainfo = oaUserinfoManager.getObjectById(userinfo
                            .getUsercode());
                    
                   String  unitcode=CodeRepositoryUtil.getPrimaryUnit(userinfo .getUsercode());
                   String  unitname=CodeRepositoryUtil.getValue("unitcode", unitcode);
                  Client c = new Client(new URL(clientUrl));
                  Object[] result = c.invoke("synchronizeUserInfo",
                       new Object[] { userinfo.getUsercode(),userinfo.getLoginname(),userinfo.getUsername(),
                          unitcode,unitname,userinfo.getUserpin(),null==oainfo?"":oainfo.getWorkplace(),null==oainfo?"":oainfo.getTelephone() ,null==oainfo?"":oainfo.getMobilephone(),
                                 null==oainfo?"":oainfo.getEmail(),null==oainfo?"":oainfo.getSex(),"T".equals(userinfo.getIsvalid())?"0":"1"});//0启用 1 禁用
                  log.info("用户信息:" + userinfo.getUsername());
                }
           }
          }
        } 
        catch (MalformedURLException e) {
            return  "FALSE";
        } catch (Exception e) {
            return   "FALSE";
        }
       
        return "SUCCESS";
    }

    public String syncDepList(String unitCodes) {
        String returnInfo = syncUnitInfo(unitCodes);
        try {
            
            if (StringUtils.isNotBlank(unitCodes)) {
                List<String> unitcodes = Arrays.asList(unitCodes.split(","));
                List<FUnitinfo> unitList = sysunitdao
                        .listUnitinfoByUnitcodes(unitcodes);
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != unitList && unitList.size() > 0) {
                for (FUnitinfo unitinfo : unitList) {
                   Client c = new Client(new URL(clientUrl));
                  Object[] result = c.invoke("synchronizeDeptInfo",
                       new Object[] {unitinfo.getUnitcode(),unitinfo.getParentunit(),unitinfo.getUnitname()});
                }
             }
          }
        } catch (MalformedURLException e) {
            returnInfo = "FALSE";
        } catch (Exception e) {
            returnInfo = "FALSE";
        }
        return returnInfo;
    }

    /**
     * 处理机构信息
     * 
     * @param unitCodes
     * @return
     */
    public String syncUnitInfo(String unitCodes) {

        String returnInfo = "";
        JSONObject dataJson = new JSONObject();// 接口查询数据结果
        if (StringUtils.isNotBlank(unitCodes)) {
            List<FUnitinfoMIP> objMIPList = new ArrayList<FUnitinfoMIP>();
            List<String> unitcodes = Arrays.asList(unitCodes.split(","));
            List<FUnitinfo> unitList = sysunitdao
                    .listUnitinfoByUnitcodes(unitcodes);
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != unitList && unitList.size() > 0) {
                for (FUnitinfo unitinfo : unitList) {
                    FUnitinfoMIP mipTemp = new FUnitinfoMIP();
                    mipTemp.copyNotNullProperty(unitinfo);
                    objMIPList.add(mipTemp);
                }
            }
            dataJson.put("deplist",
                    JsonUtil.createSimpleFormJsonString(objMIPList));
        }

        return JsonUtil.createJsonString(dataJson);
    }

    /**
     * 处理用户信息
     * 
     * @param userCodes
     * @return
     */
    public String syncUserInfo(String userCodes) {
        String returnInfo = "";
        JSONObject dataJson = new JSONObject();// 接口查询数据结果
        if (StringUtils.isNotBlank(userCodes)) {
            List<FUserinfoMIP> objMIPList = new ArrayList<FUserinfoMIP>();
            List<String> usercodes = Arrays.asList(userCodes.split(","));
            List<FUserinfo> userList = sysuserdao
                    .listUserinfoByUsercodes(usercodes);
            // 按接口要求封装数据--使用空字符串代替空值
            if (null != userList && userList.size() > 0) {
                for (FUserinfo userinfo : userList) {
                    FUserinfoMIP mipTemp = new FUserinfoMIP();
                    mipTemp.copyNotNullProperty(userinfo);
                    objMIPList.add(mipTemp);
                }
            }
            dataJson.put("userlist",
                    JsonUtil.createSimpleFormJsonString(objMIPList));
        }

        return JsonUtil.createJsonString(dataJson);
    }

    public void setSysuserDao(UserInfoDao userdao) {
        this.sysuserdao = userdao;
    }

    public void setSysunitDao(UnitInfoDao sysunitdao) {
        this.sysunitdao = sysunitdao;
    }

    public OaUserinfoManager getOaUserinfoManager() {
        return oaUserinfoManager;
    }

    public void setOaUserinfoManager(OaUserinfoManager oaUserinfoManager) {
        this.oaUserinfoManager = oaUserinfoManager;
    }

}
