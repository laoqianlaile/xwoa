package com.centit.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSubvideoInformation;
import com.centit.oa.po.OaVideoInformation;
import com.centit.oa.service.OaSubvideoInformationManager;
import com.centit.oa.service.OaVideoInformationManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.DateUtil;
import com.centit.sys.util.SysParametersUtils;

public class OaVideoInformationAction extends BaseEntityDwzAction<OaVideoInformation> {
    private static final Log log = LogFactory
            .getLog(OaVideoInformationAction.class);
    static private SimpleDateFormat pathDF = new SimpleDateFormat("yyyyMMdd");
    private static final long serialVersionUID = 1L;
    private OaVideoInformationManager oaVideoInformationMag;
    private OaSubvideoInformationManager oaSubvideoInformationMag;
    private InputStream stuffStream;//附件流
    private File big_;//大图
    public File getBig_() {
        return big_;
    }
    public void setBig_(File big_) {
        this.big_ = big_;
    }
    private File upload;//小图
   
    private String big_FileName;
    private String uploadFileName;
   
 
    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getBig_FileName() {
        return big_FileName;
    }
    public void setBig_FileName(String big_FileName) {
        this.big_FileName = big_FileName;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    private File video;
    private String videoFileName;
    private String videoPaths;
    private String videoPic;

    public void setOaVideoInformationManager(OaVideoInformationManager basemgr) {
        oaVideoInformationMag = basemgr;
        this.setBaseEntityManager(oaVideoInformationMag);
    }
    
    public void setOaSubvideoInformationMag(
            OaSubvideoInformationManager oaSubvideoInformationMag) {
        this.oaSubvideoInformationMag = oaSubvideoInformationMag;
    }
    /**
     * 保存方法
     */
    @Override
    public String save(){
        try {
            OaVideoInformation le = oaVideoInformationMag.getObject(object);
            if (le != null) {
                oaVideoInformationMag.copyObjectNotNullProperty(le, object);
                object = le;
                object.setLastmodifytime(DatetimeOpt.currentUtilDate());

            } else {
                
                FUserDetail user = (FUserDetail) getLoginUser();//获取当前用户的信息
                object.setCreater(user.getUsercode());
                object.setCreatertime(DatetimeOpt.currentUtilDate());

                //生成主键
                object.setNo(oaVideoInformationMag.createNo());
                
                object.setState("F");//默认未发布
            }
            //保存checklist
            String []bookmark =request.getParameterValues("bookmark");
            StringBuilder sb =new StringBuilder();
            if(bookmark!=null && bookmark.length>0){
                for(String s :bookmark){
                    sb.append(s+"|");
                }
            }//去掉最后一个"|"
            if(sb.length()>0){
                sb.deleteCharAt(sb.length()-1);
            } 
            object.setBookmark(sb.toString());
            //配置文件夹
            String basePath = request.getSession().getServletContext().getRealPath("/upload/videos");
            String path = File.separator + "img";
            
            path = getFilePath(path, pathDF.format(new Date()));

            // 获取文件路径oa.home
            String fullPath = basePath+path;
            File root = new File(fullPath);
            if (!root.exists()) {
                FileUtils.forceMkdir(root);
            }
                
          
            byte[] b = null; byte[] bb = null;
            if (big_ != null) {
               
                FileInputStream fis = new FileInputStream(big_);
                
                int len = fis.available();
                b = new byte[len];
                fis.read(b);
                String imppath1=fullPath+"\\"+big_FileName;
                FileOutputStream fileoutputstream = new FileOutputStream(imppath1);
                byte tag_bytes[] = b;
                fileoutputstream.write(tag_bytes);
                fileoutputstream.close();                        
                fis.close();
                object.setBigImage("upload/videos/img/"+pathDF.format(new Date())+"/"+big_FileName);
            }
            if (upload != null) {
                FileInputStream fis = new FileInputStream(upload);
                int len = fis.available();
                bb = new byte[len];
                fis.read(bb);
                String imppath2=fullPath+"\\"+uploadFileName;
                FileOutputStream fileoutputstream = new FileOutputStream(imppath2);
                byte tag_bytes[] = bb;
                fileoutputstream.write(tag_bytes);
                fileoutputstream.close();                        
                fis.close();
                object.setSmallImage("upload/videos/img/"+pathDF.format(new Date())+"/"+uploadFileName);
            }
    
          
            oaVideoInformationMag.saveObject(object);
            
       /*     //add by lq 编辑保存时如未重新上传视频
            if(video!=null){
                //flag标记是否转码成功
                boolean  flag = false ;
                String basePath = request.getSession().getServletContext().getRealPath("/upload/videos");
                //文件名自定义生成待转码的文件
                String FIname = String.valueOf(System.currentTimeMillis());
                String FItype = videoFileName.substring(videoFileName.lastIndexOf("."));
                String fullPath = basePath+File.separator+FIname+FItype;
                FileUtils.copyFile(video, new File(fullPath));
                //文件转码后保存的路径
                String codcFilePath = basePath+"/"+FIname+".flv";
                String mediaPicPath = basePath + "/images"+File.separator+FIname+".jpg";
                
                //转码工具的路径
                String ffmegPath = request.getSession().getServletContext().getRealPath("/upload/tools/ffmpeg.exe");
                //保存图片和视频地址
               
                object.setUploadFileName("upload/videos/images/"+FIname+".jpg");
                object.setVideoPath("upload/videos/"+FIname+".flv");
                object.setUploadFileName(videoFileName);
                flag = this.executeCodecs(ffmegPath, fullPath, codcFilePath, mediaPicPath);
                if(flag){
                     oaVideoInformationMag.saveObject(object);
                 }   
                
            }else{
                oaVideoInformationMag.saveObject(object);
            }*/
                 
            savedMessage();
            return videoList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
    }
    @Override
    public String built(){
        return super.built();
    }
    /**
     * 观看视频
     *//*
    public String showVideo(){
        OaVideoInformation oaVideoInformation = oaVideoInformationMag.getObject(object);
        if (oaVideoInformation != null)
            oaVideoInformationMag.copyObject(object, oaVideoInformation);
        String path = request.getContextPath();
        videoPaths = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/"+object.getVideoPath();
        return "showVideo";
    }
    *//**
     * 视频转码
     *//*
  public boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,
            String mediaPicPath) throws Exception {
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); 
        convert.add("-i"); // 
        convert.add(upFilePath); 
        convert.add("-qscale");   
        convert.add("6");
        convert.add("-ab");       
        convert.add("64");
        convert.add("-ac");       
        convert.add("2");
        convert.add("-ar");        
        convert.add("22050");
        convert.add("-r");        
        convert.add("24");
        convert.add("-y"); 
        convert.add(codcFilePath);

       
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(upFilePath); 
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); 
        cutpic.add("17"); 
        cutpic.add("-t"); 
        cutpic.add("0.001"); 
        cutpic.add("-s"); 
        cutpic.add("800*280"); 
        cutpic.add(mediaPicPath); 

        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            builder.start();
            
            builder.command(cutpic);
            builder.redirectErrorStream(true);
            builder.start();
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }*/
     @Override
    public String view(){
         super.view();
         String [] dealtype=null;
         if(object!=null){
             if(object.getBookmark()!=null && !"".equals(object.getBookmark())){
                 dealtype=object.getBookmark().split("\\|");
                 StringBuffer sb = new StringBuffer();
                 for(int i=0;i<dealtype.length;i++){
                     sb.append(CodeRepositoryUtil.getDataPiece("bookmark", dealtype[i]).getValue());
                     if(i<dealtype.length-1){
                         sb.append("，");
                     }
                 }
                 object.setBookmark(sb.toString());
             }
             Map<String,Object> filterMap = new HashMap<String,Object>();
             filterMap.put("no", object.getNo());
             List<OaSubvideoInformation> videoItems = oaSubvideoInformationMag.listObjects(filterMap);
             request.setAttribute("videoItems", videoItems);
          }
        return VIEW;
    }
    
    
    
    @SuppressWarnings("unchecked")
    @Override
    public String list(){
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        

        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if(filterMap.get("includeDel") == null){
            filterMap.put("excludeStates","D");//视频状态
        }
       
        if (!StringUtils.isBlank(orderField) && !StringUtils.isBlank(orderDirection)) {
            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " " + orderDirection);
        }
        
      //默认查询当前月份第一天到现在的记录
        if(StringUtils.isBlank((String)filterMap.get("begReleaseDate"))&&StringUtils.isBlank((String)filterMap.get("endReleaseDate"))){
            filterMap.put("begReleaseDate",DateUtil.getCurrentMonthOfDay() );
            filterMap.put("endReleaseDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
        }
        PageDesc pageDesc = makePageDesc();
        objList = baseEntityManager.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        
        this.pageDesc = pageDesc;
        return LIST;
    }
    
    
  
    public String videoList(){
      
        list();
      
        return "videoList";
    }
    public String videoView(){
        super.view();
        return "videoView";
    }
    private List bookmarkList;

    public String videoEdit(){
        OaVideoInformation oa=oaVideoInformationMag.getObjectById(object.getNo());
        //初始化视频节目标签
        Map<String,String> map =new HashMap<String,String>(); 
        List<FDatadictionary> datalist = CodeRepositoryUtil.getDictionary("bookmark");
        if(datalist!=null &&datalist.size()>0){
            for(FDatadictionary nary : datalist){
                map.put(nary.getDatacode(), nary.getDatavalue());
            } 
        }
        request.setAttribute("dataMap", map);
      
            String [] dealtype=null;
        if(oa!=null){
            if(oa.getBookmark()!=null && oa.getBookmark()!=""){
                dealtype=oa.getBookmark().split("|");
                bookmarkList=Arrays.asList(dealtype);
            }
         }
        super.edit();
        return "videoEdit";
    }
    /**
     * 启用
     */
    public String isstate(){
        OaVideoInformation le = oaVideoInformationMag.getObject(object);
        if (le != null) {
            oaVideoInformationMag.copyObjectNotNullProperty(le, object);
           
            object = le;
            oaVideoInformationMag.saveObject(object);
        }
        return videoList();
    }
    /**
     * 删除
     */
    public String delete(){
        oaVideoInformationMag.deleteObject(object);
        return videoList();
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
            OaVideoInformation newle =new OaVideoInformation();
            newle.setNo(no);
            oaVideoInformationMag.deleteObject(newle);
         }
        }
        return videoList();
    }
    
