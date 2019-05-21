package com.centit.powerruntime.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerruntime.dao.OptStuffInfoDao;
import com.centit.powerruntime.po.OptStuffInfo;
import com.centit.powerruntime.service.OptStuffInfoManager;
import com.centit.sys.util.IdGen;
import com.centit.sys.util.SysParametersUtils;
import com.centit.webservice.util.PDFUtil;

public class OptStuffInfoManagerImpl extends BaseEntityManagerImpl<OptStuffInfo>  implements OptStuffInfoManager {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private OptStuffInfoDao optStuffInfoDao;

    public void setOptStuffInfoDao(OptStuffInfoDao optStuffInfoDao) {
        this.optStuffInfoDao = optStuffInfoDao;
        setBaseDao(optStuffInfoDao);
    }

    @Override
    public OptStuffInfo getObjectById_SortId(String djId, String sortId) {
        // TODO Auto-generated method stub
        return optStuffInfoDao.getObjectById_SortId(djId,sortId);
    }
    public OptStuffInfo getStuffInfoByArchiveType(String djId, String archiveType){
        
        return optStuffInfoDao.getStuffInfoByArchiveType(djId,archiveType);
    }

   public List<OptStuffInfo> getZwfjStuffInfo(String djId){
        
        return optStuffInfoDao.listZwclStuffs(djId);
    }
   public List<OptStuffInfo> getStuffInfoList(String djId){
       
       return optStuffInfoDao.getStuffInfoListByFileType(djId,null);
   }

   //支持附件管理删除办件使用
   @Override
   public void deleteObjectBanInfo(String djId) {
       optStuffInfoDao.deleteObjectBanInfo(djId);
   }

   @Override
   public String getStuffNameById(String stuffId) {
       // TODO Auto-generated method stub
       OptStuffInfo optStuffInfo=optStuffInfoDao.getObjectById(stuffId);
       if(null!=optStuffInfo){
           return  optStuffInfo.getFilename();
        
       }
       return "";
   }

   @Override
   public List<OptStuffInfo> getStuffInfoList(String djId, long nodeinstid) {
       Map<String, Object> filterMap =new HashMap<String, Object>();
       filterMap.put("djId", djId);
       filterMap.put("nodeinstid", nodeinstid);
       return optStuffInfoDao.listObjects(filterMap);
   }

   /**
    * 下载签批附件
    * @param layerUrl
    * @param pdfStoreDir
    * @return
    */
   @Override
   public List<String> downloadFiles(String layerUrl,String pdfStoreDir){
       //下载签批附件到本地与原附件上传目录一样
       String downloadPdfLayerPath = PDFUtil.downloadFiles(layerUrl, pdfStoreDir);
       
       File pdfLayer = new File(downloadPdfLayerPath);
       List<String> layerPathArr = new ArrayList<String> ();
       //判断下载后的签批附件是否是压缩包
       if(pdfLayer.getName().endsWith(".zip")){
           layerPathArr = PDFUtil.ectractQP(downloadPdfLayerPath, pdfLayer.getParent());//对下载后的图层解压处理
       }else{
           layerPathArr.add(downloadPdfLayerPath);
       }
       return layerPathArr;
   }
   
   @Override
   public String assignFilesStorePathOnDisk(String djId,Long nodeInstd){
       String dir = getFilesParentPath(djId,nodeInstd);
       return dir +  File.separator + appendFileSuffix(IdGen.uuid(), 2);
   }

   private String getFilesParentPath(String djId, Long nodeInstd) {
       String root = SysParametersUtils.getWorkflowAffixHome();//签批附件存放的跟目录
       String type = djId.replaceAll("\\d+", "").toLowerCase();
       
       String baseDirPath = root + File.separator + type + File.separator + djId;
       /*File dir = null;
       String resDirPath = "";
       
       do{
            resDirPath = baseDirPath + File.separator + System.currentTimeMillis();
            dir = new File(resDirPath);
       }while(dir.exists());*/
       
       return baseDirPath;
   }
   
   /**
    * 
    * @param name
    * @param fileType 1-word 2-pdf
    * @return
    */
   private String appendFileSuffix(String name,int fileType){
       String suffix = "";
       switch (fileType) {
       case 1:
           suffix = ".doc";
           break;
       case 2:
           suffix = ".pdf";
           break;
       case 3:
           suffix = ".swf";
           break;
       case 4:
           suffix = ".html";
           break;
       default:
           break;
       }
       
       return name + suffix;
   }

@Override
public OptStuffInfo ZwStuffs(String djid) {
    // TODO Auto-generated method stub
    return optStuffInfoDao.ZwStuffs(djid);
}

@Override
public OptStuffInfo ZwPDFStuffs(String djid) {
    // TODO Auto-generated method stub
    return optStuffInfoDao.ZwPDFStuffs(djid);
}
}

