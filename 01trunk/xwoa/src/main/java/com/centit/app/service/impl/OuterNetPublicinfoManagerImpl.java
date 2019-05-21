package com.centit.app.service.impl;

import com.centit.app.dao.OuterNetFileinfoFsDao;
import com.centit.app.dao.OuterNetPublicinfoDao;
import com.centit.app.exception.PublicInfoException;
import com.centit.app.exception.PublicInfoNoAuthorityException;
import com.centit.app.exception.PublicInfoNoFileFoundException;
import com.centit.app.po.OuterNetFileinfoFs;
import com.centit.app.po.OuterNetPublicinfo;
import com.centit.app.service.OuterNetPublicinfoManager;
import com.centit.core.dao.CodeBook;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.support.searcher.DocDesc;
import com.centit.sys.dao.TaskLogDao;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.IndexManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.service.impl.IndexManagerImpl;
import com.centit.sys.util.CommonCodeUtil;
import com.centit.sys.util.SysParametersUtils;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO Class description should be added
 * 
 * @author zk
 * @create 2013-2-25
 * @version 
 */
public class OuterNetPublicinfoManagerImpl extends BaseEntityManagerImpl<OuterNetPublicinfo> implements OuterNetPublicinfoManager{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 文件名校验规则
	 */
	private static final Pattern FILENAME_PATTERN = Pattern.compile("\\.|\\*|\\?|\\%|\\_");

	public static final Log log = LogFactory.getLog(OuterNetPublicinfoManagerImpl.class);

	private OuterNetPublicinfoDao publicinfoDao ;
	public void setPublicinfoDao(OuterNetPublicinfoDao baseDao)
	{
		this.publicinfoDao = baseDao;
		setBaseDao(this.publicinfoDao);
	}
	
	private TaskLogDao taskLogDao;
	
	private OuterNetFileinfoFsDao fileinfoFsDao;
	
 

    public void setFileinfoFsDao(OuterNetFileinfoFsDao fileinfoFsDao) {
        this.fileinfoFsDao = fileinfoFsDao;
    }

    public void setTaskLogDao(TaskLogDao taskLogDao) {
        this.taskLogDao = taskLogDao;
    }
    
