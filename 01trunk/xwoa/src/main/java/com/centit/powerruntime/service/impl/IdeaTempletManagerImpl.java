package com.centit.powerruntime.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.catalina.loader.WebappClassLoader;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.centit.oa.service.OaUserinfoManager;
import com.centit.powerruntime.po.GeneralModuleParam;
import com.centit.powerruntime.po.OptBaseInfo;
import com.centit.powerruntime.po.OptIdeaInfo;
import com.centit.powerruntime.service.GeneralModuleParamManager;
import com.centit.powerruntime.service.IdeaTempletManager;
import com.centit.powerruntime.service.OptBaseInfoManager;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;
import com.centit.webservice.util.JsonUtil;
import com.centit.workflow.FlowEngine;
import com.centit.workflow.FlowInstance;

/**
 * 
 * 快捷意见模板处理
 * 
 * @author Ghost
 * @create 2016年4月25日
 * @version
 */
public class IdeaTempletManagerImpl implements IdeaTempletManager{
    protected  GeneralModuleParamManager generalModuleParamManager;
    
    protected OptProcInfoManager optProcInfoManager;//办件过程信息
    
    protected OptBaseInfoManager optBaseInfoManager;
    
    private OaUserinfoManager oaUserinfoManager;// 用户详细信息
    
    protected  FlowEngine flowEngine;
    
    private static Logger log=Logger.getLogger(IdeaTempletManager.class);
    
    public void setGeneralModuleParamManager(
            GeneralModuleParamManager generalModuleParamManager) {
        this.generalModuleParamManager = generalModuleParamManager;
    }
    
    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public void setOptBaseInfoManager(OptBaseInfoManager optBaseInfoManager) {
        this.optBaseInfoManager = optBaseInfoManager;
    }

    public void setFlowEngine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    @Override
    public String loadTemplet(String moduleCode) {
        String defaultIdeaTemplet= "{\"type\":\"\",\"dataModule\":\"{transcontent} </br> <div class='r' align='right'> {username} {transdate}</div>\" ,\"DATE_PATTERN\":\"yyyy-MM-dd\" }";//默认;
        //从通用配置里获取模板信息
        GeneralModuleParam  moduleParam =generalModuleParamManager.getObjectById(moduleCode);
        if(null!=moduleParam && StringUtils.isNotBlank(moduleParam.getIdeaModule()) ){//空值时取默认值
           return moduleParam.getIdeaModule();//意见模板
        
        }
        
        return defaultIdeaTemplet;
    }
    /**
     * 依据模板显示办理意见，优先使用用户签名图片
     */
    @Override
    public String parseByTemplet(String templetJsonStr,
            List<OptIdeaInfo> optIdeaInfos) {
        String idea = "";// 办理信息
        try{
                JSONObject templetJsonObject = JSONObject.fromObject(templetJsonStr);
                
                String type=templetJsonObject.has("type")?templetJsonObject.getString("type"):"";
                String dataModule=templetJsonObject.has("dataModule")?templetJsonObject.getString("dataModule"):"";
                String DATE_PATTERN=templetJsonObject.has("DATE_PATTERN")?templetJsonObject.getString("DATE_PATTERN"):"";
                
                if (optIdeaInfos != null && !optIdeaInfos.isEmpty()) {
                    String view="";
                    for (OptIdeaInfo optIdeaInfo : optIdeaInfos) {
                       
                           Map<String, Object>  dateMap =new HashMap<String, Object>();
                           if(StringUtils.isNotBlank( getImgHtml( optIdeaInfo ))){
                               dateMap.put("{username}",JsonUtil.replaceNullString(getImgHtml( optIdeaInfo )));
                           }else{
                               dateMap.put("{username}",JsonUtil.replaceNullString(optIdeaInfo.getUsername()));  
                           }
                           dateMap.put("{transdate}",null==optIdeaInfo.getTransdate()?"":DateUtil.date2String(optIdeaInfo.getTransdate(),DATE_PATTERN));
                           dateMap.put("{transcontent}",JsonUtil.replaceNullString(optIdeaInfo.getTranscontent()));
                          
                            if(!("sigle".equals(type)&&StringUtils.isNotBlank(idea))){//是否单条记录
                                view=BizCommUtil.replace(dateMap, dataModule);//替换
                                idea += view ;
                            }
                    }
                }
        }catch(Exception e){
            return "";
        }
        return idea;
    }

    
    @Override
    public String getIdea(String djid, String moduleCode) {
        String templetJsonStr = loadTemplet(moduleCode);
        
        List<OptIdeaInfo> optIdeaInfos = null;
        if(StringUtils.isNotBlank(djid)){
           optIdeaInfos = optProcInfoManager
                        .listIdeaLogsByDjIdAndModuleCode(djid, moduleCode);
        }
        List<OptIdeaInfo> optIdeaInfos2 = new ArrayList<OptIdeaInfo>();
        if(null!=optIdeaInfos && optIdeaInfos.size()!=0) {
            if("xwfw_wb".equals(moduleCode) || "xwfw_bmsh".equals(moduleCode)) {
                int size=optIdeaInfos.size();
                optIdeaInfos2.add(optIdeaInfos.get(size-1));
                optIdeaInfos=optIdeaInfos2;
            }
        }
        String idea = parseByTemplet(templetJsonStr, optIdeaInfos);
        return idea;
    }
    @Override
    public String getIdeaXW(String djid, String moduleCode,String unitcode,String flag) {
        String templetJsonStr = loadTemplet(moduleCode);
        List<OptIdeaInfo> optIdeaInfos = null;
        List<OptIdeaInfo> optIdeaInfosT=null;
        if(StringUtils.isNotBlank(djid)){
           optIdeaInfos = optProcInfoManager.listIdeaLogsByDjIdAndModuleCodeXW(djid, "xwsw_zldy",unitcode,flag);
           optIdeaInfosT = optProcInfoManager.listIdeaLogsByDjIdAndModuleCodeXW(djid, "xwsw_fldy",unitcode,flag);
           if(optIdeaInfos!=null && optIdeaInfos.size()>0 && optIdeaInfosT!=null && optIdeaInfosT.size()>0){
               optIdeaInfos.addAll(0,optIdeaInfosT);
           }else if(optIdeaInfos.size()==0 && optIdeaInfosT!=null && optIdeaInfosT.size()>0){
               optIdeaInfos=optIdeaInfosT;
           }
        }
        String idea = parseByTemplet(templetJsonStr, optIdeaInfos);
        return idea;
    }
    /**
     *获取 签名
     */
    private  String  getImgHtml(OptIdeaInfo optIdeaInfo ){
        String q=this.getClass().getClassLoader().getResource("/").getPath();
        
        
        String imgHtml=null;
        String userCode=optIdeaInfo.getUsercode();
        String contextPath = SysParametersUtils.getParameters("mobileTerminalAccessAddr", null);//内网地址
        if(contextPath.endsWith("/")){
            contextPath = contextPath.substring(0, contextPath.length()-1);
        }
        contextPath = contextPath +((WebappClassLoader) this.getClass().getClassLoader()).getContextName();
        String imgUrl= contextPath+"/images/userSign/"+  userCode+".png";
        try {  
            URL url = new URL(imgUrl);  
            // 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。  
            URLConnection uc = url.openConnection();  
            // 打开的连接读取的输入流。  
            InputStream in = uc.getInputStream();  
            
            imgHtml=" <img alt=\"头像\" src=\""+imgUrl+"\" style=\"width: 100px; height: 50px\" />";
            log.info("用户签名地址"+imgUrl);
        } catch (Exception e) {  
            imgHtml=null;//判断文件是否存在
        }
       
        return imgHtml;
    }
    
