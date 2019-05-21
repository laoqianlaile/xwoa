package com.centit.webservice.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.centit.app.po.FileinfoFs;
import com.centit.app.service.FileinfoFsManager;
import com.centit.dispatchdoc.po.OptPdfInfo;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.efile.mgt.EfileManager;
import com.centit.oa.po.OaInformationAttachment;
import com.centit.oa.service.OaInformationAttachmentManager;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.sys.util.SysParametersUtils;
import com.centit.webservice.util.WebUtil;

/**
 * 
 * 本地静态内容展示与下载的Servlet.
 * 
 * 
 * @author Administrator
 * @create 2016-2-25
 * @version
 */
public class DownloadServlet extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /** 需要被Gzip压缩的Mime类型. */
    private static final String[] GZIP_MIME_TYPES = { "text/html", "application/xhtml+xml", "text/plain", "text/css",
            "text/javascript", "application/x-javascript", "application/json" };
    
    /** 需要被Gzip压缩的最小文件大小. */
    private static final int GZIP_MINI_LENGTH = 512;
    /**合法的可下载的文档类型PERSONALDOC-个人文档，MAILSTUFF-邮件附件，DOCSTUFF-公文附件，INFOSTUFF-资讯附件*/
    private static final String [] LEGAL_FILE_TYPES = new String[]{"PDF","PERSONALDOC","MAILSTUFF","DOCSTUFF","INFOSTUFF"};
    
    private MimetypesFileTypeMap mimetypesFileTypeMap;
    
    private ApplicationContext applicationContext;
    
    @Override
    public void init() throws ServletException {
     // 初始化mimeTypes, 默认缺少css的定义,添加之.
        mimetypesFileTypeMap = new MimetypesFileTypeMap();
        mimetypesFileTypeMap.addMimeTypes("text/css css");
        applicationContext =  WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }
    
    /**
     * 下载请求,请求参数说明参见类Parameter
     * 1、绝对路径，就是获取容器之外的资源
     *    A、完整的url：http://localhost:8080/mip/download?fileType=PDF&fileId=123&pathType=absolute&storageLocation=disk&download=true
     *    B、最简url：http://localhost:8080/mip/download?fileType=PDF&fileId=123
     * 2、相对路径，就是容器内的资源
     *   http://localhost:8080/mip/download?pathType=relative&contentPath=upload/infos/img/20160229/20160229031827.xml
     * 注意:download = false时，对于浏览器识别的文档，浏览器不做下载处理
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
     // 取得参数
        Parameter param = new Parameter(req);
       
        //检查参数
        String msg = checkRequestParameter(param);
        if(msg != null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
            return;
        }
        //如果是存储在磁盘上的
        if("disk".equals(param.storageLocation)){
            downloadFileOnDisk(param,req,resp);
        }
        //如果是存储在数据库上的
        if("db".equals(param.storageLocation)){
            downloadFileOnDB(param, req, resp);
        }
    }
    /**
     *  下载存储在数据库里文件
     * @param param
     * @param req
     * @param resp
     * @throws IOException 
     */
    private void downloadFileOnDB(Parameter param,HttpServletRequest req, HttpServletResponse resp) throws IOException{
        ContentInfo contentInfo = getFileContent(param.fileType, param.fileId);
       
       // 根据Etag或ModifiedSince Header判断客户端的缓存文件是否有效, 如仍有效则设置返回码为304,直接返回.
       if (!WebUtil.checkIfModifiedSince(req, resp, contentInfo.lastModified)
               || !WebUtil.checkIfNoneMatchEtag(req, resp, contentInfo.etag)) {
           return;
       }
       
    // 设置Etag/过期时间
       WebUtil.setExpiresHeader(resp, WebUtil.ONE_YEAR_SECONDS);
       WebUtil.setLastModifiedHeader(resp, contentInfo.lastModified);
       WebUtil.setEtag(resp, contentInfo.etag);
       
     // 设置MIME类型
       resp.setContentType(contentInfo.mimeType);

       // 设置弹出下载文件请求窗口的Header
       if ("true".equals(param.download)) {
           WebUtil.setFileDownloadHeader(req, resp, contentInfo.fileName);
       }
       
    // 构造OutputStream
       OutputStream output;
       if (checkAccetptGzip(req) && contentInfo.needGzip) {
           // 使用压缩传输的outputstream, 使用http1.1 trunked编码不设置content-length.
           output = buildGzipOutputStream(resp);
       } else {
           // 使用普通outputstream, 设置content-length.
           resp.setContentLength(contentInfo.length);
           output = resp.getOutputStream();
       }
       
       output.write(contentInfo.content);
       output.flush();
    }
    
    
    /**
     * 下载存储在磁盘里的文件
     * @param param
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void downloadFileOnDisk(Parameter param,HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        //获取文件的物理存取路径
        if("absolute".equals(param.pathType)){
            param.contentPath = getAbsoluteFilePath(param.fileType, param.fileId);
        }
        
        // 获取请求内容的基本信息.
        ContentInfo contentInfo = getContentInfo(param.pathType,param.contentPath);
        
     // 根据Etag或ModifiedSince Header判断客户端的缓存文件是否有效, 如仍有效则设置返回码为304,直接返回.
        if (!WebUtil.checkIfModifiedSince(req, resp, contentInfo.lastModified)
                || !WebUtil.checkIfNoneMatchEtag(req, resp, contentInfo.etag)) {
            return;
        }
        
     // 设置Etag/过期时间
        WebUtil.setExpiresHeader(resp, WebUtil.ONE_YEAR_SECONDS);
        WebUtil.setLastModifiedHeader(resp, contentInfo.lastModified);
        WebUtil.setEtag(resp, contentInfo.etag);
        
      // 设置MIME类型
        resp.setContentType(contentInfo.mimeType);

        // 设置弹出下载文件请求窗口的Header
        if ("true".equals(param.download)) {
            WebUtil.setFileDownloadHeader(req, resp, contentInfo.fileName);
        }
        
     // 构造OutputStream
        OutputStream output;
        if (checkAccetptGzip(req) && contentInfo.needGzip) {
            // 使用压缩传输的outputstream, 使用http1.1 trunked编码不设置content-length.
            output = buildGzipOutputStream(resp);
        } else {
            // 使用普通outputstream, 设置content-length.
            resp.setContentLength(contentInfo.length);
            output = resp.getOutputStream();
        }

        // 高效读取文件内容并输出,然后关闭input file
        FileUtils.copyFile(contentInfo.file, output);
        output.flush();
    }
    /**
     * 检查请求参数
     * @param param
     * @return
     */
    private String checkRequestParameter(Parameter param){
        String msg = null;
      //如果是相对路径，那么contentPath是必要
        if ("relative".equals(param.pathType) && StringUtils.isBlank(param.contentPath)) {
            msg = "参数contentPath是必须的";
            return msg;
        }
        //如果是绝对路径，那么fileType和fileId是必要
        else if("absolute".equals(param.pathType) && (StringUtils.isBlank(param.fileType) 
                || StringUtils.isBlank(param.fileId))) {
            msg = "参数fileType和fileId是必须的";
            return msg;
        }
        
        if("absolute".equals(param.pathType) && !verifyFileType(param.fileType)){
            msg = "未知的文件类型";
            return msg;
        }
        return msg;
    }
    
    /**
     * 检查浏览器客户端是否支持gzip编码.
     */
    private static boolean checkAccetptGzip(HttpServletRequest request) {
        // Http1.1 header
        String acceptEncoding = request.getHeader("Accept-Encoding");

        return StringUtils.contains(acceptEncoding, "gzip");
    }

    /**
     * 验证文档类型是否合法
     * @param fileType
     * @return
     */
    private boolean verifyFileType(String fileType){
        boolean res = false;
        for(String legalFileType : LEGAL_FILE_TYPES){
            if(legalFileType.equals(fileType)){
                res = true;
                break;
            }
        }
        return res;
    }
    /**
     * 设置Gzip Header并返回GZIPOutputStream.
     */
    private OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Vary", "Accept-Encoding");
        return new GZIPOutputStream(response.getOutputStream());
    }
    /**
     * 获取文件的绝对路径
     * @param docType
     * @param docFileId
     * @return
     */
    private String getAbsoluteFilePath(String docType,String docFileId){
        String path = null;
        if("PDF".equals(docType)){
            OptPdfInfoManager optPdfInfoMgr = (OptPdfInfoManager) applicationContext.getBean("optPdfInfoManager");
            OptPdfInfo optPdfInfo = optPdfInfoMgr.getObjectById(Long.valueOf(docFileId));
            path = optPdfInfo.getFilePath();
        }else if("PERSONALDOC".equals(docType) || "MAILSTUFF".equals(docType)){
            FileinfoFsManager fileInfoFsMgr = (FileinfoFsManager) applicationContext.getBean("fileinfoFsManager");
            FileinfoFs fileInfoFs = fileInfoFsMgr.getObjectById(docFileId);
            path = fileInfoFsMgr.getFullFilePath(fileInfoFs.getPath());
        }else if("INFOSTUFF".equals(docType)){
            OaInformationAttachmentManager oaInformationAttachmentManager =  (OaInformationAttachmentManager) applicationContext.getBean("oaInformationAttachmentManager");
            OaInformationAttachment infoAttach =  oaInformationAttachmentManager.getObjectById(Long.valueOf(docFileId));
            path = SysParametersUtils.getInfosHome() + File.separator + infoAttach.getPath();
        }
       return path;
    }
    
    /**
     * 获取文件内容
     * @param docType
     * @param docFileId
     * @return
     */
    private ContentInfo getFileContent(String docType,String docFileId){
        ContentInfo contentInfo = new ContentInfo();
        
        //获取数据库公文附件信息
        if("DOCSTUFF".equals(docType)){
            OptStuffInfoManager optStuffInfoManager =  (OptStuffInfoManager) applicationContext.getBean("optStuffInfoManager");
            OptStuffInfo optStuffInfo = optStuffInfoManager.getObjectById(docFileId);
            
            String absolutePath = SysParametersUtils.getWorkflowAffixHome() + optStuffInfo.getStuffpath();
            contentInfo.content = EfileManager.read(absolutePath);
            contentInfo.length = contentInfo.content.length;
            contentInfo.fileName = optStuffInfo.getFilename();
            contentInfo.lastModified = optStuffInfo.getUploadtime().getTime();
            contentInfo.etag = "W/\"" + contentInfo.lastModified + "\"";
            contentInfo.mimeType = mimetypesFileTypeMap.getContentType(contentInfo.fileName);

            if ((contentInfo.length >= GZIP_MINI_LENGTH) && ArrayUtils.contains(GZIP_MIME_TYPES, contentInfo.mimeType)) {
                contentInfo.needGzip = true;
            } else {
                contentInfo.needGzip = false;
            }
        }
       
        return contentInfo;
    }
  
    /**
     * 创建Content基本信息.
     */
    private ContentInfo getContentInfo(String pathType,String contentPath) {
        ContentInfo contentInfo = new ContentInfo();
        String realFilePath = null;
        File file = null;
        if("relative".equals(pathType)){
            realFilePath = getServletContext().getRealPath(contentPath);
            file = new File(realFilePath);
        }else if("absolute".equals(pathType)){
            file = new File(contentPath);
        }

        contentInfo.file = file;
        contentInfo.contentPath = contentPath;
        contentInfo.fileName = file.getName();
        contentInfo.length = (int) file.length();

        contentInfo.lastModified = file.lastModified();
        contentInfo.etag = "W/\"" + contentInfo.lastModified + "\"";

        contentInfo.mimeType = mimetypesFileTypeMap.getContentType(contentInfo.fileName);

        if ((contentInfo.length >= GZIP_MINI_LENGTH) && ArrayUtils.contains(GZIP_MIME_TYPES, contentInfo.mimeType)) {
            contentInfo.needGzip = true;
        } else {
            contentInfo.needGzip = false;
        }

        return contentInfo;
    }
    
   /**
    * 
    * 定义一个参数信息
    * 
    * @author Administrator
    * @create 2016-2-29
    * @version
    */
   static class Parameter{
       protected String fileId;//资源的唯一表示
       protected String fileType;//具体类型见变量LEGAL_FILE_TYPES
       protected String pathType = "absolute"; //默认在容器之外，即绝对路径
       protected String contentPath;//资源路径
       protected String download = "true";//默认作下载处理
       protected String storageLocation = "disk";//存储位置，默认是磁盘,取值为disk，db
       
       public Parameter(HttpServletRequest req){
           fileId = req.getParameter("fileId");//文件id
           fileType = req.getParameter("fileType");//文件类型
           if(StringUtils.isNotBlank(req.getParameter("pathType"))){
               pathType = req.getParameter("pathType");
           }
           contentPath = req.getParameter("contentPath");
           if(StringUtils.isNotBlank(req.getParameter("download"))){
               download = req.getParameter("download");
           }
           if(StringUtils.isNotBlank(req.getParameter("storageLocation"))){
               storageLocation = req.getParameter("storageLocation");
           }
       }
   }
    /**
    * 定义Content的基本信息.
    */
   static class ContentInfo {
       protected String contentPath;
       protected File file;
       protected byte[] content;
       protected String fileName;
       protected int length;
       protected String mimeType;
       protected long lastModified;
       protected String etag;
       protected boolean needGzip;
   }
}
