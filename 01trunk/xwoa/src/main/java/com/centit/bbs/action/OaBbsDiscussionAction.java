package com.centit.bbs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.bbs.po.OaBbsDashboard;
import com.centit.bbs.po.OaBbsDiscussion;
import com.centit.bbs.po.OaBbsTheme;
import com.centit.bbs.service.OaBbsDashboardManager;
import com.centit.bbs.service.OaBbsDiscussionManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUserManager;

public class OaBbsDiscussionAction extends BaseEntityDwzAction<OaBbsDiscussion> {
    private static final Log log = LogFactory
            .getLog(OaBbsDiscussionAction.class);
    private static final long serialVersionUID = 1L;
    private OaBbsDiscussionManager oaBbsDiscussionMag;
    public SysUserManager sysUserManager;

    public void setOaBbsDiscussionManager(OaBbsDiscussionManager basemgr) {
        oaBbsDiscussionMag = basemgr;
        this.setBaseEntityManager(oaBbsDiscussionMag);
    }

    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    private List<OaBbsTheme> oaBbsThemes;

    public List<OaBbsTheme> getNewOaBbsThemes() {
        return this.oaBbsThemes;
    }

    public void setNewOaBbsThemes(List<OaBbsTheme> oaBbsThemes) {
        this.oaBbsThemes = oaBbsThemes;
    }

    private OaBbsDashboardManager oaBbsDashboardManager;

    public void setOaBbsDashboardManager(
            OaBbsDashboardManager oaBbsDashboardManager) {
        this.oaBbsDashboardManager = oaBbsDashboardManager;
    }

    private String s_layoutcode;
    private String s_isOwner;// 是否管理员

    public String getS_layoutcode() {
        return s_layoutcode;
    }

    public void setS_layoutcode(String s_layoutcode) {
        this.s_layoutcode = s_layoutcode;
    }

    private String s_discussOrNot;

    public String getS_discussOrNot() {
        return s_discussOrNot;
    }

    public void setS_discussOrNot(String s_discussOrNot) {
        this.s_discussOrNot = s_discussOrNot;
    }

    private File uploadFile_;

    public File getUploadFile_() {
        return uploadFile_;
    }

    public void setUploadFile_(File uploadFile_) {
        this.uploadFile_ = uploadFile_;
    }

    private String uploadFile_FileName;

    public String getUploadFile_FileName() {
        return uploadFile_FileName;
    }

    public void setUploadFile_FileName(String uploadFile_FileName) {
        this.uploadFile_FileName = uploadFile_FileName;
    }

    /**
     * 列表入口包含机构菜单
     * 
     * @return
     */
    public String oaDiscussonLab() {
        putUnitTree();
        return "oaDiscussonLab";
    }

    /**
     * 获取讨论版面数据
     * 
     * @return
     */
    public void putUnitTree() {
        FUserDetail user = ((FUserDetail) getLoginUser());
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        String sParentUnit = dept.getUnitcode();
        JSONArray ja = oaBbsDashboardManager.putLayoutTree(user.getUsercode(),
                sParentUnit);
        request.setAttribute("unit", ja.toString());
    }

    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        // 设置子版块所属的大模块，作显示大模块名称用
        if (null != filterMap
                && null != filterMap.get("layoutcode")
                && StringUtils.isNotBlank(filterMap.get("layoutcode")
                        .toString())) {
            /*
             * object.setOaBbsDashboard(oaBbsDashboardManager
             * .getObjectById(filterMap
             * .get("layoutcode").toString()));//页面加载完异步请求处理
             */

            if ("0".equals(filterMap.get("layoutcode").toString())) {
                filterMap.remove("layoutcode");
            }
        }
        PageDesc pageDesc = makePageDesc();

        FUserDetail user = ((FUserDetail) getLoginUser());

