package com.centit.app.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.util.CollectionUtils;

import com.centit.app.exception.PublicInfoException;
import com.centit.app.exception.PublicInfoNoAuthorityException;
import com.centit.app.exception.PublicInfoNoFileFoundException;
import com.centit.app.po.FileinfoFs;
import com.centit.app.po.Forum;
import com.centit.app.po.Publicinfo;
import com.centit.app.po.Reply;
import com.centit.app.po.ReplyAnnex;
import com.centit.app.po.ReplyAnnexId;
import com.centit.app.po.Thread;
import com.centit.app.po.ThreadAnnex;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.service.ForumManager;
import com.centit.app.service.PublicinfoManager;
import com.centit.app.service.ReplyManager;
import com.centit.app.service.ThreadManager;
import com.centit.app.util.JacobUtil;
import com.centit.app.util.ZipUtils;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.CommonCodeUtil;
import com.centit.sys.util.SysParametersUtils;

public class PublicinfoAction extends BaseEntityDwzAction<Publicinfo> {
    private PublicinfoManager publicinfoManager;
    private FileinfoFsManager fileinfoFsManager;
    
    public void setFileinfoFsManager(FileinfoFsManager fileinfoFsManager) {
        this.fileinfoFsManager = fileinfoFsManager;
    }

    private ReplyManager replyManager;
    private ThreadManager threadManager;
    private ForumManager forumManager;
    private String path = null;
    
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    private List<Object> dataList = new ArrayList<Object>();


    private static final Log log = LogFactory.getLog(PublicinfoAction.class);

    private static final long serialVersionUID = 1L;
    
    public String infocode;
    public String type;
    
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    
    /**
     * PUBLICFILE 公共文件夹； PERSONFILE 个人文件夹； UNITSAHREFILE 部门共享文件夹
     */
    private String mode;
    
    private String rootunitcode;//部门编号
    
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    public String getRootunitcode() {
        return rootunitcode;
    }

    public void setRootunitcode(String rootunitcode) {
        this.rootunitcode = rootunitcode;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 处理异常
     * 
     * @param e
     */
    private void doWithException(PublicInfoException e) {
        
        if (e instanceof PublicInfoNoFileFoundException) {
            dataMap.put("result", "1");
            dataMap.put("description", e.getMessage());
        }
        else if (e instanceof PublicInfoNoAuthorityException) {
            dataMap.put("result", "2");
            dataMap.put("description", e.getMessage());
            
        }
        else {
            dataMap.put("result", "9");
            dataMap.put("description", e.getMessage());
            
        }
    }
    
    public String listFile() { 
        return "list";
    }
    
    /**
     * 弹出框选择文件夹
     * 
     * @return
     */
    public String selectFolder() {
        String infocode = object.getInfocode();
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        
        List<Publicinfo> folders = new ArrayList<Publicinfo>();
        List<Map<String, Object>> zTreeData = new ArrayList<Map<String, Object>>();
        
        dataMap.clear();
        
        try {
            
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode)) {
                // 首次进入无参数，返回根文件夹
                if (StringUtils.isBlank(infocode)) {
                   Publicinfo root = publicinfoManager.getPublicRootDirectory();
                   folders.addAll(publicinfoManager.queryPublicFolders(root.getInfocode(), uinfo, true));
                }
                // 只返回指定目录下的文件夹
                else {
                   folders.addAll(publicinfoManager.queryPublicFolders(infocode, uinfo, false));
                }
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)) {
                // 首次进入无参数，返回根文件夹
                if (StringUtils.isBlank(infocode)) {
                   Publicinfo root = publicinfoManager.getPersonalRootDirectory(uinfo);
                   folders.addAll(publicinfoManager.queryPersonalFolders(root.getInfocode(), uinfo, true));
                }
                // 只返回指定目录下的文件夹
                else {
                   folders.addAll(publicinfoManager.queryPersonalFolders(infocode, uinfo, false));
                }
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            
            // 转换成zTree所需要的数据格式
            for(Publicinfo info : folders) {
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("id", info.getInfocode());
                data.put("pId", info.getParentinfocode());
                data.put("name", info.getFilename());
                data.put("isParent", true);
                data.put("authority", info.getAuthority());
                
                zTreeData.add(data);
            }
            
            dataList.addAll(zTreeData);
            
        } catch (PublicInfoException e) {
           doWithException(e);
        }
        
