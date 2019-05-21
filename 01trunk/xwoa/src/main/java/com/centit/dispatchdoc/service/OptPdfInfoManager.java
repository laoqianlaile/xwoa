package com.centit.dispatchdoc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.centit.core.service.BaseEntityManager;
import com.centit.dispatchdoc.po.OptPdfInfo;

public interface OptPdfInfoManager extends BaseEntityManager<OptPdfInfo>{
    /**
     * 创建一个PDF
     * @param djId
     * @param currNodeInstId 当前节点
     * @param formHtmlUrl 表单url
     * @return
     * @throws Exception
     */
    public OptPdfInfo createPDF(String djId,Long currNodeInstId,String formHtmlUrl) throws Exception;
    
    public OptPdfInfo createPDF2(String djId,Long currNodeInstId,String formHtmlUrl) throws Exception;
    
    /**
     * 在PDF上添加图层
     * @param optPdfInfo
     * @return
     * @throws Exception
     */
    public OptPdfInfo addLayerOnPDF(OptPdfInfo optPdfInfo) throws Exception;
    
    /**
     * 讲pdf转成swf
     * @param exeToolpath
     * @param pdfPath
     * @param outDir
     * @param outName
     * @throws Exception
     */
    public void convertPdfToSwf(String exeToolpath,String pdfPath,String outDir,String outName) throws Exception;
    
    /**
     * 加密pdf
     * @param optPdfInfo
     * @return
     * @throws Exception
     */
    public OptPdfInfo encryptPdf(OptPdfInfo optPdfInfo,String outDir,String outName) throws Exception;
    
    /**
     * 下载图层
     * @param layerUrl 图层url
     * @param pdfStoreDir pdf存储目录
     * @return
     */
    public List<String> downloadLayer(String layerUrl,String pdfStoreDir);
    
    
    public String getFWFormHtmlUrl(String context,String usercode,String djId,String flowInstId);
    
    public String getSWFormHtmlUrl(String context,String usercode,String djId);
    
    public String getCARFormHtmlUrl(String context,String usercode,String djId);
    
    public String getQBFormHtmlUrl(String context,String usercode,String djId);
    
    public String getSQFormHtmlUrl(String context, String usercode, String moduleType,
            String djId);
    
    
    public Long getNextLongSequence();
    
    /**
     * 获取最新的pdf信息
     * @param djId 业务id
     * @return
     */
    public OptPdfInfo findNewestPdfInfo(String djId);
    
    /**
     * 1-发文 2-收文
     * @param djId
     * @return
     */
    public int getBizTypeForPdf(String djId);
    
    /**
     * 给pdf指定一个磁盘路径
     * @param djId
     * @param nodeInstd
     * @return
     */
    public String assignPdfStorePathOnDisk(String djId,Long nodeInstd);
    
    /**
     * 获取流程中某个节点的pdf信息
     * @param djId
     * @param nodeInstd
     * @return
     */
    public OptPdfInfo findPdfInfoBy(String djId,Long nodeInstd);
   
    
    /**
     * 获取pdf2swf.exe路径
     * @param request
     * @return
     */
    public String getPdf2SwfToolPath(HttpServletRequest request);
    /**
     * 会签   pdf处理
     * @param djId
     * @param nodeInstId
     * @param userCode
     * @return
     */
    public OptPdfInfo findPdfInfoBy(String djId, Long nodeInstId, String userCode);
    
    /**
     * 清除临时文件
     * @param path
     * @return
     */
    public boolean clearTempFile(String path);

 
    public void createPDF(String  djId,String nodeName,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl);
    
}
