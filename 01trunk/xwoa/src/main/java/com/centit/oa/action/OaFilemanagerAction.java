package com.centit.oa.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.efile.mgt.EfileManager;
import com.centit.efile.model.EfileStore;
import com.centit.oa.po.OaFilemanager;
import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.service.OaFilemanagerManager;
import com.centit.oa.service.OaInformationAttachmentManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.SysParametersUtils;


public class OaFilemanagerAction extends BaseEntityDwzAction<OaFilemanager> {
    /**
     *
     */
    private static final long serialVersionUID = -2551187666778246797L;
    private OaFilemanagerManager oaFilemanagerMag;
    
    
    private File[] document;
    private String[] documentFileName;
    private InputStream stuffStream;//供下载使用
    private String filename;
    private  String s_infoType;//文件类型
    
    private OaInformationAttachmentManager oaInformationAttachmentManager;
    
    public void setOaFilemanagerManager(OaFilemanagerManager basemgr) {
        oaFilemanagerMag = basemgr;
        this.setBaseEntityManager(oaFilemanagerMag);
    }
    
    @Override
    public String view(){
        //保存查看日志
        OaFilemanager o=oaFilemanagerMag.getObject(object);
        super.view();
        List<OaInformationAttachment> docAttachments = oaInformationAttachmentManager.findDocAttachments(o.getNo());
        request.setAttribute("docAttachments", docAttachments);
        return VIEW;
        
    }

