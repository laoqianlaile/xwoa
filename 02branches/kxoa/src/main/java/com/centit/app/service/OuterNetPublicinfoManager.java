package com.centit.app.service;

import com.centit.app.exception.PublicInfoException;
import com.centit.app.exception.PublicInfoNoFileFoundException;
import com.centit.app.po.OuterNetFileinfoFs;
import com.centit.app.po.OuterNetPublicinfo;
import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.sys.security.FUserDetail;

import java.util.List;
import java.util.Map;

public interface OuterNetPublicinfoManager extends BaseEntityManager<OuterNetPublicinfo> {

    /**
     * 列出某个用户所有的文件
     * @param ownercode
     * @param infocode
     * @return
     */
    public List<OuterNetPublicinfo> listAllSubPublicinfos(String ownercode, String infocode);
    
    /**
     * 获取用户公共文件夹根目录
     * 
     * @param unitcode
     * @return
     */
    OuterNetPublicinfo getPublicRootDirectory(String unitcode) throws PublicInfoNoFileFoundException;
    
    /**
     * 根据ID列取文件对象
     * @param infocodes
     * @return
     */
    List<OuterNetPublicinfo> listFiles(List<String> infocodes);
    
    /**
     * 获取用户公共文件夹首页
     * 
     * @return
     */
    OuterNetPublicinfo getPublicRootDirectory();
    
    /**
     * 设置部门文档首页
     * @param user
     * @return
     */
    public OuterNetPublicinfo getUnitRootDirectory(FUserDetail user);
    
    /**
     * 获取用户个人文件夹首页
     * 
     * @return
     */
    OuterNetPublicinfo getPersonalRootDirectory(FUserDetail user);
    
    /**
     * 获取公共文件所有子文件
     * 
     * @param code
     * @return
     */
    public List<OuterNetPublicinfo> queryAllPublicFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException;
    
    /**
     * 获取所有部门文档子文件
     * @param codes
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public List<OuterNetPublicinfo> queryAllUnitPublicFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException; 
    
    /**
     * 获取个人文件所有子文件
     * 
     * @param code
     * @return
     */
    List<OuterNetPublicinfo> queryAllPersonalFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException;
    
    /**
     * 获取公共文件夹下的所有文件
     * 
     * @param infocode
     * @param user
     * @return
     */
    List<OuterNetPublicinfo> queryPublicFiles(String infocode, FUserDetail user) throws PublicInfoException;
    
    /**
     * 列出部门文档
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public List<OuterNetPublicinfo> queryUnitFiles(String infocode, FUserDetail user) throws PublicInfoException;
    
    /**
     * 获取个人文件夹下的所有文件
     * 
     * @param infocode
     * @param user
     * @return
     */
    List<OuterNetPublicinfo> queryPersonalFiles(String infocode, FUserDetail user) throws PublicInfoException;
    
    /**
     * 判断列出的每个文件是否自己上传的，如果是，将“isOneself”设置为Y，否则为N
     * @param infos
     * @param user
     * @return
     */
    public String isOneself(List<OuterNetPublicinfo> infos, String usercode);
    
    /**
     * 列出目录下的文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    List<OuterNetPublicinfo> queryPublicFolders(String infocode, FUserDetail user, boolean includeSelf) throws PublicInfoException;
    
    /**
     * 列出目录下的个人文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    List<OuterNetPublicinfo> queryPersonalFolders(String infocode, FUserDetail user, boolean includeSelf) throws PublicInfoException;
    
    /**
     * 列出文件路径
     * 
     * @param infocode
     * @return
     */
    List<Map<String,String>> queryFolderParents(String infocode);
    
    /**
     * 保存文件
     * 
     * @param file
     * @param root
     * @return
     */
    OuterNetPublicinfo saveFile(OuterNetFileinfoFs file, OuterNetPublicinfo root, FUserDetail user);
    
    /**
     * 保存部门文档
     * @param file
     * @param root
     * @param user
     * @return
     */
    public OuterNetPublicinfo saveUnitFile(OuterNetFileinfoFs file, OuterNetPublicinfo root, FUserDetail user);
    
    /**
     * 保存个人文件
     * 
     * @param file
     * @param root
     * @return
     */
    OuterNetPublicinfo savePersonalFile(OuterNetFileinfoFs file, OuterNetPublicinfo root, FUserDetail user);
    
    /**
     * 保存复制文件
     * 
     * @param copyFile
     * @param copyTo
     * @param user
     * @return
     */
    OuterNetPublicinfo saveCopyFile(OuterNetPublicinfo copyFile, OuterNetPublicinfo copyTo, FUserDetail user);
    
    /**
     * 保存复制个人文件
     * 
     * @param copyFile
     * @param copyTo
     * @param user
     * @return
     */
    OuterNetPublicinfo saveCopyPersonalFile(OuterNetPublicinfo copyFile, OuterNetPublicinfo copyTo, FUserDetail user);
    
    /**
     * 修改移动文件
     * 
     * @param file
     * @param move2File
     * @param uinfo
     */
    OuterNetPublicinfo saveMoveFile(OuterNetPublicinfo file, OuterNetPublicinfo move2File, FUserDetail uinfo);
    
    /**
     * 修改移动个人文件
     * 
     * @param file
     * @param move2File
     * @param uinfo
     */
    OuterNetPublicinfo saveMovePersonalFile(OuterNetPublicinfo file, OuterNetPublicinfo move2File, FUserDetail uinfo);
    
