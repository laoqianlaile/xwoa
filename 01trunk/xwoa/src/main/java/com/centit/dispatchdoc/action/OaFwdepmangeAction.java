package com.centit.dispatchdoc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.OaFwdepmange;
import com.centit.dispatchdoc.service.OaFwdepmangeManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUnitManager;
	

public class OaFwdepmangeAction  extends BaseEntityDwzAction<OaFwdepmange>  {
	private static final Log log = LogFactory.getLog(OaFwdepmangeAction.class);
	private static final long serialVersionUID = 1L;
	private OaFwdepmangeManager oaFwdepmangeMag;
	
	private SysUnitManager sysUnitManager;
	private OaPowerrolergroupManager oaPowerrolergroupManager;//部门树
	private String s_unitcode;// 部门编号
	
	/**
	 * 公文分级管理
	 * 1.左侧机构数 当前用户所在机构(包含子机构)当不包含子机构时不显示
	 * 2.部门文书部分
	 * 3.部门文号部分
	 */
	public String mangeDetail(){
	    //过滤条件
	    Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        List<OaFwdepmange>  oaDocdepmange =new ArrayList<OaFwdepmange>();
        List<OaFwdepmange>  oaZWHdepmange =new ArrayList<OaFwdepmange>(); 
        List<OaFwdepmange>  oaDocParentdepmange =new ArrayList<OaFwdepmange>();
        List<OaFwdepmange>  oaZWHParentdepmange =new ArrayList<OaFwdepmange>(); 
	    //当前用户信息
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        if(null==loginUser){
            return  "login";
        }
        
	    if(StringUtils.isEmpty(s_unitcode)){
	        s_unitcode=loginUser.getPrimaryUnit();
	    }
	    /*//顶级机构查询所有信息
	    if("1".equals(s_unitcode)){
	        //获取所有配置信息
	          oaDocdepmange= oaFwdepmangeMag.getAllDocManageList();
	          oaZWHdepmange=oaFwdepmangeMag.getAllZWHManageList();
	      
	        
	    }else{*/
	        
	         //获取上级机构配置信息
	         FUnitinfo  unitParent =sysUnitManager.getObjectById(s_unitcode);
	         
	         if(null!=unitParent){
	             if("0".equals(unitParent.getParentunit())){//顶级部门
	                 oaDocParentdepmange= oaFwdepmangeMag.getDocManageListByUnit(s_unitcode);
	                 oaZWHParentdepmange=oaFwdepmangeMag.getZWHManageListByUnit(s_unitcode); 
	             }else{
	                 oaDocParentdepmange= oaFwdepmangeMag.getDocManageListByUnit(unitParent.getParentunit());
	                 oaZWHParentdepmange=oaFwdepmangeMag.getZWHManageListByUnit(unitParent.getParentunit()); 
	                 //获取配置信息
	                 oaDocdepmange= oaFwdepmangeMag.getDocManageListByUnit(s_unitcode);
	                 oaZWHdepmange=oaFwdepmangeMag.getZWHManageListByUnit(s_unitcode);
	             }
	            
	             
	         }
	        
             
	/*}*/
	  
	    setbackSearchColumn(filterMap);
	    request.setAttribute("oaDocdepmange", oaDocdepmange);
        request.setAttribute("oaZWHdepmange", oaZWHdepmange);
        request.setAttribute("oaDocParentdepmange", oaDocParentdepmange);
        request.setAttribute("oaZWHParentdepmange", oaZWHParentdepmange);
        return "mangeDetail";
	    
	}	
	
	
	 /**
     * 公文分级管理
     * 1.左侧机构数 当前用户所在机构(包含子机构)当不包含子机构时不显示
     */
    public String manage(){
        //当前用户信息
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        if(null==loginUser){
            return  "login";
        }
        
//        if(StringUtils.isEmpty(s_unitcode)){
            s_unitcode=loginUser.getPrimaryUnit();
//        }
       
        JSONArray   units= oaPowerrolergroupManager.getSubUnitsFWFJ(s_unitcode);//发文分级配置获取部门
       
        request.setAttribute("units", units);
        return "mangeList";
        
    }
    
    /**待选
     * 新增文号列表
     * @param basemgr
     */
    public String selectZWHList(){
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        //当前用户信息
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        if(null==loginUser){
            return  "login";
        }
        
        if(StringUtils.isEmpty(s_unitcode)){
            s_unitcode=loginUser.getPrimaryUnit();
        }
        
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        List<FDatadictionary> ZWHList=oaFwdepmangeMag.selectZWHList(s_unitcode, filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        request.setAttribute("ZWHList", ZWHList);
        return "ZWHSelectList";
        
    }

    /**待选
     * 新增文书列表
     * 
     */
    public String selectDocList(){
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
        //当前用户信息
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        if(null==loginUser){
            return  "login";
        }
        
        if(StringUtils.isEmpty(s_unitcode)){
            s_unitcode=loginUser.getPrimaryUnit();
        }
        
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        List<TemplateFile> templateList=oaFwdepmangeMag.selectDocList(s_unitcode,filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        request.setAttribute("templateList", templateList);
        return "DocSelectList";
        
    }
    
    
    
    /**
     * 保存文号配置信息
     */
    public  void saveSelectZWHList(){
        
        String s_templates = (String)request.getParameter("s_templates");
        String s_unitcodes = (String)request.getParameter("s_unitcodes");
        if(StringUtils.isEmpty(s_unitcodes)){
          //当前用户信息
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
            s_unitcodes=loginUser.getPrimaryUnit();
        }
        
        oaFwdepmangeMag.saveSelectZWhList(s_unitcodes,s_templates);
           
    }
    
    /**
     * 保存文书配置信息
     */
    public  void saveSelectDocList(){
        
        String s_templates = (String)request.getParameter("s_templates");
        String s_unitcodes = (String)request.getParameter("s_unitcodes");
        if(StringUtils.isEmpty(s_unitcodes)){
          //当前用户信息
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
            s_unitcodes=loginUser.getPrimaryUnit();
        }
        
        oaFwdepmangeMag.saveSelectDocList(s_unitcodes,s_templates);
           
    }
 
    
    
  
    
    
    
    
    
    

    public void setOaFwdepmangeManager(OaFwdepmangeManager basemgr)
    {
        oaFwdepmangeMag = basemgr;
        this.setBaseEntityManager(oaFwdepmangeMag);
    }

    public SysUnitManager getSysUnitManager() {
        return sysUnitManager;
    }


    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }


    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }


    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }


    public String getS_unitcode() {
        return s_unitcode;
    }


    public void setS_unitcode(String s_unitcode) {
        this.s_unitcode = s_unitcode;
    }




	
}