        return "ztree";
    }
    
    public String download() {
        String infocode = request.getParameter("infocode");
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        List<String> codes = new ArrayList<String>();
        dataMap.clear();
        
        try {
            if (!StringUtils.isBlank(infocode)) {
                codes.addAll(Arrays.asList(infocode.split(",")));
            }
            List<Publicinfo> infos = null;
            
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode)) {
                infos = publicinfoManager.queryAllPublicFileChilds(codes, uinfo);
            }else if(CommonCodeUtil.UNITFILEINFO_OPTCODE.equals(mode)){
                infos = publicinfoManager.queryAllUnitPublicFileChilds(codes, uinfo);
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)) {
                infos = publicinfoManager.queryAllPersonalFileChilds(codes, uinfo);
            }
           // 部门共享
            else if (CommonCodeUtil.UNITFILESHARE_OPTCODE.equals(mode)) {
                infos = publicinfoManager.queryAllUnitShareFileChilds(codes, uinfo);//部门共享根目录
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            Collections.reverse(infos);
            
            if (infos.isEmpty()) {
                throw new PublicInfoNoFileFoundException("没有可以下载的文件");
            }
            
            Publicinfo info = infos.get(0);
            
            // 单个文件直接下载返回
            if (1 == infos.size() && !StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, info.getIsfolder())) {
                dataMap.put("result", "0");
                dataMap.put("filecode", info.getFileinfoFs().getFilecode());
                
                return "files";
            }
            
            
            // 对于文件夹或者多个文件，采取先压缩后下载的方法
            File zipFile = createZipFile(info.getFilename()+"等【批量下载】.zip", uinfo);
            
            // 压缩
            ZipUtils.zipPublicinfo(infos, zipFile);
            
            FileinfoFs zipFileinfo = new FileinfoFs();
            zipFileinfo.setOptcode("TEMP");
            zipFileinfo.setRecorder(uinfo.getUsercode());
            
            // 保存至FILEINFO
            fileinfoFsManager.saveFileinfo(zipFileinfo, zipFile);
            
            dataMap.put("result", "0");
            dataMap.put("filecode", zipFileinfo.getFilecode());
        } catch (PublicInfoException pe) {
            doWithException(pe);
        } 
        catch (Exception e) {
            dataMap.put("result", "9");
            dataMap.put("description", "下载时发生错误\n" + e.getMessage());
            
        }
        
        return "files";
    }

    /**
     * 查询公共文件夹目录
     * 
     * @return
     */
    public String dirPublicFolder() {
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        dataMap.clear();
        
        List<Publicinfo> files= new ArrayList<Publicinfo>();
        files = null;
        Publicinfo file = null;
        Publicinfo root = null;
        
        try {
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode) ) {
                root = publicinfoManager.getPublicRootDirectory();
            // 部门文档
            }else if (CommonCodeUtil.UNITFILEINFO_OPTCODE.equals(mode)){
                root = publicinfoManager.getUnitRootDirectory(uinfo);
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)) {
                root = publicinfoManager.getPersonalRootDirectory(uinfo);
            }
            // 部门共享
            else if (CommonCodeUtil.UNITFILESHARE_OPTCODE.equals(mode)) {
                root = publicinfoManager.getUnitShareRootDirectory(rootunitcode,uinfo);//部门共享根目录
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            
            // 第一次进入页面时获取用户根目录
            if (StringUtils.isBlank(path)) {
                file = root;
                
            } else {
                file = publicinfoManager.getObjectById(path);
            }
            
            // 公共文件夹 
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode)) {
                // 鉴权是否能够进入文件夹
                publicinfoManager.authenticate4EntryPublicDirectory(file, uinfo);
                files = publicinfoManager.queryPublicFiles(file.getInfocode(), uinfo);
                //将不存在的文件从列表中移除
                if(files!=null&&files.size()>0){
                    for(int i=0;i<files.size();i++){
                        if(files.get(i).getFileinfoFs()!=null){
                            FileinfoFs info = fileinfoFsManager.getObjectById(files.get(i).getFileinfoFs().getFilecode());
                            String path = FilenameUtils.normalize(SysParametersUtils.getUploadHome() + info.getPath());
                            File ffile = new File(path);
                            if (!ffile.exists()) {//验证文件是否存在
                                files.remove(i);
                                i--;
                            }
                        }
                    }
                }
                
                dataMap.put("authority", publicinfoManager.getAuthority(file, uinfo));
            // 部门文档
            }else if(CommonCodeUtil.UNITFILEINFO_OPTCODE.equals(mode)) {
                // 鉴权是否能够进入文件夹
                publicinfoManager.authenticate4EntryUnitFileDirectory(file, uinfo);
                files = publicinfoManager.queryUnitFiles(file.getInfocode(), uinfo);
                //将不存在的文件从列表中移除
                if(files!=null&&files.size()>0){
                    for(int i=0;i<files.size();i++){
                        if(files.get(i).getFileinfoFs()!=null){
                            FileinfoFs info = fileinfoFsManager.getObjectById(files.get(i).getFileinfoFs().getFilecode());
                            String path = FilenameUtils.normalize(SysParametersUtils.getUploadHome() + info.getPath());
                            File ffile = new File(path);
                            if (!ffile.exists()) {//验证文件是否存在
                                files.remove(i);
                                i--;
                            }
                        }
                    }
                }
                dataMap.put("authority", publicinfoManager.getUnitAuthority(file, uinfo));
            }
            
            // 部门共享文件夹
            else if (CommonCodeUtil.UNITFILESHARE_OPTCODE.equals(mode)) {
                // 鉴权是否能够进入文件夹
                publicinfoManager.authenticate4EntryUnitShareDirectory(file, uinfo);
                files = publicinfoManager.queryUnitShareFiles(file.getInfocode(), uinfo,rootunitcode);
                //将不存在的文件从列表中移除
                if(files!=null&&files.size()>0){
                    for(int i=0;i<files.size();i++){
                        if(files.get(i).getFileinfoFs()!=null){
                            FileinfoFs info = fileinfoFsManager.getObjectById(files.get(i).getFileinfoFs().getFilecode());
                            String path = FilenameUtils.normalize(SysParametersUtils.getUploadHome() + info.getPath());
                            File ffile = new File(path);
                            if (!ffile.exists()) {//验证文件是否存在
                                files.remove(i);
                                i--;
                            }
                        }
                    }
                }
                
                dataMap.put("authority", publicinfoManager.getUnitShareAuthority(file, uinfo));
                dataMap.put("rootunitcode", file.getUnitcode());
            
            }
            // 个人文件夹
            else {
                // 鉴权是否能够进入文件夹
                publicinfoManager.authenticate4EntryPersonalDirectory(file, uinfo);
                files = publicinfoManager.queryPersonalFiles(file.getInfocode(), uinfo);
                //将不存在的文件从列表中移除
                if(files!=null&&files.size()>0){
                    for(int i=0;i<files.size();i++){
                        if(files.get(i).getFileinfoFs()!=null){
                            FileinfoFs info = fileinfoFsManager.getObjectById(files.get(i).getFileinfoFs().getFilecode());
                            String path = FilenameUtils.normalize(SysParametersUtils.getUploadHome() + info.getPath());
                            File ffile = new File(path);
                            if (!ffile.exists()) {//验证文件是否存在
                                files.remove(i);
                                i--;
                            }
                        }
                    }
                }
                dataMap.put("authority", publicinfoManager.getPersonalAuthority(file, uinfo));
            }
            
            dataMap.put("isOneself", publicinfoManager.isOneself(files, uinfo.getUsercode())) ;
            dataMap.put("path", publicinfoManager.queryFolderParents(file.getInfocode()));
            
            // 查询成功
            dataMap.put("result", "0");
            dataMap.put("files", files);
            
        } catch (PublicInfoException e) {
            doWithException(e);
        } 
        
        // 用户信息
        dataMap.put("usercode", uinfo.getUsercode());
        dataMap.put("username", CodeRepositoryUtil.getValue("usercode", uinfo.getUsercode()));
        dataMap.put("userunit", uinfo.getPrimaryUnit());
        
        // 当前文件夹信息
        if (null != file) {
            dataMap.put("infocode", file.getInfocode());
            dataMap.put("fileunit", file.getUnitcode());
            dataMap.put("parentcode", file.getParentinfocode());
        }
        
        // 部门文件夹信息
        if (null != root) {
            dataMap.put("unitroot", root.getInfocode());
        }

        return "files";
    }
    
    
   
    /**
     * 新建文件夹
     * 
     * @return
     */
    public String addFolder() {
        String filename = request.getParameter("filename");
        String root = request.getParameter("root");
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        dataMap.clear();
        
        try {
            
            Publicinfo folder = null;
            
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode)) {
                folder = publicinfoManager.authenticate4AddFolder(filename, root, uinfo);
            }
            // 部门文档
            else if(CommonCodeUtil.UNITFILEINFO_OPTCODE.equals(mode)){
                folder = publicinfoManager.authenticate4AddUnitFolder(filename, root, uinfo);
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)){
                folder = publicinfoManager.authenticate4AddPersonalFolder(filename, root, uinfo);
            }
            // 部门共享
            else if (CommonCodeUtil.UNITFILESHARE_OPTCODE.equals(mode)) {
                folder = publicinfoManager.authenticate4AddUnitShareFolder(filename, root, uinfo,rootunitcode);//部门共享根目录
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            
            publicinfoManager.saveObject(folder);
            
            dataMap.put("result", "0");
            dataMap.put("file", folder);
            
        } catch (PublicInfoException e) {
            doWithException(e);
        } 
        
        return "files";
    }
    
    /**
     * 删除文件
     * @return
     */
    public String delete() {
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        dataMap.clear();
        
        try {
            List<Publicinfo> toDeleteFiles = null;
            
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode) ) {
                toDeleteFiles = publicinfoManager.authenticate4DeleteFiles(request.getParameter("infocode"), uinfo);
            }
            //部门文档
            else if ( CommonCodeUtil.UNITFILEINFO_OPTCODE.equals(mode)) {
                toDeleteFiles = publicinfoManager.authenticate4DeleteGRBGBMWDFiles(request.getParameter("infocode"), uinfo);
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)){
                toDeleteFiles = publicinfoManager.authenticate4DeletePersonalFiles(request.getParameter("infocode"), uinfo);
            }
            // 部门共享
            else if (CommonCodeUtil.UNITFILESHARE_OPTCODE.equals(mode)) {
                toDeleteFiles = publicinfoManager.authenticate4DeleteUnitShareFiles(request.getParameter("infocode"), uinfo);//部门共享根目录
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            
            for (Publicinfo info : toDeleteFiles) {
//                this.deleteUploadFile(info);
                publicinfoManager.deleteFile(info);
//                if(StringUtils.isNotBlank(info.getInfocode())){
//                    Searcher.deleteIndexByQuery(info.getInfocode(), mode);
//                }                
            }
            
            dataMap.put("result", "0");
            
        } catch (PublicInfoException e) {
            doWithException(e);
        }
        
        return "files";
    }
    
    public void deleteUploadFile(Publicinfo info){
        
        if (null == info.getFileinfoFs()) {
            return;
        }
        
        String destPathHeader = request.getSession().getServletContext().getRealPath("upload");
        String relPath = new File(info.getFileinfoFs().getPath()).getParent() + File.separator + info.getInfocode();

        File fileDelete = null;
        if (info.getFileinfoFs().getFileext().equals("pdf")) {
            fileDelete = new File(destPathHeader + relPath + ".pdf");                     
        }else fileDelete = new File(destPathHeader + relPath + ".html");
        
        if(fileDelete.exists()){
            fileDelete.delete();
        }        
    }
    
    public String copy() {
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        dataMap.clear();
        
        String copyTo = request.getParameter("toInfocode");
        
        try {
            
            // 获取复制到的文件夹
            Publicinfo copy2File = publicinfoManager.getObjectById(copyTo);
            
            // 获取需要复制的文件夹
            List<Publicinfo> copyFiles;
            
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode)) {
                copyFiles = publicinfoManager.authenticate4CopyFiles(object.getInfocode(), copy2File, uinfo);
                
                // 复制到新的文件对象
                for(Publicinfo file : copyFiles) {
                    publicinfoManager.saveCopyFile(file, copy2File, uinfo);
                }
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)){
                copyFiles = publicinfoManager.authenticate4CopyPersonalFiles(object.getInfocode(), copy2File, uinfo);
                
                // 复制到新的文件对象
                for(Publicinfo file : copyFiles) {
                    publicinfoManager.saveCopyPersonalFile(file, copy2File, uinfo);
                }
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            
            dataMap.put("result", "0");
            dataMap.put("file", copy2File);
            
        } catch (PublicInfoException e) {
           doWithException(e);
        }
        
        return "files";
    }
    
    /**
     * 移动对象
     * 
     * @return
     */
    public String move() {
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        dataMap.clear();
        
        String moveTo = request.getParameter("toInfocode");
        
        try {
            // 获取移动到的文件夹
            Publicinfo move2File = publicinfoManager.getObjectById(moveTo);
            
            // 获取需要复制的文件夹
            List<Publicinfo> moveFiles;
            
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode)) {
                // 获取需要复制的文件夹
                moveFiles = publicinfoManager.authenticate4MoveFiles(object.getInfocode(), move2File, uinfo);
                
                // 复制到新的文件对象
                for(Publicinfo file : moveFiles) {
                    publicinfoManager.saveMoveFile(file, move2File, uinfo);
                }
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)){
             // 获取需要复制的文件夹
                moveFiles = publicinfoManager.authenticate4MovePersonalFiles(object.getInfocode(), move2File, uinfo);
                
                // 复制到新的文件对象
                for(Publicinfo file : moveFiles) {
                    publicinfoManager.saveMovePersonalFile(file, move2File, uinfo);
                }
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            
            dataMap.put("result", "0");
            dataMap.put("file", move2File);
            
        } catch (PublicInfoException e) {
           doWithException(e);
        }
        
        return "files";
    }
    
    /**
     * 重命名文件
     * @return
     */
    public String rename() {
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        dataMap.clear();
        
        String infocode = request.getParameter("infocode");
        String name = request.getParameter("name");
        String root = request.getParameter("root");
        
        try {
            // 鉴权重命名文件
            Publicinfo info = null;
            
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode)) {
                info = publicinfoManager.authenticate4RenameFiles(infocode, root, name, uinfo);
            // 部门文档
            }else if(CommonCodeUtil.UNITFILEINFO_OPTCODE.equals(mode)){
                info = publicinfoManager.authenticate4RenameUnitFiles(infocode, root, name, uinfo);
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)){
                info = publicinfoManager.authenticate4RenamePersonalFiles(infocode, root, name, uinfo);
            }
            // 部门共享
            else if (CommonCodeUtil.UNITFILESHARE_OPTCODE.equals(mode)) {
                info = publicinfoManager.authenticate4RenameUnitShareFiles(infocode,root, name, uinfo);//部门共享
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            
            // 重命名数据库记录
            info = publicinfoManager.updateFilename(info, name);
            
            dataMap.put("result", "0");
            dataMap.put("file", info);
            
        } catch (PublicInfoException e) {
            doWithException(e);
        }
        
        return "files";
    }

    /**
     * 上传文件
     * 
     * @return
     */
    public String upload() {
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        dataMap.clear();
        
        try {
            // 鉴权上传文件
            Publicinfo root = null;
            
            // 公共文件夹
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode) ) {
                root = publicinfoManager.authenticate4UploadFile(path, uinfo);
            // 部门文档
            }else if(CommonCodeUtil.UNITFILEINFO_OPTCODE.equals(mode)){
                root = publicinfoManager.authenticate4UploadUnitFile(path, uinfo);
            }
            // 个人文件夹
            else if (CommonCodeUtil.PERSONAL_OPTCODE.equals(mode)){
                root = publicinfoManager.authenticate4UploadPersonalFile(path, uinfo);
            } 
            // 部门共享
            else if (CommonCodeUtil.UNITFILESHARE_OPTCODE.equals(mode)) {
                root = publicinfoManager.authenticate4UploadUnitShareFile(path,rootunitcode,uinfo);//部门共享根目录
            }
            else {
                throw new PublicInfoException("无效的业务类型。");
            }
            
            Publicinfo info = null;
            
            MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
            File[] items = wrapper.getFiles("Filedata");
            String[] names = wrapper.getFileNames("Filedata");
            
            // 公共文件夹 
            if (CommonCodeUtil.PUBLICINFO_OPTCODE.equals(mode)) {
                for (int i=0; i < items.length; i++) {
                    FileinfoFs temp = processUploadedFile(items[i], names[i], CommonCodeUtil.PUBLICINFO_OPTCODE);
                    info = publicinfoManager.saveFile(temp, root, uinfo);
                }
            }
            // 部门文档
            else if (CommonCodeUtil.UNITFILEINFO_OPTCODE.equals(mode)) {
                for (int i=0; i < items.length; i++) {
                    FileinfoFs temp = processUploadedFile(items[i], names[i], CommonCodeUtil.UNITFILEINFO_OPTCODE);
                    info = publicinfoManager.saveUnitFile(temp, root, uinfo);
                }
            }
         // 部门共享
            else if (CommonCodeUtil.UNITFILESHARE_OPTCODE.equals(mode)) {
                for (int i=0; i < items.length; i++) {
                    FileinfoFs temp = processUploadedFile(items[i], names[i], CommonCodeUtil.UNITFILESHARE_OPTCODE);
                    info = publicinfoManager.saveUnitShareFile(temp, root, uinfo,rootunitcode);
                }
                
            }
            // 个人文件夹
            else {
                for (int i=0; i < items.length; i++) {
                    FileinfoFs temp = processUploadedFile(items[i], names[i], CommonCodeUtil.PERSONAL_OPTCODE);
                    info = publicinfoManager.savePersonalFile(temp, root, uinfo);
                }
            }
            
            info.setUploader(CodeRepositoryUtil.getValue("usercode", uinfo.getUsercode()));
            dataMap.put("result", "0");
            dataMap.put("description", "上传文件成功。");
            dataMap.put("file", info);
            
        } catch (PublicInfoException e) {
            doWithException(e);
        } catch (IOException e) {
            dataMap.put("result", "3");
            dataMap.put("description", e.getMessage());
        } 
        
        return "files";
    }
    
    public Thread createThread(Publicinfo info){
        Forum forum = forumManager.getExternalUse("PUBLIC", "UPLOADFI");
        forumManager.saveObject(forum);

        Thread thread = threadManager.getExternalUse(forum, info.getInfocode());
        threadManager.saveObject(thread);
        
        return thread;
    }
    
    public String dirPersonalFolder() {

        return null;
    }
    
    public String convertDocToHtml(Publicinfo info){

        String sourcePath = SysParametersUtils.getUploadHome() + info.getFileinfoFs().getPath();
                
        //System.out.println(this.getClass().getClassLoader().getResource("").getPath());      
        
//        String s = ServletActionContext.getContext().getRealPath("/upload");

        String destPathHeader  = request.getSession().getServletContext().getRealPath("upload");
        String folderPath = new File(info.getFileinfoFs().getPath()).getParent();
        String relPath = folderPath + File.separator + info.getInfocode() + ("txt".equals(info.getFileinfoFs().getFileext()) ? ".html" :".pdf");
        
        String fileDir = destPathHeader + relPath;  

        // 创建文件夹
        File folder = new File(destPathHeader + folderPath);
        folder.mkdirs();
        
        if(!new File(destPathHeader + relPath).exists()){
            if ("doc".equals(info.getFileinfoFs().getFileext()) || "docx".equals(info.getFileinfoFs().getFileext())) {
                JacobUtil.wordToPDF(sourcePath, fileDir);
            } else if ("xls".equals(info.getFileinfoFs().getFileext())
                    || "xlsx".equals(info.getFileinfoFs().getFileext())) {
                JacobUtil.excel2pdf(sourcePath, fileDir);
            } else if("ppt".equals(info.getFileinfoFs().getFileext()) || "pptx".equals(info.getFileinfoFs().getFileext()))
                JacobUtil.ppt2pdf(sourcePath, fileDir);
            else {
                this.txtToHtml(sourcePath, fileDir);
            }
        }       

        return relPath;  
        
    }
    
    public void txtToHtml(String sourcePath,String filePath){
        try {
        	
        	File file = new File(filePath);
            File parent = file.getParentFile();

            parent.mkdirs();           

            BufferedReader br = new BufferedReader(new FileReader(sourcePath));
            BufferedWriter wr = new BufferedWriter(new FileWriter(file));
            
            String s = null;
            
            while(null != (s = br.readLine())){
                wr.write(s);
                wr.write("<br></br>");
            }
            
            wr.flush();
            wr.close();           
            br.close();
                      
        } catch (Exception e) {
            e.printStackTrace();
       }
    }
    
    public String pdfOfcopy(Publicinfo info){
        try{
            String sourcePath = SysParametersUtils.getUploadHome() + info.getFileinfoFs().getPath();
            String destPathHeader = request.getSession().getServletContext().getRealPath("upload");
            
            String folderPath = new File(info.getFileinfoFs().getPath()).getParent();
            String relDir = folderPath + File.separator + info.getInfocode() + ".pdf";
            
            // 创建文件夹
            File folder = new File(destPathHeader + folderPath);
            folder.mkdirs();
            
            File pdfDest = new File(destPathHeader + relDir);
            File source = new File(sourcePath);

            if(pdfDest.createNewFile()){
                if(null != pdfDest && null != source){
                    FileUtils.copyFile( source,pdfDest);              
                }
            }         
            
            return relDir;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public File htmlFile(FileinfoFs info){
        if(null != info){
            File file = new File(info.getPath()).getParentFile();  
            String htmlPath = SysParametersUtils.getUploadHome() + file.getPath() + File.separator + CommonCodeUtil.PUBLICINFO_HTML;
            
            return  new File(htmlPath);
        } else return null;            
               
    }

    public String view(){

        String infocode = request.getParameter("infocode");
        String type = request.getParameter("type");
     
        Publicinfo info = null;
        String filesrc = null;

        info = publicinfoManager.getObjectById(infocode);               
        
        if(null != info){
            if ("word".equals(type) || "excel".equals(type) || "ppt".equals(type) || "txt".equals(type)){
                
                filesrc = this.convertDocToHtml(info);
            }            
            else if ("pdf".equals(type))
                filesrc = this.pdfOfcopy(info); 
                
            ServletActionContext.getContext().put("filesrc",filesrc.replaceAll("\\\\", "/"));
        }
                    
        JSONArray array = new JSONArray();
        array.addAll(this.listFiles());
        ServletActionContext.getContext().put("type",type);
        
        ServletActionContext.getContext().put("files",array.toString());

        ServletActionContext.getContext().put("parentcodes",this.getParentcodes(infocode));                                       
        
        ServletActionContext.getContext().put("infocode",infocode);
        Reply reply = new Reply();
        reply.setThread(this.createThread(info));
        
        ServletActionContext.getContext().put("reply",this.getReply(reply));    
        ServletActionContext.getContext().put("replys",this.getReplys(this.getReply(reply)));    

        String newWin = request.getParameter("newWin");
        if("1".equals(newWin)){
            return "viewNew";
        }
        return VIEW;
    }
    
    public String saveComment(){
        
        String threadid = request.getParameter("thread.threadid");
        String replyAnnexId = request.getParameter("replyAnnexId");
        String comment = request.getParameter("reply");
        
        Thread thread = threadManager.getObjectById(Long.parseLong(threadid));
        thread.setReplnum(null == thread.getReplnum() ? 1 : thread.getReplnum() + 1);
        thread.setPosttime(new Date());

        Reply reply = new Reply();
        reply.setReply(comment);
        reply.setThread(thread);

        reply.setUserid(getLoginUsercode());
        reply.setReplytime(new Date());

        List<ReplyAnnex> replyAnnexes = new ArrayList<ReplyAnnex>();
        
        if (StringUtils.isNotBlank(replyAnnexId)) {
            for (String s : replyAnnexId.split(",")) {
                replyAnnexes.add(new ReplyAnnex(new ReplyAnnexId(null, StringUtils.trim(s))));
            }
        }

        if(StringUtils.isNotBlank(comment) || StringUtils.isNotBlank(replyAnnexId)){
            replyManager.saveObject(reply, replyAnnexes);
        }
        
        this.setInfocode(request.getParameter("infocode"));
        this.setType(request.getParameter("type"));
        return "saveComment";
    }
    
    public String getTextData(String filePath){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            StringBuffer sb = new StringBuffer("");
            String s = null;
            if(br.ready()){
                while(( s = br.readLine()) != null){
                    sb.append(s);
                }
            }           
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }       
    }
    
    public List<Object> listFiles(){
                      
        FUserDetail uinfo = ((FUserDetail) getLoginUser());
        List<Object> files = null;
        Publicinfo file = null;
        Publicinfo root = null;
        
        try {
            root = publicinfoManager.getPublicRootDirectory(uinfo.getPrimaryUnit());

            if (StringUtils.isBlank(path)) {
                file = root;
                
            } else {
                file = publicinfoManager.getObjectById(path);
            }

            files = publicinfoManager.listFiles(file.getInfocode(), uinfo);
        } catch (PublicInfoException e) {
            doWithException(e);
        } 
        
        return files;
    }
    
    public String getParentcodes(String infocode){
        List<Map<String,String>> parent = publicinfoManager.queryFolderParents(infocode);
        List<String> parentcodes = new ArrayList<String>();
        
        if(null != parent && parent.size() != 0){
            for(int i=0;i<parent.size()-1;i++){
                parentcodes.add(parent.get(i).get("INFOCODE"));
            }
        }
        JSONArray array = new JSONArray();
        array.addAll(parentcodes);
        return array.toString();
    }
    
    public Reply getReply(Reply reply){
        
        Thread thread = threadManager.getObject(reply.getThread());
        thread.setViewnum(null == thread.getViewnum() ? 1 : thread.getViewnum() + 1);
        threadManager.saveObject(thread);

        //附件
        if (!CollectionUtils.isEmpty(thread.getThreadAnnexs())){
            List<FileinfoFs> fileinfos = new ArrayList<FileinfoFs>();
            for (ThreadAnnex threadAnnex : thread.getThreadAnnexs()) {
                fileinfos.add(fileinfoFsManager.getObjectById(threadAnnex.getCid().getFilecode()));
            }

            ServletActionContext.getContext().put("fileinfos", fileinfos);
        }

        reply.setThread(thread);
        
        return reply;

    }
    
    public List<Reply> getReplys(Reply obj){

        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        filterMap.put("threadid", obj.getThread().getThreadid());
        
        PageDesc pageDesc = makePageDesc();
        List<Reply> objList = replyManager.listObjects(filterMap, pageDesc);

        //附件
        Map<Long, List<FileinfoFs>> replyAnnexs = new HashMap<Long, List<FileinfoFs>>();
        for (Reply reply : objList) {
            if (!CollectionUtils.isEmpty(reply.getReplyAnnexs())) {
                List<FileinfoFs> fileinfos = new ArrayList<FileinfoFs>();

                for (ReplyAnnex replyAnnex : reply.getReplyAnnexs()) {
                    fileinfos.add(fileinfoFsManager.getObjectById(replyAnnex.getFilecode()));
                }

                replyAnnexs.put(reply.getReplyid(), fileinfos);
            }
        }
//        ServletActionContext.getContext().put("replyAnnexs", replyAnnexs);
        request.setAttribute("replyAnnexs", replyAnnexs);

        this.pageDesc = pageDesc;
        
        return objList;
    }

    
    /**
     * 处理上传文件
     * 
     * @param item
     * @param path
     * @return
     * @throws IOException
     */
    private FileinfoFs processUploadedFile(File file, String filename, String optCode) throws IOException {
        
        FileinfoFs info = new FileinfoFs();
        info.setFilename(FilenameUtils.getBaseName(filename));
        info.setFileext(FilenameUtils.getExtension(filename));
        info.setFilesize(file.length());
        info.setRecorderDate(new Date());
        info.setOptcode(optCode);
        info.setInstid(1L);
        info.setIndb("0");
        info.setIsindex("1");
        
        fileinfoFsManager.saveFileinfo(info, new FileInputStream(file), optCode);
        
        return info;
    }
    
    private static File createZipFile(String name, FUserDetail user) throws IOException {
        String path = SysParametersUtils.getUploadTempHome() + File.separator + user.getUsercode();
        
        // 检查文件夹是否存在
        File root = new File(path);
        if (!root.exists()) {
            FileUtils.forceMkdir(root);
        }
        
        File file = new File(path + File.separator + name);
        
        return file;
    }
    
    //部门共享文件
    public  String  unitFilePanel(){
        //部门树
        JSONArray ja =oaPowerrolergroupManager.putUnitsTree();
        request.setAttribute("unit", ja.toString());
        
      
        return "unitFilePanel";
        
    }
    
    
    private String getLoginUsercode() {
        return ((FUserinfo)super.getLoginUser()).getUsercode();
    }
    
    public void setPublicinfoManager(PublicinfoManager publicinfoManager) {
        this.publicinfoManager = publicinfoManager;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    
    public void setReplyManager(ReplyManager replyManager) {
        this.replyManager = replyManager;
    }
    
    public void setThreadManager(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }   

    public void setForumManager(ForumManager forumManager) {
        this.forumManager = forumManager;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

   

    

}
