package com.centit.webservice.util;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.License;
import com.aspose.words.PaperSize;
import com.aspose.words.SaveFormat;
import com.aspose.words.Section;
import com.aspose.words.SectionCollection;
import com.centit.sys.util.SysParametersUtils;

/**
 * 
 * Aspose.Words这个组件，非常好，文档齐全，案例丰富，功能也很多，支持非常完善,很可惜这需要收费,费用还挺高，这里使用破解版
 * 支持DOC, DOCX, OOXML, RTF, HTML, OpenDocument, PDF, EPUB, XPS, SWF等相互转换
 * 这里提供一个博客地址可以参考API http://www.cnblogs.com/asxinyu/p/3242754.html
 * 英文Api： http://www.aspose.com/api/java/words
 * @author lay
 * @create 2016年2月22日
 * @version
 */
public class AsposeWordsUtil {
   
    public final static String  TEMP_BASE_DIR;
    public final static String TEMP_WORD_DIR;
    public final static String TEMP_PDF_DIR;
    
    static{
        TEMP_BASE_DIR = SysParametersUtils.getUploadTempHome();
        TEMP_WORD_DIR = TEMP_BASE_DIR + File.separator + "word";
        TEMP_PDF_DIR = TEMP_BASE_DIR + File.separator + "pdf";
    }
  
    /**
     * 获取证书
     * @return
     */
    private static boolean getAsposeWordLicense(){
        boolean result = false;
        try {
            InputStream is = AsposeWordsUtil.class.getClassLoader().getResourceAsStream("aspose-word-license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
  /**
   * 清除页眉页脚
   * @param doc
   */
  private static void clearHeadersFooters(Document doc){
      SectionCollection sc = doc.getSections();
      for(Section sec : sc){
          sec.clearHeadersFooters();
      }
  }
  
  /**
   * 填充word文档里的书签
   * @param inputStream word的文档流
   * @param bookMarkMap 书签简直对
   * @throws Exception
   * @return word 路径
   */
  public static void fillWordBookMark(String wordTempletPath,String outputWordPath,Map<String,String> bookMarkMap) throws Exception{
   // 验证License
      if (!getAsposeWordLicense()) {
          return;
      }
      Document doc = new Document(wordTempletPath);
      DocumentBuilder builder = new DocumentBuilder(doc);
      for(Entry<String, String> entrty : bookMarkMap.entrySet()){
          builder.moveToBookmark(entrty.getKey());
          builder.write(entrty.getValue());
      }
      doc.save(outputWordPath);
  }
  
  
  /**
   * 使用破解版的aspose.words进行文件导出pdf
   * @param inputFile word物理路径
   * @param pdfFilePath pdf文件路径
   * @param removeHeaderFooter
   * @return
   * @throws Exception
   */
  public static void convertToPdf(String inputFile,String pdfFilePath,boolean removeHeaderFooter) throws Exception{
   // 验证License
      if (!getAsposeWordLicense()) {
          return;
      }

      Document doc = new Document(inputFile);// 原始word路径
      
      if(removeHeaderFooter){
        //清除文档的页眉页脚
          clearHeadersFooters(doc);
      }
          
      doc.save(pdfFilePath, SaveFormat.PDF);
  }
  
  /**
   * 这里将word转换成png，注意后缀是docx的才能设置透明背景，
   * 而我用wps建的doc，生成的png无论设置什么背景色都无效
   * @param inputFilePath
   * @param pngPath
   * @return
   * @throws Exception
   */
  public static void convertToTransparentPngImage(String inputFilePath,String pngPath) throws Exception{
   // 验证License
      if (!getAsposeWordLicense()) {
          return;
      }
      if(!inputFilePath.endsWith(".docx")){
          throw new IllegalArgumentException("只支持后缀为docx的word文档转换");
      }
      Document doc = new Document(inputFilePath);// 原始word路径
      ImageSaveOptions imgOptions = new ImageSaveOptions(SaveFormat.PNG);
      imgOptions.setPaperColor(new Color(0,0,0,0));
      imgOptions.setResolution(100);
      
      File pngFile = new File(pngPath);// 输出路径
      //判断目录是否存在
      File dir = pngFile.getParentFile();
      if(dir.isDirectory()){
          if (!dir.exists()){
              dir.mkdirs();
          }
      }
      doc.save(pngPath, imgOptions);
  }
  
  /**
   * 合并word
   * @param srcWords
   * @param distDocPath 最终word路径
   * @param removeHeaderFooter 是否移除页眉页脚
   * @return
   * @throws Exception
   */
  public static void mergingWord(List<String> srcWords,String distDocPath,boolean removeHeaderFooter) throws Exception{
   // 验证License
      if (!getAsposeWordLicense()) {
          return;
      }
      
      Document doc = new Document();
      doc.removeAllChildren();
      for(int i=0 ;i < srcWords.size(); i++){
          Document srcDoc = new Document(srcWords.get(i));
          if(removeHeaderFooter){
              clearHeadersFooters(srcDoc);
          }
          doc.appendDocument(srcDoc, ImportFormatMode.KEEP_SOURCE_FORMATTING);
          // In automation you were required to insert a new section break at this point, however in Aspose.Words we
          // don't need to do anything here as the appended document is imported as separate sectons already.
       
          // If this is the second document or above being appended then unlink all headers footers in this section
          // from the headers and footers of the previous section.
          doc.getSections().get(i).getPageSetup().setPaperSize(PaperSize.A4);//设置纸张为A4，否则合并后可能造成宽度不一样
          if (i > 0 && !removeHeaderFooter)
              doc.getSections().get(i).getHeadersFooters().linkToPrevious(false);
      }
      
      doc.save(distDocPath);
      
  }
  
  
  /**
   * 将字节数组写到磁盘的word文件中
   * @param bytes
   * @param outputName
   * @return
   * @throws IOException
   */
  public static String saveDocToDisk(byte[]bytes,String wordFilePath) throws IOException{
      File file = new File(wordFilePath);
      
      BufferedOutputStream bos = null;
      try{
           bos =  new BufferedOutputStream(new FileOutputStream(file));
          bos.write(bytes);
      }catch(IOException e){
          throw new IOException(e.getMessage());
      }finally{
          closeQuietly(bos);
      }
     return wordFilePath;
  }
  
  /**
   * 讲html转换成word
   * @param strHtml
   * @param distDocPath 生成路径
   * @return
   * @throws Exception
   */
  public static void convertHtmlToWord(String htmlPath,String distDocPath) throws Exception{
   // 验证License
      if (!getAsposeWordLicense()) {
          return;
      }
      
      Document doc = new Document(htmlPath);
          
      doc.save(distDocPath, SaveFormat.DOC);
      
  }
  
  /**
   * 获取文档页数
   * @param path
   * @return
   * @throws Exception
   */
  public static int getDocPageCount(String path) throws Exception{
   // 验证License
      if (!getAsposeWordLicense()) {
          return 0;
      }
      Document doc = new Document(path);
      return doc.getPageCount();
  }
  
  /**
   * 关闭流
   * 
   * @param stream
   */
  public static void closeQuietly(Closeable stream) {
      if (stream != null) {
          try {
              stream.close();
          } catch (IOException e) {
              // ignore
          }
      }
  }
}
