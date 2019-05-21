package com.centit.powerbase.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.extremecomponents.table.limit.Limit;
import org.springframework.security.core.context.SecurityContextHolder;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Pcfreeumpiredegree;
import com.centit.powerbase.po.Pcfreeumpiretype;
import com.centit.powerbase.po.PcpunishStandard;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.po.SuppowerStandard;
import com.centit.powerbase.po.Suppowerchglog;
import com.centit.powerbase.po.Suppowerstatechglog;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.service.PowerOrgInfoManager;
import com.centit.powerbase.service.SuppowerManager;
import com.centit.powerbase.service.SuppowerchglogManager;
import com.centit.powerbase.service.SuppowerstatechglogManager;
import com.centit.powerbase.service.VPowerUserInfoManager;
import com.centit.powerbase.service.ZycXmlElement;
import com.centit.powerbase.util.IMonitorOptLog;
import com.centit.powerbase.util.MonitorOptLogFactoryImpl;
import com.centit.powerruntime.po.VOrgSupPower;
import com.centit.powerruntime.po.VPowerOrgInfo;
import com.centit.powerruntime.service.VPowerOrgInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.SysTypeUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * TODO Class description should be added
 * 
 * @author hx
 * @create 2012-12-7
 * @version
 */
public class SupPowerAction extends BaseEntityExtremeAction<Suppower> implements
        ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private static final IMonitorOptLog monitorOptLog = MonitorOptLogFactoryImpl
            .getMonitorOptLog("FzjdQlgl");
    private SysUserManager sysUserManager;
    private SysUnitManager sysUnitManager;
    @SuppressWarnings("unused")
    private PowerOrgInfoManager powerOrgInfoManager;
    private VPowerOrgInfoManager vPowerOrgInfoManager;
    
    private VPowerUserInfoManager vPowerUserInfoManager;//权力人员关联
    private List<FUnitinfo> unitList;
    private String s_itemType;

    public List<FUnitinfo> getUnitList() {
        return unitList;
    }
    
    public void setPowerOrgInfoManager(PowerOrgInfoManager powerOrgInfoManager) {
        this.powerOrgInfoManager = powerOrgInfoManager;
    }

    //contrastlist
    public void setUnitList(List<FUnitinfo> unitList) {
        this.unitList = unitList;
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }
    

    public void setvPowerOrgInfoManager(VPowerOrgInfoManager vPowerOrgInfoManager) {
        this.vPowerOrgInfoManager = vPowerOrgInfoManager;
    }


    private SuppowerManager suppowerManager;
    private SuppowerstatechglogManager suppowerstatechglogManager;
    private SuppowerchglogManager suppowerchglogManager;
    private Suppowerchglog suppowerchglog;
    // 下面是页面要用到的一些变量
    @SuppressWarnings("rawtypes")
    private List subPowerList;
    @SuppressWarnings("rawtypes")
    private List supPowerWithoutLobList;
    private List<VPowerOrgInfo> nowList;
    private List<VPowerUserInfo> userPowerList;
    @SuppressWarnings("rawtypes")
    private List supppowerqlbgList;
    private List<Suppower> suppowerList;
    // 保存权力信息
    private File processDesc_;
    private File applyForm_;
    private File srvDirectoryStuff_;
    private File applyFormDemo_;
    public File getSrvDirectoryStuff_() {
        return srvDirectoryStuff_;
    }

    public void setSrvDirectoryStuff_(File srvDirectoryStuff_) {
        this.srvDirectoryStuff_ = srvDirectoryStuff_;
    }

    public File getApplyFormDemo_() {
        return applyFormDemo_;
    }

    public void setApplyFormDemo_(File applyFormDemo_) {
        this.applyFormDemo_ = applyFormDemo_;
    }
    

    public List<VPowerOrgInfo> getNowList() {
        return nowList;
    }
    public void setNowList(List<VPowerOrgInfo> nowList) {
        this.nowList = nowList;
    }

    public String getS_itemType() {
        return s_itemType;
    }

    public void setS_itemType(String s_itemType) {
        this.s_itemType = s_itemType;
    }


    private File inFlowImg_;
    private String processDesc_FileName;
    private String applyForm_FileName;
    private String inFlowImg_FileName;
    private String sItemType;
    private String filename;
    private String fileType;// 附件类型
    // 针对附件异常的提示
    ActionContext context = ActionContext.getContext();
    HttpServletResponse response;
    private List<VOrgSupPower> selectPowerList;
    @SuppressWarnings({ "unchecked","static-access" })
    @Override
    public String edit() {
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        if (StringUtils.isBlank(object.getCid().getItemId())) {
            suppowerManager.clearObjectProperties(object);
        } else {

            String itemid = (String) request.getAttribute("itemId");
            Long version = (Long) request.getAttribute("version");
            if (version_bg != null) {
                object.setVersion(version_bg);
                setVersion_bg(version_bg);
            }
            object = suppowerManager.getObject(object);
            suppowerList = suppowerManager.getlistSuppowerOld(itemid, version);
            for (int i = 0; i < suppowerList.size(); i++) {
                Suppower temp= (Suppower) suppowerList.get(i);
                versionList.add(temp.getVersion());
            }
            if (version != null) {
                object.setVersion(version);
                if (version_bg == null) {
                    setVersion_bg(version);
                }
            }
            List<SuppowerStandard> list2=this.genxmlStandardList(object.getRisk());
            request.setAttribute("list2", list2);
            List<SuppowerStandard> list1=this.genProcessWorkDescList(object.getProcessWorkDesc());
            request.setAttribute("list1", list1);
            List<SuppowerStandard> list3=this.genAcceptConditionList(object.getAcceptCondition());
            request.setAttribute("list3", list3);
            String flag="1";//表示添加
            if(list2.size()>0){
                flag="2";
            }           
            request.setAttribute("flag_hx", flag);
        }
        if(SysTypeUtils.sysType == 1){
            return "nEdit";
        }else{
            return EDIT;
        }
    }


    public List<Suppower> getSuppowerList() {
        return suppowerList;
    }

    public void setSuppowerList(List<Suppower> suppowerList) {
        this.suppowerList = suppowerList;
    }

    public String editInitial() {
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        try {
            object = getEntityClass().newInstance();
            object.clearProperties();
            if(SysTypeUtils.sysType == 1){
                return "nEdit";
            }else{
                return EDIT;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 权力管理，保存权力事项
     */
    @Override
    public String save() {
        String url = null;
        try {

            if (getVersion_bg() != null) {// 编辑
                Suppower suppower = suppowerManager.getObjectById(
                        object.getItemId(), getVersion_bg());
                Suppower bean = new Suppower();
                bean.setDis_detail(suppower.getDis_detail());
                bean.setDis_standard(suppower.getDis_standard());
                //System.out.println(oldValue);
                baseEntityManager.copyObjectNotNullProperty(suppower, object);
                object = suppower;
                if (StringBaseOpt.isNvl(object.getDis_detail())
                        || StringBaseOpt.isNvl(object.getDis_standard())) {
                    if (StringBaseOpt.isNvl(object.getDis_detail())) {
                        object.setDis_detail(bean.getDis_detail());
                    }
                    if (StringBaseOpt.isNvl(object.getDis_standard())) {
                        object.setDis_standard(bean.getDis_standard());
                    }
                }
            } else {// 新增
                object.setBeginTime(new Date(System.currentTimeMillis()));
                object.setVersion(1l);
               
               
            }
            if (processDesc_ != null) {
                try {
                    FileInputStream fis = new FileInputStream(processDesc_);
                    if (fis != null) {
                        byte[] bbuf = null;
                        int len = fis.available();
                        bbuf = new byte[len];
                        fis.read(bbuf);
                        object.setProcessDesc(bbuf);
                        object.setFileName(processDesc_FileName);
                    }
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 内部流程图
            if (inFlowImg_ != null) {
                try {
                    FileInputStream fis = new FileInputStream(inFlowImg_);
                    if (fis != null) {
                        byte[] bbuf = null;
                        int len = fis.available();
                        bbuf = new byte[len];
                        fis.read(bbuf);
                        object.setInFlowImg(bbuf);
                        object.setInFlowImgName(inFlowImg_FileName);
                    }
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 申请表格
            if (applyForm_ != null) {
                try {
                    FileInputStream fis = new FileInputStream(applyForm_);
                    if (fis != null) {
                        byte[] bbuf = null;
                        int len = fis.available();
                        bbuf = new byte[len];
                        fis.read(bbuf);
                        object.setApplyForm(bbuf);
                        object.setFormName(applyForm_FileName);
                    }
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            // 内部流程图
            if (srvDirectoryStuff_ != null) {
                try {
                    FileInputStream fis = new FileInputStream(srvDirectoryStuff_);
                    if (fis != null) {
                        byte[] bbuf = null;
                        int len = fis.available();
                        bbuf = new byte[len];
                        fis.read(bbuf);
                        object.setSrvDirectoryStuff(bbuf);
                        //object.setInFlowImgName(inFlowImg_FileName);
                    }
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 申请表格
            if (applyFormDemo_ != null) {
                try {
                    FileInputStream fis = new FileInputStream(applyFormDemo_);
                    if (fis != null) {
                        byte[] bbuf = null;
                        int len = fis.available();
                        bbuf = new byte[len];
                        fis.read(bbuf);
                        object.setApplyFormDemo(bbuf);
                       // object.setFormName(applyForm_FileName);
                    }
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            object.setLastmodifytime(new Date(System.currentTimeMillis()));
            Suppower s = new Suppower();
            object.setPowerOptInfos(null);
            object.setBeginTime(null);// 不管是新增还是修改，将内容复制过来之后就将启用时间修改为空
            object.setVersion(0l);// 20130521全部只修改0版本的信息
            object.setQlState("w");// 20130521全部只修改0版本的信息
            String risk=this.getStandXml(request);
            object.setRisk(risk);
            String processWorkDesc=this.getProcessWorkDescHtml(request);
            object.setProcessWorkDesc(processWorkDesc);
            
            String acceptCondition=this.getAcceptConditionHtml(request);
            object.setAcceptCondition(acceptCondition);
            s.copy(object);
            suppowerManager.saveSuppower(s);// object：版本号为当前列表的权力信息，s：版本号为0
                                            // 或1（当前版本号是0的时候）的权力信息

            url = "powerbase/supPower!list.do?itemId=" + object.getItemId()
                    + "&itemName=" + object.getItemName() + "&orgId="
                    + object.getOrgId() + "&itemType=" + object.getItemType();
            this.AlertMessage(url);
            return this.list();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return ERROR;
        }
    }
    private Long itemType;
    /**
     * 查询权力事项，供选择窗口使用
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listSupPower() {
    /*    Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        filterMap.put("sParentUnit", sParentUnit);
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        selectPowerList = suppowerManager.listOrgSuppower(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "listSupPower";*/
        
        
      Map<Object, Object> paramMap = request.getParameterMap();
      resetPageParam(paramMap);
      Map<String, Object> filterMap = convertSearchColumn(paramMap);
      Limit limit = ExtremeTableUtils.getLimit(request);
      PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
      selectPowerList = suppowerManager.listOrgSuppower(filterMap,pageDesc);
      totalRows = pageDesc.getTotalRows();
      return "listSupPower";
    }
    /**
     * 查询权力事项，供选择窗口使用
     * @return
     */
    public String listSupPower_CF() {
        this.listSupPower();
      return   "fromcf";
    } 
    
    /**
     * 现场处罚：查询权力事项，供选择窗口使用
     * @return
     */
    public String listSupPower_CF_faci(){
        this.listSupPower();
        String punishobjectid=request.getParameter("punishobjectid");
        request.setAttribute("punishobjectid", punishobjectid);
        return  "listSupPower_CF_faci";        
    }
    
    /**
     * 项目登记：查询权力事项，供选择窗口使用,山东发改委
     * @return
     */
    public String listSupPower_Sd(){
        this.listSupPower();
        return "epowersd";
    }
    /**
     * 查询权力事项，供选择窗口使用
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String Qlfblist() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        filterMap.put("sParentUnit", sParentUnit);
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        String shql = "From Suppower where 1=1 and version=0 and begin_Time is null and ql_State='w' ";
        subPowerList = suppowerManager.listObjects(shql, filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "Qlfblist";
    }

    /**
     * 权力事项历史版本List
     * 
     * @return
     */
    @SuppressWarnings({ "unchecked", "unused" })
    public String SuppowerListOld() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
//        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);

        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        // suppowerList =
        // suppowerManager.getlistSuppowerOld(filterMap,pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "suppowerListOld";
    }

    /**
     * 发布权力事项详细信息
     * 
     * @return
     */
    public String SuppowerQlfbView() {
        object = suppowerManager.getObjectById(object.getItemId(),
                object.getVersion());
        object.setBeginTime(DatetimeOpt.currentUtilDate());
        return "suppowerQlfbView";
    }

    /**
     * 权力管理，保存权力事项
     */
    public String SuppowerQlfbSave() {
        String oldValue = null;
        String url = null;
        Suppower suppower = null;
        Suppower suppower_0 = null;
        Suppower suppower_new = null;
        try {
            suppower = suppowerManager.getSuppowerQlfb(object.getItemId());// 获取需要设置结束时间的版本号,这里面取的是非0版本
            suppower_0 = suppowerManager.getObjectById(object.getItemId(),
                    object.getVersion());// 获取0版本内容
            Long version = 0l;// 默认是0版本号
            if (suppower == null || StringBaseOpt.isNvl(suppower.getItemId())) {
                Date beginTime = object.getBeginTime();
                suppower_0.setLastmodifytime(new Date(System
                        .currentTimeMillis()));
                suppower_0.setQlState("A");// 20130521全部只修改0版本的信息
                suppower_0.setBeginTime(beginTime);
                if(suppower_new==null){
                    suppower_new= new Suppower();
                }
                baseEntityManager.copyObjectNotNullProperty(suppower_new,
                        suppower_0);
                suppower_new.setVersion(version + 1);// 将最大版本号加一
                suppowerManager.saveSuppower(suppower_0, suppower_new);
            } else {
                version = suppower.getVersion();
                oldValue = suppower.display();// 监察操纵日志
                Date beginTime = object.getBeginTime();
                suppower_0.setLastmodifytime(new Date(System
                        .currentTimeMillis()));
                suppower_0.setQlState("A");// 20130521全部只修改0版本的信息
                suppower_0.setBeginTime(beginTime);
                if(suppower_new==null){
                    suppower_new= new Suppower();
                }
                baseEntityManager.copyObjectNotNullProperty(suppower_new,
                        suppower_0);
                suppower_new.setVersion(version + 1);// 将最大版本号加一
                suppowerManager.saveSuppower(suppower_0, suppower_new);
                suppower.setQlState("U");// 设置为已升级版本
                suppower.setEndTime(DatetimeOpt.currentUtilDate());// 设置结束时间
                suppower.setLastmodifytime(new Date(System.currentTimeMillis()));// 设置最后修改时间
                suppowerManager.updateSuppower(suppower);// 将最大版本号的修改为历史版本
            }
            /*
             * sysOptLog.info(new
             * OptLog(OptLog.OptId.ROLE_DEFINITION.getValue(),isNew ?
             * OptLog.OptCode.INSERT.getValue() :
             * OptLog.OptCode.UPDATE.getValue(), (isNew ?
             * OptLog.OptCode.INSERT.getName() :
             * OptLog.OptCode.UPDATE.getName()) + "权力信息 [编号:" +
             * object.getItemId() + "]", oldValue));
             */
            FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Suppowerstatechglog logbean = new Suppowerstatechglog();
            logbean.setChgno(suppowerstatechglogManager
                    .generateNextPunishClassID());
            logbean.setItemId(suppower_new.getItemId());
            logbean.setVersion(suppower_new.getVersion());
            logbean.setChgType(suppower_new.getQlState());
            logbean.setBeginTime(suppower_new.getBeginTime());
            logbean.setRecoder(fUserDetail.getUsercode());
            //suppowerstatechglogManager.saveObject(logbean);
            url = "powerbase/supPower!Qlfblist.do?itemId=" + object.getItemId()
                    + "&itemName=" + object.getItemName() + "&orgId="
                    + object.getOrgId() + "&itemType=" + object.getItemType();

            monitorOptLog.log(((FUserinfo) this.getLoginUser()).getUsercode(),
                    object.getItemId() + "#" + object.getVersion(),
                    suppower_new.display(), oldValue, "9");

            this.AlertMessage(url);

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return ERROR;
        }
    }

    /**
     * 权力变更申请list
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listSupPowerSQ() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        filterMap.put("sParentUnit", sParentUnit);
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        String flag = "sq";
        supPowerWithoutLobList = suppowerManager.listSuppowerWithoutLob(flag,
                filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "listSupPowerSQ";
    }

    /**
     * 权力变更审核list
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listSupPowerSH() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        filterMap.put("sParentUnit", sParentUnit);
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        String flag = "sh";
        supppowerqlbgList = suppowerManager.listSuppowerWithoutLob(flag,
                filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "listSupPowerSH";
    }

    /**
     * 权力变更回复list
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listSupPowerReply() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        filterMap.put("sParentUnit", sParentUnit);
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        String flag = "reply";
        supppowerqlbgList = suppowerManager.listSuppowerWithoutLob(flag,
                filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return "listSupPowerReply";
    }

    /**
     * 查询权力事项，供选择窗口使用
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        filterMap.put("sParentUnit", sParentUnit);
        String begTime = (String)filterMap.get("begTime");
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = dateFormat.format(dateNow);
        if(StringBaseOpt.isNvl(begTime)){
            begTime = dateNowStr;
           
            
        }
        filterMap.put("begTime", begTime);
        filterMap.put("endTime", begTime);
        filterMap.put("NP_not", true);
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        supPowerWithoutLobList = suppowerManager.listPowerWithoutLob(filterMap, pageDesc);// 当权力项有多个版本时，列表显示版本号最大的一条
        totalRows = pageDesc.getTotalRows();
        // sysOptLog.info(new OptLog(OptLog.OptId.SYSTEMS_BUSINESS.getValue(),
        // OptLog.OptCode.QUERY.getValue(), "权力信息列表查询"));
        return LIST;
    }

    /**
     * 权力事项查看列表
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String listCK() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = "";
        if(dept!=null){
            sParentUnit=dept.getUnitcode();
        }else{
            sParentUnit=sysUnitManager.getUnitCode(request.getParameter("s_orgId"));
        }
        filterMap.put("sParentUnit", sParentUnit);
        String begTime = (String)filterMap.get("begTime");
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = dateFormat.format(dateNow);
        if(StringBaseOpt.isNvl(begTime)){
            begTime = dateNowStr;
            
            
        }
        filterMap.put("begTime", begTime);
        filterMap.put("endTime", begTime);
        filterMap.put("NP_not", true);
       
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        supPowerWithoutLobList = suppowerManager.listPowerWithoutLob(filterMap, pageDesc);// 当权力项有多个版本时，列表显示版本号最大的一条
        totalRows = pageDesc.getTotalRows();
        object.setQlState(object.getQlState());
        return "listCK";
    }

    @SuppressWarnings("unchecked")
    public String contrastlist() {
        Map<Object, Object> paramMap = request.getParameterMap();
        //System.out.println(request.getParameter("s_qlState"));
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        filterMap.put("sParentUnit", sParentUnit);
        //String begTime = (String)filterMap.get("begTime");
        //Date dateNow = new Date();
       // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       // String dateNowStr = dateFormat.format(dateNow);
//        if(StringBaseOpt.isNvl(begTime)){
//            begTime = dateNowStr;
//            
//            
//        }
//        filterMap.put("begTime", begTime);
//        filterMap.put("endTime", begTime);
        filterMap.put("NP_not", true);
       
        unitList = sysUnitManager.getAllSubUnits(sParentUnit);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        supPowerWithoutLobList = suppowerManager.listPowerWithoutLob(filterMap, pageDesc);// 当权力项有多个版本时，列表显示版本号最大的一条
        totalRows = pageDesc.getTotalRows();
        object.setQlState(object.getQlState());
        return "contrastlist";
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String editContrast(){
        String itemId = object.getItemId();
        String[] itemIds = itemId.split(",");
        List powerList = new ArrayList();
        Object[] temp = new Object[itemIds.length + 1];
        Object[] temp1 = new Object[itemIds.length + 1];
        Object[] temp2 = new Object[itemIds.length + 1];
        Object[] temp3 = new Object[itemIds.length + 1];
        Object[] temp4 = new Object[itemIds.length + 1];
        Object[] temp5 = new Object[itemIds.length + 1];
        Object[] temp6 = new Object[itemIds.length + 1];
        Object[] temp7 = new Object[itemIds.length + 1];
        Object[] temp8 = new Object[itemIds.length + 1];
        Object[] temp9 = new Object[itemIds.length + 1];
        Object[] temp10 = new Object[itemIds.length + 1];
        Object[] temp11 = new Object[itemIds.length + 1];
        Object[] temp12 = new Object[itemIds.length + 1];
        Object[] temp13 = new Object[itemIds.length + 1];
        Object[] temp14 = new Object[itemIds.length + 1];
        Object[] temp15 = new Object[itemIds.length + 1];
        Object[] temp16 = new Object[itemIds.length + 1];
        Object[] temp17 = new Object[itemIds.length + 1];
        Object[] temp18 = new Object[itemIds.length + 1];
        Object[] temp19 = new Object[itemIds.length + 1];
        Object[] temp20 = new Object[itemIds.length + 1];
        Object[] temp21 = new Object[itemIds.length + 1];
        Object[] temp22 = new Object[itemIds.length + 1];
        Object[] temp23 = new Object[itemIds.length + 1];
        Object[] temp24 = new Object[itemIds.length + 1];
        Object[] temp25 = new Object[itemIds.length + 1];
        Object[] temp26 = new Object[itemIds.length + 1];
        Object[] temp27 = new Object[itemIds.length + 1];
        Object[] temp28 = new Object[itemIds.length + 1];
        Object[] temp29 = new Object[itemIds.length + 1];
        Object[] temp30 = new Object[itemIds.length + 1];
        Object[] temp31 = new Object[itemIds.length + 1];
        Object[] temp32 = new Object[itemIds.length + 1];
        Object[] temp33 = new Object[itemIds.length + 1];
        Object[] temp34 = new Object[itemIds.length + 1];
        Object[] temp35 = new Object[itemIds.length + 1];
        Object[] temp36 = new Object[itemIds.length + 1];
        Object[] temp37 = new Object[itemIds.length + 1];
        Object[] temp38 = new Object[itemIds.length + 1];
        Object[] temp39 = new Object[itemIds.length + 1];
        Object[] temp40 = new Object[itemIds.length + 1];
        Object[] temp41 = new Object[itemIds.length + 1];
        Object[] temp42 = new Object[itemIds.length + 1];
        Object[] temp43 = new Object[itemIds.length + 1];
        Object[] temp44 = new Object[itemIds.length + 1];
        Object[] temp45 = new Object[itemIds.length + 1];
        Object[] temp46 = new Object[itemIds.length + 1];
        for(int i = 0; i < itemIds.length; i++){
            
            String item_id = itemIds[i].split(";")[0];
            Long version = Long.parseLong(itemIds[i].split(";")[1]);
            Suppower suppower = suppowerManager.getObjectById(item_id, version);
            if(i == 0){
                temp[i] = "权力编码";
                temp[i+1] = suppower.getItemId();
            }else{
                temp[i+1] = suppower.getItemId();
            }
            if(i == 0){
                temp1[i] = "权力名称";
                temp1[i+1] = suppower.getItemName();
            }else{
                temp1[i+1] = suppower.getItemName();
            }
            if(i == 0){
                temp2[i] = "版本号";
                temp2[i+1] = suppower.getVersion();
            }else{
                temp2[i+1] = suppower.getVersion();
            }
            
            if(i == 0){
                temp3[i] = "权力外网公示编码";
                temp3[i+1] = suppower.getOutItemId();
            }else{
                temp3[i+1] = suppower.getOutItemId();
            }
            
            if(i == 0){
                temp4[i] = "启用时间";
                temp4[i+1] = suppower.getBeginTime();
            }else{
                temp4[i+1] = suppower.getBeginTime();
            }
            
            if(i == 0){
                temp5[i] = "本版本停用时间";
                temp5[i+1] = suppower.getEndTime();
            }else{
                temp5[i+1] = suppower.getEndTime();
            }
            
            if(i == 0){
                temp6[i] = "权力类型";
                //FDatadictionary fDate = CodeRepositoryUtil.getDataPiece("punishType", infoxmlvalue.getPunishtypeid());
                temp6[i+1] = CodeRepositoryUtil.getDataPiece("ITEM_TYPE",suppower.getItemType()).getDatavalue();
            }else{
                temp6[i+1] = CodeRepositoryUtil.getDataPiece("ITEM_TYPE",suppower.getItemType()).getDatavalue();
            }
            
            if(i == 0){
                temp7[i] = "行使部门";
                temp7[i+1] = CodeRepositoryUtil.getUnitName(suppower.getOrgId());
            }else{
                temp7[i+1] = CodeRepositoryUtil.getUnitName(suppower.getOrgId());
            }
            
            if(i == 0){
                temp8[i] = "承诺时限";
                temp8[i+1] = CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getPromiseType()) == null ? "" :CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getPromiseType()).getDatavalue();
                if(suppower.getTimeLimit() == null){
                    temp8[i+1] = "";
                }else
                    temp8[i+1] = suppower.getTimeLimit() + (String)temp8[i+1];
            }else{
               // temp8[i+1] = suppower.getTimeLimit() + CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getPromiseType()).getDatavalue();
                temp8[i+1] = CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getPromiseType()) == null ? "" :CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getPromiseType()).getDatavalue();
                if(suppower.getTimeLimit() == null){
                    temp8[i+1] = "";
                }else
                temp8[i+1] = suppower.getTimeLimit() + (String)temp8[i+1];
            }
            
            if(i == 0){
                temp9[i] = "法定时限";
                temp9[i+1] = CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getAnticipateType()) == null ? "" :CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getAnticipateType()).getDatavalue();
                if(suppower.getLegalTimeLimit() == null){
                    temp9[i+1] = "";
                }else
                    temp9[i+1] = suppower.getLegalTimeLimit() + (String)temp9[i+1];
            }else{
                temp9[i+1] = CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getAnticipateType()) == null ? "" :CodeRepositoryUtil.getDataPiece("Promise_Type",suppower.getAnticipateType()).getDatavalue();
                if(suppower.getLegalTimeLimit() == null){
                    temp9[i+1] = "";
                }else
                    temp9[i+1] = suppower.getLegalTimeLimit() + (String)temp9[i+1];
            }
            
            if(i == 0){
                temp10[i] = "是否网上受理";
                temp10[i+1] = CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIsNetwork()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIsNetwork()).getDatavalue();
            }else{
                temp10[i+1] = CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIsNetwork()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIsNetwork()).getDatavalue();
            }
            
            if(i == 0){
                temp11[i] = "是否公示";
                temp11[i+1] = CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIsFormula()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIsFormula()).getDatavalue();
            }else{
                temp11[i+1] = CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIsFormula()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIsFormula()).getDatavalue();
            }
            
            if(i == 0){
                temp12[i] = "是否收费";
                temp12[i+1] = CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIscharge()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIscharge()).getDatavalue();
            }else{
                temp12[i+1] = CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIscharge()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getIscharge()).getDatavalue();
            }
            if(i == 0){
                temp13[i] = "收费依据";
                temp13[i+1] = suppower.getChargeBasis();
            }else{
                temp13[i+1] = suppower.getChargeBasis();
            }
            if(i == 0){
                temp3[i] = "收费标准";
                temp3[i+1] = suppower.getChargeStandard();
            }else{
                temp3[i+1] = suppower.getChargeStandard();
            }
            if(i == 0){
                temp14[i] = "权力归类";
                temp14[i+1] = CodeRepositoryUtil.getDataPiece("itemClass",suppower.getItemClass()) == null ? "" :CodeRepositoryUtil.getDataPiece("itemClass",suppower.getItemClass()).getDatavalue();
            }else{
                temp14[i+1] = CodeRepositoryUtil.getDataPiece("itemClass",suppower.getItemClass()) == null ? "" :CodeRepositoryUtil.getDataPiece("itemClass",suppower.getItemClass()).getDatavalue();
            }
            if(i == 0){
                temp15[i] = "联系电话";
                temp15[i+1] = suppower.getPhone();
            }else{
                temp15[i+1] = suppower.getPhone();
            }
            if(i == 0){
                temp16[i] = "监督电话";
                temp16[i+1] = suppower.getMonitorPhone();
            }else{
                temp16[i+1] = suppower.getMonitorPhone();
            }
            if(i == 0){
                temp17[i] = "办理地点";
                temp17[i+1] = suppower.getAddress();
            }else{
                temp17[i+1] = suppower.getAddress();
            }
            if(i == 0){
                temp18[i] = "办理部门";
                temp18[i+1] = suppower.getTransactDepname();
            }else{
                temp18[i+1] = suppower.getTransactDepname();
            }
            if(i == 0){
                temp19[i] = "实施主体";
                temp19[i+1] = CodeRepositoryUtil.getUnitName(suppower.getQlDepid());
            }else{
                temp19[i+1] = CodeRepositoryUtil.getUnitName(suppower.getQlDepid());
            }
            if(i == 0){
                temp20[i] = "实施主体性质";
                temp20[i+1] = CodeRepositoryUtil.getDataPiece("QL_DepState",suppower.getQlDepstate()) == null ? "" :CodeRepositoryUtil.getDataPiece("QL_DepState",suppower.getQlDepstate()).getDatavalue();
            }else{
                temp20[i+1] = CodeRepositoryUtil.getDataPiece("QL_DepState",suppower.getQlDepstate()) == null ? "" :CodeRepositoryUtil.getDataPiece("QL_DepState",suppower.getQlDepstate()).getDatavalue();
            }
            if(i == 0){
                temp21[i] = "委托机关";
                temp21[i+1] = suppower.getEntrustName();
            }else{
                temp21[i+1] = suppower.getEntrustName();
            }
            if(i == 0){
                temp22[i] = "是否关联项目信息";
                temp22[i+1] = CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getHasItem()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getHasItem()).getDatavalue();
            }else{
                temp22[i+1] = CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getHasItem()) == null ? "" :CodeRepositoryUtil.getDataPiece("IS_BOOLEAN",suppower.getHasItem()).getDatavalue();
            }
            if(i == 0){
                temp23[i] = "受理机构";
                temp23[i+1] = suppower.getAcceptDepname();
            }else{
                temp23[i+1] = suppower.getAcceptDepname();
            }
            if(i == 0){
                temp24[i] = "适用的申请主体";
                if(StringBaseOpt.isNvl(suppower.getCompatibleType())){
                    temp24[i+1] = "";
                }else{
                    temp24[i+1] = "1".equals(suppower.getCompatibleType())? "个人":"机构";
                }
            }else{
                if(StringBaseOpt.isNvl(suppower.getCompatibleType())){
                    temp24[i+1] = "";
                }else{
                    temp24[i+1] = "1".equals(suppower.getCompatibleType())? "个人":"机构";
                }
            }
            if(i == 0){
                temp25[i] = "受理时间";
                temp25[i+1] = suppower.getAcceptTimeScope();
            }else{
                temp25[i+1] = suppower.getAcceptTimeScope();
            }
            if(i == 0){
                temp26[i] = "办理状态查询(url)";
                temp26[i+1] = suppower.getStateQueryUrl();
            }else{
                temp26[i+1] = suppower.getStateQueryUrl();
            }
            if(i == 0){
                temp27[i] = "结果查询(url)";
                temp27[i+1] = suppower.getResultQueryUrl();
            }else{
                temp27[i+1] = suppower.getResultQueryUrl();
            }
            if(i == 0){
                temp28[i] = "申请材料";
                temp28[i+1] = suppower.getApplyMaterial();
            }else{
                temp28[i+1] = suppower.getApplyMaterial();
            }
            if(i == 0){
                temp29[i] = "获取核准的条件";
                temp29[i+1] = suppower.getAcceptCondition();
            }else{
                temp29[i+1] = suppower.getAcceptCondition();
            }
            if(i == 0){
               
                temp30[i] = "程序及相关工作";
                temp30[i+1] = suppower.getProcessWorkDesc();
            }else{
                temp30[i+1] = suppower.getProcessWorkDesc();
            }
            if(i == 0){
                temp31[i] = "主要监管措施";
                temp31[i+1] = suppower.getRegulatoryMeasures();
            }else{
                temp31[i+1] = suppower.getRegulatoryMeasures();
            }
            if(i == 0){
                temp32[i] = "法律依据";
                temp32[i+1] = suppower.getPunishbasis();
            }else{
                temp32[i+1] = suppower.getPunishbasis();
            }
            if(i == 0){
                temp33[i] = "处罚种类";
                temp33[i+1] = suppower.getPunishClass();
            }else{
                temp33[i+1] = suppower.getPunishClass();
            }
            if(i == 0){
                temp34[i] = "自由裁量";
                temp34[i+1] = suppower.getFreeJudge();
            }else{
                temp34[i+1] = suppower.getFreeJudge();
            }
            if(i == 0){
                temp35[i] = "征收标准";
                temp35[i+1] = suppower.getLevyUpon();
            }else{
                temp35[i+1] = suppower.getLevyUpon();
            }
            if(i == 0){
                temp36[i] = "受理条件";
                temp36[i+1] = suppower.getCondition();
            }else{
                temp36[i+1] = suppower.getCondition();
            }
            if(i == 0){
                temp37[i] = "外网在线办理链接";
                temp37[i+1] = suppower.getAcceptLink();
            }else{
                temp37[i+1] = suppower.getAcceptLink();
            }
            if(i == 0){
                temp38[i] = "常见问题";
                temp38[i+1] = suppower.getQuestion();
            }else{
                temp38[i+1] = suppower.getQuestion();
            }
            if(i == 0){
                temp39[i] = "权力状态";
                temp39[i+1] = CodeRepositoryUtil.getDataPiece("QL_State",suppower.getQlState()) == null ? "" :CodeRepositoryUtil.getDataPiece("QL_State",suppower.getQlState()).getDatavalue();
            }else{
                temp39[i+1] = CodeRepositoryUtil.getDataPiece("QL_State",suppower.getQlState()) == null ? "" :CodeRepositoryUtil.getDataPiece("QL_State",suppower.getQlState()).getDatavalue();
            }
            if(i == 0){
                temp40[i] = "最后更新时间";
                temp40[i+1] = suppower.getLastmodifytime();
            }else{
                temp40[i+1] = suppower.getLastmodifytime();
            }
            
            if(i == 0){
                temp41[i] = "内部流程信息";
                temp41[i+1] = "<input type=\"button\" name=\"btnblc\" value=\"流程查看\" onclick=\"openFlow();\" class=\"btn\" /><input type=\"hidden\" id=\"xmlDate\" value=\""+suppower.getInFlowXml()+"\" />";
            }else{
                temp41[i+1] = "<input type=\"button\" name=\"btnblc\" value=\"流程查看\" onclick=\"openFlow();\" class=\"btn\" /><input type=\"hidden\" id=\"xmlDate\" value=\""+suppower.getInFlowXml()+"\" />";
            }
            
            if(i == 0){
                temp42[i] = "服务指南";
                temp42[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=srvDirectoryStuff_'>服务指南</a>";
            }else{
                temp42[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=srvDirectoryStuff_'>服务指南</a>";
            }
            if(i == 0){
                temp43[i] = "申请表格";
                temp43[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=applyForm_'>申请表格</a>";
            }else{
                temp43[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=applyForm_'>申请表格</a>";
            }
            if(i == 0){
                temp44[i] = "申请表格范表";
                temp44[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=applyFormDemo_'>申请表格范表</a>";
            }else{
                temp44[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=applyFormDemo_'>申请表格范表</a>";
            }
            if(i == 0){
                temp45[i] = "内部流程图";
                temp45[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=inFlowImg_'>内部流程图</a>";
            }else{
                temp45[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=inFlowImg_'>内部流程图</a>";
            }
            if(i == 0){
                temp46[i] = "办件流程(外部流程图)";
                temp46[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=processDesc_'>办件流程(外部流程图</a>";
            }else{
                temp46[i+1] = "<a href='supPower!downloadStuff.do?itemId="+suppower.getItemId()+"&version="+suppower.getVersion()+"&fileType=processDesc_'>办件流程(外部流程图</a>";
            }
        }
        powerList.add(temp);
        powerList.add(temp1);
        powerList.add(temp2);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp3);
        powerList.add(temp4);
        powerList.add(temp5);
        powerList.add(temp6);
        powerList.add(temp7);
        powerList.add(temp8);
        powerList.add(temp9);
        powerList.add(temp10);
        powerList.add(temp11);
        powerList.add(temp12);
        powerList.add(temp13);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp14);
        powerList.add(temp15);
        powerList.add(temp16);
        powerList.add(temp17);
        powerList.add(temp18);
        powerList.add(temp19);
        powerList.add(temp20);
        powerList.add(temp21);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp22);
        powerList.add(temp23);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp24);
        powerList.add(temp25);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp26);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp27);
        powerList.add(temp28);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp29);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp30);
       
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp31);
        powerList.add(temp42);
        powerList.add(temp32);
        powerList.add(temp33);
        powerList.add(temp34);
        powerList.add(temp35);
        powerList.add(temp43);
        if(SysTypeUtils.sysType == 1)
            powerList.add(temp44);
        powerList.add(temp45);
        powerList.add(temp41);
        powerList.add(temp36);
        powerList.add(temp46);
        powerList.add(temp37);
        powerList.add(temp38);
        powerList.add(temp39);
        powerList.add(temp40);
        
        request.setAttribute("powerList", powerList);
        return "editContrast";
    }
    /**
     * 根据权利状态查询列表
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String viewVersion() {
        //System.out.println("kkkkkk:" + object.getItemId());
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("itemId", object.getItemId());
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        totalRows = pageDesc.getTotalRows();
        return "viewVersion";
    }

    /**
     * 材料下载使用
     */
    private InputStream stuffStream;

    public InputStream getStuffStream() {
        return stuffStream;
    }

    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }

    /**
     * 查看附件信息
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    public String downloadStuff() throws IOException {
        Suppower bean = suppowerManager.getObjectById(object.getCid());
        String fileName = "文件查看";
        byte[] bt = null;
        if ("processDesc_".equals(fileType)) {
            fileName = bean.getFileName();
            bt = bean.getProcessDesc();
        } else if ("inFlowImg_".equals(fileType)) {
            fileName = bean.getInFlowImgName();
            bt = bean.getInFlowImg();
        } else if ("applyForm_".equals(fileType)) {
            fileName = bean.getFormName();
            bt = bean.getApplyForm();
        }else if ("applyFormDemo_".equals(fileType)) {
            fileName = "申请表格范表.doc";
            bt = bean.getApplyFormDemo();
        } else if ("srvDirectoryStuff_".equals(fileType)) {
            fileName = "服务指南.doc";
            bt = bean.getSrvDirectoryStuff();
        }

        try {
            if (bt != null) {
                setStuffStream(new ByteArrayInputStream(bt));
            } else {
                // saveError("没有电子档！");
                this.postAlertMessage("操作失败，没有电子档！", response);
                return null;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        this.setFilename(new String(fileName.getBytes("GBK"), "ISO8859-1"));
        return "download";
    }

    @SuppressWarnings("rawtypes")
    public String viewzycl() {
        Suppower bean = suppowerManager.getObjectById(object.getCid());
        String xml = null;
        if (bean != null) {
            xml = bean.getDis_detail();
        }

        List list = suppowerManager.getzycvie("punishType");// 自由裁量处罚种类
        List seqtypelist = suppowerManager.getzycvie("seq_type");// 裁量基准
        List seqBaseUnitList = suppowerManager
                .getseqlowlimitunit("seqBaseUnit");// 裁量基准
        List list1 = suppowerManager.xmlDISCRETIONList(xml, "", "");
        List list2 = suppowerManager.xmlStandardList(xml, "", "");
        request.setAttribute("list1", list1);
        request.setAttribute("list2", list2);
        request.setAttribute("list", list);
        request.setAttribute("seqtypelist", seqtypelist);
        request.setAttribute("seqBaseUnitList", seqBaseUnitList);
        return "viewzycl";
    }

    /**
     * 查看处罚种类
     * 
     * @return
     */

    @SuppressWarnings("rawtypes")
    public String viewcfcx() {
        Suppower bean = suppowerManager.getObjectById(object.getCid());

        String xml = null;
        if (bean != null) {
            xml = bean.getDis_standard();
        }
        List list = suppowerManager.getzycvie("punishType");// 自由裁量处罚种类
        List seqtypelist = suppowerManager.getzycvie("seq_type");// 裁量基准
        List seqBaseUnitList = suppowerManager
                .getseqlowlimitunit("seqBaseUnit");// 裁量基准
        List list2 = suppowerManager.genxmlStandardList(xml, "", "");
        request.setAttribute("list2", list2);
        request.setAttribute("list", list);
        request.setAttribute("seqtypelist", seqtypelist);
        request.setAttribute("seqBaseUnitList", seqBaseUnitList);

        return "viewcfcx";
    }

    @SuppressWarnings("rawtypes")
    public String editzycl() {
        String  xml = object.getDis_detail();
        Suppower bean = suppowerManager.getObjectById(object.getCid());
      if (bean != null) {
          if(StringBaseOpt.isNvl(xml) ){
              xml = bean.getDis_detail();
          }
      }
        List list = suppowerManager.getzycvie("punishType");// 自由裁量处罚种类
        List seqtypelist = suppowerManager.getzycvie("seq_type");// 裁量基准
        List seqBaseUnitList = suppowerManager
                .getseqlowlimitunit("seqBaseUnit");// 裁量基准

        List list1 = suppowerManager.xmlDISCRETIONList(xml, "", "");
        List list2 = suppowerManager.xmlStandardList(xml, "", "");

        // List list2 = manager.getsuppowerDis(item_id);//获取处罚办件自由裁量数据
        if (list1.size() > 0) {
            request.setAttribute("flagcyz", "1");
        } else {
            request.setAttribute("flagcyz", "2");
        }
        if (list2.size() > 0) {
            request.setAttribute("flagcyzdis", "1");
        } else {
            request.setAttribute("flagcyzdis", "2");
        }
        request.setAttribute("list1", list1);
        request.setAttribute("list2", list2);
        request.setAttribute("list", list);
        request.setAttribute("seqtypelist", seqtypelist);
        request.setAttribute("seqBaseUnitList", seqBaseUnitList);

        return "editzycl";
    }

    @SuppressWarnings("rawtypes")
    public String editcfcx() {
        String  xml = object.getDis_standard();
        Suppower bean = suppowerManager.getObjectById(object.getCid());
      if (bean != null) {
          if(StringBaseOpt.isNvl(xml) ){
              xml = bean.getDis_standard();
          }
      }
        List list = suppowerManager.getzycvie("punishType");// 自由裁量处罚种类
        List seqtypelist = suppowerManager.getzycvie("seq_type");// 裁量基准
        List seqBaseUnitList = suppowerManager
                .getseqlowlimitunit("seqBaseUnit");// 裁量基准

        List list2 = suppowerManager.genxmlStandardList(xml, "", "");
        // List list2 = manager.getZyclStand(item_id);//获取处罚职权自由裁量标准表信息
        if (list2.size() > 0) {
            request.setAttribute("flagcyzdis", "1");
        } else {
            request.setAttribute("flagcyzdis", "2");
        }
        request.setAttribute("list2", list2);
        request.setAttribute("list", list);
        request.setAttribute("seqtypelist", seqtypelist);
        request.setAttribute("seqBaseUnitList", seqBaseUnitList);

        return "editcfcx";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String genStandXml() throws IOException {
        String seq_id[] = request.getParameter("seq_id").split(",");
        String seq_type[] = request.getParameter("seq_type").split(",");
        String eq_toplimit[] = request.getParameter("eq_toplimit").split(",");
        String seq_toplimit_unit[] = request.getParameter("seq_toplimit_unit")
                .split(",");
        String seq_lowlimit[] = request.getParameter("seq_lowlimit").split(",");
        String seq_lowlimit_unit[] = request.getParameter("seq_lowlimit_unit")
                .split(",");
        String seq_base_name[] = request.getParameter("seq_base_name").split(
                ",");
        String seq_base_topmul[] = request.getParameter("seq_base_topmul")
                .split(",");
        String seq_base_lowmul[] = request.getParameter("seq_base_lowmul")
                .split(",");
        String seq_base_unit[] = request.getParameter("seq_base_unit").split(
                ",");
        String seq_remark[] = request.getParameter("seq_remark").split(",");

        PcpunishStandard standinfo = new PcpunishStandard();
        // manager.delStandardItemID(info.getItem_id());
        standinfo.setItemId(standinfo.getItemId());
        List standardList = new ArrayList();
        for (int i = 0; i < seq_id.length; i++) {
            standinfo = new PcpunishStandard();
            if (!seq_id[i].equals("")) {

                standinfo.setPunishtypeid(seq_id[i]);
                String seq_typei = seq_type[i];
                if (!StringBaseOpt.isNvl(seq_typei)) {
                    seq_typei = seq_typei.trim();
                }

                standinfo.setPunishtype(StringBaseOpt.isNvl(seq_typei) ? null
                        : Long.parseLong(seq_typei));
                standinfo.setToplimit(eq_toplimit[i]);
                String lowlimit_unit = "";
                String toplimit_unit = "";
                String base_name = "";
                String base_unit = "";
                String remark = "";

                toplimit_unit = seq_toplimit_unit[i];
                lowlimit_unit = seq_lowlimit_unit[i];
                base_name = seq_base_name[i];
                base_unit = seq_base_unit[i];
                remark = seq_remark[i];
                //
                standinfo.setToplimitUnit(toplimit_unit);
                standinfo.setLowlimit(seq_lowlimit[i]);
                standinfo.setLowlimitUnit(lowlimit_unit);
                standinfo.setBaseName(base_name);
                standinfo.setBaseToplimit(seq_base_topmul[i]);
                standinfo.setBaseLowlimit(seq_base_lowmul[i]);
                standinfo.setBaseUnit(base_unit);
                standinfo.setRemark(remark);
                standinfo.setIsinuse(Long.parseLong("1"));
                standardList.add(standinfo);

            }
        }
        String xml = "";
       /* if(standardList.size()>0){
          
           
        } */
        // 有样式(缩进)的写出
        // 生产XML
        ZycXmlElement zycXml = new ZycXmlElement();
       // String xml = "";
       // response.setContentType("text/html;   charset=GBK");

        try {
            xml = zycXml.buildStandXMLDoc(standardList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String genDiscretionaryXml() {
        Pcfreeumpiredegree info =null;
        String stand_id[] = request.getParameter("stand_id").split(",");
        String stand_code[] = request.getParameter("stand_code").split(",");
        String seq_id[] = request.getParameter("seq_id").split(",");
        String seq_type[] = request.getParameter("seq_type").split(",");
        String eq_toplimit[] = request.getParameter("eq_toplimit").split(",");
        String seq_toplimit_unit[] = request.getParameter("seq_toplimit_unit")
                .split(",");
        String seq_lowlimit[] = request.getParameter("seq_lowlimit").split(",");
        String seq_lowlimit_unit[] = request.getParameter("seq_lowlimit_unit")
                .split(",");
        String seq_base_name[] = request.getParameter("seq_base_name").split(
                ",");
        String seq_base_topmul[] = request.getParameter("seq_base_topmul")
                .split(",");
        String seq_base_lowmul[] = request.getParameter("seq_base_lowmul")
                .split(",");
        String seq_base_unit[] = request.getParameter("seq_base_unit").split(
                ",");
        String buttable[] = request.getParameter("buttable").split(",");
        String standIdbtn[] = request.getParameter("standIdbtn").split(",");

        Pcfreeumpiretype supinfower = null;
        List standList = new ArrayList();
        List seqList = new ArrayList();
        for (int i = 1; i < stand_id.length; i++) {
            info = new Pcfreeumpiredegree();
            //
            info.setStandardNo(stand_id[i]);
            info.setPunishfactgrade(stand_code[i]);

            info.setThisPart(standIdbtn[i]);

            standList.add(info);
        }
        for (int j = 1; j < seq_id.length; j++) {
            supinfower = new Pcfreeumpiretype();
            supinfower.setPunishtypeid(seq_id[j]);
            String seq_typei = seq_type[j];
            if (!StringBaseOpt.isNvl(seq_typei)) {
                seq_typei = seq_typei.trim();
            }
            String lowlimit_unit = "";
            String toplimit_unit = "";
            String base_name = "";
            String base_unit = "";
            // String remark = "";

            toplimit_unit = seq_toplimit_unit[j];
            lowlimit_unit = seq_lowlimit_unit[j];
            base_name = seq_base_name[j];
            base_unit = seq_base_unit[j];

            supinfower.setPunishtype(StringBaseOpt.isNvl(seq_typei) ? null
                    : Long.parseLong(seq_typei));
            supinfower.setToplimit(eq_toplimit[j]);
            supinfower.setToplimitUnit(toplimit_unit);
            supinfower.setLowlimit(seq_lowlimit[j]);
            supinfower.setLowlimitUnit(lowlimit_unit);
            supinfower.setBaseName(base_name);
            supinfower.setBaseToplimit(seq_base_topmul[j]);
            supinfower.setBaseLowlimit(seq_base_lowmul[j]);
            supinfower.setBaseUnit(base_unit);

            supinfower.setSupPart(buttable[j]);
            seqList.add(supinfower);
        }
        // 生产XML
        ZycXmlElement zycXml = new ZycXmlElement();
        String xml = "";
       // response.setContentType("text/html;   charset=GBK"); // 这里是否可以设置为UTF-8

        try {
            xml = zycXml.BuildXMLDoc(standList, seqList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteStuff() {
        Suppower bean = suppowerManager.getObjectById(object.getCid());
        if ("processDesc_".equals(fileType)) {
            bean.setFileName("");
            bean.setProcessDesc(null);
        } else if ("inFlowImg_".equals(fileType)) {
            bean.setInFlowImgName("");
            bean.setInFlowImg(null);
        } else if ("applyForm_".equals(fileType)) {
            bean.setFormName("");
            bean.setApplyForm(null);
        }else if ("applyFormDemo_".equals(fileType)) {          
            bean.setApplyFormDemo(null);
        } else if ("srvDirectoryStuff_".equals(fileType)) {
            bean.setSrvDirectoryStuff(null);
        }

        suppowerManager.saveObject(bean);
        return this.edit();
    }

    /**
     * 弹出提示信息
     * 
     * @param msg
     * @param response
     */
    protected void postAlertMessage(String msg, HttpServletResponse response) {
        String alertCoding = "UTF-8";
        ServletOutputStream sos;
        String str = "<script language=\"JavaScript\""
                + " type=\"text/JavaScript\" charset=\"" + alertCoding + "\">"
                + "javascript:alert('" + msg + "');history.back(-1);"
                + " </script>";
        response.setContentType("text/html; charset=" + alertCoding);
        try {
            sos = response.getOutputStream();
//            int strSize = (int) str.length();
            byte[] b = str.getBytes();
            sos.write(b);
            sos.flush();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * 权力管理，修改权力状态
     * 
     * @return
     */
    public String upDateQlState() {
        try {
            FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Suppower bean = suppowerManager.getObjectById(object.getItemId(),
                    object.getVersion());
            Suppowerstatechglog logbean = new Suppowerstatechglog();
//            logbean = suppowerstatechglogManager.getObjectByIdAndVersion(
//                    bean.getItemId(), bean.getVersion());

            bean.setQlState(object.getQlState());
            if (bean.getQlState().equals("A")) {
                bean.setBeginTime(DatetimeOpt.currentUtilDate());
                bean.setEndTime(null);
               // logbean = new Suppowerstatechglog();
                logbean.setChgno(suppowerstatechglogManager
                        .generateNextPunishClassID());

            }
            if (bean.getQlState().equals("T") || bean.getQlState().equals("X")) {
                logbean.setChgno(suppowerstatechglogManager
                        .generateNextPunishClassID());
                bean.setEndTime(DatetimeOpt.currentUtilDate());
            }
            logbean.setItemId(bean.getItemId());
            logbean.setVersion(bean.getVersion());
            logbean.setChgType(bean.getQlState());
            logbean.setBeginTime(bean.getBeginTime());
            //logbean.setEndTime(bean.getEndTime());
            logbean.setRecoder(fUserDetail.getUsercode());
            suppowerManager.saveSupPownerAndState(bean, logbean);
            return this.list();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            this.saveError(e.getMessage());
            return this.list();
        }

    }

    /**
     * 权力变更使用的(挂起和废止)
     * 
     * @return
     */
    public String updateQlbgState() {
        try {
            // 下面这个是获取用户信息类
            FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Suppower suppower = suppowerManager.getObjectById(
                    object.getItemId(), Long.valueOf("0"));// 获取0版本的
            Suppowerchglog logBean = new Suppowerchglog();
            logBean.setRequester(fUserDetail.getUsercode());// 申请者

            logBean.setVersion(object.getVersion());// 默认只修改0版本
            logBean.setItemId(object.getItemId());// 这是通过页面上传过来的值
            logBean.setRequestTime(DatetimeOpt.currentUtilDate());// 申请时间是系统时间
            if ("T".equals(object.getQlState())) {
                logBean.setChgType("gq");// 这是通过页面上传过来的值
                logBean.setChgReason("申请挂起权力");
                logBean.setChgContent("申请挂起权力");
            }
            if ("X".equals(object.getQlState())) {
                logBean.setChgType("fz");// 这是通过页面上传过来的值
                logBean.setChgReason("申请废止权力");
                logBean.setChgContent("申请废止权力");
            }
            // 变更版本号不用修改
            suppower.setQlState(object.getQlState());
            suppowerManager.saveObject(suppower);
            logBean.setChangeId(suppowerchglogManager.genNextChangeId());
            suppowerchglogManager.saveSuppowerchglog(logBean);
            return this.listSupPowerSQ();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            this.saveError(e.getMessage());
            return this.listSupPowerSQ();
        }
    }

    /**
     * 权力变更(启用)
     * 
     * @return
     */
    public String upDateQlbgQyState() {
        try {
            // 下面这个是获取用户信息类
            FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Suppower suppower = suppowerManager.getObjectById(
                    object.getItemId(), Long.valueOf("0"));// 获取0版本的
            Suppowerchglog logBean = new Suppowerchglog();
            logBean.setRequester(fUserDetail.getUsercode());// 申请者
            logBean.setVersion(object.getVersion());// 默认只修改0版本
            logBean.setItemId(object.getItemId());// 这是通过页面上传过来的值
            logBean.setRequestTime(DatetimeOpt.currentUtilDate());// 申请时间是系统时间
            logBean.setChgType("qy");// 这是通过页面上传过来的值
            logBean.setChgReason("申请启用权力");
            logBean.setChgContent("申请启用权力");
            // 变更版本号不用修改
            suppower.setQlState(object.getQlState());
            suppowerManager.saveObject(suppower);
            logBean.setChangeId(suppowerchglogManager.genNextChangeId());
            suppowerchglogManager.saveSuppowerchglog(logBean);
            return this.listSupPowerSQ();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            this.saveError(e.getMessage());
            return this.listSupPowerSQ();
        }
    }

    /**
     * 查看权力事项详细信息
     * 
     * @return
     */
    public String SuppowerQlbgView() {
        this.view();
        return "SuppowerQlbgView";
    }

    /**
     * 权力变更申请修改权力事项信息
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    List versionList = new ArrayList();
    Long version_bg;

    public Long getVersion_bg() {
        return version_bg;
    }

    @SuppressWarnings("rawtypes")
    public List getVersionList() {
        return versionList;
    }

    @SuppressWarnings("rawtypes")
    public void setVersionList(List versionList) {
        this.versionList = versionList;
    }

    public String SuppowerQlbgSqXg() {
        if (version_bg != null) {
            object.setVersion(version_bg);
            setVersion_bg(version_bg);
        }
        this.edit();
        suppowerList = suppowerManager.getlistSuppowerOld(object.getItemId(),
                object.getVersion());
        if(SysTypeUtils.sysType == 1){
            return "SuppowerQlbgSqNXg";
        }else{
            return "SuppowerQlbgSqXg";
        }
        
    }

    /**
     * 权力变更新增权力事项
     * 
     * @return
     */
    public String SuppowerQlbgSqXz() {
        this.editInitial();
        if(SysTypeUtils.sysType == 1){
            return "SuppowerQlbgSqNXz";
        }else{
            return "SuppowerQlbgSqXz";
        }
       
    }

    public String AlertMessage(String url_temp) throws IOException {
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        String url = "<script>window.location.href='" + url_temp + "'</script>";
        out.print("<script>alert('保存成功！')</script>");
        out.print(url);
        out.flush();
        out.close();
        return null;
    }

    /**
     * 权力变更申请，保存修改之后的权力信息
     * 
     * @return
     */
    public String suppowerchglogSave() {
        if (processDesc_ != null) {
            try {
                FileInputStream fis = new FileInputStream(processDesc_);
                if (fis != null) {
                    byte[] bbuf = null;
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                    object.setProcessDesc(bbuf);
                    object.setFileName(processDesc_FileName);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 内部流程图
        if (inFlowImg_ != null) {
            try {
                FileInputStream fis = new FileInputStream(inFlowImg_);
                if (fis != null) {
                    byte[] bbuf = null;
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                    object.setInFlowImg(bbuf);
                    object.setInFlowImgName(inFlowImg_FileName);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // 申请表格
        if (applyForm_ != null) {
            try {
                FileInputStream fis = new FileInputStream(applyForm_);
                if (fis != null) {
                    byte[] bbuf = null;
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                    object.setApplyForm(bbuf);
                    object.setFormName(applyForm_FileName);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // 内部流程图
        if (srvDirectoryStuff_ != null) {
            try {
                FileInputStream fis = new FileInputStream(srvDirectoryStuff_);
                if (fis != null) {
                    byte[] bbuf = null;
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                    object.setSrvDirectoryStuff(bbuf);
                    //object.setInFlowImgName(inFlowImg_FileName);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 申请表格
        if (applyFormDemo_ != null) {
            try {
                FileInputStream fis = new FileInputStream(applyFormDemo_);
                if (fis != null) {
                    byte[] bbuf = null;
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                    object.setApplyFormDemo(bbuf);
                   // object.setFormName(applyForm_FileName);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String risk = null;
        try {
            risk = this.getStandXml(request);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        object.setRisk(risk);
        String processWorkDesc=this.getProcessWorkDescHtml(request);
        object.setProcessWorkDesc(processWorkDesc);
        
        String acceptCondition=this.getAcceptConditionHtml(request);
        object.setAcceptCondition(acceptCondition);
        try {
            Suppower suppower = suppowerManager.getObjectById(
                    object.getItemId(), getVersion_bg());
            Long version = object.getVersion();// 获取原始版本号信息
            String url = null;
            if (suppower != null) {// 编辑
                // 这里将选择的版本内容复制到要修改的0版本内容里面
                suppower.setVersion(Long.valueOf("0"));
                suppower.setQlState("N");// 修改权力事项里面，状态为N
                suppower.setLastmodifytime(new Date(System.currentTimeMillis()));// 修改时间
               // suppowerManager.saveSuppower(suppower);// 修改0版本
                Suppower bean = new Suppower();
                bean.setDis_detail(suppower.getDis_detail());
                bean.setDis_standard(suppower.getDis_standard());
                object.setVersion(Long.valueOf("0"));
                object.setQlState("N");// 修改在权力事项里面，状态为N
                object.setLastmodifytime(new Date(System.currentTimeMillis()));// 修改时间
                if (StringBaseOpt.isNvl(object.getDis_detail())
                        || StringBaseOpt.isNvl(object.getDis_standard())) {
                    // Suppower bean =
                    // suppowerManager.getObjectById(object.getCid());
                    if (StringBaseOpt.isNvl(object.getDis_detail())) {
                        object.setDis_detail(bean.getDis_detail());
                    }
                    if (StringBaseOpt.isNvl(object.getDis_standard())) {
                        object.setDis_standard(bean.getDis_standard());
                    }
                }
                object.setPowerOptInfos(null);
                Suppower s = new Suppower();
                s.copy(object);
                suppowerManager.saveSuppower(s);// 修改0版本
                // 增加权力变更信息
                FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                Suppowerchglog logBean = new Suppowerchglog();
                logBean.setRequester(fUserDetail.getUsercode());// 申请者
                logBean.setChgType("xg");// 新版未使用
                logBean.setVersion(version);// version具体版本
                logBean.setItemId(object.getItemId());// 这是通过页面上传过来的值
                logBean.setRequestTime(DatetimeOpt.currentUtilDate());// 申请时间是系统时间
                logBean.setChgReason(suppowerchglog.getChgReason());
                logBean.setChgContent(suppowerchglog.getChgContent());
                // 获取权力变更信息表里面最大变更编号
                logBean.setChangeId(suppowerchglogManager.genNextChangeId());
                suppowerchglogManager.saveSuppowerchglog(logBean);
                url = "powerbase/supPower!listSupPowerSQ.do?itemId="
                        + object.getItemId() + "&itemName="
                        + object.getItemName() + "&orgId=" + object.getOrgId()
                        + "&itemType=" + object.getItemType();
                this.AlertMessage(url);
                return null;
            } else {
                object.setLastmodifytime(new Date(System.currentTimeMillis()));
                object.setVersion(Long.valueOf("0"));// 设置默认的版本为0
                suppowerManager.saveSuppower(object);// 新增权力事项的时候走的流程            
                // 获取用户信息类
                FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                Suppowerchglog logBean = new Suppowerchglog();
                logBean.setRequester(fUserDetail.getUsercode());// 申请者
                logBean.setChgType("tj");// 新版未使用
                logBean.setVersion(Long.valueOf("0"));// 默认只修改0版本
                logBean.setItemId(object.getItemId());// 这是通过页面上传过来的值
                logBean.setRequestTime(DatetimeOpt.currentUtilDate());// 申请时间是系统时间
                logBean.setChgReason(suppowerchglog.getChgReason());
                logBean.setChgContent(suppowerchglog.getChgContent());
                logBean.setChangeId(suppowerchglogManager.genNextChangeId());
                suppowerchglogManager.saveSuppowerchglog(logBean);
                url = "powerbase/supPower!listSupPowerSQ.do?itemId="
                        + object.getItemId() + "&itemName="
                        + object.getItemName() + "&orgId=" + object.getOrgId()
                        + "&itemType=" + object.getItemType();
                this.AlertMessage(url);
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return ERROR;
        }
    }

    public void setVersion_bg(Long version_bg) {
        this.version_bg = version_bg;
    }

    public String getSItemType() {
        return sItemType;
    }

    public void setSItemType(String sItemType) {
        this.sItemType = sItemType;
    }

    public String getFilename() {
        return filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getProcessDesc_FileName() {
        return processDesc_FileName;
    }

    public void setProcessDesc_FileName(String processDesc_FileName) {
        this.processDesc_FileName = processDesc_FileName;
    }

    public String getApplyForm_FileName() {
        return applyForm_FileName;
    }

    public void setApplyForm_FileName(String applyForm_FileName) {
        this.applyForm_FileName = applyForm_FileName;
    }

    public String getInFlowImg_FileName() {
        return inFlowImg_FileName;
    }

    public void setInFlowImg_FileName(String inFlowImg_FileName) {
        this.inFlowImg_FileName = inFlowImg_FileName;
    }

    public File getProcessDesc_() {
        return processDesc_;
    }

    public void setProcessDesc_(File processDesc_) {
        this.processDesc_ = processDesc_;
    }

    public File getApplyForm_() {
        return applyForm_;
    }

    public void setApplyForm_(File applyForm_) {
        this.applyForm_ = applyForm_;
    }

    public File getInFlowImg_() {
        return inFlowImg_;
    }

    public void setInFlowImg_(File inFlowImg_) {
        this.inFlowImg_ = inFlowImg_;
    }

    @SuppressWarnings("rawtypes")
    public List getSubPowerList() {
        return subPowerList;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setSubPowerList(@SuppressWarnings("rawtypes") List subPowerList) {
        this.subPowerList = subPowerList;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Suppowerchglog getSuppowerchglog() {
        return suppowerchglog;
    }

    public void setSuppowerchglog(Suppowerchglog suppowerchglog) {
        this.suppowerchglog = suppowerchglog;
    }

    public void setSuppowerchglogManager(
            SuppowerchglogManager suppowerchglogManager) {
        this.suppowerchglogManager = suppowerchglogManager;
    }

    public void setSuppowerstatechglogManager(
            SuppowerstatechglogManager suppowerstatechglogManager) {
        this.suppowerstatechglogManager = suppowerstatechglogManager;
    }

    @SuppressWarnings("rawtypes")
    public List getSupPowerWithoutLobList() {
        return supPowerWithoutLobList;
    }

    @SuppressWarnings("rawtypes")
    public void setSupPowerWithoutLobList(List supPowerWithoutLobList) {
        this.supPowerWithoutLobList = supPowerWithoutLobList;
    }

    @SuppressWarnings("rawtypes")
    public List getSupppowerqlbgList() {
        return supppowerqlbgList;
    }

    @SuppressWarnings("rawtypes")
    public void setSupppowerqlbgList(List supppowerqlbgList) {
        this.supppowerqlbgList = supppowerqlbgList;
    }

    public void setSuppowerManager(SuppowerManager basemgr) {
        suppowerManager = basemgr;
        this.setBaseEntityManager(suppowerManager);
    }

    public List<VOrgSupPower> getSelectPowerList() {
        return selectPowerList;
    }

    public void setSelectPowerList(List<VOrgSupPower> selectPowerList) {
        this.selectPowerList = selectPowerList;
    }

    public Long getItemType() {
        return itemType;
    }

    public void setItemType(Long itemType) {
        this.itemType = itemType;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String getStandXml(HttpServletRequest request) throws IOException {
        String xml="";
        String []   seq_id=request.getParameterValues("seq_id");
        String []   seq_type=request.getParameterValues("seq_type");
        String []   seq_base_name=request.getParameterValues("seq_base_name");
        String []   seq_remark=request.getParameterValues("seq_remark");
        List standardList=new ArrayList();
        if(seq_id!=null&&seq_id.length>0){          
            for(int i=0;i<seq_id.length;i++){
                if(!StringBaseOpt.isNvl(seq_type[i])||!StringBaseOpt.isNvl(seq_base_name[i])||!StringBaseOpt.isNvl(seq_remark[i])){
                SuppowerStandard standinfo = new SuppowerStandard();
                standinfo.setSeq_id(seq_id[i]);
                standinfo.setSeq_type(seq_type[i]);             
                standinfo.setSeq_base_name(seq_base_name[i]);       
                standinfo.setSeq_remark(seq_remark[i]);
                standardList.add(standinfo);
                }
            }
        }
        if(standardList.size()>0){
            DocumentFactory df = DocumentFactory.getInstance();
             
            Document Doc = df.createDocument("UTF-8");
            // 创建根节点
            Element root = Doc.addElement("STANDARD");
            // 创建根节点 STANDARD;    
               
              //添加到CONTENT节点中
            SuppowerStandard supinfo = new SuppowerStandard();
            for (int j = 0; j < standardList.size(); j++){
                supinfo = (SuppowerStandard)standardList.get(j);
                Element elementsStandSeq = root.addElement("STAND_SEQ");
                Element seqId = elementsStandSeq.addElement("SEQ_ID");
                seqId.setText(""+supinfo.getSeq_id()+"");
                Element seqType =  elementsStandSeq.addElement("SEQ_TYPE"); 
                seqType.setText(""+supinfo.getSeq_type()+"");           
                Element seqBaseName =  elementsStandSeq.addElement("SEQ_BASE_NAME"); 
                seqBaseName.setText(""+supinfo.getSeq_base_name()+"");             
                Element seqRemark =  elementsStandSeq.addElement("SEQ_REMARK"); 
                seqRemark.setText(""+supinfo.getSeq_remark()+"");
             
            
            }  
             OutputFormat opf = OutputFormat.createPrettyPrint();
                opf.setEncoding("UTF-8");
                opf.setTrimText(true);
                
                // 生成XML文件
                XMLWriter xmlOut = null;
                try {
                    xmlOut = new XMLWriter(new FileOutputStream
                            ("city-dom4j.xml"), opf);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                xmlOut.write(Doc);
                xmlOut.flush();
                xmlOut.close();
                
                // 获取XML字符串形式
                StringWriter writerStr = new StringWriter();
                XMLWriter xmlw = new XMLWriter(writerStr, opf);
                xmlw.write(Doc);
                xml = writerStr.getBuffer().toString();
            
           
        } // 有样式(缩进)的写出
      return xml;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    static public List<SuppowerStandard> genxmlStandardList(String risk) {
        List<SuppowerStandard> insertList = new ArrayList();
        Document doc = null;
        if(StringBaseOpt.isNvl(risk)) return insertList;
        try {
            doc = DocumentHelper.parseText(risk); // 为Docunment对象doc加载CLOB数据
            Element root = doc.getRootElement(); // 获得XML根节点
            Iterator iter = root.elementIterator("STAND_SEQ");// 获取根节点的子节点STAND_SEQ放入迭代器中
            while (iter.hasNext()) { // 遍历STAND_SEQ
                Element recordEle = (Element) iter.next(); // 在迭代器中获取当前STAND_SEQ
                SuppowerStandard info=new SuppowerStandard();               

                info.setSeq_id(StringBaseOpt.isNvl(recordEle.elementTextTrim("SEQ_ID"))?"":recordEle.elementTextTrim("SEQ_ID"));
                info.setSeq_type(StringBaseOpt.isNvl(recordEle.elementTextTrim("SEQ_TYPE"))?"":recordEle.elementTextTrim("SEQ_TYPE"));
                info.setSeq_base_name(StringBaseOpt.isNvl(recordEle.elementTextTrim("SEQ_BASE_NAME"))?"":recordEle.elementTextTrim("SEQ_BASE_NAME"));
                info.setSeq_remark(StringBaseOpt.isNvl(recordEle.elementTextTrim("SEQ_REMARK"))?"":recordEle.elementTextTrim("SEQ_REMARK") );                
                insertList.add(info);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return insertList;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String getProcessWorkDescHtml(HttpServletRequest request){
        String xml="";
        String []   seq_base_lowmul=request.getParameterValues("seq_base_lowmul");
        String []   seq_base_topmul=request.getParameterValues("seq_base_topmul");
        String []   lastmodyfidate=request.getParameterValues("lastmodyfidate");
        String []   seq_base_unit=request.getParameterValues("seq_base_unit");
        List standardList=new ArrayList();
        if(seq_base_lowmul!=null&&seq_base_lowmul.length>0){          
            for(int i=0;i<seq_base_lowmul.length;i++){
                if(!StringBaseOpt.isNvl(seq_base_lowmul[i])||!StringBaseOpt.isNvl(seq_base_topmul[i])||!StringBaseOpt.isNvl(seq_base_unit[i])){
                SuppowerStandard standinfo = new SuppowerStandard();
                standinfo.setSeq_base_lowmul(seq_base_lowmul[i]);
                standinfo.setSeq_base_topmul(seq_base_topmul[i]); 
                standinfo.setLastmodyfidate(lastmodyfidate[i]);
                standinfo.setSeq_base_unit(seq_base_unit[i]);       
                standardList.add(standinfo);
                }
            }
        }
        if(standardList.size()>0){
            xml += "<table width=\"95%\" id=\"table_b1\" cellspacing=\"0\" cellpadding=\"0\">";
            xml += "<tr class=\"b_darkblue\">";
            xml += "<td width=\"15%\">步骤</td>";
            xml += "<td width=\"30%\">申请人和部门要做的事情</td>";
            xml += "<td width=\"15%\">回应时间</td>";
            xml += "</tr>";
            for(int i = 0 ; i < standardList.size();){
                SuppowerStandard standinfo = (SuppowerStandard)standardList.get(i);
                if(!StringBaseOpt.isNvl(standinfo.getLastmodyfidate()) && !StringBaseOpt.isNvl(standinfo.getSeq_base_topmul())){
                    xml += "<tr>";
                    xml += "<td rowspan=\"2\">";
                    xml += standinfo.getSeq_base_lowmul();
                    xml += "</td>";
                    xml += "<td>";
                    xml += standinfo.getSeq_base_topmul();
                    xml += "</td>";
                    xml += "<td>";
                    xml += "&nbsp;";
                    xml += "</td>";
                    xml += "</tr>";
                    xml += "<tr>";
                    xml += "<td>";
                    xml += standinfo.getLastmodyfidate();
                    xml += "</td>";
                    xml += "<td>";
                    xml += standinfo.getSeq_base_unit();
                    xml += "</td>";
                    xml += "</tr>";
                    i += 1;
                }else{
                    xml += "<tr>";
                    xml += "<td>";
                    xml += standinfo.getSeq_base_lowmul();
                    xml += "</td>";
                    xml += "<td>";
                    xml += standinfo.getLastmodyfidate();
                    xml += "</td>";
                    xml += "<td>";
                    xml += standinfo.getSeq_base_unit();
                    xml += "</td>";
                    xml += "</tr>";
                    i += 1;
                }
            }
            xml += "</table>";
           
        } // 有样式(缩进)的写出
      return xml;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    static public List<SuppowerStandard> genProcessWorkDescList(String processWorkDesc) {
        List<SuppowerStandard> insertList = new ArrayList();
       
        if(StringBaseOpt.isNvl(processWorkDesc)) return insertList;
      
  
                     
        String temp = processWorkDesc.substring(processWorkDesc.indexOf("<tr>", 2), processWorkDesc.length());
        String[] trs = temp.split("</tr>");
        for(int i = 0; i < trs.length -1;){
            SuppowerStandard info=new SuppowerStandard();  
            String test = trs[i];
            if(test.indexOf("rowspan=\"2\"") > 0){
                info.setSeq_base_lowmul(test.substring(test.indexOf("rowspan=\"2\"")+12, test.indexOf("</td>")));
                test = test.substring(test.indexOf("</td>")+5, test.length());
                info.setSeq_base_topmul(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                test = test.substring(test.indexOf("</td>")+5, test.length());
                info.setSeq_base_unit(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                test = trs[i+1];
                info.setLastmodyfidate(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                test = test.substring(test.indexOf("</td>")+5, test.length());
                info.setSeq_base_unit(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                i += 2;
            }else{
                info.setSeq_base_lowmul(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                test = test.substring(test.indexOf("</td>")+5, test.length());
                info.setLastmodyfidate(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                test = test.substring(test.indexOf("</td>")+5, test.length());
                info.setSeq_base_unit(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                i += 1;
            }
            insertList.add(info);
        }
       
           
          
       
        return insertList;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private String getAcceptConditionHtml(HttpServletRequest request){
        String xml="";
        String []   stand_id=request.getParameterValues("stand_id");
        String []   stand_code=request.getParameterValues("stand_code");

        List standardList=new ArrayList();
        if(stand_id!=null&&stand_id.length>0){          
            for(int i=0;i<stand_id.length;i++){
                if(!StringBaseOpt.isNvl(stand_id[i])||!StringBaseOpt.isNvl(stand_code[i])){
                SuppowerStandard standinfo = new SuppowerStandard();
                standinfo.setStand_id(stand_id[i]);
                standinfo.setStand_code(stand_code[i]); 
                
                standardList.add(standinfo);
                }
            }
        }
        if(standardList.size()>0){
            xml += "<table width=\"95%\" id=\"table_b1\" cellspacing=\"0\" cellpadding=\"0\">";
          
            for(int i = 0 ; i < standardList.size();){
                SuppowerStandard standinfo = (SuppowerStandard)standardList.get(i);
                
                    xml += "<tr>";
                    xml += "<td>";
                    xml += standinfo.getStand_id();
                    xml += "</td>";
                    xml += "<td>";
                    xml += standinfo.getStand_code();
                    xml += "</td>";
                   
                    xml += "</tr>";
                    i += 1;
            
            }
            xml += "</table>";
           
        } // 有样式(缩进)的写出
      return xml;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    static public List<SuppowerStandard> genAcceptConditionList(String processWorkDesc) {
        List<SuppowerStandard> insertList = new ArrayList();
       
        if(StringBaseOpt.isNvl(processWorkDesc)) return insertList;
      
  
                     
        String temp = processWorkDesc.substring(processWorkDesc.indexOf("<tr>", 2), processWorkDesc.length());
        String[] trs = temp.split("</tr>");
        for(int i = 0; i < trs.length -1;){
            SuppowerStandard info=new SuppowerStandard();  
            String test = trs[i];
           
                info.setStand_id(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                test = test.substring(test.indexOf("</td>")+5, test.length());
                info.setStand_code(test.substring(test.indexOf("<td>")+4, test.indexOf("</td>")));
                
                i += 1;
          
            insertList.add(info);
        }
       
           
          
       
        return insertList;
    }

    /**
     * 内部事权列表
     * 
     */
    @SuppressWarnings("unchecked")
    public String SQlist() {
    Map<Object, Object> paramMap = request.getParameterMap();
    resetPageParam(paramMap);
    Map<String, Object> filterMap = convertSearchColumn(paramMap);
    Limit limit = ExtremeTableUtils.getLimit(request);
    FUserDetail user = ((FUserDetail) getLoginUser());
    FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
    String sParentUnit = dept.getUnitcode();
    filterMap.put("sParentUnit", sParentUnit);
    String begTime = (String)filterMap.get("begTime");
    Date dateNow = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateNowStr = dateFormat.format(dateNow);
    if(StringBaseOpt.isNvl(begTime)){
        begTime = dateNowStr;
       
        
    }
    if(StringUtils.isBlank(request.getParameter("s_itemType"))){
        filterMap.put("itemType", "SQ");
    }
    filterMap.put("begTime", begTime);
    filterMap.put("endTime", begTime);
    filterMap.put("NP_not", true);
    unitList = sysUnitManager.getAllSubUnits(sParentUnit);
    PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
    supPowerWithoutLobList = suppowerManager.listPowerWithoutLob(filterMap, pageDesc);// 当权力项有多个版本时，列表显示版本号最大的一条
    totalRows = pageDesc.getTotalRows();

    return "SQlist";
    }
    /**
     * 流程启动发起事项登记列表
     */
    public String startFQList(){
        this.sqList();
        String startDjId = (String)request.getParameter("djId");
        String nodeInstId = (String)request.getParameter("nodeInstId");
        Long nodeId = Long.parseLong(nodeInstId);
        request.setAttribute("nodeId", nodeId);
        request.setAttribute("nodeInstId", nodeId);
        request.setAttribute("startDjId", startDjId);
        return "startFQList";
    }
    /**
     * 事项登记列表
     * @return
     */
    @SuppressWarnings("unchecked")
    public String nbsqList(){
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
            FUserDetail user = ((FUserDetail) getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();
            filterMap.put("unitcode", sParentUnit);
            unitList = sysUnitManager.getAllSubUnits(sParentUnit);
            nowList = vPowerOrgInfoManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            return "nbsqList";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 事项登记列表New 事项与人员关联
     * @return
     */
    @SuppressWarnings("unchecked")
    public String sqList(){
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
            FUserDetail user = ((FUserDetail) getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();
            filterMap.put("unitcode", sParentUnit);
            unitList = sysUnitManager.getAllSubUnits(sParentUnit);
            
            //当前登录人员可以操作的"SQ"
            filterMap.put("usercode", user.getUsercode());
//            filterMap.put("itemType","SQ");
            userPowerList = vPowerUserInfoManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            //return "userPowerList";
            return "userPowerListV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public List<VPowerUserInfo> getUserPowerList() {
        return userPowerList;
    }

    public void setUserPowerList(List<VPowerUserInfo> userPowerList) {
        this.userPowerList = userPowerList;
    }

    public VPowerUserInfoManager getvPowerUserInfoManager() {
        return vPowerUserInfoManager;
    }

    public void setvPowerUserInfoManager(VPowerUserInfoManager vPowerUserInfoManager) {
        this.vPowerUserInfoManager = vPowerUserInfoManager;
    }
    
}
