package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaMeetingmaterialinfos;

public interface OaMeetingmaterialinfosManager extends BaseEntityManager<OaMeetingmaterialinfos> 
{
    public List<OaMeetingmaterialinfos> findStuffIdByCode(String meetcode,String djid);
    /**
     * 根据业务id，参与人编码，和初始附件编码定位记录
     * @param djId
     * @param usercode
     * @param initalStuffId
     * @return
     */
    public OaMeetingmaterialinfos findObjectBy(String djId,String usercode,String initalStuffId);
    public void deleteBydjId(String djId);
    /**
     * 根据初始附件id，删除汇总表
     * @param initalStuffId
     */
    public void deleteByInitalStuffId(String initalStuffId);
    /**
     * 根据业务id获取所有记录
     * @param djId
     * @return
     */
    public List<OaMeetingmaterialinfos> findListByDjId(String djId);
}
