package com.centit.powerruntime.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.powerruntime.po.OptStuffInfo;

public interface OptStuffInfoManager extends BaseEntityManager<OptStuffInfo> {

    /**
     * 根据业务编号和排序号获取材料信息
     * 
     * @param djId
     * @param sortId
     * @return
     */
    public OptStuffInfo getObjectById_SortId(String djId, String sortId);

    public OptStuffInfo getStuffInfoByArchiveType(String djId,
            String archiveType);

    /**
     * 正文材料文件查询
     * 
     * @param djId
     * @return
     */
    public List<OptStuffInfo> getZwfjStuffInfo(String djId);

    /**
     * 根据业务编号获取材料信息
     * 
     * @param djId
     * @return
     */
    public List<OptStuffInfo> getStuffInfoList(String djId);

    /**
     * 根据业务编号获取材料信息-某个阶段产生的 的附件材料
     * 
     * @param djId
     * @return
     */
    public List<OptStuffInfo> getStuffInfoList(String djId, long nodeinstid);

    // 附件管理删除文件
    public void deleteObjectBanInfo(String djId);

    /*
     * 根据文件编号获取文件名
     */
    public String getStuffNameById(String stuffId);
    
    /**
     * 下载最新签批附件
     * @param layerUrl 签批附件url
     * @param pdfStoreDir 签批附件存储目录
     * @return
     */
    public List<String> downloadFiles(String layerUrl,String pdfStoreDir);
    
    /**
     * 给签批附件指定一个磁盘路径
     * @param djId
     * @param nodeInstd
     * @return
     */
    public String assignFilesStorePathOnDisk(String djId,Long nodeInstd);

    /**
     * 给正文doc变为pdf指定一个磁盘路径
     * @param djId
     * @return
     */
    public OptStuffInfo ZwStuffs(String djid);
    
    public OptStuffInfo ZwPDFStuffs(String djid);

}
