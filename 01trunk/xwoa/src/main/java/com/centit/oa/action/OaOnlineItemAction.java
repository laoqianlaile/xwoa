package com.centit.oa.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaOnlineItem;
import com.centit.oa.po.OaOnlineItems;
import com.centit.oa.po.OaSurvey;
import com.centit.oa.po.OaSurveydetail;
import com.centit.oa.service.OaOnlineItemManager;
import com.centit.oa.service.OaSurveyManager;
import com.centit.oa.service.OaSurveydetailManager;
import com.centit.sys.po.FUserinfo;

public class OaOnlineItemAction  extends BaseEntityDwzAction<OaOnlineItem>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaOnlineItemAction.class);
	private static final long serialVersionUID = 1L;
	private OaOnlineItemManager oaOnlineItemMag;
	
	
	private String s_itemType; //题目查看类型：preList 预览 list 题目列表 surList 调查页面
	private String s_type;//new 新建调查 mgr 调查管理 vote 调查投票
	
	private OaSurveyManager oaSurveyManager;//调查信息
	private OaSurveydetailManager oaSurveydetailManager;//投票结果
	private String isSaved="false";//判断是否已提交过投票
	public void setOaOnlineItemManager(OaOnlineItemManager basemgr)
	{
		oaOnlineItemMag = basemgr;
		this.setBaseEntityManager(oaOnlineItemMag);
	}

	private List<OaOnlineItems> newOaOnlineItemss;
	private String s_djid;//调查序号
//	private List<OaOnlineItems> oaOnlineItems;
	
//	public List<OaOnlineItems> getNewOaOnlineItemss() {
//		return this.oaOnlineItemss;
//	}
//	public void setNewOaOnlineItemss(List<OaOnlineItems> oaOnlineItemss) {
//		this.oaOnlineItemss = oaOnlineItemss;
//	}
	
	
	 /**
     * 列表数据(根据调查序号 djid 获取题目)
     */
    @SuppressWarnings("unchecked")
    public String list() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
            OaSurvey oaSurvey=new OaSurvey();
            List<OaSurveydetail> oaSurveydetail=null;
            String djid=(String)filterMap.get("djid");
            if(StringUtils.isNotBlank(djid)){
                oaSurvey=oaSurveyManager.getObjectById(djid);//djid调查序号   
                Map<String, Object> filterMapTemp=new HashMap<String, Object>();
                filterMapTemp.put("djid",djid);//根据djid ，usercode 获取调查信息明细
                filterMapTemp.put("creater",getLoginUserCode());
                oaSurveydetail=oaSurveydetailManager.listObjects(filterMapTemp);
                if(oaSurveydetail.size()>0){
                    isSaved="true";
                }
            }
            objList=oaOnlineItemMag.listObjects(filterMap, pageDesc);
            
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            request.setAttribute("oaSurvey", oaSurvey);
            //题目查看类型：preList 预览 list 题目列表 surList 调查页面  tjList统计
            if("tjList".equals(s_itemType)){
                return "tjList";
            }
            if("preList".equals(s_itemType)||"surList".equals(s_itemType)){
                return "previewList";
            }else{
                return LIST;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
   
    
    
    
    
    
    /**
     * 列表数据(根据调查序号 djid 获取题目)
     */
    @SuppressWarnings("unchecked")
    public String previewList() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
            OaSurvey oaSurvey=new OaSurvey();
            String djid=(String)filterMap.get("djid");
            if(StringUtils.isNotBlank(djid)){
                oaSurvey=oaSurveyManager.getObjectById(djid);//djid调查序号    
            }
            objList=oaOnlineItemMag.listObjects(filterMap, pageDesc);
            
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            request.setAttribute("oaSurvey", oaSurvey);
            return "previewList";

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
	
    /**
     * 编辑,提前生成no
     */
    public String edit() {
      
        super.edit();
        if (StringUtils.isBlank(object.getNo())) {
            object.setNo(oaOnlineItemMag.getNextKey());//主键
            object.setDjid(s_djid);//外键 调查序号
        }
        return EDIT;
    }
    /*
     * 当前登录人员usercode
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }
	public String save(){
		oaOnlineItemMag.saveObject(object,newOaOnlineItemss);
		return SUCCESS;
	}
    public String getS_djid() {
        return s_djid;
    }
    public void setS_djid(String s_djid) {
        this.s_djid = s_djid;
    }

    public List<OaOnlineItems> getNewOaOnlineItemss() {
        return newOaOnlineItemss;
    }

    public void setNewOaOnlineItemss(List<OaOnlineItems> newOaOnlineItemss) {
        this.newOaOnlineItemss = newOaOnlineItemss;
    }
    public OaSurveyManager getOaSurveyManager() {
        return oaSurveyManager;
    }
    public void setOaSurveyManager(OaSurveyManager oaSurveyManager) {
        this.oaSurveyManager = oaSurveyManager;
    }
    public String getS_itemType() {
        return s_itemType;
    }
    public void setS_itemType(String s_itemType) {
        this.s_itemType = s_itemType;
    }
    public String getS_type() {
        return s_type;
    }
    public void setS_type(String s_type) {
        this.s_type = s_type;
    }
    public OaSurveydetailManager getOaSurveydetailManager() {
        return oaSurveydetailManager;
    }
    public void setOaSurveydetailManager(OaSurveydetailManager oaSurveydetailManager) {
        this.oaSurveydetailManager = oaSurveydetailManager;
    }
    public String getIsSaved() {
        return isSaved;
    }
    public void setIsSaved(String isSaved) {
        this.isSaved = isSaved;
    }
		
}