    /**
     * 删除文件
     * 
     * @param info
     * @throws PublicInfoException
     */
    void deleteFile(OuterNetPublicinfo info) throws PublicInfoException;
    
    /**
     * 重命名文件
     * 
     * @param infocode
     * @param name
     * @param user
     * @return
     * @throws PublicInfoException
     */
    OuterNetPublicinfo updateFilename(OuterNetPublicinfo file, String name) throws PublicInfoException;
    
    /**
     * 获取单个文件权限
     * 
     * @param file
     * @param user
     * @return
     */
    int getAuthority(OuterNetPublicinfo file, FUserDetail user);
    
    /**
     * 获取部门用户权限
     * @param file
     * @param user
     * @return
     */
    int getUnitAuthority(OuterNetPublicinfo file, FUserDetail user);
    
    /**
     * 获取单个个人文件权限
     * 
     * @param file
     * @param user
     * @return
     */
    int getPersonalAuthority(OuterNetPublicinfo file, FUserDetail user);
    
    /**
     * 鉴权上传文件
     * 
     * @param path
     * @param user
     * @throws PublicInfoException
     */
    OuterNetPublicinfo authenticate4UploadFile(String path, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权上次部门文档
     * @param path
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public OuterNetPublicinfo authenticate4UploadUnitFile(String path, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权上传个人文件
     * 
     * @param path
     * @param user
     * @throws PublicInfoException
     */
    OuterNetPublicinfo authenticate4UploadPersonalFile(String path, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权进入文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    OuterNetPublicinfo authenticate4EntryPublicDirectory(OuterNetPublicinfo info, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权进入部门文档
     * @param file
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public OuterNetPublicinfo authenticate4EntryUnitFileDirectory(OuterNetPublicinfo file, FUserDetail user) throws PublicInfoException ;
    
    /**
     * 鉴权进入个人文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    OuterNetPublicinfo authenticate4EntryPersonalDirectory(OuterNetPublicinfo info, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权创建文件夹
     * 
     * @param filename
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    OuterNetPublicinfo authenticate4AddFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权创建部门文件夹
     * @param filename
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public OuterNetPublicinfo authenticate4AddUnitFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException ;
    
    /**
     * 鉴权创建个人文件夹
     * 
     * @param filename
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    OuterNetPublicinfo authenticate4AddPersonalFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException;
    
    
    /**
     * 鉴权删除文件
     * 
     * @param infocodes
     * @param user
     * @return
     */
    List<OuterNetPublicinfo> authenticate4DeleteFiles(String infocodes, FUserDetail user);
    
    /**
     * 鉴权删除个人文件
     * 
     * @param infocodes
     * @param user
     * @return
     */
    List<OuterNetPublicinfo> authenticate4DeletePersonalFiles(String infocodes, FUserDetail user);
    
    /**
     * 鉴权重命名文件
     * 
     * @param infocode
     * @param root
     * @param name
     * @param user
     * @return
     * @throws PublicInfoException
     */
    OuterNetPublicinfo authenticate4RenameFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权重命名部门文档
     * @param infocode
     * @param root
     * @param name
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public OuterNetPublicinfo authenticate4RenameUnitFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException;
    /**
     * 鉴权重命名个人文件
     * 
     * @param infocode
     * @param root
     * @param name
     * @param user
     * @return
     * @throws PublicInfoException
     */
    OuterNetPublicinfo authenticate4RenamePersonalFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException;

    /**
     * 鉴权复制文件
     * 
     * @param infocode
     * @param copy2File
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    List<OuterNetPublicinfo> authenticate4CopyFiles(String infocode, OuterNetPublicinfo copy2File, FUserDetail uinfo) throws PublicInfoException;
    
    /**
     * 鉴权复制个人文件
     * 
     * @param infocode
     * @param copy2File
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    List<OuterNetPublicinfo> authenticate4CopyPersonalFiles(String infocode, OuterNetPublicinfo copy2File, FUserDetail uinfo) throws PublicInfoException;

    /**
     * 鉴权移动文件
     * 
     * @param infocode
     * @param move2File
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    List<OuterNetPublicinfo> authenticate4MoveFiles(String infocode, OuterNetPublicinfo move2File, FUserDetail uinfo) throws PublicInfoException;
    
    /**
     * 鉴权移动个人文件
     * 
     * @param infocode
     * @param move2File
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    List<OuterNetPublicinfo> authenticate4MovePersonalFiles(String infocode, OuterNetPublicinfo move2File, FUserDetail uinfo) throws PublicInfoException;
    
    public List<Object> listFiles(String infocode, FUserDetail user);
    /**
     * 移动端查询个人文档
     * @param infocode
     * @param currentdatetime
     * @param pagesize
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public List<OuterNetPublicinfo> queryPersonalFiles2(
            Map<String, Object> filterMap, PageDesc pageDesc,FUserDetail user)
            throws PublicInfoException;
    /**
     * 移动端查询列出部门文档
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public List<OuterNetPublicinfo> queryUnitFiles2(Map<String, Object> filterMap, PageDesc pageDesc,FUserDetail user) throws PublicInfoException;
    /**
     * 移动端查询公共文档
     * @param infocode
     * @param currentdatetime
     * @param pagesize
     * @param user
     * @return
     */
    public List<OuterNetPublicinfo> queryPublicFiles2(
            Map<String, Object> filterMap,PageDesc pageDesc, FUserDetail user);

}
