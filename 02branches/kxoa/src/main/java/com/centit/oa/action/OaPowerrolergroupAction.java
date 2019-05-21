package com.centit.oa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;
import org.springframework.util.StringUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaPowergroupDetail;
import com.centit.oa.po.OaPowerrolergroup;
import com.centit.oa.service.OaPowergroupDetailManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.opensymphony.xwork2.ActionContext;

public class OaPowerrolergroupAction extends
        BaseEntityExtremeAction<OaPowerrolergroup> {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory
            .getLog(OaPowerrolergroupAction.class);
    private static final long serialVersionUID = 1L;
    private OaPowerrolergroupManager oaPowerrolergroupMag;
    private OaPowergroupDetailManager oaPowergroupDetailManager;

    public void setOaPowerrolergroupManager(OaPowerrolergroupManager basemgr) {
        oaPowerrolergroupMag = basemgr;
        this.setBaseEntityManager(oaPowerrolergroupMag);
    }

    private List<OaPowergroupDetail> oaPowergroupDetails;

    public List<OaPowergroupDetail> getNewOaPowergroupDetails() {
        return this.oaPowergroupDetails;
    }

    private String usercodes;
    private String userNames;

    private String s_groupType;// 分组类型：1个人2公共

    /**
     * 分组列表
     * 
     * @return
     */
    public String list() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            String group_type = (String) filterMap.get("s_groupType");// 分组类型

            if ("1".equals(group_type)) {
                filterMap.put("creater", getLoginUserCode());
            }

            filterMap.put("NP_state", true);// 默认查询启用，和无使用状态的记录
            if (("true").equals(filterMap.get("isBoth"))) {
                filterMap.remove("NP_state");
            }
            objList = baseEntityManager.listObjects(filterMap, pageDesc);

            // 返回页面查询痕迹
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 只是编辑权限分组基本信息
     */
    public String edit() {
        // usercodes=getUsercodes(object.getNo());
        // userNames=getUserNames(usercodes);
        // putInnermsgTree();
        return super.edit();

    }

    /**
     * 查看页面做权限分组明细维护
     */
    public String view() {
        usercodes = getUsercodes(object.getNo());
        userNames = getUserNames(usercodes);
        putInnermsgTree();
      
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("no", object.getNo());
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        oaPowergroupDetails=oaPowergroupDetailManager.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return super.view();

    }

    /*
     * 取共享人员usercode
     */
    private String getUsercodes(String no) {
        List<String> usercodeList = oaPowergroupDetailManager
                .listUsercodesByNo(no);

        return StringUtils.collectionToDelimitedString(usercodeList, ",");
    }

    /*
     * 取共享人员userName：管理员,系统管理员
     */
    private String getUserNames(String usercodes) {

        List<String> userNameList = new ArrayList<String>();
        if (StringUtils.hasText(usercodes)) {
            String[] usercode = usercodes.split(",");
            for (String s : usercode) {
                // 根据人员usercode获取userName
                userNameList.add(CodeRepositoryUtil.getValue("usercode", s));
            }
        }

        return StringUtils.collectionToDelimitedString(userNameList, ",");
    }

    public String save() {
        if (!StringUtils.hasText(object.getNo())) {
            object.setCreater(getLoginUserCode());
            object.setCreatertime(new Date());
        }

        super.save();

       return list();
    }
    
    /**保存权限分组详情
     * 
     * @return
     */
    public String saveDetails() {
        // 先删除该权限分组已有的detail
        oaPowergroupDetailManager.deleteDetails(object);
        // 根据usercode 保存权限分组明细 oa_powerGroup_detail,
        oaPowergroupDetailManager.saveDetails(object, usercodes);
        return this.view();
    }
    

    public String deleteDetail(){
        //获取需要删除的详情主键
        String id=request.getParameter("id");
        if(StringUtils.hasText(id)){
            oaPowergroupDetailManager.deleteObjectById(id);
        }
        return this.view();
    }
    
    
    
    /*
     * 当前登录人员usercode
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    public String getS_groupType() {
        return s_groupType;
    }

    public void setS_groupType(String s_groupType) {
        this.s_groupType = s_groupType;
    }

  

    /**
     * 获取部门人员数据
     * 
     * @return
     */
    public String putInnermsgTree() {
        JSONArray ja = oaPowerrolergroupMag.putUnitsUsersTree();
        ActionContext.getContext().put("unit", ja.toString());
        return "userTree";
    }
    
    
    
    /**
     * 获取部门人员数据
     * 
     * @return
     */
    public String putAllUserTree() {
        JSONArray ja =oaPowerrolergroupMag.putAllUserTree(getLoginUserCode());
        ActionContext.getContext().put("unit", ja.toString());
        return "userTree";
    }


    public OaPowergroupDetailManager getOaPowergroupDetailManager() {
        return oaPowergroupDetailManager;
    }

    public void setOaPowergroupDetailManager(
            OaPowergroupDetailManager oaPowergroupDetailManager) {
        this.oaPowergroupDetailManager = oaPowergroupDetailManager;
    }

    public String getUsercodes() {
        return usercodes;
    }

    public void setUsercodes(String usercodes) {
        this.usercodes = usercodes;
    }

    public void setNewOaPowergroupDetails(
            List<OaPowergroupDetail> oaPowergroupDetails) {
        this.oaPowergroupDetails = oaPowergroupDetails;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }
}
