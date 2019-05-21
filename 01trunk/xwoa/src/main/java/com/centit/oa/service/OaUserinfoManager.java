package com.centit.oa.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaUserinfo;
import com.centit.sys.po.FUserinfo;

public interface OaUserinfoManager extends BaseEntityManager<OaUserinfo> 
{
    //查询出部门下面的人员
    public List<FUserinfo> getUserUnderUserUnit(Map<String, Object> filterMap, String bodycode);
    /**
     * 查询所有用户
     */
    public List<OaUserinfo> listOauserinfoObjects(Map<String, Object> filterMap);

    /**
     * 查询用户全信息
     * @param filterMap
     * @return
     */
    public List<OaUserinfo> listUserInfos(Map<String, Object> filterMap);
}
