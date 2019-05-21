package com.centit.efile.mgt;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.centit.efile.model.EfileStore;
import com.centit.sys.util.IdGen;

/**
 * 
 * 电子文件磁盘管理
 * 
 * @author lay
 * @create 2016年6月2日
 * @version
 */
public class EfileManager {
    
    /**
     * 存储文件
     * @param efileStore
     * @throws IOException
     */
    public static void store(EfileStore efileStore) throws IOException{
        if(efileStore == null)
            throw new IllegalArgumentException("参数对象为空");
        
        if((efileStore.getContent() == null && EfileStore.SRC_PARAM_TYPE_FILE.equals(efileStore.getSrcParamType()))
                ||(efileStore.getContentBytes() == null && EfileStore.SRC_PARAM_TYPE_BYTES.equals(efileStore.getSrcParamType())))
            throw new IllegalArgumentException("存储内容为空");
        
        if(efileStore.getOriginalName() == null || "".equals(efileStore.getOriginalName()))
            throw new IllegalArgumentException("存储对象的名称为空");
        
        if(efileStore.getStoreDir() == null || "".equals(efileStore.getStoreDir()))
            throw new IllegalArgumentException("存储对象的存放目录为空");
        
        if(efileStore.getStoreName() == null || "".equals(efileStore.getStoreName())){
            String storeName = IdGen.uuid()+efileStore.getExtName(true);
            efileStore.setStoreName(storeName);
        }
        createDirIfNoExist(efileStore.getStoreDir());
        
        if(EfileStore.SRC_PARAM_TYPE_FILE.equals(efileStore.getSrcParamType())){
            File saveFile = new File(efileStore.getAbsolutePath());
            FileUtils.copyFile(efileStore.getContent(), saveFile);
        }
        if(EfileStore.SRC_PARAM_TYPE_BYTES.equals(efileStore.getSrcParamType())){
            BufferedOutputStream bos = null;  
            FileOutputStream fos = null;  
            File file = null;  
            try {  
                file = new File(efileStore.getAbsolutePath());  
                fos = new FileOutputStream(file);  
                bos = new BufferedOutputStream(fos);  
                bos.write(efileStore.getContentBytes());  
            } catch (IOException e) {  
                throw new IOException(e.getMessage());
            } finally {  
                if (bos != null) {  
                    try {  
                        bos.close();  
                    } catch (IOException e1) {}  
                }  
                if (fos != null) {  
                    try {  
                        fos.close();  
                    } catch (IOException e1) {}  
                }  
            } 
        }
    }
    
    /**
     * 复制文件到新的目录
     * @param srcPath
     * @param targetDir
     * @return 目标文件路径
     * @throws IOException 
     */
    public static String copyFileToDir(String srcPath,String targetDir) throws IOException{
        File srcFile = new File(srcPath);
        if(!srcFile.exists()){
           throw new RuntimeException("目标文件不存在");    
        }
        createDirIfNoExist(targetDir);
        String extName = getExtName(srcFile.getName(),true);
        String fileName = IdGen.uuid() + extName;
        String newFilePath = targetDir + File.separator +  fileName;
        
        FileUtils.copyFile(srcFile, new File(newFilePath));
        
        return newFilePath;
    }
    
    /**
     * 获取扩展名
     * @param filename
     * @param includePoint
     * @return
     */
    public static String getExtName(String filename,boolean includePoint){
        if(filename != null && !"".equals(filename)){
            int startIndex = filename.lastIndexOf(".");
            if(!includePoint){
                startIndex++;
            }
            return filename.substring(startIndex).toLowerCase();
        }
        return "";
     }
    
    /**
     * 删除文件
     * @param absolutePath
     */
    public static void remove(String absolutePath){
        if(absolutePath == null)
            throw new IllegalArgumentException("删除的文件路径为空");
        
        File file = new File(absolutePath);
        if (file.exists()){
            FileUtils.deleteQuietly(file);
        }
    }
    
    /**
     * 将文件以字节数组的形式读出
     * @param absolutePath
     * @return
     */
    public static byte[] read(String absolutePath){
        byte[] buffer = null;  
        try {  
            File file = new File(absolutePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {  
            throw new RuntimeException(e.getMessage());
        }  
        return buffer;  
    }
    
    /**
     * 如果目录不存在就创建目录
     * @param dirPath
     */
    public static void createDirIfNoExist(String dirPath){
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
    }
}
