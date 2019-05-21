package com.centit.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import rtx.RTXSvrApi;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.powerruntime.page.OptHtmlFrameInfo;
import com.centit.powerruntime.page.OptJspInfo;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.ExportExcelUtil;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.ISysOptLog;
import com.centit.sys.util.SysOptLogFactoryImpl;
import com.centit.webservice.client.WsclientManager;
	

public class OaUserinfoAction  extends BaseEntityDwzAction<OaUserinfo>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaUserinfoAction.class);
	private static final long serialVersionUID = 1L;
	private OaUserinfoManager oaUserinfoMag;
	private String bodycode;
	private SysUnitManager sysUnitManager;
	private SysUserManager sysUserManager;
	private List<FUserinfo> fUserinfos;
	private List<OaUserinfo> oaUserinfos;
	private FUserunit fUserunit;
	private List<FUserinfo> uselist;
	private OaPowerrolergroupManager oaPowerrolergroupManager;
	private WsclientManager wsclientManager;//同步人员数据
	private static final ISysOptLog SYS_OPT_LOG = SysOptLogFactoryImpl.getSysOptLog("USERMAG");//记录人员信息操作历史记录
	
	private String s_usercode;

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
    
    private String uploadHeadPicture_;
    
    private String uploadHeadPicture_FileName;
        
	public String getUploadHeadPicture_() {
        return uploadHeadPicture_;
    }

    public void setUploadHeadPicture_(String uploadHeadPicture_) {
        this.uploadHeadPicture_ = uploadHeadPicture_;
    }

    public String getUploadHeadPicture_FileName() {
        return uploadHeadPicture_FileName;
    }

    public void setUploadHeadPicture_FileName(String uploadHeadPicture_FileName) {
        this.uploadHeadPicture_FileName = uploadHeadPicture_FileName;
    }

    public void setOaUserinfoManager(OaUserinfoManager basemgr)
	{
		oaUserinfoMag = basemgr;
		this.setBaseEntityManager(oaUserinfoMag);
	}
	 /**
     * 获取当前未被禁用所有人员 oaUserinfo表中
     * @return
     */
    @SuppressWarnings("unchecked")
    public String oaList(){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        //初始化oa_userinfo
        //insertOAuser();
        //根据部门查询f_user_unit表中的所有人员userCode
        List<FUserinfo> unitusers = oaUserinfoMag.getUserUnderUserUnit(filterMap,bodycode);
     /*   if(null!=filterMap.get("userName") && filterMap.get("userName")!="") {
            filterMap.put("usercode",sysUserManager.getFUserInfoByLoginname((String)filterMap.get("userName")).getUsercode());
        }*/
        objList = this.showUser(unitusers, filterMap);
       
        totalRows = objList.size();
        setbackSearchColumn(filterMap);
        
        Map<String,Object> unitFilter = new HashMap<String, Object>();
        unitFilter.put("isvalid", "T");
        List<FUnitinfo> units = sysUnitManager.listObjects(unitFilter);
        request.setAttribute("allUnits", units);
                
       // return LIST;
        return "listV2";
    }
    
    /**
     * 
     * @return
     */
    public String editDY(){
        JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
        return "editDY";
    }
    /**
     * 同步用户信息到rtx
     * @throws IOException 
     */
    public void syncUserInfoToRTX() throws IOException{
        boolean flag = false;
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        //根据部门查询f_user_unit表中的所有人员userCode
        List<FUserinfo> unitusers = oaUserinfoMag.getUserUnderUserUnit(filterMap,bodycode);
        objList = this.showUser(unitusers, filterMap);
        
        RTXSvrApi  rtxsvrapiObj = new RTXSvrApi();
        int result = 0;
        try{
            
            for(OaUserinfo oaUserInfo:objList){
                if (rtxsvrapiObj.Init()) {
                result = rtxsvrapiObj.SetUserSimpleInfo(oaUserInfo.getUsername(), oaUserInfo.getEmail(), oaUserInfo.getSex(), 
                        oaUserInfo.getMobilephone(), oaUserInfo.getTelephone(), null);
                if(result!=0){
                    break;
                }
                }
                rtxsvrapiObj.UnInit();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        flag = (result==0);
        ServletActionContext.getResponse().getWriter().print(flag);
    }
    
    //封装所有查询到的oa_userinfo
    public List<OaUserinfo> showUser(List<FUserinfo> unitusers,Map<String, Object> filterMap){
        List<OaUserinfo> infos = new ArrayList<OaUserinfo>();
        if (unitusers != null && unitusers.size() >= 1){
            for(FUserinfo o:unitusers){
                //查询oa_userinfo中的人员
                String usercode = (String)filterMap.get("usercode");
                if(StringUtils.isBlank(usercode)){
                    filterMap.put("usercode", o.getUsercode());
                    //oaUserinfos = oaUserinfoMag.listObjects(filterMap);
                    oaUserinfos = oaUserinfoMag.listOauserinfoObjects(filterMap);
                    filterMap.remove("usercode");
                }else{
                  //oaUserinfos = oaUserinfoMag.listObjects(filterMap);
                    oaUserinfos = oaUserinfoMag.listOauserinfoObjects(filterMap);
                    infos=  oaUserinfos;
                    break;
                }
                if(oaUserinfos!=null){
                    for(OaUserinfo oaUserinfo : oaUserinfos){
                        infos.add(oaUserinfo);
                    }
                }
            } 
        }
        return infos;
    }
    @Override
    public String view(){
        super.view();
        FUserinfo fUserinfo = sysUserManager.getObjectById(object.getUsercode());
        fUserunit = sysUserManager.getUserPrimaryUnit(object
                .getUsercode());
        object.setfUserinfo(fUserinfo);
        //用户职务说明等信息
        request.setAttribute("userrank", fUserunit.getUserrank());
        return VIEW;
    }
    @Override
    public String edit(){
        super.edit();
        FUserinfo fUserinfo = sysUserManager.getObjectById(object.getUsercode());
        fUserunit = sysUserManager.getUserPrimaryUnit(object
                .getUsercode());
        object.setfUserinfo(fUserinfo);
        request.setAttribute("userrank", fUserunit.getUserrank());
        return EDIT;
    }
    //保存或者编辑信息
    public String save(){
        OaUserinfo oaUserinfo = oaUserinfoMag.getObject(object);
        if (oaUserinfo != null) {
            oaUserinfoMag.copyObject(oaUserinfo,object);
            object = oaUserinfo;
        }
        oaUserinfoMag.saveObject(object);
        //组织管理  用户管理数据同步
        FUserinfo dbobject = sysUserManager.getObjectById(object.getUsercode());
        dbobject.setOfficePhone(object.getTelephone());
        dbobject.setContactPhone(object.getMobilephone());
        dbobject.setRegemail(object.getEmail());
        sysUserManager.saveObject(dbobject);
        
        //同步人员信息到移动端
        String log=wsclientManager.syncUserList(object.getUsercode());
        
        SYS_OPT_LOG.log(((FUserinfo) this.getLoginUser()).getUsercode(), object.getUsercode(), "禁用用户 ["
                + object.getUsername() + " ]"+"移动端数据同步 ["
                + log + " ]");
        
        return this.oaList();
    }
    
    /**
     *  初始化oa_userinfo表中的记录
     * @return
     */
    public void insertOAuser(){
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        fUserinfos = sysUserManager.listObjects(filterMap);
        oaUserinfos =oaUserinfoMag.listObjects();
        if((oaUserinfos==null)
                ||(oaUserinfos!=null && (oaUserinfos.size()!=fUserinfos.size()))){
            for(FUserinfo fUserinfo :fUserinfos){
                OaUserinfo oaUserinfo = new OaUserinfo();
                oaUserinfo.setUsercode(fUserinfo.getUsercode());
                oaUserinfoMag.saveObject(oaUserinfo);
            }
        }
    }
    //选择人员
    public String selectuser() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
            uselist=sysUserManager.listUnderUnit(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            this.pageDesc = pageDesc;
            return "selectuser";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    @Override
    public String exportExcel() {
        try {
           
            String fileName="";
            if(null!=bodycode){
                fileName+=CodeRepositoryUtil.getValue("unitcode", String.valueOf(bodycode));
            }
            exportFileName = new String((fileName+"内部通讯录列表.xls").getBytes("GBK"),
                    "ISO8859-1");// 将导出Excel的文件名进行ISO8859-1编码，否则中文文件名会乱码
        } catch (UnsupportedEncodingException e) {
        }
        return "exportExcel";
    }
    
    public InputStream getExportExcelFile() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        //初始化oa_userinfo
        //insertOAuser();
        //根据部门查询f_user_unit表中的所有人员userCode
        List<FUserinfo> unitusers = oaUserinfoMag.getUserUnderUserUnit(filterMap,bodycode);
        List<OaUserinfo> objectList =this.showUser(unitusers, filterMap);
        // 获取需要生成Excel的数据
        if (objectList != null) {
            int i = 1;
            for (OaUserinfo o : objectList) {
                o.setNo(String.valueOf(i++)); // 编号
                o.setUsername(CodeRepositoryUtil.getValue("usercode", o.getUsercode()));
                o.setBirthday(DatetimeOpt.convertSqlDate(o.getBirthday()));
//                o.setSex(CodeRepositoryUtil.getValue("sex", o.getSex()));
                
            }
        }
        String[] header = { "编号", "姓名","出生日期", "工作地点（楼层）","办公电话","手机号码","电子邮箱","用户说明","备注"};// 列头
        String[] property = { "no", "username", "birthday","workplace","telephone","mobilephone","email","introduce","remark"};// --订单内容
//        response.reset();
//        response.setContentType("APPLICATION/vnd.ms-excel");
        // 注意，如果去掉下面一行代码中的attachment; 那么也会使IE自动打开文件。
//        response.setHeader("Content-Disposition", "attachment;filename=" + new String(("通讯录列表.xls").getBytes("GBK"), "ISO8859-1"));
        return ExportExcelUtil.generateExcel(objectList, header, property);
    
}
    
    
    
    /**
     * 导出Excel 通讯录
     * 
     * @throws IOException
     */
    public void exportExcelByPo() throws IOException {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Map<String,String> map = this.buildMap2(filterMap);
        map.put("gkstate", "2");
        
        String fileName="";
        if(null!=bodycode){
            fileName+=CodeRepositoryUtil.getValue("unitcode", String.valueOf(bodycode))+"通讯录";
        }
        
       
        List<FUserinfo> unitusers = oaUserinfoMag.getUserUnderUserUnit(filterMap,bodycode);
        List<OaUserinfo> objectList =new ArrayList<OaUserinfo>();
        objectList = this.showUser(unitusers, filterMap);
        
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
     // 获取需要生成Excel的数据
        if (objectList != null) {
            int i = 1;
            for (OaUserinfo o : objectList) {
                FUserinfo fUserinfo=CodeRepositoryUtil.getUserInfoByCode(o.getUsercode());
                Object[] temp= new Object[10];
                temp[0]=String.valueOf(i++);
                temp[1]=CodeRepositoryUtil.getValue("usercode", o.getUsercode());
                temp[2]=DatetimeOpt.convertSqlDate(o.getBirthday());
                temp[3]=o.getWorkplace();
                temp[4]=o.getTelephone();
                temp[5]=o.getMobilephone();
                temp[6]=o.getEmail();
                temp[7]=fUserinfo.getUserdesc();
                temp[8]=o.getRemark();
                chosedList.add(temp);
               
            }
        }
        String[] header = { "编号", "姓名","出生年月日", "工作地点（楼层）","办公电话","手机号码","电子邮箱","用户说明","备注"};// 列头
        BizCommUtil.doPoi2Excel(fileName, header, chosedList);
    }
    
    public  static Map<String,String> buildMap2(Map<String, Object> filterMap){
        Set<String> key = filterMap.keySet();
        Map<String,String> map = new HashMap<String, String>();
        for(Iterator it = key.iterator();it.hasNext();){
            String str = (String)it.next();
            map.put(str, filterMap.get(str).toString());
        }
        return map;
    }
    
    /**
     * 页面显示图片
     * 
     * @return
     * @throws Exception
     */
    public String showImage() throws Exception {
        OaUserinfo info=baseEntityManager.getObjectById(object.getUsercode());
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream out = null;
        
        // 页面上需要显示正式照片与否的参数
        String userPicture = request.getParameter("userPicture");
        
        try {
            
            
            response.setContentType("application/octet-stream");
            out = response.getOutputStream();
            
            //人员上传头像时从数据库读取
           
            byte[] b=null;
            if ("1".equals(userPicture)) {
                b=info.getPictureim();
            } else if ("0".equals(userPicture)) {
                b=info.getHeadpicture();
            } else {
                if ("1".equals(info.getIsusepicture())) {
                   b=info.getPictureim();
                } else {
                   b=info.getHeadpicture();
                }
            }
            
            
            //否则使用默认头像
            if(null == b){
                String basePath = request.getSession().getServletContext().getRealPath("/");
                File  file= new File(basePath+CodeRepositoryUtil.getDataPiece("SYSPARAM", "DefaultImage"));
                FileInputStream fis = new FileInputStream(file);
               
                if (fis != null) {
                    int len = fis.available();
                    b = new byte[len];
                    fis.read(b);
                   
                }
            }
           
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

    
    private String curUrl;
    private OptJspInfo jspInfo;
    
    public String generalOptView() {
        object.setUsercode(s_usercode);
        oaUserinfoMag.getObject(object);
        
        List<OptHtmlFrameInfo> frameList = new ArrayList<OptHtmlFrameInfo>();

        // 用于初始化查看页面默认显示
        curUrl="/bbs/oaBbsTheme!generalOptView.do?s_usercode="+s_usercode;
        jspInfo = new OptJspInfo();
        jspInfo.setTitle("我的帖子");
        jspInfo.setFrameList(frameList);

        return "userinfoDashboard";
    }
    
    
    private JSONObject json;
    /**
     * 讨论版成员信息主题数,等级
     * 
     * @return
     */
    public String getUserinfo() {
        json = new JSONObject();
        String usercode = (String) request.getParameter("usercode");
        object.setUsercode(usercode);

        OaUserinfo dbObject = baseEntityManager.getObject(object);
        json.put("jf", dbObject.getLevelnum()==null?0:dbObject.getLevelnum());//积分
        json.put("dj",dbObject.getUserlevel()==null?"新秀":dbObject.getUserlevel());//等级（具体数值需要看等级字段存储值--可能需字典项转换）
       
        return "options";
    }
    
    /**
     * 查看用户个人资料
     * @return
     */
    public String userinfoView(){
        
        String  usercode = request.getParameter("s_usercode");
        if(StringUtils.isNotBlank(usercode)){
        
            object.setUsercode(usercode);
            super.view();
            
            fUserunit = sysUserManager.getUserPrimaryUnit(usercode);
            //用户职务说明等信息
            request.setAttribute("userrank", fUserunit.getUserrank());
        }
        return "userinfoView";
    }
    
  public String userinfoView2(){
        
        String  usercode = request.getParameter("s_usercode");
        if(StringUtils.isNotBlank(usercode)){
        
            object.setUsercode(usercode);
            super.view();
            
            fUserunit = sysUserManager.getUserPrimaryUnit(usercode);
            //用户职务说明等信息
            request.setAttribute("userrank", fUserunit.getUserrank());
        }
        return "userinfoView2";
    }
    /**
     * 修改个人资料
     * @return
     */
    public String userinfoEdit(){
        
        String  usercode = request.getParameter("s_usercode");
        if(StringUtils.isNotBlank(usercode)){
        
            object.setUsercode(usercode);
            super.edit();
            
        }
        return "userinfoEdit";
    }
    
    public String userinfoEdit2(){
        
        String  usercode = request.getParameter("s_usercode");
        if(StringUtils.isNotBlank(usercode)){
        
            object.setUsercode(usercode);
            super.edit();
            
        }
        return "userinfoEdit2";
    }
    /**
     * 保存个人资料
     * @return
     */
    public String userinfoSave(){
        
        OaUserinfo oaUserinfo = oaUserinfoMag.getObject(object);
        if (oaUserinfo != null) {
            oaUserinfoMag.copyObjectNotNullProperty(oaUserinfo,object);
            object = oaUserinfo;
            this.setUploadPicture(object);
            this.setUploadHeadPicture(object);
        }
        oaUserinfoMag.saveObject(object);
        return "userinfoSave";
    }
    
 public String userinfoSave2(){
        
        OaUserinfo oaUserinfo = oaUserinfoMag.getObject(object);
        if (oaUserinfo != null) {
            oaUserinfoMag.copyObjectNotNullProperty(oaUserinfo,object);
            object = oaUserinfo;
            this.setUploadPicture(object);
            this.setUploadHeadPicture(object);
        }
        oaUserinfoMag.saveObject(object);
        return "userinfoSave2";
    }
    
    /**
     * 设置正式头像
     * @param object
     */
    public void setUploadPicture(OaUserinfo object){
        try{
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
                object.setPictureim(bbuf);
                object.setPicturename(uploadFile_FileName);
            }
                        
         }catch(Exception e){
             e.printStackTrace();
         }
    }
    
    /**
     * 设置头像
     * @param object
     */
    public void setUploadHeadPicture(OaUserinfo object){
        try{
            // 读取上传文件
            if (uploadHeadPicture_ != null) {
                byte[] bbuf = null;
    
                FileInputStream fis = new FileInputStream(uploadHeadPicture_);
                if (fis != null) {
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                }
                fis.close();
                object.setHeadpicture(bbuf);
                object.setHeadpicturename(uploadHeadPicture_FileName);
            }
                        
         }catch(Exception e){
             e.printStackTrace();
         }
        
    }
    
    /**
     * 获取用户联系方式
     * 
     */
    public void getUserConnectWay(){
        OaUserinfo oaUserinfo = oaUserinfoMag.getObject(object);
        String respJson = "{\"workphone\":\"\",\"mobile\":\"\"}";
        if(oaUserinfo != null){
            respJson = "{\"workphone\":\""+StringUtils.defaultString(oaUserinfo.getTelephone())
                    + "\",\"mobile\":\""+StringUtils.defaultString(oaUserinfo.getMobilephone()) +"\"}";
        }
        
        try {
            ServletActionContext.getResponse().getWriter().print(respJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getBodycode() {
        return bodycode;
    }
    public void setBodycode(String bodycode) {
        this.bodycode = bodycode;
    }
    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }
    public List<FUserinfo> getfUserinfos() {
        return fUserinfos;
    }
    public void setfUserinfos(List<FUserinfo> fUserinfos) {
        this.fUserinfos = fUserinfos;
    }
    public List<OaUserinfo> getOaUserinfos() {
        return oaUserinfos;
    }
    public void setOaUserinfos(List<OaUserinfo> oaUserinfos) {
        this.oaUserinfos = oaUserinfos;
    }
    public FUserunit getfUserunit() {
        return fUserunit;
    }
    public void setfUserunit(FUserunit fUserunit) {
        this.fUserunit = fUserunit;
    }
    public List<FUserinfo> getUselist() {
        return uselist;
    }
    public void setUselist(List<FUserinfo> uselist) {
        this.uselist = uselist;
    }
    public String getCurUrl() {
        return curUrl;
    }
    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }
    
   
    public OptJspInfo getJspInfo() {
        return jspInfo;
    }

    public void setJspInfo(OptJspInfo jspInfo) {
        this.jspInfo = jspInfo;
    }
    
    public String getS_usercode() {
        return s_usercode;
    }
    public void setS_usercode(String s_usercode) {
        this.s_usercode = s_usercode;
    }	
    public JSONObject getJson(){
        return json;
    }
    
    public void setJson(JSONObject json){
        this.json=json;
    }
    
    public WsclientManager getWsclientManager() {
        return wsclientManager;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    public void setWsclientManager(WsclientManager wsclientManager) {
        this.wsclientManager = wsclientManager;
    }
    
}
