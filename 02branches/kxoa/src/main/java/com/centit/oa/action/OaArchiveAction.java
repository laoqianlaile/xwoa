package com.centit.oa.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.DispatchDoc;
import com.centit.dispatchdoc.po.IncomeDoc;
import com.centit.dispatchdoc.service.DispatchDocManager;
import com.centit.dispatchdoc.service.IncomeDocManager;
import com.centit.oa.po.OaArchive;
import com.centit.oa.service.OaArchiveManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.DateUtil;

public class OaArchiveAction extends OACommonBizAction<OaArchive> {
    private static final Log log = LogFactory.getLog(OaArchiveAction.class);
    private static final long serialVersionUID = 1L;
    private OaArchiveManager oaArchiveMag;
    private DispatchDocManager dispatchDocManager;
    private IncomeDocManager incomeDocManager;
    private List<FUnitinfo> unitlist;// 责任人
    private String flag;//标识收发文类别
    private List<OaArchive> ndlist;//年度
    public void setOaArchiveManager(OaArchiveManager basemgr) {
        oaArchiveMag = basemgr;
        this.setBaseEntityManager(oaArchiveMag);
    }
    
    public void setDispatchDocManager(DispatchDocManager dispatchDocManager) {
        this.dispatchDocManager = dispatchDocManager;
    }

    public void setIncomeDocManager(IncomeDocManager incomeDocManager) {
        this.incomeDocManager = incomeDocManager;
    }
    //编辑归档表单信息

    @Override
    public String edit(){
        super.edit();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date currentTime = new Date();
        String year = formatter.format(currentTime);
        request.setAttribute("year", Integer.parseInt(year)-1);
        long storeYear=object.getFilingannual();
        long yearofsub=Long.parseLong(year)-storeYear;//判断当前时间和已归档时间的差
        request.setAttribute("isEditYear",yearofsub>2?"T":"F");
        if("O".equals(object.getDoctype())){
            request.setAttribute("flag","O");
        }
       this.initparam("GDDJ");//归档手动登记通用配置信息
        return EDIT;
    }
    /**
     * 初始化登记页面材料上传控件
     * @param string
     */
    private void initparam(String modecode) {
        // TODO Auto-generated method stub
        moduleParam=generalModuleParamManager.getObjectById(modecode);
    }

    //进入归档填写
    public String add() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date currentTime = new Date();
        String year = formatter.format(currentTime);
        request.setAttribute("nowtime", currentTime);
        request.setAttribute("year", Integer.parseInt(year)-1);
        try {
            object.setId(oaArchiveMag.getNextKey());
            object.setTitle(URLDecoder.decode(request.getParameter("title"),"UTF-8")) ;
            object.setDocno(URLDecoder.decode(request.getParameter("docno"),"UTF-8")) ;
            if(object.getTitanic()==null){
                object.setTitanic(oaArchiveMag.getNextMaxKey()); 
            }
            this.initparam("GDDJ");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EDIT;
    }
    //进入归档填写-手动添加
    public String toDoadd() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date currentTime = new Date();
        String year = formatter.format(currentTime);
        request.setAttribute("nowtime", currentTime);
        request.setAttribute("flag", "O");//标志为手动情况
        request.setAttribute("year", Integer.parseInt(year)-1);
        try {
            object.setId(oaArchiveMag.getNextKey());
            if(object.getTitanic()==null){
                object.setTitanic(oaArchiveMag.getNextMaxKey()); 
            }
            this.initparam("GDDJ");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EDIT;
    }
    
    
    @Override
    public String view() {
        // TODO Auto-generated method stub
        this.initparam("GDDJ");
        return super.view();
    }
    //保存归档 
    @Override
    public String save(){
        FUserDetail user =null;
        try{
            user=((FUserDetail) getLoginUser()); 
        }catch (Exception e) {
         return "login";
        }
        OaArchive oaArchive =oaArchiveMag.getObjectById(object.getId());
           
        if(oaArchive!=null){
            oaArchive.copyNotNullProperty(object);
            oaArchive.setLastmodifytime(DatetimeOpt.currentSqlDate());
            oaArchive.setUpdateuser(user.getUsercode());
            oaArchive.setUnitcode(user.getPrimaryUnit());
            object=oaArchive;
            oaArchiveMag.saveObject(object);
            //oaArchiveMag.updateTatanic(object.getDuration());
        }else{
            oaArchive =new OaArchive();
            oaArchive.copy(object);
            oaArchive.setCreatetime(DatetimeOpt.currentSqlDate());
            oaArchive.setCreateuser(user.getUsercode());
            //oaArchive.setId(oaArchiveMag.getNextKey());
            oaArchive.setLastmodifytime(DatetimeOpt.currentSqlDate());
            oaArchive.setUpdateuser(user.getUsercode());
            //oaArchive.setUnitcode(user.getPrimaryUnit());
            if("F".equals(flag)){
                DispatchDoc dispatchDoc = dispatchDocManager.getObjectById(oaArchive.getNo());
                dispatchDoc.setArchiveLimit(oaArchive.getDuration());
                dispatchDoc.setIsArchive("T");
                oaArchive.setDoctype("F");//发文类别
                dispatchDocManager.saveObject(dispatchDoc);
               
            }else if("S".equals(flag)){
                IncomeDoc incomeDoc = incomeDocManager.getObjectById(oaArchive.getNo());
                incomeDoc.setItemSource("T");
                oaArchive.setDoctype("S");//收文类别
                incomeDoc.setApplyOrgCode(object.getDuration());
                incomeDocManager.saveObject(incomeDoc);
            }else{
                oaArchive.setDoctype("O");//其他类型,用户手工录入
            }
                
        }
        object=oaArchive;
        oaArchiveMag.saveObject(object);
        //oaArchiveMag.updateTatanic(object.getDuration());//生成按保管期限类别标准排序的件号
       saveMessage("归档成功，请在已归档汇总列表中查看！");
        if("F".equals(flag)){
            return "FWList";
        }if("S".equals(flag)){
            return "SWList";
        }else{
            return this.list();
        }
    }
    