    @Override
    public String save() {
        OaFilemanager oaFilemanager = oaFilemanagerMag.getObjectById(object
                .getNo());
        if (oaFilemanager == null) {
            oaFilemanager = new OaFilemanager();
        }
        
        oaFilemanagerMag.copyObjectNotNullProperty(oaFilemanager, object);
        
        // 有效期在页面被清空后，值为null，若用copyObjectNotNullProperty，则会重新赋值，且值为数据库中的值
        if(null == object.getValidDate()){
            oaFilemanager.setValidDate(null);
        }
        object = oaFilemanager;
        FUserDetail user = (FUserDetail) getLoginUser();
        
        if (StringUtils.isBlank(object.getNo())) {
            object.setNo(oaFilemanagerMag.getNextkey(""));
            object.setCreatetime(DatetimeOpt.currentUtilDate());
            object.setCreater(user.getUsercode());
            object.setState("T");
        }
        saveFile();
        oaFilemanagerMag.saveObject(object);
        return list();
    }
    @Override
    public String edit() {
        List<OaInformationAttachment> docAttachments = oaInformationAttachmentManager.findDocAttachments(object.getNo());
        request.setAttribute("docAttachments", docAttachments);
        return super.edit();
    }
    @Override
    public String built(){
        super.built();
        //初始化数据
        FUserDetail user = (FUserDetail) getLoginUser();
        object.setCreatetime(DatetimeOpt.currentUtilDate());
        object.setCreater(user.getUsercode());
        object.setReleaseDate(DatetimeOpt.currentUtilDate());
        return EDIT;
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public String list() {
        try {
            FUserDetail user=(FUserDetail)getLoginUser();
            String userCode=user.getUsercode();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("state", "T");//默认查询状态
            PageDesc pageDesc =null;
            pageDesc = makeSearchPageDesc(request);
            this.pageDesc = pageDesc;
            objList = baseEntityManager.listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);

            
            if(null!=objList&&objList.size()>0){
                for(OaFilemanager files:  objList){
                    List<OaInformationAttachment> docAttachments = oaInformationAttachmentManager.findDocAttachments(files.getNo());
                    files.setDocAttachments(docAttachments);
                }
            }
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    
    public void removeAttachment(){
        boolean f = false;
        try{
            Long attachmentId = Long.valueOf(request.getParameter("attachmentId"));
            OaInformationAttachment attachment = oaInformationAttachmentManager.getObjectById(attachmentId);
            oaInformationAttachmentManager.deleteObjectById(attachment.getId());
            //删除磁盘上的文件
            String absolutePath = SysParametersUtils.getInfosHome() + attachment.getPath();
            EfileManager.remove(absolutePath);
            f = true;
        }catch(Exception e){
            log.error("删除附件异常:"+e.getMessage());
        }
       
        PrintWriter out = null;;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(f);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        out.flush();  
        out.close();  
    }
    
    
    //附件下载--服务器端下载
    public String downLocalStuffInfo() throws IOException {
        
        OaInformationAttachment attachment = oaInformationAttachmentManager.getObjectById(Long.valueOf(request.getParameter("no")));
        if (null == attachment) {
            return "download";
        }
        String absolutePath = SysParametersUtils.getInfosHome() + attachment.getPath();
        File file = new File(absolutePath);
        
        
        InputStream stuffStream = FileUtils.openInputStream(file);
       
        try {
            setStuffStream(stuffStream);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }
        this.setFilename(new String(attachment.getFileName().getBytes("GBK"), "ISO8859-1"));
        
        return "download";
    }

    public  void saveFile(){
        try {
            String dirPath = SysParametersUtils.getInfosHome() + File.separator + object.getNo();
            if(document!=null){
                for(int i=0;i<document.length;i++){
                    EfileStore efileStore = new EfileStore(document[i], documentFileName[i],  dirPath);
                    EfileManager.store(efileStore);
                    
                    OaInformationAttachment attachment = new OaInformationAttachment();
                    attachment.setFileName(documentFileName[i]);
                    attachment.setFileSize(efileStore.getContentLength());
                    attachment.setType(OaInformationAttachment.TYPE_DOCUMENT);
                    attachment.setPath(efileStore.getAbsolutePath().replace(SysParametersUtils.getInfosHome(), ""));
                    attachment.setRefId(object.getNo());
                    attachment.setId(oaInformationAttachmentManager.getNextSequence());
                    oaInformationAttachmentManager.saveObject(attachment);
                }
            }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    /**
     * 删除功能
     * @return
     */
    public String delete() {
        //批量ids
        String ids = request.getParameter("no");
        if (StringUtils.isNotEmpty(ids)) {
        for (String no : ids.split(",")) {
            //存放作修改字段
            object.setNo(no);
            super.delete(); 
           
            List<OaInformationAttachment> docAttachments = oaInformationAttachmentManager.findDocAttachments(object.getNo());
            for(OaInformationAttachment fileItem:docAttachments){
                oaInformationAttachmentManager.deleteObjectById(fileItem.getId());
            }
            List<OaInformationAttachment> videoAttachments = oaInformationAttachmentManager.findVideoAttachments(object.getNo());
            for(OaInformationAttachment fileItem:videoAttachments){
                oaInformationAttachmentManager.deleteObjectById(fileItem.getId());
            }
            
            //删除磁盘上的文件
            String absolutePath = SysParametersUtils.getInfosHome() + File.separator + no;
            EfileManager.remove(absolutePath);
         }
        }
        return list();
    } 
    public PageDesc makeSearchPageDesc(HttpServletRequest request) {

        String pagesize = request.getParameter("numPerPage");
        String pageno = request.getParameter("pageNum");

        String offset = request.getParameter("pager.offset");
        int pageSize = StringUtils.isNumeric(pagesize) ? Integer
                .parseInt(pagesize) : 15;
        int pageNo = Integer.parseInt(StringUtils.isNumeric(pageno) ? pageno
                : "1");

        if (StringUtils.isNotBlank(offset) && !StringUtils.isNotBlank(pageno)
                && StringUtils.isNumeric(offset)) {
            int offsetNum = Integer.parseInt(offset);

            pageNo = (offsetNum / pageSize) + 1;
        }

        PageDesc pageDesc = new PageDesc(pageNo, pageSize);

        return pageDesc;

    }
    
    
    /* get set 方法 beg */
    public File[] getDocument() {
        return document;
    }

    public void setDocument(File[] document) {
        this.document = document;
    }

    public String[] getDocumentFileName() {
        return documentFileName;
    }

    public void setDocumentFileName(String[] documentFileName) {
        this.documentFileName = documentFileName;
    }

    public InputStream getStuffStream() {
        return stuffStream;
    }

    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }

    public OaInformationAttachmentManager getOaInformationAttachmentManager() {
        return oaInformationAttachmentManager;
    }

    public void setOaInformationAttachmentManager(
            OaInformationAttachmentManager oaInformationAttachmentManager) {
        this.oaInformationAttachmentManager = oaInformationAttachmentManager;
    }

    public OaFilemanagerManager getOaFilemanagerMag() {
        return oaFilemanagerMag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getS_infoType() {
        return s_infoType;
    }

    public void setS_infoType(String s_infoType) {
        this.s_infoType = s_infoType;
    }
    
    /* get set 方法 end */
}
    
    