    @Override
    public List<OptIdeaInfo> loadAllAvailableIdeas(String djId,boolean showIdeaContent) {
        List<OptIdeaInfo> ideas = new ArrayList<OptIdeaInfo> ();
        
        OptBaseInfo  baseInfo=optBaseInfoManager.getObjectById(djId);//获取业务信息
        
        if(null!=baseInfo ){
            if(null!=baseInfo.getFlowInstId()){
                //获取流程节点信息
                FlowInstance flowInstance=flowEngine.getFlowInstById(baseInfo.getFlowInstId());
                List<GeneralModuleParam> moduleListtemp= generalModuleParamManager.getGeneralModuleList(flowInstance.getFlowCode(),flowInstance.getVersion());
            
                if(null!=moduleListtemp&&moduleListtemp.size()>0){
                    for(GeneralModuleParam  moduleParam:moduleListtemp){
                        if(!"F".equals(moduleParam.getIsShowInNode())){
                            OptIdeaInfo idea = new OptIdeaInfo();
                            idea.setTransidea(moduleParam.getNodeLabel());
                            
                            if(showIdeaContent){
                                String ideaFormatted =  getIdea(djId, moduleParam.getModuleCode());
                                idea.setTranscontent(ideaFormatted);
                                }
                            ideas.add(idea);
                        }
                    }
                }
            }
        }
        return ideas;
    }
    
    
    /**
     * 加载出所有可用意见，自由流程特殊处理
     * 如：收文动态加载意见
     * @param djId
     * @return
     */
    @Override
    public List<OptIdeaInfo> loadAllAvailableIdeasXW(String djId,boolean showIdeaContent) {
        List<OptIdeaInfo> ideas = new ArrayList<OptIdeaInfo> ();
        OptBaseInfo  baseInfo=optBaseInfoManager.getObjectById(djId);//获取业务信息
        if(null!=baseInfo ){//获取流程节点信息
            if(null!=baseInfo.getFlowInstId()) {
                FlowInstance flowInstance=flowEngine.getFlowInstById(baseInfo.getFlowInstId());
                List<GeneralModuleParam> moduleListtemp= generalModuleParamManager.getGeneralModuleList(flowInstance.getFlowCode(),flowInstance.getVersion());
                if(null!=moduleListtemp&&moduleListtemp.size()>0){
                    for(GeneralModuleParam  moduleParam:moduleListtemp){
                        if(!"F".equals(moduleParam.getIsShowInNode())){
                            OptIdeaInfo idea = new OptIdeaInfo();
                            idea.setTransidea(moduleParam.getNodeLabel());
                            if(showIdeaContent){
                                if(moduleParam.getModuleCode().equals("xwsw_fldy")){//只在领导阅做特殊处理，其他不做特殊处理了
                                    //OptIdeaInfo ideaLd = new OptIdeaInfo();
                                    idea.setTransidea("领导批示:");//需要保留的节点
                                    String ideaFormattedLd =  getIdeaXW(djId, "xwsw_fldy","881","881");
                                    idea.setTranscontent(ideaFormattedLd);
                                    //ideas.add(ideaLd);
                                    //continue;
                                    //String ideaFormatted =  getIdeaXW(djId, "xwsw_zldy","881",null);
                                    //idea.setTranscontent(ideaFormatted);
                                }else{
                                    String ideaFormatted =  getIdea(djId, moduleParam.getModuleCode());
                                    idea.setTranscontent(ideaFormatted);
                                }
                            }
                            ideas.add(idea);
                        }
                    }
                }
            }
        }
        return ideas;
    }

}
