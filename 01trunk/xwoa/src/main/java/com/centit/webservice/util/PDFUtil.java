package com.centit.webservice.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.IdGen;
import com.centit.sys.util.SysParametersUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * 
 * pdf 的操作工具类 依赖第三方jar，itext,POI,Jsoup
 * 
 * 
 * 
 * 特别注意：1、html中含有中文的时候一定要定义样式的font-family，否则中文不显示；
 *        2、html标签一定要严格，有开有闭，否则会出错；
 * 
 * 描述：常见的word转pdf有几种方式
 *     1、poi+itext 解析word 填充pdf 开源免费 效果很差
 *     2、调用windows com+组件，只能在windows环境 清晰度受限
 *     3、使用openoffice，需要安装openoffice，首先启动一个服务，然后程序中调用这个服务进行word操作，转换效果略有偏差；
 *        openoffice占用内存比较大，服务一直开着没有必要，网上建议可以用java代码调用开启，杀掉进程
 * @author lay
 * @create 2016年2月18日
 * @version
 * @see itext 实例文档http://developers.itextpdf.com/examples/merging-pdf-documents/adding-cover-page-existing-pdf
 *      itext API文档http://developers.itextpdf.com/reference/classes
 *      itext 中文实例http://rensanning.iteye.com/blog/1538689
 */
public class PDFUtil {
    
    public final static String  TEMP_BASE_DIR;
    public final static String TEMP_HTML_DIR;
    public final static String TEMP_PDF_DIR;
    public final static String TEMP_IMAG_DIR;//pdf 图层目录
    public final static String TEMP_CEB_DIR;
    //AES 密码加密的密钥：用Hex编码
    public final static String AES_USER_SECRET_KEY = "5186e9a727d7443dea37762030c17cfd";
    static{
        TEMP_BASE_DIR = SysParametersUtils.getUploadTempHome();
        TEMP_HTML_DIR = TEMP_BASE_DIR + File.separator + "html";
        TEMP_PDF_DIR = TEMP_BASE_DIR + File.separator + "pdf";
        TEMP_IMAG_DIR = TEMP_PDF_DIR + File.separator + "layer";
        TEMP_CEB_DIR = TEMP_BASE_DIR.substring(0, TEMP_BASE_DIR.indexOf(File.separator)+1) + "cebTemp";
    }
  
  
   
   /**
    * 将Html转换成PDF文件
    * @param inputFile html文件物理路径
    * @param pdfFileName pdf文件名
    * @throws DocumentException 
    * @throws IOException 
    */
   public static void  convertHtmlToPdf(String inputFile, String pdfFileName) throws DocumentException, IOException{
       convertHtmlToPdf(new FileInputStream(inputFile), pdfFileName);
   }
   
   /**
    * 将Html转换成PDF文件 使用itextpdf，这里这个对html有严格要求，
    * 一定义要定义样式的字体，字体样式不能出现中文，比如“微软雅黑”要写成"Microsoft YaHei",
    * 否则中文不显示
    * @param htmlFileStream html文件流
    * @param pdfFileName pdf文件名
    * @throws DocumentException
    * @throws IOException
    */
   public static void convertHtmlToPdf(InputStream htmlFileStream,String pdfFileName) throws DocumentException, IOException{
       Document document = new Document();
      
       String pdfFilePath = getPdfGeneratedAbsolutePath(pdfFileName);
       PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
       document.open();
       // html文件
       InputStreamReader isr = new InputStreamReader(htmlFileStream, "UTF-8");
       XMLWorkerHelper.getInstance().parseXHtml(writer, document, isr);
       document.close();
   }
   
