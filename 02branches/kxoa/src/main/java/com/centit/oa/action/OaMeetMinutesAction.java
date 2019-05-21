package com.centit.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.AddressBookRelectionId;
import com.centit.oa.po.OaMeetApply;
import com.centit.oa.po.OaMeetMinutes;
import com.centit.oa.po.OaMeetinfo;
import com.centit.oa.po.VoaMeetMinute;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.OaMeetApplyManager;
import com.centit.oa.service.OaMeetMinutesManager;
import com.centit.oa.service.OaMeetinfoManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;

public class OaMeetMinutesAction extends BaseEntityDwzAction<OaMeetMinutes>implements ServletResponseAware {
    private static final Log log = LogFactory.getLog(OaMeetMinutesAction.class);

    // private static final ISysOptLog sysOptLog =
    // SysOptLogFactoryImpl.getSysOptLog("optid");

    private static final long serialVersionUID = 1L;
    private OaMeetMinutesManager oaMeetMinutesMag;

    public void setOaMeetMinutesManager(OaMeetMinutesManager basemgr) {
        oaMeetMinutesMag = basemgr;
        this.setBaseEntityManager(oaMeetMinutesMag);
    }

    private SysUserManager sysUserManager;
    private SysUnitManager sysUnitManager;
    private OaMeetApplyManager oaMeetApplyManager;
    private OaMeetinfoManager oaMeetinfoManager;
    private AddressBookRelectionManager addressBookRelectionManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    
 
