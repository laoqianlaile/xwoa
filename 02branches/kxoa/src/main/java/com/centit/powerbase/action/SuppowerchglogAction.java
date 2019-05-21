package com.centit.powerbase.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.po.Suppowerchglog;
import com.centit.powerbase.po.Suppowerqlbgsq;
import com.centit.powerbase.po.VPowerUserInfo;
import com.centit.powerbase.po.VPowerUserInfoId;
import com.centit.powerbase.service.SuppowerManager;
import com.centit.powerbase.service.SuppowerchglogManager;
import com.centit.powerbase.service.VPowerUserInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;

public class SuppowerchglogAction extends
        BaseEntityExtremeAction<Suppowerchglog> implements ServletResponseAware {
    private static final Log log = LogFactory
            .getLog(SuppowerchglogAction.class);

    private static final long serialVersionUID = 1L;
    private SuppowerchglogManager suppowerchglogManager;
    private SuppowerManager suppowerManager;
    private Suppowerqlbgsq suppowerqlbgsq;
    HttpServletResponse response;
    private VPowerUserInfoManager vPowerUserInfoManager;// 权力人员关联

    public void setSuppowerManager(SuppowerManager suppowerManager) {
        this.suppowerManager = suppowerManager;
    }

    public void setvPowerUserInfoManager(
            VPowerUserInfoManager vPowerUserInfoManager) {
        this.vPowerUserInfoManager = vPowerUserInfoManager;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setSuppowerchglogManager(SuppowerchglogManager basemgr) {
        suppowerchglogManager = basemgr;
        this.setBaseEntityManager(suppowerchglogManager);
    }

    @Override
    public String save() {
        return "save";
    }

    public String editInitial() {
        try {
            object = getEntityClass().newInstance();
            object.clearProperties();
            return EDIT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String delete() {
        super.delete();

        return "delete";
    }

    @Override
    public String edit() {
        if (StringUtils.isBlank(object.getItemId())) {
            suppowerchglogManager.clearObjectProperties(object);
        } else {
            object = suppowerchglogManager.getObject(object);
        }
        return EDIT;
    }

    public Suppowerqlbgsq getSuppowerqlbgsq() {
        return suppowerqlbgsq;
    }

    public void setSuppowerqlbgsq(Suppowerqlbgsq suppowerqlbgsq) {
        this.suppowerqlbgsq = suppowerqlbgsq;
    }

    /*
     * 权力变更审核获取具体权力事项信息
     */
    public String suppowerQlbgShEdit() {

        suppowerqlbgsq = suppowerchglogManager.getSuppowerqlbgsqInfo(
                object.getItemId(), object.getVersion());
        suppowerqlbgsq.setBegTime(DatetimeOpt.currentUtilDate());
        suppowerqlbgsq.setChgResult("1");// 默认值设置为1
        return "suppowerQlbgShEdit";
    }

    /*
     * 权力变更审核获取具体权力事项信息
     */
    public String suppowerQlbgReplyEdit() {

        suppowerqlbgsq = suppowerchglogManager.getSuppowerqlbgsqInfo(
                object.getItemId(), object.getVersion());
        return "suppowerQlbgReplyEdit";
    }

    /*
     * 下面是获取具体某一个权力事项修改的信息
     */
    public String listSupPowerSH() {

        return "listSupPowerSH";
    }

    /*
     * 下面是获取具体某一个权力事项修改的信息
     */
    public String listSupPowerReply() {

        return "listSupPowerReply";
    }

    /**
     * 保存审核之后的信息
     * 
     * @return
     */
    public String SuppowerSHSave() {
        if ("1".equals(object.getChgResult())) {// 申请同意修改
            try {
                FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                object.setChgResult(object.getChgResult());// 申请结果
                object.setAuditor(fUserDetail.getUsercode());// 审核者
                object.setAuditTime(DatetimeOpt.currentUtilDate());// 审核时间
                object.setBegTime(object.getBegTime());// 生效时间
                suppowerchglogManager.saveObject(object);
                return "success";
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                saveError(e.getMessage());
                return ERROR;
            }
        } else {
            if (0 != object.getVersion()) {
                Suppower suppower = suppowerManager.getObjectById(
                        object.getItemId(), object.getVersion());
                ;
                suppower.setVersion(Long.valueOf("0"));
                suppowerManager.saveObject(suppower);// 修改版本重新返回到0版本上去
                FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                object.setChgResult(object.getChgResult());// 申请结果
                object.setAuditor(fUserDetail.getUsercode());// 审核者、
                object.setAuditTime(DatetimeOpt.currentUtilDate());// 审核时间
                suppowerchglogManager.saveObject(object);
                return "success";
            } else {
                FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                object.setAuditor(fUserDetail.getUsercode());// 审核者、
                object.setAuditTime(DatetimeOpt.currentUtilDate());// 审核时间
                suppowerchglogManager.saveObject(object);
                return "success";
            }
        }
    }

    /**
     * 权力变更回复，保存
     * 
     * @return
     */
    public String SuppowerReplySave() {
        Suppower suppower = new Suppower();
        Long version = object.getVersion();
        if ("1".equals(object.getChgResult())) {// 申请同意修改
            try {
                Long versionTemp = Long.valueOf("0");
                suppower = suppowerManager.getObjectById(object.getItemId(),
                        versionTemp);// 获取版本号为0的该权力事项
                Suppower suppower_temp = null;
                Suppower suppower_old = null;
                suppower_temp.copyNotNullProperty(suppower);// 获取版本号为0的该权力事项
                if (0 != version) {// 非新增的修改
                    suppower_old = suppowerManager.getObjectById(
                            object.getItemId(), version);// 获取版本号为0的该权力事项
                    // suppower.setEndTime(DatetimeOpt.currentUtilDate());//
                    // 设置结束时间为系统时间
                    if ("N".equals(suppower.getQlState())) {
                        suppower_temp.setVersion(version + 1);// 新版本号
                        suppower_temp.setBeginTime(object.getBegTime());// 设置启用时间为系统时间
                        suppower_temp.setQlState("A");// 新版本为在用A
                        suppower_old.setEndTime(DatetimeOpt.currentUtilDate());// 设置结束时间为系统时间
                        suppower_old.setQlState("U");// 已经经过一次升级,以前的版本

                    } else if ("A".equals(suppower.getQlState())) {// 启用该权力事项
                        suppower_old.setBeginTime(object.getBegTime());// 设置启用时间为选择的生效时间
                        suppower_temp.setBeginTime(object.getBegTime());// 设置启用时间为系统时间
                        suppower_old.setEndTime(null);// 设置结束时间为null
                    } else {// 废止或者挂起
                        suppower_old.setQlState(suppower_temp.getQlState());
                        suppower_old.setEndTime(object.getBegTime());// 设置结束时间为系统时间
                    }
                    suppower_temp.setPowerOptInfos(null);
                    suppower_old.setPowerOptInfos(null);
                    Suppower s = new Suppower();
                    s.copy(suppower_temp);
                    Suppower s1 = new Suppower();
                    s1.copy(suppower_old);

                    suppowerManager.saveSuppower(suppower_old, s);
                    // suppowerManager.saveSuppower(suppower_old,
                    // suppower_temp);
                } else {
                    suppower.setVersion(version);// 原来的版本号
                    suppower.setBeginTime(object.getBegTime());// 设置启用时间为审核填写的生效时间
                    suppower_temp.setVersion(version + 1);// 新版本号
                    if ("T".equals(suppower.getQlState())
                            || "X".equals(suppower.getQlState())) {
                        suppower.setEndTime(DatetimeOpt.currentUtilDate());
                    }
                    suppower_temp.setBeginTime(object.getBegTime());// 设置启用时间为系统时间
                    suppower_temp.setPowerOptInfos(null);
                    suppower.setPowerOptInfos(null);
                    Suppower s = new Suppower();
                    s.copy(suppower_temp);
                    Suppower s1 = new Suppower();
                    s1.copy(suppower);
                    suppowerManager.saveSuppower(suppower, s);
                }
                FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                object.setReplyPeople(fUserDetail.getUsercode());// 回复者
                object.setReplyTime(DatetimeOpt.currentUtilDate());// 回复时间
                suppowerchglogManager.saveObject(object);
                return "successReply";
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                saveError(e.getMessage());
                return ERROR;
            }
        } else {
            try {
                if (0 != version) {// 非新增的
                    suppower = suppowerManager.getObjectById(
                            object.getItemId(), version);// 获取该权力事项
                    suppower.setVersion(Long.valueOf("0"));// 新版本号
                    suppowerManager.saveObject(suppower);
                } 
               /* else {
                    // 新增的如果同意就不做修改
                }*/
                FUserDetail fUserDetail = (FUserDetail) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                object.setReplyPeople(fUserDetail.getUsercode());// 回复者
                object.setReplyTime(DatetimeOpt.currentUtilDate());// 回复时间
                suppowerchglogManager.saveObject(object);
                return "successReply";
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                saveError(e.getMessage());
                return ERROR;
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private List versionList;
    @SuppressWarnings("rawtypes")
    private List suppowerList;
    private Suppower suppower;

    public Suppower getSuppower() {
        return suppower;
    }

    public void setSuppower(Suppower suppower) {
        this.suppower = suppower;
    }

    @SuppressWarnings("rawtypes")
    public List getSuppowerList() {
        return suppowerList;
    }

    @SuppressWarnings("rawtypes")
    public void setSuppowerList(List suppowerList) {
        this.suppowerList = suppowerList;
    }

    @SuppressWarnings("rawtypes")
    public List getVersionList() {
        return versionList;
    }

    @SuppressWarnings("rawtypes")
    public void setVersionList(List versionList) {
        this.versionList = versionList;
    }

    public String listVersion() {
        /*
         * //屏蔽之前的方式，方法很多内容都是冗余的，根本没有用 String itemid = (String)
         * request.getAttribute("itemId"); Long version = (Long)
         * request.getAttribute("version"); Map<Object, Object> paramMap =
         * request.getParameterMap(); resetPageParam(paramMap); Map<String,
         * Object> filterMap = convertSearchColumn(paramMap);
         * filterMap.put("itemId", itemid); Limit limit =
         * ExtremeTableUtils.getLimit(request); PageDesc pageDesc =
         * ExtremeTableUtils.makePageDesc(limit); versionList =
         * suppowerchglogManager
         * .getlistVersionByItemid(filterMap,pageDesc);//当权力项有多个版本时，列表显示版本号最大的一条
         * suppowerList = suppowerManager.getlistSuppowerOld(itemid,version);
         * totalRows = pageDesc.getTotalRows(); suppower =
         * suppowerManager.getObjectById(itemid, version);
         * 
         * List<SuppowerStandard> list2 =
         * SupPowerAction.genxmlStandardList(suppower.getRisk());
         * request.setAttribute("list2", list2); List<SuppowerStandard> list1=
         * SupPowerAction.genProcessWorkDescList(suppower.getProcessWorkDesc());
         * request.setAttribute("list1", list1); List<SuppowerStandard> list3=
         * SupPowerAction.genAcceptConditionList(suppower.getAcceptCondition());
         * request.setAttribute("list3", list3);
         * request.setAttribute("sup",suppower);
         * request.setAttribute("suppowerList", suppowerList); if
         * (SysTypeUtils.sysType == 1) { return "listNVersion"; } else { return
         * "listVersion"; }
         */

        // 根据页面传递的权力编码以及版本号，获取最新的事项信息
        String itemid = (String) request.getAttribute("itemId");
        Long version = (Long) request.getAttribute("version");
        suppower = suppowerManager.getObjectById(itemid, version);
        request.setAttribute("sup", suppower);
        // 此处新增查看事项对应的流程图信息
        FUserDetail user = ((FUserDetail) getLoginUser());
        VPowerUserInfoId vPowerUserInfoId = new VPowerUserInfoId();
        vPowerUserInfoId.setItemId(itemid);
        vPowerUserInfoId.setUsercode(user.getUsercode());
        VPowerUserInfo vPowerUserInfo = vPowerUserInfoManager
                .getObjectById(vPowerUserInfoId);
        request.setAttribute("vPowerUserInfo", vPowerUserInfo);
        return "listNVersion";
    }
}
