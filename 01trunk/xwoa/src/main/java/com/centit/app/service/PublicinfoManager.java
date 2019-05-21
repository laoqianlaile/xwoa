package com.centit.app.service;

import com.centit.app.exception.PublicInfoException;
import com.centit.app.exception.PublicInfoNoFileFoundException;
import com.centit.app.po.FileinfoFs;
import com.centit.app.po.Publicinfo;
import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.sys.security.FUserDetail;

import java.util.List;
import java.util.Map;

public interface PublicinfoManager extends BaseEntityManager<Publicinfo> {

    /**
     * 列出某个用户所有的文件
     * @param ownercode
     * @param infocode
     * @return
     */
    public List<Publicinfo> listAllSubPublicinfos(String ownercode, String infocode);
    
    /**
     * 获取用户公共文件夹根目录
     * 
     * @param unitcode
     * @return
     */
    Publicinfo getPublicRootDirectory(String unitcode) throws PublicInfoNoFileFoundException;
    
    /**
     * 根据ID列取文件对象
     * @param infocodes
     * @return
     */
    List<Publicinfo> listFiles(List<String> infocodes);
    
    /**
     * 获取用户公共文件夹首页
     * 
     * @return
     */
    Publicinfo getPublicRootDirectory();
    
    /**
     * 设置部门文档首页
     * @param user
     * @return
     */
    public Publicinfo getUnitRootDirectory(FUserDetail user);
    
    /**
     * 获取用户个人文件夹首页
     * 
     * @return
     */
    Publicinfo getPersonalRootDirectory(FUserDetail user);
    
    /**
     * 获取公共文件所有子文件
     * 
     * @param code
     * @return
     */
    public List<Publicinfo> queryAllPublicFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException;
    
    /**
     * 获取所有部门文档子文件
     * @param codes
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public List<Publicinfo> queryAllUnitPublicFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException; 
    
    /**
     * 获取个人文件所有子文件
     * 
     * @param code
     * @return
     */
    List<Publicinfo> queryAllPersonalFileChilds(List<String> codes, FUserDetail user) throws PublicInfoException;
    
    /**
     * 获取公共文件夹下的所有文件
     * 
     * @param infocode
     * @param user
     * @return
     */
    List<Publicinfo> queryPublicFiles(String infocode, FUserDetail user) throws PublicInfoException;
    
    /**
     * 列出部门文档
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public List<Publicinfo> queryUnitFiles(String infocode, FUserDetail user) throws PublicInfoException;
    
    /**
     * 获取个人文件夹下的所有文件
     * 
     * @param infocode
     * @param user
     * @return
     */
    List<Publicinfo> queryPersonalFiles(String infocode, FUserDetail user) throws PublicInfoException;
    
    /**
     * 判断列出的每个文件是否自己上传的，如果是，将“isOneself”设置为Y，否则为N
     * @param infos
     * @param user
     * @return
     */
    public String isOneself(List<Publicinfo> infos, String usercode);
    
    /**
     * 列出目录下的文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    List<Publicinfo> queryPublicFolders(String infocode, FUserDetail user, boolean includeSelf) throws PublicInfoException;
    
    /**
     * 列出目录下的个人文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    List<Publicinfo> queryPersonalFolders(String infocode, FUserDetail user, boolean includeSelf) throws PublicInfoException;
    
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
    Publicinfo saveFile(FileinfoFs file, Publicinfo root, FUserDetail user);
    
    /**
     * 保存部门文档
     * @param file
     * @param root
     * @param user
     * @return
     */
    public Publicinfo saveUnitFile(FileinfoFs file, Publicinfo root, FUserDetail user);
    
    /**
     * 保存个人文件
     * 
     * @param file
     * @param root
     * @return
     */
    Publicinfo savePersonalFile(FileinfoFs file, Publicinfo root, FUserDetail user);
    
    /**
     * 保存复制文件
     * 
     * @param copyFile
     * @param copyTo
     * @param user
     * @return
     */
    Publicinfo saveCopyFile(Publicinfo copyFile, Publicinfo copyTo, FUserDetail user);
    
