package com.centit.oa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.powerruntime.service.OptApplyManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.DateUtil;
	

public class VOptBaseListAction  extends OACommonBizAction<VOptBaseList>  {
	private static final long serialVersionUID = 1L;
	private VOptBaseListManager vOptBaseListMag;
	private OptApplyManager optApplyManager;
	public void setVOptBaseListManager(VOptBaseListManager basemgr)
	{
		vOptBaseListMag = basemgr;
		this.setBaseEntityManager(vOptBaseListMag);
	}

	
	
//	    private String queryUnderUnit;//(列表类别)按职务等级查询列表
	    private FUserunit userUnit;// 用户单位
	    private String userRank=null;
	    private String unitsJson;
	    private String parentunit;
	    private List<FUnitinfo> unitList;
	    private List<VOptBaseList>	vOptBaseList;
	    
	    



        
	    
	    /**
	     * 根据分管领导查询其分管部门办件情况
	     * @return
	     */
	    @SuppressWarnings("unchecked")
        public String listBM(){
	        FUserDetail fuser = ((FUserDetail) getLoginUser());
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
      
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
           
            filterMap.put("NP_bizstate", true);//默认查看在办已办件
            
            //默认查询当前月份第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("begincreateDate"))&&StringUtils.isBlank((String)filterMap.get("endcreateDate"))){
                filterMap.put("begincreateDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endcreateDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }           
            filterMap.put("leadercode", fuser.getUsercode());
            
            vOptBaseList = vOptBaseListMag.listOptBaseInfoLeader(filterMap,pageDesc);
            totalRows =vOptBaseList!=null?vOptBaseList.size():0;   
            
            
            setbackSearchColumn(filterMap);
	        //return "listBM";
	        return "listBMV2";
	    }
	    
	    /**
	     * 个人办件查看
	     */
	  @SuppressWarnings("unchecked")
	    public String list() {
	        try {
	            FUserDetail fuser = ((FUserDetail) getLoginUser());
	            Map<Object, Object> paramMap = request.getParameterMap();
	            resetPageParam(paramMap);
	            Map<String, Object> filterMap = convertSearchColumn(paramMap);
	      
	            Limit limit = ExtremeTableUtils.getLimit(request);
	            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
	           
	            filterMap.put("NP_bizstate", true);//默认查看在办已办件
	            
	            //默认查询当前月份第一天到现在的记录
	            if(StringUtils.isBlank((String)filterMap.get("begincreateDate"))&&StringUtils.isBlank((String)filterMap.get("endcreateDate"))){
	                filterMap.put("begincreateDate",DateUtil.getCurrentMonthOfDay() );
	                filterMap.put("endcreateDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
	            }
	            
	            
	            userRank=getUserRankByUsercode(fuser.getUsercode());//控制页面显示内容:全机构(部门下拉框)、全处室（checkboox）
	            //列表页面添加职务分类查看 ：厅长查看机构所有查看，处长查看本处室
	            if("true".equals(filterMap.get("queryUnderUnit"))){//(列表类别)按职务等级查询列表--默认显示自己的
	                
	                userUnit =sysUserManager.getUserPrimaryUnit(fuser.getUsercode());
	                if(StringUtils.equals(userRank, "CZ")){
	                    filterMap.put("queryUnderUnit","true");//页面传参
	                    filterMap.put("unitcode",userUnit.getUnitcode());
	                }
                else  if(StringUtils.equals(userRank, "TZ")){
	                   
	                    filterMap.put("queryUnderUnit","true");//部门编码从页面传入queryUnderUnit
	                  //filterMap.put("unitcode",userUnit.getUnitcode());//部门编码从页面传入s_unitcode
	                }
	            }else{
	                filterMap.put("usercode", fuser.getUsercode());
	            }
	           
	            vOptBaseList = vOptBaseListMag.listOptBaseInfo(filterMap,
	                    pageDesc);
	            totalRows = pageDesc.getTotalRows();
	            
	            FUserunit dept = sysUserManager.getUserPrimaryUnit(fuser.getUsercode());
	            unitsJson = sysUnitManager.getAllSubUnitsJSON(dept.getUnitcode());
	            parentunit=sysUnitManager.getObjectById(dept.getUnitcode()).getParentunit();
	            unitList=unitList();//科级部门
	            
	            filterMap.put("fromMenu", "GRBGBJCK");
	            setbackSearchColumn(filterMap);
	            //return LIST;
	            return "listV2";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ERROR;
	        }
	    }
	  
	  public String deleteDoc() {
	        if(StringUtils.isNotBlank(object.getDjId())){
	            deleteObjectBanInfo(object.getDjId());
	        }
	        return this.list();
	    }
	    
	    public void deleteObjectBanInfo(String djId) {
	        optApplyManager.deleteObjectBanInfo(djId);
	    }
	  
	  
	  /**
	   * 提供不同业务办件查看的通用调用方法入口
	   * @return
	   */
	  public String listOwn() {
          try {
              FUserDetail fuser = ((FUserDetail) getLoginUser());
              Map<Object, Object> paramMap = request.getParameterMap();
              resetPageParam(paramMap);
              Map<String, Object> filterMap = convertSearchColumn(paramMap);
        
              Limit limit = ExtremeTableUtils.getLimit(request);
              PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
             
              filterMap.put("NP_bizstate", true);//默认查看在办已办件
              
              //默认查询当前月份第一天到现在的记录
              if(StringUtils.isBlank((String)filterMap.get("begincreateDate"))&&StringUtils.isBlank((String)filterMap.get("endcreateDate"))){
                  filterMap.put("begincreateDate",DateUtil.getCurrentMonthOfDay() );
                  filterMap.put("endcreateDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
              }              
              
            //查询列表--默认显示自己的
                  filterMap.put("usercode", fuser.getUsercode());
              vOptBaseList = vOptBaseListMag.listOptBaseInfo(filterMap,
                      pageDesc);
              totalRows = pageDesc.getTotalRows();
              
              FUserunit dept = sysUserManager.getUserPrimaryUnit(fuser.getUsercode());
              unitsJson = sysUnitManager.getAllSubUnitsJSON(dept.getUnitcode());
              parentunit=sysUnitManager.getObjectById(dept.getUnitcode()).getParentunit();
              unitList=unitList();//科级部门
              setbackSearchColumn(filterMap);
              //return LIST;
              return "listV2";
          } catch (Exception e) {
              e.printStackTrace();
              return ERROR;
          }
      }
	  /**
	     * 根据usercode 获取用户行政职务
	     * @param usercode
	     * @return
	     */
	    private String getUserRankByUsercode(String  usercode){
	        
	        
	        userUnit =sysUserManager.getUserPrimaryUnit(usercode);
	        
	        if(null==userUnit){
	            userRank=null;
	        }else
	        {
	            String userUnitRank=userUnit.getUserrank();
	            if("ZT".equals(userUnitRank)||"FT".equals(userUnitRank)){
	            userRank="TZ";//厅长级别
	            }else if("ZC".equals(userUnitRank)||"FC".equals(userUnitRank)){
	            userRank="CZ";//处长级别
	            }else{
	            userRank=null;//其他（科员，办事员等）--/科长
	            }
	        }
	        return userRank;
	        
	    }

	    /**
	     *  //只获取科室一级的部门
	     * @return
	     */
	    private List<FUnitinfo> unitList(){
	        List<FUnitinfo> unitlist = sysUnitManager.getAllSubUnits("1");
	        List<FUnitinfo> delList = new ArrayList<FUnitinfo>();
	        // 剔除非科室的部门
	        if (unitlist != null && unitlist.size() > 0) {
	            for (FUnitinfo fUnitinfo : unitlist) {
	                if ("1".equals(fUnitinfo.getParentunit())
	                        || "1".equals(fUnitinfo.getUnitcode())) {
	                    continue;
	                } else {
	                    delList.add(fUnitinfo);
	                }
	            }
	            unitlist.removeAll(delList);
	        }
	        return unitlist;
	    }
	    

	    /**
	     * 流程关联签报及发文的列表
	     * @return
	     */
	    private String bjType;
	    public String getBjType() {
	        return bjType;
	    }

	    public void setBjType(String bjType) {
	        this.bjType = bjType;
	    }




    public FUserunit getUserUnit() {
        return userUnit;
    }


    public void setUserUnit(FUserunit userUnit) {
        this.userUnit = userUnit;
    }


    public String getUserRank() {
        return userRank;
    }


    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }


    public String getUnitsJson() {
        return unitsJson;
    }


    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }


    public String getParentunit() {
        return parentunit;
    }


    public void setParentunit(String parentunit) {
        this.parentunit = parentunit;
    }


    public List<FUnitinfo> getUnitList() {
        return unitList;
    }


    public void setUnitList(List<FUnitinfo> unitList) {
        this.unitList = unitList;
    }


    public List<VOptBaseList> getVOptBaseList() {
        return vOptBaseList;
    }


    public void setVOptBaseList(List<VOptBaseList> vOptBaseList) {
        this.vOptBaseList = vOptBaseList;
    }

    public OptApplyManager getOptApplyManager() {
        return optApplyManager;
    }

    public void setOptApplyManager(OptApplyManager optApplyManager) {
        this.optApplyManager = optApplyManager;
    }
	
}
