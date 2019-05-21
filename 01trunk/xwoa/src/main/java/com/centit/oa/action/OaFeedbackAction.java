package com.centit.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaFeedback;
import com.centit.oa.service.OaFeedbackManager;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;

public class OaFeedbackAction extends BaseEntityDwzAction<OaFeedback> {
    private static final Log log = LogFactory.getLog(OaFeedbackAction.class);
    private static final long serialVersionUID = 1L;
    private OaFeedbackManager oaFeedbackMag;
    private SysUnitManager sysUnitManager;
    private SysUserManager sysUserManager;
    private List<FUnitinfo> unitlist;
    private String flag;//标记编辑操作

    public void setOaFeedbackManager(OaFeedbackManager basemgr) {
        oaFeedbackMag = basemgr;
        this.setBaseEntityManager(oaFeedbackMag);
    }
    
private String show_type;
    public String getShow_type() {
    return show_type;
}


public void setShow_type(String show_type) {
    this.show_type = show_type;
}


    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }


    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
    }


    public static Log getLog() {
        return log;
    }
    private List<FUnitinfo> units;
    
    private List<FUserinfo> users;

    
    public List<FUnitinfo> getUnits() {
        return units;
    }

    public void setUnits(List<FUnitinfo> units) {
        this.units = units;
    }

    public List<FUserinfo> getUsers() {
        return users;
    }

    public void setUsers(List<FUserinfo> users) {
        this.users = users;
    }
    public String listFK(){
        try {
            FUserDetail user = (FUserDetail) getLoginUser();
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();
            unitlist = sysUnitManager.getAllSubUnits(sParentUnit);

            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("isopen", "1");
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaFeedbackMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            return "listFK";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 情况汇总查看(查看已回复的反馈)
     */
     public String listHZ(){
         try {
             FUserDetail user = (FUserDetail) getLoginUser();
             FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
             String sParentUnit = dept.getUnitcode();
             unitlist = sysUnitManager.getAllSubUnits(sParentUnit);

             @SuppressWarnings("unchecked")
             Map<Object, Object> paramMap = request.getParameterMap();
             resetPageParam(paramMap);
             Map<String, Object> filterMap = convertSearchColumn(paramMap);
             //add by lq (情况汇总显示已回复的反馈)
             filterMap.put("isAnswer", "1");
             Limit limit = ExtremeTableUtils.getLimit(request);
             PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
             objList = oaFeedbackMag.listObjects(filterMap, pageDesc);
             totalRows = pageDesc.getTotalRows();
             return "listHZ";
         } catch (Exception e) {
             e.printStackTrace();
             return ERROR;
         }
     }
    /**
     * 列表数据
     */
    public String list() {
        try {
            FUserDetail user = (FUserDetail) getLoginUser();
            FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
            String sParentUnit = dept.getUnitcode();
            unitlist = sysUnitManager.getAllSubUnits(sParentUnit);

            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            // filterMap.put("biztype", "F");
            filterMap.put("isAnswer","0");

            if (("true").equals(filterMap.get("isBoth"))) {
                filterMap.remove("isAnswer");
            }
            filterMap.put("reception", user.getUsercode());
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            objList = oaFeedbackMag.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 编辑
     */
 
    @Override
    public String edit() {
        super.edit();
        if (StringUtils.isBlank(object.getDjid())) {
            object.setDjid(oaFeedbackMag.getNextKey());
        }
        // 获取全部在用的部门
        units = CodeRepositoryUtil.getAllUnits("T");
    
        if(StringUtils.isNotBlank(object.getDepno())){
            //获取人员列表
            users = CodeRepositoryUtil
                    .getSortedUnitUsers(object.getDepno());
        }
        return EDIT;
    }
  
           
           /**
            * 附件材料文件，供上传使用
            */

           private InputStream inputStream;

           public InputStream getInputStream() {
               return inputStream;
           }

           public void setInputStream(InputStream inputStream) {
               this.inputStream = inputStream;
           }

           private String filename;

           public String getFilename() {
               return filename;
           }

           public void setFilename(String filename) {
               this.filename = filename;
           }

           private File stuffFile_;

           private String stuffFile_FileName;

           /**
            * 保存回复
            * @return
            */
           public String saveAndSubmit(){
               this.savePermitReg();
               object.setReplyTime(new Date(System.currentTimeMillis()));
               object.setIsAnswer("1");
               oaFeedbackMag.saveObject(object);
               return this.list();
           }
           /**
            *是否公开
            * @return
            */
           public String saveOpen(){
               String isopen = request.getParameter("isopen");
               
              /* this.savePermitReg();
               object.setIsopen(isopen);
               oaFeedbackMag.saveObject(object);*/
               
               //changed by lq 这边只是更新字段，用上面的方法将好多字段重新赋值了比如isanswer
               //存放作修改字段
               OaFeedback newle =new OaFeedback();
               newle.setDjid(object.getDjid());
               newle.setIsopen(isopen);
               //获取原对象，避免使用object
               OaFeedback le=  oaFeedbackMag.getObject(newle);
               if (le != null) {
                   oaFeedbackMag.copyObjectNotNullProperty(le, newle);
                   newle = le;
                   oaFeedbackMag.saveObject(newle);
               }
               
               return this.listHZ();
           }
           
           /**
            *是否公开批量
            * @return
            */
           public String saveOpenIds(){
               String isopen = request.getParameter("isopen");
               //批量ids
               String ids = request.getParameter("ids");
               if (StringUtils.isNotEmpty(ids)) {
               for (String djid : ids.split(",")) {
                   //存放作修改字段
                   OaFeedback newle =new OaFeedback();
                   newle.setDjid(djid);
                   newle.setIsopen(isopen);
                   //获取原对象，避免使用object
                   OaFeedback le =  oaFeedbackMag.getObject(newle);
                   if (le != null) {
                       oaFeedbackMag.copyObjectNotNullProperty(le, newle);
                       newle = le;
                       oaFeedbackMag.saveObject(newle);
                   }
                }
               }
               return this.listHZ();
           }
           
           public String savePermitReg() {
               try {
                   // 保存
                   // 根据上传的附件来判断是否更新版本
                   OaFeedback info = oaFeedbackMag.getObjectById(object.getDjid());

                   if (info == null) {
                       info = new OaFeedback();
                   }
                   oaFeedbackMag.copyObjectNotNullProperty(info, object);
                   object = info;
                   FUserDetail fuser = ((FUserDetail) getLoginUser());
                       
                        //匿名提交不保存creater
                       if("1".equals(object.getIsanonymous())){
                        
                           object.setCreatertime(new Date(System.currentTimeMillis()));
                       }else{
                           object.setCreater(fuser.getUsercode());
                           object.setCreatertime(new Date(System.currentTimeMillis()));
                           object.setIsanonymous("0");
                       }
                   // 附件上传
                   if (stuffFile_ != null) {
                       byte[] bbuf = null;

                       FileInputStream fis = new FileInputStream(stuffFile_);
                       if (fis != null) {
                           int len = fis.available();
                           bbuf = new byte[len];
                           fis.read(bbuf);
                       }
                       fis.close();
                       object.setFeedFile(bbuf);
                       object.setFeedFileName(stuffFile_FileName);

                    
                   }
                   object.setIsAnswer("0");//反馈提交时，给出初始值0，表示未回复状态
                   oaFeedbackMag.saveObject(object);
               } catch (Exception e) {
                   log.error(e.getMessage());
                   e.printStackTrace();
               }
               return this.list();
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
               OaFeedback info = baseEntityManager.getObjectById(object.getDjid());
               if (null == info) {

                   return "download";
               }
               byte[] bt = null;
               String fn = info.getFeedFileName();
               bt = info.getFeedFile();
               try {

                   if (bt != null) {
                       setStuffStream(new ByteArrayInputStream(bt));
                   }
               } catch (Exception e) {
                   log.error(e.getMessage(), e);
                   saveError(e.getMessage());
               }
               this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
               return "download";

           }
           /**
            * 删除操作
            */
        @Override
        public String delete(){
            super.delete();
            return LIST;
        }

        public File getStuffFile_() {
            return stuffFile_;
        }

        public void setStuffFile_(File stuffFile_) {
            this.stuffFile_ = stuffFile_;
        }

        public String getStuffFile_FileName() {
            return stuffFile_FileName;
        }

        public void setStuffFile_FileName(String stuffFile_FileName) {
            this.stuffFile_FileName = stuffFile_FileName;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setSysUnitManager(SysUnitManager sysUnitManager) {
            this.sysUnitManager = sysUnitManager;
        }


        public void setSysUserManager(SysUserManager sysUserManager) {
            this.sysUserManager = sysUserManager;
        }

    
}
