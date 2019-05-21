package com.centit.oa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaUnitIncomedoc;
import com.centit.oa.po.VoaUnitIncomedoc;
import com.centit.oa.service.OaUnitIncomedocManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.DateUtil;
	

public class OaUnitIncomedocAction  extends BaseEntityDwzAction<OaUnitIncomedoc>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaUnitIncomedocAction.class);
	private static final long serialVersionUID = 1L;
	private OaUnitIncomedocManager oaUnitIncomedocMag;
	private SysUserManager sysUserManager;
	private List<VoaUnitIncomedoc> VoaUnitIncomedocs =new ArrayList<VoaUnitIncomedoc>();
	public void setOaUnitIncomedocManager(OaUnitIncomedocManager basemgr)
	{
		oaUnitIncomedocMag = basemgr;
		this.setBaseEntityManager(oaUnitIncomedocMag);
	}
	//收文归档查看列表
	public String list(){
	    try {
	        FUserDetail user = ((FUserDetail) getLoginUser());
	        FUserunit funit = sysUserManager.getUserPrimaryUnit(user
	                .getUsercode());
	        @SuppressWarnings("unchecked")
	        Map<Object, Object> paramMap = request.getParameterMap();
	        resetPageParam(paramMap);
	        Map<String, Object> filterMap = convertSearchColumn(paramMap);
	        if(StringUtils.isBlank(request.getParameter("bmsw"))){//如果bmsw为空，显示单位收文
	            filterMap.put("unitcode", funit.getUnitcode());
	        }
	        filterMap.put("NP_isopen", true);//显示公开
	        Limit limit = ExtremeTableUtils.getLimit(request);
	        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
	        if(StringUtils.isBlank((String)filterMap.get("begincomeDate"))&&StringUtils.isBlank((String)filterMap.get("endincomeDate"))){
                filterMap.put("begincomeDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endincomeDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            if(StringUtils.isBlank((String)filterMap.get("begUpdateTime"))&&StringUtils.isBlank((String)filterMap.get("endUpdateTime"))){
                filterMap.put("begUpdateTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endUpdateTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }

	        VoaUnitIncomedocs = oaUnitIncomedocMag.listVoaBizBindInfo(filterMap,pageDesc);
	        totalRows = pageDesc.getTotalRows();
	        this.pageDesc=pageDesc;
	        filterMap.put("fromMenu", "SWKFYDCK");//收文开放阅读查看
	        setbackSearchColumn(filterMap);
	        //return LIST;
	        return "listV2";
	    }catch(Exception e){
	        e.printStackTrace();
	        return ERROR;
	    }
	   
	}
	//收文归档维护列表
	@SuppressWarnings("unchecked")
    public String listDoc(){
	    try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            filterMap.put("usercode", getLoginUserCode());
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            if(StringUtils.isBlank((String)filterMap.get("begincomeDate"))&&StringUtils.isBlank((String)filterMap.get("endincomeDate"))){
                filterMap.put("begincomeDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endincomeDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
//            if(StringUtils.isBlank((String)filterMap.get("begUpdateTime"))&&StringUtils.isBlank((String)filterMap.get("endUpdateTime"))){
//                filterMap.put("begUpdateTime",DateUtil.getCurrentMonthOfDay() );
//                filterMap.put("endUpdateTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
//            }

            VoaUnitIncomedocs = oaUnitIncomedocMag.listVoaBizBindInfo(filterMap,pageDesc);
            totalRows = pageDesc.getTotalRows();
            this.pageDesc = pageDesc;
            filterMap.put("fromMenu", "SWKFYDPZ");//收文开放阅读配置
            setbackSearchColumn(filterMap);
            //return "listDoc";
            return "listDocV2";
        }catch(Exception e){
            e.printStackTrace();
            return ERROR;
        }
	}
	/**
     *封装公开设置
     * @return
     */
    public void showOpen(String [] pk,String isopen){
        FUserunit funit = sysUserManager.getUserPrimaryUnit(getLoginUserCode());
        for(String s :pk){
            String id= s.split("\\|")[0];//获取表单提交的id
            String no= s.split("\\|")[1];
            if(StringUtils.isNotBlank(id)){
                OaUnitIncomedoc incomedoc =oaUnitIncomedocMag.getObjectById(id); 
                incomedoc.setIsopen(isopen);
                incomedoc.setLastmodifytime(DatetimeOpt.currentSqlDate());
                incomedoc.setUpdateuser(getLoginUserCode());
                oaUnitIncomedocMag.saveObject(incomedoc);
            }else{
                OaUnitIncomedoc indoc =new OaUnitIncomedoc();
                indoc.setId(oaUnitIncomedocMag.getNextKey());
                indoc.setCreatetime(DatetimeOpt.currentSqlDate());
                indoc.setCreateuser(getLoginUserCode());
                indoc.setLastmodifytime(DatetimeOpt.currentSqlDate());
                indoc.setUpdateuser(getLoginUserCode());
                indoc.setUnitcode(funit.getUnitcode());
                indoc.setNo(no);
                indoc.setIsopen(isopen);//默认为空或者为0都是公开  第一次保存后应显示不公开
                oaUnitIncomedocMag.saveObject(indoc);
            }
        }
    }
	/**
     * 批量公开
     * @return
     */
    public String openList() {
        String ids = request.getParameter("nos");
        if(StringUtils.isNotBlank(ids)){
            String [] pk = ids.split(",");
            showOpen(pk,"1");
        }
        return listDoc();
    }
    /**
     * 批量不公开
     * @return
     */
    public String closeList() {
        String ids = request.getParameter("nos");
        if(StringUtils.isNotBlank(ids)){
            String [] pk = ids.split(",");
            showOpen(pk,"0");
        }
        return listDoc();
    }
	//保存收文归档 1:表示公开    0:表示不公开
	@Override
	public String save(){
	    FUserunit funit = sysUserManager.getUserPrimaryUnit(getLoginUserCode());
	    OaUnitIncomedoc incomedoc =oaUnitIncomedocMag.getObjectById(object.getId());
	    if(incomedoc!=null){
	        if (incomedoc.getIsopen() != null) {
	            switch(Integer.parseInt(incomedoc.getIsopen())){
	               case 0:incomedoc.setIsopen("1");
	               break;
                   case 1:incomedoc.setIsopen("0");
                   break;
	            }
	        }
	        incomedoc.setLastmodifytime(DatetimeOpt.currentSqlDate());
	        incomedoc.setUpdateuser(getLoginUserCode());
	        oaUnitIncomedocMag.saveObject(incomedoc);
	        
	    }else{
	        OaUnitIncomedoc comedoc =new OaUnitIncomedoc();
	        comedoc.setCreatetime(DatetimeOpt.currentSqlDate());
	        comedoc.setCreateuser(getLoginUserCode());
	        comedoc.setId(oaUnitIncomedocMag.getNextKey());
	        comedoc.setLastmodifytime(DatetimeOpt.currentSqlDate());
	        comedoc.setUpdateuser(getLoginUserCode());
	        comedoc.setUnitcode(funit.getUnitcode());
	        comedoc.setNo(object.getNo());
	        comedoc.setIsopen(object.getIsopen());
	        oaUnitIncomedocMag.saveObject(comedoc);
	    }
	    return this.listDoc();
	}
    public List<VoaUnitIncomedoc> getVoaUnitIncomedocs() {
        return VoaUnitIncomedocs;
    }
    public void setVoaUnitIncomedocs(List<VoaUnitIncomedoc> voaUnitIncomedocs) {
        VoaUnitIncomedocs = voaUnitIncomedocs;
    }
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }//获取登录人员信息
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }	
}