   /**
    * 将pdf转换成swf
    * http://blog.csdn.net/hil2000/article/details/8459940
    * http://www.cnblogs.com/qinpeifeng107/archive/2011/08/29/2158879.html
    * @param exePath
    * @param pdfPath
    * @param swfPath
    * @throws IOException 
 * @throws InterruptedException 
    */
   public static void convertPdfToSwf(String exePath,String pdfPath,String swfPath) throws IOException, InterruptedException{
       Runtime r = Runtime.getRuntime();
       File swfFile = new File(swfPath);
       if (!swfFile.getParentFile().exists() && !swfFile.getParentFile().isDirectory()) {
           swfFile.getParentFile().mkdirs();
       }
       File pdfFile = new File(pdfPath);
       if (!swfFile.exists()) { 
           if (pdfFile.exists()) { 
               StringBuffer cmd = new StringBuffer(exePath)
               .append(" -t ")
               .append(pdfPath)
               .append(" -s ")
               .append("flashversion=9")
               .append(" -o ")
               .append(swfPath);
               //开启子进程执行cmd命令
               Process p = r.exec(cmd.toString());  
               //下面2句所说的情况暂时没有发现，先将代码放着
               //调用Runtime.getRuntime().exec()后，如果不及时捕捉进程的输出，会导致JAVA挂住，看似被调用进程没退出。
              //所以，解决办法是，启动进程后，再启动两个JAVA线程及时的把被调用进程的输出截获，
               //我这里没有用线程把信息输出，貌似没啥问题，等有问题后，用线程将信息输出来。
               System.out.print(loadStream(p.getInputStream()));  
               System.err.print(loadStream(p.getErrorStream()));
               //阻塞父线程，等待子进程执行结束
               p.waitFor(); 
           }
       }
   }
   
   /**
    * ceb 转 pdf
    * 本方法有可能转换失败
    * 1、不能打开文件选择框导致的失败-就是通过日志判断
    * 2、日志成功输出，但是文件保存路径失败--这个暂时没有找到判断逻辑，我们无法知道文件生成时间多长，不能通过文件是否成功生成来判断
    * 3、文件存取目录不能太长
    * @param exePath 执行文件路径
    * @param cebPath ceb文件地址
    * @param distDir 输出pdf目录
    * @return
    * @throws IOException
    * @throws InterruptedException
    */
   public static String convertCebToPdf(String exePath,String cebPath,String distDir) throws IOException, InterruptedException{
       String result = null;
       Runtime r = Runtime.getRuntime();
       File pdfDir = new File(distDir);
       if (!pdfDir.exists() && pdfDir.isDirectory()) {
           pdfDir.mkdirs();
       }
       //存在的ceb文件
       File cebFile = new File(cebPath);
       if(cebFile.exists()){
           //为了避免文件目录太长导致转换失败，我们将ceb文件先拷贝到临时目录下
           File cebTempDir = new File(TEMP_CEB_DIR);
           if (!cebTempDir.exists() && cebTempDir.isDirectory()) {
               cebTempDir.mkdirs();
           }
           //将ceb文件上传到cebTemp目录下
           File cebTemp = new File(cebTempDir.getAbsolutePath() + File.separator + DateUtil.date2String(new Date(), "yyyyMMddHHmmss")+".ceb");
           FileUtils.copyFile(cebFile, cebTemp);
           
           //ceb转换pdf命令
           StringBuffer convertToCmd = new StringBuffer(exePath)
           .append(" ")
           .append(cebTemp.getAbsolutePath())
           .append(" ")
           .append(cebTemp.getParent());
           //杀死进程
           String killC2pCmd = "TASKKILL /IM c2pfree.exe";
           
           boolean Go = true;
           Process p = null;
           //开启子进程执行cmd命令
           int time = 0;
           
           String msg = "";
           while(Go){
               if(time >= 5){//当尝试5次还是失败的话，放弃执行
                   break;
               }
               p = r.exec(convertToCmd.toString());//这边执行的时候，打开文件选择框这一步会出现打开失败的现象
               String output = loadStream(p.getInputStream());//获取输出日志
             
               if(output.indexOf("Application Ended")==-1){//失败，日志都没有输出结束
                   msg = cebFile.getAbsolutePath()+"转PDF，没有获取到日志结果，断言失败!";
                   p = r.exec(killC2pCmd);
               }else{
                   //命令执行后，文件不会立马生成，我们无法判断是否生成成功，只能通过延时操作来检测
                   Thread.sleep(2000);
                   File pdfTemp = new File(cebTemp.getAbsolutePath().replaceAll("\\.ceb", ".pdf"));
                   //如果pdf临时文件已经生成那么放到真实目录下
                   if(pdfTemp.exists()){
                       result = pdfDir.getAbsolutePath()+File.separator + cebFile.getName().replaceAll("\\.ceb", ".pdf"); 
                       FileUtils.copyFile(pdfTemp, new File(result));
                       //删除临时文件
                       pdfTemp.delete();
                   }
                   cebTemp.delete();
                   Go = false;
                   msg = "";
               }
               time++;
           }
           if(p != null){
              //阻塞父线程，等待子进程执行结束
              p.waitFor(); 
           }
           
           if(msg !=null && msg.length()>0){
               throw new RuntimeException(msg);
           }
             
       }
       return result;
   }
   
