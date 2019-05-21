package com.centit.dispatchdoc.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VDispatchDocList;

public interface VDispatchDocListManager extends
        BaseEntityManager<VDispatchDocList> {
    /**
     * 发文列表（可按部门编号unitcode查询）
     * @param filterMap
     * @param pageDesc
     * @return
     */
        List<VDispatchDocList> listDispatchDoc(Map<String, Object> filterMap,
                PageDesc pageDesc);
        /**
         * 发文列表（可按部门编号unitcode查询）
         * 可查询全厅或部门 发文
         * @param filterMap
         * @param pageDesc
         * @return
         */  
        List<VDispatchDocList> listDispatchDocV2(Map<String, Object> filterMap,
                PageDesc pageDesc);
        
        /**
         * 获取相邻的djId
         * @param params
         * @return
         */
        public List<String> findNeighbouringDjId(Map<String,Object> params,String currDjId);
        /**
         * 发文列表（去除分页）
         * @param filterMap
         * @return
         */ 
        public List<VDispatchDocList> listDispatchDocForExcel(
                Map<String, Object> filterMap);
}