    private IndexManager indexManager;
	
    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }
    
    private SysUserManager sysUserManager;
    
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }
    
    /**
     * 列出某个用户所有的文件
     * @param ownercode
     * @param infocode
     * @return
     */
    public List<OuterNetPublicinfo> listAllSubPublicinfos(String ownercode, String infocode) {
        
        return publicinfoDao.listAllSubPublicinfos(ownercode, infocode);
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#getPublicRootDirectory(java.lang.String)
     */
    @Override
    public OuterNetPublicinfo getPublicRootDirectory(String unitcode) 
            throws PublicInfoNoFileFoundException {
        
        OuterNetPublicinfo root = publicinfoDao.getUnitRootDirectory(unitcode);
        
        if (null == root) {
            throw new PublicInfoNoFileFoundException("机构[" + unitcode + "]没有根目录，请联系管理员创建。");
        }
        
        return root;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#getPublicRootDirectory()
     */
    @Override
    public OuterNetPublicinfo getPublicRootDirectory() {
        OuterNetPublicinfo root = publicinfoDao.getUnitRootDirectory();
        
        if (null == root) {
            root = new OuterNetPublicinfo();
            root.setInfocode("1");
            root.setParentinfocode("0");
            root.setFilename("资料库首页");
            root.setUnitcode("0000000");
            root.setOwnercode("u0000000");
            root.setStatus("0");
            root.setUploadtime(new Date());
            root.setModifytime(new Date());
            root.setIsfolder("1");
            root.setFoldertype("9");
            
            publicinfoDao.save(root);
        }
        
        return root;
    }
    
    /**
     * 设置部门文档首页
     * @param user
     * @return
     */
    @Override
    public OuterNetPublicinfo getUnitRootDirectory(FUserDetail user) {
        OuterNetPublicinfo root = publicinfoDao.getUnitFileRootDirectory(sysUserManager.getUserPrimaryUnit(user.getUsercode())
                .getUnitcode());
        
        if (null == root) {
            root = new OuterNetPublicinfo();
            root.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
            root.setParentinfocode("0");
            root.setFilename("部门文档首页");
            root.setUnitcode(sysUserManager.getUserPrimaryUnit(user.getUsercode())
                    .getUnitcode());
            root.setOwnercode(user.getUsercode());
            root.setStatus("0");
            root.setUploadtime(new Date());
            root.setModifytime(new Date());
            root.setIsfolder("1");
            root.setFoldertype("7");
            
            publicinfoDao.save(root);
        }
        
        return root;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#getPublicRootDirectory()
     */
    @Override
    public OuterNetPublicinfo getPersonalRootDirectory(FUserDetail user) {
        OuterNetPublicinfo root = publicinfoDao.getPersonalRootDirectory(user.getUsercode());
        
        if (null == root) {
            root = new OuterNetPublicinfo();
            root.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
            root.setParentinfocode("0");
            root.setFilename("个人文档首页");
            root.setOwnercode(user.getUsercode());
            root.setStatus("0");
            root.setUploadtime(new Date());
            root.setModifytime(new Date());
            root.setFoldertype(CommonCodeUtil.PERSONAL_T_HOME);
            root.setIsfolder(CommonCodeUtil.PUBLICINFO_T_FOLDER);
            
            publicinfoDao.save(root);
        }
        
        return root;
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#queryAllPublicFileChilds(java.util.List)
     */
    @Override
    public List<OuterNetPublicinfo> queryAllPublicFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException {
        List<OuterNetPublicinfo> childrenFiles = new ArrayList<OuterNetPublicinfo>();
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(codes);
        
        // 下载鉴权
        for (OuterNetPublicinfo file : files) {
            if (CommonCodeUtil.PUBLICINFO_T_HOME.equals(file.getFoldertype())) {
                throw new PublicInfoNoAuthorityException("下载内容不能包含首页");
            }
            int auth = getAuthority(file, user);
            if (!authenticate(auth, CommonCodeUtil.PUBLICINFO_AUTHORITY_VIEW)) {
                throw new PublicInfoNoAuthorityException("下载内容包含非权限文件、文件夹");
            }
            
            file.setAuthority(auth+"");
        }
        
        for (OuterNetPublicinfo file : files) {
            childrenFiles.addAll(queryAllChildPublicinfos(file, file.getFilename()));
        }
        
        return childrenFiles;
    }
    
    /**
     * 获取所有部门文档子文件
     * @param codes
     * @param user
     * @return
     * @throws PublicInfoException
     */
    @Override
    public List<OuterNetPublicinfo> queryAllUnitPublicFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException {
        List<OuterNetPublicinfo> childrenFiles = new ArrayList<OuterNetPublicinfo>();
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(codes);
        
        // 下载鉴权
        for (OuterNetPublicinfo file : files) {
            if (CommonCodeUtil.UNITFILE_T_HOME.equals(file.getFoldertype())) {
                throw new PublicInfoNoAuthorityException("下载内容不能包含首页");
            }
            int auth = getAuthority(file, user);
            if (!authenticate(auth, CommonCodeUtil.PUBLICINFO_AUTHORITY_VIEW)) {
                throw new PublicInfoNoAuthorityException("下载内容包含非权限文件、文件夹");
            }
            
            file.setAuthority(auth+"");
        }
        
        for (OuterNetPublicinfo file : files) {
            childrenFiles.addAll(queryAllChildPublicinfos(file, file.getFilename()));
        }
        
        return childrenFiles;
    }
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#queryAllPersonalFileChilds(java.util.List, com.centit.sys.security.FUserDetail)
     */
    public List<OuterNetPublicinfo> queryAllPersonalFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException {
        List<OuterNetPublicinfo> childrenFiles = new ArrayList<OuterNetPublicinfo>();
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(codes);
        
        // 下载鉴权
        for (OuterNetPublicinfo file : files) {
            int auth = getPersonalAuthority(file, user);
            if (!authenticate(auth, CommonCodeUtil.PUBLICINFO_AUTHORITY_VIEW)) {
                throw new PublicInfoNoAuthorityException("下载内容包含非权限文件、文件夹");
            }
            
            file.setAuthority(auth+"");
        }
        
        for (OuterNetPublicinfo file : files) {
            childrenFiles.addAll(queryAllChildPublicinfos(file, file.getFilename()));
        }
        
        return childrenFiles;
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#queryPublicFiles(java.lang.String, com.centit.sys.security.FUserDetail)
     */
    @Override
    public List<OuterNetPublicinfo> queryPublicFiles(String infocode, FUserDetail user) throws PublicInfoException {
        OuterNetPublicinfo file = publicinfoDao.getObjectById(infocode);
        
        List<OuterNetPublicinfo> files = null;
        
        // 查找目录下所有文件、文件夹或者具有查看所有文件权限的用户
        if (!CommonCodeUtil.PUBLICINFO_T_HOME.equals(file.getFoldertype()) || 
                CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "listall")) {
            files = publicinfoDao.listAllPublicinfos(file.getInfocode());
            
        }
        // 首页只查找用户可见文件
        else {
            files = publicinfoDao.listRootPublicinfos(file.getInfocode(), user.getUsercode());
        }
        
        setFilesAuthority(files, user);
        
        return files;
    }
    
    /**
     * 列出部门文档
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    
    @Override
    public List<OuterNetPublicinfo> queryUnitFiles(String infocode, FUserDetail user) throws PublicInfoException {
        OuterNetPublicinfo file = publicinfoDao.getObjectById(infocode);
        
        List<OuterNetPublicinfo> files =null;
        
        // 查找目录下所有文件、文件夹或者具有查看所有文件权限的用户
        if (!CommonCodeUtil.UNITFILE_T_HOME.equals(file.getFoldertype()) || 
                CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "listall")) {
            
            files = publicinfoDao.listUnitAllPublicinfos(infocode,
                    sysUserManager.getUserPrimaryUnit(user.getUsercode())
                            .getUnitcode());
        }
        // 首页只查找用户可见文件
        else {
            files = publicinfoDao.listRootPublicinfos(file.getInfocode(), user.getUsercode());
        }
        
        setUnitFilesAuthority(files, user);
        
        return files;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#queryPersonalFiles(java.lang.String, com.centit.sys.security.FUserDetail)
     */
    @Override
    public List<OuterNetPublicinfo> queryPersonalFiles(String infocode, FUserDetail user) throws PublicInfoException {
        OuterNetPublicinfo file = publicinfoDao.getObjectById(infocode);
        
        List<OuterNetPublicinfo> files  = publicinfoDao.listAllPublicinfos(file.getInfocode());
        
        setPersonalFilesAuthority(files, user);
        
        return files;
    }
    
    /**
     * 判断列出的每个文件是否自己上传的，如果是，将“isOneself”设置为Y，否则为N
     * @param infos
     * @param user
     * @return
     */
    public String isOneself(List<OuterNetPublicinfo> infos, String usercode){
        
        if (null != infos && !infos.isEmpty() && StringUtils.isNotBlank(usercode)) {
            
            for(OuterNetPublicinfo info : infos){
                if(!usercode.equals(info.getOwnercode())){
                   return "N";
                }
            }
        }
        
        return "Y";
        
    }
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#queryPublicFiles(java.lang.String, com.centit.sys.security.FUserDetail)
     */
    @Override
    public List<OuterNetPublicinfo> queryPublicFolders(String infocode, FUserDetail user, boolean includeSelf) throws PublicInfoException {
        OuterNetPublicinfo file = publicinfoDao.getObjectById(infocode);
        List<OuterNetPublicinfo> files = new ArrayList<OuterNetPublicinfo>();
        
        if (null == file) {
            return files;
        }
        
        // 没有权限的人无法使用 复制、移动中选择文件夹下拉框
        if (!CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "add") &&
                !CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall")) {
            return files;
        }
        
        // 没有权限操作文件夹
        if (!authenticate(getAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            return files;
        }
        
        if (includeSelf) {
            files.add(file);
        }
        
        // 首页 且没有 eidtall权限
        if (CommonCodeUtil.PUBLICINFO_T_HOME.equals(file.getFoldertype()) &&
                !CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall")) {
            // 自己机构下的目录
            files.addAll(publicinfoDao.listUnitPublicinfosFolder(file.getInfocode(), user.getPrimaryUnit()));
        }
        else {
            // 查找目录下所有文件夹
            files.addAll(publicinfoDao.listAllPublicinfosFolder(file.getInfocode()));
        }
        
        setFilesAuthority(files, user);
        
        return files;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#queryPersonalFolders(java.lang.String, com.centit.sys.security.FUserDetail, boolean)
     */
    @Override
    public List<OuterNetPublicinfo> queryPersonalFolders(String infocode, FUserDetail user, boolean includeSelf) throws PublicInfoException {
        OuterNetPublicinfo file = publicinfoDao.getObjectById(infocode);
        List<OuterNetPublicinfo> files = new ArrayList<OuterNetPublicinfo>();
        
        if (null == file) {
            return files;
        }
        
        // 没有权限操作文件夹
        if (!authenticate(getPersonalAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY)) {
            return files;
        }
        
        if (includeSelf) {
            files.add(file);
        }
        
        // 查找目录下所有文件夹
        files.addAll(publicinfoDao.listAllPublicinfosFolder(file.getInfocode()));
        
        setPersonalFilesAuthority(files, user);
        
        return files;
    }
    
    /**
     * 查询机构直接父级机构
     * 
     * @param unitcode
     * @return
     */
    @Override
    public List<Map<String,String>> queryFolderParents(String infocode) {
        
        List<Map<String,String>> list = publicinfoDao.listFolderParents(infocode);
        Collections.reverse(list);
        
        return list;
        
    }
    
    /**
     * 获取文件夹父级INFOCODE
     * 
     * @param infocode
     * @return
     */
    private Set<String> queryFolderParentsInfocode(String infocode) {
        
        List<Map<String,String>> list = publicinfoDao.listFolderParents(infocode);
        
        Set<String> codes = new HashSet<String>();
        for(Map<String,String> info : list) {
            codes.add(info.get("INFOCODE"));
        }
        return codes;
        
    }
    
    /**
     * 获取用户单个文件权限
     * 
     * @param file
     * @param user
     * @return
     */
    @Override
    public int getAuthority(OuterNetPublicinfo file, FUserDetail user) {
        Set<String> units = queryUserUnits(user.getUsercode());
        return getFileAuthority(file, user, units);
    }
    
    /**
     * 获取部门用户权限
     * @param file
     * @param user
     * @return
     */
    @Override
    public int getUnitAuthority(OuterNetPublicinfo file, FUserDetail user) {
        return getUnitFileAuthority(file, user);
    }
    
    /**
     * 获取用户单个文件权限
     * 
     * @param file
     * @param user
     * @return
     */
    @Override
    public int getPersonalAuthority(OuterNetPublicinfo file, FUserDetail user) {
        return getPersonalFileAuthority(file, user);
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#saveFile(com.centit.app.po.Fileinfo, com.centit.app.po.OuterNetPublicinfo)
     */
    @Override
    public OuterNetPublicinfo saveFile(OuterNetFileinfoFs file, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo newFolder = createFile(file, root, user);
        renameDuplicationNameFile(newFolder, root.getInfocode());
        publicinfoDao.saveObject(newFolder);
        setFileAuthority(newFolder, user);

        //Map<String, Object> jsonMap = new HashMap<String, Object>();
        DocDesc docDesc = new DocDesc();
        
        //jsonMap.put("dataID", "UPLOADFI" + newFolder.ge++++++++++++tInfocode());
        
        docDesc.setDataID(CommonCodeUtil.PUBLICINFO_OPTCODE + newFolder.getInfocode());
        docDesc.setOptID(CommonCodeUtil.PUBLICINFO_OPTCODE);
        docDesc.setMethod(CommonCodeUtil.PUBLICINFO_OPTCODE);
        docDesc.setOpttag(CommonCodeUtil.PUBLICINFO_OPTCODE);
        docDesc.setTitle(newFolder.getFilename());
        docDesc.setExtension(newFolder.getFileextension());
        docDesc.setOwner(((FUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsercode());

        JSONObject jsonObject = JSONObject.fromObject(docDesc);

        jsonObject.put(IndexManagerImpl.INDEX_TYPE, IndexManagerImpl.INDEX_TYPE_ADD);
        jsonObject.put("type", DocDesc.TYPE_FILE);
        jsonObject.put("infocode", newFolder.getInfocode());
        //taskLogDao.saveObject(new TaskLog("FileToIndex", jsonObject.toString()));
        return newFolder;
    }

    /**
     * 保存部门文档
     * @param file
     * @param root
     * @param user
     * @return
     */
    @Override
    public OuterNetPublicinfo saveUnitFile(OuterNetFileinfoFs file, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo newFolder = createUnitFile(file, root, user);
        renameDuplicationNameFile(newFolder, root.getInfocode());
        publicinfoDao.saveObject(newFolder);
        setUnitFileAuthority(newFolder, user);

        return newFolder;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#savePersonalFile(com.centit.app.po.Fileinfo, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo savePersonalFile(OuterNetFileinfoFs file, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo newFolder = createPersonalFile(file, root, user);
        renameDuplicationNameFile(newFolder, root.getInfocode());
        publicinfoDao.saveObject(newFolder);
        setPersonalFileAuthority(newFolder, user);

        //Map<String, Object> jsonMap = new HashMap<String, Object>();
        DocDesc docDesc = new DocDesc();
        
        //jsonMap.put("dataID", "UPLOADFI" + newFolder.getInfocode());
        
        docDesc.setDataID(CommonCodeUtil.PERSONAL_OPTCODE + newFolder.getInfocode());
        docDesc.setOptID(CommonCodeUtil.PERSONAL_OPTCODE);
        docDesc.setMethod(CommonCodeUtil.PERSONAL_OPTCODE);
        docDesc.setOpttag(CommonCodeUtil.PERSONAL_OPTCODE);
        docDesc.setTitle(newFolder.getFilename());
        docDesc.setExtension(newFolder.getFileextension());
        if(null!=SecurityContextHolder.getContext().getAuthentication()&&null!=SecurityContextHolder.getContext().getAuthentication().getPrincipal()){
        docDesc.setOwner(((FUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsercode());
        }
        JSONObject jsonObject = JSONObject.fromObject(docDesc);

        jsonObject.put(IndexManagerImpl.INDEX_TYPE, IndexManagerImpl.INDEX_TYPE_ADD);
        jsonObject.put("type", DocDesc.TYPE_FILE);
        jsonObject.put("infocode", newFolder.getInfocode());
        //taskLogDao.saveObject(new TaskLog("FileToIndex", jsonObject.toString()));
        
        // 索引
       // indexManager.indexFile(docDesc);
        
        return newFolder;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#saveCopyFile(com.centit.app.po.OuterNetPublicinfo, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo saveCopyFile(OuterNetPublicinfo copyFile, OuterNetPublicinfo copyTo, FUserDetail user) {
        OuterNetPublicinfo newFile = createFile(copyFile, copyTo, user);
        renameDuplicationNameFile(newFile, copyTo.getInfocode());
        
        publicinfoDao.saveObject(newFile);
        setFileAuthority(newFile, user);
        
        // 复制文件夹下子文件夹
        if (StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, newFile.getIsfolder())) {
            List<OuterNetPublicinfo> children = publicinfoDao.listAllPublicinfos(copyFile.getInfocode());
            
            for (OuterNetPublicinfo child : children) {
                saveCopyFile(child, newFile, user);
            }
        }
        
        return newFile;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#saveCopyPersonalFile(com.centit.app.po.OuterNetPublicinfo, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo saveCopyPersonalFile(OuterNetPublicinfo copyFile, OuterNetPublicinfo copyTo, FUserDetail user) {
        OuterNetPublicinfo newFile = createPersonalFile(copyFile, copyTo, user);
        renameDuplicationNameFile(newFile, copyTo.getInfocode());
        
        publicinfoDao.saveObject(newFile);
        setPersonalFileAuthority(newFile, user);
        
        // 复制文件夹下子文件夹
        if (StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, newFile.getIsfolder())) {
            List<OuterNetPublicinfo> children = publicinfoDao.listAllPublicinfos(copyFile.getInfocode());
            
            for (OuterNetPublicinfo child : children) {
                saveCopyPersonalFile(child, newFile, user);
            }
        }
        
        return newFile;
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#saveMoveFile(com.centit.app.po.OuterNetPublicinfo, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo saveMoveFile(OuterNetPublicinfo file, OuterNetPublicinfo move2File, FUserDetail uinfo) {
        // 如果移动到新位置，才保存
        if (!file.getParentinfocode().equals(move2File.getInfocode())) {
            
            OuterNetPublicinfo parent = getRootPublicinfo(move2File);
            String unitcode = parent.getUnitcode();
            String foldertype = parent.getFoldertype();
            
            renameDuplicationNameFile(file, move2File.getInfocode());
            file.setParentinfocode(move2File.getInfocode());
            
            // 移动至他人文件夹需要修改所有子文件夹的类型和unitcode
            if (!CommonCodeUtil.PUBLICINFO_T_HOME.equals(foldertype)
                    && StringUtils.equals(file.getUnitcode(), unitcode)) {
                changePublicinfoUnitAndType(file, parent.getUnitcode(), parent.getFoldertype());
            }
            else {
                publicinfoDao.saveObject(file);
            }
           
        }
        
        setFileAuthority(file, uinfo);
        
        return file;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#saveMovePersonalFile(com.centit.app.po.OuterNetPublicinfo, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo saveMovePersonalFile(OuterNetPublicinfo file, OuterNetPublicinfo move2File, FUserDetail uinfo) {
        // 如果移动到新位置，才保存
        if (!file.getParentinfocode().equals(move2File.getInfocode())) {
            
            renameDuplicationNameFile(file, move2File.getInfocode());
            file.setParentinfocode(move2File.getInfocode());
            
            publicinfoDao.saveObject(file);
           
        }
        
        setPersonalFileAuthority(file, uinfo);
        
        return file;
    }
    
    /**
     * 修改所有子文件夹的类型和unitcode
     * 
     * @param info
     * @param unitcode
     * @param foldertype
     */
    private void changePublicinfoUnitAndType(OuterNetPublicinfo info, String unitcode, String foldertype) {
        info.setUnitcode(unitcode);
        info.setFoldertype(foldertype);
        
        if (StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, info.getIsfolder())) {
               List<OuterNetPublicinfo> children = publicinfoDao.listAllPublicinfos(info.getInfocode());
            
            for (OuterNetPublicinfo child : children) {
                changePublicinfoUnitAndType(child, unitcode, foldertype);
            }
            
        }
        
        publicinfoDao.saveObject(info);
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#deleteFiles(java.lang.String[], com.centit.sys.security.FUserDetail)
     */
    @Override
    public void deleteFile(OuterNetPublicinfo info) throws PublicInfoException {
        if (null == info) {
            return;
        }
        
        String path = null;
        OuterNetFileinfoFs fileinfo = info.getFileinfoFs();
        
        // 当FILEINFO只被唯一一个PUBLICINFO引用时才删除
        if (null != fileinfo && isLastQuoteFileinfo(fileinfo)) {
            path = getFullFilePath(fileinfo.getPath());
        }
        
        // 公共文件和文件信息异步删除
        info.setFileinfoFs(null);
        publicinfoDao.getHibernateTemplate().delete(info);
        
//        if (!StringUtils.isBlank(path)) {
//            File file = new File(path);
//            
//            if (file.exists()) {
//                file.delete();
//            }
//        }
        
        // 不直接删除文件，留给后来定时任务删除
        if (!StringUtils.isBlank(path)) {
            fileinfo.setIsDelete("1");
            
            fileinfoFsDao.getHibernateTemplate().merge(fileinfo);
        }
        
    }
    

    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#updateFilename(java.lang.String, java.lang.String, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo updateFilename(OuterNetPublicinfo info, String name) throws PublicInfoException {
        
        info.setFilename(name);
        
        if (null != info.getFileinfoFs()) {
            info.getFileinfoFs().setFilename(name);
        }
        
        info.setModifytime(new Date());
        
        publicinfoDao.saveObject(info);
        
        return info;
    }

    /**
     * 新建文件夹
     * 
     * @param filename
     * @param user
     * @return
     */
    private OuterNetPublicinfo createFolder(String filename, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo file = new OuterNetPublicinfo();
        
        file.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
        file.setFileinfoFs(null);
        file.setDownloadcount(0L);
        file.setFilename(filename);
        file.setIsfolder(CommonCodeUtil.PUBLICINFO_T_FOLDER);
        file.setModifytime(new Date());
        file.setOwnercode(user.getUsercode());
        file.setReadcount(0L);
        file.setStatus(CommonCodeUtil.PUBLICINFO_T_NORMAL);
        file.setUploadtime(new Date());
        file.setParentinfocode(root.getInfocode());
        
        OuterNetPublicinfo parent = getRootPublicinfo(root);
        
        // 首页新增文件夹
        if (CommonCodeUtil.PUBLICINFO_T_HOME.equals(root.getFoldertype())) {
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall")) {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT);
            }
            else {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM);
                file.setUnitcode(user.getPrimaryUnit());
            }
            
        }
        // 其他位置创建文件夹和顶级父文件夹统一属性
        else {
            file.setFoldertype(parent.getFoldertype());
            file.setUnitcode(parent.getUnitcode());
        }
        
        return file;
    }
    
    /**
     * 新建部门文件夹
     * @param filename
     * @param root
     * @param user
     * @return
     */
    private OuterNetPublicinfo createUnitFolder(String filename, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo file = new OuterNetPublicinfo();
        
        file.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
        file.setFileinfoFs(null);
        file.setDownloadcount(0L);
        file.setFilename(filename);
        file.setIsfolder(CommonCodeUtil.PUBLICINFO_T_FOLDER);
        file.setModifytime(new Date());
        file.setOwnercode(user.getUsercode());
        file.setReadcount(0L);
        file.setStatus(CommonCodeUtil.PUBLICINFO_T_NORMAL);
        file.setUploadtime(new Date());
        file.setParentinfocode(root.getInfocode());
        
        file.setUnitcode(sysUserManager.getUserPrimaryUnit(user.getUsercode()).getUnitcode());
        
        OuterNetPublicinfo parent = getUnitRootPublicinfo(root);
        
        // 首页新增文件夹
        if (CommonCodeUtil.UNITFILE_T_HOME.equals(root.getFoldertype())) {
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "addall")) {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT);
            }
            else {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM);
                file.setUnitcode(user.getPrimaryUnit());
            }
        }
        // 其他位置创建文件夹和顶级父文件夹统一属性
        else {
            file.setFoldertype(parent.getFoldertype());
            file.setUnitcode(parent.getUnitcode());
        }
        
        return file;
    }
    
    /**
     * 新建个人文件夹
     * 
     * @param filename
     * @param user
     * @return
     */
    private OuterNetPublicinfo createPersonalFolder(String filename, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo file = new OuterNetPublicinfo();
        
        file.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
        file.setFileinfoFs(null);
        file.setDownloadcount(0L);
        file.setFilename(filename);
        file.setIsfolder(CommonCodeUtil.PUBLICINFO_T_FOLDER);
        file.setModifytime(new Date());
        file.setOwnercode(user.getUsercode());
        file.setReadcount(0L);
        file.setStatus(CommonCodeUtil.PUBLICINFO_T_NORMAL);
        file.setUploadtime(new Date());
        file.setParentinfocode(root.getInfocode());
        file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PERSONAL_CUSTOM);
        
        return file;
    }
    
    /**
     * 获取顶级节点的unitcode
     * 
     * @param root
     * @return
     */
    private OuterNetPublicinfo getRootPublicinfo(OuterNetPublicinfo root) {
        if (CommonCodeUtil.PUBLICINFO_T_HOME.equals(root.getFoldertype())) {
            return root;
        }
        
        OuterNetPublicinfo parent = publicinfoDao.getObjectById(root.getParentinfocode());
        if (CommonCodeUtil.PUBLICINFO_T_HOME.equals(parent.getFoldertype())) {
            return root;
        }
        
        return getRootPublicinfo(parent);
    }
    
    /**
     * 获取部门文档的顶级接待unitcode
     * @param root
     * @return
     */
    private OuterNetPublicinfo getUnitRootPublicinfo(OuterNetPublicinfo root) {
        if (CommonCodeUtil.UNITFILE_T_HOME.equals(root.getFoldertype())) {
            return root;
        }
        
        OuterNetPublicinfo parent = publicinfoDao.getObjectById(root.getParentinfocode());
        if (CommonCodeUtil.UNITFILE_T_HOME.equals(parent.getFoldertype())) {
            return root;
        }
        
        return getRootPublicinfo(parent);
    }
    
    /**
     * 新建文件
     * 
     * @param file
     * @param root
     * @param user
     * @return
     */
    private OuterNetPublicinfo createFile(OuterNetFileinfoFs info, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo file = new OuterNetPublicinfo();
        
        file.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
        file.setDownloadcount(0L);
        file.setFilename(info.getFilename());
        file.setFileextension(info.getFileext());
        file.setFileinfoFs(info);
        file.setIsfolder("0");
        file.setModifytime(new Date());
        file.setOwnercode(user.getUsercode());
        file.setReadcount(0L);
        file.setFilesize(info.getFilesize());
        file.setStatus(CommonCodeUtil.PUBLICINFO_T_NORMAL);
        file.setUploadtime(new Date());
        file.setParentinfocode(root.getInfocode());
        
        file.setUnitcode(sysUserManager.getUserPrimaryUnit(user.getUsercode()).getUnitcode());
        
        OuterNetPublicinfo parent = getRootPublicinfo(root);
        
        // 首页新增文件夹
        if (CommonCodeUtil.PUBLICINFO_T_HOME.equals(root.getFoldertype())) {
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall")) {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT);
            }
            else {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM);
                file.setUnitcode(user.getPrimaryUnit());
            }
            
        }
        // 其他位置创建文件夹和顶级父文件夹统一属性
        else {
            file.setFoldertype(parent.getFoldertype());
            file.setUnitcode(parent.getUnitcode());
        }
        
        return file;
    }
    
    /**
     * 创建部门文件
     * @param info
     * @param root
     * @param user
     * @return
     */
    private OuterNetPublicinfo createUnitFile(OuterNetFileinfoFs info, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo file = new OuterNetPublicinfo();
        
        file.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
        file.setDownloadcount(0L);
        file.setFilename(info.getFilename());
        file.setFileextension(info.getFileext());
        file.setFileinfoFs(info);
        file.setIsfolder("0");
        file.setModifytime(new Date());
        file.setOwnercode(user.getUsercode());
        file.setReadcount(0L);
        file.setFilesize(info.getFilesize());
        file.setStatus(CommonCodeUtil.PUBLICINFO_T_NORMAL);
        file.setUploadtime(new Date());
        file.setParentinfocode(root.getInfocode());
        
        file.setUnitcode(sysUserManager.getUserPrimaryUnit(user.getUsercode()).getUnitcode());
        
        //file.setFoldertype(CommonCodeUtil.UNITFILE_T_HOME);
        
        OuterNetPublicinfo parent = getUnitRootPublicinfo(root);
        
        // 首页新增文件夹
        if (CommonCodeUtil.UNITFILE_T_HOME.equals(root.getFoldertype())) {
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "addall")) {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT);
            }
            else {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM);
                file.setUnitcode(user.getPrimaryUnit());
            }
            
        }
        // 其他位置创建文件夹和顶级父文件夹统一属性
        else {
            file.setFoldertype(parent.getFoldertype());
            file.setUnitcode(parent.getUnitcode());
        }
        
        return file;
    }
    /**
     * 新建文件
     * 
     * @param file
     * @param root
     * @param user
     * @return
     */
    private OuterNetPublicinfo createPersonalFile(OuterNetFileinfoFs info, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo file = new OuterNetPublicinfo();
        
        file.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
        file.setDownloadcount(0L);
        file.setFilename(info.getFilename());
        file.setFileextension(info.getFileext());
        file.setFileinfoFs(info);
        file.setIsfolder("0");
        file.setModifytime(new Date());
        file.setOwnercode(user.getUsercode());
        file.setReadcount(0L);
        file.setFilesize(info.getFilesize());
        file.setStatus(CommonCodeUtil.PUBLICINFO_T_NORMAL);
        file.setUploadtime(new Date());
        file.setParentinfocode(root.getInfocode());
        file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PERSONAL_CUSTOM);
        
        return file;
    }
    
    /**
     * 新建文件
     * 
     * @param info
     * @param root
     * @param user
     * @return
     */
    private OuterNetPublicinfo createFile(OuterNetPublicinfo info, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo file = new OuterNetPublicinfo();
        
        OuterNetPublicinfo parent = getRootPublicinfo(root);
        
        file.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
        file.setDownloadcount(0L);
        file.setFilename(info.getFilename());
        file.setFileextension(info.getFileextension());
        file.setFileinfoFs(info.getFileinfoFs());
        file.setIsfolder(info.getIsfolder());
        file.setModifytime(new Date());
        file.setOwnercode(user.getUsercode());
        file.setReadcount(0L);
        file.setFilesize(info.getFilesize());
        file.setStatus(CommonCodeUtil.PUBLICINFO_T_NORMAL);
        file.setUploadtime(new Date());
        file.setParentinfocode(root.getInfocode());
        
        // 首页新增文件夹
        if (CommonCodeUtil.PUBLICINFO_T_HOME.equals(root.getFoldertype())) {
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall")) {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT);
            }
            else {
                file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM);
                file.setUnitcode(user.getPrimaryUnit());
            }
            
        }
        // 其他位置创建文件夹和顶级父文件夹统一属性
        else {
            file.setFoldertype(parent.getFoldertype());
            file.setUnitcode(parent.getUnitcode());
        }
        
        return file;
    }
    
    /**
     * 新建个人文件
     * 
     * @param info
     * @param root
     * @param user
     * @return
     */
    private OuterNetPublicinfo createPersonalFile(OuterNetPublicinfo info, OuterNetPublicinfo root, FUserDetail user) {
        OuterNetPublicinfo file = new OuterNetPublicinfo();
        
        file.setInfocode(publicinfoDao.getNextValueOfSequence("s_fileno"));
        file.setDownloadcount(0L);
        file.setFilename(info.getFilename());
        file.setFileextension(info.getFileextension());
        file.setFileinfoFs(info.getFileinfoFs());
        file.setIsfolder(info.getIsfolder());
        file.setModifytime(new Date());
        file.setOwnercode(user.getUsercode());
        file.setReadcount(0L);
        file.setFilesize(info.getFilesize());
        file.setStatus(CommonCodeUtil.PUBLICINFO_T_NORMAL);
        file.setUploadtime(new Date());
        file.setParentinfocode(root.getInfocode());
        
        file.setFoldertype(CommonCodeUtil.PUBLICINFO_T_PERSONAL_CUSTOM);
        file.setUnitcode(user.getPrimaryUnit());
        
        return file;
    }
    
    /**
     * 查找目录下所有子文件
     * 
     * @param file
     * @return
     */
    private List<OuterNetPublicinfo> queryAllChildPublicinfos(OuterNetPublicinfo file, String path) {
        List<OuterNetPublicinfo> files = new ArrayList<OuterNetPublicinfo>();
        
        // 如果是文件直接返回
        if (!StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, file.getIsfolder())) {
            if (StringUtils.isBlank(file.getFileextension())) {
                file.setZipPath(path);
            }
            else {
                file.setZipPath(path + "." + file.getFileextension());
            }
            
            files.add(file);
            return files;
        }
        file.setZipPath(path);
        
        List<OuterNetPublicinfo> childs = publicinfoDao.listAllPublicinfos(file.getInfocode());
        
        for (OuterNetPublicinfo child : childs) {
            files.addAll(queryAllChildPublicinfos(child, path + File.separator + child.getFilename()));
        }
        
        files.add(file);
        return files;
    }

    /**
     * 获取权限
     * 
     * @param authority
     * @param pos
     * @return
     */
    private static boolean authenticate(int authority, int pos) {
        return ((authority >> pos) % 2) == CommonCodeUtil.HAS_AUTHORITY;
    }
    
    private void setFileAuthority(OuterNetPublicinfo file, FUserDetail user) {
        Set<String> units = queryUserUnits(user.getUsercode());
        setFileAuthority(file, user, units);
        file.setUploader(CodeRepositoryUtil.getValue("usercode", file.getOwnercode()));
    }
    
    /**
     * 获取用户文件权限
     * 
     * @param file
     * @param user
     * @param units
     * @return
     */
    private static int getFileAuthority(OuterNetPublicinfo file, FUserDetail user, Set<String> units) {
        int authorityModify = 0;
        int authorityAdd = 0;
        int authorityView = 0;
        
        int authority;
        
        if (!CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PUBLICINFO_T_HOME.equals(file.getFoldertype())) {
            
            return 0;
        }
        
        // 首页：任何人无权限修改
        // 具有ADDALL和ADD权限的人可以上传
        if (CommonCodeUtil.PUBLICINFO_T_HOME.equals(file.getFoldertype())) {
            authorityView = 1;
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall") || 
                    CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "add")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall",user)||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "add",user)) {
                authorityAdd = 1;
            }
        }
        
        // 公共文件、文件夹：所有人可以查看，具有ADDALL和EDITALL的人可以上传和修改、删除
        else if (CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT.equals(file.getFoldertype())) {
            authorityView = 1;
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall",user)) {
                authorityAdd = 1;
            }
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "editall")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "editall",user)) {
                authorityModify = 1;
            }
        }
        
        // 部门的文件、文件夹：
        // 具有LISTALL权限人可以查看、部门人员可以查看 具有list权限的人可以查看所属机构文件夹
        // 具有ADDALL权限人可以上传、具有ADD权限且主机构等于被操作文件夹所属机构的人可以上传
        // 具有EDITALL权限人可以修改、具有EDIT权限且主机构等于被操作文件、文件夹所属机构的人可以修改
        else if (CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM.equals(file.getFoldertype())) {
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "listall")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall",user)) {
                authorityView = 1;
            }
            
            if ((CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "list")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "list",user)) && (units.contains(file.getUnitcode()))) {
                authorityView = 1;
            }
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "addall",user)) {
                authorityAdd = 1;
            }
            
            if ((CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "add") ||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "add",user))&& (user.getPrimaryUnit().equals(file.getUnitcode()))) {
                authorityAdd = 1;
            }
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "editall")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "editall",user)) {
                authorityModify = 1;
            }
            
            if ((CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "edit")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "edit",user)) && (user.getPrimaryUnit().equals(file.getUnitcode()))) {
                authorityModify = 1;
            }
        }
        
        authority = (authorityModify << CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY) 
                + (authorityAdd << CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD) 
                + authorityView;
        
        return authority;
    }
    
    /**
     * 部门用户文档权限
     * @param file
     * @param user
     * @return
     */
    private static int getUnitFileAuthority(OuterNetPublicinfo file, FUserDetail user) {
        int authorityModify = 0;
        int authorityAdd = 0;
        int authorityView = 0;
        
        int authority;
        
        if (!CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT.equals(file.getFoldertype()) &&
                !CommonCodeUtil.UNITFILE_T_HOME.equals(file.getFoldertype())) {
            
            return 0;
        }
        
        // 首页：任何人无权限修改
        // 具有ADDALL和ADD权限的人可以上传
        if (CommonCodeUtil.UNITFILE_T_HOME.equals(file.getFoldertype())) {
            authorityView = 1;
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "addall") || 
                    CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "add")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "addall",user)||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "add",user)) {
                authorityAdd = 1;
            }
        }
        
        // 公共文件、文件夹：所有人可以查看，具有ADDALL和EDITALL的人可以上传和修改、删除
        else if (CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT.equals(file.getFoldertype())) {
            authorityView = 1;
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "addall")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "addall",user)) {
                authorityAdd = 1;
            }
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "editall")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "editall",user)) {
                authorityModify = 1;
            }
        }
        
        // 部门的文件、文件夹：
        // 具有LISTALL权限人可以查看、部门人员可以查看 具有list权限的人可以查看所属机构文件夹
        // 具有ADDALL权限人可以上传、具有ADD权限且主机构等于被操作文件夹所属机构的人可以上传
        // 具有EDITALL权限人可以修改、具有EDIT权限且主机构等于被操作文件、文件夹所属机构的人可以修改
        else if (CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM.equals(file.getFoldertype())) {
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "listall")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "listall",user)) {
                authorityView = 1;
            }
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "list")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "list",user)) {
                authorityView = 1;
            }
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "addall")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "addall",user)) {
                authorityAdd = 1;
            }
            
            if ((CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "add")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "add",user) ) && (user.getPrimaryUnit().equals(file.getUnitcode()))) {
                authorityAdd = 1;
            }
            
            if (CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "editall")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "editall",user)) {
                authorityModify = 1;
            }
            
            if ((CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "edit")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "edit",user)) && (user.getPrimaryUnit().equals(file.getUnitcode()))) {
                authorityModify = 1;
            }
        }
        
        authority = (authorityModify << CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY) 
                + (authorityAdd << CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD) 
                + authorityView;
        
        return authority;
    }
    
    /**
     * 获取个人用户文件权限
     * 
     * @param file
     * @param user
     * @param units
     * @return
     */
    private static int getPersonalFileAuthority(OuterNetPublicinfo file, FUserDetail user) {
        int authorityModify = 0;
        int authorityAdd = 0;
        int authorityView = 0;
        
        int authority;
        
        // 不是个人文件夹（2、3、8）
        if (!CommonCodeUtil.PUBLICINFO_T_PERSONAL_CUSTOM.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PUBLICINFO_T_PERSONAL_DEFAULT.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PERSONAL_T_HOME.equals(file.getFoldertype())) {
            return 0;
        }
        
        if (user.getUsercode().equals(file.getOwnercode())) {
            authorityModify = 1;
            authorityAdd = 1;
            authorityView = 1;
        }
        
        authority = (authorityModify << CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY) 
                + (authorityAdd << CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD) 
                + authorityView;
        
        return authority;
    }
    
    @SuppressWarnings("static-method")
    private void setFileAuthority(OuterNetPublicinfo file, FUserDetail user, Set<String> units) {
        
        file.setAuthority(getFileAuthority(file, user, units)+"");
    }
    
    @SuppressWarnings("static-method")
    private void setPersonalFileAuthority(OuterNetPublicinfo file, FUserDetail user) {
        
        file.setAuthority(getPersonalFileAuthority(file, user)+"");
    }
    
    @SuppressWarnings("static-method")
    private void setUnitFileAuthority(OuterNetPublicinfo file, FUserDetail user) {
        
        file.setAuthority(getUnitFileAuthority(file, user)+"");
    }
    
    /**
     * 获取用户权限
     * 
     * @param files
     */
    private void setFilesAuthority(Collection<OuterNetPublicinfo> files, FUserDetail user) {
        
        Set<String> units = queryUserUnits(user.getUsercode());
        
        for (OuterNetPublicinfo file : files) {
            setFileAuthority(file, user, units);
            file.setUploader(CodeRepositoryUtil.getValue("usercode", file.getOwnercode()));
        }
    }
    
    private void setUnitFilesAuthority(Collection<OuterNetPublicinfo> files, FUserDetail user) {
                
        for (OuterNetPublicinfo file : files) {
            setUnitFileAuthority(file, user);
            file.setUploader(CodeRepositoryUtil.getValue("usercode", file.getOwnercode()));
        }
    }
    
    /**
     * 获取用户权限
     * 
     * @param files
     */
    private void setPersonalFilesAuthority(Collection<OuterNetPublicinfo> files, FUserDetail user) {
        
        for (OuterNetPublicinfo file : files) {
            setPersonalFileAuthority(file, user);
            file.setUploader(CodeRepositoryUtil.getValue("usercode", file.getOwnercode()));
        }
    }
    
    /**
     * 鉴权重命名文件
     * 
     * @param file
     * @param name
     * @param user
     */
    @Override
    public OuterNetPublicinfo authenticate4RenameFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException {
        OuterNetPublicinfo info = publicinfoDao.getObjectById(infocode);
        
        if (null == info) {
            throw new PublicInfoNoFileFoundException("文件不存在。");
        }
        
        info.setFilename(name);
        if (StringUtils.isBlank(name)) {
            throw new PublicInfoException("文件名不能为空，请重新输入。");
        }
        
        Matcher m = FILENAME_PATTERN.matcher(name);
        if (m.matches()) {
            throw new PublicInfoException("文件名不能含有. * ? % _等特殊字符，请重新输入。");
        }
        
        if (checkDuplicationNameFolder(info, root)) {
            throw new PublicInfoException("重复的文件名，请重新输入。");
        }
        
        if (!authenticate(getAuthority(info, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY)) {
            throw new PublicInfoNoAuthorityException("没有权限重命名。");
        }
        
        if (StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_LOCKED, info.getStatus())) {
            throw new PublicInfoNoAuthorityException("文件被锁定不能重命名。");
        }
        
        return info;
    }
    
    /**
     * 鉴权重命名部门文档
     * @param infocode
     * @param root
     * @param name
     * @param user
     * @return
     * @throws PublicInfoException
     */
    @Override
    public OuterNetPublicinfo authenticate4RenameUnitFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException {
        OuterNetPublicinfo info = publicinfoDao.getObjectById(infocode);
        
        if (null == info) {
            throw new PublicInfoNoFileFoundException("文件不存在。");
        }
        
        info.setFilename(name);
        if (StringUtils.isBlank(name)) {
            throw new PublicInfoException("文件名不能为空，请重新输入。");
        }
        
        Matcher m = FILENAME_PATTERN.matcher(name);
        if (m.matches()) {
            throw new PublicInfoException("文件名不能含有. * ? % _等特殊字符，请重新输入。");
        }
        
        if (checkDuplicationNameFolder(info, root)) {
            throw new PublicInfoException("重复的文件名，请重新输入。");
        }
        
        if (!authenticate(getUnitAuthority(info, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY)) {
            throw new PublicInfoNoAuthorityException("没有权限重命名。");
        }
        
        if (StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_LOCKED, info.getStatus())) {
            throw new PublicInfoNoAuthorityException("文件被锁定不能重命名。");
        }
        
        return info;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4RenamePersonalFiles(java.lang.String, java.lang.String, java.lang.String, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo authenticate4RenamePersonalFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException {
        OuterNetPublicinfo info = publicinfoDao.getObjectById(infocode);
        
        if (null == info) {
            throw new PublicInfoNoFileFoundException("文件不存在。");
        }
        
        info.setFilename(name);
        if (StringUtils.isBlank(name)) {
            throw new PublicInfoException("文件名不能为空，请重新输入。");
        }
        
        Matcher m = FILENAME_PATTERN.matcher(name);
        if (m.matches()) {
            throw new PublicInfoException("文件名不能含有. * ? % _等特殊字符，请重新输入。");
        }
        
        info.setFilename(name);
        if (checkDuplicationNameFolder(info, root)) {
            throw new PublicInfoException("重复的文件名，请重新输入。");
        }
        
        if (!authenticate(getPersonalAuthority(info, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY)) {
            throw new PublicInfoNoAuthorityException("没有权限重命名。");
        }
        
        if (StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_LOCKED, info.getStatus())) {
            throw new PublicInfoNoAuthorityException("文件被锁定不能重命名。");
        }
        
        return info;
    }
    
    /**
     * 鉴权删除文件
     * 
     * @param files
     * @param user
     * @return
     */
    @Override
    public List<OuterNetPublicinfo> authenticate4DeleteFiles(String infocodes, FUserDetail user) {
        List<OuterNetPublicinfo> toDeleteFiles = new ArrayList<OuterNetPublicinfo>();
        
        if (StringUtils.isBlank(infocodes)) {
            return toDeleteFiles;
        }
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(Arrays.asList(infocodes.split(",")));
        setFilesAuthority(files, user);
        
        for (OuterNetPublicinfo file : files) {
            // 无权限删除文件
            if (!authenticate(Integer.parseInt(file.getAuthority()), CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY)) {
                continue;
            }
            
            // 状态为锁定的文件不可删除
            if (StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_LOCKED, file.getStatus())) {
                continue;
            }
            
            toDeleteFiles.addAll(queryAllChildPublicinfos(file, file.getFilename()));
        }
        
        return toDeleteFiles;
    }
    
    /**
     * 鉴权删除个人文件
     * 
     * @param files
     * @param user
     * @return
     */
    @Override
    public List<OuterNetPublicinfo> authenticate4DeletePersonalFiles(String infocodes, FUserDetail user) {
        List<OuterNetPublicinfo> toDeleteFiles = new ArrayList<OuterNetPublicinfo>();
        
        if (StringUtils.isBlank(infocodes)) {
            return toDeleteFiles;
        }
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(Arrays.asList(infocodes.split(",")));
        setPersonalFilesAuthority(files, user);
        
        for (OuterNetPublicinfo file : files) {
            // 无权限删除文件
            if (!authenticate(Integer.parseInt(file.getAuthority()), CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY)) {
                continue;
            }
            
            toDeleteFiles.addAll(queryAllChildPublicinfos(file, file.getFilename()));
        }
        
        return toDeleteFiles;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4CopyFiles(java.lang.String, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public List<OuterNetPublicinfo> authenticate4CopyFiles(String infocode, OuterNetPublicinfo copy2File, FUserDetail uinfo) throws PublicInfoException {
        List<OuterNetPublicinfo> toCopyFiles = new ArrayList<OuterNetPublicinfo>();
        
        if (StringUtils.isBlank(infocode)) {
            return toCopyFiles;
        }
        
        if (null == copy2File) {
            throw new PublicInfoNoFileFoundException("复制到的文件夹不存在");
        }
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(Arrays.asList(infocode.split(",")));
        
        // 设置权限
        setFilesAuthority(files, uinfo);
        setFileAuthority(copy2File, uinfo);
        
        // 复制目标不是文件夹
        if (!StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, copy2File.getIsfolder())) {
            throw new PublicInfoNoFileFoundException("复制到的文件夹类型错误");
        }
        
        // 用户无权限复制对象到目标文件夹
        if (!authenticate(Integer.parseInt(copy2File.getAuthority()), CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限复制到【"+ copy2File.getFilename() +"】文件夹");
        }
        
        // 判断复制对象是否是目标文件夹的父节点，如果是父节点则不能复制
        Set<String> parentCodes = queryFolderParentsInfocode(copy2File.getInfocode());
        
        for (OuterNetPublicinfo file : files) {
            if (parentCodes.contains(file.getInfocode())) {
                throw new PublicInfoException("无法复制，目标文件夹是源文件夹的子文件夹");
            }
            
            toCopyFiles.add(file);
        }
        
        return toCopyFiles;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4CopyPersonalFiles(java.lang.String, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public List<OuterNetPublicinfo> authenticate4CopyPersonalFiles(String infocode, OuterNetPublicinfo copy2File, FUserDetail uinfo) throws PublicInfoException {
        List<OuterNetPublicinfo> toCopyFiles = new ArrayList<OuterNetPublicinfo>();
        
        if (StringUtils.isBlank(infocode)) {
            return toCopyFiles;
        }
        
        if (null == copy2File) {
            throw new PublicInfoNoFileFoundException("复制到的文件夹不存在");
        }
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(Arrays.asList(infocode.split(",")));
        
        // 设置权限
        setPersonalFilesAuthority(files, uinfo);
        setPersonalFileAuthority(copy2File, uinfo);
        
        // 复制目标不是文件夹
        if (!StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, copy2File.getIsfolder())) {
            throw new PublicInfoNoFileFoundException("复制到的文件夹类型错误");
        }
        
        // 用户无权限复制对象到目标文件夹
        if (!authenticate(Integer.parseInt(copy2File.getAuthority()), CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限复制到【"+ copy2File.getFilename() +"】文件夹");
        }
        
        // 判断复制对象是否是目标文件夹的父节点，如果是父节点则不能复制
        Set<String> parentCodes = queryFolderParentsInfocode(copy2File.getInfocode());
        
        for (OuterNetPublicinfo file : files) {
            if (parentCodes.contains(file.getInfocode())) {
                throw new PublicInfoException("无法复制，目标文件夹是源文件夹的子文件夹");
            }
            
            toCopyFiles.add(file);
        }
        
        return toCopyFiles;
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4MoveFiles(java.lang.String, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public List<OuterNetPublicinfo> authenticate4MoveFiles(String infocode, OuterNetPublicinfo move2File, FUserDetail uinfo) throws PublicInfoException {
        List<OuterNetPublicinfo> toMoveFiles = new ArrayList<OuterNetPublicinfo>();
        
        if (StringUtils.isBlank(infocode)) {
            return toMoveFiles;
        }
        
        if (null == move2File) {
            throw new PublicInfoNoFileFoundException("复制到的文件夹不存在");
        }
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(Arrays.asList(infocode.split(",")));
        
        // 设置权限
        setFilesAuthority(files, uinfo);
        setFileAuthority(move2File, uinfo);
        
        // 移动目标不是文件夹
        if (!StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, move2File.getIsfolder())) {
            throw new PublicInfoNoFileFoundException("复制到的文件夹类型错误");
        }
        
        // 用户无权限移动对象到目标文件夹
        if (!authenticate(Integer.parseInt(move2File.getAuthority()), CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限移动到目标文件夹");
        }
        
        for (OuterNetPublicinfo temp : files) {
            // 用户无权限移动对象到目标文件夹
            if (!authenticate(Integer.parseInt(temp.getAuthority()), CommonCodeUtil.PUBLICINFO_AUTHORITY_MODIFY)) {
                throw new PublicInfoNoAuthorityException("没有权限移动源文件");
            }
        }
        
        // 判断移动对象是否是目标文件夹的父节点，如果是父节点则不能移动
        Set<String> parentCodes = queryFolderParentsInfocode(move2File.getInfocode());
        
        for (OuterNetPublicinfo file : files) {
            if (parentCodes.contains(file.getInfocode())) {
                throw new PublicInfoException("无法移动，目标文件夹是源文件夹的子目录。");
            };
            
            toMoveFiles.add(file);
        }
        
        return toMoveFiles;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4MovePersonalFiles(java.lang.String, com.centit.app.po.OuterNetPublicinfo, com.centit.sys.security.FUserDetail)
     */
    @Override
    public List<OuterNetPublicinfo> authenticate4MovePersonalFiles(String infocode, OuterNetPublicinfo move2File, FUserDetail uinfo) throws PublicInfoException {
        List<OuterNetPublicinfo> toMoveFiles = new ArrayList<OuterNetPublicinfo>();
        
        if (StringUtils.isBlank(infocode)) {
            return toMoveFiles;
        }
        
        if (null == move2File) {
            throw new PublicInfoNoFileFoundException("复制到的文件夹不存在");
        }
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(Arrays.asList(infocode.split(",")));
        
        // 设置权限
        setPersonalFilesAuthority(files, uinfo);
        setPersonalFileAuthority(move2File, uinfo);
        
        // 移动目标不是文件夹
        if (!StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, move2File.getIsfolder())) {
            throw new PublicInfoNoFileFoundException("复制到的文件夹类型错误");
        }
        
        // 用户无权限移动对象到目标文件夹
        if (!authenticate(Integer.parseInt(move2File.getAuthority()), CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限复制到非本机构文件夹");
        }
        
        // 判断移动对象是否是目标文件夹的父节点，如果是父节点则不能移动
        Set<String> parentCodes = queryFolderParentsInfocode(move2File.getInfocode());
        
        for (OuterNetPublicinfo file : files) {
            if (parentCodes.contains(file.getInfocode())) {
                throw new PublicInfoException("无法移动，目标文件夹是源文件夹的子目录。");
            };
            
            
            toMoveFiles.add(file);
        }
        
        return toMoveFiles;
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4AddFolder(java.lang.String, java.lang.String, com.centit.sys.security.FUserDetail)
     */
    public OuterNetPublicinfo authenticate4AddFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException {
        
        OuterNetPublicinfo file = publicinfoDao.getObjectById(infocode);
        
        if (StringUtils.isBlank(filename)) {
            throw new PublicInfoException("文件夹名称不能为空。");
        }
        
        if (null == file) {
            throw new PublicInfoNoFileFoundException("根目录不存在，无法新建文件夹。");
        }
        
        if (!CommonCodeUtil.PUBLICINFO_T_FOLDER.equals(file.getIsfolder())) {
            throw new PublicInfoNoFileFoundException("根目录类型不正确，无法新建文件夹。");
        }
        
        if (!CommonCodeUtil.PUBLICINFO_T_NORMAL.equals(file.getStatus())) {
            throw new PublicInfoNoFileFoundException("根目录状态不正确，无法新建文件夹。");
        }
        
        int authority = getAuthority(file, user);
        if (!authenticate(authority, CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限在非本机构创建文件件。");
        }
        
        OuterNetPublicinfo newFolder = createFolder(filename, file, user);
        
        renameDuplicationNameFile(newFolder, infocode);
        
        return newFolder;
    }
    
    public OuterNetPublicinfo authenticate4AddUnitFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException {
        
        OuterNetPublicinfo file = publicinfoDao.getObjectById(infocode);
        
        if (StringUtils.isBlank(filename)) {
            throw new PublicInfoException("文件夹名称不能为空。");
        }
        
        if (null == file) {
            throw new PublicInfoNoFileFoundException("根目录不存在，无法新建文件夹。");
        }
        
        if (!CommonCodeUtil.PUBLICINFO_T_FOLDER.equals(file.getIsfolder())) {
            throw new PublicInfoNoFileFoundException("根目录类型不正确，无法新建文件夹。");
        }
        
        if (!CommonCodeUtil.PUBLICINFO_T_NORMAL.equals(file.getStatus())) {
            throw new PublicInfoNoFileFoundException("根目录状态不正确，无法新建文件夹。");
        }
        
        int authority = getUnitAuthority(file, user);
        if (!authenticate(authority, CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限在非本机构创建文件件。");
        }
        
        OuterNetPublicinfo newFolder = createUnitFolder(filename, file, user);
        
        renameDuplicationNameFile(newFolder, infocode);
        
        return newFolder;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4AddPersonalFolder(java.lang.String, java.lang.String, com.centit.sys.security.FUserDetail)
     */
    public OuterNetPublicinfo authenticate4AddPersonalFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException {
        
        OuterNetPublicinfo file = publicinfoDao.getObjectById(infocode);
        
        if (StringUtils.isBlank(filename)) {
            throw new PublicInfoException("文件夹名称不能为空。");
        }
        
        if (null == file) {
            throw new PublicInfoNoFileFoundException("根目录不存在，无法新建文件夹。");
        }
        
        if (!CommonCodeUtil.PUBLICINFO_T_FOLDER.equals(file.getIsfolder())) {
            throw new PublicInfoNoFileFoundException("根目录类型不正确，无法新建文件夹。");
        }
        
        if (!CommonCodeUtil.PUBLICINFO_T_NORMAL.equals(file.getStatus())) {
            throw new PublicInfoNoFileFoundException("根目录状态不正确，无法新建文件夹。");
        }
        
        int authority = getPersonalFileAuthority(file, user);
        if (!authenticate(authority, CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限在他人创建目录文件夹。");
        }
        
        OuterNetPublicinfo newFolder = createPersonalFolder(filename, file, user);
        
        renameDuplicationNameFile(newFolder, infocode);
        
        return newFolder;
    }
	
    /**
     * 鉴权进入公共文件夹目录
     * 
     * @param file 要进入的文件夹
     * @param userUnitcode 用户机构编码
     * @throws PublicInfoException
     */
    @Override
    public OuterNetPublicinfo authenticate4EntryPublicDirectory(OuterNetPublicinfo file, FUserDetail user) throws PublicInfoException {
        
        if (null == user) {
            throw new PublicInfoNoAuthorityException("没有权限。");
        }
        
        // 为空直接抛异常
        if (null == file) {
            throw new PublicInfoNoFileFoundException("文件夹不存在。");
        }
        
        // 不是文件夹，也抛出异常
        if (!CommonCodeUtil.PUBLICINFO_T_FOLDER.equals(file.getIsfolder())) {
            throw new PublicInfoException(file.getFilename() + "[" + file.getInfocode() + "]类型错误。");
        }
        
        // 不是公共文件夹（0、1、9）
        if (!CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PUBLICINFO_T_HOME.equals(file.getFoldertype())) {
            throw new PublicInfoException(file.getFilename() + "[" + file.getInfocode() + "]类型错误。");
        }
        
        // 用户只能查看所属机构的文件夹、文件
        if (!authenticate(getAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_VIEW)) {
            throw new PublicInfoNoAuthorityException("用户无权访问" 
                    + file.getFilename() +"（机构["+ file.getUnitcode() +"]）");
        }
        
        // 文件夹锁定，不允许查看
        if (CommonCodeUtil.PUBLICINFO_T_LOCKED.equals(file.getStatus()) && !user.getUsercode().equals(file.getOwnercode())) {
            throw new PublicInfoNoAuthorityException(file.getFilename() + "已被用户锁定，无法访问。");
        }
        
        return file;
    }
    
    /**
     * 鉴权进入部门文档目录
     */
    @Override
    public OuterNetPublicinfo authenticate4EntryUnitFileDirectory(OuterNetPublicinfo file, FUserDetail user) throws PublicInfoException {
        
        if (null == user) {
            throw new PublicInfoNoAuthorityException("没有权限。");
        }
        
        // 为空直接抛异常
        if (null == file) {
            throw new PublicInfoNoFileFoundException("文件夹不存在。");
        }
        
        // 不是文件夹，也抛出异常
        if (!CommonCodeUtil.PUBLICINFO_T_FOLDER.equals(file.getIsfolder())) {
            throw new PublicInfoException(file.getFilename() + "[" + file.getInfocode() + "]类型错误。");
        }
        
        // 不是部门文件夹
        if (!CommonCodeUtil.PUBLICINFO_T_PUBLIC_CUSTOM.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PUBLICINFO_T_PUBLIC_DEFAULT.equals(file.getFoldertype()) &&
                !CommonCodeUtil.UNITFILE_T_HOME.equals(file.getFoldertype())) {
            throw new PublicInfoException(file.getFilename() + "[" + file.getInfocode() + "]类型错误。");
        }
        
        // 用户只能查看所属机构的文件夹、文件
        if (!authenticate(getUnitAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_VIEW)) {
            throw new PublicInfoNoAuthorityException("用户无权访问" 
                    + file.getFilename() +"（机构["+ file.getUnitcode() +"]）");
        }
        
        // 文件夹锁定，不允许查看
        if (CommonCodeUtil.PUBLICINFO_T_LOCKED.equals(file.getStatus()) && !user.getUsercode().equals(file.getOwnercode())) {
            throw new PublicInfoNoAuthorityException(file.getFilename() + "已被用户锁定，无法访问。");
        }
        
        return file;
    }

    /**
     * 鉴权进入个人文件夹目录
     * 
     * @param file 要进入的文件夹
     * @param userUnitcode 用户机构编码
     * @throws PublicInfoException
     */
    @Override
    public OuterNetPublicinfo authenticate4EntryPersonalDirectory(OuterNetPublicinfo file, FUserDetail user) throws PublicInfoException {
        
        if (null == user) {
            throw new PublicInfoNoAuthorityException("没有权限。");
        }
        
        // 为空直接抛异常
        if (null == file) {
            throw new PublicInfoNoFileFoundException("文件夹不存在。");
        }
        
        // 不是文件夹抛出异常
        if (!CommonCodeUtil.PUBLICINFO_T_FOLDER.equals(file.getIsfolder())) {
            throw new PublicInfoException(file.getFilename() + "[" + file.getInfocode() + "]类型错误。");
        }
        
        // 不是个人文件夹（2、3、8）
        if (!CommonCodeUtil.PUBLICINFO_T_PERSONAL_CUSTOM.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PUBLICINFO_T_PERSONAL_DEFAULT.equals(file.getFoldertype()) &&
                !CommonCodeUtil.PERSONAL_T_HOME.equals(file.getFoldertype())) {
            throw new PublicInfoException(file.getFilename() + "[" + file.getInfocode() + "]类型错误。");
        }
        
        // 用户只能查看所属机构的文件夹、文件
        if (!authenticate(getPersonalAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_VIEW)) {
            throw new PublicInfoNoAuthorityException("用户无权访问 ");
        }
        
        // 文件夹锁定，不允许查看
        if (CommonCodeUtil.PUBLICINFO_T_LOCKED.equals(file.getStatus()) && !user.getUsercode().equals(file.getOwnercode())) {
            throw new PublicInfoNoAuthorityException(file.getFilename() + "已被用户锁定，无法访问。");
        }
        
        return file;
    }
    
    public OuterNetPublicinfo authenticate4EntryUnitDirectory(OuterNetPublicinfo file, FUserDetail user) throws PublicInfoException {
        
        if (null == user) {
            throw new PublicInfoNoAuthorityException("没有权限。");
        }
        
        // 为空直接抛异常
        if (null == file) {
            throw new PublicInfoNoFileFoundException("文件夹不存在。");
        }
        
        // 不是文件夹抛出异常
        if (!CommonCodeUtil.PUBLICINFO_T_FOLDER.equals(file.getIsfolder())) {
            throw new PublicInfoException(file.getFilename() + "[" + file.getInfocode() + "]类型错误。");
        }
        
        // 不是部门文件夹
        if (!CommonCodeUtil.UNITFILE_T_HOME.equals(file.getFoldertype())) {
            throw new PublicInfoException(file.getFilename() + "[" + file.getInfocode() + "]类型错误。");
        }
        
        // 用户只能查看所属机构的文件夹、文件
        if (!authenticate(getPersonalAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_VIEW)) {
            throw new PublicInfoNoAuthorityException("用户无权访问 ");
        }
        
        // 文件夹锁定，不允许查看
        if (CommonCodeUtil.PUBLICINFO_T_LOCKED.equals(file.getStatus()) && !user.getUsercode().equals(file.getOwnercode())) {
            throw new PublicInfoNoAuthorityException(file.getFilename() + "已被用户锁定，无法访问。");
        }
        
        return file;
    }
    
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4UploadFile(java.lang.String, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo authenticate4UploadFile(String path, FUserDetail user) throws PublicInfoException {
        
        OuterNetPublicinfo file = publicinfoDao.getObjectById(path);
        if (null == file) {
            throw new PublicInfoNoFileFoundException("目录不存在无法上传。");
        }
        
        if (!StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, file.getIsfolder())) {
            throw new PublicInfoException("目录类型不正确无法上传。");
        }
        
        if (!authenticate(getAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限无法上传。");
        }
        
        return file;
    }
    
    @Override
    public OuterNetPublicinfo authenticate4UploadUnitFile(String path, FUserDetail user) throws PublicInfoException {
        
        OuterNetPublicinfo file = publicinfoDao.getObjectById(path);
        if (null == file) {
            throw new PublicInfoNoFileFoundException("目录不存在无法上传。");
        }
        
        if (!StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, file.getIsfolder())) {
            throw new PublicInfoException("目录类型不正确无法上传。");
        }
        
        if (!authenticate(getUnitAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限无法上传。");
        }
        
        return file;
    }
    /* (non-Javadoc)
     * @see com.centit.app.service.PublicinfoManager#authenticate4UploadPersonalFile(java.lang.String, com.centit.sys.security.FUserDetail)
     */
    @Override
    public OuterNetPublicinfo authenticate4UploadPersonalFile(String path, FUserDetail user) throws PublicInfoException {
        
        OuterNetPublicinfo file = publicinfoDao.getObjectById(path);
        if (null == file) {
            throw new PublicInfoNoFileFoundException("目录不存在无法上传。");
        }
        
        if (!StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, file.getIsfolder())) {
            throw new PublicInfoException("目录类型不正确无法上传。");
        }
        
        if (!authenticate(getPersonalAuthority(file, user), CommonCodeUtil.PUBLICINFO_AUTHORITY_ADD)) {
            throw new PublicInfoNoAuthorityException("没有权限无法上传。");
        }
        
        return file;
    }
    
    /**
     * 查询用户所属机构编码
     * 
     * @param usercode
     * @return
     */
    private Set<String> queryUserUnits(String usercode) {
        List<String> units = publicinfoDao.listUserUnits(usercode);
        
        Set<String> unitSet = new HashSet<String>();
        unitSet.addAll(units);
        
        return unitSet;
    }
    
    /**
     * 判断是否有重名文件夹
     * 
     * @param name
     * @param infocode
     * 
     * @return 
     *  true有重名 
     *  false无重名
     */
    private boolean checkDuplicationNameFolder(OuterNetPublicinfo file, String infocode) {
        String name = file.getFilename();
        String ext = file.getFileextension();
        
        if (StringUtils.equals(CommonCodeUtil.PUBLICINFO_T_FOLDER, file.getIsfolder())) {
            return null != publicinfoDao.getPublicFolderByName(file.getFilename(), infocode);
        }
        
        if (StringUtils.isBlank(ext)) {
            return null != publicinfoDao.getPublicFileByName(name, infocode);
        }
        
        return null != publicinfoDao.getPublicFileByName(name, ext, infocode);
    }
    
    /**
     * 判断文件信息是否是最后的引用
     * 
     * 因为可能会有多个PUBLICINFO对象引用同一个FILEINFO，所以在删除的时候会对此判断
     * 若还有多个对象引用FILEINFO，则不删除此FILEINFO
     * 
     * @param file
     * @return
     */
    private boolean isLastQuoteFileinfo(OuterNetFileinfoFs file) {
        int count = publicinfoDao.countFilecodeUsed(file.getFilecode());
        
        return count == 1;
    }

    
    /**
     * 判断是否有重名，并重命名
     * 
     * @param file
     * @param infocode
     * @return
     */
    private String renameDuplicationNameFile(OuterNetPublicinfo file, String infocode) {
        String name = file.getFilename();
        int index = 1;
        
        while(checkDuplicationNameFolder(file, infocode)) {
            file.setFilename(name + "(" + (index++) + ")");
        }
        
        return file.getFilename();
    }
    
    private static String getFullFilePath(String path) {
        return SysParametersUtils.getUploadHome() + path;
    }
    
    /**
     * 鉴权删除文件
     * 
     * @param files
     * @param user
     * @return
     */
    @Override
    public List<OuterNetPublicinfo> listFiles(List<String> infocodes) {
        
        List<OuterNetPublicinfo> files = publicinfoDao.listPublicinfos(infocodes);
        
        return files;
    }
    
    public List<Object> listFiles(String infocode, FUserDetail user){
        
        Map<String,Object> filterMap = new HashMap<String,Object>();
        //filterMap.put("parentinfocode", infocode);
        filterMap.put(CodeBook.SELF_ORDER_BY,"isFolder desc");
        List<OuterNetPublicinfo>   files = publicinfoDao.listObjects(filterMap);
        files.add(publicinfoDao.getObjectById(infocode));
        
        List<Object> fileList = new ArrayList<Object>();
        
        for(OuterNetPublicinfo file : files){
            
            Map<String,Object> fileMap = new HashMap<String, Object>();
            
            fileMap.put("id", file.getInfocode());
            fileMap.put("pId", file.getParentinfocode());
            fileMap.put("name", file.getFilename());
            fileMap.put("isFolder", file.getIsfolder());
            fileMap.put("infocode", file.getInfocode());
            
            if("doc".equals(file.getFileextension()) || "docx".equals(file.getFileextension())){
                fileMap.put("type", "word");
            }else if("xls".equals(file.getFileextension()) || "xlsx".equals(file.getFileextension())){
                fileMap.put("type", "excel");
            }else if("ppt".equals(file.getFileextension()) || "pptx".equals(file.getFileextension())){
                fileMap.put("type", "ppt");
            }else
                fileMap.put("type", file.getFileextension());
            fileList.add(fileMap);
        }       
                
        return fileList;
    }
    
    //移动端
    /**
     * 移动端查询个人文档
     * @param infocode
     * @param currentdatetime
     * @param pagesize
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public List<OuterNetPublicinfo> queryPersonalFiles2(Map<String, Object> filterMap,PageDesc pageDesc,FUserDetail user) throws PublicInfoException {
        
        List<OuterNetPublicinfo> files  = publicinfoDao.listObjects(filterMap,pageDesc);
        
        setPersonalFilesAuthority(files, user);
        
        return files;
    }
    /**
     * 移动端查询公共文档列出部门文档
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    @Override
    public List<OuterNetPublicinfo> queryUnitFiles2(Map<String, Object> filterMap,PageDesc pageDesc,FUserDetail user) throws PublicInfoException {
        OuterNetPublicinfo file = publicinfoDao.getObjectById((String)filterMap.get("parentinfocode"));
        
        List<OuterNetPublicinfo> files =null;
        
//        // 查找目录下所有文件、文件夹或者具有查看所有文件权限的用户
        if (!CommonCodeUtil.UNITFILE_T_HOME.equals(file.getFoldertype()) || 
                CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "listall")||checkUserOptPower(CommonCodeUtil.UNITFILEINFO_OPTCODE, "listall",user)) {
            
            files = publicinfoDao.listObjects(filterMap, pageDesc);
        }
        // 首页只查找用户可见文件
        else {
            files = publicinfoDao.listRootPublicinfos(file.getInfocode(), user.getUsercode());
        }
        
        setUnitFilesAuthority(files, user);
        
        return files;
    }


    /**
     * 移动端查询公共文档
     * @param infocode
     * @param currentdatetime
     * @param pagesize
     * @param user
     * @return
     */
    public List<OuterNetPublicinfo> queryPublicFiles2(Map<String, Object> filterMap,PageDesc pageDesc,FUserDetail user) {
        
        
       OuterNetPublicinfo file = publicinfoDao.getObjectById((String)filterMap.get("parentinfocode"));;
        
        List<OuterNetPublicinfo> files = null;
        
        // 查找目录下所有文件、文件夹或者具有查看所有文件权限的用户
        if (!CommonCodeUtil.PUBLICINFO_T_HOME.equals(file.getFoldertype()) || 
                CodeRepositoryUtil.checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "listall")||checkUserOptPower(CommonCodeUtil.PUBLICINFO_OPTCODE, "listall",user)) {
            files = publicinfoDao.listObjects(filterMap, pageDesc);
            
        }
        // 首页只查找用户可见文件
        else {
            files = publicinfoDao.listRootPublicinfos(file.getInfocode(), user.getUsercode());
        }
        
        setFilesAuthority(files, user);
        
        return files;
    }

    
    /**
     * 验证当前用户是否有某个操作方法的权限[直接数据库访问]
     * @param optId
     * @param optMethod
     * @return
     */
    private static  Boolean checkUserOptPower(String optId,String optMethod,FUserDetail userdetail)
    {
        
       Map<String,String> userOptList = userdetail.getUserOptList();
       if(userOptList==null)
           return false;
       String s = userOptList.get(optId+"-"+optMethod);
       if(s==null)
           return false;
       return "T".equals(s);
    }
   
}