   private static  String loadStream(InputStream in) throws IOException {  
       return IOUtils.toString(in,"GBK");
   }
   
   /**
    * 获取绝对路径
    * @param pdfFileName
    * @return
    */
   private static String getPdfGeneratedAbsolutePath(String pdfFileName){
       return getPdfGeneratedAbsolutePath(TEMP_PDF_DIR,pdfFileName);
   }
   
   /**
    * 获取绝对路径
    * @param pdfFileName
    * @return
    */
   private static String getPdfGeneratedAbsolutePath(String baseDir,String pdfFileName){
       File dir = new File(baseDir);
       if (!dir.exists() && !dir.isDirectory()) {
           dir.mkdirs();
       }
       String pdfFilePath = baseDir + File.separator + pdfFileName;
       return pdfFilePath;
   }
   
   /**
    * 合并多个pdf，合并后的页顺序是list里的顺序
    * @param inputFiles
    * @param destPdfFileName
    * @throws DocumentException
    * @throws IOException
    */
   public static void mergingPdf(List<String> inputFiles,String destPdfFileName) throws DocumentException, IOException{
       Document document = new Document();
       String pdfFilePath = getPdfGeneratedAbsolutePath(destPdfFileName);
       PdfCopy copy = new PdfCopy(document, new FileOutputStream(pdfFilePath));
   
       document.open();
       for(String srcPdfPath : inputFiles){
           PdfReader srcPdf = new PdfReader(srcPdfPath);
           
           int n = srcPdf.getNumberOfPages();  
           for (int j = 1; j <= n; j++) {  
               document.newPage(); 
               
               //将每一页都对齐
               PdfDictionary pageDic =srcPdf.getPageN(j);//pdf字典
               PdfArray crop = pageDic.getAsArray(PdfName.CROPBOX);
               
               crop = new PdfArray();
               crop.add(new PdfNumber(0));
               crop.add(new PdfNumber(0));
               //下面这2个数值是通过单元测试获取出来的，略微改动了后的结果
               crop.add(new PdfNumber(596));
               crop.add(new PdfNumber(842));
               //cropbox mediabos 是pdf的专业测量尺寸，有5个标准
               pageDic.put(PdfName.MEDIABOX, crop);
               pageDic.put(PdfName.CROPBOX, crop);
               
               //获取当前文档的某一页
               PdfImportedPage page = copy.getImportedPage(srcPdf, j);
               
               //消除页脚分页
               /*PageStamp pageStamp = copy.createPageStamp(page);
               PdfContentByte overContent = pageStamp.getOverContent();
               overContent.beginText();
               overContent.setFontAndSize(BaseFont.createFont(), 18);
               overContent.setTextMatrix(30, 30);
               overContent.showText("total page " + 4);
               overContent.endText();
               pageStamp.alterContents();*/
               copy.addPage(page);
           }
           srcPdf.close();
       }
       
       document.close();
   }
   
