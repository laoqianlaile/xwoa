package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaPowerrolergroup;

public interface OaPowerrolergroupManager extends
        BaseEntityManager<OaPowerrolergroup> {
    /**
     * 获取系统所有人员及个人分组，公共分组
     * 
     * @param usercode
     * @return
     */
    public JSONArray putAllUserTree(String usercode);
    /**
     *获取系统所有部门人员JSONArray
     * 
     * @param usercode
     * @return
     */
    public JSONArray putUnitsUsersTree();
    
    /**
     * 获取系统所有部门JSONArray
     * @return
     */
    public JSONArray putUnitsTree();

    /**
     * 获取当前人员所在部门及下级部门人员及个人分组，公共分组
     * 
     * @param usercode
     * @return
     */
    public JSONArray putSubUnitsTree(String usercode);

    /**
     * 获取系统所有人员List
     * 
     * @param usercode
     * @return
     */
    public List<Map<String, String>> setUnitUser();
    
    /**
     * 获取当前人员所在部门及下级部门人员List
     * 
     * @param usercode
     * @return
     */

    public List<Map<String, String>> setSubUnitsUser(String usercode);

    /**
     * 根据部门编码获取该部门下所有员工数据
     * @param unitCode
     * @return
     */
    public List<Map<String, String>> setSubUnitsUserByUnitCode(String unitCode);
    /**
     * 个人分组，公共分组List
     * @param usercode
     * @return
     */
    public List<Map<String, String>> setTreeGrouple(String usercode);
    
    /**
     * 获取当前部门的所有下级部门（包含当前部门）
     * @param unitcode 当前部门编码
     */
    public List<Map<String,String>> putSubUnits(String unitcode);    
    
    /**
     * 将按照岗位分类的人员转成JsonArray格式
     */
    public JSONArray getStationtUsersTree(String[] stationsChosen) ;
    /**
     * 将按照分管领导分类的人员转成JsonArray格式
     */
    public JSONArray getUnitLeaderUsersTree();
    
    /**
     * 获取当前部门的所有下级部门（包含当前部门）并转换成JsonArray格式
     * @param unitcode 当前部门编码
     */
    public JSONArray getSubUnits(String unitcode);
    
    /**
     * 获取当前部门的所有下级部门（包含当前部门）并转换成JsonArray格式
     * @param unitcode 当前部门编码
     */
    public JSONArray getSubUnitsFWFJ(String unitcode);//发文分级配置获取部门
    /**
     * 获取党员
     * @return
     */
    public JSONArray getDyUnitsUsersTree();  
    /**
     * 获取系统所有人员List
     * 
     * @param usercode
     * @return
     */
    public List<Map<String, String>> setCommonUser(String loginName);
    /**
     * 获取系统所有人员List
     * 
     * @param usercode
     * @return
     */
    public List<Map<String, String>> setRankUser();
}
