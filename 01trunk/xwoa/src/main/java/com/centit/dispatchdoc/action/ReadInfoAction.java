package com.centit.dispatchdoc.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.ReadInfo;
import com.centit.dispatchdoc.po.VReadInfo;
import com.centit.dispatchdoc.service.ReadInfoManager;
import com.centit.dispatchdoc.service.VReadInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.SysUserManager;

public class ReadInfoAction extends BaseEntityExtremeAction<ReadInfo> implements ServletResponseAware{
    private static final Log log = LogFactory.getLog(ReadInfoAction.class);
    private static final long serialVersionUID = 1L;
    private ReadInfoManager readInfoMag;
    private VReadInfoManager vReadInfoManager;
    public void setvReadInfoManager(VReadInfoManager vReadInfoManager) {
        this.vReadInfoManager = vReadInfoManager;
    }
    private File incomeDocFile_;
    private String incomeDocFile_FileName;
    private SysUserManager sysUserManager; 
    public List<VReadInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<VReadInfo> infoList) {
        this.infoList = infoList;
    }
    private List<VReadInfo> infoList = new ArrayList<VReadInfo>();
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }

    /**
     * 阅件管理编辑
     */
    @Override
    public String edit() {
        if (StringUtils.isBlank(object.getReadNo())) { // 新增
            FUserDetail fuser = ((FUserDetail) getLoginUser());
            //
            object.setCreateDate(new Date());// 登记日期,默认为当前时间
            object.setCreateName(fuser.getUsercode());// 登记人
        }else{            
            object=readInfoMag.getObjectById(object.getReadNo());
        }
        return EDIT;
    }

    /**
     * 保存阅件信息 其中涉及附件保存
     */
    @Override
    public String save() {
        ReadInfo readInfo = readInfoMag.getObjectById(object.getReadNo());
        if(readInfo==null){
            readInfo=new ReadInfo();            
        }
        readInfoMag.copyObjectNotNullProperty(readInfo, object);
        object = readInfo;
        if(StringUtils.isBlank(object.getReadNo())){
            object.setReadNo(readInfoMag.getNextkey(""));
        }        
        if (incomeDocFile_ != null) {
            try {
                FileInputStream fis = new FileInputStream(incomeDocFile_);
                if (fis != null) {
                    byte[] bbuf = null;
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                    object.setIncomeDocFile(bbuf);
                    object.setIncomeDocName(incomeDocFile_FileName);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        object.setCreateDate(DatetimeOpt.currentUtilDate());
        readInfoMag.saveObject(object);       
        return this.list();
    }
    @SuppressWarnings("unchecked")
    public String list(){
        //this.list();
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
           
//            FUserDetail loginUser = ((FUserDetail) getLoginUser());
//            FUserunit dept = sysUserManager.getUserPrimaryUnit(loginUser.getUsercode());
//            filterMap.put("readInfoRole", dept.getUserrank());
            infoList = vReadInfoManager.listObjects(filterMap, pageDesc);
            
            totalRows = pageDesc.getTotalRows();

            this.pageDesc = pageDesc;
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        //return "listView";       
    }
    
    @SuppressWarnings("unchecked")
    public String listView(){
        //this.list();
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            PageDesc pageDesc = makePageDesc();
           
            FUserDetail loginUser = ((FUserDetail) getLoginUser());
            FUserunit dept = sysUserManager.getUserPrimaryUnit(loginUser.getUsercode());
            filterMap.put("readInfoRole", dept.getUserrank());
            infoList = vReadInfoManager.listObjects(filterMap, pageDesc);
            
            totalRows = pageDesc.getTotalRows();

            this.pageDesc = pageDesc;
            return "listView";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        //return "listView";       
    }
    private InputStream inputStream;
    private String filename;
    /**
     * 正文材料下载
     * @return
     */
    public String downloadstuff(){       
        ReadInfo readInfo = readInfoMag.getObjectById(object.getReadNo());
       String fn = readInfo.getIncomeDocName();
       try {
           if(readInfo.getIncomeDocFile()!=null){
           inputStream = new ByteArrayInputStream(readInfo.getIncomeDocFile());
           }
       } catch (Exception e) {
           log.error(e.getMessage(), e);
           saveError(e.getMessage());
       }
       try {
        this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
       return "download";
        
    }
    
    

    public void setReadInfoManager(ReadInfoManager basemgr) {
        readInfoMag = basemgr;
        this.setBaseEntityManager(readInfoMag);
    }

    public File getIncomeDocFile_() {
        return incomeDocFile_;
    }

    public void setIncomeDocFile_(File incomeDocFile_) {
        this.incomeDocFile_ = incomeDocFile_;
    }

    public String getIncomeDocFile_FileName() {
        return incomeDocFile_FileName;
    }

    public void setIncomeDocFile_FileName(String incomeDocFile_FileName) {
        this.incomeDocFile_FileName = incomeDocFile_FileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    HttpServletResponse response;
    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

}