    /**
     * 批量修改状态
     * @return
     */
    public String batchUpdateState(){
      //批量ids
        String ids = request.getParameter("ids");
        String state = request.getParameter("state");
        if (StringUtils.isNotEmpty(ids)) {
            for (String no : ids.split(",")) {
                OaVideoInformation le = oaVideoInformationMag.getObjectById(no);
                if (le != null) {
                  //如果是批量下线操作,记录必须是已发布状态，才能执行下线操作;如果是批量发布操作,记录必须是未发布状态，才能执行发布操作
                    if(("F".equals(state)&&"T".equals(le.getState())) 
                            || ("T".equals(state)&&"F".equals(le.getState()))){
                        le.setState(state);
                        oaVideoInformationMag.saveObject(le);
                    }
                }
            }
        }
        return videoList();
    }
    /**
     * ajax验证
     * @return
     */
    private Object json;
    public Object getJson() {
        return json;
    }
    public void setJson(Object json) {
        this.json = json;
    }
    public String checksmalimg(){
        json = new JSONObject();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String no = request.getParameter("no");
            paramMap.put("no", new String(no.getBytes("ISO-8859-1"),
                    "GBK").trim());
           
            OaVideoInformation oa=oaVideoInformationMag.getObjectById(no);
          if(oa!=null){
            if(StringUtils.isNotBlank(oa.getSmallImage())){
                ((JSONObject) json).put("status", "none" );
                
            }else{
                ((JSONObject) json).put("status", "exist" );
            }
          }
           
        } catch (Exception e) {
            log.info(e);
            ((JSONObject) json).put("status", "failed");
        }

