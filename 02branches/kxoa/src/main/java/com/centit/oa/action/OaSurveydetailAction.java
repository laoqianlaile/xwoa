package com.centit.oa.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSurvey;
import com.centit.oa.po.OaSurveydetail;
import com.centit.oa.po.VOaSurveyDetail;
import com.centit.oa.service.OaSurveyManager;
import com.centit.oa.service.OaSurveydetailManager;
import com.centit.sys.po.FUserinfo;

public class OaSurveydetailAction extends BaseEntityExtremeAction<OaSurveydetail> {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory
            .getLog(OaSurveydetailAction.class);
    private static final long serialVersionUID = 1L;
    private OaSurveydetailManager oaSurveydetailMag;
    private OaSurveyManager oaSurveyManager;
    

    private String s_itemType; // 题目查看类型：preList 预览 list 题目列表 surList 调查页面
    private String s_type;// new 新建调查 mgr 调查管理 vote 调查投票

    public void setOaSurveydetailManager(OaSurveydetailManager basemgr) {
        oaSurveydetailMag = basemgr;
        this.setBaseEntityManager(oaSurveydetailMag);
    }

    private List<OaSurveydetail> oaSurveydetails;
    private List<VOaSurveyDetail> surveyDetailList;
    /**
     * 保存投票结果,投票成功后返回调查投票列表
     * 
     * @return
     */
    public String saveSurveydetail() {
        oaSurveydetailMag.saveSurveydetail(oaSurveydetails, getLoginUserCode());
        return "surveyList";
    }

    /**
     * 按調查id獲取人員列表
     * @return
     */
    @SuppressWarnings("unchecked")
    public String detailList() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        PageDesc pageDesc = makePageDesc();
        OaSurvey oaSurvey=new OaSurvey();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        String djid=(String)filterMap.get("djid");
        if(StringUtils.isNotBlank(djid)){
            oaSurvey=oaSurveyManager.getObjectById(djid);//djid调查序号    
        }
        
        List<FUserinfo> infoList =oaSurveydetailMag.detailList(djid,pageDesc);
        request.setAttribute("infoList", infoList);
        request.setAttribute("oaSurvey", oaSurvey);
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return "detailList";
    }
    
    /**
     *  根据调查id，投票者获取详细调查结果
     * @return
     */
    @SuppressWarnings("unchecked")
    public String surveyDetailViewList() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        //PageDesc pageDesc = makePageDesc();
        OaSurvey oaSurvey=new OaSurvey();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        String djid=(String)filterMap.get("djid");
        String creater=(String)filterMap.get("creater");
        
        
        if(StringUtils.isNotBlank(djid)){
            oaSurvey=oaSurveyManager.getObjectById(djid);//djid调查序号    
        }
        
        surveyDetailList =oaSurveydetailMag.detailViewList(djid,creater);
        request.setAttribute("infoList", surveyDetailList);
        request.setAttribute("oaSurvey", oaSurvey);
        totalRows = surveyDetailList.size();
        setbackSearchColumn(filterMap);
        return "surveyDetailView";
    }

    /*
     * 当前登录人员usercode
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }
    /**
     * 分页
     * 
     * @return
     */
    public PageDesc makePageDesc() {
        return DwzTableUtils.makePageDesc(request);
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

    public List<OaSurveydetail> getOaSurveydetails() {
        return oaSurveydetails;
    }

    public void setOaSurveydetails(List<OaSurveydetail> oaSurveydetails) {
        this.oaSurveydetails = oaSurveydetails;
    }

    public OaSurveyManager getOaSurveyManager() {
        return oaSurveyManager;
    }

    public void setOaSurveyManager(OaSurveyManager oaSurveyManager) {
        this.oaSurveyManager = oaSurveyManager;
    }

    public List<VOaSurveyDetail> getSurveyDetailList() {
        return surveyDetailList;
    }

    public void setSurveyDetailList(List<VOaSurveyDetail> surveyDetailList) {
        this.surveyDetailList = surveyDetailList;
    }
    
}
