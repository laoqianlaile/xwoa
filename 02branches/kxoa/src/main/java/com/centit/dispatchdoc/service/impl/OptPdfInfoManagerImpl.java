package com.centit.dispatchdoc.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.dispatchdoc.dao.OptPdfInfoDao;
import com.centit.dispatchdoc.po.OptPdfInfo;
import com.centit.dispatchdoc.service.OptPdfInfoManager;
import com.centit.powerruntime.dao.OptStuffInfoDao;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.IdGen;
import com.centit.sys.util.SysParametersUtils;
import com.centit.webservice.util.AsposeWordsUtil;
import com.centit.webservice.util.PDFUtil;

public class OptPdfInfoManagerImpl extends BaseEntityManagerImpl<OptPdfInfo>
     implements OptPdfInfoManager{
  /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Logger log = Logger.getLogger(OptPdfInfoManagerImpl.class);
    
    private OptPdfInfoDao optPdfInfoDao;
    
    private OptStuffInfoDao optStuffInfoDao ;
    
    
    public void setOptPdfInfoDao(OptPdfInfoDao optPdfInfoDao) {
        this.optPdfInfoDao = optPdfInfoDao;
        setBaseDao(optPdfInfoDao);
    }

    public void setOptStuffInfoDao(OptStuffInfoDao baseDao) {
        this.optStuffInfoDao = baseDao;
    }
    
    
    /**
     * 这一步比较耗费内存和时间，
     * 不要再查询的时候做此操作。
     * 如果没有实时性的要求的话，可以在保存数据后，异步创建pdf，然后用消息中间件进行推送提示
     * 注意 初始的nodeinstId为0
     */
    @Override
    public OptPdfInfo createPDF(String djId,Long currNodeInstId,String formHtmlUrl) throws Exception{
        //创建一个临时文件夹，最后统一清理
        String tempDirPath = createTempDir();
        
        //将表单html转换成word
        String tempHtmlToWordPath = convertHtmlToWord(formHtmlUrl,tempDirPath);
        
        //同步附件word到磁盘
        List<String> stuffWordPathsOnDisk = getPathsOfStuff(djId);
       
        //合并表单word和附件word
        String tempWordPathAfterMerging = tempHtmlToWordPath;
        tempWordPathAfterMerging = mergingFormWordAndStuffs(tempHtmlToWordPath, stuffWordPathsOnDisk, tempDirPath);
        
        //将合并的word转换成pdf，放在临时目录下
        String pdfPath = convertWordToPdf(tempWordPathAfterMerging, tempDirPath);
        
        OptPdfInfo optPdfInfo = new OptPdfInfo(djId,currNodeInstId, pdfPath, pdfPath.replace(tempDirPath+File.separator, ""), tempDirPath);
        //获取表单生成文档的页数
        int formDocPageCount = AsposeWordsUtil.getDocPageCount(tempHtmlToWordPath);
        optPdfInfo.setFormDocPageCount(formDocPageCount);
        
        return optPdfInfo;
    }

    /**
     * 并将历史图层给添加上去
     *
     * @return
     * @throws Exception
     */
    @Override
    public OptPdfInfo addLayerOnPDF(OptPdfInfo optPdfInfo) throws Exception{
        //获取历史图层
        List<String> historyImages = getHistoryImages(optPdfInfo.getDjId(),optPdfInfo.getFormDocPageCount());
        if(historyImages != null && !historyImages.isEmpty()){
            String tempDirPath = optPdfInfo.getTempDirPath();
            if(StringUtils.isBlank(tempDirPath)){
                tempDirPath = createTempDir();
            }
            String pdfPath = getTempFileAbsolutePath(tempDirPath, 2);
            PDFUtil.markLocalImage(optPdfInfo.getFilePath(),optPdfInfo.getSecretKey(),historyImages,pdfPath);
            log.info("添加历史图层成功,路径："+pdfPath);
            optPdfInfo.setFilePath(pdfPath);
            optPdfInfo.setCreateTime(new Date());
            optPdfInfo.setTempDirPath(tempDirPath);
            optPdfInfo.setFileName(pdfPath.replace(optPdfInfo.getTempDirPath()+File.separator, ""));
        }
        
        return optPdfInfo;
    }
    
    /**
     * 转换pdf成swf
     * @param exeToolpath
     * @param pdfPath
     * @param outDir
     * @param outName
     * @throws Exception
     */
    @Override
    public void convertPdfToSwf(String exeToolpath,String pdfPath,String outDir,String outName) throws Exception{
        //生成swf文件,这里会开启一个子进程，阻塞线程继续执行，因为转换swf需要用到临时pdf，
        //而线程最后会清除临时的pdf，这时转换的子进程可能没有执行完成,所以这里就涉及到线程与子进程之间的通信
         String swfPath = outDir + File.separator + outName;
         PDFUtil.convertPdfToSwf(exeToolpath, pdfPath, swfPath);
         log.info("将pdf转换成swf成功:"+swfPath);
    }
    
    /**
     * 加密pdf
     * @param optPdfInfo
     * @return
     * @throws Exception
     */
    @Override
    public OptPdfInfo encryptPdf(OptPdfInfo optPdfInfo,String outDir,String outName) throws Exception{
        String secretKey = IdGen.randomString(6);//产生6位随机数作为密钥
        String encryptPdfPath = PDFUtil.encryptPdf(optPdfInfo.getFilePath(),outDir,outName,secretKey);//pdf加密并放到真实目录下
        optPdfInfo.setSecretKey(secretKey);
        optPdfInfo.setFilePath(encryptPdfPath);
        optPdfInfo.setCreateTime(new Date());
        optPdfInfo.setFileName(outName);
        log.info("将pdf加密成功:"+encryptPdfPath+"密钥："+secretKey);
        return optPdfInfo;
    }
    /**
     * 下载图层
     * @param layerUrl
     * @param pdfStoreDir
     * @return
     */
    @Override
    public List<String> downloadLayer(String layerUrl,String pdfStoreDir){
        //下载图层到本地与pdf同级目录
        String downloadPdfLayerPath = PDFUtil.downloadPdfLayer(layerUrl, pdfStoreDir);
        
        File pdfLayer = new File(downloadPdfLayerPath);
        List<String> layerPathArr = new ArrayList<String> ();
        //判断下载后的图层是否是压缩包
        if(pdfLayer.getName().endsWith(".zip")){
            layerPathArr = PDFUtil.ectract(downloadPdfLayerPath, pdfLayer.getParent());//对下载后的图层解压处理
        }else{
            layerPathArr.add(downloadPdfLayerPath);
        }
        return layerPathArr;
    }
    
    @Override
    public Long getNextLongSequence() {
        return optPdfInfoDao.getNextLongSequence("SEQ_OPT_PDF_INFO");
    }

    @Override
    public OptPdfInfo findNewestPdfInfo(String djId) {
        int bizType = getBizTypeForPdf(djId);
        return optPdfInfoDao.findNewestPdfInfo(djId, bizType);
    }
    /**
     * 获取业务类型
     * @param djId
     * @return
     */
    @Override
    public int getBizTypeForPdf(String djId) {
        String type = djId.replaceAll("\\d+", "");
        if("FW".equals(type)){
            return 1;
        }
        if("SW".equals(type)){
            return 2;
        }
        if("QB".equals(type)){
            return 3;
        }
        if("CARSQ".equals(type)){
            return 4;
        }
        //可以补充
        return 0;
    }
    
    @Override
    public OptPdfInfo findPdfInfoBy(String djId, Long nodeInstId) {
        int bizType = getBizTypeForPdf(djId);
        return optPdfInfoDao.findPdfInfoBy(djId, nodeInstId,bizType);
    }
    
    @Override
    public OptPdfInfo findPdfInfoBy(String djId, Long nodeInstId,String userCode) {
        int bizType = getBizTypeForPdf(djId);
        return optPdfInfoDao.findPdfInfoBy(djId, nodeInstId,bizType,userCode);
    }


    @Override
    public String getPdf2SwfToolPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/upload/tools/pdf2swf.exe");
    }
    
    @Override
    public String assignPdfStorePathOnDisk(String djId,Long nodeInstd){
        String dir = getPdfParentPath(djId,nodeInstd);
        return dir +  File.separator + appendFileSuffix(IdGen.uuid(), 2);
    }
    
    /**
     * 清除临时文件
     * @param path 绝对路径
     * @return
     */
    @Override
    public  boolean clearTempFile(String path) {
        if(path == null || "".equals(path)){
            return false;
        }
        File file = new File(path);

        // 若文件不存在，则无需删除
        if (!file.exists()) return true;

        // 删除文件
        return FileUtils.deleteQuietly(file);
    }
    private String getPdfParentPath(String djId, Long nodeInstd) {
        String root = SysParametersUtils.getUploadPdfHome();//pdf存放的跟目录
        
        String baseDirPath = root + File.separator + djId + File.separator + nodeInstd;
        File dir = null;
        String resDirPath = "";
        
        do{
             resDirPath = baseDirPath + File.separator + System.currentTimeMillis();
             dir = new File(resDirPath);
        }while(dir.exists());
        
        return resDirPath;
    }
    
    /**
     * 获取附件路径的集合
     * @param djId
     * @return
     */
    private List<String> getPathsOfStuff(String djId){
        int bizType = getBizTypeForPdf(djId);
        List<OptStuffInfo> stuffInfoArr = new ArrayList<OptStuffInfo>();
        List<String> wordPathsOnDisk = new ArrayList<String>();
        //获取数据库附件信息
        switch (bizType) {
        case 1: //如果是发文
            OptStuffInfo optStuffInfo =  optStuffInfoDao.getStuffInfoByArchiveType(djId, "zw");
            stuffInfoArr.add(optStuffInfo);
            break;
        case 2://收文
            //收文暂时不需要合并附件，因此注释掉
           /* List<OptStuffInfo> res = optStuffInfoDao.getStuffInfoListByFileType(djId, "1");
            if(res != null && !res.isEmpty()){
                stuffInfoArr = res;
            }*/
            break;
        case 3: //如果是签报
            OptStuffInfo optStuffInfoQB =  optStuffInfoDao.getStuffInfoByArchiveType(djId, "SQWS");
            stuffInfoArr.add(optStuffInfoQB);
            break;     
        default:
            break;
        }
        for(OptStuffInfo optStuffInfo : stuffInfoArr) {
            if(optStuffInfo != null && optStuffInfo.getStuffpath() != null && !"".equals(optStuffInfo.getStuffpath())){
                String docName = optStuffInfo.getFilename();
                //只将word类型的文档同步到磁盘上
               if(docName != null && (docName.toLowerCase().endsWith(".doc")||docName.toLowerCase().endsWith(".docx"))){
                   String optStuffPath = SysParametersUtils.getWorkflowAffixHome()+ optStuffInfo.getStuffpath();
                   wordPathsOnDisk.add(optStuffPath);
               }
            }
        }
        return wordPathsOnDisk;
    }
    
    /**
     * 于2016.6.6后附件被迁移到磁盘上，所以不需要再同步了
     * 同步附件到磁盘
     * @param djId
     * @return
     * @throws IOException 
     */
    @Deprecated
    private List<String> syncStuffToDisk(String djId,String tempDirPath) throws IOException{
        List<String> tempWordPathsOnDisk = new ArrayList<String>();
        List<OptStuffInfo> stuffInfoArr = new ArrayList<OptStuffInfo>();
        
        int bizType = getBizTypeForPdf(djId);
        
        //获取数据库附件信息
        switch (bizType) {
        case 1: //如果是发文
            OptStuffInfo optStuffInfo =  optStuffInfoDao.getStuffInfoByArchiveType(djId, "zw");
            stuffInfoArr.add(optStuffInfo);
            break;
        case 2://收文
            List<OptStuffInfo> res = optStuffInfoDao.getStuffInfoListByFileType(djId, "1");
            if(res != null && !res.isEmpty()){
                stuffInfoArr = res;
            }
            break;
        default:
            break;
        }
        
        for(OptStuffInfo optStuffInfo : stuffInfoArr) {
            if(optStuffInfo != null && optStuffInfo.getStuffcontent() != null){
                String docName = optStuffInfo.getFilename();
                //只将word类型的文档同步到磁盘上
               if(docName != null && (docName.toLowerCase().endsWith(".doc")||docName.toLowerCase().endsWith(".docx"))){
                   String optStuffPath = AsposeWordsUtil.saveDocToDisk(optStuffInfo.getStuffcontent(), getTempFileAbsolutePath(tempDirPath, 1));
                   tempWordPathsOnDisk.add(optStuffPath);
               }
            }
        }
        log.info("同步附件到磁盘成功,附件个数："+tempWordPathsOnDisk.size());
        return tempWordPathsOnDisk;
    }
    
    /**
     * 
     * @param name
     * @param fileType 1-word 2-pdf
     * @return
     */
    private String appendFileSuffix(String name,int fileType){
        String suffix = "";
        switch (fileType) {
        case 1:
            suffix = ".doc";
            break;
        case 2:
            suffix = ".pdf";
            break;
        case 3:
            suffix = ".swf";
            break;
        case 4:
            suffix = ".html";
            break;
        default:
            break;
        }
        
        return name + suffix;
    }
   
    /**
     * 将word转换成pdf
     * @param wordPath
     * @param tempDirPath
     * @return
     * @throws Exception
     */
    private String convertWordToPdf(String wordPath,String tempDirPath) throws Exception{
        String wordToPdfPath = getTempFileAbsolutePath(tempDirPath,2);
        AsposeWordsUtil.convertToPdf(wordPath,wordToPdfPath, false);
        log.info("word转pdf到临时目录成功,路径"+wordToPdfPath);
        return wordToPdfPath;
    }
    /**
     * 合并表单word和附件
     * @param formWordPath
     * @param tempStuffWordPathsOnDisk
     * @param tempDirPath
     * @return
     * @throws Exception
     */
    private String mergingFormWordAndStuffs(String formWordPath,List<String> stuffWordPathsOnDisk,String tempDirPath) throws Exception{
        String wordPathAfterMerging = formWordPath;
        if(stuffWordPathsOnDisk != null && !stuffWordPathsOnDisk.isEmpty()){
            List<String> srcWords = new ArrayList<String> ();
            srcWords.add(formWordPath);
            srcWords.addAll(stuffWordPathsOnDisk);
            
            wordPathAfterMerging = getTempFileAbsolutePath(tempDirPath,1);
            AsposeWordsUtil.mergingWord(srcWords, wordPathAfterMerging, false);
            log.info("合并word表单和附件word成功，路径"+wordPathAfterMerging);
        }
        return wordPathAfterMerging;
    }
    /**
     * 将html转换成word
     * @param formHtmlUrl
     * @return
     * @throws Exception
     */
    private String convertHtmlToWord(String formHtmlUrl,String tempDirPath) throws Exception{
        String formHtml = getFormHtml(formHtmlUrl,tempDirPath);
        String htmlToWordPath = getTempFileAbsolutePath(tempDirPath,1);
        AsposeWordsUtil.convertHtmlToWord(formHtml,htmlToWordPath);
        log.info("表单Html转成Word成功,路径："+htmlToWordPath);
        return htmlToWordPath;
    }
    
    /**
     * 创建一个临时目录，存放临时文件，到时方便清理
     */
    private String createTempDir(){
        File dir = new File(SysParametersUtils.getUploadTempHome() + File.separator + IdGen.uuid());
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        String tempDirPath = dir.getAbsolutePath();
        log.info("生成一个临时目录："+tempDirPath);
        return tempDirPath;
    }
    /**
     * 获取临时文件的绝对路径
     * @param tempDir
     * @param fileType
     * @return
     */
    private String getTempFileAbsolutePath(String tempDirPath,int fileType){
        String fileName = appendFileSuffix(IdGen.uuid(),fileType);
        return tempDirPath + File.separator + fileName;
    }
    /**
     * 获取当前流程的所有拟文单上的历史图层,
     * @param djId
     * @return
     * @throws Exception 
     */
    private List<String> getHistoryImages(String djId, int formDocPageCount) throws Exception{
        List<String> images = new ArrayList<String>();
        
        String root = SysParametersUtils.getUploadPdfHome() + File.separator +djId;
        
        List<String> imageNames = new ArrayList<String>();
        //图片名称是以页号命名的
        for(int i=1;i<=formDocPageCount;i++){
            imageNames.add(i+".png");
        }
        File dir = new File(root);
        images.addAll(filterPngImage(dir,imageNames));
        return images;
        
    }
    
    /**
     * 递归过滤png图片
     * @param dir
     * @return
     */
    private List<String> filterPngImage(File dir,List<String> imageNames){
        List<String> images = new ArrayList<String>();
        File[] files = dir.listFiles();
        if(files==null)return images;// 判断目录下是不是空的
        
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory()){// 判断是否文件夹
                images.addAll(filterPngImage(files[i],imageNames));  
            }else{
                if(imageNames.contains(files[i].getName().toLowerCase())){
                    images.add(files[i].getPath());
                }
            }
        }
        return images;
    }

    private String getFormHtml(String url,String tempDirPath) {
        String formHtml = "";
        String htmlPath = getTempFileAbsolutePath(tempDirPath, 4);
        
        InputStream urlInstream = null;
        ByteArrayOutputStream baos = null;
        
        FileOutputStream fos = null;
        try {
            URL formHtmlUrl = new URL(url);
            urlInstream = formHtmlUrl.openStream();
            baos = new ByteArrayOutputStream();
            int i;
            while ((i = urlInstream.read()) != -1) {  
                baos.write(i);
            }  
            formHtml = baos.toString("UTF-8"); 
            log.info(formHtmlUrl);
            String startPoint = "<!--WordStartExport-->";
            String endPoint = "<!--WordEndExport-->";
            if(formHtml.indexOf(startPoint)>-1 && formHtml.indexOf(endPoint)>-1){
                formHtml = formHtml.substring(formHtml.indexOf(startPoint)+startPoint.length(),formHtml.indexOf(endPoint));
            }
            formHtml = wraperFormHtml(formHtml);
            
            //将html输出
            fos = new FileOutputStream(htmlPath);
            fos.write(formHtml.getBytes("UTF-8"));
        } catch (MalformedURLException e) {
            log.error(url+"构建成URL对象失败:"+e.getMessage());
            return null;
        }catch (IOException e) {
            log.error(url+"打开失败:"+e.getMessage());
            return null;
        }catch (Exception e){
            log.error("错误:"+e.getMessage());
            return null;
        }finally{
            AsposeWordsUtil.closeQuietly(fos);
            AsposeWordsUtil.closeQuietly(baos);
            AsposeWordsUtil.closeQuietly(urlInstream);
        }
        return htmlPath;
    }

    private String wraperFormHtml(String formHtml){
        StringBuffer documentHtml = new StringBuffer();
        documentHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
        documentHtml.append("<html lang='zh-cn'><head>");
        documentHtml.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>");
        documentHtml.append("</head><body>");
        documentHtml.append(formHtml);
        documentHtml.append("</body></html>");
        return documentHtml.toString().replace("#e8e8e8", "#000");
    }
    @Override
    public String getFWFormHtmlUrl(String context, String usercode,String djId,
            String flowInstId) {
        /**
         * strus2 url解析规则/namespace开头，/actionname结尾，保证这个中间可以随便添加
         */
        return  removeUrlLastSlash(context) + "/dispatchdoc/anonymous/dispatchDoc!viewDispatchDocInfoTemplate.do?djId="+djId+"&flowInstId="+flowInstId +"&usercode="+usercode+"&isPdf=T";
    }

    @Override
    public String getSWFormHtmlUrl(String context, String usercode,
            String djId) {
        /**
         * strus2 url解析规则/namespace开头，/actionname结尾，保证这个中间可以随便添加
         */
        return removeUrlLastSlash(context) + "/dispatchdoc/anonymous/incomeDoc!registerDoOrReadView.do?vewtype=T&djId="+djId+"&usercode="+usercode+"&isPdf=T";
    }
    
    @Override
    public String getQBFormHtmlUrl(String context, String usercode,
            String djId) {
        /**
         * strus2 url解析规则/namespace开头，/actionname结尾，保证这个中间可以随便添加
         */
        return removeUrlLastSlash(context) + "/powerruntime/anonymous/optApply!viewInfo.do?vewtype=T&djId="+djId+"&usercode="+usercode+"&isPdf=T";
    }
    
    @Override
    public String getCARFormHtmlUrl(String context, String usercode,
            String djId) {
        /**
         * strus2 url解析规则/namespace开头，/actionname结尾，保证这个中间可以随便添加
         */
        return removeUrlLastSlash(context) + "/oa/anonymous/oaCarApply!view.do?vewtype=T&djId="+djId+"&usercode="+usercode+"&isPdf=T";
    }
    
    /**
     * 去除最后一个斜杠如http://localhost:9080/xxx/
     * @param url
     * @return
     */
    private String removeUrlLastSlash(String url){
        if(url.endsWith("/")){
            return url.substring(0, url.length()-1);
        }
        return url;
    }
    
    @Override
    public String getSQFormHtmlUrl(String context, String usercode,String moduleType,
            String djId) {
        if(StringUtils.isEmpty(moduleType)){
            return removeUrlLastSlash(context) + "/powerruntime/anonymous/optApply!viewInfo.do?vewtype=T&djId="+djId+"&usercode="+usercode+"&isPdf=T";
        }else{
            return removeUrlLastSlash(context) + "/oa/anonymous/"+moduleType+"!view.do?vewtype=T&djId="+djId+"&usercode="+usercode+"&isPdf=T";
        }
    }

    /**
     * 新增是生成pdf
     * @param oaCarApply
     * @param nodeName
     * @param nodeInstId
     * @param userCode
     * @param exeToolpath
     * @param formHtmlUrl
     */
   public void createPDF(String  djId,String nodeName,Long nodeInstId,String userCode,String exeToolpath,String formHtmlUrl){
       OptPdfInfo optPdfInfo = null;
       try {
          String type = djId.replaceAll("\\d+", "");
          log.info("<<<<<<<<<<<<<<<<<<<<<<<<"+CodeRepositoryUtil.getValue("optTypeName", type)+"djId:"+djId+"生成PDF开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
          String pdfStorePath = this.assignPdfStorePathOnDisk(djId, nodeInstId);
          File pdfStoreFile = new File(pdfStorePath);
           //创建pdf
          optPdfInfo = this.createPDF(djId,nodeInstId,formHtmlUrl);
           //转成swf
          this.convertPdfToSwf(exeToolpath, optPdfInfo.getFilePath(), pdfStoreFile.getParent(), pdfStoreFile.getName().replace("pdf", "swf"));
           //加密
          optPdfInfo = this.encryptPdf(optPdfInfo, pdfStoreFile.getParent(), pdfStoreFile.getName());
          
          optPdfInfo.setUserCode(userCode);
          optPdfInfo.setBizType(String.valueOf(this.getBizTypeForPdf(djId)));
          optPdfInfo.setNodeName(nodeName);
          optPdfInfo.setNodeInstId(nodeInstId);
          optPdfInfo.setId(this.getNextLongSequence());
          this.saveObject(optPdfInfo);
          log.info("<<<<<<<<<<<<<<<<<<<<<<<<"+CodeRepositoryUtil.getValue("optTypeName", type)+"djId:"+djId+"生成PDF结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
       } catch (Exception e) {
           log.error("生成PDF异常："+e.getMessage());
       } finally{
           if(optPdfInfo!=null && StringUtils.isNotBlank(optPdfInfo.getTempDirPath())){
               this.clearTempFile(optPdfInfo.getTempDirPath());
           }
       }
   }
    
}
