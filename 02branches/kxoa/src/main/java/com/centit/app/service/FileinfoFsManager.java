package com.centit.app.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.centit.app.po.FileinfoFs;
import com.centit.core.service.BaseEntityManager;
import com.centit.support.searcher.DocDesc;

public interface FileinfoFsManager extends BaseEntityManager<FileinfoFs> 
{
    public String getNextKey();
    
    /**
     * 上传保存文件
     * 
     * @param file
     * @param is
     * @param optID
     * @return
     * @throws IOException
     */
    public FileinfoFs saveFileinfo(FileinfoFs file, InputStream is, String optID) throws IOException;
    
    /**
     * 保存文件对象，与上传保存文件不同的是，这个方法不会再复制文件，直接根据原文件信息保存对象
     * 
     * @param info 传入时只要有业务逻辑字段如：上传者、OPTCODE等，其他会根据FILE信息自动生成
     * @param file
     * @return
     */
    public FileinfoFs saveFileinfo(FileinfoFs info, File file);
    
    /**
     * 二次确认上传文件
     * 
     * @param info
     * @return
     */
    public FileinfoFs update4ConfirmFileinfo(FileinfoFs info, DocDesc docDesc);
    
    /**
     * 二次确认上传文件
     * @param infos
     */
    public void update4ConfirmFileinfos(List<FileinfoFs> infos, List<DocDesc> docDescs);
    
    /**
     * 删除文件
     * 
     * 若文件对象已与其他业务对象做级联删除，强烈不建议使用这2个方法单独删除。
     * 而是应该直接删除父对象时级联删除文件对象，再调用接口直接删除磁盘上的文件。 
     * 
     * @param file 待删除文件对象
     */
    @Deprecated
    public boolean deleteFileinfo(FileinfoFs file);
    
    /**
     * 直接删除磁盘上文件
     * 
     * @param file 待删除文件对象
     * @return
     */
    public boolean deleteFileOnDisk(FileinfoFs file);
    
    /**
     * 直接删除磁盘上文件
     * 
     * @param path 文件路径
     * @return
     */
    public boolean deleteFileOnDisk(String path);
    
    /**
     * 下载文件
     * 
     * @param file
     * @return
     */
    public InputStream downloadFileinfo(FileinfoFs file) throws IOException;

    public String getFullFilePath(String path);
    
}