   /**
    *  根据页号合并pdf，产生临时文件
    * @param inputFilePaths
    * @param importPageRangeArr
    * @return
    * @throws IOException
    * @throws DocumentException
    */
   public static String mergingPdf4Temp(List<String> inputFilePaths,List<Map<String,String>> importPageRangeArr)
           throws IOException, DocumentException {
       if(inputFilePaths == null || importPageRangeArr == null || inputFilePaths.size() != importPageRangeArr.size()){
           throw new IllegalArgumentException("inputFilePaths和importPageRangeArr不能为空，且长度必须相等");
       }
       Document document = new Document();
       String pdfFilePath = getPdfGeneratedAbsolutePath(IdGen.uuid()+".pdf");
       PdfCopy copy = new PdfCopy(document, new FileOutputStream(pdfFilePath));
       document.open();
       for(int i=0;i<inputFilePaths.size();i++){
           //待合并的pdf
           PdfReader srcPdf = new PdfReader(inputFilePaths.get(i));
           int n = srcPdf.getNumberOfPages(); //获取pdf的最大页数 
           
           Entry<String,String> pageRanges = importPageRangeArr.get(i).entrySet().iterator().next();
           String strPageStart = pageRanges.getKey();
           String strPageEnd = pageRanges.getValue();
           //检查起始页参数是否符合要求
           if(!StringUtils.isNumeric(strPageStart)){
               throw new IllegalArgumentException(String.format("importPageRangeArr中index为%d的初始页号必须是数字", i));
           }
           if(!"~".equals(strPageEnd)&&!StringUtils.isNumeric(strPageEnd)){
               throw new IllegalArgumentException(String.format("importPageRangeArr中index为%d的结束页号必须是数字或~", i));
           }
           if((!"~".equals(strPageEnd)&&Integer.parseInt(strPageEnd) < Integer.parseInt(strPageStart))){
               throw new IllegalArgumentException(String.format("importPageRangeArr中index为%d的开始页号超过了结束页号", i));
           }
           
           //重新设置起始页
           if(Integer.parseInt(strPageStart) > n || Integer.parseInt(strPageStart) < 1) {
               strPageStart = "1";
           }
           if((!"~".equals(strPageEnd)&&Integer.parseInt(strPageEnd) > n) || "~".equals(strPageEnd)){
               strPageEnd = String.valueOf(n);
           }
           
           
           for (int j = Integer.parseInt(strPageStart); j <= Integer.parseInt(strPageEnd); j++) { 
               document.newPage(); 
               //获取当前文档的某一页
               PdfImportedPage page = copy.getImportedPage(srcPdf, j);
               copy.addPage(page);
           }
           srcPdf.close();
       }
      
       document.close();
       return pdfFilePath;
   }
   
   /**
    * pdf 加密
    * @param srcPdf 原pdf地址
    * @param distPdf 加密后的地址
    * @param secretKey 密钥
    * @throws FileNotFoundException
    * @throws DocumentException
    * @throws IOException
    * @return 返回加密后的pdf地址
    */
   public static String encryptPdf(String srcPdf,String distPdf,String secretKey) throws FileNotFoundException, DocumentException, IOException{
       PdfReader reader = new PdfReader(srcPdf);
       PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(distPdf));
       stamper.setEncryption(secretKey.getBytes(), secretKey.getBytes(), PdfWriter.ALLOW_MODIFY_ANNOTATIONS
               | PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING, false);

