package com.centit.sys.action;

import java.io.IOException;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.sys.po.FAccessLog;
import com.centit.sys.service.AccessLogManager;


public class AccessLogAction extends BaseEntityExtremeAction<FAccessLog>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private AccessLogManager accessLogMgr;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    private static final Logger logger = Logger.getLogger(AccessLogAction.class);
    
    /**
     * 获取在线用户数
     * @throws IOException
     */
    public void getUserCountOnline() throws IOException{
        int count = 0;
        try{
            count = accessLogMgr.getUserCountOnline();
            if(count==0){//防止异地登录相同账号情况，其中一个注销，另一个依旧登录的情况
                count=1; 
            }
        }catch(Exception e){
            logger.error("获取在线用户统计时发生异常，异常信息："+e.getMessage());
        }
        String resultJson = "{\"count\":"+count+"}";
        ServletActionContext.getResponse().getWriter().print(resultJson);
    }

    public String list(){
        JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
        return super.list();
    }
    public AccessLogManager getAccessLogMgr() {
        return accessLogMgr;
    }

    public void setAccessLogMgr(AccessLogManager accessLogMgr) {
        this.accessLogMgr = accessLogMgr;
        this.setBaseEntityManager(accessLogMgr);
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    
    
}