    /**
     * 保存复制个人文件
     * 
     * @param copyFile
     * @param copyTo
     * @param user
     * @return
     */
    Publicinfo saveCopyPersonalFile(Publicinfo copyFile, Publicinfo copyTo, FUserDetail user);
    
    /**
     * 修改移动文件
     * 
     * @param file
     * @param move2File
     * @param uinfo
     */
    Publicinfo saveMoveFile(Publicinfo file, Publicinfo move2File, FUserDetail uinfo);
    
    /**
     * 修改移动个人文件
     * 
     * @param file
     * @param move2File
     * @param uinfo
     */
    Publicinfo saveMovePersonalFile(Publicinfo file, Publicinfo move2File, FUserDetail uinfo);
    
    /**
     * 删除文件
     * 
     * @param info
     * @throws PublicInfoException
     */
    void deleteFile(Publicinfo info) throws PublicInfoException;
    
    /**
     * 重命名文件
     * 
     * @param infocode
     * @param name
     * @param user
     * @return
     * @throws PublicInfoException
     */
    Publicinfo updateFilename(Publicinfo file, String name) throws PublicInfoException;
    
    /**
     * 获取单个文件权限
     * 
     * @param file
     * @param user
     * @return
     */
    int getAuthority(Publicinfo file, FUserDetail user);
    
    /**
     * 获取部门用户权限
     * @param file
     * @param user
     * @return
     */
    int getUnitAuthority(Publicinfo file, FUserDetail user);
    
    /**
     * 获取单个个人文件权限
     * 
     * @param file
     * @param user
     * @return
     */
    int getPersonalAuthority(Publicinfo file, FUserDetail user);
    
