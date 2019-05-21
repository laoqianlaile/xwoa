package com.centit.powerbase.util;

import java.util.List;
import java.util.Map;

import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Dataenterprise;
import com.centit.powerbase.po.Dataindividual;
/**
 * 针对组织结构、个人等基础信息操作的接口
 * TODO Class description should be added
 * 
 * @author tyj
 * @create 2013-9-24
 * @version
 */
public interface IDataBasicInfo {
    /**
     * 根据条件获取组织机构列表
     * @param filterMap 支持根据enterpriseid, application,lastmoddate等过滤
     * @param pageDesc 分页实体类参数
     * @return
     */
    List<Dataenterprise> getDataenterpriseList(Map<String, Object> filterMap, PageDesc pageDesc);
    /**
     * 根据条件获取组织机构列表
     * @param filterMap 支持根据enterpriseid, application,lastmoddate等过滤
     * @return
     */
    List<Dataenterprise> getDataenterpriseList(Map<String, Object> filterMap);
    /**
     * 根据条件获取组织机构列表
     * @param shql 自定义hql
     * @param filterMap 支持根据enterpriseid, application,lastmoddate等过滤
     * @param pageDesc 分页实体类参数
     * @return
     */
    List<Dataenterprise> getDataenterpriseList(String shql, Map<String, Object> filterMap,PageDesc pageDesc);
    /**
     * 根据条件获取人员列表
     * @param filterMap 支持根据individualid, application,lastmoddate等过滤
     * @param pageDesc 分页实体类参数
     * @return
     */
    List<Dataindividual> getDataindividualList(Map<String, Object> filterMap, PageDesc pageDesc);
    /**
     * 根据条件获取人员列表
     * @param filterMap 支持根据individualid, application,lastmoddate等过滤
     * @return
     */
    List<Dataindividual> getDataindividualList(Map<String, Object> filterMap) ;
    /**
     * 根据条件获取人员列表
     * @param shql 自定义hql
     * @param filterMap 支持根据individualid, application,lastmoddate等过滤
     * @param pageDesc 分页实体类参数
     * @return
     */
    List<Dataindividual> getDataindividualList(String shql, Map<String, Object> filterMap,PageDesc pageDesc);
    /**
     * 获取组织结构实体数据
     * @param enterpriseid
     * @return
     */
    Dataenterprise getDataenterprise(Long enterpriseid);
    /**
     * 获取人员实体数据
     * @param individualid
     * @return
     */
    Dataindividual getDataindividual(Long individualid);
    /**
     * 保存组织结构实体信息
     * @param enterprise
     */
    void saveDataenterprise(Dataenterprise enterprise);
    /**
     * 保存个人实体信息
     * @param individual
     */
    void saveDataenterprise(Dataindividual individual);    
}