        return "json";
        
        
        
    }
    
    private static String getFilePath(String path, String... filename) {
        String filePath = path;
        for (String name : filename) {
            filePath += File.separator + name;
        }

        return filePath;
    }
    @SuppressWarnings("unused")
    private static String getFullFilePath(String path) {
        return SysParametersUtils.getUploadHome() + path;
    }
    /**
     * 下载照片
     * @return
     * @throws IOException 
     */
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
   public String downloadPhoto() throws IOException {
        OaVideoInformation le = oaVideoInformationMag.getObjectById(object.getNo());
        String v=request.getParameter("type");
   
        String fn="";
        if (le != null&&"big_".equals(v)){
             fn = le.getBigImage()
                    .substring(le.getBigImage().lastIndexOf('\\'));
            File file=new File(le.getBigImage());
            FileInputStream fis = new FileInputStream(file);
            byte[] bt = null;
            if (fis != null) {
                int len = fis.available();
                bt = new byte[len];
                fis.read(bt);
                setStuffStream(new ByteArrayInputStream(bt));
            }
            fis.close();
        }else{
             fn = le.getSmallImage()
                    .substring(le.getSmallImage().lastIndexOf('\\'));
            File file=new File(le.getSmallImage());
            FileInputStream fis = new FileInputStream(file);
            byte[] bt = null;
            if (fis != null) {
                int len = fis.available();
                bt = new byte[len];
                fis.read(bt);
                setStuffStream(new ByteArrayInputStream(bt));
            }
            fis.close();
        }
        this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
        return "download";
    }
   
    public InputStream getStuffStream() {
        return stuffStream;
    }
    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }
 
    public File getVideo() {
        return video;
    }
    public void setVideo(File video) {
        this.video = video;
    }
    public String getVideoFileName() {
        return videoFileName;
    }
    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }
    public String getVideoPaths() {
        return videoPaths;
    }
    public void setVideoPaths(String videoPaths) {
        this.videoPaths = videoPaths;
    }
    public String getVideoPic() {
        return videoPic;
    }
    public void setVideoPic(String videoPic) {
        this.videoPic = videoPic;
    }

    public List getBookmarkList() {
        return bookmarkList;
    }
    public void setBookmarkList(List bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

  
}
