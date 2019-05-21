package com.centit.powerbase.util;


/**
 * 
 * 记录监察操作日志接口
 * 
 * @author tyj
 * @create 2013-5-30
 * @version
 */
public interface IMonitorOptLog {

    /**
     * 记录系统日志
     * 
     * @param usercode
     *            用户代码
     * @param optId
     *            F_OPTINFO表中 OPTID 字段
     * @param tagId
     *            一般用于关联到业务主体[业务主体主键] 如记录办件编号、投诉编号、预报警编号、督查督办编号等
     *            针对联合主键用"#"分隔
     * @param optMethod
     *            记录系统日志的方法名
     * @param optContent
     *            记录操作内容
     * @param oldValue
     *            更新操作时，更新前数据
     * @param bjType
     *            记录监察日志类型： 1许可 2处罚 3投诉 4 预报警 5 督查督办 9其他
     * 
     */
    void log(String usercode, String optId, String tagId, String optMethod,
            String optContent, String oldValue, String bjType);
    
    //缺失optId：非功能性日志记录
    void log(String usercode, String tagId, String optContent, String oldValue, String bjType);
    
    //缺失oldValue：新增操作日志记录
    void log(String usercode, String tagId, String optContent, String bjType);

  
    
}