    /**
     * 鉴权上传文件
     * 
     * @param path
     * @param user
     * @throws PublicInfoException
     */
    Publicinfo authenticate4UploadFile(String path, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权上次部门文档
     * @param path
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public Publicinfo authenticate4UploadUnitFile(String path, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权上传个人文件
     * 
     * @param path
     * @param user
     * @throws PublicInfoException
     */
    Publicinfo authenticate4UploadPersonalFile(String path, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权进入文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    Publicinfo authenticate4EntryPublicDirectory(Publicinfo info, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权进入部门文档
     * @param file
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public Publicinfo authenticate4EntryUnitFileDirectory(Publicinfo file, FUserDetail user) throws PublicInfoException ;
    
    /**
     * 鉴权进入个人文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    Publicinfo authenticate4EntryPersonalDirectory(Publicinfo info, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权创建文件夹
     * 
     * @param filename
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    Publicinfo authenticate4AddFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权创建部门文件夹
     * @param filename
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public Publicinfo authenticate4AddUnitFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException ;
    
    /**
     * 鉴权创建个人文件夹
     * 
     * @param filename
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    Publicinfo authenticate4AddPersonalFolder(String filename, String infocode, FUserDetail user) throws PublicInfoException;
    
    
    /**
     * 鉴权删除文件
     * 
     * @param infocodes
     * @param user
     * @return
     */
    List<Publicinfo> authenticate4DeleteFiles(String infocodes, FUserDetail user);
    
    /**
     * 鉴权删除部门文件
     * 
     * @param infocodes
     * @param user
     * @return
     */
    List<Publicinfo> authenticate4DeleteGRBGBMWDFiles(String infocodes, FUserDetail user);
    
    /**
     * 鉴权删除个人文件
     * 
     * @param infocodes
     * @param user
     * @return
     */
    List<Publicinfo> authenticate4DeletePersonalFiles(String infocodes, FUserDetail user);
    
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
    Publicinfo authenticate4RenameFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException;
    
    /**
     * 鉴权重命名部门文档
     * @param infocode
     * @param root
     * @param name
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public Publicinfo authenticate4RenameUnitFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException;
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
    Publicinfo authenticate4RenamePersonalFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException;

    /**
     * 鉴权复制文件
     * 
     * @param infocode
     * @param copy2File
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    List<Publicinfo> authenticate4CopyFiles(String infocode, Publicinfo copy2File, FUserDetail uinfo) throws PublicInfoException;
    
    /**
     * 鉴权复制个人文件
     * 
     * @param infocode
     * @param copy2File
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    List<Publicinfo> authenticate4CopyPersonalFiles(String infocode, Publicinfo copy2File, FUserDetail uinfo) throws PublicInfoException;

    /**
     * 鉴权移动文件
     * 
     * @param infocode
     * @param move2File
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    List<Publicinfo> authenticate4MoveFiles(String infocode, Publicinfo move2File, FUserDetail uinfo) throws PublicInfoException;
    
    /**
     * 鉴权移动个人文件
     * 
     * @param infocode
     * @param move2File
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    List<Publicinfo> authenticate4MovePersonalFiles(String infocode, Publicinfo move2File, FUserDetail uinfo) throws PublicInfoException;
    
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
    public List<Publicinfo> queryPersonalFiles2(
            Map<String, Object> filterMap, PageDesc pageDesc,FUserDetail user)
            throws PublicInfoException;
    
    /**
     * 移动端查询列出部门文档
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    public List<Publicinfo> queryUnitFiles2(Map<String, Object> filterMap, PageDesc pageDesc,FUserDetail user) throws PublicInfoException;
    
    
    /**
     * 移动端查询公共文档
     * @param infocode
     * @param currentdatetime
     * @param pagesize
     * @param user
     * @return
     */
    public List<Publicinfo> queryPublicFiles2(
            Map<String, Object> filterMap,PageDesc pageDesc, FUserDetail user);

    
    
    /**
     * 获取部门共享文档
     * @param unitcode
     * @param user 
     * @return
     */
    public Publicinfo getUnitShareRootDirectory(String unitcode, FUserDetail user);
    
    /**
     * 获取部门共享的所有文件
     * 
     * @param infocode
     * @param user
     * @param rootunitcode 
     * @return
     */
    List<Publicinfo> queryUnitShareFiles(String infocode, FUserDetail user, String rootunitcode)
            throws PublicInfoException;
    
    /**
     * 获取单个部门共享文件权限
     * 
     * @param file
     * @param user
     * @param rootunitcode 所属部门
     * @return
     */
    int getUnitShareAuthority(Publicinfo file, FUserDetail user);
    
    /**
     * 保存部门共享文档
     * @param file
     * @param root
     * @param user
     * @param  rootunitcode
     * @return
     */
    public Publicinfo saveUnitShareFile(FileinfoFs file, Publicinfo root, FUserDetail user,String  rootunitcode);
   
    /**
     * 鉴权删除部门共享文件
     * 
     * @param infocodes
     * @param user
     * @return
     */
    List<Publicinfo> authenticate4DeleteUnitShareFiles(String infocodes, FUserDetail user);

    /**
     * 鉴权重命名部门共享文件
     * 
     * @param infocode
     * @param root
     * @param name
     * @param user
     * @return
     * @throws PublicInfoException
     */
    Publicinfo authenticate4RenameUnitShareFiles(String infocode, String root, String name, FUserDetail user) throws PublicInfoException;
    /**
     * 鉴权创建部门共享文件夹
     * 
     * @param filename
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    Publicinfo authenticate4AddUnitShareFolder(String filename, String infocode, FUserDetail user,String  rootunitcode) throws PublicInfoException;

    /**
     * 鉴权上传部门共享文档
     * @param path
     * @param unitcode
     * @param uinfo
     * @return
     * @throws PublicInfoException
     */
    public Publicinfo authenticate4UploadUnitShareFile(String path, String unitcode, FUserDetail uinfo)throws PublicInfoException;

    /**
     * 鉴权进入文件夹
     * 
     * @param infocode
     * @param user
     * @return
     * @throws PublicInfoException
     */
    Publicinfo authenticate4EntryUnitShareDirectory(Publicinfo info, FUserDetail user) throws PublicInfoException;

    List<Publicinfo> queryAllUnitShareFileChilds(List<String> codes,FUserDetail user) throws PublicInfoException;

    List<Publicinfo> queryUnitShareFiles2(Map<String, Object> filterMap,PageDesc pageDesc, FUserDetail user) throws PublicInfoException;
}

