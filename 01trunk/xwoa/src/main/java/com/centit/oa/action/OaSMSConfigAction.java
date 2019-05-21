package com.centit.oa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSMSConfig;
import com.centit.oa.service.OaSMSConfigManager;
import com.centit.oa.service.OaSmssendManager;
	

public class OaSMSConfigAction  extends BaseEntityExtremeAction<OaSMSConfig>  {
	private static final Log log = LogFactory.getLog(OaSMSConfigAction.class);
	
	//private static final ISysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog("optid");
	
	private static final long serialVersionUID = 1L;
	private OaSMSConfigManager oaSMSConfigManager;
	
	private OaSmssendManager oaSmssendManager;
	
	public void setOaSMSConfigManager(OaSMSConfigManager basemgr)
	{
	    oaSMSConfigManager = basemgr;
		this.setBaseEntityManager(oaSMSConfigManager);
	}

	 /**
     * 短信配置页面
     * 1.如果存在启用数据，显示当前启用的配置详情+最近数据
     * 2.没有启用数据时，且没有暂存数据 显示配置页面
     * @return
     */
    public String settingCustomPanel(){
        object=oaSMSConfigManager.getActiveSMSConfig();
      
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        objList=oaSMSConfigManager.listObjects(new HashMap<String,Object>(),pageDesc);
        
        if(null!=object && StringUtils.isEmpty(object.getSmsid())&&(null!=objList&& objList.size()==0)){
            return "settingCustomPanel";
        }
        
        return "customPanel";
    }
	
    
    /**
     * 新增短信配置信息
     * 以生效的短信配置信息内容为模板修改
     */
    public String addSMSConfig(){
        
        object=oaSMSConfigManager.getActiveSMSConfig();
        object.setSmsid(null);
        return "settingCustomPanel";
    }
    
    
    /**
     * 编辑维护短信配置信息
     *
     */
    public String editSMSConfig(){
        
        object=oaSMSConfigManager.getObjectById(object.getSmsid());
        return "settingCustomPanel";
    }
    
    /**
     * 保存短信配置信息
     * 修改编辑保存时只做新增操作
     */
    public void saveSMSConfig(){
        
        boolean f = false;
        
        try{
            oaSMSConfigManager.saveSMSConfig(object);
            oaSmssendManager.closeConnect();
            f = true;
        } 
        catch(Exception e){
            log.error(e.getMessage());
        }
        responseJson(f);
        
    }
    
    /**
     * 修改状态
     */
    public String updateState(){
        
        
        try{
            
            oaSMSConfigManager.updateState(object);
            oaSmssendManager.closeConnect();
        } 
        catch(Exception e){
            log.error(e.getMessage());
        }
        
       return settingCustomPanel();
        
    }
    private void responseJson(Object obj){
        PrintWriter out = null;;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(obj);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();  
        out.close();  
    }

    public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
        this.oaSmssendManager = oaSmssendManager;
    }
    
    
}