        if (null != filterMap && null != filterMap.get("isOwner")
                && StringUtils.isNotBlank(filterMap.get("isOwner").toString())
                && "T".equals(filterMap.get("isOwner").toString())
                && null != user) {
            filterMap.put("oaBbsDashboard.approvals", user.getUsercode());
        }
        if(null == filterMap.get("includeDel")){
          filterMap.put("excludeStates", "D");
        }
        objList = baseEntityManager.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();

        this.pageDesc = pageDesc;

        // 大模块名称列表
       /* List<OaBbsDashboard> dashboardList = oaBbsDashboardManager
                .listObjects(); //getDashboarListByUser代替 ，添加过滤
        request.setAttribute("dashboardList", dashboardList);*/
        getDashboarListByUser();

       

        // resetParam(filterMap.get("oaBbsDashboard.approvals"));
        setbackSearchColumn(filterMap);
        return LIST;
    }

    /**
     * 重新将参数两边的%去除
     * 
     * @param param
     */
    public void resetParam(Object param) {

        if (null != param && StringUtils.isNotBlank(param.toString())) {
            String s = param.toString();
            if (s.startsWith("%")) {
                s = s.substring(1);
            }
            if (s.endsWith("%")) {
                s = s.substring(0, s.length() - 1);
            }
        }
    }

    public String edit() {

        OaBbsDiscussion o = oaBbsDiscussionMag.getObject(object);

        if (null != o) {
            oaBbsDiscussionMag.copyObjectNotNullProperty(object, o);
        }else{
            object.setState("N");
        }
        // 新增
        if (StringUtils.isEmpty(object.getLayoutno())) {
            if ("0".equals(s_layoutcode) || StringUtils.isEmpty(s_layoutcode)) {// 顶级选择所属大版块
                getDashboarListByUser();
            } else {
                object.setLayoutcode(s_layoutcode);
            }

        }

        /*
         * // 设置所属大版块对象，用于在页面中显示其所属大版块名称
         * if(StringUtils.isNotBlank(s_layoutcode)){
         * object.setLayoutcode(s_layoutcode); } OaBbsDashboard dashboard =
         * oaBbsDashboardManager.getObjectById(object.getLayoutcode());
         * object.setOaBbsDashboard(dashboard);
         */
        return EDIT;
    }

    public void getDashboarListByUser() {
        FUserDetail user = ((FUserDetail) getLoginUser());
        if (null != user) {
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user
                    .getUsercode());
            String sParentUnit = dept.getUnitcode();
            List<OaBbsDashboard> oaBbsDashboList = oaBbsDashboardManager
                    .getDashboarListByUser(user.getUsercode(), sParentUnit);
            request.setAttribute("dashboardList", oaBbsDashboList);
        }
    }

    public String save() {

        // 根据页面暂存的时间段变量设置开放时间范围
        object.setOpentime();
        OaBbsDiscussion dbObject = oaBbsDiscussionMag.getObject(object);
        if (null != dbObject) {
            oaBbsDiscussionMag.copyObjectNotNullProperty(dbObject, object);
            // 重新设置开放时间段，如果页面传来的暂存时间点有空值，就清空该时间点
            this.setOpentimeWithNull(object, dbObject);
            object = dbObject;
        }

        FUserDetail user = ((FUserDetail) getLoginUser());
        // 设置创建人
        if (null != user && StringUtils.isBlank(object.getCreater())) {
            object.setCreater(user.getUsercode());
        }
        // 设置创建时间
        if (null == object.getCreatetime()) {
            object.setCreatetime(new Date());
        }
        // 如果有审核结果，就设置审核人
        if (StringUtils.isNotBlank(object.getApprovalresults())) {
            object.setApproval(user.getUsercode());
        }
        // 最后更新时间
        object.setLastmodifytime(new Date());
        // 设置上传的图片
        this.setUploadPicture(object);
        oaBbsDiscussionMag.saveObject(object);

        // 判断是从子版块进入还是从大模块进入的，如果是子版块的，就返回到子版块列表 ，否则返回到大模块列表
        if ("Y".equals(s_discussOrNot)) {
            return this.list();
        }
        return "dashboardList";
    }

    /**
     * 重新设置开放时间段，如果页面有传来空值，就清空该时间点
     * 
     * @param object
     *            页面对象
     * @param dbObject
     *            数据库对象
     */

    public void setOpentimeWithNull(OaBbsDiscussion object,
            OaBbsDiscussion dbObject) {

        if (StringUtils.isBlank(object.getStarttimeTemp())) {
            dbObject.setStarttime(null);
        }
        if (StringUtils.isBlank(object.getEndtimeTemp())) {
            dbObject.setEndtime(null);
        }
        if (StringUtils.isBlank(object.getStarttimepmTemp())) {
            dbObject.setStarttimepm(null);
        }
        if (StringUtils.isBlank(object.getEndtimepmTemp())) {
            dbObject.setEndtimepm(null);
        }
    }

    /**
     * 设置上传的图片
     * 
     * @param object
     */
    public void setUploadPicture(OaBbsDiscussion object) {
        try {
            // 读取上传文件
            if (uploadFile_ != null) {
                byte[] bbuf = null;

                FileInputStream fis = new FileInputStream(uploadFile_);
                if (fis != null) {
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                }
                fis.close();
                object.setShowpicture(bbuf);
                object.setShowpicturename(uploadFile_FileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String delete() {

        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        //modify by lay 2015-11-21 改成逻辑删除
       // super.delete();
        oaBbsDiscussionMag.deleteObject(object);
        //end modify
        setbackSearchColumn(filterMap);

        return SUCCESS;
    }

    /**
     * 启用
     * @return
     */
    public String setup(){
        OaBbsDiscussion o = oaBbsDiscussionMag.getObject(object);
        if (object == null) {
            return LIST;
        }
        if (o != null) {
            o.setState("N");
            oaBbsDiscussionMag.saveObject(o);
        }
        return SUCCESS;
    }
    
    public String view() {

        OaBbsDiscussion o = oaBbsDiscussionMag.getObject(object);
        if (object == null) {

            return LIST;
        }
        if (o != null)
            oaBbsDiscussionMag.copyObject(object, o);

        /*
         * // 设置大模块对象，用来在页面中获取版面代码
         * if(StringUtils.isNotBlank(object.getLayoutcode())){ OaBbsDashboard
         * dashboard =
         * oaBbsDashboardManager.getObjectById(object.getLayoutcode());
         * object.setOaBbsDashboard(dashboard); }
         */
        return VIEW;
    }

    /**
     * 页面显示图片
     * 
     * @return
     * @throws Exception
     */
    public String showImage() throws Exception {
        OaBbsDiscussion info = baseEntityManager.getObjectById(object
                .getLayoutno());
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream out = null;
        try {
            response.setContentType("image/jpeg");
            out = response.getOutputStream();

            byte[] b = null == info ? null : info.getShowpicture();
            // 否则使用图片
            if (null == b) {
                String basePath = request.getSession().getServletContext()
                        .getRealPath("/");
                File file = new File(basePath
                        + CodeRepositoryUtil.getDataPiece("SYSPARAM",
                                "DefaultImage"));
                FileInputStream fis = new FileInputStream(file);

                if (fis != null) {
                    int len = fis.available();
                    b = new byte[len];
                    fis.read(b);

                }
            }

            // 输出图片
            out.write(b);
            out.flush();
        } catch (Exception e) {
        } finally {
            try {
                if (null != out)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private JSONObject json;

    /**
     * 根据编号获取板块名称
     * 
     * @return
     */
    public String getsubLayoutTitle() {
        json = new JSONObject();
        // String layoutno=(String) request.getParameter("layoutno");
        object = oaBbsDiscussionMag.getObject(object);

        json.put("status", object.getSublayouttitle() == null ? "模块已删除"
                : object.getSublayouttitle());

        return "options";
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public String getS_isOwner() {
        return s_isOwner;
    }

    public void setS_isOwner(String s_isOwner) {
        this.s_isOwner = s_isOwner;
    }

}