    /**
     * 检查数据库中是否存在同样件号的归档文件
     * 判断标准：1、如果成查不到，则当前可用；2、查到了，判断是否是编辑当前本身；除此之外都为不合法数据；
     * @return
     */    
    public String checkTitanic(){
        String result = "";
        try {      
              
              OaArchive oaArchive2= oaArchiveMag.getObjectByIds(object.getFilingannual(),object.getTitanic());
              if(oaArchive2==null){//表示数据库没有此项记录
                  result = "success";
              }else{
                 //如果不为空，此时需检查是否为当前编辑记录
                  OaArchive oaArchive=oaArchiveMag.getObjectById(object.getId());
                  if(oaArchive!=null&&object.getTitanic().equals(oaArchive.getTitanic())){
                      result = "success";
                  }
              }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e);
        } finally {
            super.ajaxResponseText(response, result);
        }
        
        return null;         
    }
    //删除归档
    public String deleteObj(){
        super.delete();
        //oaArchiveMag.updateTatanic(object.getDuration());//生成按保管期限类别标准排序的件号
        if("F".equals(object.getDoctype())){
            DispatchDoc dispatchDoc = dispatchDocManager.getObjectById(object.getNo());
            dispatchDoc.setArchiveLimit(null);
            dispatchDoc.setIsArchive("F");
            dispatchDocManager.saveObject(dispatchDoc);
        }else if("S".equals(object.getDoctype())){
            IncomeDoc incomeDoc = incomeDocManager.getObjectById(object.getNo());
            incomeDoc.setItemSource("F");
            incomeDoc.setApplyOrgCode(null);
            incomeDocManager.saveObject(incomeDoc);
        }
        return this.list();
    }
    @Override
    public String list() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
          //默认查询当年第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("begincomeDate"))&&StringUtils.isBlank((String)filterMap.get("endincomeDate"))){
                filterMap.put("begincomeDate",DateUtil.getCurrentMonthOfDay() );
                filterMap.put("endincomeDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
            }
            
            
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
           
            objList = oaArchiveMag.listObjects(filterMap,pageDesc);
            totalRows = pageDesc.getTotalRows();
            filterMap.put("fromMenu", "YGDHZ");
            setbackSearchColumn(filterMap);
            return "listV2";
            // return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public List<OaArchive> getNdlist() {
        ndlist = oaArchiveMag.listNdList();
        return ndlist;
    }

    public void setNdlist(List<OaArchive> ndlist) {
        this.ndlist = ndlist;
    }

    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    

}
