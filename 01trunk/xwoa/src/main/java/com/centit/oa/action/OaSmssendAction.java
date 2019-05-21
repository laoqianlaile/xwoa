package com.centit.oa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSmssend;
import com.centit.oa.po.OaSmssendLog;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaSmssendLogManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
	

public class OaSmssendAction  extends BaseEntityDwzAction<OaSmssend>  {
	private static final Log log = LogFactory.getLog(OaSmssendAction.class);
	private static final long serialVersionUID = 1L;
	private OaSmssendManager oaSmssendMag;
	private OaPowerrolergroupManager oaPowerrolergroupManager;
	 
	private Map<String, Object> result;
	
	public void setOaSmssendManager(OaSmssendManager basemgr)
	{
		oaSmssendMag = basemgr;
		this.setBaseEntityManager(oaSmssendMag);
	}

	public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

	private OaSmssendLogManager oaSmssendLogManager;
	
	public void setOaSmssendLogManager(OaSmssendLogManager oaSmssendLogManager) {
        this.oaSmssendLogManager = oaSmssendLogManager;
    }
	   
	private List<OaSmssendLog> smsSendLogList;
    private String originate;  // 用于存放链接来源
	
    public String editSend(){
        this.built();
	    this.initUsers();
	  //如果从首页进入，用dashboard标记
        if(StringUtils.isNotBlank(request.getParameter("dashboard"))){
            request.setAttribute("dashboard", request.getParameter("dashboard"));
        }
        
        // 从在线人员列表或者其他非短信平台进来
        if("usersOnline".equals(originate)||"otherSend".equals(originate)){
            String usercodes = request.getParameter("usercodes");
            object.setAcceptpeocode(usercodes);
            object.setAcceptpeo(BizCommUtil.getUsernamesFromUsercodes(usercodes));
        }
	    return "editSend";
	}
    
    public String edit(){
        this.initUsers();
        request.setAttribute("edit", request.getParameter("edit"));
        return super.edit();
    }
	
	/*
     * 获取人员
     */
    public void initUsers() {
        JSONArray ja =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", ja.toString());
    }	
    
    /**
     * 重新发送短信
     * @return
     */
    public String reSend() {

        // 重新发送短信
        OaSmssend dbObject = oaSmssendMag.getObject(object);
        if (null != dbObject) {
            oaSmssendMag.copyObjectNotNullProperty(dbObject, object);
            object = dbObject;
            object.setState("-1");
            object.setRestoremessage("-1");
            
            object.setSendtime(DatetimeOpt.currentUtilDate());
            // 验证接收人有无手机号或者号码有没不合规范
            oaSmssendMag.validatePhoneNum(object);
            if("E".equals(object.getState())){
                //object.setRestoremessage("E");
                // 保存短信发送记录
                oaSmssendLogManager.saveSMSLogs(object);
            }
            oaSmssendMag.saveObject(object);
            
            if(!"E".equals(object.getState())){
                oaSmssendMag.executeSendMsg();                                
            }
        }

        return "reSend";
    }
    
    public String saveAcceptpeo(){
        
        // 重新发送短信
        OaSmssend dbObject = oaSmssendMag.getObject(object);
        if(null != dbObject){
            this.reSend();
        }else{    // 新添加一条短信
            
            String acceptpeocodes=object.getAcceptpeocode();
            if(StringUtils.isNotBlank(acceptpeocodes)){
                String [] aa=acceptpeocodes.split(",");
                FUserDetail user = ((FUserDetail) getLoginUser());
                for(String acceptpeocode:aa){
                    // 发送短信
                    oaSmssendMag.saveMsgs(acceptpeocode, user.getUsercode(), object.getContent(),"R");
                    oaSmssendMag.executeSendMsg();
                }
            }
            
        }
        
        // 如果是从在线人员列表进来的，就再返回过去
        if("usersOnline".equals(originate)){
            return "userOnlineList";
        }
        //其他地方进来的
        if("otherSend".equals(originate)){
            return "sendSucces";
        }
        return this.list();
    }
    
    /**
     * 验证是否可以发送短信
     * @return
     */
    public String canSendMsgs(){
        
        FUserDetail user = (FUserDetail)getLoginUser();
        
        if(null != user){
            // 当月发送短信数已达上限，不能继续发送
            if(!oaSmssendMag.canSendMsg(user.getUsercode())){
                result = new HashMap<String, Object>();
                result.put("error", "您当月已发短信数已达上限！");
            }
        }
               
        return "canSendMsgs";
    }
    /**
     * 列表，查询已发短信
     * 
     * @return
     */
    public String list() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);

            // 列出登录人员发送的短信
            FUserDetail user = (FUserDetail)getLoginUser();
            if(null != user){
                filterMap.put("sendpeocode", user.getUsercode());                
            }
            objList = oaSmssendMag.listObjects(filterMap, pageDesc);
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
           
            //return LIST;
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
   
    public String view(){
        
        OaSmssend send = oaSmssendMag.getObject(object);
        if(null != send){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("smsid", send.getSmsid());
            smsSendLogList = oaSmssendLogManager.listObjects(map);
            
            oaSmssendMag.copyObject(object, send);
            object.setOaSmssendLogList(smsSendLogList);
            
            if (null != smsSendLogList && smsSendLogList.size() > 1) {
                request.setAttribute("isShowLogs", true);
            }
        }
        return VIEW;
    }
    public String getOriginate() {
        return originate;
    }

    public void setOriginate(String originate) {
        this.originate = originate;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public List<OaSmssendLog> getSmsSendLogList() {
        return smsSendLogList;
    }

    public void setSmsSendLogList(List<OaSmssendLog> smsSendLogList) {
        this.smsSendLogList = smsSendLogList;
    }
    
}
