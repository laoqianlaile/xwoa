package com.centit.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.app.dao.OuterNetFileinfoFsDao;
import com.centit.app.po.OuterNetFileinfoFs;
import com.centit.app.service.OuterNetFileinfoFsManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.support.searcher.DocDesc;
import com.centit.sys.dao.TaskLogDao;
import com.centit.sys.service.SchedulerManager;
import com.centit.sys.util.CommonCodeUtil;
import com.centit.sys.util.SysParametersUtils;

/**
 * TODO Class description should be added
 *
 * @author zk
 * @create 2013-3-6
 */
public class OuterNetFileinfoFsManagerImpl extends BaseEntityManagerImpl<OuterNetFileinfoFs>
        implements OuterNetFileinfoFsManager/*, IQuartzJobBean*/ {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OuterNetFileinfoFsManagerImpl.class);

    //private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

    private TaskLogDao taskLogDao;

    private SchedulerManager schedulerManager;

    public void setSchedulerManager(SchedulerManager schedulerManager) {
        this.schedulerManager = schedulerManager;
    }

    public void setTaskLogDao(TaskLogDao taskLogDao) {
        this.taskLogDao = taskLogDao;
    }

    private OuterNetFileinfoFsDao fileinfoFsDao;
    
    

    public void setFileinfoFsDao(OuterNetFileinfoFsDao fileinfoFsDao) {
        this.fileinfoFsDao = fileinfoFsDao;
        setBaseDao(this.fileinfoFsDao);
    }

    static private SimpleDateFormat pathDF = new SimpleDateFormat("yyyyMMdd");
    static private SimpleDateFormat nameDF = new SimpleDateFormat("yyyyMMddHHmmss");

    public String getNextKey() {
        return fileinfoFsDao.getNextValueOfSequence("S_FILENO");
    }


    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#saveFileinfo(com.centit.app.po.Fileinfo, java.io.InputStream, java.lang.String)
     */
    @Override
    public OuterNetFileinfoFs saveFileinfo(OuterNetFileinfoFs info, InputStream is, String optID) throws IOException {
        String path = File.separator + optID;
        path = getFilePath(path, pathDF.format(new Date()));

        // 获取文件路径
        String fullPath = getFullFilePath(path);

        File root = new File(fullPath);
        if (!root.exists()) {
            FileUtils.forceMkdir(root);
        }

        String filename = nameDF.format(new Date()) + "_" + getFileName(info.getFilename(), info.getFileext());
        File file = new File(getFilePath(fullPath, filename));


        // 创建HTML文件夹，用于放office文档对应的html文档
        //this.createFolderOfficeToHtml(fullPath);

        // 复制文件
        FileUtils.copyInputStreamToFile(is, file);

        // 保存文件对象
        info.setFilesize(file.length());
        info.setFilecode(getNextKey());
        info.setPath(getFilePath(path, filename));
        fileinfoFsDao.saveObject(info);

        return info;
    }

    // 创建HTML文件夹，用于放office文档对应的html文档
    public void createFolderOfficeToHtml(String fullPath){

        File htmlFile = new File(fullPath + File.separator + CommonCodeUtil.PUBLICINFO_HTML);

        htmlFile.mkdir();
    }


    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#saveFileinfo(com.centit.app.po.Fileinfo, java.io.File)
     */
    @Override
    public OuterNetFileinfoFs saveFileinfo(OuterNetFileinfoFs info, File file) {

        String filename = file.getName();

        info.setFilename(FilenameUtils.getBaseName(filename));
        info.setFileext(FilenameUtils.getExtension(filename));

        // 重写路径，去除上传文件路径前缀
        info.setPath(StringUtils.replace(FilenameUtils.normalize(file.getPath()), SysParametersUtils.getUploadHome(), ""));
        info.setFilesize(file.length());
        info.setFilecode(getNextKey());

        info.setIndb("0");
        info.setInstid(0L);
        info.setIsindex("0");
        info.setRecorderDate(new Date());

        fileinfoFsDao.saveObject(info);
        return info;
    }


    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#update4ConfirmFileinfo(com.centit.app.po.Fileinfo)
     */
    @Override
    public OuterNetFileinfoFs update4ConfirmFileinfo(OuterNetFileinfoFs info, DocDesc docDesc) {

        info.setInstid(1L);
        fileinfoFsDao.saveObject(info);


        //二次确认后，添加任务日志
        JSONObject jsonObject = JSONObject.fromObject(docDesc);
        jsonObject.put("filecode", info.getFilecode());
        //jsonObject.put(IndexManagerImpl.INDEX_TYPE, IndexManagerImpl.INDEX_TYPE_ADD);

//        taskLogDao.saveObject(new TaskLog("FileToIndex", jsonObject.toString()));

        return info;
    }

    @Override
    public void update4ConfirmFileinfos(List<OuterNetFileinfoFs> infos, List<DocDesc> docDescs) {
        for (int i = 0; i < infos.size(); i++) {
            update4ConfirmFileinfo(infos.get(i), docDescs.get(i));
        }
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#deleteFileinfo(com.centit.app.po.Fileinfo)
     */
    @Override
    public boolean deleteFileinfo(OuterNetFileinfoFs info) {
        if (null == info) {
            return true;
        }


        try {
            info.setIsDelete("1");

       

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#deleteFileOnDisk(com.centit.app.po.Fileinfo)
     */
    @Override
    public boolean deleteFileOnDisk(OuterNetFileinfoFs info) {
        if (null == info) {
            return true;
        }

        String path = getFullFilePath(info.getPath());
        File file = new File(path);

        // 删除文件
        return FileUtils.deleteQuietly(file);
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#deleteFileOnDisk(java.lang.String)
     */
    @Override
    public boolean deleteFileOnDisk(String path) {
        String fullPath = getFullFilePath(path);
        File file = new File(fullPath);

        // 若文件不存在，则无需删除
        if (!file.exists()) return true;

        // 删除文件
        return FileUtils.deleteQuietly(file);
    }


    @Override
    public InputStream downloadFileinfo(OuterNetFileinfoFs info) throws IOException {
        File file = new File(getFullFilePath(info.getPath()));

        InputStream is = FileUtils.openInputStream(file);

        return is;
    }

    private static String getFileName(String filename, String fileext) {
        return StringUtils.isBlank(fileext) ? filename : (filename + "." + fileext);
    }

    private static String getFilePath(String path, String... filename) {
        String filePath = path;
        for (String name : filename) {
            filePath += File.separator + name;
        }

        return filePath;
    }
    
    public String getFullFilePath(String path) {
        return SysParametersUtils.getUploadHome() + path;
    }

}

