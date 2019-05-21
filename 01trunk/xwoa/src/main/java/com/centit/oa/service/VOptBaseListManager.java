package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.po.VoaBizBindInfo;

public interface VOptBaseListManager extends BaseEntityManager<VOptBaseList> 
{
    public List<VOptBaseList> listOptBaseInfo(Map<String,Object> filterMap, PageDesc pageDesc);
    /**
     * 根据分管部门设置，查看分管部门办件
     * @param filterMap
     * @param pageDesc
     * @return
     */
    public List<VOptBaseList> listOptBaseInfoLeader(
            Map<String, Object> filterMap, PageDesc pageDesc);
    public List<VOptBaseList> listNotVOptBaseList(String djId,String itemtype) ;
    public List<VOptBaseList> listVOptBaseList(String djId,String itemtype,String usercode); 
    /**
     * 根据djid获得办件
     * @param djId
     * @return
     */
    public VOptBaseList getObjectByDjId(String djId);
    
    /**
     * 判断人员是否直接参与办件过程
     * 根据djid usercode 唯一获得业务信息
     * 是否需要办件查看权限验证
     * @param djId
     * @param usercode
     * @return
     */
    public boolean isVailViewPower (String djId,String usercode);
    /**
     * 获取相邻项
     * @param filterMap
     * @param currDjId
     * @return
     */
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,
            String currDjId);
}
