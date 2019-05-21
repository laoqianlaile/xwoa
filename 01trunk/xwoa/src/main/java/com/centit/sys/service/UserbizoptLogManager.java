package com.centit.sys.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.security.FUserDetail;

public interface UserbizoptLogManager extends BaseEntityManager<UserbizoptLog> 
{
    /**
     * 查询
     * @param djId
     * @param usercode
     * @param nodeinstid
     * @return UserbizoptLog
     */
    public UserbizoptLog listObject(String djId,String usercode,Long nodeinstid);
    /**
     * 查询
     * @param djId
     * @param usercode
     * @param remark
     * @return UserbizoptLog
     */
    public UserbizoptLog listObject(String djId,String usercode,String remark);
    /**
     * 查询
     * @param djId
     * @param usercode
     * @return UserbizoptLog
     */
    public UserbizoptLog listObject(String djId,String usercode); 
    /**
     * 查询排除usercode的记录
     * @param djId
     * @param usercode
     * @param nodeinstid
     * @return
     */
    public  List<UserbizoptLog> listObjectNotSelf(String djId, String usercode,Long nodeinstid);
    /**
     * 保存记录
     * @param djId
     * @param bizname
     * @param fuser
     * @param nodeinstid
     */
    public void saveUserbizoptLog(String djId,String bizname,FUserDetail fuser,Long nodeinstid);
    /**
     * 移除已阅读记录
     * @param l
     * @return list
     */
    public List<Object> remaveObjectList(List<Object> l);
    /**
     * 保存记录
     * @param u
     * @param fuser
     */
    public void saveUserbizoptLog(UserbizoptLog u,FUserDetail fuser);
}