    private  String userValue;//获取参会人员usercode
    private  String csuserValue;//获取参会人员usercode
    HttpServletResponse response;
    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;

    }
  
  private List<VoaMeetMinute> vobjList;

    public List<VoaMeetMinute> getVobjList() {
        return vobjList;
    }
    public void setVobjList(List<VoaMeetMinute> vobjList) {
        this.vobjList = vobjList;
    }
    @Override
    /**
     * 列表数据
     */
    public String list() {
        try {
            // unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            FUserDetail fuser = ((FUserDetail) getLoginUser());

            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            //获取list时根据当前登录人员来匹配中间人员表达到权限查看
            vobjList = oaMeetMinutesMag.getMaxlist(filterMap, pageDesc,fuser.getUsercode());
            if(null!=vobjList&&vobjList.size()>0){
                totalRows = vobjList.size();
            }
//            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑
     */
    public List<OaMeetApply> nolist;
    private String edittype;
    private List<OaMeetinfo> oaMeetinfolist;
   

    @Override
    public String edit() {
        super.edit();
        if (StringUtils.isBlank(object.getVersion())) {
            // 新增版本号则为0
            object.setVersion("0");
        }
        // 会议室列表:启用
        Map<String, Object> filterMapHys = new HashMap<String, Object>();
        filterMapHys.put("isuse", "T");
        oaMeetinfolist = oaMeetinfoManager.listObjects(filterMapHys);
        
        //获取参会人员
        initUsers();
        
        //编辑的时候自动去人员中间表去获取usercode
        
        if (StringUtils.isNotBlank(object.getDjid())) {
          //参会人员
            List<AddressBookRelection>  listuser=addressBookRelectionManager.getUserlist(object.getDjid(),"0");
            if(listuser!=null && listuser.size()>0){       
            userValue="";
          for(int i=0;i<listuser.size();i++){
            String value=listuser.get(i).getShareman()+",";
            userValue+=value;
          }
            }
          if(StringUtils.isNotBlank(userValue)){
          userValue=userValue.substring(0, userValue.length()-1); 
          }
          
        //抄送人员
          List<AddressBookRelection>  cslistuser=addressBookRelectionManager.getUserlist(object.getDjid(),"1");
          if(cslistuser!=null && cslistuser.size()>0){       
              csuserValue="";
        for(int i=0;i<cslistuser.size();i++){
          String value=cslistuser.get(i).getShareman()+",";
          csuserValue+=value;
        }
          }
        if(StringUtils.isNotBlank(csuserValue)){
            csuserValue=csuserValue.substring(0, csuserValue.length()-1); 
        }
       }
        
        //流程内的会议纪要登记,根据djid进行查询赋值
        if("T".equals(edittype)){
            
            OaMeetApply oaMeetApply = oaMeetApplyManager.getObjectById(object.getCid().getDjid());// 业务数据
            object.setTitle(oaMeetApply.getTitle());
            object.setMeetingNo(oaMeetApply.getMeetingNo());
            object.setDoCreater(oaMeetApply.getDoCreater());
            object.setDoDepno(oaMeetApply.getDoDepno());
            if(oaMeetApply.getBegTime()==null){
                object.setBegTime(oaMeetApply.getDoBegTime());
            }else{
                object.setBegTime(oaMeetApply.getBegTime());
            }
            if(oaMeetApply.getEndTime()==null){
                object.setEndTime(oaMeetApply.getDoEndTime());
            }else{
                object.setEndTime(oaMeetApply.getEndTime());
            }       
          
        }
        
        return EDIT;
    }
    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray userjson =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
    }
    /*
     * 当前登录人员usercode
     */
    @SuppressWarnings("unused")
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    /**
     * 保存业务数据
     * 
     * @return
     */
    /**
     * 附件材料文件，供上传使用
     */

    private InputStream inputStream;
    private String filename;
    private File docFile_;
    private String docFile_FileName;
   private List<OaMeetMinutes> versionlist;
    public String savePermitReg() {
        try {
            // 保存
            //新增的则先查处表中业务编码是否存在最大version
            versionlist=oaMeetMinutesMag.getversionbyid(object.getDjid());
            if(versionlist!=null&&versionlist.size()!=0){
                object.setVersion(versionlist.get(0).getVersion());
                
            }else{
                object.setVersion("0");
            }
            // 根据上传的附件来判断是否更新版本
            OaMeetMinutes info = oaMeetMinutesMag.getObjectById(object
                    .getCid());
            byte[] olderfile = null;
            if (info != null) {
                olderfile = info.getDocFile();               
            }
            else{
                info = new OaMeetMinutes();  
            }
            
            oaMeetMinutesMag.copyObjectNotNullProperty(info, object);
      
            object = info;
            if (StringUtils.isBlank(object.getDjid())) {
                // 会议室发布状态初始值state 为0
                object.setState("0");

            } else {
                object.setMotifyTime(new Date(System.currentTimeMillis()));
            }

            // 附件上传
            if (docFile_ != null) {
                byte[] bbuf = null;

                FileInputStream fis = new FileInputStream(docFile_);
                if (fis != null) {
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                }
                fis.close();
                object.setDocFile(bbuf);
                object.setDocFileName(docFile_FileName);

                if (info != null) {              
                    if (olderfile != object.getDocFile()&&olderfile!=null) {
                        // 版本号+1
                        object.setVersion(String.valueOf(Integer
                                .parseInt(object.getVersion()) + 1));
                        object.setState("1");
                    }
                }
            }
            OaMeetMinutes infonew = oaMeetMinutesMag.getObjectById(object
                    .getCid());
            if (infonew == null) {
                info = new OaMeetMinutes();  
            }
            
            oaMeetMinutesMag.copyObjectNotNullProperty(info, object);
      
            object = info;
            
            oaMeetMinutesMag.saveObject(object);
            // 把参会人员记录到中间表m_address_book_relection
            addressBookRelectionManager.deleteuser(object.getDjid());
            
          //把参会人员记录到中间表oa_address_book_relection            
            this.saveMeetPersions(userValue,"0");          
            //把抄送人员记录到中间表oa_address_book_relection
            this.saveMeetPersions(csuserValue,"1");                
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return this.list();
    }

    /**
     * 把人员记录到中间表oa_address_book_relection 
     * @param userValue2
     * @param biztype
     */
    private void saveMeetPersions(String userValue2, String biztype) {
        // TODO Auto-generated method stub
        addressBookRelectionManager.deleteuser(object.getDjid(),biztype);
        if(StringUtils.isNotBlank(userValue2)){           
            
            String[] csvalues=userValue2.split(",");//分割字符串   
            for (int i=0;i<csvalues.length;i++){
                AddressBookRelectionId cid=new AddressBookRelectionId();
                cid.setAddrbookid(object.getDjid());
                cid.setBizType(biztype);
                cid.setShareman(csvalues[i]);
                AddressBookRelection csinfo=addressBookRelectionManager.getObjectById(cid);
                if(csinfo==null){
                    csinfo=new AddressBookRelection();
                }
                csinfo.setAddrbookid(object.getCid().getDjid());
                csinfo.setShareman(csvalues[i]);
                csinfo.setBizType(biztype);//抄送人员biztype=1,参会人员biztype=0
                addressBookRelectionManager.saveObject(csinfo);
            }
        }
    }

    /**
     * 下载文件，查看
     * 
     * @return
     * @throws IOException
     */
    private InputStream stuffStream;

    public InputStream getStuffStream() {
        return stuffStream;
    }

    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }

    public String downStuffInfo() throws IOException {
        OaMeetMinutes info = baseEntityManager.getObjectById(object.getCid());
        if (null == info) {

            return "download";
        }
        byte[] bt = null;
        String fn = info.getDocFileName();
        bt = info.getDocFile();
        try {

            if (bt != null) {
                setStuffStream(new ByteArrayInputStream(bt));
            }else {
                postAlertMessage("",response);
                return null;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
//        response.setContentType("image/gif"); 
//        ServletOutputStream out=response.getOutputStream(); 
//        out.write(bt); 
//        out.flush();
        this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
        return "download";

    }
    protected void postAlertMessage(String msg, HttpServletResponse response) {
        String alertCoding = "GBK";
        ServletOutputStream sos;
        String str = "<script language=\"JavaScript\""
                + " type=\"text/JavaScript\" charset=\"" + alertCoding + "\">"
                + "javascript:alert('" + msg + "');history.back(-1);"
                + " </script>";
        response.setContentType("text/html; charset=" + alertCoding);
        try {
            sos = response.getOutputStream();
//            int strSize = (int) str.length();
            byte[] b =str.getBytes();
            sos.write(b);
            sos.flush();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    // 查看-历史列表
    private List<OaMeetMinutes> versionList;

    /**
     * 查看 根据djid version
     * 
     * @return
     * @throws IOException
     */
    public String view() {
        super.view();
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        versionList = oaMeetMinutesMag.listObjects(filterMap);
        totalRows = pageDesc.getTotalRows();
     
        oaMeetMinutes=oaMeetMinutesMag.getObjectById(object.getCid());
        request.setAttribute("oaMeetMinutes", oaMeetMinutes);
        return VIEW;

    }
    /**
     * 查看 根据djid 
     * 
     * @return
     * @throws IOException
     */
    private OaMeetMinutes oaMeetMinutes;
        public String viewAll(){
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            versionList = oaMeetMinutesMag.listObjects(filterMap);
            totalRows = pageDesc.getTotalRows();
            //根据djid，倒叙后取出最大版本号的信息
            if(versionList!=null && versionList.size()>0){
                oaMeetMinutes=versionList.get(0);              
            }
           
            request.setAttribute("oaMeetMinutes", oaMeetMinutes);
            return VIEW;
 
        }
      

     

        public String delete() {
            super.delete();
    
            return "delete";
        }

        /*************************************setter/getter*********************************************************/

        public AddressBookRelectionManager getAddressBookRelectionManager() {
            return addressBookRelectionManager;
        }

        public void setAddressBookRelectionManager(
                AddressBookRelectionManager addressBookRelectionManager) {
            this.addressBookRelectionManager = addressBookRelectionManager;
        }

        public OaMeetinfoManager getOaMeetinfoManager() {
            return oaMeetinfoManager;
        }

        public void setOaMeetinfoManager(OaMeetinfoManager oaMeetinfoManager) {
            this.oaMeetinfoManager = oaMeetinfoManager;
        }

        public SysUserManager getSysUserManager() {
            return sysUserManager;
        }

        public SysUnitManager getSysUnitManager() {
            return sysUnitManager;
        }

        public void setSysUserManager(SysUserManager sysUserManager) {
            this.sysUserManager = sysUserManager;
        }

        public void setSysUnitManager(SysUnitManager sysUnitManager) {
            this.sysUnitManager = sysUnitManager;
        }

        public OaMeetApplyManager getOaMeetApplyManager() {
            return oaMeetApplyManager;
        }

        public void setOaMeetApplyManager(OaMeetApplyManager oaMeetApplyManager) {
            this.oaMeetApplyManager = oaMeetApplyManager;
        }

        public String getUserValue() {
            return userValue;
        }

        public void setUserValue(String userValue) {
            this.userValue = userValue;
        }

        public String getCsuserValue() {
            return csuserValue;
        }

        public void setCsuserValue(String csuserValue) {
            this.csuserValue = csuserValue;
        }
        public List<OaMeetinfo> getOaMeetinfolist() {
            return oaMeetinfolist;
        }

        public void setOaMeetinfolist(List<OaMeetinfo> oaMeetinfolist) {
            this.oaMeetinfolist = oaMeetinfolist;
        }

        public String getEdittype() {
            return edittype;
        }

        public void setEdittype(String edittype) {
            this.edittype = edittype;
        }

        public List<OaMeetApply> getNolist() {
            return nolist;
        }

        public void setNolist(List<OaMeetApply> nolist) {
            this.nolist = nolist;
        }

        public List<OaMeetMinutes> getVersionList() {
            return versionList;
        }

        public void setVersionList(List<OaMeetMinutes> versionList) {
            this.versionList = versionList;
        }
        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public InputStream getInputStream() {
            return inputStream;
        }
      

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }



        
        public File getDocFile_() {
            return docFile_;
        }
        public void setDocFile_(File docFile_) {
            this.docFile_ = docFile_;
        }
        public String getDocFile_FileName() {
            return docFile_FileName;
        }
        public void setDocFile_FileName(String docFile_FileName) {
            this.docFile_FileName = docFile_FileName;
        }
        public OaMeetMinutes getOaMeetMinutes() {
            return oaMeetMinutes;
        }

        public void setOaMeetMinutes(OaMeetMinutes oaMeetMinutes) {
            this.oaMeetMinutes = oaMeetMinutes;
        }
        public OaPowerrolergroupManager getOaPowerrolergroupManager() {
            return oaPowerrolergroupManager;
        }
        public void setOaPowerrolergroupManager(OaPowerrolergroupManager oaPowerrolergroupManager) {
            this.oaPowerrolergroupManager = oaPowerrolergroupManager;
        }
 
}
