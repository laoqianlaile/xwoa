package com.centit.sys.util;

import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.centit.support.file.PropertiesReader;

/**
 * 
 * 系统外置目录路径工具类
 * 
 * @author sx
 * @create 2012-12-7
 * @version
 */
public class SysParametersUtils {

    private static Properties prop;

    /**
     * 
     * 常用参数在此添加对应枚举，并在下面添加对应静态方法
     * 
     * @author sx
     * @create 2012-12-7
     * @version
     */
    public static enum Parameters {
        APP_HOME("app.home"), LOG_HOME("log"), UPLOAD_HOME("upload"), PUBLIC_FILE("publicfile"), INDEX_HOME("index"), TEMP("temp"),PDF("pdf"),
        MAILAFFIX("mailaffix"),WORKFLOWAFFIX("workflowaffix"),INFOS_HOME("infos"),FILING_CABINETS_HOME("cabinets");

        private String value;

        private Parameters(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 获取上传文件临时目录
     * 
     * @return
     */
    public static String getUploadTempHome() {
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.UPLOAD_HOME) + getParameters(Parameters.TEMP));
    }
    
    /**
     * 获取日志目录
     * 
     * @return
     */
    public static String getLogHome() {
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.LOG_HOME));
    }

    /**
     * 获取上传文件目录
     * 
     * @return
     */
    public static String getUploadHome() {
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.UPLOAD_HOME));
    }

    /**
     * 获取索引文件目录
     * 
     * @return
     */
    public static String getIndexHome() {
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.INDEX_HOME));
    }
    /**
     * 获取pdf存储目录
     * @return
     */
    public static String getUploadPdfHome(){
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.UPLOAD_HOME) + getParameters(Parameters.PDF));
    }
    /**
     * 获取邮箱附件存储目录
     * @return
     */
    public static String getMailAffixHome(){
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.UPLOAD_HOME) + getParameters(Parameters.MAILAFFIX));
    }
    /**
     * 流程中附件存储目录
     * @return
     */
    public static String getWorkflowAffixHome(){
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.UPLOAD_HOME) + getParameters(Parameters.WORKFLOWAFFIX));
    }
    /**
     * 获取资料信息存储目录
     * @return
     */
    public static String getInfosHome(){
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.UPLOAD_HOME) + getParameters(Parameters.INFOS_HOME));
    }
    /**
     * 获取电子文件柜目录
     * @return
     */
    public static String getFilingCabinetsHome(){
        return FilenameUtils.normalize(getParameters(Parameters.APP_HOME) + getParameters(Parameters.UPLOAD_HOME) + getParameters(Parameters.FILING_CABINETS_HOME));
    }
    /**
     * 公共文件夹
     * 
     * @return
     */
    public static String getPublicFileHome() {
        return FilenameUtils.normalize(getUploadHome() + getParameters(Parameters.PUBLIC_FILE));
    }
//    SysParametersUtils.class.getResource("/").getPath()
    public static String getClassLoader() {

        return SysParametersUtils.class.getResource("/").getPath().toString();
    }

    
    public static String getParameters(Parameters p) {
        return getResource().getProperty(p.getValue());
    }
    
    public static String getParameters(String key,String nullDefault){
        String value = getResource().getProperty(key);
        return StringUtils.defaultIfEmpty(value, nullDefault);
    }
    
    private static Properties getResource() {
        if (null == prop) {
            prop = PropertiesReader.getClassPathProperties("system.properties");
        }

        return prop;
    }

    public static void main(String[] args) {
        System.out.println(getResource());
    }

}
