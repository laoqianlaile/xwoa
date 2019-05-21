package com.centit.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

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
import com.centit.sys.service.CodeRepositoryUtilExtend;
import com.centit.sys.util.SysParametersUtils;

public class OaSubvideoInformationAction extends
        BaseEntityDwzAction<OaSubvideoInformation> {
    private static final long serialVersionUID = 1L;
    static private SimpleDateFormat pathDF = new SimpleDateFormat("yyyyMMdd");
    private OaSubvideoInformationManager oaSubvideoInformationMag;
    private List<FDatadictionary> infoTypesList;// 数据字典list
    private List infoList;//视频类型列表list
    private String yearSearch;

    public void setOaSubvideoInformationManager(
            OaSubvideoInformationManager basemgr) {
        oaSubvideoInformationMag = basemgr;
        this.setBaseEntityManager(oaSubvideoInformationMag);
    }

    private OaVideoInformationManager oaVideoInformationManager;

    public void setOaVideoInformationManager(
            OaVideoInformationManager oaVideoInformationManager) {
        this.oaVideoInformationManager = oaVideoInformationManager;
    }

    private InputStream stuffStream;// 附件流

    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }

    public InputStream getStuffStream() {
        return stuffStream;
    }

    private File video_;// 大图

    private File upload;// 小图

    private String video_FileName;
    private String uploadFileName;

    @SuppressWarnings("unchecked")
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        String no = request.getParameter("no");
        object.setNo(no);
       
        filterMap.put("no", no);// 视频状态
        String [] retName=null;
        OaVideoInformation oa = oaVideoInformationManager.getObjectById(no);
        if(oa.getBookmark()!=null){
            retName=oa.getBookmark().trim().split("\\|");
            StringBuilder sb =new StringBuilder();
            for(String rtName : retName){
                String retNameValue = CodeRepositoryUtil.getValue("bookmark", rtName);
                sb.append(retNameValue+",");
            }
            if(sb.length()>0){
                sb.deleteCharAt(sb.length()-1);
            }
            oa.setBookmark(sb.toString());
        }
        
        request.setAttribute("oa", oa);
        if (!StringUtils.isBlank(orderField)
                && !StringUtils.isBlank(orderDirection)) {
            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                    + orderDirection);
        }

        PageDesc pageDesc = makePageDesc();
        objList = baseEntityManager.listObjects(filterMap, pageDesc);
        for (OaSubvideoInformation o : objList) {
            String videoPaths = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/" + o.getSmallimage();
            o.setSmallimage(videoPaths);
        }
        return LIST;
    }

    /**
     * 根据名称或关键字查询视频
     * 
     * @return
     */
    public String oasubvideoShowList() {
        try {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            String content =(String)request.getParameter("s_search");//提交的搜索内容
            if(StringUtils.isNotBlank(content)){
                content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
            }
            String typeSearch=(String)request.getParameter("typeSearch");
            request.setAttribute("typeSearch", typeSearch);
            
            yearSearch =(String)request.getParameter("yearSearch");
            filterMap.put("yearSearch", yearSearch);
            
            //获取当前年份
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            Date currentTime = new Date();
            String year = formatter.format(currentTime);
            request.setAttribute("year", Integer.parseInt(year)-5);
            
            filterMap.put("search", content);
            List<OaSubvideoInformation> subs = this.videos(typeSearch, filterMap);
            request.setAttribute("subs", subs);
           
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "oasubvideoShowList";
    }

    /**
     * 视频节目的首页信息
     * 
     * @return
     */
    public String oasubvideoMainpage() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        filterMap.put("state", "T");// 视频状态
        List<OaVideoInformation> objList = oaVideoInformationManager.listObjects(filterMap);
        request.setAttribute("objList", objList);
        // 获取数据字典
        infoTypesList = CodeRepositoryUtilExtend.getDictionary("videoType");
        infoList = new ArrayList();
        // 按字典项依次获取列表
        if (null != infoTypesList) {
            for (FDatadictionary f : infoTypesList) {
                infoList.add(f);
                //filterMap.clear();
                filterMap.put("infoType", f.getDatacode());
                
                // 列表不为空时截取固定长度
                List<OaVideoInformation> info = oaVideoInformationManager.listObjects(filterMap);
                infoList.add(info);
            }
        }

        return "oasubvideoMainpage";
    }

    // 查询不同类别的视频
    public List<OaSubvideoInformation> videos(String infotype,
            Map<String, Object> filterMap) {

        filterMap.remove("infoType");

        filterMap.put("infoType", infotype);
        filterMap.put("state", "T");

        List<OaSubvideoInformation> oasubs = new ArrayList<OaSubvideoInformation>();
        List<OaVideoInformation> oavdus= oaVideoInformationManager.listObjects(filterMap);
        if (oavdus != null && oavdus.size() > 0) {
            for (OaVideoInformation o : oavdus) {
                filterMap.put("no", o.getNo());
                List<OaSubvideoInformation> f1 = baseEntityManager
                        .listObjects(filterMap);
                filterMap.remove("no");
                if (f1 != null && f1.size() > 0) {
                    oasubs.addAll(f1);
                }
            }
        }
        if (oasubs != null && oasubs.size() > 0) {
            return oasubs;
        }
        return null;

    }

    @Override
    public String built() {
        super.edit();
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        // 获取节目标号

        object.setNo((String) filterMap.get("no"));
        String no = (String) filterMap.get("no");
        request.setAttribute("no", no);
        return super.built();
    }

    /**
     * 保存方法
     */
    @Override
    public String save() {
        try {
            OaSubvideoInformation le = oaSubvideoInformationMag
                    .getObject(object);
            if (le != null) {
                oaSubvideoInformationMag.copyObjectNotNullProperty(le, object);
                object = le;
                object.setLastmodifytime(DatetimeOpt.currentUtilDate());

            } else {

                FUserDetail user = (FUserDetail) getLoginUser();// 获取当前用户的信息
                object.setCreater(user.getUsercode());
                object.setCreatertime(DatetimeOpt.currentUtilDate());

                // 生成主键
                object.setId(oaSubvideoInformationMag.createid());

                object.setState("F");// 默认未启用状态
            }
            // 配置文件夹
            String basePath = request.getSession().getServletContext()
                    .getRealPath("/upload/videos");
            String path = File.separator + "video";// 视频路径
            String path1 = File.separator + "img";// 图片路径

            String datePath = pathDF.format(new Date());// 视频的日期文件夹

            path = getFilePath(path, datePath);
            path1 = getFilePath(path1, datePath);
            // 获取文件路径oa.home
            String fullPath = basePath + path;
            String fullPath1 = basePath + path1;
            File root = new File(fullPath);
            File root1 = new File(fullPath1);

            if (!root.exists()) {
                FileUtils.forceMkdir(root);
            }
            if (!root1.exists()) {
                FileUtils.forceMkdir(root1);
            }

            byte[] bb = null;
            // 是否沿用首页图片
            if ("0".equals(object.getIsuseprarent())) {
                OaVideoInformation oa = oaVideoInformationManager
                        .getObjectById(object.getNo());
                object.setSmallimage(oa.getSmallImage());
            } else {
                if (upload != null) {
                    FileInputStream fis = new FileInputStream(upload);
                    int len = fis.available();
                    bb = new byte[len];
                    fis.read(bb);
                    String imppath1 = fullPath1 + "\\" + uploadFileName;
                    FileOutputStream fileoutputstream = new FileOutputStream(
                            imppath1);
                    byte tag_bytes[] = bb;
                    fileoutputstream.write(tag_bytes);
                    fileoutputstream.close();
                    fis.close();
                    object.setSmallimage("upload/videos/img/" + datePath + "/"
                            + uploadFileName);
                }

            }

            if (video_ != null) {
                // flag标记是否转码成功
                boolean flag = false;
                // 文件名自定义生成待转码的文件
                String FIname = String.valueOf(System.currentTimeMillis());
                String videofullPath = fullPath + File.separator
                        + video_FileName;
                FileUtils.copyFile(video_, new File(videofullPath));
                // 文件转码后保存的路径
                String codcFilePath = fullPath + File.separator + FIname
                        + ".flv";
                String mediaPicPath = fullPath1 + File.separator
                        + String.valueOf(System.currentTimeMillis()) + ".jpg";

                // 转码工具的路径
                String ffmegPath = request.getSession().getServletContext()
                        .getRealPath("/upload/tools/ffmpeg.exe");
                // 保存图片和视频地址
                object.setVideoPath("upload/videos/video/" + datePath + "/"
                        + FIname + ".flv");
                flag = this.executeCodecs(ffmegPath, videofullPath,
                        codcFilePath, mediaPicPath);
                if (flag) {
                    oaSubvideoInformationMag.saveObject(object);
                }

            } else {
                oaSubvideoInformationMag.saveObject(object);
            }

            savedMessage();
            return "oaSubvideoFormSucces";
        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 视频剧集列表
     */

    public String showVideoList() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        OaVideoInformation v = oaVideoInformationManager
                .getObjectById((String) filterMap.get("no"));
        long i = 1;
        if (v.getClickNum() == null) {
            v.setClickNum(i);
        } else {
            v.setClickNum(v.getClickNum() + 1);
        }
        oaVideoInformationManager.saveObject(v);
        //获取当前年份
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date currentTime = new Date();
        String year = formatter.format(currentTime);
        request.setAttribute("year", Integer.parseInt(year)-5);
        
        // 查询出可观看的视频
        objList = this.videos("", filterMap);
        if (objList != null && objList.size() > 0) {
            for (OaSubvideoInformation o : objList) {
                String videoPaths = request.getScheme() + "://"
                        + request.getServerName() + ":"
                        + request.getServerPort() + request.getContextPath()
                        + "/" + o.getVideoPath();
                o.setVideoPath(videoPaths);
            }
        } else {
            return "showVideoEmpty";
        }
        request.setAttribute("object", objList.get(0));
        return "showVideo";
    }

    /**
     * 观看视频
     * 
     * @return
     */
    public String show() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        object = oaSubvideoInformationMag.getObjectById(object.getId());

        // 记录点击次数
        long i = 1;
        if (object.getClickNum() == null) {
            object.setClickNum(i);
        } else {
            object.setClickNum(object.getClickNum() + 1);
        }
        oaSubvideoInformationMag.saveObject(object);

        filterMap.put("no", object.getNo());
        objList = this.videos("", filterMap);
        if (objList != null && objList.size() > 0) {
            for (OaSubvideoInformation o : objList) {
                String videoPaths = request.getScheme() + "://"
                        + request.getServerName() + ":"
                        + request.getServerPort() + request.getContextPath()
                        + "/" + o.getVideoPath();
                o.setVideoPath(videoPaths);
            }
        }
        return "showVideo";
    }

    public boolean executeCodecs(String ffmpegPath, String upFilePath,
            String codcFilePath, String mediaPicPath) throws Exception {
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
        cutpic.add("640*480"); // 添加截取的图片大小
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
    }

    /**
     * 改变state
     */
    public String updateState() {
        String id = request.getParameter("id");
        String state = request.getParameter("state");
        OaSubvideoInformation le = oaSubvideoInformationMag.getObjectById(id);
        if (le != null) {
            object = le;
            object.setState(state);
            oaSubvideoInformationMag.saveObject(object);
        }
        return "listSubVideo";
    }

    /**
     * 下载照片
     * 
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
        OaSubvideoInformation le = oaSubvideoInformationMag
                .getObjectById(object.getId());
        String fn = "";

        fn = le.getSmallimage().substring(le.getSmallimage().lastIndexOf('\\'));
        File file = new File(le.getSmallimage());
        FileInputStream fis = new FileInputStream(file);
        byte[] bt = null;
        if (fis != null) {
            int len = fis.available();
            bt = new byte[len];
            fis.read(bt);
            setStuffStream(new ByteArrayInputStream(bt));
        }
        fis.close();
        FileOutputStream out = new FileOutputStream(file, true);
        out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });// utf-8
                                                                        // bom
        // out.write(content.getBytes(charset));
        out.close();

        this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
        return "download";
    }

    public String getimg() {
        OaSubvideoInformation le = oaSubvideoInformationMag
                .getObjectById(request.getParameter("id"));
        System.out.println(request.getParameter("id"));
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream out = null;
        File file = new File(le.getSmallimage());
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        byte[] bt = null;
        if (fis != null) {
            int len = 0;
            try {
                len = fis.available();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            bt = new byte[len];
            try {
                fis.read(bt);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            response.setContentType("image/jpeg");

            out = response.getOutputStream();
            // 输出图片
            out.write(bt);
            out.flush();
            out.close();
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

    private static String getFilePath(String path, String... filename) {
        String filePath = path;
        for (String name : filename) {
            filePath += File.separator + name;
        }

        return filePath;
    }

    private static String getFullFilePath(String path) {
        return SysParametersUtils.getUploadHome() + path;
    }

    public File getVideo_() {
        return video_;
    }

    public void setVideo_(File video_) {
        this.video_ = video_;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getVideo_FileName() {
        return video_FileName;
    }

    public void setVideo_FileName(String video_FileName) {
        this.video_FileName = video_FileName;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<FDatadictionary> getInfoTypesList() {
        return infoTypesList;
    }

    public void setInfoTypesList(List<FDatadictionary> infoTypesList) {
        this.infoTypesList = infoTypesList;
    }

    public List getInfoList() {
        return infoList;
    }

    public void setInfoList(List infoList) {
        this.infoList = infoList;
    }

    public String getYearSearch() {
        return yearSearch;
    }

    public void setYearSearch(String yearSearch) {
        this.yearSearch = yearSearch;
    }


}