       stamper.close();
       reader.close();
       return distPdf;
   }
   
   /**
    * pdf 加密
    * @param srcPdf 原pdf地址
    * @param distPdfDir 加密后的目录
    * @param distPdfName 加密后的pdf文件名
    * @param secretKey
    * @return 返回加密后的pdf地址
    * @throws FileNotFoundException
    * @throws DocumentException
    * @throws IOException
    */
   public static String encryptPdf(String srcPdf,String distPdfDir,String distPdfName,String secretKey) 
           throws FileNotFoundException, DocumentException, IOException{
       String distPdf = getPdfGeneratedAbsolutePath(distPdfDir,distPdfName);
       return encryptPdf(srcPdf, distPdf, secretKey);
   }
  
   /**
    * 清除临时pdf文件
    * @param path 绝对路径
    * @return
    */
   public static boolean clearTempPdf(String path) {
       if(path == null || "".equals(path)){
           return false;
       }
       File file = new File(path);

       // 若文件不存在，则无需删除
       if (!file.exists()) return true;

       // 删除文件
       return FileUtils.deleteQuietly(file);
   }
   
   /**
    * 从远程下载图层，添加图层到pdf后存放到临时路径；
    * 
    * 这里图层和pdf每页的长宽一样,
    * 图层的名称就是当前页的页号,多页就是压缩成zip
    * @param srcPdf 原加密后的pdf绝对路径
    * @param imageUrl 图层下载地址
    * @param secretKey 原pdf加密明文密钥
    * @return 返回添加图层后未加密的pdf路径
    * @throws IOException
    * @throws DocumentException
    */
  public static String markRemoteImage4Temp(String srcPdf,String imageUrl,String secretKey) throws IOException, DocumentException{
      /************************下载图层到临时图层目录****************************************/
      String downloadPdfLayerPath = downloadPdfLayer(imageUrl,TEMP_IMAG_DIR + File.separator + IdGen.uuid());
      
      /************************对下载后的图层解压处理****************************************/
      File pdfLayer = new File(downloadPdfLayerPath);
      List<String> layerPathArr = new ArrayList<String> ();
          //判断下载后的图层是否是压缩包
      if(pdfLayer.getName().endsWith(".zip")){
          layerPathArr = ectract(downloadPdfLayerPath, pdfLayer.getParent());
      }else{
          layerPathArr.add(downloadPdfLayerPath);
      }
      
      /*************************给pdf添加图层*********************************************/
      //获取添加图层后的pdf临时存放路径
      String distPdf = getPdfGeneratedAbsolutePath(TEMP_PDF_DIR,IdGen.uuid()+".pdf");
      markLocalImage(srcPdf, secretKey, layerPathArr, distPdf);
      //清理掉临时目录
      FileUtils.cleanDirectory(pdfLayer.getParentFile());
      return distPdf;
  }
  
  /**
   * 添加图层,这里图层和pdf每页的长宽一样,
   * 图层的名称就是当前页的页号,多页就是压缩成zip
   * @param srcPdf 将要添加图层的加密pdf文件
   * @param secretKey 明文密钥
   * @param layerPathArr 图层路径数组
   * @return 返回添加图层后未加密的临时pdf路径
   * @throws IOException
   * @throws DocumentException
   */
  public static String markLocalImage4Temp(String srcPdf,String secretKey,List<String> layerPathArr) throws IOException, DocumentException{
      String distPdf = getPdfGeneratedAbsolutePath(TEMP_PDF_DIR,IdGen.uuid()+".pdf");
      markLocalImage(srcPdf, secretKey, layerPathArr, distPdf);
      return distPdf;
  }
  
  /**
   * 添加图层,这里图层和pdf每页的长宽一样,
   * 图层的名称就是当前页的页号,多页就是压缩成zip
   * @param srcPdf 将要添加图层的加密pdf文件
   * @param secretKey 明文密钥
   * @param layerPathArr 图层路径数组
   * @param distPdf 添加后重新生成的pdf文件路径
   * @throws IOException
   * @throws DocumentException
   */
  public static void markLocalImage(String srcPdf,String secretKey,List<String> layerPathArr,String distPdf) throws IOException, DocumentException{
      File srcPdfFile = new File(srcPdf);
      File distPdfFile = new File(distPdf);
      
      if(!srcPdfFile.exists()){
          throw new IllegalArgumentException("找不到需要添加图层的pdf文件");
      }
      if (!distPdfFile.getParentFile().exists()) {
          distPdfFile.getParentFile().mkdirs();
      }
      
      PdfReader reader = null;
      if(secretKey != null){
          reader = new PdfReader(srcPdf, secretKey.getBytes());
      }else{
          reader = new PdfReader(srcPdf);
      }
      
      int n = reader.getNumberOfPages(); 
      
      PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(distPdf));
      PdfContentByte over;
      
      for(String layerPath : layerPathArr) {
          File layerFile = new File(layerPath);
          String currentPageNo = layerFile.getName().substring(0,layerFile.getName().lastIndexOf("."));
          
          boolean isNum=currentPageNo.matches("[0-9]+");
          if(!isNum){
             throw new IllegalArgumentException("图层名称是不是数字");
          }
          
          Image img = Image.getInstance(layerPath); 
          img.setAbsolutePosition(0, 0); 
          if(n > 0 && n >= Integer.parseInt(currentPageNo)) 
          { 
            // Text over the existing page 
            over = stamp.getOverContent(Integer.parseInt(currentPageNo)); 
            over.addImage(img); 
          } 
      }
      stamp.close(); 
  }
 
  /**
   * 给一个不加密的pdf添加图层，输出到临时文件夹下
   * @param srcPdf
   * @param layerPathArr
   * @return
   * @throws IOException
   * @throws DocumentException
   */
  public static String markLocalImage4Temp(String srcPdf,List<String> layerPathArr) throws IOException, DocumentException{
      File srcPdfFile = new File(srcPdf);
      String distPdf = getPdfGeneratedAbsolutePath(TEMP_PDF_DIR,IdGen.uuid()+".pdf");
      
      if(!srcPdfFile.exists()){
          throw new IllegalArgumentException("找不到需要添加图层的pdf文件");
      }
     
      PdfReader reader = new PdfReader(srcPdf);
      int n = reader.getNumberOfPages(); 
      
      PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(distPdf));
      PdfContentByte over;
      
      for(String layerPath : layerPathArr) {
          File layerFile = new File(layerPath);
          String currentPageNo = layerFile.getName().substring(0,layerFile.getName().lastIndexOf("."));
          
          boolean isNum=currentPageNo.matches("[0-9]+");
          if(!isNum){
             throw new IllegalArgumentException("图层名称是不是数字");
          }
          
          Image img = Image.getInstance(layerPath); 
          img.setAbsolutePosition(0, 0); 
          if(n > 0 && n >= Integer.parseInt(currentPageNo)) 
          { 
            // Text over the existing page 
            over = stamp.getOverContent(Integer.parseInt(currentPageNo)); 
            over.addImage(img); 
          } 
      }
      stamp.close(); 
      return distPdf;
  }
  
   /**
    * 解压文件
    * @param sZipPathFile
    * @param distDir
    * @return 返回解压后的文件路径列表
    */
   public static List<String> ectract(String sZipPathFile,String distDir){
       List<String> layerPathArr = new ArrayList<String> ();
       
       FileInputStream fins = null;
       ZipInputStream zins = null;
       try{
           // 先指定压缩档的位置和档名，建立FileInputStream对象  
           fins = new FileInputStream(sZipPathFile); 
           // 将fins传入ZipInputStream中  
           zins = new ZipInputStream(fins);
           
           ZipEntry ze = null;  
           byte[] ch = new byte[256];
           //遍历zip里面文件
           while ((ze = zins.getNextEntry()) != null) {  
               //创建当前文件对象
               File zfile = new File(distDir + File.separator + ze.getName());
               //创建上级目录对象
               File fpath = new File(zfile.getParentFile().getPath());
               //如果当前对象是目录
               if (ze.isDirectory()) {  
                   if (!zfile.exists()){
                       zfile.mkdirs();
                   }
                   zins.closeEntry();  
               }else{
                   if(!fpath.exists()){
                       fpath.mkdirs();
                   }
                   FileOutputStream fouts = new FileOutputStream(zfile);  
                   int i; 
                   layerPathArr.add(zfile.getAbsolutePath());
                   //将文件解压出来
                   while ((i = zins.read(ch)) != -1){
                       fouts.write(ch, 0, i);  
                   }
                   zins.closeEntry();  
                   fouts.close();  
               }
           }
            
       }catch(Exception e){
           throw new RuntimeException(e.getMessage());
       }finally{
           try {
            if(fins != null) fins.close();
            if(zins != null) zins.close();
           } catch (IOException e) {
           }  
       }
       return layerPathArr;
   }
  /**
   * 下载pdf图层
   * @param url 远程url地址
   * @param localDir
   * @return 下载后的绝对路径
   */
   public static String downloadPdfLayer(String url,String localDir){
       URL urlfile = null;
       HttpURLConnection httpUrl = null;
       BufferedInputStream bis = null;
       BufferedOutputStream bos = null;
       String localFilePath = null;
       
       try{
           urlfile = new URL(url);
           httpUrl = (HttpURLConnection)urlfile.openConnection();
           httpUrl.connect();
           
           //根据http头获取信息
           String[] fileInfoArr = analyzeResourceInfo(httpUrl,url);
           localFilePath = getPdfGeneratedAbsolutePath(localDir, fileInfoArr[0]+"."+fileInfoArr[1]);
           
           File f = new File(localFilePath);
           bis = new BufferedInputStream(httpUrl.getInputStream());
           bos = new BufferedOutputStream(new FileOutputStream(f));
           int len = 2048;
           byte[] b = new byte[len];
           while ((len = bis.read(b)) != -1)
           {
               bos.write(b, 0, len);
           }
           bos.flush();
           bis.close();
           httpUrl.disconnect();
       }catch(Exception e){
           throw new RuntimeException(e.getMessage());
       }finally{
           try{
               bis.close();
               bos.close();
           }catch (IOException e){
           }
       }
      return localFilePath;
   }
   
   
  
   
   /**
    * 根据http头信息分析资源的名称和类型
    * @param httpUrl
    * @return 数组 0-文件名 1-文件后缀
    */
   private static String[] analyzeResourceInfo(HttpURLConnection httpUrl,String url){
       String contentDisposition=httpUrl.getHeaderField("Content-Disposition");
       String[] res =new String[2];
       String filename = null;
       if(contentDisposition == null){
           File f = new File(url);
           filename = f.getName();
       }else{
           filename=contentDisposition.substring(contentDisposition.indexOf("filename")+10, contentDisposition.length()-1);
           try {
              filename=new String(filename.getBytes("ISO-8859-1"), "GB2312");
           } catch (UnsupportedEncodingException e) {
              throw new RuntimeException(e.getMessage());
           }
       }
     
       res[0] = filename.substring(0,filename.indexOf("."));
       res[1] = filename.substring(filename.indexOf(".")+1,filename.length());
       return res;
   }
   
   
    
   
   /**
    * 获取签批附件绝对路径
    * @param pdfFileName
    * @return
    */
   private static String getFilesGeneratedAbsolutePath(String baseDir,String pdfFileName){
       File dir = new File(baseDir);
       if (!dir.exists() && !dir.isDirectory()) {
           dir.mkdirs();
       }
       String FilesPath = baseDir + File.separator + pdfFileName;
       return FilesPath;
   }
   
   /**
    * 下载签批附件
    * @param url 远程url地址
    * @param localDir
    * @return 下载后的绝对路径
    */
   public static String downloadFiles(String url,String localDir){
       URL urlfile = null;
       HttpURLConnection httpUrl = null;
       BufferedInputStream bis = null;
       BufferedOutputStream bos = null;
       String localFilePath = null;
       
       try{
           urlfile = new URL(url);
           httpUrl = (HttpURLConnection)urlfile.openConnection();
           httpUrl.connect();
           
           //根据http头获取信息
           String[] fileInfoArr = analyzeResourceInfo(httpUrl,url);
           localFilePath = getFilesGeneratedAbsolutePath(localDir, fileInfoArr[0]+"."+fileInfoArr[1]);
           
           File f = new File(localFilePath);
           bis = new BufferedInputStream(httpUrl.getInputStream());
           bos = new BufferedOutputStream(new FileOutputStream(f));
           int len = 2048;
           byte[] b = new byte[len];
           while ((len = bis.read(b)) != -1)
           {
               bos.write(b, 0, len);
           }
           bos.flush();
           bis.close();
           httpUrl.disconnect();
       }catch(Exception e){
           throw new RuntimeException(e.getMessage());
       }finally{
           try{
               bis.close();
               bos.close();
           }catch (IOException e){
           }
       }
       return localFilePath;
   }
   
   /**
    * 解压签批附件
    * @param sZipPathFile
    * @param distDir
    * @return 返回解压后的文件路径列表
    */
   public static List<String> ectractQP(String sZipPathFile,String distDir){
       List<String> layerPathArr = new ArrayList<String> ();
       FileInputStream fins = null;
       ZipInputStream zins = null;
       try{
           // 先指定压缩档的位置和档名，建立FileInputStream对象  
           fins = new FileInputStream(sZipPathFile); 
           // 将fins传入ZipInputStream中  
           zins = new ZipInputStream(fins);
           
           ZipEntry ze = null;  
           byte[] ch = new byte[256];
           //遍历zip里面文件
           while ((ze = zins.getNextEntry()) != null) {  
               //创建当前文件对象
               File zfile = new File(distDir + File.separator + ze.getName());
               //确认一下这个值zfile.length();
               //创建上级目录对象
               File fpath = new File(zfile.getParentFile().getPath());
               //如果当前对象是目录
               if (ze.isDirectory()) {  
                   if (!zfile.exists()){
                       zfile.mkdirs();
                   }
                   zins.closeEntry();  
               }else{
                   if(!fpath.exists()){
                       fpath.mkdirs();
                   }
                   FileOutputStream fouts = new FileOutputStream(zfile);  
                   int i; 
                   layerPathArr.add(zfile.getAbsolutePath());
                   //将文件解压出来
                   while ((i = zins.read(ch)) != -1){
                       //请确认该大小是不是就是㢟大小？ch.length;
                       fouts.write(ch, 0, i);  
                   }
                   //在这一步调用插入数据库的数据，
                   zins.closeEntry();  
                   fouts.close();  
               }
           }
            
       }catch(Exception e){
           throw new RuntimeException(e.getMessage());
       }finally{
           try {
            if(fins != null) fins.close();
            if(zins != null) zins.close();
           } catch (IOException e) {
           }  
       }
       return layerPathArr;
   }
   /**
    * 解压签批附件
    * @param sZipPathFile
    * @param distDir
    * @return 返回解压后的文件路径列表
    */
   public static List<String> ectractQPHY(String sZipPathFile,String distDir,String localUrl){
       List<String> layerPathArr = new ArrayList<String> ();
       FileInputStream fins = null;
       ZipInputStream zins = null;
       try{
           // 先指定压缩档的位置和档名，建立FileInputStream对象  
           fins = new FileInputStream(sZipPathFile); 
           // 将fins传入ZipInputStream中  
           zins = new ZipInputStream(fins);
           
           ZipEntry ze = null;  
           byte[] ch = new byte[256];
           //遍历zip里面文件
           while ((ze = zins.getNextEntry()) != null) {  
               //创建当前文件对象
               File zfile = new File(localUrl);
               //确认一下这个值zfile.length();
               //创建上级目录对象
               File fpath = new File(zfile.getParentFile().getPath());
               //如果当前对象是目录
               if (ze.isDirectory()) {  
                   if (!zfile.exists()){
                       zfile.mkdirs();
                   }
                   zins.closeEntry();  
               }else{
                   if(!fpath.exists()){
                       fpath.mkdirs();
                   }
                   FileOutputStream fouts = new FileOutputStream(zfile);  
                   int i; 
                   layerPathArr.add(zfile.getAbsolutePath());
                   //将文件解压出来
                   while ((i = zins.read(ch)) != -1){
                       //请确认该大小是不是就是㢟大小？ch.length;
                       fouts.write(ch, 0, i);  
                   }
                   //在这一步调用插入数据库的数据，
                   zins.closeEntry();  
                   fouts.close();  
               }
           }
            
       }catch(Exception e){
           throw new RuntimeException(e.getMessage());
       }finally{
           try {
            if(fins != null) fins.close();
            if(zins != null) zins.close();
           } catch (IOException e) {
           }  
       }
       return layerPathArr;
   }
}
