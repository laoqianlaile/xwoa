package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaMeetinfo;
import com.centit.sys.po.FUserinfo;

public interface OaMeetinfoManager extends BaseEntityManager<OaMeetinfo> {

    /**
     * 获取不带大字段的列表
     * 
     * @param filterMapHys
     * @return
     */
    public List<OaMeetinfo> listObjectsWithOutLob(
            Map<String, Object> filterMapHys);

    /**
     * 查询不带不字段的
     * 
     * @param picMap
     * @return
     */
    public List<OaMeetinfo> listObjectsWithOutLOB(Map<String, Object> picMap);

    /**
     * 查询不带不字段的
     * @param djid
     * @return
     */
    public OaMeetinfo getObjectByIdWithOutLOB(String djid);


}
