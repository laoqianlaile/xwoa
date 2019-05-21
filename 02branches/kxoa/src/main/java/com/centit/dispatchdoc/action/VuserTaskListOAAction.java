package com.centit.dispatchdoc.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VTransactUser;
import com.centit.dispatchdoc.po.VoadDcdbNum;
import com.centit.dispatchdoc.po.VuserTaskListOA;
import com.centit.dispatchdoc.service.VuserTaskListOAManager;
import com.centit.oa.po.OaSupervise;
import com.centit.oa.service.OaSuperviseManager;
import com.centit.powerruntime.po.OptProcInfo;
import com.centit.powerruntime.service.OptProcInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserrole;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysRoleManager;
import com.centit.sys.service.UserbizoptLogManager;
import com.centit.sys.util.DateUtil;

public class VuserTaskListOAAction extends BaseEntityExtremeAction<VuserTaskListOA> {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory
            .getLog(VuserTaskListOAAction.class);
    private static final long serialVersionUID = 1L;
    private VuserTaskListOAManager vuserTaskListOAMag;
    private OaSuperviseManager oaSuperviseManager;
    private UserbizoptLogManager userbizoptLogManager;//阅读记录
    private OptProcInfoManager optProcInfoManager;
    
    public void setOptProcInfoManager(OptProcInfoManager optProcInfoManager) {
        this.optProcInfoManager = optProcInfoManager;
    }

    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }

    public void setSysRoleManager(SysRoleManager sysRoleManager) {
        this.sysRoleManager = sysRoleManager;
    }
    private SysRoleManager sysRoleManager;
    private String isShow;//首页待办是否需要时间查询
    
    private List<VTransactUser> transactList;
    
    public List<VTransactUser> getTransactList() {
        return transactList;
    }

    public void setTransactList(List<VTransactUser> transactList) {
        this.transactList = transactList;
    }

    public void setOaSuperviseManager(OaSuperviseManager oaSuperviseManager) {
        this.oaSuperviseManager = oaSuperviseManager;
    }

    public void setVuserTaskListOAManager(VuserTaskListOAManager basemgr) {
        vuserTaskListOAMag = basemgr;
        this.setBaseEntityManager(vuserTaskListOAMag);
    }

    private String s_flowCode;
       private List<VuserTaskListOA> numlist;
    public List<VuserTaskListOA> getNumlist() {
        return numlist;
    }

    public void setNumlist(List<VuserTaskListOA> numlist) {
        this.numlist = numlist;
    }

    public String getS_flowCode() {
        return s_flowCode;
    }

    public void setS_flowCode(String s_flowCode) {
        this.s_flowCode = s_flowCode;
    }

    public String list() {
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String userCode = fuser.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("userCode", userCode);
            String itemtype=(String)filterMap.get("itemTypeName");
            //业务类别
            if(StringUtils.isNotBlank(itemtype)){
                filterMap.remove("itemTypeName");
                filterMap.put("itemtype", itemtype);
            }
            //排序  阅读状态+时间 by dk 2016-03-01
            filterMap.put("ORDER_BY", " criticalLevel desc,readState asc,createtime desc ");
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            /*       numlist = vuserTaskListOAMag.listObjects(filterMap, pageDesc);
         
            setbackSearchColumn(filterMap);
        if(numlist.size()>0&&numlist!=null){
              //判断numlist是否是督办流程的列表数据
                if((numlist.get(0).getDjId()).indexOf("DCDB")!=-1){
                    for(int i=0;i<numlist.size();i++){
                        VuserTaskListOA oa=numlist.get(i);
                        OaSupervise oasup=oaSuperviseManager.getObjectById(oa.getDjId());
                            if(oasup!=null&&oasup.getLimittime()!=null){
                                Date nowtime=(new Date(System.currentTimeMillis()));
                                //比较日期，
                               //flag=true  超时；flag=false 提醒
                                boolean flag = oasup.getLimittime().before(nowtime);
                           
                              //warntype 0 提醒 1 超时
                                    if(flag){
                                        oa.setWarnType("1");
                                        objList.add(oa);
                                    }else{
                                        oa.setWarnType("0");
                                        objList.add(oa);
                                    }
                                
                            }else{
                             objList.addAll(numlist);
                        }
                    }                    
                }else{
                    objList.addAll(numlist);
                }
   
            }else{
                
                objList.addAll(numlist);
            
            }*/
           //change by lq 目的为当前列表判断是否可以督办，以及显示督办标识（页面根据${vuserTaskListOA.flowCode != '000859' }判断督办权限）
           
            if(!"T".equals(isShow)){//首页待办 
                if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("endTime"))){
                    filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                    filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
                }
            }
            objList = vuserTaskListOAMag.listObjects(filterMap, pageDesc);
            //查询完还原，以免对其他产生影响
            if(StringUtils.isNotBlank(itemtype)){
                filterMap.remove("itemtype");
                filterMap.put("itemTypeName", itemtype);
            }
            if(objList!=null&&objList.size()>0){
                //判断objList是否是督办流程的列表数据
                      for(int i=0;i<objList.size();i++){
                       
                          //事项被督办的话，在办件流水号前面用图标标注
                          VuserTaskListOA oa=objList.get(i);
                          Map<String, Object> filterMapTemp = new HashMap<String, Object>();
                          filterMapTemp.put("supDjidEq",oa.getDjId());
                          
                         List<OaSupervise> supList=oaSuperviseManager.listObjects(filterMapTemp);
                         if(supList!=null&&supList.size()>0){
                             oa.setIsSuprised("1");
                         }else{
                             oa.setIsSuprised("0");
                         }
                          
                          //督办件添加督办时限标识
                          if((objList.get(i).getDjId()).indexOf("DCDB")!=-1){
//                          VuserTaskListOA oa=objList.get(i);
                          OaSupervise oasup=oaSuperviseManager.getObjectById(oa.getDjId());
                              if(oasup!=null&&oasup.getLimittime()!=null){
                                  Date nowtime=(new Date(System.currentTimeMillis()));
                                  //比较日期，
                                 //flag=true  超时；flag=false 提醒
                                  boolean flag = oasup.getLimittime().before(nowtime);
                             
                                //warntype 0 提醒 1 超时
                                      if(flag){
                                          oa.setWarnType("1");
                                        
                                      }else{
                                          oa.setWarnType("0");
                                      }
                                  
                              }
                          }
                          //=============办件添加是否阅读状态start=============
                          UserbizoptLog uboptlog=userbizoptLogManager.listObject(oa.getDjId(), userCode, oa.getNodeInstId());
                          if(uboptlog!=null){
                              oa.setReadstate("T");//已读
                          }else{
                              oa.setReadstate("F");//未读
                          }
                          oa.setReadstate(CodeRepositoryUtil.getValue("readstate", oa.getReadstate()));//已读
                         
                          //==================       end      ===========
                          OptProcInfo opt=null;
                          opt=optProcInfoManager.getObjectByDjidAndNodeInstId(oa.getDjId(), oa.getNodeInstId(), userCode);
                          if(opt!=null){//此办件已被暂存
                              oa.setReadstate("暂存");
                          }
                          
                          // 添加收发文办件是否超时提醒
                          oa.setOverdueType(vuserTaskListOAMag.getOverDueState(oa));
                          
                      }
            }
           /* //排序
            Collections.sort(objList, new Comparator<VuserTaskListOA>(){

                @Override
                public int compare(VuserTaskListOA o1, VuserTaskListOA o2) {
                    String hits0 = o1.getReadstate();
                    String hits1 = o2.getReadstate();
                    return hits1.compareTo(hits0);
                    
                }
                
            });*/
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            //return LIST;
            return "listV2";
         
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    private List<VoadDcdbNum> selectList;


    public List<VoadDcdbNum> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<VoadDcdbNum> selectList) {
        this.selectList = selectList;
    }

    public String selectlist() {
        try {
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            String userCode = fuser.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            //filterMap.put("userCode", userCode);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("endTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endTime", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            //督办发起人可以看到所以的办件 by dk-2016-02-17
            //add by lay 2015-11-12 机构为厅领导的看所有的，其他看自己部门的
            FUserinfo userInfo = (FUserinfo)getLoginUser();
            FUserrole fUserrole=null;
            fUserrole=sysRoleManager.getValidUserrole2V(userCode, "G-G_DBFQ");
            
            if( userInfo!=null && fUserrole==null && !"001801".equals(userInfo.getPrimaryUnit()) && !"99999999".equals(userInfo.getUsercode())){
                filterMap.put("unitcode", userInfo.getPrimaryUnit());
            }
            //end add
            selectList = vuserTaskListOAMag.getdcdbnum(filterMap, pageDesc);
            if(selectList!=null){
            totalRows = selectList.size();
            }
            setbackSearchColumn(filterMap);
            return "dblist";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String listUsersOfTransaction(){
        
        String djId = request.getParameter("djId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("djId", djId);
        
        transactList = vuserTaskListOAMag.getCurrentTransactUsers(djId);
        
        request.setAttribute("currNodeName", vuserTaskListOAMag.getCurrentNodeNames(djId));
        
        return "listUsersOfTransaction";
    }
    
    /**
     * 
     * @return
     */
    public String selectDbList(){
        this.selectlist();
       //return "DbList"; 
       return "DbListV2"; 
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
}
