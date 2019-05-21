package com.centit.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaCarinfo;
import com.centit.oa.po.OaDriverInfo;
import com.centit.oa.service.OaDriverInfoManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;

public class OaDriverInfoAction extends BaseEntityExtremeAction<OaDriverInfo> {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaDriverInfoAction.class);
    private static final long serialVersionUID = 1L;
    private OaDriverInfoManager oaDriverInfoMag;
    
    private File uploadFile_;
    private String uploadFile_FileName;

    public void setOaDriverInfoManager(OaDriverInfoManager basemgr) {
        oaDriverInfoMag = basemgr;
        this.setBaseEntityManager(oaDriverInfoMag);
    }

    private List<OaCarinfo> oaCarinfos;

    public List<OaCarinfo> getNewOaCarinfos() {
        return this.oaCarinfos;
    }

    public void setNewOaCarinfos(List<OaCarinfo> oaCarinfos) {
        this.oaCarinfos = oaCarinfos;
    }
    
    public String save() {
        try {
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            if (StringUtils.isBlank(object.getNo())) {// 录入人员,录入时间
                object.setCreater(user.getUsercode());
                object.setCreatertime(DatetimeOpt.currentUtilDate());
            }
            // 读取上传文件（），存在MeetinfPicture字段
            if (uploadFile_ != null) {
                byte[] bbuf = null;

                FileInputStream fis = new FileInputStream(uploadFile_);
                if (fis != null) {
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                }
                fis.close();
                object.setPersonalPhoto(bbuf);
                object.setPhotoname(uploadFile_FileName);
            }
            return super.save();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
    public String edit(){
        
        OaDriverInfo driver = oaDriverInfoMag.getObject(object);
        
        int currentYear = DatetimeOpt.getYear(new Date());
        // 数据库中保存的司机信息还没有驾龄，则根据发证时间自动计算
        if(null != driver && null != driver.getReleaseDate() && null == driver.getBeenDriving()){
            
            int releaseYear = DatetimeOpt.getYear(driver.getReleaseDate());
            driver.setBeenDriving(Long.valueOf(currentYear - releaseYear));
        }
        
        if(null != driver && null != driver.getBirthDate() && null == driver.getAge()){
            
            int birthYear = DatetimeOpt.getYear(driver.getBirthDate());
            driver.setAge(new Long(currentYear - birthYear));
            
        }
        
        request.setAttribute("currentDate", DatetimeOpt.currentDate());
        oaDriverInfoMag.copyObject(object, driver);
        return EDIT;
    }
   
    @Override
    public String list() {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> filterMap = convertSearchColumn(request
                    .getParameterMap());
            // state启用标识 ，默认查询在用司机
//            filterMap.put("state", "T");
//            
//            if (("true").equals(filterMap.get("isBoth"))) {
//                filterMap.remove("state");
//            }
            
            filterMap.put("NP_state", true);//默认查询启用，和无使用状态司机信息
            if (("true").equals(filterMap.get("isBoth"))) {
                filterMap.remove("NP_state");
                filterMap.put("NP_state_W",true);                
            }
            
            PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
            objList = baseEntityManager.listObjects(filterMap, pageDesc);
            setbackSearchColumn(filterMap);
            totalRows = pageDesc.getTotalRows();
            //return LIST;
            return "listV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 页面显示图片
     * 
     * @return
     * @throws Exception
     */
    public String getUserImgFromByte() throws Exception {
        OaDriverInfo info = baseEntityManager.getObjectById(object.getNo());
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream out = null;
        try {
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            // 输出图片
            out.write(info.getPersonalPhoto());
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

    public File getUploadFile_() {
        return uploadFile_;
    }

    public void setUploadFile_(File uploadFile_) {
        this.uploadFile_ = uploadFile_;
    }

    public String getUploadFile_FileName() {
        return uploadFile_FileName;
    }

    public void setUploadFile_FileName(String uploadFile_FileName) {
        this.uploadFile_FileName = uploadFile_FileName;
    }

    /**
     * 批量删除功能
     * @return
     */
    public String deleteIds() {
        //批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
        for (String no : ids.split(",")) {
            //存放作修改字段
            object.setNo(no);
            super.delete();    
         }
        }
        return this.list();
    } 
    
  
}
