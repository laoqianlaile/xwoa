package com.centit.app.service.impl;

import com.centit.app.dao.FileinfoFsDao;
import com.centit.app.po.FileinfoFs;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.service.FileinfoManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.support.searcher.DocDesc;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.IQuartzJobBean;
import com.centit.sys.service.SchedulerManager;
//import com.centit.sys.service.impl.IndexManagerImpl;
import com.centit.sys.util.CommonCodeUtil;
import com.centit.sys.dao.TaskLogDao;
import com.centit.sys.po.TaskLog;
import com.centit.sys.util.SysParametersUtils;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO Class description should be added
 *
 * @author zk
 * @create 2013-3-6
 */
public class FileinfoFsManagerImpl extends BaseEntityManagerImpl<FileinfoFs>
        implements FileinfoFsManager/*, IQuartzJobBean*/ {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(FileinfoManager.class);

    //private static final SysOptLog sysOptLog = SysOptLogFactoryImpl.getSysOptLog();

    private TaskLogDao taskLogDao;

    private SchedulerManager schedulerManager;

    public void setSchedulerManager(SchedulerManager schedulerManager) {
        this.schedulerManager = schedulerManager;
    }

    public void setTaskLogDao(TaskLogDao taskLogDao) {
        this.taskLogDao = taskLogDao;
    }

    private FileinfoFsDao fileinfoFsDao;
    
    

    public void setFileinfoFsDao(FileinfoFsDao fileinfoFsDao) {
        this.fileinfoFsDao = fileinfoFsDao;
        setBaseDao(this.fileinfoFsDao);
    }

    static private SimpleDateFormat pathDF = new SimpleDateFormat("yyyyMMdd");
    static private SimpleDateFormat nameDF = new SimpleDateFormat("yyyyMMddHHmmss");

    public String getNextKey() {
        /*String sKey = "00000000000"+
                fileinfoDao.getNextValueOfSequence("S_FILENO");
        return df.format(new Date()) + sKey.substring(sKey.length()-10);*/

        return fileinfoFsDao.getNextValueOfSequence("S_FILENO");
    }


    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#saveFileinfo(com.centit.app.po.Fileinfo, java.io.InputStream, java.lang.String)
     */
    @Override
    public FileinfoFs saveFileinfo(FileinfoFs info, InputStream is, String optID) throws IOException {
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
    public FileinfoFs saveFileinfo(FileinfoFs info, File file) {

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
    public FileinfoFs update4ConfirmFileinfo(FileinfoFs info, DocDesc docDesc) {

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
    public void update4ConfirmFileinfos(List<FileinfoFs> infos, List<DocDesc> docDescs) {
        for (int i = 0; i < infos.size(); i++) {
            update4ConfirmFileinfo(infos.get(i), docDescs.get(i));
        }
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#deleteFileinfo(com.centit.app.po.Fileinfo)
     */
    @Override
    public boolean deleteFileinfo(FileinfoFs info) {
        if (null == info) {
            return true;
        }

//        String path = getFullFilePath(info.getPath());
//        File file = new File(path);

        try {
//            fileinfoDao.deleteObject(info);
            info.setIsDelete("1");
//            TaskLogDao.saveObject(info);

            //删除时建立任务日志，通知更新索引
//            DocDesc docDesc = new DocDesc(info.getOptcode() + "_" + info.getFilecode());
//            JSONObject jsonObject = JSONObject.fromObject(docDesc);
//            jsonObject.put(IndexManagerImpl.INDEX_TYPE, IndexManagerImpl.INDEX_TYPE_DEL);
//
//            taskLogDao.saveObject(new TaskLog("FileToIndex", jsonObject.toString()));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see com.centit.app.service.FileinfoManager#deleteFileOnDisk(com.centit.app.po.Fileinfo)
     */
    @Override
    public boolean deleteFileOnDisk(FileinfoFs info) {
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
    public InputStream downloadFileinfo(FileinfoFs info) throws IOException {
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

//    @Override
//    public void initTimerTask() {
//        JobDetail jobDetail = schedulerManager.getJobDetail("Job_Fileinfo", "Job_Group_Fileinfo");
//        jobDetail.getJobDataMap().put(QUARTZ_JOB_BEAN_KEY, this);
//
//
//        String cronEx = "0 0 23 * * ?";
//
//        try {
//            schedulerManager.schedule(jobDetail, "Trigger_Fileinfo", "Trigger_Group_Fileinfo", cronEx);
//        } catch (Exception e) {
//            log.error("表达式错误 " + cronEx, e);
//        }
//    }

//    @Override
//    public void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
//        Map<String, Object> filterMap = new HashMap<String, Object>();
//        filterMap.put("isDelete", "1");
//        filterMap.put("beforeRecorderDate", 2);
//        List<Fileinfo> fileinfos = baseDao.listObjects(filterMap);
//
//        for (Fileinfo fileinfo : fileinfos) {
//            baseDao.deleteObject(fileinfo);
//
//            deleteFileOnDisk(fileinfo);
//        }
//
//    }
}

