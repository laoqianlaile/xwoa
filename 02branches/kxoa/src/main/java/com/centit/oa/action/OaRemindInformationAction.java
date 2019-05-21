package com.centit.oa.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.AddressBookRelectionId;
import com.centit.oa.po.OaRemindInformation;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;

public class OaRemindInformationAction extends
BaseEntityExtremeAction<OaRemindInformation> {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory
            .getLog(OaRemindInformationAction.class);
    private static final long serialVersionUID = 1L;
    private OaRemindInformationManager oaRemindInformationMag;

    public void setOaRemindInformationManager(OaRemindInformationManager basemgr) {
        oaRemindInformationMag = basemgr;
        this.setBaseEntityManager(oaRemindInformationMag);
    }

    private AddressBookRelectionManager addressBookRelectionManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    
    private OaSmssendManager oaSmssendManager;

    private String s_bizType;
    private String s_thesign; // 提醒状态1 已发送2 待发送 区别list页面是否有新增按钮
    private String searchtype; // 区别公用页面上的查询list的参数
    private String userValue;// 获取参会人员usercode
    
    private String originate;// 用于存放链接来源

    /**
     * 
     * 提醒数据
     * 
     */
    public String list() {
        try {
            FUserDetail user = (FUserDetail) getLoginUser();
            if(user==null){
                return "login";
            }
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            if (StringUtils.isNotBlank(this.searchtype)) {
                if (this.searchtype.equals("T")) {
                    filterMap.put("thesign", "1"); // 已发送查询
                } else {
                    filterMap.put("thesign", "2"); // 暂存查询
                }
            }
            filterMap.put("creater",user.getUsercode()); //发起者角度
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaRemindInformationMag.listObjects(filterMap, pageDesc);
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
           // return LIST;
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
   /**
    * 新的增加提醒，首页日程添加时 by dk 2016-2-26 
    * @return
    */
   
   public String builtV2(){
       request.setAttribute("xzrc_sy", request.getParameter("xzrc_sy"));
       String retype=request.getParameter("reType");//市总工会首页发文提醒入口
       if(StringUtils.isNotBlank(retype)){
           request.setAttribute("reTypeFrom", retype);
       }
       return BUILT;
   }
    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {
        //如果是从公共办理页码进来，用from来标记
        if(StringUtils.isNotBlank(request.getParameter("from"))){
            request.setAttribute("from",request.getParameter("from"));
        }
        //从首页进入用dashboard标记
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        
        
        super.edit();
        // 获取提醒人员
        initUsers();
        // 获取中间表某一提醒已存在人员
        getUservalues();
        
        // 从在线人员列表进来
        if("usersOnline".equals(originate)){
            String usercodes = request.getParameter("usercodes");
            request.setAttribute("usercode", BizCommUtil.getUsernamesFromUsercodes(usercodes));
            this.setUserValue(usercodes);
        }
        //督办发起提醒 2016-3-31 by dk
        String txusers=request.getParameter("txusers");
        if(StringUtils.isNotBlank(txusers)){
            request.setAttribute("usercode", BizCommUtil.getUsernamesFromUsercodes(txusers));
            this.setUserValue(txusers);
            String title = request.getParameter("title");
            try {
                title = new String(title.getBytes("iso8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            object.setTitle(title);
            //去掉返回按钮
            request.setAttribute("xzrc_sy", request.getParameter("xzrc_sy"));
        }
        return EDIT;
    }

    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray userjson =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
    }
    /*
     * 当前登录人员usercode
     */
    @SuppressWarnings("unused")
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    private void getUservalues() {

        if (StringUtils.isNotBlank(object.getNo())) {
            List<AddressBookRelection> listuser = addressBookRelectionManager
                    .getUserlist(object.getNo(), "2");//获取待发送
            if (listuser != null && listuser.size() > 0) {
                userValue = "";
                for (int i = 0; i < listuser.size(); i++) {
                    String value = listuser.get(i).getShareman() + ",";
                    userValue += value;
                }
            }
            if (StringUtils.isNotBlank(userValue)) {
                userValue = userValue.substring(0, userValue.length() - 1);
            }
        }

    }

    /**
     * 暂存
     * 
     * @return
     */
    public String save() {
        try {

            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            OaRemindInformation info = oaRemindInformationMag
                    .getObjectById(object.getNo());

            if (info == null) {
                info = new OaRemindInformation();
            }
            oaRemindInformationMag.copyObjectNotNullProperty(info, object);
            object = info;

            if (StringUtils.isBlank(object.getNo())) {
                object.setNo(oaRemindInformationMag.getNextkey());
                object.setCreater(user.getUsercode());
                object.setThesign("2");// 暂存
                object.setCreatetime(new Date(System.currentTimeMillis()));
            }
            addressBookRelectionManager.deleteuser(object.getNo());
            // 把提醒人员记录到中间表m_address_book_relection
            if (StringUtils.isNotBlank(userValue)) {
                String[] values = this.userValue.split(",");// 分割字符串
                for (int i = 0; i < values.length; i++) {
                    AddressBookRelection oainfo = new AddressBookRelection();
                    oainfo.setAddrbookid(object.getNo());
                    oainfo.setShareman(values[i]);
                    oainfo.setBizType("2");// 为未生效的状态
                    addressBookRelectionManager.saveObject(oainfo);
                }
            }
            oaRemindInformationMag.saveObject(object);

            // 如果是从在线人员列表进来的，就再返回过去
            if("usersOnline".equals(originate)){
                return "userOnlineList";
            }
            
            return "save";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 提交
     * 
     * @return
     */
    public String submit() {
        try {

            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            OaRemindInformation info = oaRemindInformationMag
                    .getObjectById(object.getNo());

            if (info == null) {
                info = new OaRemindInformation();
            }
            oaRemindInformationMag.copyObjectNotNullProperty(info, object);
            object = info;

            if (StringUtils.isBlank(object.getNo())) {
                object.setNo(oaRemindInformationMag.getNextkey());
                object.setCreater(user.getUsercode());
                object.setThesign("1");// 提交
                object.setCreatetime(new Date(System.currentTimeMillis()));
            } else {

                object.setThesign("1");// 提交
            }
            addressBookRelectionManager.deleteuser(object.getNo());
            // 把提醒人员记录到中间表m_address_book_relection
            if (StringUtils.isNotBlank(userValue)) {
                String[] values = this.userValue.split(",");// 分割字符串
                for (int i = 0; i < values.length; i++) {
                    AddressBookRelection oainfo = new AddressBookRelection();
                    oainfo.setAddrbookid(object.getNo());
                    oainfo.setShareman(values[i]);
                    oainfo.setBizType("0");// 未读
                    
                    this.sendMsg(values[i]);
                    addressBookRelectionManager.saveObject(oainfo);
                }
            }
            oaRemindInformationMag.saveObject(object);

            // 如果是从在线人员列表进来的，就再返回过去
            if("usersOnline".equals(originate)){
                return "userOnlineList";
            }
            
            return "submit";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 发送短信
     * @param receptcode
     */
    public void sendMsg(String receptcode){
        
        String isSendMsg = request.getParameter("isSendMsg");
        if(StringUtils.isNotBlank(isSendMsg) && "T".equals(isSendMsg)){
            
            String remindContent = "{username}，您好，您有一条关于{title}的提醒";
            String s = CodeRepositoryUtil.getValue("sendMSgMod", "remind");
            if(StringUtils.isNotBlank(s) && !"remind".equals(s)){
                remindContent = s;
            }
            
            remindContent = StringUtils.replace(remindContent, "{username}", CodeRepositoryUtil.getValue("usercode", getLoginUserCode()));
            remindContent = StringUtils.replace(remindContent, "{title}", object.getTitle());
            oaSmssendManager.saveMsgs(receptcode, getLoginUserCode(),remindContent,"TX");
            oaSmssendManager.executeSendMsg();
        }
    }
    
    /**
     * 督办发起时提交
     * 
     * @return
     */
    public void submitForDUBAN() {
        try {

            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            OaRemindInformation info = oaRemindInformationMag
                    .getObjectById(object.getNo());

            if (info == null) {
                info = new OaRemindInformation();
            }
            oaRemindInformationMag.copyObjectNotNullProperty(info, object);
            object = info;

            if (StringUtils.isBlank(object.getNo())) {
                object.setNo(oaRemindInformationMag.getNextkey());
                object.setCreater(user.getUsercode());
                object.setThesign("1");// 提交
                object.setCreatetime(new Date(System.currentTimeMillis()));
            } else {

                object.setThesign("1");// 提交
            }
            addressBookRelectionManager.deleteuser(object.getNo());
            // 把提醒人员记录到中间表m_address_book_relection
            if (StringUtils.isNotBlank(userValue)) {
                String[] values = this.userValue.split(",");// 分割字符串
                for (int i = 0; i < values.length; i++) {
                    AddressBookRelection oainfo = new AddressBookRelection();
                    oainfo.setAddrbookid(object.getNo());
                    oainfo.setShareman(values[i]);
                    oainfo.setBizType("0");// 未读
                    addressBookRelectionManager.saveObject(oainfo);
                }
            }
            oaRemindInformationMag.saveObject(object);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 禁用
     * 
     * @return
     */
    public String delete() {
        try {

            OaRemindInformation info = oaRemindInformationMag
                    .getObjectById(object.getNo());

            if (info == null) {
                info = new OaRemindInformation();
            }
            oaRemindInformationMag.copyObjectNotNullProperty(info, object);
            object = info;

            object.setThesign("3");// 重置提醒状态==删除
            oaRemindInformationMag.saveObject(object);
            if (this.searchtype.equals("T")) {
                return "submit";
            } else {
                return "save";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    
    /**
     * 批量删除功能
     * @return
     */
    public String deleteIds() {
        //批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
        for (String no : ids.split(",")) {
            //存放作修改字段
            OaRemindInformation newle =new OaRemindInformation();
            newle.setNo(no);
            newle.setThesign("3");// 重置提醒状态==删除
            //获取原对象，避免使用object
            OaRemindInformation le =  oaRemindInformationMag.getObject(newle);
            if (le != null) {
                oaRemindInformationMag.copyObjectNotNullProperty(le, newle);
                newle = le;
                oaRemindInformationMag.saveObject(newle);
            }
         }
        }
        if (this.searchtype.equals("T")) {
            return "submit";
        } else {
            return "save";
        }
    }
    
    
    public String recipientList() {
        try {
            FUserDetail user = (FUserDetail) getLoginUser();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaRemindInformationMag.recipientList(filterMap, pageDesc,
                    user.getUsercode());
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            //return "recList";
            return "recListV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String view() {
        FUserDetail user = (FUserDetail) getLoginUser();
        super.view();
        // 更新状态bizType
        AddressBookRelection addressBookRelection = addressBookRelectionManager
                .getObjectById(new AddressBookRelectionId(object.getNo(), user
                        .getUsercode(), "0"));
        if (null != addressBookRelection) {
            // 三个字段都为主键，必须先删除，后inser(为不同记录)
            AddressBookRelection addressBookRelectionNew = new AddressBookRelection();
            addressBookRelectionNew.setBizType("1");
            addressBookRelectionNew.setAddrbookid(addressBookRelection
                    .getAddrbookid());
            addressBookRelectionNew.setShareman(addressBookRelection
                    .getShareman());
            addressBookRelectionManager.deleteObject(addressBookRelection);
            addressBookRelectionManager.saveObject(addressBookRelectionNew);
        }

        return "view";
    }

    public String deletRec() {
        FUserDetail user = (FUserDetail) getLoginUser();
        // 更新状态bizType
        AddressBookRelection addressBookRelection = addressBookRelectionManager
                .getObjectById(new AddressBookRelectionId(object.getNo(), user
                        .getUsercode(), s_bizType));
        if (null != addressBookRelection) {
            AddressBookRelection addressBookRelectionNew = new AddressBookRelection();
            addressBookRelectionNew.setBizType("3");
            addressBookRelectionNew.setAddrbookid(addressBookRelection
                    .getAddrbookid());
            addressBookRelectionNew.setShareman(addressBookRelection
                    .getShareman());
            addressBookRelectionManager.deleteObject(addressBookRelection);
            addressBookRelectionManager.saveObject(addressBookRelectionNew);
        }
        return recipientList();
    }
    /**
     * 批量删除功能：中间表
     * @return
     */
    public String deleteRecIds() {

        FUserDetail user = (FUserDetail) getLoginUser();

        // 批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
            for (String no : ids.split(",")) {

                // 更新状态bizType
                AddressBookRelection addressBookRelection = addressBookRelectionManager
                        .getObjectById(new AddressBookRelectionId(no, user.getUsercode(), s_bizType));
                if (null != addressBookRelection) {
                    AddressBookRelection addressBookRelectionNew = new AddressBookRelection();
                    addressBookRelectionNew.setBizType("3");
                    addressBookRelectionNew.setAddrbookid(addressBookRelection
                            .getAddrbookid());
                    addressBookRelectionNew.setShareman(addressBookRelection
                            .getShareman());
                    addressBookRelectionManager
                            .deleteObject(addressBookRelection);
                    addressBookRelectionManager
                            .saveObject(addressBookRelectionNew);
                }
            }
        }
        return recipientList();
    }

    public AddressBookRelectionManager getAddressBookRelectionManager() {
        return addressBookRelectionManager;
    }

    public void setAddressBookRelectionManager(
            AddressBookRelectionManager addressBookRelectionManager) {
        this.addressBookRelectionManager = addressBookRelectionManager;
    }

    public String getS_bizType() {
        return s_bizType;
    }

    public void setS_bizType(String s_bizType) {
        this.s_bizType = s_bizType;
    }

    public String getS_thesign() {
        return s_thesign;
    }

    public void setS_thesign(String s_thesign) {
        this.s_thesign = s_thesign;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    
    public String getOriginate() {
        return originate;
    }
    
    public void setOriginate(String originate) {
        this.originate = originate;
    }
    
    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }

    
}
